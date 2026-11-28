package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;

public class ItemCoal extends Item {

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      return stack.getMetadata() == 1 ? "item.charcoal" : "item.coal";
   }

   public ItemCoal() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }
}
