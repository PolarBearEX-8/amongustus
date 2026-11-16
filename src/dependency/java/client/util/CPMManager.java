package client.util;

import client.setting.LocalOptionType;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CPMManager {
   public void applyExtendedManager(ExtendedManager extendedManager) {
      extendedManager.applyItemStack(new ItemStack(Items.mushroomStew), new Object[]{Blocks.secondaryBlockAdapter2, Blocks.secondaryBlockAdapter, Items.bowl});
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Items.cookie, 8), new Object[]{"#X#", 'X', new ItemStack(Items.dye, 1, LocalOptionType.BROWN.getCount2()), '#', Items.wheat}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Items.rabbitStew),
         new Object[]{
            " R ",
            "CPM",
            " B ",
            'R',
            new ItemStack(Items.cookedRabbit),
            'C',
            Items.carrot,
            'P',
            Items.bakedPotato,
            'M',
            Blocks.secondaryBlockAdapter2,
            'B',
            Items.bowl
         }
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Items.rabbitStew),
         new Object[]{
            " R ",
            "CPD",
            " B ",
            'R',
            new ItemStack(Items.cookedRabbit),
            'C',
            Items.carrot,
            'P',
            Items.bakedPotato,
            'D',
            Blocks.secondaryBlockAdapter,
            'B',
            Items.bowl
         }
      );
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block67), new Object[]{"MMM", "MMM", "MMM", 'M', Items.melon});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.melonSeeds), new Object[]{"M", 'M', Items.melon});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Items.pumpkinSeeds, 4), new Object[]{"M", 'M', Blocks.block146});
      extendedManager.applyItemStack(new ItemStack(Items.pumpkinPie), new Object[]{Blocks.block146, Items.sugar, Items.egg});
      extendedManager.applyItemStack(new ItemStack(Items.fermentedSpiderEye), new Object[]{Items.spiderEye, Blocks.secondaryBlockAdapter2, Items.sugar});
      extendedManager.applyItemStack(new ItemStack(Items.blazePowder, 2), new Object[]{Items.blazeRod});
      extendedManager.applyItemStack(new ItemStack(Items.magmaCream), new Object[]{Items.blazePowder, Items.slimeBall});
   }
}
