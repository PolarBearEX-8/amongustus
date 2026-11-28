package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandFill extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3
         ? getTabCompletionCoordinate(strings, 0, pos)
         : (
            strings.length > 3 && strings.length <= 6
               ? getTabCompletionCoordinate(strings, 3, pos)
               : (
                  strings.length == 7
                     ? getListOfStringsMatchingLastWord(strings, Block.blockRegistry.getKeys())
                     : (
                        strings.length == 9
                           ? getListOfStringsMatchingLastWord(strings, new String[]{"replace", "destroy", "keep", "hollow", "outline"})
                           : (strings.length == 10 && "replace".equals(strings[8]) ? getListOfStringsMatchingLastWord(strings, Block.blockRegistry.getKeys()) : null)
                     )
               )
         );
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 7) {
         throw new WrongUsageException("commands.fill.usage", new Object[0]);
      } else {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos pos = parseBlockPos(commandSender, strings, 0, false);
         BlockPos pos2 = parseBlockPos(commandSender, strings, 3, false);
         Block blockByText = CommandBase.getBlockByText(commandSender, strings[6]);
         int n = 0;
         if (strings.length >= 8) {
            n = parseInt(strings[7], 0, 15);
         }

         BlockPos pos3 = new BlockPos(Math.min(pos.getX(), pos2.getX()), Math.min(pos.getY(), pos2.getY()), Math.min(pos.getZ(), pos2.getZ()));
         BlockPos pos4 = new BlockPos(Math.max(pos.getX(), pos2.getX()), Math.max(pos.getY(), pos2.getY()), Math.max(pos.getZ(), pos2.getZ()));
         int n2 = (pos4.getX() - pos3.getX() + 1) * (pos4.getY() - pos3.getY() + 1) * (pos4.getZ() - pos3.getZ() + 1);
         if (n2 > 32768) {
            throw new CommandException("commands.fill.tooManyBlocks", new Object[]{n2, 32768});
         } else if (pos3.getY() >= 0 && pos4.getY() < 256) {
            World entityWorld = commandSender.getEntityWorld();

            for (int i = pos3.getZ(); i < pos4.getZ() + 16; i += 16) {
               for (int j = pos3.getX(); j < pos4.getX() + 16; j += 16) {
                  if (!entityWorld.isBlockLoaded(new BlockPos(j, pos4.getY() - pos3.getY(), i))) {
                     throw new CommandException("commands.fill.outOfWorld", new Object[0]);
                  }
               }
            }

            NBTTagCompound tag = new NBTTagCompound();
            boolean flag = false;
            if (strings.length >= 10 && blockByText.hasTileEntity()) {
               String unformattedText = getChatComponentFromNthArg(commandSender, strings, 9).getUnformattedText();

               try {
                  tag = JsonToNBT.getTagFromJson(unformattedText);
                  flag = true;
               } catch (NBTException e) {
                  throw new CommandException("commands.fill.tagError", new Object[]{e.getMessage()});
               }
            }

            ArrayList list = Lists.newArrayList();
            n2 = 0;

            for (int k = pos3.getZ(); k <= pos4.getZ(); k++) {
               for (int m = pos3.getY(); m <= pos4.getY(); m++) {
                  for (int idx = pos3.getX(); idx <= pos4.getX(); idx++) {
                     BlockPos pos5 = new BlockPos(idx, m, k);
                     if (strings.length >= 9) {
                        if (!strings[8].equals("outline") && !strings[8].equals("hollow")) {
                           if (strings[8].equals("destroy")) {
                              entityWorld.destroyBlock(pos5, true);
                           } else if (strings[8].equals("keep")) {
                              if (!entityWorld.isAirBlock(pos5)) {
                                 continue;
                              }
                           } else if (strings[8].equals("replace") && !blockByText.hasTileEntity()) {
                              if (strings.length > 9) {
                                 Block blockByText2 = CommandBase.getBlockByText(commandSender, strings[9]);
                                 if (entityWorld.getBlockState(pos5).getBlock() != blockByText2) {
                                    continue;
                                 }
                              }

                              if (strings.length > 10) {
                                 int n3 = CommandBase.parseInt(strings[10]);
                                 IBlockState blockState = entityWorld.getBlockState(pos5);
                                 if (blockState.getBlock().getMetaFromState(blockState) != n3) {
                                    continue;
                                 }
                              }
                           }
                        } else if (idx != pos3.getX()
                           && idx != pos4.getX()
                           && m != pos3.getY()
                           && m != pos4.getY()
                           && k != pos3.getZ()
                           && k != pos4.getZ()) {
                           if (strings[8].equals("hollow")) {
                              entityWorld.setBlockState(pos5, Blocks.air.getDefaultState(), 2);
                              list.add(pos5);
                           }
                           continue;
                        }
                     }

                     TileEntity tileEntity = entityWorld.getTileEntity(pos5);
                     if (tileEntity != null) {
                        if (tileEntity instanceof IInventory) {
                           ((IInventory)tileEntity).clear();
                        }

                        entityWorld.setBlockState(pos5, Blocks.barrier.getDefaultState(), blockByText == Blocks.barrier ? 2 : 4);
                     }

                     if (entityWorld.setBlockState(pos5, blockByText.getStateFromMeta(n), 2)) {
                        list.add(pos5);
                        n2++;
                        if (flag) {
                           TileEntity tileEntity2 = entityWorld.getTileEntity(pos5);
                           if (tileEntity2 != null) {
                              tag.setInteger("x", pos5.getX());
                              tag.setInteger("y", pos5.getY());
                              tag.setInteger("z", pos5.getZ());
                              tileEntity2.readFromNBT(tag);
                           }
                        }
                     }
                  }
               }
            }

            for (BlockPos pos6 : (Iterable<BlockPos>)(list)) {
               entityWorld.notifyNeighborsRespectDebug(pos6, entityWorld.getBlockState(pos6).getBlock());
            }

            if (n2 <= 0) {
               throw new CommandException("commands.fill.failed", new Object[0]);
            } else {
               commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, n2);
               notifyOperators(commandSender, this, "commands.fill.success", new Object[]{n2});
            }
         } else {
            throw new CommandException("commands.fill.outOfWorld", new Object[0]);
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.fill.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "fill";
   }
}
