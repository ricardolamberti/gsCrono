package pss.www.platform.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

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

    public static String serializeObject(Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static Serializable deserializeObject(String serializedObj) {
        try {
            if (serializedObj == null) return null;
            byte[] data = Base64.getDecoder().decode(serializedObj);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Serializable obj = (Serializable) ois.readObject();
            ois.close();
            return obj;
        } catch (ClassNotFoundException e) {
            PssLogger.logError(e);
        } catch (IOException e) {
            PssLogger.logError(e);
        }
        return null;
    }

    public static String baseWinToSession(JBaseWin zOwner) throws Exception {
        return serializeObject(new JWebWinFactory(null).baseWinToJSON(zOwner));
    }
}
