package client.util;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class RayTraceSynchronizer extends Listener87Entry {
   public static final Pattern pattern = Pattern.compile(
      "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"
   );

   public void applyICommandSender(ICommandSender iCommandSender, String text, String text2) {
      AuxiliarySimpleDateFormatEntryAdapter auxiliarySimpleDateFormatEntryAdapter = new AuxiliarySimpleDateFormatEntryAdapter(text, (Date)null, iCommandSender.getText(), (Date)null, text2);
      VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().applyTargetStack(auxiliarySimpleDateFormatEntryAdapter);
      List list = VanillaRunnable.getVanillaRunnable().getMovingManager().resolveList(text);
      String[] textArray = new String[list.size()];
      int count = 0;

      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Object)(list)) {
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyTextByText("You have been IP banned.");
         textArray[count++] = entityPlayerAdapter.getText();
      }

      if (list.isEmpty()) {
         applyICommandSender(iCommandSender, this, "commands.banip.success", new Object[]{text});
      } else {
         applyICommandSender(iCommandSender, this, "commands.banip.success.players", new Object[]{text, resolveText(textArray)});
      }
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return VanillaRunnable.getVanillaRunnable().getMovingManager().getLoggerEntryBridge().isEnabled() && super.matchesICommandSender(iCommandSender);
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length >= 1 && textArray[0].length() > 1) {
         IChatComponent iChatComponent = textArray.length >= 2 ? resolveIChatComponent(iCommandSender, textArray, 1) : null;
         if (pattern.matcher(textArray[0]).matches()) {
            this.applyICommandSender(iCommandSender, textArray[0], iChatComponent == null ? null : iChatComponent.getString());
         } else {
            EntityPlayerAdapter entityPlayerAdapter = VanillaRunnable.getVanillaRunnable().getMovingManager().resolveEntityPlayerAdapter(textArray[0]);
            if (entityPlayerAdapter == null) {
               throw new AimProvider("commands.banip.invalid", new Object[0]);
            }

            this.applyICommandSender(iCommandSender, entityPlayerAdapter.getTextAsText(), iChatComponent == null ? null : iChatComponent.getString());
         }
      } else {
         throw new AimDispatcher("commands.banip.usage", new Object[0]);
      }
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.banip.usage";
   }

   @Override
   public String getText() {
      return "ban-ip";
   }
}
