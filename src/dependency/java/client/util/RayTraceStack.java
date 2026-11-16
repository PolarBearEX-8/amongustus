package client.util;

import net.minecraft.command.ICommandSender;

public class RayTraceStack extends Listener87Entry {
   @Override
   public String getText() {
      return "setidletimeout";
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length != 1) {
         throw new AimDispatcher("commands.setidletimeout.usage", new Object[0]);
      } else {
         int count = computeCount(textArray[0], 0);
         VanillaRunnable.getVanillaRunnable().setCount4(count);
         applyICommandSender(iCommandSender, this, "commands.setidletimeout.success", new Object[]{count});
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.setidletimeout.usage";
   }
}
