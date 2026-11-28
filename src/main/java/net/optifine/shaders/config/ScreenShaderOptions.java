package net.optifine.shaders.config;


public class ScreenShaderOptions {
   private String name;
   private ShaderOption[] shaderOptions;
   private int columns;

   public ScreenShaderOptions(String str, ShaderOption[] shaderOptions2, int n) {
      this.name = str;
      this.shaderOptions = shaderOptions2;
      this.columns = n;
   }

   public int getColumns() {
      return this.columns;
   }

   public String getName() {
      return this.name;
   }

   public ShaderOption[] getShaderOptions() {
      return this.shaderOptions;
   }
}
