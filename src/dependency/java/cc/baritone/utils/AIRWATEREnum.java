package cc.baritone.utils;

public enum AIRWATEREnum {
    AIR(0),
    WATER(1),
    AVOID(2),
    SOLID(3);

   public final boolean[] enabledArray;

   public static AIRWATEREnum resolveAIRWATEREnum(boolean flag, boolean flag2) {
      return flag ? (flag2 ? SOLID : AVOID) : (flag2 ? WATER : AIR);
   }



   private AIRWATEREnum(int count) {
      this.enabledArray = new boolean[]{(count & 2) != 0, (count & 1) != 0};
   }

   public final boolean[] getEnabledArray() {
      return this.enabledArray;
   }
}
