package client.util;

import client.alt.OptionsCapeOFNativeGuiScreenAdapter;
import client.gui.AuxiliaryTexturesGuiIc;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.TabListCapeTypeAuxiliaryTexturesGuiIc;
import client.setting.TabListCapeType;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class GeometryMonitor extends GuiScreen {
   public String labelText;

   private String resolveText(TabListCapeType tabListCapeType) {
      String text;
      if (this.mc.gameSettings.processAction().contains(tabListCapeType)) {
         text = StatCollector.getText("options.on", new Object[0]);
      } else {
         text = StatCollector.getText("options.off", new Object[0]);
      }

      return tabListCapeType.getIChatComponent().getString2() + ": " + text;
   }

   public static String resolveText(GeometryMonitor geometryMonitor, TabListCapeType tabListCapeType) {
      return geometryMonitor.resolveText(tabListCapeType);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 210) {
            Minecraft mc = this.mc;
            OptionsCapeOFNativeGuiScreenAdapter optionsCapeOFNativeGuiScreenAdapter = new OptionsCapeOFNativeGuiScreenAdapter(this);
            mc.displayGuiScreen(optionsCapeOFNativeGuiScreenAdapter);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            this.mc.gameSettings.updateState();
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof TabListCapeTypeAuxiliaryTexturesGuiIc) {
            TabListCapeType tabListCapeType = TabListCapeTypeAuxiliaryTexturesGuiIc.resolveTabListCapeType((TabListCapeTypeAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter);
            this.mc.gameSettings.applyTabListCapeType(tabListCapeType);
            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.resolveText(tabListCapeType);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 20, 16777215);
      super.applyIndex(count, count2, factor);
   }

   @Override
   public void updateState2() {
      int count = 0;
      this.labelText = StatCollector.getText("options.skinCustomisation.title", new Object[0]);

      for (TabListCapeType tabListCapeType : TabListCapeType.values()) {
         this.list
            .add(
               new TabListCapeTypeAuxiliaryTexturesGuiIc(
                  this, tabListCapeType.getCount2(), this.width / 2 - 155 + count % 2 * 160, this.height / 6 + 24 * (count >> 1), 150, 20, tabListCapeType, null
               )
            );
         count++;
      }

      if (count % 2 == 1) {
         count++;
      }

      this.list
         .add(
            new AuxiliaryTexturesGuiIc(
               210, this.width / 2 - 100, this.height / 6 + 24 * (count >> 1), StatCollector.getText("of.options.skinCustomisation.ofCape", new Object[0])
            )
         );
      int count2 = count + 2;
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 100, this.height / 6 + 24 * (count2 >> 1), StatCollector.getText("gui.done", new Object[0])
            )
         );
   }

   public GeometryMonitor(GuiScreen screen) {
      super(screen);
      GuiScreen.set.add(this);
   }
}
