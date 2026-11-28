package net.optifine.shaders.gui;

import net.minecraft.client.gui.GuiButton;
import net.optifine.shaders.config.ShaderOption;

public class GuiButtonShaderOption extends GuiButton {
   private ShaderOption shaderOption = null;

   public ShaderOption getShaderOption() {
      return this.shaderOption;
   }

   public void valueChanged() {
   }

   public GuiButtonShaderOption(int n, int n2, int n3, int n4, int n5, ShaderOption shaderOption2, String str) {
      super(n, n2, n3, n4, n5, str);
      this.shaderOption = shaderOption2;
   }

   public boolean isSwitchable() {
      return true;
   }
}
