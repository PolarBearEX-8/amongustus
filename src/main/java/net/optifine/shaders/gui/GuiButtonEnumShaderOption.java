package net.optifine.shaders.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.EnumShaderOption;

public class GuiButtonEnumShaderOption extends GuiButton {
   private EnumShaderOption enumShaderOption = null;

   private static String getButtonText(EnumShaderOption enumShaderOption2) {
      String str = I18n.format(enumShaderOption2.getResourceKey(), new Object[0]) + ": ";
      switch (enumShaderOption2) {
         case ANTIALIASING:
            return str + GuiShaders.toStringAa(Shaders.configAntialiasingLevel);
         case NORMAL_MAP:
            return str + GuiShaders.toStringOnOff(Shaders.configNormalMap);
         case SPECULAR_MAP:
            return str + GuiShaders.toStringOnOff(Shaders.configSpecularMap);
         case RENDER_RES_MUL:
            return str + GuiShaders.toStringQuality(Shaders.configRenderResMul);
         case SHADOW_RES_MUL:
            return str + GuiShaders.toStringQuality(Shaders.configShadowResMul);
         case HAND_DEPTH_MUL:
            return str + GuiShaders.toStringHandDepth(Shaders.configHandDepthMul);
         case CLOUD_SHADOW:
            return str + GuiShaders.toStringOnOff(Shaders.configCloudShadow);
         case OLD_HAND_LIGHT:
            return str + Shaders.configOldHandLight.getUserValue();
         case OLD_LIGHTING:
            return str + Shaders.configOldLighting.getUserValue();
         case SHADOW_CLIP_FRUSTRUM:
            return str + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum);
         case TWEAK_BLOCK_DAMAGE:
            return str + GuiShaders.toStringOnOff(Shaders.configTweakBlockDamage);
         default:
            return str + Shaders.getEnumShaderOption(enumShaderOption2);
      }
   }

   public void updateButtonText() {
      this.displayString = getButtonText(this.enumShaderOption);
   }

   public GuiButtonEnumShaderOption(EnumShaderOption enumShaderOption2, int n, int n2, int n3, int n4) {
      super(enumShaderOption2.ordinal(), n, n2, n3, n4, getButtonText(enumShaderOption2));
      this.enumShaderOption = enumShaderOption2;
   }

   public EnumShaderOption getEnumShaderOption() {
      return this.enumShaderOption;
   }
}
