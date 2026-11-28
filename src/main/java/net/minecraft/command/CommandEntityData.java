package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class CommandEntityData extends CommandBase {

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.entitydata.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "entitydata";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.entitydata.usage", new Object[0]);
      } else {
         Entity entity = getEntity(commandSender, strings[0]);
         if (entity instanceof EntityPlayer) {
            throw new CommandException("commands.entitydata.noPlayers", new Object[]{entity.getDisplayName()});
         } else {
            NBTTagCompound tag = new NBTTagCompound();
            entity.writeToNBT(tag);
            NBTTagCompound tag2 = (NBTTagCompound)tag.copy();

            NBTTagCompound tag3;
            try {
               tag3 = JsonToNBT.getTagFromJson(getChatComponentFromNthArg(commandSender, strings, 1).getUnformattedText());
            } catch (NBTException e) {
               throw new CommandException("commands.entitydata.tagError", new Object[]{e.getMessage()});
            }

            tag3.removeTag("UUIDMost");
            tag3.removeTag("UUIDLeast");
            tag.merge(tag3);
            if (tag.equals(tag2)) {
               throw new CommandException("commands.entitydata.failed", new Object[]{tag.toString()});
            } else {
               entity.readFromNBT(tag);
               notifyOperators(commandSender, this, "commands.entitydata.success", new Object[]{tag.toString()});
            }
         }
      }
   }
}
