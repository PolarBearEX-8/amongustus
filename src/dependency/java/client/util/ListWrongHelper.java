package client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListWrongHelper {
   private static void applyNodeDifferentController(NodeDifferentController nodeDifferentController) {
      StringBuffer builder = new StringBuffer();
      nodeDifferentController.getIterator().forEachRemaining(item -> applyStringBuffer(builder, (NodeDifferentControlController)item));
      println("List: " + builder);
   }

   private static void println(String text) {
      System.out.println(text);
   }

   private static void applyStringBuffer(StringBuffer builder, NodeDifferentControlController nodeDifferentControlController) {
      GeometryCalculator dataRef = (GeometryCalculator)nodeDifferentControlController.getDataRef();
      if (builder.length() > 0) {
         builder.append(", ");
      }

      builder.append(dataRef.getCountAsIndex());
   }

   private static void applyNodeDifferentController(NodeDifferentController nodeDifferentController, int count) {
      if (nodeDifferentController.getCount() != count) {
         throw new RuntimeException("Wrong size, linked: " + nodeDifferentController.getCount() + ", used: " + count);
      } else {
         int count2 = 0;

         for (NodeDifferentControlController nodeDifferentControlController = nodeDifferentController.getNodeDifferentControlController(); nodeDifferentControlController != null; nodeDifferentControlController = nodeDifferentControlController.getNodeDifferentControlController2()) {
            count2++;
         }

         if (nodeDifferentController.getCount() != count2) {
            throw new RuntimeException("Wrong count, linked: " + nodeDifferentController.getCount() + ", count: " + count2);
         } else {
            int count3 = 0;

            for (NodeDifferentControlController nodeDifferentControlController2 = nodeDifferentController.getNodeDifferentControlController2(); nodeDifferentControlController2 != null; nodeDifferentControlController2 = nodeDifferentControlController2.getNodeDifferentControlController()) {
               count3++;
            }

            if (nodeDifferentController.getCount() != count3) {
               throw new RuntimeException("Wrong count back, linked: " + nodeDifferentController.getCount() + ", count: " + count3);
            }
         }
      }
   }

   private static void applyList(List list, List list2, int count) {
      int count2 = list.size() + list2.size();
      if (count2 != count) {
         throw new RuntimeException("Total size: " + count2);
      }
   }

   public static void main(String[] textArray) throws Exception {
      NodeDifferentController nodeDifferentController = new NodeDifferentController();
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      Random random = new Random();

      for (int index = 0; index < 100; index++) {
         GeometryCalculator geometryCalculator = new GeometryCalculator();
         geometryCalculator.setCount(index);
         list.add(geometryCalculator);
      }

      for (int index2 = 0; index2 < 100000; index2++) {
         applyList(list, list2, 100);
         applyNodeDifferentController(nodeDifferentController, list2.size());
         if (index2 % 5 == 0) {
            applyNodeDifferentController(nodeDifferentController);
         }

         if (random.nextBoolean()) {
            if (!list.isEmpty()) {
               GeometryCalculator geometryCalculator2 = (GeometryCalculator)list.get(random.nextInt(list.size()));
               NodeDifferentControlController nodeDifferentControlController = geometryCalculator2.getNodeDifferentControlController();
               if (random.nextBoolean()) {
                  nodeDifferentController.applyNodeDifferentControlContro3(nodeDifferentControlController);
                  println("Add first: " + geometryCalculator2.getCountAsIndex());
               } else if (random.nextBoolean()) {
                  nodeDifferentController.applyNodeDifferentControlContro(nodeDifferentControlController);
                  println("Add last: " + geometryCalculator2.getCountAsIndex());
               } else {
                  if (list2.isEmpty()) {
                     continue;
                  }

                  GeometryCalculator geometryCalculator3 = (GeometryCalculator)list2.get(random.nextInt(list2.size()));
                  NodeDifferentControlController nodeDifferentControlController2 = geometryCalculator3.getNodeDifferentControlController();
                  nodeDifferentController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController2, nodeDifferentControlController);
                  println("Add after: " + geometryCalculator3.getCountAsIndex() + ", " + geometryCalculator2.getCountAsIndex());
               }

               list.remove(geometryCalculator2);
               list2.add(geometryCalculator2);
            }
         } else if (!list2.isEmpty()) {
            GeometryCalculator geometryCalculator4 = (GeometryCalculator)list2.get(random.nextInt(list2.size()));
            NodeDifferentControlController nodeDifferentControlController3 = geometryCalculator4.getNodeDifferentControlController();
            nodeDifferentController.resolveNodeDifferentControlControll(nodeDifferentControlController3);
            println("Remove: " + geometryCalculator4.getCountAsIndex());
            list2.remove(geometryCalculator4);
            list.add(geometryCalculator4);
         }
      }
   }
}
