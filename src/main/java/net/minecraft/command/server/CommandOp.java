package net.minecraft.command.server;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandOp extends CommandBase {

   @Override
   public String getCommandName() {
      return "op";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.op.usage";
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      if (strings.length == 1) {
         String str = strings[strings.length - 1];
         ArrayList list = Lists.newArrayList();

         for (GameProfile profile : MinecraftServer.getServer().getGameProfiles()) {
            if (!MinecraftServer.getServer().getConfigurationManager().canSendCommands(profile) && doesStringStartWith(str, profile.getName())) {
               list.add(profile.getName());
            }
         }

         return list;
      } else {
         return null;
      }
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length == 1 && strings[0].length() > 0) {
         MinecraftServer server = MinecraftServer.getServer();
         GameProfile gameProfileForUsername = server.getPlayerProfileCache().getGameProfileForUsername(strings[0]);
         if (gameProfileForUsername == null) {
            throw new CommandException("commands.op.failed", new Object[]{strings[0]});
         } else {
            server.getConfigurationManager().addOp(gameProfileForUsername);
            notifyOperators(commandSender, this, "commands.op.success", new Object[]{strings[0]});
         }
      } else {
         throw new WrongUsageException("commands.op.usage", new Object[0]);
      }
   }
}
