package net.minecraft.command;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandDebug extends CommandBase {
   private static final Logger LOGGER = LogManager.getLogger();
   private long profileStartTime;
   private int profileStartTick;

   @Override
   public String getCommandName() {
      return "debug";
   }

   private void saveProfileResults(long l, int n) {
      File file = new File(
         MinecraftServer.getServer().getFile("debug"), "profile-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt"
      );
      file.getParentFile().mkdirs();

      try {
         FileWriter fileWriter = new FileWriter(file);
         fileWriter.write(this.getProfileResults(l, n));
         fileWriter.close();
      } catch (Throwable e) {
         LOGGER.error("Could not save profiler results to " + file, e);
      }
   }

   private void appendProfilerResults(int n, String str, StringBuilder sb) {
   }

   @Override
   public List addTabCompletionOptions(ICommandSender commandSender, String[] strings, BlockPos pos) {
      return strings.length == 1 ? getListOfStringsMatchingLastWord(strings, new String[]{"start", "stop"}) : null;
   }

   @Override
   public String getCommandUsage(ICommandSender commandSender) {
      return "commands.debug.usage";
   }

   private static String getWittyComment() {
      String[] strings = new String[]{
         "Shiny numbers!",
         "Am I not running fast enough? :(",
         "I'm working as hard as I can!",
         "Will I ever be good enough for you? :(",
         "Speedy. Zoooooom!",
         "Hello world",
         "40% better than a crash report.",
         "Now with extra numbers",
         "Now with less numbers",
         "Now with the same numbers",
         "You should add flames to things, it makes them go faster!",
         "Do you feel the need for... optimization?",
         "*cracks redstone whip*",
         "Maybe if you treated it better then it'll have more motivation to work faster! Poor server."
      };

      try {
         return strings[(int)(System.nanoTime() % (long)strings.length)];
      } catch (Throwable e) {
         return "Witty comment unavailable :(";
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 3;
   }

   @Override
   public void processCommand(ICommandSender commandSender, String[] strings) throws CommandException {
      if (strings.length < 1) {
         throw new WrongUsageException("commands.debug.usage", new Object[0]);
      } else {
         if (strings[0].equals("start")) {
            if (strings.length != 1) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            notifyOperators(commandSender, this, "commands.debug.start", new Object[0]);
            MinecraftServer.getServer().enableProfiling();
            this.profileStartTime = MinecraftServer.getCurrentTimeMillis();
            this.profileStartTick = MinecraftServer.getServer().getTickCounter();
         } else {
            if (!strings[0].equals("stop")) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            if (strings.length != 1) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            if (!MinecraftServer.getServer().theProfiler.profilingEnabled) {
               throw new CommandException("commands.debug.notStarted", new Object[0]);
            }

            long currentTimeMillis = MinecraftServer.getCurrentTimeMillis();
            int tickCounter = MinecraftServer.getServer().getTickCounter();
            long l = currentTimeMillis - this.profileStartTime;
            int n = tickCounter - this.profileStartTick;
            this.saveProfileResults(l, n);
            MinecraftServer.getServer().theProfiler.profilingEnabled = false;
            notifyOperators(commandSender, this, "commands.debug.stop", new Object[]{(float)l / 1000.0F, n});
         }
      }
   }

   private String getProfileResults(long l, int n) {
      StringBuilder sb = new StringBuilder();
      sb.append("---- Minecraft Profiler Results ----\n");
      sb.append("// ");
      sb.append(getWittyComment());
      sb.append("\n\n");
      sb.append("Time span: ").append(l).append(" ms\n");
      sb.append("Tick span: ").append(n).append(" ticks\n");
      sb.append("// This is approximately ")
         .append(String.format("%.2f", (float)n / ((float)l / 1000.0F)))
         .append(" ticks per second. It should be ")
         .append(20)
         .append(" ticks per second\n\n");
      sb.append("--- BEGIN PROFILE DUMP ---\n\n");
      this.appendProfilerResults(0, "root", sb);
      sb.append("--- END PROFILE DUMP ---\n\n");
      return sb.toString();
   }
}
