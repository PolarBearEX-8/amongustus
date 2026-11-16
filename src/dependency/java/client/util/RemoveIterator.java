package client.util;

import java.util.Iterator;

public class RemoveIterator implements Iterator {
   public NodeDifferentControlController nodeDifferentControlController;
   public final NodeDifferentController nodeDifferentController;

   @Override
   public boolean hasNext() {
      return this.nodeDifferentControlController != null;
   }

   public NodeDifferentControlController getNodeDifferentControlControll() {
      NodeDifferentControlController nodeDifferentControlController2 = this.nodeDifferentControlController;
      if (this.nodeDifferentControlController != null) {
         this.nodeDifferentControlController = NodeDifferentControlController.resolveNodeDifferentControlControll(this.nodeDifferentControlController);
      }

      return nodeDifferentControlController2;
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("remove");
   }

   @Override
   public Object next() {
      return this.getNodeDifferentControlControll();
   }

   public RemoveIterator(NodeDifferentController nodeDifferentController2) {
      this.nodeDifferentController = nodeDifferentController2;
      this.nodeDifferentControlController = this.nodeDifferentController.getNodeDifferentControlController();
   }
}
