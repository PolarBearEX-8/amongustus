package net.augustus.events.listeners;


import net.augustus.events.EventLiquidJump;
import net.augustus.events.EventManager;
public interface LiquidJumpListener {
   EventLiquidJump LIQUID_JUMP_EVENT = new EventLiquidJump();

   void onLiquidJump(EventLiquidJump event);

   static EventLiquidJump onLiquidJump(boolean flag, boolean flag2, boolean flag3, double d) {
      EventLiquidJump.reset(LIQUID_JUMP_EVENT, flag2, flag3, d, d);
      if (flag) {
         EventManager.getEventBus().post(67, LIQUID_JUMP_EVENT);
      }

      return LIQUID_JUMP_EVENT;
   }
}
