package net.minecraft.client.resources.data;

public class AnimationFrame {
   private final int frameIndex;
   private final int frameTime;

   public int getFrameTime() {
      return this.frameTime;
   }

   public AnimationFrame(int n) {
      this(n, -1);
   }

   public AnimationFrame(int n, int n2) {
      this.frameIndex = n;
      this.frameTime = n2;
   }

   public int getFrameIndex() {
      return this.frameIndex;
   }

   public boolean hasNoTime() {
      return this.frameTime == -1;
   }
}
