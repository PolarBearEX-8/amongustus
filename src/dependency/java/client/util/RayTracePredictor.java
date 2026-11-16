package client.util;

import net.minecraft.command.ICommandSender;

public class RayTracePredictor extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.downfall.usage";
   }

   public void updateState() {
      RandomSeedManager randomSeedManager = VanillaRunnable.getVanillaRunnable().worldServerArray[0].getRandomSeedManager();
      randomSeedManager.setEnabled4(!randomSeedManager.isEnabled4());
   }

   @Override
   public String getText() {
      return "toggledownfall";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      this.updateState();
      applyICommandSender(iCommandSender, this, "commands.downfall.success", new Object[0]);
   }
}
