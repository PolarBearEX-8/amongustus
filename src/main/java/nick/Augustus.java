package nick;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import sun.misc.Unsafe;

// $VF: Compiled from Augustus.java
public final class Augustus {
   public static void main(String[] args) throws Throwable {
      try (URLClassLoader ucl = Libraries.loader()) {
         Field _u = Unsafe.class.getDeclaredField("theUnsafe");
         _u.setAccessible(true);
         Unsafe unsafe = (Unsafe)_u.get(null);
         unsafe.ensureClassInitialized(ucl.loadClass("launcher.41"));
         Class _4v = ucl.loadClass("launcher.4v");
         Method _4v_1 = _4v.getDeclaredMethod("a", long.class, long.class, Object.class);

         try {
            Object _4v_s1 = _4v_1.invoke(null, 7268163872431129643L, -8735975056915242386L, MethodHandles.lookup().lookupClass());
            _4v_s1.getClass().getDeclaredMethod("a", long.class).invoke(_4v_s1, 33074648803191L);
            Class c = ucl.loadClass("launcher.Controller");
            Object ci = c.getDeclaredConstructors()[0].newInstance();
            Field cf = c.getDeclaredField("instance");
            cf.setAccessible(true);
            cf.set(null, ci);
         } catch (Throwable _t) {
            throw new RuntimeException(_t);
         }

         Map classes = (Map<String, byte[]>)Objects.requireNonNull(Data.classes());
         Map resources = (Map<String, byte[]>)Objects.requireNonNull(Data.resources());
         Class classLoader = ucl.loadClass("launcher.444");
         Constructor _444_c = classLoader.getDeclaredConstructor();
         AugustusClassLoader loader = new AugustusClassLoader((ClassLoader)_444_c.newInstance(), classes, resources);
         Class handlerClass = ucl.loadClass("launcher.41");
         Object handler = unsafe.allocateInstance(handlerClass);
         Field classMap = handlerClass.getDeclaredField("48");
         classMap.setAccessible(true);
         classMap.set(handler, classes);
         Field resourceMap = handlerClass.getDeclaredField("42");
         resourceMap.setAccessible(true);
         resourceMap.set(handler, resources);
         Field handlerField = handlerClass.getDeclaredField("47");
         handlerField.setAccessible(true);
         handlerField.set(null, handler);
         Map<String, byte[]> keys = Data.keys();

         for (Entry<String, byte[]> entry : keys.entrySet()) {
            Field field = classLoader.getDeclaredField((String)entry.getKey());
            field.setAccessible(true);
            field.set(null, entry.getValue());
         }

         String resourcePath = String.format("%s\\AppData\\Roaming\\ElectricLauncher\\data\\data\\augustus\\resources.jar", System.getProperty("user.home"));
         Path path = Paths.get(resourcePath);
         Files.createDirectories(path.getParent());
         Files.deleteIfExists(path);

         try (InputStream is = (InputStream)Objects.requireNonNull(Augustus.class.getClassLoader().getResourceAsStream("loader/resources.jar"))) {
            Files.copy(is, path);
         }

         Libraries.create();
         Class main = loader.loadClass("StartMinecraftDirectory");
         main.getDeclaredMethod("main", String[].class).invoke(null, (Object)new String[0]);
      }
   }
}
