package net.minecraft.command;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandToggleDownfall extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.downfall.usage";
   }

   public void toggleDownfall() {
      WorldInfo worldInfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();
      worldInfo.setRaining(!worldInfo.isRaining());
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "toggledownfall";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      this.toggleDownfall();
      notifyOperators(commandSender, this, "commands.downfall.success", new Object[0]);
   }
}
