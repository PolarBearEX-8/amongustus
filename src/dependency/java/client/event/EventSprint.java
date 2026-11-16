package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventSprint extends AbstractEvent {
   public boolean enabled3;
   public boolean enabled;
   public static final int count = 33;
   public boolean enabled2;
   public StateTypes stateTypes;

   public void setActive(boolean active) {
      this.enabled2 = active;
   }

   public void applySprintListener(SprintListener sprintListener) {
      if (this.stateTypes == StateTypes.PRE) {
         sprintListener.onSprint(this);
      } else {
         sprintListener.onPostSprint(this);
      }
   }

   public void call(Object entry) {
      SprintListener sprintListener = (SprintListener)entry;
      this.applySprintListener(sprintListener);
   }

   public boolean canExecute() {
      return this.enabled3;
   }

   public static void onSprint(EventSprint event, StateTypes stateTypes2, boolean flag) {
      event.applyStateTypes(stateTypes2, flag);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setActive2(boolean active) {
      this.enabled = active;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   private void applyStateTypes(StateTypes stateTypes2, boolean flag) {
      this.stateTypes = stateTypes2;
      this.enabled3 = flag;
      this.enabled2 = false;
      this.enabled = false;
   }
}
