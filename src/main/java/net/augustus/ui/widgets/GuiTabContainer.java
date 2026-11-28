package net.augustus.ui.widgets;

import net.augustus.render.RenderUtil;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiTabContainer {
   private int widthRemainder;
   private int x;
   private int selectedTabIndex;
   private final boolean drawBackground;
   private final int y;
   private final List tabButtons;
   private final int width;
   private final List tabPages;
   private final TabLayoutMode layoutMode;

   public GuiTabContainer(int x, int y, int width, int buttonHeight, String[] tabLabels) {
      this(x, y, width, buttonHeight, TabLayoutMode.STATIC, false, createStaticTabPages(tabLabels));
   }

   public int getSelectedTabIndex() {
      return this.selectedTabIndex;
   }

   public void setSelectedTabIndex(int selectedTabIndex) {
      this.selectedTabIndex = selectedTabIndex;
   }

   private static GuiTabPage[] createStaticTabPages(String[] tabLabels) {
      GuiTabPage[] pages = new GuiTabPage[tabLabels.length];
      for (int i = 0; i < tabLabels.length; i++) {
         pages[i] = new StaticLabelTabItem(tabLabels[i]);
      }
      return pages;
   }

   public void selectTabButton(GuiButton button) {
      this.selectedTabIndex = button.id;
      for (int i = 0; i < this.tabButtons.size(); i++) {
         ((GuiButton)this.tabButtons.get(i)).enabled = i != this.selectedTabIndex;
      }
   }

   public void drawTabs(int mouseX, int mouseY) {
      this.drawTabsAtX(mouseX, mouseY, 0);
   }

   public GuiTabContainer(int x, int y, int width, String[] tabLabels) {
      this(x, y, width, 20, TabLayoutMode.STATIC, false, createStaticTabPages(tabLabels));
   }

   public TabLayoutMode getLayoutMode() {
      return this.layoutMode;
   }

   public void drawTabsAtX(int mouseX, int mouseY, int overrideX) {
      if (overrideX != 0) {
         this.x = overrideX;
      }

      GuiTabPage activePage = (GuiTabPage)this.tabPages.get(this.selectedTabIndex);
      int index = 0;
      for (Object obj : this.tabButtons) {
         GuiButton button = (GuiButton)obj;
         if (overrideX != 0) {
            button.xPosition = overrideX + this.width / this.tabPages.size() * index;
         }
         button.yPosition = this.layoutMode.equals(TabLayoutMode.STATIC)
            ? this.y
            : this.y - activePage.getContentHeight();
         button.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
         index++;
      }

      int contentY = this.layoutMode.equals(TabLayoutMode.STATIC)
         ? this.y + 22
         : this.y + 22 - activePage.getContentHeight();

      if (this.drawBackground) {
         RenderUtil.drawRect(this.x, contentY - 2, this.x + this.width - this.widthRemainder, contentY + activePage.getContentHeight(), Integer.MIN_VALUE);
      }

      activePage.drawContent(this.x, contentY, mouseX, mouseY);
   }

   public void handleMouseClick(int mouseX, int mouseY, int mouseButton) {
      if (mouseButton != 0 && mouseButton != 1) {
         return;
      }

      for (Object obj : this.tabButtons) {
         GuiButton button = (GuiButton)obj;
         if (button.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
            return;
         }
      }
   }

   public GuiTabContainer(int n, int n2, int n3, int n4, TabLayoutMode tabLayoutMode, boolean flag, GuiTabPage[] guiTabPages) {
      this.widthRemainder = 0;
      this.selectedTabIndex = 0;
      this.x = n;
      this.y = n2;
      this.layoutMode = tabLayoutMode;
      this.width = n3;
      this.drawBackground = flag;
      this.tabPages = Lists.newArrayList();
      this.tabButtons = Lists.newArrayList();
      int n5 = this.width / guiTabPages.length;
      this.widthRemainder = this.width - n5 * guiTabPages.length;

      for (int i = 0; i < guiTabPages.length; i++) {
         GuiTabPage guiTabPage = guiTabPages[i];
         this.tabPages.add(guiTabPage);
         GuiButton button;
         this.tabButtons.add(button = new GuiButton(i, n + n5 * i, n2, n5, n4, guiTabPage.getTitle()));
         button.enabled = i != this.selectedTabIndex;
      }
   }

   public boolean isDrawBackground() {
      return this.drawBackground;
   }

   public int getX() {
      return this.x;
   }

   public int getWidth() {
      return this.width;
   }

   public int getY() {
      return this.y;
   }

}
