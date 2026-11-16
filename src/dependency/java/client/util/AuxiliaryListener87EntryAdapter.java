package client.util;

import client.setting.ClientSettingsBlackType;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class AuxiliaryListener87EntryAdapter extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.message.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public List asList() {
      return Arrays.asList("w", "msg");
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
   }

   @Override
   public String getText() {
      return "tell";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.message.usage", new Object[0]);
      } else {
         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender, textArray[0]);
         if (entityPlayerAdapter == iCommandSender) {
            throw new AimProvider("commands.message.sameTarget", new Object[0]);
         } else {
            IChatComponent iChatComponent = resolveIChatComponent(iCommandSender, textArray, 1, !(iCommandSender instanceof EntityPlayer));
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
               "commands.message.display.incoming", new Object[]{iCommandSender.getChatComponent(), iChatComponent.getIChatComponent()}
            );
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter(
               "commands.message.display.outgoing", new Object[]{entityPlayerAdapter.getChatComponent(), iChatComponent.getIChatComponent()}
            );
            baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY).withMemberRef5(Boolean.TRUE);
            baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GRAY).withMemberRef5(Boolean.TRUE);
            entityPlayerAdapter.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
            iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter2);
         }
      }
   }
}
