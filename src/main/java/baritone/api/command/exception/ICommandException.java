package baritone.api.command.exception;

import baritone.api.command.ICommand;
import baritone.api.utils.Helper;
import java.util.List;
import net.minecraft.util.EnumChatFormatting;

public interface ICommandException {
   String getMessage();

   default void handle(ICommand command, List list) {
      Helper.HELPER.logDirect3(this.getMessage(), EnumChatFormatting.RED);
   }
}
