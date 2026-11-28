package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import wdl.WDLPluginChannels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLButtonEntry implements GuiListExtended.IGuiListEntry {
   private final GuiButton button;
   private final GuiScreen toOpen;
   private final String tooltip;
   public final GuiWDLButtonList parentList;

   public void setSelected(int n, int n2, int n3) {
   }

   public GuiWDLButtonEntry(GuiWDLButtonList guiWDLButtonList, String str, GuiScreen screen, boolean flag) {
      this.parentList = guiWDLButtonList;
      this.button = new GuiButton(0, 0, 0, I18n.format("wdl.gui.wdl." + str + ".name", new Object[0]));
      this.toOpen = screen;
      if (flag) {
         this.button.enabled = WDLPluginChannels.canDownloadAtAll();
      }

      this.tooltip = I18n.format("wdl.gui.wdl." + str + ".description", new Object[0]);
   }

   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      this.button.xPosition = this.parentList.parentGui.width / 2 - 100;
      this.button.yPosition = n3;
      this.button.drawButton(GuiWDLButtonList.getMinecraftForButtonDraw(this.parentList), n6, n7);
      if (this.button.isMouseOver()) {
         GuiWDL.setDisplayedTooltip(this.parentList.parentGui, this.tooltip);
      }
   }

   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.button.mousePressed(GuiWDLButtonList.getMinecraftForButtonPress(this.parentList), n2, n3)) {
         GuiWDLButtonList.getMinecraftForOpenScreen(this.parentList).displayGuiScreen(this.toOpen);
         this.button.playPressSound(GuiWDLButtonList.getMinecraftForButtonSound(this.parentList).getSoundHandler());
         return true;
      } else {
         return false;
      }
   }

}
