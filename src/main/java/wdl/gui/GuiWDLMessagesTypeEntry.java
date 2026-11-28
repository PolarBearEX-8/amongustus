package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import wdl.MessageTypeCategory;
import wdl.WDLMessages;
import wdl.api.IWDLMessageType;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiWDLMessagesTypeEntry implements GuiListExtended.IGuiListEntry {
   private final GuiButton button;
   private final IWDLMessageType type;
   private final MessageTypeCategory category;
   public final GuiMessageTypeList parentList;

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      this.button.xPosition = this.parentList.parentGui.width / 2 - 100;
      this.button.yPosition = n3;
      this.button.displayString = I18n.format("wdl.gui.messages.message." + WDLMessages.isEnabled(this.type), new Object[]{this.type.getDisplayName()});
      this.button.enabled = WDLMessages.enableAllMessages && WDLMessages.isGroupEnabled(this.category);
      this.button.drawButton(GuiMessageTypeList.getMinecraftForTypeButtonDraw(this.parentList), n6, n7);
      if (this.button.isMouseOver()) {
         GuiWDLMessages.setHoveredButtonDescription(this.parentList.parentGui, this.type.getDescription());
      }
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   public GuiWDLMessagesTypeEntry(GuiMessageTypeList guiMessageTypeList, IWDLMessageType wdlMessageType, MessageTypeCategory messageTypeCategory) {
      this.parentList = guiMessageTypeList;
      this.type = wdlMessageType;
      this.button = new GuiButton(0, 0, 0, wdlMessageType.toString());
      this.category = messageTypeCategory;
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.button.mousePressed(GuiMessageTypeList.getMinecraftForTypeButtonPress(this.parentList), n2, n3)) {
         WDLMessages.toggleEnabled(this.type);
         this.button.playPressSound(GuiMessageTypeList.getMinecraftForTypeButtonSound(this.parentList).getSoundHandler());
         return true;
      } else {
         return false;
      }
   }
}
