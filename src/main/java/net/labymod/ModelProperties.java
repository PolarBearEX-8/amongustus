package net.labymod;

import com.google.gson.annotations.SerializedName;

public class ModelProperties {
   private Boolean animationArmsDown;
   private Boolean animationArmsOutFront;
   private Boolean animationDontShowArmor;
   private Boolean animationInvertedCrouch;
   private Boolean animationNoHeadBob;
   private Boolean animationSingleArmAnimation;
   private Boolean animationSingleLegAnimation;
   private Boolean animationStationaryLegs;
   private Boolean animationStatueOfLibertyArms;
   private Boolean animationUpsideDown;
   private String identifier;
   @SerializedName("preserve_model_pose")
   private Boolean preserveModelPose;
   @SerializedName("texture_height")
   private Double textureHeight;
   @SerializedName("texture_width")
   private Double textureWidth;
   @SerializedName("visible_bounds_height")
   private Double visibleBoundsHeight;
   @SerializedName("visible_bounds_offset")
   private double[] visibleBoundsOffset;
   @SerializedName("visible_bounds_width")
   private Double visibleBoundsWidth;

   public void setVisibleBoundsWidth(Double d) {
      this.visibleBoundsWidth = d;
   }

   public void setAnimationNoHeadBob(Boolean bool) {
      this.animationNoHeadBob = bool;
   }

   public void setVisibleBoundsOffset(double[] doubles) {
      this.visibleBoundsOffset = doubles;
   }

   public void setTextureWidth(Double d) {
      this.textureWidth = d;
   }

   public Boolean getAnimationNoHeadBob() {
      return this.animationNoHeadBob;
   }

   public void setAnimationDontShowArmor(Boolean bool) {
      this.animationDontShowArmor = bool;
   }

   public void setTextureHeight(Double d) {
      this.textureHeight = d;
   }

   public Boolean getAnimationUpsideDown() {
      return this.animationUpsideDown;
   }

   public void setAnimationSingleArmAnimation(Boolean bool) {
      this.animationSingleArmAnimation = bool;
   }

   public Boolean getAnimationSingleLegAnimation() {
      return this.animationSingleLegAnimation;
   }

   public Double getTextureWidth() {
      return this.textureWidth;
   }

   public Boolean getAnimationStatueOfLibertyArms() {
      return this.animationStatueOfLibertyArms;
   }

   public Boolean getAnimationInvertedCrouch() {
      return this.animationInvertedCrouch;
   }

   public Double getVisibleBoundsWidth() {
      return this.visibleBoundsWidth;
   }

   public Double getTextureHeight() {
      return this.textureHeight;
   }

   public void setAnimationStatueOfLibertyArms(Boolean bool) {
      this.animationStatueOfLibertyArms = bool;
   }

   public double[] getVisibleBoundsOffset() {
      return this.visibleBoundsOffset;
   }

   public String getIdentifier() {
      return this.identifier;
   }

   public void setAnimationArmsOutFront(Boolean bool) {
      this.animationArmsOutFront = bool;
   }

   public void setAnimationArmsDown(Boolean bool) {
      this.animationArmsDown = bool;
   }

   public Boolean getAnimationDontShowArmor() {
      return this.animationDontShowArmor;
   }

   public Boolean getAnimationArmsOutFront() {
      return this.animationArmsOutFront;
   }

   public void setAnimationUpsideDown(Boolean bool) {
      this.animationUpsideDown = bool;
   }

   public Boolean getAnimationArmsDown() {
      return this.animationArmsDown;
   }

   public Boolean getAnimationSingleArmAnimation() {
      return this.animationSingleArmAnimation;
   }

   public void setAnimationInvertedCrouch(Boolean bool) {
      this.animationInvertedCrouch = bool;
   }

   public Boolean getAnimationStationaryLegs() {
      return this.animationStationaryLegs;
   }

   public void setIdentifier(String str) {
      this.identifier = str;
   }

   public Boolean getPreserveModelPose() {
      return this.preserveModelPose;
   }

   public void setPreserveModelPose(Boolean bool) {
      this.preserveModelPose = bool;
   }

   public void setVisibleBoundsHeight(Double d) {
      this.visibleBoundsHeight = d;
   }

   public Double getVisibleBoundsHeight() {
      return this.visibleBoundsHeight;
   }

   public void setAnimationStationaryLegs(Boolean bool) {
      this.animationStationaryLegs = bool;
   }

   public void setAnimationSingleLegAnimation(Boolean bool) {
      this.animationSingleLegAnimation = bool;
   }
}
