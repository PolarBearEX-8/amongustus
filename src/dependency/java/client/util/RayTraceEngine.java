package client.util;

import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class RayTraceEngine extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.tellraw.usage", new Object[0]);
      } else {
         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender, textArray[0]);
         String text = resolveText(textArray, 1);

         try {
            IChatComponent iChatComponent = ChatComponentSerializer.parseChatComponent(text);
            entityPlayerAdapter.applyIChatComponent(AngleMonitor.resolveIChatComponentByICommandSender(iCommandSender, iChatComponent, entityPlayerAdapter));
         } catch (JsonParseException jsonParseException) {
            Throwable rootCause = ExceptionUtils.getRootCause(jsonParseException);
            throw new AimEngine("commands.tellraw.jsonException", new Object[]{rootCause == null ? "" : rootCause.getMessage()});
         }
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.tellraw.usage";
   }

   @Override
   public String getText() {
      return "tellraw";
   }

   @Override
   public int getCount() {
      return 2;
   }
}
