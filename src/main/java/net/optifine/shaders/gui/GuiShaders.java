package net.optifine.shaders.gui;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Lang;
import net.optifine.config.Config;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderEnumShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersTex;
import net.optifine.shaders.config.EnumShaderOption;
import org.lwjgl.Sys;

public class GuiShaders extends GuiScreenOF {
   public GuiScreen parentGui;
   public String screenTitle = "Shaders";
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderEnumShaderOptions());
   private int updateTimer = -1;
   private GuiSlotShaders shaderList;
   private boolean saved = false;
   private static float[] QUALITY_MULTIPLIERS = new float[]{0.5F, 0.6F, 0.6666667F, 0.75F, 0.8333333F, 0.9F, 1.0F, 1.1666666F, 1.3333334F, 1.5F, 1.6666666F, 1.8F, 2.0F};
   private static String[] QUALITY_MULTIPLIER_NAMES = new String[]{"0.5x", "0.6x", "0.66x", "0.75x", "0.83x", "0.9x", "1x", "1.16x", "1.33x", "1.5x", "1.66x", "1.8x", "2x"};
   private static float QUALITY_MULTIPLIER_DEFAULT = 1.0F;
   private static float[] HAND_DEPTH_VALUES = new float[]{0.0625F, 0.125F, 0.25F};
   private static String[] HAND_DEPTH_NAMES = new String[]{"0.5x", "1x", "2x"};
   private static float HAND_DEPTH_DEFAULT = 0.125F;
   public static final int EnumOS_UNKNOWN = 0;
   public static final int EnumOS_WINDOWS = 1;
   public static final int EnumOS_OSX = 2;
   public static final int EnumOS_SOLARIS = 3;
   public static final int EnumOS_LINUX = 4;

   public void actionPerformed(GuiButton button) {
      this.actionPerformed(button, false);
   }

   public static int getOSType() {
      String str = System.getProperty("os.name").toLowerCase();
      return str.contains("win")
         ? 1
         : (
            str.contains("mac")
               ? 2
               : (str.contains("solaris") ? 3 : (str.contains("sunos") ? 3 : (str.contains("linux") ? 4 : (str.contains("unix") ? 4 : 0))))
         );
   }

   public boolean hasShiftDown() {
      return isShiftKeyDown();
   }

   public void actionPerformedRightClick(GuiButton button) {
      this.actionPerformed(button, true);
   }

   public static String toStringQuality(float f) {
      return toStringValue(f, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_NAMES);
   }

   public static String toStringOnOff(boolean flag) {
      String on = Lang.getOn();
      String off = Lang.getOff();
      return flag ? on : off;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.shaderList.drawScreen(mouseX, mouseY, partialTicks);
      if (this.updateTimer <= 0) {
         this.shaderList.updateList();
         this.updateTimer += 20;
      }

      drawCenteredString(this.fontRendererObj, this.screenTitle + " ", this.width / 2, 15, 16777215);
      String str = "OpenGL: " + Shaders.glVersionString + ", " + Shaders.glVendorString + ", " + Shaders.glRendererString;
      if (this.fontRendererObj.getStringWidth(str) < this.width - 5) {
         drawCenteredString(this.fontRendererObj, str, this.width / 2, this.height - 40, 8421504);
      } else {
         this.drawString(this.fontRendererObj, str, 5, this.height - 40, 8421504);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
   }

   public void updateScreen() {
      super.updateScreen();
      this.updateTimer--;
   }

   public Minecraft getMc() {
      return this.mc;
   }

   public static int getValueIndex(float f, float[] floats) {
      for (int i = 0; i < floats.length; i++) {
         if (floats[i] >= f) {
            return i;
         }
      }

      return floats.length - 1;
   }

   public static String toStringHandDepth(float f) {
      return toStringValue(f, HAND_DEPTH_VALUES, HAND_DEPTH_NAMES);
   }

   private void actionPerformed(GuiButton button, boolean flag) {
      if (button.enabled) {
         if (!(button instanceof GuiButtonEnumShaderOption)) {
            if (!flag) {
               switch (button.id) {
                  case 201:
                     switch (getOSType()) {
                        case 1:
                           String str = String.format("cmd.exe /C start \"Open file\" \"%s\"", Shaders.shaderPacksDir.getAbsolutePath());

                           try {
                              Runtime.getRuntime().exec(str);
                              return;
                           } catch (IOException e) {
                              e.printStackTrace();
                              break;
                           }
                        case 2:
                           try {
                              Runtime.getRuntime().exec(new String[]{"/usr/bin/open", Shaders.shaderPacksDir.getAbsolutePath()});
                              return;
                           } catch (IOException e2) {
                              e2.printStackTrace();
                           }
                     }

                     boolean flag2 = false;

                     try {
                        Class clazz = Class.forName("java.awt.Desktop");
                        Object obj = clazz.getMethod("getDesktop").invoke(null);
                        clazz.getMethod("browse", URI.class).invoke(obj, new File(this.mc.mcDataDir, "shaderpacks").toURI());
                     } catch (Throwable e3) {
                        e3.printStackTrace();
                        flag2 = true;
                     }

                     if (flag2) {
                        Config.dbg("Opening via system class!");
                        Sys.openURL("file://" + Shaders.shaderPacksDir.getAbsolutePath());
                     }
                     break;
                  case 202:
                     Shaders.storeConfig();
                     this.saved = true;
                     GuiScreen parentGui2 = this.parentGui;
                     this.mc.displayGuiScreen(parentGui2);
                     break;
                  case 203:
                     GuiShaderOptions guiShaderOptions = new GuiShaderOptions(this, Config.getGameSettings());
                     Config.getMc().displayGuiScreen((GuiScreen)guiShaderOptions);
                     break;
                  case 210:
                     try {
                        Class clazz2 = Class.forName("java.awt.Desktop");
                        Object obj2 = clazz2.getMethod("getDesktop").invoke(null);
                        clazz2.getMethod("browse", URI.class).invoke(obj2, new URI("http://optifine.net/shaderPacks"));
                     } catch (Throwable e4) {
                        e4.printStackTrace();
                     }
                  case 204:
                  case 205:
                  case 206:
                  case 207:
                  case 208:
                  case 209:
                  default:
                     this.shaderList.actionPerformed(button);
               }
            }
         } else {
            GuiButtonEnumShaderOption option = (GuiButtonEnumShaderOption)button;
            switch (option.getEnumShaderOption()) {
               case ANTIALIASING:
                  Shaders.nextAntialiasingLevel(!flag);
                  if (this.hasShiftDown()) {
                     Shaders.configAntialiasingLevel = 0;
                  }

                  Shaders.uninit();
                  break;
               case NORMAL_MAP:
                  Shaders.configNormalMap = !Shaders.configNormalMap;
                  if (this.hasShiftDown()) {
                     Shaders.configNormalMap = true;
                  }

                  Shaders.uninit();
                  this.mc.scheduleResourcesRefresh();
                  break;
               case SPECULAR_MAP:
                  Shaders.configSpecularMap = !Shaders.configSpecularMap;
                  if (this.hasShiftDown()) {
                     Shaders.configSpecularMap = true;
                  }

                  Shaders.uninit();
                  this.mc.scheduleResourcesRefresh();
                  break;
               case RENDER_RES_MUL:
                  Shaders.configRenderResMul = this.getNextValue(Shaders.configRenderResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !flag, this.hasShiftDown());
                  Shaders.uninit();
                  Shaders.scheduleResize();
                  break;
               case SHADOW_RES_MUL:
                  Shaders.configShadowResMul = this.getNextValue(Shaders.configShadowResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !flag, this.hasShiftDown());
                  Shaders.uninit();
                  Shaders.scheduleResizeShadow();
                  break;
               case HAND_DEPTH_MUL:
                  Shaders.configHandDepthMul = this.getNextValue(Shaders.configHandDepthMul, HAND_DEPTH_VALUES, HAND_DEPTH_DEFAULT, !flag, this.hasShiftDown());
                  Shaders.uninit();
                  break;
               case OLD_HAND_LIGHT:
                  Shaders.configOldHandLight.nextValue(!flag);
                  if (this.hasShiftDown()) {
                     Shaders.configOldHandLight.resetValue();
                  }

                  Shaders.uninit();
                  break;
               case OLD_LIGHTING:
                  Shaders.configOldLighting.nextValue(!flag);
                  if (this.hasShiftDown()) {
                     Shaders.configOldLighting.resetValue();
                  }

                  Shaders.updateBlockLightLevel();
                  Shaders.uninit();
                  this.mc.scheduleResourcesRefresh();
                  break;
               case TWEAK_BLOCK_DAMAGE:
                  Shaders.configTweakBlockDamage = !Shaders.configTweakBlockDamage;
                  break;
               case CLOUD_SHADOW:
                  Shaders.configCloudShadow = !Shaders.configCloudShadow;
                  break;
               case TEX_MIN_FIL_B:
                  Shaders.configTexMinFilB = (Shaders.configTexMinFilB + 1) % 3;
                  Shaders.configTexMinFilN = Shaders.configTexMinFilS = Shaders.configTexMinFilB;
                  button.displayString = "Tex Min: " + Shaders.texMinFilDesc[Shaders.configTexMinFilB];
                  ShadersTex.updateTextureMinMagFilter();
                  break;
               case TEX_MAG_FIL_N:
                  Shaders.configTexMagFilN = (Shaders.configTexMagFilN + 1) % 2;
                  button.displayString = "Tex_n Mag: " + Shaders.texMagFilDesc[Shaders.configTexMagFilN];
                  ShadersTex.updateTextureMinMagFilter();
                  break;
               case TEX_MAG_FIL_S:
                  Shaders.configTexMagFilS = (Shaders.configTexMagFilS + 1) % 2;
                  button.displayString = "Tex_s Mag: " + Shaders.texMagFilDesc[Shaders.configTexMagFilS];
                  ShadersTex.updateTextureMinMagFilter();
                  break;
               case SHADOW_CLIP_FRUSTRUM:
                  Shaders.configShadowClipFrustrum = !Shaders.configShadowClipFrustrum;
                  button.displayString = "ShadowClipFrustrum: " + toStringOnOff(Shaders.configShadowClipFrustrum);
                  ShadersTex.updateTextureMinMagFilter();
            }

            option.updateButtonText();
         }
      }
   }

   private float getNextValue(float f, float[] floats, float f2, boolean flag, boolean flag2) {
      if (flag2) {
         return f2;
      } else {
         int valueIndex = getValueIndex(f, floats);
         if (flag) {
            if (++valueIndex >= floats.length) {
               valueIndex = 0;
            }
         } else if (--valueIndex < 0) {
            valueIndex = floats.length - 1;
         }

         return floats[valueIndex];
      }
   }

   public GuiShaders(GuiScreen screen, GameSettings gameSettings) {
      this.parentGui = screen;
   }

   public void drawCenteredString(String str, int n, int n2, int n3) {
      drawCenteredString(this.fontRendererObj, str, n, n2, n3);
   }

   public void updateButtons() {
      boolean isShaderPackLoaded = Config.isShaderPackLoaded();

      for (GuiButton button : (Iterable<GuiButton>)(this.buttonList)) {
         if (button.id != 201 && button.id != 202 && button.id != 210 && button.id != EnumShaderOption.ANTIALIASING.ordinal()) {
            button.enabled = isShaderPackLoaded;
         }
      }
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if (!this.saved) {
         Shaders.storeConfig();
      }
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.shaderList.handleMouseInput();
   }

   public void initGui() {
      this.screenTitle = I18n.format("of.options.shadersTitle", new Object[0]);
      if (Shaders.shadersConfig == null) {
         Shaders.loadConfig();
      }

      byte b = 120;
      byte b2 = 20;
      int n = this.width - b - 10;
      byte b3 = 30;
      byte b4 = 20;
      int n2 = this.width - b - 20;
      this.shaderList = new GuiSlotShaders(this, n2, this.height, b3, this.height - 50, 16);
      this.shaderList.registerScrollButtons(7, 8);
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.ANTIALIASING, n, 0 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.NORMAL_MAP, n, 1 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.SPECULAR_MAP, n, 2 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.RENDER_RES_MUL, n, 3 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.SHADOW_RES_MUL, n, 4 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.HAND_DEPTH_MUL, n, 5 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_HAND_LIGHT, n, 6 * b4 + b3, b, b2));
      this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_LIGHTING, n, 7 * b4 + b3, b, b2));
      int n3 = Math.min(150, n2 / 2 - 10);
      int n4 = n2 / 4 - n3 / 2;
      int n5 = this.height - 25;
      this.buttonList.add(new GuiButton(201, n4, n5, n3 - 22 + 1, b2, Lang.get("of.options.shaders.shadersFolder")));
      this.buttonList.add(new GuiButtonDownloadShaders(210, n4 + n3 - 22 - 1, n5));
      this.buttonList.add(new GuiButton(202, n2 / 4 * 3 - n3 / 2, this.height - 25, n3, b2, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(203, n, this.height - 25, b, b2, Lang.get("of.options.shaders.shaderOptions")));
      this.updateButtons();
   }

   public static String toStringAa(int n) {
      return n == 2 ? "FXAA 2x" : (n == 4 ? "FXAA 4x" : Lang.getOff());
   }

   public static String toStringValue(float f, float[] floats, String[] strings) {
      return strings[getValueIndex(f, floats)];
   }

}
