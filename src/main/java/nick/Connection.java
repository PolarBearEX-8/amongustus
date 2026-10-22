package nick;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import sun.misc.Unsafe;

// $VF: Compiled from Connection.java
public final class Connection {
   public static AtomicBoolean LOCK = new AtomicBoolean(true);
   public static Object INSTANCE;
   public static Object KO;

   public static void end() throws Throwable {
      LOCK.set(false);
      Field _u = Unsafe.class.getDeclaredField("theUnsafe");
      _u.setAccessible(true);
      Unsafe unsafe = (Unsafe)_u.get(null);
      Class k = AugustusClassLoader.INSTANCE.loadClass("net.40U");
      Field _inst = k.getDeclaredField("45");
      _inst.setAccessible(true);
      Object inst = _inst.get(null);
      Field f1 = k.getDeclaredField("45G");
      f1.setAccessible(true);
      f1.set(inst, f1.getType().getDeclaredConstructor().newInstance());
      Field f2 = k.getDeclaredField("45h");
      f2.setAccessible(true);
      f2.set(inst, f2.getType().getDeclaredConstructor().newInstance());
      Field f3 = k.getDeclaredField("47");
      f3.setAccessible(true);
      Object _479q = unsafe.allocateInstance(f3.getType());
      Object eventBus = AugustusClassLoader.INSTANCE.loadClass("net.47yA").getDeclaredMethod("49").invoke(null);
      Method subscribe = eventBus.getClass().getDeclaredMethod("subscribe", int.class, Object.class);
      subscribe.invoke(eventBus, 32, _479q);
      f3.set(inst, _479q);
      Field f4 = k.getDeclaredField("45y");
      f4.setAccessible(true);
      long l = 60755798944382L;
      f4.set(inst, f4.getType().getDeclaredConstructor(long.class).newInstance(60755798944382L));
      Field f5 = k.getDeclaredField("40");
      f5.setAccessible(true);
      f5.set(inst, f5.getType().getDeclaredConstructor().newInstance());
   }

   static {
      try {
         Field f = Unsafe.class.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         Unsafe unsafe = (Unsafe)f.get(null);
         INSTANCE = unsafe.allocateInstance(AugustusClassLoader.INSTANCE.loadClass("net.47Ua"));
         Field atomic = INSTANCE.getClass().getDeclaredField("47h");
         atomic.setAccessible(true);
         atomic.set(INSTANCE, LOCK);
         Field ko = INSTANCE.getClass().getDeclaredField("47T");
         ko.setAccessible(true);
         KO = unsafe.allocateInstance(ko.getType());
         ko.set(INSTANCE, KO);
         Field mag = KO.getClass().getDeclaredField("42");
         mag.setAccessible(true);
         mag.set(KO, new int[0]);
         Field queue = INSTANCE.getClass().getDeclaredField("47M");
         queue.setAccessible(true);
         queue.set(INSTANCE, new ConcurrentLinkedQueue());
         Field lock = INSTANCE.getClass().getDeclaredField("47g");
         lock.setAccessible(true);
         lock.set(INSTANCE, new ReentrantReadWriteLock());
         Field decoder = INSTANCE.getClass().getDeclaredField("41");
         decoder.setAccessible(true);
         decoder.set(INSTANCE, AugustusClassLoader.INSTANCE.loadClass("net.47S5").getDeclaredConstructor().newInstance());
      } catch (Throwable _t) {
         throw new RuntimeException(_t);
      }
   }
}
