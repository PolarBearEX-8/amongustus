package client.util;

import client.setting.LocalOptionType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CoreInternalManager {
   public Object[][] objectArray = new Object[][]{
      {Blocks.block35, new ItemStack(Items.goldIngot, 9)},
      {Blocks.block16, new ItemStack(Items.ironIngot, 9)},
      {Blocks.block91, new ItemStack(Items.diamond, 9)},
      {Blocks.block93, new ItemStack(Items.emerald, 9)},
      {Blocks.block76, new ItemStack(Items.dye, 9, LocalOptionType.BLUE.getCount2())},
      {Blocks.block57, new ItemStack(Items.redstone, 9)},
      {Blocks.block9, new ItemStack(Items.coal, 9, 0)},
      {Blocks.block137, new ItemStack(Items.wheat, 9)},
      {Blocks.block71, new ItemStack(Items.slimeBall, 9)}
   };

   public void applyExtendedManager(ExtendedManager extendedManager) {
      for (int index = 0; index < this.objectArray.length; index++) {
         Block block = (Block)this.objectArray[index][0];
         ItemStack stack = (ItemStack)this.objectArray[index][1];
         extendedManager.resolveInternalListenerProvider(new ItemStack(block), new Object[]{"###", "###", "###", '#', stack});
         extendedManager.resolveInternalListenerProvider(stack, new Object[]{"#", '#', block});
      }

      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.goldIngot), new Object[]{"###", "###", "###", '#', Items.goldNugget});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.goldNugget, 9), new Object[]{"#", '#', Items.goldIngot});
   }
}
