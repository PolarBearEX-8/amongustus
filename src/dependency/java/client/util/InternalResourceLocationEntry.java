package client.util;

import client.event.PreMotionDispatcher;
import client.render.QuadBatchRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.DiamondOrb;
import net.minecraft.util.ResourceLocation;

public class InternalResourceLocationEntry implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
   public final QuadBatchRenderer quadBatchRenderer;
   public final DiamondOrb diamondOrb = new DiamondOrb(2.0F);

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityCreeper((EntityCreeper)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void applyEntityCreeper(EntityCreeper entityCreeper, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (entityCreeper.isActive34()) {
         boolean active = entityCreeper.isActive12();
         GlStateManager.depthMask(!active);
         this.quadBatchRenderer.applyResourceLocation2(resourceLocation);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         float factor = (float)entityCreeper.ticksExisted + posX3;
         GlStateManager.translate(factor * 0.01F, factor * 0.01F, 0.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.disableBlend();
         float factor2 = 0.5F;
         GlStateManager.color4f2(factor2, factor2, factor2, 1.0F);
         GlStateManager.applyState3();
         GlStateManager.glStateAction3(1, 1);
         this.diamondOrb.applyPassiveFloatEntry(this.quadBatchRenderer.getPassiveFloatEntry());
         this.diamondOrb.applyEntity(entityCreeper, posX, posX2, posX4, posX5, posY, posZ, true);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.applyState13();
         GlStateManager.enableBlend();
         GlStateManager.depthMask(active);
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public InternalResourceLocationEntry(QuadBatchRenderer quadBatchRenderer2) {
      this.quadBatchRenderer = quadBatchRenderer2;
   }
}
