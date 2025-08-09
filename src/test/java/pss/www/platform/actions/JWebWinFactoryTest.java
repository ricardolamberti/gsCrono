package pss.www.platform.actions;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.cocoon.environment.Request;

import pss.core.services.records.JBaseRecord;
import pss.core.win.JBaseWin;
import pss.core.win.actions.BizAction;
import pss.core.win.submits.JAct;

public class JWebWinFactoryTest {

    static class StubWin extends JBaseWin {
        JBaseRecord rec = new JBaseRecord();
        BizAction action;
        @Override
        public JBaseRecord ObtenerBaseDato() {
            return rec;
        }
        @Override
        public BizAction findActionByUniqueId(String zActId) {
            return action;
        }
    }

    static class TestWebWinFactory extends JWebWinFactory {
        Map<JBaseWin,String> winToId = new HashMap<>();
        Map<String,JBaseWin> idToWin = new HashMap<>();
        int counter = 0;
        TestWebWinFactory() { super(null); }
        @Override
        public String baseWinToURL(JBaseWin win) throws Exception {
            String id = winToId.get(win);
            if (id == null) {
                id = "W" + counter++;
                winToId.put(win, id);
                idToWin.put(id, win);
            }
            return id;
        }
        @Override
        public JBaseWin getRegisterObjectTemp(String key) throws Exception {
            byte[] decoded;
            try {
                decoded = JWebRequest.b64urlDecode(key);
            } catch (IllegalArgumentException e) {
                decoded = Base64.getDecoder().decode(key);
            }
            String id = new String(decoded, StandardCharsets.UTF_8);
            return idToWin.get(id);
        }
        @Override
        public JBaseRecord getRegisterObjectRecTemp(String key) throws Exception {
            return null;
        }
    }

    private static JWebRequest prepareRequest() throws Exception {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                if ("getLocale".equals(method.getName())) {
                    return Locale.getDefault();
                }
                return null;
            }
        };
        Request req = (Request) Proxy.newProxyInstance(Request.class.getClassLoader(), new Class<?>[]{Request.class}, handler);
        return new JWebRequest(req, null);
    }

    private static StubWin newWin(TestWebWinFactory factory) throws Exception {
        StubWin win = new StubWin();
        factory.baseWinToURL(win); // register and assign id
        return win;
    }

    private static void assertTrue(boolean cond, String msg) {
        if (!cond) {
            throw new AssertionError(msg);
        }
    }

    public static void main(String[] args) throws Exception {
        JWebRequest req = prepareRequest();
        TestWebWinFactory factory = new TestWebWinFactory();

        // case 1: i + o
        StubWin owner1 = newWin(factory);
        BizAction a1 = new BizAction();
        a1.SetIdAction("1");
        a1.setObjOwner(owner1);
        owner1.action = a1;
        String url1 = factory.convertActionToURL(a1);
        Map<String,String> map1 = req.deserializeRegisterMapJSON(url1);
        assertTrue(map1.containsKey("i") && map1.containsKey("o"), "i+o keys");
        assertTrue(!map1.containsKey("r") && !map1.containsKey("a"), "no r/a");
        BizAction back1 = factory.convertURLToAction(url1);
        assertTrue(back1 == a1, "action reference");

        // case 2: i + o + r
        StubWin owner2 = newWin(factory);
        BizAction a2 = new BizAction();
        a2.SetIdAction("2");
        a2.setObjOwner(owner2);
        owner2.action = a2;
        StubWin result2 = newWin(factory);
        JAct submit2 = new JAct(result2) {};
        a2.setObjSubmit(submit2);
        String url2 = factory.convertActionToURL(a2);
        Map<String,String> map2 = req.deserializeRegisterMapJSON(url2);
        assertTrue(map2.containsKey("r"), "r key present");
        BizAction back2 = factory.convertURLToAction(url2);
        assertTrue(back2.getObjSubmit().getResult() == result2, "result restored");

        // case 3: full a
        StubWin owner3 = newWin(factory);
        BizAction a3 = new BizAction() {
            @Override
            public boolean needsFullSerialization() { return true; }
        };
        a3.SetIdAction("3");
        a3.setObjOwner(owner3);
        owner3.action = a3;
        String url3 = factory.convertActionToURL(a3);
        Map<String,String> map3 = req.deserializeRegisterMapJSON(url3);
        assertTrue(map3.containsKey("a"), "a key present");
        BizAction back3 = factory.convertURLToAction(url3);
        assertTrue(back3 != a3 && back3.getIdAction().equals("3"), "full deserialization");

        // case 4: legacy
        StubWin owner4 = newWin(factory);
        BizAction a4 = new BizAction();
        a4.SetIdAction("4");
        a4.setObjOwner(owner4);
        owner4.action = a4;
        String token = factory.baseWinToURL(owner4);
        Map<String,String> legacy = new HashMap<>();
        legacy.put("actionid", a4.getIdAction());
        legacy.put("owner", Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8)));
        String legacyUrl = req.serializeRegisterMapJSON(legacy);
        BizAction back4 = factory.convertURLToAction(legacyUrl);
        assertTrue(back4 == a4, "legacy owner resolved");

        System.out.println("All tests passed.");
    }
}
