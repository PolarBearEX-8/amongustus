package client.util;

import client.setting.MovementFilterProperty;
import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtraLoggerEntry extends Listener87Entry {
   public static final Logger logger = LogManager.getLogger();

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.title.usage", new Object[0]);
      } else {
         if (textArray.length < 3) {
            if ("title".equals(textArray[1]) || "subtitle".equals(textArray[1])) {
               throw new AimDispatcher("commands.title.usage.title", new Object[0]);
            }

            if ("times".equals(textArray[1])) {
               throw new AimDispatcher("commands.title.usage.times", new Object[0]);
            }
         }

         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender, textArray[0]);
         MovementFilterProperty movementFilterProperty = MovementFilterProperty.resolveMovementFilterProperty(textArray[1]);
         if (movementFilterProperty == MovementFilterProperty.CLEAR || movementFilterProperty == MovementFilterProperty.RESET) {
            if (textArray.length != 2) {
               throw new AimDispatcher("commands.title.usage", new Object[0]);
            }

            S45PacketTitle packet = new S45PacketTitle(movementFilterProperty, (IChatComponent)null);
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
            applyICommandSender(iCommandSender, this, "commands.title.success", new Object[0]);
         } else if (movementFilterProperty == MovementFilterProperty.TIMES) {
            if (textArray.length != 5) {
               throw new AimDispatcher("commands.title.usage", new Object[0]);
            }

            int count = parseInt(textArray[2]);
            int count2 = parseInt(textArray[3]);
            int count3 = parseInt(textArray[4]);
            S45PacketTitle packet2 = new S45PacketTitle(count, count2, count3);
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet2);
            applyICommandSender(iCommandSender, this, "commands.title.success", new Object[0]);
         } else {
            if (textArray.length < 3) {
               throw new AimDispatcher("commands.title.usage", new Object[0]);
            }

            String text = resolveText(textArray, 2);

            IChatComponent iChatComponent;
            try {
               iChatComponent = ChatComponentSerializer.parseChatComponent(text);
            } catch (JsonParseException jsonParseException) {
               Throwable rootCause = ExceptionUtils.getRootCause(jsonParseException);
               throw new AimEngine("commands.tellraw.jsonException", new Object[]{rootCause == null ? "" : rootCause.getMessage()});
            }

            S45PacketTitle packet3 = new S45PacketTitle(movementFilterProperty, AngleMonitor.resolveIChatComponentByICommandSender(iCommandSender, iChatComponent, entityPlayerAdapter));
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet3);
            applyICommandSender(iCommandSender, this, "commands.title.success", new Object[0]);
         }
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray())
         : (textArray.length == 2 ? resolveList(textArray, MovementFilterProperty.getTextArray()) : null);
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public String getText() {
      return "title";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.title.usage";
   }
}
