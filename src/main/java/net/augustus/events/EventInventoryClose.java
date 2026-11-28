package net.augustus.events;

import net.augustus.events.listeners.InventoryCloseListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import de.florianmichael.dietrichevents2.StateTypes;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;

public class EventInventoryClose extends AbstractEvent<InventoryCloseListener> {
   private final TimeHelper timeHelper;
   public static final int ID = 52;
   private boolean prevented = false;
   private StateTypes state = StateTypes.PRE;

   @Override
   public void call(InventoryCloseListener listener) {
      if (this.state == StateTypes.PRE) {
         if (!this.prevented) {
            this.prevented = listener.onPreInventoryClose();
         }
      } else if (!this.prevented) {
         listener.onPostInventoryClose(this);
      }
   }

   public static StateTypes setState(EventInventoryClose event, StateTypes stateTypes) {
      return event.state = stateTypes;
   }

   public static TimeHelper getTimer(EventInventoryClose event) {
      return event.timeHelper;
   }


   public boolean canCloseAfterDelay(double d) {
      boolean flag = this.timeHelper.reached(d) && Minecraft.getMinecraft().currentScreen != null;
      if (flag) {
         this.timeHelper.reset();
      }

      return flag;
   }

   public EventInventoryClose() {
      this.timeHelper = new TimeHelper();
   }

   public static void reset(EventInventoryClose event) {
      event.reset();
   }

   public static boolean isPrevented(EventInventoryClose event) {
      return event.prevented;
   }

   private void reset() {
      this.prevented = false;
      this.state = StateTypes.PRE;
   }
}
