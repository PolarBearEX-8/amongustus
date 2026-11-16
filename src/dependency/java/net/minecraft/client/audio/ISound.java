package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public interface ISound {
   float getZPosF();

   float getPitch();

   ResourceLocation getPositionedSoundLocation();

   int getRepeatDelay();

   float getVolume();

   float getYPosF();

   boolean isRepeat();

   SoundAttenuationType getAttenuationType();

   float getXPosF();
}
