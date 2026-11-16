package net.minecraft.block;

import client.setting.MovementProperty;
import client.util.DecayableManager;
import client.util.ExtendedPredicateAdapter;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.StatCraftItemManager;
import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryBlockLeaves extends DecayableManager {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(
      "variant", MovementProperty.class, new ExtendedPredicateAdapter()
   );

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      return blockState.getBlock().getIndex(blockState) & 3;
   }

   @Override
   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, int count) {
      if (blockState.getComparable(immutableSetEntry) == MovementProperty.DARK_OAK && world.random.nextInt(count) == 0) {
         applyWorld(world, blockPos, new ItemStack(Items.apple, 1, 0));
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(immutableSetEntry, this.resolveMovementProperty(count))
         .resolveIBlockState(internalImmutableSetEntry2, (count & 4) == 0)
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public MovementProperty resolveMovementProperty(int count) {
      return MovementProperty.resolveMovementProperty((count & 3) + 4);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, internalImmutableSetEntry, internalImmutableSetEntry2});
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      if (!world.state && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears) {
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
         applyWorld(world, blockPos, new ItemStack(Item.processAction2(this), 1, ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount() - 4));
      } else {
         super.applyWorld(world, player, blockPos, blockState, tileEntity);
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount() - 4;
      if (!(Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
         count |= 4;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      return new ItemStack(Item.processAction2(this), 1, ((MovementProperty)blockState.getComparable(immutableSetEntry)).getCount() - 4);
   }

   public AuxiliaryBlockLeaves() {
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(immutableSetEntry, MovementProperty.ACACIA)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.TRUE)
      );
   }
}
