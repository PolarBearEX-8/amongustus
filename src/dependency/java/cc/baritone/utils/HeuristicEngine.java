package cc.baritone.utils;

import net.minecraft.client.gui.GuiScreen;

public class HeuristicEngine extends GuiScreen {
   public String titleText;
   public int statusCode;

   public HeuristicEngine(String titleText, int statusCode) {
      this.titleText = titleText;
      this.statusCode = statusCode;
   }

   public void applyText(String text, int color) {
   }

   public void applyTextByText(String text, int color) {
   }

   public void updateState() {
   }
}
