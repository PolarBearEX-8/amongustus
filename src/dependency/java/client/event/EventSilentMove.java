package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventSilentMove extends AbstractEvent {
   public boolean enabled2 = false;
   public boolean enabled = false;
   public static final int count3 = 19;
   public StateTypes stateTypes;
   public int count;
   public float factor;
   public int count2;

   public void setActive(boolean active) {
      this.enabled = active;
   }

   public void processAction(int count4) {
      this.count2 = count4;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void applyStateTypes(int count4, int count5, float factor2, StateTypes stateTypes2) {
      this.count2 = count4;
      this.count = count5;
      this.factor = factor2;
      this.stateTypes = stateTypes2;
   }

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   public void call(Object entry) {
      SilentMoveListener silentMoveListener = (SilentMoveListener)entry;
      this.applySilentMoveListener(silentMoveListener);
   }

   public boolean canExecute() {
      return this.enabled2;
   }

   public void setActive2(boolean active) {
      this.enabled2 = active;
   }

   public int getIndex() {
      return this.count;
   }

   public void processAction2(int count4) {
      this.count = count4;
   }

   public void applySilentMoveListener(SilentMoveListener silentMoveListener) {
      if (this.stateTypes == StateTypes.PRE) {
         silentMoveListener.onSilentMove(this);
      } else {
         silentMoveListener.onPostSilentMove();
      }
   }

   public int getIndex2() {
      return this.count2;
   }

   public static void onSilentMove(EventSilentMove event, int count4, int count5, float factor2, StateTypes stateTypes2) {
      event.applyStateTypes(count4, count5, factor2, stateTypes2);
   }

   public float getScaleFactor() {
      return this.factor;
   }
}
