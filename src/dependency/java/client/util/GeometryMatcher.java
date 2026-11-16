package client.util;

public class GeometryMatcher {
   public double value;
   public double value2;

   public double computeValue(GeometryMatcher geometryMatcher) {
      double offset = geometryMatcher.value2 - this.value2;
      double offset2 = geometryMatcher.value - this.value;
      return Math.sqrt(offset * offset + offset2 * offset2);
   }

   public GeometryMatcher(double offset, double offset2) {
      this.value2 = offset;
      this.value = offset2;
   }
}
