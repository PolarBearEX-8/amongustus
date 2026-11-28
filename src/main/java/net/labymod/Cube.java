package net.labymod;

public class Cube {
   private Double inflate;
   private Boolean mirror;
   private double[] origin = new double[]{0.0, 0.0, 0.0};
   private double[] pivot = new double[]{0.0, 0.0, 0.0};
   private double[] rotation = new double[]{0.0, 0.0, 0.0};
   private double[] size = new double[]{1.0, 1.0, 1.0};
   private UvUnion uv;

   public double[] getPivot() {
      return this.pivot;
   }

   public void setRotation(double[] doubles) {
      this.rotation = doubles;
   }

   public UvUnion getUv() {
      return this.uv;
   }

   public void setPivot(double[] doubles) {
      this.pivot = doubles;
   }

   public double[] getOrigin() {
      return this.origin;
   }

   public void setUv(UvUnion uvUnion) {
      this.uv = uvUnion;
   }

   public Boolean getMirror() {
      return this.mirror;
   }

   public void setOrigin(double[] doubles) {
      this.origin = doubles;
   }

   public double[] getSize() {
      return this.size;
   }

   public double[] getRotation() {
      return this.rotation;
   }

   public void setSize(double[] doubles) {
      this.size = doubles;
   }

   public void setMirror(Boolean bool) {
      this.mirror = bool;
   }

   public Double getInflate() {
      return this.inflate;
   }

   public void setInflate(Double d) {
      this.inflate = d;
   }
}
