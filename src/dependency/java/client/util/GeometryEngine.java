package client.util;

import client.Client;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GeometryEngine extends GuiScreen {
   public GuiScreen guiScreen2;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      super.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
         this.updateState();
         GuiScreen guiScreen = this.guiScreen2;
         super.mc.displayGuiScreen(guiScreen);
      }
   }

   @Override
   public void updateState4() {
      super.updateState4();
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
   }

   private static void updateState5() {
      Client.getClient().getClickGuiScreen().getPanelWindowAdapter().renderElement6();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
      if (count == 1) {
         GuiScreen guiScreen = this.guiScreen2;
         super.mc.displayGuiScreen(guiScreen);
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      ScaledResolution scaledResolution = new ScaledResolution(super.mc);
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
         1, super.fontRenderer, scaledResolution.getScaledWidth() / 2 - 75, scaledResolution.getScaledHeight() / 2 - 25, 150, 20
      );
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(1377);
      super.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, scaledResolution.getScaledWidth() / 2 - 50, scaledResolution.getScaledHeight() / 2 + 5, 100, 20, "Create"));
   }

   public GeometryEngine(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   private void updateState() {
      if (!this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().equals("")) {
         RayTraceUtil.applyString("Config created: §c" + this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
         BaseHashMapEntry baseHashMapEntry = Client.getClient().getAltItemEntry().baseHashMapEntry;
         String labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
         String text = "configs";
         String text2 = labelText;
         baseHashMapEntry.applyTextByText(text2, text);
         this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
      }

      CompletableFuture.runAsync(GeometryEngine::updateState5);
   }
}
