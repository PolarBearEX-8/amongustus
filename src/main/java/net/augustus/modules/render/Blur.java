package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventRenderEntity;
import net.augustus.render.RenderPassTask;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.BlurItemOption;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.settings.options.EspRenderModeOption;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Blur extends Module implements RenderEntityListener {
   public final StringValue modesNew;
   public final DoubleValue sigma;
   public final BooleanValue inScreens;
   public final BooleanValue blurEntities;
   private final TargetSelectionSettings entities;
   private final MultiOptionSetting itemsToBlur;

   public Blur() {
       super("Blur", "Unschärfe", new Color(170, 181, 182, 255), Categorys.RENDER);
      this.modesNew = (StringValue)new StringValue(this, "ModesNew", "Kawase", new String[]{"Kawase", "Gaussian", "Box"}).withDisplayName("Modes");
      this.sigma = new DoubleValue(this, "Sigma", 7.0, 4.0, 30.0, 1);
      this.inScreens = new BooleanValue(this, "InScreens", true);
      this.blurEntities = new BooleanValue(this, "BlurEntities", false);
      TargetSelectionSettings targetSelectionSettings2 = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilterWithSelf()).withTargetsDisplayName("Entities");
      BooleanValue blurEntities2 = this.blurEntities;
      this.entities = targetSelectionSettings2.withVisibility(() -> blurEntities2.getValue());
      this.itemsToBlur = new MultiOptionSetting(this, "ItemsToBlur", BlurItemOption.values());
   }

   @Override
   public void onEnable() {
      BlurShadowManager.shouldWarnIncompatibleSettings = true;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(31);
   }

   @Override
   public void onRenderEntity(EventRenderEntity event) {
      if (this.blurEntities.getValue() && (!ClientManagers.moduleManager.esp.isToggled() || !EspRenderModeOption.VANILLA.isSelected())) {
         EntityLivingBase entity = event.getEntity();
         if (this.entities.getTargetFilter().matches(entity)) {
            BlurShadowManager.enableBlurOnce();
            BlurShadowManager.disableNormalPass();
            RendererLivingEntity renderer = event.getRenderer();
            EntityLivingBase entity2 = event.getEntity();
            BlurShadowManager.submitRenderTask(!entity.isRenderNameTag(), shaderRenderPass -> renderEntityBlurPass(event, entity2, renderer, entity, shaderRenderPass));
         }
      }
   }

   private static void renderEntityBlurPass(EventRenderEntity event, EntityLivingBase entity, RendererLivingEntity rendererLivingEntity, Entity entity2, ShaderRenderPass shaderRenderPass) {
      event.renderEntityModel(entity, rendererLivingEntity, entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)mc.getTimer().renderPartialTicks, entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)mc.getTimer().renderPartialTicks, entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks, true);
   }

}
