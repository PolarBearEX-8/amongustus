package client.util;

import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class RayTraceCollector extends Listener87Entry {
   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 1;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length <= 0) {
         throw new AimDispatcher("commands.xp.usage", new Object[0]);
      } else {
         String text = textArray[0];
         boolean flag = text.endsWith("l") || text.endsWith("L");
         if (flag && text.length() > 1) {
            text = text.substring(0, text.length() - 1);
         }

         int count = parseInt(text);
         boolean flag2 = count < 0;
         if (flag2) {
            count *= -1;
         }

         EntityPlayerAdapter entityPlayerAdapter = textArray.length > 1 ? resolveEntityPlayerAdapter(iCommandSender, textArray[1]) : resolveEntityPlayerAdapter(iCommandSender);
         if (flag) {
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, entityPlayerAdapter.count25);
            if (flag2) {
               entityPlayerAdapter.applyIndex8(-count);
               applyICommandSender(iCommandSender, this, "commands.xp.success.negative.levels", new Object[]{count, entityPlayerAdapter.getText()});
            } else {
               entityPlayerAdapter.applyIndex8(count);
               applyICommandSender(iCommandSender, this, "commands.xp.success.levels", new Object[]{count, entityPlayerAdapter.getText()});
            }
         } else {
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, entityPlayerAdapter.count32);
            if (flag2) {
               throw new InternalExceptionAdapter("commands.xp.failure.widthdrawXp", new Object[0]);
            }

            entityPlayerAdapter.applyIndex12(count);
            applyICommandSender(iCommandSender, this, "commands.xp.success", new Object[]{count, entityPlayerAdapter.getText()});
         }
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 2 ? resolveList(textArray, this.getTextArray()) : null;
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.xp.usage";
   }

   @Override
   public String getText() {
      return "xp";
   }

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }
}
