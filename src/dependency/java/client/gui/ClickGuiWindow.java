package client.gui;

import client.Client;
import client.font.FontFace;
import client.font.FontRegistry;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BeverageNotifyInfoType;
import client.setting.BeverageNotifyModuleTogglesType;
import client.util.BaseHashMapEntry;
import client.util.BeverageItemNotification;
import client.util.ConfigManager;
import client.util.CurvePipeline;
import client.util.GeometryEngine;
import client.util.KeyUtils;
import client.util.ManagedDoubleEntry;
import client.util.MinecraftAccessor;
import client.util.OnlineConfigsManager;
import client.util.RayTraceUtil;
import client.util.SoundPlayer;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class ClickGuiWindow extends PanelWindow {
   public final ManagedDoubleEntry managedDoubleEntry2;
   public static final float factor8;
   public CopyOnWriteArrayList copyOnWriteArrayList;
   public final FontFace fontFace2;
   public int count12;
   public static final float factor4;
   public static final float factor5;
   public CompletableFuture completableFuture2;
   public CopyOnWriteArrayList copyOnWriteArrayList2;
   public static final float factor13;
   public final ManagedDoubleEntry managedDoubleEntry;
   public CompletableFuture completableFuture;
   public CurvePipeline curvePipeline;
   public static final float factor2;
   public static final float factor6;
   public static final float factor11;
   public static final float factor14;
   public static final float factor12;
   public static final float factor7;
   public final FontFace fontFace3;
   public static final float factor10;
   public final Color color;
   public static final float factor;
   public static final float factor9;
   public static final float factor3;
   public int count11;

   public void renderElement7() {
      if (this.completableFuture == null || this.completableFuture.isDone()) {
         this.completableFuture = CompletableFuture.runAsync(this::renderElement6);
      }
   }

   public ClickGuiWindow getClickGuiWindow() {
      long value = 0x89794226902L;
      value = 0x4ff931d23b76L ^ value;
      super.renderElement();
      return this;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (this.isActive()) {
         if (KeyUtils.checkIndex(count) && count == 31 && Display.isActive()) {
            if (this.curvePipeline != null) {
               BaseHashMapEntry baseHashMapEntry = Client.getClient().getAltItemEntry().baseHashMapEntry;
               String labelText = this.curvePipeline.getLabelText3();
               String text = "configs";
               String text2 = labelText;
               baseHashMapEntry.applyTextByText(text2, text);
            } else {
               SoundPlayer.processAction(SoundPlayer.resourceLocation8);
            }
         }

         super.applyChar(character, count);
      }
   }

   private void renderElement4() {
      if (this.curvePipeline != null) {
         File file = new File(OnlineConfigsManager.text + "/configs/" + this.curvePipeline.getLabelText3() + ".json");
         ConfigManager.applyFile(file);
         this.curvePipeline = null;
      }

      this.renderElement6();
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (this.isActive()) {
         super.applyIndex(count, count2, count3);
      }
   }

   public void renderElement6() {
      String text = OnlineConfigsManager.text;
      ConfigManager.applyString2(text + "/configs");
      this.applyText3(text);
   }

   @Override
   public void applyIndex(int count, int count2, float factor15) {
      int count4 = 299566372;
      super.applyIndex(count, count2, factor15);
      if (this.isActive()) {
         ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
         count *= scaledResolution.getScaleFactor();
         count2 *= scaledResolution.getScaleFactor();
         GL11.glPushMatrix();
         count -= this.getCount5();
         count2 -= this.getCount();
         GL11.glTranslatef((float)this.getCount5(), (float)this.getCount(), 0.0F);
         GL11.glEnable(3089);
         float count6 = (float)this.getCount5();
         float count7 = (float)this.getCount();
         float factor16 = (float)(this.getCount5() + this.getCount7());
         int count8 = this.getCount();
         int count9 = 41348;
         RenderUtils.processFloat4(count6, count7, factor16, (float)(count8 + this.getCount3()), 1.0);
         count9 = this.getCount7() / 3;
         int count10 = count9 - 1;
         int count13 = count9 + 1;
         int count14 = 41348;
         RenderUtils.processAction(count10, 30, count13, this.getCount3(), 34, 34, 34);
         int count15 = count9 * 2 - 1;
         int count16 = count9 * 2 + 1;
         count14 = 41348;
         RenderUtils.processAction(count15, 30, count16, this.getCount3(), 34, 34, 34);
         RenderUtils.processAction(0, 82, count9 * 2, 84, 34, 34, 34);
         FontFace fontFace = this.fontFace2;
         double offset = (double)((int)((float)count9 / 2.0F - factor3 / 2.0F));
         count14 = this.color.getRGB();
         double offset2 = 50.0;
         double offset3 = offset;
         String text = "OnlineConfigs";
         fontFace.applyString8(text, offset3, offset2, count14);
         FontFace fontFace4 = this.fontFace2;
         double offset4 = (double)((int)((float)count9 + (float)count9 / 2.0F - factor / 2.0F));
         count14 = this.color.getRGB();
         offset2 = 50.0;
         offset3 = offset4;
         String text2 = "OfflineConfigs";
         fontFace4.applyString8(text2, offset3, offset2, count14);
         count6 = (float)(this.getCount5() + super.count6);
         float factor17 = (float)(this.getCount() + 90);
         factor16 = (float)(this.getCount5() + count9 - 3);
         count8 = this.getCount();
         int count17 = (char)count5;
         RenderUtils.processFloat4(count6, factor17, factor16, (float)(count8 + this.getCount3() - super.count6), 1.0);
         byte byteValue = 0;

         for (Object curvePipeline2_o : this.copyOnWriteArrayList) {
            CurvePipeline curvePipeline2 = (CurvePipeline)curvePipeline2_o;
            int count18 = count9 - 3;
            float factor18 = 0.0F + (float)count18 / 2.0F;
            FontFace fontFace5 = this.fontFace3;
            String labelText = curvePipeline2.getLabelText3();
            int count19 = (int)(factor18 - fontFace5.getScaleFactor(labelText) / 2.0F);
            int count20 = 90 + byteValue + this.count11;
            int fontSize = (int)this.fontFace3.getFontSize();
            FontFace fontFace6 = this.fontFace3;
            int count21 = fontSize;
            int count22 = count20;
            int count23 = count19;
            curvePipeline2.applyFontFace(count23, count22, count18, count21, count, count2, fontFace6, false);
            byteValue += 25;
         }

         ManagedDoubleEntry managedDoubleEntry3 = this.managedDoubleEntry;
         int count24 = count9 - 15;
         count8 = count9 - 5;
         int count25 = (char)count5;
         int count26 = this.getCount3() - super.count6;
         int count27 = 90 - this.count11;
         Color color2 = new Color(32, 34, 37);
         int count28 = count27;
         int count29 = count26;
         int count30 = count8;
         int count31 = 90;
         int count32 = count24;
         managedDoubleEntry3.matchesColor(count32, count31, count30, count29, count28, byteValue, color2);
         ManagedDoubleEntry managedDoubleEntry4 = this.managedDoubleEntry;
         count17 = (char)count5;
         count26 = this.getCount3() - super.count6;
         count27 = this.count11;
         count17 = (char)count5;
         count17 = this.getCount3() - super.count6 - 90;
         int count33 = 90;
         int count34 = 0;
         count30 = count27;
         count31 = count26;
         int count35 = 90;
         this.count11 = managedDoubleEntry4.computeCount(byteValue, count35, count31, count30, count34, count33, count9, count17, count, count2);
         count6 = (float)(this.getCount5() + count9 + 3);
         float factor19 = (float)(this.getCount() + 90);
         factor16 = (float)(this.getCount5() + count9 * 2 - 3);
         count8 = this.getCount();
         RenderUtils.processFloat4(count6, factor19, factor16, (float)(count8 + this.getCount3() - super.count6), 1.0);
         byteValue = 0;

         for (Object curvePipeline3_o : this.copyOnWriteArrayList2) {
            CurvePipeline curvePipeline3 = (CurvePipeline)curvePipeline3_o;
            count25 = count9 - 3;
            float factor20 = (float)count9 + (float)count25 / 2.0F;
            FontFace fontFace7 = this.fontFace3;
            String labelText2 = curvePipeline3.getLabelText3();
            int count36 = (int)(factor20 - fontFace7.getScaleFactor(labelText2) / 2.0F);
            int count37 = 90 + byteValue + this.count12;
            count26 = (int)this.fontFace3.getFontSize();
            boolean flag = this.curvePipeline != null ? curvePipeline3.equals(this.curvePipeline) : false;
            FontFace fontFace8 = this.fontFace3;
            count34 = count26;
            count31 = count37;
            count35 = count36;
            curvePipeline3.applyFontFace(count35, count31, count25, count34, count, count2, fontFace8, flag);
            byteValue += 25;
         }

         ManagedDoubleEntry managedDoubleEntry5 = this.managedDoubleEntry2;
         int count38 = count9 * 2 - 15;
         count8 = count9 * 2 - 5;
         count26 = this.getCount3() - super.count6;
         count27 = 90 - this.count12;
         Color color3 = new Color(32, 34, 37);
         count33 = count27;
         count34 = count26;
         count30 = count8;
         byte byteValue2 = 90;
         count35 = count38;
         managedDoubleEntry5.matchesColor(count35, byteValue2, count30, count34, count33, byteValue, color3);
         ManagedDoubleEntry managedDoubleEntry6 = this.managedDoubleEntry2;
         int count39 = (char)count5;
         count27 = this.getCount3() - super.count6;
         int count40 = this.count12;
         count39 = (char)count5;
         count39 = this.getCount3() - super.count6 - 90;
         byte byteValue3 = 90;
         count34 = count40;
         count30 = count27;
         byteValue2 = 90;
         this.count12 = managedDoubleEntry6.computeCount(byteValue, byteValue2, count30, count34, count9, byteValue3, count9, count39, count, count2);
         count6 = (float)(this.getCount5() + count9 * 2 + 3);
         float factor21 = (float)(this.getCount() + 40);
         factor16 = (float)(this.getCount5() + this.getCount7() - super.count6);
         count8 = this.getCount();
         int count41 = (char)count5;
         RenderUtils.processFloat4(count6, factor21, factor16, (float)(count8 + this.getCount3() - super.count6), 1.0);
         byte byteValue4 = 50;
         FontFace fontFace9 = this.fontFace2;
         double offset5 = (double)((int)((float)(count9 * 2) + (float)count9 / 2.0F - factor6 / 2.0F));
         double offset6 = (double)50;
         count4 = RenderUtils.processAction(
            this.color,
            0.2,
            count,
            count2,
            (int)((float)(count9 * 2) + (float)count9 / 2.0F - factor6 / 2.0F - 10.0F),
            47,
            (int)factor2 + 20,
            (int)this.fontFace3.getFontSize() + 6
         );
         offset2 = offset6;
         offset3 = offset5;
         String text3 = "Create";
         fontFace9.applyString8(text3, offset3, offset2, count4);
         count41 = byteValue4 + 35;
         FontFace fontFace10 = this.fontFace2;
         double offset7 = (double)((int)((float)(count9 * 2) + (float)count9 / 2.0F - factor7 / 2.0F));
         double offset8 = (double)85;
         count4 = RenderUtils.processAction(
            this.color,
            0.2,
            count,
            count2,
            (int)((float)(count9 * 2) + (float)count9 / 2.0F - factor7 / 2.0F - 10.0F),
            82,
            (int)factor12 + 20,
            (int)this.fontFace3.getFontSize() + 6
         );
         offset2 = offset8;
         offset3 = offset7;
         String text4 = "Load";
         fontFace10.applyString8(text4, offset3, offset2, count4);
         count41 += 35;
         FontFace fontFace11 = this.fontFace2;
         double offset9 = (double)((int)((float)(count9 * 2) + (float)count9 / 2.0F - factor9 / 2.0F));
         double offset10 = (double)120;
         count4 = RenderUtils.processAction(
            this.color,
            0.2,
            count,
            count2,
            (int)((float)(count9 * 2) + (float)count9 / 2.0F - factor9 / 2.0F - 10.0F),
            117,
            (int)factor4 + 20,
            (int)this.fontFace3.getFontSize() + 6
         );
         offset2 = offset10;
         offset3 = offset9;
         String text5 = "Save";
         fontFace11.applyString8(text5, offset3, offset2, count4);
         count41 += 35;
         FontFace fontFace12 = this.fontFace2;
         double offset11 = (double)((int)((float)(count9 * 2) + (float)count9 / 2.0F - factor13 / 2.0F));
         double offset12 = (double)155;
         count4 = RenderUtils.processAction(
            this.color,
            0.2,
            count,
            count2,
            (int)((float)(count9 * 2) + (float)count9 / 2.0F - factor13 / 2.0F - 10.0F),
            152,
            (int)factor11 + 20,
            (int)this.fontFace3.getFontSize() + 6
         );
         offset2 = offset12;
         offset3 = offset11;
         String text6 = "Folder";
         fontFace12.applyString8(text6, offset3, offset2, count4);
         count41 += 35;
         FontFace fontFace13 = this.fontFace2;
         double offset13 = (double)((int)((float)(count9 * 2) + (float)count9 / 2.0F - factor14 / 2.0F));
         double offset14 = (double)190;
         count41 = RenderUtils.processAction(
            this.color,
            0.2,
            count,
            count2,
            (int)((float)(count9 * 2) + (float)count9 / 2.0F - factor14 / 2.0F - 10.0F),
            187,
            (int)factor8 + 20,
            (int)this.fontFace3.getFontSize() + 6
         );
         offset2 = offset14;
         offset3 = offset13;
         String text7 = "Refresh";
         fontFace13.applyString8(text7, offset3, offset2, count41);
         FontFace fontFace14 = this.fontFace2;
         double offset15 = (double)((int)((float)(count9 * 2) + (float)count9 / 2.0F - factor10 / 2.0F));
         int count42 = (char)count5;
         double offset16 = (double)((float)this.getCount3() - this.fontFace2.getFontSize() - 2.0F - (float)super.count6);
         int count43 = (int)((float)(count9 * 2) + (float)count9 / 2.0F - factor10 / 2.0F - 10.0F);
         count42 = 41348;
         count42 = RenderUtils.processAction(
            this.color,
            0.2,
            count,
            count2,
            count43,
            (int)((float)this.getCount3() - this.fontFace2.getFontSize() - 2.0F - (float)super.count6 - 3.0F),
            (int)factor5 + 20,
            (int)this.fontFace3.getFontSize() + 6
         );
         offset2 = offset16;
         offset3 = offset15;
         String text8 = "Delete";
         fontFace14.applyString8(text8, offset3, offset2, count42);
         GL11.glDisable(3089);
         count8 = this.getCount7();
         int count44 = (char)count5;
         count44 = this.getCount3() - super.count6;
         count4 = count8;
         byte byteValue5 = 0;
         byte byteValue6 = 0;
         if (this.checkIndexByIndex(byteValue6, byteValue5, count4, count44, count, count2)) {
            for (Object curvePipeline4_o : this.copyOnWriteArrayList) {
               CurvePipeline curvePipeline4 = (CurvePipeline)curvePipeline4_o;
               curvePipeline4.isEmpty();
            }

            for (Object curvePipeline5_o : this.copyOnWriteArrayList2) {
               CurvePipeline curvePipeline5 = (CurvePipeline)curvePipeline5_o;
               curvePipeline5.isEmpty();
            }
         }

         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   @Override
   public void applyIndexByIndex(int count, int count2, int count3) throws IOException {
      if (this.isActive()) {
         int count8 = this.getCount7() / 3;
         super.applyIndexByIndex(count, count2, count3);
         ScaledResolution scaledResolution = new ScaledResolution(MinecraftAccessor.minecraft);
         count *= scaledResolution.getScaleFactor();
         count2 *= scaledResolution.getScaleFactor();
         count -= this.getCount5();
         count2 -= this.getCount();
         float factor15 = (float)(count8 * 2) + (float)count8 / 2.0F;
         String text = "Create";
         float factor16 = (float)((int)(factor15 - this.fontFace2.getScaleFactor(text) / 2.0F - 10.0F));
         String text2 = "Create";
         if (RenderUtils.checkCondition(
            factor16, 47.0F, (float)((int)this.fontFace3.getScaleFactor(text2) + 20), (float)((int)this.fontFace3.getFontSize() + 6), count, count2
         )) {
            System.out.println("Create");
            SoundPlayer.processAction(SoundPlayer.resourceLocation14);
            Minecraft minecraft = MinecraftAccessor.minecraft;
            GeometryEngine geometryEngine = new GeometryEngine(Client.getClient().getClickGuiScreen());
            minecraft.displayGuiScreen(geometryEngine);
         }

         float factor17 = (float)(count8 * 2) + (float)count8 / 2.0F;
         String text3 = "Load";
         float factor18 = (float)((int)(factor17 - this.fontFace2.getScaleFactor(text3) / 2.0F - 10.0F));
         String text4 = "Load";
         if (RenderUtils.checkCondition(
               factor18, 82.0F, (float)((int)this.fontFace3.getScaleFactor(text4) + 20), (float)((int)this.fontFace3.getFontSize() + 6), count, count2
            )
            && this.curvePipeline != null) {
            BaseHashMapEntry baseHashMapEntry = Client.getClient().getAltItemEntry().baseHashMapEntry;
            String labelText = this.curvePipeline.getLabelText3();
            String text5 = "configs";
            String text6 = labelText;
            baseHashMapEntry.applyText(text6, text5);
         }

         float factor19 = (float)(count8 * 2) + (float)count8 / 2.0F;
         String text7 = "Save";
         float factor20 = (float)((int)(factor19 - this.fontFace2.getScaleFactor(text7) / 2.0F - 10.0F));
         String text8 = "Save";
         if (RenderUtils.checkCondition(
               factor20, 117.0F, (float)((int)this.fontFace3.getScaleFactor(text8) + 20), (float)((int)this.fontFace3.getFontSize() + 6), count, count2
            )
            && this.curvePipeline != null) {
            BaseHashMapEntry baseHashMapEntry2 = Client.getClient().getAltItemEntry().baseHashMapEntry;
            String labelText2 = this.curvePipeline.getLabelText3();
            String text9 = "configs";
            String text10 = labelText2;
            baseHashMapEntry2.applyTextByText(text10, text9);
         }

         float factor21 = (float)(count8 * 2) + (float)count8 / 2.0F;
         String text11 = "Folder";
         float factor22 = (float)((int)(factor21 - this.fontFace2.getScaleFactor(text11) / 2.0F - 10.0F));
         String text12 = "Folder";
         if (RenderUtils.checkCondition(
            factor22, 152.0F, (float)((int)this.fontFace3.getScaleFactor(text12) + 20), (float)((int)this.fontFace3.getFontSize() + 6), count, count2
         )) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation14);

            try {
               Desktop.getDesktop().open(new File(new File(OnlineConfigsManager.text + "/configs").getAbsolutePath()));
            } catch (Exception exception) {
               BeverageNotifyInfoType beverageNotifyInfoType = BeverageNotifyInfoType.ERROR;
               String text13 = "Error opening folder";
               this.applyText(text13, beverageNotifyInfoType);
            }

            System.out.println("Opend Folder");
         }

         float factor23 = (float)(count8 * 2) + (float)count8 / 2.0F;
         String text14 = "Refresh";
         float factor24 = (float)((int)(factor23 - this.fontFace2.getScaleFactor(text14) / 2.0F - 10.0F));
         String text15 = "Refresh";
         if (RenderUtils.checkCondition(
            factor24, 187.0F, (float)((int)this.fontFace3.getScaleFactor(text15) + 20), (float)((int)this.fontFace3.getFontSize() + 6), count, count2
         )) {
            if (this.completableFuture2 == null) {
               this.completableFuture2 = CompletableFuture.supplyAsync(ClickGuiWindow::getBoolean);
               BeverageNotifyInfoType beverageNotifyInfoType2 = BeverageNotifyInfoType.INFO;
               String text16 = "Started configs download...";
               this.applyText(text16, beverageNotifyInfoType2);
               CompletableFuture.runAsync(this::renderElement5);
            } else {
               BeverageNotifyInfoType beverageNotifyInfoType3 = BeverageNotifyInfoType.INFO;
               String text17 = "The download is still running...";
               this.applyText(text17, beverageNotifyInfoType3);
            }

            SoundPlayer.processAction(SoundPlayer.resourceLocation14);
         }

         float factor25 = (float)(count8 * 2) + (float)count8 / 2.0F;
         String text18 = "Delete";
         float factor26 = (float)((int)(factor25 - this.fontFace2.getScaleFactor(text18) / 2.0F - 10.0F));
         int count9 = (char)count6;
         float factor27 = (float)((int)((float)this.getCount3() - this.fontFace2.getFontSize() - 2.0F - (float)super.count6 - 3.0F));
         String text19 = "Delete";
         if (RenderUtils.checkCondition(
               factor26, factor27, (float)((int)this.fontFace3.getScaleFactor(text19) + 20), (float)((int)this.fontFace3.getFontSize() + 6), count, count2
            )
            && this.curvePipeline != null) {
            String labelText3 = this.curvePipeline.getLabelText3();
            if (this.completableFuture == null || this.completableFuture.isDone()) {
               this.completableFuture = CompletableFuture.runAsync(this::renderElement4);
               SoundPlayer.processAction(SoundPlayer.resourceLocation7);
               String text20 = "Config moved to trash bin: §c" + labelText3;
               BeverageNotifyInfoType beverageNotifyInfoType4 = BeverageNotifyInfoType.ERROR;
               String text21 = text20;
               this.applyText(text21, beverageNotifyInfoType4);
               System.out.println("Delete");
            }
         }

         for (CurvePipeline curvePipeline2 : (Iterable<CurvePipeline>)(Iterable)(this.copyOnWriteArrayList)) {
            int count10 = this.getCount5() + super.count6;
            int count13 = this.getCount() + 90;
            int count14 = this.getCount5() + count8 - 3;
            int count15 = this.getCount();
            count9 = (char)count6;
            count15 = count15 + this.getCount3() - super.count6;
            int count16 = count + this.getCount5();
            int count17 = count2 + this.getCount();
            count9 = count17;
            int count18 = count16;
            int count19 = count15;
            int count20 = count14;
            int count21 = count13;
            int count22 = count10;
            if (this.checkIndex(count22, count21, count20, count19, count18, count9)) {
               curvePipeline2.applyIndex2(count, count2, count3);
            }
         }

         for (Object curvePipeline3_o : this.copyOnWriteArrayList2) {
            CurvePipeline curvePipeline3 = (CurvePipeline)curvePipeline3_o;
            int count23 = this.getCount5() + count8 + 3;
            int count24 = this.getCount() + 90;
            int count25 = this.getCount5() + count8 * 2 - 3;
            int count26 = this.getCount();
            int count27 = (char)count6;
            count26 = count26 + this.getCount3() - super.count6;
            int count28 = count + this.getCount5();
            int count29 = count2 + this.getCount();
            count27 = count29;
            int count30 = count28;
            int count31 = count26;
            int count32 = count25;
            int count33 = count24;
            int count34 = count23;
            boolean flag = this.checkIndex(count34, count33, count32, count31, count30, count27);
            if (flag && curvePipeline3.getFontFace() != null) {
               float factor28 = (float)(curvePipeline3.getCount3() - 5);
               float count35 = (float)curvePipeline3.getCount();
               FontFace fontFace = curvePipeline3.getFontFace();
               String labelText4 = curvePipeline3.getLabelText3();
               if (RenderUtils.checkCondition(factor28, count35, (float)((int)(fontFace.getScaleFactor(labelText4) + 10.0F)), (float)(curvePipeline3.getCount7() + 2), count, count2)
                  && count3 == 0) {
                  this.curvePipeline = curvePipeline3;
               }
            }

            if (flag) {
               curvePipeline3.applyIndex2(count, count2, count3);
            }
         }
      }
   }

   private void applyText(String text, BeverageNotifyInfoType beverageNotifyInfoType) {
      RayTraceUtil.applyString(text);
      if (((Module)Client.getClient().getModuleManager().notifications).isToggled() && BeverageNotifyModuleTogglesType.CONFIG_SYSTEM.isSelected()) {
         BeverageItemNotification.updateText("Configsystem", text, beverageNotifyInfoType);
      }
   }

   private static Boolean getBoolean() {
      Client.getClient().getOnlineConfigsManager();
      return OnlineConfigsManager.isActive();
   }

   private boolean matchesCurvePipeline(CurvePipeline curvePipeline2) {
      return curvePipeline2.getLabelText3().equals(this.curvePipeline.getLabelText3());
   }

   private synchronized void applyText3(String text) {
      long value = 0x2716f862b4feL;
      value = 0x4ff931d23b76L ^ value;
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();

      try {
         File[] fileArray = new File(text + "/configs").listFiles();
         if (fileArray != null) {
            for (File file : fileArray) {
               if (file.getName().contains(".json")) {
                  try {
                     StringBuilder builder = new StringBuilder();

                     for (int index = 0; index < file.getName().length() - 5; index++) {
                        builder.append(file.getName().charAt(index));
                     }

                     BaseHashMapEntry baseHashMapEntry = Client.getClient().getAltItemEntry().baseHashMapEntry;
                     String text2 = builder.toString();
                     String text3 = "configs";
                     String text4 = text2;
                     String[] textArray = baseHashMapEntry.resolveTextArray(text4, text3);
                     CurvePipeline curvePipeline2 = new CurvePipeline(builder.toString(), false, textArray == null ? "" : textArray[1], textArray == null ? "" : textArray[2]);
                     list.add(curvePipeline2);
                  } catch (Exception exception) {
                     if (MinecraftAccessor.minecraft.theWorld != null) {
                        String text5 = "Failed to load config: " + file.getName();
                        BeverageNotifyInfoType beverageNotifyInfoType = BeverageNotifyInfoType.ERROR;
                        String text6 = text5;
                        this.applyText(text6, beverageNotifyInfoType);
                     }
                  }
               }
            }
         }

         Path path = Paths.get(text + "/onlineConfigs");
         if (!Files.exists(path)) {
            try {
               Files.createDirectories(path);
            } catch (IOException ioException) {
               ioException.printStackTrace();
            }
         }

         File file2 = new File(text + "/onlineConfigs");
         File[] fileArray2 = file2.listFiles();
         if (fileArray2 != null) {
            for (File file3 : fileArray2) {
               if (file3.getName().contains(".json")) {
                  try {
                     StringBuilder builder2 = new StringBuilder();

                     for (int index2 = 0; index2 < file3.getName().length() - 5; index2++) {
                        builder2.append(file3.getName().charAt(index2));
                     }

                     BaseHashMapEntry baseHashMapEntry2 = Client.getClient().getAltItemEntry().baseHashMapEntry;
                     String text7 = builder2.toString();
                     String text8 = "onlineConfigs";
                     String text9 = text7;
                     String[] textArray2 = baseHashMapEntry2.resolveTextArray(text9, text8);
                     CurvePipeline curvePipeline3 = new CurvePipeline(builder2.toString(), true, textArray2 == null ? "" : textArray2[1], textArray2 == null ? "" : textArray2[2]);
                     list2.add(curvePipeline3);
                  } catch (Exception exception2) {
                     if (MinecraftAccessor.minecraft.theWorld != null) {
                        String text10 = "Failed to load onlineconfig: " + file2.getName();
                        BeverageNotifyInfoType beverageNotifyInfoType2 = BeverageNotifyInfoType.ERROR;
                        String text11 = text10;
                        this.applyText(text11, beverageNotifyInfoType2);
                     }
                  }
               }
            }
         }
      } catch (Exception exception3) {
      }

      this.copyOnWriteArrayList2.clear();
      this.copyOnWriteArrayList2.addAll(list);
      this.copyOnWriteArrayList.clear();
      this.copyOnWriteArrayList.addAll(list2);
      if (this.curvePipeline != null) {
         this.curvePipeline = (CurvePipeline)this.copyOnWriteArrayList2.stream().filter(item -> this.matchesCurvePipeline((CurvePipeline)item)).findFirst().orElse(null);
      }
   }

   private void renderElement5() {
      while (this.completableFuture2 != null && !this.completableFuture2.isDone()) {
      }

      try {
         if (this.completableFuture2 != null && (Boolean)this.completableFuture2.get()) {
            CompletableFuture.runAsync(this::renderElement6);
            BeverageNotifyInfoType beverageNotifyInfoType = BeverageNotifyInfoType.INFO;
            String text = "Finished configs download successfully.";
            this.applyText(text, beverageNotifyInfoType);
         } else {
            BeverageNotifyInfoType beverageNotifyInfoType2 = BeverageNotifyInfoType.ERROR;
            String text2 = "Failed to download configs.";
            this.applyText(text2, beverageNotifyInfoType2);
         }
      } catch (ExecutionException | InterruptedException executionException) {
         BeverageNotifyInfoType beverageNotifyInfoType3 = BeverageNotifyInfoType.ERROR;
         String text3 = "Failed to download configs.";
         this.applyText(text3, beverageNotifyInfoType3);
      }

      this.completableFuture2 = null;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      if (this.isActive()) {
         super.applyIndex(count, count2, count3, value);
      }
   }

   public ClickGuiWindow(String text, int count, int count2, int count3, int count4) {
        super(text, count, count2, count3, count4);
      long value = 0x2b4fac72e392L;
      value = 0x4ff931d23b76L ^ value;
      
      this.fontFace3 = FontRegistry.fontFace2;
      this.fontFace2 = FontRegistry.fontFace11;
      this.copyOnWriteArrayList = new CopyOnWriteArrayList();
      this.copyOnWriteArrayList2 = new CopyOnWriteArrayList();
      this.managedDoubleEntry2 = new ManagedDoubleEntry();
      this.managedDoubleEntry = new ManagedDoubleEntry();
      this.count11 = 0;
      this.count12 = 0;
      this.color = new Color(185, 187, 190);
      this.completableFuture2 = null;
      this.completableFuture = null;
   }

   static {
      String text = "OnlineConfigs";
      factor3 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "OfflineConfigs";
      factor = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Create";
      factor6 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Load";
      factor7 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Save";
      factor9 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Folder";
      factor13 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Refresh";
      factor14 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Delete";
      factor10 = FontRegistry.fontFace11.getScaleFactor(text);
      text = "Create";
      factor2 = FontRegistry.fontFace2.getScaleFactor(text);
      text = "Load";
      factor12 = FontRegistry.fontFace2.getScaleFactor(text);
      text = "Save";
      factor4 = FontRegistry.fontFace2.getScaleFactor(text);
      text = "Folder";
      factor11 = FontRegistry.fontFace2.getScaleFactor(text);
      text = "Refresh";
      factor8 = FontRegistry.fontFace2.getScaleFactor(text);
      text = "Delete";
      factor5 = FontRegistry.fontFace2.getScaleFactor(text);
   }
}
