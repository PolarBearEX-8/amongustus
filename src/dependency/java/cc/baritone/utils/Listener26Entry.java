package cc.baritone.utils;

import client.util.MovingObjectType;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.MovingObjectPosition;

public class Listener26Entry {
   public final StaticListener staticListener;
   public int count;

   public void setEnabled(boolean enabled) {
      if (this.count > 0) {
         this.count--;
      } else {
         MovingObjectPosition movingObjectPosition = this.staticListener.getMovingObjectPosition();
         if (enabled && movingObjectPosition != null && movingObjectPosition.getBlockPos() != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK) {
            this.count = (Integer)BaritoneManager.getSettings().rightClickSpeed.dataRef;
            if (this.staticListener
               .getDefaultListener()
               .matchesEntityPlayerSP(
                  this.staticListener.getEntityPlayerSP(),
                  (WorldClient)this.staticListener.getWorld(),
                  this.staticListener.getEntityPlayerSP().getHeldItem(),
                  movingObjectPosition.getBlockPos(),
                  movingObjectPosition.sideHit,
                  movingObjectPosition.hitVec
               )) {
               this.staticListener.getEntityPlayerSP().respawnPlayer();
            } else if (this.staticListener.getEntityPlayerSP().getItemStack().getItem() == null
               || !this.staticListener
                  .getDefaultListener()
                  .matchesEntityPlayer(
                     this.staticListener.getEntityPlayerSP(), this.staticListener.getWorld(), this.staticListener.getEntityPlayerSP().getHeldItem()
                  )) {
               ;
            }
         }
      }
   }

   public Listener26Entry(StaticListener staticListener2) {
      this.staticListener = staticListener2;
   }
}
