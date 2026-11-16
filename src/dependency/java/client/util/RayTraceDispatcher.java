package client.util;

import client.setting.CombatBooleanProperty;
import client.setting.CombatNumberProperty;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class RayTraceDispatcher extends Listener87Entry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         return resolveList(textArray, this.getDoFireTickManager().getTextArray());
      } else {
         return textArray.length == 2 && this.getDoFireTickManager().matchesText(textArray[0], CombatBooleanProperty.BOOLEAN_VALUE)
            ? resolveList(textArray, new String[]{"true", "false"})
            : null;
      }
   }

   private DoFireTickManager getDoFireTickManager() {
      return VanillaRunnable.getVanillaRunnable().resolveWorldServer(0).getDoFireTickManager();
   }

   @Override
   public String getText() {
      return "gamerule";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      DoFireTickManager doFireTickManager = this.getDoFireTickManager();
      String text = textArray.length > 0 ? textArray[0] : "";
      String text2 = textArray.length > 1 ? resolveText(textArray, 1) : "";
      switch (textArray.length) {
         case 0:
            iCommandSender.applyIChatComponent(new ChatComponentText(resolveText(doFireTickManager.getTextArray())));
            break;
         case 1:
            if (!doFireTickManager.containsKey(text)) {
               throw new InternalExceptionAdapter("commands.gamerule.norule", new Object[]{text});
            }

            String text3 = doFireTickManager.resolveText(text);
            iCommandSender.applyIChatComponent(new ChatComponentText(text).resolveIChatComponent(" = ").resolveIChatComponent(text3));
            iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, doFireTickManager.computeCount(text));
            break;
         default:
            if (doFireTickManager.matchesText(text, CombatBooleanProperty.BOOLEAN_VALUE) && !"true".equals(text2) && !"false".equals(text2)) {
               throw new InternalExceptionAdapter("commands.generic.boolean.invalid", new Object[]{text2});
            }

            doFireTickManager.applyText(text, text2);
            applyDoFireTickManager(doFireTickManager, text);
            applyICommandSender(iCommandSender, this, "commands.gamerule.success", new Object[0]);
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.gamerule.usage";
   }

   public static void applyDoFireTickManager(DoFireTickManager doFireTickManager, String text) {
      if ("reducedDebugInfo".equals(text)) {
         byte byteValue = (byte)(doFireTickManager.matchesText(text) ? 22 : 23);

         for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable)(VanillaRunnable.getVanillaRunnable().getMovingManager().getList())) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S19PacketEntityStatus(entityPlayerAdapter, byteValue));
         }
      }
   }
}
