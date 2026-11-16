package client.util;

public enum LINEAREnum {
   LINEAR("linear") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         return Helper3.computeFactor(posX, posY, posZ);
      }
   },
   QUAD_IN("quad_in") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         return posX + (posY - posX) * posZ * posZ;
      }
   },
   QUAD_OUT("quad_out") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         return posX - (posY - posX) * posZ * (posZ - 2.0F);
      }
   },
   QUAD_INOUT("quad_inout") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         float factor = posZ * 2.0F;
         return factor - 1.0F;
      }
   },
   CUBIC_IN("cubic_in") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         return posX + (posY - posX) * posZ * posZ * posZ;
      }
   },
   CUBIC_OUT("cubic_out") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         float factor = posZ - 1.0F;
         return posX + (posY - posX) * (factor * factor * factor + 1.0F);
      }
   },
   CUBIC_INOUT("cubic_inout") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         float factor = posZ * 2.0F;
         float factor2 = factor + 1.0F;
         String[] textArray = new String[3];
         return factor2;
      }
   },
   EXP_IN("exp_in") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         return posX + (posY - posX) * (float)Math.pow(2.0, (double)(10.0F * (posZ - 1.0F)));
      }
   },
   EXP_OUT("exp_out") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         return posX + (posY - posX) * (float)(-Math.pow(2.0, (double)(-10.0F * posZ)) + 1.0);
      }
   },
   EXP_INOUT("exp_inout") {
      @Override
      public float pow(float posX, float posY, float posZ) {
         if (!(posZ >= 0.0F)) {
            return posX + (posY - posX) / 2.0F * (float)Math.pow(2.0, (double)(10.0F * (posZ - 1.0F)));
         } else {
            float factor = posZ - 1.0F;
            return posX + (posY - posX) / 2.0F * (float)(-Math.pow(2.0, (double)(-10.0F * factor)) + 2.0);
         }
      }
   };

   public final String key;
   public static final LINEAREnum[] ENUM_VALUES = values();

   private LINEAREnum(String text) {
      this.key = text;
   }

   public String getText() {
      return "mclib.interpolations." + this.key;
   }

   public abstract float pow(float posX, float posY, float posZ);

}
