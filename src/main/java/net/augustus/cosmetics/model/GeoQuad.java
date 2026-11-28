package net.augustus.cosmetics.model;

public class GeoQuad {
   public GeoDirection direction;
   public GeoVertex[] vertices;
   public final IntVector3 normal;
   public final GeoVector3f transformedNormal = new GeoVector3f(0.0F, 0.0F, 0.0F);

   public GeoQuad(GeoVertex[] geoVertexs, double[] doubles, double[] doubles2, float f, float f2, Boolean bool, GeoDirection geoDirection) {
       this(geoVertexs, (float)doubles[0], (float)doubles[1], (float)doubles2[0], (float)doubles2[1], f, f2, bool, geoDirection);
   }

   public GeoQuad(GeoVertex[] geoVertexs, float f, float f2, float f3, float f4, float f5, float f6, Boolean bool, GeoDirection geoDirection) {
      this.direction = geoDirection;
      this.vertices = geoVertexs;
      float f7 = f + f3;
      float f8 = f2 + f4;
      f /= f5;
      f7 /= f5;
      f2 /= f6;
      f8 /= f6;
      if (bool != null && bool) {
         this.vertices[0] = geoVertexs[0].setTextureUV(f, f2);
         this.vertices[1] = geoVertexs[1].setTextureUV(f7, f2);
         this.vertices[2] = geoVertexs[2].setTextureUV(f7, f8);
         this.vertices[3] = geoVertexs[3].setTextureUV(f, f8);
      } else {
         this.vertices[0] = geoVertexs[0].setTextureUV(f7, f2);
         this.vertices[1] = geoVertexs[1].setTextureUV(f, f2);
         this.vertices[2] = geoVertexs[2].setTextureUV(f, f8);
         this.vertices[3] = geoVertexs[3].setTextureUV(f7, f8);
      }

      this.normal = geoDirection.getDirectionVec();
   }
}
