package nick;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.InflaterInputStream;

// $VF: Compiled from Data.java
public final class Data {
   public static InputStream decompress(InputStream compressed) throws IOException {
      try (
         InflaterInputStream inflater = new InflaterInputStream(compressed);
         ByteArrayOutputStream out = new ByteArrayOutputStream();
      ) {
         byte[] buf = new byte[4096];

         int len;
         while ((len = inflater.read(buf)) > 0) {
            out.write(buf, 0, len);
         }

         return new ByteArrayInputStream(out.toByteArray());
      }
   }

   private static String readString(DataInputStream dis) throws Throwable {
      byte[] data = new byte[dis.readInt()];
      dis.readFully(data);
      return new String(data, StandardCharsets.UTF_8);
   }

   private static char[] readCharArray(DataInputStream dis) throws Throwable {
      byte coder = dis.readByte();
      char[] c = new char[dis.readInt()];

      for (int i = 0; i < c.length; i++) {
         byte lo = dis.readByte();
         byte hi = dis.readByte();
         c[i] = (char)(hi << 8 | lo & 0xFF);
      }

      return c;
   }

   private static byte[] readByteArray(DataInputStream dis) throws Throwable {
      byte[] b = new byte[dis.readInt()];
      dis.readFully(b);
      return b;
   }

   public static Map resources() {
      try {
         Map resources = new HashMap();
         DataInputStream dis = new DataInputStream(
            decompress((InputStream)Objects.requireNonNull(Data.class.getClassLoader().getResourceAsStream("data/resources.bin")))
         );

         while (dis.available() > 0) {
            String key = new String(readCharArray(dis));
            byte[] data = readByteArray(dis);
            resources.put(key, data);
         }

         dis.close();
         return resources;
      } catch (Throwable _t) {
         _t.printStackTrace(System.err);
         return null;
      }
   }

   public static Map classes() {
      try {
         Map classes = new HashMap();
         DataInputStream dis = new DataInputStream(
            decompress((InputStream)Objects.requireNonNull(Data.class.getClassLoader().getResourceAsStream("data/classes.bin")))
         );

         while (dis.available() > 0) {
            String key = new String(readCharArray(dis));
            byte[] data = readByteArray(dis);
            classes.put(key, data);
         }

         dis.close();
         return classes;
      } catch (Throwable _t) {
         _t.printStackTrace(System.err);
         return null;
      }
   }

   public static Map cache() {
      try {
         Map cache = new HashMap();
         DataInputStream dis = new DataInputStream((InputStream)Objects.requireNonNull(Data.class.getClassLoader().getResourceAsStream("data/cache.bin")));

         while (dis.available() > 0) {
            String name = readString(dis).replace('/', '.');
            byte[] klass = readByteArray(dis);
            cache.put(name, klass);
         }

         return cache;
      } catch (Throwable _t) {
         throw new RuntimeException(_t);
      }
   }

   public static Map keys() {
      try {
         Map cache = new HashMap();
         DataInputStream dis = new DataInputStream((InputStream)Objects.requireNonNull(Data.class.getClassLoader().getResourceAsStream("data/keys.bin")));

         while (dis.available() > 0) {
            String name = readString(dis).replace('/', '.');
            byte[] klass = readByteArray(dis);
            cache.put(name, klass);
         }

         return cache;
      } catch (Throwable _t) {
         throw new RuntimeException(_t);
      }
   }
}
