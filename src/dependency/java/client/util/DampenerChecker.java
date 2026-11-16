package client.util;

import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpecialBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class DampenerChecker extends Listener97Entry {
   public boolean enabled3;
   public boolean enabled;
   public boolean enabled4;
   public boolean enabled2;
   public boolean enabled5;

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   @Override
   public int computeCount(BaseFloatEntry[] baseFloatEntryArray, Entity entity, BaseFloatEntry baseFloatEntry, BaseFloatEntry baseFloatEntry2, float factor) {
      int count = 0;
      byte byteValue = 0;
      if (this.computeCountByEntity(entity, baseFloatEntry.count, baseFloatEntry.count5 + 1, baseFloatEntry.count3) == 1) {
         byteValue = 1;
      }

      BaseFloatEntry baseFloatEntry3 = this.resolveBaseFloatEntryByEntity(entity, baseFloatEntry.count, baseFloatEntry.count5, baseFloatEntry.count3 + 1, byteValue);
      BaseFloatEntry baseFloatEntry4 = this.resolveBaseFloatEntryByEntity(entity, baseFloatEntry.count - 1, baseFloatEntry.count5, baseFloatEntry.count3, byteValue);
      BaseFloatEntry baseFloatEntry5 = this.resolveBaseFloatEntryByEntity(entity, baseFloatEntry.count + 1, baseFloatEntry.count5, baseFloatEntry.count3, byteValue);
      BaseFloatEntry baseFloatEntry6 = this.resolveBaseFloatEntryByEntity(entity, baseFloatEntry.count, baseFloatEntry.count5, baseFloatEntry.count3 - 1, byteValue);
      if (baseFloatEntry3 != null && !baseFloatEntry3.enabled && baseFloatEntry3.computeFactorByBaseFloatEntry(baseFloatEntry2) < factor) {
         baseFloatEntryArray[count++] = baseFloatEntry3;
      }

      if (baseFloatEntry4 != null && !baseFloatEntry4.enabled && baseFloatEntry4.computeFactorByBaseFloatEntry(baseFloatEntry2) < factor) {
         baseFloatEntryArray[count++] = baseFloatEntry4;
      }

      if (baseFloatEntry5 != null && !baseFloatEntry5.enabled && baseFloatEntry5.computeFactorByBaseFloatEntry(baseFloatEntry2) < factor) {
         baseFloatEntryArray[count++] = baseFloatEntry5;
      }

      if (baseFloatEntry6 != null && !baseFloatEntry6.enabled && baseFloatEntry6.computeFactorByBaseFloatEntry(baseFloatEntry2) < factor) {
         baseFloatEntryArray[count++] = baseFloatEntry6;
      }

      return count;
   }

   public static int computeCount(
      IBlockAccess iBlockAccess, Entity entity, int count, int count2, int count3, int count4, int count5, int count6, boolean flag, boolean flag2, boolean flag3
   ) {
      boolean flag4 = false;
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = count; index < count + count4; index++) {
         for (int index2 = count2; index2 < count2 + count5; index2++) {
            for (int index3 = count3; index3 < count3 + count6; index3++) {
               auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, index2, index3);
               Block block = iBlockAccess.getBlockState(auxiliaryBlockPosAdapter).getBlock();
               if (block.getMaterial() != Material.coreItemEntry) {
                  if (block == Blocks.block30 || block == Blocks.block14) {
                     flag4 = true;
                  } else if (block != Blocks.blockFlowingLava && block != Blocks.blockLava2) {
                     if (!flag3 && block instanceof BlockDarkOakDoor && block.getMaterial() == Material.material) {
                        return 0;
                     }
                  } else {
                     if (flag) {
                        return -1;
                     }

                     flag4 = true;
                  }

                  if (entity.worldObj.getBlockState(auxiliaryBlockPosAdapter).getBlock() instanceof SpecialBlockAdapter) {
                     if (!(entity.worldObj.getIBlockState2(entity.posX, entity.posY, entity.posZ).getBlock() instanceof SpecialBlockAdapter)
                        && !(entity.worldObj.getIBlockState2(entity.posX, entity.posY - 1.0, entity.posZ).getBlock() instanceof SpecialBlockAdapter)) {
                        return -3;
                     }
                  } else if (!block.matchesIBlockAccess(iBlockAccess, auxiliaryBlockPosAdapter)
                     && (!flag2 || !(block instanceof BlockDarkOakDoor) || block.getMaterial() != Material.material)) {
                     if (block instanceof BlockAcaciaFence || block instanceof BlockAcaciaFenceGate || block instanceof BlockCobblestoneWall) {
                        return -3;
                     }

                     if (block == Blocks.block30 || block == Blocks.block14) {
                        return -4;
                     }

                     if (block.getMaterial() != Material.material29) {
                        return 0;
                     }

                     if (!entity.isWet()) {
                        return -2;
                     }
                  }
               }
            }
         }
      }

      return flag4 ? 2 : 1;
   }

   public void setEnabled2(boolean enabled6) {
      this.enabled2 = enabled6;
   }

   @Override
   public void applyIBlockAccess(IBlockAccess iBlockAccess, Entity entity) {
      super.applyIBlockAccess(iBlockAccess, entity);
      this.enabled5 = this.enabled4;
   }

   private BaseFloatEntry resolveBaseFloatEntryByEntity(Entity entity, int count, int count2, int count3, int count4) {
      int count5 = count2;
      BaseFloatEntry baseFloatEntry = null;
      int count6 = this.computeCountByEntity(entity, count, count2, count3);
      if (count6 == 2) {
         return this.resolveBaseFloatEntry(count, count2, count3);
      } else {
         if (count6 == 1) {
            baseFloatEntry = this.resolveBaseFloatEntry(count, count2, count3);
         }

         if (baseFloatEntry == null && count4 > 0 && count6 != -3 && count6 != -4 && this.computeCountByEntity(entity, count, count2 + count4, count3) == 1) {
            baseFloatEntry = this.resolveBaseFloatEntry(count, count2 + count4, count3);
            count5 = count2 + count4;
         }

         if (baseFloatEntry != null) {
            int count7 = 0;

            int count8;
            for (count8 = 0; count5 > 0; baseFloatEntry = this.resolveBaseFloatEntry(count, count5, count3)) {
               count8 = this.computeCountByEntity(entity, count, count5 - 1, count3);
               if (this.enabled4 && count8 == -1) {
                  return null;
               }

               if (count8 != 1) {
                  break;
               }

               if (count7++ >= entity.getCount3()) {
                  return null;
               }

               if (--count5 <= 0) {
                  return null;
               }
            }

            if (count8 == -2) {
               return null;
            }
         }

         return baseFloatEntry;
      }
   }

   private int computeCountByEntity(Entity entity, int count, int count2, int count3) {
      return computeCount(this.iBlockAccess, entity, count, count2, count3, this.count, this.count3, this.count2, this.enabled4, this.enabled, this.enabled3);
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void setEnabled4(boolean enabled6) {
      this.enabled4 = enabled6;
   }

   @Override
   public BaseFloatEntry resolveBaseFloatEntry(Entity entity, double posX, double posY, double posZ) {
      return this.resolveBaseFloatEntry(
         MathHelper.ceiling_double_int(posX - (double)(entity.width / 2.0F)),
         MathHelper.ceiling_double_int(posY),
         MathHelper.ceiling_double_int(posZ - (double)(entity.width / 2.0F))
      );
   }

   @Override
   public void updateState() {
      super.updateState();
      this.enabled4 = this.enabled5;
   }

   public void setEnabled(boolean enabled6) {
      this.enabled = enabled6;
   }

   @Override
   public BaseFloatEntry resolveBaseFloatEntry(Entity entity) {
      int count;
      if (this.enabled2 && entity.isInWater()) {
         count = (int)entity.getBoundingBox2().minY;
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(MathHelper.ceiling_double_int(entity.posX), count, MathHelper.ceiling_double_int(entity.posZ));

         for (Block block = this.iBlockAccess.getBlockState(auxiliaryBlockPosAdapter).getBlock();
            block == Blocks.blockFlowingLava || block == Blocks.blockLava2;
            block = this.iBlockAccess.getBlockState(auxiliaryBlockPosAdapter).getBlock()
         ) {
            auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(MathHelper.ceiling_double_int(entity.posX), ++count, MathHelper.ceiling_double_int(entity.posZ));
         }

         this.enabled4 = false;
      } else {
         count = MathHelper.ceiling_double_int(entity.getBoundingBox2().minY + 0.5);
      }

      return this.resolveBaseFloatEntry(
         MathHelper.ceiling_double_int(entity.getBoundingBox2().minX), count, MathHelper.ceiling_double_int(entity.getBoundingBox2().minZ)
      );
   }

   public void setEnabled3(boolean enabled6) {
      this.enabled3 = enabled6;
   }
}
