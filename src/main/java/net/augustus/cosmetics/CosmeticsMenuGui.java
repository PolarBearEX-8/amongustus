package net.augustus.cosmetics;

import net.augustus.cosmetics.ui.CosmeticsMenuHook;
import net.augustus.cosmetics.ui.CosmeticsMenuTab;
import net.augustus.cosmetics.ui.CosmeticsWidgetList;
import java.util.ArrayList;

public class CosmeticsMenuGui {
   private ArrayList tabs = new ArrayList();
   private boolean popupOpen;
   private int height;
   private static CosmeticsMenuHook menuHook;
   private int gridHeight;
   private int visibleTabCount;
   private int totalGridRows;
   private CosmeticsMenuRenderer renderer;
   private boolean gridScrollable;
   public static final CosmeticsMenuTab SPACER_TAB = new CosmeticsMenuTab("", "");
   private CosmeticsWidgetList popupWidgetList;
   private int gridWidth;
   public static final CosmeticsMenuTab DIVIDER_TAB = new CosmeticsMenuTab("", "");
   private int scrollbarTopY;
   private int tabScrollOffset;
   private CosmeticsWidgetList mainWidgetList;
   private boolean tabBarScrollable;
   private int gridColumns;
   private boolean initialized;
   private int selectedTabIndex;
   private int width;
   private int scrollbarBottomY;
   private int selectedRowIndex;
   private int gridScrollRow;
   private CosmeticsMenuElement selectedElement;
   private int visibleGridRows;
   public static final int ELEMENT_PADDING = 9;

   public int getContentRightX() {
      return 318;
   }

   public CosmeticsMenuRenderer getRenderer() {
      return this.renderer;
   }

   public void keyTyped(char typedChar, int keyCode) {
      if (menuHook != null) {
         menuHook.onKeyTyped();
      }

      if (this.tabs.size() > this.selectedTabIndex) {
         CosmeticsMenuTab cosmeticsMenuTab = (CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex);
         if (!cosmeticsMenuTab.isListLayout()) {
            cosmeticsMenuTab.getElements().forEach(obj -> dispatchKeyTyped(typedChar, keyCode, (CosmeticsMenuElement)obj));
         }

         if (cosmeticsMenuTab.isListLayout()) {
            this.mainWidgetList.keyTyped(typedChar, keyCode);
         }

         if (this.popupOpen) {
            this.popupWidgetList.keyTyped(typedChar, keyCode);
         }
      }
   }

   public int getContentLeftX() {
      return 93;
   }

   public static void setMenuHook(CosmeticsMenuHook cosmeticsMenuHook) {
      menuHook = cosmeticsMenuHook;
   }

   public void setPopupOpen(boolean flag) {
      this.setPopupOpen(flag, (CosmeticsMenuElement)null);
   }

   public int getHeaderHeight() {
      return 22;
   }

   public ArrayList getTabs() {
      return this.tabs;
   }

   public int getScrollbarBottomY() {
      return this.scrollbarBottomY;
   }

   public int getSidebarLeftX() {
      return 0;
   }

   public void refreshLayout() {
      if (this.tabs.size() - 1 >= this.selectedTabIndex) {
         for (CosmeticsMenuTab cosmeticsMenuTab : (Iterable<CosmeticsMenuTab>)(this.tabs)) {
            for (int i = 0; i < cosmeticsMenuTab.getElements().size(); i++) {
               ((CosmeticsMenuElement)cosmeticsMenuTab.getElements().get(i)).attachToGui(this, i);
            }
         }

         this.gridScrollRow = 0;
         int size = ((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).getElements().size();
         this.gridScrollable = size > this.gridColumns * this.visibleGridRows;
         this.totalGridRows = Math.round((float)size / (float)this.gridColumns);
      }
   }

   public void scrollGrid(int n) {
      this.gridScrollRow -= n;
      this.gridScrollRow = this.gridScrollRow + this.visibleGridRows > this.totalGridRows ? this.totalGridRows - this.visibleGridRows : this.gridScrollRow;
      this.gridScrollRow = this.gridScrollRow < 0 ? 0 : this.gridScrollRow;

      for (int i = 0; i < ((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).getElements().size(); i++) {
         CosmeticsMenuElement cosmeticsMenuElement = (CosmeticsMenuElement)((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).getElements().get(i);
         if (i < this.gridScrollRow * this.gridColumns || i > (this.gridScrollRow + this.visibleGridRows) * this.gridColumns) {
            cosmeticsMenuElement.setHovered(false);
         }
      }

      if (this.popupOpen) {
         if (this.selectedElement.getIndex() >= this.gridScrollRow * this.gridColumns && this.selectedElement.getIndex() <= (this.gridScrollRow + this.visibleGridRows) * this.gridColumns) {
            this.selectedRowIndex = this.selectedElement.getIndex() / this.gridColumns - this.gridScrollRow;
         } else if (this.selectedElement instanceof CosmeticsMenuEntry) {
            this.selectedRowIndex = this.selectedElement.getIndex() - this.mainWidgetList.getScrollOffset();
         }

         CosmeticsWidgetList popupWidgetList2 = this.popupWidgetList;
         int n2 = this.getContentRightX() + (this.isScrollable() ? 5 : 0);
         int selectedRowIndex2 = this.selectedRowIndex;
         popupWidgetList2.layout(n2, this.height, selectedRowIndex2);
      } else {
         this.popupOpen = false;
      }
   }

   public void onTick() {
      if (this.tabs.size() > this.selectedTabIndex && ((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).isListLayout()) {
      }
   }

   public int getScrollbarTopY() {
      return this.scrollbarTopY;
   }

   public void handleMouseScroll(int n, int n2) {
      int n3 = (int)Math.signum((float)MoreCosmeticsCore.getInstance().getMinecraftBridge().getScrollDelta());
      if (n3 != 0) {
         if (this.tabBarScrollable && n < this.getContentLeftX()) {
            this.tabScrollOffset = CosmeticMathHelper.clampInt(this.tabScrollOffset -= n3, 0, this.tabs.size() - this.visibleTabCount);
         } else {
            if (this.gridScrollable) {
               this.scrollGrid(n3);
            }

            if (((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).isListLayout() && this.mainWidgetList.isScrollable() && n < this.getContentRightX()) {
               this.mainWidgetList.scroll(n3);
            }

            if (n > this.getContentRightX() + 4 && this.popupOpen && this.popupWidgetList.isScrollable()) {
               this.popupWidgetList.scroll(n3);
            }
         }
      }
   }


   public boolean isScrollable() {
      return this.gridScrollable || this.mainWidgetList.isScrollable();
   }

   public CosmeticsMenuGui(long l, int n, int n2, CosmeticsMenuRenderer cosmeticsMenuRenderer) {
      this.height = n2;
      this.width = n;
      this.renderer = cosmeticsMenuRenderer;
      this.mainWidgetList = new CosmeticsWidgetList(this, this.getContentLeftX(), false);
      this.popupWidgetList = new CosmeticsWidgetList(this, this.getContentRightX() + (this.gridScrollable ? 5 : 0), true);
   }

   private void handleTabClick(int n, int n2) {
      if (n >= this.getSidebarLeftX() && n <= this.getContentLeftX() && n2 >= this.getHeaderHeight() + 2 && n2 <= this.getHeight()) {
         int n3 = this.tabScrollOffset + (n2 - this.getHeaderHeight() + 2) / 21;
         if (this.tabs.size() > n3) {
            CosmeticsMenuTab cosmeticsMenuTab = (CosmeticsMenuTab)this.tabs.get(n3);
            if (cosmeticsMenuTab == DIVIDER_TAB || cosmeticsMenuTab == SPACER_TAB) {
               return;
            }

            if (cosmeticsMenuTab.hasAction()) {
               cosmeticsMenuTab.runAction();
               return;
            }

            this.selectedTabIndex = n3;
            if (cosmeticsMenuTab.isListLayout()) {
               this.mainWidgetList.recalculateScroll();
               this.mainWidgetList.layout(this.getContentLeftX(), this.getHeight(), (byte)0);
            }

            this.selectedElement = null;
            this.popupOpen = false;
         }

         this.refreshLayout();
      }
   }

   public void setPopupOpen(boolean flag, CosmeticsMenuElement cosmeticsMenuElement) {
      if (cosmeticsMenuElement != null) {
         this.popupWidgetList.setEntries(cosmeticsMenuElement.getChildren());
         if (cosmeticsMenuElement instanceof CosmeticsMenuEntry) {
            this.selectedRowIndex = cosmeticsMenuElement.getIndex() - this.mainWidgetList.getScrollOffset();
            if (cosmeticsMenuElement.hasChildren()) {
               ((CosmeticsMenuEntry)cosmeticsMenuElement).withPopupActive(flag);
            }
         } else {
            this.selectedRowIndex = cosmeticsMenuElement.getIndex() / this.gridColumns - this.gridScrollRow;
         }

         this.selectedElement = cosmeticsMenuElement;
      }

      this.popupOpen = flag;

      for (CosmeticsMenuElement cosmeticsMenuElement2 : (Iterable<CosmeticsMenuElement>)(((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).getElements())) {
         cosmeticsMenuElement2.setInteractable(!this.popupOpen);
         if (!flag && cosmeticsMenuElement2 instanceof CosmeticsMenuEntry) {
            ((CosmeticsMenuEntry)cosmeticsMenuElement2).withPopupActive(false);
         }
      }

      if (this.popupOpen) {
         if (this.selectedElement != null) {
            this.selectedElement.setInteractable(true);
         }

         CosmeticsWidgetList popupWidgetList3 = this.popupWidgetList;
         int n = this.getContentRightX() + (this.isScrollable() ? 5 : 0);
         int selectedRowIndex2 = this.selectedRowIndex;
         popupWidgetList3.layout(n, this.height, selectedRowIndex2);
      }
   }

   public void resize(int n, int n2) {
      this.width = n;
      this.height = n2;
      this.gridWidth = this.getContentRightX() + 1 - this.getContentLeftX();
      this.gridHeight = this.getHeight() - this.getHeaderHeight();
      this.gridColumns = this.gridWidth / 75;
      this.visibleGridRows = this.gridHeight / 54;
      this.gridColumns = this.gridColumns < 1 ? 1 : this.gridColumns;
      this.visibleGridRows = this.visibleGridRows < 1 ? 1 : this.visibleGridRows;
      this.refreshLayout();
      if (this.popupOpen) {
         this.selectedRowIndex = this.selectedElement.getIndex() / this.gridColumns - this.gridScrollRow;
         this.popupWidgetList.layout(this.getContentRightX() + (this.isScrollable() ? 5 : 0), this.getHeight(), this.selectedRowIndex);
      }

      if (this.tabs.size() > this.selectedTabIndex && ((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).isListLayout()) {
         CosmeticsWidgetList mainWidgetList2 = this.mainWidgetList;
         int height3 = this.getHeight();
         mainWidgetList2.layout(this.getContentLeftX(), height3);
      }
   }

   public String getSelectedTabTitle() {
      String str;
      if (this.tabs.size() > this.selectedTabIndex) {
         str = ((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).getEffectiveTitle();
      } else {
         str = "";
      }

      return str;
   }

   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (this.popupOpen && mouseX < this.getContentRightX()) {
         this.setPopupOpen(false, (CosmeticsMenuElement)null);
      }

      this.handleTabClick(mouseX, mouseY);
      if (this.tabs.size() > this.selectedTabIndex) {
         CosmeticsMenuTab cosmeticsMenuTab = (CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex);
         if (!cosmeticsMenuTab.isListLayout()) {
            cosmeticsMenuTab.getElements().forEach(obj -> {
               ((CosmeticsMenuElement)obj).mouseClicked(mouseX, mouseY, mouseButton);
            });
         }

         if (this.popupOpen) {
            this.popupWidgetList.mouseClicked(mouseX, mouseY, mouseButton);
         }

         if (cosmeticsMenuTab.isListLayout()) {
            this.mainWidgetList.mouseClicked(mouseX, mouseY, mouseButton);
         }
      }

      boolean flag2;
      if (menuHook == null) {
         flag2 = false;
      } else {
         CosmeticsMenuRenderer renderer2 = this.renderer;
         flag2 = menuHook.onMouseClicked(renderer2, mouseX, mouseY, mouseButton);
      }

      return flag2;
   }

   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (this.tabs.size() > this.selectedTabIndex) {
         CosmeticsMenuTab cosmeticsMenuTab = (CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex);
         if (!cosmeticsMenuTab.isListLayout()) {
            cosmeticsMenuTab.getElements().forEach(obj -> ((CosmeticsMenuElement)obj).mouseReleased(mouseX, mouseY));
         }

         if (this.popupOpen) {
            this.popupWidgetList.mouseReleased(mouseX, mouseY, state);
         }

         if (cosmeticsMenuTab.isListLayout()) {
            this.mainWidgetList.mouseReleased(mouseX, mouseY, state);
         }
      }
   }

   public CosmeticsMenuTab getSelectedTab() {
      return (CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex);
   }

   private static void dispatchKeyTyped(char ch, int n, CosmeticsMenuElement cosmeticsMenuElement) {
      cosmeticsMenuElement.keyTyped(ch, n);
   }

   public void render(Object obj, int n, int n2, boolean flag) {
      if (menuHook != null) {
         menuHook.onRender(obj, n, n2, flag);
      }

      if (flag) {
         int size = this.tabs.size();
         if (size != 0) {
            if (this.selectedTabIndex >= size) {
               this.selectedTabIndex = size - 1;
            }

            int n3 = this.getHeaderHeight() + 10;
            byte b = 21;
            if (!(this.tabBarScrollable = n3 + b + this.tabs.size() * b > this.height)) {
               this.tabScrollOffset = 0;
            } else {
               this.visibleTabCount = 0;

               for (int i = n3 + b + b; i < this.height; this.visibleTabCount++) {
                  i += b;
               }

               this.tabScrollOffset = CosmeticMathHelper.clampInt(this.tabScrollOffset, 0, this.tabs.size() - this.visibleTabCount);
            }

            for (int j = this.tabScrollOffset; j < this.tabs.size(); j++) {
               CosmeticsMenuTab cosmeticsMenuTab = (CosmeticsMenuTab)this.tabs.get(j);
               if (n3 + b + b > this.height) {
                  break;
               }

               if (cosmeticsMenuTab != DIVIDER_TAB) {
                  boolean flag2 = this.selectedTabIndex == j;
                  boolean flag3 = j == 0;
                  this.renderer.drawTab(n3, flag2, flag3, cosmeticsMenuTab);
               }

               if (this.selectedTabIndex == j) {
                  if (cosmeticsMenuTab.isListLayout()) {
                     this.mainWidgetList.draw(obj, n, n2, (short)219);
                  } else {
                     if (this.gridScrollable) {
                        float f = (float)this.gridScrollRow / (float)this.totalGridRows;
                        this.scrollbarTopY = (int)((float)this.gridHeight * f) + this.getHeaderHeight();
                        float f2 = (float)(this.gridScrollRow + this.visibleGridRows) / (float)this.totalGridRows;
                        this.scrollbarBottomY = (int)((float)this.gridHeight * f2) + this.getHeaderHeight();
                     }

                     int n4 = 0;
                     int n5 = this.getHeaderHeight() + 2;
                     boolean flag5 = true;

                     for (int k = this.gridScrollRow * this.gridColumns; k < cosmeticsMenuTab.getElements().size(); k++) {
                        CosmeticsMenuElement cosmeticsMenuElement = (CosmeticsMenuElement)cosmeticsMenuTab.getElements().get(k);
                        if (k / this.gridColumns > this.gridScrollRow + this.visibleGridRows - 1) {
                           break;
                        }

                        if (flag5) {
                           n4 = this.getContentLeftX();
                           flag5 = false;
                        }

                        cosmeticsMenuElement.draw(obj, n4, n5, (byte)72, (byte)51, n, n2);
                        n4 += 75;
                        if (n4 + 32 + 40 >= this.getContentRightX()) {
                           n5 += 54;
                           flag5 = true;
                        }
                     }
                  }
               }

               n3 += b;
            }

            if (this.gridScrollable && !((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).isListLayout()) {
               this.renderer.drawGridScrollbar();
            }

            if ((this.popupOpen || ((CosmeticsMenuTab)this.tabs.get(this.selectedTabIndex)).isListLayout()) && this.mainWidgetList.isScrollable()) {
               CosmeticsWidgetList mainWidgetList2 = this.mainWidgetList;
               this.renderer.drawListScrollbar(mainWidgetList2);
            }

            if (this.popupOpen) {
               float f3 = 180.0F;
               this.renderer.setPopupOverlay(true, f3);
               this.popupWidgetList.draw(obj, n, n2, (short)150);
               float f4 = 0.0F;
               this.renderer.setPopupOverlay(false, f4);
            }
         }
      }
   }

   public int getSelectedTabIndex() {
      return this.selectedTabIndex;
   }

   public int getHeight() {
      return this.height;
   }



}
