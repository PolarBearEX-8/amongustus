package net.augustus.cosmetics.render;

public class AnimatedCosmeticTexture {
   private long lastFrameTime;
   private int frameCount;
   private int[] frameDelays;
   private CosmeticBufferedTexture[] frames;
   private int currentFrameIndex;

   public AnimatedCosmeticTexture(CosmeticBufferedTexture[] cosmeticBufferedTextures, int[] ints) {
      this.frames = cosmeticBufferedTextures;
      this.frameDelays = ints;
      this.frameCount = cosmeticBufferedTextures.length;
   }

   public int getCurrentFrameDelay() {
      return this.frameDelays[this.currentFrameIndex];
   }

   public CosmeticBufferedTexture getCurrentFrame() {
      if (System.currentTimeMillis() - this.lastFrameTime >= (long)this.frameDelays[this.currentFrameIndex]) {
         this.lastFrameTime = System.currentTimeMillis();
         if (this.currentFrameIndex < this.frameCount - 1) {
            this.currentFrameIndex++;
         } else {
            this.currentFrameIndex = 0;
         }
      }

      return this.frames[this.currentFrameIndex];
   }

   public void deleteTextures() {

      for (CosmeticBufferedTexture cosmeticBufferedTexture : this.frames) {
         cosmeticBufferedTexture.releaseTexture();
      }
   }
}
