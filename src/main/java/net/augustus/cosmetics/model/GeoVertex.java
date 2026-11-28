package net.augustus.cosmetics.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class GeoVertex {
   public final GeoVector3f transformedPosition = new GeoVector3f(0.0F, 0.0F, 0.0F);
   public float textureU;
   public float textureV;
   public final GeoVector3f position;

   public GeoVertex setTextureUV(double[] textureUV) {
      Validate.validIndex(ArrayUtils.toObject(textureUV), 1);
      return new GeoVertex(this.position, (float)textureUV[0], (float)textureUV[1]);
   }

   public GeoVertex(GeoVector3f geoVector3f, float f, float f2) {
      this.position = geoVector3f;
      this.textureU = f;
      this.textureV = f2;
   }

   public GeoVertex setTextureUV(float f, float f2) {
      return new GeoVertex(this.position, f, f2);
   }

   public GeoVertex(double d, double d2, double d3) {
      this.position = new GeoVector3f((float)d, (float)d2, (float)d3);
   }

   public GeoVertex(float f, float f2, float f3) {
      this.position = new GeoVector3f(f, f2, f3);
   }
}
