package client.util;

import java.util.List;
import java.util.Random;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class RayTraceSelector extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.weather.usage";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, new String[]{"clear", "rain", "thunder"}) : null;
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length >= 1 && textArray.length <= 2) {
         int count = (300 + new Random().nextInt(600)) * 20;
         if (textArray.length >= 2) {
            count = computeCount(textArray[1], 1, 1000000) * 20;
         }

         WorldServer worldServer = VanillaRunnable.getVanillaRunnable().worldServerArray[0];
         RandomSeedManager randomSeedManager = worldServer.getRandomSeedManager();
         if ("clear".equalsIgnoreCase(textArray[0])) {
            randomSeedManager.setCount(count);
            randomSeedManager.setCount6(0);
            randomSeedManager.setCount10(0);
            randomSeedManager.setEnabled4(false);
            randomSeedManager.setEnabled6(false);
            applyICommandSender(iCommandSender, this, "commands.weather.clear", new Object[0]);
         } else if ("rain".equalsIgnoreCase(textArray[0])) {
            randomSeedManager.setCount(0);
            randomSeedManager.setCount6(count);
            randomSeedManager.setCount10(count);
            randomSeedManager.setEnabled4(true);
            randomSeedManager.setEnabled6(false);
            applyICommandSender(iCommandSender, this, "commands.weather.rain", new Object[0]);
         } else {
            if (!"thunder".equalsIgnoreCase(textArray[0])) {
               throw new AimDispatcher("commands.weather.usage", new Object[0]);
            }

            randomSeedManager.setCount(0);
            randomSeedManager.setCount6(count);
            randomSeedManager.setCount10(count);
            randomSeedManager.setEnabled4(true);
            randomSeedManager.setEnabled6(true);
            applyICommandSender(iCommandSender, this, "commands.weather.thunder", new Object[0]);
         }
      } else {
         throw new AimDispatcher("commands.weather.usage", new Object[0]);
      }
   }

   @Override
   public String getText() {
      return "weather";
   }
}
