package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class NativeListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length == 0) {
         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender);
         entityPlayerAdapter.updateState15();
         applyICommandSender(iCommandSender, this, "commands.kill.successful", new Object[]{entityPlayerAdapter.getChatComponent()});
      } else {
         Entity entity = resolveEntity(iCommandSender, textArray[0]);
         entity.updateState15();
         applyICommandSender(iCommandSender, this, "commands.kill.successful", new Object[]{entity.getChatComponent()});
      }
   }

   @Override
   public String getText() {
      return "kill";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.kill.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }
}
