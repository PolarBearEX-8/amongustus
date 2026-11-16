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
import client.util.ClientManager;
import client.util.ConsumerTask;
import client.util.SmoothPicker;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;

public class ModeSetting extends Setting {
   public int count16;
   public int count15;
   public final ConcurrentHashMap concurrentHashMap;
   public static final int count10;
   public static final Color color = new Color(185, 187, 190);
   public final SmoothPicker valueProperty;
   public String[] options;

   public String getSelected() {
      return (String)this.valueProperty.getValue();
   }

   private void updateOptions(String[] textArray) {
      this.applyTextArray(textArray);
   }

   public void applyStringArray2(String[] textArray) {
      if (Minecraft.getMinecraft().isSingleplayer()) {
         this.applyTextArray(textArray);
      } else {
         ClientManager.applyRunnable2(() -> this.updateOptions(textArray));
      }
   }

   public ModeSetting getModeSetting(ConsumerTask consumerTask) {
      this.consumerTask = consumerTask;
      return this;
   }

   public int getInt2() {
      return Arrays.asList(this.options).indexOf(this.getSelected());
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      boolean flag = false;
      if (count3 == 0 || count3 == 1) {
         int count4 = this.count5 + this.count16;
         int count5 = this.count6;
         int count6 = 0;

         for (String text : this.getOptions()) {
            Integer count7 = (Integer)this.concurrentHashMap.get(text);
            if (count7 != null) {
               if (count4 + count7 + (count6 < this.getOptions().length - 1 ? count10 : 0) > this.count3) {
                  count5 += (int)(this.fontFace.getFontSize() + 1.0F);
                  count4 = this.count5 + this.count16;
               }

               if (RenderUtils.checkCondition(
                  (float)(count4 - 6), (float)count5, (float)(count7 + 12), (float)((int)(this.fontFace.getFontSize() + 1.0F)), count, count2
               )) {
                  this.setSelected(text);
                  flag = true;
                  break;
               }

               count4 += count7;
               if (count6 < this.getOptions().length - 1) {
                  count4 += count10;
               }

               count6++;
            }
         }
      }

      return flag;
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter9().resolveJsonObject(this);
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public Setting getSetting(String text) {
      return this.getModeSetting(text);
   }

   @Override
   public void updateState() {
      String dataRef = (String)this.valueProperty.getDataRef();
      this.setSelected(dataRef);
   }

   @Override
   public void updateState2() {
      if (!this.getText4().contains("Point selection mode")) {
         String text = this.options[ThreadLocalRandom.current().nextInt(this.options.length)];
         this.setSelected(text);
      }
   }

   public ModeSetting getModeSetting(BooleanSupplier booleanSupplier) {
      this.booleanSupplier = booleanSupplier;
      return this;
   }

   public ModeSetting getModeSetting(String text) {
      this.setText4(text);
      return this;
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter9().applyPostMotionInterceptor(this, jsonObject);
   }

   public void setSelected(String selected) {
      if (!selected.equals(this.getSelected())) {
         this.setState1(true);
      }

      this.valueProperty.applyObject2(selected);
   }

   @Override
   public Setting getSetting(ConsumerTask consumerTask) {
      return this.getModeSetting(consumerTask);
   }

   @Override
   public void updateState3() {
      int count = this.count16;
      this.applyIndex(count);
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.getModeSetting(booleanSupplier);
   }

   public String[] getOptions() {
      return this.options;
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      FontFace fontFace2 = fontFace;

      try {
         fontFace2 = FontRegistry.fontFace4;
         super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace2, flag);
         this.updateState7();
         if (flag) {
            String text = this.getText4() + ": ";
            double offset = (double)count3;
            double offset2 = (double)count4;
            double offset3 = offset;
            fontFace2.applyString8(text, offset3, offset2, -4604994);
            int count7 = count3 + this.count16;
            int count8 = count4;
            int count9 = 0;

            for (String text2 : this.getOptions()) {
               try {
                  Integer count11 = (Integer)this.concurrentHashMap.get(text2);
                  if (count11 != null) {
                     if (count7 + count11 + (count9 < this.getOptions().length - 1 ? count10 : 0) > count5) {
                        count8 += (int)(fontFace2.getFontSize() + 1.0F);
                        count7 = count3 + this.count16;
                     }

                     int count12;
                     if (text2.equals(this.getSelected())) {
                        Color color2 = Client.getClient().getColor();
                        int count13 = count7 - 6;
                        int count14 = count11 + 12;
                        int count17 = (int)(fontFace2.getFontSize() + 1.0F);
                        int count18 = count14;
                        int count19 = count13;
                        double offset4 = 0.2;
                        Color color3 = color2;
                        count12 = this.computeCount(color3, offset4, count, count2, count19, count8, count18, count17);
                     } else {
                        Color color4 = color;
                        int count20 = count7 - 6;
                        int count21 = count11 + 12;
                        int count22 = (int)(fontFace2.getFontSize() + 1.0F);
                        int count23 = count21;
                        int count24 = count20;
                        double offset5 = 0.2;
                        Color color5 = color4;
                        count12 = this.computeCount(color5, offset5, count, count2, count24, count8, count23, count22);
                     }

                     int count25 = count12;
                     boolean flag2 = count9 < this.getOptions().length - 1;
                     offset = (double)count7;
                     double offset6 = (double)count8;
                     double offset7 = offset;
                     fontFace2.applyString8(text2, offset7, offset6, count25);
                     count7 += count11;
                     if (flag2) {
                        offset = (double)count7;
                        double offset8 = (double)count8;
                        double offset9 = offset;
                        String text3 = ", ";
                        fontFace2.applyString8(text3, offset9, offset8, -4604994);
                        count7 += count10;
                     }

                     if (count9 > 150) {
                        fontFace2 = FontRegistry.fontFace6;
                     }

                     count9++;
                  }
               } catch (Exception exception) {
                  String text4 = this.getString() + " " + this.getTextAsText() + " " + Arrays.toString((Object[])this.getOptions()) + " " + fontFace2;
                  throw new RuntimeException(text4, exception);
               }
            }

            this.count15 = (int)((float)count8 + fontFace2.getFontSize() + 12.0F - (float)count4);
            this.state5 = false;
            this.indexOffset = 150;
         }
      } catch (Exception exception2) {
         String text5 = this.getString() + " " + this.getTextAsText() + " " + Arrays.toString((Object[])this.getOptions()) + " " + fontFace2;
         throw new RuntimeException(text5, exception2);
      }
   }

   @Override
   public int getCount10() {
      return this.count15;
   }

   private void applyTextArray(String[] textArray) {
      this.options = textArray;
      this.concurrentHashMap.clear();

      for (String text : textArray) {
         if (text != null) {
            this.concurrentHashMap.put(text, (int)FontRegistry.fontFace4.getScaleFactor(text));
         }
      }
   }

   private void updateState7() {
      boolean flag = this.count16 == -1;
      if (this.checkFlag(flag)) {
         FontFace fontFace = FontRegistry.fontFace4;
         String text = this.getText4() + ": ";
         this.count16 = (int)fontFace.getScaleFactor(text);
      }
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public ModeSetting(Module module, String text, String text2, String[] textArray) {
      this(text, module, text2, textArray);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting2((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }

   private ModeSetting(String text, Module module, String text2, String[] textArray) {
      super(module, text);
      SmoothPicker smoothPicker = new SmoothPicker(this, "S", "");
      Consumer consumer = (item -> this.setSelected((String)item));
      this.valueProperty = (SmoothPicker)smoothPicker.withConsumer(consumer);
      this.count15 = 0;
      this.count16 = -1;
      this.concurrentHashMap = new ConcurrentHashMap();
      this.valueProperty.applyObject2(text2);
      this.valueProperty.setDataRef(text2);
      this.applyStringArray2(textArray);
   }

   public ModeSetting(Module module, String text, String text2, String[] textArray, SettingGroup settingGroup) {
      this(text, module, text2, textArray);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSetting((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }

   static {
      String text = ", ";
      count10 = (int)FontRegistry.fontFace4.getScaleFactor(text);
   }
}
