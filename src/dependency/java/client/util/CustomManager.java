package client.util;

import client.setting.CombatAlphaProperty;
import client.setting.CombatToggleProperty;
import client.setting.CommonOptionType;
import client.setting.CoreOptionType;
import client.setting.CustomOptionType;
import client.setting.IndirectOptionType;
import client.setting.InternalOptionType;
import client.setting.LocalOptionType;
import client.setting.MovementOffsetProperty;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.BlockStonebrick;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CustomManager {
   public void applyExtendedManager(ExtendedManager extendedManager) {
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.blockTrappedChest), new Object[]{"###", "# #", "###", '#', Blocks.block142});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block128), new Object[]{"#-", '#', Blocks.blockTrappedChest, '-', Blocks.blockTripwireHook});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block60), new Object[]{"###", "#E#", "###", '#', Blocks.block113, 'E', Items.enderEye});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block39), new Object[]{"###", "# #", "###", '#', Blocks.block65});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block46), new Object[]{"##", "##", '#', Blocks.block142});
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block136), new Object[]{"##", "##", '#', new ItemStack(Blocks.blockSand, 1, CombatAlphaProperty.SAND.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block119), new Object[]{"##", "##", '#', new ItemStack(Blocks.blockSand, 1, CombatAlphaProperty.RED_SAND.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block136, 4, InternalOptionType.SMOOTH.getCount()),
         new Object[]{"##", "##", '#', new ItemStack(Blocks.block136, 1, InternalOptionType.DEFAULT.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block119, 4, CombatToggleProperty.SMOOTH.getCount()),
         new Object[]{"##", "##", '#', new ItemStack(Blocks.block119, 1, CombatToggleProperty.DEFAULT.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block136, 1, InternalOptionType.CHISELED.getCount()),
         new Object[]{"#", "#", '#', new ItemStack(Blocks.extraBlockAdapter6, 1, IndirectOptionType.SAND.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block119, 1, CombatToggleProperty.CHISELED.getCount()),
         new Object[]{"#", "#", '#', new ItemStack(Blocks.extraBlockAdapter4, 1, CoreOptionType.RED_SANDSTONE.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block17, 1, CommonOptionType.CHISELED.getCount()),
         new Object[]{"#", "#", '#', new ItemStack(Blocks.extraBlockAdapter6, 1, IndirectOptionType.QUARTZ.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block17, 2, CommonOptionType.LINES_Y.getCount()),
         new Object[]{"#", "#", '#', new ItemStack(Blocks.block17, 1, CommonOptionType.DEFAULT.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block42, 4), new Object[]{"##", "##", '#', new ItemStack(Blocks.stone, 1, CustomOptionType.STONE.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block42, 1, BlockStonebrick.count4),
         new Object[]{"#", "#", '#', new ItemStack(Blocks.extraBlockAdapter6, 1, IndirectOptionType.SMOOTHBRICK.getCount())}
      );
      extendedManager.applyItemStack(new ItemStack(Blocks.block42, 1, BlockStonebrick.count3), new Object[]{Blocks.block42, Blocks.block144});
      extendedManager.applyItemStack(new ItemStack(Blocks.block131, 1), new Object[]{Blocks.block65, Blocks.block144});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block127, 16), new Object[]{"###", "###", '#', Items.ironIngot});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block19, 16), new Object[]{"###", "###", '#', Blocks.block68});
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block64, 1), new Object[]{" R ", "RGR", " R ", 'R', Items.redstone, 'G', Blocks.block77});
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.blockBeacon, 1), new Object[]{"GGG", "GSG", "OOO", 'G', Blocks.block68, 'S', Items.netherStar, 'O', Blocks.block113}
      );
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block86, 1), new Object[]{"NN", "NN", 'N', Items.netherbrick});
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.stone, 2, CustomOptionType.DIORITE.getCount()), new Object[]{"CQ", "QC", 'C', Blocks.block65, 'Q', Items.quartz}
      );
      extendedManager.applyItemStack(
         new ItemStack(Blocks.stone, 1, CustomOptionType.GRANITE.getCount()),
         new Object[]{new ItemStack(Blocks.stone, 1, CustomOptionType.DIORITE.getCount()), Items.quartz}
      );
      extendedManager.applyItemStack(
         new ItemStack(Blocks.stone, 2, CustomOptionType.ANDESITE.getCount()),
         new Object[]{new ItemStack(Blocks.stone, 1, CustomOptionType.DIORITE.getCount()), Blocks.block65}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block115, 4, MovementOffsetProperty.COARSE_DIRT.getCount()),
         new Object[]{"DG", "GD", 'D', new ItemStack(Blocks.block115, 1, MovementOffsetProperty.DIRT.getCount()), 'G', Blocks.block45}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.stone, 4, CustomOptionType.DIORITE_SMOOTH.getCount()),
         new Object[]{"SS", "SS", 'S', new ItemStack(Blocks.stone, 1, CustomOptionType.DIORITE.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.stone, 4, CustomOptionType.GRANITE_SMOOTH.getCount()),
         new Object[]{"SS", "SS", 'S', new ItemStack(Blocks.stone, 1, CustomOptionType.GRANITE.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.stone, 4, CustomOptionType.ANDESITE_SMOOTH.getCount()),
         new Object[]{"SS", "SS", 'S', new ItemStack(Blocks.stone, 1, CustomOptionType.ANDESITE.getCount())}
      );
      extendedManager.resolveInternalListenerProvider(new ItemStack(Blocks.block58, 1, BlockPrismarine.count3), new Object[]{"SS", "SS", 'S', Items.prismarineShard});
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block58, 1, BlockPrismarine.count2), new Object[]{"SSS", "SSS", "SSS", 'S', Items.prismarineShard}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block58, 1, BlockPrismarine.count),
         new Object[]{"SSS", "SIS", "SSS", 'S', Items.prismarineShard, 'I', new ItemStack(Items.dye, 1, LocalOptionType.BLACK.getCount2())}
      );
      extendedManager.resolveInternalListenerProvider(
         new ItemStack(Blocks.block62, 1, 0), new Object[]{"SCS", "CCC", "SCS", 'S', Items.prismarineShard, 'C', Items.prismarineCrystals}
      );
   }
}
