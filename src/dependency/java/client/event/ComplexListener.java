package client.event;

import net.minecraft.util.IChatComponent;

public interface ComplexListener {
   boolean processAction();

   String getText();

   IChatComponent getChatComponent();
}
