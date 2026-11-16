package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AuxiliaryXXXManager {
   public String[][] stringArray = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
   public Object[][] objectArray = new Object[][]{
      {Blocks.block142, Blocks.block65, Items.ironIngot, Items.diamond, Items.goldIngot},
      {Items.woodenPickaxe, Items.stonePickaxe, Items.ironPickaxe, Items.diamondPickaxe, Items.goldenPickaxe},
      {Items.woodenShovel, Items.stoneShovel, Items.ironShovel, Items.diamondShovel, Items.goldenShovel},
      {Items.woodenAxe, Items.stoneAxe, Items.ironAxe, Items.diamondAxe, Items.goldenAxe},
      {Items.woodenHoe, Items.stoneHoe, Items.ironHoe, Items.diamondHoe, Items.goldenHoe}
   };

   public void applyExtendedManager(ExtendedManager extendedManager) {
      for (int index = 0; index < this.objectArray[0].length; index++) {
         Object entry = this.objectArray[0][index];

         for (int index2 = 0; index2 < this.objectArray.length - 1; index2++) {
            Item item = (Item)this.objectArray[index2 + 1][index];
            extendedManager.resolveInternalListenerProvider(new ItemStack(item), new Object[]{this.stringArray[index2], '#', Items.stick, 'X', entry});
         }
      }

      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.shears), new Object[]{" #", "# ", '#', Items.ironIngot});
   }
}
