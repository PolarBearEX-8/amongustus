package wdl.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class ExtensionModList extends GuiListExtended {
   private List entries;
   public final GuiWDLExtensions parentGui;

   public static Minecraft getMinecraftForSettingsButtonPress(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   @Override
   public int getScrollBarX() {
      return this.parentGui.width - 10;
   }

   @Override
   public boolean isSelected(int n) {
      return n == GuiWDLExtensions.getSelectedModIndex(this.parentGui);
   }

   @Override
   public int getListWidth() {
      return this.parentGui.width - 20;
   }

   public static Minecraft getMinecraftForDisableButtonPress(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   public static Minecraft getMinecraftForSelectSound(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   @Override
   public void drawScreen(int mouseX2, int mouseY2, float partialTicks) {
      this.height = this.bottom = GuiWDLExtensions.getBottomLocation(this.parentGui);
      super.drawScreen(mouseX2, mouseY2, partialTicks);
   }

   public static Minecraft getMinecraftForSettingsButtonSound(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   public static Minecraft getMinecraftForSettingsButtonDraw(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   @Override
   public void handleMouseInput() {
      if (this.mouseY < GuiWDLExtensions.getBottomLocation(this.parentGui)) {
         super.handleMouseInput();
      }
   }

   public static Minecraft getMinecraftForDisableButtonDraw(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   public static Minecraft getMinecraftForDisableButtonSound(ExtensionModList extensionModList) {
      return extensionModList.mc;
   }

   @Override
   public int getSize() {
      return this.entries.size();
   }

   public ExtensionModList(GuiWDLExtensions guiWDLExtensions) {
       super(GuiWDLExtensions.getMinecraftForModList(guiWDLExtensions), guiWDLExtensions.width, GuiWDLExtensions.getBottomLocation(guiWDLExtensions), 23, GuiWDLExtensions.getBottomLocation(guiWDLExtensions), 22);
       this.parentGui = guiWDLExtensions;
      this.entries = new ExtensionModEntryList(this);
      this.showSelectionBox = true;
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return (GuiListExtended.IGuiListEntry)this.entries.get(n);
   }
}
