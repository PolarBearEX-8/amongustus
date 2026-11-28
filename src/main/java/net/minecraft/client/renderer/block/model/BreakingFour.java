package net.minecraft.client.renderer.block.model;

import net.minecraft.util.EnumFacing;
import java.util.Arrays;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class BreakingFour extends BakedQuad {
   private final TextureAtlasSprite texture;

   private void remapVert(int n) {
      int n3 = (this.vertexData.length / 4) * n;
      float f = Float.intBitsToFloat(this.vertexData[n3]);
      float f2 = Float.intBitsToFloat(this.vertexData[n3 + 1]);
      float f3 = Float.intBitsToFloat(this.vertexData[n3 + 2]);
      float f4 = 0.0F;
      float f5 = 0.0F;
      switch (this.face) {
         case DOWN:
            f4 = f * 16.0F;
            f5 = (1.0F - f3) * 16.0F;
            break;
         case UP:
            f4 = f * 16.0F;
            f5 = f3 * 16.0F;
            break;
         case NORTH:
            f4 = (1.0F - f) * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
            break;
         case SOUTH:
            f4 = f * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
            break;
         case WEST:
            f4 = f3 * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
            break;
         case EAST:
            f4 = (1.0F - f3) * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
      }

      this.vertexData[n3 + 4] = Float.floatToRawIntBits(this.texture.getInterpolatedU((double)f4));
      this.vertexData[n3 + 4 + 1] = Float.floatToRawIntBits(this.texture.getInterpolatedV((double)f5));
   }

   public BreakingFour(BakedQuad bakedQuad, TextureAtlasSprite textureAtlasSprite) {
      super(Arrays.copyOf(bakedQuad.getVertexData(), bakedQuad.getVertexData().length), bakedQuad.tintIndex, FaceBakery.getFacingFromVertexData(bakedQuad.getVertexData()));
      this.texture = textureAtlasSprite;
      this.remapQuad();
      this.fixVertexData();
   }

   private void remapQuad() {
      for (int i = 0; i < 4; i++) {
         this.remapVert(i);
      }
   }

}
