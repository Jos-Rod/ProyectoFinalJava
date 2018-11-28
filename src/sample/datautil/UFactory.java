package sample.datautil;

import java.util.Hashtable;
import java.util.ResourceBundle;

public class UFactory { //Todos los objectos se almacenaran en instances, asi para crear un nuevo objeto, si ya esta creado, lo tomara de aqui

    //consiste en una llave y valor
    private static Hashtable<String, Object> instances = new Hashtable<>();

    public static Object getInstance(String objName) {
        Object obj = instances.get(objName);

        try {
            if (obj == null) {
                ResourceBundle rb = ResourceBundle.getBundle("factory");
                String sClassname = rb.getString(objName);
                obj = Class.forName(sClassname).newInstance();

                instances.put(objName, obj);
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
