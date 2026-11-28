package wdl.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class GuiMessageTypeList extends GuiListExtended {
   private List entries;
   public final GuiWDLMessages parentGui;

   public static Minecraft getMinecraftForTypeButtonPress(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }

   public static Minecraft getMinecraftForCategoryButtonDraw(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }

   public static Minecraft getMinecraftForTypeButtonSound(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return (GuiListExtended.IGuiListEntry)this.entries.get(n);
   }

   public static Minecraft getMinecraftForCategoryFontHeight(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }

   @Override
   public int getSize() {
      return this.entries.size();
   }

   public static Minecraft getMinecraftForCategoryButtonPress(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }

   public GuiMessageTypeList(GuiWDLMessages guiWDLMessages) {
      super(GuiWDLMessages.getMinecraft(guiWDLMessages), guiWDLMessages.width, guiWDLMessages.height, 39, guiWDLMessages.height - 32, 20);
      this.parentGui = guiWDLMessages;
      this.entries = new MessageTypeEntryList(this);
   }

   public static Minecraft getMinecraftForTypeButtonDraw(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }

   public static Minecraft getMinecraftForCategoryButtonSound(GuiMessageTypeList guiMessageTypeList) {
      return guiMessageTypeList.mc;
   }
}
