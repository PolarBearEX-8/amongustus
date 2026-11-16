package client.util;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimaryListener87EntryAdapter extends Listener87Entry {
   public static final Logger logger = LogManager.getLogger();
   public long timestamp;
   public int count;

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.debug.usage";
   }

   private String resolveText(long value, int count2) {
      StringBuilder builder = new StringBuilder();
      builder.append("---- Minecraft Profiler Results ----\n");
      builder.append("// ");
      builder.append(getTextAsText());
      builder.append("\n\n");
      builder.append("Time span: ").append(value).append(" ms\n");
      builder.append("Tick span: ").append(count2).append(" ticks\n");
      builder.append("// This is approximately ")
         .append(String.format("%.2f", (float)count2 / ((float)value / 1000.0F)))
         .append(" ticks per second. It should be ")
         .append(20)
         .append(" ticks per second\n\n");
      builder.append("--- BEGIN PROFILE DUMP ---\n\n");
      this.applyText(0, "root", builder);
      builder.append("--- END PROFILE DUMP ---\n\n");
      return builder.toString();
   }

   @Override
   public String getText() {
      return "debug";
   }

   private void applyText(int count2, String text, StringBuilder builder) {
   }

   private void applyTime(long value, int count2) {
      File file = new File(
         VanillaRunnable.getVanillaRunnable().resolveFile("debug"),
         "profile-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt"
      );
      file.getParentFile().mkdirs();

      try {
         FileWriter writer = new FileWriter(file);
         writer.write(this.resolveText(value, count2));
         writer.close();
      } catch (Throwable throwable) {
         logger.error("Could not save profiler results to " + file, throwable);
      }
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.debug.usage", new Object[0]);
      } else {
         if (textArray[0].equals("start")) {
            if (textArray.length != 1) {
               throw new AimDispatcher("commands.debug.usage", new Object[0]);
            }

            applyICommandSender(iCommandSender, this, "commands.debug.start", new Object[0]);
            VanillaRunnable.getVanillaRunnable().updateState8();
            this.timestamp = VanillaRunnable.currentTimeMillis();
            this.count = VanillaRunnable.getVanillaRunnable().getCountAsIndex();
         } else {
            if (!textArray[0].equals("stop")) {
               throw new AimDispatcher("commands.debug.usage", new Object[0]);
            }

            if (textArray.length != 1) {
               throw new AimDispatcher("commands.debug.usage", new Object[0]);
            }

            if (!VanillaRunnable.getVanillaRunnable().uNKNOWNManager.enabled) {
               throw new InternalExceptionAdapter("commands.debug.notStarted", new Object[0]);
            }

            long timestamp2 = VanillaRunnable.currentTimeMillis();
            int countAsIndex = VanillaRunnable.getVanillaRunnable().getCountAsIndex();
            long value = timestamp2 - this.timestamp;
            int count2 = countAsIndex - this.count;
            this.applyTime(value, count2);
            VanillaRunnable.getVanillaRunnable().uNKNOWNManager.enabled = false;
            applyICommandSender(iCommandSender, this, "commands.debug.stop", new Object[]{(float)value / 1000.0F, count2});
         }
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, new String[]{"start", "stop"}) : null;
   }

   private static String getTextAsText() {
      String[] textArray = new String[]{
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
         return textArray[(int)(System.nanoTime() % (long)textArray.length)];
      } catch (Throwable throwable) {
         return "Witty comment unavailable :(";
      }
   }

   @Override
   public int getCount() {
      return 3;
   }
}
