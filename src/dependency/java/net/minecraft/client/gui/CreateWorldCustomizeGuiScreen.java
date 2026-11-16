package net.minecraft.client.gui;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.EnhancedGuiScreenAdapter;
import client.util.AirCreateWorldExtendedMinecraftEntry;
import client.util.VillageManager;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class CreateWorldCustomizeGuiScreen extends GuiScreen {
   public final EnhancedGuiScreenAdapter enhancedGuiScreenAdapter;
   public VillageManager villageManager = VillageManager.getVillageManager();
   public String labelText;
   public String labelText2;
   public String labelText3;
   public AirCreateWorldExtendedMinecraftEntry airCreateWorldExtendedMinecraftEntry;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      int count = this.villageManager.getList().size() - this.airCreateWorldExtendedMinecraftEntry.count15 - 1;
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         EnhancedGuiScreenAdapter enhancedGuiScreenAdapter2 = this.enhancedGuiScreenAdapter;
         this.mc.displayGuiScreen(enhancedGuiScreenAdapter2);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         this.enhancedGuiScreenAdapter.labelText = this.getProperty();
         EnhancedGuiScreenAdapter enhancedGuiScreenAdapter3 = this.enhancedGuiScreenAdapter;
         this.mc.displayGuiScreen(enhancedGuiScreenAdapter3);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 5) {
         Minecraft mc = this.mc;
         ClassicFlatGuiScreen classicFlatGuiScreen = new ClassicFlatGuiScreen(this);
         mc.displayGuiScreen(classicFlatGuiScreen);
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4 && this.isActive4()) {
         this.villageManager.getList().remove(count);
         this.airCreateWorldExtendedMinecraftEntry.count15 = Math.min(
            this.airCreateWorldExtendedMinecraftEntry.count15, this.villageManager.getList().size() - 1
         );
      }

      this.villageManager.updateState();
      this.renderElement();
   }

   private boolean isActive4() {
      return this.airCreateWorldExtendedMinecraftEntry.count15 > -1 && this.airCreateWorldExtendedMinecraftEntry.count15 < this.villageManager.getList().size();
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.labelText = StatCollector.getText("createWorld.customize.flat.title", new Object[0]);
      this.labelText2 = StatCollector.getText("createWorld.customize.flat.tile", new Object[0]);
      this.labelText3 = StatCollector.getText("createWorld.customize.flat.height", new Object[0]);
      this.airCreateWorldExtendedMinecraftEntry = new AirCreateWorldExtendedMinecraftEntry(this);
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 - 154, this.height - 52, 100, 20, StatCollector.getText("createWorld.customize.flat.addLayer", new Object[0]) + " (NYI)"
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               3, this.width / 2 - 50, this.height - 52, 100, 20, StatCollector.getText("createWorld.customize.flat.editLayer", new Object[0]) + " (NYI)"
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               4, this.width / 2 - 155, this.height - 52, 150, 20, StatCollector.getText("createWorld.customize.flat.removeLayer", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, this.width / 2 - 155, this.height - 28, 150, 20, StatCollector.getText("gui.done", new Object[0]))
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               5, this.width / 2 + 5, this.height - 52, 150, 20, StatCollector.getText("createWorld.customize.presets", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 + 5, this.height - 28, 150, 20, StatCollector.getText("gui.cancel", new Object[0]))
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled2 = this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled2 = false;
      this.villageManager.updateState();
      this.renderElement();
   }

   public static VillageManager resolveVillageManager(CreateWorldCustomizeGuiScreen createWorldCustomizeGuiScreen) {
      return createWorldCustomizeGuiScreen.villageManager;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.airCreateWorldExtendedMinecraftEntry.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      int count3 = this.width / 2 - 92 - 16;
      this.applyFontRenderer(this.fontRenderer, this.labelText2, count3, 32, 16777215);
      this.applyFontRenderer(this.fontRenderer, this.labelText3, count3 + 2 + 213 - this.fontRenderer.getIndex(this.labelText3), 32, 16777215);
      super.applyIndex(count, count2, factor);
   }

   public String getProperty() {
      return this.villageManager.toString();
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.airCreateWorldExtendedMinecraftEntry.updateState();
   }

   public void renderElement() {
      boolean active = this.isActive4();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = active;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = active;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
   }

   public CreateWorldCustomizeGuiScreen(EnhancedGuiScreenAdapter enhancedGuiScreenAdapter2, String text) {
      this.enhancedGuiScreenAdapter = enhancedGuiScreenAdapter2;
      this.setProperty(text);
   }

   public void setProperty(String property) {
      this.villageManager = VillageManager.resolveVillageManager(property);
   }
}
