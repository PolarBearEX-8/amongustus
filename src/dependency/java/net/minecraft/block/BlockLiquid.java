package net.minecraft.block;

import client.Client;
import client.module.Module;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.setting.MovementFactorProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.item.GoldenShovel;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockLiquid extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("level", 0, 15);

   @Override
   public BlockSolidType processAction3() {
      return this.coreItemEntry == Material.coreItemEntry1 ? BlockSolidType.TRANSLUCENT : BlockSolidType.SOLID;
   }

   public static double computeValue(IBlockAccess iBlockAccess, BlockPos blockPos, Material material) {
      Vec3 vec = resolveBlockFlowingLava(material).resolveVec3(iBlockAccess, blockPos);
      return vec.xCoord == 0.0 && vec.zCoord == 0.0 ? -1000.0 : MathHelper.getDouble6(vec.zCoord, vec.xCoord) - (Math.PI / 2);
   }

   public void applyWorld2(World world, BlockPos blockPos) {
      double coordX = (double)blockPos.getX();
      double coordY = (double)blockPos.getY();
      double coordZ = (double)blockPos.getZ();
      world.applyText(coordX + 0.5, coordY + 0.5, coordZ + 0.5, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

      for (int index = 0; index < 8; index++) {
         world.applyAttackEffectsExplosionNorm(
            AttackEffectsExplosionNormalType.SMOKE_LARGE, coordX + Math.random(), coordY + 1.2, coordZ + Math.random(), 0.0, 0.0, 0.0, new int[0]
         );
      }
   }

   @Override
   public boolean processAction(IBlockState blockState, boolean flag, boolean flag2) {
      return flag && (flag2 || (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) == 0);
   }

   @Override
   public int processAction(World world) {
      return this.coreItemEntry == Material.coreItemEntry1 ? 5 : (this.coreItemEntry == Material.material29 ? (world.secondaryFloatArrayEntry.isEnabled() ? 10 : 30) : 0);
   }

   public Vec3 resolveVec3(IBlockAccess iBlockAccess, BlockPos blockPos) {
      Vec3 vec = new Vec3(0.0, 0.0, 0.0);
      int count = this.computeCountByIBlockAccess(iBlockAccess, blockPos);

      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         int count2 = this.computeCountByIBlockAccess(iBlockAccess, blockPos2);
         if (count2 < 0) {
            if (!iBlockAccess.getBlockState(blockPos2).getBlock().getMaterial().isActiveAsFlag()) {
               int count3 = this.computeCountByIBlockAccess(iBlockAccess, blockPos2.down());
               if (count3 >= 0) {
                  int count4 = count3 - (count - 8);
                  vec = vec.getVec312(
                     (double)((blockPos2.getX() - blockPos.getX()) * count4),
                     (double)((blockPos2.getY() - blockPos.getY()) * count4),
                     (double)((blockPos2.getZ() - blockPos.getZ()) * count4)
                  );
               }
            }
         } else if (count2 >= 0) {
            int count5 = count2 - count;
            vec = vec.getVec312(
               (double)((blockPos2.getX() - blockPos.getX()) * count5), (double)((blockPos2.getY() - blockPos.getY()) * count5), (double)((blockPos2.getZ() - blockPos.getZ()) * count5)
            );
         }
      }

      if ((Integer)iBlockAccess.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry) >= 8) {
         for (EnumFacing enumFacing2 : (Iterable<EnumFacing>)(Iterable)(MovementFactorProperty.HORIZONTAL)) {
            BlockPos blockPos3 = blockPos.offset(enumFacing2);
            if (this.processAction(iBlockAccess, blockPos3, enumFacing2) || this.processAction(iBlockAccess, blockPos3.up(), enumFacing2)) {
               vec = vec.normalize().getVec312(0.0, -6.0, 0.0);
               break;
            }
         }
      }

      return vec.normalize();
   }

   public static BlockFlowingLava resolveBlockFlowingLava(Material material) {
      if (material == Material.coreItemEntry1) {
         return Blocks.blockFlowingLava;
      } else if (material == Material.material29) {
         return Blocks.blockFlowingLava2;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return this.coreItemEntry != Material.material29;
   }

   @Override
   public Vec3 processAction(World world, BlockPos blockPos, Entity entity, Vec3 vec) {
      return entity instanceof EntityPlayerSP && Client.getClient().getModuleManager().jesus.canExecute6()
         ? new Vec3(vec)
         : vec.getVec36(this.resolveVec3(world, blockPos));
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public int getIndex(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      int count4 = iBlockAccess.computeCount(count, count2, count3, 0);
      int count5 = iBlockAccess.computeCount(count, count2 + 1, count3, 0);
      int count6 = count4 & 0xFF;
      int count7 = count5 & 0xFF;
      int count8 = count4 >> 16 & 0xFF;
      int count9 = count5 >> 16 & 0xFF;
      return (count6 > count7 ? count6 : count7) | (count8 > count9 ? count8 : count9) << 16;
   }

   public int computeCountByIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      int count = this.computeCount(iBlockAccess, blockPos);
      return count >= 8 ? 0 : count;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public boolean processAction(IBlockState blockState, boolean flag) {
      return flag && (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) == 0;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return this.coreItemEntry == Material.coreItemEntry1 ? GoldenShovel.computeCountByIBlockAccess(iBlockAccess, blockPos) : 16777215;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      this.matchesWorld(world, blockPos, blockState);
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return this.coreItemEntry != Material.material29;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean processAction(IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing enumFacing) {
      Material material = iBlockAccess.getBlockState(blockPos).getBlock().getMaterial();
      return material != this.coreItemEntry && (enumFacing == EnumFacing.UP || material != Material.material12 && super.processAction(iBlockAccess, blockPos, enumFacing));
   }

   public int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return iBlockAccess.getBlockState(blockPos).getBlock().getMaterial() == this.coreItemEntry
         ? (Integer)iBlockAccess.getBlockState(blockPos).getComparable(auxiliaryImmutableSetEntry)
         : -1;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return iBlockAccess.getIBlockState(count, count2, count3).getBlock().getMaterial() != this.coreItemEntry
         && (enumFacing == EnumFacing.UP || super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing));
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      this.matchesWorld(world, blockPos, blockState);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.coreItemEntry == Material.material29) {
         boolean flag = false;

         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            if (enumFacing != EnumFacing.DOWN && world.getBlockState(blockPos.offset(enumFacing)).getBlock().getMaterial() == Material.coreItemEntry1) {
               flag = true;
               break;
            }
         }

         if (flag) {
            Integer comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            if (comparable == 0) {
               world.matchesBlockPos(blockPos, Blocks.block113.getDefaultState());
               this.applyWorld2(world, blockPos);
               return true;
            }

            if (comparable <= 4) {
               world.matchesBlockPos(blockPos, Blocks.block65.getDefaultState());
               this.applyWorld2(world, blockPos);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public int processAction13() {
      return 1;
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      for (int index = -1; index <= 1; index++) {
         for (int index2 = -1; index2 <= 1; index2++) {
            Block block = iBlockAccess.getIBlockState(count + index, count2, count3 + index2).getBlock();
            if (block.getMaterial() != this.coreItemEntry && !block.isState18()) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return ((Module)Client.getClient().getModuleManager().jesus).isToggled()
            && Client.getClient().getModuleManager().jesus.settingMode.getSelected().equals("Solid")
            && Client.getClient().getModuleManager().jesus.canExecute5()
         ? AxisAlignedBB.resolveAxisAlignedBB(
            (double)count + this.coordOffset,
            (double)count2 + this.coordOffset11,
            (double)count3 + this.coordOffset2,
            (double)count + this.coordOffset9,
            (double)count2 + this.coordOffset6,
            (double)count3 + this.coordOffset15
         )
         : null;
   }

   public static BlockLava resolveBlockLava(Material material) {
      if (material == Material.coreItemEntry1) {
         return Blocks.blockLava2;
      } else if (material == Material.material29) {
         return Blocks.blockLava;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      double coordX = (double)blockPos.getX();
      double coordY = (double)blockPos.getY();
      double coordZ = (double)blockPos.getZ();
      if (this.coreItemEntry == Material.coreItemEntry1) {
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         if (comparable > 0 && comparable < 8) {
            if (random.nextInt(64) == 0) {
               world.applyText(coordX + 0.5, coordY + 0.5, coordZ + 0.5, "liquid.water", random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
            }
         } else if (random.nextInt(10) == 0) {
            world.applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SUSPENDED,
               coordX + (double)random.nextFloat(),
               coordY + (double)random.nextFloat(),
               coordZ + (double)random.nextFloat(),
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         }
      }

      if (this.coreItemEntry == Material.material29
         && world.getBlockState(blockPos.up()).getBlock().getMaterial() == Material.coreItemEntry
         && !world.getBlockState(blockPos.up()).getBlock().processAction15()) {
         if (random.nextInt(100) == 0) {
            double offset = coordX + (double)random.nextFloat();
            double offset2 = coordY + this.coordOffset6;
            double offset3 = coordZ + (double)random.nextFloat();
            world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.LAVA, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
            world.applyText(offset, offset2, offset3, "liquid.lavapop", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
         }

         if (random.nextInt(200) == 0) {
            world.applyText(coordX, coordY, coordZ, "liquid.lava", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
         }
      }

      if (random.nextInt(10) == 0 && World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())) {
         Material material = world.getBlockState(blockPos.resolveBlockPos4(2)).getBlock().getMaterial();
         if (!material.isActiveAsFlag() && !material.isActive2()) {
            double offset4 = coordX + (double)random.nextFloat();
            double offset5 = coordY - 1.05;
            double offset6 = coordZ + (double)random.nextFloat();
            if (this.coreItemEntry == Material.coreItemEntry1) {
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.DRIP_WATER, offset4, offset5, offset6, 0.0, 0.0, 0.0, new int[0]);
            } else {
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.DRIP_LAVA, offset4, offset5, offset6, 0.0, 0.0, 0.0, new int[0]);
            }
         }
      }
   }

   public static float computeFactor(int count) {
      int count2 = count;
      if (count >= 8) {
         count2 = 0;
      }

      return (float)(count2 + 1) / 9.0F;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockLiquid(Material material) {
      super(material);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.processAction(true);
   }
}
