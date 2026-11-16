package client.util;

import client.event.CompositeListener;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.WorldServer;

public class RayTraceProvider extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("commands.save.start", new Object[0]));
      if (vanillaRunnable.getMovingManager() != null) {
         vanillaRunnable.getMovingManager().updateState4();
      }

      try {
         for (int index = 0; index < vanillaRunnable.worldServerArray.length; index++) {
            if (vanillaRunnable.worldServerArray[index] != null) {
               WorldServer worldServer = vanillaRunnable.worldServerArray[index];
               boolean enabled = worldServer.enabled8;
               worldServer.enabled8 = false;
               worldServer.applyCompositeListener(true, (CompositeListener)null);
               worldServer.enabled8 = enabled;
            }
         }

         if (textArray.length > 0 && "flush".equals(textArray[0])) {
            iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("commands.save.flushStart", new Object[0]));

            for (int index2 = 0; index2 < vanillaRunnable.worldServerArray.length; index2++) {
               if (vanillaRunnable.worldServerArray[index2] != null) {
                  WorldServer worldServer2 = vanillaRunnable.worldServerArray[index2];
                  boolean enabled2 = worldServer2.enabled8;
                  worldServer2.enabled8 = false;
                  worldServer2.updateState18();
                  worldServer2.enabled8 = enabled2;
               }
            }

            iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("commands.save.flushEnd", new Object[0]));
         }
      } catch (ExceptionAdapter exception) {
         applyICommandSender(iCommandSender, this, "commands.save.failed", new Object[]{exception.getMessage()});
         return;
      }

      applyICommandSender(iCommandSender, this, "commands.save.success", new Object[0]);
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.save.usage";
   }

   @Override
   public String getText() {
      return "save-all";
   }
}
