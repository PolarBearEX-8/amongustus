package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class RayTraceQueue extends Listener87Entry {
   @Override
   public String getText() {
      return "kick";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length >= 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.kick.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length > 0 && textArray[0].length() > 1) {
         EntityPlayerAdapter entityPlayerAdapter = VanillaRunnable.getVanillaRunnable().getMovingManager().resolveEntityPlayerAdapter(textArray[0]);
         String text = "Kicked by an operator.";
         boolean flag = false;
         if (entityPlayerAdapter == null) {
            throw new AimProvider();
         } else {
            if (textArray.length >= 2) {
               text = resolveIChatComponent(iCommandSender, textArray, 1).getString();
               flag = true;
            }

            entityPlayerAdapter.auxiliaryListenerAdapter4.applyTextByText(text);
            if (flag) {
               applyICommandSender(iCommandSender, this, "commands.kick.success.reason", new Object[]{entityPlayerAdapter.getText(), text});
            } else {
               applyICommandSender(iCommandSender, this, "commands.kick.success", new Object[]{entityPlayerAdapter.getText()});
            }
         }
      } else {
         throw new AimDispatcher("commands.kick.usage", new Object[0]);
      }
   }
}
