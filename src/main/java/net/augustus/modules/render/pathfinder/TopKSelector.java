package net.augustus.modules.render.pathfinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import net.augustus.utils.TimeHelper;

public class TopKSelector {
   public ArrayList selectTopK(ArrayList list, int n, boolean flag) {
      PriorityQueue priorityQueue = new PriorityQueue(n);

      for (Comparable comparable : (Iterable<Comparable>)(list)) {
         if (priorityQueue.size() < n) {
            priorityQueue.offer(comparable);
         } else if (comparable.compareTo(priorityQueue.peek()) == 1) {
            priorityQueue.poll();
            priorityQueue.offer(comparable);
         }
      }

      if (flag) {
         ArrayList list2 = new ArrayList(priorityQueue);
         list2.sort(Comparator.naturalOrder());
         return list2;
      } else {
         return new ArrayList(priorityQueue);
      }
   }
}
