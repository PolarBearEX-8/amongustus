package net.minecraft.client.audio;

public enum SoundAttenuationType {
    NONE(0),
    LINEAR(2);

   public final int count;
   public static final SoundAttenuationType[] ENUM_VALUES = new SoundAttenuationType[]{NONE, LINEAR};

   public int getCount() {
      return this.count;
   }

   private SoundAttenuationType(int count2) {
      this.count = count2;
   }


}
