package net.augustus.events.listeners;

import net.augustus.events.EventItemGlint;
import net.augustus.events.EventManager;
import net.minecraft.item.ItemStack;

public interface ItemGlintListener {
   EventItemGlint ITEM_GLINT_EVENT = new EventItemGlint();

   static EventItemGlint onItemGlint(ItemStack stack, int n, double d) {
      EventItemGlint.reset(ITEM_GLINT_EVENT, stack, n, d);
      EventManager.getEventBus().post(12, ITEM_GLINT_EVENT);
      return ITEM_GLINT_EVENT;
   }

   void onItemGlint(EventItemGlint event);
}
