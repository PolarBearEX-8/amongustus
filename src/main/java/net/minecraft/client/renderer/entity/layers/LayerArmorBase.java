package net.minecraft.client.renderer.entity.layers;

import net.augustus.modules.Module;
import net.augustus.modules.render.NoArmorOverlay;
import net.augustus.settings.options.CustomGlintRemoveItemOption;
import net.augustus.ClientManagers;
import net.augustus.events.EventItemGlint;
import net.augustus.settings.options.CustomGlintFakeEnchantOption;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.augustus.events.listeners.ItemGlintListener;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;

public abstract class LayerArmorBase implements LayerRenderer {
   public static final ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   public ModelBase modelLeggings;
   public ModelBase modelArmor;
   private final RendererLivingEntity renderer;
   private float alpha = 1.0F;
   private float colorR = 1.0F;
   private float colorG = 1.0F;
   private float colorB = 1.0F;
   private boolean skipRenderGlint;
   private static final ResourceLocation[] LAYER_2_TEXTURES = new ResourceLocation[5];
   private static final ResourceLocation[] LAYER_2_OVERLAY_TEXTURES = new ResourceLocation[5];
   private static final ResourceLocation[] LAYER_1_OVERLAY_TEXTURES = new ResourceLocation[5];
   private static final ResourceLocation[] LAYER_1_TEXTURES = new ResourceLocation[5];

   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (!ClientManagers.moduleManager.modelRenderer.shouldRenderCustomModel(entity) || ClientManagers.moduleManager.modelRenderer.renderArmor.getValue()) {
         NoArmorOverlay noArmorOverlay;
         boolean flag;
         noArmorOverlay = ClientManagers.moduleManager.noArmorOverlay;
            flag = ((Module)noArmorOverlay).isToggled();
if (flag) {
if (!noArmorOverlay.shouldHideHelmet(entity)) {
this.renderLayer(entity, f, f2, f3, f4, f5, f6, f7, 4);
}
} else {
this.renderLayer(entity, f, f2, f3, f4, f5, f6, f7, 4);
}

         if (!flag || !noArmorOverlay.shouldHideChestplate(entity)) {
            this.renderLayer(entity, f, f2, f3, f4, f5, f6, f7, 3);
         }

         if (!flag || !noArmorOverlay.shouldHideLeggings(entity)) {
            this.renderLayer(entity, f, f2, f3, f4, f5, f6, f7, 2);
         }

         if (!flag || !noArmorOverlay.shouldHideBoots(entity)) {
            this.renderLayer(entity, f, f2, f3, f4, f5, f6, f7, 1);
         }
      }
   }

   private void renderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7, int n) {
      ItemStack currentArmor = this.getCurrentArmor(entity, n);
      if (currentArmor != null && currentArmor.getItem() instanceof ItemArmor) {
         ItemArmor item = (ItemArmor)currentArmor.getItem();
         ModelBase armorModel = this.getArmorModel(n);
         armorModel.setModelAttributes(this.renderer.getMainModel());
         armorModel.setLivingAnimations(entity, f, f2, f3);
         this.setModelPartVisible(armorModel, n);
         boolean isSlotForLeggings = this.isSlotForLeggings(n);
         if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(currentArmor, isSlotForLeggings ? 2 : 1, (String)null)) {
            this.renderer.bindTexture(this.getArmorResource(item, isSlotForLeggings));
         }

         switch (item.getArmorMaterial()) {
            case LEATHER:
               int color = item.getColor(currentArmor);
               float f8 = (float)(color >> 16 & 0xFF) / 255.0F;
               float f9 = (float)(color >> 8 & 0xFF) / 255.0F;
               float f10 = (float)(color & 0xFF) / 255.0F;
               GlStateManager.color(this.colorR * f8, this.colorG * f9, this.colorB * f10, this.alpha);
               armorModel.render(entity, f, f2, f4, f5, f6, f7, true);
               if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(currentArmor, isSlotForLeggings ? 2 : 1, "overlay")) {
                  this.renderer.bindTexture(this.getArmorResource(item, isSlotForLeggings, true));
               }
            case CHAIN:
            case IRON:
            case GOLD:
            case DIAMOND:
               GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
               armorModel.render(entity, f, f2, f4, f5, f6, f7, true);
         }

         boolean flag = (currentArmor.isItemEnchanted() || CustomGlintFakeEnchantOption.ARMOR.isSelected() && ClientManagers.moduleManager.customGlint.isToggled())
            && (!ClientManagers.moduleManager.customGlint.isToggled() || !ClientManagers.moduleManager.customGlint.remove.getValue() || !CustomGlintRemoveItemOption.shouldRemoveGlint(currentArmor));
         if (!this.skipRenderGlint && flag && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(entity, currentArmor, armorModel, f, f2, f3, f4, f5, f6, f7))) {
            this.renderGlintEffect(entity, armorModel, currentArmor, f, f2, f3, f4, f5, f6, f7);
         }
      }
   }

   public abstract void initArmor();

   private ResourceLocation getArmorResource(ItemArmor itemArmor, boolean flag) {
      return this.getArmorResource(itemArmor, flag, false);
   }

   public ModelBase getArmorModel(int n) {
      return this.isSlotForLeggings(n) ? this.modelLeggings : this.modelArmor;
   }

   public LayerArmorBase(RendererLivingEntity rendererLivingEntity) {
      this.renderer = rendererLivingEntity;
      this.initArmor();
   }

   public abstract void setModelPartVisible(ModelBase modelBase, int n);

   public boolean shouldCombineTextures() {
      return Augustus.getInstance().getModuleManager().legacyAnimations.isDamageAnimationEnabled();
   }

   public ItemStack getCurrentArmor(EntityLivingBase entity, int n) {
      return entity.getCurrentArmor(n - 1);
   }

   private ResourceLocation getArmorResource(ItemArmor itemArmor, boolean flag, boolean flag2) {
      int n = itemArmor.getArmorMaterial().ordinal();
      if (flag) {
         if (!flag2) {
            if (LAYER_2_TEXTURES[n] == null) {
               LAYER_2_TEXTURES[n] = new ResourceLocation("textures/models/armor/" + itemArmor.getArmorMaterial().getName() + "_layer_2.png");
            }

            return LAYER_2_TEXTURES[n];
         } else {
            if (LAYER_2_OVERLAY_TEXTURES[n] == null) {
               LAYER_2_OVERLAY_TEXTURES[n] = new ResourceLocation("textures/models/armor/" + itemArmor.getArmorMaterial().getName() + "_layer_2_overlay.png");
            }

            return LAYER_2_OVERLAY_TEXTURES[n];
         }
      } else if (flag2) {
         if (LAYER_1_OVERLAY_TEXTURES[n] == null) {
            LAYER_1_OVERLAY_TEXTURES[n] = new ResourceLocation("textures/models/armor/" + itemArmor.getArmorMaterial().getName() + "_layer_1_overlay.png");
         }

         return LAYER_1_OVERLAY_TEXTURES[n];
      } else {
         if (LAYER_1_TEXTURES[n] == null) {
            LAYER_1_TEXTURES[n] = new ResourceLocation("textures/models/armor/" + itemArmor.getArmorMaterial().getName() + "_layer_1.png");
         }

         return LAYER_1_TEXTURES[n];
      }
   }

   private void renderGlint(EntityLivingBase entity, ModelBase modelBase, ItemStack stack, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if ((!Config.isCustomItems() || CustomItems.isUseGlint()) && (!Config.isShaderPackLoaded() || !Shaders.isShadowPass)) {
         EventItemGlint event = ItemGlintListener.onItemGlint(stack, -8372020, 1.0);
         float f8 = (float)((double)entity.ticksExisted * event.getSpeed()) + f3;
         this.renderer.bindTexture(ENCHANTED_ITEM_GLINT_RES);
         if (Config.isShaderPackLoaded()) {
            ShadersRender.renderEnchantedGlintBegin();
         }

         GlStateManager.enableBlend();
         GlStateManager.depthFunc(514);
         GlStateManager.depthMask(false);
         float f9 = 0.5F;
         GlStateManager.color(f9, f9, f9, 1.0F);

         for (int i = 0; i < 2; i++) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(768, 1);
            RenderUtil.setGlColor(event.getColor());
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f10 = 0.33333334F;
            GlStateManager.scale(f10, f10, f10);
            GlStateManager.rotate(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, f8 * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            if (!event.isCancelled()) {
               modelBase.render(entity, f, f2, f4, f5, f6, f7, true);
            }
         }

         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.enableLighting();
         GlStateManager.depthMask(true);
         GlStateManager.depthFunc(515);
         GlStateManager.disableBlend();
         if (Config.isShaderPackLoaded()) {
            ShadersRender.renderEnchantedGlintEnd();
         }
      }
   }

   private boolean isSlotForLeggings(int n) {
      return n == 2;
   }

   private void renderGlintEffect(EntityLivingBase entity, ModelBase modelBase, ItemStack stack, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.renderGlint(entity, modelBase, stack, f, f2, f3, f4, f5, f6, f7);
      if (Augustus.getInstance().getModuleManager().customGlint.isToggled() && Augustus.getInstance().getModuleManager().customGlint.brighterEnchantments.getValue()) {
         this.renderGlint(entity, modelBase, stack, f, f2, f3, f4, f5, f6, f7);
      }
   }
}
