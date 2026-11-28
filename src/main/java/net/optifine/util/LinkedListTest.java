package net.optifine.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.optifine.render.VboRange;

public class LinkedListTest {

   private static void checkLinkedList(LinkedList list, int n) {
      if (list.getSize() != n) {
         throw new RuntimeException("Wrong size, linked: " + list.getSize() + ", used: " + n);
      } else {
         int n2 = 0;

         for (LinkedList.Node i = list.getFirst(); i != null; i = i.getNext()) {
            n2++;
         }

         if (list.getSize() != n2) {
            throw new RuntimeException("Wrong count, linked: " + list.getSize() + ", count: " + n2);
         } else {
            int n3 = 0;

            for (LinkedList.Node j = list.getLast(); j != null; j = j.getPrev()) {
               n3++;
            }

            if (list.getSize() != n3) {
               throw new RuntimeException("Wrong count back, linked: " + list.getSize() + ", count: " + n3);
            }
         }
      }
   }

   public static void main(String[] args) throws Exception {
      LinkedList list = new LinkedList();
      ArrayList list2 = new ArrayList();
      ArrayList list3 = new ArrayList();
      Random rand = new Random();
      byte b = 100;

      for (int i = 0; i < b; i++) {
         VboRange vboRange = new VboRange();
         vboRange.setPosition(i);
         list2.add(vboRange);
      }

      for (int j = 0; j < 100000; j++) {
         checkLists(list2, list3, b);
         checkLinkedList(list, list3.size());
         if (j % 5 == 0) {
            dbgLinkedList(list);
         }

         if (rand.nextBoolean()) {
            if (!list2.isEmpty()) {
               VboRange vboRange2 = (VboRange)list2.get(rand.nextInt(list2.size()));
               LinkedList.Node node = vboRange2.getNode();
               if (rand.nextBoolean()) {
                  list.addFirst(node);
                  dbg("Add first: " + vboRange2.getPosition());
               } else if (rand.nextBoolean()) {
                  list.addLast(node);
                  dbg("Add last: " + vboRange2.getPosition());
               } else {
                  if (list3.isEmpty()) {
                     continue;
                  }

                  VboRange vboRange3 = (VboRange)list3.get(rand.nextInt(list3.size()));
                  list.addAfter(vboRange3.getNode(), node);
                  dbg("Add after: " + vboRange3.getPosition() + ", " + vboRange2.getPosition());
               }

               list2.remove(vboRange2);
               list3.add(vboRange2);
            }
         } else if (!list3.isEmpty()) {
            VboRange vboRange4 = (VboRange)list3.get(rand.nextInt(list3.size()));
            list.remove(vboRange4.getNode());
            dbg("Remove: " + vboRange4.getPosition());
            list3.remove(vboRange4);
            list2.add(vboRange4);
         }
      }
   }

   private static void dbgLinkedList(LinkedList list) {
      StringBuffer sb = new StringBuffer();
      list.iterator().forEachRemaining(obj -> {
         VboRange item = (VboRange)((LinkedList.Node)obj).getItem();
         if (sb.length() > 0) {
            sb.append(", ");
         }

         sb.append(item.getPosition());
      });
      dbg("List: " + sb);
   }

   private static void dbg(String str) {
      System.out.println(str);
   }

   private static void checkLists(List list, List list2, int n) {
      int n2 = list.size() + list2.size();
      if (n2 != n) {
         throw new RuntimeException("Total size: " + n2);
      }
   }

}
