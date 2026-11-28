package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import wdl.WDL;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiWDLEntitiesCategoryEntry implements GuiListExtended.IGuiListEntry {
   private final String group;
   private final int labelWidth;
   private final GuiButton enableGroupButton;
   private boolean groupEnabled;
   public final GuiEntityList parentList;

   public GuiWDLEntitiesCategoryEntry(GuiEntityList guiEntityList, String str) {
       super();
       this.parentList = guiEntityList;
      this.group = str;
      this.labelWidth = GuiEntityList.getMinecraftForCategoryWidth(guiEntityList).fontRendererObj.getStringWidth(str);
      this.groupEnabled = WDL.worldProps.getProperty("EntityGroup." + str + ".Enabled", "true").equals("true");
      this.enableGroupButton = new GuiButton(0, 0, 0, 90, 18, this.getButtonText());
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      GuiEntityList.getMinecraftForCategoryDraw(this.parentList)
         .fontRendererObj
         .drawString(this.group, n2 + 55 - this.labelWidth / 2, n3 + n5 - GuiEntityList.getMinecraftForCategoryFontHeight(this.parentList).fontRendererObj.FONT_HEIGHT - 1, 16777215);
      this.enableGroupButton.xPosition = n2 + 110;
      this.enableGroupButton.yPosition = n3;
      this.enableGroupButton.displayString = this.getButtonText();
      this.enableGroupButton.drawButton(GuiEntityList.getMinecraftForCategoryButtonDraw(this.parentList), n6, n7);
   }

   public boolean isGroupEnabled() {
      return this.groupEnabled;
   }

   private String getButtonText() {
      return this.groupEnabled ? I18n.format("wdl.gui.entities.group.enabled", new Object[0]) : I18n.format("wdl.gui.entities.group.disabled", new Object[0]);
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.enableGroupButton.mousePressed(GuiEntityList.getMinecraftForCategoryButtonPress(this.parentList), n2, n3)) {
         this.groupEnabled ^= true;
         this.enableGroupButton.playPressSound(GuiEntityList.getMinecraftForCategoryButtonSound(this.parentList).getSoundHandler());
         this.enableGroupButton.displayString = this.getButtonText();
         WDL.worldProps.setProperty("EntityGroup." + this.group + ".Enabled", Boolean.toString(this.groupEnabled));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }
}
