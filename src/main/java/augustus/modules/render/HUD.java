package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.event.RenderListener;
import client.font.CustomFont;
import client.font.FontFace;
import client.font.FontRegistry;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import java.awt.Color;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class HUD extends Module implements RenderListener {
    public ModeSetting settingMode = new ModeSetting(
    this, "Mode", "Basic", new String[]{"None", "Basic", "Other", "Simple", "Ausustus", "Xave", "Augustus", "Amogus", "Logo", "Fanart"}
    );
    public ModeSetting settingSide;
    public ModeSetting settingCasing;
    public NumberSetting settingXPos;
    public NumberSetting settingYPos;
    public NumberSetting settingScale;
    public NumberSetting settingSize;
    public NumberSetting settingNewFontSize;
    public ColorSetting settingColor;
    public BooleanSetting settingBackGround;
    public BooleanSetting settingRoudedBackGround;
    public NumberSetting settingRoundFactor;
    public ColorSetting settingBackGroundColor;
    public ShaderOptions settingHud;
    public String name2;
    public String displayName2;
    public ResourceLocation logoTexture2;
    public ResourceLocation logoTexture4;
    public ResourceLocation logoTexture1;
    public ResourceLocation logoTexture3;
    public int tickDelay;

    public HUD() {
        super("HUD", "ÜberlagerungsElemente", new Color(75, 166, 91), Category.RENDER);
        this.settingSide = new ModeSetting(this, "Side", "Left", new String[]{"Left", "Right"}).getModeSetting(() -> this.isSideVisible());
        this.settingCasing = new ModeSetting(this, "Casing", "Default", new String[]{"Default", "UpperCase", "LowerCase"}).getModeSetting(() -> this.isCasingVisible());
        this.settingXPos = new NumberSetting(this, "X-Pos", 0.0, 0.0, 100.0, 2).getNumberSetting(() -> this.isXPosVisible());
        this.settingYPos = new NumberSetting(this, "Y-Pos", 0.0, 0.0, 100.0, 2).getNumberSetting(() -> this.isYPosVisible());
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2).getNumberSetting(() -> this.isScaleVisible());
        this.settingSize = new NumberSetting(this, "Size", 0.6, 0.01, 2.0, 2).getNumberSetting(() -> this.isSizeVisible());
        this.settingNewFontSize = new NumberSetting(this, "NewFontSize", 33.0, 16.0, 80.0, 0).getNumberSetting("Size").getNumberSetting(() -> this.isNewFontSizeVisible());
        ColorSetting colorSetting = new ColorSetting(this, "Color", Color.white);
        this.settingColor = (ColorSetting)colorSetting.getSetting(() -> this.isColorVisible3());
        BooleanSetting booleanSetting = new BooleanSetting(this, "BackGround", true);
        this.settingBackGround = (BooleanSetting)booleanSetting.getSetting(() -> this.isColorVisible());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "RoudedBackGround", false);
        this.settingRoudedBackGround = (BooleanSetting)booleanSetting2.getSetting(() -> this.isColorVisible2());
        this.settingRoundFactor = new NumberSetting(this, "RoundFactor", 2.0, 1.0, 10.0, 0).getNumberSetting(() -> this.isRoundFactorVisible());
        ColorSetting colorSetting2 = new ColorSetting(this, "BackGroundColor", new Color(0, 0, 0, 100));
        this.settingBackGroundColor = (ColorSetting)colorSetting2.getSetting(() -> this.isBackGroundColorVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this, "Hud");
        Color black = Color.black;
        ShaderOptions shaderOptions2 = shaderOptions.getShaderOptions(black);
        this.settingHud = (ShaderOptions)shaderOptions2.getSetting(() -> this.isHudVisible());
        Client client = Client.getClient();
        this.name2 = client.getString4().toUpperCase(Locale.ROOT);
        this.displayName2 = Client.getClient().getString4().toLowerCase(Locale.ROOT);
        this.logoTexture2 = new ResourceLocation("augustus", "pictures/augustus.png");
        this.logoTexture4 = new ResourceLocation("augustus", "pictures/amogus.png");
        this.logoTexture1 = new ResourceLocation("augustus", "pictures/logo.png");
        this.logoTexture3 = new ResourceLocation("augustus", "pictures/fanart.png");
        this.tickDelay = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (!minecraft.gameSettings.state14) {
            GL11.glPushMatrix();
            GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
            double offset = this.settingXPos.getValue() / 100.0 * (double)minecraft.displayWidth;
            GL11.glTranslated(offset, this.settingYPos.getValue() / 100.0 * (double)minecraft.displayHeight, 0.0);
            boolean sizeVisible = this.isSizeVisible();
            boolean enabled = this.settingSide.getSelected().equals("Left");
            double offset2;
            if (sizeVisible) {
                offset2 = this.settingSize.getValue();
            } else {
                offset2 = this.settingScale.getValue();
            }

            if (!this.settingMode.getSelected().equals("Simple")) {
                GL11.glScaled(offset2, offset2, 1.0);
            }

            GlStateManager.enableBlend();
            ScaledResolution scaledResolution2 = new ScaledResolution(
            (double)minecraft.displayWidth * (1.0 / offset2), (double)minecraft.displayHeight * (1.0 / offset2), (int)offset2
            );
            FontRenderer fontRendererObj = minecraft.fontRendererObj;
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "Augustus":
            case "Logo":
            case "Fanart":
            case "Amogus":
                GlStateManager.applyState14();
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                boolean enabled2 = this.settingMode.getSelected().equals("Augustus");
                int index4 = enabled2 ? 210 : 256;
                int index5;
                if (enabled) {
                        index5 = -20;
                    } else {
                        index5 = scaledResolution2.getScaledWidth() - index4;
                    }
                TextureManager textureManager = minecraft.getTextureManager();
                ResourceLocation location;
                if (this.settingMode.getSelected().equals("Augustus")) {
                        location = this.logoTexture2;
                    } else if (this.settingMode.getSelected().equals("Logo")) {
                        location = this.logoTexture1;
                    } else if (this.settingMode.getSelected().equals("Amogus")) {
                        location = this.logoTexture4;
                    } else {
                        location = this.logoTexture3;
                    }
                textureManager.applyResourceLocation3(location);
                if (this.settingHud.canExecute2()) {
                        MovementHandler.processAction3();
                    }
                if (!this.settingHud.canExecute()) {
                        MovementHandler.processAction8();
                    }
                int index6 = index5;
                RunnableTask runnableTask = renderPass -> applyRenderPass4(index6, index4, renderPass);
                MovementHandler.applyRunnableTask(true, runnableTask);
                double offset3 = (double)((float)(256.0 * offset2) + 1.0F);
                this.processAction(offset3, 1.0);
                break;
            case "Other":
                GL11.glPushMatrix();
                GL11.glScaled(1.5, 1.5, 1.0);
                double offset4 = (double)((int)((double)((float)fontRendererObj.indexOffset * 1.75F + 5.0F) * 1.5));
                this.processAction(offset4, offset2);
                int index = fontRendererObj.getIndex("A");
                int index2 = fontRendererObj.getIndex("ugustus");
                int int12 = this.settingColor.getInt12(0);
                int int122 = this.settingBackGroundColor.getInt12(0);
                if (this.settingBackGround.isEnabled()) {
                        boolean enabled3 = this.settingHud.canExecute();
                        boolean enabled4 = this.settingHud.canExecute2();
                        MovementHandler.processBoolean(enabled3, enabled4);
                    }
                if (enabled) {
                        if (this.settingBackGround.isEnabled()) {
                            MovementHandler.applyRunnableTask(false, renderPass2 -> this.applyFontRenderer2(int122, index, index2, fontRendererObj, renderPass2));
                        }

                        GL11.glPushMatrix();
                        GL11.glScaled(1.75, 1.75, 1.0);
                        fontRendererObj.getIndex("A", 1.0F, 1.0F, int12, true, 0.5F);
                        GL11.glPopMatrix();
                        fontRendererObj.getIndex("ugustus", (float)index * 1.75F + 2.0F, (float)(2 + fontRendererObj.indexOffset) - 4.0F, int12, true, 0.5F);
                        Client.getClient().getClass();
                    } else {
                        if (this.settingBackGround.isEnabled()) {
                            MovementHandler.applyRunnableTask(false, renderPass3 -> this.applyScaledResolution3(int122, scaledResolution2, index, index2, fontRendererObj, renderPass3));
                        }

                        GL11.glPushMatrix();
                        GL11.glScaled(1.75, 1.75, 1.0);
                        fontRendererObj.getIndex(
                        "A", (float)scaledResolution2.getScaledWidth() / 1.5F / 1.75F - (float)index - (float)index2 / 1.75F - 0.25F, 1.0F, int12, true, 0.5F
                        );
                        GL11.glPopMatrix();
                        fontRendererObj.getIndex(
                        "ugustus",
                        (float)scaledResolution2.getScaledWidth() / 1.5F - 1.75F - (float)index * 1.5F * 1.75F - (float)index2 / 1.5F + 4.0F,
                        (float)(2 + fontRendererObj.indexOffset) - 4.0F,
                        int12,
                        true,
                        0.5F
                        );
                    }
                GL11.glPopMatrix();
                break;
            case "Basic":
            case "Ausustus":
                GregorianCalendar gregorianCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
                Date date = new Date();
                gregorianCalendar.setTime(date);
                int index7;
                if (ClientGlobals.moduleManager.protector.isToggled() && ClientGlobals.moduleManager.protector.settingTime.isEnabled()) {
                        index7 = ClientGlobals.moduleManager.protector.getIndex();
                    } else {
                        index7 = gregorianCalendar.get(10);
                    }
                String text;
                if (gregorianCalendar.get(12) <= 9) {
                        text = "0" + gregorianCalendar.get(12);
                    } else {
                        text = String.valueOf(gregorianCalendar.get(12));
                    }
                if (ClientGlobals.moduleManager.protector.isToggled() && ClientGlobals.moduleManager.protector.settingTime.isEnabled()) {
                        int index3 = ClientGlobals.moduleManager.protector.getIndex3();
                        text = String.valueOf(index3);
                    }
                String text2;
                if (gregorianCalendar.get(9) == 0) {
                        text2 = index7 < 9 ? " (0" + index7 + ":" + text + " AM)" : " (" + index7 + ":" + text + " AM)";
                    } else {
                        text2 = index7 < 9 ? " (0" + index7 + ":" + text + " PM)" : " (" + index7 + ":" + text + " PM)";
                    }
                StringBuilder stringBuilder = new StringBuilder();
                String text3;
                if (this.settingMode.getSelected().equals("Basic")) {
                        text3 = Client.getClient().getString4();
                    } else {
                        text3 = "Ausustus";
                    }
                StringBuilder sb = stringBuilder.append(text3).append(" ");
                Client client = Client.getClient();
                String text4 = sb.append(client.getString5()).toString();
                float value = (float)fontRendererObj.getIndex(text4);
                float value2 = (float)fontRendererObj.getIndex(text2);
                double offset5 = (double)(fontRendererObj.indexOffset + 1);
                this.processAction(offset5, offset2);
                int rGB = new Color(182, 186, 189).getRGB();
                int int123 = this.settingColor.getInt12(0);
                int int124 = this.settingBackGroundColor.getInt12(0);
                if (this.settingBackGround.isEnabled()) {
                        boolean enabled5 = this.settingHud.canExecute();
                        boolean enabled6 = this.settingHud.canExecute2();
                        MovementHandler.processBoolean(enabled5, enabled6);
                    }
                if (enabled) {
                        if (this.settingBackGround.isEnabled()) {
                            MovementHandler.applyRunnableTask(false, renderPass4 -> this.applyFontRenderer(int124, value2, value, fontRendererObj, renderPass4));
                        }

                        fontRendererObj.getIndex(text4, 1.0F, 1.0F, int123, true, 0.5F);
                        fontRendererObj.getIndex(text2, value + 1.0F, 1.0F, rGB, true, 0.5F);
                    } else {
                        if (this.settingBackGround.isEnabled()) {
                            MovementHandler.applyRunnableTask(false, renderPass5 -> this.applyScaledResolution4(int124, scaledResolution2, value2, value, fontRendererObj, renderPass5));
                        }

                        fontRendererObj.getIndex(text4, (float)scaledResolution2.getScaledWidth() - value - value2, 1.0F, int123, true, 0.5F);
                        fontRendererObj.getIndex(text2, (float)scaledResolution2.getScaledWidth() - value2 - 1.0F, 1.0F, rGB, true, 0.5F);
                    }
                break;
            case "Xave":
                int int125 = this.settingColor.getInt12(0);
                boolean enabled7 = this.settingHud.canExecute2();
                MovementHandler.processBoolean(false, enabled7);
                if (enabled) {
                        MovementHandler.applyRunnableTask(false, renderPass6 -> this.applyRenderPass2(int125, renderPass6));
                    } else {
                        MovementHandler.applyRunnableTask(enabled, renderPass7 -> this.applyScaledResolution2(scaledResolution2, int125, renderPass7));
                    }
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                double offset6 = (double)FontRegistry.fontFace13.getFontSize() * offset2;
                FontFace fontFace12 = FontRegistry.fontFace12;
                double offset7 = offset6 + (double)fontFace12.getFontSize() / 1.25;
                this.processAction(offset7, 1.0);
                break;
            case "Simple":
                int int126 = MathHelper.getInt12((int)this.settingNewFontSize.getValue(), 1, 300);
                if (this.tickDelay != int126) {
                        FontRegistry.fontFaceAdapter3.processAction(int126);
                    }
                String text5;
                if (this.settingCasing.getSelected().equals("UpperCase")) {
                        text5 = this.name2;
                    } else if (this.settingCasing.getSelected().equals("LowerCase")) {
                        text5 = this.displayName2;
                    } else {
                        text5 = Client.getClient().getString4();
                    }
                this.tickDelay = int126;
                int int127 = this.settingColor.getInt12(0);
                boolean enabled8 = this.settingHud.canExecute();
                boolean enabled9 = this.settingHud.canExecute2();
                MovementHandler.processBoolean(enabled8, enabled9);
                float scaleFactor = FontRegistry.fontFaceAdapter3.getScaleFactor(text5);
                float value3 = FontRegistry.fontFaceAdapter3.getScaleFactor((byte)0, text5, 1371042, 8224173) * 1.12F;
                if (enabled) {
                        if (this.settingBackGround.isEnabled()) {
                            int int128 = this.settingBackGroundColor.getInt12(0);
                            MovementHandler.applyRunnableTask(false, renderPass8 -> this.applyRenderPass(int128, scaleFactor, value3, renderPass8));
                        }

                        boolean enabled10;
                        if (this.settingHud.canExecute2() && !this.settingBackGround.isEnabled()) {
                            enabled10 = true;
                        } else {
                            enabled10 = false;
                        }

                        MovementHandler.processBoolean(false, enabled10);
                        String text6 = text5;
                        MovementHandler.applyRunnableTask(false, renderPass9 -> this.applyRenderPass6(text6, int127, renderPass9));
                    } else {
                        if (this.settingBackGround.isEnabled()) {
                            int int129 = this.settingBackGroundColor.getInt12(0);
                            MovementHandler.applyRunnableTask(false, renderPass10 -> this.applyRenderPass3(int129, scaleFactor, value3, renderPass10));
                        }

                        boolean enabled11;
                        if (this.settingHud.canExecute2() && !this.settingBackGround.isEnabled()) {
                            enabled11 = true;
                        } else {
                            enabled11 = false;
                        }

                        MovementHandler.processBoolean(false, enabled11);
                        String text7 = text5;
                        MovementHandler.applyRunnableTask(false, renderPass11 -> this.applyRenderPass5(text7, scaleFactor, int127, renderPass11));
                    }
                double offset8 = (double)value3 + (double)value3 / 1.25;
                this.processAction(offset8, 1.0);
                break;
            default:
                this.processAction(0.0, 1.0);
        }

            GL11.glPopMatrix();
        } else {
            this.processAction(0.0, 1.0);
        }

        RenderUtils.processAction();
    }

    @Override
    public void onRenderOverlay(float partialTicks, ScaledResolution scaledResolution) {
        if (this.isSizeVisible()) {
            if (this.settingHud.canExecute()) {
                GL11.glPushMatrix();
                GL11.glScaled((double)(1.0F / (float)scaledResolution.getScaleFactor()), (double)(1.0F / (float)scaledResolution.getScaleFactor()), 1.0);
                double offset = this.settingXPos.getValue() / 100.0 * (double)minecraft.displayWidth;
                GL11.glTranslated(offset, this.settingYPos.getValue() / 100.0 * (double)minecraft.displayHeight, 0.0);
                boolean enabled = this.settingSide.getSelected().equals("Left");
                double value = this.settingSize.getValue();
                GL11.glScaled(value, value, 1.0);
                ScaledResolution scaledResolution2 = new ScaledResolution(
                (double)minecraft.displayWidth * (1.0 / value), (double)minecraft.displayHeight * (1.0 / value), (int)value
                );
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                short id;
                if (!this.settingMode.getSelected().equals("Amogus") && !this.settingMode.getSelected().equals("Logo")) {
                    id = 210;
                } else {
                    id = 256;
                }

                int index;
                if (enabled) {
                    index = -20;
                } else {
                    index = scaledResolution2.getScaledWidth() - id;
                }

                TextureManager textureManager = minecraft.getTextureManager();
                ResourceLocation location;
                if (this.settingMode.getSelected().equals("Augustus")) {
                    location = this.logoTexture2;
                } else if (this.settingMode.getSelected().equals("Logo")) {
                    location = this.logoTexture1;
                } else {
                    location = this.logoTexture4;
                }

                textureManager.applyResourceLocation(location, false);
                minecraft.ingameGUI.processAction(index, -3, 0, 0, id, 256);
                MovementHandler.processAction6();
                MovementHandler.processAction11();
                short id2 = id;
                int index2 = index;
                RunnableTask runnableTask = renderPass -> applyRenderPass7(index2, id2, renderPass);
                MovementHandler.applyRunnableTask(false, runnableTask);
                GL11.glScaled(value, value, 1.0);
                GL11.glPopMatrix();
            }
        }
    }

    private void applyScaledResolution2(ScaledResolution scaledResolution, int index2, RenderPass renderPass) {
        FontFace fontFace13 = FontRegistry.fontFace13;
        String name22 = this.name2;
        float scaledWidth = (float)scaledResolution.getScaledWidth();
        String name23 = this.name2;
        FontFace fontFace132 = FontRegistry.fontFace13;
        double offset = (double)(scaledWidth - fontFace132.getScaleFactor(name23));
        int index = this.settingHud.getIndex(renderPass, index2);
        fontFace13.applyString4(name22, offset, 4.0, index);
    }

    private void applyRenderPass(int index3, float value, float value2, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingHud.getIndex(renderPass, index);
        if (this.settingRoudedBackGround.isEnabled()) {
            float value3 = value + 6.0F;
            RenderUtils.processFloat9(0.0F, 0.0F, value3, value2, (float)this.settingRoundFactor.getValue(), 1, index2);
        } else {
            RenderUtils.processAction(0.0F, 0.0F, value + 6.0F, value2, index2);
        }
    }

    private void applyRenderPass2(int index2, RenderPass renderPass) {
        FontFace fontFace13 = FontRegistry.fontFace13;
        String name22 = this.name2;
        int index = this.settingHud.getIndex(renderPass, index2);
        fontFace13.applyString4(name22, 2.0, 2.0, index);
    }

    private void applyRenderPass3(int index3, float value, float value2, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingHud.getIndex(renderPass, index);
        if (this.settingRoudedBackGround.isEnabled()) {
            float value3 = (float)minecraft.displayWidth - value - 6.0F;
            float displayWidth = (float)minecraft.displayWidth;
            RenderUtils.processFloat9(value3, 0.0F, displayWidth, value2, (float)this.settingRoundFactor.getValue(), 1, index2);
        } else {
            RenderUtils.processAction((float)minecraft.displayWidth - value - 6.0F, 0.0F, (float)minecraft.displayWidth, value2, index2);
        }
    }

    private void applyFontRenderer(int index3, float value, float value2, FontRenderer fontRenderer, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingHud.getIndex(renderPass, index);
        if (this.settingRoudedBackGround.isEnabled()) {
            float value3 = value + 3.0F + value2;
            float value4 = (float)(fontRenderer.indexOffset + 1);
            RenderUtils.processFloat9(0.0F, 0.0F, value3, value4, (float)this.settingRoundFactor.getValue(), 1, index2);
        } else {
            RenderUtils.processAction(0.0F, 0.0F, value + 3.0F + value2, (float)(fontRenderer.indexOffset + 1), index2);
        }
    }

    private boolean isXPosVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    private void processAction(double offset, double offset2) {
        double offset3 = offset;
        if (!this.settingSide.getSelected().equals(ClientGlobals.moduleManager.itemList.settingSide.getSelected())) {
            offset3 = 0.0;
        }

        int index = (int)((float)(offset3 * offset2));
        int index2 = offset3 != 0.0 ? 1 : 0;
        GuiIngame.reservedTopOffset = (float)(index + index2);
    }

    private void applyFontRenderer2(int x, int y, int z, FontRenderer fontRenderer, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, x);
        int index2 = this.settingHud.getIndex(renderPass, index);
        if (this.settingRoudedBackGround.isEnabled()) {
            float value = (float)((int)((float)y * 1.75F + (float)z + 4.0F));
            float value2 = (float)((int)((float)fontRenderer.indexOffset * 1.75F + 3.0F));
            RenderUtils.processFloat9(0.0F, 0.0F, value, value2, (float)this.settingRoundFactor.getValue(), 1, index2);
        } else {
            TexturesGuiIconsPngManager.processAction(
            0, 0, (int)((float)y * 1.75F + (float)z + 4.0F), (int)((float)fontRenderer.indexOffset * 1.75F + 3.0F), index2
            );
        }
    }

    private static void applyRenderPass4(int index, int index2, RenderPass renderPass) {
        minecraft.ingameGUI.processAction(index, -3, 0, 0, index2, 256);
    }

    private boolean isRoundFactorVisible() {
        if (!this.settingBackGround.isEnabled()) {
            return false;
        } else {
            return !this.settingBackGround.isVisible() ? false : this.settingRoudedBackGround.isEnabled();
        }
    }

    private void applyRenderPass5(String text, float value, int index2, RenderPass renderPass) {
        CustomFont fontFaceAdapter3 = FontRegistry.fontFaceAdapter3;
        double offset = (double)((float)minecraft.displayWidth - value - 3.0F);
        int index = this.settingHud.getIndex(renderPass, index2);
        fontFaceAdapter3.applyString8(text, offset, 0.0, index);
    }

    private void applyScaledResolution3(int index3, ScaledResolution scaledResolution, int index4, int index5, FontRenderer fontRenderer, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingHud.getIndex(renderPass, index);
        if (this.settingRoudedBackGround.isEnabled()) {
            float value = (float)((int)((float)scaledResolution.getScaledWidth() / 1.5F - (float)((int)((float)index4 * 1.75F + (float)index5 + 4.0F))));
            float value2 = (float)((int)((float)scaledResolution.getScaledWidth() / 1.5F) + 1);
            float value3 = (float)((int)((float)fontRenderer.indexOffset * 1.75F + 3.0F));
            RenderUtils.processFloat9(value, 0.0F, value2, value3, (float)this.settingRoundFactor.getValue(), 1, index2);
        } else {
            TexturesGuiIconsPngManager.processAction(
            (int)((float)scaledResolution.getScaledWidth() / 1.5F - (float)((int)((float)index4 * 1.75F + (float)index5 + 4.0F))),
            0,
            (int)((float)scaledResolution.getScaledWidth() / 1.5F) + 1,
            (int)((float)fontRenderer.indexOffset * 1.75F + 3.0F),
            index2
            );
        }
    }

    private void applyRenderPass6(String text, int index2, RenderPass renderPass) {
        CustomFont fontFaceAdapter3 = FontRegistry.fontFaceAdapter3;
        double offset = (double)(-1.0F * ((float)this.tickDelay / 80.0F));
        int index = this.settingHud.getIndex(renderPass, index2);
        fontFaceAdapter3.applyString8(text, 3.0, offset, index);
    }

    private void applyScaledResolution4(int index3, ScaledResolution scaledResolution, float value, float value2, FontRenderer fontRenderer, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingHud.getIndex(renderPass, index);
        if (this.settingRoudedBackGround.isEnabled()) {
            float value3 = (float)scaledResolution.getScaledWidth() - value - 3.0F - value2;
            float scaledWidth = (float)scaledResolution.getScaledWidth();
            float value4 = (float)(fontRenderer.indexOffset + 1);
            RenderUtils.processFloat9(value3, 0.0F, scaledWidth, value4, (float)this.settingRoundFactor.getValue(), 1, index2);
        } else {
            RenderUtils.processAction(
            (float)scaledResolution.getScaledWidth() - value - 3.0F - value2, 0.0F, (float)scaledResolution.getScaledWidth(), (float)(fontRenderer.indexOffset + 1), index2
            );
        }
    }

    private static void applyRenderPass7(int index, int index2, RenderPass renderPass) {
        minecraft.ingameGUI.processAction(index, -3, 0, 0, index2, 256);
    }

    private boolean isYPosVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    private boolean isHudVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    private boolean isSideVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    private boolean isColorVisible() {
        return this.settingMode.getSelected().equals("Ausustus")
        || this.settingMode.getSelected().equals("Other")
        || this.settingMode.getSelected().equals("Basic")
        || this.settingMode.getSelected().equals("Simple");
    }

    private boolean isSizeVisible() {
        return this.settingMode.getSelected().equals("Augustus")
        || this.settingMode.getSelected().equals("Amogus")
        || this.settingMode.getSelected().equals("Logo")
        || this.settingMode.getSelected().equals("Fanart");
    }

    private boolean isColorVisible2() {
        return !this.settingBackGround.isEnabled() ? false : this.settingBackGround.isVisible();
    }

    private boolean isColorVisible3() {
        return this.settingMode.getSelected().equals("Ausustus")
        || this.settingMode.getSelected().equals("Other")
        || this.settingMode.getSelected().equals("Basic")
        || this.settingMode.getSelected().equals("Simple")
        || this.settingMode.getSelected().equals("Xave");
    }

    private boolean isBackGroundColorVisible() {
        return !this.settingBackGround.isEnabled() ? false : this.settingBackGround.isVisible();
    }

    private boolean isScaleVisible() {
        if (this.settingMode.getSelected().equals("Augustus")) {
            return false;
        } else if (this.isSizeVisible()) {
            return false;
        } else {
            return this.settingMode.getSelected().equals("None") ? false : !this.settingMode.getSelected().equals("Simple");
        }
    }

    private boolean isNewFontSizeVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean isCasingVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }
}
