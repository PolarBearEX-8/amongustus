package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class RayTraceAggregator extends Listener87Entry {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.banlist.usage";
   }

   @Override
   public String getText() {
      return "banlist";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, new String[]{"players", "ips"}) : null;
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return (
            VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().isEnabled()
               || VanillaRunnable.getVanillaRunnable().getMovingManager().getInternalLoggerEntryAdapter().isEnabled()
         )
         && super.matchesICommandSender(iCommandSender);
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length >= 1 && textArray[0].equalsIgnoreCase("ips")) {
         iCommandSender.applyIChatComponent(
            new BaseManagerStyleIChatComponentAdapter(
               "commands.banlist.ips", new Object[]{VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().getTextArray().length}
            )
         );
         iCommandSender.applyIChatComponent(
            new ChatComponentText(resolveText(VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().getTextArray()))
         );
      } else {
         iCommandSender.applyIChatComponent(
            new BaseManagerStyleIChatComponentAdapter(
               "commands.banlist.players",
               new Object[]{VanillaRunnable.getVanillaRunnable().getMovingManager().getInternalLoggerEntryAdapter().getTextArray().length}
            )
         );
         iCommandSender.applyIChatComponent(
            new ChatComponentText(resolveText(VanillaRunnable.getVanillaRunnable().getMovingManager().getInternalLoggerEntryAdapter().getTextArray()))
         );
      }
   }
}
