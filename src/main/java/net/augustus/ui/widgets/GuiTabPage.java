package net.augustus.ui.widgets;

public interface GuiTabPage {
   int getContentHeight();

   String getTitle();

   void drawContent(int n, int n2, int n3, int n4);
}
