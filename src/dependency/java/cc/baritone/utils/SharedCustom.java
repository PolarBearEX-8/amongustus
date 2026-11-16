package cc.baritone.utils;

public enum SharedCustom implements BreadthFirstPredictor {
    OR(SharedCustom::checkFlag2),
    AND(SharedCustom::checkFlagByFlag),
    XOR(SharedCustom::checkFlag3);

   public final BreadthFirstPredictor breadthFirstPredictor;
   public static final SharedCustom[] ENUM_VALUES = new SharedCustom[]{OR, AND, XOR};

   private static boolean checkFlagByFlag(boolean flag, boolean flag2) {
      return flag && flag2;
   }

   private static boolean checkFlag2(boolean flag, boolean flag2) {
      return flag || flag2;
   }

   private SharedCustom(BreadthFirstPredictor breadthFirstPredictor2) {
      this.breadthFirstPredictor = breadthFirstPredictor2;
   }

   @Override
   public boolean checkFlag(boolean flag, boolean flag2) {
      return this.breadthFirstPredictor.checkFlag(flag, flag2);
   }



   private static boolean checkFlag3(boolean flag, boolean flag2) {
      return flag ^ flag2;
   }
}
