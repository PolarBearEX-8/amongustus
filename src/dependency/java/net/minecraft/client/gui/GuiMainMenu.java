package net.minecraft.client.gui;

import client.ClientGlobals;
import client.event.PreMotionObserver;
import client.gui.AugustusMainMenu;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.CommonGuiScreenAdapter;
import client.gui.DefaultGuiScreenAdapter;
import client.gui.PrimaryGuiScreenAdapter;
import client.module.Module;
import client.render.WorldRenderer;
import client.setting.ClientSettingsBlackType;
import client.util.AdvancedLongEntry;
import client.util.BezierWriter;
import client.util.DefaultVertexFormats;
import client.util.DistanceEngine;
import client.util.GeometryService;
import client.util.InterpolationQueue;
import client.util.RandomSeedManager;
import client.util.SplinePredictor;
import client.util.WeightManager;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.ISaveFormat;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class GuiMainMenu extends GuiScreen implements PreMotionObserver {
   public static final String labelText3 = "Please click "
      + ClientSettingsBlackType.UNDERLINE
      + "here"
      + ClientSettingsBlackType.RESET
      + " for more information.";
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public static final Logger logger2 = LogManager.getLogger();
   public static final Random random = new Random();
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("texts/splashes.txt");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/gui/title/minecraft.png");
   public static final ResourceLocation[] resourceLocationArray = new ResourceLocation[]{
      new ResourceLocation("textures/gui/title/background/panorama_0.png"),
      new ResourceLocation("textures/gui/title/background/panorama_1.png"),
      new ResourceLocation("textures/gui/title/background/panorama_2.png"),
      new ResourceLocation("textures/gui/title/background/panorama_3.png"),
      new ResourceLocation("textures/gui/title/background/panorama_4.png"),
      new ResourceLocation("textures/gui/title/background/panorama_5.png")
   };
   public final Object dataRef = new Object();
   public float factor3;
   public String labelText5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public int count9;
   public DynamicTexture dynamicTexture;
   public boolean enabled6 = true;
   public String labelText;
   public String labelText2 = labelText3;
   public String labelText4;
   public int count2;
   public int count4;
   public int count3;
   public int count6;
   public int count8;
   public int count5;
   public ResourceLocation resourceLocation5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;

   private void applyIndex(int count, int count7) {
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 - 100, count, StatCollector.getText("menu.singleplayer", new Object[0])));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, this.width / 2 - 100, count + count7 * 1, StatCollector.getText("menu.multiplayer", new Object[0]))
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               14, this.width / 2 - 100, count + count7 * 2, StatCollector.getText("menu.online", new Object[0])
            )
         );
   }

   @Override
   public void updateState2() {
      if (ClientGlobals.moduleManager != null
         && ClientGlobals.moduleManager.invisMode != null
         && (!((Module)ClientGlobals.moduleManager.invisMode).isToggled() || !ClientGlobals.moduleManager.invisMode.checkCondition())) {
         Minecraft mc = Minecraft.getMinecraft();
         GuiScreen screen = AugustusMainMenu.resolveGuiScreen((short)0, (char)3322, -80609536);
         mc.displayGuiScreen(screen);
      }

      this.dynamicTexture = new DynamicTexture(256, 256);
      this.resourceLocation5 = this.mc.getTextureManager().resolveResourceLocationByText("background", this.dynamicTexture);
      Calendar instance = Calendar.getInstance();
      instance.setTime(new Date());
      if (instance.get(2) + 1 == 12 && instance.get(5) == 24) {
         this.labelText5 = "Merry X-mas!";
      } else if (instance.get(2) + 1 == 1 && instance.get(5) == 1) {
         this.labelText5 = "Happy new year!";
      } else if (instance.get(2) + 1 == 10 && instance.get(5) == 31) {
         this.labelText5 = "OOoooOOOoooo! Spooky!";
      }

      int count11 = this.height / 4 + 48;
      if (this.mc.isEnabled14()) {
         this.applyIndexByIndex(count11, 24);
      } else {
         this.applyIndex(count11, 24);
      }

      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 100, count11 + 72 + 12, 98, 20, StatCollector.getText("menu.options", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(4, this.width / 2 + 2, count11 + 72 + 12, 98, 20, StatCollector.getText("menu.quit", new Object[0]))
         );
      this.list.add(new InterpolationQueue(5, this.width / 2 - 124, count11 + 72 + 12));
      synchronized (this.dataRef) {
         this.count4 = this.fontRenderer.getIndex(this.labelText);
         this.count2 = this.fontRenderer.getIndex(this.labelText2);
         int count12 = Math.max(this.count4, this.count2);
         this.count3 = (this.width - count12) / 2;
         this.count6 = ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).count3 - 24;
         this.count8 = this.count3 + count12;
         this.count5 = this.count6 + 24;
      }

      this.mc.setEnabled5(false);
   }

   private void applyIndexByIndex(int count, int count7, float factor) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.disableBlend();
      GlStateManager.applyState10();
      GlStateManager.disableAlpha();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      int count10 = 64;
      DistanceEngine distance = WeightManager.getDistanceEngine();
      if (distance != null) {
         count10 = distance.getCount2();
      }

      for (int index = 0; index < count10; index++) {
         GlStateManager.pushMatrix();
         float factor2 = ((float)(index % 8) / (float)8 - 0.5F) / 64.0F;
         float factor4 = ((float)(index / 8) / (float)8 - 0.5F) / 64.0F;
         float factor5 = 0.0F;
         GlStateManager.translate(factor2, factor4, factor5);
         GlStateManager.rotate(MathHelper.abs(((float)this.count9 + factor) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(-((float)this.count9 + factor) * 0.1F, 0.0F, 1.0F, 0.0F);

         for (int index2 = 0; index2 < 6; index2++) {
            GlStateManager.pushMatrix();
            if (index2 == 1) {
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (index2 == 2) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (index2 == 3) {
               GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (index2 == 4) {
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (index2 == 5) {
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            ResourceLocation[] locationArray = resourceLocationArray;
            if (distance != null) {
               locationArray = distance.getResourceLocationArray();
            }

            this.mc.getTextureManager().applyResourceLocation3(locationArray[index2]);
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            int count11 = 255 / (index + 1);
            worldRenderer.getWorldRenderer(-1.0, -1.0, 1.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(255, 255, 255, count11).updateState();
            worldRenderer.getWorldRenderer(1.0, -1.0, 1.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(255, 255, 255, count11).updateState();
            worldRenderer.getWorldRenderer(1.0, 1.0, 1.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(255, 255, 255, count11).updateState();
            worldRenderer.getWorldRenderer(-1.0, 1.0, 1.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(255, 255, 255, count11).updateState();
            tessellator.updateState();
            GlStateManager.popMatrix();
         }

         GlStateManager.popMatrix();
         GlStateManager.applyFlag(true, true, true, false);
      }

      worldRenderer.applyDouble(0.0, 0.0, 0.0);
      GlStateManager.applyFlag(true, true, true, true);
      GlStateManager.matrixMode(5889);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.enableLighting();
      GlStateManager.disableDepth();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         Minecraft mc = this.mc;
         CommonGuiScreenAdapter commonGuiScreenAdapter = new CommonGuiScreenAdapter(this, this.mc.gameSettings);
         mc.displayGuiScreen(commonGuiScreenAdapter);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 5) {
         Minecraft mc2 = this.mc;
         GeometryService geometryService = new GeometryService(this, this.mc.gameSettings, this.mc.getLanguageManager());
         mc2.displayGuiScreen(geometryService);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         Minecraft mc3 = this.mc;
         PrimaryGuiScreenAdapter primaryGuiScreenAdapter = new PrimaryGuiScreenAdapter(this);
         mc3.displayGuiScreen(primaryGuiScreenAdapter);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
         Minecraft mc4 = this.mc;
         GuiMultiplayer guiMultiplayer = new GuiMultiplayer(this);
         mc4.displayGuiScreen(guiMultiplayer);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
         this.mc.updateState14();
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 11) {
         AdvancedLongEntry advancedLongEntry = BezierWriter.advancedLongEntry;
         String text = "Demo_World";
         String text2 = "Demo_World";
         this.mc.applyText(text2, text, advancedLongEntry);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 12) {
         RandomSeedManager randomSeedManager = this.mc.getISaveFormat().resolveRandomSeedManager("Demo_World");
         if (randomSeedManager != null) {
            DefaultGuiScreenAdapter defaultGuiScreenAdapter = PrimaryGuiScreenAdapter.resolveDefaultGuiScreenAdapter(this, randomSeedManager.getLabelText(), 12);
            this.mc.displayGuiScreen(defaultGuiScreenAdapter);
         }
      }
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
   }

   @Override
   public void updateState4() {
      this.count9++;
   }

   private void applyIndex2(int count, int count7, float factor) {
      this.mc.getFramebuffer().renderElement();
      GlStateManager.viewport(0, 0, 256, 256);
      this.applyIndexByIndex(count, count7, factor);
      this.applyFloat(factor);
      int count10 = 3;
      DistanceEngine distance = WeightManager.getDistanceEngine();
      if (distance != null) {
         count10 = distance.getCount4();
      }

      for (int index = 0; index < count10; index++) {
         this.applyFloat(factor);
         this.applyFloat(factor);
      }

      this.mc.getFramebuffer().setState(true);
      GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      float factor2 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
      float factor4 = (float)this.height * factor2 / 256.0F;
      float factor5 = (float)this.width * factor2 / 256.0F;
      int count11 = this.width;
      int count12 = this.height;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(0.0, (double)count12, (double)this.factor)
         .getWorldRenderer6((double)(0.5F - factor4), (double)(0.5F + factor5))
         .resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)count11, (double)count12, (double)this.factor)
         .getWorldRenderer6((double)(0.5F - factor4), (double)(0.5F - factor5))
         .resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)count11, 0.0, (double)this.factor)
         .getWorldRenderer6((double)(0.5F + factor4), (double)(0.5F - factor5))
         .resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, (double)this.factor)
         .getWorldRenderer6((double)(0.5F + factor4), (double)(0.5F + factor5))
         .resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F)
         .updateState();
      tessellator.updateState();
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag && count == 12) {
         ISaveFormat iSaveFormat = this.mc.getISaveFormat();
         iSaveFormat.updateState();
         iSaveFormat.matchesText2("Demo_World");
         this.mc.displayGuiScreen(this);
      } else if (count == 13) {
         if (flag) {
            try {
               Class clazz = Class.forName("java.awt.Desktop");
               Object entry = clazz.getMethod("getDesktop").invoke(null);
               clazz.getMethod("browse", URI.class).invoke(entry, new URI(this.labelText4));
            } catch (Throwable throwable) {
               logger2.error("Couldn't open link", throwable);
            }
         }

         this.mc.displayGuiScreen(this);
      }
   }

   private void applyFloat(float factor) {
      this.mc.getTextureManager().applyResourceLocation3(this.resourceLocation5);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.applyFlag(true, true, true, false);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      GlStateManager.applyState10();
      int count = 3;
      DistanceEngine distance = WeightManager.getDistanceEngine();
      if (distance != null) {
         count = distance.getCount3();
      }

      for (int index = 0; index < count; index++) {
         float factor2 = 1.0F / (float)(index + 1);
         int count7 = this.width;
         int count10 = this.height;
         float factor4 = (float)(index - 3 / 2) / 256.0F;
         worldRenderer.getWorldRenderer((double)count7, (double)count10, (double)this.factor)
            .getWorldRenderer6((double)(0.0F + factor4), 1.0)
            .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor2)
            .updateState();
         worldRenderer.getWorldRenderer((double)count7, 0.0, (double)this.factor)
            .getWorldRenderer6((double)(1.0F + factor4), 1.0)
            .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor2)
            .updateState();
         worldRenderer.getWorldRenderer(0.0, 0.0, (double)this.factor)
            .getWorldRenderer6((double)(1.0F + factor4), 0.0)
            .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor2)
            .updateState();
         worldRenderer.getWorldRenderer(0.0, (double)count10, (double)this.factor)
            .getWorldRenderer6((double)(0.0F + factor4), 0.0)
            .resolveWorldRenderer(1.0F, 1.0F, 1.0F, factor2)
            .updateState();
      }

      tessellator.updateState();
      GlStateManager.applyState14();
      GlStateManager.applyFlag(true, true, true, true);
   }

   private void applyIndexByIndex(int count, int count7) {
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(11, this.width / 2 - 100, count, StatCollector.getText("menu.playdemo", new Object[0])));
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               12, this.width / 2 - 100, count + count7 * 1, StatCollector.getText("menu.resetdemo", new Object[0])
            )
         );
      if (this.mc.getISaveFormat().resolveRandomSeedManager("Demo_World") == null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
      }
   }

   @Override
   public void applyIndex(int count, int count7, float factor) {
      GlStateManager.applyState10();
      this.applyIndex2(count, count7, factor);
      GlStateManager.applyState14();
      Tessellator.getTessellator().getWorldRenderer();
      int count10 = this.width / 2 - 274 / 2;
      int count11 = -2130706433;
      int count12 = 16777215;
      int count13 = 0;
      int count14 = Integer.MIN_VALUE;
      DistanceEngine distance = WeightManager.getDistanceEngine();
      if (distance != null) {
         count11 = distance.getCount5();
         count12 = distance.getCount6();
         count13 = distance.getCount7();
         count14 = distance.getCount8();
      }

      if (count11 != 0 || count12 != 0) {
         this.applyIndex(0, 0, this.width, this.height, count11, count12);
      }

      if (count13 != 0 || count14 != 0) {
         this.applyIndex(0, 0, this.width, this.height, count13, count14);
      }

      this.mc.getTextureManager().applyResourceLocation3(resourceLocation6);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      if ((double)this.factor3 < 1.0E-4) {
         this.processAction(count10 + 0, 30, 0, 0, 99, 44);
         this.processAction(count10 + 99, 30, 129, 0, 27, 44);
         this.processAction(count10 + 99 + 26, 30, 126, 0, 3, 44);
         this.processAction(count10 + 99 + 26 + 3, 30, 99, 0, 26, 44);
         this.processAction(count10 + 155, 30, 0, 45, 155, 44);
      } else {
         this.processAction(count10 + 0, 30, 0, 0, 155, 44);
         this.processAction(count10 + 155, 30, 0, 45, 155, 44);
      }

      GlStateManager.pushMatrix();
      GlStateManager.translate((float)(this.width / 2 + 90), 70.0F, 0.0F);
      GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
      float factor2 = (1.8F - MathHelper.computeFactor(MathHelper.abs((float)(Minecraft.getTimestamp() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F))
         * 100.0F
         / (float)(this.fontRenderer.getIndex(this.labelText5) + 32);
      GlStateManager.translate3f3(factor2, factor2, factor2);
      applyFontRenderer4(this.fontRenderer, this.labelText5, 0, -8, -256);
      GlStateManager.popMatrix();
      String text = "Minecraft 1.8.9";
      if (this.mc.isEnabled14()) {
         text = text + " Demo";
      }

      this.applyFontRenderer(this.fontRenderer, text, 2, this.height - 10, -1);
      String text2 = "Copyright Mojang AB. Do not distribute!";
      this.applyFontRenderer(this.fontRenderer, text2, this.width - this.fontRenderer.getIndex(text2) - 2, this.height - 10, -1);
      if (this.labelText != null && this.labelText.length() > 0) {
         processAction(this.count3 - 2, this.count6 - 2, this.count8 + 2, this.count5 - 1, 1428160512);
         this.applyFontRenderer(this.fontRenderer, this.labelText, this.count3, this.count6, -1);
         this.applyFontRenderer(
            this.fontRenderer,
            this.labelText2,
            (this.width - this.count2) / 2,
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(0)).count3 - 12,
            -1
         );
      }

      super.applyIndex(count, count7, factor);
   }

   @Override
   public void processAction(int count, int count7, int count10) throws IOException {
      super.processAction(count, count7, count10);
      synchronized (this.dataRef) {
         if (this.labelText.length() > 0 && count >= this.count3 && count <= this.count8 && count7 >= this.count6 && count7 <= this.count5) {
            SplinePredictor splinePredictor = new SplinePredictor(this, this.labelText4, 13, true);
            splinePredictor.updateState5();
            this.mc.displayGuiScreen(splinePredictor);
         }
      }
   }

   public GuiMainMenu() {
      this.labelText5 = "missingno";
      BufferedReader reader = null;
      boolean flag = false ;

      guardBlock: {
         searchBlock: {
            try {
               flag = true;
               ArrayList list = Lists.newArrayList();
               reader = new BufferedReader(
                  new InputStreamReader(
                     Minecraft.getMinecraft().getIResourceManager().resolveIResourceByResourceLocation(resourceLocation7).getInputStream(), Charsets.UTF_8
                  )
               );

               String text;
               while ((text = reader.readLine()) != null) {
                  String text2 = text.trim();
                  if (!text2.isEmpty()) {
                     list.add(text2);
                  }
               }

               if (list.isEmpty()) {
                  flag = false;
               } else {
                  do {
                     this.labelText5 = (String)list.get(random.nextInt(list.size()));
                  } while (this.labelText5.hashCode() == 125780783);

                  flag = false;
               }
               break searchBlock;
            } catch (IOException ioException) {
               flag = false;
            } finally {
               if (flag) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (IOException ioException2) {
                     }
                  }
               }
            }

            if (reader != null) {
               try {
                  reader.close();
               } catch (IOException ioException3) {
               }
            }
            break guardBlock;
         }

         try {
            reader.close();
         } catch (IOException ioException4) {
         }
      }

      this.factor3 = random.nextFloat();
      this.labelText = "";
      if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.isEnabled4()) {
         this.labelText = StatCollector.getText("title.oldgl1", new Object[0]);
         this.labelText2 = StatCollector.getText("title.oldgl2", new Object[0]);
         this.labelText4 = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
      }
   }
}
