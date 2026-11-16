package net.minecraft.block;

import client.setting.CombatConfigProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDldetector;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDaylightDetectorInverted extends AdvancedBlockAdapter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("power", 0, 15);
   public final boolean enabled2;

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return player.onReset() ? true : super.onBlockActivated(world, blockPos, blockState, player, enumFacing, posX, posY, posZ);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.blockDaylightDetectorInverted);
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   public void applyWorld2(World world, BlockPos blockPos) {
      if (!world.secondaryFloatArrayEntry.isEnabled()) {
         IBlockState blockState = world.getBlockState(blockPos);
         int count = world.computeCount(CombatConfigProperty.SKY, blockPos.getX(), blockPos.getY(), blockPos.getZ()) - world.getIndexOffset();
         float factor = world.computeFactor2(1.0F);
         float factor2 = factor < (float) Math.PI ? 0.0F : (float) (Math.PI * 2);
         float factor3 = factor + (factor2 - factor) * 0.2F;
         int intItem = MathHelper.getInt12(Math.round((float)count * MathHelper.cos(factor3)), 0, 15);
         if (this.enabled2) {
            intItem = 15 - intItem;
         }

         if ((Integer)blockState.getComparable(auxiliaryImmutableSetEntry) != intItem) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, intItem), 3);
         }
      }
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      if (!this.enabled2) {
         super.processAction(item, itemGroupManager, list);
      }
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Item.processAction2(Blocks.blockDaylightDetectorInverted);
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityDldetector();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (player.onReset()) {
         if (!world.state) {
            if (this.enabled2) {
               world.matchesBlockPos(
                  blockPos,
                  Blocks.blockDaylightDetectorInverted
                     .getDefaultState()
                     .resolveIBlockState(auxiliaryImmutableSetEntry, blockState.getComparable(auxiliaryImmutableSetEntry)),
                  4
               );
               Blocks.blockDaylightDetectorInverted.applyWorld2(world, blockPos);
            } else {
               world.matchesBlockPos(
                  blockPos,
                  Blocks.blockDaylightDetectorInverted2
                     .getDefaultState()
                     .resolveIBlockState(auxiliaryImmutableSetEntry, blockState.getComparable(auxiliaryImmutableSetEntry)),
                  4
               );
               Blocks.blockDaylightDetectorInverted2.applyWorld2(world, blockPos);
            }
         }

         return true;
      } else {
         return super.onBlockActivated(world, blockPos, blockState, player, enumFacing, posX, posY, posZ);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockDaylightDetectorInverted(boolean flag) {
      super(Material.material);
      this.enabled2 = flag;
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
      this.processAction(ItemGroupManager.itemGroupManager9);
      this.getBlock5(0.2F);
      this.processAction(stepManager19);
      this.setUnlocalizedName("daylightDetector");
   }
}
