package client.setting;

import client.Client;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.font.FontRegistry;
import client.gui.ClickGuiPanel;
import client.module.Module;
import client.render.RenderUtils;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.util.ListenerTracker;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

public abstract class Setting extends ListenerTracker {
   public String text = "";
   public int count8;
   public int count2;
   public boolean state1;
   public FontFace fontFace;
   public int count5;
   public int indexOffset;
   public int count4;
   public ConsumerTask consumerTask;
   public static HashMap hashMap2 = new HashMap();
   public boolean enabled;
   public boolean state;
   public BooleanSupplier booleanSupplier = null;
   public int count9;
   public int count6;
   public static HashMap hashMap = new HashMap();
   public long timestamp;
   public boolean state2;
   public String text4;
   public int count3;
   public boolean state5;
   public String text3;
   public int count7;

   public void applyModule(Module module) {
      this.setModuleModeOption(module);
   }

   public void setState2(String state3) {
      if (!this.text4.equals(state3)) {
         this.text4 = state3;
         this.state2 = true;
      }
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text2) {
      this.text = text2;
   }

   public boolean isState1() {
      return this.state1;
   }

   public void setBooleanSupplier(BooleanSupplier booleanSupplier2) {
      this.booleanSupplier = booleanSupplier2;
   }

   public abstract JsonObject resolveJsonObject(SupplementalListener supplementalListener);

   public boolean checkIndex(int count, int count10, int count11) throws IOException {
      return false;
   }

   public int computeCount(Color color, double offset, int count, int count10, int count11, int count12, int count13, int count14) {
      int count15;
      if (RenderUtils.checkCondition((float)count11, (float)count12, (float)count13, (float)count14, count, count10)) {
         float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
         float factor = (float)((double)hsbValues[2] + offset);
         if ((double)hsbValues[2] + offset > 1.0) {
            factor = 1.0F;
         } else if ((double)hsbValues[2] + offset < 0.0) {
            factor = 0.0F;
         }

         count15 = Color.HSBtoRGB(hsbValues[0], hsbValues[1], factor);
      } else {
         count15 = color.getRGB();
      }

      return count15;
   }

   public void updateState5() {
      this.updateState6();
   }

   public String getTextAsText() {
      return this.getModuleModeOption() != null ? ((Module)this.getModuleModeOption()).getString() : "";
   }

   public Setting getSetting(String text2) {
      this.text4 = text2;
      return this;
   }

   public Module getModule() {
      return (Module)this.getModuleModeOption();
   }

   public boolean isState2() {
      return this.state2;
   }

   public synchronized void setState1(boolean state3) {
      this.state1 = state3;
   }

   public abstract void updateState();

   public boolean checkFlag(boolean flag) {
      boolean flag2 = this.state2 || flag;
      if (flag2) {
         this.state2 = false;
      }

      return flag2;
   }

   public String[] getTextArray() {
      return new String[]{this.getString()};
   }

   public void updateState2() {
   }

   public void setFontFace(FontFace fontFace2) {
      this.fontFace = fontFace2;
   }

   public boolean isVisible() {
      return this.booleanSupplier == null || this.booleanSupplier.getAsBoolean();
   }

   public void applyIndex(int count, int count10, int count11, long value) {
   }

   public boolean isState() {
      return this.state;
   }

   public void setState5(boolean state3) {
      this.state5 = state3;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public void applyText(String text2, String text5) {
      this.state2 = true;
      if (text5.equals("English_Default")) {
         this.text3 = "";
      } else {
         this.text3 = text5;
      }
   }

   public void setState2(boolean state3) {
      this.state2 = state3;
   }

   public abstract void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject);

   public void updateState4() {
   }

   public String getText3() {
      return this.text3;
   }

   public void applyIndex(int count) {
      long value = 0x2F773EAEEB37L;
      if (this.length() && Client.getClient().getModuleManager().clickGui.settingSettingDescriptions.isEnabled()) {
         boolean flag = RenderUtils.checkCondition(
            (float)(this.count5 - 2), (float)this.count6, (float)count, this.fontFace.getFontSize(), this.count2, this.count4
         );
         if (flag) {
            if (!this.enabled) {
               this.timestamp = System.currentTimeMillis();
               this.count9 = -1337;
               this.count8 = -1337;
            }

            if (System.currentTimeMillis() - this.timestamp >= 750L) {
               if (this.count9 == -1337 && this.count8 == -1337) {
                  this.count9 = this.count2;
                  this.count8 = this.count4;
               }

               FontFace fontFace2 = this.fontFace;
               String text2 = this.getText();
               int count10 = this.count8;
               int count11 = this.count9;
               String text5 = text2;
               ClickGuiPanel.applyFontFaceByFontFace(fontFace2, text5, count11, count10);
            }
         }

         this.enabled = flag;
      }
   }

   public void updateState6() {
      if (this.consumerTask != null && this.isState1()) {
         this.consumerTask.run();
         this.setState1(false);
      }
   }

   public void setText3(String text2) {
      this.text3 = text2;
   }

   public void applyChar(char character, int count) throws IOException {
   }

   public boolean isState5() {
      return this.state5;
   }

   public Setting getSetting(ConsumerTask consumerTask2) {
      this.consumerTask = consumerTask2;
      return this;
   }

   public void applyIndex(int count, int count10, int count11) {
   }

   public void setText4(String text2) {
      this.text4 = text2;
   }

   public abstract void updateState3();

   public boolean length() {
      return this.text.length() > 0;
   }

   public Setting getSetting(BooleanSupplier booleanSupplier2) {
      this.booleanSupplier = booleanSupplier2;
      return this;
   }

   public int getCount10() {
      return 25;
   }

   public void applyFontFace(int count, int count10, int count11, int count12, int count13, int count14, FontFace fontFace2, boolean flag) {
      this.count2 = count;
      this.count4 = count10;
      this.count5 = count11;
      this.count6 = count12;
      this.count3 = count13;
      this.count7 = count14;
      this.fontFace = fontFace2;
   }

   public void setState(boolean state3) {
      this.state = state3;
   }

   public String getText4() {
      return this.text3.isEmpty() ? this.text4 : this.text3;
   }

   @Override
   public void updateState15() {
      this.updateState6();
   }

   public Setting(String text2) {
      this(null, text2);
   }

   public Setting(Module module, String text2) {
      super(module, text2);
      this.consumerTask = null;
      this.state = false;
      this.text3 = "";
      this.state2 = false;
      this.state1 = false;
      this.indexOffset = 0;
      this.count5 = -1000;
      this.count6 = -1000;
      this.count3 = -1000;
      this.count7 = -1000;
      this.fontFace = FontRegistry.fontFace2;
      this.timestamp = 0L;
      Client.getClient().getClass();
      if (module != null && module.getString().equals(text2)) {
         throw new IllegalArgumentException("Setting '" + text2 + "' is duplicated");
      } else {
         this.text4 = text2;
      }
   }
}
