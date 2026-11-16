package net.minecraft.block;

import client.setting.CombatConfigProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnowLayer extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("layers", 1, 8);
   public static final AxisAlignedBB[] axisAlignedBBArray = new AxisAlignedBB[]{
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)
   };

   public void applyIndex(int count) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (float)count / 8.0F, 1.0F);
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (world.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 11) {
         this.processAction(world, blockPos, world.getBlockState(blockPos), 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public void processAction2() {
      this.applyIndex(0);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_13)) {
         this.processAction(axisAlignedBBArray[((Integer)iBlockState.getComparable(auxiliaryImmutableSetEntry))]);
      } else {
         this.applyIndex((Integer)iBlockState.getComparable(auxiliaryImmutableSetEntry));
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return (Integer)iBlockAccess.getIBlockState(count, count2, count3).getComparable(auxiliaryImmutableSetEntry) < 5;
   }

   private boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.processAction7(world, blockPos)) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, (count & 7) + 1);
   }

   @Override
   public boolean processAction6(World world, BlockPos blockPos) {
      return (Integer)world.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry) == 1;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.snowball;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.matchesWorld(world, blockPos, blockState);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return (Integer)iBlockAccess.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry) < 5;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos.down());
      Block block = blockState.getBlock();
      return block != Blocks.block53 && block != Blocks.block105
         ? (
            block.getMaterial() == Material.material9
               ? true
               : (block == this && (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) >= 7 ? true : block.processAction15() && block.coreItemEntry.isActiveAsFlag())
         )
         : false;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.UP || super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      applyWorld(world, blockPos, new ItemStack(Items.snowball, (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) + 1, 0));
      world.matchesBlockPos8(blockPos);
      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) - 1;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      int count4 = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) - 1;
      float factor = 0.125F;
      return new AxisAlignedBB(
         (double)count + this.coordOffset,
         (double)count2 + this.coordOffset11,
         (double)count3 + this.coordOffset2,
         (double)count + this.coordOffset9,
         (double)((float)count2 + (float)count4 * factor),
         (double)count3 + this.coordOffset15
      );
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockSnowLayer() {
      super(Material.material18);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 1));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.processAction2();
   }
}
