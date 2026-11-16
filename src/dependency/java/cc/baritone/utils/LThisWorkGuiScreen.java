package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class LThisWorkGuiScreen extends GuiScreen {
   public static final int count2 = 61;
   public static final int count3 = 32;
   public PreUpdateDispatcherSplineCalculator preUpdateDispatcherSplineCalculator;
   public final GuiScreen guiScreen2;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;

   public LThisWorkGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void updateState2() {
      this.preUpdateDispatcherSplineCalculator = new PreUpdateDispatcherSplineCalculator(this.mc, this.width, this.height, 61, 32);
      this.preUpdateDispatcherSplineCalculator.applyText("§c§lThis is a work in progress.");
      this.preUpdateDispatcherSplineCalculator
         .applyText("You can request permissions in this GUI, although it currently requires manually specifying the names.");
      this.preUpdateDispatcherSplineCalculator.add();
      this.preUpdateDispatcherSplineCalculator.applyText("Boolean fields: " + DownloadInGeneralManager.list3);
      this.preUpdateDispatcherSplineCalculator.applyText("Integer fields: " + DownloadInGeneralManager.list);
      this.preUpdateDispatcherSplineCalculator.add();

      for (Entry entry : (Iterable<Entry>) (DownloadInGeneralManager.copyOfAsMap().entrySet())) {
         this.preUpdateDispatcherSplineCalculator.applyText("Requesting '" + (String)entry.getKey() + "' to be '" + (String)entry.getValue() + "'.");
      }

      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(0, this.fontRenderer, this.width / 2 - 155, 18, 150, 20);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 + 5, 18, 150, 20, "Submit request");
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = !DownloadInGeneralManager.copyOfAsMap().isEmpty();
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 155, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.current", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               201, this.width / 2 - 50, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.request", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               202, this.width / 2 + 55, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.overrides", new Object[0])
            )
         );
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      super.updateState4();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
      if (this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2()) {
         String labelText = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
         boolean flag = false;
         if (labelText.contains("=")) {
            String[] textArray = labelText.split("=", 2);
            if (textArray.length == 2) {
               String text = textArray[0];
               String text2 = textArray[1];
               flag = DownloadInGeneralManager.matchesText(text, text2);
               if (flag && count == 28) {
                  this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
                  flag = false;
                  DownloadInGeneralManager.applyText(text, text2);
                  this.preUpdateDispatcherSplineCalculator.applyText("Requesting '" + text + "' to be '" + text2 + "'.");
                  this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = true;
               }
            }
         }

         this.internalTexturesGuiIconsPngManagerAdapter.setCount8(flag ? 4251712 : 14696512);
      }
   }

   @Override
   public void processAction(int count, int count4, int count5) throws IOException {
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count4, count5);
      this.preUpdateDispatcherSplineCalculator.checkIndex(count, count4, count5);
      super.processAction(count, count4, count5);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         DownloadInGeneralManager.updateState2();
         auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "Submitted!";
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
         Minecraft mc = this.mc;
         HeuristicProvider heuristicProvider = new HeuristicProvider(this.guiScreen2);
         mc.displayGuiScreen(heuristicProvider);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 202) {
         Minecraft mc2 = this.mc;
         DiagonalCalculator diagonalCalculator = new DiagonalCalculator(this.guiScreen2);
         mc2.displayGuiScreen(diagonalCalculator);
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.preUpdateDispatcherSplineCalculator.updateState();
   }

   @Override
   public void applyIndex(int count, int count4, int count5) {
      if (!this.preUpdateDispatcherSplineCalculator.checkIndexByIndex(count, count4, count5)) {
         super.applyIndex(count, count4, count5);
      }
   }

   @Override
   public void applyIndex(int count, int count4, float factor) {
      if (this.preUpdateDispatcherSplineCalculator != null) {
         this.preUpdateDispatcherSplineCalculator.applyIndex(count, count4, factor);
         this.internalTexturesGuiIconsPngManagerAdapter.updateState();
         applyFontRenderer4(this.fontRenderer, "Permission request", this.width / 2, 8, 16777215);
         super.applyIndex(count, count4, factor);
      }
   }
}
