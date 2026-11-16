package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.LocalOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockStainedGlassPane extends BlockGlassPane {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("color", LocalOptionType.class);

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.TRANSLUCENT;
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, LocalOptionType.resolveLocalOptionType(count));
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         BlockBeacon.submit(world, blockPos);
      }
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (int index = 0; index < LocalOptionType.values().length; index++) {
         list.add(new ItemStack(item, 1, index));
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         BlockBeacon.submit(world, blockPos);
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this,
         new IProperty[]{internalImmutableSetEntry, internalImmutableSetEntry2, internalImmutableSetEntry3, internalImmutableSetEntry4, immutableSetEntry}
      );
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((LocalOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockStainedGlassPane() {
      super(Material.material26, false);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, LocalOptionType.WHITE)
      );
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
