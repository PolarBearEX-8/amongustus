package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class GeometryPredictor extends GuiScreen {
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public final String labelText;

   @Override
   public void applyChar(char character, int count) throws IOException {
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
      ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).enabled3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim().length()
         > 0;
      if (count == 28 || count == 156) {
         this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0));
      }
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("selectWorld.renameTitle", new Object[0]), this.width / 2, 20, 16777215);
      this.applyFontRenderer(this.fontRenderer, StatCollector.getText("selectWorld.enterName", new Object[0]), this.width / 2 - 100, 47, 10526880);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
   }

   public GeometryPredictor(GuiScreen screen, String text) {
      super(screen);
      this.labelText = text;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            this.mc.getISaveFormat().applyText(this.labelText, this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().trim());
            GuiScreen guiScreen2 = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen2);
         }
      }
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
   }

   @Override
   public void updateState2() {
      Keyboard.enableRepeatEvents(true);
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, this.height / 4 + 96 + 12, StatCollector.getText("selectWorld.renameButton", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 100, this.height / 4 + 120 + 12, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      String labelText2 = this.mc.getISaveFormat().resolveRandomSeedManager(this.labelText).getLabelText();
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(2, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
      this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(true);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(labelText2);
   }
}
