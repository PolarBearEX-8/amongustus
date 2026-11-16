package client.gui;

import client.Client;
import client.font.FontFace;
import client.font.FontRegistry;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.ModuleOrCategoryNaHelper;
import client.setting.Setting;
import client.util.KeyUtils;
import client.util.ManagedDoubleEntry;
import client.util.RayTraceUtil;
import client.util.SoundPlayer;
import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import org.lwjgl.opengl.Display;

public class ModuleButton {
   public FontFace fontFace;
   public final ManagedDoubleEntry managedDoubleEntry;
   public int count;
   public int count5;
   public FontFace fontFace2;
   public final ManagedDoubleEntry managedDoubleEntry2;
   public int count2;
   public boolean enabled2 = false;
   public Module module;
   public boolean enabled;
   public int count3;
   public int count4;
   public boolean enabled3 = false;

   public void applyIndexByIndex(int count6, int count7, int count8) {
      this.managedDoubleEntry2.applyIndex(count6, count7, count8);
      this.managedDoubleEntry.applyIndex(count6, count7, count8);

      for (Setting setting : (Iterable<Setting>)(Iterable)(this.module.getSettings())) {
         if (setting.isVisible() && setting.isState()) {
            setting.applyIndex(count6, count7, count8);
         }
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void applyChar(char character, int count6) throws IOException {
      this.module.processChar2(character, count6);
      if (this.module != null && KeyUtils.checkIndex(count6) && Display.isActive()) {
         switch (count6) {
            case 45:
            case 46:
               if (!ModuleOrCategoryNaHelper.matchesModule2(this.module)) {
                  SoundPlayer.processAction(SoundPlayer.resourceLocation8);
                  RayTraceUtil.applyString("Failed to export " + this.module.getString() + " to clipboard");
               }
               break;
            case 47:
               boolean flag = ModuleOrCategoryNaHelper.matchesModule(this.module);
               if (!flag) {
                  flag = ModuleOrCategoryNaHelper.matchesModuleByModule(this.module);
               }

               if (!flag) {
                  SoundPlayer.processAction(SoundPlayer.resourceLocation8);
                  RayTraceUtil.applyString("Failed to import " + this.module.getString() + " to clipboard");
               }
         }
      }

      Iterator iterator = this.module.getSettings().iterator();

      while (iterator.hasNext()) {
         ((Setting)iterator.next()).applyChar(character, count6);
      }
   }

   public void applyIndex(int count6, int count7, int count8) throws IOException {
      this.managedDoubleEntry2.applyIndexByIndex(count6, count7, count8);
      this.managedDoubleEntry.applyIndexByIndex(count6, count7, count8);
   }

   public void applyModuleByModule(int count6, int count7, int count8, int count9, int count10, int count11, int count12, Module module2, boolean flag) {
      long value = 0x47ad320f6584L;
      long keySalt = 0x5419097d983aL ^ value;
      long keySalt2 = 0x5419097d983aL ^ value ^ 0x491b6f0e79daL;
      int count13 = (int)((keySalt ^ 0x491b6f0e79daL) >>> 32);
      int count14 = (int)(keySalt2 << 32 >>> 48);
      int count15 = (int)(keySalt2 << 48 >>> 48);
      if (module2 != null) {
         this.module = module2;
      }

      this.count5 = count10;
      this.count2 = count11;
      this.count3 = count8;
      this.count4 = count9;
      this.count = count12;
      FontFace fontFace3 = this.fontFace;
      String text = module2.getDescription() + ":";
      int rgb = Client.getClient().getColor().getRGB();
      double offset = 6.0;
      double offset2 = 0.0;
      String text2 = text;
      fontFace3.applyString8(text2, offset2, offset, rgb);
      FontFace fontFace4 = this.fontFace2;
      double offset3 = (double)(count8 - 60);
      int count16 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count6, count7, count8 - 60, 4, 40, 17);
      double offset4 = 4.0;
      double offset5 = offset3;
      String text3 = "Reset";
      fontFace4.applyString8(text3, offset5, offset4, count16);
      int count17 = (int)(this.fontFace.getFontSize() + 10.0F);
      int count18 = (char)count14;
      char character = (char)count15;
      FontFace fontFace5 = this.fontFace2;
      char character2 = character;
      char character3 = (char)count18;
      int count19 = count17;
      byte byteValue = 0;
      int index = module2.getIndex(byteValue, count19, count13, character3, count6, count7, character2, fontFace5, flag);
      int count20 = (int)(this.fontFace.getFontSize() + 7.0F + this.fontFace2.getFontSize() + 9.0F);
      RenderUtils.processFloat4((float)this.count5, (float)(this.count2 + count20 - 5), (float)(this.count5 + count8), (float)(this.count2 + this.count4), 1.0);
      int count21 = (int)(this.fontFace.getFontSize() + 7.0F + this.fontFace2.getFontSize() + 9.0F);

      for (Setting setting : (Iterable<Setting>)(Iterable)(module2.getSettings())) {
         boolean visible = setting.isVisible();
         setting.setState(visible);
         if (setting.isVisible() && setting.isState()) {
            int count22 = count21 + module2.getIndexOffset1();
            boolean flag2 = count22 + setting.getCount10() > -20 && count22 < count9 + 20;
            int indexOffset = module2.getIndexOffset2();
            count18 = count21 + module2.getIndexOffset1();
            FontFace fontFace6 = this.fontFace2;
            int count23 = count18;
            int count24 = indexOffset;
            setting.applyFontFace(count6, count7, count24, count23, count8, count9, fontFace6, flag2);
            count21 += setting.getCount10();
            if (index < setting.getIndexOffset()) {
               index = setting.getIndexOffset();
            }
         }
      }

      for (Setting setting2 : (Iterable<Setting>)(Iterable)(module2.getSettings())) {
         if (setting2.isVisible() && setting2.isState()) {
            setting2.updateState3();
         }
      }

      Iterator iterator = module2.getSettings().iterator();

      while (true) {
         if (iterator.hasNext()) {
            Setting setting3 = (Setting)iterator.next();
            if (!setting3.isVisible() || !setting3.isState5()) {
               continue;
            }

            this.enabled = true;
            break;
         }

         this.enabled = false;
         break;
      }

      RenderUtils.processFloat4((float)this.count5, (float)(this.count2 + count20 - 5), (float)(this.count5 + count8), (float)(this.count2 + this.count4), 1.0);
      ManagedDoubleEntry managedDoubleEntry3 = this.managedDoubleEntry2;
      int count25 = count8 - 10;
      int count26 = count20 - module2.getIndexOffset1();
      Color color = new Color(32, 34, 37);
      int count27 = count26;
      int count28 = count25;
      this.enabled3 = managedDoubleEntry3.matchesColor(count28, count20, count8, count9, count27, count21, color);
      ManagedDoubleEntry managedDoubleEntry4 = this.managedDoubleEntry;
      int count29 = count9 - 10;
      count26 = -module2.getIndexOffset2();
      Color color2 = new Color(32, 34, 37);
      int count30 = count26;
      int count31 = count29;
      byte byteValue2 = 0;
      this.enabled2 = managedDoubleEntry4.matchesColorByColor(byteValue2, count31, count8, count9, count30, index, color2);
      if (this.enabled2) {
         ManagedDoubleEntry managedDoubleEntry5 = this.managedDoubleEntry2;
         int indexOffset2 = module2.getIndexOffset1();
         int count32 = count9 - 50;
         byte byteValue3 = 0;
         byte byteValue4 = 0;
         int count33 = indexOffset2;
         int count34 = managedDoubleEntry5.computeCount(count21, count20, count9, count33, byteValue4, byteValue3, count8, count32, count6, count7);
         module2.setIndexOffset1(count34);
      } else {
         ManagedDoubleEntry managedDoubleEntry6 = this.managedDoubleEntry2;
         int indexOffset3 = module2.getIndexOffset1();
         int count35 = managedDoubleEntry6.computeCount(count21, count20, count9, indexOffset3, 0, 0, count8, count9, count6, count7);
         module2.setIndexOffset1(count35);
      }

      if (this.enabled3) {
         ManagedDoubleEntry managedDoubleEntry7 = this.managedDoubleEntry;
         int indexOffset4 = module2.getIndexOffset2();
         int count36 = count9 - 50;
         byte byteValue5 = 0;
         int count37 = indexOffset4;
         byte byteValue6 = 0;
         int count38 = managedDoubleEntry7.computeCount(index, byteValue6, count8, count37, byteValue5, count36, count8, count9, count6, count7);
         module2.setIndexOffset2(count38);
      } else {
         ManagedDoubleEntry managedDoubleEntry8 = this.managedDoubleEntry;
         int indexOffset5 = module2.getIndexOffset2();
         byte byteValue7 = 0;
         int count39 = managedDoubleEntry8.computeCount(index, byteValue7, count8, indexOffset5, 0, 0, count8, count9, count6, count7);
         module2.setIndexOffset2(count39);
      }
   }

   public void applyIndex(int count6, int count7, int count8, long value) {
      for (Setting setting : (Iterable<Setting>)(Iterable)(this.module.getSettings())) {
         if (setting.isVisible() && setting.isState()) {
            setting.applyIndex(count6, count7, count8, value);
         }
      }
   }

   public void renderElement() {
      for (Setting setting : (Iterable<Setting>)(Iterable)(this.module.getSettings())) {
         if (setting.isVisible() && setting.isState()) {
            setting.updateState4();
         }
      }
   }

   public void applyIndex2(int count6, int count7, int count8) throws IOException {
      if (RenderUtils.checkCondition((float)(this.count3 - 60), 4.0F, 40.0F, 17.0F, count6, count7)) {
         for (Setting setting : (Iterable<Setting>)(Iterable)(Client.getClient().getAuxiliaryCopyOnWriteArrayLis().getList())) {
            if (setting.getModule() == this.module) {
               setting.updateState();
            }
         }
      } else {
         this.module.processAction(count6, count7, count8);
         int count9 = (int)(this.fontFace.getFontSize() + 7.0F + this.fontFace2.getFontSize() + 9.0F);

         for (Setting setting2 : (Iterable<Setting>)(Iterable)(this.module.getSettings())) {
            boolean visible = setting2.isVisible();
            setting2.setState(visible);
            if (setting2.isVisible() && setting2.isState()) {
               int count10 = count9 + this.module.getIndexOffset1();
               if (count10 + setting2.getCount10() > -20 && count10 < this.count4 + 20 && setting2.checkIndex(count6, count7, count8)) {
                  break;
               }

               count9 += setting2.getCount10();
            }
         }
      }
   }

   public ModuleButton() {
      this.module = Client.getClient().getModuleManager().velocity;
      this.enabled = false;
      this.fontFace2 = FontRegistry.fontFace2;
      this.fontFace = FontRegistry.fontFace10;
      this.managedDoubleEntry2 = new ManagedDoubleEntry();
      this.managedDoubleEntry = new ManagedDoubleEntry();
   }
}
