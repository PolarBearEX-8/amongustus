package client.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class WrongManagerTypeManager {
   public Class memberRef = null;
   public int count = 0;
   public ArrayDeque arrayDeque = new ArrayDeque();

   public synchronized void applyTarget(Object entry) {
      if (entry != null) {
         if (entry.getClass().getComponentType() != this.memberRef) {
            throw new IllegalArgumentException("Wrong component type");
         }

         if (this.arrayDeque.size() < this.count) {
            this.arrayDeque.add(entry);
         }
      }
   }

   public synchronized Object resolveTarget(int count2) {
      Object entry = this.arrayDeque.pollLast();
      if (entry == null || Array.getLength(entry) < count2) {
         entry = Array.newInstance(this.memberRef, count2);
      }

      return entry;
   }

   public WrongManagerTypeManager(Class clazz, int count2) {
      this.memberRef = clazz;
      this.count = count2;
   }
}
