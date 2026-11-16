package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.StatCollector;

public class HeuristicPipeline extends AbstractGameSettingsFullTypeGuiScreen {
   public final VirtualListener virtualListener;
   public final String labelText;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public boolean enabled9;
   public List list3;
   public List list4;
   public DefinedDimensionsRecord definedDimensionsRecord;
   public int count3 = 0;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter6;
   public int count4;
   public String labelText2 = "";

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (this.count3 >= this.list3.size() - this.count4) {
         this.count3 = this.list3.size() - this.count4;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = false;
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = true;
      }

      if (this.count3 <= 0) {
         this.count3 = 0;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled3 = false;
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled3 = true;
      }

      CouldnOpenHelper.applyIndexByIndex(53, 53, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      applyFontRenderer4(this.fontRenderer, StatCollector.getText("wdl.gui.multiworldSelect.subtitle", new Object[0]), this.width / 2, 18, 16711680);
      if (this.enabled9) {
         this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      }

      this.internalTexturesGuiIconsPngManagerAdapter2.updateState();
      if (this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText().isEmpty() && !this.internalTexturesGuiIconsPngManagerAdapter2.isEnabled2()) {
         this.applyFontRenderer(
            this.fontRenderer,
            StatCollector.getText("wdl.gui.multiworldSelect.search", new Object[0]),
            this.internalTexturesGuiIconsPngManagerAdapter2.count7 + 4,
            this.internalTexturesGuiIconsPngManagerAdapter2.count10 + 6,
            9474192
         );
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = !this.enabled9;
      super.applyIndex(count, count2, factor);
      this.updateState();
   }

   private void updateState5() {
      String text = this.labelText2.toLowerCase();
      this.list4.clear();

      for (DefinedDimensionsRecord definedDimensionsRecord2 : (Iterable<DefinedDimensionsRecord>)(Iterable<?>)this.list3) {
         if (definedDimensionsRecord2.labelText2.toLowerCase().contains(text)) {
            this.list4.add(definedDimensionsRecord2);
         }
      }
   }

   @Override
   public void processAction(int count, int count2, int count5) throws IOException {
      super.processAction(count, count2, count5);
      if (this.enabled9) {
         this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count5);
      }

      this.internalTexturesGuiIconsPngManagerAdapter2.applyIndex(count, count2, count5);
   }

   public static DefinedDimensionsRecord resolveDefinedDimensionsRecord(HeuristicPipeline heuristicPipeline) {
      return heuristicPipeline.definedDimensionsRecord;
   }

   public HeuristicPipeline(String text, VirtualListener virtualListener2) {
      this.labelText = text;
      this.virtualListener = virtualListener2;
      String[] textArray = Pokechu22WorldDownloaderManager.properties4.getProperty("LinkedWorlds").split("\\|");
      this.list3 = new java.util.ArrayList();

      for (String text2 : textArray) {
         if (text2 != null && !text2.isEmpty()) {
            Properties properties = Pokechu22WorldDownloaderManager.resolveProperties(text2);
            if (properties.containsKey("WorldName")) {
               String property = properties.getProperty("WorldName", text2);
               this.list3.add(new DefinedDimensionsRecord(text2, property));
            }
         }
      }

      this.list4 = new java.util.ArrayList();
      this.list4.addAll(this.list3);
   }

   public static List resolveList(HeuristicPipeline heuristicPipeline) {
      return heuristicPipeline.list4;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter instanceof HeuristicPipelineAuxiliaryTexturesGuiIc) {
            this.definedDimensionsRecord = ((HeuristicPipelineAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter).getDefinedDimensionsRecord();
            if (this.definedDimensionsRecord != null) {
               this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = true;
            } else {
               this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -1) {
            this.virtualListener.updateState();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -2) {
            this.virtualListener.applyText(this.definedDimensionsRecord.labelText);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -3) {
            this.enabled9 = true;
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -4) {
            this.count3--;
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == -5) {
            this.count3++;
         }
      }
   }

   public static int computeCount(HeuristicPipeline heuristicPipeline) {
      return heuristicPipeline.count3;
   }

   private void updateState() {
      if (this.definedDimensionsRecord != null) {
         String text = "Info about " + this.definedDimensionsRecord.labelText2;
         List list = this.definedDimensionsRecord.getList();
         int index = this.fontRenderer.getIndex(text);

         for (String text2 : (Iterable<String>)(Iterable<?>)list) {
            int index2 = this.fontRenderer.getIndex(text2);
            if (index2 > index) {
               index = index2;
            }
         }

         processAction(2, 61, 5 + index + 3, this.height - 61, Integer.MIN_VALUE);
         this.applyFontRenderer(this.fontRenderer, text, 5, 64, 16777215);
         int count = 64 + this.fontRenderer.indexOffset;

         for (String text3 : (Iterable<String>)(Iterable<?>)list) {
            this.applyFontRenderer(this.fontRenderer, text3, 5, count, 16777215);
            count += this.fontRenderer.indexOffset;
         }
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.count4 = (this.width - 50) / 155;
      if (this.count4 < 1) {
         this.count4 = 1;
      }

      int count = (this.count4 * 155 + 45) / 2;
      int count2 = this.height - 49;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         -1, this.width / 2 - 155, this.height - 25, 150, 20, StatCollector.getText("gui.cancel", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         -2, this.width / 2 + 5, this.height - 25, 150, 20, StatCollector.getText("wdl.gui.multiworldSelect.done", new Object[0])
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = this.definedDimensionsRecord != null;
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter6 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(-4, this.width / 2 - count, count2, 20, 20, "<");
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter6);

      for (int index = 0; index < this.count4; index++) {
         this.list.add(new HeuristicPipelineAuxiliaryTexturesGuiIc(this, index, this.width / 2 - count + index * 155 + 25, count2, 150, 20));
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         -5, this.width / 2 - count + 25 + this.count4 * 155, count2, 20, 20, ">"
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter5);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         -3, this.width / 2 - 155, 29, 150, 20, StatCollector.getText("wdl.gui.multiworldSelect.newName", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter4);
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(40, this.fontRenderer, this.width / 2 - 155, 29, 150, 20);
      this.internalTexturesGuiIconsPngManagerAdapter2 = new InternalTexturesGuiIconsPngManagerAdapter(41, this.fontRenderer, this.width / 2 + 5, 29, 150, 20);
      this.internalTexturesGuiIconsPngManagerAdapter2.applyText(this.labelText2);
   }

   private void applyText(String text) {
      String text2 = text;

      for (char character : "\\/:*?\"<>|.".toCharArray()) {
         text2 = text2.replace(character, '_');
      }

      Properties properties = new Properties(Pokechu22WorldDownloaderManager.properties4);
      properties.setProperty("WorldName", text);
      String property = Pokechu22WorldDownloaderManager.properties4.getProperty("LinkedWorlds");
      String text3 = property + "|" + text2;
      Pokechu22WorldDownloaderManager.properties4.setProperty("LinkedWorlds", text3);
      Pokechu22WorldDownloaderManager.applyText(text2, properties);
      this.list3.add(new DefinedDimensionsRecord(text2, text));
      this.updateState5();
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      this.internalTexturesGuiIconsPngManagerAdapter2.renderElement();
      super.updateState4();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 1) {
         this.virtualListener.updateState();
      }

      super.applyChar(character, count);
      if (this.enabled9) {
         this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
         if (count == 28) {
            String labelText3 = this.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
            if (labelText3 != null && !labelText3.isEmpty()) {
               this.applyText(labelText3);
               this.internalTexturesGuiIconsPngManagerAdapter.applyText("");
               this.enabled9 = false;
            }
         }
      }

      if (this.internalTexturesGuiIconsPngManagerAdapter2.checkChar(character, count)) {
         this.labelText2 = this.internalTexturesGuiIconsPngManagerAdapter2.getLabelText();
         this.updateState5();
      }
   }
}
