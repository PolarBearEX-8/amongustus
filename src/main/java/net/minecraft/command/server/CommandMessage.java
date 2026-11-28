package net.minecraft.command.server;

import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class CommandMessage extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public String getCommandName() {
      return "tell";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.message.usage", new Object[0]);
      } else {
         EntityPlayerMP player = getPlayer(commandSender, strings[0]);
         if (player == commandSender) {
            throw new PlayerNotFoundException("commands.message.sameTarget", new Object[0]);
         } else {
            IChatComponent component = getChatComponentFromNthArg(commandSender, strings, 1, !(commandSender instanceof EntityPlayer));
            ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("commands.message.display.incoming", new Object[]{commandSender.getDisplayName(), component.copy()});
            ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation("commands.message.display.outgoing", new Object[]{player.getDisplayName(), component.copy()});
            chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(Boolean.TRUE);
            chatComponentTranslation2.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(Boolean.TRUE);
            player.addChatMessage(chatComponentTranslation);
            commandSender.addChatMessage(chatComponentTranslation2);
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.message.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public List getCommandAliases() {
      return Arrays.asList("w", "msg");
   }
}
