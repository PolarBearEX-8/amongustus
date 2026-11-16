package net.minecraft.util;

import client.event.EventMove;

public class MovementInput {
   public float moveStrafe;
   public float moveForward;
   public float factor;
   public float factor2;
   public boolean state;
   public boolean state2;

   public EventMove onMove(EventMove event) {
      return null;
   }

   public void applyFloat(float factor3, float factor4) {
   }
}
