package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.DefaultGuiScreenAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class MessagesEnableAllGuiScreen extends GuiScreen {
   public String labelText = null;
   public GuiScreen guiScreen2;
   public MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            LoggerEntry.enabled ^= true;
            Pokechu22WorldDownloaderManager.properties4.setProperty("Messages.enableAll", Boolean.toString(LoggerEntry.enabled));
            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.getTextAsText();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 101) {
            Minecraft mc = this.mc;
            DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(
               this,
               StatCollector.getText("wdl.gui.messages.reset.confirm.title", new Object[0]),
               StatCollector.getText("wdl.gui.messages.reset.confirm.subtitle", new Object[0]),
               101
            );
            mc.displayGuiScreen(defaultGuiScreenAdapter);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 102) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   @Override
   public void enableRepeatEvents() {
      Pokechu22WorldDownloaderManager.updateState2();
   }

   public MessagesEnableAllGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   public static Minecraft resolveMinecraft(MessagesEnableAllGuiScreen messagesEnableAllGuiScreen) {
      return messagesEnableAllGuiScreen.mc;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (!this.minecraftSplineCalculatorImpl.checkIndexByIndex(count, count2, count3)) {
         super.applyIndex(count, count2, count3);
      }
   }

   public static FontRenderer resolveFontRenderer(MessagesEnableAllGuiScreen messagesEnableAllGuiScreen) {
      return messagesEnableAllGuiScreen.fontRenderer;
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.minecraftSplineCalculatorImpl.updateState();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      if (!this.minecraftSplineCalculatorImpl.checkIndex(count, count2, count3)) {
         super.processAction(count, count2, count3);
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.labelText = null;
      this.renderElement3();
      this.minecraftSplineCalculatorImpl.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.messages.message.title", new Object[0]), this.width / 2, 8, 16777215);
      super.applyIndex(count, count2, factor);
      if (this.labelText != null) {
         CouldnOpenHelper.applyText(this.labelText, this.width, this.height, 48);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2.isEnabled()) {
         CouldnOpenHelper.applyText(StatCollector.getText("wdl.gui.messages.all.description", new Object[0]), this.width, this.height, 48);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter3.isEnabled()) {
         CouldnOpenHelper.applyText(StatCollector.getText("wdl.gui.messages.reset.description", new Object[0]), this.width, this.height, 48);
      }
   }

   public static String resolveText(MessagesEnableAllGuiScreen messagesEnableAllGuiScreen, String text) {
      return messagesEnableAllGuiScreen.labelText = text;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag && count == 101) {
         LoggerEntry.applyByte((byte)0, 5368675, 13949340);
      }

      this.mc.displayGuiScreen(this);
   }

   private String getTextAsText() {
      return StatCollector.getText("wdl.gui.messages.all." + LoggerEntry.enabled, new Object[0]);
   }

   @Override
   public void updateState2() {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         100, this.width / 2 - 155, 18, 150, 20, this.getTextAsText()
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         101, this.width / 2 + 5, 18, 150, 20, StatCollector.getText("wdl.gui.messages.reset", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      this.minecraftSplineCalculatorImpl = new MinecraftSplineCalculatorImpl(this);
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(102, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
   }
}
