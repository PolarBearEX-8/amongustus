package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.BezierFormatter;
import client.util.GETManager;
import client.util.IBlockState;
import client.util.StatCraftItemManager;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockBeacon extends AdvancedBlockAdapter {
   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      super.processAction(world, blockPos, blockState, livingEntity, stack);
      if (stack.isActive()) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityBeacon) {
            ((TileEntityBeacon)tileEntity).setLabelText(stack.getString());
         }
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityBeacon) {
         ((TileEntityBeacon)tileEntity).updateState7();
         world.applyBlockPosByBlockPos(blockPos, this, 1, 0);
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityBeacon) {
            player.applyIInventory((TileEntityBeacon)tileEntity);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager43);
         }
      }

      return true;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityBeacon();
   }

   @Override
   public int processAction13() {
      return 3;
   }

   public static void submit(World world, BlockPos blockPos) {
      GETManager.listeningExecutorService.submit(new BezierFormatter(world, blockPos));
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockBeacon() {
      super(Material.material26, MapColor.mapColor);
      this.getBlock5(3.0F);
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}
