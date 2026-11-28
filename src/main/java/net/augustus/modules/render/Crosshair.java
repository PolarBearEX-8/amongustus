package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.ui.GuiIngameHook;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;

public class Crosshair extends Module implements RenderListener {
   public final BooleanValue defaultCrossHair;
   public final BooleanValue thirdPerson;
   public final DoubleValue scale;
   public final ColorSetting color2;
   public final DoubleValue length;
   public final DoubleValue width;
   public final DoubleValue margin;
   public final BooleanValue dot;
   public final BooleanValue tStyle;
   public final BooleanValue shadow;

   public Crosshair() {
       super("Crosshair", "Fadenkreuz", new Color(157, 230, 106), Categorys.RENDER);
      this.defaultCrossHair = new BooleanValue(this, "DefaultCrossHair", false);
      this.thirdPerson = new BooleanValue(this, "ThirdPerson", false);
      DoubleValue scaleSetting = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 2);
      BooleanValue defaultCrossHair2 = this.defaultCrossHair;
      this.scale = scaleSetting.visibleWhen(() -> defaultCrossHair2.getValue());
      this.color2 = (ColorSetting)new ColorSetting(this, "Color", new Color(94, 0, 255, 255)).withVisibility(() -> this.isColorVisible());
      DoubleValue lengthSetting = new DoubleValue(this, "Length", 4.0, 0.0, 40.0, 0);
      SettingVisibilityCondition condition3 = () -> {
         return !this.defaultCrossHair.getValue();
      };
      this.length = lengthSetting.visibleWhen(condition3);
      this.width = new DoubleValue(this, "Width", 1.0, 1.0, 20.0, 0).visibleWhen(() -> this.isWidthVisible());
      this.margin = new DoubleValue(this, "Margin", 1.0, 0.0, 40.0, 0).visibleWhen(() -> this.isMarginVisible());
      this.dot = (BooleanValue)new BooleanValue(this, "Dot", true).withVisibility(() -> this.isDotVisible());
      BooleanValue booleanValue3 = new BooleanValue(this, "T-Style", false);
      SettingVisibilityCondition condition7 = () -> {
         return !this.defaultCrossHair.getValue();
      };
      this.tStyle = (BooleanValue)booleanValue3.withVisibility(condition7);
      this.shadow = (BooleanValue)new BooleanValue(this, "Shadow", true).withVisibility(() -> this.isShadowVisible());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32, 1000000);
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (!ClientManagers.moduleManager.crosshair.defaultCrossHair.getValue()) {
         if (mc.ingameGUI.showCrosshair() && (mc.gameSettings.thirdPersonView < 1 || ClientManagers.moduleManager.crosshair.isToggled() && ClientManagers.moduleManager.crosshair.thirdPerson.getValue())) {
            BlurShadowManager.enableOneShotPass(false, ClientManagers.moduleManager.crosshair.shadow.getValue() && ClientManagers.moduleManager.shadow.isToggled());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderCrosshairPass(sr, shaderRenderPass));
            RenderUtil.resetGlColorState();
         }
      }
   }

   private static void renderCrosshairPass(ScaledResolution sr, ShaderRenderPass shaderRenderPass) {
      GuiIngameHook.drawCustomCrosshair(sr);
   }

   private boolean isShadowVisible() {
      return !this.defaultCrossHair.getValue();
   }

   private boolean isDotVisible() {
      return !this.defaultCrossHair.getValue();
   }

   private boolean isMarginVisible() {
      return !this.defaultCrossHair.getValue();
   }

   private boolean isWidthVisible() {
      return !this.defaultCrossHair.getValue();
   }

   private boolean isColorVisible() {
      return !this.defaultCrossHair.getValue();
   }

}
