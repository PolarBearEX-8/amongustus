package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class CommandClone extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3
         ? getTabCompletionCoordinate(strings, 0, pos)
         : (
            strings.length > 3 && strings.length <= 6
               ? getTabCompletionCoordinate(strings, 3, pos)
               : (
                  strings.length > 6 && strings.length <= 9
                     ? getTabCompletionCoordinate(strings, 6, pos)
                     : (
                        strings.length == 10
                           ? getListOfStringsMatchingLastWord(strings, new String[]{"replace", "masked", "filtered"})
                           : (
                              strings.length == 11
                                 ? getListOfStringsMatchingLastWord(strings, new String[]{"normal", "force", "move"})
                                 : (
                                    strings.length == 12 && "filtered".equals(strings[9])
                                       ? getListOfStringsMatchingLastWord(strings, Block.blockRegistry.getKeys())
                                       : null
                                 )
                           )
                     )
               )
         );
   }

   @Override
   public String getCommandName() {
      return "clone";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.clone.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 9) {
         throw new WrongUsageException("commands.clone.usage", new Object[0]);
      } else {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos pos = parseBlockPos(commandSender, strings, 0, false);
         BlockPos pos2 = parseBlockPos(commandSender, strings, 3, false);
         BlockPos pos3 = parseBlockPos(commandSender, strings, 6, false);
         StructureBoundingBox structureBoundingBox = new StructureBoundingBox(pos, pos2);
         StructureBoundingBox structureBoundingBox2 = new StructureBoundingBox(pos3, pos3.add(structureBoundingBox.getLength()));
         int n = structureBoundingBox.getXSize() * structureBoundingBox.getYSize() * structureBoundingBox.getZSize();
         if (n > 32768) {
            throw new CommandException("commands.clone.tooManyBlocks", new Object[]{n, 32768});
         } else {
            boolean flag = false;
            Block block = null;
            int n2 = -1;
            if ((strings.length < 11 || !strings[10].equals("force") && !strings[10].equals("move")) && structureBoundingBox.intersectsWith(structureBoundingBox2)) {
               throw new CommandException("commands.clone.noOverlap", new Object[0]);
            } else {
               if (strings.length >= 11 && strings[10].equals("move")) {
                  flag = true;
               }

               if (structureBoundingBox.minY >= 0 && structureBoundingBox.maxY < 256 && structureBoundingBox2.minY >= 0 && structureBoundingBox2.maxY < 256) {
                  World entityWorld = commandSender.getEntityWorld();
                  if (entityWorld.isAreaLoaded(structureBoundingBox) && entityWorld.isAreaLoaded(structureBoundingBox2)) {
                     boolean flag2 = false;
                     if (strings.length >= 10) {
                        if (strings[9].equals("masked")) {
                           flag2 = true;
                        } else if (strings[9].equals("filtered")) {
                           if (strings.length < 12) {
                              throw new WrongUsageException("commands.clone.usage", new Object[0]);
                           }

                           block = getBlockByText(commandSender, strings[11]);
                           if (strings.length >= 13) {
                              n2 = parseInt(strings[12], 0, 15);
                           }
                        }
                     }

                     ArrayList list = Lists.newArrayList();
                     ArrayList list2 = Lists.newArrayList();
                     ArrayList list3 = Lists.newArrayList();
                     LinkedList list4 = Lists.newLinkedList();
                     BlockPos pos4 = new BlockPos(structureBoundingBox2.minX - structureBoundingBox.minX, structureBoundingBox2.minY - structureBoundingBox.minY, structureBoundingBox2.minZ - structureBoundingBox.minZ);

                     for (int i = structureBoundingBox.minZ; i <= structureBoundingBox.maxZ; i++) {
                        for (int j = structureBoundingBox.minY; j <= structureBoundingBox.maxY; j++) {
                           for (int k = structureBoundingBox.minX; k <= structureBoundingBox.maxX; k++) {
                              BlockPos pos5 = new BlockPos(k, j, i);
                              BlockPos pos6 = pos5.add(pos4);
                              IBlockState blockState = entityWorld.getBlockState(pos5);
                              if ((!flag2 || blockState.getBlock() != Blocks.air)
                                 && (block == null || blockState.getBlock() == block && (n2 < 0 || blockState.getBlock().getMetaFromState(blockState) == n2))) {
                                 TileEntity tileEntity = entityWorld.getTileEntity(pos5);
                                 if (tileEntity != null) {
                                    NBTTagCompound tag = new NBTTagCompound();
                                    tileEntity.writeToNBT(tag);
                                    list2.add(new CommandClone.StaticCloneData(pos6, blockState, tag));
                                    list4.addLast(pos5);
                                 } else if (!blockState.getBlock().isFullBlock() && !blockState.getBlock().isFullCube()) {
                                    list3.add(new CommandClone.StaticCloneData(pos6, blockState, (NBTTagCompound)null));
                                    list4.addFirst(pos5);
                                 } else {
                                    list.add(new CommandClone.StaticCloneData(pos6, blockState, (NBTTagCompound)null));
                                    list4.addLast(pos5);
                                 }
                              }
                           }
                        }
                     }

                     if (flag) {
                        for (BlockPos pos7 : (Iterable<BlockPos>)(list4)) {
                           TileEntity tileEntity2 = entityWorld.getTileEntity(pos7);
                           if (tileEntity2 instanceof IInventory) {
                              ((IInventory)tileEntity2).clear();
                           }

                           entityWorld.setBlockState(pos7, Blocks.barrier.getDefaultState(), 2);
                        }

                        for (BlockPos pos8 : (Iterable<BlockPos>)(list4)) {
                           entityWorld.setBlockState(pos8, Blocks.air.getDefaultState(), 3);
                        }
                     }

                     ArrayList list5 = Lists.newArrayList();
                     list5.addAll(list);
                     list5.addAll(list2);
                     list5.addAll(list3);
                     List list6 = Lists.reverse(list5);

                     for (CommandClone.StaticCloneData staticCloneData : (Iterable<CommandClone.StaticCloneData>)(list6)) {
                        TileEntity tileEntity3 = entityWorld.getTileEntity(staticCloneData.pos);
                        if (tileEntity3 instanceof IInventory) {
                           ((IInventory)tileEntity3).clear();
                        }

                        entityWorld.setBlockState(staticCloneData.pos, Blocks.barrier.getDefaultState(), 2);
                     }

                     n = 0;

                     for (CommandClone.StaticCloneData staticCloneData2 : (Iterable<CommandClone.StaticCloneData>)(list5)) {
                        if (entityWorld.setBlockState(staticCloneData2.pos, staticCloneData2.blockState, 2)) {
                           n++;
                        }
                     }

                     for (CommandClone.StaticCloneData staticCloneData3 : (Iterable<CommandClone.StaticCloneData>)(list2)) {
                        TileEntity tileEntity4 = entityWorld.getTileEntity(staticCloneData3.pos);
                        if (staticCloneData3.compound != null && tileEntity4 != null) {
                           staticCloneData3.compound.setInteger("x", staticCloneData3.pos.getX());
                           staticCloneData3.compound.setInteger("y", staticCloneData3.pos.getY());
                           staticCloneData3.compound.setInteger("z", staticCloneData3.pos.getZ());
                           tileEntity4.readFromNBT(staticCloneData3.compound);
                           tileEntity4.markDirty();
                        }

                        entityWorld.setBlockState(staticCloneData3.pos, staticCloneData3.blockState, 2);
                     }

                     for (CommandClone.StaticCloneData staticCloneData4 : (Iterable<CommandClone.StaticCloneData>)(list6)) {
                        entityWorld.notifyNeighborsRespectDebug(staticCloneData4.pos, staticCloneData4.blockState.getBlock());
                     }

                     List pendingBlockUpdates2 = entityWorld.getPendingBlockUpdates2(structureBoundingBox, false);
                     if (pendingBlockUpdates2 != null) {
                        for (NextTickListEntry nextTickListEntry : (Iterable<NextTickListEntry>)(pendingBlockUpdates2)) {
                           if (structureBoundingBox.isVecInside(nextTickListEntry.position)) {
                              entityWorld.scheduleBlockUpdate(
                                 nextTickListEntry.position.add(pos4), nextTickListEntry.getBlock(), (int)(nextTickListEntry.scheduledTime - entityWorld.getWorldInfo().getWorldTotalTime()), nextTickListEntry.priority
                              );
                           }
                        }
                     }

                     if (n <= 0) {
                        throw new CommandException("commands.clone.failed", new Object[0]);
                     } else {
                        commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, n);
                        notifyOperators(commandSender, this, "commands.clone.success", new Object[]{n});
                     }
                  } else {
                     throw new CommandException("commands.clone.outOfWorld", new Object[0]);
                  }
               } else {
                  throw new CommandException("commands.clone.outOfWorld", new Object[0]);
               }
            }
         }
      }
   }


   public static class StaticCloneData {
      public final BlockPos pos;
      public final IBlockState blockState;
      public final NBTTagCompound compound;

      public StaticCloneData(BlockPos pos2, IBlockState state, NBTTagCompound tag) {
         this.pos = pos2;
         this.blockState = state;
         this.compound = tag;
      }
   }
}
