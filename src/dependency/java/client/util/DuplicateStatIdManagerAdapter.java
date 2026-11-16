package client.util;

import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;

public class DuplicateStatIdManagerAdapter extends DuplicateStatIdManager {
   public final Item item;

   public Item getItem() {
      return this.item;
   }

   public DuplicateStatIdManagerAdapter(String text, String text2, IChatComponent iChatComponent, Item item2) {
      super(text + text2, iChatComponent);
      this.item = item2;
      int index = Item.getIndex(item2);
      if (index != 0) {
         HealthHandler.map.put(text + index, this.getHealthHandler());
      }
   }
}
