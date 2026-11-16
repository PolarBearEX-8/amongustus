package nick;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

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
            Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            String[] currentPaths = (String[]) Objects.requireNonNull(sysPathsField.get(null));
            String[] patchedPaths = new String[currentPaths.length + 1];
            System.arraycopy(currentPaths, 0, patchedPaths, 0, currentPaths.length);
            patchedPaths[patchedPaths.length - 1] = dir.toString();
            sysPathsField.set(null, patchedPaths);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }

    private static Path deleteOnExit(Path path) {
        path.toFile().deleteOnExit();
        return path;
    }

    public static void create() {
        try {
            Path dir = Libraries.deleteOnExit(Files.createTempDirectory("augustus_natives"));
            for (String lib : LIBS) {
                try (InputStream stream = Objects.requireNonNull(
                        Libraries.class.getClassLoader().getResourceAsStream("natives/".concat(lib)))) {
                    Path out = Libraries.deleteOnExit(dir.resolve(lib));
                    Files.deleteIfExists(out);
                    Files.copy(stream, out);
                }
            }
            Libraries.rebuild(dir);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }

    public static URLClassLoader loader() {
        try {
            Path dir = Libraries.deleteOnExit(Files.createTempDirectory("augustus_loader"));
            Path jar = Libraries.deleteOnExit(dir.resolve("ElectricLauncher.jar"));
            try (InputStream stream = Objects.requireNonNull(
                    Libraries.class.getClassLoader().getResourceAsStream("loader/ElectricLauncher.jar"))) {
                Files.deleteIfExists(jar);
                Files.copy(stream, jar);
            }
            return new URLClassLoader(new URL[]{jar.toUri().toURL()});
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }
}
