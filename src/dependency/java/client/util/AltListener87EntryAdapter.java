package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class AltListener87EntryAdapter extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length <= 0) {
         throw new AimDispatcher("commands.me.usage", new Object[0]);
      } else {
         IChatComponent iChatComponent = resolveIChatComponent(iCommandSender, textArray, 0, !(iCommandSender instanceof EntityPlayer));
         VanillaRunnable.getVanillaRunnable()
            .getMovingManager()
            .applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("chat.type.emote", new Object[]{iCommandSender.getChatComponent(), iChatComponent}));
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.me.usage";
   }

   @Override
   public String getText() {
      return "me";
   }

   @Override
   public int getCount() {
      return 0;
   }
}
