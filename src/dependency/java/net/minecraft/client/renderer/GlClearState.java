package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;

public class GlClearState {
   public double value = 1.0;
   public GlColor4f glColor4f = new GlColor4f(0.0F, 0.0F, 0.0F, 0.0F);
   public int count = 0;

   public GlClearState(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private GlClearState() {
   }
}
