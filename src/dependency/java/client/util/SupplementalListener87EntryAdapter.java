package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class SupplementalListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.give.usage", new Object[0]);
      } else {
         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender, textArray[0]);
         Item item = resolveItem(iCommandSender, textArray[1]);
         int count = textArray.length >= 3 ? computeCount(textArray[2], 1, 64) : 1;
         int count2 = textArray.length >= 4 ? parseInt(textArray[3]) : 0;
         ItemStack stack = new ItemStack(item, count, count2);
         if (textArray.length >= 5) {
            String string = resolveIChatComponent(iCommandSender, textArray, 4).getString();

            try {
               stack.processAction(DampenerEstimator.resolveNBTTagCompound(string));
            } catch (SecondaryExceptionAdapter exception) {
               throw new InternalExceptionAdapter("commands.give.tagError", new Object[]{exception.getMessage()});
            }
         }

         boolean flag = entityPlayerAdapter.inventoryPlayer.checkItemStack3(stack);
         if (flag) {
            entityPlayerAdapter.worldObj.applyEntityByEntity(entityPlayerAdapter, "random.pop", 0.2F, ((entityPlayerAdapter.getRandom().nextFloat() - entityPlayerAdapter.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityPlayerAdapter.container.detectAndSendChanges();
         }

         if (flag && stack.animationsToGo <= 0) {
            stack.animationsToGo = 1;
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, count);
            EntityItem entityItem = entityPlayerAdapter.resolveEntityItem(stack, false);
            if (entityItem != null) {
               entityItem.updateState27();
            }
         } else {
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ITEMS, count - stack.animationsToGo);
            EntityItem entityItem2 = entityPlayerAdapter.resolveEntityItem(stack, false);
            if (entityItem2 != null) {
               entityItem2.updateState2();
               entityItem2.setLabelText2(entityPlayerAdapter.getText());
            }
         }

         applyICommandSender(iCommandSender, this, "commands.give.success", new Object[]{stack.getIChatComponent(), count, entityPlayerAdapter.getText()});
      }
   }

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.give.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String getText() {
      return "give";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, this.getTextArray()) : (textArray.length == 2 ? resolveList(textArray, Item.iterableExtensionAdapter.getSet()) : null);
   }
}
