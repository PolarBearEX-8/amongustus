package client.util;

public class NodeDifferentControlController {
   public final Object dataRef;
   public NodeDifferentControlController nodeDifferentControlController;
   public NodeDifferentControlController nodeDifferentControlController2;
   public NodeDifferentController nodeDifferentController;

   public static NodeDifferentControlController resolveNodeDifferentControlControll(NodeDifferentControlController nodeDifferentControlController3) {
      return nodeDifferentControlController3.nodeDifferentControlController2;
   }

   private void setNodeDifferentController(NodeDifferentController nodeDifferentController2) {
      this.nodeDifferentController = nodeDifferentController2;
   }

   private void setNodeDifferentControlController2(NodeDifferentControlController nodeDifferentControlController3) {
      this.nodeDifferentControlController2 = nodeDifferentControlController3;
   }

   public static void applyNodeDifferentControlControByNodeDifferentCon(NodeDifferentControlController nodeDifferentControlController3, NodeDifferentControlController nodeDifferentControlController4) {
      nodeDifferentControlController3.setNodeDifferentControlController2(nodeDifferentControlController4);
   }

   public NodeDifferentControlController getNodeDifferentControlController2() {
      return this.nodeDifferentControlController2;
   }

   public NodeDifferentControlController(Object entry) {
      this.dataRef = entry;
   }

   private void setNodeDifferentControlController(NodeDifferentControlController nodeDifferentControlController3) {
      this.nodeDifferentControlController = nodeDifferentControlController3;
   }

   @Override
   public String toString() {
      return "" + this.dataRef;
   }

   public static NodeDifferentController resolveNodeDifferentController(NodeDifferentControlController nodeDifferentControlController3) {
      return nodeDifferentControlController3.nodeDifferentController;
   }

   public static void applyNodeDifferentControlContro(NodeDifferentControlController nodeDifferentControlController3, NodeDifferentControlController nodeDifferentControlController4) {
      nodeDifferentControlController3.setNodeDifferentControlController(nodeDifferentControlController4);
   }

   public static void applyNodeDifferentControlContro(NodeDifferentControlController nodeDifferentControlController3, NodeDifferentController nodeDifferentController2) {
      nodeDifferentControlController3.setNodeDifferentController(nodeDifferentController2);
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public NodeDifferentControlController getNodeDifferentControlController() {
      return this.nodeDifferentControlController;
   }
}
