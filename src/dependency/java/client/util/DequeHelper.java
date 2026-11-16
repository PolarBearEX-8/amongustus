package client.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class DequeHelper {
   public static Deque deque = new ArrayDeque();

   private static void applyIteratorBridge(IteratorBridge iteratorBridge) {
      synchronized (deque) {
         if (deque.size() <= 1000) {
            iteratorBridge.applyList(null);
            deque.addLast(iteratorBridge);
         }
      }
   }

   public static void applyIteratorBridgeByIteratorBridge(IteratorBridge iteratorBridge) {
      applyIteratorBridge(iteratorBridge);
   }

   public static Iterator resolveIterator(List list) {
      synchronized (deque) {
         Object entry = (IteratorBridge)deque.pollFirst();
         if (entry == null) {
            entry = new IteratorStillIteratorBridge();
         }

         ((IteratorBridge)entry).applyList(list);
         return (Iterator)entry;
      }
   }

   static {
      for (int index = 0; index < 1000; index++) {
         IteratorStillIteratorBridge iteratorStillIteratorBridge = new IteratorStillIteratorBridge();
         deque.add(iteratorStillIteratorBridge);
      }
   }
}
