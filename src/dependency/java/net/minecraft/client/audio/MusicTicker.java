package net.minecraft.client.audio;

import client.event.PreUpdateConsumer;
import client.setting.BaseOptionType;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class MusicTicker implements PreUpdateConsumer {
   public final Random random = new Random();
   public final Minecraft mc;
   public ISound iSound;
   public int count = 100;

   public void applyBaseOptionType(BaseOptionType baseOptionType) {
      this.iSound = PositionedSoundRecord.createFullVolumeSound(baseOptionType.getResourceLocation());
      this.mc.getMcSoundHandler().applyISound2(this.iSound);
      this.count = Integer.MAX_VALUE;
   }

   @Override
   public void updateState() {
      BaseOptionType baseOptionType = this.mc.getBaseOptionType();
      if (this.iSound != null) {
         if (!baseOptionType.getResourceLocation().equals(this.iSound.getPositionedSoundLocation())) {
            this.mc.getMcSoundHandler().applyISound(this.iSound);
            this.count = MathHelper.nextInt(this.random, 0, baseOptionType.getCount2() / 2);
         }

         if (!this.mc.getMcSoundHandler().matchesISound(this.iSound)) {
            this.iSound = null;
            this.count = Math.min(MathHelper.nextInt(this.random, baseOptionType.getCount2(), baseOptionType.getCount()), this.count);
         }
      }

      if (this.iSound == null && this.count-- <= 0) {
         this.applyBaseOptionType(baseOptionType);
      }
   }

   public void updateState2() {
      if (this.iSound != null) {
         this.mc.getMcSoundHandler().applyISound(this.iSound);
         this.iSound = null;
         this.count = 0;
      }
   }

   public MusicTicker(Minecraft mc2) {
      this.mc = mc2;
   }
}
