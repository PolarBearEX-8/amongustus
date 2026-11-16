package net.minecraft.entity;

import client.event.PostRenderConsumer;
import client.setting.GameSettingsPeacefulType;
import net.minecraft.item.GoldenOre;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityCavespider extends EntitySpider {
   @Override
   public float getHeight2() {
      return 0.45F;
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      if (super.matchesEntity4(entity)) {
         if (entity instanceof EntityLivingBase) {
            byte byteValue = 0;
            if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.NORMAL) {
               byteValue = 7;
            } else if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD) {
               byteValue = 15;
            }

            if (byteValue > 0) {
               ((EntityLivingBase)entity).applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion24.indexOffset3, byteValue * 20, 0));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(12.0);
   }

   @Override
   public PostRenderConsumer resolvePostRenderConsumer(GoldenOre goldenOre, PostRenderConsumer postRenderConsumer) {
      return postRenderConsumer;
   }

   public EntityCavespider(World world) {
      super(world);
      this.applyFloat(0.7F, 0.5F);
   }
}
