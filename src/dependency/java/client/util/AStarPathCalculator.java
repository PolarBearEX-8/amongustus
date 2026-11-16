package client.util;

import client.util.TimeHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class AStarPathCalculator {
    public ArrayList getArrayList(ArrayList arrayList, int n, boolean bl) {
        PriorityQueue<Comparable> priorityQueue = new PriorityQueue<Comparable>(n);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Comparable comparable = (Comparable)iterator.next();
            if (priorityQueue.size() < n) {
                priorityQueue.offer(comparable);
                continue;
            }
            if (comparable.compareTo(priorityQueue.peek()) != 1) continue;
            priorityQueue.poll();
            priorityQueue.offer(comparable);
        }
        if (bl) {
            ArrayList arrayList2 = new ArrayList(priorityQueue);
            arrayList2.sort(Comparator.naturalOrder());
            return arrayList2;
        }
        return new ArrayList(priorityQueue);
    }
}

