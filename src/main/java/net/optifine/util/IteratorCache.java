package net.optifine.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class IteratorCache {
   private static Deque dequeIterators = new ArrayDeque();

   public static void releaseIterator(IteratorReusable iteratorReusable) {
      finished(iteratorReusable);
   }

   public static Iterator getReadOnly(List list) {
      synchronized (dequeIterators) {
         Object obj = (IteratorReusable)dequeIterators.pollFirst();
         if (obj == null) {
            obj = new IteratorReadOnly();
         }

         ((IteratorReusable)obj).setList(list);
         return (Iterator)obj;
      }
   }

   private static void finished(IteratorReusable iteratorReusable) {
      synchronized (dequeIterators) {
         if (dequeIterators.size() <= 1000) {
            iteratorReusable.setList(null);
            dequeIterators.addLast(iteratorReusable);
         }
      }
   }

   static {
      for (int i = 0; i < 1000; i++) {
         IteratorReadOnly iteratorReadOnly = new IteratorReadOnly();
         dequeIterators.add(iteratorReadOnly);
      }
   }
}
