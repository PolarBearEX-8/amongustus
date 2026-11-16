package client.util;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class RayTraceObserver extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length == 1 && textArray[0].length() > 0) {
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         GameProfile gameProfile = vanillaRunnable.getYyyyMMDdHHMmSsZManager().resolveGameProfile(textArray[0]);
         if (gameProfile == null) {
            throw new InternalExceptionAdapter("commands.op.failed", new Object[]{textArray[0]});
         } else {
            vanillaRunnable.getMovingManager().applyGameProfile2(gameProfile);
            applyICommandSender(iCommandSender, this, "commands.op.success", new Object[]{textArray[0]});
         }
      } else {
         throw new AimDispatcher("commands.op.usage", new Object[0]);
      }
   }

   @Override
   public String getText() {
      return "op";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         String text = textArray[textArray.length - 1];
         ArrayList list = Lists.newArrayList();

         for (GameProfile gameProfile : VanillaRunnable.getVanillaRunnable().getGameProfileArray()) {
            if (!VanillaRunnable.getVanillaRunnable().getMovingManager().matchesGameProfileByGameProfile(gameProfile) && matchesText(text, gameProfile.getName())) {
               list.add(gameProfile.getName());
            }
         }

         return list;
      } else {
         return null;
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.op.usage";
   }

   @Override
   public int getCount() {
      return 3;
   }
}
