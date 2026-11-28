package net.minecraft.command.server;

import net.minecraft.command.CommandResultStats;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandSetBlock extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 4) {
         throw new WrongUsageException("commands.setblock.usage", new Object[0]);
      } else {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos pos = parseBlockPos(commandSender, strings, 0, false);
         Block blockByText = CommandBase.getBlockByText(commandSender, strings[3]);
         int n = 0;
         if (strings.length >= 5) {
            n = parseInt(strings[4], 0, 15);
         }

         World entityWorld = commandSender.getEntityWorld();
         if (!entityWorld.isBlockLoaded(pos)) {
            throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
         } else {
            NBTTagCompound tag = new NBTTagCompound();
            boolean flag = false;
            if (strings.length >= 7 && blockByText.hasTileEntity()) {
               String unformattedText = getChatComponentFromNthArg(commandSender, strings, 6).getUnformattedText();

               try {
                  tag = JsonToNBT.getTagFromJson(unformattedText);
                  flag = true;
               } catch (NBTException e) {
                  throw new CommandException("commands.setblock.tagError", new Object[]{e.getMessage()});
               }
            }

            if (strings.length >= 6) {
               if (strings[5].equals("destroy")) {
                  entityWorld.destroyBlock(pos, true);
                  if (blockByText == Blocks.air) {
                     notifyOperators(commandSender, this, "commands.setblock.success", new Object[0]);
                     return;
                  }
               } else if (strings[5].equals("keep") && !entityWorld.isAirBlock(pos)) {
                  throw new CommandException("commands.setblock.noChange", new Object[0]);
               }
            }

            TileEntity tileEntity = entityWorld.getTileEntity(pos);
            if (tileEntity != null) {
               if (tileEntity instanceof IInventory) {
                  ((IInventory)tileEntity).clear();
               }

               entityWorld.setBlockState(pos, Blocks.air.getDefaultState(), blockByText == Blocks.air ? 2 : 4);
            }

            IBlockState stateFromMeta = blockByText.getStateFromMeta(n);
            if (!entityWorld.setBlockState(pos, stateFromMeta, 2)) {
               throw new CommandException("commands.setblock.noChange", new Object[0]);
            } else {
               if (flag) {
                  TileEntity tileEntity2 = entityWorld.getTileEntity(pos);
                  if (tileEntity2 != null) {
                     tag.setInteger("x", pos.getX());
                     tag.setInteger("y", pos.getY());
                     tag.setInteger("z", pos.getZ());
                     tileEntity2.readFromNBT(tag);
                  }
               }

               entityWorld.notifyNeighborsRespectDebug(pos, stateFromMeta.getBlock());
               commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
               notifyOperators(commandSender, this, "commands.setblock.success", new Object[0]);
            }
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3
         ? getTabCompletionCoordinate(strings, 0, pos)
         : (
            strings.length == 4
               ? getListOfStringsMatchingLastWord(strings, Block.blockRegistry.getKeys())
               : (strings.length == 6 ? getListOfStringsMatchingLastWord(strings, new String[]{"replace", "destroy", "keep"}) : null)
         );
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.setblock.usage";
   }

   @Override
   public String getCommandName() {
      return "setblock";
   }
}
