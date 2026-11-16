package client.util;

public class GeometryCalculator {
   public int count = -1;
   public int count2 = 0;
   public NodeDifferentControlController nodeDifferentControlController = new NodeDifferentControlController(this);

   public void setCount2(int count3) {
      this.count2 = count3;
   }

   public int getCount() {
      return this.count + this.count2;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public GeometryCalculator getGeometryCalculator() {
      NodeDifferentControlController nodeDifferentControlController2 = this.nodeDifferentControlController.getNodeDifferentControlController();
      return nodeDifferentControlController2 == null ? null : (GeometryCalculator)nodeDifferentControlController2.getDataRef();
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   public NodeDifferentControlController getNodeDifferentControlController() {
      return this.nodeDifferentControlController;
   }

   @Override
   public String toString() {
      return "" + this.count + "/" + this.count2 + "/" + (this.count + this.count2);
   }

   public GeometryCalculator getGeometryCalculatorAsGeometryCalculat() {
      NodeDifferentControlController nodeDifferentControlController2 = this.nodeDifferentControlController.getNodeDifferentControlController2();
      return nodeDifferentControlController2 == null ? null : (GeometryCalculator)nodeDifferentControlController2.getDataRef();
   }

   public int getCount2() {
      return this.count2;
   }
}
