package client.setting;

import client.util.ChestItemPriorityMap;

public class InternalNamedOptionAdapter implements NamedOption {
   public boolean enabled;
   public final String labelText;
   public final ChestItemPriorityMap chestItemPriorityMap;

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static boolean matchesInternalNamedOptionAdapter(InternalNamedOptionAdapter internalNamedOptionAdapter) {
      return internalNamedOptionAdapter.enabled;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public InternalNamedOptionAdapter(ChestItemPriorityMap chestItemPriorityMap2, String text, boolean flag) {
      this.chestItemPriorityMap = chestItemPriorityMap2;
      this.enabled = true;
      this.labelText = text;
      this.enabled = flag;
   }
}
