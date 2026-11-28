package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandGameMode extends CommandBase {

   public WorldSettings.GameType getGameModeFromCommand(ICommandSender commandSender, String str) throws CommandException, NumberInvalidException {
      return str.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.getName()) || str.equalsIgnoreCase("s")
         ? WorldSettings.GameType.SURVIVAL
         : (
            str.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.getName()) || str.equalsIgnoreCase("c")
               ? WorldSettings.GameType.CREATIVE
               : (
                  str.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.getName()) || str.equalsIgnoreCase("a")
                     ? WorldSettings.GameType.ADVENTURE
                     : (
                        !str.equalsIgnoreCase(WorldSettings.GameType.SPECTATOR.getName()) && !str.equalsIgnoreCase("sp")
                           ? WorldSettings.getGameTypeById(parseInt(str, 0, WorldSettings.GameType.values().length - 2))
                           : WorldSettings.GameType.SPECTATOR
                     )
               )
         );
   }

   @Override
   public boolean isUsernameIndex(String[] strings, int n) {
      return n == 1;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length <= 0) {
         throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
      } else {
         WorldSettings.GameType gameModeFromCommand = this.getGameModeFromCommand(commandSender, strings[0]);
         EntityPlayerMP entityPlayerMP = strings.length >= 2 ? getPlayer(commandSender, strings[1]) : getCommandSenderAsPlayer(commandSender);
         entityPlayerMP.setGameType(gameModeFromCommand);
         entityPlayerMP.fallDistance = 0.0F;
         if (commandSender.getEntityWorld().getGameRules().getBoolean("sendCommandFeedback")) {
            entityPlayerMP.addChatMessage(new ChatComponentTranslation("gameMode.changed", new Object[0]));
         }

         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("gameMode." + gameModeFromCommand.getName(), new Object[0]);
         if (entityPlayerMP != commandSender) {
            notifyOperators(commandSender, this, 1, "commands.gamemode.success.other", new Object[]{entityPlayerMP.getName(), chatComponentTranslation});
         } else {
            notifyOperators(commandSender, this, 1, "commands.gamemode.success.self", new Object[]{chatComponentTranslation});
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.gamemode.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1
         ? getListOfStringsMatchingLastWord(strings, new String[]{"survival", "creative", "adventure", "spectator"})
         : (strings.length == 2 ? getListOfStringsMatchingLastWord(strings, this.getListOfPlayerUsernames()) : null);
   }

   public String[] getListOfPlayerUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   @Override
   public String getCommandName() {
      return "gamemode";
   }
}
