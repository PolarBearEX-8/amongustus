package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public interface ComparableExtension extends Comparable {
   List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos);

   boolean matchesTextArray(String[] textArray, int count);

   List asList();

   String resolveText(ICommandSender iCommandSender);

   void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter;

   boolean matchesICommandSender(ICommandSender iCommandSender);

   String getText();
}
