package client.util;

import net.minecraft.client.audio.AbstractPositionedSound;
import net.minecraft.client.audio.SoundAttenuationType;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CommonCustom extends AbstractPositionedSound {
   public final EntityPlayer entityPlayer;
   public final EntityMinecart entityMinecart;

   @Override
   public void updateState() {
      if (!this.entityMinecart.isDead && this.entityPlayer.isSneaking() && this.entityPlayer.ridingEntity == this.entityMinecart) {
         float factor = MathHelper.sqrt_double(
            this.entityMinecart.motionX * this.entityMinecart.motionX + this.entityMinecart.motionZ * this.entityMinecart.motionZ
         );
         if ((double)factor >= 0.01) {
            this.volume = 0.0F + MathHelper.clamp_float(factor, 0.0F, 1.0F) * 0.75F;
         } else {
            this.volume = 0.0F;
         }
      } else {
         this.enabled = true;
      }
   }

   public CommonCustom(EntityPlayer player, EntityMinecart entityMinecart2) {
      super(new ResourceLocation("minecraft:minecart.inside"));
      this.entityPlayer = player;
      this.entityMinecart = entityMinecart2;
      this.attenuationType = SoundAttenuationType.NONE;
      this.repeat = true;
      this.repeatDelay = 0;
   }
}
