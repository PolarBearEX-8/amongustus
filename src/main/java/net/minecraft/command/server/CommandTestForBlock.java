package net.minecraft.command.server;

import net.minecraft.command.CommandResultStats;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandTestForBlock extends CommandBase {

   @Override
   public String getCommandName() {
      return "testforblock";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.testforblock.usage";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 4) {
         throw new WrongUsageException("commands.testforblock.usage", new Object[0]);
      } else {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos pos = parseBlockPos(commandSender, strings, 0, false);
         Block blockFromName = Block.getBlockFromName(strings[3]);
         if (blockFromName == null) {
            throw new NumberInvalidException("commands.setblock.notFound", new Object[]{strings[3]});
         } else {
            int n = -1;
            if (strings.length >= 5) {
               n = parseInt(strings[4], -1, 15);
            }

            World entityWorld = commandSender.getEntityWorld();
            if (!entityWorld.isBlockLoaded(pos)) {
               throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
            } else {
               NBTTagCompound tag = new NBTTagCompound();
               boolean flag = false;
               if (strings.length >= 6 && blockFromName.hasTileEntity()) {
                  String unformattedText = getChatComponentFromNthArg(commandSender, strings, 5).getUnformattedText();

                  try {
                     tag = JsonToNBT.getTagFromJson(unformattedText);
                     flag = true;
                  } catch (NBTException e) {
                     throw new CommandException("commands.setblock.tagError", new Object[]{e.getMessage()});
                  }
               }

               IBlockState blockState = entityWorld.getBlockState(pos);
               Block block = blockState.getBlock();
               if (block != blockFromName) {
                  throw new CommandException(
                     "commands.testforblock.failed.tile",
                     new Object[]{pos.getX(), pos.getY(), pos.getZ(), block.getLocalizedName(), blockFromName.getLocalizedName()}
                  );
               } else {
                  if (n > -1) {
                     int metaFromState = blockState.getBlock().getMetaFromState(blockState);
                     if (metaFromState != n) {
                        throw new CommandException("commands.testforblock.failed.data", new Object[]{pos.getX(), pos.getY(), pos.getZ(), metaFromState, n});
                     }
                  }

                  if (flag) {
                     TileEntity tileEntity = entityWorld.getTileEntity(pos);
                     if (tileEntity == null) {
                        throw new CommandException("commands.testforblock.failed.tileEntity", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
                     }

                     NBTTagCompound tag2 = new NBTTagCompound();
                     tileEntity.writeToNBT(tag2);
                     if (!NBTUtil.areNBTEquals(tag, tag2, true)) {
                        throw new CommandException("commands.testforblock.failed.nbt", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
                     }
                  }

                  commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
                  notifyOperators(commandSender, this, "commands.testforblock.success", new Object[]{pos.getX(), pos.getY(), pos.getZ()});
               }
            }
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3
         ? getTabCompletionCoordinate(strings, 0, pos)
         : (strings.length == 4 ? getListOfStringsMatchingLastWord(strings, Block.blockRegistry.getKeys()) : null);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }
}
