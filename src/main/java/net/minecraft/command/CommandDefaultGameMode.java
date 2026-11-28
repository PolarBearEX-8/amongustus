package net.minecraft.command;

import net.minecraft.world.WorldSettings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandDefaultGameMode extends CommandGameMode {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.defaultgamemode.usage";
   }

   @Override
   public String getCommandName() {
      return "defaultgamemode";
   }

   public void setGameType(WorldSettings.GameType gameType) {
      MinecraftServer server = MinecraftServer.getServer();
      server.setGameType(gameType);
      if (server.getForceGamemode()) {
         for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(MinecraftServer.getServer().getConfigurationManager().getPlayerList())) {
            entityPlayerMP.setGameType(gameType);
            entityPlayerMP.fallDistance = 0.0F;
         }
      }
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length <= 0) {
         throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
      } else {
         WorldSettings.GameType gameModeFromCommand = this.getGameModeFromCommand(commandSender, strings[0]);
         this.setGameType(gameModeFromCommand);
         notifyOperators(
            commandSender, this, "commands.defaultgamemode.success", new Object[]{new ChatComponentTranslation("gameMode." + gameModeFromCommand.getName(), new Object[0])}
         );
      }
   }
}
