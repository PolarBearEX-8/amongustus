package cc.baritone.utils;

public enum MOVINGPANNINGEnum {
    PANNING(0, 128),
    REQUESTING(16, 128),
    ERASING(32, 128),
    MOVING(48, 128);

   public final int overlayU;
   public final int overlayV;
   public static final MOVINGPANNINGEnum[] ENUM_VALUES = new MOVINGPANNINGEnum[]{PANNING, REQUESTING, ERASING, MOVING};

   private MOVINGPANNINGEnum(int count, int count2) {
      this.overlayU = count;
      this.overlayV = count2;
   }
}
