package net.augustus.cosmetics;


import net.augustus.cosmetics.ui.CosmeticsMenuTab;
import net.augustus.cosmetics.ui.CosmeticsWidgetList;
public interface CosmeticsMenuRenderer {
   void drawListScrollbar(CosmeticsWidgetList cosmeticsWidgetList);

   void drawRoundedRect(int n, int n2, int n3, int n4, int n5, int n6);

   void drawGridScrollbar();

   default void drawTooltip(String[] strings) {
   }

   void drawBackground();

   void setPopupOverlay(boolean flag, float f);

   void drawTab(int n, boolean flag, boolean flag2, CosmeticsMenuTab cosmeticsMenuTab);
}
