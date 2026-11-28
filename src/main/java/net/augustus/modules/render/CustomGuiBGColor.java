package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventRenderGuiBackground;
import net.augustus.settings.options.CustomGuiBgScreenOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.listeners.RenderGuiBackgroundListener;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class CustomGuiBGColor extends Module implements RenderGuiBackgroundListener {

   public CustomGuiBGColor() {
       super("CustomGuiBGColor", "EinstellbareFenserHintergrundfarbe", new Color(235, 190, 127), Categorys.RENDER);
      new MultiOptionSetting(this, "Guis", CustomGuiBgScreenOption.values());

      for (CustomGuiBgScreenOption customGuiBgScreenOption : CustomGuiBgScreenOption.values()) {
         SettingGroup settingGroup = new SettingGroup(this, customGuiBgScreenOption.getName(), true);
         String str = customGuiBgScreenOption.getName() + "Color";
         Color color2 = new Color(0, 0, 0, 50);
         CustomGuiBgScreenOption.setBackgroundColorSetting(customGuiBgScreenOption, (ColorSetting)new ColorSetting(this, str, color2, settingGroup).withDisplayName("Color"));
         CustomGuiBgScreenOption.setBlurSetting(customGuiBgScreenOption, (BooleanValue)new BooleanValue(this, customGuiBgScreenOption.getName() + "Blur", false, settingGroup).withDisplayName("Blur"));
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
      this.toggleEventSubscription(54);
   }

   @Override
   public void onRenderGuiBackground(EventRenderGuiBackground event) {
      GuiScreen guiScreen = event.getGuiScreen();

      for (CustomGuiBgScreenOption customGuiBgScreenOption : CustomGuiBgScreenOption.values()) {
         if (customGuiBgScreenOption.isSelected() && (Boolean)CustomGuiBgScreenOption.getScreenMatcher(customGuiBgScreenOption).apply(guiScreen)) {
            boolean isGuiLayerEnabled = BlurShadowManager.isGuiLayerEnabled();
            BlurShadowManager.disableGuiLayer();
            int alphaFunc = GlStateManager.getAlphaFunc();
            float alphaRef = GlStateManager.getAlphaRef();
            GlStateManager.alphaFunc(alphaFunc, 0.0F);
            BlurShadowManager.enableOneShotPass(CustomGuiBgScreenOption.getBlurSetting(customGuiBgScreenOption).getValue(), false);
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderGuiBackgroundPass(guiScreen, customGuiBgScreenOption, shaderRenderPass));
            GlStateManager.alphaFunc(alphaFunc, alphaRef);
            if (isGuiLayerEnabled) {
               BlurShadowManager.enableGuiLayer();
            }

            event.cancel();
            return;
         }
      }
   }

   private static void renderGuiBackgroundPass(GuiScreen screen, CustomGuiBgScreenOption customGuiBgScreenOption, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.BLUR) {
         RenderUtil.drawRect(0, 0, screen.width, screen.height, -1);
      } else {
         RenderUtil.drawRect(0, 0, screen.width, screen.height, CustomGuiBgScreenOption.getBackgroundColorSetting(customGuiBgScreenOption).getColorInt((byte)0));
      }
   }

}
