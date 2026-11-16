package client.util;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class BezierSystem extends Listener87Entry {
   @Override
   public int getCount() {
      return 3;
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         return resolveList(textArray, new String[]{"on", "off", "list", "add", "remove", "reload"});
      } else {
         if (textArray.length == 2) {
            if (textArray[0].equals("remove")) {
               return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getMovingManager().getTextArray3());
            }

            if (textArray[0].equals("add")) {
               return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getYyyyMMDdHHMmSsZManager().getTextArray());
            }
         }

         return null;
      }
   }

   @Override
   public String getText() {
      return "whitelist";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.whitelist.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.whitelist.usage", new Object[0]);
      } else {
         VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
         if (textArray[0].equals("on")) {
            vanillaRunnable.getMovingManager().setEnabled(true);
            applyICommandSender(iCommandSender, this, "commands.whitelist.enabled", new Object[0]);
         } else if (textArray[0].equals("off")) {
            vanillaRunnable.getMovingManager().setEnabled(false);
            applyICommandSender(iCommandSender, this, "commands.whitelist.disabled", new Object[0]);
         } else if (textArray[0].equals("list")) {
            iCommandSender.applyIChatComponent(
               new BaseManagerStyleIChatComponentAdapter(
                  "commands.whitelist.list",
                  new Object[]{vanillaRunnable.getMovingManager().getTextArray3().length, vanillaRunnable.getMovingManager().getTextArrayAsTextArray().length}
               )
            );
            String[] textArray2 = vanillaRunnable.getMovingManager().getTextArray3();
            iCommandSender.applyIChatComponent(new ChatComponentText(resolveText(textArray2)));
         } else if (textArray[0].equals("add")) {
            if (textArray.length < 2) {
               throw new AimDispatcher("commands.whitelist.add.usage", new Object[0]);
            }

            GameProfile gameProfile = vanillaRunnable.getYyyyMMDdHHMmSsZManager().resolveGameProfile(textArray[1]);
            if (gameProfile == null) {
               throw new InternalExceptionAdapter("commands.whitelist.add.failed", new Object[]{textArray[1]});
            }

            vanillaRunnable.getMovingManager().applyGameProfileByGameProfile(gameProfile);
            applyICommandSender(iCommandSender, this, "commands.whitelist.add.success", new Object[]{textArray[1]});
         } else if (textArray[0].equals("remove")) {
            if (textArray.length < 2) {
               throw new AimDispatcher("commands.whitelist.remove.usage", new Object[0]);
            }

            GameProfile gameProfile2 = vanillaRunnable.getMovingManager().getAuxiliaryLoggerEntryAdapter().resolveGameProfile(textArray[1]);
            if (gameProfile2 == null) {
               throw new InternalExceptionAdapter("commands.whitelist.remove.failed", new Object[]{textArray[1]});
            }

            vanillaRunnable.getMovingManager().applyGameProfile(gameProfile2);
            applyICommandSender(iCommandSender, this, "commands.whitelist.remove.success", new Object[]{textArray[1]});
         } else if (textArray[0].equals("reload")) {
            vanillaRunnable.getMovingManager().updateState();
            applyICommandSender(iCommandSender, this, "commands.whitelist.reloaded", new Object[0]);
         }
      }
   }
}
