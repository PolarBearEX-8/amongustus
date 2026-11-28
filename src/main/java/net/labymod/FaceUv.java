package net.labymod;

import com.google.gson.annotations.SerializedName;

public class FaceUv {
   @SerializedName("material_instance")
   private String materialInstance;
   private double[] uv;
   @SerializedName("uv_size")
   private double[] uvSize;

   public double[] getUvSize() {
      return this.uvSize;
   }

   public String getMaterialInstance() {
      return this.materialInstance;
   }

   public void setUvSize(double[] doubles) {
      this.uvSize = doubles;
   }

   public void setUv(double[] doubles) {
      this.uv = doubles;
   }

   public void setMaterialInstance(String str) {
      this.materialInstance = str;
   }

   public double[] getUv() {
      return this.uv;
   }
}
