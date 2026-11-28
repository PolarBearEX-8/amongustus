package javazoom.jl.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

public class JavaLayerUtils {
   private static JavaLayerHook hook = null;

   public static synchronized void setHook(JavaLayerHook javaLayerHook) {
      hook = javaLayerHook;
   }

   public static synchronized InputStream getResourceAsStream(String str) {
      InputStream in = null;
      if (hook != null) {
         in = hook.getResourceAsStream(str);
      } else {
         in = JavaLayerUtils.class.getClassLoader().getResourceAsStream(str);
      }

      return in;
   }

   public static Object deserialize(InputStream in) throws IOException {
      if (in == null) {
         throw new NullPointerException("in");
      } else {
         ObjectInputStream objectInputStream = new ObjectInputStream(in);

         try {
            return objectInputStream.readObject();
         } catch (ClassNotFoundException e) {
            throw new InvalidClassException(e.toString());
         }
      }
   }

   public static Object deserializeArray(InputStream in, Class clazz, int n) throws IOException {
      if (clazz == null) {
         throw new NullPointerException("elemType");
      } else if (n < -1) {
         throw new IllegalArgumentException("length");
      } else {
         Object obj = deserialize(in);
         Class clazz2 = obj.getClass();
         if (!clazz2.isArray()) {
            throw new InvalidObjectException("object is not an array");
         } else {
            if (clazz2.getComponentType() != clazz) {
               throw new InvalidObjectException("unexpected array component type");
            } else {
               if (n != -1) {
                  if (Array.getLength(obj) != n) {
                     throw new InvalidObjectException("array length mismatch");
                  }
               }

               return obj;
            }
         }
      }
   }

   public static synchronized JavaLayerHook getHook() {
      return hook;
   }

   public static Object deserializeArrayResource(String str, Class clazz, int n) throws IOException {
      InputStream resourceAsStream = getResourceAsStream(str);
      if (resourceAsStream == null) {
         throw new IOException("unable to load resource '" + str + "'");
      } else {
         return deserializeArray(resourceAsStream, clazz, n);
      }
   }

   public static Object deserialize(InputStream in, Class clazz) throws IOException {
      if (clazz == null) {
         throw new NullPointerException("cls");
      } else {
         Object obj = deserialize(in, clazz);
         if (!clazz.isInstance(obj)) {
            throw new InvalidObjectException("type of deserialized instance not of required class.");
         } else {
            return obj;
         }
      }
   }

   public static void serialize(OutputStream out, Object obj) throws IOException {
      if (out == null) {
         throw new NullPointerException("out");
      } else if (obj == null) {
         throw new NullPointerException("obj");
      } else {
         new ObjectOutputStream(out).writeObject(obj);
      }
   }
}
