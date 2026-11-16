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
import sun.misc.Unsafe;

public final class Augustus {
    private static final String LAUNCHER_RESOURCE_HANDLER = "launcher.41";
    private static final String LAUNCHER_BOOTSTRAP_GUARD = "launcher.4v";
    private static final String LAUNCHER_DELEGATE_LOADER = "launcher.444";

    public static void main(String[] args) throws Throwable {
        try (URLClassLoader launcherLoader = Libraries.loader()) {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            unsafe.ensureClassInitialized(launcherLoader.loadClass(LAUNCHER_RESOURCE_HANDLER));

            Class<?> bootstrapGuard = launcherLoader.loadClass(LAUNCHER_BOOTSTRAP_GUARD);
            Method resolveSession = bootstrapGuard.getDeclaredMethod("a", Long.TYPE, Long.TYPE, Object.class);
            try {
                Object sessionToken = resolveSession.invoke(
                        null, 0x64ddb4889995c42bL, -0x793c6b07721b1d92L, MethodHandles.lookup().lookupClass());
                sessionToken.getClass().getDeclaredMethod("a", Long.TYPE).invoke(sessionToken, 0x1e14cab16777L);

                Class<?> controllerClass = launcherLoader.loadClass("launcher.Controller");
                Object controllerInstance = controllerClass.getDeclaredConstructors()[0].newInstance();
                Field instanceField = controllerClass.getDeclaredField("instance");
                instanceField.setAccessible(true);
                instanceField.set(null, controllerInstance);
            } catch (Throwable error) {
                throw new RuntimeException(error);
            }

            Map<String, byte[]> classes = Objects.requireNonNull(Data.classes());
            Map<String, byte[]> resources = Objects.requireNonNull(Data.resources());
            Class<?> delegateLoaderClass = launcherLoader.loadClass(LAUNCHER_DELEGATE_LOADER);
            Constructor<?> delegateConstructor = delegateLoaderClass.getDeclaredConstructor();
            AugustusClassLoader loader = new AugustusClassLoader(
                    (ClassLoader) delegateConstructor.newInstance(), classes, resources);

            Class<?> handlerClass = launcherLoader.loadClass(LAUNCHER_RESOURCE_HANDLER);
            Object handler = unsafe.allocateInstance(handlerClass);
            Field classMapField = handlerClass.getDeclaredField("48");
            classMapField.setAccessible(true);
            classMapField.set(handler, classes);
            Field resourceMapField = handlerClass.getDeclaredField("42");
            resourceMapField.setAccessible(true);
            resourceMapField.set(handler, resources);
            Field handlerInstanceField = handlerClass.getDeclaredField("47");
            handlerInstanceField.setAccessible(true);
            handlerInstanceField.set(null, handler);

            Map<String, byte[]> keys = Data.keys();
            for (Map.Entry<String, byte[]> entry : keys.entrySet()) {
                Field keyField = delegateLoaderClass.getDeclaredField(entry.getKey());
                keyField.setAccessible(true);
                keyField.set(null, entry.getValue());
            }

            String resourcePath = String.format(
                    "%s\\AppData\\Roaming\\ElectricLauncher\\data\\data\\augustus\\resources.jar",
                    System.getProperty("user.home"));
            Path targetPath = Paths.get(resourcePath);
            Files.createDirectories(targetPath.getParent());
            Files.deleteIfExists(targetPath);
            try (InputStream stream = Objects.requireNonNull(
                    Augustus.class.getClassLoader().getResourceAsStream("loader/resources.jar"))) {
                Files.copy(stream, targetPath);
            }

            Libraries.create();
            Class<?> mainClass = loader.loadClass("StartMinecraftDirectory");
            mainClass.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[0]);
        }
    }
}
