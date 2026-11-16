package net.minecraft.command;

import java.util.List;
import java.util.Map;
import net.minecraft.util.BlockPos;

public interface ICommandManager {
   Map getMap();

   List resolveList(ICommandSender iCommandSender);

   List resolveList(ICommandSender iCommandSender, String text, BlockPos blockPos);

   int computeCount(ICommandSender iCommandSender, String text);
}
