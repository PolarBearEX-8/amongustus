package client.util;

import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class RayTraceDirector extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length >= 1 ? resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray()) : null;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.ban.usage";
   }

   @Override
   public boolean matchesICommandSender(ICommandSender iCommandSender) {
      return VanillaRunnable.getVanillaRunnable().getMovingManager().getInternalLoggerEntryAdapter().isEnabled() && super.matchesICommandSender(iCommandSender);
   }

   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length >= 1 && textArray[0].length() > 0) {
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         GameProfile gameProfile = vanillaRunnable.getYyyyMMDdHHMmSsZManager().resolveGameProfile(textArray[0]);
         if (gameProfile == null) {
            throw new InternalExceptionAdapter("commands.ban.failed", new Object[]{textArray[0]});
         } else {
            String text = null;
            if (textArray.length >= 2) {
               text = resolveIChatComponent(iCommandSender, textArray, 1).getString();
            }

            SimpleDateFormatEntryAdapter simpleDateFormatEntryAdapter = new SimpleDateFormatEntryAdapter(gameProfile, (Date)null, iCommandSender.getText(), (Date)null, text);
            vanillaRunnable.getMovingManager().getInternalLoggerEntryAdapter().applyTargetStack(simpleDateFormatEntryAdapter);
            EntityPlayerAdapter entityPlayerAdapter = vanillaRunnable.getMovingManager().resolveEntityPlayerAdapter(textArray[0]);
            if (entityPlayerAdapter != null) {
               entityPlayerAdapter.auxiliaryListenerAdapter4.applyTextByText("You are banned from this server.");
            }

            applyICommandSender(iCommandSender, this, "commands.ban.success", new Object[]{textArray[0]});
         }
      } else {
         throw new AimDispatcher("commands.ban.usage", new Object[0]);
      }
   }

   @Override
   public String getText() {
      return "ban";
   }
}
