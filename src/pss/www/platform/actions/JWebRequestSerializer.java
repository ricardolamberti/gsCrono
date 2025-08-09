package pss.www.platform.actions;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pss.core.tools.JTools;
import pss.core.tools.PssLogger;
import pss.core.win.JBaseWin;

/**
 * Utility class for serialization and deserialization of request related objects.
 */
public final class JWebRequestSerializer {

    private JWebRequestSerializer() {
        // Utility class
    }

    public static String serializeRegisterMapJSON(Map<String, String> pack) {
        Gson gson = new Gson();
        String serializedMap = gson.toJson(pack);
        return Base64.getEncoder().encodeToString(JTools.stringToByteArray(serializedMap));
    }

    public static Map<String, String> deserializeRegisterMapJSON(String serializedDictionary) {
        Map<String, String> map = new TreeMap<>();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        map = gson.fromJson(JTools.byteVectorToString(Base64.getDecoder().decode(serializedDictionary)), type);
        return map;
    }

    public static String serializeRegisterJSON(JWebRequest.JWebRequestPackage pack) {
        Gson gson = new Gson();
        String serializedMap = gson.toJson(pack);
        return Base64.getEncoder().encodeToString(JTools.stringToByteArray(serializedMap));
    }

    public static JWebRequest.JWebRequestPackage deserializeRegisterJSON(String serializedDictionary) {
        Gson gson = new Gson();
        Type type = new TypeToken<JWebRequest.JWebRequestPackage>() {}.getType();
        return gson.fromJson(JTools.byteVectorToString(Base64.getDecoder().decode(serializedDictionary)), type);
    }

    private static final List<Class<?>> ALLOWED_TYPES = Arrays.asList(
            String.class,
            Integer.class,
            Long.class,
            Short.class,
            Double.class,
            Float.class,
            Boolean.class,
            Map.class,
            java.util.List.class);

    private static boolean isAllowed(Class<?> cls) {
        for (Class<?> allowed : ALLOWED_TYPES) {
            if (allowed.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    private static class JsonPayload {
        String type;
        String json;

        JsonPayload(String type, String json) {
            this.type = type;
            this.json = json;
        }
    }

    public static String toJson(Serializable obj) throws IOException {
        if (obj == null) return null;
        if (!isAllowed(obj.getClass())) {
            throw new IOException("Type not allowed: " + obj.getClass());
        }
        Gson gson = new Gson();
        JsonPayload payload = new JsonPayload(obj.getClass().getName(), gson.toJson(obj));
        String json = gson.toJson(payload);
        return Base64.getEncoder().encodeToString(JTools.stringToByteArray(json));
    }

    public static Serializable fromJson(String serializedObj) {
        try {
            if (serializedObj == null) return null;
            String payloadStr = JTools.byteVectorToString(Base64.getDecoder().decode(serializedObj));
            Gson gson = new Gson();
            JsonPayload payload = gson.fromJson(payloadStr, JsonPayload.class);
            Class<?> cls = Class.forName(payload.type);
            if (!isAllowed(cls)) {
                throw new ClassNotFoundException("Type not allowed: " + payload.type);
            }
            return (Serializable) gson.fromJson(payload.json, cls);
        } catch (Exception e) {
            PssLogger.logError(e);
            return null;
        }
    }

    public static String baseWinToSession(JBaseWin zOwner) throws Exception {
        return toJson(new JWebWinFactory(null).baseWinToJSON(zOwner));
    }
}
