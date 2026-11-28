package net.minecraft.client.renderer;

import net.minecraft.client.renderer.texture.Stitcher;

public class StitcherException extends RuntimeException {
   private final Stitcher.Holder holder;

   public StitcherException(Stitcher.Holder holder2, String str) {
      super(str);
      this.holder = holder2;
   }
}
