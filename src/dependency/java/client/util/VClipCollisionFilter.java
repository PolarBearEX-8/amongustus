package client.util;

import java.util.Random;
import net.minecraft.block.AdvancedBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VClipCollisionFilter extends AdvancedBlockAdapter {
   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return world.state ? true : world.resolveTileEntity(blockPos) instanceof TileEntitySign;
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return super.processAction(world, blockPos);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.sign;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return true;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.sign;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return !this.matchesWorld3(world, blockPos) && super.processAction7(world, blockPos);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return true;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (world.state) {
         return true;
      } else {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         return tileEntity instanceof TileEntitySign ? ((TileEntitySign)tileEntity).matchesEntityPlayerByEntityPlayer(player) : false;
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntitySign();
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public VClipCollisionFilter() {
      super(Material.material);
      float factor = 0.25F;
      float factor2 = 1.0F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, factor2, 0.5F + factor);
   }
}
