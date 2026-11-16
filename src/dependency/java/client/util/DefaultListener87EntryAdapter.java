package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class DefaultListener87EntryAdapter extends Listener87Entry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.playsound.usage";
   }

   @Override
   public String getText() {
      return "playsound";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher(this.resolveText(iCommandSender), new Object[0]);
      } else {
         int count = 0;
         count++;
         String text = textArray[0];
         count++;
         EntityPlayerAdapter entityPlayerAdapter = resolveEntityPlayerAdapter(iCommandSender, textArray[1]);
         Vec3 vec3 = iCommandSender.getVec3();
         double posX = vec3.xCoord;
         if (textArray.length > 2) {
            posX = computeValue(posX, textArray[count++], true);
         }

         double posY = vec3.yCoord;
         if (textArray.length > count) {
            posY = computeValue(posY, textArray[count++], 0, 0, false);
         }

         double posZ = vec3.zCoord;
         if (textArray.length > count) {
            posZ = computeValue(posZ, textArray[count++], true);
         }

         double offset = 1.0;
         if (textArray.length > count) {
            offset = computeValue(textArray[count++], 0.0, Float.MAX_VALUE);
         }

         double offset2 = 1.0;
         if (textArray.length > count) {
            offset2 = computeValue(textArray[count++], 0.0, 2.0);
         }

         double offset3 = 0.0;
         if (textArray.length > count) {
            offset3 = computeValue(textArray[count], 0.0, 1.0);
         }

         double offset4 = offset > 1.0 ? offset * 16.0 : 16.0;
         if (entityPlayerAdapter.getDouble4(posX, posY, posZ) > offset4) {
            if (offset3 <= 0.0) {
               throw new InternalExceptionAdapter("commands.playsound.playerTooFar", new Object[]{entityPlayerAdapter.getText()});
            }

            double offset5 = posX - entityPlayerAdapter.posX;
            double offset6 = posY - entityPlayerAdapter.posY;
            double offset7 = posZ - entityPlayerAdapter.posZ;
            double offset8 = Math.sqrt(offset5 * offset5 + offset6 * offset6 + offset7 * offset7);
            if (offset8 > 0.0) {
               posX = entityPlayerAdapter.posX + offset5 / offset8 * 2.0;
               posY = entityPlayerAdapter.posY + offset6 / offset8 * 2.0;
               posZ = entityPlayerAdapter.posZ + offset7 / offset8 * 2.0;
            }

            offset = offset3;
         }

         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S29PacketSoundEffect(text, posX, posY, posZ, (float)offset, (float)offset2));
         applyICommandSender(iCommandSender, this, "commands.playsound.success", new Object[]{text, entityPlayerAdapter.getText()});
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 1;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 2
         ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray())
         : (textArray.length > 2 && textArray.length <= 5 ? resolveListByTextArray(textArray, 2, blockPos) : null);
   }
}
