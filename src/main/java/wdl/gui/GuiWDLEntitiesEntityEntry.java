package wdl.gui;

import net.minecraft.client.gui.GuiListExtended;
import wdl.EntityUtils;
import wdl.WDL;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiWDLEntitiesEntityEntry implements GuiListExtended.IGuiListEntry {
   private final GuiWDLEntitiesCategoryEntry category;
   private final String entity;
   private final GuiButton onOffButton;
   private final GuiSlider rangeSlider;
   private boolean entityEnabled;
   private int range;
   private String cachedMode;
   public final GuiEntityList parentList;

   private String getButtonText() {
      return this.category.isGroupEnabled() && this.entityEnabled
         ? I18n.format("wdl.gui.entities.entity.included", new Object[0])
         : I18n.format("wdl.gui.entities.entity.ignored", new Object[0]);
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (this.onOffButton.mousePressed(GuiEntityList.getMinecraftForEntityButtonPress(this.parentList), n2, n3)) {
         this.entityEnabled ^= true;
         this.onOffButton.playPressSound(GuiEntityList.getMinecraftForEntityButtonSound(this.parentList).getSoundHandler());
         this.onOffButton.displayString = this.getButtonText();
         WDL.worldProps.setProperty("Entity." + this.entity + ".Enabled", Boolean.toString(this.entityEnabled));
         return true;
      } else if (this.rangeSlider.mousePressed(GuiEntityList.getMinecraftForSliderPress(this.parentList), n2, n3)) {
         this.range = this.rangeSlider.getValue();
         WDL.worldProps.setProperty("Entity." + this.entity + ".TrackDistance", Integer.toString(this.range));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      int n8 = this.parentList.parentGui.width / 2 - GuiEntityList.getTotalWidth(this.parentList) / 2 + GuiEntityList.getLargestWidth(this.parentList) + 10;
      GuiEntityList.getMinecraftForEntityDraw(this.parentList)
         .fontRendererObj
         .drawString(this.entity, n8 - GuiEntityList.getLargestWidth(this.parentList) - 10, n3 + n5 / 2 - GuiEntityList.getMinecraftForEntityFontHeight(this.parentList).fontRendererObj.FONT_HEIGHT / 2, 16777215);
      this.onOffButton.xPosition = n8;
      this.onOffButton.yPosition = n3;
      this.onOffButton.enabled = this.category.isGroupEnabled();
      this.onOffButton.displayString = this.getButtonText();
      this.rangeSlider.xPosition = n8 + 85;
      this.rangeSlider.yPosition = n3;
      if (!this.cachedMode.equals(GuiWDLEntities.getMode(this.parentList.parentGui))) {
         this.cachedMode = GuiWDLEntities.getMode(this.parentList.parentGui);
         this.rangeSlider.enabled = this.cachedMode.equals("user");
         this.rangeSlider.setValue(EntityUtils.getEntityTrackDistance2(this.entity));
      }

      this.onOffButton.drawButton(GuiEntityList.getMinecraftForEntityButtonDraw(this.parentList), n6, n7);
      this.rangeSlider.drawButton(GuiEntityList.getMinecraftForSliderDraw(this.parentList), n6, n7);
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
      this.rangeSlider.mouseReleased(n2, n3);
      if (this.cachedMode.equals("user")) {
         this.range = this.rangeSlider.getValue();
         WDL.worldProps.setProperty("Entity." + this.entity + ".TrackDistance", Integer.toString(this.range));
      }
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   public GuiWDLEntitiesEntityEntry(GuiEntityList guiEntityList, GuiWDLEntitiesCategoryEntry entry, String str) {
       super();
       this.parentList = guiEntityList;
      this.category = entry;
      this.entity = str;
      this.entityEnabled = WDL.worldProps.getProperty("Entity." + str + ".Enabled", "true").equals("true");
      this.range = EntityUtils.getEntityTrackDistance2(str);
      this.onOffButton = new GuiButton(0, 0, 0, 75, 18, this.getButtonText());
      this.onOffButton.enabled = entry.isGroupEnabled();
      this.rangeSlider = new GuiSlider(1, 0, 0, 150, 18, "wdl.gui.entities.trackDistance", this.range, 256);
      this.cachedMode = GuiWDLEntities.getMode(guiEntityList.parentGui);
      this.rangeSlider.enabled = this.cachedMode.equals("user");
   }
}
