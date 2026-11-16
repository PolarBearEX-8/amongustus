package client.util;

import net.minecraft.command.ICommandSender;

public class BezierStack extends Listener87Entry {
   @Override
   public String getText() {
      return "stop";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.stop.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (VanillaRunnable.getVanillaRunnable().worldServerArray != null) {
         applyICommandSender(iCommandSender, this, "commands.stop.start", new Object[0]);
      }

      VanillaRunnable.getVanillaRunnable().updateState();
   }
}
