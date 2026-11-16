package client.util;

import client.setting.WorldSettingsGameType;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;

public class RayTraceBuilder extends CustomListener87EntryAdapter {
   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.defaultgamemode.usage";
   }

   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType) {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      vanillaRunnable.applyWorldSettingsGameType(worldSettingsGameType);
      if (vanillaRunnable.isEnabled10()) {
         for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable)VanillaRunnable.getVanillaRunnable().getMovingManager().getList()) {
            entityPlayerAdapter.applyWorldSettingsGameType(worldSettingsGameType);
            entityPlayerAdapter.fallDistance = 0.0F;
         }
      }
   }

   @Override
   public String getText() {
      return "defaultgamemode";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length <= 0) {
         throw new AimDispatcher("commands.defaultgamemode.usage", new Object[0]);
      } else {
         WorldSettingsGameType worldSettingsGameType = this.resolveWorldSettingsGameType(iCommandSender, textArray[0]);
         this.applyWorldSettingsGameType(worldSettingsGameType);
         applyICommandSender(
            iCommandSender,
            this,
            "commands.defaultgamemode.success",
            new Object[]{new BaseManagerStyleIChatComponentAdapter("gameMode." + worldSettingsGameType.getLabelText(), new Object[0])}
         );
      }
   }
}
