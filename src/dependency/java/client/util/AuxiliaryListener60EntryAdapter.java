package client.util;

import net.minecraft.item.IronGem;

public class AuxiliaryListener60EntryAdapter extends IronGem {
   public static ListenerTracker listenerTracker = new ListenerTracker(null);

   @Override
   public String getString() {
      return "Modules_Settings";
   }

   @Override
   public String getText() {
      return "settings";
   }

   public AuxiliaryListener60EntryAdapter() {
      super(new ListenerTracker[]{listenerTracker});
   }
}
