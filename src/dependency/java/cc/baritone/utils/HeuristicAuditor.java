package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class HeuristicAuditor extends GuiScreen {
   public final ExternalListener externalListener;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public boolean enabled6 = false;
   public int count2;
   public int count3;
   public int count4;
   public int count5;
   public List list3;

   @Override
   public void updateState4() {
      super.updateState4();
   }

   @Override
   public void processAction(int count, int count6, int count7) throws IOException {
      super.processAction(count, count6, count7);
   }

   private void updateState() {
      if (this.enabled6) {
         this.enabled6 = false;
      } else {
         this.enabled6 = true;
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.getTextAsText();
   }

   private String getTextAsText() {
      return StatCollector.getText("wdl.gui.multiworld." + this.enabled6, new Object[0]);
   }

   public HeuristicAuditor(ExternalListener externalListener2) {
      this.externalListener = externalListener2;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         this.updateState();
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
         this.externalListener.updateState();
      } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 101) {
         this.externalListener.setEnabled(this.enabled6);
      }
   }

   @Override
   public void applyIndex(int count, int count6, float factor) {
      this.renderElement3();
      CouldnOpenHelper.applyIndexByIndex(32, 32, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.multiworld.title", new Object[0]), this.width / 2, 8, 16777215);
      processAction(this.count4, this.count5, this.count4 + this.count2, this.count5 + this.count3, -1342177280);
      int count7 = this.count4 + 10;
      int count8 = this.count5 + 10;

      for (String text : (Iterable<String>)(Iterable)this.list3) {
         this.applyFontRenderer(this.fontRenderer, text, count7, count8, 16777215);
         count8 += this.fontRenderer.indexOffset;
      }

      processAction(
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count - 2,
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 - 2,
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count + this.auxiliaryTexturesGuiIconsPngManagerAdapter2.getCount4() + 2,
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 + 20 + 2,
         -65536
      );
      super.applyIndex(count, count6, factor);
   }

   @Override
   public void updateState2() {
      this.list.clear();
      String text = StatCollector.getText("wdl.gui.multiworld.descirption.requiredWhen", new Object[0])
         + "\n\n"
         + StatCollector.getText("wdl.gui.multiworld.descirption.whatIs", new Object[0]);
      this.count2 = 320;
      this.list3 = CouldnOpenHelper.resolveList(text, this.count2 - 20);
      this.count3 = this.fontRenderer.indexOffset * (this.list3.size() + 1) + 40;
      this.count4 = this.width / 2 - this.count2 / 2;
      this.count5 = this.height / 2 - this.count3 / 2;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         1, this.width / 2 - 100, this.count5 + this.count3 - 30, this.getTextAsText()
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               100, this.width / 2 - 155, this.height - 29, 150, 20, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(101, this.width / 2 + 5, this.height - 29, 150, 20, StatCollector.getText("gui.done", new Object[0]))
         );
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
   }
}
