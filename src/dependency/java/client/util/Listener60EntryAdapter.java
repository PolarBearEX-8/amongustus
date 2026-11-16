package client.util;

import net.minecraft.item.IronGem;

public class Listener60EntryAdapter extends IronGem {
   public static final ListenerTracker listenerTracker = new ListenerTracker(null);

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public String getString() {
      return "Client_Settings";
   }

   @Override
   public String getText() {
      return "settings";
   }

   public Listener60EntryAdapter() {
      super(new ListenerTracker[]{listenerTracker});
   }
}
