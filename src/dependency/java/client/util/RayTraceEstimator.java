package client.util;

import client.setting.WorldSettingsGameType;
import net.minecraft.command.ICommandSender;

public class RayTraceEstimator extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      String text = VanillaRunnable.getVanillaRunnable().resolveText(WorldSettingsGameType.SURVIVAL, false);
      if (text != null) {
         applyICommandSender(iCommandSender, this, "commands.publish.started", new Object[]{text});
      } else {
         applyICommandSender(iCommandSender, this, "commands.publish.failed", new Object[0]);
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.publish.usage";
   }

   @Override
   public String getText() {
      return "publish";
   }
}
