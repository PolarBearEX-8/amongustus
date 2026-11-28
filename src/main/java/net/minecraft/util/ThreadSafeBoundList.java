package net.minecraft.util;

import java.lang.reflect.Array;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeBoundList {
   private final Object[] elements;
   private final Class elementType;
   private final ReadWriteLock lock = new ReentrantReadWriteLock();
   private int size;
   private int writeIndex;

   public ThreadSafeBoundList(Class clazz, int n) {
      this.elementType = clazz;
      this.elements = (Object[])Array.newInstance(clazz, n);
   }

   public Object add(Object obj) {
      this.lock.writeLock().lock();
      this.elements[this.writeIndex] = obj;
      this.writeIndex = (this.writeIndex + 1) % this.getMaxSize();
      if (this.size < this.getMaxSize()) {
         this.size++;
      }

      this.lock.writeLock().unlock();
      return obj;
   }

   public int getMaxSize() {
      this.lock.readLock().lock();
      int length = this.elements.length;
      this.lock.readLock().unlock();
      return length;
   }

   public Object[] toArray() {
      Object[] array = (Object[])Array.newInstance(this.elementType, this.size);
      this.lock.readLock().lock();

      for (int i = 0; i < this.size; i++) {
         int n = (this.writeIndex - this.size + i) % this.getMaxSize();
         if (n < 0) {
            n += this.getMaxSize();
         }

         array[i] = this.elements[n];
      }

      this.lock.readLock().unlock();
      return array;
   }
}
