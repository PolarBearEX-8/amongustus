package net.augustus.modules.render;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.options.PotionHudEffectOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.settings.ColorSetting;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.opengl.GL11;

public class PotionHUD extends Module implements RenderListener {
   private final DoubleValue xCoord;
   private final DoubleValue yCoord;
   private final DoubleValue scale;
   private final DoubleValue margin;
   private final ColorSetting backgroundColor;
   private final BooleanValue potionIcon;
   private final BlurShadowSettings blurShadowSettings;
   private final MultiOptionSetting potionsToDisplay;
   private final Map potionOptionMap;

   public PotionHUD() {
       super("PotionHUD", "Tränke-ÜberlagerungsElemente", new Color(123, 252, 3), Categorys.RENDER);
      this.xCoord = new DoubleValue(this, "X-Coord", 0.0, 0.0, 100.0, 2);
      this.yCoord = new DoubleValue(this, "Y-Coord", 0.0, 0.0, 100.0, 2);
      this.scale = new DoubleValue(this, "Scale", 2.0, 0.5, 4.0, 2);
      this.margin = new DoubleValue(this, "Margin", 1.0, 0.0, 10.0, 0);
      this.backgroundColor = new ColorSetting(this, "BackgroundColor", new Color(0, 0, 0, 150));
      this.potionIcon = new BooleanValue(this, "PotionIcon", true);
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      this.potionOptionMap = new HashMap();
      List list = PotionHudEffectOption.createAllOptions();
      this.potionsToDisplay = new MultiOptionSetting(this, "PotionsToDisplay", (SelectableOption[])list.toArray(new PotionHudEffectOption[0]));

      for (PotionHudEffectOption potionHudEffectOption : (Iterable<PotionHudEffectOption>)(list)) {
         this.potionOptionMap.put(potionHudEffectOption.getPotion(), potionHudEffectOption);
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      Collection activePotionEffects = mc.thePlayer.getActivePotionEffects();
      GL11.glPushMatrix();
      GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
      GL11.glScaled(this.scale.getValue(), this.scale.getValue(), 1.0);
      ScaledResolution sr2 = new ScaledResolution(
         (double)mc.displayWidth * (1.0 / this.scale.getValue()), (double)mc.displayHeight * (1.0 / this.scale.getValue()), (int)this.scale.getValue()
      );
      if (!activePotionEffects.isEmpty()) {
         byte b = 32;
         int n = (int)((double)b + this.margin.getValue());
         int n2 = (int)((double)(sr2.getScaledWidth() - 142) * (this.xCoord.getValue() / 100.0));
         int n3 = (int)((double)(sr2.getScaledHeight() - (n * (activePotionEffects.size() - 1) + b)) * (this.yCoord.getValue() / 100.0));
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableLighting();
         GlStateManager.enableAlpha();
         int colorInt = this.backgroundColor.getColorInt((byte)0);
         int n4 = this.potionIcon.getValue() ? 23 : 0;
         int n5 = 119 + n4;

         for (PotionEffect potionEffect : (Iterable<PotionEffect>)(mc.thePlayer.getActivePotionEffects())) {
            Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            if (potion != null) {
               PotionHudEffectOption potionHudEffectOption = (PotionHudEffectOption)this.potionOptionMap.get(potion);
               if (potionHudEffectOption == null || potionHudEffectOption.isSelected()) {
                  int n6 = n3;
                  int n7 = n3;
                  BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
                  BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderEntryBackgroundPass(n2, n6, n5, n7, b, colorInt, shaderRenderPass));
                  GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                  if (this.potionIcon.getValue() && potion.hasStatusIcon()) {
                     mc.getTextureManager().bindTexture(GuiInventory.inventoryBackground);
                     int statusIconIndex = potion.getStatusIconIndex();
                     mc.ingameGUI.drawTexturedModalRect(n2 + 6, n3 + 7, 0 + statusIconIndex % 8 * 18, 198 + statusIconIndex / 8 * 18, 18, 18);
                  }

                  String str = I18n.format(potion.getName(), new Object[0]);
                  if (potionEffect.getAmplifier() == 1) {
                     str = str + " " + I18n.format("enchantment.level.2", new Object[0]);
                  } else if (potionEffect.getAmplifier() == 2) {
                     str = str + " " + I18n.format("enchantment.level.3", new Object[0]);
                  } else if (potionEffect.getAmplifier() == 3) {
                     str = str + " " + I18n.format("enchantment.level.4", new Object[0]);
                  }

                  mc.fontRendererObj.drawStringWithShadow(str, (float)(n2 + 5 + n4), (float)(n3 + 6), 16777215);
                  String durationString = Potion.getDurationString(potionEffect);
                  mc.fontRendererObj.drawStringWithShadow(durationString, (float)(n2 + 5 + n4), (float)(n3 + 6 + 10), 8355711);
                  n3 += n;
               }
            }
         }
      }

      GL11.glPopMatrix();
      RenderUtil.resetGlColorState();
   }

   private void renderEntryBackgroundPass(int n, int n2, int n3, int n4, int n5, int n6, ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawRect(n, n2, n + n3, n4 + n5, this.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n6)));
   }

}
