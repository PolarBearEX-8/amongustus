package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import wdl.MessageTypeCategory;
import wdl.WDLMessages;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiWDLMessagesCategoryEntry implements GuiListExtended.IGuiListEntry {
   private final GuiButton button;
   private final MessageTypeCategory category;
   public final GuiMessageTypeList parentList;

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      Gui.drawCenteredString(
         GuiWDLMessages.getFontRenderer(this.parentList.parentGui),
         this.category.getDisplayName(),
         this.parentList.parentGui.width / 2 - 40,
         n3 + n5 - GuiMessageTypeList.getMinecraftForCategoryFontHeight(this.parentList).fontRendererObj.FONT_HEIGHT - 1,
         16777215
      );
      this.button.xPosition = this.parentList.parentGui.width / 2 + 20;
      this.button.yPosition = n3;
      this.button.displayString = I18n.format("wdl.gui.messages.group." + WDLMessages.isGroupEnabled(this.category), new Object[0]);
      this.button.enabled = WDLMessages.enableAllMessages;
      this.button.drawButton(GuiMessageTypeList.getMinecraftForCategoryButtonDraw(this.parentList), n6, n7);
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.button.mousePressed(GuiMessageTypeList.getMinecraftForCategoryButtonPress(this.parentList), n2, n3)) {
         WDLMessages.toggleGroupEnabled(this.category);
         this.button.playPressSound(GuiMessageTypeList.getMinecraftForCategoryButtonSound(this.parentList).getSoundHandler());
         return true;
      } else {
         return false;
      }
   }

   public GuiWDLMessagesCategoryEntry(GuiMessageTypeList guiMessageTypeList, MessageTypeCategory messageTypeCategory) {
      this.parentList = guiMessageTypeList;
      this.category = messageTypeCategory;
      this.button = new GuiButton(0, 0, 0, 80, 20, "");
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }
}
