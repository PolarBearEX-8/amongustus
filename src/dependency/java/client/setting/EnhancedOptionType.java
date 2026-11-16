package client.setting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnhancedOptionType {
    ALL,
    ARMOR,
    ARMOR_FEET,
    ARMOR_LEGS,
    ARMOR_TORSO,
    ARMOR_HEAD,
    WEAPON,
    DIGGER,
    FISHING_ROD,
    BREAKABLE,
    BOW;

   public boolean matchesItem(Item item) {
      if (this == ALL) {
         return true;
      } else if (this == BREAKABLE && item.isActive()) {
         return true;
      } else if (item instanceof ItemGoldenBoots) {
         if (this == ARMOR) {
            return true;
         } else {
            ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
            return itemGoldenBoots.indexOffset3 == 0
               ? this == ARMOR_HEAD
               : (
                  itemGoldenBoots.indexOffset3 == 2
                     ? this == ARMOR_LEGS
                     : (itemGoldenBoots.indexOffset3 == 1 ? this == ARMOR_TORSO : (itemGoldenBoots.indexOffset3 == 3 ? this == ARMOR_FEET : false))
               );
         }
      } else {
         return item instanceof ItemSword
            ? this == WEAPON
            : (
               item instanceof ItemTool
                  ? this == DIGGER
                  : (item instanceof ItemBow ? this == BOW : (item instanceof ItemFishingRod ? this == FISHING_ROD : false))
            );
      }
   }


}
