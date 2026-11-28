package net.minecraft.block;

import java.util.Random;
import net.augustus.modules.ModuleManager;
import net.augustus.modules.modes.jesus.SolidJesusMode;
import net.augustus.Augustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.chunk.Chunk;

public abstract class BlockLiquid extends Block {
   public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
   private static final EnumFacing[] HORIZONTAL_FACINGS = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   public void triggerMixEffects(World world, BlockPos pos) {
      double x = (double)pos.getX();
      double y = (double)pos.getY();
      double z = (double)pos.getZ();
      world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

      for (int i = 0; i < 8; i++) {
         world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x + Math.random(), y + 1.2, z + Math.random(), 0.0, 0.0, 0.0, new int[0]);
      }
   }

   public static BlockDynamicLiquid getFlowingBlock(Material material) {
      if (material == Material.water) {
         return Blocks.flowing_water;
      } else if (material == Material.lava) {
         return Blocks.flowing_lava;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return world.getBlockState2(n, n2, n3).getBlock().getMaterial() != this.blockMaterial && (facing == EnumFacing.UP || super.shouldSideBeRendered(world, n, n2, n3, facing));
   }

   @Override
   public Vec3 modifyAcceleration(World world, BlockPos pos, Entity entity, Vec3 vec) {
      return entity instanceof EntityPlayerSP && Augustus.getInstance().getModuleManager().jesus.shouldIgnoreWaterFlow() ? new Vec3(vec) : vec.add(this.getFlowVector(world, pos));
   }

   @Override
   public boolean isBlockSolid(IBlockAccess world, BlockPos pos, EnumFacing facing) {
      Material material = world.getBlockState(pos).getBlock().getMaterial();
      return material != this.blockMaterial && (facing == EnumFacing.UP || material != Material.ice && super.isBlockSolid(world, pos, facing));
   }

   public BlockLiquid(Material material) {
      super(material);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.setTickRandomly(true);
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   public boolean isBlockSolid(IBlockState state, EnumFacing facing) {
      Material material = state.getBlock().getMaterial();
      return material != this.blockMaterial && (facing == EnumFacing.UP || material != Material.ice && material.isSolid());
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return this.blockMaterial == Material.water ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.SOLID;
   }

   public int getEffectiveFlowDecay(IBlockAccess world, BlockPos pos) {
      int level = this.getLevel(world, pos);
      return level >= 8 ? 0 : level;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      ModuleManager moduleManager = Augustus.getInstance().getModuleManager();
      if (moduleManager.jesus.isToggled() && moduleManager.jesus.mode.getAbstractSubMode() instanceof SolidJesusMode && moduleManager.jesus.isLiquidSolidEnabled()) {
         SolidJesusMode abstractSubMode = (SolidJesusMode)moduleManager.jesus.mode.getAbstractSubMode();
         double d = (double)n2 + this.maxY;
         if (abstractSubMode.userWaterBlockBounds.getValue()) {
            d -= (double)getLiquidHeightPercent((Integer)state.getValue(LEVEL));
         }

         return AxisAlignedBB.fromBounds(
            (double)n + this.minX, (double)n2 + this.minY, (double)n3 + this.minZ, (double)n + this.maxX, d, (double)n3 + this.maxZ
         );
      } else {
         return null;
      }
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return this.blockMaterial != Material.lava;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public int getEffectiveFlowDecay(IBlockState state) {
      int level = this.getLevel(state);
      return level >= 8 ? 0 : level;
   }

   @Override
   public int getMixedBrightnessForBlock(IBlockAccess world, int n, int n2, int n3) {
      int combinedLight = world.getCombinedLight(n, n2, n3, 0);
      int combinedLight2 = world.getCombinedLight(n, n2 + 1, n3, 0);
      int n4 = combinedLight & 0xFF;
      int n5 = combinedLight2 & 0xFF;
      int n6 = combinedLight >> 16 & 0xFF;
      int n7 = combinedLight2 >> 16 & 0xFF;
      return (n4 > n5 ? n4 : n5) | (n6 > n7 ? n6 : n7) << 16;
   }

   public boolean checkForMixing(World world, BlockPos pos, IBlockState state) {
      if (this.blockMaterial == Material.lava) {
         boolean flag = false;

         for (EnumFacing facing : EnumFacing.getValues()) {
            if (facing != EnumFacing.DOWN && world.getBlockState(pos.offset(facing)).getBlock().getMaterial() == Material.water) {
               flag = true;
               break;
            }
         }

         if (flag) {
            Integer value = (Integer)state.getValue(LEVEL);
            if (value == 0) {
               world.setBlockState(pos, Blocks.obsidian.getDefaultState());
               this.triggerMixEffects(world, pos);
               return true;
            }

            if (value <= 4) {
               world.setBlockState(pos, Blocks.cobblestone.getDefaultState());
               this.triggerMixEffects(world, pos);
               return true;
            }
         }
      }

      return false;
   }

   public static BlockStaticLiquid getStaticBlock(Material material) {
      if (material == Material.water) {
         return Blocks.water;
      } else if (material == Material.lava) {
         return Blocks.lava;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   public static double getFlowDirection(IBlockAccess world, BlockPos pos, Material material) {
      Vec3 flowVector = getFlowingBlock(material).getFlowVector(world, pos);
      return flowVector.xCoord == 0.0 && flowVector.zCoord == 0.0 ? -1000.0 : MathHelper.atan2(flowVector.zCoord, flowVector.xCoord) - 1.5707963267948966;
   }

   @Override
   public int tickRate(World world) {
      return this.blockMaterial == Material.water ? 5 : (this.blockMaterial == Material.lava ? (world.provider.getHasNoSky() ? 10 : 30) : 0);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(LEVEL);
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return this.blockMaterial != Material.lava;
   }

   @Override
   public boolean canCollideCheck(IBlockState state, boolean flag) {
      return flag && (Integer)state.getValue(LEVEL) == 0;
   }

   public int getLevel(IBlockAccess world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().getMaterial() == this.blockMaterial ? (Integer)world.getBlockState(pos).getValue(LEVEL) : -1;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(LEVEL, n);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.checkForMixing(world, pos, state);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.checkForMixing(world, pos, state);
   }

   public Vec3 getFlowVector(IBlockAccess world, BlockPos pos) {
      Vec3 vec = new Vec3(0.0, 0.0, 0.0);
      int effectiveFlowDecay = this.getEffectiveFlowDecay(world, pos);

      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         BlockPos pos2 = pos.offset(facing);
         int effectiveFlowDecay2 = this.getEffectiveFlowDecay(world, pos2);
         if (effectiveFlowDecay2 < 0) {
            if (!world.getBlockState(pos2).getBlock().getMaterial().blocksMovement()) {
               effectiveFlowDecay2 = this.getEffectiveFlowDecay(world, pos2.down());
               if (effectiveFlowDecay2 >= 0) {
                  int n = effectiveFlowDecay2 - (effectiveFlowDecay - 8);
                  vec = vec.addVector(
                     (double)((pos2.getX() - pos.getX()) * n), (double)((pos2.getY() - pos.getY()) * n), (double)((pos2.getZ() - pos.getZ()) * n)
                  );
               }
            }
         } else if (effectiveFlowDecay2 >= 0) {
            int n2 = effectiveFlowDecay2 - effectiveFlowDecay;
            vec = vec.addVector(
               (double)((pos2.getX() - pos.getX()) * n2), (double)((pos2.getY() - pos.getY()) * n2), (double)((pos2.getZ() - pos.getZ()) * n2)
            );
         }
      }

      if ((Integer)world.getBlockState(pos).getValue(LEVEL) >= 8) {
         for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            BlockPos pos3 = pos.offset(facing2);
            if (this.isBlockSolid(world, pos3, facing2) || this.isBlockSolid(world, pos3.up(), facing2)) {
               vec = vec.normalize().addVector(0.0, -6.0, 0.0);
               break;
            }
         }
      }

      return vec.normalize();
   }

   @Override
   public boolean canCollideCheck(IBlockState state, boolean flag, boolean flag2) {
      return flag && (flag2 || (Integer)state.getValue(LEVEL) == 0);
   }

   public boolean hasOpenNeighbor(IBlockAccess world, int n, int n2, int n3) {
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            Block block = world.getBlockState2(n + i, n2, n3 + j).getBlock();
            if (block.getMaterial() != this.blockMaterial && !block.isFullBlock()) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public int getRenderType() {
      return 1;
   }

   public Vec3 getFlowVector(World world, IBlockState state, Chunk chunk, int n, int n2, int n3) {
      double d = 0.0;
      double d2 = 0.0;
      double d3 = 0.0;
      int effectiveFlowDecay = this.getEffectiveFlowDecay(state);
      int n4 = n & 15;
      int n5 = n3 & 15;
      int n6 = 0;
      int n7 = 0;
      boolean flag = true;
      if (n4 > 0 && n4 < 15 && n5 > 0 && n5 < 15) {
         flag = false;
      } else {
         n6 = n >> 15;
         n7 = n3 >> 15;
      }

      int value = (Integer)state.getValue(LEVEL);
      boolean flag2 = false;

      for (int i = 0; i < HORIZONTAL_FACINGS.length; i++) {
         EnumFacing facing = HORIZONTAL_FACINGS[i];
         int n8 = n + facing.getFrontOffsetX();
         int n9 = n2 + facing.getFrontOffsetY();
         int n10 = n3 + facing.getFrontOffsetZ();
         if (flag) {
            int n11 = n8 >> 4;
            int n12 = n10 >> 4;
            if (n11 != n6 || n12 != n7) {
               chunk = world.getChunkFromChunkCoords(n11, n12);
               n6 = n11;
               n7 = n12;
            }
         }

         IBlockState blockState2 = chunk.getBlockState(n8, n9, n10);
         int effectiveFlowDecay2 = this.getEffectiveFlowDecay(blockState2);
         if (effectiveFlowDecay2 < 0) {
            if (!blockState2.getBlock().getMaterial().blocksMovement()) {
               effectiveFlowDecay2 = this.getEffectiveFlowDecay(chunk.getBlockState(n8, n9 - 1, n10));
               if (effectiveFlowDecay2 >= 0) {
                  int n13 = effectiveFlowDecay2 - (effectiveFlowDecay - 8);
                  d += (double)((n8 - n) * n13);
                  d2 += (double)((n9 - n2) * n13);
                  d3 += (double)((n10 - n3) * n13);
               }
            }
         } else if (effectiveFlowDecay2 >= 0) {
            int n14 = effectiveFlowDecay2 - effectiveFlowDecay;
            d += (double)((n8 - n) * n14);
            d2 += (double)((n9 - n2) * n14);
            d3 += (double)((n10 - n3) * n14);
         }

         if (value >= 8 && !flag2 && (this.isBlockSolid(blockState2, facing) || this.isBlockSolid(chunk.getBlockState(n8, n9 + 1, n10), facing))) {
            flag2 = true;
         }
      }

      if (flag2) {
         double d4 = this.vectorLength(d, d2, d3);
         if (d4 < 1.0E-4) {
            d = 0.0;
            d2 = 0.0;
            d3 = 0.0;
         } else {
            d /= d4;
            d2 /= d4;
            d3 /= d4;
         }

         d2 += -0.6;
      }

      double d5 = this.vectorLength(d, d2, d3);
      if (d5 < 1.0E-4) {
         d = 0.0;
         d2 = 0.0;
         d3 = 0.0;
      } else {
         d /= d5;
         d2 /= d5;
         d3 /= d5;
      }

      return new Vec3(d, d2, d3);
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      double x = (double)pos.getX();
      double y = (double)pos.getY();
      double z = (double)pos.getZ();
      if (this.blockMaterial == Material.water) {
         int value = (Integer)state.getValue(LEVEL);
         if (value > 0 && value < 8) {
            if (rand.nextInt(64) == 0) {
               world.playSound(x + 0.5, y + 0.5, z + 0.5, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
            }
         } else if (rand.nextInt(10) == 0) {
            world.spawnParticle(
               EnumParticleTypes.SUSPENDED,
               x + (double)rand.nextFloat(),
               y + (double)rand.nextFloat(),
               z + (double)rand.nextFloat(),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }
      }

      if (this.blockMaterial == Material.lava
         && world.getBlockState(pos.up()).getBlock().getMaterial() == Material.air
         && !world.getBlockState(pos.up()).getBlock().isOpaqueCube()) {
         if (rand.nextInt(100) == 0) {
            double d = x + (double)rand.nextFloat();
            double d2 = y + this.maxY;
            double d3 = z + (double)rand.nextFloat();
            world.spawnParticle(EnumParticleTypes.LAVA, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
            world.playSound(d, d2, d3, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
         }

         if (rand.nextInt(200) == 0) {
            world.playSound(x, y, z, "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
         }
      }

      if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ())) {
         Material material = world.getBlockState(pos.down(2)).getBlock().getMaterial();
         if (!material.blocksMovement() && !material.isLiquid()) {
            double d4 = x + (double)rand.nextFloat();
            double d5 = y - 1.05;
            double d6 = z + (double)rand.nextFloat();
            if (this.blockMaterial == Material.water) {
               world.spawnParticle(EnumParticleTypes.DRIP_WATER, d4, d5, d6, 0.0, 0.0, 0.0, new int[0]);
            } else {
               world.spawnParticle(EnumParticleTypes.DRIP_LAVA, d4, d5, d6, 0.0, 0.0, 0.0, new int[0]);
            }
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{LEVEL});
   }

   public static float getLiquidHeightPercent(int n) {
      if (n >= 8) {
         n = 0;
      }

      return (float)(n + 1) / 9.0F;
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return this.blockMaterial == Material.water ? BiomeColorHelper.getWaterColorAtPos(world, pos) : 16777215;
   }

   private double vectorLength(double d, double d2, double d3) {
      return (double)MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
   }

   public int getLevel(IBlockState state) {
      return state.getBlock().getMaterial() == this.blockMaterial ? (Integer)state.getValue(LEVEL) : -1;
   }

}
