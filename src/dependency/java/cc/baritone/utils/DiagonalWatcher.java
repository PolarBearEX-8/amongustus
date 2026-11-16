package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class DiagonalWatcher extends AbstractGameSettingsFullTypeGuiScreen implements IndirectListener {
   public volatile boolean enabled9 = false;
   public volatile String labelText = "";
   public volatile int count3;
   public volatile int count4;
   public volatile String labelText2 = "";
   public int count5;
   public int count6;
   public int count7;
   public int count8;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;
   public final long timestamp2;
   public final long timestamp3;
   public String labelText3;
   public String labelText4;
   public String labelText5;
   public String labelText6;
   public String labelText7;
   public String labelText8;
   public String labelText9;

   public DiagonalWatcher(long value, long value2) {
      this.timestamp2 = value;
      this.timestamp3 = value2;
   }

   @Override
   public void applyIndex(int count) {
      this.count3 = count;
      this.count4 = 0;
   }

   @Override
   public void updateState2() {
      this.enabled9 = false;
      this.labelText3 = StatCollector.getText("wdl.gui.overwriteChanges.title", new Object[0]);
      if (this.timestamp2 != -1L) {
         this.labelText4 = StatCollector.getText("wdl.gui.overwriteChanges.footer", new Object[]{this.timestamp2, this.timestamp3});
      } else {
         this.labelText4 = StatCollector.getText("wdl.gui.overwriteChanges.footerNeverSaved", new Object[]{this.timestamp3});
      }

      this.labelText5 = StatCollector.getText("wdl.gui.overwriteChanges.captionTitle", new Object[0]);
      this.labelText6 = StatCollector.getText("wdl.gui.overwriteChanges.captionSubtitle", new Object[0]);
      this.labelText7 = StatCollector.getText("wdl.gui.overwriteChanges.overwriteWarning1", new Object[0]);
      this.labelText8 = StatCollector.getText("wdl.gui.overwriteChanges.overwriteWarning2", new Object[0]);
      this.labelText9 = StatCollector.getText("wdl.gui.overwriteChanges.backingUp.title", new Object[0]);
      this.count7 = this.fontRenderer.getIndex(this.labelText7);
      this.count8 = 132;
      if (this.count7 < 200) {
         this.count7 = 200;
      }

      this.count6 = 48;
      this.count5 = this.width / 2 - this.count7 / 2;
      int count = this.width / 2 - 100;
      int count2 = this.count6 + 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         0, count, count2, StatCollector.getText("wdl.gui.overwriteChanges.asZip.name", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      count2 += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         1, count, count2, StatCollector.getText("wdl.gui.overwriteChanges.asFolder.name", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      count2 += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         2, count, count2, StatCollector.getText("wdl.gui.overwriteChanges.startNow.name", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter4);
      count2 += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         3, count, count2, StatCollector.getText("wdl.gui.overwriteChanges.cancel.name", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter5);
      super.updateState2();
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (this.enabled9) {
         this.applyIndex2(0);
         applyFontRenderer4(this.fontRenderer, this.labelText9, this.width / 2, this.height / 4 - 40, 16777215);
         applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, this.height / 4 - 10, 16777215);
         if (this.labelText2 != null) {
            String text = StatCollector.getText("wdl.gui.overwriteChanges.backingUp.progress", new Object[]{this.count4, this.count3, this.labelText2});
            applyFontRenderer4(this.fontRenderer, text, this.width / 2, this.height / 4 + 10, 16777215);
         }
      } else {
         this.renderElement3();
         CouldnOpenHelper.applyIndexByIndex(32, 22, 0, 0, this.height, this.width);
         applyFontRenderer4(this.fontRenderer, this.labelText3, this.width / 2, 8, 16777215);
         applyFontRenderer4(this.fontRenderer, this.labelText4, this.width / 2, this.height - 8 - this.fontRenderer.indexOffset, 16777215);
         processAction(this.count5 - 5, this.count6 - 5, this.count5 + this.count7 + 5, this.count6 + this.count8 + 5, -1342177280);
         applyFontRenderer4(this.fontRenderer, this.labelText5, this.width / 2, this.count6, 16777215);
         applyFontRenderer4(this.fontRenderer, this.labelText6, this.width / 2, this.count6 + this.fontRenderer.indexOffset, 16777215);
         applyFontRenderer4(this.fontRenderer, this.labelText7, this.width / 2, this.count6 + 115, 16777215);
         applyFontRenderer4(this.fontRenderer, this.labelText8, this.width / 2, this.count6 + 115 + this.fontRenderer.indexOffset, 16777215);
         super.applyIndex(count, count2, factor);
         String text2 = null;
         if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2.isEnabled()) {
            text2 = StatCollector.getText("wdl.gui.overwriteChanges.asZip.description", new Object[0]);
         } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter3.isEnabled()) {
            text2 = StatCollector.getText("wdl.gui.overwriteChanges.asFolder.description", new Object[0]);
         } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter4.isEnabled()) {
            text2 = StatCollector.getText("wdl.gui.overwriteChanges.startNow.description", new Object[0]);
         } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter5.isEnabled()) {
            text2 = StatCollector.getText("wdl.gui.overwriteChanges.cancel.description", new Object[0]);
         }

         CouldnOpenHelper.applyText(text2, this.width, this.height, 48);
      }
   }

   public static Minecraft resolveMinecraft(DiagonalWatcher diagonalWatcher) {
      return diagonalWatcher.mc;
   }

   @Override
   public void applyText(String text) {
      this.count4++;
      this.labelText2 = text;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count != 1) {
         super.applyChar(character, count);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (!this.enabled9) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            this.enabled9 = true;
            new UserZipThread(this, true).start();
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.enabled9 = true;
            new UserZipThread(this, false).start();
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            Pokechu22WorldDownloaderManager.enabled3 = true;
            this.mc.displayGuiScreen((GuiScreen)null);
            Pokechu22WorldDownloaderManager.updateState3();
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            this.mc.displayGuiScreen((GuiScreen)null);
         }
      }
   }

   public static String resolveText(DiagonalWatcher diagonalWatcher, String text) {
      return diagonalWatcher.labelText = text;
   }

   public static boolean matchesDiagonalWatcher(DiagonalWatcher diagonalWatcher, boolean flag) {
      return diagonalWatcher.enabled9 = flag;
   }
}
