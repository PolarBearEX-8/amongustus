package client.util;

import client.setting.EnumFacingPositiveType;
import client.setting.EnumFacingXType;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.Blocks;
import net.minecraft.block.InternalBlockPosAdapter;
import net.minecraft.block.InternalBlockPosEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldServer;

public class WorldServerEntry {
   public final WorldServer worldServer;
   public final Random random;
   public final InternalFloatEntry internalFloatEntry = new InternalFloatEntry();
   public final List list = Lists.newArrayList();

   public boolean matchesEntity(Entity entity) {
      double offset = -1.0;
      int count = MathHelper.ceiling_double_int(entity.posX);
      int count2 = MathHelper.ceiling_double_int(entity.posY);
      int count3 = MathHelper.ceiling_double_int(entity.posZ);
      int count4 = count;
      int count5 = count3;
      int count6 = 0;
      int count7 = this.random.nextInt(4);
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = count - 16; index <= count + 16; index++) {
         double offset2 = (double)index + 0.5 - entity.posX;

         for (int index2 = count3 - 16; index2 <= count3 + 16; index2++) {
            double offset3 = (double)index2 + 0.5 - entity.posZ;

            innerLoop:
            for (int index3 = this.worldServer.getCount3() - 1; index3 >= 0; index3--) {
               if (this.worldServer.isAirBlock(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, index3, index2))) {
                  while (index3 > 0 && this.worldServer.isAirBlock(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, index3 - 1, index2))) {
                     index3--;
                  }

                  for (int index4 = count7; index4 < count7 + 4; index4++) {
                     int count8 = index4 % 2;
                     int count9 = 1 - count8;
                     if (index4 % 4 >= 2) {
                        count8 = -count8;
                        count9 = -count9;
                     }

                     for (int index5 = 0; index5 < 3; index5++) {
                        for (int index6 = 0; index6 < 4; index6++) {
                           for (int index7 = -1; index7 < 4; index7++) {
                              int count10 = index + (index6 - 1) * count8 + index5 * count9;
                              int count11 = index3 + index7;
                              int count12 = index2 + (index6 - 1) * count9 - index5 * count8;
                              auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count10, count11, count12);
                              if (index7 < 0 && !this.worldServer.getBlockState(auxiliaryBlockPosAdapter).getBlock().getMaterial().canExecute2()
                                 || index7 >= 0 && !this.worldServer.isAirBlock(auxiliaryBlockPosAdapter)) {
                                 continue innerLoop;
                              }
                           }
                        }
                     }

                     double offset4 = (double)index3 + 0.5 - entity.posY;
                     double offset5 = offset2 * offset2 + offset4 * offset4 + offset3 * offset3;
                     if (offset < 0.0 || offset5 < offset) {
                        offset = offset5;
                        count4 = index;
                        count2 = index3;
                        count5 = index2;
                        count6 = index4 % 4;
                     }
                  }
               }
            }
         }
      }

      if (offset < 0.0) {
         for (int index8 = count - 16; index8 <= count + 16; index8++) {
            double offset6 = (double)index8 + 0.5 - entity.posX;

            for (int index9 = count3 - 16; index9 <= count3 + 16; index9++) {
               double offset7 = (double)index9 + 0.5 - entity.posZ;

               scanLoop:
               for (int index10 = this.worldServer.getCount3() - 1; index10 >= 0; index10--) {
                  if (this.worldServer.isAirBlock(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index8, index10, index9))) {
                     while (index10 > 0 && this.worldServer.isAirBlock(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index8, index10 - 1, index9))) {
                        index10--;
                     }

                     for (int index11 = count7; index11 < count7 + 2; index11++) {
                        int count13 = index11 % 2;
                        int count14 = 1 - count13;

                        for (int index12 = 0; index12 < 4; index12++) {
                           for (int index13 = -1; index13 < 4; index13++) {
                              int count15 = index8 + (index12 - 1) * count13;
                              int count16 = index10 + index13;
                              int count17 = index9 + (index12 - 1) * count14;
                              auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count15, count16, count17);
                              if (index13 < 0 && !this.worldServer.getBlockState(auxiliaryBlockPosAdapter).getBlock().getMaterial().canExecute2()
                                 || index13 >= 0 && !this.worldServer.isAirBlock(auxiliaryBlockPosAdapter)) {
                                 continue scanLoop;
                              }
                           }
                        }

                        double offset8 = (double)index10 + 0.5 - entity.posY;
                        double offset9 = offset6 * offset6 + offset8 * offset8 + offset7 * offset7;
                        if (offset < 0.0 || offset9 < offset) {
                           offset = offset9;
                           count4 = index8;
                           count2 = index10;
                           count5 = index9;
                           count6 = index11 % 2;
                        }
                     }
                  }
               }
            }
         }
      }

      int count18 = count4;
      int count19 = count6 % 2;
      int count20 = 1 - count19;
      if (count6 % 4 >= 2) {
         count19 = -count19;
         count20 = -count20;
      }

      if (offset < 0.0) {
         count2 = MathHelper.getInt12(count2, 70, this.worldServer.getCount3() - 10);

         for (int index14 = -1; index14 <= 1; index14++) {
            for (int index15 = 1; index15 < 3; index15++) {
               for (int index16 = -1; index16 < 3; index16++) {
                  int count21 = count18 + (index15 - 1) * count19 + index14 * count20;
                  int count22 = count2 + index16;
                  int count23 = count5 + (index15 - 1) * count20 - index14 * count19;
                  boolean flag = index16 < 0;
                  this.worldServer.matchesBlockPos(new BlockPos(count21, count22, count23), flag ? Blocks.block113.getDefaultState() : Blocks.air.getDefaultState());
               }
            }
         }
      }

      IBlockState blockState = Blocks.blockPortal
         .getDefaultState()
         .resolveIBlockState(BlockPortal.immutableSetEntry, count19 != 0 ? EnumFacingXType.X : EnumFacingXType.Z);

      for (int index17 = 0; index17 < 4; index17++) {
         for (int index18 = 0; index18 < 4; index18++) {
            for (int index19 = -1; index19 < 4; index19++) {
               int count24 = count18 + (index18 - 1) * count19;
               int count25 = count2 + index19;
               int count26 = count5 + (index18 - 1) * count20;
               boolean flag2 = index18 == 0 || index18 == 3 || index19 == -1 || index19 == 3;
               this.worldServer.matchesBlockPos(new BlockPos(count24, count25, count26), flag2 ? Blocks.block113.getDefaultState() : blockState, 2);
            }
         }

         for (int index20 = 0; index20 < 4; index20++) {
            for (int index21 = -1; index21 < 4; index21++) {
               int count27 = count18 + (index20 - 1) * count19;
               int count28 = count2 + index21;
               int count29 = count5 + (index20 - 1) * count20;
               BlockPos blockPos = new BlockPos(count27, count28, count29);
               this.worldServer.applyBlockPosByBlockPos(blockPos, this.worldServer.getBlockState(blockPos).getBlock());
            }
         }
      }

      return true;
   }

   public WorldServerEntry(WorldServer worldServer2) {
      this.worldServer = worldServer2;
      this.random = new Random(worldServer2.getTimestamp());
   }

   public void applyEntityByEntity(Entity entity, float factor) {
      if (this.worldServer.secondaryFloatArrayEntry.getCountAsIndex() != 1) {
         if (!this.matchesEntity(entity, factor)) {
            this.matchesEntity(entity);
            this.matchesEntity(entity, factor);
         }
      } else {
         int count = MathHelper.ceiling_double_int(entity.posX);
         int count2 = MathHelper.ceiling_double_int(entity.posY) - 1;
         int count3 = MathHelper.ceiling_double_int(entity.posZ);

         for (int index = -2; index <= 2; index++) {
            for (int index2 = -2; index2 <= 2; index2++) {
               for (int index3 = -1; index3 < 3; index3++) {
                  int count4 = count + index2 * 1 + index * 0;
                  int count5 = count2 + index3;
                  int count6 = count3 + index2 * 0 - index * 1;
                  boolean flag = index3 < 0;
                  this.worldServer.matchesBlockPos(new BlockPos(count4, count5, count6), flag ? Blocks.block113.getDefaultState() : Blocks.air.getDefaultState());
               }
            }
         }

         entity.applyDoubleByDouble((double)count, (double)count2, (double)count3, entity.rotationYaw, 0.0F);
         entity.motionX = entity.motionY = entity.motionZ = 0.0;
      }
   }

   public boolean matchesEntity(Entity entity, float factor) {
      double offset = -1.0;
      int count = MathHelper.ceiling_double_int(entity.posX);
      int count2 = MathHelper.ceiling_double_int(entity.posZ);
      boolean flag = true;
      Object origin = BlockPos.origin;
      long value = InterpolationCollector.computeTimestamp(count, count2);
      if (this.internalFloatEntry.checkTime(value)) {
         InternalBlockPosAdapter internalBlockPosAdapter = (InternalBlockPosAdapter)this.internalFloatEntry.resolveTarget(value);
         offset = 0.0;
         origin = internalBlockPosAdapter;
         internalBlockPosAdapter.timestamp4 = this.worldServer.getTimestamp2();
         flag = false;
      } else {
         BlockPos blockPos = new BlockPos(entity);

         for (int index = -128; index <= 128; index++) {
            for (int index2 = -128; index2 <= 128; index2++) {
               BlockPos blockPos2 = blockPos.add(index, this.worldServer.getCount3() - 1 - blockPos.getY(), index2);

               while (blockPos2.getY() >= 0) {
                  BlockPos blockPos3 = blockPos2.down();
                  if (this.worldServer.getBlockState(blockPos2).getBlock() == Blocks.blockPortal) {
                     while (this.worldServer.getBlockState(blockPos3 = blockPos2.down()).getBlock() == Blocks.blockPortal) {
                        blockPos2 = blockPos3;
                     }

                     double coordinate = blockPos2.getCoordinate(blockPos);
                     if (offset < 0.0 || coordinate < offset) {
                        offset = coordinate;
                        origin = blockPos2;
                     }
                  }

                  blockPos2 = blockPos3;
               }
            }
         }
      }

      if (offset >= 0.0) {
         if (flag) {
            this.internalFloatEntry.applyTarget(value, new InternalBlockPosAdapter(this, (BlockPos)origin, this.worldServer.getTimestamp2()));
            this.list.add(value);
         }

         double offset2 = (double)((BlockPos)origin).getX() + 0.5;
         ((BlockPos)origin).getY();
         double offset3 = (double)((BlockPos)origin).getZ() + 0.5;
         InternalBlockPosEntry internalBlockPosEntry = Blocks.blockPortal.resolveInternalBlockPosEntry(this.worldServer, (BlockPos)origin);
         boolean flag2 = internalBlockPosEntry.getEnumFacing().getEnumFacingAsEnumFacing().getEnumFacingPositiveType() == EnumFacingPositiveType.NEGATIVE;
         double offset4 = internalBlockPosEntry.getEnumFacing().getEnumFacingXType() == EnumFacingXType.X
            ? (double)internalBlockPosEntry.getBlockPos().getZ()
            : (double)internalBlockPosEntry.getBlockPos().getX();
         double offset5 = (double)(internalBlockPosEntry.getBlockPos().getY() + 1) - entity.getPositionVec2().yCoord * (double)internalBlockPosEntry.getCount2();
         if (flag2) {
            offset4++;
         }

         if (internalBlockPosEntry.getEnumFacing().getEnumFacingXType() == EnumFacingXType.X) {
            offset3 = offset4
               + (1.0 - entity.getPositionVec2().xCoord)
                  * (double)internalBlockPosEntry.getCount()
                  * (double)internalBlockPosEntry.getEnumFacing().getEnumFacingAsEnumFacing().getEnumFacingPositiveType().getCount();
         } else {
            offset2 = offset4
               + (1.0 - entity.getPositionVec2().xCoord)
                  * (double)internalBlockPosEntry.getCount()
                  * (double)internalBlockPosEntry.getEnumFacing().getEnumFacingAsEnumFacing().getEnumFacingPositiveType().getCount();
         }

         float factor2 = 0.0F;
         float factor3 = 0.0F;
         float factor4 = 0.0F;
         float factor5 = 0.0F;
         if (internalBlockPosEntry.getEnumFacing().getEnumFacing5() == entity.getEnumFacing()) {
            factor2 = 1.0F;
            factor3 = 1.0F;
         } else if (internalBlockPosEntry.getEnumFacing().getEnumFacing5() == entity.getEnumFacing().getEnumFacing5()) {
            factor2 = -1.0F;
            factor3 = -1.0F;
         } else if (internalBlockPosEntry.getEnumFacing().getEnumFacing5() == entity.getEnumFacing().getEnumFacingAsEnumFacing()) {
            factor4 = 1.0F;
            factor5 = -1.0F;
         } else {
            factor4 = -1.0F;
            factor5 = 1.0F;
         }

         double motionX = entity.motionX;
         double motionZ = entity.motionZ;
         entity.motionX = motionX * (double)factor2 + motionZ * (double)factor5;
         entity.motionZ = motionX * (double)factor4 + motionZ * (double)factor3;
         entity.rotationYaw = factor - (float)(entity.getEnumFacing().getEnumFacing5().getIndexOffset() * 90) + (float)(internalBlockPosEntry.getEnumFacing().getIndexOffset() * 90);
         entity.applyDoubleByDouble(offset2, offset5, offset3, entity.rotationYaw, entity.rotationPitch);
         return true;
      } else {
         return false;
      }
   }

   public void applyTime(long value) {
      if (value % 100L == 0L) {
         Iterator iterator = this.list.iterator();
         long value2 = value - 300L;

         while (iterator.hasNext()) {
            Long value3 = (Long)iterator.next();
            InternalBlockPosAdapter internalBlockPosAdapter = (InternalBlockPosAdapter)this.internalFloatEntry.resolveTarget(value3);
            if (internalBlockPosAdapter == null || internalBlockPosAdapter.timestamp4 < value2) {
               iterator.remove();
               this.internalFloatEntry.resolveTargetByTime(value3);
            }
         }
      }
   }
}
