package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityChicken;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityZombie;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PathfindManagerAdapter extends PathfindManager {
   public DampenerChecker dampenerChecker;
   public boolean enabled;

   @Override
   public void updateState2() {
      super.updateState2();
      if (this.enabled) {
         if (this.world
            .checkIndex2(
               MathHelper.ceiling_double_int(this.entityMob.posX),
               MathHelper.ceiling_double_int((double)((int)(this.entityMob.getBoundingBox2().minY + 0.5))),
               MathHelper.ceiling_double_int(this.entityMob.posZ)
            )) {
            return;
         }

         for (int index = 0; index < this.interpolationConverter.getCount2(); index++) {
            BaseFloatEntry baseFloatEntry = this.interpolationConverter.resolveBaseFloatEntry(index);
            if (this.world.checkIndex2(baseFloatEntry.count, baseFloatEntry.count5, baseFloatEntry.count3)) {
               this.interpolationConverter.setCount2(index - 1);
               return;
            }
         }
      }
   }

   private boolean matchesVec3(int count, int count2, int count3, int count4, int count5, int count6, Vec3 vec, double offset, double offset2) {
      int count7 = count - count4 / 2;
      int count8 = count3 - count6 / 2;
      if (!this.matchesVecByVec3(count7, count2, count8, count4, count5, count6, vec, offset, offset2)) {
         return false;
      } else {
         for (int index = count7; index < count7 + count4; index++) {
            for (int index2 = count8; index2 < count8 + count6; index2++) {
               double offset3 = (double)index + 0.5 - vec.xCoord;
               double offset4 = (double)index2 + 0.5 - vec.zCoord;
               if (offset3 * offset + offset4 * offset2 >= 0.0) {
                  Material material = this.world.getBlockState(new BlockPos(index, count2 - 1, index2)).getBlock().getMaterial();
                  if (material == Material.coreItemEntry) {
                     return false;
                  }

                  if (material == Material.coreItemEntry1 && !this.entityMob.isInWater()) {
                     return false;
                  }

                  if (material == Material.material29) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public boolean isActive3() {
      return this.dampenerChecker.isEnabled3();
   }

   public void setEnabled2(boolean enabled2) {
      this.dampenerChecker.setEnabled2(enabled2);
   }

   @Override
   public OWKNOWSManagerEntry getOWKNOWSManagerEntry() {
      this.dampenerChecker = new DampenerChecker();
      this.dampenerChecker.setEnabled3(true);
      return new OWKNOWSManagerEntry(this.dampenerChecker);
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      this.dampenerChecker.setEnabled3(enabledByFlag);
   }

   public void setEnabled(boolean enabled2) {
      this.dampenerChecker.setEnabled(enabled2);
   }

   private int getCount() {
      if (this.entityMob.isInWater() && this.isActive5()) {
         int minY = (int)this.entityMob.getBoundingBox2().minY;
         Block block = this.world
            .getBlockState(new BlockPos(MathHelper.ceiling_double_int(this.entityMob.posX), minY, MathHelper.ceiling_double_int(this.entityMob.posZ)))
            .getBlock();
         int count = 0;

         while (block == Blocks.blockFlowingLava || block == Blocks.blockLava2) {
            block = this.world
               .getBlockState(
                  new BlockPos(MathHelper.ceiling_double_int(this.entityMob.posX), ++minY, MathHelper.ceiling_double_int(this.entityMob.posZ))
               )
               .getBlock();
            if (++count > 16) {
               return (int)this.entityMob.getBoundingBox2().minY;
            }
         }

         return minY;
      } else {
         return (int)(this.entityMob.getBoundingBox2().minY + 0.5);
      }
   }

   @Override
   public boolean isActive() {
      return this.entityMob.onGround
         || this.isActive5() && this.isActiveAsFlag()
         || this.entityMob.isSneaking() && this.entityMob instanceof EntityZombie && this.entityMob.ridingEntity instanceof EntityChicken;
   }

   public void setEnabled3(boolean enabled2) {
      this.dampenerChecker.setEnabled4(enabled2);
   }

   private boolean matchesVecByVec3(int count, int count2, int count3, int count4, int count5, int count6, Vec3 vec, double offset, double offset2) {
      for (Object blockPos_o : BlockPos.resolveIterable(new BlockPos(count, count2, count3), new BlockPos(count + count4 - 1, count2 + count5 - 1, count3 + count6 - 1))) {
         BlockPos blockPos = (BlockPos)blockPos_o;
         double offset3 = (double)blockPos.getX() + 0.5 - vec.xCoord;
         double offset4 = (double)blockPos.getZ() + 0.5 - vec.zCoord;
         if (offset3 * offset + offset4 * offset2 >= 0.0 && !this.world.getBlockState(blockPos).getBlock().matchesIBlockAccess(this.world, blockPos)) {
            return false;
         }
      }

      return true;
   }

   public boolean isActive5() {
      return this.dampenerChecker.isEnabled2();
   }

   public void setEnabled4(boolean enabled2) {
      this.enabled = enabled2;
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(this.entityMob.posX, (double)this.getCount(), this.entityMob.posZ);
   }

   @Override
   public boolean matchesVec3(Vec3 vec, Vec3 vec2, int count, int count2, int count3) {
      int count4 = MathHelper.ceiling_double_int(vec.xCoord);
      int count5 = MathHelper.ceiling_double_int(vec.zCoord);
      double offset = vec2.xCoord - vec.xCoord;
      double offset2 = vec2.zCoord - vec.zCoord;
      double offset3 = offset * offset + offset2 * offset2;
      if (offset3 < 1.0E-8) {
         return false;
      } else {
         double offset4 = 1.0 / Math.sqrt(offset3);
         double offset5 = offset * offset4;
         double offset6 = offset2 * offset4;
         int count6 = count + 2;
         int count7 = count3 + 2;
         if (!this.matchesVec3(count4, (int)vec.yCoord, count5, count6, count2, count7, vec, offset5, offset6)) {
            return false;
         } else {
            count6 -= 2;
            count7 -= 2;
            double offset7 = 1.0 / Math.abs(offset5);
            double offset8 = 1.0 / Math.abs(offset6);
            double offset9 = (double)(count4 * 1) - vec.xCoord;
            double offset10 = (double)(count5 * 1) - vec.zCoord;
            if (offset5 >= 0.0) {
               offset9++;
            }

            if (offset6 >= 0.0) {
               offset10++;
            }

            double offset11 = offset9 / offset5;
            double offset12 = offset10 / offset6;
            int count8 = offset5 < 0.0 ? -1 : 1;
            int count9 = offset6 < 0.0 ? -1 : 1;
            int count10 = MathHelper.ceiling_double_int(vec2.xCoord);
            int count11 = MathHelper.ceiling_double_int(vec2.zCoord);
            int count12 = count10 - count4;
            int count13 = count11 - count5;

            while (count12 * count8 > 0 || count13 * count9 > 0) {
               if (offset11 < offset12) {
                  offset11 += offset7;
                  count4 += count8;
                  count12 = count10 - count4;
               } else {
                  offset12 += offset8;
                  count5 += count9;
                  count13 = count11 - count5;
               }

               if (!this.matchesVec3(count4, (int)vec.yCoord, count5, count6, count2, count7, vec, offset5, offset6)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean isActive4() {
      return this.dampenerChecker.isEnabled4();
   }

   public PathfindManagerAdapter(EntityMob entityMob, World world) {
      super(entityMob, world);
   }
}
