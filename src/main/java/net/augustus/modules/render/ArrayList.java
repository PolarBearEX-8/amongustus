package net.augustus.modules.render;

import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.font.DynamicFontUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.TextInputSetting;
import net.augustus.settings.ActionSetting;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.Augustus;
import net.augustus.utils.render.GlStateSaver;
import net.augustus.ui.GuiIngameHook;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.GuiScaleListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.EasingType;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class ArrayList extends Module implements GuiScaleListener, RenderListener {
   private final java.util.ArrayList visibleModules;
   private boolean needsAnimationReset;
   private final DoubleValue xPos;
   private final DoubleValue yPos;
   public final StringValue side;
   public final StringValue sorting;
   public final StringValue suffix;
   public final TextInputSetting customSuffix;
   public final StringValue suffixCasing;
   public final StringValue casing;
   public final BooleanValue spacing;
   public final ColorSetting suffixColor;
   public final BooleanValue randomColor;
   public final ColorSetting customColor;
   public final BooleanValue backgound;
   public final ColorSetting backgroundColor;
   public final BooleanValue line;
   public final DoubleValue lineWidth;
   public final DoubleValue lineGap;
   public final BooleanValue customLineColor;
   public final ColorSetting lineColor;
   public final BlurShadowSettings blurShadowSettings;
   public final StringValue font;
   public final ActionSetting openCustomFontFolder;
   public final BooleanValue bold;
   public final DoubleValue verticalSpacing;
   public final DoubleValue scale;
   public final DoubleValue fontSize;
   public final BooleanValue fontShadow;
   public final StringValue toggleAnimationMode;
   public final DoubleValue animationTime;
   private final GlStateSaver glStateSaver;
   private DynamicFontUtil customFont;
   private final FontRenderer fontRenderer;
   private boolean animationsEnabled;
   private int lastFontSize;

   public ArrayList() {
       super("ArrayList", "Modulliste", Color.CYAN, Categorys.RENDER);
      this.visibleModules = new java.util.ArrayList();
      this.needsAnimationReset = false;
      this.xPos = new DoubleValue(this, "X-Pos", 100.0, 0.0, 100.0, 2);
      this.yPos = new DoubleValue(this, "Y-Pos", 0.0, 0.0, 100.0, 2);
      this.side = new StringValue(this, "Side", "Right", new String[]{"Left", "Right"});
      this.sorting = new StringValue(this, "Sorting", "Length", new String[]{"Alphabet", "Length"});
      this.suffix = new StringValue(this, "Suffix", "Dash", new String[]{"None", "Simple", "Dash", "Bracket", "Colon", "Custom"});
      this.customSuffix = (TextInputSetting)new TextInputSetting(this, "CustomSuffix", "?").withVisibility(() -> this.isCustomSuffixVisible());
      StringValue setting = (StringValue)new StringValue(this, "SuffixCasing", "Default", new String[]{"Default", "UpperCase", "LowerCase"}).withVisibility(() -> this.isSuffixCasingVisible());
      SettingChangeListener listener = () -> this.recalculateModuleDimensions();
      this.suffixCasing = (StringValue)setting.withChangeListener(listener);
      StringValue setting2 = new StringValue(this, "Casing", "Default", new String[]{"Default", "UpperCase", "LowerCase"});
      listener = () -> this.recalculateModuleDimensions();
      this.casing = (StringValue)setting2.withChangeListener(listener);
      BooleanValue booleanValue = new BooleanValue(this, "Spacing", false);
      listener = () -> this.recalculateModuleDimensions();
      this.spacing = (BooleanValue)booleanValue.withChangeListener(listener);
      this.suffixColor = (ColorSetting)new ColorSetting(this, "SuffixColor", Color.white).withVisibility(() -> this.isSuffixColorVisible());
      this.randomColor = new BooleanValue(this, "RandomColor", false);
      this.customColor = ((ColorSetting)new ColorSetting(this, "CustomColor", Augustus.getInstance().getClientColor()).withVisibility(() -> this.isCustomColorVisible())).withFadeDefault(true).withDefaultFadeColor(new Color(1, 254, 177)).withDefaultFadeDistance((byte)50).withDefaultFadeSpeed((byte)43);
      this.backgound = (BooleanValue)new BooleanValue(this, "Backgound", true).withDisplayName("BackGround");
      ColorSetting colorSetting7 = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 119));
      BooleanValue backgound2 = this.backgound;
      BooleanValue booleanValue3 = backgound2;
      this.backgroundColor = (ColorSetting)colorSetting7.withVisibility(() -> booleanValue3.getValue());
      this.line = new BooleanValue(this, "Line", false);
      DoubleValue lineWidthSetting = new DoubleValue(this, "LineWidth", 3.0, 0.0, 10.0, 0);
      backgound2 = this.line;
      BooleanValue booleanValue4 = backgound2;
      this.lineWidth = lineWidthSetting.visibleWhen(() -> booleanValue4.getValue());
      DoubleValue lineGapSetting = new DoubleValue(this, "LineGap", 0.0, 0.0, 10.0, 0);
      backgound2 = this.line;
      BooleanValue booleanValue5 = backgound2;
      this.lineGap = lineGapSetting.visibleWhen(() -> booleanValue5.getValue());
      BooleanValue booleanValue6 = new BooleanValue(this, "CustomLineColor", false);
      backgound2 = this.line;
      BooleanValue booleanValue7 = backgound2;
      this.customLineColor = (BooleanValue)booleanValue6.withVisibility(() -> booleanValue7.getValue());
      this.lineColor = (ColorSetting)new ColorSetting(this, "LineColor", Color.BLUE).withVisibility(() -> this.isLineColorVisible());
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Augustus.getInstance().getClientColor());
      StringValue setting3 = new StringValue(
         this,
         "Font",
         "Minecraft",
         new String[]{
            "Minecraft",
            "Verdana",
            "Arial",
            "Roboto",
            "Comfortaa",
            "Esp",
            "GoldBil",
            "Jello",
            "ProductSans",
            "MuseoSans",
            "Borel",
            "Rubik",
            "RubikBold",
            "Tahoma",
            "TahomaBold"
         }
      );
      listener = () -> this.updateSelectedFont();
      this.font = (StringValue)setting3.withChangeListener(listener);
      this.openCustomFontFolder = new ActionSetting(this, "Open custom font folder", () -> openCustomFontsFolder());
      BooleanValue booleanValue8 = new BooleanValue(this, "Bold", false);
      SettingVisibilityCondition condition10 = () -> {
         return this.font.getSelectedValue().equals("Minecraft");
      };
      BooleanValue booleanValue9 = (BooleanValue)booleanValue8.withVisibility(condition10);
      listener = () -> this.recalculateModuleDimensions();
      this.bold = (BooleanValue)booleanValue9.withChangeListener(listener);
      this.verticalSpacing = new DoubleValue(this, "VerticalSpacing", 1.0, -5.0, 5.0, 0);
      this.scale = new DoubleValue(this, "Scale", 2.0, 0.5, 4.0, 2).visibleWhen(() -> this.isMinecraftScaleVisible());
      this.fontSize = new DoubleValue(this, "FontSize", 16.0, 16.0, 80.0, 0).visibleWhen(() -> this.isFontSizeVisible());
      this.fontShadow = new BooleanValue(this, "FontShadow", true);
      StringValue setting4 = new StringValue(this, "ToggleAnimation", "easeInOutBack", AnimationUtil.getEasingNames(true));
      listener = () -> this.needsAnimationReset = true;
      this.toggleAnimationMode = (StringValue)setting4.withChangeListener(listener);
      this.animationTime = new DoubleValue(this, "AnimationTime", 520.0, 0.0, 2000.0, 0).visibleWhen(() -> this.isAnimationTimeVisible());
      this.glStateSaver = GlStateSaver.create();
      this.customFont = FontManager.robotoArrayList;
      this.animationsEnabled = true;
      this.lastFontSize = 0;
      this.fontRenderer = mc.fontRendererObj;
      this.refreshFontOptions();
   }

   @Override
   public void onEnable() {
      this.needsAnimationReset = true;
      this.updateSelectedFont();
      this.recalculateModuleDimensions();
      FontManager.reloadCustomFonts();
      this.refreshFontOptions();
   }

   @Override
   public void onDisable() {
      this.visibleModules.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(11);
      this.toggleEventSubscription(32, 1);
   }

   @Override
   public void onGuiScale() {
      ClientManagers.moduleManager.sortModules();
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (!mc.gameSettings.showDebugInfo) {
         this.visibleModules.clear();
         this.animationsEnabled = !this.toggleAnimationMode.getSelectedValue().equals("None");
         boolean flag = this.side.getSelectedValue().equals("Left");
         if ((double)this.lastFontSize != this.fontSize.getValue()) {
            this.customFont.setFontSize((int)this.fontSize.getValue());
            this.recalculateModuleDimensions();
         }

         this.lastFontSize = (int)this.fontSize.getValue();
         EasingType easingType = AnimationUtil.parseEasingType(this.toggleAnimationMode.getSelectedValue());

         for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getSortedModules())) {
            if (module.isShownInArrayList()) {
               if (this.needsAnimationReset && module.isToggled()) {
                  module.toggleAnimation.start((int)this.animationTime.getValue(), easingType, module.isToggled() ? 1 : 0, false);
               }

               if (!this.animationsEnabled ? module.isToggled() : module.toggleAnimation.getLinearProgress() < 1.0 || module.isToggled()) {
                  this.visibleModules.add(module);
               }
            }
         }

         int n = this.line.getValue() ? Math.max(0, (int)this.lineWidth.getValue()) : 0;
         int value2 = (int)this.verticalSpacing.getValue();
         int n2 = (int)Math.max(0.0, this.lineGap.getValue());
         this.needsAnimationReset = false;
         BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(
            false,
            shaderRenderPass -> {
               GL11.glPushMatrix();
               boolean flag2 = this.font.getSelectedValue().equals("Minecraft");
               GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
               GL11.glTranslated(
                  (double)((int)(this.xPos.getValue() / 100.0 * (double)mc.displayWidth)),
                  (double)((int)(this.yPos.getValue() / 100.0 * (double)mc.displayHeight)),
                  0.0
               );
               if (flag2) {
                  GL11.glScaled(this.scale.getValue(), this.scale.getValue(), 1.0);
               }

               float f2 = (float)((int)((float)((double)(GuiIngameHook.hudTopOffsetY != 0.0F ? 1 : 0) + (double)GuiIngameHook.hudTopOffsetY * (1.0 / this.scale.getValue()))));
               int n3 = 0;
               boolean flag3 = !this.suffix.getSelectedValue().equals("None");
               int n4 = (flag2 ? this.fontRenderer.FONT_HEIGHT : Math.round(this.customFont.getFontHeight())) + value2 * 2;
               GlStateManager.enableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
               if (this.backgound.getValue()) {
                  this.glStateSaver.setTexture2D(false);
                  if (shaderRenderPass == ShaderRenderPass.SHADOW) {
                     RenderUtil.setGlColorWithAlpha(this.blurShadowSettings.resolveShadowColor(this.backgroundColor.getColorInt(n3)), 1.0F);
                  } else {
                     RenderUtil.setGlColor(this.backgroundColor.getColorInt(n3));
                  }

                  GL11.glBegin(7);

                  for (Module module2 : (Iterable<Module>)(this.visibleModules)) {
                     int n5 = 0;
                     if (!flag) {
                        n5 = (int)((double)n5 - module2.getFullDisplayWidth());
                     }

                     if (this.animationsEnabled) {
                        if (flag) {
                           module2.toggleAnimation.setBounds((float)((double)n5 - module2.getFullDisplayWidth()), f2 - (float)n4, (float)n5, f2);
                        } else {
                           module2.toggleAnimation.setBounds((float)((double)n5 + module2.getFullDisplayWidth()), f2 - (float)n4, (float)n5, f2);
                        }

                        n5 = (int)module2.toggleAnimation.updateX();
                     }

                     int n6 = flag ? n : -n;
                     RenderUtil.emitQuadVertices2D(
                        (double)(n5 - 3 + n6),
                        (double)(f2 - (float)value2),
                        (double)(n5 + (int)module2.getFullDisplayWidth() + 3 + n6),
                        (double)(f2 + (float)n4 - (float)value2)
                     );
                     if (this.animationsEnabled) {
                        f2 = module2.toggleAnimation.updateY();
                     }

                     f2 += (float)n4;
                  }

                  GL11.glEnd();
                  this.glStateSaver.restoreTexture2D();
               }

               if (shaderRenderPass == ShaderRenderPass.NONE || shaderRenderPass == ShaderRenderPass.SHADOW && !this.backgound.getValue()) {
                  f2 = (float)((int)((float)((double)(GuiIngameHook.hudTopOffsetY != 0.0F ? 1 : 0) + (double)GuiIngameHook.hudTopOffsetY * (1.0 / this.scale.getValue()))));

                  for (Module module3 : (Iterable<Module>)(this.visibleModules)) {
                     int colorInt2 = this.customColor.getColorInt(n3);
                     int n7 = 0;
                     if (!flag) {
                        n7 = (int)((double)n7 - module3.getFullDisplayWidth());
                     }

                     if (this.animationsEnabled) {
                        if (flag) {
                           module3.toggleAnimation.setBounds((float)((double)n7 - module3.getFullDisplayWidth()), f2 - (float)n4, (float)n7, f2);
                        } else {
                           module3.toggleAnimation.setBounds((float)((double)n7 + module3.getFullDisplayWidth()), f2 - (float)n4, (float)n7, f2);
                        }

                        n7 = (int)module3.toggleAnimation.updateX();
                        if (module3.toggleAnimation.getEasedProgress() == 1.0) {
                           f2 = module3.toggleAnimation.updateY();
                        }
                     }

                     if (this.randomColor.getValue()) {
                        colorInt2 = module3.getColor().getRGB();
                     }

                     if (this.line.getValue()) {
                        GlStateManager.resetColor();
                        int n8 = colorInt2;
                        if (this.customLineColor.getValue()) {
                           n8 = this.lineColor.getColorInt(n3);
                        }

                        n8 = this.blurShadowSettings.resolvePassColor(shaderRenderPass, n8, n3);
                        if (flag) {
                           RenderUtil.drawRect(
                              n7 - 3,
                              (int)f2 - value2 + n2,
                              n7 + n - 3,
                              (int)f2 + (int)module3.getDisplayHeight() + value2 - n2,
                              n8
                           );
                        } else {
                           RenderUtil.drawRect(
                              n7 + (int)module3.getFullDisplayWidth() + 3,
                              (int)f2 - value2 + n2,
                              n7 + (int)module3.getFullDisplayWidth() - n + 3,
                              (int)f2 + (int)module3.getDisplayHeight() + value2 - n2,
                              n8
                           );
                        }
                     }

                     int n9 = flag ? n : -n;
                     n7 += n9;
                     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
                     if (flag2) {
                        if (flag3) {
                           this.fontRenderer
                              .drawStringFiltered(
                                 this.bold.getValue() ? "§l" + module3.getDisplayName() : module3.getDisplayName(),
                                 (float)n7,
                                 f2 + 1.0F,
                                 this.blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt2, n3),
                                 this.fontShadow.getValue(),
                                 false,
                                 true
                              );
                           String formattedSuffix2 = module3.getFormattedSuffix();
                           if (!formattedSuffix2.isEmpty()) {
                              this.fontRenderer.drawStringFiltered(this.bold.getValue() ? "§l" + formattedSuffix2 : formattedSuffix2, (float)((double)n7 + module3.getNameWidth()), f2 + 1.0F, this.blurShadowSettings.resolvePassColor(shaderRenderPass, this.suffixColor.getColorInt(n3), n3), this.fontShadow.getValue(), false, true);
                           }
                        } else {
                           this.fontRenderer
                              .drawStringFiltered(
                                 this.bold.getValue() ? "§l" + module3.getFullDisplayName() : module3.getFullDisplayName(),
                                 (float)n7,
                                 f2 + 1.0F,
                                 this.blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt2, n3),
                                 this.fontShadow.getValue(),
                                 false,
                                 true
                              );
                        }
                     } else if (flag3) {
                        DynamicFontUtil customFont3 = this.customFont;
                        String displayName2 = module3.getDisplayName();
                        double d = (double)n7;
                        double d2 = (double)((int)f2);
                        int n10 = this.blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt2, n3);
                        customFont3.drawString(displayName2, d, d2, n10, this.fontShadow.getValue());
                        String formattedSuffix3 = module3.getFormattedSuffix();
                        if (!formattedSuffix3.isEmpty()) {
                           customFont3 = this.customFont;
                           d = (double)n7 + module3.getNameWidth();
                           d2 = (double)((int)f2);
                           n10 = this.blurShadowSettings.resolvePassColor(shaderRenderPass, this.suffixColor.getColorInt(n3), n3);
                           customFont3.drawString(formattedSuffix3, d, d2, n10, this.fontShadow.getValue());
                        }
                     } else {
                        this.customFont.drawString(module3.getFullDisplayName(), (double)n7, (double)((int)f2), this.blurShadowSettings.resolvePassColor(shaderRenderPass, colorInt2, n3), this.fontShadow.getValue());
                     }

                     n3++;
                     if (this.animationsEnabled) {
                        f2 = module3.toggleAnimation.updateY();
                     }

                     f2 += (float)n4;
                  }
               }

               GuiIngameHook.arrayListBottomY = f2 + (float)((int)(this.yPos.getValue() / 100.0 * (double)mc.displayHeight));
               GL11.glPopMatrix();
            }
         );
      }

      RenderUtil.resetGlColorState();
   }

   public void recalculateModuleDimensions() {

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getSortedModules())) {
         module.updateDisplayName();
      }

      ClientManagers.moduleManager.sortModules();
   }

   public void updateSelectedFont() {
      switch (this.font.getSelectedValue()) {
         case "Verdana":
            this.customFont = FontManager.verdanaArrayList;
            break;
         case "Roboto":
            this.customFont = FontManager.robotoArrayList;
            break;
         case "Arial":
            this.customFont = FontManager.arialArrayList;
            break;
         case "Comfortaa":
            this.customFont = FontManager.comfortaaArrayList;
            break;
         case "Esp":
            this.customFont = FontManager.espArrayList;
            break;
         case "GoldBil":
            this.customFont = FontManager.goldBillArrayList;
            break;
         case "Jello":
            this.customFont = FontManager.jelloArrayList;
            break;
         case "ProductSans":
            this.customFont = FontManager.productSansArrayList;
            break;
         case "MuseoSans":
            this.customFont = FontManager.museoSansArrayList;
            break;
         case "Borel":
            this.customFont = FontManager.borelArrayList;
            break;
         case "Rubik":
            this.customFont = FontManager.rubikArrayList;
            break;
         case "RubikBold":
            this.customFont = FontManager.rubikBoldArrayList;
            break;
         case "Tahoma":
            this.customFont = FontManager.tahomaArrayList;
            break;
         case "TahomaBold":
            this.customFont = FontManager.tahomaBoldArrayList;
            break;
         default:
            DynamicFontUtil customDynamicFont = FontManager.getCustomDynamicFont(this.font.getSelectedValue());
            if (customDynamicFont != null) {
               this.customFont = customDynamicFont;
            }
      }

      ClientManagers.moduleManager.sortModules();
      this.customFont.setFontSize((int)this.fontSize.getValue());
      this.recalculateModuleDimensions();
   }

   public DynamicFontUtil getCustomFont() {
      return this.customFont;
   }

   private void refreshFontOptions() {
      HashSet set = new HashSet<>(Arrays.asList(this.font.getOptions()));
      set.addAll(new java.util.ArrayList(FontManager.customDynamicFonts.keySet()));
      java.util.ArrayList list2 = new java.util.ArrayList(set);
      Collections.sort(list2);
      this.font.setOptions((String[])list2.toArray(new String[0]));
   }

   private boolean isAnimationTimeVisible() {
      return !this.toggleAnimationMode.getSelectedValue().equals("None");
   }

   private boolean isFontSizeVisible() {
      return !this.font.getSelectedValue().equals("Minecraft");
   }

   private boolean isMinecraftScaleVisible() {
      return this.font.getSelectedValue().equals("Minecraft");
   }

   private static void openCustomFontsFolder() {
      ClientFileUtil.openFolder(FontManager.CLIENT_DATA_DIR + File.separator + "fonts");
   }

   private boolean isLineColorVisible() {
      return this.line.getValue() && this.customLineColor.getValue();
   }

   private boolean isCustomColorVisible() {
      return !this.randomColor.getValue();
   }

   private boolean isSuffixColorVisible() {
      return !this.suffix.getSelectedValue().equals("None");
   }

   private boolean isSuffixCasingVisible() {
      return !this.suffix.getSelectedValue().equals("None");
   }

   private boolean isCustomSuffixVisible() {
      return this.suffix.getSelectedValue().equals("Custom");
   }


}
