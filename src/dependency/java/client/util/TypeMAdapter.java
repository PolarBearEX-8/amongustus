package client.util;

import net.minecraft.client.audio.AbstractPositionedSound;
import net.minecraft.client.audio.SoundAttenuationType;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.util.ResourceLocation;

public class TypeMAdapter extends AbstractPositionedSound {
   public final EntityGuardian entityGuardian;

   public TypeMAdapter(EntityGuardian entityGuardian2) {
      super(new ResourceLocation("minecraft:mob.guardian.attack"));
      this.entityGuardian = entityGuardian2;
      this.attenuationType = SoundAttenuationType.NONE;
      this.repeat = true;
      this.repeatDelay = 0;
   }

   @Override
   public void updateState() {
      if (!this.entityGuardian.isDead && this.entityGuardian.isActive33()) {
         this.xPosF = (float)this.entityGuardian.posX;
         this.yPosF = (float)this.entityGuardian.posY;
         this.zPosF = (float)this.entityGuardian.posZ;
         float factor = this.entityGuardian.computeFactor4(0.0F);
         this.volume = 0.0F + 1.0F * factor * factor;
         this.pitch = 0.7F + 0.5F * factor;
      } else {
         this.enabled = true;
      }
   }
}
