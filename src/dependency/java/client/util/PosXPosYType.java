package client.util;

public enum PosXPosYType {
   POS_X("tx"),
   POS_Y("ty"),
   POS_Z("tz"),
   ANGLE_X("rx"),
   ANGLE_Y("ry"),
   ANGLE_Z("rz"),
   OFFSET_X("ox"),
   OFFSET_Y("oy"),
   OFFSET_Z("oz"),
   SCALE_X("sx"),
   SCALE_Y("sy"),
   SCALE_Z("sz");

   public String labelText;
   public static PosXPosYType[] VALUES = values();
   public static final PosXPosYType[] ENUM_VALUES = values();

   public float computeFactor(BoxesManager boxesManager) {
      switch (PosXPosYTypePosXPosYTypeSwitchMap.countArray[this.ordinal()]) {
         case 1:
            return boxesManager.factor5;
         case 2:
            return boxesManager.factor2;
         case 3:
            return boxesManager.factor10;
         case 4:
            return boxesManager.factor;
         case 5:
            return boxesManager.factor9;
         case 6:
            return boxesManager.factor8;
         case 7:
            return boxesManager.factor4;
         case 8:
            return boxesManager.factor6;
         case 9:
            return boxesManager.factor3;
         case 10:
            return boxesManager.factor7;
         case 11:
            return boxesManager.factor13;
         case 12:
            return boxesManager.factor14;
         default:
            BuildManager.applyText3("GetFloat not supported for: " + this);
            return 0.0F;
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   private PosXPosYType(String text) {
      this.labelText = text;
   }

   public static PosXPosYType resolvePosXPosYType(String text) {
      for (int index = 0; index < VALUES.length; index++) {
         PosXPosYType posXPosYType = VALUES[index];
         if (posXPosYType.getLabelText().equals(text)) {
            return posXPosYType;
         }
      }

      return null;
   }

   public void applyBoxesManager(BoxesManager boxesManager, float factor) {
      switch (PosXPosYTypePosXPosYTypeSwitchMap.countArray[this.ordinal()]) {
         case 1:
            boxesManager.factor5 = factor;
            return;
         case 2:
            boxesManager.factor2 = factor;
            return;
         case 3:
            boxesManager.factor10 = factor;
            return;
         case 4:
            boxesManager.factor = factor;
            return;
         case 5:
            boxesManager.factor9 = factor;
            return;
         case 6:
            boxesManager.factor8 = factor;
            return;
         case 7:
            boxesManager.factor4 = factor;
            return;
         case 8:
            boxesManager.factor6 = factor;
            return;
         case 9:
            boxesManager.factor3 = factor;
            return;
         case 10:
            boxesManager.factor7 = factor;
            return;
         case 11:
            boxesManager.factor13 = factor;
            return;
         case 12:
            boxesManager.factor14 = factor;
            return;
         default:
            BuildManager.applyText3("SetFloat not supported for: " + this);
      }
   }
}
