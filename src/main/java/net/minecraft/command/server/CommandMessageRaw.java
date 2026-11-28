package net.minecraft.command.server;

import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class CommandMessageRaw extends CommandBase {

   @Override
   public String getCommandName() {
      return "tellraw";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.tellraw.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()) : null;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.tellraw.usage", new Object[0]);
      } else {
         EntityPlayerMP player = getPlayer(commandSender, strings[0]);
         String str = buildString(strings, 1);

         try {
            player.addChatMessage(ChatComponentProcessor.processComponent(commandSender, IChatComponent.Serializer.jsonToComponent(str), player));
         } catch (JsonParseException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new SyntaxErrorException("commands.tellraw.jsonException", new Object[]{rootCause == null ? "" : rootCause.getMessage()});
         }
      }
   }
}
