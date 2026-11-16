package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.event.EventRenderArrayListListener;
import client.event.RenderListener;
import client.font.CustomFont;
import client.font.FontRegistry;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.EasingFunction;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ConsumerTask;
import client.util.KeybindAnimation;
import client.util.MovementHandler;
import client.util.RunnableTask;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.Iterator;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class ArrayList extends Module implements EventRenderArrayListListener, RenderListener {
    public java.util.ArrayList activeModules;
    public boolean expanded2;
    public NumberSetting settingXPos;
    public NumberSetting settingYPos;
    public ModeSetting settingSide;
    public ModeSetting settingSorting;
    public ModeSetting settingSuffix;
    public ModeSetting settingCasing;
    public ModeSetting settingSuffixCasing;
    public ColorSetting settingSuffixColor;
    public ModeSetting settingCustomSuffix;
    public BooleanSetting settingRandomColor;
    public ColorSetting settingCustomColor;
    public BooleanSetting settingBackgound;
    public ColorSetting settingBackGroundColor;
    public BooleanSetting settingLine;
    public NumberSetting settingLineWidth;
    public NumberSetting settingLineGap;
    public BooleanSetting settingCustomLineColor;
    public ColorSetting settingLineColor;
    public ShaderOptions settingLineColor2;
    public ModeSetting settingFont;
    public BooleanSetting settingBold;
    public NumberSetting settingVerticalSpacing;
    public NumberSetting settingScale;
    public NumberSetting settingFontSize;
    public BooleanSetting settingFontShadow;
    public ModeSetting settingToggleAnimation;
    public NumberSetting settingAnimationTime;
    public CustomFont customFont;
    public FontRenderer fontRenderer2;
    public boolean enabled2;
    public int tickDelay;
    public TimeHelper timer;
    public static boolean activeState;

    public ArrayList() {
        super("ArrayList", "Modulliste", Color.CYAN, Category.RENDER);
        this.activeModules = new java.util.ArrayList();
        this.expanded2 = false;
        this.settingXPos = new NumberSetting(this, "X-Pos", 100.0, 0.0, 100.0, 2);
        this.settingYPos = new NumberSetting(this, "Y-Pos", 0.0, 0.0, 100.0, 2);
        String[] textArray = new String[2];
        textArray[0] = "Left";
        textArray[1] = "Right";
        this.settingSide = new ModeSetting(this, "Side", "Right", textArray);
        String[] textArray2 = new String[2];
        textArray2[0] = "Alphabet";
        textArray2[1] = "Length";
        this.settingSorting = new ModeSetting(this, "Sorting", "Length", textArray2);
        String[] textArray3 = new String[6];
        textArray3[0] = "None";
        textArray3[1] = "Simple";
        textArray3[2] = "Dash";
        textArray3[3] = "Bracket";
        textArray3[4] = "Colon";
        textArray3[5] = "Custom";
        this.settingSuffix = new ModeSetting(this, "Suffix", "Dash", textArray3);
        String[] textArray4 = new String[3];
        textArray4[0] = "Default";
        textArray4[1] = "UpperCase";
        textArray4[2] = "LowerCase";
        this.settingCasing = new ModeSetting(this, "Casing", "Default", textArray4).getModeSetting(() -> this.onCasingAction());
        String[] textArray5 = new String[3];
        textArray5[0] = "Default";
        textArray5[1] = "UpperCase";
        textArray5[2] = "LowerCase";
        this.settingSuffixCasing = new ModeSetting(this, "SuffixCasing", "Default", textArray5).getModeSetting(() -> this.isSuffixCasingVisible()).getModeSetting(() -> this.onCasingAction());
        this.settingSuffixColor = (ColorSetting)new ColorSetting(this, "SuffixColor", Color.white).getSetting(() -> this.isSuffixColorVisible());
        String[] textArray6 = new String[1];
        textArray6[0] = "";
        this.settingCustomSuffix = new ModeSetting(this, "CustomSuffix", "", textArray6).getModeSetting(() -> canExecute5());
        this.settingRandomColor = new BooleanSetting(this, "RandomColor", false);
        Color color = Client.getClient().getColor();
        ColorSetting colorSetting2 = new ColorSetting(this, "CustomColor", color);
        ColorSetting colorSetting6 = ((ColorSetting)colorSetting2.getSetting(() -> this.isCustomColorVisible())).getColorSetting6(true);
        Color color2 = new Color(1, 254, 177);
        this.settingCustomColor = colorSetting6.getColorSetting(color2).getColorSetting5(50).getColorSetting2(43);
        this.settingBackgound = (BooleanSetting)new BooleanSetting(this, "Backgound", true).getSetting("BackGround");
        Color color3 = new Color(0, 0, 0, 119);
        this.settingBackGroundColor = (ColorSetting)new ColorSetting(this, "BackGroundColor", color3).getSetting(() -> this.settingBackgound.isEnabled());
        this.settingLine = new BooleanSetting(this, "Line", false);
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 3.0, 0.0, 10.0, 0).getNumberSetting(() -> this.settingLine.isEnabled());
        this.settingLineGap = new NumberSetting(this, "LineGap", 0.0, 0.0, 10.0, 0).getNumberSetting(() -> this.settingLine.isEnabled());
        this.settingCustomLineColor = (BooleanSetting)new BooleanSetting(this, "CustomLineColor", false).getSetting(() -> this.settingLine.isEnabled());
        this.settingLineColor = (ColorSetting)new ColorSetting(this, "LineColor", Color.BLUE).getSetting(() -> this.isLineColorVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color color4 = Client.getClient().getColor();
        this.settingLineColor2 = shaderOptions.getShaderOptions(color4);
        String[] textArray7 = new String[15];
        textArray7[0] = "Minecraft";
        textArray7[1] = "Verdana";
        textArray7[2] = "Arial";
        textArray7[3] = "Roboto";
        textArray7[4] = "Comfortaa";
        textArray7[5] = "Esp";
        textArray7[6] = "GoldBil";
        textArray7[7] = "Jello";
        textArray7[8] = "ProductSans";
        textArray7[9] = "MuseoSans";
        textArray7[10] = "Borel";
        textArray7[11] = "Rubik";
        textArray7[12] = "RubikBold";
        textArray7[13] = "Tahoma";
        textArray7[14] = "TahomaBold";
        this.settingFont = new ModeSetting(this, "Font", "Minecraft", textArray7).getModeSetting(() -> this.onFontAction());
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "Bold", false);
        this.settingBold = (BooleanSetting)(((BooleanSetting)booleanSetting5.getSetting(() -> this.isFontVisible())).getSetting(() -> this.onCasingAction()));
        this.settingVerticalSpacing = new NumberSetting(this, "VerticalSpacing", 1.0, -5.0, 5.0, 0);
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 0.5, 4.0, 2).getNumberSetting(() -> this.isScaleVisible());
        this.settingFontSize = new NumberSetting(this, "FontSize", 16.0, 16.0, 80.0, 0).getNumberSetting(() -> this.isFontSizeVisible());
        this.settingFontShadow = new BooleanSetting(this, "FontShadow", true);
        String[] stringArray = KeybindAnimation.getStringArray(true);
        this.settingToggleAnimation = new ModeSetting(this, "ToggleAnimation", "easeInOutBack", stringArray).getModeSetting(() -> this.onToggleAnimationAction());
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 520.0, 0.0, 2000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        this.customFont = FontRegistry.fontFaceAdapter14;
        this.enabled2 = true;
        this.tickDelay = 0;
        this.timer = new TimeHelper();
        this.settingCustomSuffix.getModeSetting(() -> canExecute8());
        this.fontRenderer2 = minecraft.fontRendererObj;
    }

    @Override
    public void onEnable() {
        this.registerEvent(11);
        this.registerEvent(32, 1);
    }

    @Override
    public void onDisable() {
        this.activeModules.clear();
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = true;
        this.onFontAction();
        this.onCasingAction();
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (!minecraft.gameSettings.state14) {
            this.activeModules.clear();
            boolean enabled = !this.settingToggleAnimation.getSelected().equals("None");
            this.enabled2 = enabled;
            boolean enabled3 = this.settingSide.getSelected().equals("Left");
            double tickDelay2 = (double)this.tickDelay;
            if (tickDelay2 != this.settingFontSize.getValue()) {
                CustomFont customFont2 = this.customFont;
                int fontSize = (int)this.settingFontSize.getValue();
                customFont2.processAction(fontSize);
                this.onCasingAction();
            }

            this.tickDelay = (int)this.settingFontSize.getValue();
            EasingFunction easingFunction = KeybindAnimation.getEasingFunction(this.settingToggleAnimation.getSelected());

            for (Module module : ((Iterable<Module>) ClientGlobals.moduleManager.getCopyOnWriteArrayList())) {
                if (module.canExecute2()) {
                    if (this.expanded2 && module.isToggled()) {
                        KeybindAnimation keybindAnimation = module.keybindAnimation;
                        int animationTime = (int)this.settingAnimationTime.getValue();
                        byte state = (byte)(module.isToggled() ? 1 : 0);
                        keybindAnimation.updateEasingFunction(animationTime, easingFunction, state, false);
                    }

                    if (!this.enabled2 ? module.isToggled() : !(module.keybindAnimation.getDouble2() >= 1.0) || module.isToggled()
                    )
                    {
                        this.activeModules.add(module);
                    }
                }
            }

            int index;
            if (this.settingLine.isEnabled()) {
                index = Math.max(0, (int)this.settingLineWidth.getValue());
            } else {
                index = 0;
            }

            int verticalSpacing = (int)this.settingVerticalSpacing.getValue();
            int index2 = (int)Math.max(0.0, this.settingLineGap.getValue());
            this.expanded2 = false;
            boolean enabled4 = this.settingLineColor2.canExecute();
            boolean enabled5 = this.settingLineColor2.canExecute2();
            MovementHandler.processBoolean(enabled4, enabled5);
            int index3 = index;
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyScaledResolution2(scaledResolution, verticalSpacing, enabled3, index3, index2, renderPass));
        }

        RenderUtils.processAction();
    }

    @Override
    public void updateState() {
        ClientGlobals.moduleManager.updateState();
    }

    private void onCasingAction() {
        Iterator iterator = ClientGlobals.moduleManager.getCopyOnWriteArrayList().iterator();

        while (iterator.hasNext()) {
            ((Module)iterator.next()).onLoad();
        }

        ClientGlobals.moduleManager.updateState();
    }

    public void onFontAction() {
        String selected = this.settingFont.getSelected();
        switch (selected) {
            case "Verdana":
                this.customFont = FontRegistry.fontFaceAdapter12;
                break;
            case "Roboto":
                this.customFont = FontRegistry.fontFaceAdapter14;
                break;
            case "Arial":
                this.customFont = FontRegistry.fontFaceAdapter11;
                break;
            case "Comfortaa":
                this.customFont = FontRegistry.fontFaceAdapter7;
                break;
            case "Esp":
                this.customFont = FontRegistry.fontFaceAdapter10;
                break;
            case "GoldBil":
                this.customFont = FontRegistry.fontFaceAdapter8;
                break;
            case "Jello":
                this.customFont = FontRegistry.fontFaceAdapter6;
                break;
            case "ProductSans":
                this.customFont = FontRegistry.fontFaceAdapter1;
                break;
            case "MuseoSans":
                this.customFont = FontRegistry.fontFaceAdapter13;
                break;
            case "Borel":
                this.customFont = FontRegistry.fontFaceAdapter4;
                break;
            case "Rubik":
                this.customFont = FontRegistry.fontFaceAdapter2;
                break;
            case "RubikBold":
                this.customFont = FontRegistry.fontFaceAdapter9;
                break;
            case "Tahoma":
                this.customFont = FontRegistry.fontFaceAdapter5;
                break;
            case "TahomaBold":
                this.customFont = FontRegistry.fontFaceAdapter;
        }

        ClientGlobals.moduleManager.updateState();
        CustomFont customFont2 = this.customFont;
        int fontSize = (int)this.settingFontSize.getValue();
        customFont2.processAction(fontSize);
        this.onCasingAction();
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    private boolean isSuffixColorVisible() {
        return !this.settingSuffix.getSelected().equals("None");
    }

    private void applyScaledResolution2(ScaledResolution scaledResolution, int index2, boolean enabled, int index3, int index4, RenderPass renderPass) {
        GL11.glPushMatrix();
        boolean enabled3 = this.settingFont.getSelected().equals("Minecraft");
        GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
        GL11.glTranslated(
        (double)((int)(this.settingXPos.getValue() / 100.0 * (double)minecraft.displayWidth)),
        (double)((int)(this.settingYPos.getValue() / 100.0 * (double)minecraft.displayHeight)),
        0.0
        );
        if (enabled3) {
            GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
        }

        float value = (float)(
        (int)(
        (float)(
        (double)(GuiIngame.reservedTopOffset != 0.0F ? 1 : 0)
        + (double)GuiIngame.reservedTopOffset * (1.0 / this.settingScale.getValue())
        )
        )
        );
        int index5 = 0;
        boolean enabled4 = !this.settingSuffix.getSelected().equals("None");
        int index6 = (enabled3 ? this.fontRenderer2.indexOffset : Math.round(this.customFont.getFontSize())) + index2 * 2;
        if (this.settingBackgound.isEnabled()) {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            if (renderPass == RenderPass.SHADOW) {
                RenderUtils.processAction(
                this.settingLineColor2.getInt3(this.settingBackGroundColor.getInt12(index5)), 1.0F
                );
            } else {
                RenderUtils.setColor(this.settingBackGroundColor.getInt12(index5));
            }

            GL11.glBegin(7);

            for (Module module : (Iterable<Module>) this.activeModules) {
                int index7 = 0;
                if (!enabled) {
                    index7 -= module.getIndexOffset();
                }

                if (this.enabled2) {
                    if (enabled) {
                        module.keybindAnimation.processFloat((float)(index7 - module.getIndexOffset()), value - (float)index6, (float)index7, value);
                    } else {
                        module.keybindAnimation.processFloat((float)(index7 + module.getIndexOffset()), value - (float)index6, (float)index7, value);
                    }

                    index7 = (int)module.keybindAnimation.getFloat();
                }

                int index8 = enabled ? index3 : -index3;
                RenderUtils.processAction(
                (double)(index7 - 3 + index8),
                (double)(value - (float)index2),
                (double)(index7 + module.getIndexOffset() + 3 + index8),
                (double)(value + (float)index6 - (float)index2)
                );
                if (this.enabled2) {
                    value = module.keybindAnimation.getFloat2();
                }

                value += (float)index6;
            }

            GL11.glEnd();
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
        }

        if (renderPass == RenderPass.NONE || renderPass == RenderPass.SHADOW && !this.settingBackgound.isEnabled()) {
            float value2 = (float)(
            (int)(
            (float)(
            (double)(GuiIngame.reservedTopOffset != 0.0F ? 1 : 0)
            + (double)GuiIngame.reservedTopOffset * (1.0 / this.settingScale.getValue())
            )
            )
            );

            for (Module module2 : (Iterable<Module>) this.activeModules) {
                int int12 = this.settingCustomColor.getInt12(index5);
                int index9 = 0;
                if (!enabled) {
                    index9 -= module2.getIndexOffset();
                }

                if (this.enabled2) {
                    if (enabled) {
                        module2.keybindAnimation.processFloat((float)(index9 - module2.getIndexOffset()), value2 - (float)index6, (float)index9, value2);
                    } else {
                        module2.keybindAnimation.processFloat((float)(index9 + module2.getIndexOffset()), value2 - (float)index6, (float)index9, value2);
                    }

                    index9 = (int)module2.keybindAnimation.getFloat();
                    if (module2.keybindAnimation.getDouble3() == 1.0) {
                        value2 = module2.keybindAnimation.getFloat2();
                    }
                }

                if (this.settingRandomColor.isEnabled()) {
                    int12 = module2.getColor().getRGB();
                }

                if (this.settingLine.isEnabled()) {
                    GlStateManager.applyState();
                    int index10 = int12;
                    if (this.settingCustomLineColor.isEnabled()) {
                        index10 = this.settingLineColor.getInt12(index5);
                    }

                    int index = this.settingLineColor2.getIndex(renderPass, index10, index5);
                    if (enabled) {
                        RenderUtils.processAction(index9 - 3, (int)value2 - index2 + index4, index9 + index3 - 3, (int)value2 + module2.getKeyCode() + index2 - index4, index);
                    } else {
                        RenderUtils.processAction(
                        index9 + module2.getIndexOffset() + 3,
                        (int)value2 - index2 + index4,
                        index9 + module2.getIndexOffset() - index3 + 3,
                        (int)value2 + module2.getKeyCode() + index2 - index4,
                        index
                        );
                    }
                }

                int index11 = index9 + (enabled ? index3 : -index3);
                if (enabled3) {
                    if (enabled4) {
                        this.fontRenderer2
                        .getIndex(
                        this.settingBold.isEnabled() ? "§l" + module2.getSuffix() : module2.getSuffix(),
                        (float)index11,
                        value2 + 1.0F,
                        this.settingLineColor2.getIndex(renderPass, int12, index5),
                        this.settingFontShadow.isEnabled(),
                        false,
                        true
                        );
                        String text = module2.getText();
                        if (!text.isEmpty()) {
                            this.fontRenderer2
                            .getIndex(
                            this.settingBold.isEnabled() ? "§l" + text : text,
                            (float)((int)((double)index11 + module2.getCoordOffset())),
                            value2 + 1.0F,
                            this.settingLineColor2
                            .getIndex(renderPass, this.settingSuffixColor.getInt12(index5), index5),
                            this.settingFontShadow.isEnabled(),
                            false,
                            true
                            );
                        }
                    } else {
                        this.fontRenderer2
                        .getIndex(
                        this.settingBold.isEnabled() ? "§l" + module2.getString2() : module2.getString2(),
                        (float)index11,
                        value2 + 1.0F,
                        this.settingLineColor2.getIndex(renderPass, int12, index5),
                        this.settingFontShadow.isEnabled(),
                        false,
                        true
                        );
                    }
                } else if (enabled4) {
                    this.customFont
                    .applyString6(
                    module2.getSuffix(),
                    (double)index11,
                    (double)((int)value2),
                    this.settingLineColor2.getIndex(renderPass, int12, index5),
                    
                    
                    this.settingFontShadow.isEnabled()
                    );
                    String text2 = module2.getText();
                    if (!text2.isEmpty()) {
                        this.customFont
                        .applyString6(
                        text2,
                        (double)index11 + module2.getCoordOffset(),
                        (double)((int)value2),
                        this.settingLineColor2
                        .getIndex(renderPass, this.settingSuffixColor.getInt12(index5), index5),
                        
                        
                        this.settingFontShadow.isEnabled()
                        );
                    }
                } else {
                    this.customFont
                    .applyString6(
                    module2.getString2(),
                    (double)index11,
                    (double)((int)value2),
                    this.settingLineColor2.getIndex(renderPass, int12, index5),
                    
                    
                    this.settingFontShadow.isEnabled()
                    );
                }

                index5++;
                if (this.enabled2) {
                    value2 = module2.keybindAnimation.getFloat2();
                }

                value2 += (float)index6;
            }
        }

        GL11.glPopMatrix();
    }

    public CustomFont getFontFaceAdapter() {
        return this.customFont;
    }

    private boolean isScaleVisible() {
        return this.settingFont.getSelected().equals("Minecraft");
    }

    private void onToggleAnimationAction() {
        this.expanded2 = true;
    }

    private boolean isCustomColorVisible() {
        return !this.settingRandomColor.isEnabled();
    }

    private boolean isAnimationTimeVisible() {
        return !this.settingToggleAnimation.getSelected().equals("None");
    }

    private boolean isFontSizeVisible() {
        return !this.settingFont.getSelected().equals("Minecraft");
    }

    private boolean isFontVisible() {
        return this.settingFont.getSelected().equals("Minecraft");
    }

    private static boolean canExecute5() {
        return false;
    }

    public static boolean canExecute6() {
        return !canExecute7();
    }

    private boolean isSuffixCasingVisible() {
        return !this.settingSuffix.getSelected().equals("None");
    }

    private boolean isLineColorVisible() {
        return !this.settingLine.isEnabled() ? false : this.settingCustomLineColor.isEnabled();
    }

    public static boolean canExecute7() {
        return activeState;
    }

    private static boolean canExecute8() {
        return false;
    }
}
