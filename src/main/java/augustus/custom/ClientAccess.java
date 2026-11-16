package augustus.custom;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clean, safe access to the retained client ABI with compile-time symbol folding support.
 * All resolveClassName, resolveFieldName, and resolveMethodName calls are folded by
 * ReadableNamespaceMapper into literal runtime bytecode strings during reverse mapping.
 */
public final class ClientAccess {
    private static final Map<String, Field> FIELDS = new ConcurrentHashMap<>();
    private static final Map<String, Method> METHODS = new ConcurrentHashMap<>();
    private static final Set<String> WARNED = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private ClientAccess() {}

    /**
     * Folded into runtime obfuscated class name by ReadableNamespaceMapper.
     */
    public static String resolveClassName(String readableClass) {
        return readableClass;
    }

    /**
     * Folded into runtime obfuscated field name by ReadableNamespaceMapper.
     */
    public static String resolveFieldName(String readableOwner, String readableField) {
        return readableField;
    }

    /**
     * Folded into runtime obfuscated field name by ReadableNamespaceMapper.
     */
    public static String resolveFieldName(String readableOwner, String readableField, String descriptor) {
        return readableField;
    }

    /**
     * Folded into runtime obfuscated method name by ReadableNamespaceMapper.
     */
    public static String resolveMethodName(String readableOwner, String readableMethod) {
        return readableMethod;
    }

    /**
     * Folded into runtime obfuscated method name by ReadableNamespaceMapper.
     */
    public static String resolveMethodName(String readableOwner, String readableMethod, String descriptor) {
        return readableMethod;
    }

    public static Class<?> type(String name) {
        try {
            return Class.forName(name, false, ClientAccess.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class not found: " + name, e);
        }
    }

    public static Field field(Class<?> owner, String name) {
        String key = owner.getName() + "." + name;
        Field found = FIELDS.get(key);
        if (found != null) {
            return found;
        }
        for (Class<?> i = owner; i != null; i = i.getSuperclass()) {
            try {
                found = i.getDeclaredField(name);
                found.setAccessible(true);
                FIELDS.put(key, found);
                return found;
            } catch (NoSuchFieldException ignored) {}
        }
        throw new IllegalStateException("Missing field: " + key);
    }

    public static Object get(Object target, String name) {
        try {
            Class<?> owner = target instanceof Class ? (Class<?>) target : target.getClass();
            return field(owner, name).get(target instanceof Class ? null : target);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Object getDeclared(String owner, Object target, String name) {
        try {
            return field(type(owner), name).get(target);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void set(Object target, String name, Object value) {
        try {
            Class<?> owner = target instanceof Class ? (Class<?>) target : target.getClass();
            field(owner, name).set(target instanceof Class ? null : target, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Method method(Class<?> owner, String name, Class<?> result, Class<?>... args) {
        String key = owner.getName() + "." + name + Arrays.toString(args) + result.getName();
        Method found = METHODS.get(key);
        if (found != null) {
            return found;
        }
        for (Class<?> i = owner; i != null; i = i.getSuperclass()) {
            for (Method m : i.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getReturnType() == result && Arrays.equals(m.getParameterTypes(), args)) {
                    m.setAccessible(true);
                    METHODS.put(key, m);
                    return m;
                }
            }
        }
        throw new IllegalStateException("Missing exact method: " + key);
    }

    public static Object call(Object target, String name, Class<?> result, Class<?>[] signature, Object... args) {
        try {
            Class<?> owner = target instanceof Class ? (Class<?>) target : target.getClass();
            return method(owner, name, result, signature).invoke(target instanceof Class ? null : target, args);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getCause());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Constructor<?> constructor(Class<?> owner, Class<?>... args) {
        for (Constructor<?> constructor : owner.getDeclaredConstructors()) {
            if (Arrays.equals(constructor.getParameterTypes(), args)) {
                constructor.setAccessible(true);
                return constructor;
            }
        }
        throw new IllegalStateException("Missing exact constructor: " + owner.getName() + Arrays.toString(args));
    }

    public static Object construct(Class<?> owner, Class<?>[] signature, Object... args) {
        try {
            return constructor(owner, signature).newInstance(args);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getCause());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Object minecraft() {
        String mcClass = resolveClassName("net.minecraft.client.Minecraft");
        String getMcMethod = resolveMethodName("net.minecraft.client.Minecraft", "getMinecraft", "()Lnet/minecraft/client/Minecraft;");
        return call(type(mcClass), getMcMethod, type(mcClass), new Class<?>[0]);
    }

    public static Object registry() {
        String globalsClass = resolveClassName("client.ClientGlobals");
        String managerField = resolveFieldName("client.ClientGlobals", "moduleManager", "Lclient/module/ModuleManager;");
        return get(type(globalsClass), managerField);
    }

    public static Object module(String className) {
        Object reg = registry();
        if (reg == null) return null;
        for (Field field : reg.getClass().getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            if (fieldType.getName().equals(className)
                    || fieldType.getName().endsWith("." + className)
                    || fieldType.getSimpleName().equalsIgnoreCase(className)) {
                try {
                    field.setAccessible(true);
                    return field.get(reg);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return null;
    }

    public static Object value(Object setting) {
        if (setting == null) return null;
        String modeSettingClass = resolveClassName("client.setting.ModeSetting");
        String valuePropClass = resolveClassName("client.setting.ValueProperty");
        String getValueMethod = resolveMethodName("client.setting.ValueProperty", "getValue", "()Ljava/lang/Object;");

        if (setting.getClass().getName().equals(modeSettingClass) || type(modeSettingClass).isInstance(setting)) {
            String valuePropField = resolveFieldName("client.setting.ModeSetting", "valueProperty");
            Object prop = getDeclared(modeSettingClass, setting, valuePropField);
            return call(prop, getValueMethod, Object.class, new Class<?>[0]);
        }

        String baseSettingClass = resolveClassName("client.setting.Setting");
        for (Class<?> i = setting.getClass(); i != null && !i.getName().equals(baseSettingClass); i = i.getSuperclass()) {
            for (Field field : i.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) continue;
                if (!field.getType().getName().equals(valuePropClass)) continue;
                try {
                    field.setAccessible(true);
                    Object state = field.get(setting);
                    if (state != null) {
                        return call(state, getValueMethod, Object.class, new Class<?>[0]);
                    }
                } catch (RuntimeException | IllegalAccessException ignored) {}
            }
        }
        return null;
    }

    public static boolean enabled(Object module) {
        if (module == null) return false;
        String moduleClass = resolveClassName("client.module.Module");
        String enabledField = resolveFieldName("client.module.Module", "enabledProperty");
        String getValueMethod = resolveMethodName("client.setting.ValueProperty", "getValue", "()Ljava/lang/Object;");
        Object prop = getDeclared(moduleClass, module, enabledField);
        return Boolean.TRUE.equals(call(prop, getValueMethod, Object.class, new Class<?>[0]));
    }

    public static boolean bool(Object setting) {
        return Boolean.TRUE.equals(value(setting));
    }

    public static double number(Object setting, double fallback) {
        Object v = value(setting);
        return v instanceof Number ? ((Number) v).doubleValue() : fallback;
    }

    public static double coordinate(Object object, String name) {
        String entityClass = resolveClassName("net.minecraft.entity.Entity");
        return ((Number) getDeclared(entityClass, object, name)).doubleValue();
    }

    public static String choice(Object setting) {
        Object v = value(setting);
        return v == null ? "" : String.valueOf(v);
    }

    public static void choices(Object setting, String value) {
        String modeSettingClass = resolveClassName("client.setting.ModeSetting");
        String optionsField = resolveFieldName("client.setting.ModeSetting", "options");
        String updateOptionsMethod = resolveMethodName("client.setting.ModeSetting", "updateOptions");

        String[] before = (String[]) getDeclared(modeSettingClass, setting, optionsField);
        String[] after = before;
        if (!Arrays.asList(before).contains(value)) {
            after = Arrays.copyOf(before, before.length + 1);
            after[before.length] = value;
        }
        call(setting, updateOptionsMethod, void.class, new Class<?>[]{String[].class}, (Object) after);
    }

    public static void warn(String key, Throwable error) {
        if (WARNED.add(key)) {
            System.err.println("[custom] " + key + ": " + error);
        }
    }
}
