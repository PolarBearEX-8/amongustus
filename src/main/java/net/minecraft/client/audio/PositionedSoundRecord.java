package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public class PositionedSoundRecord extends PositionedSound {
   public PositionedSoundRecord(ResourceLocation location, float f, float f2, float f3, float f4, float f5) {
      this(location, f, f2, false, 0, ISound.AttenuationType.LINEAR, f3, f4, f5);
   }

   public static PositionedSoundRecord createWithVolume(ResourceLocation location, float f) {
      return new PositionedSoundRecord(location, f, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static PositionedSoundRecord create(ResourceLocation location, float f) {
      return new PositionedSoundRecord(location, 0.25F, f, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static PositionedSoundRecord create(ResourceLocation location, float f, float f2, float f3) {
      return new PositionedSoundRecord(location, 4.0F, 1.0F, false, 0, ISound.AttenuationType.LINEAR, f, f2, f3);
   }

   public static PositionedSoundRecord create(ResourceLocation location) {
      return new PositionedSoundRecord(location, 1.0F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   private PositionedSoundRecord(
      ResourceLocation location, float f, float f2, boolean flag, int n, ISound.AttenuationType attenuationType2, float f3, float f4, float f5
   ) {
      super(location);
      this.volume = f;
      this.pitch = f2;
      this.xPosF = f3;
      this.yPosF = f4;
      this.zPosF = f5;
      this.repeat = flag;
      this.repeatDelay = n;
      this.attenuationType = attenuationType2;
   }
}
