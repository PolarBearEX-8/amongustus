package client.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.world.WorldServer;

public class RayTraceWatcher extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.save-off.usage";
   }

   @Override
   public String getText() {
      return "save-off";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      boolean flag = false;

      for (int index = 0; index < vanillaRunnable.worldServerArray.length; index++) {
         if (vanillaRunnable.worldServerArray[index] != null) {
            WorldServer worldServer = vanillaRunnable.worldServerArray[index];
            if (!worldServer.enabled8) {
               worldServer.enabled8 = true;
               flag = true;
            }
         }
      }

      if (flag) {
         applyICommandSender(iCommandSender, this, "commands.save.disabled", new Object[0]);
      } else {
         throw new InternalExceptionAdapter("commands.save-off.alreadyOff", new Object[0]);
      }
   }
}
