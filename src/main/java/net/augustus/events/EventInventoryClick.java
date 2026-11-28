package net.augustus.events;

import net.augustus.events.listeners.InventoryClickListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventInventoryClick extends AbstractEvent<InventoryClickListener> {
   private boolean overrideMouseInput;
   public static final int ID = 51;
   private int rightClicks;
   private int leftClicks = 0;


   public EventInventoryClick() {
      this.rightClicks = 0;
      this.overrideMouseInput = false;
   }

   public void incrementLeftClicks() {
      this.leftClicks++;
   }

   public boolean isOverrideMouseInput() {
      return this.overrideMouseInput;
   }

   public void setOverrideMouseInput(boolean flag) {
      this.overrideMouseInput = flag;
   }

   public int getLeftClicks() {
      return this.leftClicks;
   }

   public static void reset(EventInventoryClick event) {
      event.reset();
   }

   public int getRightClicks() {
      return this.rightClicks;
   }

   @Override
   public void call(InventoryClickListener listener) {
      listener.onInventoryClick(this);
   }

   public void incrementRightClicks() {
      this.rightClicks++;
   }

   private void reset() {
      this.leftClicks = 0;
      this.rightClicks = 0;
      this.overrideMouseInput = false;
   }
}
