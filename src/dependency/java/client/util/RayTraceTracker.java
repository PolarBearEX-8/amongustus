package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.ClientSettingsOpenUrlType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class RayTraceTracker extends Listener87Entry {
   @Override
   public List asList() {
      return Arrays.asList("?");
   }

   @Override
   public int getCount() {
      return 0;
   }

   public Map getMap() {
      return VanillaRunnable.getVanillaRunnable().getICommandManager().getMap();
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      List list = this.resolveList(iCommandSender);
      int count = (list.size() - 1) / 7;

      int count2;
      try {
         count2 = textArray.length == 0 ? 0 : computeCount(textArray[0], 1, count + 1) - 1;
      } catch (AimDirector exception) {
         ComparableExtension comparableExtension = (ComparableExtension)this.getMap().get(textArray[0]);
         if (comparableExtension != null) {
            throw new AimDispatcher(comparableExtension.resolveText(iCommandSender), new Object[0]);
         }

         if (MathHelper.getIndex(textArray[0], -1) != -1) {
            throw exception;
         }

         throw new AimSystem();
      }

      int count3 = Math.min((count2 + 1) * 7, list.size());
      BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("commands.help.header", new Object[]{count2 + 1, count + 1});
      baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GREEN);
      iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);

      for (int index = count2 * 7; index < count3; index++) {
         ComparableExtension comparableExtension2 = (ComparableExtension)list.get(index);
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter(comparableExtension2.resolveText(iCommandSender), new Object[0]);
         baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager()
            .getStyleHasParentManager(new ClickEventActionManager(ClientSettingsOpenUrlType.SUGGEST_COMMAND, "/" + comparableExtension2.getText() + " "));
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter2);
      }

      if (count2 == 0 && iCommandSender instanceof EntityPlayer) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter3 = new BaseManagerStyleIChatComponentAdapter("commands.help.footer", new Object[0]);
         baseManagerStyleIChatComponentAdapter3.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.GREEN);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter3);
      }
   }

   public List resolveList(ICommandSender iCommandSender) {
      List list = VanillaRunnable.getVanillaRunnable().getICommandManager().resolveList(iCommandSender);
      Collections.sort(list);
      return list;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.help.usage";
   }

   @Override
   public String getText() {
      return "help";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         Set keys = this.getMap().keySet();
         return resolveList(textArray, (String[])keys.toArray(new String[keys.size()]));
      } else {
         return null;
      }
   }
}
