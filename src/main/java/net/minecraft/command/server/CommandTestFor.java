package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTestFor extends CommandBase {

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.testfor.usage", new Object[0]);
      } else {
         Entity entity = getEntity(commandSender, strings[0]);
         NBTTagCompound tag = null;
         if (strings.length >= 2) {
            try {
               tag = JsonToNBT.getTagFromJson(buildString(strings, 1));
            } catch (NBTException e) {
               throw new CommandException("commands.testfor.tagError", new Object[]{e.getMessage()});
            }
         }

         if (tag != null) {
            NBTTagCompound tag2 = new NBTTagCompound();
            entity.writeToNBT(tag2);
            if (!NBTUtil.areNBTEquals(tag, tag2, true)) {
               throw new CommandException("commands.testfor.failure", new Object[]{entity.getName()});
            }
         }

         notifyOperators(commandSender, this, "commands.testfor.success", new Object[]{entity.getName()});
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.testfor.usage";
   }

   @Override
   public String getCommandName() {
      return "testfor";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }
}
