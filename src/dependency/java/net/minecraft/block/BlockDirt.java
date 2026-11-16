package net.minecraft.block;

import client.setting.MovementOffsetProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDirt extends Block {
   public static final ImmutableSetEntry immutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      return blockState.getBlock() != this ? 0 : ((MovementOffsetProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState blockState2 = blockState;
      if (blockState.getComparable(immutableSetEntry) == MovementOffsetProperty.PODZOL) {
         Block block = iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock();
         blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, block == Blocks.block61 || block == Blocks.block74);
      }

      return blockState2;
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return ((MovementOffsetProperty)blockState.getComparable(immutableSetEntry)).getMapColor();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, MovementOffsetProperty.resolveMovementOffsetProperty(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(this, 1, MovementOffsetProperty.DIRT.getCount()));
      list.add(new ItemStack(this, 1, MovementOffsetProperty.COARSE_DIRT.getCount()));
      list.add(new ItemStack(this, 1, MovementOffsetProperty.PODZOL.getCount()));
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      MovementOffsetProperty comparable = (MovementOffsetProperty)blockState.getComparable(immutableSetEntry);
      if (comparable == MovementOffsetProperty.PODZOL) {
         comparable = MovementOffsetProperty.DIRT;
      }

      return comparable.getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((MovementOffsetProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockDirt() {
      super(Material.material32);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(immutableSetEntry, MovementOffsetProperty.DIRT)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.processAction(ItemGroupManager.itemGroupManager10);
   }

   static {
      String[] textArray = new String[]{"variant", "snowy"};
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[0], MovementOffsetProperty.class);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
   }
}
