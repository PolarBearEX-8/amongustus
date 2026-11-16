package client.gui;

import augustus.modules.misc.ClientAntiCheat;
import client.Client;
import client.ClientGlobals;
import client.font.FontFace;
import client.font.FontRegistry;
import client.module.ActionModule;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.module.SubModule;
import client.render.RenderUtils;
import client.setting.ModuleOrCategoryNaHelper;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.AuxiliaryNoneManager;
import client.util.ManagedDoubleEntry;
import client.util.MinecraftAccessor;
import client.util.RayTraceUtil;
import client.util.SmoothPicker;
import client.util.SoundPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemCallback;
import org.lwjgl.opengl.GL11;

public class ClickGuiPanel extends PanelWindow {
   public final SmoothPicker smoothPicker2;
   public ClickGuiCategoryPanel categoryEntry;
   public int count12;
   public boolean state;
   public int count16;
   public SettingGroup settingGroup;
   public ModuleButton fontFaceEntry;
   public ManagedDoubleEntry managedDoubleEntry;
   public int count13;
   public boolean enabled5;
   public Module module;
   public final SmoothPicker smoothPicker;
   public Module module3;
   public ManagedDoubleEntry managedDoubleEntry2;
   public ClickGuiCategoryPanel clickGuiCategoryPanel;
   public final TimeHelper timer;
   public int count11;
   public int count17;
   public int count15;
   public FontFace fontFace2;
   public Module module2;
   public final ArrayList itemList2 = new ArrayList();

   public boolean isState() {
      return this.state;
   }

   public ArrayList getItemList2() {
      return this.itemList2;
   }

   private void applyIndex4(int count, int count2) {
      if (this.clickGuiCategoryPanel != null) {
         if (RenderUtils.checkCondition((float)this.count11, (float)this.count16, 130.0F, 19.0F, count, count2)) {
            Category categoryAsCategory = this.clickGuiCategoryPanel.getCategoryAsCategory();
            boolean flag = ModuleOrCategoryNaHelper.matchesCategory2(categoryAsCategory);
            if (!flag) {
               flag = ModuleOrCategoryNaHelper.matchesCategory(this.clickGuiCategoryPanel.getCategoryAsCategory());
            }

            if (!flag) {
               SoundPlayer.processAction(SoundPlayer.resourceLocation8);
               RayTraceUtil.applyString("Failed to import " + categoryAsCategory.name() + " to clipboard");
            }
         } else if (RenderUtils.checkCondition((float)this.count11, (float)(this.count16 + 21), 130.0F, 19.0F, count, count2)) {
            if (!ModuleOrCategoryNaHelper.matchesCategoryByCategory(this.clickGuiCategoryPanel.getCategoryAsCategory())) {
               SoundPlayer.processAction(SoundPlayer.resourceLocation8);
               RayTraceUtil.applyString("Failed to export " + this.clickGuiCategoryPanel.getCategoryAsCategory().name() + " to clipboard");
            }
         } else if (RenderUtils.checkCondition((float)this.count11, (float)(this.count16 + 40), 130.0F, 19.0F, count, count2) && this.categoryEntry != null) {
            int byteValue = 0;
            ModuleManager moduleManager = ClientGlobals.moduleManager;
            Category categoryAsCategory2 = this.categoryEntry.getCategoryAsCategory();
            List list = moduleManager.getList(categoryAsCategory2);

            for (Module module4 : (Iterable<Module>)(Iterable<?>)list) {
               byteValue += module4.canExecute4() ? 1 : 0;
            }

            boolean flag2 = (double)byteValue / (double)list.size() < 0.5;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
               ((Module)iterator.next()).setState4(flag2);
            }
         } else if (RenderUtils.checkCondition((float)this.count11, (float)(this.count16 + 59), 130.0F, 19.0F, count, count2) && this.categoryEntry != null) {
            int byteValue2 = 0;
            ModuleManager moduleManager2 = ClientGlobals.moduleManager;
            Category categoryAsCategory3 = this.categoryEntry.getCategoryAsCategory();
            List list2 = moduleManager2.getList(categoryAsCategory3);

            for (Module module5 : (Iterable<Module>)(Iterable<?>)list2) {
               byteValue2 += module5.canExecute2() ? 1 : 0;
            }

            boolean flag3 = (double)byteValue2 / (double)list2.size() < 0.5;
            Iterator iterator2 = list2.iterator();

            while (iterator2.hasNext()) {
               ((Module)iterator2.next()).setState(flag3);
            }
         }
      }
   }

   private void applyIndex2(int count, int count2) {
      RenderUtils.processAction(this.count11, this.count16, this.count11 + 130, this.count16 + 59, 40, 39, 3);
      RenderUtils.processAction((double)this.count11, (double)this.count16, (double)(this.count11 + 130), (double)(this.count16 + 59), 1.0F, Color.BLACK);
      FontFace fontFace = FontRegistry.fontFace2;
      double offset = (double)(this.count11 + 2);
      double offset2 = (double)(this.count16 + 2);
      int count3 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16, 130, 19);
      double offset3 = offset2;
      double offset4 = offset;
      String text = "Import Settings";
      fontFace.applyString8(text, offset4, offset3, count3);
      fontFace = FontRegistry.fontFace2;
      offset = (double)(this.count11 + 2);
      offset2 = (double)(this.count16 + 2 + 19);
      int count4 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16 + 21, 130, 19);
      double offset5 = offset2;
      double offset6 = offset;
      String text2 = "Export Settings";
      fontFace.applyString8(text2, offset6, offset5, count4);
      fontFace = FontRegistry.fontFace2;
      offset = (double)(this.count11 + 2);
      offset2 = (double)(this.count16 + 2 + 19 + 19);
      int count5 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16 + 21 + 19, 130, 19);
      double offset7 = offset2;
      double offset8 = offset;
      String text3 = "Wiki page";
      fontFace.applyString8(text3, offset8, offset7, count5);
   }

   public static void applyFontFaceByFontFace(FontFace fontFace, String text, int count, int count2) {
      if (!Client.getClient().getClickGuiScreen().getClickGuiPanel().isState()) {
         int count3 = (int)fontFace.getScaleFactor(text) + 5;
         int count4 = count + 25;
         RenderUtils.processDouble4(
            (double)(count4 - 5), (double)count2, (double)(count4 + count3), (double)((float)count2 + fontFace.getFontSize()), new Color(40, 39, 39).getRGB()
         );
         RenderUtils.processAction((double)(count4 - 5), (double)count2, (double)(count4 + count3), (double)((float)count2 + fontFace.getFontSize()), 1.0F, Color.BLACK);
         double offset = (double)count4;
         double offset2 = (double)count2;
         int rgb = Color.white.getRGB();
         double offset3 = offset2;
         double offset4 = offset;
         fontFace.applyString8(text, offset4, offset3, rgb);
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.fontFaceEntry.applyChar(character, count);
      ItemCallback.updateState7();
   }

   private void applyText3(String text) {
      Module module4 = ClientGlobals.moduleManager.resolveModule(0x182e84bcff65L, text);
      this.applyModule(module4);
   }

   private void applyIndexByIndex(int count, int count2) {
      RenderUtils.processAction(this.count11, this.count16, this.count11 + 130, this.count16 + 40, 40, 39, 3);
      RenderUtils.processAction((double)this.count11, (double)this.count16, (double)(this.count11 + 130), (double)(this.count16 + 40), 1.0F, Color.BLACK);
      FontFace fontFace = FontRegistry.fontFace2;
      double offset = (double)(this.count11 + 2);
      double offset2 = (double)(this.count16 + 2);
      int count3 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16, 130, 19);
      double offset3 = offset2;
      double offset4 = offset;
      String text = "Import Settings";
      fontFace.applyString8(text, offset4, offset3, count3);
      fontFace = FontRegistry.fontFace2;
      offset = (double)(this.count11 + 2);
      offset2 = (double)(this.count16 + 2 + 19);
      int count4 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16 + 21, 130, 19);
      double offset5 = offset2;
      double offset6 = offset;
      String text2 = "Export Settings";
      fontFace.applyString8(text2, offset6, offset5, count4);
   }

   public ModuleButton getFontFaceEntry() {
      return this.fontFaceEntry;
   }

   public void renderElement4() {
      this.fontFaceEntry.renderElement();
      this.module2 = null;
      this.clickGuiCategoryPanel = null;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      super.applyIndex(count, count2, count3);
      ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
      int count4 = count * scaledResolution.getScaleFactor();
      int count5 = count2 * scaledResolution.getScaleFactor();
      GL11.glPushMatrix();
      count4 -= this.getCount5();
      count5 -= this.getCount();
      this.managedDoubleEntry2.applyIndex(count4, count5, count3);
      this.managedDoubleEntry.applyIndex(count4, count5, count3);
      GL11.glTranslatef((float)this.getCount5(), (float)this.getCount(), 0.0F);
      GL11.glTranslatef(210.0F, 90.0F, 0.0F);
      count4 -= 210;
      count5 -= 95;
      this.fontFaceEntry.applyIndexByIndex(count4, count5, count3);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      ItemCallback.updateState7();
   }

   private void applyIndex(int count, int count2) {
      if (this.module2 != null) {
         if (RenderUtils.checkCondition((float)this.count11, (float)this.count16, 130.0F, 19.0F, count, count2)) {
            boolean flag = ModuleOrCategoryNaHelper.matchesModule(this.module2);
            if (!flag) {
               flag = ModuleOrCategoryNaHelper.matchesModuleByModule(this.module2);
            }

            if (!flag) {
               SoundPlayer.processAction(SoundPlayer.resourceLocation8);
               RayTraceUtil.applyString("Failed to import " + this.module2.getString() + " to clipboard");
            }
         } else if (RenderUtils.checkCondition((float)this.count11, (float)(this.count16 + 21), 130.0F, 19.0F, count, count2)) {
            if (!ModuleOrCategoryNaHelper.matchesModule2(this.module2)) {
               SoundPlayer.processAction(SoundPlayer.resourceLocation8);
               RayTraceUtil.applyString("Failed to export " + this.module2.getString() + " to clipboard");
            }
         } else if (RenderUtils.checkCondition((float)this.count11, (float)(this.count16 + 21 + 19), 130.0F, 19.0F, count, count2)) {
            try {
               Desktop.getDesktop()
                  .browse(
                     new URI(
                        "https://augustusclient.github.io/Augustus-Wiki/"
                           + this.module2.getCategory().name().toLowerCase(Locale.ROOT)
                           + "/"
                           + this.module2.getString().toLowerCase(Locale.ROOT)
                           + "/"
                     )
                  );
            } catch (URISyntaxException | IOException uRISyntaxException) {
               uRISyntaxException.printStackTrace();
            }
         }
      }
   }

   @Override
   public void applyIndexByIndex(int count, int count2, int count3) throws IOException {
      long value = 1990674444L;
      short shortValue = 4101;
      if (this.isActive()) {
         super.applyIndexByIndex(count, count2, count3);
         ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
         int count4 = count * scaledResolution.getScaleFactor();
         int count5 = count2 * scaledResolution.getScaleFactor();
         GL11.glPushMatrix();
         count4 -= this.getCount5();
         count5 -= this.getCount();
         GL11.glTranslatef((float)this.getCount5(), (float)this.getCount(), 0.0F);
         int count6 = super.count6;
         int count7 = Math.max(super.count6, 30);
         int count8 = this.getCount7() - super.count6 * 2;
         int count9 = this.getCount3() - super.count6 - Math.max(super.count6, 30);
         int count10 = count8;
         int count14 = count7;
         int count18 = count6;
         if (!this.checkIndexByIndex(count18, count14, count10, count9, count4, count5)) {
            GL11.glPopMatrix();
            GL11.glPopMatrix();
         } else {
            if ((count3 == 1 || count3 == 0) && !this.enabled5) {
               this.clickGuiCategoryPanel = null;
            }

            int count19 = this.getCount7() - super.count6 - 210;
            byte byteValue = 30;
            short shortValue2 = 210;
            if (this.checkIndexByIndex(shortValue2, byteValue, count19, 60, count4, count5) && !this.state) {
               for (ClickGuiCategoryPanel clickGuiCategoryPanel2 : (Iterable<ClickGuiCategoryPanel>)(Iterable<?>)this.itemList2) {
                  int count20 = clickGuiCategoryPanel2.count5;
                  int count21 = clickGuiCategoryPanel2.count10;
                  int count22 = clickGuiCategoryPanel2.count9;
                  int count23 = clickGuiCategoryPanel2.count8;
                  if (this.checkIndexByIndex(count23, count22, count21, count20, count4, count5)) {
                     if (this.categoryEntry == clickGuiCategoryPanel2 && count3 == 1) {
                        this.clickGuiCategoryPanel = clickGuiCategoryPanel2;
                        this.count11 = count4;
                        this.count16 = count5;
                        this.module2 = null;
                     } else if (!this.enabled5) {
                        int count24 = this.count15;
                        this.categoryEntry.setCount(count24);
                        this.categoryEntry = clickGuiCategoryPanel2;
                        this.count15 = clickGuiCategoryPanel2.getCount();
                        this.managedDoubleEntry.updateState();
                     }
                     break;
                  }
               }
            }

            if ((count3 == 1 || count3 == 0) && !this.state) {
               this.module2 = null;
            }

            if ((count3 == 1 || count3 == 0) && !this.state) {
               this.module2 = null;
            }

            for (Module module4 : (Iterable<Module>)(Iterable<?>)this.categoryEntry.getList()) {
               if (!module4.isEnabled()) {
                  count6 = module4.getIndexOffset4() - 2;
                  count7 = module4.getIndexOffset5();
                  int count25 = module4.getIndexOffset3() + 2;
                  short shortValue3 = 160;
                  int count26 = count7;
                  int count27 = count6;
                  if (this.checkIndexByIndex(count27, count26, shortValue3, count25, count4, count5)) {
                     if (count3 == 1) {
                        if (this.module == module4 && !(module4 instanceof ClientAntiCheat)) {
                           this.count11 = count4;
                           this.count16 = count5;
                           this.module2 = module4;
                           this.clickGuiCategoryPanel = null;
                        }

                        this.module = module4;
                        break;
                     }

                     if (count3 == 0 && !this.state) {
                        module4.onReset();
                        break;
                     }
                  }
               }
            }

            if (this.enabled5 && count3 == 0) {
               this.applyIndex4(count4, count5);
            }

            if (!this.state) {
               this.managedDoubleEntry2.applyIndexByIndex(count4, count5, count3);
               this.managedDoubleEntry.applyIndexByIndex(count4, count5, count3);
            } else if (count3 == 0) {
               this.applyIndex(count4, count5);
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(210.0F, 95.0F, 0.0F);
            count4 -= 210;
            count5 -= 95;
            count8 = this.getCount7() - 210 - super.count6 - (this.fontFaceEntry.enabled3 ? 15 : 0);
            int count28 = this.getCount3() - 95 - super.count6 - (this.fontFaceEntry.enabled2 ? 15 : 0);
            int count29 = count8;
            int byteValue2 = 0;
            byte byteValue3 = 0;
            if (this.checkIndexByIndex(byteValue3, byteValue2, count29, count28, count4, count5)
               && !this.state
               && (this.settingGroup == null || !this.settingGroup.checkIndex(count4, count5, count3))) {
               this.fontFaceEntry.applyIndex2(count4, count5, count3);
            }

            count8 = this.getCount7() - 210 - super.count6;
            int count30 = this.getCount3() - 95 - super.count6;
            int count31 = count8;
            byte byteValue4 = 0;
            byte byteValue5 = 0;
            if (this.checkIndexByIndex(byteValue5, byteValue4, count31, count30, count4, count5) && !this.state) {
               this.fontFaceEntry.applyIndex(count4, count5, count3);
            }

            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            ItemCallback.updateState7();
         }
      }
   }

   public ClickGuiCategoryPanel getCategoryEntry() {
      return this.categoryEntry;
   }

   private String getTextAsText() {
      return this.getCategoryEntry().getCategoryAsCategory().name();
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (this.isActive()) {
         AuxiliaryNoneManager.updateState();
         ItemCallback.updateState5();

         for (Module module4 : (Iterable<Module>)(Iterable<?>)ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
            if (module4 instanceof ActionModule) {
               ((ActionModule)module4).updateState();
            } else if (module4 instanceof SubModule) {
               ((SubModule)module4).updateState2();
            }
         }

         super.applyIndex(count, count2, factor);
         ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
         int count5 = count * scaledResolution.getScaleFactor();
         int count6 = count2 * scaledResolution.getScaleFactor();
         GL11.glPushMatrix();
         count5 -= this.getCount5();
         count6 -= this.getCount();
         GL11.glTranslatef((float)this.getCount5(), (float)this.getCount(), 0.0F);
         GL11.glEnable(3089);
         RenderUtils.processFloat4(
            (float)this.getCount5(), (float)this.getCount(), (float)(this.getCount5() + this.getCount7()), (float)(this.getCount() + this.getCount3()), 1.0
         );
         RenderUtils.processAction(200, 90, this.getCount7(), 90, 3.0F, new Color(34, 34, 34));
         RenderUtils.processAction(200, 30, 200, this.getCount3(), 3.0F, new Color(34, 34, 34));
         RenderUtils.processFloat4(
            (float)(this.getCount5() + 210),
            (float)(this.getCount() + 30),
            (float)(this.getCount5() + this.getCount7() - super.count6),
            (float)(this.getCount() + 90),
            1.0
         );
         int count7 = 0;
         this.state = this.module2 != null && RenderUtils.checkCondition((float)this.count11, (float)this.count16, 130.0F, 59.0F, count5, count6);
         this.enabled5 = this.clickGuiCategoryPanel != null
            && RenderUtils.checkCondition((float)this.count11, (float)this.count16, 130.0F, 78.0F, count5, count6);

         for (ClickGuiCategoryPanel clickGuiCategoryPanel2 : (Iterable<ClickGuiCategoryPanel>)(Iterable<?>)this.itemList2) {
            int count8 = 220 + count7 + this.count17;
            boolean state2 = this.state;
            int byteValue = 50;
            int count9 = count8;
            clickGuiCategoryPanel2.applyIndex(count5, count6, count9, byteValue, state2);
            float factor2 = (float)count7;
            FontFace fontFace = this.fontFace2;
            String text = clickGuiCategoryPanel2.getCategoryAsCategory().name();
            count7 = (int)(factor2 + fontFace.getScaleFactor(text) + 30.0F);
         }

         RenderUtils.processAction(
            this.categoryEntry.getCount2(),
            this.categoryEntry.count6 - this.categoryEntry.getCount7(),
            this.categoryEntry.count3 - this.categoryEntry.getCount4(),
            this.categoryEntry.count6 - this.categoryEntry.getCount7(),
            2.0F,
            new Color(185, 187, 190)
         );
         ManagedDoubleEntry managedDoubleEntry3 = this.managedDoubleEntry2;
         int count10 = this.getCount7() - super.count6;
         int count14 = 220 - this.count17;
         int count18 = ((ClickGuiCategoryPanel)this.itemList2.get(this.itemList2.size() - 1)).count3 - 220;
         Color color = new Color(32, 34, 37);
         int count19 = count18;
         int count20 = count14;
         int byteValue2 = 85;
         int count21 = count10;
         byte byteValue3 = 75;
         short shortValue = 220;
         managedDoubleEntry3.matchesColorByColor(shortValue, byteValue3, count21, byteValue2, count20, count19, color);
         ManagedDoubleEntry managedDoubleEntry4 = this.managedDoubleEntry2;
         int count22 = ((ClickGuiCategoryPanel)this.itemList2.get(this.itemList2.size() - 1)).count3 - 220;
         int count23 = (char)count4;
         count14 = this.getCount7() - super.count6;
         count18 = this.count17;
         int count24 = this.getCount7() - super.count6 - 220;
         byte byteValue4 = 35;
         short shortValue2 = 220;
         int count25 = count18;
         int count26 = count14;
         short shortValue3 = 220;
         int count27 = count22;
         this.count17 = managedDoubleEntry4.computeCount(count27, shortValue3, count26, count25, shortValue2, byteValue4, count24, 50, count5, count6);
         RenderUtils.processFloat4(
            (float)this.getCount5(),
            (float)(this.getCount() + 30),
            (float)(this.getCount5() + 195),
            (float)(this.getCount() + this.getCount3() - super.count6),
            1.0
         );
         int count28 = 0;

         for (Module module5 : (Iterable<Module>)(Iterable<?>)this.categoryEntry.getList()) {
            if (!module5.isEnabled()) {
               count23 = 40 + count28 + this.count15;
               boolean state3 = this.state;
               FontFace fontFace3 = this.fontFace2;
               Module module6 = this.module;
               int count29 = count23;
               byte byteValue5 = 20;
               module5.applyModule(count5, count6, byteValue5, count29, module6, fontFace3, state3);
               count28 = (int)((float)count28 + this.fontFace2.getFontSize() + 5.0F);
               if (!module5.equals(this.module)) {
                  module5.setExpanded(false);
               }
            }
         }

         managedDoubleEntry3 = this.managedDoubleEntry;
         count23 = this.getCount3() - super.count6;
         count14 = 40 - this.count15;
         Color color2 = new Color(32, 34, 37);
         int count30 = count14;
         int count31 = count23;
         short shortValue4 = 195;
         byte byteValue6 = 40;
         short shortValue5 = 185;
         managedDoubleEntry3.matchesColor(shortValue5, byteValue6, shortValue4, count31, count30, count28, color2);
         managedDoubleEntry4 = this.managedDoubleEntry;
         count23 = this.getCount3() - super.count6;
         count14 = this.count15;
         count18 = super.count6;
         int count32 = 200 - super.count6;
         int count33 = this.getCount3() - super.count6 - 30;
         int count34 = count32;
         byte byteValue7 = 30;
         int count35 = count18;
         int count36 = count14;
         int count37 = count23;
         byte byteValue8 = 40;
         this.count15 = managedDoubleEntry4.computeCount(count28, byteValue8, count37, count36, count35, byteValue7, count34, count33, count5, count6);
         GL11.glPushMatrix();
         RenderUtils.processFloat4(
            (float)(this.getCount5() + 210),
            (float)(this.getCount() + 95),
            (float)(this.getCount5() + this.getCount7() - super.count6),
            (float)(this.getCount() + this.getCount3() - super.count6),
            1.0
         );
         if (Client.getClient().getInternalRenderListenerAdapte().count != 0
            && RenderUtils.checkCondition(210.0F, 95.0F, (float)(this.getCount7() - super.count6), (float)(this.getCount3() - super.count6), count5, count6)) {
            this.settingGroup = null;
         }

         GL11.glTranslatef(210.0F, 95.0F, 0.0F);
         ModuleButton fontFaceEntry2 = this.fontFaceEntry;
         int count38 = count5 - 210;
         count22 = count6 - 95;
         count10 = this.getCount7() - 210 - super.count6;
         count23 = this.getCount3() - 95 - super.count6;
         count18 = this.getCount5() + 210;
         int count39 = this.getCount() + 95;
         boolean state4 = this.state;
         Module module7 = this.module;
         int count40 = super.count6;
         int count41 = count39;
         int count42 = count18;
         int count43 = count23;
         int count44 = count10;
         int count45 = count22;
         int count46 = count38;
         fontFaceEntry2.applyModuleByModule(count46, count45, count44, count43, count42, count41, count40, module7, state4);
         GL11.glTranslatef(-210.0F, -95.0F, 0.0F);
         GL11.glDisable(3089);
         int count47 = 0;
         if (ClientGlobals.moduleManager.clickGui.settingModuleDescriptions.isEnabled()) {
            int count48 = this.getCount3() - super.count6 - 30;
            short shortValue6 = 195;
            byte byteValue9 = 30;
            byte byteValue10 = 0;
            if (this.checkIndexByIndex(byteValue10, byteValue9, shortValue6, count48, count5, count6)) {
               if (Client.getClient().getInternalRenderListenerAdapte().count != 0) {
                  this.module2 = null;
               }

               boolean flag = false;

               for (Module module8 : (Iterable<Module>)(Iterable<?>)this.categoryEntry.getList()) {
                  if (!module8.isEnabled()) {
                     count22 = 40 + count47 + this.count15;
                     int count49 = (int)(this.fontFace2.getFontSize() + 2.0F);
                     short shortValue7 = 160;
                     int count50 = count22;
                     byte byteValue11 = 18;
                     if (this.checkIndexByIndex(byteValue11, count50, shortValue7, count49, count5, count6)) {
                        flag = true;
                        if (this.module3 == null || !this.module3.equals(module8)) {
                           this.timer.reset();
                           this.count12 = -1337;
                           this.count13 = -1337;
                        }

                        this.module3 = module8;
                     }

                     if (!module8.getDisplayName().isEmpty() && this.timer.hasReached(750L)) {
                        count22 = 40 + count47 + this.count15;
                        int count51 = (int)(this.fontFace2.getFontSize() + 2.0F);
                        short shortValue8 = 160;
                        int count52 = count22;
                        byte byteValue12 = 18;
                        if (this.checkIndexByIndex(byteValue12, count52, shortValue8, count51, count5, count6)) {
                           if (this.count12 == -1337 && this.count13 == -1337) {
                              this.count12 = count5;
                              this.count13 = count6;
                           }

                           FontFace fontFace4 = FontRegistry.fontFace2;
                           String name = module8.getDisplayName();
                           int count53 = this.count13;
                           int count54 = this.count12;
                           String text2 = name;
                           applyFontFaceByFontFace(fontFace4, text2, count54, count53);
                        }
                     }

                     count47 += (int)(this.fontFace2.getFontSize() + 5.0F);
                  }
               }

               if (!flag) {
                  this.timer.reset();
                  this.count12 = -1337;
                  this.count13 = -1337;
               }
            }
         }

         if (this.module2 != null) {
            this.applyIndex2(count5, count6);
         }

         if (this.clickGuiCategoryPanel != null) {
            this.applyIndex3(count5, count6);
         }

         if (this.settingGroup != null) {
            this.applyIndexByIndex(count5, count6);
         }

         GL11.glPopMatrix();
         GL11.glDisable(3089);
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   public void setCategoryEntry(ClickGuiCategoryPanel categoryEntry2) {
      this.categoryEntry = categoryEntry2;
   }

   public FontFace getFontFace2() {
      return this.fontFace2;
   }

   private void applyIndex3(int count, int count2) {
      short shortValue = 28961;
      short shortValue2 = 6601;
      short shortValue3 = 8845;
      RenderUtils.processAction(this.count11, this.count16, this.count11 + 130, this.count16 + 78, 40, 39, 39);
      RenderUtils.processAction((double)this.count11, (double)this.count16, (double)(this.count11 + 130), (double)(this.count16 + 78), 1.0F, Color.BLACK);
      FontFace fontFace = FontRegistry.fontFace2;
      double offset = (double)(this.count11 + 2);
      double offset2 = (double)(this.count16 + 2);
      int count3 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16, 130, 19);
      double offset3 = offset2;
      double offset4 = offset;
      String text = "Import Settings";
      fontFace.applyString8(text, offset4, offset3, count3);
      fontFace = FontRegistry.fontFace2;
      offset = (double)(this.count11 + 2);
      offset2 = (double)(this.count16 + 21);
      int count4 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16 + 21, 130, 19);
      double offset5 = offset2;
      double offset6 = offset;
      String text2 = "Export Settings";
      fontFace.applyString8(text2, offset6, offset5, count4);
      fontFace = FontRegistry.fontFace2;
      offset = (double)(this.count11 + 2);
      offset2 = (double)(this.count16 + 40);
      int count5 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16 + 40, 130, 19);
      double offset7 = offset2;
      double offset8 = offset;
      String text3 = "LoadFromConfig";
      fontFace.applyString8(text3, offset8, offset7, count5);
      fontFace = FontRegistry.fontFace2;
      offset = (double)(this.count11 + 2);
      offset2 = (double)(this.count16 + 59);
      int count6 = RenderUtils.processAction(new Color(185, 187, 190), 0.2, count, count2, this.count11, this.count16 + 59, 130, 19);
      double offset9 = offset2;
      double offset10 = offset;
      String text4 = "ShowInArrayList";
      fontFace.applyString8(text4, offset10, offset9, count6);
   }

   public Module getModule() {
      return this.module;
   }

   public void renderElement5() {
      Iterator iterator = Client.getClient().getAuxiliaryCopyOnWriteArrayLis().getList().iterator();

      while (iterator.hasNext()) {
         ((Setting)iterator.next()).updateState5();
      }
   }

   private String getText2() {
      return this.getModule().getString();
   }

   private void applyText4(String text) {
      for (ClickGuiCategoryPanel clickGuiCategoryPanel2 : (Iterable<ClickGuiCategoryPanel>)(Iterable<?>)this.getItemList2()) {
         if (clickGuiCategoryPanel2.getCategoryAsCategory().name().equals(text)) {
            this.setCategoryEntry(clickGuiCategoryPanel2);
            break;
         }
      }
   }

   public void applyModule(Module module4) {
      if (module4 != null) {
         this.module = module4;
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
      GL11.glPushMatrix();
      GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
      int count4 = count * scaledResolution.getScaleFactor();
      int count5 = count2 * scaledResolution.getScaleFactor();
      GL11.glPushMatrix();
      count4 -= this.getCount5();
      count5 -= this.getCount();
      GL11.glTranslatef((float)this.getCount5(), (float)this.getCount(), 0.0F);
      GL11.glTranslatef(210.0F, 90.0F, 0.0F);
      count4 -= 210;
      count5 -= 95;
      if (!this.state) {
         this.fontFaceEntry.applyIndex(count4, count5, count3, value);
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   public ClickGuiPanel(String text, int count, int count2, int count3, int count4) {
      super(text, count, count2, count3, count4);
      this.managedDoubleEntry = new ManagedDoubleEntry();
      this.managedDoubleEntry2 = new ManagedDoubleEntry();
      this.fontFaceEntry = new ModuleButton();
      this.count15 = 0;
      this.count17 = 0;
      this.timer = new TimeHelper();
      this.module3 = null;
      this.module2 = null;
      SmoothPicker smoothPicker3 = new SmoothPicker(this, "SelectedModule", "");
      Supplier supplier = this::getText2;
      smoothPicker3 = (SmoothPicker)smoothPicker3.withSupplier(supplier);
      Consumer consumer = (item -> this.applyText3((String)item));
      this.smoothPicker = (SmoothPicker)smoothPicker3.withConsumer(consumer);
      smoothPicker3 = new SmoothPicker(this, "SelectedCategory", "");
      Supplier supplier2 = this::getTextAsText;
      smoothPicker3 = (SmoothPicker)smoothPicker3.withSupplier(supplier2);
      Consumer consumer2 = (item -> this.applyText4((String)item));
      this.smoothPicker2 = (SmoothPicker)smoothPicker3.withConsumer(consumer2);
      this.state = false;
      this.enabled5 = false;
      this.fontFace2 = FontRegistry.fontFace11;
      int count5 = 0;

      for (Category category : Category.values()) {
         if (!category.hidden) {
            this.itemList2.add(new ClickGuiCategoryPanel(220 + count5, 50, 11, 7, category, this.fontFace2));
            float factor = (float)count5;
            FontFace fontFace = this.fontFace2;
            String text2 = category.name();
            count5 = (int)(factor + fontFace.getScaleFactor(text2) + 30.0F);
         }
      }

      this.categoryEntry = (ClickGuiCategoryPanel)this.itemList2.get(0);
      this.module = (Module)this.categoryEntry.getList().get(0);
   }
}
