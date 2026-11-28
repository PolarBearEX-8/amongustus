package net.minecraft.client.gui;

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
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.ui.menu.AbstractMainMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.CustomPanorama;
import net.optifine.CustomPanoramaProperties;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
   public static final String MORE_INFO_TEXT = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
   private static final AtomicInteger THREAD_ID = new AtomicInteger(0);
   private static final Logger LOGGER2 = LogManager.getLogger();
   private static final Random random = new Random();
   private static final ResourceLocation SPLASHES_TEXTURE = new ResourceLocation("texts/splashes.txt");
   private static final ResourceLocation MINECRAFT_TEXTURE = new ResourceLocation("textures/gui/title/minecraft.png");
   private static final ResourceLocation[] TITLE_PANORAMA_PATHS = new ResourceLocation[]{
      new ResourceLocation("textures/gui/title/background/panorama_0.png"),
      new ResourceLocation("textures/gui/title/background/panorama_1.png"),
      new ResourceLocation("textures/gui/title/background/panorama_2.png"),
      new ResourceLocation("textures/gui/title/background/panorama_3.png"),
      new ResourceLocation("textures/gui/title/background/panorama_4.png"),
      new ResourceLocation("textures/gui/title/background/panorama_5.png")
   };
   private final Object threadLock = new Object();
   private float updateCounter;
   private String splashText;
   private GuiButton buttonResetDemo;
   private int panoramaTimer;
   private DynamicTexture viewportTexture;
   private boolean checkGLCaps = true;
   private String openGLWarning1;
   private String openGLWarning2 = MORE_INFO_TEXT;
   private String openGLWarningLink;
   private int openGLWarning2Width;
   private int openGLWarning1Width;
   private int openGLWarningX1;
   private int openGLWarningY1;
   private int openGLWarningX2;
   private int openGLWarningY2;
   private ResourceLocation backgroundTexture;
   private GuiButton realmsButton;

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
      }

      if (button.id == 5) {
         this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
      }

      if (button.id == 1) {
         this.mc.displayGuiScreen(new GuiSelectWorld(this));
      }

      if (button.id == 2) {
         this.mc.displayGuiScreen(new GuiMultiplayer(this));
      }

      if (button.id == 4) {
         this.mc.shutdown();
      }

      if (button.id == 11) {
         WorldSettings demoWorldSettings = DemoWorldServer.demoWorldSettings;
         String str = "Demo_World";
         String str2 = "Demo_World";
         this.mc.launchIntegratedServer(str2, str, demoWorldSettings);
      }

      if (button.id == 12) {
         WorldInfo worldInfo = this.mc.getSaveLoader().getWorldInfo("Demo_World");
         if (worldInfo != null) {
            GuiYesNo guiYesNo = GuiSelectWorld.makeDeleteWorldYesNo(this, worldInfo.getWorldName(), 12);
            this.mc.displayGuiScreen(guiYesNo);
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      GlStateManager.disableAlpha();
      this.renderSkybox(mouseX, mouseY, partialTicks);
      GlStateManager.enableAlpha();
      WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
      int n = this.width / 2 - ((short)274) / 2;
      byte b = 30;
      int n2 = -2130706433;
      int n3 = 16777215;
      int n4 = 0;
      int n5 = -2147483648;
      CustomPanoramaProperties customPanoramaProperties = CustomPanorama.getCustomPanoramaProperties();
      if (customPanoramaProperties != null) {
         n2 = customPanoramaProperties.getOverlay1Top();
         n3 = customPanoramaProperties.getOverlay1Bottom();
         n4 = customPanoramaProperties.getOverlay2Top();
         n5 = customPanoramaProperties.getOverlay2Bottom();
      }

      if (n2 != 0 || n3 != 0) {
         this.drawGradientRect(0, 0, this.width, this.height, n2, n3);
      }

      if (n4 != 0 || n5 != 0) {
         this.drawGradientRect(0, 0, this.width, this.height, n4, n5);
      }

      this.mc.getTextureManager().bindTexture(MINECRAFT_TEXTURE);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if ((double)this.updateCounter < 1.0E-4) {
         this.drawTexturedModalRect(n + 0, b + 0, 0, 0, 99, 44);
         this.drawTexturedModalRect(n + 99, b + 0, 129, 0, 27, 44);
         this.drawTexturedModalRect(n + 99 + 26, b + 0, 126, 0, 3, 44);
         this.drawTexturedModalRect(n + 99 + 26 + 3, b + 0, 99, 0, 26, 44);
         this.drawTexturedModalRect(n + 155, b + 0, 0, 45, 155, 44);
      } else {
         this.drawTexturedModalRect(n + 0, b + 0, 0, 0, 155, 44);
         this.drawTexturedModalRect(n + 155, b + 0, 0, 45, 155, 44);
      }

      GlStateManager.pushMatrix();
      GlStateManager.translate((float)(this.width / 2 + 90), 70.0F, 0.0F);
      GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
      float f = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
      f = f * 100.0F / (float)(this.fontRendererObj.getStringWidth(this.splashText) + 32);
      GlStateManager.scale(f, f, f);
      drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, -256);
      GlStateManager.popMatrix();
      String str = "Minecraft 1.8.9";
      if (this.mc.isDemo()) {
         str = str + " Demo";
      }

      this.drawString(this.fontRendererObj, str, 2, this.height - 10, -1);
      String str2 = "Copyright Mojang AB. Do not distribute!";
      this.drawString(this.fontRendererObj, str2, this.width - this.fontRendererObj.getStringWidth(str2) - 2, this.height - 10, -1);
      if (this.openGLWarning1 != null && this.openGLWarning1.length() > 0) {
         drawRect(this.openGLWarningX1 - 2, this.openGLWarningY1 - 2, this.openGLWarningX2 + 2, this.openGLWarningY2 - 1, 1428160512);
         this.drawString(this.fontRendererObj, this.openGLWarning1, this.openGLWarningX1, this.openGLWarningY1, -1);
         this.drawString(this.fontRendererObj, this.openGLWarning2, (this.width - this.openGLWarning2Width) / 2, ((GuiButton)this.buttonList.get(0)).yPosition - 12, -1);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      synchronized (this.threadLock) {
         if (this.openGLWarning1.length() > 0 && mouseX >= this.openGLWarningX1 && mouseX <= this.openGLWarningX2 && mouseY >= this.openGLWarningY1 && mouseY <= this.openGLWarningY2) {
            GuiConfirmOpenLink guiConfirmOpenLink = new GuiConfirmOpenLink(this, this.openGLWarningLink, 13, true);
            guiConfirmOpenLink.disableSecurityWarning();
            this.mc.displayGuiScreen((GuiScreen)guiConfirmOpenLink);
         }
      }
   }

   private void rotateAndBlurSkybox(float f) {
      this.mc.getTextureManager().bindTexture(this.backgroundTexture);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.colorMask(true, true, true, false);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      GlStateManager.disableAlpha();
      int n = 3;
      CustomPanoramaProperties customPanoramaProperties = CustomPanorama.getCustomPanoramaProperties();
      if (customPanoramaProperties != null) {
         n = customPanoramaProperties.getBlur2();
      }

      for (int i = 0; i < n; i++) {
         float f2 = 1.0F / (float)(i + 1);
         int width2 = this.width;
         int height2 = this.height;
         float f3 = (float)(i - ((byte)3) / 2) / 256.0F;
         worldRenderer.pos((double)width2, (double)height2, (double)this.zLevel).tex((double)(0.0F + f3), 1.0).color(1.0F, 1.0F, 1.0F, f2).endVertex();
         worldRenderer.pos((double)width2, 0.0, (double)this.zLevel).tex((double)(1.0F + f3), 1.0).color(1.0F, 1.0F, 1.0F, f2).endVertex();
         worldRenderer.pos(0.0, 0.0, (double)this.zLevel).tex((double)(1.0F + f3), 0.0).color(1.0F, 1.0F, 1.0F, f2).endVertex();
         worldRenderer.pos(0.0, (double)height2, (double)this.zLevel).tex((double)(0.0F + f3), 0.0).color(1.0F, 1.0F, 1.0F, f2).endVertex();
      }

      instance.draw();
      GlStateManager.enableAlpha();
      GlStateManager.colorMask(true, true, true, true);
   }

   @Override
   public void updateScreen() {
      this.panoramaTimer++;
   }

   @Override
   public void initGui() {
      if (!((Module)ClientManagers.moduleManager.invisMode).isToggled() || !ClientManagers.moduleManager.invisMode.shouldDisableCustomMainMenu()) {
         Minecraft.getMinecraft().displayGuiScreen(AbstractMainMenuScreen.createMainMenuScreen());
      }

      this.viewportTexture = new DynamicTexture(256, 256);
      this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
      Calendar instance = Calendar.getInstance();
      instance.setTime(new Date());
      if (instance.get(2) + 1 == 12 && instance.get(5) == 24) {
         this.splashText = "Merry X-mas!";
      } else if (instance.get(2) + 1 == 1 && instance.get(5) == 1) {
         this.splashText = "Happy new year!";
      } else if (instance.get(2) + 1 == 10 && instance.get(5) == 31) {
         this.splashText = "OOoooOOOoooo! Spooky!";
      }

      int n = this.height / 4 + 48;
      if (this.mc.isDemo()) {
         this.addDemoButtons(n, 24);
      } else {
         this.addSingleplayerMultiplayerButtons(n, 24);
      }

      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, n + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
      this.buttonList.add(new GuiButton(4, this.width / 2 + 2, n + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
      this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, n + 72 + 12));
      synchronized (this.threadLock) {
         this.openGLWarning1Width = this.fontRendererObj.getStringWidth(this.openGLWarning1);
         this.openGLWarning2Width = this.fontRendererObj.getStringWidth(this.openGLWarning2);
         int n2 = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
         this.openGLWarningX1 = (this.width - n2) / 2;
         this.openGLWarningY1 = ((GuiButton)this.buttonList.get(0)).yPosition - 24;
         this.openGLWarningX2 = this.openGLWarningX1 + n2;
         this.openGLWarningY2 = this.openGLWarningY1 + 24;
      }

      this.mc.setConnectedToRealms(false);
   }

   private void addSingleplayerMultiplayerButtons(int n, int n2) {
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, n, I18n.format("menu.singleplayer", new Object[0])));
      this.buttonList.add(new GuiButton(2, this.width / 2 - 100, n + n2 * 1, I18n.format("menu.multiplayer", new Object[0])));
      this.buttonList.add(this.realmsButton = new GuiButton(14, this.width / 2 - 100, n + n2 * 2, I18n.format("menu.online", new Object[0])));
   }

   private void renderSkybox(int n, int n2, float f) {
      this.mc.getFramebuffer().unbindFramebuffer();
      GlStateManager.viewport(0, 0, 256, 256);
      this.drawPanorama(n, n2, f);
      this.rotateAndBlurSkybox(f);
      int n3 = 3;
      CustomPanoramaProperties customPanoramaProperties = CustomPanorama.getCustomPanoramaProperties();
      if (customPanoramaProperties != null) {
         n3 = customPanoramaProperties.getBlur3();
      }

      for (int i = 0; i < n3; i++) {
         this.rotateAndBlurSkybox(f);
         this.rotateAndBlurSkybox(f);
      }

      this.mc.getFramebuffer().bindFramebuffer(true);
      GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      float f2 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
      float f3 = (float)this.height * f2 / 256.0F;
      float f4 = (float)this.width * f2 / 256.0F;
      int width2 = this.width;
      int height2 = this.height;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(0.0, (double)height2, (double)this.zLevel).tex((double)(0.5F - f3), (double)(0.5F + f4)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      worldRenderer.pos((double)width2, (double)height2, (double)this.zLevel).tex((double)(0.5F - f3), (double)(0.5F - f4)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      worldRenderer.pos((double)width2, 0.0, (double)this.zLevel).tex((double)(0.5F + f3), (double)(0.5F - f4)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      worldRenderer.pos(0.0, 0.0, (double)this.zLevel).tex((double)(0.5F + f3), (double)(0.5F + f4)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      instance.draw();
   }

   public GuiMainMenu() {
      this.splashText = "missingno";
      BufferedReader reader = null;

      try {
         ArrayList list = Lists.newArrayList();
         reader = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(SPLASHES_TEXTURE).getInputStream(), Charsets.UTF_8));

         String str;
         while ((str = reader.readLine()) != null) {
            str = str.trim();
            if (!str.isEmpty()) {
               list.add(str);
            }
         }

         if (!list.isEmpty()) {
            do {
               this.splashText = (String)list.get(random.nextInt(list.size()));
            } while (this.splashText.hashCode() == 125780783);
         }
      } catch (IOException e) {
      } finally {
         if (reader != null) {
            try {
               reader.close();
            } catch (IOException e2) {
            }
         }
      }

      this.updateCounter = random.nextFloat();
      this.openGLWarning1 = "";
      if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
         this.openGLWarning1 = I18n.format("title.oldgl1", new Object[0]);
         this.openGLWarning2 = I18n.format("title.oldgl2", new Object[0]);
         this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
      }
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   @Override
   public void confirmClicked(boolean flag, int n) {
      if (flag && n == 12) {
         ISaveFormat saveLoader = this.mc.getSaveLoader();
         saveLoader.flushCache();
         saveLoader.deleteWorldDirectory("Demo_World");
         this.mc.displayGuiScreen(this);
      } else if (n == 13) {
         if (flag) {
            try {
               Class clazz = Class.forName("java.awt.Desktop");
               Object obj = clazz.getMethod("getDesktop").invoke(null);
               clazz.getMethod("browse", URI.class).invoke(obj, new URI(this.openGLWarningLink));
            } catch (Throwable e) {
               LOGGER2.error("Couldn't open link", e);
            }
         }

         this.mc.displayGuiScreen(this);
      }
   }

   private void drawPanorama(int n, int n2, float f) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.disableCull();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      byte b = 8;
      int n3 = 64;
      CustomPanoramaProperties customPanoramaProperties = CustomPanorama.getCustomPanoramaProperties();
      if (customPanoramaProperties != null) {
         n3 = customPanoramaProperties.getBlur1();
      }

      for (int i = 0; i < n3; i++) {
         GlStateManager.pushMatrix();
         GlStateManager.translate(((float)(i % b) / (float)b - 0.5F) / 64.0F, ((float)(i / b) / (float)b - 0.5F) / 64.0F, 0.0F);
         GlStateManager.rotate(MathHelper.sin(((float)this.panoramaTimer + f) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(-((float)this.panoramaTimer + f) * 0.1F, 0.0F, 1.0F, 0.0F);

         for (int j = 0; j < 6; j++) {
            GlStateManager.pushMatrix();
            if (j == 1) {
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (j == 2) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (j == 3) {
               GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (j == 4) {
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (j == 5) {
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            ResourceLocation[] locations = TITLE_PANORAMA_PATHS;
            if (customPanoramaProperties != null) {
               locations = customPanoramaProperties.getPanoramaLocations();
            }

            this.mc.getTextureManager().bindTexture(locations[j]);
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            int n4 = 255 / (i + 1);
            float f5 = 0.0F;
            worldRenderer.pos(-1.0, -1.0, 1.0).tex(0.0, 0.0).color(255, 255, 255, n4).endVertex();
            worldRenderer.pos(1.0, -1.0, 1.0).tex(1.0, 0.0).color(255, 255, 255, n4).endVertex();
            worldRenderer.pos(1.0, 1.0, 1.0).tex(1.0, 1.0).color(255, 255, 255, n4).endVertex();
            worldRenderer.pos(-1.0, 1.0, 1.0).tex(0.0, 1.0).color(255, 255, 255, n4).endVertex();
            instance.draw();
            GlStateManager.popMatrix();
         }

         GlStateManager.popMatrix();
         GlStateManager.colorMask(true, true, true, false);
      }

      worldRenderer.setTranslation(0.0, 0.0, 0.0);
      GlStateManager.colorMask(true, true, true, true);
      GlStateManager.matrixMode(5889);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.enableCull();
      GlStateManager.enableDepth();
   }

   private void addDemoButtons(int n, int n2) {
      this.buttonList.add(new GuiButton(11, this.width / 2 - 100, n, I18n.format("menu.playdemo", new Object[0])));
      this.buttonList.add(this.buttonResetDemo = new GuiButton(12, this.width / 2 - 100, n + n2 * 1, I18n.format("menu.resetdemo", new Object[0])));
      if (this.mc.getSaveLoader().getWorldInfo("Demo_World") == null) {
         this.buttonResetDemo.enabled = false;
      }
   }

}
