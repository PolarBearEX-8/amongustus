package client.setting;

import client.Client;
import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.font.FontRegistry;
import client.module.Module;
import client.render.RenderUtils;
import client.util.AuxiliaryModuleAdapter;
import client.util.BooleanSupplier;
import client.util.ExtendedListenerProcessor;
import client.util.KeyUtils;
import client.util.PlayerUtil;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;
import net.minecraft.util.MathHelper;

public class NumberSetting extends Setting {
   public static final Color color2 = new Color(34, 34, 34);
   public final ExtendedListenerProcessor extendedListenerProcessor = new ExtendedListenerProcessor(this, "V", 0.0);
   public long timestamp3;
   public boolean enabled2;
   public final NumberSliderComponent auxiliaryFontFaceEntryAdapter = new NumberSliderComponent();
   public double coordOffset1;
   public boolean state3;
   public double coordOffset;
   public final NumberSettingComponent fontFaceEntryAdapter;
   public long timestamp2 = 0L;
   public int count13;
   public int indexOffset2;
   public boolean state4;
   public static final Color color = new Color(185, 187, 190);

   public void setIndexOffset2(int indexOffset) {
      this.indexOffset2 = indexOffset;
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public void setCoordOffset1(double coordOffset2) {
      this.coordOffset1 = coordOffset2;
   }

   public int getIndexOffset2() {
      return this.indexOffset2;
   }

   public boolean isState3() {
      return this.state3;
   }

   public void applyDouble(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor2 = this.extendedListenerProcessor;
      Double offset2 = offset;
      extendedListenerProcessor2.applyObject2(offset2);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter2().resolveJsonObject(this);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      this.fontFaceEntryAdapter.applyIndexByIndex(count, count2, count3);
      this.auxiliaryFontFaceEntryAdapter.applyIndexByIndex(count, count2, count3);
      return false;
   }

   public NumberSliderComponent getAuxiliaryFontFaceEntryAdapter() {
      return this.auxiliaryFontFaceEntryAdapter;
   }

   @Override
   public void updateState() {
      this.extendedListenerProcessor.updateState();
   }

   public double getCoordOffset1() {
      return this.coordOffset1;
   }

   @Override
   public Setting getSetting(String text) {
      return this.getNumberSetting(text);
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      this.auxiliaryFontFaceEntryAdapter.applyIndex(count, count2, count3, value);
   }

   public double getValue() {
      return (Double)this.extendedListenerProcessor.getValue();
   }

   @Override
   public void updateState2() {
      if (this.getString().contains("frequency")) {
         double doubleItem = PlayerUtil.getDouble(this.coordOffset, 5.0, Math.random());
         this.applyDouble(doubleItem);
      } else if (this.getText4().contains("Rate of change")) {
         double doubleItem2 = PlayerUtil.getDouble(this.coordOffset, 5.0, Math.random());
         this.applyDouble(doubleItem2);
      } else {
         double doubleItem3 = PlayerUtil.getDouble(this.coordOffset, this.coordOffset1, Math.random());
         this.applyDouble(doubleItem3);
      }
   }

   private double computeValue(double offset, int count) {
      if (count == 0) {
         return (double)((int)offset);
      } else {
         double offset2 = Math.pow(10.0, (double)count);
         return (double)Math.round(offset * offset2) / offset2;
      }
   }

   public void setCoordOffset(double coordOffset2) {
      this.coordOffset = coordOffset2;
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter2().applyPostMotionInterceptor(this, jsonObject);
   }

   public boolean isState4() {
      return this.state4;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.fontFaceEntryAdapter.applyChar(character, count);
      this.auxiliaryFontFaceEntryAdapter.applyChar(character, count);
      if (RenderUtils.checkCondition(
            (float)this.auxiliaryFontFaceEntryAdapter.getCount3(),
            (float)this.auxiliaryFontFaceEntryAdapter.getCount4(),
            (float)this.auxiliaryFontFaceEntryAdapter.getCount7(),
            (float)this.auxiliaryFontFaceEntryAdapter.getCount9(),
            this.count2,
            this.count4
         )
         && (count == 203 || count == 205)) {
         this.enabled2 = true;
         this.timestamp3 = System.currentTimeMillis();
         this.timestamp2 = System.currentTimeMillis();
         double offset = 1.0 / Math.pow(10.0, (double)this.indexOffset2) * (double)(count == 203 ? -1 : 1);
         if (this.getValue() + offset <= this.coordOffset1 && this.getValue() + offset >= this.coordOffset) {
            double offset2 = this.getValue() + offset;
            this.applyDouble(offset2);
            this.setState1(true);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      this.auxiliaryFontFaceEntryAdapter.applyIndex(count, count2, count3);
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateState3() {
      int count = this.count13;
      this.applyIndex(count);
   }

   public NumberSetting getNumberSetting(String text) {
      this.setText4(text);
      return this;
   }

   public NumberSettingComponent getFontFaceEntryAdapter() {
      return this.fontFaceEntryAdapter;
   }

   public double getDouble2(boolean flag) {
      return flag ? MathHelper.getDouble7(this.getValue(), this.coordOffset, this.coordOffset1) : this.getValue();
   }

   @Override
   public int getCount10() {
      return 27;
   }

   public NumberSetting getNumberSetting(BooleanSupplier booleanSupplier) {
      this.booleanSupplier = booleanSupplier;
      return this;
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      FontFace fontFace2 = FontRegistry.fontFace9;
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace2, flag);
      String text = this.getText4() + ": ";
      this.state3 = this.enabled2;
      boolean flag2 = this.count13 == -1;
      if (this.checkFlag(flag2)) {
         this.count13 = (int)fontFace2.getScaleFactor(text);
      }

      if (flag) {
         double offset = (double)count3;
         double offset2 = (double)count4;
         double offset3 = offset;
         fontFace2.applyString8(text, offset3, offset2, -4604994);
         int count7 = count3 + this.count13;
         Color color3 = color;
         int count8 = (int)((float)count7 + (float)(200 - this.fontFaceEntryAdapter.getCount5()) / 2.0F) - 4;
         int count9 = this.fontFaceEntryAdapter.getCount5() + 8;
         int count10 = (int)(fontFace2.getFontSize() + 1.0F);
         int count11 = count9;
         int count12 = count8;
         double offset4 = 0.2;
         Color color4 = color3;
         int count14 = this.computeCount(color4, offset4, count, count2, count12, count4, count11, count10);
         if (RenderUtils.checkCondition(
            (float)this.auxiliaryFontFaceEntryAdapter.getCount3(),
            (float)this.auxiliaryFontFaceEntryAdapter.getCount4(),
            (float)this.auxiliaryFontFaceEntryAdapter.getCount7(),
            (float)this.auxiliaryFontFaceEntryAdapter.getCount9(),
            count,
            count2
         )) {
            if (this.enabled2
               && (KeyUtils.checkCondition(203) || KeyUtils.checkCondition(205))
               && System.currentTimeMillis() - this.timestamp3 >= 400L
               && System.currentTimeMillis() - this.timestamp2 >= 50L) {
               double offset5 = 1.0 / Math.pow(10.0, (double)Math.min(this.indexOffset2, 3)) * (double)(KeyUtils.checkCondition(203) ? -1 : 1);
               if (this.getValue() + offset5 <= this.coordOffset1 && this.getValue() + offset5 >= this.coordOffset) {
                  double offset6 = this.getValue() + offset5;
                  this.applyDouble(offset6);
                  this.setState1(true);
               }

               this.timestamp2 = System.currentTimeMillis();
            }

            this.state4 = false;
            if (KeyUtils.isActive()) {
               int count15 = Client.getClient().getInternalRenderListenerAdapte().count;
               if (count15 != 0) {
                  int count16 = count15 > 0 ? 1 : -1;
                  double offset7 = (double)Math.abs(count16) / Math.pow(10.0, (double)Math.min(this.indexOffset2, 3)) * (double)count16;
                  if (this.getValue() + offset7 <= this.coordOffset1 && this.getValue() + offset7 >= this.coordOffset) {
                     double offset8 = this.getValue() + offset7;
                     this.applyDouble(offset8);
                     this.setState1(true);
                     this.state4 = true;
                  }
               }
            }
         }

         if (!KeyUtils.checkCondition(203) && !KeyUtils.checkCondition(205)) {
            this.enabled2 = false;
         }

         NumberSliderComponent auxiliaryFontFaceEntryAdapter2 = this.auxiliaryFontFaceEntryAdapter;
         int count17 = count4 - 2;
         double value = this.getValue();
         double coordOffset2 = this.coordOffset;
         double coordOffset3 = this.coordOffset1;
         int indexOffset = this.indexOffset2;
         Color color5 = color2;
         Color color6 = Client.getClient().getColor();
         int count18 = count4 - 2;
         double offset9 = 0.2;
         Color color7 = color6;
         Color color8 = new Color(this.computeCount(color7, offset9, count, count2, count7, count18, 200, 20));
         Color color9 = color5;
         int count19 = indexOffset;
         double offset10 = coordOffset3;
         double offset11 = coordOffset2;
         double offset12 = value;
         byte byteValue = 20;
         short shortValue = 200;
         int count20 = count17;
         double offset13 = auxiliaryFontFaceEntryAdapter2.computeValueByFontFace(count7, count20, shortValue, byteValue, count, count2, offset12, offset11, offset10, count19, fontFace2, color9, color8);
         if (offset13 != this.getValue()) {
            this.setState1(true);
         }

         this.applyDouble(offset13);
         int count21 = this.fontFaceEntryAdapter.getCount5();
         NumberSettingComponent fontFaceEntryAdapter2 = this.fontFaceEntryAdapter;
         int count22 = (int)((float)count7 + (float)(200 - count21) / 2.0F);
         Color color10 = new Color(count14);
         int count23 = count22;
         fontFaceEntryAdapter2.applyFontFaceByFontFace(count23, count4, count, count2, fontFace2, color10);
         if (this.fontFaceEntryAdapter.isEnabled()) {
            if (!Objects.equals(this.fontFaceEntryAdapter.getTextAsText(), "")) {
               if (!this.fontFaceEntryAdapter.getTextAsText().equals("-")
                  && !this.fontFaceEntryAdapter.getTextAsText().endsWith("-")
                  && !this.fontFaceEntryAdapter.getTextAsText().endsWith("E")) {
                  offset = Double.parseDouble(this.fontFaceEntryAdapter.getTextAsText());
                  int indexOffset3 = this.indexOffset2;
                  double offset14 = offset;
                  double offset15 = this.computeValue(offset14, indexOffset3);
                  if (offset15 != this.getValue()) {
                     this.setState1(true);
                  }

                  this.applyDouble(offset15);
               } else {
                  double offset16 = Double.MAX_VALUE;
                  this.applyDouble(offset16);
               }
            }
         } else {
            NumberSettingComponent fontFaceEntryAdapter3 = this.fontFaceEntryAdapter;
            String text2 = String.valueOf(this.getValue());
            fontFaceEntryAdapter3.applyTextByText(text2);
         }

         this.state5 = this.fontFaceEntryAdapter.isEnabled() || this.auxiliaryFontFaceEntryAdapter.isEnabled();
         this.indexOffset = count7 + 220;
      }
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.getNumberSetting(booleanSupplier);
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public NumberSetting(Module module, String text, double posX, double posY, double posZ, int count) {
      this(text, module, posX, posY, posZ, count);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }

   public NumberSetting(String text, double posX, double posY, double posZ, int count) {
      this(text, null, posX, posY, posZ, count);
   }

   private NumberSetting(String text, Module module, double posX, double posY, double posZ, int count) {
      super(module, text);
      this.timestamp3 = 0L;
      this.enabled2 = false;
      this.count13 = -1;
      this.state3 = false;
      this.state4 = false;
      this.applyDouble(posX);
      ExtendedListenerProcessor extendedListenerProcessor2 = this.extendedListenerProcessor;
      Double offset = posX;
      extendedListenerProcessor2.setDataRef(offset);
      this.coordOffset = posY;
      this.coordOffset1 = posZ;
      this.indexOffset2 = count;
      this.fontFaceEntryAdapter = new NumberSettingComponent(String.valueOf(posX), "1234567890.-");
   }

   public NumberSetting(Module module, String text, double posX, double posY, double posZ, int count, SettingGroup settingGroup) {
      this(text, module, posX, posY, posZ, count);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSetting2((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }
}
