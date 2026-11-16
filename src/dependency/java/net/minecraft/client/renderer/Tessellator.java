package net.minecraft.client.renderer;

import client.render.WorldRenderer;
import client.util.BitSetHolder;
import client.util.DampenerService;

public class Tessellator {
   public WorldRenderer worldRenderer;
   public DampenerService dampenerService = new DampenerService();
   public static final Tessellator tessellator = new Tessellator(2097152);

   public WorldRenderer getWorldRenderer() {
      return this.worldRenderer;
   }

   public static Tessellator getTessellator() {
      return tessellator;
   }

   public void updateState() {
      if (this.worldRenderer.bitSet != null) {
         BitSetHolder.or(this.worldRenderer.bitSet);
      }

      this.worldRenderer.renderElement6();
      this.dampenerService.applyWorldRenderer(this.worldRenderer);
   }

   public Tessellator(int count) {
      this.worldRenderer = new WorldRenderer(count);
   }
}
