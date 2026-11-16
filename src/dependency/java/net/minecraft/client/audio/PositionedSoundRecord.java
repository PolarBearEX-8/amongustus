package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public class PositionedSoundRecord extends PositionedSound {
   public static PositionedSoundRecord createPositionedSound(ResourceLocation location, float factor) {
      return new PositionedSoundRecord(location, factor, 1.0F, false, 0, SoundAttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static PositionedSoundRecord createButtonPress(ResourceLocation location, float factor) {
      return new PositionedSoundRecord(location, 0.25F, factor, false, 0, SoundAttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static PositionedSoundRecord createRecordAtPosition(ResourceLocation location, float posX, float posY, float posZ) {
      return new PositionedSoundRecord(location, 4.0F, 1.0F, false, 0, SoundAttenuationType.LINEAR, posX, posY, posZ);
   }

   public static PositionedSoundRecord createFullVolumeSound(ResourceLocation location) {
      return new PositionedSoundRecord(location, 1.0F, 1.0F, false, 0, SoundAttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   private PositionedSoundRecord(
      ResourceLocation location, float factor, float factor2, boolean flag, int count, SoundAttenuationType soundAttenuationType, float posX, float posY, float posZ
   ) {
      super(location);
      this.volume = factor;
      this.pitch = factor2;
      this.xPosF = posX;
      this.yPosF = posY;
      this.zPosF = posZ;
      this.repeat = flag;
      this.repeatDelay = count;
      this.attenuationType = soundAttenuationType;
   }

   public PositionedSoundRecord(ResourceLocation location, float posX, float posX2, float posX3, float posY, float posZ) {
      this(location, posX, posX2, false, 0, SoundAttenuationType.LINEAR, posX3, posY, posZ);
   }
}
