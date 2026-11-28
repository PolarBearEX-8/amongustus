package net.augustus.events;

import net.augustus.events.listeners.MoveEntityListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventMoveEntity extends CancellableEvent<MoveEntityListener> {
   public MoveEntityPhase phase;
   public static final int ID = 20;
   private double z;
   private double y;
   private double x;

   public static void reset(EventMoveEntity event, double d, double d2, double d3, MoveEntityPhase moveEntityPhase) {
      event.reset(d, d2, d3, moveEntityPhase);
   }


   public double getY() {
      return this.y;
   }

   private void reset(double d, double d2, double d3, MoveEntityPhase moveEntityPhase) {
      this.x = d;
      this.y = d2;
      this.z = d3;
      this.phase = moveEntityPhase;
      this.setCancelled(false);
   }

   public void setZ(double d) {
      this.z = d;
   }

   public double getX() {
      return this.x;
   }

   public double getZ() {
      return this.z;
   }

   @Override
   public void call(MoveEntityListener listener) {
      if (this.phase == MoveEntityPhase.PRE) {
         listener.onPreMoveEntity(this);
      } else {
         listener.onPostMoveEntity(this);
      }
   }

   public void setY(double d) {
      this.y = d;
   }

   public void setX(double d) {
      this.x = d;
   }
}
