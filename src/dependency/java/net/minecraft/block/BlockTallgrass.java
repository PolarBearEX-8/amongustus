package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.BlockNoneType;
import client.setting.ExternalOptionType;
import client.setting.MovementColorProperty;
import client.util.DefaultIntArrayEntry;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTallgrass extends SecondaryBlockAdapter implements PreMotionFilter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("type", MovementColorProperty.class);

   @Override
   public int processAction2(IBlockState blockState) {
      if (blockState.getBlock() != this) {
         return super.processAction2(blockState);
      } else {
         return (MovementColorProperty)blockState.getComparable(immutableSetEntry) == MovementColorProperty.DEAD_BUSH
            ? 16777215
            : DefaultIntArrayEntry.computeCount(0.5, 1.0);
      }
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      return blockState.getBlock().getIndex(blockState);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, MovementColorProperty.resolveMovementColorProperty(count));
   }

   @Override
   public int processAction6() {
      return DefaultIntArrayEntry.computeCount(0.5, 1.0);
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return blockState.getComparable(immutableSetEntry) != MovementColorProperty.DEAD_BUSH;
   }

   @Override
   public boolean processAction6(World world, BlockPos blockPos) {
      return true;
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      ExternalOptionType externalOptionType = ExternalOptionType.GRASS;
      if (blockState.getComparable(immutableSetEntry) == MovementColorProperty.FERN) {
         externalOptionType = ExternalOptionType.FERN;
      }

      if (Blocks.blockDoublePlant.processAction7(world, blockPos)) {
         Blocks.blockDoublePlant.applyWorldByWorld(world, blockPos, externalOptionType, 2);
      }
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return true;
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (int index = 1; index < 3; index++) {
         list.add(new ItemStack(item, 1, index));
      }
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return random.nextInt(8) == 0 ? Items.wheatSeeds : null;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(blockPos).computeCountByBlockPos(blockPos);
   }

   @Override
   public BlockNoneType processAction9() {
      return BlockNoneType.XYZ;
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      return this.matchesBlockByBlock(world.getBlockState(blockPos.down()).getBlock());
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int processAction(int count, Random random) {
      return 1 + random.nextInt(count * 2 + 1);
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      if (!world.state && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
         applyWorld(world, blockPos, new ItemStack(Blocks.blockTallgrass, 1, ((MovementColorProperty)blockState.getComparable(immutableSetEntry)).getCount()));
      } else {
         super.applyWorld(world, player, blockPos, blockState, tileEntity);
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((MovementColorProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockTallgrass() {
      super(Material.material7);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, MovementColorProperty.DEAD_BUSH));
      float factor = 0.4F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 0.8F, 0.5F + factor);
   }
}
