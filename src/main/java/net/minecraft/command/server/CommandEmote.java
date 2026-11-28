package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandEmote extends CommandBase {

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length <= 0) {
         throw new WrongUsageException("commands.me.usage", new Object[0]);
      } else {
         IChatComponent component = getChatComponentFromNthArg(commandSender, strings, 0, !(commandSender instanceof EntityPlayer));
         MinecraftServer.getServer()
            .getConfigurationManager()
            .sendChatMsg(new ChatComponentTranslation("chat.type.emote", new Object[]{commandSender.getDisplayName(), component}));
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.me.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public String getCommandName() {
      return "me";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
   }
}
