package net.labymod;

import com.google.gson.annotations.SerializedName;

public class TextureMesh {
   @SerializedName("local_pivot")
   private double[] localPivot;
   private double[] position;
   private double[] rotation;
   private double[] scale;
   private String texture;

   public double[] getPosition() {
      return this.position;
   }

   public void setScale(double[] doubles) {
      this.scale = doubles;
   }

   public void setPosition(double[] doubles) {
      this.position = doubles;
   }

   public double[] getRotation() {
      return this.rotation;
   }

   public String getTexture() {
      return this.texture;
   }

   public void setLocalPivot(double[] doubles) {
      this.localPivot = doubles;
   }

   public double[] getScale() {
      return this.scale;
   }

   public void setTexture(String str) {
      this.texture = str;
   }

   public double[] getLocalPivot() {
      return this.localPivot;
   }

   public void setRotation(double[] doubles) {
      this.rotation = doubles;
   }
}
