package client.setting;

public enum ServiceOptionType {
    NONE(0.0F, 0.0F, 30, 1),
    HOP(0.8F, 0.2F, 20, 10),
    STEP(1.0F, 0.45F, 14, 14),
    SPRINT(1.75F, 0.4F, 1, 8),
    ATTACK(2.0F, 0.7F, 7, 8);

   public final float factor;
   public final float factor2;
   public final int count;
   public final int count2;
   public static final ServiceOptionType[] ENUM_VALUES = new ServiceOptionType[]{NONE, HOP, STEP, SPRINT, ATTACK};

   public int getCount2() {
      return this.count2;
   }

   public float getFactor() {
      return this.factor;
   }

   private ServiceOptionType(float factor3, float factor4, int count3, int count4) {
      this.factor = factor3;
      this.factor2 = factor4;
      this.count = count3;
      this.count2 = count4;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public int getCount() {
      return this.count;
   }


}
