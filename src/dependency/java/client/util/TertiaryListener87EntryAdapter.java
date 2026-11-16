package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class TertiaryListener87EntryAdapter extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, new String[]{"set", "add", "query"})
         : (
            textArray.length == 2 && textArray[0].equals("set")
               ? resolveList(textArray, new String[]{"day", "night"})
               : (textArray.length == 2 && textArray[0].equals("query") ? resolveList(textArray, new String[]{"daytime", "gametime"}) : null)
         );
   }

   public void applyICommandSenderByICommandSender(ICommandSender iCommandSender, int count) {
      for (int index = 0; index < VanillaRunnable.getVanillaRunnable().worldServerArray.length; index++) {
         WorldServer worldServer = VanillaRunnable.getVanillaRunnable().worldServerArray[index];
         worldServer.applyTime(worldServer.getTimestampAsTime() + (long)count);
      }
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length > 1) {
         if (textArray[0].equals("set")) {
            int count;
            if (textArray[1].equals("day")) {
               count = 1000;
            } else if (textArray[1].equals("night")) {
               count = 13000;
            } else {
               count = computeCount(textArray[1], 0);
            }

            this.applyICommandSender(iCommandSender, count);
            applyICommandSender(iCommandSender, this, "commands.time.set", new Object[]{count});
            return;
         }

         if (textArray[0].equals("add")) {
            int count2 = computeCount(textArray[1], 0);
            this.applyICommandSenderByICommandSender(iCommandSender, count2);
            applyICommandSender(iCommandSender, this, "commands.time.added", new Object[]{count2});
            return;
         }

         if (textArray[0].equals("query")) {
            if (textArray[1].equals("daytime")) {
               int count3 = (int)(iCommandSender.getWorldObj().getTimestampAsTime() % 2147483647L);
               iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, count3);
               applyICommandSender(iCommandSender, this, "commands.time.query", new Object[]{count3});
               return;
            }

            if (textArray[1].equals("gametime")) {
               int count4 = (int)(iCommandSender.getWorldObj().getTimestamp2() % 2147483647L);
               iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, count4);
               applyICommandSender(iCommandSender, this, "commands.time.query", new Object[]{count4});
               return;
            }
         }
      }

      throw new AimDispatcher("commands.time.usage", new Object[0]);
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.time.usage";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String getText() {
      return "time";
   }

   public void applyICommandSender(ICommandSender iCommandSender, int count) {
      short shortValue = 6000;

      for (int index = 0; index < VanillaRunnable.getVanillaRunnable().worldServerArray.length; index++) {
         VanillaRunnable.getVanillaRunnable().worldServerArray[index].applyTime((long)shortValue);
      }
   }
}
