package net.minecraft.command;

import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandWeather extends CommandBase {

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, new String[]{"clear", "rain", "thunder"}) : null;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length >= 1 && strings.length <= 2) {
         int n = (300 + new Random().nextInt(600)) * 20;
         if (strings.length >= 2) {
            n = parseInt(strings[1], 1, 1000000) * 20;
         }

         WorldInfo worldInfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();
         if ("clear".equalsIgnoreCase(strings[0])) {
            worldInfo.setCleanWeatherTime(n);
            worldInfo.setRainTime(0);
            worldInfo.setThunderTime(0);
            worldInfo.setRaining(false);
            worldInfo.setThundering(false);
            notifyOperators(commandSender, this, "commands.weather.clear", new Object[0]);
         } else if ("rain".equalsIgnoreCase(strings[0])) {
            worldInfo.setCleanWeatherTime(0);
            worldInfo.setRainTime(n);
            worldInfo.setThunderTime(n);
            worldInfo.setRaining(true);
            worldInfo.setThundering(false);
            notifyOperators(commandSender, this, "commands.weather.rain", new Object[0]);
         } else {
            if (!"thunder".equalsIgnoreCase(strings[0])) {
               throw new WrongUsageException("commands.weather.usage", new Object[0]);
            }

            worldInfo.setCleanWeatherTime(0);
            worldInfo.setRainTime(n);
            worldInfo.setThunderTime(n);
            worldInfo.setRaining(true);
            worldInfo.setThundering(true);
            notifyOperators(commandSender, this, "commands.weather.thunder", new Object[0]);
         }
      } else {
         throw new WrongUsageException("commands.weather.usage", new Object[0]);
      }
   }

   @Override
   public String getCommandName() {
      return "weather";
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.weather.usage";
   }
}
