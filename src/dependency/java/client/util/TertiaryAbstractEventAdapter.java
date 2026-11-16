package client.util;

import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.item.TertiaryItemCallback;

public class TertiaryAbstractEventAdapter extends AbstractEvent {
   public static final int count = 47;
   public int count2;
   public int count3;

   public void applyTertiaryItemCallback(TertiaryItemCallback tertiaryItemCallback) {
      int count4 = this.count3;
      int count5 = this.count2;
      tertiaryItemCallback.applyIndex(count5, count4);
   }

   private void applyIndex(int count4, int count5) {
      this.count2 = count4;
      this.count3 = count5;
   }

   public void call(Object entry) {
      TertiaryItemCallback tertiaryItemCallback = (TertiaryItemCallback)entry;
      this.applyTertiaryItemCallback(tertiaryItemCallback);
   }

   public static void applyTertiaryAbstractEventAdapt(TertiaryAbstractEventAdapter event, int count4, int count5) {
      event.applyIndex(count4, count5);
   }
}
