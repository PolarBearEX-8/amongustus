package net.optifine.util;

import java.util.Iterator;

public class LinkedList {
   private LinkedList.Node first;
   private LinkedList.Node last;
   private int size;

   public void addLast(LinkedList.Node node) {
      this.checkNoParent(node);
      if (this.isEmpty()) {
         this.first = node;
         this.last = node;
      } else {
         LinkedList.Node last2 = this.last;
         LinkedList.Node.setPrevOf(node, last2);
         LinkedList.Node.setNextOf(last2, node);
         this.last = node;
      }

      LinkedList.Node.setParentOf(node, this);
      this.size++;
   }

   public Iterator iterator() {
      return new LinkedList.EntryIterator(this);
   }

   public LinkedList.Node getLast() {
      return this.last;
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer();
      Iterator it = this.iterator();

      while (it.hasNext()) {
         LinkedList.Node node = (LinkedList.Node)it.next();
         if (sb.length() > 0) {
            sb.append(", ");
         }

         sb.append(node.getItem());
      }

      return "" + this.size + " [" + sb.toString() + "]";
   }

   public void moveAfter(LinkedList.Node node, LinkedList.Node node2) {
      this.remove(node2);
      this.addAfter(node, node2);
   }

   public void addFirst(LinkedList.Node node) {
      this.checkNoParent(node);
      if (this.isEmpty()) {
         this.first = node;
         this.last = node;
      } else {
         LinkedList.Node first2 = this.first;
         LinkedList.Node.setNextOf(node, first2);
         LinkedList.Node.setPrevOf(first2, node);
         this.first = node;
      }

      LinkedList.Node.setParentOf(node, this);
      this.size++;
   }

   public boolean contains(LinkedList.Node node) {
      return LinkedList.Node.getParentOf(node) == this;
   }

   public LinkedList.Node remove(LinkedList.Node node) {
      this.checkParent(node);
      LinkedList.Node prev = node.getPrev();
      LinkedList.Node next = node.getNext();
      if (prev != null) {
         LinkedList.Node.setNextOf(prev, next);
      } else {
         this.first = next;
      }

      if (next != null) {
         LinkedList.Node.setPrevOf(next, prev);
      } else {
         this.last = prev;
      }

      LinkedList.Node.setPrevOf(node, null);
      LinkedList.Node.setNextOf(node, null);
      LinkedList.Node.setParentOf(node, null);
      this.size--;
      return node;
   }

   private void checkNoParent(LinkedList.Node node) {
      if (LinkedList.Node.getParentOf(node) != null) {
         throw new IllegalArgumentException("Node has different parent, node: " + node + ", parent: " + LinkedList.Node.getParentOf(node) + ", this: " + this);
      }
   }

   private void checkParent(LinkedList.Node node) {
      if (LinkedList.Node.getParentOf(node) != this) {
         throw new IllegalArgumentException("Node has different parent, node: " + node + ", parent: " + LinkedList.Node.getParentOf(node) + ", this: " + this);
      }
   }

   public void addAfter(LinkedList.Node node, LinkedList.Node node2) {
      if (node == null) {
         this.addFirst(node2);
      } else if (node == this.last) {
         this.addLast(node2);
      } else {
         this.checkParent(node);
         this.checkNoParent(node2);
         LinkedList.Node next = node.getNext();
         LinkedList.Node.setNextOf(node, node2);
         LinkedList.Node.setPrevOf(node2, node);
         LinkedList.Node.setPrevOf(next, node2);
         LinkedList.Node.setNextOf(node2, next);
         LinkedList.Node.setParentOf(node2, this);
         this.size++;
      }
   }

   public boolean isEmpty() {
      return this.size <= 0;
   }

   public int getSize() {
      return this.size;
   }

   public boolean find(LinkedList.Node node, LinkedList.Node node2, LinkedList.Node node3) {
      this.checkParent(node2);
      if (node3 != null) {
         this.checkParent(node3);
      }

      LinkedList.Node node4;
      for (node4 = node2; node4 != null && node4 != node3; node4 = node4.getNext()) {
         if (node4 == node) {
            return true;
         }
      }

      if (node4 != node3) {
         throw new IllegalArgumentException("Sublist is not linked, from: " + node2 + ", to: " + node3);
      } else {
         return false;
      }
   }

   public LinkedList.Node getFirst() {
      return this.first;
   }


   public static class EntryIterator implements Iterator {
      public LinkedList.Node node;
      public final LinkedList list;

      @Override
      public Object next() {
         return this.nextNode();
      }

      public LinkedList.Node nextNode() {
         LinkedList.Node node2 = this.node;
         if (this.node != null) {
            this.node = LinkedList.Node.getNextOf(this.node);
         }

         return node2;
      }

      @Override
      public boolean hasNext() {
         return this.node != null;
      }

      public EntryIterator(LinkedList list2) {
         this.list = list2;
         this.node = this.list.getFirst();
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException("remove");
      }
   }

   public static class Node {
      private final Object item;
      private Node prev;
      private Node next;
      private LinkedList parent;

      public static LinkedList getParentOf(Node node) {
         return node.parent;
      }

      public Node(Object obj) {
         this.item = obj;
      }

      public static void setParentOf(Node node, LinkedList list) {
         node.setParent(list);
      }

      @Override
      public String toString() {
         return "" + this.item;
      }

      private void setPrev(Node node) {
         this.prev = node;
      }

      public static void setNextOf(Node node, Node node2) {
         node.setNext(node2);
      }

      private void setParent(LinkedList list) {
         this.parent = list;
      }

      private void setNext(Node node) {
         this.next = node;
      }

      public Node getNext() {
         return this.next;
      }

      public Object getItem() {
         return this.item;
      }

      public Node getPrev() {
         return this.prev;
      }

      public static Node getNextOf(Node node) {
         return node.next;
      }

      public static void setPrevOf(Node node, Node node2) {
         node.setPrev(node2);
      }
   }
}
