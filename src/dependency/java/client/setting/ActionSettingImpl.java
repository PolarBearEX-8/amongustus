package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.module.Module;
import client.render.RenderUtils;
import client.util.AuxiliaryModuleAdapter;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;

public class ActionSettingImpl extends Setting {
   public static final Color color = new Color(-4604994);
   public long timestamp2;
   public int count10 = -1;
   public int count11 = -1;
   public final Runnable runnable;

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      if (count3 == 0
         && RenderUtils.checkCondition(
            (float)(this.count5 + this.count11 - 8),
            (float)(this.count6 - 4),
            (float)(this.count5 + this.count10 + 16),
            this.fontFace.getFontSize() + 5.0F,
            count,
            count2
         )) {
         long timestamp = System.currentTimeMillis();
         if (timestamp - this.timestamp2 > 50L) {
            this.timestamp2 = timestamp;
            this.runnable.run();
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   @Override
   public void updateState() {
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace, flag);
      if (this.count11 == -1) {
         String text = "Action: ";
         this.count11 = (int)fontFace.getScaleFactor(text);
      }

      String text2 = this.getText4();
      boolean flag2 = this.count10 == -1;
      if (this.checkFlag(flag2)) {
         this.count10 = (int)fontFace.getScaleFactor(text2);
      }

      this.indexOffset = this.count10 + 20;
      if (flag) {
         double offset = (double)count3;
         double offset2 = (double)count4;
         double offset3 = offset;
         String text3 = "Action: ";
         fontFace.applyString8(text3, offset3, offset2, -4604994);
         offset = (double)(count3 + this.count11);
         double offset4 = (double)count4;
         Color color2 = color;
         int count7 = count3 + this.count11 - 8;
         int count8 = count4 - 4;
         int count9 = this.count10 + 16;
         int count12 = (int)(fontFace.getFontSize() + 5.0F);
         int count13 = count9;
         int count14 = count8;
         int count15 = count7;
         double offset5 = 0.2;
         Color color3 = color2;
         int count16 = this.computeCount(color3, offset5, count, count2, count15, count14, count13, count12);
         double offset6 = offset4;
         double offset7 = offset;
         fontFace.applyString8(text2, offset7, offset6, count16);
      }
   }

   public ActionSettingImpl(Module module, String text, Runnable task) {
      this(text, module, task);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }

   @Override
   public void updateState3() {
      int count = this.count10;
      this.applyIndex(count);
   }

   private boolean matchesSettingBySetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   private ActionSettingImpl(String text, Module module, Runnable task) {
      super(module, text);
      this.timestamp2 = 0L;
      this.runnable = task;
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public int getCount10() {
      return 27;
   }

   public ActionSettingImpl(Module module, String text, Runnable task, SettingGroup settingGroup) {
      this(text, module, task);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSettingBySetting((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }
}
