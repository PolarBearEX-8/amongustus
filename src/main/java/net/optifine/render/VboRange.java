package net.optifine.render;

import net.optifine.util.LinkedList;

public class VboRange {
   private int position = -1;
   private int size = 0;
   private LinkedList.Node node = new LinkedList.Node(this);

   public int getPosition() {
      return this.position;
   }

   public LinkedList.Node getNode() {
      return this.node;
   }

   public int getPositionNext() {
      return this.position + this.size;
   }

   public VboRange getPrev() {
      LinkedList.Node prev = this.node.getPrev();
      return prev == null ? null : (VboRange)prev.getItem();
   }

   public void setSize(int n) {
      this.size = n;
   }

   public void setPosition(int n) {
      this.position = n;
   }

   public VboRange getNext() {
      LinkedList.Node next = this.node.getNext();
      return next == null ? null : (VboRange)next.getItem();
   }

   @Override
   public String toString() {
      return "" + this.position + "/" + this.size + "/" + (this.position + this.size);
   }

   public int getSize() {
      return this.size;
   }
}
