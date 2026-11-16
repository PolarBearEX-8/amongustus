package client.event;

import client.setting.CombatRangeProperty;

public interface EventJumpCallback {
   EventJump eventJump = new EventJump();

   default void onJump(EventJump event) {
   }

   static EventJump resolveEventJump(float posX, float posY, float posZ, CombatRangeProperty combatRangeProperty) {
      EventJump.onJump(eventJump, posX, posY, posZ, combatRangeProperty);
      EventBusManager.getDietrichEvents2().post(44, eventJump);
      return eventJump;
   }

   default void onJump2(EventJump event) {
   }
}
