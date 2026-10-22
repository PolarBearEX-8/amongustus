package nick;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// $VF: Compiled from AugustusClassLoader.java
public final class AugustusClassLoader extends ClassLoader {
   public static AugustusClassLoader INSTANCE;
   private static final Map<String, Class> CLASSES = new HashMap<>();
   private static final MethodHandle FIND;
   private final ClassLoader delegate;
   private final Map cached;
   private final Map classes;
   private final Map resources;

   private static byte[] read(InputStream is) throws Throwable {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      byte[] buf = new byte[8192];

      int r;
      while ((r = is.read(buf)) != -1) {
         os.write(buf, 0, r);
      }

      return os.toByteArray();
   }

   public AugustusClassLoader(ClassLoader delegate, Map classes, Map resources) {
      INSTANCE = this;
      this.delegate = delegate;
      this.cached = (Map)Objects.requireNonNull(Data.cache());
      this.classes = classes;
      this.resources = resources;
      this.overwrite();

      try {
         this.patch("472g");
         this.patch("47eJ");
         this.patch("47Ua");
         this.patch("47cq");
      } catch (Throwable _t) {
         _t.printStackTrace(System.err);
      }
   }

   private void patch(String klass) {
      try {
         this.cached
            .put(
               "net.".concat(klass),
               read((InputStream)Objects.requireNonNull(AugustusClassLoader.class.getClassLoader().getResourceAsStream(klass.concat(".class"))))
            );
      } catch (Throwable _t) {
         _t.printStackTrace(System.err);
      }
   }

   private void overwrite() {
      try {
         Field field = ClassLoader.class.getDeclaredField("parent");
         field.setAccessible(true);
         field.set(this.delegate, this);
      } catch (Throwable _t) {
         throw new RuntimeException(_t);
      }
   }

   private static String format(String name) {
      return name.replace('.', '/').concat(".class");
   }

   @Override
   public Class loadClass(String name) throws ClassNotFoundException {
      try {
         return (Class)CLASSES.computeIfAbsent(name, key -> {
            try {
               return this.findClass(key);
            } catch (Throwable _t) {
               if (!(_t instanceof ClassNotFoundException)) {
                  _t.printStackTrace(System.err);
                  System.exit(0);
               }

               return null;
            }
         });
      } catch (Throwable _t) {
         _t.printStackTrace(System.err);
         throw new ClassNotFoundException(name);
      }
   }

   @Override
   public Class findClass(String name) throws ClassNotFoundException {
      try {
         if (this.cached.containsKey(name)) {
            byte[] data = (byte[])Objects.requireNonNull(this.cached.getOrDefault(name, null));
            return this.defineClass(name, data, 0, data.length);
         }

         if (this.classes.containsKey(format(name))) {
            byte[] data = (byte[])Objects.requireNonNull(this.classes.getOrDefault(format(name), null));
            return this.defineClass(name, data, 0, data.length);
         }
      } catch (ClassFormatError _t) {
         throw new RuntimeException("Failed to load " + name, _t);
      }

      try {
         Class k = (Class)FIND.invoke((ClassLoader)this.delegate, (String)name);
         return k != null ? k : super.findClass(name);
      } catch (Throwable _t) {
         if (_t instanceof ClassNotFoundException) {
            throw (ClassNotFoundException)_t;
         } else {
            throw new RuntimeException(_t);
         }
      }
   }

   @Override
   public InputStream getResourceAsStream(String name) {
      if (this.resources.containsKey(name)) {
         return new ByteArrayInputStream((byte[])this.resources.get(name));
      }

      if (name.equals("net/my.Sus.top.main.bre")) {
         return new ByteArrayInputStream(
            new byte[]{
               118, -105, 63, 109, -36, 29, -96, 101, -18, -11, 8, -13, -58, 27, -51, 30, -71, 6, -111, 72, 66, 84, 21, -110, 62, -128, 5, 83, 60, 39, -100, 70
            }
         );
      }

      if (name.equals("net/Cmw5FzWZE1s1fngeD5LW2wQ5mrdceWxFugsLrgE7")) {
         return new ByteArrayInputStream(
            new byte[]{
               107,
               119,
               97,
               115,
               103,
               120,
               97,
               85,
               74,
               70,
               52,
               68,
               83,
               54,
               121,
               66,
               80,
               111,
               105,
               109,
               116,
               78,
               77,
               89,
               70,
               65,
               100,
               75,
               104,
               90,
               73,
               99,
               77,
               106,
               87,
               98,
               73,
               43,
               107,
               110,
               74,
               50,
               69,
               61,
               35,
               35,
               83,
               83,
               56,
               56,
               35,
               35,
               98,
               48,
               115,
               47,
               56,
               103,
               112,
               81,
               71,
               109,
               70,
               52,
               103,
               50,
               81,
               77,
               70,
               87,
               98,
               49,
               104,
               111,
               70,
               83,
               67,
               54,
               119,
               107,
               85,
               112,
               75,
               83,
               49,
               90,
               87,
               83,
               56,
               111,
               67,
               122,
               83,
               57,
               52,
               61
            }
         );
      }

      try {
         return this.delegate.getResourceAsStream(name);
      } catch (Throwable _t) {
         _t.printStackTrace(System.err);
         System.err.flush();
         return new ByteArrayInputStream(new byte[0]);
      }
   }

   @Override
   public URL getResource(String name) {
      return this.delegate.getResource(name);
   }

   public static Class defineClassProxy(String name, byte[] data, int offset, int len) {
      return INSTANCE.defineClass(name, data, offset, len);
   }

   static {
      try {
         Lookup lookup = MethodHandles.lookup();
         Method m = ClassLoader.class.getDeclaredMethod("findClass", String.class);
         m.setAccessible(true);
         FIND = lookup.unreflect(m);
      } catch (Throwable _t) {
         throw new RuntimeException(_t);
      }
   }
}
