package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.Random;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonExtension extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing");
   public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockPistonExtension.EnumPistonType.class);
   public static final PropertyBool SHORT = PropertyBool.create("short");

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, getFacing(n))
         .withProperty(TYPE, (n & 8) > 0 ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
   }

   public BlockPistonExtension() {
      super(Material.piston);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(TYPE, BlockPistonExtension.EnumPistonType.DEFAULT)
            .withProperty(SHORT, Boolean.FALSE)
      );
      this.setStepSound(soundTypePiston);
      this.setHardness(0.5F);
   }

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      return false;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return world.getBlockState(pos).getValue(TYPE) == BlockPistonExtension.EnumPistonType.STICKY
         ? Item.getItemFromBlock(Blocks.sticky_piston)
         : Item.getItemFromBlock(Blocks.piston);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public static EnumFacing getFacing(int n) {
      int n2 = n & 7;
      return n2 > 5 ? null : EnumFacing.getFront(n2);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      BlockPos pos2 = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
      IBlockState blockState2 = world.getBlockState(pos2);
      if (blockState2.getBlock() != Blocks.piston && blockState2.getBlock() != Blocks.sticky_piston) {
         world.setBlockToAir(pos);
      } else {
         blockState2.getBlock().onNeighborBlockChange(world, pos2, blockState2, block);
      }
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   private void applyCoreBounds(IBlockState state) {
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         switch (((EnumFacing)state.getValue(FACING))) {
            case DOWN:
               this.setBlockBounds2(0.0, 0.0, 0.0, 1.0, 0.25, 1.0);
               break;
            case UP:
               this.setBlockBounds2(0.0, 0.75, 0.0, 1.0, 1.0, 1.0);
               break;
            case NORTH:
               this.setBlockBounds2(0.0, 0.0, 0.0, 1.0, 1.0, 0.25);
               break;
            case SOUTH:
               this.setBlockBounds2(0.0, 0.0, 0.75, 1.0, 1.0, 1.0);
               break;
            case WEST:
               this.setBlockBounds2(0.0, 0.0, 0.0, 0.25, 1.0, 1.0);
               break;
            case EAST:
               this.setBlockBounds2(0.75, 0.0, 0.0, 1.0, 1.0, 1.0);
         }
      } else {
         switch (((EnumFacing)state.getValue(FACING))) {
            case DOWN:
               this.setBlockBounds(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
               break;
            case UP:
               this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
               break;
            case NORTH:
               this.setBlockBounds(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
               break;
            case SOUTH:
               this.setBlockBounds(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
               break;
            case WEST:
               this.setBlockBounds(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
               break;
            case EAST:
               this.setBlockBounds(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
         }
      }
   }

   public void applyHeadBounds(IBlockState state) {
      float f = 0.25F;
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      if (value != null) {
         if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_13)) {
            switch (value) {
               case DOWN:
                  this.setBlockBounds2(0.375, 0.25, 0.375, 0.625, 1.25, 0.625);
                  break;
               case UP:
                  this.setBlockBounds2(0.375, -0.25, 0.375, 0.625, 0.75, 0.625);
                  break;
               case NORTH:
                  this.setBlockBounds2(0.375, 0.375, 0.25, 0.625, 0.625, 1.25);
                  break;
               case SOUTH:
                  this.setBlockBounds2(0.375, 0.375, -0.25, 0.625, 0.625, 0.75);
                  break;
               case WEST:
                  this.setBlockBounds2(0.25, 0.375, 0.375, 1.25, 0.625, 0.625);
                  break;
               case EAST:
                  this.setBlockBounds2(-0.25, 0.375, 0.375, 0.75, 0.625, 0.625);
            }
         } else {
            switch (value) {
               case DOWN:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
                  break;
               case UP:
                  this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
                  break;
               case NORTH:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
                  break;
               case SOUTH:
                  this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
                  break;
               case WEST:
                  this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
                  break;
               case EAST:
                  this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, TYPE, SHORT});
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      pos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
      IBlockState blockState2 = world.getBlockState(pos);
      if ((blockState2.getBlock() == Blocks.piston || blockState2.getBlock() == Blocks.sticky_piston) && (Boolean)blockState2.getValue(BlockPistonBase.EXTENDED)) {
         blockState2.getBlock().dropBlockAsItem(world, pos, blockState2, 0);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return true;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return false;
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (player.capabilities.isCreativeMode) {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         if (value != null) {
            BlockPos pos2 = pos.offset(value.getOpposite());
            Block block = world.getBlockState(pos2).getBlock();
            if (block == Blocks.piston || block == Blocks.sticky_piston) {
               world.setBlockToAir(pos2);
            }
         }
      }

      super.onBlockHarvested(world, pos, state, player);
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.applyHeadBounds(state);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.applyCoreBounds(state);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.applyHeadBounds(world.getBlockState2(n, n2, n3));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if (state.getValue(TYPE) == BlockPistonExtension.EnumPistonType.STICKY) {
         n |= 8;
      }

      return n;
   }

public enum EnumPistonType implements IStringSerializable {
      DEFAULT("normal"),
      STICKY("sticky");

   private final String VARIANT;
   @Override
      public String getName() {
         return this.VARIANT;
      }

      @Override
      public String toString() {
         return this.VARIANT;
      }

      private EnumPistonType(String str) {
         this.VARIANT = str;
      }
   }
}
