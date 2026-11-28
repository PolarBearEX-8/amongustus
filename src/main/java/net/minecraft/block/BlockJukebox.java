package net.minecraft.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockJukebox extends BlockContainer {
   public static final PropertyBool HAS_RECORD;

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      if (!world.isRemote) {
         super.dropBlockAsItemWithChance(world, pos, state, f, 0);
      }
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   static {
      HAS_RECORD = PropertyBool.create("has_record");
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return (Boolean)state.getValue(HAS_RECORD);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Boolean)state.getValue(HAS_RECORD) ? 1 : 0;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{HAS_RECORD});
   }

   public void insertRecord(World world, BlockPos pos, IBlockState state, ItemStack stack) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof BlockJukebox.TileEntityJukebox) {
            ((BlockJukebox.TileEntityJukebox)tileEntity).setRecord(new ItemStack(stack.getItem(), 1, stack.getMetadata()));
            world.setBlockState(pos, state.withProperty(HAS_RECORD, Boolean.TRUE), 2);
         }
      }
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      this.dropRecord(world, pos, state);
      super.breakBlock(world, pos, state);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   private void dropRecord(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof BlockJukebox.TileEntityJukebox) {
            BlockJukebox.TileEntityJukebox tileEntityJukebox = (BlockJukebox.TileEntityJukebox)tileEntity;
            ItemStack record = tileEntityJukebox.getRecord();
            if (record != null) {
               world.playAuxSFX(1005, pos, 0);
               world.playRecord(pos, (String)null);
               tileEntityJukebox.setRecord((ItemStack)null);
               float f = 0.7F;
               double d = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5;
               double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.2 + 0.6;
               double d3 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5;
               ItemStack stack = record.copy();
               EntityItem entityItem = new EntityItem(world, (double)pos.getX() + d, (double)pos.getY() + d2, (double)pos.getZ() + d3, stack);
               entityItem.setDefaultPickupDelay();
               world.spawnEntityInWorld(entityItem);
            }
         }
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new BlockJukebox.TileEntityJukebox();
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof BlockJukebox.TileEntityJukebox) {
         ItemStack record = ((BlockJukebox.TileEntityJukebox)tileEntity).getRecord();
         if (record != null) {
            return Item.getIdFromItem(record.getItem()) + 1 - Item.getIdFromItem(Items.record_13);
         }
      }

      return 0;
   }

   public BlockJukebox() {
      super(Material.wood, MapColor.dirtColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_RECORD, Boolean.FALSE));
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if ((Boolean)state.getValue(HAS_RECORD)) {
         this.dropRecord(world, pos, state);
         state = state.withProperty(HAS_RECORD, Boolean.FALSE);
         world.setBlockState(pos, state, 2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(HAS_RECORD, n > 0);
   }


   public static class TileEntityJukebox extends TileEntity {
      private ItemStack record;

      @Override
      public void readFromNBT(NBTTagCompound tag) {
         super.readFromNBT(tag);
         if (tag.hasKey("RecordItem", 10)) {
            this.setRecord(ItemStack.loadItemStackFromNBT(tag.getCompoundTag("RecordItem")));
         } else if (tag.getInteger("Record") > 0) {
            this.setRecord(new ItemStack(Item.getItemById(tag.getInteger("Record")), 1, 0));
         }
      }

      public void setRecord(ItemStack stack) {
         this.record = stack;
         this.markDirty();
      }

      public ItemStack getRecord() {
         return this.record;
      }

      @Override
      public void writeToNBT(NBTTagCompound tag) {
         super.writeToNBT(tag);
         if (this.getRecord() != null) {
            tag.setTag("RecordItem", this.getRecord().writeToNBT(new NBTTagCompound()));
         }
      }
   }
}
