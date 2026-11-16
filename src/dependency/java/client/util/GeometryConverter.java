package client.util;

import net.minecraft.util.ResourceLocation;

public class GeometryConverter {
   public String labelText = null;
   public String labelText2 = null;
   public ResourceLocation resourceLocation = null;
   public FovEngine[] fovEngineArray = null;
   public float factor = 0.0F;

   public String getLabelText() {
      return this.labelText;
   }

   public float getFactor() {
      return this.factor;
   }

   public GeometryConverter(String text, String text2, ResourceLocation location, FovEngine[] fovEngineArray2, float factor2) {
      this.labelText = text;
      this.labelText2 = text2;
      this.resourceLocation = location;
      this.fovEngineArray = fovEngineArray2;
      this.factor = factor2;
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public FovEngine[] getFovEngineArray() {
      return this.fovEngineArray;
   }
}
