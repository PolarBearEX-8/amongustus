package client.setting;

import augustus.modules.render.CustomClientColor;
import client.Client;
import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.module.Module;
import client.render.RenderUtils;
import client.util.AuxiliaryModuleAdapter;
import client.util.AuxiliaryStringArrayEntry;
import client.util.ExtendedListenerProcessor;
import client.util.InternalListenerCoordinator;
import client.util.RandomUtil;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.util.MathHelper;

public class ColorSetting extends Setting {
   public final ColorAlphaProperty colorAlphaProperty;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2;
   public final ExtendedListenerProcessor extendedListenerProcessor6;
   public static final Color color = new Color(34, 34, 34);
   public final ColorHueProperty colorHueProperty;
   public final ExtendedListenerProcessor extendedListenerProcessor4;
   public int indexOffset1;
   public final ColorHueProperty colorHueProperty2;
   public final NumberSetting numberSetting;
   public final NumberSetting numberSetting2;
   public final ExtendedListenerProcessor extendedListenerProcessor3;
   public final ExtendedListenerProcessor extendedListenerProcessor5;
   public final InternalListenerCoordinator internalListenerCoordinator3;
   public final ColorSaturationProperty colorSaturationProperty3;
   public final NumberSetting numberSetting3;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry;
   public final BooleanSetting booleanSetting;
   public final ColorSaturationProperty colorSaturationProperty;
   public final NumberSetting numberSetting4;
   public final BooleanSetting booleanSetting2;
   public final ExtendedListenerProcessor extendedListenerProcessor;
   public final InternalListenerCoordinator internalListenerCoordinator;
   public final InternalListenerCoordinator internalListenerCoordinator4;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry4;
   public final NumberSetting numberSetting5;
   public final InternalListenerCoordinator internalListenerCoordinator2;
   public final ColorAlphaProperty colorAlphaProperty6;
   public final ColorPickerState auxiliaryClientManager;
   public float[] scaleFactorArray5;
   public Color color2;
   public final InternalListenerCoordinator internalListenerCoordinator5;
   public final ExtendedListenerProcessor extendedListenerProcessor2;
   public final NumberSetting numberSetting6;
   public float[] scaleFactorArray;
   public final BooleanSetting booleanSetting3;
   public Color color3;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry3;
   public final ColorPickerState auxiliaryClientManager4;
   public int count10;
   public final BooleanSetting booleanSetting4;
   public final NumberSetting numberSetting7;

   public ColorPickerState getAuxiliaryClientManager4() {
      return this.auxiliaryClientManager4;
   }

   public NumberSetting getNumberSetting5() {
      return this.numberSetting5;
   }

   public boolean isToggled() {
      return (Boolean)this.auxiliaryStringArrayEntry3.getValue();
   }

   public double getDouble() {
      return (Double)this.extendedListenerProcessor.getValue();
   }

   public float[] getScaleFactorArray5() {
      return this.scaleFactorArray5;
   }

   public ColorSetting getColorSetting(int count) {
      double offset = (double)count;
      this.updateDouble4(offset);
      return this;
   }

   public void applyDouble2(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor7 = this.extendedListenerProcessor6;
      Double offset2 = offset;
      extendedListenerProcessor7.applyObject2(offset2);
      this.numberSetting6.applyDouble(offset);
   }

   public int getCount2() {
      return (Integer)this.internalListenerCoordinator2.getValue();
   }

   public int getCountAsIndex() {
      return (Integer)this.internalListenerCoordinator3.getValue();
   }

   public Color getColor(int count) {
      this.applyIndex3(count);
      return new Color(this.count10);
   }

   public NumberSetting getNumberSetting2() {
      return this.numberSetting2;
   }

   public void applyDoubleByDouble(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor7 = this.extendedListenerProcessor5;
      Double offset2 = offset;
      extendedListenerProcessor7.applyObject2(offset2);
      this.numberSetting3.applyDouble(offset);
   }

   public void applyIndexByIndex(int count) {
      InternalListenerCoordinator internalListenerCoordinator6 = this.internalListenerCoordinator2;
      Integer count2 = count;
      internalListenerCoordinator6.applyObject2(count2);
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   public float getFloat() {
      return RandomUtil.processAction5(this.count10);
   }

   public int getInt4() {
      return RandomUtil.processAction3(this.count10);
   }

   public void setScaleFactorArray(float[] scaleFactorArray2) {
      this.scaleFactorArray = scaleFactorArray2;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      this.colorHueProperty.applyIndexByIndex(count, count2, count3);
      this.colorSaturationProperty.applyIndexByIndex(count, count2, count3);
      this.colorAlphaProperty.applyIndexByIndex(count, count2, count3);
      this.auxiliaryClientManager.applyIndexByIndex(count, count2, count3);
      if (this.isToggled()) {
         this.numberSetting7.checkIndex(count, count2, count3);
         this.numberSetting3.checkIndex(count, count2, count3);
      }

      if (!this.isActive() && !this.isToggled() && !this.isActiveAsFlag()) {
         this.booleanSetting3.checkIndex(count, count2, count3);
      }

      if (!this.checkCondition() && !this.isToggled() && !this.isActiveAsFlag()) {
         this.booleanSetting.checkIndex(count, count2, count3);
      }

      if (!this.isActive() && !this.checkCondition() && !this.isActiveAsFlag()) {
         this.booleanSetting4.checkIndex(count, count2, count3);
      }

      if (!this.isActive() && !this.checkCondition() && !this.isToggled() && !(this.getModule() instanceof CustomClientColor)) {
         this.booleanSetting2.checkIndex(count, count2, count3);
      }

      if (this.isActive() && !this.isToggled()) {
         this.numberSetting6.checkIndex(count, count2, count3);
         this.numberSetting2.checkIndex(count, count2, count3);
         this.colorHueProperty2.applyIndexByIndex(count, count2, count3);
         this.colorSaturationProperty3.applyIndexByIndex(count, count2, count3);
         this.colorAlphaProperty6.applyIndexByIndex(count, count2, count3);
         this.auxiliaryClientManager4.applyIndexByIndex(count, count2, count3);
      }

      if (this.checkCondition() && !this.isToggled() && !this.isActive()) {
         this.numberSetting.checkIndex(count, count2, count3);
         this.numberSetting4.checkIndex(count, count2, count3);
      }

      if (this.isActiveAsFlag() && !(this.getModule() instanceof CustomClientColor)) {
         this.numberSetting5.checkIndex(count, count2, count3);
      }

      this.setState1(true);
      return false;
   }

   public NumberSetting getNumberSetting3() {
      return this.numberSetting3;
   }

   public void setEnabled(boolean enabled) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry2;
      Boolean flag = enabled;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.booleanSetting.setState3(enabled);
   }

   public boolean checkCondition() {
      return (Boolean)this.auxiliaryStringArrayEntry4.getValue();
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter6().resolveJsonObject(this);
   }

   public BooleanSetting getBooleanSetting2() {
      return this.booleanSetting2;
   }

   public ColorSetting resolveColorSetting(Color color4) {
      Color color5 = new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), color4.getAlpha());
      this.applyColor(color5);
      return this;
   }

   @Override
   public void updateState() {
      this.internalListenerCoordinator4.updateState();
      this.internalListenerCoordinator2.updateState();
      this.auxiliaryStringArrayEntry3.updateState();
      this.auxiliaryStringArrayEntry2.updateState();
      this.auxiliaryStringArrayEntry4.updateState();
      this.auxiliaryStringArrayEntry.updateState();
      this.internalListenerCoordinator5.updateState();
      this.internalListenerCoordinator3.updateState();
      this.extendedListenerProcessor4.updateState();
      this.extendedListenerProcessor5.updateState();
      this.extendedListenerProcessor6.updateState();
      this.extendedListenerProcessor3.updateState();
      this.extendedListenerProcessor.updateState();
      this.extendedListenerProcessor2.updateState();
      this.internalListenerCoordinator.updateState();
   }

   public ColorSetting getColorSetting2(int count) {
      double offset = (double)count;
      this.applyDouble2(offset);
      return this;
   }

   public void applyColor(Color color4) {
      this.color2 = color4;
      int alpha = this.color2.getAlpha();
      int count2 = alpha;
      this.applyIndex2(count2);
      Color.RGBtoHSB(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), this.scaleFactorArray5);
   }

   private int computeCount(int count) {
      Color color4 = this.color2;
      Color color5 = this.color3;
      int value = (int)this.getValue();
      Color color6 = color5;
      return RandomUtil.computeCount(color4, color6, count, value);
   }

   public ColorSaturationProperty getColorSaturationProperty() {
      return this.colorSaturationProperty;
   }

   public void applyDouble(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor7 = this.extendedListenerProcessor2;
      Double offset2 = offset;
      extendedListenerProcessor7.applyObject2(offset2);
      this.numberSetting4.applyDouble(offset);
   }

   public float getFloat2() {
      return RandomUtil.processAction4(this.count10);
   }

   public BooleanSetting getBooleanSetting3() {
      return this.booleanSetting3;
   }

   private Integer getInteger() {
      Color color4 = this.color3;
      return this.computeCount(color4);
   }

   public ColorSetting getColorSetting(Color color4) {
      Color color5 = new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), color4.getAlpha());
      this.applyColorByColor(color5);
      return this;
   }

   public void updateDouble4(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor7 = this.extendedListenerProcessor4;
      Double offset2 = offset;
      extendedListenerProcessor7.applyObject2(offset2);
      this.numberSetting7.applyDouble(offset);
   }

   private void applyInteger(Integer count) {
      Color color4 = new Color(count);
      Color color5 = new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), this.getInt9());
      this.applyColor(color5);
   }

   public float getFactor() {
      return RandomUtil.processAction2(this.count10);
   }

   public void setState3(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry4;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.booleanSetting3.setState3(state);
   }

   public double getValue() {
      return (Double)this.extendedListenerProcessor6.getValue();
   }

   public ColorAlphaProperty getColorAlphaProperty6() {
      return this.colorAlphaProperty6;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      if (!(this.getModule() instanceof CustomClientColor)) {
         this.booleanSetting2.applyIndex(count, count2, count3, value);
         this.numberSetting5.applyIndex(count, count2, count3, value);
      }

      this.colorHueProperty.applyIndex(count, count2, count3, value);
      this.colorSaturationProperty.applyIndex(count, count2, count3, value);
      this.colorAlphaProperty.applyIndex(count, count2, count3, value);
      this.auxiliaryClientManager.applyIndex(count, count2, count3, value);
      this.numberSetting7.applyIndex(count, count2, count3, value);
      this.numberSetting6.applyIndex(count, count2, count3, value);
      this.colorHueProperty2.applyIndex(count, count2, count3, value);
      this.colorSaturationProperty3.applyIndex(count, count2, count3, value);
      this.colorAlphaProperty6.applyIndex(count, count2, count3, value);
      this.auxiliaryClientManager4.applyIndex(count, count2, count3, value);
      this.numberSetting2.applyIndex(count, count2, count3, value);
      this.numberSetting3.applyIndex(count, count2, count3, value);
      this.numberSetting.applyIndex(count, count2, count3, value);
      this.numberSetting4.applyIndex(count, count2, count3, value);
   }

   public ColorSetting getColorSetting3(boolean flag) {
      this.setState6(flag);
      return this;
   }

   public int getInt7() {
      return RandomUtil.processAction9(this.count10);
   }

   private void applyIntegerByInteger(Integer count) {
      Color color4 = new Color(count);
      Color color5 = new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), this.getCount2());
      this.applyColorByColor(color5);
   }

   public void setScaleFactorArray5(float[] scaleFactorArray2) {
      this.scaleFactorArray5 = scaleFactorArray2;
   }

   public void applyDouble3(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor7 = this.extendedListenerProcessor3;
      Double offset2 = offset;
      extendedListenerProcessor7.applyObject2(offset2);
      this.numberSetting2.applyDouble(offset);
   }

   public BooleanSetting getBooleanSetting() {
      return this.booleanSetting;
   }

   public ColorSetting resolveColorSettingByIndex(int count) {
      this.updateInt(count);
      return this;
   }

   public ColorSaturationProperty getColorSaturationProperty3() {
      return this.colorSaturationProperty3;
   }

   public void updateInt(int count) {
      InternalListenerCoordinator internalListenerCoordinator6 = this.internalListenerCoordinator3;
      Integer count2 = count;
      internalListenerCoordinator6.applyObject2(count2);
      double offset = (double)count;
      this.numberSetting5.applyDouble(offset);
   }

   public double getDouble3() {
      return (Double)this.extendedListenerProcessor4.getValue();
   }

   public NumberSetting getNumberSetting6() {
      return this.numberSetting6;
   }

   public double getValue3() {
      return (Double)this.extendedListenerProcessor3.getValue();
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter6().applyPostMotionInterceptor(this, jsonObject);
   }

   public Color getColor2() {
      return this.color2;
   }

   private int computeCount2(int count) {
      return RandomUtil.computeCountByIndex(count, (int)this.getDouble(), this.color2.getAlpha());
   }

   @Override
   public void updateState4() {
      super.updateState4();
   }

   public int getInt9() {
      return (Integer)this.internalListenerCoordinator4.getValue();
   }

   public float[] getScaleFactorArray() {
      return this.scaleFactorArray;
   }

   public void applyIndex3(int count) {
      if (this.isToggled()) {
         int count3 = (int)this.getValueAsDouble() * count;
         this.count10 = this.computeCountByIndex(count3);
      } else if (this.isActive()) {
         int count4 = (int)(this.getValue3() * (double)count);
         int count5 = count4;
         this.count10 = this.computeCount(count5);
      } else if (this.checkCondition()) {
         int count6 = (int)(this.getValue2() * (double)count);
         this.count10 = this.computeCount2(count6);
      } else if (this.isActiveAsFlag()) {
         this.count10 = RandomUtil.getInt12(
            Client.getClient().getColor().getRed(), Client.getClient().getColor().getGreen(), Client.getClient().getColor().getBlue(), this.getCountAsIndex()
         );
      } else {
         this.count10 = this.color2.getRGB();
      }
   }

   public boolean isActiveAsFlag() {
      return (Boolean)this.auxiliaryStringArrayEntry.getValue();
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   public int getInt10() {
      return RandomUtil.processAction6(this.count10);
   }

   private int computeCount(Color color4) {
      return new Color(color4.getRed(), color4.getGreen(), color4.getBlue()).getRGB();
   }

   public ColorPickerState getAuxiliaryClientManager() {
      return this.auxiliaryClientManager;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      if (!(this.getModule() instanceof CustomClientColor)) {
         this.booleanSetting2.applyIndex(count, count2, count3);
         this.numberSetting5.applyIndex(count, count2, count3);
      }

      this.colorHueProperty.applyIndex(count, count2, count3);
      this.colorSaturationProperty.applyIndex(count, count2, count3);
      this.colorAlphaProperty.applyIndex(count, count2, count3);
      this.auxiliaryClientManager.applyIndex(count, count2, count3);
      this.numberSetting7.applyIndex(count, count2, count3);
      this.numberSetting6.applyIndex(count, count2, count3);
      this.colorHueProperty2.applyIndex(count, count2, count3);
      this.colorSaturationProperty3.applyIndex(count, count2, count3);
      this.colorAlphaProperty6.applyIndex(count, count2, count3);
      this.auxiliaryClientManager4.applyIndex(count, count2, count3);
      this.numberSetting2.applyIndex(count, count2, count3);
      this.numberSetting3.applyIndex(count, count2, count3);
      this.numberSetting.applyIndex(count, count2, count3);
      this.numberSetting4.applyIndex(count, count2, count3);
   }

   public NumberSetting getNumberSetting4() {
      return this.numberSetting4;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (!(this.getModule() instanceof CustomClientColor)) {
         this.booleanSetting2.applyChar(character, count);
         this.numberSetting5.applyChar(character, count);
      }

      this.colorHueProperty.applyChar(character, count);
      this.colorSaturationProperty.applyChar(character, count);
      this.colorAlphaProperty.applyChar(character, count);
      this.auxiliaryClientManager.applyChar(character, count);
      this.numberSetting7.applyChar(character, count);
      this.numberSetting6.applyChar(character, count);
      this.colorHueProperty2.applyChar(character, count);
      this.colorSaturationProperty3.applyChar(character, count);
      this.colorAlphaProperty6.applyChar(character, count);
      this.auxiliaryClientManager4.applyChar(character, count);
      this.numberSetting2.applyChar(character, count);
      this.numberSetting3.applyChar(character, count);
      this.numberSetting.applyChar(character, count);
      this.numberSetting4.applyChar(character, count);
   }

   public void setState4(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.booleanSetting2.setState3(state);
   }

   public void updateDouble6(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor7 = this.extendedListenerProcessor;
      Double offset2 = offset;
      extendedListenerProcessor7.applyObject2(offset2);
      this.numberSetting.applyDouble(offset);
   }

   private int computeCountByIndex(int count) {
      return RandomUtil.computeCount(count, (int)this.getDouble3(), this.scaleFactorArray5[1], this.scaleFactorArray5[2], this.color2.getAlpha());
   }

   public ColorHueProperty getColorHueProperty2() {
      return this.colorHueProperty2;
   }

   public double getValueAsDouble() {
      return (Double)this.extendedListenerProcessor5.getValue();
   }

   @Override
   public void updateState3() {
      FontFace fontFace = this.fontFace;
      String text = this.getText4() + ": ";
      int scaleFactor = (int)fontFace.getScaleFactor(text);
      this.applyIndex(scaleFactor);
   }

   public ColorSetting getColorSetting5(int count) {
      double offset = (double)count;
      this.applyDouble3(offset);
      return this;
   }

   public float getFloat4() {
      return RandomUtil.processAction7(this.count10);
   }

   public double getValue2() {
      return (Double)this.extendedListenerProcessor2.getValue();
   }

   public void setState6(boolean state) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = this.auxiliaryStringArrayEntry3;
      Boolean flag = state;
      auxiliaryStringArrayEntry5.applyObject2(flag);
      this.booleanSetting4.setState3(state);
   }

   public int getIndexOffset1() {
      return this.indexOffset1;
   }

   public BooleanSetting getBooleanSetting4() {
      return this.booleanSetting4;
   }

   public void applyIndex2(int count) {
      InternalListenerCoordinator internalListenerCoordinator6 = this.internalListenerCoordinator4;
      Integer count2 = count;
      internalListenerCoordinator6.applyObject2(count2);
   }

   public NumberSetting getNumberSetting7() {
      return this.numberSetting7;
   }

   public int getInt12(int count) {
      this.applyIndex3(count);
      return this.count10;
   }

   public boolean isActive() {
      return (Boolean)this.auxiliaryStringArrayEntry2.getValue();
   }

   public ColorHueProperty getColorHueProperty() {
      return this.colorHueProperty;
   }

   public Color getColor3() {
      return this.color3;
   }

   public ColorAlphaProperty getColorAlphaProperty() {
      return this.colorAlphaProperty;
   }

   public NumberSetting getNumberSetting() {
      return this.numberSetting;
   }

   public void applyColorByColor(Color color4) {
      this.color3 = color4;
      int alpha = this.color3.getAlpha();
      this.applyIndexByIndex(alpha);
      Color.RGBtoHSB(this.color3.getRed(), this.color3.getGreen(), this.color3.getBlue(), this.scaleFactorArray);
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace, flag);
      if (flag) {
         this.indexOffset1 = 0;
         String text = this.getText4() + ": ";
         double offset = (double)count3;
         double offset2 = (double)count4;
         double offset3 = offset;
         fontFace.applyString8(text, offset3, offset2, -4604994);
         this.indexOffset1 += 25;
         float[] scaleFactorArray2 = this.scaleFactorArray5;
         ColorHueProperty colorHueProperty3 = this.colorHueProperty;
         int count8 = count4 - 2 + this.indexOffset1;
         double offset4 = (double)this.scaleFactorArray5[0];
         Color color4 = color;
         float[] scaleFactorArray3 = this.scaleFactorArray5;
         int intItem = this.getInt9();
         float[] factorArray = scaleFactorArray3;
         Color color5 = color4;
         byte byteValue = 7;
         double offset5 = 1.0;
         double offset6 = 0.0;
         double offset7 = offset4;
         byte byteValue2 = 20;
         short shortValue = 200;
         int count9 = count8;
         scaleFactorArray2[0] = (float)colorHueProperty3.computeValue(count3, count9, shortValue, byteValue2, count, count2, offset7, offset6, offset5, byteValue, fontFace, color5, factorArray, intItem);
         this.indexOffset1 += 25;
         scaleFactorArray2 = this.scaleFactorArray5;
         ColorSaturationProperty colorSaturationProperty2 = this.colorSaturationProperty;
         count8 = count4 - 2 + this.indexOffset1;
         offset4 = (double)this.scaleFactorArray5[1];
         color4 = color;
         scaleFactorArray3 = this.scaleFactorArray5;
         int intItem2 = this.getInt9();
         float[] factorArray2 = scaleFactorArray3;
         Color color6 = color4;
         byte byteValue3 = 7;
         double offset8 = 1.0;
         double offset9 = 0.0;
         double offset10 = offset4;
         byte byteValue4 = 20;
         short shortValue2 = 200;
         int count11 = count8;
         scaleFactorArray2[1] = (float)colorSaturationProperty2.computeValueByFontFace(
            count3, count11, shortValue2, byteValue4, count, count2, offset10, offset9, offset8, byteValue3, fontFace, color6, factorArray2, intItem2
         );
         this.indexOffset1 += 25;
         scaleFactorArray2 = this.scaleFactorArray5;
         ColorAlphaProperty colorAlphaProperty2 = this.colorAlphaProperty;
         count8 = count4 - 2 + this.indexOffset1;
         offset4 = (double)this.scaleFactorArray5[2];
         color4 = color;
         scaleFactorArray3 = this.scaleFactorArray5;
         int intItem3 = this.getInt9();
         float[] factorArray3 = scaleFactorArray3;
         Color color7 = color4;
         byte byteValue5 = 7;
         double offset11 = 1.0;
         double offset12 = 0.0;
         double offset13 = offset4;
         byte byteValue6 = 20;
         short shortValue3 = 200;
         int count12 = count8;
         scaleFactorArray2[2] = (float)colorAlphaProperty2.computeValue(count3, count12, shortValue3, byteValue6, count, count2, offset13, offset12, offset11, byteValue5, fontFace, color7, factorArray3, intItem3);
         this.indexOffset1 += 25;
         ColorPickerState auxiliaryClientManager2 = this.auxiliaryClientManager;
         int count13 = count4 - 2 + this.indexOffset1;
         double intItem4 = (double)this.getInt9();
         float[] scaleFactorArray4 = this.scaleFactorArray5;
         Color color8 = color;
         byte byteValue7 = 7;
         double offset14 = 255.0;
         double offset15 = 0.0;
         double offset16 = intItem4;
         byte byteValue8 = 20;
         short shortValue4 = 200;
         int count14 = count13;
         int count15 = (int)auxiliaryClientManager2.computeValueByFontFace(count3, count14, shortValue4, byteValue8, count, count2, offset16, offset15, offset14, byteValue7, fontFace, color8, scaleFactorArray4);
         int count16 = (short)-22624;
         int count17 = count15;
         this.applyIndex2(count17);
         this.indexOffset1 += 27;
         Color color9 = new Color(Color.HSBtoRGB(this.scaleFactorArray5[0], this.scaleFactorArray5[1], this.scaleFactorArray5[2]));
         this.color2 = new Color(color9.getRed(), color9.getGreen(), color9.getBlue(), this.getInt9());
         int count18 = 240;
         int count19 = 25;
         count13 = count3 + 240;
         int count20 = count4 + 25;
         int count21 = count13;
         this.booleanSetting4.applyFontFace(count, count2, count21, count20, count5, count6, fontFace, true);
         boolean enabled = this.booleanSetting4.isEnabled();
         this.setState6(enabled);
         count19 += 25;
         if (this.isToggled()) {
            count13 = count3 + count18;
            int count22 = count4 + count19;
            int count23 = count13;
            this.numberSetting7.applyFontFace(count, count2, count23, count22, count5, count6, fontFace, true);
            double value = this.numberSetting7.getValue();
            this.updateDouble4(value);
            count19 += 25;
            count13 = count3 + count18;
            int count24 = count4 + count19;
            int count25 = count13;
            this.numberSetting3.applyFontFace(count, count2, count25, count24, count5, count6, fontFace, true);
            double value2 = this.numberSetting3.getValue();
            this.applyDoubleByDouble(value2);
            count19 += 25;
         }

         count13 = count3 + count18;
         count16 = count4 + count19;
         int count26 = count6 + 50;
         int count27 = count16;
         int count28 = count13;
         this.booleanSetting.applyFontFace(count, count2, count28, count27, count5, count26, fontFace, true);
         boolean enabled2 = this.booleanSetting.isEnabled();
         this.setEnabled(enabled2);
         count19 += 25;
         if (this.isActive() && !this.isToggled() && !this.isActiveAsFlag()) {
            count13 = count3 + count18;
            int count29 = count4 + count19;
            int count30 = count13;
            this.numberSetting6.applyFontFace(count, count2, count30, count29, count5, count6, fontFace, true);
            double value3 = this.numberSetting6.getValue();
            this.applyDouble2(value3);
            count19 += 25;
            count13 = count3 + count18;
            int count31 = count4 + count19;
            int count32 = count13;
            this.numberSetting2.applyFontFace(count, count2, count32, count31, count5, count6, fontFace, true);
            double value4 = this.numberSetting2.getValue();
            this.applyDouble3(value4);
            int count33 = 0;
            int count34 = count18 + 300;
            offset = (double)(count3 + count34);
            double offset17 = (double)(count4 + 0);
            double offset18 = offset;
            String text2 = "FadeColor:";
            fontFace.applyString8(text2, offset18, offset17, -4604994);
            count33 += 25;
            scaleFactorArray2 = this.scaleFactorArray;
            ColorHueProperty colorHueProperty4 = this.colorHueProperty2;
            count13 = count3 + count34;
            count16 = count4 - 2 + 25;
            offset4 = (double)this.scaleFactorArray[0];
            color4 = color;
            scaleFactorArray3 = this.scaleFactorArray;
            int count35 = this.getCount2();
            float[] factorArray4 = scaleFactorArray3;
            Color color10 = color4;
            byte byteValue9 = 7;
            double offset19 = 1.0;
            double offset20 = 0.0;
            double offset21 = offset4;
            byte byteValue10 = 20;
            short shortValue5 = 200;
            int count36 = count16;
            int count37 = count13;
            scaleFactorArray2[0] = (float)colorHueProperty4.computeValue(count37, count36, shortValue5, byteValue10, count, count2, offset21, offset20, offset19, byteValue9, fontFace, color10, factorArray4, count35);
            count33 += 25;
            scaleFactorArray2 = this.scaleFactorArray;
            ColorSaturationProperty colorSaturationProperty4 = this.colorSaturationProperty3;
            count13 = count3 + count34;
            count16 = count4 - 2 + 50;
            offset4 = (double)this.scaleFactorArray[1];
            color4 = color;
            scaleFactorArray3 = this.scaleFactorArray;
            int count38 = this.getCount2();
            float[] factorArray5 = scaleFactorArray3;
            Color color11 = color4;
            byte byteValue11 = 7;
            double offset22 = 1.0;
            double offset23 = 0.0;
            double offset24 = offset4;
            byte byteValue12 = 20;
            short shortValue6 = 200;
            int count39 = count16;
            int count40 = count13;
            scaleFactorArray2[1] = (float)colorSaturationProperty4.computeValueByFontFace(
               count40, count39, shortValue6, byteValue12, count, count2, offset24, offset23, offset22, byteValue11, fontFace, color11, factorArray5, count38
            );
            count33 += 25;
            scaleFactorArray2 = this.scaleFactorArray;
            ColorAlphaProperty colorAlphaProperty3 = this.colorAlphaProperty6;
            count13 = count3 + count34;
            count16 = count4 - 2 + 75;
            offset4 = (double)this.scaleFactorArray[2];
            color4 = color;
            scaleFactorArray3 = this.scaleFactorArray;
            int count41 = this.getCount2();
            float[] factorArray6 = scaleFactorArray3;
            Color color12 = color4;
            byte byteValue13 = 7;
            double offset25 = 1.0;
            double offset26 = 0.0;
            double offset27 = offset4;
            byte byteValue14 = 20;
            short shortValue7 = 200;
            int count42 = count16;
            int count43 = count13;
            scaleFactorArray2[2] = (float)colorAlphaProperty3.computeValue(count43, count42, shortValue7, byteValue14, count, count2, offset27, offset26, offset25, byteValue13, fontFace, color12, factorArray6, count41);
            count33 += 25;
            auxiliaryClientManager2 = this.auxiliaryClientManager4;
            int count44 = count3 + count34;
            count13 = count4 - 2 + 100;
            intItem4 = (double)this.getCount2();
            float[] scaleFactorArray6 = this.scaleFactorArray;
            Color color13 = color;
            byte byteValue15 = 7;
            double offset28 = 255.0;
            double offset29 = 0.0;
            double offset30 = intItem4;
            byte byteValue16 = 20;
            short shortValue8 = 200;
            int count45 = count13;
            int count46 = count44;
            int count47 = (int)auxiliaryClientManager2.computeValueByFontFace(count46, count45, shortValue8, byteValue16, count, count2, offset30, offset29, offset28, byteValue15, fontFace, color13, scaleFactorArray6);
            this.applyIndexByIndex(count47);
            count19 = 0;
            count18 = count34 + 250;
            Color color14 = new Color(Color.HSBtoRGB(this.scaleFactorArray[0], this.scaleFactorArray[1], this.scaleFactorArray[2]));
            this.color3 = new Color(color14.getRed(), color14.getGreen(), color14.getBlue(), this.getCount2());
         }

         if (this.isToggled()) {
            count18 += 300;
            count19 = 25;
         }

         count13 = count3 + count18;
         count16 = count4 + count19;
         int count48 = count6 + 50;
         int count49 = count16;
         int count50 = count13;
         this.booleanSetting3.applyFontFace(count, count2, count50, count49, count5, count48, fontFace, true);
         boolean enabled3 = this.booleanSetting3.isEnabled();
         this.setState3(enabled3);
         if (this.checkCondition() && !this.isToggled() && !this.isActive() && !this.isActiveAsFlag()) {
            float factor = (float)count18;
            String text3 = "Rainbow False";
            int count51 = (int)(factor + 50.0F + fontFace.getScaleFactor(text3));
            int count52 = 25;
            count13 = count3 + count51;
            int count53 = count4 + 25;
            int count54 = count13;
            this.numberSetting.applyFontFace(count, count2, count54, count53, count5, count6, fontFace, true);
            double value5 = this.numberSetting.getValue();
            this.updateDouble6(value5);
            count52 += 25;
            count13 = count3 + count51;
            int count55 = count4 + 50;
            int count56 = count13;
            this.numberSetting4.applyFontFace(count, count2, count56, count55, count5, count6, fontFace, true);
            double value6 = this.numberSetting4.getValue();
            this.applyDouble(value6);
            count19 = count52 + 25;
            float factor2 = (float)count51;
            String text4 = "Rainbow False";
            count18 = (int)(factor2 - (50.0F + fontFace.getScaleFactor(text4)));
         }

         if (!(this.getModule() instanceof CustomClientColor)) {
            count19 += 25;
            count13 = count3 + count18;
            count16 = count4 + count19;
            int count57 = count6 + 50;
            int count58 = count16;
            int count59 = count13;
            this.booleanSetting2.applyFontFace(count, count2, count59, count58, count5, count57, fontFace, true);
            boolean enabled4 = this.booleanSetting2.isEnabled();
            this.setState4(enabled4);
            if (this.isActiveAsFlag()) {
               count19 = 25;
               float factor3 = (float)count18;
               String text5 = "Rainbow False";
               count18 = (int)(factor3 + 50.0F + fontFace.getScaleFactor(text5));
            }

            if (this.isActiveAsFlag()) {
               count13 = count3 + count18;
               count16 = count4 + count19;
               int count60 = count6 + 50;
               int count61 = count16;
               int count62 = count13;
               this.numberSetting5.applyFontFace(count, count2, count62, count61, count5, count60, fontFace, true);
               int intItem5 = MathHelper.getInt12((int)this.numberSetting5.getValue(), 1, 255);
               this.updateInt(intItem5);
            }
         } else {
            this.setState4(false);
         }

         this.indexOffset = count18 + 200 + 20;
         RenderUtils.processAction();
      }
   }

   public ColorSetting getColorSetting6(boolean flag) {
      this.setEnabled(flag);
      return this;
   }

   private Integer getIntegerAsInteger() {
      Color color4 = this.color2;
      return this.computeCount(color4);
   }

   @Override
   public int getCount10() {
      return 127;
   }

   @Override
   public void updateState15() {
      super.updateState15();
   }

   public int getInt13() {
      return RandomUtil.processAction(this.count10);
   }

   public ColorSetting(Module module, String text, Color color4) {
      this(text, module, color4);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf(setting -> this.matchesSetting((Setting)setting));
         this.getModule().getSettings().add(this);
      }
   }

   public ColorSetting(Module module, String text, Color color4, SettingGroup settingGroup) {
      this(text, module, color4);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf(setting -> this.matchesSetting2((Setting)setting));
         settingGroup.getItemList3().add(this);
      }
   }

   private ColorSetting(String text, Module module, Color color4) {
      super(module, text);
      this.internalListenerCoordinator4 = new InternalListenerCoordinator(this, "A", 0);
      this.internalListenerCoordinator2 = new InternalListenerCoordinator(this, "FA", 0);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "R", false);
      Consumer consumer = value -> this.setState6((Boolean)value);
      this.auxiliaryStringArrayEntry3 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "F", false);
      Consumer consumer2 = value -> this.setEnabled((Boolean)value);
      this.auxiliaryStringArrayEntry2 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer2);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "AO", false);
      Consumer consumer3 = value -> this.setState3((Boolean)value);
      this.auxiliaryStringArrayEntry4 = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer3);
      auxiliaryStringArrayEntry5 = new AuxiliaryStringArrayEntry(this, "CC", false);
      Consumer consumer4 = value -> this.setState4((Boolean)value);
      this.auxiliaryStringArrayEntry = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry5.withConsumer(consumer4);
      InternalListenerCoordinator internalListenerCoordinator6 = new InternalListenerCoordinator(this, "FC", 0);
      Supplier supplier = this::getInteger;
      InternalListenerCoordinator internalListenerCoordinator7 = (InternalListenerCoordinator)internalListenerCoordinator6.withSupplier(supplier);
      Consumer consumer5 = value -> this.applyIntegerByInteger((Integer)value);
      this.internalListenerCoordinator5 = (InternalListenerCoordinator)internalListenerCoordinator7.withConsumer(consumer5);
      InternalListenerCoordinator internalListenerCoordinator8 = new InternalListenerCoordinator(this, "CCA", 100);
      Consumer consumer6 = value -> this.updateInt((Integer)value);
      this.internalListenerCoordinator3 = (InternalListenerCoordinator)internalListenerCoordinator8.withConsumer(consumer6);
      ExtendedListenerProcessor extendedListenerProcessor7 = new ExtendedListenerProcessor(this, "RS", 0.0);
      Consumer consumer7 = value -> this.updateDouble4((Double)value);
      this.extendedListenerProcessor4 = (ExtendedListenerProcessor)extendedListenerProcessor7.withConsumer(consumer7);
      ExtendedListenerProcessor extendedListenerProcessor8 = new ExtendedListenerProcessor(this, "RD", 0.0);
      Consumer consumer8 = value -> this.applyDoubleByDouble((Double)value);
      this.extendedListenerProcessor5 = (ExtendedListenerProcessor)extendedListenerProcessor8.withConsumer(consumer8);
      ExtendedListenerProcessor extendedListenerProcessor9 = new ExtendedListenerProcessor(this, "FS", 0.0);
      Consumer consumer9 = value -> this.applyDouble2((Double)value);
      this.extendedListenerProcessor6 = (ExtendedListenerProcessor)extendedListenerProcessor9.withConsumer(consumer9);
      ExtendedListenerProcessor extendedListenerProcessor10 = new ExtendedListenerProcessor(this, "FD", 0.0);
      Consumer consumer10 = value -> this.applyDouble3((Double)value);
      this.extendedListenerProcessor3 = (ExtendedListenerProcessor)extendedListenerProcessor10.withConsumer(consumer10);
      ExtendedListenerProcessor extendedListenerProcessor11 = new ExtendedListenerProcessor(this, "AS", 0.0);
      Consumer consumer11 = value -> this.updateDouble6((Double)value);
      this.extendedListenerProcessor = (ExtendedListenerProcessor)extendedListenerProcessor11.withConsumer(consumer11);
      ExtendedListenerProcessor extendedListenerProcessor12 = new ExtendedListenerProcessor(this, "AD", 0.0);
      Consumer consumer12 = value -> this.applyDouble((Double)value);
      this.extendedListenerProcessor2 = (ExtendedListenerProcessor)extendedListenerProcessor12.withConsumer(consumer12);
      InternalListenerCoordinator internalListenerCoordinator9 = new InternalListenerCoordinator(this, "C", 0);
      Supplier supplier2 = this::getIntegerAsInteger;
      InternalListenerCoordinator internalListenerCoordinator10 = (InternalListenerCoordinator)internalListenerCoordinator9.withSupplier(supplier2);
      Consumer consumer13 = value -> this.applyInteger((Integer)value);
      this.internalListenerCoordinator = (InternalListenerCoordinator)internalListenerCoordinator10.withConsumer(consumer13);
      this.scaleFactorArray5 = new float[3];
      this.scaleFactorArray = new float[3];
      this.colorHueProperty = new ColorHueProperty();
      this.colorSaturationProperty = new ColorSaturationProperty();
      this.colorAlphaProperty = new ColorAlphaProperty();
      this.auxiliaryClientManager = new ColorPickerState();
      this.booleanSetting4 = new BooleanSetting("Rainbow", false);
      this.booleanSetting = new BooleanSetting("Fade", false);
      this.booleanSetting3 = new BooleanSetting("Astolfo", false);
      this.booleanSetting2 = new BooleanSetting("ClientColor", false);
      this.numberSetting7 = new NumberSetting("RSpeed", 70.0, 1.0, 200.0, 0);
      this.numberSetting6 = new NumberSetting("FSpeed", 70.0, 1.0, 200.0, 0);
      this.numberSetting = new NumberSetting("ASpeed", 70.0, 1.0, 200.0, 0);
      this.numberSetting3 = new NumberSetting("RDist", 60.0, 0.0, 200.0, 0);
      this.numberSetting2 = new NumberSetting("FDist", 60.0, 0.0, 200.0, 0);
      this.numberSetting4 = new NumberSetting("ADist", 60.0, 0.0, 200.0, 0);
      this.numberSetting5 = new NumberSetting("ClientColorAlpha", 100.0, 1.0, 255.0, 0);
      this.colorHueProperty2 = new ColorHueProperty();
      this.colorSaturationProperty3 = new ColorSaturationProperty();
      this.colorAlphaProperty6 = new ColorAlphaProperty();
      this.auxiliaryClientManager4 = new ColorPickerState();
      this.indexOffset1 = 0;
      this.count10 = 0;
      this.applyColor(color4);
      this.applyColorByColor(color4);
      int alpha = this.color3.getAlpha();
      this.applyIndexByIndex(alpha);
      int alpha2 = this.color2.getAlpha();
      int count2 = alpha2;
      this.applyIndex2(count2);
      InternalListenerCoordinator internalListenerCoordinator11 = this.internalListenerCoordinator;
      Integer rgb = this.color2.getRGB();
      internalListenerCoordinator11.applyObject2(rgb);
      InternalListenerCoordinator internalListenerCoordinator12 = this.internalListenerCoordinator;
      Integer rgb2 = this.color2.getRGB();
      internalListenerCoordinator12.setDataRef(rgb2);
      InternalListenerCoordinator internalListenerCoordinator13 = this.internalListenerCoordinator5;
      Integer rgb3 = this.color2.getRGB();
      internalListenerCoordinator13.applyObject2(rgb3);
      InternalListenerCoordinator internalListenerCoordinator14 = this.internalListenerCoordinator5;
      Integer rgb4 = this.color2.getRGB();
      internalListenerCoordinator14.setDataRef(rgb4);
      InternalListenerCoordinator internalListenerCoordinator15 = this.internalListenerCoordinator4;
      Integer alpha3 = this.color2.getAlpha();
      internalListenerCoordinator15.setDataRef(alpha3);
      InternalListenerCoordinator internalListenerCoordinator16 = this.internalListenerCoordinator2;
      Integer alpha4 = this.color2.getAlpha();
      internalListenerCoordinator16.setDataRef(alpha4);
      Color.RGBtoHSB(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), this.scaleFactorArray5);
      Color.RGBtoHSB(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), this.scaleFactorArray);
   }
}
