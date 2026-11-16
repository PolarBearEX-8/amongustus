package client.util;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.item.ItemCallback;

public class AltAbstractEventAdapter extends AbstractEvent {
   public boolean enabled;
   public static final int count = 6;

   public static void applyAltAbstractEventAdapter(AltAbstractEventAdapter event, boolean flag) {
      event.setEnabled(flag);
   }

   private void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void call(Object entry) {
      ItemCallback itemCallback = (ItemCallback)entry;
      this.applyItemCallback(itemCallback);
   }

   public void applyItemCallback(ItemCallback itemCallback) {
      if (this.enabled) {
         itemCallback.updateState6();
      } else {
         itemCallback.updateKillAura();
      }
   }
}
