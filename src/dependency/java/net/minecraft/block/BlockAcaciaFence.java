package net.minecraft.block;

import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemLead;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAcaciaFence extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry4;
   public static final InternalImmutableSetEntry internalImmutableSetEntry2;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry3;

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      boolean flag = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 - 1);
      boolean flag2 = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 + 1);
      boolean flag3 = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count - 1, count2, count3);
      boolean flag4 = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count + 1, count2, count3);
      float factor = 0.375F;
      float factor2 = 0.625F;
      float factor3 = 0.375F;
      float factor4 = 0.625F;
      if (flag) {
         factor3 = 0.0F;
      }

      if (flag2) {
         factor4 = 1.0F;
      }

      if (flag3) {
         factor = 0.0F;
      }

      if (flag4) {
         factor2 = 1.0F;
      }

      this.setBlockBounds(factor, 0.0F, factor3, factor2, 1.0F, factor4);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return world.state ? true : ItemLead.matchesEntityPlayerByEntityPlayer(player, world, blockPos);
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry4, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 - 1))
         .resolveIBlockState(internalImmutableSetEntry2, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count + 1, count2, count3))
         .resolveIBlockState(internalImmutableSetEntry, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 + 1))
         .resolveIBlockState(internalImmutableSetEntry3, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count - 1, count2, count3));
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return false;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      boolean flag = this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1);
      boolean flag2 = this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1);
      boolean flag3 = this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX() - 1, blockPos.getY(), blockPos.getZ());
      boolean flag4 = this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX() + 1, blockPos.getY(), blockPos.getZ());
      float factor = 0.375F;
      float factor2 = 0.625F;
      float factor3 = 0.375F;
      float factor4 = 0.625F;
      if (flag) {
         factor3 = 0.0F;
      }

      if (flag2) {
         factor4 = 1.0F;
      }

      if (flag || flag2) {
         this.setBlockBounds(factor, 0.0F, factor3, factor2, 1.5F, factor4);
         super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      }

      float factor5 = 0.375F;
      float factor6 = 0.625F;
      if (flag3) {
         factor = 0.0F;
      }

      if (flag4) {
         factor2 = 1.0F;
      }

      if (flag3 || flag4 || !flag && !flag2) {
         this.setBlockBounds(factor, 0.0F, factor5, factor2, 1.5F, factor6);
         super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      }

      if (flag) {
         factor5 = 0.0F;
      }

      if (flag2) {
         factor6 = 1.0F;
      }

      this.setBlockBounds(factor, 0.0F, factor5, factor2, 1.0F, factor6);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return world.state ? true : ItemLead.matchesEntityPlayer3(player, world, blockPos);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return false;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return true;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this, new IProperty[]{internalImmutableSetEntry4, internalImmutableSetEntry2, internalImmutableSetEntry3, internalImmutableSetEntry}
      );
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0;
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      Block block = iBlockAccess.getIBlockState(count, count2, count3).getBlock();
      return block == Blocks.block145
         ? false
         : (
            (!(block instanceof BlockAcaciaFence) || block.coreItemEntry != this.coreItemEntry) && !(block instanceof BlockAcaciaFenceGate)
               ? (block.coreItemEntry.isActive3() && block.processAction14() ? block.coreItemEntry != Material.material13 : false)
               : true
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

   public BlockAcaciaFence(Material material) {
      this(material, material.getMapColor());
   }

   public BlockAcaciaFence(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
      );
      this.processAction(ItemGroupManager.itemGroupManager8);
   }

   static {
      String[] textArray = new String[]{"west", "east", "north", "south"};
      internalImmutableSetEntry4 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[2]);
      internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[3]);
      internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
   }
}
