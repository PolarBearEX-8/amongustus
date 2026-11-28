package wdl.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class GuiEntityList extends GuiListExtended {
   private int largestWidth;
   private int totalWidth;
   private List entries;
   public final GuiWDLEntities parentGui;

   @Override
   public int getScrollBarX() {
      return this.parentGui.width / 2 + this.totalWidth / 2 + 10;
   }

   public static Minecraft getMinecraftForCategoryButtonDraw(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForSliderPress(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForCategoryDraw(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   @Override
   public GuiListExtended.IGuiListEntry getEntry(int n) {
      return (GuiListExtended.IGuiListEntry)this.entries.get(n);
   }

   public static Minecraft getMinecraftForEntityButtonSound(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForCategoryButtonPress(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public GuiEntityList(GuiWDLEntities guiWDLEntities) {
       super(GuiWDLEntities.getMinecraft(guiWDLEntities), guiWDLEntities.width, guiWDLEntities.height, 39, guiWDLEntities.height - 32, 20);
       this.parentGui = guiWDLEntities;
      this.entries = new GuiWDLEntitiesListEntries(this);
   }

   public static Minecraft getMinecraftForEntityButtonPress(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static int setTotalWidth(GuiEntityList guiEntityList, int n) {
      return guiEntityList.totalWidth = n;
   }

   public static int setLargestWidth(GuiEntityList guiEntityList, int n) {
      return guiEntityList.largestWidth = n;
   }

   @Override
   public int getSize() {
      return this.entries.size();
   }

   public static Minecraft getMinecraftForEntityDraw(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForCategoryButtonSound(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static int getLargestWidth(GuiEntityList guiEntityList) {
      return guiEntityList.largestWidth;
   }

   public static Minecraft getMinecraftForEntityButtonDraw(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static int getTotalWidth(GuiEntityList guiEntityList) {
      return guiEntityList.totalWidth;
   }

   public static Minecraft getMinecraftForEntityFontHeight(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForCategoryFontHeight(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForSliderDraw(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }

   public static Minecraft getMinecraftForCategoryWidth(GuiEntityList guiEntityList) {
      return guiEntityList.mc;
   }
}
