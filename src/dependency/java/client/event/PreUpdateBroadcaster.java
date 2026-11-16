package client.event;

import client.util.ComparableExtension;
import net.minecraft.command.ICommandSender;

public interface PreUpdateBroadcaster {
   void applyICommandSender(ICommandSender iCommandSender, ComparableExtension comparableExtension, int count, String text, Object[] entryArray);
}
