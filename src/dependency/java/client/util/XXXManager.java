package client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class XXXManager {
   public String[][] stringArray = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
   public Item[][] itemArray = new Item[][]{
      {Items.leather, Items.ironIngot, Items.diamond, Items.goldIngot},
      {Items.leatherHelmet, Items.ironHelmet, Items.diamondHelmet, Items.goldenHelmet},
      {Items.leatherChestplate, Items.ironChestplate, Items.diamondChestplate, Items.goldenChestplate},
      {Items.leatherLeggings, Items.ironLeggings, Items.diamondLeggings, Items.goldenLeggings},
      {Items.leatherBoots, Items.ironBoots, Items.diamondBoots, Items.goldenBoots}
   };

   public void applyExtendedManager(ExtendedManager extendedManager) {
      for (int index = 0; index < this.itemArray[0].length; index++) {
         Item item = this.itemArray[0][index];

         for (int index2 = 0; index2 < this.itemArray.length - 1; index2++) {
            Item item2 = this.itemArray[index2 + 1][index];
            extendedManager.resolveInternalListenerProvider(new ItemStack(item2), new Object[]{this.stringArray[index2], 'X', item});
         }
      }
   }
}
