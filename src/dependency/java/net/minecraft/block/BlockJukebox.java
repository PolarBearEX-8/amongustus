package net.minecraft.block;

import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityRecordplayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockJukebox extends AdvancedBlockAdapter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("has_record");

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      if (!world.state) {
         super.applyWorld3(world, blockPos, blockState, factor, 0);
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return (Boolean)blockState.getComparable(internalImmutableSetEntry);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, count > 0);
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityRecordplayer) {
         ItemStack stack = ((TileEntityRecordplayer)tileEntity).getItemStack();
         if (stack != null) {
            return Item.getIndex(stack.getItem()) + 1 - Item.getIndex(Items.record13);
         }
      }

      return 0;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld6(world, blockPos, blockState);
      super.onBlockAdded(world, blockPos, blockState);
   }

   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, ItemStack stack) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityRecordplayer) {
            ((TileEntityRecordplayer)tileEntity).applyItemStack(new ItemStack(stack.getItem(), 1, stack.getIndexOffset()));
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 2);
         }
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityRecordplayer();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         this.applyWorld6(world, blockPos, blockState);
         IBlockState blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
         world.matchesBlockPos(blockPos, blockState2, 2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   private void applyWorld6(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntityRecordplayer) {
            TileEntityRecordplayer tileEntityRecordplayer = (TileEntityRecordplayer)tileEntity;
            ItemStack stack = tileEntityRecordplayer.getItemStack();
            if (stack != null) {
               world.applyBlockPosByBlockPos(1005, blockPos, 0);
               world.applyBlockPos(blockPos, (String)null);
               tileEntityRecordplayer.applyItemStack((ItemStack)null);
               float factor = 0.7F;
               double offset = (double)(world.random.nextFloat() * factor) + (double)(1.0F - factor) * 0.5;
               double offset2 = (double)(world.random.nextFloat() * factor) + (double)(1.0F - factor) * 0.2 + 0.6;
               double offset3 = (double)(world.random.nextFloat() * factor) + (double)(1.0F - factor) * 0.5;
               ItemStack stack2 = stack.getItemStack();
               EntityItem entityItem = new EntityItem(world, (double)blockPos.getX() + offset, (double)blockPos.getY() + offset2, (double)blockPos.getZ() + offset3, stack2);
               entityItem.updateState6();
               world.matchesEntity(entityItem);
            }
         }
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 1 : 0);
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockJukebox() {
      super(Material.material, MapColor.mapColor4);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
