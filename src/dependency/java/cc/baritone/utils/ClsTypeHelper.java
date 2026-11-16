package cc.baritone.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

public class ClsTypeHelper {
   public static InputStreamCallback inputStreamCallback = null;

   public static synchronized void setInputStreamCallback(InputStreamCallback inputStreamCallback2) {
      inputStreamCallback = inputStreamCallback2;
   }

   public static Object resolveTarget(InputStream inputStream) throws IOException {
      if (inputStream == null) {
         throw new NullPointerException("in");
      } else {
         ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

         try {
            return objectInputStream.readObject();
         } catch (ClassNotFoundException classNotFoundException) {
            throw new InvalidClassException(classNotFoundException.toString());
         }
      }
   }

   public static Object resolveTarget(InputStream inputStream, Class clazz) throws IOException {
      if (clazz == null) {
         throw new NullPointerException("cls");
      } else {
         Object entry = resolveTarget(inputStream, clazz);
         if (!clazz.isInstance(entry)) {
            throw new InvalidObjectException("type of deserialized instance not of required class.");
         } else {
            return entry;
         }
      }
   }

   public static Object resolveTarget(InputStream inputStream, Class clazz, int count) throws IOException {
      if (clazz == null) {
         throw new NullPointerException("elemType");
      } else if (count < -1) {
         throw new IllegalArgumentException("length");
      } else {
         Object entry = resolveTarget(inputStream);
         Class classItem = entry.getClass();
         if (!classItem.isArray()) {
            throw new InvalidObjectException("object is not an array");
         } else if (classItem.getComponentType() != clazz) {
            throw new InvalidObjectException("unexpected array component type");
         } else if (count != -1 && Array.getLength(entry) != count) {
            throw new InvalidObjectException("array length mismatch");
         } else {
            return entry;
         }
      }
   }

   public static Object resolveTarget(String text, Class clazz, int count) throws IOException {
      InputStream inputStream = resolveInputStream(text);
      if (inputStream == null) {
         throw new IOException("unable to load resource '" + text + "'");
      } else {
         return resolveTarget(inputStream, clazz, count);
      }
   }

   public static synchronized InputStreamCallback getInputStreamCallback() {
      return inputStreamCallback;
   }

   public static synchronized InputStream resolveInputStream(String text) {
      InputStream inputStream;
      if (inputStreamCallback != null) {
         inputStream = inputStreamCallback.resolveInputStream(text);
      } else {
         inputStream = ClsTypeHelper.class.getClassLoader().getResourceAsStream(text);
      }

      return inputStream;
   }

   public static void applyOutputStream(OutputStream outputStream, Object entry) throws IOException {
      if (outputStream == null) {
         throw new NullPointerException("out");
      } else if (entry == null) {
         throw new NullPointerException("obj");
      } else {
         new ObjectOutputStream(outputStream).writeObject(entry);
      }
   }
}
