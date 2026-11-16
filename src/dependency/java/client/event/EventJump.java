package client.event;

import client.setting.CombatRangeProperty;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventJump extends AbstractEvent {
   public float factor;
   public float factor2;
   public static final int count = 44;
   public float factor3;
   public CombatRangeProperty combatRangeProperty;

   public void onJumpCallback(EventJumpCallback event) {
      if (this.combatRangeProperty == CombatRangeProperty.WORLD_COLOR) {
         event.onJump(this);
      } else {
         event.onJump2(this);
      }
   }

   public void call(Object entry) {
      EventJumpCallback event = (EventJumpCallback)entry;
      this.onJumpCallback(event);
   }

   public void processAction(float factor4) {
      this.factor2 = factor4 / 255.0F;
   }

   public static void onJump(EventJump event, float posX, float posY, float posZ, CombatRangeProperty combatRangeProperty2) {
      event.applyCombatRangeProperty(posX, posY, posZ, combatRangeProperty2);
   }

   public void processAction2(float factor4) {
      this.factor = factor4 / 255.0F;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public void processAction3(float factor4) {
      this.factor3 = factor4 / 255.0F;
   }

   private void applyCombatRangeProperty(float posX, float posY, float posZ, CombatRangeProperty combatRangeProperty2) {
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
      this.combatRangeProperty = combatRangeProperty2;
   }

   public float getFactor() {
      return this.factor;
   }

   public float getFactor2() {
      return this.factor2;
   }
}
