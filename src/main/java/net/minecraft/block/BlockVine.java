package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {
   public static final PropertyBool UP = PropertyBool.create("up");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   public static final PropertyBool[] ALL_FACES = new PropertyBool[]{UP, NORTH, SOUTH, WEST, EAST};

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      IBlockState state = this.getDefaultState()
         .withProperty(UP, Boolean.FALSE)
         .withProperty(NORTH, Boolean.FALSE)
         .withProperty(EAST, Boolean.FALSE)
         .withProperty(SOUTH, Boolean.FALSE)
         .withProperty(WEST, Boolean.FALSE);
      return facing.getAxis().isHorizontal() ? state.withProperty(getPropertyFor(facing.getOpposite()), Boolean.TRUE) : state;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   private boolean recheckGrownSides(World world, BlockPos pos, IBlockState state) {
      IBlockState state2 = state;

      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         PropertyBool propertyFor = getPropertyFor(facing);
         if ((Boolean)state.getValue(propertyFor) && !this.canPlaceOn(world.getBlockState(pos.offset(facing)).getBlock())) {
            IBlockState blockState2 = world.getBlockState(pos.up());
            if (blockState2.getBlock() != this || !(Boolean)blockState2.getValue(propertyFor)) {
               state = state.withProperty(propertyFor, Boolean.FALSE);
            }
         }
      }

      if (getNumGrownFaces(state) == 0) {
         return false;
      } else {
         if (state2 != state) {
            world.setBlockState(pos, state, 2);
         }

         return true;
      }
   }

   @Override
   public int getRenderColor(IBlockState state) {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   @Override
   public boolean isReplaceable(World world, BlockPos pos) {
      return true;
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return world.getBiomeGenForCoords(pos).getFoliageColorAtPos(pos);
   }

   private boolean canPlaceOn(Block block) {
      return block.isFullCube() && block.blockMaterial.blocksMovement();
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      byte b = 0;
      if ((Boolean)state.getValue(SOUTH)) {
         b |= 1;
      }

      if ((Boolean)state.getValue(WEST)) {
         b |= 2;
      }

      if ((Boolean)state.getValue(NORTH)) {
         b |= 4;
      }

      if ((Boolean)state.getValue(EAST)) {
         b |= 8;
      }

      return b;
   }

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      switch (facing) {
         case UP:
            return this.canPlaceOn(world.getBlockState(pos.up()).getBlock());
         case NORTH:
         case SOUTH:
         case EAST:
         case WEST:
            return this.canPlaceOn(world.getBlockState(pos.offset(facing.getOpposite())).getBlock());
         default:
            return false;
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      float f = 0.0625F;
      float f2 = 1.0F;
      float f3 = 1.0F;
      float f4 = 1.0F;
      float f5 = 0.0F;
      float f6 = 0.0F;
      float f7 = 0.0F;
      boolean flag = false;
      if ((Boolean)world.getBlockState2(n, n2, n3).getValue(WEST)) {
         f5 = Math.max(f5, 0.0625F);
         f2 = 0.0F;
         f3 = 0.0F;
         f6 = 1.0F;
         f4 = 0.0F;
         f7 = 1.0F;
         flag = true;
      }

      if ((Boolean)world.getBlockState2(n, n2, n3).getValue(EAST)) {
         f2 = Math.min(f2, 0.9375F);
         f5 = 1.0F;
         f3 = 0.0F;
         f6 = 1.0F;
         f4 = 0.0F;
         f7 = 1.0F;
         flag = true;
      }

      if ((Boolean)world.getBlockState2(n, n2, n3).getValue(NORTH)) {
         f7 = Math.max(f7, 0.0625F);
         f4 = 0.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         f3 = 0.0F;
         f6 = 1.0F;
         flag = true;
      }

      if ((Boolean)world.getBlockState2(n, n2, n3).getValue(SOUTH)) {
         f4 = Math.min(f4, 0.9375F);
         f7 = 1.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         f3 = 0.0F;
         f6 = 1.0F;
         flag = true;
      }

      if (!flag && this.canPlaceOn(world.getBlockState2(n, n2 + 1, n3).getBlock())) {
         f3 = Math.min(f3, 0.9375F);
         f6 = 1.0F;
         f2 = 0.0F;
         f5 = 1.0F;
         f4 = 0.0F;
         f7 = 1.0F;
      }

      this.setBlockBounds(f2, f3, f4, f5, f6, f7);
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   public static PropertyBool getPropertyFor(EnumFacing facing) {
      switch (facing) {
         case UP:
            return UP;
         case NORTH:
            return NORTH;
         case SOUTH:
            return SOUTH;
         case EAST:
            return EAST;
         case WEST:
            return WEST;
         default:
            throw new IllegalArgumentException(facing + " is an invalid choice");
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   public static int getNumGrownFaces(IBlockState state) {
      int n = 0;

      for (PropertyBool propertyBool : ALL_FACES) {
         if ((Boolean)state.getValue(propertyBool)) {
            n++;
         }
      }

      return n;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote && world.rand.nextInt(4) == 0) {
         byte b = 4;
         int n = 5;
         boolean flag = false;

         outerLoop:
         for (int i = -b; i <= b; i++) {
            for (int j = -b; j <= b; j++) {
               for (int k = -1; k <= 1; k++) {
                  if (world.getBlockState2(pos.getX() + i, pos.getY() + k, pos.getZ() + j).getBlock() == this) {
                     if (--n <= 0) {
                        flag = true;
                        break outerLoop;
                     }
                  }
               }
            }
         }

         EnumFacing facing = EnumFacing.random(rand);
         BlockPos pos2 = pos.up();
         if (facing == EnumFacing.UP && pos.getY() < 255 && world.isAirBlock(pos2)) {
            if (!flag) {
               IBlockState state2 = state;

               for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
                  if (rand.nextBoolean() || !this.canPlaceOn(world.getBlockState(pos2.offset(facing2)).getBlock())) {
                     state2 = state2.withProperty(getPropertyFor(facing2), Boolean.FALSE);
                  }
               }

               if ((Boolean)state2.getValue(NORTH) || (Boolean)state2.getValue(EAST) || (Boolean)state2.getValue(SOUTH) || (Boolean)state2.getValue(WEST)) {
                  world.setBlockState(pos2, state2, 2);
               }
            }
         } else if (!facing.getAxis().isHorizontal() || (Boolean)state.getValue(getPropertyFor(facing))) {
            if (pos.getY() > 1) {
               BlockPos pos3 = pos.down();
               IBlockState blockState2 = world.getBlockState(pos3);
               Block block = blockState2.getBlock();
               if (block.blockMaterial == Material.air) {
                  IBlockState state3 = state;

                  for (EnumFacing facing3 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
                     if (rand.nextBoolean()) {
                        state3 = state3.withProperty(getPropertyFor(facing3), Boolean.FALSE);
                     }
                  }

                  if ((Boolean)state3.getValue(NORTH) || (Boolean)state3.getValue(EAST) || (Boolean)state3.getValue(SOUTH) || (Boolean)state3.getValue(WEST)) {
                     world.setBlockState(pos3, state3, 2);
                  }
               } else if (block == this) {
                  IBlockState state4 = blockState2;

                  for (EnumFacing facing4 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
                     PropertyBool propertyFor = getPropertyFor(facing4);
                     if (rand.nextBoolean() && (Boolean)state.getValue(propertyFor)) {
                        state4 = state4.withProperty(propertyFor, Boolean.TRUE);
                     }
                  }

                  if ((Boolean)state4.getValue(NORTH) || (Boolean)state4.getValue(EAST) || (Boolean)state4.getValue(SOUTH) || (Boolean)state4.getValue(WEST)) {
                     world.setBlockState(pos3, state4, 2);
                  }
               }
            }
         } else if (!flag) {
            BlockPos pos4 = pos.offset(facing);
            Block block2 = world.getBlockState(pos4).getBlock();
            if (block2.blockMaterial == Material.air) {
               EnumFacing facing5 = facing.rotateY();
               EnumFacing facing6 = facing.rotateYCCW();
               boolean value = (Boolean)state.getValue(getPropertyFor(facing5));
               boolean value2 = (Boolean)state.getValue(getPropertyFor(facing6));
               BlockPos pos5 = pos4.offset(facing5);
               BlockPos pos6 = pos4.offset(facing6);
               if (value && this.canPlaceOn(world.getBlockState(pos5).getBlock())) {
                  world.setBlockState(pos4, this.getDefaultState().withProperty(getPropertyFor(facing5), Boolean.TRUE), 2);
               } else if (value2 && this.canPlaceOn(world.getBlockState(pos6).getBlock())) {
                  world.setBlockState(pos4, this.getDefaultState().withProperty(getPropertyFor(facing6), Boolean.TRUE), 2);
               } else if (value && world.isAirBlock(pos5) && this.canPlaceOn(world.getBlockState(pos.offset(facing5)).getBlock())) {
                  world.setBlockState(pos5, this.getDefaultState().withProperty(getPropertyFor(facing.getOpposite()), Boolean.TRUE), 2);
               } else if (value2 && world.isAirBlock(pos6) && this.canPlaceOn(world.getBlockState(pos.offset(facing6)).getBlock())) {
                  world.setBlockState(pos6, this.getDefaultState().withProperty(getPropertyFor(facing.getOpposite()), Boolean.TRUE), 2);
               } else if (this.canPlaceOn(world.getBlockState(pos4.up()).getBlock())) {
                  world.setBlockState(pos4, this.getDefaultState(), 2);
               }
            } else if (block2.blockMaterial.isOpaque() && block2.isFullCube()) {
               world.setBlockState(pos, state.withProperty(getPropertyFor(facing), Boolean.TRUE), 2);
            }
         }
      }
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
         player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         spawnAsEntity(world, pos, new ItemStack(Blocks.vine, 1, 0));
      } else {
         super.harvestBlock(world, player, pos, state, tileEntity);
      }
   }

   @Override
   public int getBlockColor() {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(UP, world.getBlockState2(n, n2 + 1, n3).getBlock().isBlockNormalCube());
   }

   public BlockVine() {
      super(Material.vine);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(UP, Boolean.FALSE)
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
      );
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{UP, NORTH, EAST, SOUTH, WEST});
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote && !this.recheckGrownSides(world, pos, state)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(SOUTH, (n & 1) > 0)
         .withProperty(WEST, (n & 2) > 0)
         .withProperty(NORTH, (n & 4) > 0)
         .withProperty(EAST, (n & 8) > 0);
   }

}
