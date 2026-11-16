package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class UserEntityGuiScreen extends GuiScreen {
   public MinecraftSplineCalculator minecraftSplineCalculator;
   public GuiScreen guiScreen2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public String labelText;

   public UserEntityGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (!this.minecraftSplineCalculator.checkIndexByIndex(count, count2, count3)) {
         super.applyIndex(count, count2, count3);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
         this.updateState();
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 101 && auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         Minecraft mc = this.mc;
         WdlGuiGuiScreen wdlGuiGuiScreen = new WdlGuiGuiScreen(this);
         mc.displayGuiScreen(wdlGuiGuiScreen);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }
   }

   private boolean isActive4() {
      return this.labelText.equals("user");
   }

   public static FontRenderer resolveFontRenderer(UserEntityGuiScreen userEntityGuiScreen) {
      return userEntityGuiScreen.fontRenderer;
   }

   private String getTextAsText() {
      String property = Pokechu22WorldDownloaderManager.properties2.getProperty("Entity.TrackDistanceMode");
      return StatCollector.getText("wdl.gui.entities.trackDistanceMode." + property, new Object[0]);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.minecraftSplineCalculator.updateState();
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.minecraftSplineCalculator.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.entities.title", new Object[0]), this.width / 2, 8, 16777215);
      super.applyIndex(count, count2, factor);
   }

   public static String resolveText(UserEntityGuiScreen userEntityGuiScreen) {
      return userEntityGuiScreen.labelText;
   }

   @Override
   public void updateState2() {
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(200, this.width / 2 - 100, this.height - 29, "OK"));
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         100, this.width / 2 - 155, 18, 150, 20, this.getTextAsText()
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         101, this.width / 2 + 5, 18, 150, 20, StatCollector.getText("wdl.gui.entities.rangePresets", new Object[0])
      );
      this.labelText = Pokechu22WorldDownloaderManager.properties2.getProperty("Entity.TrackDistanceMode");
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = this.isActive4();
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      this.minecraftSplineCalculator = new MinecraftSplineCalculator(this);
   }

   public static Minecraft resolveMinecraft(UserEntityGuiScreen userEntityGuiScreen) {
      return userEntityGuiScreen.mc;
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      if (!this.minecraftSplineCalculator.checkIndex(count, count2, count3)) {
         super.processAction(count, count2, count3);
      }
   }

   @Override
   public void enableRepeatEvents() {
      Pokechu22WorldDownloaderManager.updateState2();
   }

   private void updateState() {
      if (this.labelText.equals("default")) {
         if (DownloadInGeneralManager.isActive8()) {
            this.labelText = "server";
         } else {
            this.labelText = "user";
         }
      } else if (this.labelText.equals("server")) {
         this.labelText = "user";
      } else {
         this.labelText = "default";
      }

      Pokechu22WorldDownloaderManager.properties2.setProperty("Entity.TrackDistanceMode", this.labelText);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.getTextAsText();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = this.isActive4();
   }
}
