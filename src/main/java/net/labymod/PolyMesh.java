package net.labymod;

import com.google.gson.annotations.SerializedName;

public class PolyMesh {
   @SerializedName("normalized_uvs")
   private Boolean normalizedUvs;
   private double[] normals;
   private PolysUnion polys;
   private double[] positions;
   private double[] uvs;

   public double[] getNormals() {
      return this.normals;
   }

   public Boolean getNormalizedUvs() {
      return this.normalizedUvs;
   }

   public PolysUnion getPolys() {
      return this.polys;
   }

   public void setUvs(double[] doubles) {
      this.uvs = doubles;
   }

   public void setNormals(double[] doubles) {
      this.normals = doubles;
   }

   public double[] getPositions() {
      return this.positions;
   }

   public void setPositions(double[] doubles) {
      this.positions = doubles;
   }

   public void setNormalizedUvs(Boolean bool) {
      this.normalizedUvs = bool;
   }

   public void setPolys(PolysUnion polysUnion) {
      this.polys = polysUnion;
   }

   public double[] getUvs() {
      return this.uvs;
   }
}
