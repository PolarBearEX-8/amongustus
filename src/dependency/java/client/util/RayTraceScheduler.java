package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class RayTraceScheduler extends Listener87Entry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray())
         : (textArray.length > 1 && textArray.length <= 4 ? resolveListByTextArray(textArray, 1, blockPos) : null);
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.spawnpoint.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length > 1 && textArray.length < 4) {
         throw new AimDispatcher("commands.spawnpoint.usage", new Object[0]);
      } else {
         EntityPlayerAdapter entityPlayerAdapter = textArray.length > 0 ? resolveEntityPlayerAdapter(iCommandSender, textArray[0]) : resolveEntityPlayerAdapter(iCommandSender);
         BlockPos blockPos = textArray.length > 3 ? resolveBlockPos(iCommandSender, textArray, 1, true) : entityPlayerAdapter.getBlockPos();
         if (entityPlayerAdapter.worldObj != null) {
            entityPlayerAdapter.applyBlockPos(blockPos, true);
            applyICommandSender(iCommandSender, this, "commands.spawnpoint.success", new Object[]{entityPlayerAdapter.getText(), blockPos.getX(), blockPos.getY(), blockPos.getZ()});
         }
      }
   }

   @Override
   public String getText() {
      return "spawnpoint";
   }
}
