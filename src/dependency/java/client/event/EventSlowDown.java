package client.event;

import client.setting.CombatThresholdProperty;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSlowDown extends AbstractEvent {
   public static final int count2 = 41;
   public float factor;
   public int count;
   public CombatThresholdProperty combatThresholdProperty;

   public float getFactor() {
      return this.factor;
   }

   public static void onSlowDown(EventSlowDown event, int count3, float factor2, CombatThresholdProperty combatThresholdProperty2) {
      event.applyCombatThresholdProperty(count3, factor2, combatThresholdProperty2);
   }

   public void applySlowDownListener(SlowDownListener slowDownListener) {
      if (this.combatThresholdProperty == CombatThresholdProperty.TICK) {
         slowDownListener.onCustomSlowDown(this);
      } else {
         slowDownListener.onSlowDown(this);
      }
   }

   public void call(Object entry) {
      SlowDownListener slowDownListener = (SlowDownListener)entry;
      this.applySlowDownListener(slowDownListener);
   }

   public void processAction(int count3) {
      this.count = count3;
   }

   public int getCount2() {
      return this.count;
   }

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   private void applyCombatThresholdProperty(int count3, float factor2, CombatThresholdProperty combatThresholdProperty2) {
      this.count = count3;
      this.factor = factor2;
      this.combatThresholdProperty = combatThresholdProperty2;
   }
}
