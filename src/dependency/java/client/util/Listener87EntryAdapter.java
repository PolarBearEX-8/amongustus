package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class Listener87EntryAdapter extends Listener87Entry {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.clear.usage";
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      EntityPlayerAdapter entityPlayerAdapter = textArray.length == 0 ? resolveEntityPlayerAdapter(iCommandSender) : resolveEntityPlayerAdapter(iCommandSender, textArray[0]);
      Item item = textArray.length >= 2 ? resolveItem(iCommandSender, textArray[1]) : null;
      int count = textArray.length >= 3 ? computeCount(textArray[2], -1) : -1;
      int count2 = textArray.length >= 4 ? computeCount(textArray[3], -1) : -1;
      NBTTagCompound tagCompound = null;
      if (textArray.length >= 5) {
         try {
            tagCompound = DampenerEstimator.resolveNBTTagCompound(resolveText(textArray, 4));
         } catch (SecondaryExceptionAdapter exception) {
            throw new InternalExceptionAdapter("commands.clear.tagError", new Object[]{exception.getMessage()});
         }
      }

      if (textArray.length >= 2 && item == null) {
         throw new InternalExceptionAdapter("commands.clear.failure", new Object[]{entityPlayerAdapter.getText()});
      } else {
         int index = entityPlayerAdapter.inventoryPlayer.getIndex(item, count, count2, tagCompound);
         entityPlayerAdapter.container.detectAndSendChanges();
         if (!entityPlayerAdapter.invulnerableManager.enabled2) {
            entityPlayerAdapter.updateState47();
         }

         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, index);
         if (index == 0) {
            throw new InternalExceptionAdapter("commands.clear.failure", new Object[]{entityPlayerAdapter.getText()});
         } else {
            if (count2 == 0) {
               iCommandSender.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("commands.clear.testing", new Object[]{entityPlayerAdapter.getText(), index}));
            } else {
               applyICommandSender(iCommandSender, this, "commands.clear.success", new Object[]{entityPlayerAdapter.getText(), index});
            }
         }
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   @Override
   public String getText() {
      return "clear";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, this.getTextArray()) : (textArray.length == 2 ? resolveList(textArray, Item.iterableExtensionAdapter.getSet()) : null);
   }
}
