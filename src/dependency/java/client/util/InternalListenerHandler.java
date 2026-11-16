package client.util;

import client.event.PreMotionDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class InternalListenerHandler implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/spider_eyes.png");
   public final EnhancedResourceLocationEntry enhancedResourceLocationEntry;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntitySpider((EntitySpider)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   public void applyEntitySpider(EntitySpider entitySpider, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.enhancedResourceLocationEntry.applyResourceLocation2(resourceLocation);
      GlStateManager.disableBlend();
      GlStateManager.applyState10();
      GlStateManager.glStateAction3(1, 1);
      if (entitySpider.isActive12()) {
         GlStateManager.depthMask(false);
      } else {
         GlStateManager.depthMask(true);
      }

      int count = '\uf0f0' % 65536;
      int count2 = '\uf0f0' / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count / 1.0F, (float)count2 / 1.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState40();
      }

      BuildManager.getRenderGlobalAsRenderGlobal().enabled4 = true;
      this.enhancedResourceLocationEntry.getPassiveFloatEntry().applyEntity(entitySpider, posX, posX2, posX4, posX5, posY, posZ, true);
      BuildManager.getRenderGlobalAsRenderGlobal().enabled4 = false;
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState12();
      }

      int count3 = entitySpider.computeCount(posX3);
      int count4 = count3 % 65536;
      int count5 = count3 / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count4 / 1.0F, (float)count5 / 1.0F);
      this.enhancedResourceLocationEntry.applyEntityMob(entitySpider, posX3);
      GlStateManager.enableBlend();
      GlStateManager.applyState14();
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public InternalListenerHandler(EnhancedResourceLocationEntry enhancedResourceLocationEntry2) {
      this.enhancedResourceLocationEntry = enhancedResourceLocationEntry2;
   }
}
