package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class RayTracePool extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().getTextArray()) : null;
   }

   @Override
   public String getText() {
      return "pardon-ip";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.unbanip.usage";
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length != 1 || textArray[0].length() <= 1) {
         throw new AimDispatcher("commands.unbanip.usage", new Object[0]);
      } else if (RayTraceSynchronizer.pattern.matcher(textArray[0]).matches()) {
         VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().applyTarget(textArray[0]);
         applyICommandSender(iCommandSender, this, "commands.unbanip.success", new Object[]{textArray[0]});
      } else {
         throw new AimEngine("commands.unbanip.invalid", new Object[0]);
      }
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().isEnabled() && super.matchesICommandSender(iCommandSender);
   }
}
