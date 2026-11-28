package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStairs extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyEnum HALF = PropertyEnum.create("half", BlockStairs.EnumHalf.class);
   public static final PropertyEnum SHAPE = PropertyEnum.create("shape", BlockStairs.EnumShape.class);
   private static final int[][] RAYTRACE_INDEX_MAP = new int[][]{{4, 5}, {5, 7}, {6, 7}, {4, 6}, {0, 1}, {1, 3}, {2, 3}, {0, 2}};
   private final Block modelBlock;
   private final IBlockState modelState;
   private boolean hasRaytraced;
   private int rayTracePass;

   public static boolean isSameStair(IBlockAccess world, int n, int n2, int n3, IBlockState state) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      return isBlockStairs(blockState2.getBlock()) && blockState2.getValue(HALF) == state.getValue(HALF) && blockState2.getValue(FACING) == state.getValue(FACING);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.onNeighborBlockChange(world, pos, this.modelState, Blocks.air);
      this.modelBlock.onBlockAdded(world, pos, this.modelState);
   }

   public int getInnerCornerShape(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      EnumFacing value = (EnumFacing)blockState2.getValue(FACING);
      BlockStairs.EnumHalf value2 = (BlockStairs.EnumHalf)blockState2.getValue(HALF);
      boolean flag = value2 == BlockStairs.EnumHalf.TOP;
      if (value == EnumFacing.EAST) {
         IBlockState blockState22 = world.getBlockState2(n - 1, n2, n3);
         if (isBlockStairs(blockState22.getBlock()) && value2 == blockState22.getValue(HALF)) {
            EnumFacing value3 = (EnumFacing)blockState22.getValue(FACING);
            if (value3 == EnumFacing.NORTH && !isSameStair(world, n, n2, n3 - 1, blockState2)) {
               return flag ? 1 : 2;
            }

            if (value3 == EnumFacing.SOUTH && !isSameStair(world, n, n2, n3 + 1, blockState2)) {
               return flag ? 2 : 1;
            }
         }
      } else if (value == EnumFacing.WEST) {
         IBlockState blockState23 = world.getBlockState2(n + 1, n2, n3);
         if (isBlockStairs(blockState23.getBlock()) && value2 == blockState23.getValue(HALF)) {
            EnumFacing value4 = (EnumFacing)blockState23.getValue(FACING);
            if (value4 == EnumFacing.NORTH && !isSameStair(world, n, n2, n3 - 1, blockState2)) {
               return flag ? 2 : 1;
            }

            if (value4 == EnumFacing.SOUTH && !isSameStair(world, n, n2, n3 + 1, blockState2)) {
               return flag ? 1 : 2;
            }
         }
      } else if (value == EnumFacing.SOUTH) {
         IBlockState blockState24 = world.getBlockState2(n, n2, n3 - 1);
         if (isBlockStairs(blockState24.getBlock()) && value2 == blockState24.getValue(HALF)) {
            EnumFacing value5 = (EnumFacing)blockState24.getValue(FACING);
            if (value5 == EnumFacing.WEST && !isSameStair(world, n - 1, n2, n3, blockState2)) {
               return flag ? 2 : 1;
            }

            if (value5 == EnumFacing.EAST && !isSameStair(world, n + 1, n2, n3, blockState2)) {
               return flag ? 1 : 2;
            }
         }
      } else if (value == EnumFacing.NORTH) {
         IBlockState blockState25 = world.getBlockState2(n, n2, n3 + 1);
         if (isBlockStairs(blockState25.getBlock()) && value2 == blockState25.getValue(HALF)) {
            EnumFacing value6 = (EnumFacing)blockState25.getValue(FACING);
            if (value6 == EnumFacing.WEST && !isSameStair(world, n - 1, n2, n3, blockState2)) {
               return flag ? 1 : 2;
            }

            if (value6 == EnumFacing.EAST && !isSameStair(world, n + 1, n2, n3, blockState2)) {
               return flag ? 2 : 1;
            }
         }
      }

      return 0;
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.setBaseCollisionBounds(world, pos);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      boolean flag = this.setStepCollisionBounds(world, pos.getX(), pos.getY(), pos.getZ());
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      if (flag && this.setInnerCornerCollisionBounds(world, pos)) {
         super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      }

      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public int getOuterCornerShape(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      EnumFacing value = (EnumFacing)blockState2.getValue(FACING);
      BlockStairs.EnumHalf value2 = (BlockStairs.EnumHalf)blockState2.getValue(HALF);
      boolean flag = value2 == BlockStairs.EnumHalf.TOP;
      if (value == EnumFacing.EAST) {
         IBlockState blockState22 = world.getBlockState2(n + 1, n2, n3);
         if (isBlockStairs(blockState22.getBlock()) && value2 == blockState22.getValue(HALF)) {
            EnumFacing value3 = (EnumFacing)blockState22.getValue(FACING);
            if (value3 == EnumFacing.NORTH && !isSameStair(world, n, n2, n3 + 1, blockState2)) {
               return flag ? 1 : 2;
            }

            if (value3 == EnumFacing.SOUTH && !isSameStair(world, n, n2, n3 - 1, blockState2)) {
               return flag ? 2 : 1;
            }
         }
      } else if (value == EnumFacing.WEST) {
         IBlockState blockState23 = world.getBlockState2(n - 1, n2, n3);
         if (isBlockStairs(blockState23.getBlock()) && value2 == blockState23.getValue(HALF)) {
            EnumFacing value4 = (EnumFacing)blockState23.getValue(FACING);
            if (value4 == EnumFacing.NORTH && !isSameStair(world, n, n2, n3 + 1, blockState2)) {
               return flag ? 2 : 1;
            }

            if (value4 == EnumFacing.SOUTH && !isSameStair(world, n, n2, n3 - 1, blockState2)) {
               return flag ? 1 : 2;
            }
         }
      } else if (value == EnumFacing.SOUTH) {
         IBlockState blockState24 = world.getBlockState2(n, n2, n3 + 1);
         if (isBlockStairs(blockState24.getBlock()) && value2 == blockState24.getValue(HALF)) {
            EnumFacing value5 = (EnumFacing)blockState24.getValue(FACING);
            if (value5 == EnumFacing.WEST && !isSameStair(world, n + 1, n2, n3, blockState2)) {
               return flag ? 2 : 1;
            }

            if (value5 == EnumFacing.EAST && !isSameStair(world, n - 1, n2, n3, blockState2)) {
               return flag ? 1 : 2;
            }
         }
      } else if (value == EnumFacing.NORTH) {
         IBlockState blockState25 = world.getBlockState2(n, n2, n3 - 1);
         if (isBlockStairs(blockState25.getBlock()) && value2 == blockState25.getValue(HALF)) {
            EnumFacing value6 = (EnumFacing)blockState25.getValue(FACING);
            if (value6 == EnumFacing.WEST && !isSameStair(world, n + 1, n2, n3, blockState2)) {
               return flag ? 1 : 2;
            }

            if (value6 == EnumFacing.EAST && !isSameStair(world, n - 1, n2, n3, blockState2)) {
               return flag ? 2 : 1;
            }
         }
      }

      return 0;
   }

   public static boolean isSameStair(IBlockAccess world, BlockPos pos, IBlockState state) {
      IBlockState blockState2 = world.getBlockState(pos);
      return isBlockStairs(blockState2.getBlock()) && blockState2.getValue(HALF) == state.getValue(HALF) && blockState2.getValue(FACING) == state.getValue(FACING);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
      this.modelBlock.onEntityCollidedWithBlock(world, pos, entity);
   }

   public boolean setInnerCornerCollisionBounds(IBlockAccess world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      EnumFacing value = (EnumFacing)blockState2.getValue(FACING);
      BlockStairs.EnumHalf value2 = (BlockStairs.EnumHalf)blockState2.getValue(HALF);
      boolean flag = value2 == BlockStairs.EnumHalf.TOP;
      float f = 0.5F;
      float f2 = 1.0F;
      if (flag) {
         f = 0.0F;
         f2 = 0.5F;
      }

      float f3 = 0.0F;
      float f4 = 0.5F;
      float f5 = 0.5F;
      float f6 = 1.0F;
      boolean flag2 = false;
      if (value == EnumFacing.EAST) {
         IBlockState blockState3 = world.getBlockState(pos.west());
         if (isBlockStairs(blockState3.getBlock()) && value2 == blockState3.getValue(HALF)) {
            EnumFacing value3 = (EnumFacing)blockState3.getValue(FACING);
            if (value3 == EnumFacing.NORTH && !isSameStair(world, pos.north(), blockState2)) {
               f5 = 0.0F;
               f6 = 0.5F;
               flag2 = true;
            } else if (value3 == EnumFacing.SOUTH && !isSameStair(world, pos.south(), blockState2)) {
               f5 = 0.5F;
               f6 = 1.0F;
               flag2 = true;
            }
         }
      } else if (value == EnumFacing.WEST) {
         IBlockState blockState4 = world.getBlockState(pos.east());
         if (isBlockStairs(blockState4.getBlock()) && value2 == blockState4.getValue(HALF)) {
            f3 = 0.5F;
            f4 = 1.0F;
            EnumFacing value4 = (EnumFacing)blockState4.getValue(FACING);
            if (value4 == EnumFacing.NORTH && !isSameStair(world, pos.north(), blockState2)) {
               f5 = 0.0F;
               f6 = 0.5F;
               flag2 = true;
            } else if (value4 == EnumFacing.SOUTH && !isSameStair(world, pos.south(), blockState2)) {
               f5 = 0.5F;
               f6 = 1.0F;
               flag2 = true;
            }
         }
      } else if (value == EnumFacing.SOUTH) {
         IBlockState blockState5 = world.getBlockState(pos.north());
         if (isBlockStairs(blockState5.getBlock()) && value2 == blockState5.getValue(HALF)) {
            f5 = 0.0F;
            f6 = 0.5F;
            EnumFacing value5 = (EnumFacing)blockState5.getValue(FACING);
            if (value5 == EnumFacing.WEST && !isSameStair(world, pos.west(), blockState2)) {
               flag2 = true;
            } else if (value5 == EnumFacing.EAST && !isSameStair(world, pos.east(), blockState2)) {
               f3 = 0.5F;
               f4 = 1.0F;
               flag2 = true;
            }
         }
      } else if (value == EnumFacing.NORTH) {
         IBlockState blockState6 = world.getBlockState(pos.south());
         if (isBlockStairs(blockState6.getBlock()) && value2 == blockState6.getValue(HALF)) {
            EnumFacing value6 = (EnumFacing)blockState6.getValue(FACING);
            if (value6 == EnumFacing.WEST && !isSameStair(world, pos.west(), blockState2)) {
               flag2 = true;
            } else if (value6 == EnumFacing.EAST && !isSameStair(world, pos.east(), blockState2)) {
               f3 = 0.5F;
               f4 = 1.0F;
               flag2 = true;
            }
         }
      }

      if (flag2) {
         this.setBlockBounds(f3, f, f5, f4, f2, f6);
      }

      return flag2;
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World world, int n, int n2, int n3, double d, double d2, double d3, double d4, double d5, double d6) {
      MovingObjectPosition[] hits = new MovingObjectPosition[8];
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      int horizontalIndex = ((EnumFacing)blockState2.getValue(FACING)).getHorizontalIndex();
      boolean flag = blockState2.getValue(HALF) == BlockStairs.EnumHalf.TOP;
      int[] ints = RAYTRACE_INDEX_MAP[horizontalIndex + (flag ? 4 : 0)];
      this.hasRaytraced = true;

      for (int i = 0; i < 8; i++) {
         this.rayTracePass = i;
         if (Arrays.binarySearch(ints, i) < 0) {
            hits[i] = super.collisionRayTrace(world, n, n2, n3, d, d2, d3, d4, d5, d6);
         }
      }

      for (int n4 : ints) {
         hits[n4] = null;
      }

      MovingObjectPosition hit = null;
      double d7 = 0.0;

      for (MovingObjectPosition hit2 : hits) {
         if (hit2 != null) {
            double d8 = hit2.hitVec.squareDistanceTo(d6, d3, d4);
            if (d8 > d7) {
               hit = hit2;
               d7 = d8;
            }
         }
      }

      return hit;
   }

   public boolean canModelBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return this.modelBlock.canBlockBeActivated(world, pos, this.modelState, player, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public boolean canCollideCheck(IBlockState state, boolean flag) {
      return this.modelBlock.canCollideCheck(state, flag);
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return this.modelBlock.getMapColor(this.modelState);
   }

   @Override
   public int tickRate(World world) {
      return this.modelBlock.tickRate(world);
   }

   @Override
   public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
      this.modelBlock.onBlockDestroyedByExplosion(world, pos, explosion);
   }

   public void setBaseCollisionBounds(IBlockAccess world, BlockPos pos) {
      if (world.getBlockState(pos).getValue(HALF) == BlockStairs.EnumHalf.TOP) {
         this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   @Override
   public float getExplosionResistance(Entity entity) {
      return this.modelBlock.getExplosionResistance(entity);
   }

   @Override
   public int getMixedBrightnessForBlock(IBlockAccess world, int n, int n2, int n3) {
      return this.modelBlock.getMixedBrightnessForBlock(world, n, n2, n3);
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      if (this.setStepCollisionBounds(world, n, n2, n3)) {
         switch (this.getInnerCornerShape(world, n, n2, n3)) {
            case 0:
               state = state.withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
               break;
            case 1:
               state = state.withProperty(SHAPE, BlockStairs.EnumShape.INNER_RIGHT);
               break;
            case 2:
               state = state.withProperty(SHAPE, BlockStairs.EnumShape.INNER_LEFT);
         }
      } else {
         switch (this.getOuterCornerShape(world, n, n2, n3)) {
            case 0:
               state = state.withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
               break;
            case 1:
               state = state.withProperty(SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);
               break;
            case 2:
               state = state.withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
         }
      }

      return state;
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      this.modelBlock.randomDisplayTick(world, pos, state, rand);
   }

   @Override
   public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
      this.modelBlock.onBlockDestroyedByPlayer(world, pos, state);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      byte b = 0;
      if (state.getValue(HALF) == BlockStairs.EnumHalf.TOP) {
         b |= 4;
      }

      return b | 5 - ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   @Override
   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
      this.modelBlock.onBlockClicked(world, pos, player);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(HALF, (n & 4) > 0 ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM).withProperty(FACING, EnumFacing.getFront(5 - (n & 3)));
   }

   public static boolean isBlockStairs(Block block) {
      return block instanceof BlockStairs;
   }

   @Override
   public boolean canCollideCheck(IBlockState state, boolean flag, boolean flag2) {
      return this.modelBlock.canCollideCheck(state, flag);
   }

   @Override
   public Vec3 modifyAcceleration(World world, BlockPos pos, Entity entity, Vec3 vec) {
      return this.modelBlock.modifyAcceleration(world, pos, entity, vec);
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      return this.modelBlock.getSelectedBoundingBox(world, pos);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return this.modelBlock.canPlaceBlockAt(world, pos);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, HALF, SHAPE});
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public boolean setStepCollisionBounds(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      EnumFacing value = (EnumFacing)blockState2.getValue(FACING);
      BlockStairs.EnumHalf value2 = (BlockStairs.EnumHalf)blockState2.getValue(HALF);
      boolean flag = value2 == BlockStairs.EnumHalf.TOP;
      float f = 0.5F;
      float f2 = 1.0F;
      if (flag) {
         f = 0.0F;
         f2 = 0.5F;
      }

      float f3 = 0.0F;
      float f4 = 1.0F;
      float f5 = 0.0F;
      float f6 = 0.5F;
      boolean flag2 = true;
      if (value == EnumFacing.EAST) {
         f3 = 0.5F;
         f6 = 1.0F;
         IBlockState blockState22 = world.getBlockState2(n + 1, n2, n3);
         if (isBlockStairs(blockState22.getBlock()) && value2 == blockState22.getValue(HALF)) {
            EnumFacing value3 = (EnumFacing)blockState22.getValue(FACING);
            if (value3 == EnumFacing.NORTH && !isSameStair(world, n, n2, n3 + 1, blockState2)) {
               f6 = 0.5F;
               flag2 = false;
            } else if (value3 == EnumFacing.SOUTH && !isSameStair(world, n, n2, n3 - 1, blockState2)) {
               f5 = 0.5F;
               flag2 = false;
            }
         }
      } else if (value == EnumFacing.WEST) {
         f4 = 0.5F;
         f6 = 1.0F;
         IBlockState blockState23 = world.getBlockState2(n - 1, n2, n3);
         if (isBlockStairs(blockState23.getBlock()) && value2 == blockState23.getValue(HALF)) {
            EnumFacing value4 = (EnumFacing)blockState23.getValue(FACING);
            if (value4 == EnumFacing.NORTH && !isSameStair(world, n, n2, n3 + 1, blockState2)) {
               f6 = 0.5F;
               flag2 = false;
            } else if (value4 == EnumFacing.SOUTH && !isSameStair(world, n, n2, n3 - 1, blockState2)) {
               f5 = 0.5F;
               flag2 = false;
            }
         }
      } else if (value == EnumFacing.SOUTH) {
         f5 = 0.5F;
         f6 = 1.0F;
         IBlockState blockState24 = world.getBlockState2(n, n2, n3 + 1);
         if (isBlockStairs(blockState24.getBlock()) && value2 == blockState24.getValue(HALF)) {
            EnumFacing value5 = (EnumFacing)blockState24.getValue(FACING);
            if (value5 == EnumFacing.WEST && !isSameStair(world, n + 1, n2, n3, blockState2)) {
               f4 = 0.5F;
               flag2 = false;
            } else if (value5 == EnumFacing.EAST && !isSameStair(world, n - 1, n2, n3, blockState2)) {
               f3 = 0.5F;
               flag2 = false;
            }
         }
      } else if (value == EnumFacing.NORTH) {
         IBlockState blockState25 = world.getBlockState2(n, n2, n3 - 1);
         if (isBlockStairs(blockState25.getBlock()) && value2 == blockState25.getValue(HALF)) {
            EnumFacing value6 = (EnumFacing)blockState25.getValue(FACING);
            if (value6 == EnumFacing.WEST && !isSameStair(world, n + 1, n2, n3, blockState2)) {
               f4 = 0.5F;
               flag2 = false;
            } else if (value6 == EnumFacing.EAST && !isSameStair(world, n - 1, n2, n3, blockState2)) {
               f3 = 0.5F;
               flag2 = false;
            }
         }
      }

      this.setBlockBounds(f3, f, f5, f4, f2, f6);
      return flag2;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      if (this.hasRaytraced) {
         this.setBlockBounds(
            0.5F * (float)(this.rayTracePass % 2),
            0.5F * (float)(this.rayTracePass / 4 % 2),
            0.5F * (float)(this.rayTracePass / 2 % 2),
            0.5F + 0.5F * (float)(this.rayTracePass % 2),
            0.5F + 0.5F * (float)(this.rayTracePass / 4 % 2),
            0.5F + 0.5F * (float)(this.rayTracePass / 2 % 2)
         );
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public BlockStairs(IBlockState state) {
      super(state.getBlock().blockMaterial);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(HALF, BlockStairs.EnumHalf.BOTTOM)
            .withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT)
      );
      this.modelBlock = state.getBlock();
      this.modelState = state;
      this.setHardness(this.modelBlock.blockHardness);
      this.setResistance(this.modelBlock.blockResistance / 3.0F);
      this.setStepSound(this.modelBlock.stepSound);
      this.setLightOpacity(255);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      this.modelBlock.updateTick(world, pos, state, rand);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return this.modelBlock.onBlockActivated(world, pos, this.modelState, player, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return this.modelBlock.getBlockLayer();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      IBlockState state = super.onBlockPlaced(world, pos, facing, f, f2, f3, n, entity);
      state = state.withProperty(FACING, entity.getHorizontalFacing()).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT);
      return facing == EnumFacing.DOWN || facing != EnumFacing.UP && !((double)f2 <= 0.5)
         ? state.withProperty(HALF, BlockStairs.EnumHalf.TOP)
         : state.withProperty(HALF, BlockStairs.EnumHalf.BOTTOM);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      this.modelBlock.breakBlock(world, pos, this.modelState);
   }

   @Override
   public boolean isCollidable() {
      return this.modelBlock.isCollidable();
   }


   public enum EnumHalf implements IStringSerializable {
      TOP("top"),
      BOTTOM("bottom");

   private final String name;
   @Override
      public String toString() {
         return this.name;
      }

      private EnumHalf(String str) {
         this.name = str;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }

   public enum EnumShape implements IStringSerializable {
      STRAIGHT("straight"),
      INNER_LEFT("inner_left"),
      INNER_RIGHT("inner_right"),
      OUTER_LEFT("outer_left"),
      OUTER_RIGHT("outer_right");

   private final String name;
   @Override
      public String toString() {
         return this.name;
      }

      private EnumShape(String str) {
         this.name = str;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }
}
