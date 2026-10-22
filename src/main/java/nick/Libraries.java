package nick;

import java.nio.file.CopyOption;
import java.nio.file.attribute.FileAttribute;


import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

// $VF: Compiled from Libraries.java
public final class Libraries {
   private static final String[] LIBS = new String[]{
      "avutil-ttv-51.dll",
      "freetype-jni-64.dll",
      "jinput-dx8.dll",
      "jinput-dx8_64.dll",
      "jinput-raw.dll",
      "jinput-raw_64.dll",
      "jinput-wintab.dll",
      "libmfxsw64.dll",
      "libmp3lame-ttv.dll",
      "lwjgl.dll",
      "lwjgl64.dll",
      "OpenAL32.dll",
      "OpenAL64.dll",
      "swresample-ttv-0.dll",
      "twitchsdk.dll"
   };

   private static void rebuild(Path dir) {
      try {
         String path = System.getProperty("java.library.path") + File.pathSeparator + dir;
         System.setProperty("java.library.path", path);
         Field field = ClassLoader.class.getDeclaredField("sys_paths");
         field.setAccessible(true);
         String[] curr = (String[])Objects.requireNonNull(field.get(null));
         String[] patched = new String[curr.length + 1];
         System.arraycopy(curr, 0, patched, 0, curr.length);
         patched[patched.length - 1] = dir.toString();
         field.set(null, patched);
      } catch (Throwable _t) {
         throw new RuntimeException(_t);
      }
   }

   private static Path delete(Path path) {
      path.toFile().deleteOnExit();
      return path;
   }

    public static void create() {
        try {
            Path dir = Libraries.delete(Files.createTempDirectory("augustus_natives", new FileAttribute[0]));
            for (String lib : LIBS) {
                try (InputStream is = Objects.requireNonNull(Libraries.class.getClassLoader().getResourceAsStream("natives/".concat(lib)));){
                    Path out = Libraries.delete(dir.resolve(lib));
                    Files.deleteIfExists(out);
                    Files.copy(is, out, new CopyOption[0]);
                }
            }
            Libraries.rebuild(dir);
        }
        catch (Throwable _t) {
            throw new RuntimeException(_t);
        }
    }

    public static URLClassLoader loader() {
        try {
            Path dir = Libraries.delete(Files.createTempDirectory("augustus_loader", new FileAttribute[0]));
            Path jar = Libraries.delete(dir.resolve("ElectricLauncher.jar"));
            try (InputStream is = Objects.requireNonNull(Libraries.class.getClassLoader().getResourceAsStream("loader/ElectricLauncher.jar"));){
                Files.deleteIfExists(jar);
                Files.copy(is, jar, new CopyOption[0]);
            }
            return new URLClassLoader(new URL[]{jar.toUri().toURL()});
        }
        catch (Throwable _t) {
            throw new RuntimeException(_t);
        }
    }
}
