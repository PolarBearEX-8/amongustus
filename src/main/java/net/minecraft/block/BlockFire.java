package net.minecraft.block;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class BlockFire extends Block {
   public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
   public static final PropertyBool FLIP = PropertyBool.create("flip");
   public static final PropertyBool ALT = PropertyBool.create("alt");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   public static final PropertyInteger UPPER = PropertyInteger.create("upper", 0, 2);
   private final Map encouragements = Maps.newIdentityHashMap();
   private final Map flammabilities = Maps.newIdentityHashMap();

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (rand.nextInt(24) == 0) {
         world.playSound(
            (double)((float)pos.getX() + 0.5F),
            (double)((float)pos.getY() + 0.5F),
            (double)((float)pos.getZ() + 0.5F),
            "fire.fire",
            1.0F + rand.nextFloat(),
            rand.nextFloat() * 0.7F + 0.3F,
            false
         );
      }

      if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) && !Blocks.fire.canCatchFire(world, pos.getX(), pos.getY() - 1, pos.getZ())) {
         if (Blocks.fire.canCatchFire(world, pos.getX() - 1, pos.getY(), pos.getZ())) {
            for (int i = 0; i < 2; i++) {
               double d = (double)pos.getX() + rand.nextDouble() * 0.1F;
               double d2 = (double)pos.getY() + rand.nextDouble();
               double d3 = (double)pos.getZ() + rand.nextDouble();
               world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.fire.canCatchFire(world, pos.getX() + 1, pos.getY(), pos.getZ())) {
            for (int j = 0; j < 2; j++) {
               double d4 = (double)(pos.getX() + 1) - rand.nextDouble() * 0.1F;
               double d5 = (double)pos.getY() + rand.nextDouble();
               double d6 = (double)pos.getZ() + rand.nextDouble();
               world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d4, d5, d6, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.fire.canCatchFire(world, pos.getX(), pos.getY(), pos.getZ() - 1)) {
            for (int k = 0; k < 2; k++) {
               double d7 = (double)pos.getX() + rand.nextDouble();
               double d8 = (double)pos.getY() + rand.nextDouble();
               double d9 = (double)pos.getZ() + rand.nextDouble() * 0.1F;
               world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d8, d9, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.fire.canCatchFire(world, pos.getX(), pos.getY(), pos.getZ() + 1)) {
            for (int m = 0; m < 2; m++) {
               double d10 = (double)pos.getX() + rand.nextDouble();
               double d11 = (double)pos.getY() + rand.nextDouble();
               double d12 = (double)(pos.getZ() + 1) - rand.nextDouble() * 0.1F;
               world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d10, d11, d12, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.fire.canCatchFire(world, pos.getX(), pos.getY() + 1, pos.getZ())) {
            for (int idx = 0; idx < 2; idx++) {
               double d13 = (double)pos.getX() + rand.nextDouble();
               double d14 = (double)(pos.getY() + 1) - rand.nextDouble() * 0.1F;
               double d15 = (double)pos.getZ() + rand.nextDouble();
               world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d13, d14, d15, 0.0, 0.0, 0.0, new int[0]);
            }
         }
      } else {
         for (int index = 0; index < 3; index++) {
            double d16 = (double)pos.getX() + rand.nextDouble();
            double d17 = (double)pos.getY() + rand.nextDouble() * 0.5 + 0.5;
            double d18 = (double)pos.getZ() + rand.nextDouble();
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d16, d17, d18, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   public void setFireInfo(Block block, int n, int n2) {
      this.encouragements.put(block, n);
      this.flammabilities.put(block, n2);
   }

   private int getNeighborEncouragement(World world, BlockPos pos) {
      if (!world.isAirBlock(pos)) {
         return 0;
      } else {
         int n = 0;

         for (EnumFacing facing : EnumFacing.getValues()) {
            n = Math.max(this.getEncouragement(world.getBlockState(pos.offset(facing)).getBlock()), n);
         }

         return n;
      }
   }

   public boolean canCatchFire(IBlockAccess world, int n, int n2, int n3) {
      return this.getEncouragement(world.getBlockState2(n, n2, n3).getBlock()) > 0;
   }

   public static void init() {
      Blocks.fire.setFireInfo(Blocks.planks, 5, 20);
      Blocks.fire.setFireInfo(Blocks.double_wooden_slab, 5, 20);
      Blocks.fire.setFireInfo(Blocks.wooden_slab, 5, 20);
      Blocks.fire.setFireInfo(Blocks.oak_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.spruce_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.birch_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.jungle_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.dark_oak_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.acacia_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.oak_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.spruce_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.birch_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.jungle_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.dark_oak_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.acacia_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.oak_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.birch_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.spruce_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.jungle_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.log, 5, 5);
      Blocks.fire.setFireInfo(Blocks.log2, 5, 5);
      Blocks.fire.setFireInfo(Blocks.leaves, 30, 60);
      Blocks.fire.setFireInfo(Blocks.leaves2, 30, 60);
      Blocks.fire.setFireInfo(Blocks.bookshelf, 30, 20);
      Blocks.fire.setFireInfo(Blocks.tnt, 15, 100);
      Blocks.fire.setFireInfo(Blocks.tallgrass, 60, 100);
      Blocks.fire.setFireInfo(Blocks.double_plant, 60, 100);
      Blocks.fire.setFireInfo(Blocks.yellow_flower, 60, 100);
      Blocks.fire.setFireInfo(Blocks.red_flower, 60, 100);
      Blocks.fire.setFireInfo(Blocks.deadbush, 60, 100);
      Blocks.fire.setFireInfo(Blocks.wool, 30, 60);
      Blocks.fire.setFireInfo(Blocks.vine, 15, 100);
      Blocks.fire.setFireInfo(Blocks.coal_block, 5, 5);
      Blocks.fire.setFireInfo(Blocks.hay_block, 60, 20);
      Blocks.fire.setFireInfo(Blocks.carpet, 60, 20);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) || this.canNeighborCatchFire(world, pos);
   }

   private int getFlammability(Block block) {
      Integer num = (Integer)this.flammabilities.get(block);
      return num == null ? 0 : num;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AGE, n);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) && !this.canNeighborCatchFire(world, pos)) {
         world.setBlockToAir(pos);
      }
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   private boolean canNeighborCatchFire(World world, BlockPos pos) {
      for (EnumFacing facing : EnumFacing.getValues()) {
         if (this.canCatchFire(world, pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ())) {
            return true;
         }
      }

      return false;
   }

   private int getEncouragement(Block block) {
      Integer num = (Integer)this.encouragements.get(block);
      return num == null ? 0 : num;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (world.provider.getDimensionId() > 0 || !Blocks.portal.trySpawnPortal(world, pos)) {
         if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) && !this.canNeighborCatchFire(world, pos)) {
            world.setBlockToAir(pos);
         } else {
            world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
         }
      }
   }

   @Override
   public boolean isCollidable() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AGE, NORTH, EAST, SOUTH, WEST, UPPER, FLIP, ALT});
   }

   private void catchOnFire(World world, BlockPos pos, int n, Random rand, int n2) {
      int flammability = this.getFlammability(world.getBlockState(pos).getBlock());
      if (rand.nextInt(n) < flammability) {
         IBlockState blockState2 = world.getBlockState(pos);
         if (rand.nextInt(n2 + 10) < 5 && !world.isRainingAt(pos)) {
            int n3 = n2 + rand.nextInt(5) / 4;
            if (n3 > 15) {
               n3 = 15;
            }

            world.setBlockState(pos, this.getDefaultState().withProperty(AGE, n3), 3);
         } else {
            world.setBlockToAir(pos);
         }

         if (blockState2.getBlock() == Blocks.tnt) {
            Blocks.tnt.onBlockDestroyedByPlayer(world, pos, blockState2.withProperty(BlockTNT.EXPLODE, Boolean.TRUE));
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean requiresUpdates() {
      return false;
   }

   public boolean canDie(World world, BlockPos pos) {
      return world.isRainingAt(pos) || world.isRainingAt(pos.west()) || world.isRainingAt(pos.east()) || world.isRainingAt(pos.north()) || world.isRainingAt(pos.south());
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (world.getGameRules().getBoolean("doFireTick")) {
         if (!this.canPlaceBlockAt(world, pos)) {
            world.setBlockToAir(pos);
         }

         Block block = world.getBlockState(pos.down()).getBlock();
         boolean flag = block == Blocks.netherrack;
         if (world.provider instanceof WorldProviderEnd && block == Blocks.bedrock) {
            flag = true;
         }

         if (!flag && world.isRaining() && this.canDie(world, pos)) {
            world.setBlockToAir(pos);
         } else {
            int value = (Integer)state.getValue(AGE);
            if (value < 15) {
               state = state.withProperty(AGE, value + rand.nextInt(3) / 2);
               world.setBlockState(pos, state, 4);
            }

            world.scheduleUpdate(pos, this, this.tickRate(world) + rand.nextInt(10));
            if (!flag) {
               if (!this.canNeighborCatchFire(world, pos)) {
                  if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) || value > 3) {
                     world.setBlockToAir(pos);
                  }

                  return;
               }

               if (!this.canCatchFire(world, pos.getX(), pos.getY() - 1, pos.getZ()) && value == 15 && rand.nextInt(4) == 0) {
                  world.setBlockToAir(pos);
                  return;
               }
            }

            boolean isBlockinHighHumidity = world.isBlockinHighHumidity(pos);
            byte b = 0;
            if (isBlockinHighHumidity) {
               b = -50;
            }

            this.catchOnFire(world, pos.east(), 300 + b, rand, value);
            this.catchOnFire(world, pos.west(), 300 + b, rand, value);
            this.catchOnFire(world, pos.down(), 250 + b, rand, value);
            this.catchOnFire(world, pos.up(), 250 + b, rand, value);
            this.catchOnFire(world, pos.north(), 300 + b, rand, value);
            this.catchOnFire(world, pos.south(), 300 + b, rand, value);

            for (int i = -1; i <= 1; i++) {
               for (int j = -1; j <= 1; j++) {
                  for (int k = -1; k <= 4; k++) {
                     if (i != 0 || k != 0 || j != 0) {
                        int n = 100;
                        if (k > 1) {
                           n += (k - 1) * 100;
                        }

                        BlockPos pos2 = pos.add(i, k, j);
                        int neighborEncouragement = this.getNeighborEncouragement(world, pos2);
                        if (neighborEncouragement > 0) {
                           int n2 = (neighborEncouragement + 40 + world.getDifficulty().getDifficultyId() * 7) / (value + 30);
                           if (isBlockinHighHumidity) {
                              n2 /= 2;
                           }

                           if (n2 > 0 && rand.nextInt(n) <= n2 && (!world.isRaining() || !this.canDie(world, pos2))) {
                              int n3 = value + rand.nextInt(5) / 4;
                              if (n3 > 15) {
                                 n3 = 15;
                              }

                              world.setBlockState(pos2, state.withProperty(AGE, n3), 3);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public int tickRate(World world) {
      return 30;
   }

   public BlockFire() {
      super(Material.fire);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(AGE, 0)
            .withProperty(FLIP, Boolean.FALSE)
            .withProperty(ALT, Boolean.FALSE)
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
            .withProperty(UPPER, 0)
      );
      this.setTickRandomly(true);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.tntColor;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      if (!World.doesBlockHaveSolidTopSurface(world, n, n2 - 1, n3) && !Blocks.fire.canCatchFire(world, n, n2 - 1, n3)) {
         boolean flag = (n + n + n & 1) == 1;
         boolean flag2 = (n / 2 + n / 2 + n / 2 & 1) == 1;
         int n4 = 0;
         if (this.canCatchFire(world, n, n2 + 1, n3)) {
            n4 = flag ? 1 : 2;
         }

         return state.withProperty(NORTH, this.canCatchFire(world, n, n2, n3 - 1))
            .withProperty(EAST, this.canCatchFire(world, n + 1, n2, n3))
            .withProperty(SOUTH, this.canCatchFire(world, n, n2, n3 + 1))
            .withProperty(WEST, this.canCatchFire(world, n - 1, n2, n3))
            .withProperty(UPPER, n4)
            .withProperty(FLIP, flag2)
            .withProperty(ALT, flag);
      } else {
         return this.getDefaultState();
      }
   }
}
