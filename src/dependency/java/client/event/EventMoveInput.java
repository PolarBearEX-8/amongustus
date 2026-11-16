package client.event;

import client.setting.CombatModeProperty;
import client.util.GenericIntArrayEntry;
import client.util.MoveUtil;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventMoveInput extends AbstractEvent {
   public static final int count = 36;
   public boolean enabled6;
   public boolean enabled4;
   public boolean enabled8;
   public boolean enabled;
   public CombatModeProperty combatModeProperty;
   public boolean enabled2;
   public boolean enabled5;
   public boolean enabled3 = false;
   public boolean enabled7;

   public void applyMoveInputListener(MoveInputListener moveInputListener) {
      switch (GenericIntArrayEntry.countArray[this.combatModeProperty.ordinal()]) {
         case 1:
            moveInputListener.onPostMoveInput(this);
            break;
         case 2:
            moveInputListener.onMoveInput(this);
            break;
         case 3:
            moveInputListener.onPreMoveInput(this);
      }
   }

   public void setState(boolean state) {
      this.enabled8 = true;
      this.enabled2 = state;
   }

   public void setActive(boolean active) {
      this.enabled5 = active;
   }

   public static void onMoveInput(EventMoveInput event, CombatModeProperty combatModeProperty2) {
      event.applyCombatModeProperty(combatModeProperty2);
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public boolean isEnabled8() {
      return this.enabled8;
   }

   public void call(Object entry) {
      MoveInputListener moveInputListener = (MoveInputListener)entry;
      this.applyMoveInputListener(moveInputListener);
   }

   public boolean isEnabled6() {
      return this.enabled6;
   }

   public void setActive2(boolean active) {
      this.enabled4 = active;
   }

   public boolean isEnabled7() {
      return this.enabled7;
   }

   public void setActive3(boolean active) {
      this.enabled7 = active;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setEnabled6(boolean enabled9) {
      this.enabled6 = enabled9;
   }

   public void setActive4(boolean active) {
      this.enabled = active;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void applyCombatModeProperty(CombatModeProperty combatModeProperty2) {
      this.combatModeProperty = combatModeProperty2;
      this.enabled3 = false;
      this.enabled = false;
      this.enabled5 = false;
      this.enabled4 = false;
      this.enabled6 = false;
      this.enabled7 = true;
      this.enabled2 = false;
      this.enabled8 = false;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void setActive5(boolean flag) {
      if (MoveUtil.canExecute()) {
         this.enabled3 = flag;
      }
   }

   public EventMoveInput() {
      this.enabled = false;
      this.enabled5 = false;
      this.enabled4 = false;
      this.enabled6 = false;
      this.enabled7 = true;
      this.enabled2 = false;
      this.enabled8 = false;
   }
}
