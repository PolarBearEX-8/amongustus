package net.minecraft.block;

import com.google.common.cache.LoadingCache;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortal extends BlockBreakable {
   public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class, new EnumFacing.Axis[]{EnumFacing.Axis.X, EnumFacing.Axis.Z});

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      EnumFacing.Axis axis = null;
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      if (world.getBlockState2(n, n2, n3).getBlock() == this) {
         axis = (EnumFacing.Axis)blockState2.getValue(AXIS);
         if (axis == null) {
            return false;
         }

         if (axis == EnumFacing.Axis.Z && facing != EnumFacing.EAST && facing != EnumFacing.WEST) {
            return false;
         }

         if (axis == EnumFacing.Axis.X && facing != EnumFacing.SOUTH && facing != EnumFacing.NORTH) {
            return false;
         }
      }

      boolean flag = world.getBlockState2(n - 1, n2, n3).getBlock() == this && world.getBlockState2(n - 2, n2, n3).getBlock() != this;
      boolean flag2 = world.getBlockState2(n + 1, n2, n3).getBlock() == this && world.getBlockState2(n + 2, n2, n3).getBlock() != this;
      boolean flag3 = world.getBlockState2(n, n2, n3 - 1).getBlock() == this && world.getBlockState2(n, n2, n3 - 2).getBlock() != this;
      boolean flag4 = world.getBlockState2(n, n2, n3 + 1).getBlock() == this && world.getBlockState2(n, n2, n3 + 2).getBlock() != this;
      boolean flag5 = flag || flag2 || axis == EnumFacing.Axis.X;
      boolean flag6 = flag3 || flag4 || axis == EnumFacing.Axis.Z;
      return flag5 && facing == EnumFacing.WEST
         ? true
         : (flag5 && facing == EnumFacing.EAST ? true : (flag6 && facing == EnumFacing.NORTH ? true : flag6 && facing == EnumFacing.SOUTH));
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      EnumFacing.Axis value = (EnumFacing.Axis)state.getValue(AXIS);
      if (value == EnumFacing.Axis.X) {
         BlockPortal.Size size = new BlockPortal.Size(world, pos, EnumFacing.Axis.X);
         if (!size.isValid() || BlockPortal.Size.getPortalBlockCount(size) < BlockPortal.Size.getWidth(size) * BlockPortal.Size.getHeight(size)) {
            world.setBlockState(pos, Blocks.air.getDefaultState());
         }
      } else if (value == EnumFacing.Axis.Z) {
         BlockPortal.Size size2 = new BlockPortal.Size(world, pos, EnumFacing.Axis.Z);
         if (!size2.isValid() || BlockPortal.Size.getPortalBlockCount(size2) < BlockPortal.Size.getWidth(size2) * BlockPortal.Size.getHeight(size2)) {
            world.setBlockState(pos, Blocks.air.getDefaultState());
         }
      }
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   public BlockPortal() {
      super(Material.portal, false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
      this.setTickRandomly(true);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      EnumFacing.Axis value = (EnumFacing.Axis)world.getBlockState2(n, n2, n3).getValue(AXIS);
      float f = 0.125F;
      float f2 = 0.125F;
      if (value == EnumFacing.Axis.X) {
         f = 0.5F;
      }

      if (value == EnumFacing.Axis.Z) {
         f2 = 0.5F;
      }

      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return null;
   }

   public BlockPattern.PatternHelper createPatternHelper(World world, BlockPos pos) {
      EnumFacing.Axis axis = EnumFacing.Axis.Z;
      BlockPortal.Size size = new BlockPortal.Size(world, pos, EnumFacing.Axis.X);
      LoadingCache loadingCache = BlockPattern.createLoadingCache(world, true);
      if (!size.isValid()) {
         axis = EnumFacing.Axis.X;
         size = new BlockPortal.Size(world, pos, EnumFacing.Axis.Z);
      }

      if (!size.isValid()) {
         return new BlockPattern.PatternHelper(pos, EnumFacing.NORTH, EnumFacing.UP, loadingCache, 1, 1, 1);
      } else {
         int[] ints = new int[EnumFacing.AxisDirection.values().length];
         EnumFacing facing = BlockPortal.Size.accessRightDir(size).rotateYCCW();
         BlockPos pos2 = BlockPortal.Size.accessBottomLeft(size).up(size.getHeight() - 1);

         for (EnumFacing.AxisDirection axisDirection : EnumFacing.AxisDirection.values()) {
            BlockPattern.PatternHelper patternHelper = new BlockPattern.PatternHelper(
               facing.getAxisDirection() == axisDirection ? pos2 : pos2.offset(BlockPortal.Size.accessRightDir(size), size.getWidth() - 1),
               EnumFacing.getFacingFromAxis(axisDirection, axis),
               EnumFacing.UP,
               loadingCache,
               size.getWidth(),
               size.getHeight(),
               1
            );

            for (int i = 0; i < size.getWidth(); i++) {
               for (int j = 0; j < size.getHeight(); j++) {
                  BlockWorldState blockWorldState = patternHelper.translateOffset(i, j, 1);
                  if (blockWorldState.getBlockState() != null && blockWorldState.getBlockState().getBlock().getMaterial() != Material.air) {
                     ints[axisDirection.ordinal()]++;
                  }
               }
            }
         }

         EnumFacing.AxisDirection positive = EnumFacing.AxisDirection.POSITIVE;

         for (EnumFacing.AxisDirection axisDirection2 : EnumFacing.AxisDirection.values()) {
            if (ints[axisDirection2.ordinal()] < ints[positive.ordinal()]) {
               positive = axisDirection2;
            }
         }

         return new BlockPattern.PatternHelper(
            facing.getAxisDirection() == positive ? pos2 : pos2.offset(BlockPortal.Size.accessRightDir(size), size.getWidth() - 1),
            EnumFacing.getFacingFromAxis(positive, axis),
            EnumFacing.UP,
            loadingCache,
            size.getWidth(),
            size.getHeight(),
            1
         );
      }
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      super.updateTick(world, pos, state, rand);
      if (world.provider.isSurfaceWorld() && world.getGameRules().getBoolean("doMobSpawning") && rand.nextInt(2000) < world.getDifficulty().getDifficultyId()) {
         int y = pos.getY();
         BlockPos pos2 = pos;

         while (!World.doesBlockHaveSolidTopSurface(world, pos2.getX(), pos2.getY(), pos2.getZ()) && pos2.getY() > 0) {
            pos2 = pos2.down();
         }

         if (y > 0 && !world.getBlockState(pos2.up()).getBlock().isNormalCube()) {
            Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, (double)pos2.getX() + 0.5, (double)pos2.getY() + 1.1, (double)pos2.getZ() + 0.5);
            if (entity != null) {
               entity.timeUntilPortal = entity.getPortalCooldown();
            }
         }
      }
   }

   public boolean trySpawnPortal(World world, BlockPos pos) {
      BlockPortal.Size size = new BlockPortal.Size(world, pos, EnumFacing.Axis.X);
      if (size.isValid() && BlockPortal.Size.getPortalBlockCount(size) == 0) {
         size.placePortalBlocks();
         return true;
      } else {
         BlockPortal.Size size2 = new BlockPortal.Size(world, pos, EnumFacing.Axis.Z);
         if (size2.isValid() && BlockPortal.Size.getPortalBlockCount(size2) == 0) {
            size2.placePortalBlocks();
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return getMetaForAxis((EnumFacing.Axis)state.getValue(AXIS));
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (rand.nextInt(100) == 0) {
         world.playSound(
            (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false
         );
      }

      for (int i = 0; i < 4; i++) {
         double d = (double)((float)pos.getX() + rand.nextFloat());
         double d2 = (double)((float)pos.getY() + rand.nextFloat());
         double d3 = (double)((float)pos.getZ() + rand.nextFloat());
         double d4 = ((double)rand.nextFloat() - 0.5) * 0.5;
         double d5 = ((double)rand.nextFloat() - 0.5) * 0.5;
         double d6 = ((double)rand.nextFloat() - 0.5) * 0.5;
         int n = rand.nextInt(2) * 2 - 1;
         if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
            d = (double)pos.getX() + 0.5 + 0.25 * (double)n;
            d4 = (double)(rand.nextFloat() * 2.0F * (float)n);
         } else {
            d3 = (double)pos.getZ() + 0.5 + 0.25 * (double)n;
            d6 = (double)(rand.nextFloat() * 2.0F * (float)n);
         }

         world.spawnParticle(EnumParticleTypes.PORTAL, d, d2, d3, d4, d5, d6, new int[0]);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AXIS});
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AXIS, (n & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (entity.ridingEntity == null && entity.riddenByEntity == null) {
         entity.setPortal(pos);
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public static int getMetaForAxis(EnumFacing.Axis axis) {
      return axis == EnumFacing.Axis.X ? 1 : (axis == EnumFacing.Axis.Z ? 2 : 0);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }


   public static class Size {
      private final World world;
      private final EnumFacing.Axis axis;
      private final EnumFacing rightDir;
      private final EnumFacing leftDir;
      private int portalBlockCount = 0;
      private BlockPos bottomLeft;
      private int height;
      private int width;

      public static int getHeight(Size size) {
         return size.height;
      }

      public int calculatePortalHeight() {
         outerLoop:
         for (this.height = 0; this.height < 21; this.height++) {
            for (int i = 0; i < this.width; i++) {
               BlockPos pos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
               Block block = this.world.getBlockState(pos).getBlock();
               if (!this.isEmptyBlock(block)) {
                  break outerLoop;
               }

               if (block == Blocks.portal) {
                  this.portalBlockCount++;
               }

               if (i == 0) {
                  block = this.world.getBlockState(pos.offset(this.leftDir)).getBlock();
                  if (block != Blocks.obsidian) {
                     break outerLoop;
                  }
               } else if (i == this.width - 1) {
                  block = this.world.getBlockState(pos.offset(this.rightDir)).getBlock();
                  if (block != Blocks.obsidian) {
                     break outerLoop;
                  }
               }
            }
         }

         for (int j = 0; j < this.width; j++) {
            if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != Blocks.obsidian) {
               this.height = 0;
               break;
            }
         }

         if (this.height <= 21 && this.height >= 3) {
            return this.height;
         } else {
            this.bottomLeft = null;
            this.width = 0;
            this.height = 0;
            return 0;
         }
      }

      public static BlockPos accessBottomLeft(Size size) {
         return size.bottomLeft;
      }

      public static int getWidth(Size size) {
         return size.width;
      }

      public int getDistanceUntilEdge(BlockPos pos, EnumFacing facing) {
         int n;
         for (n = 0; n < 22; n++) {
            BlockPos pos2 = pos.offset(facing, n);
            if (!this.isEmptyBlock(this.world.getBlockState(pos2).getBlock()) || this.world.getBlockState(pos2.down()).getBlock() != Blocks.obsidian) {
               break;
            }
         }

         return this.world.getBlockState(pos.offset(facing, n)).getBlock() == Blocks.obsidian ? n : 0;
      }

      public static int getPortalBlockCount(Size size) {
         return size.portalBlockCount;
      }

      public Size(World world2, BlockPos pos, EnumFacing.Axis axis2) {
         this.world = world2;
         this.axis = axis2;
         if (axis2 == EnumFacing.Axis.X) {
            this.leftDir = EnumFacing.EAST;
            this.rightDir = EnumFacing.WEST;
         } else {
            this.leftDir = EnumFacing.NORTH;
            this.rightDir = EnumFacing.SOUTH;
         }

         BlockPos pos2 = pos;

         while (pos.getY() > pos2.getY() - 21 && pos.getY() > 0 && this.isEmptyBlock(world2.getBlockState(pos.down()).getBlock())) {
            pos = pos.down();
         }

         int n = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
         if (n >= 0) {
            this.bottomLeft = pos.offset(this.leftDir, n);
            this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
            if (this.width < 2 || this.width > 21) {
               this.bottomLeft = null;
               this.width = 0;
            }
         }

         if (this.bottomLeft != null) {
            this.height = this.calculatePortalHeight();
         }
      }

      public int getWidth() {
         return this.width;
      }

      public int getHeight() {
         return this.height;
      }

      public boolean isEmptyBlock(Block block) {
         return block.blockMaterial == Material.air || block == Blocks.fire || block == Blocks.portal;
      }

      public static EnumFacing accessRightDir(Size size) {
         return size.rightDir;
      }

      public boolean isValid() {
         return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
      }

      public void placePortalBlocks() {
         for (int i = 0; i < this.width; i++) {
            BlockPos pos = this.bottomLeft.offset(this.rightDir, i);

            for (int j = 0; j < this.height; j++) {
               this.world.setBlockState(pos.up(j), Blocks.portal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
            }
         }
      }
   }
}
