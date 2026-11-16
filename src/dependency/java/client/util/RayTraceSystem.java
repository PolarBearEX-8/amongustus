package client.util;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class RayTraceSystem extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length == 1 && textArray[0].length() > 0) {
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         GameProfile gameProfile = vanillaRunnable.getMovingManager().getInternalLoggerEntryAdapter().resolveGameProfile(textArray[0]);
         if (gameProfile == null) {
            throw new InternalExceptionAdapter("commands.unban.failed", new Object[]{textArray[0]});
         } else {
            vanillaRunnable.getMovingManager().getInternalLoggerEntryAdapter().applyTarget(gameProfile);
            applyICommandSender(iCommandSender, this, "commands.unban.success", new Object[]{textArray[0]});
         }
      } else {
         throw new AimDispatcher("commands.unban.usage", new Object[0]);
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.unban.usage";
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public String getText() {
      return "pardon";
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return VanillaRunnable.getVanillaRunnable().getMovingManager().getInternalLoggerEntryAdapter().isEnabled() && super.matchesICommandSender(iCommandSender);
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getMovingManager().getInternalLoggerEntryAdapter().getTextArray())
         : null;
   }
}
