package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandShowSeed extends CommandBase {

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.seed.usage";
   }

   @Override
   public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
      return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(commandSender);
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public String getCommandName() {
      return "seed";
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      Object obj = commandSender instanceof EntityPlayer ? ((EntityPlayer)commandSender).entityWorld : MinecraftServer.getServer().worldServerForDimension(0);
      commandSender.addChatMessage(new ChatComponentTranslation("commands.seed.success", new Object[]{((World)obj).getSeed()}));
   }
}
