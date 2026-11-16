package client.setting;

import client.Client;
import client.ClientGlobals;
import client.event.SupplementalListener;
import client.font.FontFace;
import client.module.Module;
import client.render.RenderUtils;
import client.util.AuxiliaryModuleAdapter;
import client.util.AuxiliaryStringArrayEntry;
import client.util.BooleanSupplier;
import client.util.RayTraceUtil;
import client.util.SoundPlayer;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class SettingGroup extends Setting {
   public static final Color color = new Color(217, 156, 46);
   public int count10;
   public int indexOffset2;
   public final ArrayList arrayList;
   public final ArrayList itemList3 = new ArrayList();
   public float factor;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry;
   public static final Color color2 = new Color(166, 136, 97);

   public void setEnabled(boolean enabled) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = this.auxiliaryStringArrayEntry;
      Boolean flag = enabled;
      auxiliaryStringArrayEntry2.applyObject2(flag);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) throws IOException {
      super.checkIndex(count, count2, count3);
      boolean flag = false;
      int count4 = 20;
      if (this.isActive() && Client.getClient().getClickGuiScreen().getClickGuiPanel().settingGroup != this) {
         for (Setting setting : (Iterable<Setting>)(Iterable<?>)this.arrayList) {
            if (setting.isVisible() && setting.isState()) {
               if (this.count6 + count4 + setting.getCount10() > -20 && this.count6 + count4 < this.count7 + 20 && setting.checkIndex(count, count2, count3)) {
                  flag = true;
                  break;
               }

               count4 += setting.getCount10();
            }
         }
      }

      if (count3 == 0 || count3 == 1) {
         float factor2 = this.factor + 20.0F;
         if (RenderUtils.checkCondition((float)this.count5, (float)this.count6, factor2, this.fontFace.getFontSize(), count, count2)
            && this != Client.getClient().getClickGuiScreen().getClickGuiPanel().settingGroup) {
            if (count3 == 0) {
               boolean flag2 = !this.isActive();
               this.setEnabled(flag2);
            } else {
               Client.getClient().getClickGuiScreen().getClickGuiPanel().count11 = count + 210;
               Client.getClient().getClickGuiScreen().getClickGuiPanel().count16 = count2 + 95;
               Client.getClient().getClickGuiScreen().getClickGuiPanel().settingGroup = this;
            }

            flag = true;
         } else if (this == Client.getClient().getClickGuiScreen().getClickGuiPanel().settingGroup) {
            if (RenderUtils.checkCondition(
               (float)Client.getClient().getClickGuiScreen().getClickGuiPanel().count11,
               (float)Client.getClient().getClickGuiScreen().getClickGuiPanel().count16,
               130.0F,
               59.0F,
               count + 210,
               count2 + 95
            )) {
               int count5 = count + 210;
               int count6 = count2 + 95;
               int count7 = count5;
               this.applyIndex(count7, count6);
               flag = true;
            } else {
               Client.getClient().getClickGuiScreen().getClickGuiPanel().settingGroup = null;
            }
         }
      }

      return flag;
   }

   public boolean isActive() {
      return (Boolean)this.auxiliaryStringArrayEntry.getValue();
   }

   @Override
   public void updateState5() {
      super.updateState5();
      this.arrayList.clear();
      this.itemList3.forEach((item -> this.applySetting((Setting)item)));
      Iterator iterator = this.itemList3.iterator();

      while (iterator.hasNext()) {
         ((Setting)iterator.next()).updateState5();
      }
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return supplementalListener.getRotationFormatter10().resolveJsonObject(this);
   }

   private boolean matchesSetting(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void updateState() {
      this.auxiliaryStringArrayEntry.updateState();
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      if (this.isActive()) {
         int count4 = 20;

         for (Setting setting : (Iterable<Setting>)(Iterable<?>)this.arrayList) {
            if (setting.isVisible() && setting.isState()) {
               if (this.count6 + count4 + setting.getCount10() > -20 && this.count6 + count4 < this.count7 + 20) {
                  setting.applyIndex(count, count2, count3, value);
               }

               count4 += setting.getCount10();
            }
         }
      }
   }

   private boolean matchesSetting4(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public int getIndexOffset() {
      return this.indexOffset2;
   }

   private void applyIndex(int count, int count2) {
      if (Client.getClient().getClickGuiScreen().getClickGuiPanel().settingGroup == this) {
         int count6 = Client.getClient().getClickGuiScreen().getClickGuiPanel().count11;
         int count7 = Client.getClient().getClickGuiScreen().getClickGuiPanel().count16;
         if (RenderUtils.checkCondition((float)count6, (float)count7, 130.0F, 19.0F, count, count2)) {
            Module module = this.getModule();
            short shortValue = (short)0;
            char character = 27101;
            if (!ModuleOrCategoryNaHelper.matchesModule(shortValue, module, character, -1797410395, this)) {
               SoundPlayer.processAction(SoundPlayer.resourceLocation8);
               RayTraceUtil.applyString("Failed to import " + this.getString() + " to clipboard");
            }
         } else if (RenderUtils.checkCondition((float)count6, (float)(count7 + 21), 130.0F, 19.0F, count, count2)
            && !ModuleOrCategoryNaHelper.matchesModule(this.getModule(), this)) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation8);
            RayTraceUtil.applyString("Failed to export " + this.getString() + " to clipboard");
         }
      }
   }

   public SettingGroup getSettingGroup(BooleanSupplier booleanSupplier) {
      return (SettingGroup)super.getSetting(booleanSupplier);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
      supplementalListener.getRotationFormatter10().applyPostMotionInterceptor(this, jsonObject);
   }

   public ArrayList getItemList3() {
      return this.itemList3;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (this.isActive()) {
         int count2 = 20;

         for (Setting setting : (Iterable<Setting>)(Iterable<?>)this.arrayList) {
            if (setting.isVisible() && setting.isState()) {
               if (this.count6 + count2 + setting.getCount10() > -20 && this.count6 + count2 < this.count7 + 20) {
                  setting.applyChar(character, count);
               }

               count2 += setting.getCount10();
            }
         }
      }
   }

   private boolean matchesSetting6(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      if (this.isActive()) {
         int count4 = 20;

         for (Setting setting : (Iterable<Setting>)(Iterable<?>)this.arrayList) {
            if (setting.isVisible() && setting.isState()) {
               if (this.count6 + count4 + setting.getCount10() > -20 && this.count6 + count4 < this.count7 + 20) {
                  setting.applyIndex(count, count2, count3);
               }

               count4 += setting.getCount10();
            }
         }
      }
   }

   @Override
   public void updateState3() {
      int count = (int)(this.factor + 20.0F);
      this.applyIndex(count);
   }

   public boolean isEmpty() {
      return !this.arrayList.isEmpty();
   }

   @Override
   public int getCount10() {
      return this.count10;
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.getSettingGroup(booleanSupplier);
   }

   private void applySetting(Setting setting) {
      boolean visible = setting.isVisible();
      setting.setState(visible);
      FontFace fontFace = this.fontFace;
      setting.setFontFace(fontFace);
      if (setting.isVisible() && setting.isState() && !(setting instanceof RangeSetting) && !(setting instanceof ShaderOptions)) {
         this.arrayList.add(setting);
      }
   }

   @Override
   public void applyFontFace(int count, int count2, int count3, int count4, int count5, int count6, FontFace fontFace, boolean flag) {
      super.applyFontFace(count, count2, count3, count4, count5, count6, fontFace, flag);
      String text = this.getText4();
      boolean flag2 = this.factor == -1.0F;
      if (this.checkFlag(flag2)) {
         this.factor = (float)((int)fontFace.getScaleFactor(text));
      }

      if (flag) {
         Color color3 = color;
         int count7 = this.count5;
         int count8 = this.count6;
         int count9 = (int)(this.factor + 20.0F);
         int fontSize = (int)fontFace.getFontSize();
         int count11 = count9;
         int count12 = count8;
         int count13 = count7;
         double offset = 0.2;
         Color color4 = color3;
         int count14 = this.computeCount(color4, offset, count, count2, count13, count12, count11, fontSize);
         double offset2 = (double)count3;
         double offset3 = (double)count4;
         double offset4 = offset2;
         fontFace.applyString8(text, offset4, offset3, count14);
         if (this.isActive()) {
            RenderUtils.processAction((float)count3 + this.factor + 11.0F, (float)(count4 + 8), 10.0F, count14);
         } else {
            GL11.glPushMatrix();
            float factor2 = (float)count3 + this.factor + 11.0F;
            float factor3 = (float)(count4 + 8);
            GlStateManager.translate(factor2, factor3, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-factor2, -factor3, 0.0F);
            RenderUtils.processAction(factor2, factor3, 10.0F, count14);
            GL11.glPopMatrix();
         }
      }

      this.count10 = 20;
      if (this.isActive()) {
         for (Setting setting : (Iterable<Setting>)(Iterable<?>)this.arrayList) {
            if (setting.isVisible() && setting.isState()) {
               boolean flag3 = count4 + this.count10 + setting.getCount10() > -20 && count4 + this.count10 < count6 + 20;
               int count15 = count3 + 11;
               int count16 = count4 + this.count10;
               int count17 = count5 - 10;
               boolean flag4 = flag3 && flag;
               boolean flag5 = flag4;
               int count18 = count17;
               int count19 = count16;
               int count20 = count15;
               setting.applyFontFace(count, count2, count20, count19, count18, count6, fontFace, flag5);
               if (setting.equals(this.arrayList.get(this.arrayList.size() - 1))) {
                  RenderUtils.processDouble4(
                     (double)(count3 + 5),
                     (double)((float)count4 + fontFace.getFontSize() / 2.0F + (float)this.count10),
                     (double)(count3 + 8),
                     (double)((float)count4 + fontFace.getFontSize() / 2.0F + (float)this.count10 - 2.0F),
                     color2.getRGB()
                  );
                  RenderUtils.processDouble4(
                     (double)(count3 + 3),
                     (double)((float)count4 + fontFace.getFontSize()),
                     (double)(count3 + 5),
                     (double)((float)count4 + fontFace.getFontSize() / 2.0F + (float)this.count10),
                     color2.getRGB()
                  );
               } else {
                  RenderUtils.processDouble4(
                     (double)(count3 + 5),
                     (double)((float)count4 + fontFace.getFontSize() / 2.0F + (float)this.count10),
                     (double)(count3 + 7),
                     (double)((float)count4 + fontFace.getFontSize() / 2.0F + (float)this.count10 - 2.0F),
                     color2.getRGB()
                  );
                  RenderUtils.processDouble4(
                     (double)(count3 + 3),
                     (double)((float)count4 + fontFace.getFontSize()),
                     (double)(count3 + 5),
                     (double)((float)count4 + fontFace.getFontSize() + (float)this.count10),
                     color2.getRGB()
                  );
               }

               this.count10 = this.count10 + setting.getCount10();
               if (this.indexOffset2 < setting.getIndexOffset()) {
                  this.indexOffset2 = setting.getIndexOffset();
               }
            }
         }

         Iterator iterator = this.arrayList.iterator();

         while (iterator.hasNext()) {
            ((Setting)iterator.next()).updateState3();
         }
      } else {
         this.count10 = 27;
      }
   }

   private boolean matchesSetting2(Setting setting) {
      return setting.getString().equals(this.getString());
   }

   public SettingGroup(Module module, String text, SettingGroup settingGroup) {
      super(module, text + "Groupygroupy");
      this.count10 = 0;
      this.indexOffset2 = 0;
      this.auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry(this, "B", false);
      this.factor = -1.0F;
      this.arrayList = new ArrayList();
      this.getSetting(text);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSetting4((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }

   public SettingGroup(Module module, String text) {
      super(module, text + "Groupygroupy");
      this.count10 = 0;
      this.indexOffset2 = 0;
      this.auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry(this, "B", false);
      this.factor = -1.0F;
      this.arrayList = new ArrayList();
      this.getSetting(text);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting2((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }

   public SettingGroup(Module module, String text, boolean flag, SettingGroup settingGroup) {
      super(module, text + "Groupygroupy");
      this.count10 = 0;
      this.indexOffset2 = 0;
      this.auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry(this, "B", false);
      this.factor = -1.0F;
      this.arrayList = new ArrayList();
      this.getSetting(text);
      this.setEnabled(flag);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = this.auxiliaryStringArrayEntry;
      Boolean flag2 = flag;
      auxiliaryStringArrayEntry2.setDataRef(flag2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         settingGroup.getItemList3().removeIf((item -> this.matchesSetting6((Setting)item)));
         settingGroup.getItemList3().add(this);
      }
   }

   public SettingGroup(Module module, String text, boolean flag) {
      super(module, text + "Groupygroupy");
      this.count10 = 0;
      this.indexOffset2 = 0;
      this.auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry(this, "B", false);
      this.factor = -1.0F;
      this.arrayList = new ArrayList();
      this.getSetting(text);
      this.setEnabled(flag);
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = this.auxiliaryStringArrayEntry;
      Boolean flag2 = flag;
      auxiliaryStringArrayEntry2.setDataRef(flag2);
      if (module != null && !(module instanceof AuxiliaryModuleAdapter)) {
         ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
         this.getModule().getSettings().removeIf((item -> this.matchesSetting((Setting)item)));
         this.getModule().getSettings().add(this);
      }
   }
}
