package client.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.world.WorldServer;

public class RayTraceRegistry extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.save-on.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      boolean flag = false;

      for (int index = 0; index < vanillaRunnable.worldServerArray.length; index++) {
         if (vanillaRunnable.worldServerArray[index] != null) {
            WorldServer worldServer = vanillaRunnable.worldServerArray[index];
            if (worldServer.enabled8) {
               worldServer.enabled8 = false;
               flag = true;
            }
         }
      }

      if (flag) {
         applyICommandSender(iCommandSender, this, "commands.save.enabled", new Object[0]);
      } else {
         throw new InternalExceptionAdapter("commands.save-on.alreadyOn", new Object[0]);
      }
   }

   @Override
   public String getText() {
      return "save-on";
   }
}
