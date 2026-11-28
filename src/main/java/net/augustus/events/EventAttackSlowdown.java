package net.augustus.events;

import net.augustus.events.listeners.AttackSlowdownListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventAttackSlowdown extends AbstractEvent<AttackSlowdownListener> {
   public static final int ID = 2;
   private boolean simulated;
   private boolean sprint = false;
   private AttackSlowdownStage stage;
   private double slowDownFactor;
   private boolean applySecondStage = false;


   public static void reset(EventAttackSlowdown event, AttackSlowdownStage attackSlowdownStage) {
      event.reset(attackSlowdownStage);
   }

   public EventAttackSlowdown() {
      this.slowDownFactor = 0.6;
   }

   public boolean isApplySecondStage() {
      return this.applySecondStage;
   }

   public void setApplySecondStage(boolean flag) {
      this.applySecondStage = flag;
   }

   @Override
   public void call(AttackSlowdownListener listener) {
      if (this.stage == AttackSlowdownStage.FIRST) {
         listener.onAttackSlowdownFirst(this);
      } else {
         listener.onAttackSlowdownSecond(this);
      }
   }

   public double getSlowDownFactor() {
      return this.slowDownFactor;
   }

   public boolean isSprint() {
      return this.sprint;
   }

   public void setSprint(boolean flag) {
      this.sprint = flag;
   }

   public void setSimulated(boolean flag) {
      this.simulated = flag;
   }

   private void reset(AttackSlowdownStage attackSlowdownStage) {
      this.stage = attackSlowdownStage;
      this.sprint = false;
      this.applySecondStage = false;
      this.slowDownFactor = 0.6;
   }

   public void setSlowDownFactor(double d) {
      this.slowDownFactor = d;
   }

   public boolean isSimulated() {
      return this.simulated;
   }
}
