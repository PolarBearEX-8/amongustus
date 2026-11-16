package client.util;

import client.setting.WorldSettingsGameType;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;

public class CustomListener87EntryAdapter extends Listener87Entry {
   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, new String[]{"survival", "creative", "adventure", "spectator"})
         : (textArray.length == 2 ? resolveList(textArray, this.getTextArray()) : null);
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 1;
   }

   @Override
   public String getText() {
      return "gamemode";
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.gamemode.usage";
   }

   public WorldSettingsGameType resolveWorldSettingsGameType(ICommandSender iCommandSender, String text) throws InternalExceptionAdapter, AimDirector {
      return text.equalsIgnoreCase(WorldSettingsGameType.SURVIVAL.getLabelText()) || text.equalsIgnoreCase("s")
         ? WorldSettingsGameType.SURVIVAL
         : (
            text.equalsIgnoreCase(WorldSettingsGameType.CREATIVE.getLabelText()) || text.equalsIgnoreCase("c")
               ? WorldSettingsGameType.CREATIVE
               : (
                  text.equalsIgnoreCase(WorldSettingsGameType.ADVENTURE.getLabelText()) || text.equalsIgnoreCase("a")
                     ? WorldSettingsGameType.ADVENTURE
                     : (
                        !text.equalsIgnoreCase(WorldSettingsGameType.SPECTATOR.getLabelText()) && !text.equalsIgnoreCase("sp")
                           ? AdvancedLongEntry.resolveWorldSettingsGameType(computeCount(text, 0, WorldSettingsGameType.values().length - 2))
                           : WorldSettingsGameType.SPECTATOR
                     )
               )
         );
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length <= 0) {
         throw new AimDispatcher("commands.gamemode.usage", new Object[0]);
      } else {
         WorldSettingsGameType worldSettingsGameType = this.resolveWorldSettingsGameType(iCommandSender, textArray[0]);
         EntityPlayerAdapter entityPlayerAdapter = textArray.length >= 2 ? resolveEntityPlayerAdapter(iCommandSender, textArray[1]) : resolveEntityPlayerAdapter(iCommandSender);
         entityPlayerAdapter.applyWorldSettingsGameType(worldSettingsGameType);
         entityPlayerAdapter.fallDistance = 0.0F;
         if (iCommandSender.getWorldObj().getDoFireTickManager().matchesText("sendCommandFeedback")) {
            entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("gameMode.changed", new Object[0]));
         }

         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("gameMode." + worldSettingsGameType.getLabelText(), new Object[0]);
         if (entityPlayerAdapter != iCommandSender) {
            applyICommandSender(iCommandSender, this, 1, "commands.gamemode.success.other", new Object[]{entityPlayerAdapter.getText(), baseManagerStyleIChatComponentAdapter});
         } else {
            applyICommandSender(iCommandSender, this, 1, "commands.gamemode.success.self", new Object[]{baseManagerStyleIChatComponentAdapter});
         }
      }
   }
}
