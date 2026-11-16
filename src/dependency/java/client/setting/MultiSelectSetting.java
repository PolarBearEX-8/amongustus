package client.setting;

import client.Client;
import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.font.FontRegistry;
import client.module.Module;
import client.render.RenderUtils;
import client.util.AuxiliaryModuleAdapter;
import client.util.AuxiliaryStringArrayEntry;
import client.util.BooleanSupplier;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MultiSelectSetting extends Setting {
   public static final Color color = new Color(185, 187, 190);
   public int count14;
   public final List list2 = new ArrayList();
   public final ConcurrentHashMap concurrentHashMap;
   public final NamedOption[] namedOptionArray;
   public int count10;
   public static final int count16;
   public int count15 = -1;

   private static boolean matchesText(String text, NamedOption namedOption) {
      return namedOption.getText().equals(text);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter5().resolveJsonObject(this);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      boolean flag = false;
      if (count3 == 0 || count3 == 1) {
         String text = this.getText4() + ": ";
         int count4 = (int)((float)this.count5 + this.fontFace.getScaleFactor(text));
         int count5 = this.count6;
         int count6 = 0;

         for (NamedOption namedOption : this.namedOptionArray) {
            String text2 = namedOption.getText();
            if (!text2.isEmpty()) {
               int scaleFactor = (int)this.fontFace.getScaleFactor(text2);
               float factor = (float)(count4 + scaleFactor);
               String text3 = count6 < this.namedOptionArray.length - 1 ? ", " : "";
               if (factor + this.fontFace.getScaleFactor(text3) > (float)this.count3) {
                  count5 += (int)this.fontFace.getFontSize() + 1;
                  count4 = (int)((float)this.count5 + this.fontFace.getScaleFactor(text));
               }

               if (RenderUtils.checkCondition(
                  (float)(count4 - 6), (float)count5, (float)(scaleFactor + 12), (float)((int)(this.fontFace.getFontSize() + 1.0F)), count, count2
               )) {
                  this.count10 = namedOption.isSelected() ? 1 : 2;
                  boolean flag2 = !namedOption.isSelected();
                  namedOption.setActive(flag2);
                  this.setState1(true);
                  flag = true;
                  break;
               }

               count4 += (int)this.fontFace.getScaleFactor(text2);
               if (count6 < this.namedOptionArray.length - 1) {
                  String text4 = ", ";
                  count4 += (int)this.fontFace.getScaleFactor(text4);
               }

               count6++;
            }
         }
      }

      return flag;
   }

   public MultiSelectSetting getMultiSelectSetting(BooleanSupplier booleanSupplier) {
      this.booleanSupplier = booleanSupplier;
      return this;
   }

   private void applyNamedOptionArray(NamedOption[] namedOptionArray2, NamedOption namedOption, Boolean flag) {
      String text = namedOption.getText();
      NamedOption namedOption2 = this.resolveNamedOption(namedOptionArray2, text);
      boolean flag2 = flag;
      namedOption2.setActive(flag2);
   }

   @Override
   public void updateState() {
      Iterator iterator = this.list2.iterator();

      while (iterator.hasNext()) {
         ((AuxiliaryStringArrayEntry)iterator.next()).updateState();
      }
   }

   @Override
   public Setting getSetting(String text) {
      return this.getMultiSelectSetting(text);
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      if (this.count10 != 0) {
         if (count3 == 0 || count3 == 1) {
            String text = this.getText4() + ": ";
            int count4 = (int)((float)this.count5 + this.fontFace.getScaleFactor(text));
            int count5 = this.count6;
            int count6 = 0;

            for (NamedOption namedOption : this.namedOptionArray) {
               String text2 = namedOption.getText();
               if (!text2.isEmpty()) {
                  int scaleFactor = (int)this.fontFace.getScaleFactor(text2);
                  float factor = (float)(count4 + scaleFactor);
                  String text3 = count6 < this.namedOptionArray.length - 1 ? ", " : "";
                  if (factor + this.fontFace.getScaleFactor(text3) > (float)this.count3) {
                     count5 += (int)this.fontFace.getFontSize() + 1;
                     count4 = (int)((float)this.count5 + this.fontFace.getScaleFactor(text));
                  }

                  if (RenderUtils.checkCondition(
                        (float)(count4 - 6), (float)count5, (float)(scaleFactor + 12), (float)((int)(this.fontFace.getFontSize() + 1.0F)), count, count2
                     )
                     && namedOption.isSelected() == (this.count10 == 1)) {
                     boolean flag = !namedOption.isSelected();
                     namedOption.setActive(flag);
                     this.setState1(true);
                     break;
                  }

                  count4 += (int)this.fontFace.getScaleFactor(text2);
                  if (count6 < this.namedOptionArray.length - 1) {
                     String text4 = ", ";
                     count4 += (int)this.fontFace.getScaleFactor(text4);
                  }

                  count6++;
               }
            }
         }
      }
   }

   public NamedOption resolveNamedOption(NamedOption[] namedOptionArray2, String text) {
      return Arrays.stream(namedOptionArray2).filter(option -> MultiSelectSetting.matchesText(text, option)).findFirst().orElse(null);
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter5().applyPostMotionInterceptor(this, jsonObject);
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      this.count10 = 0;
   }

   public NamedOption[] getNamedOptionArray() {
      return this.namedOptionArray;
   }

   @Override
   public void updateState3() {
      int count = this.count15;
      this.applyIndex(count);
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.getMultiSelectSetting(booleanSupplier);
   }

   private Boolean resolveBoolean(NamedOption[] namedOptionArray2, NamedOption namedOption) {
      String text = namedOption.getText();
      return this.resolveNamedOption(namedOptionArray2, text).isSelected();
   }

   public MultiSelectSetting getMultiSelectSetting(String text) {
      this.setText4(text);
      return this;
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      FontFace fontFace2 = FontRegistry.fontFace5;
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace2, flag);
      if (flag) {
         String text = this.getText4() + ": ";
         double offset = (double)count3;
         double offset2 = (double)count4;
         double offset3 = offset;
         fontFace2.applyString8(text, offset3, offset2, -4604994);
         boolean flag2 = this.count15 == -1;
         if (this.checkFlag(flag2)) {
            this.count15 = (int)fontFace2.getScaleFactor(text);
            this.concurrentHashMap.clear();

            for (NamedOption namedOption : this.namedOptionArray) {
               String text2;
               this.concurrentHashMap.put(text2 = namedOption.getText(), (int)fontFace2.getScaleFactor(text2));
            }
         }

         int count7 = count3 + this.count15;
         int count8 = count4;
         int count9 = 0;

         for (NamedOption namedOption2 : this.namedOptionArray) {
            String text3 = namedOption2.getText();
            if (!text3.isEmpty()) {
               Integer count11 = (Integer)this.concurrentHashMap.get(text3);
               if (count11 != null) {
                  if (count7 + count11 + (count9 < this.namedOptionArray.length - 1 ? count16 : 0) > count5) {
                     count8 += (int)(fontFace2.getFontSize() + 1.0F);
                     count7 = count3 + this.count15;
                  }

                  int count12;
                  if (namedOption2.isSelected()) {
                     Color color2 = Client.getClient().getColor();
                     int count13 = count7 - 6;
                     int count17 = count11 + 12;
                     int count18 = (int)(fontFace2.getFontSize() + 1.0F);
                     int count19 = count17;
                     int count20 = count13;
                     double offset4 = 0.2;
                     Color color3 = color2;
                     count12 = this.computeCount(color3, offset4, count, count2, count20, count8, count19, count18);
                  } else {
                     Color color4 = color;
                     int count21 = count7 - 6;
                     int count22 = count11 + 12;
                     int count23 = (int)(fontFace2.getFontSize() + 1.0F);
                     int count24 = count22;
                     int count25 = count21;
                     double offset5 = 0.2;
                     Color color5 = color4;
                     count12 = this.computeCount(color5, offset5, count, count2, count25, count8, count24, count23);
                  }

                  int count26 = count12;
                  offset = (double)count7;
                  double offset6 = (double)count8;
                  double offset7 = offset;
                  fontFace2.applyString8(text3, offset7, offset6, count26);
                  count7 += count11;
                  if (count9 < this.namedOptionArray.length - 1) {
                     offset = (double)count7;
                     double offset8 = (double)count8;
                     double offset9 = offset;
                     String text4 = ", ";
                     fontFace2.applyString8(text4, offset9, offset8, -4604994);
                     count7 += count16;
                  }

                  if (count9 > 150) {
                     fontFace2 = FontRegistry.fontFace3;
                  }

                  count9++;
               }
            }
         }

         this.count14 = (int)((float)count8 + fontFace2.getFontSize() + 12.0F - (float)count4);
      }
   }

   @Override
   public int getCount10() {
      return this.count14;
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public MultiSelectSetting(Module module, String text, NamedOption[] namedOptionArray2, SettingGroup settingGroup) {
      this(text, module, namedOptionArray2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSetting2((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }

   public MultiSelectSetting(Module module, String text, NamedOption[] namedOptionArray2) {
      this(text, module, namedOptionArray2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }

   private MultiSelectSetting(String text, Module module, NamedOption[] namedOptionArray2) {
      super(module, text);
      this.concurrentHashMap = new ConcurrentHashMap();
      this.count10 = 0;
      this.count14 = 0;
      this.namedOptionArray = namedOptionArray2;
      Client.getClient().getClass();

      for (NamedOption namedOption : this.namedOptionArray) {
         List list = this.list2;
         AuxiliaryStringArrayEntry auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry(this, namedOption.getText(), namedOption.isSelected());
         Supplier supplier = () -> this.resolveBoolean(namedOptionArray2, namedOption);
         auxiliaryStringArrayEntry = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry.withSupplier(supplier);
         Consumer consumer = value -> this.applyNamedOptionArray(namedOptionArray2, namedOption, (Boolean)value);
         list.add(auxiliaryStringArrayEntry.withConsumer2(consumer));
      }
   }

   public MultiSelectSetting(String text, NamedOption[] namedOptionArray2) {
      super(text);
      this.concurrentHashMap = new ConcurrentHashMap();
      this.count10 = 0;
      this.count14 = 0;
      this.namedOptionArray = namedOptionArray2;
   }

   static {
      String text = ", ";
      count16 = (int)FontRegistry.fontFace2.getScaleFactor(text);
   }
}
