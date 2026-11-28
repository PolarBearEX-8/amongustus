package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      MinecraftServer server = MinecraftServer.getServer();
      commandSender.addChatMessage(new ChatComponentTranslation("commands.save.start", new Object[0]));
      if (server.getConfigurationManager() != null) {
         server.getConfigurationManager().saveAllPlayerData();
      }

      try {
         for (int i = 0; i < server.worldServers.length; i++) {
            if (server.worldServers[i] != null) {
               WorldServer world = server.worldServers[i];
               boolean disableLevelSaving = world.disableLevelSaving;
               world.disableLevelSaving = false;
               world.saveAllChunks(true, (IProgressUpdate)null);
               world.disableLevelSaving = disableLevelSaving;
            }
         }

         if (strings.length > 0 && "flush".equals(strings[0])) {
            commandSender.addChatMessage(new ChatComponentTranslation("commands.save.flushStart", new Object[0]));

            for (int j = 0; j < server.worldServers.length; j++) {
               if (server.worldServers[j] != null) {
                  WorldServer world2 = server.worldServers[j];
                  boolean disableLevelSaving2 = world2.disableLevelSaving;
                  world2.disableLevelSaving = false;
                  world2.saveChunkData();
                  world2.disableLevelSaving = disableLevelSaving2;
               }
            }

            commandSender.addChatMessage(new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
         }
      } catch (MinecraftException e) {
         notifyOperators(commandSender, this, "commands.save.failed", new Object[]{e.getMessage()});
         return;
      }

      notifyOperators(commandSender, this, "commands.save.success", new Object[0]);
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.save.usage";
   }

   @Override
   public String getCommandName() {
      return "save-all";
   }
}
