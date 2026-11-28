package net.augustus.events;

import net.augustus.events.listeners.LiquidJumpListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventLiquidJump extends AbstractEvent<LiquidJumpListener> {
   private boolean ignoreWater;
   public static final int ID = 67;
   private double lavaMotionY;
   private boolean ignoreLava;
   private double waterMotionY;

   public boolean isIgnoreWater() {
      return this.ignoreWater;
   }

   private void reset(boolean flag, boolean flag2, double d, double d2) {
      this.ignoreWater = flag;
      this.ignoreLava = flag2;
      this.waterMotionY = d;
      this.lavaMotionY = d2;
   }

   @Override
   public void call(LiquidJumpListener listener) {
      listener.onLiquidJump(this);
   }

   public static void reset(EventLiquidJump event, boolean flag, boolean flag2, double d, double d2) {
      event.reset(flag, flag2, d, d2);
   }

   public void setIgnoreWater(boolean flag) {
      this.ignoreWater = flag;
   }

   public void setLavaMotionY(double d) {
      this.lavaMotionY = d;
   }


   public void setWaterMotionY(double d) {
      this.waterMotionY = d;
   }

   public boolean isIgnoreLava() {
      return this.ignoreLava;
   }

   public void setIgnoreLava(boolean flag) {
      this.ignoreLava = flag;
   }

   public double getWaterMotionY() {
      return this.waterMotionY;
   }

   public double getLavaMotionY() {
      return this.lavaMotionY;
   }
}
