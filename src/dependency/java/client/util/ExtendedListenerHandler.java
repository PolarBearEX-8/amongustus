package client.util;

import client.event.PreMotionDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.item.CustomItemArrayEntry;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ExtendedListenerHandler implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/wither/wither_armor.png");
   public final ExtraResourceLocationEntry extraResourceLocationEntry;
   public final CustomItemArrayEntry customItemArrayEntry = new CustomItemArrayEntry(0.5F);

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityWitherboss((EntityWitherboss)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void applyEntityWitherboss(EntityWitherboss entityWitherboss, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (entityWitherboss.isActive32()) {
         GlStateManager.depthMask(!entityWitherboss.isActive12());
         this.extraResourceLocationEntry.applyResourceLocation2(resourceLocation);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         float factor = (float)entityWitherboss.ticksExisted + posX3;
         float factor2 = MathHelper.cos(factor * 0.02F) * 3.0F;
         float factor3 = factor * 0.01F;
         GlStateManager.translate(factor2, factor3, 0.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.disableBlend();
         float factor4 = 0.5F;
         GlStateManager.color4f2(factor4, factor4, factor4, 1.0F);
         GlStateManager.applyState3();
         GlStateManager.glStateAction3(1, 1);
         this.customItemArrayEntry.applyEntityLivingBase(entityWitherboss, posX, posX2, posX3);
         this.customItemArrayEntry.applyPassiveFloatEntry(this.extraResourceLocationEntry.getPassiveFloatEntry());
         this.customItemArrayEntry.applyEntity(entityWitherboss, posX, posX2, posX4, posX5, posY, posZ, true);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.applyState13();
         GlStateManager.enableBlend();
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public ExtendedListenerHandler(ExtraResourceLocationEntry extraResourceLocationEntry2) {
      this.extraResourceLocationEntry = extraResourceLocationEntry2;
   }
}
