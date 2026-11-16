package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.util.BlockPos;

public class DynamicListener87EntryAdapter extends Listener87Entry {
   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length > 0 && textArray.length <= 3 ? resolveListByTextArray(textArray, 0, blockPos) : null;
   }

   @Override
   public String getText() {
      return "setworldspawn";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.setworldspawn.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      BlockPos blockPos;
      if (textArray.length == 0) {
         blockPos = resolveEntityPlayerAdapter(iCommandSender).getBlockPos();
      } else {
         if (textArray.length != 3 || iCommandSender.getWorldObj() == null) {
            throw new AimDispatcher("commands.setworldspawn.usage", new Object[0]);
         }

         blockPos = resolveBlockPos(iCommandSender, textArray, 0, true);
      }

      iCommandSender.getWorldObj().applyBlockPos(blockPos);
      VanillaRunnable.getVanillaRunnable().getMovingManager().applyPacket(new S05PacketSpawnPosition(blockPos));
      applyICommandSender(iCommandSender, this, "commands.setworldspawn.success", new Object[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
   }
}
