package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.font.FontRegistry;
import client.module.Module;
import client.render.RenderUtils;
import client.util.AuxiliaryModuleAdapter;
import client.util.AuxiliaryStringArrayEntry;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class BooleanSetting extends Setting {
   public int count10;
   public static final Color color2 = new Color(214, 0, 0);
   public static final Color color = new Color(0, 214, 0);
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry;

   public void setEnabled(boolean enabled) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = this.auxiliaryStringArrayEntry;
      Boolean flag = enabled;
      auxiliaryStringArrayEntry2.setDataRef(flag);
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter7().resolveJsonObject(this);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      if (count3 == 0
         && RenderUtils.checkCondition(
            (float)(this.count5 + this.count10 - 8), (float)(this.count6 - 4), 60.0F, (float)((int)this.fontFace.getFontSize() + 5), count, count2
         )) {
         boolean flag = !this.isEnabled();
         this.setState3(flag);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateState() {
      boolean dataRef = (Boolean)this.auxiliaryStringArrayEntry.getDataRef();
      this.setState3(dataRef);
   }

   @Override
   public void updateState2() {
      boolean flag = ThreadLocalRandom.current().nextBoolean();
      this.setState3(flag);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter7().applyPostMotionInterceptor(this, jsonObject);
   }

   public boolean isEnabled() {
      return (Boolean)this.auxiliaryStringArrayEntry.getValue();
   }

   public void setState3(boolean state) {
      if (this.isEnabled() != state) {
         this.setState1(true);
      }

      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = this.auxiliaryStringArrayEntry;
      Boolean flag = state;
      auxiliaryStringArrayEntry2.applyObject2(flag);
   }

   @Override
   public void updateState3() {
      int count = this.count10;
      this.applyIndex(count);
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      FontFace fontFace2 = FontRegistry.fontFace7;
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace2, flag);
      String text = this.getText4() + ": ";
      boolean flag2 = this.count10 == -1;
      if (this.checkFlag(flag2)) {
         this.count10 = (int)fontFace2.getScaleFactor(text);
      }

      if (flag) {
         double offset = (double)count3;
         double offset2 = (double)count4;
         double offset3 = offset;
         fontFace2.applyString8(text, offset3, offset2, -4604994);
         String text2 = String.valueOf(this.isEnabled());
         float scaleFactor = fontFace2.getScaleFactor(text2);
         offset = (double)(count3 + this.count10);
         double offset4 = (double)count4;
         Color color3 = this.isEnabled() ? color : color2;
         int count7 = count3 + this.count10 - 8;
         int count8 = count4 - 4;
         int count9 = (int)(fontFace2.getFontSize() + 5.0F);
         byte byteValue = 60;
         int count11 = count8;
         int count12 = count7;
         double offset5 = 0.2;
         Color color4 = color3;
         int count13 = this.computeCount(color4, offset5, count, count2, count12, count11, byteValue, count9);
         double offset6 = offset4;
         double offset7 = offset;
         fontFace2.applyString8(text2, offset7, offset6, count13);
         this.indexOffset = (int)((float)this.count10 + scaleFactor + 20.0F);
      }
   }

   @Override
   public int getCount10() {
      return 27;
   }

   public BooleanSetting(Module module, String text, boolean flag) {
      this(text, module, flag);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting2((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }

   private BooleanSetting(String text, Module module, boolean flag) {
      super(module, text);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = new AuxiliaryStringArrayEntry(this, "B", false);
      Consumer consumer = (item -> this.setState3((Boolean)item));
      this.auxiliaryStringArrayEntry = (AuxiliaryStringArrayEntry)auxiliaryStringArrayEntry2.withConsumer(consumer);
      this.count10 = -1;
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry3 = this.auxiliaryStringArrayEntry;
      Boolean flag2 = flag;
      auxiliaryStringArrayEntry3.applyObject2(flag2);
      auxiliaryStringArrayEntry3 = this.auxiliaryStringArrayEntry;
      Boolean flag3 = flag;
      auxiliaryStringArrayEntry3.setDataRef(flag3);
   }

   public BooleanSetting(String text, boolean flag) {
      this(text, null, flag);
   }

   public BooleanSetting(Module module, String text, boolean flag, SettingGroup settingGroup) {
      this(text, module, flag);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSetting((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }
}
