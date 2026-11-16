package client.util;

import client.setting.GameSettingsPeacefulType;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class CommonListener87EntryAdapter extends Listener87Entry {
   public GameSettingsPeacefulType resolveGameSettingsPeacefulType(String text) throws InternalExceptionAdapter, AimDirector {
      return text.equalsIgnoreCase("peaceful") || text.equalsIgnoreCase("p")
         ? GameSettingsPeacefulType.PEACEFUL
         : (
            text.equalsIgnoreCase("easy") || text.equalsIgnoreCase("e")
               ? GameSettingsPeacefulType.EASY
               : (
                  !text.equalsIgnoreCase("normal") && !text.equalsIgnoreCase("n")
                     ? (
                        !text.equalsIgnoreCase("hard") && !text.equalsIgnoreCase("h")
                           ? GameSettingsPeacefulType.resolveGameSettingsPeacefulType(computeCount(text, 0, 3))
                           : GameSettingsPeacefulType.HARD
                     )
                     : GameSettingsPeacefulType.NORMAL
               )
         );
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.difficulty.usage";
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length <= 0) {
         throw new AimDispatcher("commands.difficulty.usage", new Object[0]);
      } else {
         GameSettingsPeacefulType gameSettingsPeacefulType = this.resolveGameSettingsPeacefulType(textArray[0]);
         VanillaRunnable.getVanillaRunnable().applyGameSettingsPeacefulType(gameSettingsPeacefulType);
         applyICommandSender(
            iCommandSender, this, "commands.difficulty.success", new Object[]{new BaseManagerStyleIChatComponentAdapter(gameSettingsPeacefulType.getLabelText(), new Object[0])}
         );
      }
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String getText() {
      return "difficulty";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1 ? resolveList(textArray, new String[]{"peaceful", "easy", "normal", "hard"}) : null;
   }
}
