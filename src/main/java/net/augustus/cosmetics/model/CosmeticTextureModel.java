package net.augustus.cosmetics.model;

import java.awt.image.BufferedImage;

public class CosmeticTextureModel extends AbstractCosmeticPart {
   private BufferedImage maskImage;
   private int color;
   private int index;
   private int boundSubModelIndex;
   private String maskUrl;
   private String url = "";
   private float opacity;

   public boolean isBoundToSubModel() {
      return this.boundSubModelIndex != -1;
   }

   public int getIndex() {
      return this.index;
   }

   public void setColor(int n) {
      this.color = n;
   }

   public void setIndex(int n) {
      this.index = n;
   }

   public String getMaskUrl() {
      return this.maskUrl;
   }

   public int getColor() {
      return this.color;
   }

   public void setMaskUrl(String str) {
      this.maskUrl = str;
   }

   public int getBoundSubModelIndex() {
      return this.boundSubModelIndex;
   }

   public BufferedImage getMaskImage() {
      return this.maskImage;
   }

   public float getOpacity() {
      return this.opacity;
   }

   public void setBoundSubModelIndex(int n) {
      this.boundSubModelIndex = n;
   }

   public void setOpacity(float f) {
      this.opacity = f;
   }

   public String getUrl() {
      return this.url;
   }

   public void setMaskImage(BufferedImage image) {
      this.maskImage = image;
   }

   public CosmeticTextureModel(String str, int n) {
      super(CosmeticBodyPart.FREE);
      this.maskUrl = "";
      this.color = -1;
      this.opacity = 1.0F;
      this.boundSubModelIndex = -1;
      this.url = str;
      this.index = n;
   }

   public void setUrl(String str) {
      this.url = str;
   }
}
