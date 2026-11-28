package wdl.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class GuiWDLButtonList extends GuiListExtended {
   private List entries;
   public final GuiWDL parentGui;

   public static Minecraft getMinecraftForButtonPress(GuiWDLButtonList guiWDLButtonList) {
      return guiWDLButtonList.mc;
   }

   public static Minecraft getMinecraftForOpenScreen(GuiWDLButtonList guiWDLButtonList) {
      return guiWDLButtonList.mc;
   }

   public static Minecraft getMinecraftForButtonDraw(GuiWDLButtonList guiWDLButtonList) {
      return guiWDLButtonList.mc;
   }

   public GuiWDLButtonList(GuiWDL guiWDL) {
       super(GuiWDL.getMinecraftForButtonList(guiWDL), guiWDL.width, guiWDL.height, 39, guiWDL.height - 32, 20);
       this.parentGui = guiWDL;
      this.entries = new GuiWDLButtonEntries(this);
   }

   @Override
   public int getSize() {
      return this.entries.size();
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return (GuiListExtended.IGuiListEntry)this.entries.get(n);
   }

   public static Minecraft getMinecraftForButtonSound(GuiWDLButtonList guiWDLButtonList) {
      return guiWDLButtonList.mc;
   }
}
