package net.augustus.events.listeners;


import net.augustus.events.EventLiquidPhysics;
import net.augustus.events.EventManager;
public interface LiquidPhysicsListener {
   EventLiquidPhysics LIQUID_PHYSICS_EVENT = new EventLiquidPhysics();

   void onLiquidPhysics(EventLiquidPhysics event);

   static EventLiquidPhysics onLiquidPhysics(
      boolean flag, float f, double d, double d2, boolean flag2, double d3, double d4, double d5, boolean flag3, double d6
   ) {
      EventLiquidPhysics.reset(LIQUID_PHYSICS_EVENT, f, d, d2, flag2, d3, d4, d5, flag3, d6);
      if (flag) {
         EventManager.getEventBus().post(66, LIQUID_PHYSICS_EVENT);
      }

      return LIQUID_PHYSICS_EVENT;
   }
}
