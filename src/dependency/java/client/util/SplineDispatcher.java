package client.util;

import client.event.OptionsShadersPreRenderNotifier;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.MovementKeybindPropeAuxiliaryTexturesGuiIc;
import client.gui.NativeGuiScreenAdapter;
import client.gui.OptifineTexturesAuxiliaryTexturesGuiIc;
import client.render.AllocateTextureMapManager;
import client.setting.GameSettings;
import client.setting.MovementKeybindProperty;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import org.lwjgl.Sys;

public class SplineDispatcher extends NativeGuiScreenAdapter {
   public GuiScreen guiScreen2;
   public String labelText = "Shaders";
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new OptionsShadersPreRenderNotifier());
   public int count2 = -1;
   public OptionsShadersExtendedMinecraftEntry optionsShadersExtendedMinecraftEntry;
   public boolean enabled6 = false;
   public static float[] factorArray = new float[]{0.5F, 0.6F, 0.6666667F, 0.75F, 0.8333333F, 0.9F, 1.0F, 1.1666666F, 1.3333334F, 1.5F, 1.6666666F, 1.8F, 2.0F};
   public static String[] stringArray = new String[]{"0.5x", "0.6x", "0.66x", "0.75x", "0.83x", "0.9x", "1x", "1.16x", "1.33x", "1.5x", "1.66x", "1.8x", "2x"};
   public static float factor3 = 1.0F;
   public static float[] factorArray2 = new float[]{0.0625F, 0.125F, 0.25F};
   public static String[] stringArray2 = new String[]{"0.5x", "1x", "2x"};
   public static float factor4 = 0.125F;
   public static final int count3 = 0;
   public static final int count4 = 1;
   public static final int count5 = 2;
   public static final int count6 = 3;
   public static final int count7 = 4;

   public void updateState() {
      boolean flag = BuildManager.canExecute();

      for (AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter : (Iterable<AuxiliaryTexturesGuiIconsPngManagerAdapter>)(this.list)) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 != 201 && auxiliaryTexturesGuiIconsPngManagerAdapter.count2 != 202 && auxiliaryTexturesGuiIconsPngManagerAdapter.count2 != 210 && auxiliaryTexturesGuiIconsPngManagerAdapter.count2 != MovementKeybindProperty.ANTIALIASING.ordinal()) {
            auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = flag;
         }
      }
   }

   public static String resolveTextByFloat(float factor) {
      return resolveText(factor, factorArray, stringArray);
   }

   public static String resolveText(float factor, float[] factorArray3, String[] textArray) {
      int count = computeCount(factor, factorArray3);
      return textArray[count];
   }

   public static String resolveText(float factor) {
      return resolveText(factor, factorArray2, stringArray2);
   }

   public static int computeCount(float factor, float[] factorArray3) {
      for (int index = 0; index < factorArray3.length; index++) {
         if (factorArray3[index] >= factor) {
            return index;
         }
      }

      return factorArray3.length - 1;
   }

   public SplineDispatcher(GuiScreen screen, GameSettings gameSettings) {
      this.guiScreen2 = screen;
   }

   private float computeFactor(float factor, float[] factorArray3, float factor2, boolean flag, boolean flag2) {
      if (flag2) {
         return factor2;
      } else {
         int count = computeCount(factor, factorArray3);
         if (flag) {
            if (++count >= factorArray3.length) {
               count = 0;
            }
         } else if (--count < 0) {
            count = factorArray3.length - 1;
         }

         return factorArray3[count];
      }
   }

   public Minecraft getMc() {
      return this.mc;
   }

   public static String resolveText(boolean flag) {
      String textAsText = GeneratorDefaultManager.getTextAsText();
      String text = GeneratorDefaultManager.getText4();
      return flag ? textAsText : text;
   }

   public boolean isActive4() {
      return isActiveAsFlag();
   }

   public void applyText(String text, int count, int count8, int count9) {
      applyFontRenderer4(this.fontRenderer, text, count, count8, count9);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsPByAuxiliaryTexture(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter, true);
   }

   @Override
   public void updateState4() {
      super.updateState4();
      this.count2--;
   }

   @Override
   public void enableRepeatEvents() {
      super.enableRepeatEvents();
      if (!this.enabled6) {
         PreEndHandManager.updateState25();
      }
   }

   public static int getCount() {
      String text = System.getProperty("os.name").toLowerCase();
      return text.contains("win")
         ? 1
         : (
            text.contains("mac")
               ? 2
               : (text.contains("solaris") ? 3 : (text.contains("sunos") ? 3 : (text.contains("linux") ? 4 : (text.contains("unix") ? 4 : 0))))
         );
   }

   public static String resolveText(int count) {
      return count == 2 ? "FXAA 2x" : (count == 4 ? "FXAA 4x" : GeneratorDefaultManager.getText4());
   }

   @Override
   public void applyIndex(int count, int count8, float factor) {
      this.renderElement3();
      this.optionsShadersExtendedMinecraftEntry.applyIndex(count, count8, factor);
      if (this.count2 <= 0) {
         this.optionsShadersExtendedMinecraftEntry.updateState4();
         this.count2 += 20;
      }

      applyFontRenderer4(this.fontRenderer, this.labelText + " ", this.width / 2, 15, 16777215);
      String text = "OpenGL: " + PreEndHandManager.labelText4 + ", " + PreEndHandManager.labelText10 + ", " + PreEndHandManager.labelText6;
      if (this.fontRenderer.getIndex(text) < this.width - 5) {
         applyFontRenderer4(this.fontRenderer, text, this.width / 2, this.height - 40, 8421504);
      } else {
         this.applyFontRenderer(this.fontRenderer, text, 5, this.height - 40, 8421504);
      }

      super.applyIndex(count, count8, factor);
      this.auxiliaryManager.applyList(count, count8, this.list);
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("of.options.shadersTitle", new Object[0]);
      if (PreEndHandManager.properties2 == null) {
         PreEndHandManager.updateState46();
      }

      int count = this.width - 120 - 10;
      int count8 = this.width - 120 - 20;
      this.optionsShadersExtendedMinecraftEntry = new OptionsShadersExtendedMinecraftEntry(this, count8, this.height, 30, this.height - 50, 16);
      this.optionsShadersExtendedMinecraftEntry.applyIndex2(7, 8);
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.ANTIALIASING, count, 30, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.NORMAL_MAP, count, 50, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.SPECULAR_MAP, count, 70, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.RENDER_RES_MUL, count, 90, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.SHADOW_RES_MUL, count, 110, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.HAND_DEPTH_MUL, count, 130, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.OLD_HAND_LIGHT, count, 150, 120, 20));
      this.list.add(new MovementKeybindPropeAuxiliaryTexturesGuiIc(MovementKeybindProperty.OLD_LIGHTING, count, 170, 120, 20));
      int count9 = Math.min(150, count8 / 2 - 10);
      int count10 = count8 / 4 - count9 / 2;
      int count11 = this.height - 25;
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               201, count10, count11, count9 - 22 + 1, 20, GeneratorDefaultManager.resolveText("of.options.shaders.shadersFolder")
            )
         );
      this.list.add(new OptifineTexturesAuxiliaryTexturesGuiIc(210, count10 + count9 - 22 - 1, count11));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               202, count8 / 4 * 3 - count9 / 2, this.height - 25, count9, 20, StatCollector.getText("gui.done", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               203, count, this.height - 25, 120, 20, GeneratorDefaultManager.resolveText("of.options.shaders.shaderOptions")
            )
         );
      this.updateState();
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.optionsShadersExtendedMinecraftEntry.updateState();
   }

   private void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter, boolean flag) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (!(auxiliaryTexturesGuiIconsPngManagerAdapter instanceof MovementKeybindPropeAuxiliaryTexturesGuiIc)) {
            if (!flag) {
               switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
                  case 201:
                     switch (getCount()) {
                        case 1:
                           String text = String.format("cmd.exe /C start \"Open file\" \"%s\"", PreEndHandManager.file2.getAbsolutePath());

                           try {
                              Runtime.getRuntime().exec(text);
                              return;
                           } catch (IOException ioException) {
                              ioException.printStackTrace();
                              break;
                           }
                        case 2:
                           try {
                              Runtime.getRuntime().exec(new String[]{"/usr/bin/open", PreEndHandManager.file2.getAbsolutePath()});
                              return;
                           } catch (IOException ioException2) {
                              ioException2.printStackTrace();
                           }
                     }

                     boolean flag2 = false;

                     try {
                        Class clazz = Class.forName("java.awt.Desktop");
                        Object entry = clazz.getMethod("getDesktop").invoke(null);
                        clazz.getMethod("browse", URI.class).invoke(entry, new File(this.mc.mcDataDir, "shaderpacks").toURI());
                     } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        flag2 = true;
                     }

                     if (flag2) {
                        BuildManager.applyText4("Opening via system class!");
                        Sys.openURL("file://" + PreEndHandManager.file2.getAbsolutePath());
                     }
                     break;
                  case 202:
                     PreEndHandManager.updateState25();
                     this.enabled6 = true;
                     GuiScreen guiScreen = this.guiScreen2;
                     this.mc.displayGuiScreen(guiScreen);
                     break;
                  case 203:
                     SplineDirector splineDirector = new SplineDirector(this, BuildManager.getGameSettings());
                     BuildManager.getMc().displayGuiScreen(splineDirector);
                     break;
                  case 210:
                     try {
                        Class clazz2 = Class.forName("java.awt.Desktop");
                        Object entry2 = clazz2.getMethod("getDesktop").invoke(null);
                        clazz2.getMethod("browse", URI.class).invoke(entry2, new URI("http://optifine.net/shaderPacks"));
                     } catch (Throwable throwable2) {
                        throwable2.printStackTrace();
                     }
                  case 204:
                  case 205:
                  case 206:
                  case 207:
                  case 208:
                  case 209:
                  default:
                     this.optionsShadersExtendedMinecraftEntry.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
               }
            }
         } else {
            MovementKeybindPropeAuxiliaryTexturesGuiIc movementKeybindPropeAuxiliaryTexturesGuiIc = (MovementKeybindPropeAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter;
            switch (SplineDispatcherMovementKeybindPropeSwitchMap.countArray[movementKeybindPropeAuxiliaryTexturesGuiIc.getMovementKeybindProperty().ordinal()]) {
               case 1:
                  PreEndHandManager.setEnabled(!flag);
                  if (this.isActive4()) {
                     PreEndHandManager.count5 = 0;
                  }

                  PreEndHandManager.updateState77();
                  break;
               case 2:
                  PreEndHandManager.enabled15 = !PreEndHandManager.enabled15;
                  if (this.isActive4()) {
                     PreEndHandManager.enabled15 = true;
                  }

                  PreEndHandManager.updateState77();
                  this.mc.getListenableFuture();
                  break;
               case 3:
                  PreEndHandManager.enabled37 = !PreEndHandManager.enabled37;
                  if (this.isActive4()) {
                     PreEndHandManager.enabled37 = true;
                  }

                  PreEndHandManager.updateState77();
                  this.mc.getListenableFuture();
                  break;
               case 4:
                  PreEndHandManager.factor31 = this.computeFactor(PreEndHandManager.factor31, factorArray, factor3, !flag, this.isActive4());
                  PreEndHandManager.updateState77();
                  PreEndHandManager.updateState41();
                  break;
               case 5:
                  PreEndHandManager.factor5 = this.computeFactor(PreEndHandManager.factor5, factorArray, factor3, !flag, this.isActive4());
                  PreEndHandManager.updateState77();
                  PreEndHandManager.updateState60();
                  break;
               case 6:
                  PreEndHandManager.factor20 = this.computeFactor(PreEndHandManager.factor20, factorArray2, factor4, !flag, this.isActive4());
                  PreEndHandManager.updateState77();
                  break;
               case 7:
                  PreEndHandManager.managerAdapter2.setEnabled(!flag);
                  if (this.isActive4()) {
                     PreEndHandManager.managerAdapter2.updateState();
                  }

                  PreEndHandManager.updateState77();
                  break;
               case 8:
                  PreEndHandManager.managerAdapter8.setEnabled(!flag);
                  if (this.isActive4()) {
                     PreEndHandManager.managerAdapter8.updateState();
                  }

                  PreEndHandManager.updateState53();
                  PreEndHandManager.updateState77();
                  this.mc.getListenableFuture();
                  break;
               case 9:
                  PreEndHandManager.enabled10 = !PreEndHandManager.enabled10;
                  break;
               case 10:
                  PreEndHandManager.enabled30 = !PreEndHandManager.enabled30;
                  break;
               case 11:
                  PreEndHandManager.count7 = (PreEndHandManager.count7 + 1) % 3;
                  PreEndHandManager.count17 = PreEndHandManager.count22 = PreEndHandManager.count7;
                  auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "Tex Min: " + PreEndHandManager.stringArray2[PreEndHandManager.count7];
                  AllocateTextureMapManager.renderElement();
                  break;
               case 12:
                  PreEndHandManager.count44 = (PreEndHandManager.count44 + 1) % 2;
                  auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "Tex_n Mag: " + PreEndHandManager.stringArray4[PreEndHandManager.count44];
                  AllocateTextureMapManager.renderElement();
                  break;
               case 13:
                  PreEndHandManager.count16 = (PreEndHandManager.count16 + 1) % 2;
                  auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "Tex_s Mag: " + PreEndHandManager.stringArray4[PreEndHandManager.count16];
                  AllocateTextureMapManager.renderElement();
                  break;
               case 14:
                  PreEndHandManager.enabled17 = !PreEndHandManager.enabled17;
                  auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = "ShadowClipFrustrum: " + resolveText(PreEndHandManager.enabled17);
                  AllocateTextureMapManager.renderElement();
            }

            movementKeybindPropeAuxiliaryTexturesGuiIc.renderElement();
         }
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter, false);
   }
}
