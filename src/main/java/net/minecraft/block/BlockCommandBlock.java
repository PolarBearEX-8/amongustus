package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCommandBlock extends BlockContainer {
   public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(TRIGGERED, Boolean.FALSE);
   }

   @Override
   public int tickRate(World world) {
      return 1;
   }

   public BlockCommandBlock() {
      super(Material.iron, MapColor.adobeColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(TRIGGERED, Boolean.FALSE));
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(TRIGGERED, (n & 1) > 0);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      byte b = 0;
      if ((Boolean)state.getValue(TRIGGERED)) {
         b |= 1;
      }

      return b;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity instanceof TileEntityCommandBlock ? ((TileEntityCommandBlock)tileEntity).getCommandBlockLogic().tryOpenEditCommandBlock(player) : false;
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity instanceof TileEntityCommandBlock ? ((TileEntityCommandBlock)tileEntity).getCommandBlockLogic().getSuccessCount() : 0;
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{TRIGGERED});
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityCommandBlock) {
         CommandBlockLogic commandBlockLogic = ((TileEntityCommandBlock)tileEntity).getCommandBlockLogic();
         if (stack.hasDisplayName()) {
            commandBlockLogic.setName(stack.getDisplayName());
         }

         if (!world.isRemote) {
            commandBlockLogic.setTrackOutput(world.getGameRules().getBoolean("sendCommandFeedback"));
         }
      }
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityCommandBlock) {
         ((TileEntityCommandBlock)tileEntity).getCommandBlockLogic().trigger(world);
         world.updateComparatorOutputLevel(pos, this);
      }
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         boolean isBlockPowered = world.isBlockPowered(pos);
         boolean value = (Boolean)state.getValue(TRIGGERED);
         if (isBlockPowered && !value) {
            world.setBlockState(pos, state.withProperty(TRIGGERED, Boolean.TRUE), 4);
            world.scheduleUpdate(pos, this, this.tickRate(world));
         } else if (!isBlockPowered && value) {
            world.setBlockState(pos, state.withProperty(TRIGGERED, Boolean.FALSE), 4);
         }
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityCommandBlock();
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return world.getTileEntity(pos) instanceof TileEntityCommandBlock && player.capabilities.isCreativeMode;
   }
}
