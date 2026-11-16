package net.minecraft.client.renderer;

import client.util.AimCoordinator;
import client.util.DampenerAccumulator;

public class GlStencilState {
   public AimCoordinator aimCoordinator = new AimCoordinator(null);
   public int count2 = -1;
   public int count = 7680;
   public int count3 = 7680;
   public int count4 = 7680;

   public GlStencilState(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private GlStencilState() {
   }
}
