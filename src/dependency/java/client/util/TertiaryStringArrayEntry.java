package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TertiaryStringArrayEntry {
   public String[][] stringArray = new String[][]{{"X", "X", "#"}};
   public Object[][] objectArray = new Object[][]{
      {Blocks.block142, Blocks.block65, Items.ironIngot, Items.diamond, Items.goldIngot},
      {Items.woodenSword, Items.stoneSword, Items.ironSword, Items.diamondSword, Items.goldenSword}
   };

   public void applyExtendedManager(ExtendedManager extendedManager) {
      for (int index = 0; index < this.objectArray[0].length; index++) {
         Object entry = this.objectArray[0][index];

         for (int index2 = 0; index2 < this.objectArray.length - 1; index2++) {
            Item item = (Item)this.objectArray[index2 + 1][index];
            extendedManager.resolveInternalListenerProvider(new ItemStack(item), new Object[]{this.stringArray[index2], '#', Items.stick, 'X', entry});
         }
      }

      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.bow, 1), new Object[]{" #X", "# X", " #X", 'X', Items.string, '#', Items.stick});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.arrow, 4), new Object[]{"X", "#", "Y", 'Y', Items.feather, 'X', Items.flint, '#', Items.stick});
   }
}
