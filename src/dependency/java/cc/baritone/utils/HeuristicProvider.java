package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class HeuristicProvider extends GuiScreen {
   public static final int count2 = 61;
   public static final int count3 = 32;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public int count4 = -1;
   public final GuiScreen guiScreen2;
   public PreUpdateDispatcherSplineCalculator preUpdateDispatcherSplineCalculator;

   @Override
   public void applyIndex(int count, int count5, float factor) {
      if (this.preUpdateDispatcherSplineCalculator != null) {
         this.preUpdateDispatcherSplineCalculator.applyIndex(count, count5, factor);
         applyFontRenderer4(this.fontRenderer, "Permission info", this.width / 2, 8, 16777215);
         if (!DownloadInGeneralManager.isEmpty()) {
            applyFontRenderer4(
               this.fontRenderer,
               "No permissions received; defaulting to everything enabled.",
               this.width / 2,
               (this.height - 32 - 23) / 2 + 23 - this.fontRenderer.indexOffset / 2,
               16777215
            );
         }

         super.applyIndex(count, count5, factor);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = false;
         auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "Refershing...";
         this.count4 = 50;
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 201) {
         Minecraft mc = this.mc;
         LThisWorkGuiScreen lThisWorkGuiScreen = new LThisWorkGuiScreen(this.guiScreen2);
         mc.displayGuiScreen(lThisWorkGuiScreen);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 202) {
         Minecraft mc2 = this.mc;
         DiagonalCalculator diagonalCalculator = new DiagonalCalculator(this.guiScreen2);
         mc2.displayGuiScreen(diagonalCalculator);
      }
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 155, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.current", new Object[0])
            )
         );
      if (DownloadInGeneralManager.isActive9()) {
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

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         1, this.width / 2 + 5, 18, 150, 20, "Reload permissions"
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.preUpdateDispatcherSplineCalculator = new PreUpdateDispatcherSplineCalculator(this.mc, this.width, this.height, 61, 32);
      this.preUpdateDispatcherSplineCalculator.applyText("§c§lThis is a work in progress.");
      if (DownloadInGeneralManager.isEmpty()) {
         this.preUpdateDispatcherSplineCalculator.add();
         if (!DownloadInGeneralManager.isActive9()) {
            this.preUpdateDispatcherSplineCalculator
               .applyText(
                  "§cThe serverside permission plugin is out of date and does support permission requests.  Please go ask a server administrator to update the plugin."
               );
            this.preUpdateDispatcherSplineCalculator.add();
         }

         if (DownloadInGeneralManager.getText() != null) {
            this.preUpdateDispatcherSplineCalculator.applyText("Note from the server moderators: ");
            this.preUpdateDispatcherSplineCalculator.applyText(DownloadInGeneralManager.getText());
            this.preUpdateDispatcherSplineCalculator.add();
         }

         this.preUpdateDispatcherSplineCalculator.applyText("These are your current permissions:");
         this.preUpdateDispatcherSplineCalculator.applyText("Can download: " + DownloadInGeneralManager.isActive4());
         this.preUpdateDispatcherSplineCalculator.applyText("Can save chunks as you move: " + DownloadInGeneralManager.isEnabled2());
         if (!DownloadInGeneralManager.isEnabled2() && DownloadInGeneralManager.isActive4()) {
            this.preUpdateDispatcherSplineCalculator.applyText("Nearby chunk save radius: " + DownloadInGeneralManager.getCount());
         }

         this.preUpdateDispatcherSplineCalculator.applyText("Can save entities: " + DownloadInGeneralManager.isActive2());
         this.preUpdateDispatcherSplineCalculator.applyText("Can save tile entities: " + DownloadInGeneralManager.isActive7());
         this.preUpdateDispatcherSplineCalculator.applyText("Can save containers: " + DownloadInGeneralManager.isActive());
         this.preUpdateDispatcherSplineCalculator
            .applyText("Received entity ranges: " + DownloadInGeneralManager.isActive8() + " (" + DownloadInGeneralManager.getMap().size() + " total)");
      }
   }

   @Override
   public void processAction(int count, int count5, int count6) throws IOException {
      this.preUpdateDispatcherSplineCalculator.checkIndex(count, count5, count6);
      super.processAction(count, count5, count6);
   }

   @Override
   public void updateState4() {
      if (this.count4 > 0) {
         this.count4--;
      } else if (this.count4 == 0) {
         this.updateState2();
         this.count4 = -1;
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.preUpdateDispatcherSplineCalculator.updateState();
   }

   @Override
   public void enableRepeatEvents() {
      Pokechu22WorldDownloaderManager.updateState2();
   }

   public HeuristicProvider(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void applyIndex(int count, int count5, int count6) {
      if (!this.preUpdateDispatcherSplineCalculator.checkIndexByIndex(count, count5, count6)) {
         super.applyIndex(count, count5, count6);
      }
   }
}
