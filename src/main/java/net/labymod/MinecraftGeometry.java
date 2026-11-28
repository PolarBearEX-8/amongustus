package net.labymod;

import com.google.gson.annotations.SerializedName;

public class MinecraftGeometry {
   private Bone[] bones;
   private String cape;
   @SerializedName("description")
   private ModelProperties modelProperties;

   public void setBones(Bone[] bones2) {
      this.bones = bones2;
   }

   public ModelProperties getProperties() {
      return this.modelProperties;
   }

   public void setProperties(ModelProperties modelProperties2) {
      this.modelProperties = modelProperties2;
   }

   public void setCape(String str) {
      this.cape = str;
   }

   public Bone[] getBones() {
      return this.bones;
   }

   public String getCape() {
      return this.cape;
   }
}
