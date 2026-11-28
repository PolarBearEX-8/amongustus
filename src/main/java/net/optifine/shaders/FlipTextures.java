package net.optifine.shaders;

import java.nio.IntBuffer;
import java.util.Arrays;

public class FlipTextures {
   private IntBuffer textures;
   private int indexFlipOffset;
   private boolean[] flips;
   private boolean[] changed;

   public boolean isChanged(int n) {
      return this.changed[n];
   }

   public void reset() {
      Arrays.fill(this.flips, false);
      Arrays.fill(this.changed, false);
   }

   public FlipTextures(IntBuffer intBuffer, int n) {
      this.textures = intBuffer;
      this.indexFlipOffset = n;
      this.flips = new boolean[intBuffer.capacity()];
      this.changed = new boolean[intBuffer.capacity()];
   }

   public int getB(int n) {
      return this.getIndex(n, !this.flips[n]);
   }

   public void flip(int n) {
      this.flips[n] = !this.flips[n];
      this.changed[n] = true;
   }

   public int getA(int n) {
      return this.getIndex(n, this.flips[n]);
   }

   private int getIndex(int n, boolean flag) {
      int n2 = flag ? this.indexFlipOffset : 0;
      return this.textures.get(n2 + n);
   }
}
