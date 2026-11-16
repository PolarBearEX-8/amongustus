package client.util;

import java.lang.reflect.Array;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClassController3 {
   public final Object[] objectArray;
   public final Class memberRef;
   public final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   public int count;
   public int count2;

   public ClassController3(Class clazz, int count3) {
      this.memberRef = clazz;
      this.objectArray = (Object[])Array.newInstance(clazz, count3);
   }

   public Object[] getTargetArray() {
      Object[] entryArray = (Object[])Array.newInstance(this.memberRef, this.count);
      this.readWriteLock.readLock().lock();

      for (int index = 0; index < this.count; index++) {
         int count3 = (this.count2 - this.count + index) % this.getCount();
         if (count3 < 0) {
            count3 += this.getCount();
         }

         entryArray[index] = this.objectArray[count3];
      }

      this.readWriteLock.readLock().unlock();
      return entryArray;
   }

   public int getCount() {
      this.readWriteLock.readLock().lock();
      int length = this.objectArray.length;
      this.readWriteLock.readLock().unlock();
      return length;
   }

   public Object resolveTarget(Object entry) {
      this.readWriteLock.writeLock().lock();
      this.objectArray[this.count2] = entry;
      this.count2 = (this.count2 + 1) % this.getCount();
      if (this.count < this.getCount()) {
         this.count++;
      }

      this.readWriteLock.writeLock().unlock();
      return entry;
   }
}
