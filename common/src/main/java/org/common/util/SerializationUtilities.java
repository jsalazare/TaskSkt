package org.common.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class SerializationUtilities {
	

	public static byte[] getBytes(Object message) {
        byte[]bytes;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(message);
            oos.flush();
            oos.reset();
            bytes = baos.toByteArray();
            oos.close();
            baos.close();
        } catch(IOException e){
            bytes = new byte[] {};
        }
        return bytes;
    }

    public static Object fromBytes(byte[] body) {
    	Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    
}
