package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class ListedRenderChunk extends RenderChunk {
   private final int baseDisplayList = GLAllocation.generateDisplayLists(EnumWorldBlockLayer.values().length);

   @Override
   public void deleteGlResources() {
      super.deleteGlResources();
      GLAllocation.deleteDisplayLists(this.baseDisplayList, EnumWorldBlockLayer.values().length);
   }

   public ListedRenderChunk(World world2, RenderGlobal renderGlobal2, BlockPos pos, int n) {
      super(world2, renderGlobal2, pos, n);
   }

   public int getDisplayList(EnumWorldBlockLayer enumWorldBlockLayer, CompiledChunk compiledChunk2) {
      return !compiledChunk2.isLayerEmpty(enumWorldBlockLayer) ? this.baseDisplayList + enumWorldBlockLayer.ordinal() : -1;
   }
}
