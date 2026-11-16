package client.event;

import client.setting.TertiaryOptionType;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventMoveEntity extends CancellableEvent {
   public static final int count = 20;
   public double coordOffset1;
   public TertiaryOptionType tertiaryOptionType;
   public double coordOffset2;
   public double coordOffset;

   public void applyMoveEntityListener(MoveEntityListener moveEntityListener) {
      if (this.tertiaryOptionType == TertiaryOptionType.PRE) {
         moveEntityListener.onPostMoveEntity(this);
      } else {
         moveEntityListener.onMoveEntity(this);
      }
   }

   public void setCoordOffset1(double coordOffset3) {
      this.coordOffset1 = coordOffset3;
   }

   public void setCoordOffset2(double coordOffset3) {
      this.coordOffset2 = coordOffset3;
   }

   public double getCoordOffset1() {
      return this.coordOffset1;
   }

   public void setCoordOffset(double coordOffset3) {
      this.coordOffset = coordOffset3;
   }

   public void call(Object entry) {
      MoveEntityListener moveEntityListener = (MoveEntityListener)entry;
      this.applyMoveEntityListener(moveEntityListener);
   }

   public double getCoordOffset2() {
      return this.coordOffset2;
   }

   public static void onMoveEntity(EventMoveEntity event, double posX, double posY, double posZ, TertiaryOptionType tertiaryOptionType2) {
      event.setCancelled(posX, posY, posZ, tertiaryOptionType2);
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   private void setCancelled(double posX, double posY, double posZ, TertiaryOptionType tertiaryOptionType2) {
      this.coordOffset2 = posX;
      this.coordOffset1 = posY;
      this.coordOffset = posZ;
      this.tertiaryOptionType = tertiaryOptionType2;
      this.setCancelled(false);
   }
}
