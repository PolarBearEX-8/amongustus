package client.util;

import client.Client;
import client.ClientGlobals;
import client.event.PreMotionDispatcher;
import client.module.Module;
import client.setting.CustomGlintSwordsType;
import client.setting.NoArmorOverlayHelmetType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class AdvancedResourceLocationEntry implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   public PassiveFloatEntry passiveFloatEntry2;
   public PassiveFloatEntry passiveFloatEntry;
   public final RendererLivingEntity rendererLivingEntity;
   public float factor2 = 1.0F;
   public float factor3 = 1.0F;
   public float factor = 1.0F;
   public float factor4 = 1.0F;
   public boolean enabled;
   public static final ResourceLocation[] resourceLocationArray2 = new ResourceLocation[5];
   public static final ResourceLocation[] resourceLocationArray3 = new ResourceLocation[5];
   public static final ResourceLocation[] resourceLocationArray4 = new ResourceLocation[5];
   public static final ResourceLocation[] resourceLocationArray = new ResourceLocation[5];

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (!ClientGlobals.moduleManager.modelRenderer.checkEntity(livingEntity) || ClientGlobals.moduleManager.modelRenderer.settingRenderArmor.isEnabled()) {
         boolean toggled = ((Module)ClientGlobals.moduleManager.noArmorOverlay).isToggled();
         if (!toggled || !NoArmorOverlayHelmetType.HELMET.isSelected()) {
            this.applyEntityLivingBase(livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ, 4);
         }

         if (!toggled || !NoArmorOverlayHelmetType.CHEST_PLATE.isSelected()) {
            this.applyEntityLivingBase(livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ, 3);
         }

         if (!toggled || !NoArmorOverlayHelmetType.TROUSERS.isSelected()) {
            this.applyEntityLivingBase(livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ, 2);
         }

         if (!toggled || !NoArmorOverlayHelmetType.BOOTS.isSelected()) {
            this.applyEntityLivingBase(livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ, 1);
         }
      }
   }

   public ItemStack resolveItemStack(EntityLivingBase livingEntity, int count) {
      return livingEntity.getItemStack3(count - 1);
   }

   public abstract void updateState();

   public abstract void applyPassiveFloatEntry(PassiveFloatEntry passiveFloatEntry3, int count);

   @Override
   public boolean isActive() {
      return Client.getClient().getModuleManager().legacyAnimations.checkCondition();
   }

   private boolean checkIndex(int count) {
      return count == 2;
   }

   private void applyEntityLivingBase(
      EntityLivingBase livingEntity, PassiveFloatEntry passiveFloatEntry3, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ
   ) {
      if ((!BuildManager.isActive35() || InvalidItemIDManager.isEnabled()) && (!BuildManager.canExecute() || !PreEndHandManager.enabled12)) {
         float factor5 = (float)livingEntity.ticksExisted + posX3;
         this.rendererLivingEntity.applyResourceLocation2(resourceLocation);
         if (BuildManager.canExecute()) {
            RotationBridge.updateState9();
         }

         GlStateManager.disableBlend();
         GlStateManager.glDepthFunc(514);
         GlStateManager.depthMask(false);
         float factor6 = 0.5F;
         GlStateManager.color4f2(factor6, factor6, factor6, 1.0F);

         for (int index = 0; index < 2; index++) {
            GlStateManager.applyState3();
            GlStateManager.glStateAction3(768, 1);
            float factor7 = 0.76F;
            GlStateManager.color4f2(0.5F * factor7, 0.25F * factor7, 0.8F * factor7, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float factor8 = 0.33333334F;
            GlStateManager.translate3f3(factor8, factor8, factor8);
            GlStateManager.rotate(30.0F - (float)index * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, factor5 * (0.001F + (float)index * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            passiveFloatEntry3.applyEntity(livingEntity, posX, posX2, posX4, posX5, posY, posZ, true);
         }

         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.applyState13();
         GlStateManager.depthMask(true);
         GlStateManager.glDepthFunc(515);
         GlStateManager.enableBlend();
         if (BuildManager.canExecute()) {
            RotationBridge.updateState();
         }
      }
   }

   public PassiveFloatEntry resolvePassiveFloatEntry(int count) {
      return this.checkIndex(count) ? this.passiveFloatEntry2 : this.passiveFloatEntry;
   }

   private ResourceLocation resolveResourceLocationByItemGoldenBoots(ItemGoldenBoots itemGoldenBoots, boolean flag, boolean flag2) {
      int ordinal = itemGoldenBoots.getCrafterLeatherType().ordinal();
      if (flag) {
         if (!flag2) {
            if (resourceLocationArray2[ordinal] == null) {
               resourceLocationArray2[ordinal] = new ResourceLocation("textures/models/armor/" + itemGoldenBoots.getCrafterLeatherType().getLabelText() + "_layer_2.png");
            }

            return resourceLocationArray2[ordinal];
         } else {
            if (resourceLocationArray3[ordinal] == null) {
               resourceLocationArray3[ordinal] = new ResourceLocation(
                  "textures/models/armor/" + itemGoldenBoots.getCrafterLeatherType().getLabelText() + "_layer_2_overlay.png"
               );
            }

            return resourceLocationArray3[ordinal];
         }
      } else if (flag2) {
         if (resourceLocationArray4[ordinal] == null) {
            resourceLocationArray4[ordinal] = new ResourceLocation("textures/models/armor/" + itemGoldenBoots.getCrafterLeatherType().getLabelText() + "_layer_1_overlay.png");
         }

         return resourceLocationArray4[ordinal];
      } else {
         if (resourceLocationArray[ordinal] == null) {
            resourceLocationArray[ordinal] = new ResourceLocation("textures/models/armor/" + itemGoldenBoots.getCrafterLeatherType().getLabelText() + "_layer_1.png");
         }

         return resourceLocationArray[ordinal];
      }
   }

   private ResourceLocation resolveResourceLocationByItemGoldenBoots(ItemGoldenBoots itemGoldenBoots, boolean flag) {
      return this.resolveResourceLocationByItemGoldenBoots(itemGoldenBoots, flag, false);
   }

   private void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ, int count) {
      ItemStack stack = this.resolveItemStack(livingEntity, count);
      if (stack != null && stack.getItem() instanceof ItemGoldenBoots) {
         ItemGoldenBoots item = (ItemGoldenBoots)stack.getItem();
         PassiveFloatEntry passiveFloatEntry3 = this.resolvePassiveFloatEntry(count);
         passiveFloatEntry3.applyPassiveFloatEntry(this.rendererLivingEntity.getPassiveFloatEntry());
         passiveFloatEntry3.applyEntityLivingBase(livingEntity, posX, posX2, posX3);
         this.applyPassiveFloatEntry(passiveFloatEntry3, count);
         boolean flag = this.checkIndex(count);
         if (!BuildManager.isActive35() || !InvalidItemIDManager.matchesItemStack(stack, flag ? 2 : 1, (String)null)) {
            this.rendererLivingEntity.applyResourceLocation2(this.resolveResourceLocationByItemGoldenBoots(item, flag));
         }

         switch (DistanceDirector.distanceDirector.countArray[item.getCrafterLeatherType().ordinal()]) {
            case 1:
               int count2 = item.computeCount(stack);
               float factor5 = (float)(count2 >> 16 & 0xFF) / 255.0F;
               float factor6 = (float)(count2 >> 8 & 0xFF) / 255.0F;
               float factor7 = (float)(count2 & 0xFF) / 255.0F;
               GlStateManager.color4f2(this.factor3 * factor5, this.factor * factor6, this.factor4 * factor7, this.factor2);
               passiveFloatEntry3.applyEntity(livingEntity, posX, posX2, posX4, posX5, posY, posZ, true);
               if (!BuildManager.isActive35() || !InvalidItemIDManager.matchesItemStack(stack, flag ? 2 : 1, "overlay")) {
                  this.rendererLivingEntity.applyResourceLocation2(this.resolveResourceLocationByItemGoldenBoots(item, flag, true));
               }
            case 2:
            case 3:
            case 4:
            case 5:
               GlStateManager.color4f2(this.factor3, this.factor, this.factor4, this.factor2);
               passiveFloatEntry3.applyEntity(livingEntity, posX, posX2, posX4, posX5, posY, posZ, true);
         }

         boolean flag2 = (stack.canExecute3() || CustomGlintSwordsType.ARMOR.isSelected() && ((Module)ClientGlobals.moduleManager.customGlint).isToggled())
            && (!((Module)ClientGlobals.moduleManager.customGlint).isToggled() || !ClientGlobals.moduleManager.customGlint.settingRemove.isEnabled());
         if (!this.enabled
            && flag2
            && (
               !BuildManager.isActive35()
                  || !InvalidItemIDManager.matchesEntityLivingBaseByEntityLivingBase(livingEntity, stack, passiveFloatEntry3, posX, posX2, posX3, posX4, posX5, posY, posZ)
            )) {
            this.applyEntityLivingBase(livingEntity, passiveFloatEntry3, posX, posX2, posX3, posX4, posX5, posY, posZ);
         }
      }
   }

   public AdvancedResourceLocationEntry(RendererLivingEntity rendererLivingEntity2) {
      this.rendererLivingEntity = rendererLivingEntity2;
      this.updateState();
   }
}
