package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public interface ISound {
   float getXPosF();

   float getVolume();

   int getRepeatDelay();

   float getYPosF();

   float getPitch();

   ResourceLocation getSoundLocation();

   boolean canRepeat();

   float getZPosF();

   ISound.AttenuationType getAttenuationType();


   public enum AttenuationType {
      NONE(0),
      LINEAR(2);

   private final int type;
   private AttenuationType(int n) {
         this.type = n;
      }

      public int getTypeInt() {
         return this.type;
      }
   }
}
