package client.util;

import client.event.PreMotionDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class TertiaryResourceLocationEntry implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
   public final AltResourceLocationEntry altResourceLocationEntry;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityEnderdragon((EntityEnderdragon)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyEntityEnderdragon(EntityEnderdragon entityEnderdragon, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.altResourceLocationEntry.applyResourceLocation2(resourceLocation);
      GlStateManager.disableBlend();
      GlStateManager.applyState10();
      GlStateManager.glStateAction3(1, 1);
      GlStateManager.applyState3();
      GlStateManager.glDepthFunc(514);
      int count = '\uf0f0' % 65536;
      int count2 = '\uf0f0' / 65536;
      OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count / 1.0F, (float)count2 / 1.0F);
      GlStateManager.applyState13();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState40();
      }

      BuildManager.getRenderGlobalAsRenderGlobal().enabled4 = true;
      this.altResourceLocationEntry.getPassiveFloatEntry().applyEntity(entityEnderdragon, posX, posX2, posX4, posX5, posY, posZ, true);
      BuildManager.getRenderGlobalAsRenderGlobal().enabled4 = false;
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState12();
      }

      this.altResourceLocationEntry.applyEntityMob(entityEnderdragon, posX3);
      GlStateManager.enableBlend();
      GlStateManager.applyState14();
      GlStateManager.glDepthFunc(515);
   }

   public TertiaryResourceLocationEntry(AltResourceLocationEntry altResourceLocationEntry2) {
      this.altResourceLocationEntry = altResourceLocationEntry2;
   }
}
