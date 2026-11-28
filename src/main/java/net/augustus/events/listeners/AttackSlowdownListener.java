package net.augustus.events.listeners;


import net.augustus.events.AttackSlowdownStage;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.events.EventManager;
public interface AttackSlowdownListener {
   EventAttackSlowdown ATTACK_SLOWDOWN_EVENT = new EventAttackSlowdown();

   default void onAttackSlowdownFirst(EventAttackSlowdown event) {
   }

   static EventAttackSlowdown onAttackSlowdown(AttackSlowdownStage attackSlowdownStage, boolean flag) {
      EventAttackSlowdown.reset(ATTACK_SLOWDOWN_EVENT, attackSlowdownStage);
      ATTACK_SLOWDOWN_EVENT.setSimulated(flag);
      EventManager.getEventBus().post(2, ATTACK_SLOWDOWN_EVENT);
      return ATTACK_SLOWDOWN_EVENT;
   }

   default void onAttackSlowdownSecond(EventAttackSlowdown event) {
   }

   static EventAttackSlowdown onAttackSlowdown(AttackSlowdownStage attackSlowdownStage) {
      return onAttackSlowdown(attackSlowdownStage, false);
   }
}
