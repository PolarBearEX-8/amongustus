package client.util;

import client.setting.CombatNumberProperty;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.command.ICommandSender;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RayTraceProfiler extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3
         ? resolveListByTextArray(textArray, 0, blockPos)
         : (
            textArray.length > 3 && textArray.length <= 6
               ? resolveListByTextArray(textArray, 3, blockPos)
               : (
                  textArray.length > 6 && textArray.length <= 9
                     ? resolveListByTextArray(textArray, 6, blockPos)
                     : (
                        textArray.length == 10
                           ? resolveList(textArray, new String[]{"replace", "masked", "filtered"})
                           : (
                              textArray.length == 11
                                 ? resolveList(textArray, new String[]{"normal", "force", "move"})
                                 : (textArray.length == 12 && "filtered".equals(textArray[9]) ? resolveList(textArray, Block.iterableExtensionBridge.getSet()) : null)
                           )
                     )
               )
         );
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 9) {
         throw new AimDispatcher("commands.clone.usage", new Object[0]);
      } else {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, 0);
         BlockPos blockPos = resolveBlockPos(iCommandSender, textArray, 0, false);
         BlockPos blockPos2 = resolveBlockPos(iCommandSender, textArray, 3, false);
         BlockPos blockPos3 = resolveBlockPos(iCommandSender, textArray, 6, false);
         DistanceAggregator distanceAggregator = new DistanceAggregator(blockPos, blockPos2);
         DistanceAggregator distanceAggregator2 = new DistanceAggregator(blockPos3, blockPos3.getBlockPos2(distanceAggregator.getVec3i()));
         int count = distanceAggregator.getCountAsIndex() * distanceAggregator.getCount2() * distanceAggregator.getCount();
         if (count > 32768) {
            throw new InternalExceptionAdapter("commands.clone.tooManyBlocks", new Object[]{count, 32768});
         } else {
            boolean flag = false;
            Block block = null;
            int count2 = -1;
            if ((textArray.length < 11 || !textArray[10].equals("force") && !textArray[10].equals("move")) && distanceAggregator.matchesDistanceAggregator(distanceAggregator2)) {
               throw new InternalExceptionAdapter("commands.clone.noOverlap", new Object[0]);
            } else {
               if (textArray.length >= 11 && textArray[10].equals("move")) {
                  flag = true;
               }

               if (distanceAggregator.count6 >= 0 && distanceAggregator.count2 < 256 && distanceAggregator2.count6 >= 0 && distanceAggregator2.count2 < 256) {
                  World worldObj = iCommandSender.getWorldObj();
                  if (worldObj.matchesDistanceAggregator(distanceAggregator) && worldObj.matchesDistanceAggregator(distanceAggregator2)) {
                     boolean flag2 = false;
                     if (textArray.length >= 10) {
                        if (textArray[9].equals("masked")) {
                           flag2 = true;
                        } else if (textArray[9].equals("filtered")) {
                           if (textArray.length < 12) {
                              throw new AimDispatcher("commands.clone.usage", new Object[0]);
                           }

                           block = resolveBlock(iCommandSender, textArray[11]);
                           if (textArray.length >= 13) {
                              count2 = computeCount(textArray[12], 0, 15);
                           }
                        }
                     }

                     ArrayList list = Lists.newArrayList();
                     ArrayList list2 = Lists.newArrayList();
                     ArrayList list3 = Lists.newArrayList();
                     LinkedList list4 = Lists.newLinkedList();
                     BlockPos blockPos4 = new BlockPos(distanceAggregator2.count3 - distanceAggregator.count3, distanceAggregator2.count6 - distanceAggregator.count6, distanceAggregator2.count4 - distanceAggregator.count4);

                     for (int index = distanceAggregator.count4; index <= distanceAggregator.count; index++) {
                        for (int index2 = distanceAggregator.count6; index2 <= distanceAggregator.count2; index2++) {
                           for (int index3 = distanceAggregator.count3; index3 <= distanceAggregator.count5; index3++) {
                              BlockPos blockPos5 = new BlockPos(index3, index2, index);
                              BlockPos blockPos6 = blockPos5.getBlockPos2(blockPos4);
                              IBlockState blockState = worldObj.getBlockState(blockPos5);
                              if ((!flag2 || blockState.getBlock() != Blocks.air)
                                 && (block == null || blockState.getBlock() == block && (count2 < 0 || blockState.getBlock().getIndex(blockState) == count2))) {
                                 TileEntity tileEntity = worldObj.resolveTileEntity(blockPos5);
                                 if (tileEntity != null) {
                                    NBTTagCompound tagCompound = new NBTTagCompound();
                                    tileEntity.applyNBTTagCompound(tagCompound);
                                    list2.add(new BlockPosRecord(blockPos6, blockState, tagCompound));
                                    list4.addLast(blockPos5);
                                 } else if (!blockState.getBlock().isState18() && !blockState.getBlock().processAction14()) {
                                    list3.add(new BlockPosRecord(blockPos6, blockState, (NBTTagCompound)null));
                                    list4.addFirst(blockPos5);
                                 } else {
                                    list.add(new BlockPosRecord(blockPos6, blockState, (NBTTagCompound)null));
                                    list4.addLast(blockPos5);
                                 }
                              }
                           }
                        }
                     }

                     if (flag) {
                        for (BlockPos blockPos7 : (Iterable<BlockPos>)(list4)) {
                           TileEntity tileEntity2 = worldObj.resolveTileEntity(blockPos7);
                           if (tileEntity2 instanceof IInventory) {
                              ((IInventory)tileEntity2).updateState2();
                           }

                           worldObj.matchesBlockPos(blockPos7, Blocks.block145.getDefaultState(), 2);
                        }

                        for (BlockPos blockPos8 : (Iterable<BlockPos>)(list4)) {
                           worldObj.matchesBlockPos(blockPos8, Blocks.air.getDefaultState(), 3);
                        }
                     }

                     ArrayList list5 = Lists.newArrayList();
                     list5.addAll(list);
                     list5.addAll(list2);
                     list5.addAll(list3);
                     List list6 = Lists.reverse(list5);

                     for (BlockPosRecord blockPosRecord : (Iterable<BlockPosRecord>)(list6)) {
                        TileEntity tileEntity3 = worldObj.resolveTileEntity(blockPosRecord.blockPos);
                        if (tileEntity3 instanceof IInventory) {
                           ((IInventory)tileEntity3).updateState2();
                        }

                        worldObj.matchesBlockPos(blockPosRecord.blockPos, Blocks.block145.getDefaultState(), 2);
                     }

                     int count3 = 0;

                     for (BlockPosRecord blockPosRecord2 : (Iterable<BlockPosRecord>)(list5)) {
                        if (worldObj.matchesBlockPos(blockPosRecord2.blockPos, blockPosRecord2.iBlockState, 2)) {
                           count3++;
                        }
                     }

                     for (BlockPosRecord blockPosRecord3 : (Iterable<BlockPosRecord>)(list2)) {
                        TileEntity tileEntity4 = worldObj.resolveTileEntity(blockPosRecord3.blockPos);
                        if (blockPosRecord3.nBTTagCompound != null && tileEntity4 != null) {
                           blockPosRecord3.nBTTagCompound.put("x", blockPosRecord3.blockPos.getX());
                           blockPosRecord3.nBTTagCompound.put("y", blockPosRecord3.blockPos.getY());
                           blockPosRecord3.nBTTagCompound.put("z", blockPosRecord3.blockPos.getZ());
                           tileEntity4.applyNBTTagCompoundByNBTTagCompound(blockPosRecord3.nBTTagCompound);
                           tileEntity4.processAction5();
                        }

                        worldObj.matchesBlockPos(blockPosRecord3.blockPos, blockPosRecord3.iBlockState, 2);
                     }

                     for (BlockPosRecord blockPosRecord4 : (Iterable<BlockPosRecord>)(list6)) {
                        worldObj.applyBlockPos3(blockPosRecord4.blockPos, blockPosRecord4.iBlockState.getBlock());
                     }

                     List list7 = worldObj.resolveList(distanceAggregator, false);
                     if (list7 != null) {
                        for (ExtendedComparableAdapter extendedComparableAdapter : (Iterable<ExtendedComparableAdapter>)(list7)) {
                           if (distanceAggregator.matchesVec3i(extendedComparableAdapter.blockPos)) {
                              BlockPos blockPos9 = extendedComparableAdapter.blockPos.getBlockPos2(blockPos4);
                              worldObj.applyBlockPos(blockPos9, extendedComparableAdapter.getBlock(), (int)(extendedComparableAdapter.timestamp - worldObj.getRandomSeedManager().getTimestamp2()), extendedComparableAdapter.count);
                           }
                        }
                     }

                     if (count3 <= 0) {
                        throw new InternalExceptionAdapter("commands.clone.failed", new Object[0]);
                     } else {
                        iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_BLOCKS, count3);
                        applyICommandSender(iCommandSender, this, "commands.clone.success", new Object[]{count3});
                     }
                  } else {
                     throw new InternalExceptionAdapter("commands.clone.outOfWorld", new Object[0]);
                  }
               } else {
                  throw new InternalExceptionAdapter("commands.clone.outOfWorld", new Object[0]);
               }
            }
         }
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.clone.usage";
   }

   @Override
   public String getText() {
      return "clone";
   }
}
