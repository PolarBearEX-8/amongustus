package net.augustus.events;

import net.augustus.events.listeners.SafeWalkListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;

public class EventSafeWalk extends AbstractEvent<SafeWalkListener> {
   public static final int ID = 33;
   private boolean disableSneakSafeWalk;
   private boolean safeWalk;
   private boolean clippedAtEdge;
   private StateTypes state;

   public void setDisableSneakSafeWalk(boolean flag) {
      this.disableSneakSafeWalk = flag;
   }

   public boolean isSafeWalk() {
      return this.safeWalk;
   }

   public boolean isDisableSneakSafeWalk() {
      return this.disableSneakSafeWalk;
   }

   @Override
   public void call(SafeWalkListener listener) {
      if (this.state == StateTypes.PRE) {
         listener.onPreSafeWalk(this);
      } else {
         listener.onPostSafeWalk(this);
      }
   }

   public static void reset(EventSafeWalk event, StateTypes stateTypes, boolean flag) {
      event.reset(stateTypes, flag);
   }


   public boolean isClippedAtEdge() {
      return this.clippedAtEdge;
   }

   public void setSafeWalk(boolean flag) {
      this.safeWalk = flag;
   }

   private void reset(StateTypes stateTypes, boolean flag) {
      this.state = stateTypes;
      this.clippedAtEdge = flag;
      this.safeWalk = false;
      this.disableSneakSafeWalk = false;
   }
}
