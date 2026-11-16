package client.util;

import client.setting.CombatNumberProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class RayTraceAccumulator extends Listener87Entry {
   @Override
   public String getText() {
      return "list";
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      int count = VanillaRunnable.getVanillaRunnable().getCount8();
      iCommandSender.applyIChatComponent(
         new BaseManagerStyleIChatComponentAdapter("commands.players.list", new Object[]{count, VanillaRunnable.getVanillaRunnable().getCount2()})
      );
      iCommandSender.applyIChatComponent(
         new ChatComponentText(VanillaRunnable.getVanillaRunnable().getMovingManager().resolveText(textArray.length > 0 && "uuids".equalsIgnoreCase(textArray[0])))
      );
      iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, count);
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.players.usage";
   }
}
