package baritone.pathing.calc.openset;

import baritone.pathing.calc.PathNode;

public class LinkedListOpenSet implements IOpenSet {
   private LinkedListOpenSet.Node first = null;

   @Override
   public void insert(PathNode pathNode) {
      LinkedListOpenSet.Node node = new LinkedListOpenSet.Node();
      LinkedListOpenSet.Node.setVal(node, pathNode);
      LinkedListOpenSet.Node.setNext(node, this.first);
      this.first = node;
   }

   @Override
   public void update(PathNode pathNode) {
   }

   @Override
   public boolean isEmpty() {
      return this.first == null;
   }

   @Override
   public PathNode removeLowest() {
      if (this.first == null) {
         return null;
      } else {
         LinkedListOpenSet.Node next = LinkedListOpenSet.Node.getNext(this.first);
         if (next == null) {
            LinkedListOpenSet.Node first2 = this.first;
            this.first = null;
            return LinkedListOpenSet.Node.getVal(first2);
         } else {
            LinkedListOpenSet.Node first3 = this.first;
            double combinedCost = LinkedListOpenSet.Node.getVal(this.first).combinedCost;
            LinkedListOpenSet.Node first4 = this.first;

            LinkedListOpenSet.Node node;
            for (node = null; next != null; next = LinkedListOpenSet.Node.getNext(next)) {
               double combinedCost2 = LinkedListOpenSet.Node.getVal(next).combinedCost;
               if (combinedCost2 < combinedCost) {
                  combinedCost = combinedCost2;
                  first4 = next;
                  node = first3;
               }

               first3 = next;
            }

            if (node == null) {
               this.first = LinkedListOpenSet.Node.getNext(this.first);
               LinkedListOpenSet.Node.setNext(first4, (LinkedListOpenSet.Node)null);
               return LinkedListOpenSet.Node.getVal(first4);
            } else {
               LinkedListOpenSet.Node.setNext(node, LinkedListOpenSet.Node.getNext(first4));
               LinkedListOpenSet.Node.setNext(first4, (LinkedListOpenSet.Node)null);
               return LinkedListOpenSet.Node.getVal(first4);
            }
         }
      }
   }


   public static class Node {
      private Node next;
      private PathNode val;

      public static Node setNext(Node node, Node node2) {
         return node.next = node2;
      }

      public static PathNode getVal(Node node) {
         return node.val;
      }

      public static Node getNext(Node node) {
         return node.next;
      }

      public static PathNode setVal(Node node, PathNode pathNode) {
         return node.val = pathNode;
      }
   }
}
