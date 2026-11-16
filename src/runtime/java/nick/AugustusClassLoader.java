package nick;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class AugustusClassLoader extends ClassLoader {
    public static AugustusClassLoader INSTANCE;
    private static final Map<String, Class<?>> CLASSES = new HashMap<>();
    private static final MethodHandle FIND;

    private final ClassLoader delegate;
    private final Map<String, byte[]> cached;
    private final Map<String, byte[]> classes;
    private final Map<String, byte[]> resources;

    private static byte[] read(InputStream stream) throws Throwable {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int count;
        while ((count = stream.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        return out.toByteArray();
    }

    public AugustusClassLoader(ClassLoader delegate, Map<String, byte[]> classes, Map<String, byte[]> resources) {
        INSTANCE = this;
        this.delegate = delegate;
        this.cached = Objects.requireNonNull(Data.cache());
        this.classes = classes;
        this.resources = resources;
        this.overwrite();
        try {
            this.patch("472g");
            this.patch("47eJ");
            this.patch("47Ua");
            this.patch("47cq");
        } catch (Throwable error) {
            error.printStackTrace(System.err);
        }
        EmbeddedOverlay.install(this.cached);
        DevelopmentOverlay.install(this.cached);
    }

    private void patch(String classId) {
        try {
            InputStream stream = Objects.requireNonNull(
                    AugustusClassLoader.class.getClassLoader().getResourceAsStream(classId.concat(".class")));
            this.cached.put("net.".concat(classId), AugustusClassLoader.read(stream));
        } catch (Throwable error) {
            error.printStackTrace(System.err);
        }
    }

    private void overwrite() {
        try {
            Field parentField = ClassLoader.class.getDeclaredField("parent");
            parentField.setAccessible(true);
            parentField.set(this.delegate, this);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }

    private static String format(String name) {
        return name.replace('.', '/').concat(".class");
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return CLASSES.computeIfAbsent(name, key -> {
                try {
                    return this.findClass(key);
                } catch (Throwable error) {
                    if (!(error instanceof ClassNotFoundException)) {
                        error.printStackTrace(System.err);
                        System.exit(0);
                    }
                    return null;
                }
            });
        } catch (Throwable error) {
            error.printStackTrace(System.err);
            throw new ClassNotFoundException(name);
        }
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            if (this.cached.containsKey(name)) {
                byte[] data = Objects.requireNonNull(this.cached.getOrDefault(name, null));
                return this.defineClass(name, data, 0, data.length);
            }
            String resourceName = AugustusClassLoader.format(name);
            if (this.classes.containsKey(resourceName)) {
                byte[] data = Objects.requireNonNull(this.classes.getOrDefault(resourceName, null));
                return this.defineClass(name, data, 0, data.length);
            }
        } catch (ClassFormatError error) {
            throw new RuntimeException("Failed to load " + name, error);
        }
        try {
            Class<?> found = (Class<?>) FIND.invoke(this.delegate, name);
            return found != null ? found : super.findClass(name);
        } catch (Throwable error) {
            if (error instanceof ClassNotFoundException) {
                throw (ClassNotFoundException) error;
            }
            throw new RuntimeException(error);
        }
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (name.startsWith("assets/augustus/alan/")) {
            return AugustusClassLoader.class.getClassLoader().getResourceAsStream(name);
        }
        if (this.resources.containsKey(name)) {
            return new ByteArrayInputStream(this.resources.get(name));
        }
        if (name.equals("net/my.Sus.top.main.bre")) {
            return new ByteArrayInputStream(new byte[]{
                    118, -105, 63, 109, -36, 29, -96, 101, -18, -11, 8, -13, -58, 27, -51, 30,
                    -71, 6, -111, 72, 66, 84, 21, -110, 62, -128, 5, 83, 60, 39, -100, 70});
        }
        if (name.equals("net/Cmw5FzWZE1s1fngeD5LW2wQ5mrdceWxFugsLrgE7")) {
            return new ByteArrayInputStream(new byte[]{
                    107, 119, 97, 115, 103, 120, 97, 85, 74, 70, 52, 68, 83, 54, 121, 66,
                    80, 111, 105, 109, 116, 78, 77, 89, 70, 65, 100, 75, 104, 90, 73, 99,
                    77, 106, 87, 98, 73, 43, 107, 110, 74, 50, 69, 61, 35, 35, 83, 83,
                    56, 56, 35, 35, 98, 48, 115, 47, 56, 103, 112, 81, 71, 109, 70, 52,
                    103, 50, 81, 77, 70, 87, 98, 49, 104, 111, 70, 83, 67, 54, 119, 107,
                    85, 112, 75, 83, 49, 90, 87, 83, 56, 111, 67, 122, 83, 57, 52, 61});
        }
        try {
            return this.delegate.getResourceAsStream(name);
        } catch (Throwable error) {
            error.printStackTrace(System.err);
            System.err.flush();
            return new ByteArrayInputStream(new byte[0]);
        }
    }

    @Override
    public URL getResource(String name) {
        URL editable = EditableResources.find(name);
        if (editable != null) return editable;
        if (name.startsWith("assets/augustus/alan/")) {
            return AugustusClassLoader.class.getClassLoader().getResource(name);
        }
        return this.delegate.getResource(name);
    }

    public static Class<?> defineClassProxy(String name, byte[] data, int offset, int len) {
        return INSTANCE.defineClass(name, data, offset, len);
    }

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            Method findMethod = ClassLoader.class.getDeclaredMethod("findClass", String.class);
            findMethod.setAccessible(true);
            FIND = lookup.unreflect(findMethod);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }
}
