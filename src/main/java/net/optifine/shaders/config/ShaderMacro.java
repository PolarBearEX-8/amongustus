package net.optifine.shaders.config;

public class ShaderMacro {
   private String name;
   private String value;

   public String getValue() {
      return this.value;
   }

   @Override
   public String toString() {
      return this.getSourceLine();
   }

   public String getName() {
      return this.name;
   }

   public String getSourceLine() {
      return "#define " + this.name + " " + this.value;
   }

   public ShaderMacro(String str, String str2) {
      this.name = str;
      this.value = str2;
   }
}
