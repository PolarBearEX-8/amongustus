package net.minecraft.command;

import java.util.List;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandBlockData extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length > 0 && strings.length <= 3 ? getTabCompletionCoordinate(strings, 0, pos) : null;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.blockdata.usage";
   }

   @Override
   public String getCommandName() {
      return "blockdata";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 4) {
         throw new WrongUsageException("commands.blockdata.usage", new Object[0]);
      } else {
         commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos pos = parseBlockPos(commandSender, strings, 0, false);
         World entityWorld = commandSender.getEntityWorld();
         if (!entityWorld.isBlockLoaded(pos)) {
            throw new CommandException("commands.blockdata.outOfWorld", new Object[0]);
         } else {
            TileEntity tileEntity = entityWorld.getTileEntity(pos);
            if (tileEntity == null) {
               throw new CommandException("commands.blockdata.notValid", new Object[0]);
            } else {
               NBTTagCompound tag = new NBTTagCompound();
               tileEntity.writeToNBT(tag);
               NBTTagCompound tag2 = (NBTTagCompound)tag.copy();

               NBTTagCompound tag3;
               try {
                  tag3 = JsonToNBT.getTagFromJson(getChatComponentFromNthArg(commandSender, strings, 3).getUnformattedText());
               } catch (NBTException e) {
                  throw new CommandException("commands.blockdata.tagError", new Object[]{e.getMessage()});
               }

               tag.merge(tag3);
               tag.setInteger("x", pos.getX());
               tag.setInteger("y", pos.getY());
               tag.setInteger("z", pos.getZ());
               if (tag.equals(tag2)) {
                  throw new CommandException("commands.blockdata.failed", new Object[]{tag.toString()});
               } else {
                  tileEntity.readFromNBT(tag);
                  tileEntity.markDirty();
                  entityWorld.markBlockForUpdate(pos);
                  commandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
                  notifyOperators(commandSender, this, "commands.blockdata.success", new Object[]{tag.toString()});
               }
            }
         }
      }
   }
}
