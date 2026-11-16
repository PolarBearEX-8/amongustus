package client.render;

import client.setting.BlockSolidType;

public class WorldRendererArrayEntry {
   public final WorldRenderer[] worldRendererArray = new WorldRenderer[BlockSolidType.values().length];

   public WorldRenderer ordinal(BlockSolidType blockSolidType) {
      return this.worldRendererArray[blockSolidType.ordinal()];
   }

   public WorldRenderer resolveWorldRenderer(int count) {
      return this.worldRendererArray[count];
   }

   public WorldRendererArrayEntry() {
      this.worldRendererArray[BlockSolidType.SOLID.ordinal()] = new WorldRenderer(2097152);
      this.worldRendererArray[BlockSolidType.CUTOUT.ordinal()] = new WorldRenderer(131072);
      this.worldRendererArray[BlockSolidType.CUTOUT_MIPPED.ordinal()] = new WorldRenderer(131072);
      this.worldRendererArray[BlockSolidType.TRANSLUCENT.ordinal()] = new WorldRenderer(262144);
   }
}
