package net.minecraft.block;

import net.minecraft.init.Blocks;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.World;

public class BlockBeacon extends BlockContainer {
   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityBeacon();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityBeacon) {
            player.displayGUIChest((TileEntityBeacon)tileEntity);
            player.triggerAchievement(StatList.beaconInteraction);
         }
      }

      return true;
   }

   public BlockBeacon() {
      super(Material.glass, MapColor.diamondColor);
      this.setHardness(3.0F);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      super.onBlockPlacedBy(world, pos, state, entity, stack);
      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityBeacon) {
            ((TileEntityBeacon)tileEntity).setName(stack.getDisplayName());
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   public static void updateColorAsync(World world, BlockPos pos) {
      HttpUtil.DOWNLOADER_EXECUTOR.submit(new BlockBeacon.WorkerTask(world, pos));
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityBeacon) {
         ((TileEntityBeacon)tileEntity).updateBeacon();
         world.addBlockEvent(pos, this, 1, 0);
      }
   }


   public static final class WorkerTask implements Runnable {
      public final World worldIn;
      public final BlockPos glassPos;

      public WorkerTask(World world, BlockPos pos) {
         this.worldIn = world;
         this.glassPos = pos;
      }

      @Override
      public void run() {
         Chunk chunkFromBlockCoords = this.worldIn.getChunkFromBlockCoords(this.glassPos);

         for (int i = this.glassPos.getY() - 1; i >= 0; i--) {
            BlockPos pos = new BlockPos(this.glassPos.getX(), i, this.glassPos.getZ());
            if (!chunkFromBlockCoords.canSeeSky(pos.getX(), pos.getY(), pos.getZ())) {
               break;
            }

            if (this.worldIn.getBlockState(pos).getBlock() == Blocks.beacon) {
               WorldServer worldIn2 = (WorldServer)this.worldIn;
               World worldIn3 = this.worldIn;
               worldIn2.addScheduledTask(() -> {
                  TileEntity tileEntity = worldIn3.getTileEntity(pos);
                  if (tileEntity instanceof TileEntityBeacon) {
                     ((TileEntityBeacon)tileEntity).updateBeacon();
                     worldIn3.addBlockEvent(pos, Blocks.beacon, 1, 0);
                  }
               });
            }
         }
      }
   }
}
