package net.augustus.render;

import net.minecraft.util.ResourceLocation;

public class EmojiSprite {
   public int halfSize;
   public int offsetY;
   public int offsetX;
   public String textureName;
   private ResourceLocation resourceLocation;

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   @Override
   public String toString() {
      return this.offsetX + "," + this.offsetY + "," + this.halfSize;
   }

   public EmojiSprite(String str, int n, int n2, int n3) {
      this.textureName = str;
      this.offsetX = n;
      this.offsetY = n2;
      this.halfSize = n3;
      this.resourceLocation = new ResourceLocation("augustus", "emojifymod/" + this.textureName);
   }

   public EmojiSprite(String str, int n, int n2) {
      this.textureName = str;
      this.offsetX = 0;
      this.offsetY = n;
      this.halfSize = n2;
      this.resourceLocation = new ResourceLocation("augustus", "emojifymod/" + this.textureName);
   }
}
