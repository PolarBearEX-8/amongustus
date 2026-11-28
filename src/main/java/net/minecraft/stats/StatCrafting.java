package net.minecraft.stats;

import net.minecraft.item.Item;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.IChatComponent;

public class StatCrafting extends StatBase {
   private final Item item;

   public StatCrafting(String str, String str2, IChatComponent component, Item item2) {
      super(str + str2, component);
      this.item = item2;
      int idFromItem = Item.getIdFromItem(item2);
      if (idFromItem != 0) {
         IScoreObjectiveCriteria.INSTANCES.put(str + idFromItem, this.getObjectiveCriteria());
      }
   }

   public Item getItem() {
      return this.item;
   }
}
