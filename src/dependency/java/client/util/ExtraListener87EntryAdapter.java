package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class ExtraListener87EntryAdapter extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.say.usage";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length >= 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length > 0 && textArray[0].length() > 0) {
         IChatComponent iChatComponent = resolveIChatComponent(iCommandSender, textArray, 0, true);
         VanillaRunnable.getVanillaRunnable()
            .getMovingManager()
            .applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("chat.type.announcement", new Object[]{iCommandSender.getChatComponent(), iChatComponent}));
      } else {
         throw new AimDispatcher("commands.say.usage", new Object[0]);
      }
   }

   @Override
   public String getText() {
      return "say";
   }

   @Override
   public int getCount() {
      return 1;
   }
}
