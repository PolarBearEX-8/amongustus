package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.util.DefaultResourceLocationEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySlime;

public class GoldenBoots implements PreMotionDispatcher {
   public final DefaultResourceLocationEntry defaultResourceLocationEntry;
   public final PassiveFloatEntry passiveFloatEntry = new DiamondRod(0);

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntitySlime((EntitySlime)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   public void applyEntitySlime(EntitySlime entitySlime, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (!entitySlime.isActive12()) {
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableCull();
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 771);
         this.passiveFloatEntry.applyPassiveFloatEntry(this.defaultResourceLocationEntry.getPassiveFloatEntry());
         this.passiveFloatEntry.applyEntity(entitySlime, posX, posX2, posX4, posX5, posY, posZ, true);
         GlStateManager.enableBlend();
         GlStateManager.disableCull();
      }
   }

   public GoldenBoots(DefaultResourceLocationEntry defaultResourceLocationEntry2) {
      this.defaultResourceLocationEntry = defaultResourceLocationEntry2;
   }
}
