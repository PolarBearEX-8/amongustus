package client.util;

import client.event.PreMotionObserver;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.DefaultGuiScreenAdapter;
import java.io.IOException;
import net.minecraft.util.StatCollector;

public class SplinePredictor extends DefaultGuiScreenAdapter {
   public final String labelText7;
   public final String labelText8;
   public final String labelText9;
   public boolean enabled6 = true;

   public void updateState5() {
      this.enabled6 = false;
   }

   public SplinePredictor(PreMotionObserver preMotionObserver, String text, int count, boolean flag) {
      super(preMotionObserver, StatCollector.getText(flag ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), text, count);
      this.labelText3 = StatCollector.getText(flag ? "chat.link.open" : "gui.yes", new Object[0]);
      this.labelText4 = StatCollector.getText(flag ? "gui.cancel" : "gui.no", new Object[0]);
      this.labelText8 = StatCollector.getText("chat.copy", new Object[0]);
      this.labelText7 = StatCollector.getText("chat.link.warning", new Object[0]);
      this.labelText9 = text;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
         this.updateState();
      }

      this.preMotionObserver.applyFlag(auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0, this.count2);
   }

   public void updateState() {
      applyText4(this.labelText9);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      super.applyIndex(count, count2, factor);
      if (this.enabled6) {
         applyFontRenderer4(this.fontRenderer, this.labelText7, this.width / 2, 110, 16764108);
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.list.clear();
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(0, this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, this.labelText3));
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, this.width / 2 - 50, this.height / 6 + 96, 100, 20, this.labelText8));
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, this.labelText4));
   }
}
