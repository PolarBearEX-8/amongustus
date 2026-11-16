package client.util;

import java.util.Iterator;

public class NodeDifferentController {
   public NodeDifferentControlController nodeDifferentControlController;
   public NodeDifferentControlController nodeDifferentControlController2;
   public int count;

   @Override
   public String toString() {
      StringBuffer builder = new StringBuffer();
      Iterator iterator = this.getIterator();

      while (iterator.hasNext()) {
         NodeDifferentControlController nodeDifferentControlController3 = (NodeDifferentControlController)iterator.next();
         if (builder.length() > 0) {
            builder.append(", ");
         }

         builder.append(nodeDifferentControlController3.getDataRef());
      }

      return "" + this.count + " [" + builder.toString() + "]";
   }

   public NodeDifferentControlController resolveNodeDifferentControlControll(NodeDifferentControlController nodeDifferentControlController3) {
      this.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController3);
      NodeDifferentControlController nodeDifferentControlController4 = nodeDifferentControlController3.getNodeDifferentControlController();
      NodeDifferentControlController nodeDifferentControlController5 = nodeDifferentControlController3.getNodeDifferentControlController2();
      if (nodeDifferentControlController4 != null) {
         NodeDifferentControlController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController4, nodeDifferentControlController5);
      } else {
         this.nodeDifferentControlController = nodeDifferentControlController5;
      }

      if (nodeDifferentControlController5 != null) {
         NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController5, nodeDifferentControlController4);
      } else {
         this.nodeDifferentControlController2 = nodeDifferentControlController4;
      }

      NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController3, (NodeDifferentControlController)null);
      NodeDifferentControlController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController3, null);
      NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController3, (NodeDifferentController)null);
      this.count--;
      return nodeDifferentControlController3;
   }

   public void applyNodeDifferentControlContro3(NodeDifferentControlController nodeDifferentControlController3) {
      this.applyNodeDifferentControlContro2(nodeDifferentControlController3);
      if (this.isActive()) {
         this.nodeDifferentControlController = nodeDifferentControlController3;
         this.nodeDifferentControlController2 = nodeDifferentControlController3;
      } else {
         NodeDifferentControlController nodeDifferentControlController4 = this.nodeDifferentControlController;
         NodeDifferentControlController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController3, nodeDifferentControlController4);
         NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController4, nodeDifferentControlController3);
         this.nodeDifferentControlController = nodeDifferentControlController3;
      }

      NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController3, this);
      this.count++;
   }

   public Iterator getIterator() {
      return new RemoveIterator(this);
   }

   public boolean isActive() {
      return this.count <= 0;
   }

   private void applyNodeDifferentControlControByNodeDifferentCon(NodeDifferentControlController nodeDifferentControlController3) {
      if (NodeDifferentControlController.resolveNodeDifferentController(nodeDifferentControlController3) != this) {
         throw new IllegalArgumentException(
            "Node has different parent, node: " + nodeDifferentControlController3 + ", parent: " + NodeDifferentControlController.resolveNodeDifferentController(nodeDifferentControlController3) + ", this: " + this
         );
      }
   }

   public void applyNodeDifferentControlContro(NodeDifferentControlController nodeDifferentControlController3, NodeDifferentControlController nodeDifferentControlController4) {
      this.resolveNodeDifferentControlControll(nodeDifferentControlController4);
      this.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController3, nodeDifferentControlController4);
   }

   public boolean matchesNodeDifferentControlContro(
      NodeDifferentControlController nodeDifferentControlController3, NodeDifferentControlController nodeDifferentControlController4, NodeDifferentControlController nodeDifferentControlController5
   ) {
      this.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController4);
      if (nodeDifferentControlController5 != null) {
         this.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController5);
      }

      NodeDifferentControlController nodeDifferentControlController6;
      for (nodeDifferentControlController6 = nodeDifferentControlController4; nodeDifferentControlController6 != null && nodeDifferentControlController6 != nodeDifferentControlController5; nodeDifferentControlController6 = nodeDifferentControlController6.getNodeDifferentControlController2()) {
         if (nodeDifferentControlController6 == nodeDifferentControlController3) {
            return true;
         }
      }

      if (nodeDifferentControlController6 != nodeDifferentControlController5) {
         throw new IllegalArgumentException("Sublist is not linked, from: " + nodeDifferentControlController4 + ", to: " + nodeDifferentControlController5);
      } else {
         return false;
      }
   }

   private void applyNodeDifferentControlContro2(NodeDifferentControlController nodeDifferentControlController3) {
      if (NodeDifferentControlController.resolveNodeDifferentController(nodeDifferentControlController3) != null) {
         throw new IllegalArgumentException(
            "Node has different parent, node: " + nodeDifferentControlController3 + ", parent: " + NodeDifferentControlController.resolveNodeDifferentController(nodeDifferentControlController3) + ", this: " + this
         );
      }
   }

   public boolean matchesNodeDifferentControlContro(NodeDifferentControlController nodeDifferentControlController3) {
      return NodeDifferentControlController.resolveNodeDifferentController(nodeDifferentControlController3) == this;
   }

   public NodeDifferentControlController getNodeDifferentControlController2() {
      return this.nodeDifferentControlController2;
   }

   public void applyNodeDifferentControlContro(NodeDifferentControlController nodeDifferentControlController3) {
      this.applyNodeDifferentControlContro2(nodeDifferentControlController3);
      if (this.isActive()) {
         this.nodeDifferentControlController = nodeDifferentControlController3;
         this.nodeDifferentControlController2 = nodeDifferentControlController3;
      } else {
         NodeDifferentControlController nodeDifferentControlController4 = this.nodeDifferentControlController2;
         NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController3, nodeDifferentControlController4);
         NodeDifferentControlController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController4, nodeDifferentControlController3);
         this.nodeDifferentControlController2 = nodeDifferentControlController3;
      }

      NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController3, this);
      this.count++;
   }

   public void applyNodeDifferentControlControByNodeDifferentCon(NodeDifferentControlController nodeDifferentControlController3, NodeDifferentControlController nodeDifferentControlController4) {
      if (nodeDifferentControlController3 == null) {
         this.applyNodeDifferentControlContro3(nodeDifferentControlController4);
      } else if (nodeDifferentControlController3 == this.nodeDifferentControlController2) {
         this.applyNodeDifferentControlContro(nodeDifferentControlController4);
      } else {
         this.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController3);
         this.applyNodeDifferentControlContro2(nodeDifferentControlController4);
         NodeDifferentControlController nodeDifferentControlController5 = nodeDifferentControlController3.getNodeDifferentControlController2();
         NodeDifferentControlController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController3, nodeDifferentControlController4);
         NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController4, nodeDifferentControlController3);
         NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController5, nodeDifferentControlController4);
         NodeDifferentControlController.applyNodeDifferentControlControByNodeDifferentCon(nodeDifferentControlController4, nodeDifferentControlController5);
         NodeDifferentControlController.applyNodeDifferentControlContro(nodeDifferentControlController4, this);
         this.count++;
      }
   }

   public NodeDifferentControlController getNodeDifferentControlController() {
      return this.nodeDifferentControlController;
   }

   public int getCount() {
      return this.count;
   }
}
