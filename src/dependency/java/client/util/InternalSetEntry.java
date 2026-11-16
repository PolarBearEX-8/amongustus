package client.util;

import client.event.PostRenderConsumer;
import client.setting.ExtraOptionType;
import client.setting.MovementThresholdProperty;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public final class InternalSetEntry {
   public static final int count = (int)Math.pow(17.0, 2.0);
   public final Set set = Sets.newHashSet();
   public final Map map = new HashMap();
   public int count2 = Integer.MAX_VALUE;
   public int count3 = Integer.MAX_VALUE;
   public int count4;

   private static BlockPos resolveBlockPos(World world, int count5, int count6) {
      AlertNotificationSound loggerEntry = world.getLoggerEntry2(count5, count6);
      int count7 = count5 * 16 + world.random.nextInt(16);
      int count8 = count6 * 16 + world.random.nextInt(16);
      int count9 = MathHelper.computeCount(loggerEntry.computeCount(new BlockPos(count7, 0, count8)) + 1, 16);
      int count10 = world.random.nextInt(count9 > 0 ? count9 : loggerEntry.getCount() + 16 - 1);
      return new BlockPos(count7, count10, count8);
   }

   public static void applyWorld(World world, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, int count5, int count6, int count7, int count8, Random random) {
      List list = biomeIDIsOutOfBoundsManager.resolveList(MovementThresholdProperty.CREATURE);
      if (!list.isEmpty()) {
         while (random.nextFloat() < biomeIDIsOutOfBoundsManager.getFactor()) {
            ClassHolder classHolder = (ClassHolder)SmoothSystem.resolveDampenerSorter(world.random, list);
            int count9 = classHolder.count2 + random.nextInt(1 + classHolder.count3 - classHolder.count2);
            PostRenderConsumer postRenderConsumer = null;
            int count10 = count5 + random.nextInt(count7);
            int count11 = count6 + random.nextInt(count8);
            int count12 = count10;
            int count13 = count11;

            for (int index = 0; index < count9; index++) {
               boolean flag = false;

               for (int index2 = 0; !flag && index2 < 4; index2++) {
                  BlockPos blockPos = world.resolveBlockPos(new BlockPos(count10, 0, count11));
                  if (matchesExtraOptionType(ExtraOptionType.ON_GROUND, world, blockPos)) {
                     EntityMob entityMob;
                     try {
                        entityMob = (EntityMob)classHolder.memberRef.getConstructor(World.class).newInstance(world);
                     } catch (Exception exception) {
                        exception.printStackTrace();
                        continue;
                     }

                     entityMob.applyDoubleByDouble(
                        (double)((float)count10 + 0.5F), (double)blockPos.getY(), (double)((float)count11 + 0.5F), random.nextFloat() * 360.0F, 0.0F
                     );
                     world.matchesEntity(entityMob);
                     postRenderConsumer = entityMob.resolvePostRenderConsumer(world.resolveGoldenOre(new BlockPos(entityMob)), postRenderConsumer);
                     flag = true;
                  }

                  count10 += random.nextInt(5) - random.nextInt(5);

                  for (count11 += random.nextInt(5) - random.nextInt(5);
                     count10 < count5 || count10 >= count5 + count7 || count11 < count6 || count11 >= count6 + count7;
                     count11 = count13 + random.nextInt(5) - random.nextInt(5)
                  ) {
                     count10 = count12 + random.nextInt(5) - random.nextInt(5);
                  }
               }
            }
         }
      }
   }

   private static ExtendedBlockPosAdapter resolveExtendedBlockPosAdapter(World world, int count5, int count6, ExtendedBlockPosAdapter extendedBlockPosAdapter) {
      AlertNotificationSound loggerEntry = world.getLoggerEntry2(count5, count6);
      int count7 = count5 * 16 + world.random.nextInt(16);
      int count8 = count6 * 16 + world.random.nextInt(16);
      int count9 = MathHelper.computeCount(loggerEntry.computeCount(count7 & 15, count8 & 15) + 1, 16);
      int count10 = world.random.nextInt(count9 > 0 ? count9 : loggerEntry.getCount() + 16 - 1);
      extendedBlockPosAdapter.applyIndex(count7, count10, count8);
      return extendedBlockPosAdapter;
   }

   public int computeCount(WorldServer worldServer, boolean flag, boolean flag2, boolean flag3) {
      if (!flag && !flag2) {
         return 0;
      } else {
         boolean flag4 = true;
         EntityPlayer player = null;
         if (worldServer.itemList7.size() == 1) {
            player = (EntityPlayer)worldServer.itemList7.get(0);
            if (this.set.size() > 0 && player != null && player.count7 == this.count2 && player.count8 == this.count3) {
               flag4 = false;
            }
         }

         if (flag4) {
            this.set.clear();
            int count5 = 0;

            for (EntityPlayer player2 : (Iterable<EntityPlayer>) (Iterable) worldServer.itemList7) {
               if (!player2.isActive23()) {
                  int count6 = MathHelper.ceiling_double_int(player2.posX / 16.0);
                  int count7 = MathHelper.ceiling_double_int(player2.posZ / 16.0);
                  byte byteValue = 8;

                  for (int index = -8; index <= byteValue; index++) {
                     for (int index2 = -byteValue; index2 <= byteValue; index2++) {
                        boolean flag5 = index == -byteValue || index == byteValue || index2 == -byteValue || index2 == byteValue;
                        InterpolationCollector interpolationCollector = new InterpolationCollector(index + count6, index2 + count7);
                        if (!this.set.contains(interpolationCollector)) {
                           count5++;
                           if (!flag5 && worldServer.getInternalListEntry().matchesInterpolationCollector(interpolationCollector)) {
                              this.set.add(interpolationCollector);
                           }
                        }
                     }
                  }
               }
            }

            this.count4 = count5;
            if (player != null) {
               this.count2 = player.count7;
               this.count3 = player.count8;
            }
         }

         int count8 = 0;
         BlockPos blockPos = worldServer.getBlockPos();
         ExtendedBlockPosAdapter extendedBlockPosAdapter = new ExtendedBlockPosAdapter(0, 0, 0);
         new AuxiliaryBlockPosAdapter();

         for (MovementThresholdProperty movementThresholdProperty : MovementThresholdProperty.values()) {
            if ((!movementThresholdProperty.isEnabled() || flag2) && (movementThresholdProperty.isEnabled() || flag) && (!movementThresholdProperty.isEnabled2() || flag3)) {
               int count9 = worldServer.computeCount(movementThresholdProperty.getMemberRef());
               int count10 = movementThresholdProperty.getCount() * this.count4 / count;
               if (count9 <= count10) {
                  innerLoop:
                  for (InterpolationCollector interpolationCollector2 : (Iterable<InterpolationCollector>) (Iterable) this.set) {
                     ExtendedBlockPosAdapter extendedBlockPosAdapter2 = resolveExtendedBlockPosAdapter(worldServer, interpolationCollector2.count3, interpolationCollector2.count2, extendedBlockPosAdapter);
                     int coordX = extendedBlockPosAdapter2.getX();
                     int coordY = extendedBlockPosAdapter2.getY();
                     int coordZ = extendedBlockPosAdapter2.getZ();
                     if (!worldServer.getBlockState(extendedBlockPosAdapter2).getBlock().isActive2()) {
                        int count11 = 0;

                        for (int index3 = 0; index3 < 3; index3++) {
                           int count12 = coordX;
                           int count13 = coordY;
                           int count14 = coordZ;
                           ClassHolder classHolder = null;
                           PostRenderConsumer postRenderConsumer = null;

                           for (int index4 = 0; index4 < 4; index4++) {
                              count12 += worldServer.random.nextInt(6) - worldServer.random.nextInt(6);
                              count13 += worldServer.random.nextInt(1) - worldServer.random.nextInt(1);
                              count14 += worldServer.random.nextInt(6) - worldServer.random.nextInt(6);
                              BlockPos blockPos2 = new BlockPos(count12, count13, count14);
                              float factor = (float)count12 + 0.5F;
                              float factor2 = (float)count14 + 0.5F;
                              if (!worldServer.checkDouble((double)factor, (double)count13, (double)factor2, 24.0)
                                 && blockPos.computeValue((double)factor, (double)count13, (double)factor2) >= 576.0) {
                                 if (classHolder == null) {
                                    classHolder = worldServer.resolveClassHolder(movementThresholdProperty, blockPos2);
                                    if (classHolder == null) {
                                       break;
                                    }
                                 }

                                 if (worldServer.matchesMovementThresholdProperty(movementThresholdProperty, classHolder, blockPos2)
                                    && matchesExtraOptionType(SecondaryHashMapEntry.get(classHolder.memberRef), worldServer, blockPos2)) {
                                    EntityMob entityMob;
                                    try {
                                       entityMob = (EntityMob)this.map.get(classHolder.memberRef);
                                       if (entityMob == null) {
                                          entityMob = (EntityMob)classHolder.memberRef.getConstructor(World.class).newInstance(worldServer);
                                          this.map.put(classHolder.memberRef, entityMob);
                                       }
                                    } catch (Exception exception) {
                                       exception.printStackTrace();
                                       return count8;
                                    }

                                    entityMob.applyDoubleByDouble((double)factor, (double)count13, (double)factor2, worldServer.random.nextFloat() * 360.0F, 0.0F);
                                    if (entityMob.nextInt() && entityMob.isActive23()) {
                                       this.map.remove(classHolder.memberRef);
                                       postRenderConsumer = entityMob.resolvePostRenderConsumer(worldServer.resolveGoldenOre(new BlockPos(entityMob)), postRenderConsumer);
                                       if (entityMob.isActive23()) {
                                          count11++;
                                          worldServer.matchesEntity(entityMob);
                                       }

                                       int count15 = entityMob.getCount6();
                                       if (count11 >= count15) {
                                          continue innerLoop;
                                       }
                                    }

                                    count8 += count11;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return count8;
      }
   }

   public static boolean matchesExtraOptionType(ExtraOptionType extraOptionType, World world, BlockPos blockPos) {
      if (!world.getInternalListEntry().checkBlockPos(blockPos)) {
         return false;
      } else if (extraOptionType == null) {
         return false;
      } else {
         Block block = world.getBlockState(blockPos).getBlock();
         if (extraOptionType == ExtraOptionType.IN_WATER) {
            return block.getMaterial().isActive2()
               && world.getBlockState(blockPos.down()).getBlock().getMaterial().isActive2()
               && !world.getBlockState(blockPos.up()).getBlock().isActive2();
         } else if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())) {
            return false;
         } else {
            Block block2 = world.getIBlockState(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()).getBlock();
            return block2 != Blocks.block95
               && block2 != Blocks.block145
               && !block.isActive2()
               && !block.getMaterial().isActive2()
               && !world.getBlockState(blockPos.up()).getBlock().isActive2();
         }
      }
   }
}
