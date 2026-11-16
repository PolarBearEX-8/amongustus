package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class CurveConverter extends GuiScreen {
   public GuiScreen guiScreen2;
   public String labelText;
   public String labelText2;
   public final List list3 = Lists.newArrayList();
   public String labelText3;
   public int count2;

   @Override
   public void updateState2() {
      this.list.add(new ValueSliderControl(0, this.width / 2 - 74, this.height / 6 + 96, this.labelText3));
      this.list3.clear();
      this.list3.addAll(this.fontRenderer.getList(this.labelText2, this.width - 50));
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 70, 16777215);
      int count4 = 90;

      for (Object element : this.list3) {
         applyFontRenderer4(this.fontRenderer, (String)element, this.width / 2, count4, 16777215);
         count4 += this.fontRenderer.indexOffset;
      }

      super.applyIndex(count, count3, factor);
   }

   public void applyIndex(int count) {
      this.count2 = count;
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).enabled3 = false;
      }
   }

   public CurveConverter(GuiScreen screen, String text, String text2) {
      this.guiScreen2 = screen;
      this.labelText = text;
      this.labelText2 = text2;
      this.labelText3 = StatCollector.getText("gui.done", new Object[0]);
   }

   @Override
   public void updateState4() {
      super.updateState4();
      if (--this.count2 == 0) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).enabled3 = true;
         }
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      Minecraft mc = BuildManager.getMc();
      GuiScreen guiScreen = this.guiScreen2;
      mc.displayGuiScreen(guiScreen);
   }
}
