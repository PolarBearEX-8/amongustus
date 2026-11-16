package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;

public class GlColorMask {
   public boolean enabled = true;
   public boolean enabled3 = true;
   public boolean enabled2 = true;
   public boolean enabled4 = true;

   private GlColorMask() {
   }

   public GlColorMask(DampenerAccumulator dampenerAccumulator) {
      this();
   }
}
