package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandGameRule extends CommandBase {

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      GameRules gameRules = this.getGameRules();
      String str = strings.length > 0 ? strings[0] : "";
      String str2 = strings.length > 1 ? buildString(strings, 1) : "";
      switch (strings.length) {
         case 0:
            commandSender.addChatMessage(new ChatComponentText(joinNiceString(gameRules.getRules())));
            break;
         case 1:
            if (!gameRules.hasRule(str)) {
               throw new CommandException("commands.gamerule.norule", new Object[]{str});
            }

            String string = gameRules.getString(str);
            commandSender.addChatMessage(new ChatComponentText(str).appendText(" = ").appendText(string));
            commandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, gameRules.getInt(str));
            break;
         default:
            if (gameRules.areSameType(str, GameRules.ValueType.BOOLEAN_VALUE) && !"true".equals(str2) && !"false".equals(str2)) {
               throw new CommandException("commands.generic.boolean.invalid", new Object[]{str2});
            }

            gameRules.setOrCreateGameRule(str, str2);
            notifyGameRuleChange(gameRules, str);
            notifyOperators(commandSender, this, "commands.gamerule.success", new Object[0]);
      }
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.gamerule.usage";
   }

   private GameRules getGameRules() {
      return MinecraftServer.getServer().worldServerForDimension(0).getGameRules();
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         return getListOfStringsMatchingLastWord(strings, this.getGameRules().getRules());
      } else {
         if (strings.length == 2) {
            if (this.getGameRules().areSameType(strings[0], GameRules.ValueType.BOOLEAN_VALUE)) {
               return getListOfStringsMatchingLastWord(strings, new String[]{"true", "false"});
            }
         }

         return null;
      }
   }

   @Override
   public String getCommandName() {
      return "gamerule";
   }

   public static void notifyGameRuleChange(GameRules gameRules, String str) {
      if ("reducedDebugInfo".equals(str)) {
         byte b = (byte)(gameRules.getBoolean(str) ? 22 : 23);

         for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(MinecraftServer.getServer().getConfigurationManager().getPlayerList())) {
            entityPlayerMP.playerNetServerHandler.sendPacket(new S19PacketEntityStatus(entityPlayerMP, b));
         }
      }
   }
}
