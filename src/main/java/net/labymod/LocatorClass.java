package net.labymod;

import com.google.gson.annotations.SerializedName;

public class LocatorClass {
   @SerializedName("ignore_inherited_scale")
   private Boolean ignoreInheritedScale;
   private double[] offset;
   private double[] rotation;

   public void setRotation(double[] doubles) {
      this.rotation = doubles;
   }

   public void setIgnoreInheritedScale(Boolean bool) {
      this.ignoreInheritedScale = bool;
   }

   public double[] getOffset() {
      return this.offset;
   }

   public void setOffset(double[] doubles) {
      this.offset = doubles;
   }

   public Boolean getIgnoreInheritedScale() {
      return this.ignoreInheritedScale;
   }

   public double[] getRotation() {
      return this.rotation;
   }
}
