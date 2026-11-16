package client.util;

import net.minecraft.client.gui.GuiScreen;

public class GeometrySynchronizer extends GuiScreen {
   public final String titleText;
   public final String messageText;

   public GeometrySynchronizer(String titleText, String messageText) {
      this.titleText = titleText;
      this.messageText = messageText;
   }
}
