package net.minecraft.block;

import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.VectorDispatcher;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityControl;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCommandBlock extends AdvancedBlockAdapter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("triggered");

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityControl) {
         ((TileEntityControl)tileEntity).getVectorDispatcher().applyWorldByWorld(world);
         world.applyBlockPos(blockPos, this);
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityControl) {
         VectorDispatcher vectorDispatcher = ((TileEntityControl)tileEntity).getVectorDispatcher();
         if (stack.isActive()) {
            vectorDispatcher.setLabelText2(stack.getString());
         }

         if (!world.state) {
            vectorDispatcher.setEnabled(world.getDoFireTickManager().matchesText("sendCommandFeedback"));
         }
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return world.resolveTileEntity(blockPos) instanceof TileEntityControl && player.invulnerableManager.enabled2;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, (count & 1) > 0);
   }

   @Override
   public int processAction(World world) {
      return 1;
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      return tileEntity instanceof TileEntityControl ? ((TileEntityControl)tileEntity).getVectorDispatcher().getCount() : 0;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         boolean flag = world.matchesBlockPos7(blockPos);
         boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
         if (flag && !comparable) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 4);
            world.applyBlockPos(blockPos, this, this.processAction(world));
         } else if (!flag && comparable) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 4);
         }
      }
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityControl();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      return tileEntity instanceof TileEntityControl ? ((TileEntityControl)tileEntity).getVectorDispatcher().matchesEntityPlayerByEntityPlayer(player) : false;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue = 0;
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         byteValue |= 1;
      }

      return byteValue;
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockCommandBlock() {
      super(Material.material5, MapColor.mapColor36);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
   }
}
