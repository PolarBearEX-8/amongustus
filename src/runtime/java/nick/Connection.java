package nick;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import sun.misc.Unsafe;

public final class Connection {
    public static AtomicBoolean LOCK = new AtomicBoolean(true);
    public static Object INSTANCE;
    public static Object KO;

    public static void end() throws Throwable {
        LOCK.set(false);
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        Class<?> clientGlobalsClass = AugustusClassLoader.INSTANCE.loadClass("net.40U");
        Field globalsField = clientGlobalsClass.getDeclaredField("45");
        globalsField.setAccessible(true);
        Object globalsInstance = globalsField.get(null);

        Field primaryRegistryField = clientGlobalsClass.getDeclaredField("45G");
        primaryRegistryField.setAccessible(true);
        primaryRegistryField.set(globalsInstance, primaryRegistryField.getType().getDeclaredConstructor().newInstance());

        Field secondaryRegistryField = clientGlobalsClass.getDeclaredField("45h");
        secondaryRegistryField.setAccessible(true);
        secondaryRegistryField.set(globalsInstance, secondaryRegistryField.getType().getDeclaredConstructor().newInstance());

        Field sessionListenerField = clientGlobalsClass.getDeclaredField("47");
        sessionListenerField.setAccessible(true);
        Object sessionListener = unsafe.allocateInstance(sessionListenerField.getType());
        Object eventBus = AugustusClassLoader.INSTANCE.loadClass("net.47yA").getDeclaredMethod("49").invoke(null);
        Method subscribe = eventBus.getClass().getDeclaredMethod("subscribe", Integer.TYPE, Object.class);
        subscribe.invoke(eventBus, 32, sessionListener);
        sessionListenerField.set(globalsInstance, sessionListener);

        Field sessionTimerField = clientGlobalsClass.getDeclaredField("45y");
        sessionTimerField.setAccessible(true);
        sessionTimerField.set(globalsInstance, sessionTimerField.getType().getDeclaredConstructor(Long.TYPE).newInstance(0x3741cfd06a7eL));

        Field stateManagerField = clientGlobalsClass.getDeclaredField("40");
        stateManagerField.setAccessible(true);
        stateManagerField.set(globalsInstance, stateManagerField.getType().getDeclaredConstructor().newInstance());
    }

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);

            INSTANCE = unsafe.allocateInstance(AugustusClassLoader.INSTANCE.loadClass("net.47Ua"));
            Field activeFlagField = INSTANCE.getClass().getDeclaredField("47h");
            activeFlagField.setAccessible(true);
            activeFlagField.set(INSTANCE, LOCK);

            Field keyStateField = INSTANCE.getClass().getDeclaredField("47T");
            keyStateField.setAccessible(true);
            KO = unsafe.allocateInstance(keyStateField.getType());
            keyStateField.set(INSTANCE, KO);

            Field magnitudeField = KO.getClass().getDeclaredField("42");
            magnitudeField.setAccessible(true);
            magnitudeField.set(KO, new int[0]);

            Field packetQueueField = INSTANCE.getClass().getDeclaredField("47M");
            packetQueueField.setAccessible(true);
            packetQueueField.set(INSTANCE, new ConcurrentLinkedQueue<>());

            Field readWriteLockField = INSTANCE.getClass().getDeclaredField("47g");
            readWriteLockField.setAccessible(true);
            readWriteLockField.set(INSTANCE, new ReentrantReadWriteLock());

            Field decoderField = INSTANCE.getClass().getDeclaredField("41");
            decoderField.setAccessible(true);
            decoderField.set(INSTANCE, AugustusClassLoader.INSTANCE.loadClass("net.47S5").getDeclaredConstructor().newInstance());
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }
}
