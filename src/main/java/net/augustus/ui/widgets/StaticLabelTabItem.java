package net.augustus.ui.widgets;

public class StaticLabelTabItem implements GuiTabPage {
   private final String title;

   public StaticLabelTabItem(String str) {
      this.title = str;
   }

   @Override
   public int getContentHeight() {
      return 0;
   }

   @Override
   public String getTitle() {
      return this.title;
   }

   @Override
   public void drawContent(int n, int n2, int n3, int n4) {
   }
}
