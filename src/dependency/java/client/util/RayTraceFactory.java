package client.util;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class RayTraceFactory extends Listener87Entry {
   @Override
   public String getText() {
      return "deop";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length == 1 && textArray[0].length() > 0) {
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         GameProfile gameProfile = vanillaRunnable.getMovingManager().getExtendedLoggerEntryAdapter().resolveGameProfile(textArray[0]);
         if (gameProfile == null) {
            throw new InternalExceptionAdapter("commands.deop.failed", new Object[]{textArray[0]});
         } else {
            vanillaRunnable.getMovingManager().applyGameProfile3(gameProfile);
            applyICommandSender(iCommandSender, this, "commands.deop.success", new Object[]{textArray[0]});
         }
      } else {
         throw new AimDispatcher("commands.deop.usage", new Object[0]);
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getMovingManager().getTextArray()) : null;
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.deop.usage";
   }
}
