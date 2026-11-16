package client.util;

public class OverflowHelper {
   public static final SignumComparable signumComparable2 = SignumComparable.resolveSignumComparableByTime(Long.MIN_VALUE);
   public static final SignumComparable signumComparable = SignumComparable.resolveSignumComparableByTime(Long.MAX_VALUE);

   private OverflowHelper() {
   }

   public static void applySplineParser(SplineParser splineParser) {
      SignumComparable entry = SplineParser.resolveSignumComparable(splineParser);
      if (entry.computeCountBySignumComparable(signumComparable2) >= 0 && entry.computeCountBySignumComparable(signumComparable) <= 0) {
         return;
      }

      throw new ArithmeticException("Overflow");
   }
}
