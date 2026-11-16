package net.minecraft.item;

import client.setting.CustomOptionType;
import client.setting.MovementOffsetProperty;
import client.util.AimQueue;
import client.util.AlertNotificationSound;
import client.util.DimensionStringEntry;
import client.util.IBlockState;
import client.util.Packet;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import java.util.List;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemFilledMap extends ItemMapBase {
   @Override
   public void processAction(ItemStack stack, World world, Entity entity, int count, boolean flag) {
      if (!world.state) {
         DimensionStringEntry dimensionStringEntry = this.resolveDimensionStringEntryByItemStack(stack, world);
         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            dimensionStringEntry.applyEntityPlayer(player, stack);
         }

         if (flag) {
            this.applyWorldByWorld(world, entity, dimensionStringEntry);
         }
      }
   }

   @Override
   public void processAction(ItemStack stack, World world, EntityPlayer player) {
      if (stack.canExecute() && stack.getStackTagCompound().matchesText("map_is_scaling")) {
         DimensionStringEntry dimensionStringEntry = Items.filledMap.resolveDimensionStringEntryByItemStack(stack, world);
         stack.applyIndexByIndex(world.computeCount("map"));
         DimensionStringEntry dimensionStringEntry2 = new DimensionStringEntry("map_" + stack.getIndexOffset());
         dimensionStringEntry2.byteFlag2 = (byte)(dimensionStringEntry.byteFlag2 + 1);
         if (dimensionStringEntry2.byteFlag2 > 4) {
            dimensionStringEntry2.byteFlag2 = 4;
         }

         dimensionStringEntry2.applyDouble((double)dimensionStringEntry.count2, (double)dimensionStringEntry.count, dimensionStringEntry2.byteFlag2);
         dimensionStringEntry2.byteFlag = dimensionStringEntry.byteFlag;
         dimensionStringEntry2.updateState();
         world.applyText("map_" + stack.getIndexOffset(), dimensionStringEntry2);
      }
   }

   public DimensionStringEntry resolveDimensionStringEntryByItemStack(ItemStack stack, World world) {
      String text = "map_" + stack.getIndexOffset();
      DimensionStringEntry dimensionStringEntry = (DimensionStringEntry)world.resolveAdapterStringEntry(DimensionStringEntry.class, text);
      if (dimensionStringEntry == null && !world.state) {
         stack.applyIndexByIndex(world.computeCount("map"));
         String text2 = "map_" + stack.getIndexOffset();
         dimensionStringEntry = new DimensionStringEntry(text2);
         dimensionStringEntry.byteFlag2 = 3;
         dimensionStringEntry.applyDouble((double)world.getRandomSeedManager().getCount5(), (double)world.getRandomSeedManager().getCount2(), dimensionStringEntry.byteFlag2);
         dimensionStringEntry.byteFlag = (byte)world.secondaryFloatArrayEntry.getCountAsIndex();
         dimensionStringEntry.updateState();
         world.applyText(text2, dimensionStringEntry);
      }

      return dimensionStringEntry;
   }

   public static DimensionStringEntry resolveDimensionStringEntry(int count, World world) {
      String text = "map_" + count;
      DimensionStringEntry dimensionStringEntry = (DimensionStringEntry)world.resolveAdapterStringEntry(DimensionStringEntry.class, text);
      if (dimensionStringEntry == null) {
         dimensionStringEntry = new DimensionStringEntry(text);
         world.applyText(text, dimensionStringEntry);
      }

      return dimensionStringEntry;
   }

   public void applyWorldByWorld(World world, Entity entity, DimensionStringEntry dimensionStringEntry) {
      if (world.secondaryFloatArrayEntry.getCountAsIndex() == dimensionStringEntry.byteFlag && entity instanceof EntityPlayer) {
         int count = 1 << dimensionStringEntry.byteFlag2;
         int count2 = dimensionStringEntry.count2;
         int count3 = dimensionStringEntry.count;
         int count4 = MathHelper.ceiling_double_int(entity.posX - (double)count2) / count + 64;
         int count5 = MathHelper.ceiling_double_int(entity.posZ - (double)count3) / count + 64;
         int count6 = 128 / count;
         if (world.secondaryFloatArrayEntry.isEnabled()) {
            count6 /= 2;
         }

         AimQueue aimQueue = dimensionStringEntry.resolveAimQueueByEntityPlayer((EntityPlayer)entity);
         aimQueue.count++;
         boolean flag = false;

         for (int index = count4 - count6 + 1; index < count4 + count6; index++) {
            if ((index & 15) == (aimQueue.count & 15) || flag) {
               flag = false;
               double offset = 0.0;

               for (int index2 = count5 - count6 - 1; index2 < count5 + count6; index2++) {
                  if (index >= 0 && index2 >= -1 && index < 128 && index2 < 128) {
                     int count7 = index - count4;
                     int count8 = index2 - count5;
                     boolean flag2 = count7 * count7 + count8 * count8 > (count6 - 2) * (count6 - 2);
                     int count9 = (count2 / count + index - 64) * count;
                     int count10 = (count3 / count + index2 - 64) * count;
                     HashMultiset hashMultiset = HashMultiset.create();
                     AlertNotificationSound loggerEntry = world.getLoggerEntry(new BlockPos(count9, 0, count10));
                     if (!loggerEntry.canExecute()) {
                        int count11 = count9 & 15;
                        int count12 = count10 & 15;
                        int count13 = 0;
                        double offset2 = 0.0;
                        if (world.secondaryFloatArrayEntry.isEnabled()) {
                           int count14 = count9 + count10 * 231871;
                           if ((count14 * count14 * 31287121 + count14 * 11 >> 20 & 1) == 0) {
                              hashMultiset.add(
                                 Blocks.block115
                                    .processAction3(
                                       Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.DIRT)
                                    ),
                                 10
                              );
                           } else {
                              hashMultiset.add(
                                 Blocks.stone
                                    .processAction3(Blocks.stone.getDefaultState().resolveIBlockState(BlockStone.immutableSetEntry, CustomOptionType.STONE)),
                                 100
                              );
                           }

                           offset2 = 100.0;
                        } else {
                           AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

                           for (int index3 = 0; index3 < count; index3++) {
                              for (int index4 = 0; index4 < count; index4++) {
                                 int count15 = loggerEntry.computeCount(index3 + count11, index4 + count12) + 1;
                                 IBlockState defaultState = Blocks.air.getDefaultState();
                                 if (count15 > 1) {
                                    do {
                                       defaultState = loggerEntry.resolveIBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index3 + count11, --count15, index4 + count12));
                                    } while (defaultState.getBlock().processAction3(defaultState) == MapColor.mapColor19 && count15 > 0);

                                    if (count15 > 0 && defaultState.getBlock().getMaterial().isActive2()) {
                                       int count16 = count15 - 1;

                                       Block block;
                                       do {
                                          block = loggerEntry.resolveBlock(index3 + count11, count16--, index4 + count12);
                                          count13++;
                                       } while (count16 > 0 && block.getMaterial().isActive2());
                                    }
                                 }

                                 offset2 += (double)count15 / (double)(count * count);
                                 hashMultiset.add(defaultState.getBlock().processAction3(defaultState));
                              }
                           }
                        }

                        int count17 = count13 / (count * count);
                        double offset3 = (offset2 - offset) * 4.0 / (double)(count + 4) + ((double)(index + index2 & 1) - 0.5) * 0.4;
                        byte byteValue = 1;
                        if (offset3 > 0.6) {
                           byteValue = 2;
                        }

                        if (offset3 < -0.6) {
                           byteValue = 0;
                        }

                        MapColor first = (MapColor)Iterables.getFirst(Multisets.copyHighestCountFirst(hashMultiset), MapColor.mapColor19);
                        if (first == MapColor.mapColor9) {
                           double offset4 = (double)count17 * 0.1 + (double)(index + index2 & 1) * 0.2;
                           byteValue = 1;
                           if (offset4 < 0.5) {
                              byteValue = 2;
                           }

                           if (offset4 > 0.9) {
                              byteValue = 0;
                           }
                        }

                        offset = offset2;
                        if (index2 >= 0 && count7 * count7 + count8 * count8 < count6 * count6 && (!flag2 || (index + index2 & 1) != 0)) {
                           byte byteValue2 = dimensionStringEntry.byteFlagArray[index + index2 * 128];
                           byte byteValue3 = (byte)(first.count * 4 + byteValue);
                           if (byteValue2 != byteValue3) {
                              dimensionStringEntry.byteFlagArray[index + index2 * 128] = byteValue3;
                              dimensionStringEntry.applyIndex(index, index2);
                              flag = true;
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
   public Packet resolvePacketByItemStack(ItemStack stack, World world, EntityPlayer player) {
      return this.resolveDimensionStringEntryByItemStack(stack, world).resolvePacketByItemStack(stack, world, player);
   }

   @Override
   public void processAction(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      DimensionStringEntry dimensionStringEntry = this.resolveDimensionStringEntryByItemStack(stack, player.worldObj);
      if (flag) {
         if (dimensionStringEntry == null) {
            list.add("Unknown map");
         } else {
            list.add("Scaling at 1:" + (1 << dimensionStringEntry.byteFlag2));
            list.add("(Level " + dimensionStringEntry.byteFlag2 + "/" + 4 + ")");
         }
      }
   }

   public ItemFilledMap() {
      this.processAction(true);
   }
}
