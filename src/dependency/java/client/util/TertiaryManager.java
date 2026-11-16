package client.util;

import client.setting.CombatFactorProperty;
import client.setting.ExternalOptionType;
import client.setting.LocalOptionType;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TertiaryManager {
   public void applyExtendedManager(ExtendedManager extendedManager) {
      for (int index = 0; index < 16; index++) {
         extendedManager.applyItemStack(
            new ItemStack(Blocks.wool, 1, index), new Object[]{new ItemStack(Items.dye, 1, 15 - index), new ItemStack(Item.processAction2(Blocks.wool), 1, 0)}
         );
         extendedManager.resolveInternalListenerProvider(
            new ItemStack(Blocks.block107, 8, 15 - index),
            new Object[]{"###", "#X#", "###", '#', new ItemStack(Blocks.block98), 'X', new ItemStack(Items.dye, 1, index)}
         );
         extendedManager.resolveInternalListenerProvider(
            new ItemStack(Blocks.blockStainedGlass, 8, 15 - index),
            new Object[]{"###", "#X#", "###", '#', new ItemStack(Blocks.block68), 'X', new ItemStack(Items.dye, 1, index)}
         );
         extendedManager.resolveInternalListenerProvider(
            new ItemStack(Blocks.blockStainedGlassPane, 16, index), new Object[]{"###", "###", '#', new ItemStack(Blocks.blockStainedGlass, 1, index)}
         );
      }

      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.YELLOW.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry, 1, CombatFactorProperty.DANDELION.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.POPPY.getCount())}
      );
      extendedManager.applyItemStack(new ItemStack(Items.dye, 3, LocalOptionType.WHITE.getCount2()), new Object[]{Items.bone});
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.PINK.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.ORANGE.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.YELLOW.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.LIME.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.GREEN.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.GRAY.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.BLACK.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.SILVER.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.GRAY.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 3, LocalOptionType.SILVER.getCount2()),
         new Object[]{
            new ItemStack(Items.dye, 1, LocalOptionType.BLACK.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())
         }
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.LIGHT_BLUE.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.CYAN.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.GREEN.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.PURPLE.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.MAGENTA.getCount2()),
         new Object[]{new ItemStack(Items.dye, 1, LocalOptionType.PURPLE.getCount2()), new ItemStack(Items.dye, 1, LocalOptionType.PINK.getCount2())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 3, LocalOptionType.MAGENTA.getCount2()),
         new Object[]{
            new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.PINK.getCount2())
         }
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 4, LocalOptionType.MAGENTA.getCount2()),
         new Object[]{
            new ItemStack(Items.dye, 1, LocalOptionType.BLUE.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()),
            new ItemStack(Items.dye, 1, LocalOptionType.WHITE.getCount2())
         }
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.LIGHT_BLUE.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.BLUE_ORCHID.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.MAGENTA.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.ALLIUM.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.SILVER.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.HOUSTONIA.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.RED.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.RED_TULIP.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.ORANGE.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.ORANGE_TULIP.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.SILVER.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.WHITE_TULIP.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.PINK.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.PINK_TULIP.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 1, LocalOptionType.SILVER.getCount2()),
         new Object[]{new ItemStack(Blocks.immutableSetEntry2Entry2, 1, CombatFactorProperty.OXEYE_DAISY.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.YELLOW.getCount2()),
         new Object[]{new ItemStack(Blocks.blockDoublePlant, 1, ExternalOptionType.SUNFLOWER.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.MAGENTA.getCount2()),
         new Object[]{new ItemStack(Blocks.blockDoublePlant, 1, ExternalOptionType.SYRINGA.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.RED.getCount2()),
         new Object[]{new ItemStack(Blocks.blockDoublePlant, 1, ExternalOptionType.ROSE.getCount())}
      );
      extendedManager.applyItemStack(
         new ItemStack(Items.dye, 2, LocalOptionType.PINK.getCount2()),
         new Object[]{new ItemStack(Blocks.blockDoublePlant, 1, ExternalOptionType.PAEONIA.getCount())}
      );

      for (int index2 = 0; index2 < 16; index2++) {
         extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block141, 3, index2), new Object[]{"##", '#', new ItemStack(Blocks.wool, 1, index2)});
      }
   }
}
