package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.module.Module;
import client.util.AuxiliaryModuleAdapter;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.util.ExtendedListenerProcessor;
import client.util.PlayerUtil;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.function.Consumer;
import net.minecraft.util.MathHelper;

public class ActionSetting extends Setting {
   public NumberSetting settingMin;
   public int count10;
   public Runnable runnable;
   public boolean enabled2;
   public boolean enabled3;
   public final ExtendedListenerProcessor extendedListenerProcessor2;
   public final ExtendedListenerProcessor extendedListenerProcessor;
   public NumberSetting settingMax;
   public boolean enabled4;

   public double getValueAsDouble() {
      return this.settingMin.getCoordOffset();
   }

   private boolean matchesSetting4(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   public int getCount() {
      return this.settingMin.getIndexOffset2();
   }

   public double getValue() {
      return this.settingMin.getCoordOffset1();
   }

   public double getDouble3() {
      return this.getDouble5(false);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter4().resolveJsonObject(this);
   }

   @Override
   public void updateState5() {
      super.updateState5();
      this.settingMin.updateState5();
      this.settingMax.updateState5();
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      this.settingMin.checkIndex(count, count2, count3);
      this.settingMax.checkIndex(count, count2, count3);
      return false;
   }

   public void applyIndexByIndex(int count) {
      this.settingMax.setIndexOffset2(count);
      this.settingMin.setIndexOffset2(count);
   }

   @Override
   public void updateState() {
      this.extendedListenerProcessor2.updateState();
      this.extendedListenerProcessor.updateState();
      double value = (Double)this.extendedListenerProcessor2.getValue();
      this.applyDoubleByDouble(value);
      double value2 = (Double)this.extendedListenerProcessor.getValue();
      this.applyDouble(value2);
   }

   public double getDouble4(boolean flag) {
      return flag
         ? MathHelper.getDouble7((Double)this.extendedListenerProcessor.getValue(), this.settingMax.getCoordOffset(), this.settingMax.getCoordOffset1())
         : (Double)this.extendedListenerProcessor.getValue();
   }

   @Override
   public Setting getSetting(String text) {
      return this.getActionSetting(text);
   }

   public Runnable getRunnable() {
      return this.runnable;
   }

   public ActionSetting resolveActionSetting(ConsumerTask consumerTask) {
      this.consumerTask = consumerTask;
      this.settingMin = (NumberSetting)this.settingMin.getSetting(consumerTask);
      this.settingMax = (NumberSetting)this.settingMax.getSetting(consumerTask);
      return this;
   }

   public ActionSetting getActionSetting() {
      this.enabled2 = true;
      return this;
   }

   public void applyDouble(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor3 = this.extendedListenerProcessor;
      Double offset2 = offset;
      extendedListenerProcessor3.applyObject2(offset2);
      this.settingMax.applyDouble(offset);
   }

   @Override
   public String[] getTextArray() {
      return new String[]{this.settingMin.getString(), this.settingMax.getString()};
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      this.settingMin.applyIndex(count, count2, count3, value);
      this.settingMax.applyIndex(count, count2, count3, value);
   }

   @Override
   public void updateState2() {
      double doubleItem = PlayerUtil.getDouble(this.settingMin.getCoordOffset(), this.settingMin.getCoordOffset1(), Math.random());
      this.applyDoubleByDouble(doubleItem);
      double doubleItem2 = PlayerUtil.getDouble((Double)this.extendedListenerProcessor2.getValue(), this.settingMax.getCoordOffset1(), Math.random());
      this.applyDouble(doubleItem2);
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   public ActionSetting getActionSetting(String text, String text2) {
      this.settingMin.setText4(text);
      this.settingMax.setText4(text2);
      return this;
   }

   public ActionSetting withRunnable(Runnable task) {
      this.runnable = task;
      return this;
   }

   public ActionSetting getActionSetting(BooleanSupplier booleanSupplier) {
      this.setBooleanSupplier(booleanSupplier);
      return this;
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter4().applyPostMotionInterceptor(this, jsonObject);
   }

   @Override
   public void applyText(String text, String text2) {
      if (text2.equals("English_Default")) {
         this.settingMin.setState2(true);
         this.settingMax.setState2(true);
         String text3 = "";
         this.settingMin.setText3(text3);
         String text4 = "";
         this.settingMax.setText3(text4);
      } else {
         if (this.settingMin.getString().equalsIgnoreCase(text)) {
            this.settingMin.setState2(true);
            this.settingMin.setText3(text2);
         } else if (this.settingMax.getString().equalsIgnoreCase(text)) {
            this.settingMax.setState2(true);
            this.settingMax.setText3(text2);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      this.settingMin.applyIndex(count, count2, count3);
      this.settingMax.applyIndex(count, count2, count3);
   }

   public void applyDoubleByDouble(double offset) {
      ExtendedListenerProcessor extendedListenerProcessor3 = this.extendedListenerProcessor2;
      Double offset2 = offset;
      extendedListenerProcessor3.applyObject2(offset2);
      this.settingMin.applyDouble(offset);
   }

   @Override
   public Setting getSetting(ConsumerTask consumerTask) {
      return this.resolveActionSetting(consumerTask);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.settingMin.applyChar(character, count);
      this.settingMax.applyChar(character, count);
   }

   @Override
   public void updateState3() {
      int count = this.count10;
      this.applyIndex(count);
   }

   public ActionSetting getActionSetting(String text) {
      short shortValue = 26749;
      short shortValue2 = 18450;
      char character = '\ufdb7';
      String text2 = "Min" + text;
      String text3 = "Max" + text;
      String text4 = text2;
      return this.getActionSetting(text4, text3);
   }

   private boolean matchesSetting6(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public int getCount10() {
      return this.settingMin.getCount10() + this.settingMax.getCount10();
   }

   public double getDouble5(boolean flag) {
      return flag
         ? MathHelper.getDouble7((Double)this.extendedListenerProcessor2.getValue(), this.settingMin.getCoordOffset(), this.settingMin.getCoordOffset1())
         : (Double)this.extendedListenerProcessor2.getValue();
   }

   public double getDouble6() {
      return this.getDouble4(false);
   }

   public void applyRunnable(Runnable task) {
      this.extendedListenerProcessor2.add(task);
      this.extendedListenerProcessor.add(task);
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      super.applyFontFace(count, count2, count3, count4, count5, count6 * 2, fontFace, flag);
      boolean flag2 = this.count10 == -1;
      if (this.checkFlag(flag2)) {
         String text = this.settingMin.getText4() + ": ";
         this.count10 = (int)fontFace.getScaleFactor(text);
      }

      if (this.enabled2) {
         this.settingMax.applyFontFace(count, count2, count3, count4, count5, count6, fontFace, flag);
         double value = this.settingMax.getValue();
         this.applyDouble(value);
         NumberSetting settingMin2 = this.settingMin;
         int count7 = count4 + this.settingMax.getCount10();
         settingMin2.applyFontFace(count, count2, count3, count7, count5, count6, fontFace, flag);
         double value2 = this.settingMin.getValue();
         this.applyDoubleByDouble(value2);
      } else {
         this.settingMin.applyFontFace(count, count2, count3, count4, count5, count6, fontFace, flag);
         double value3 = this.settingMin.getValue();
         this.applyDoubleByDouble(value3);
         NumberSetting settingMax2 = this.settingMax;
         int count8 = count4 + this.settingMin.getCount10();
         settingMax2.applyFontFace(count, count2, count3, count8, count5, count6, fontFace, flag);
         double value4 = this.settingMax.getValue();
         this.applyDouble(value4);
      }

      if (this.getDouble3() > this.getDouble6()) {
         if (this.settingMin.getAuxiliaryFontFaceEntryAdapter().isEnabled()
            || this.enabled4 && !this.settingMin.getFontFaceEntryAdapter().isEnabled()
            || this.settingMin.isState3()
            || this.settingMin.isState4()) {
            NumberSetting settingMax3 = this.settingMax;
            double value5 = this.settingMin.getValue();
            settingMax3.applyDouble(value5);
            double value6 = this.settingMax.getValue();
            this.applyDouble(value6);
         }

         if (this.settingMax.getAuxiliaryFontFaceEntryAdapter().isEnabled()
            || this.enabled3 && !this.settingMax.getFontFaceEntryAdapter().isEnabled()
            || this.settingMax.isState3()
            || this.settingMax.isState4()) {
            NumberSetting settingMin3 = this.settingMin;
            double value7 = this.settingMax.getValue();
            settingMin3.applyDouble(value7);
            double value8 = this.settingMin.getValue();
            this.applyDoubleByDouble(value8);
         }
      }

      this.enabled4 = this.settingMin.getFontFaceEntryAdapter().isEnabled();
      this.enabled3 = this.settingMax.getFontFaceEntryAdapter().isEnabled();
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.getActionSetting(booleanSupplier);
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public ActionSetting(Module module, String text, String text2, double posX, double posX2, double posY, double posZ, int count) {
      super(module, text + text2);
      ExtendedListenerProcessor extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "F", 0.0);
      Consumer consumer = value -> this.applyDoubleByDouble((Double)value);
      this.extendedListenerProcessor2 = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer);
      extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "S", 0.0);
      Consumer consumer2 = value -> this.applyDouble((Double)value);
      this.extendedListenerProcessor = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer2);
      this.enabled2 = false;
      this.count10 = -1;
      this.runnable = null;
      this.enabled4 = false;
      this.enabled3 = false;
      ExtendedListenerProcessor extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset = posX;
      extendedListenerProcessor4.applyObject2(offset);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset2 = posX2;
      extendedListenerProcessor4.applyObject2(offset2);
      extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset3 = posX;
      extendedListenerProcessor4.setDataRef(offset3);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset4 = posX2;
      extendedListenerProcessor4.setDataRef(offset4);
      NumberSetting setting = new NumberSetting(text, posX, posY, posZ, count);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingMin = setting.getNumberSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting(text2, posX2, posY, posZ, count);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingMax = setting2.getNumberSetting(booleanSupplier2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf(item -> this.matchesSetting4((Setting)item));
         this.getModule().getSettings().add(this);
      }
   }

   public ActionSetting(Module module, String text, double posX, double posX2, double posY, double posZ, int count) {
      super(module, text);
      ExtendedListenerProcessor extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "F", 0.0);
      Consumer consumer = value -> this.applyDoubleByDouble((Double)value);
      this.extendedListenerProcessor2 = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer);
      extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "S", 0.0);
      Consumer consumer2 = value -> this.applyDouble((Double)value);
      this.extendedListenerProcessor = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer2);
      this.enabled2 = false;
      this.count10 = -1;
      this.runnable = null;
      this.enabled4 = false;
      this.enabled3 = false;
      ExtendedListenerProcessor extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset = posX;
      extendedListenerProcessor4.applyObject2(offset);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset2 = posX2;
      extendedListenerProcessor4.applyObject2(offset2);
      extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset3 = posX;
      extendedListenerProcessor4.setDataRef(offset3);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset4 = posX2;
      extendedListenerProcessor4.setDataRef(offset4);
      NumberSetting setting = new NumberSetting("Min" + text, posX, posY, posZ, count);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingMin = setting.getNumberSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting("Max" + text, posX2, posY, posZ, count);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingMax = setting2.getNumberSetting(booleanSupplier2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf(item -> this.matchesSetting((Setting)item));
         this.getModule().getSettings().add(this);
      }
   }

   public ActionSetting(Module module, String text, String text2, double posX, double posX2, double posY, double posZ, int count, SettingGroup settingGroup) {
      super(module, text + text2);
      ExtendedListenerProcessor extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "F", 0.0);
      Consumer consumer = value -> this.applyDoubleByDouble((Double)value);
      this.extendedListenerProcessor2 = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer);
      extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "S", 0.0);
      Consumer consumer2 = value -> this.applyDouble((Double)value);
      this.extendedListenerProcessor = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer2);
      this.enabled2 = false;
      this.count10 = -1;
      this.runnable = null;
      this.enabled4 = false;
      this.enabled3 = false;
      ExtendedListenerProcessor extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset = posX;
      extendedListenerProcessor4.applyObject2(offset);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset2 = posX2;
      extendedListenerProcessor4.applyObject2(offset2);
      extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset3 = posX;
      extendedListenerProcessor4.setDataRef(offset3);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset4 = posX2;
      extendedListenerProcessor4.setDataRef(offset4);
      NumberSetting setting = new NumberSetting(text, posX, posY, posZ, count);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingMin = setting.getNumberSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting(text2, posX2, posY, posZ, count);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingMax = setting2.getNumberSetting(booleanSupplier2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf(item -> this.matchesSetting2((Setting)item));
         settingGroup.getItemList3().add(this);
      }
   }

   public ActionSetting(Module module, String text, double posX, double posX2, double posY, double posZ, int count, SettingGroup settingGroup) {
      super(module, text);
      ExtendedListenerProcessor extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "F", 0.0);
      Consumer consumer = value -> this.applyDoubleByDouble((Double)value);
      this.extendedListenerProcessor2 = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer);
      extendedListenerProcessor3 = new ExtendedListenerProcessor(this, "S", 0.0);
      Consumer consumer2 = value -> this.applyDouble((Double)value);
      this.extendedListenerProcessor = (ExtendedListenerProcessor)extendedListenerProcessor3.withConsumer(consumer2);
      this.enabled2 = false;
      this.count10 = -1;
      this.runnable = null;
      this.enabled4 = false;
      this.enabled3 = false;
      ExtendedListenerProcessor extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset = posX;
      extendedListenerProcessor4.applyObject2(offset);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset2 = posX2;
      extendedListenerProcessor4.applyObject2(offset2);
      extendedListenerProcessor4 = this.extendedListenerProcessor2;
      Double offset3 = posX;
      extendedListenerProcessor4.setDataRef(offset3);
      extendedListenerProcessor4 = this.extendedListenerProcessor;
      Double offset4 = posX2;
      extendedListenerProcessor4.setDataRef(offset4);
      NumberSetting setting = new NumberSetting("Min" + text, posX, posY, posZ, count);
      BooleanSupplier booleanSupplier = this::isVisible;
      this.settingMin = setting.getNumberSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting("Max" + text, posX2, posY, posZ, count);
      BooleanSupplier booleanSupplier2 = this::isVisible;
      this.settingMax = setting2.getNumberSetting(booleanSupplier2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf(item -> this.matchesSetting6((Setting)item));
         settingGroup.getItemList3().add(this);
      }
   }
}
