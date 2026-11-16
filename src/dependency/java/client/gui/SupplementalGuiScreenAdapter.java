package client.gui;

import client.setting.ComplexOptionType;
import client.util.TertiaryPredicateAdapter;
import com.google.common.base.Predicate;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class SupplementalGuiScreenAdapter extends GuiScreen {
   public final ServerData serverData;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public Predicate predicate = new TertiaryPredicateAdapter(this);

   @Override
   public void updateState2() {
      Keyboard.enableRepeatEvents(true);
      this.list.clear();
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, this.height / 4 + 96 + 18, StatCollector.getText("addServer.add", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 100, this.height / 4 + 120 + 18, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2,
               this.width / 2 - 100,
               this.height / 4 + 72,
               StatCollector.getText("addServer.resourcePack", new Object[0]) + ": " + this.serverData.getComplexOptionType().getIChatComponent().getString2()
            )
         );
      this.internalTexturesGuiIconsPngManagerAdapter2 = new InternalTexturesGuiIconsPngManagerAdapter(0, this.fontRenderer, this.width / 2 - 100, 66, 200, 20);
      this.internalTexturesGuiIconsPngManagerAdapter2.setEnabledByFlag(true);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyText(this.serverData.labelText3);
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(1, this.fontRenderer, this.width / 2 - 100, 106, 200, 20);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex3(128);
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(this.serverData.text);
      this.internalTexturesGuiIconsPngManagerAdapter.setPredicate(this.predicate);
      ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).enabled3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().length() > 0
         && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().split(":").length > 0
         && this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText().length() > 0;
   }

   @Override
   public void enableRepeatEvents() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            this.serverData
               .setComplexOptionType(ComplexOptionType.values()[(this.serverData.getComplexOptionType().ordinal() + 1) % ComplexOptionType.values().length]);
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = StatCollector.getText("addServer.resourcePack", new Object[0])
               + ": "
               + this.serverData.getComplexOptionType().getIChatComponent().getString2();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.guiScreen.applyFlag(false, 0);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            this.serverData.labelText3 = this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText();
            this.serverData.text = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
            this.guiScreen.applyFlag(true, 0);
         }
      }
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter2.renderElement();
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      this.internalTexturesGuiIconsPngManagerAdapter2.checkChar(character, count);
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
      if (count == 15) {
         this.internalTexturesGuiIconsPngManagerAdapter2.setEnabledByFlag(!this.internalTexturesGuiIconsPngManagerAdapter2.isEnabled2());
         this.internalTexturesGuiIconsPngManagerAdapter.setEnabledByFlag(!this.internalTexturesGuiIconsPngManagerAdapter.isEnabled2());
      }

      if (count == 28 || count == 156) {
         this.applyAuxiliaryTexturesGuiIconsP((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0));
      }

      ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).enabled3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().length() > 0
         && this.internalTexturesGuiIconsPngManagerAdapter.getLabelText().split(":").length > 0
         && this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText().length() > 0;
      super.applyChar(character, count);
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyIndex(count, count2, count3);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("addServer.title", new Object[0]), this.width / 2, 17, 16777215);
      this.applyFontRenderer(this.fontRenderer, StatCollector.getText("addServer.enterName", new Object[0]), this.width / 2 - 100, 53, 10526880);
      this.applyFontRenderer(this.fontRenderer, StatCollector.getText("addServer.enterIp", new Object[0]), this.width / 2 - 100, 94, 10526880);
      this.internalTexturesGuiIconsPngManagerAdapter2.updateState();
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
   }

   public SupplementalGuiScreenAdapter(GuiScreen screen, ServerData serverData2) {
      super(screen);
      this.serverData = serverData2;
   }
}
