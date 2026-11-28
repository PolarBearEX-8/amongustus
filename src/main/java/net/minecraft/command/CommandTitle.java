package net.minecraft.command;

import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandTitle extends CommandBase {
   private static final Logger LOGGER = LogManager.getLogger();

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.title.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 0;
   }

   @Override
   public String getCommandName() {
      return "title";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 2) {
         throw new WrongUsageException("commands.title.usage", new Object[0]);
      } else {
         if (strings.length < 3) {
            if ("title".equals(strings[1]) || "subtitle".equals(strings[1])) {
               throw new WrongUsageException("commands.title.usage.title", new Object[0]);
            }

            if ("times".equals(strings[1])) {
               throw new WrongUsageException("commands.title.usage.times", new Object[0]);
            }
         }

         EntityPlayerMP player = getPlayer(commandSender, strings[0]);
         S45PacketTitle.Type type = S45PacketTitle.Type.byName(strings[1]);
         if (type == S45PacketTitle.Type.CLEAR || type == S45PacketTitle.Type.RESET) {
            if (strings.length != 2) {
               throw new WrongUsageException("commands.title.usage", new Object[0]);
            }

            S45PacketTitle packet = new S45PacketTitle(type, (IChatComponent)null);
            player.playerNetServerHandler.sendPacket(packet);
            notifyOperators(commandSender, this, "commands.title.success", new Object[0]);
         } else if (type == S45PacketTitle.Type.TIMES) {
            if (strings.length != 5) {
               throw new WrongUsageException("commands.title.usage", new Object[0]);
            }

            int n = parseInt(strings[2]);
            int n2 = parseInt(strings[3]);
            int n3 = parseInt(strings[4]);
            S45PacketTitle packet2 = new S45PacketTitle(n, n2, n3);
            player.playerNetServerHandler.sendPacket(packet2);
            notifyOperators(commandSender, this, "commands.title.success", new Object[0]);
         } else {
            if (strings.length < 3) {
               throw new WrongUsageException("commands.title.usage", new Object[0]);
            }

            String str = buildString(strings, 2);

            IChatComponent component;
            try {
               component = IChatComponent.Serializer.jsonToComponent(str);
            } catch (JsonParseException e) {
               Throwable rootCause = ExceptionUtils.getRootCause(e);
               throw new SyntaxErrorException("commands.tellraw.jsonException", new Object[]{rootCause == null ? "" : rootCause.getMessage()});
            }

            S45PacketTitle packet3 = new S45PacketTitle(type, ChatComponentProcessor.processComponent(commandSender, component, player));
            player.playerNetServerHandler.sendPacket(packet3);
            notifyOperators(commandSender, this, "commands.title.success", new Object[0]);
         }
      }
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames())
         : (strings.length == 2 ? getListOfStringsMatchingLastWord(strings, S45PacketTitle.Type.getNames()) : null);
   }
}
