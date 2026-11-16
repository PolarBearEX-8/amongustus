package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import augustus.custom.TargetHudRepair;
import client.ClientGlobals;
import client.event.RenderListener;
import client.event.TickListener;
import client.font.FontFace;
import client.font.FontRegistry;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Category;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.EasingFunction;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.setting.TabListCapeType;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.KeybindAnimation;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RunnableTask;
import client.util.StencilBlurUtils;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class TargetHud extends Module implements RenderListener, TickListener {
    public ModeSetting settingModes;
    public ModeSetting settingHealthMode;
    public ColorSetting settingBackGroundColor;
    public ColorSetting settingHealthBackGroundColor;
    public ColorSetting settingHealthColor;
    public ColorSetting settingTextColor;
    public NumberSetting settingScale;
    public ColorSetting settingBackGroundColorNew;
    public ColorSetting settingHealthBackGroundColorNew;
    public ColorSetting settingHealthColorNew;
    public ColorSetting settingHeadDamageColorNew;
    public ColorSetting settingTextColorNew;
    public BooleanSetting settingTrackEntity;
    public NumberSetting settingTrackY;
    public NumberSetting settingXCoord;
    public NumberSetting settingYCoord;
    public ModeSetting settingToggleAnimation;
    public NumberSetting settingAnimationTime;
    public ShaderOptions settingAnimationTime2;
    public KeybindAnimation keybindAnimation3;
    public KeybindAnimation keybindAnimation2;
    public float motionMultiplier1;
    public float motionMultiplier;
    public int tickDelay;
    public AbstractClientPlayer abstractClientPlayer;
    public AbstractClientPlayer abstractClientPlayer2;
    public Vec3 positionVec;

    public TargetHud() {
        super("TargetHud", "Angriffszieloberfläche", new Color(50, 168, 158), Category.RENDER);
        String[] textArray = new String[2];
        textArray[0] = "Old";
        textArray[1] = "New";
        this.settingModes = new ModeSetting(this, "Modes", "Old", textArray);
        String[] textArray2 = new String[2];
        textArray2[0] = "Entity";
        textArray2[1] = "Scoreboard";
        this.settingHealthMode = new ModeSetting(this, "HealthMode", "Entity", textArray2);
        Color color = new Color(0, 0, 0, 180);
        this.settingBackGroundColor = (ColorSetting)new ColorSetting(this, "BackGroundColor", color).getSetting(() -> this.isBackGroundColorVisible());
        Color color2 = new Color(0, 0, 0);
        this.settingHealthBackGroundColor = (ColorSetting)new ColorSetting(this, "HealthBackGroundColor", color2).getSetting(() -> this.isHealthBackGroundColorVisible());
        Color color3 = new Color(255, 0, 0);
        this.settingHealthColor = (ColorSetting)new ColorSetting(this, "HealthColor", color3).getSetting(() -> this.isHealthColorVisible());
        Color color4 = new Color(255, 255, 255);
        this.settingTextColor = (ColorSetting)new ColorSetting(this, "TextColor", color4).getSetting(() -> this.isTextColorVisible());
        this.settingScale = new NumberSetting(this, "Scale", 1.0, 0.1, 2.0, 2).getNumberSetting(() -> this.isScaleVisible());
        Color color5 = new Color(0, 0, 0, 100);
        Setting setting5 = new ColorSetting(this, "BackGroundColorNew", color5).getSetting("BackGroundColor");
        this.settingBackGroundColorNew = (ColorSetting)(((ColorSetting)setting5).getSetting(() -> this.isBackGroundColorNewVisible()));
        Color color6 = new Color(0, 0, 0);
        Setting setting7 = new ColorSetting(this, "HealthBackGroundColorNew", color6).getSetting("HealthBackGroundColor");
        this.settingHealthBackGroundColorNew = (ColorSetting)(((ColorSetting)setting7).getSetting(() -> this.isHealthBackGroundColorNewVisible()));
        Color color7 = new Color(255, 0, 0);
        Setting setting9 = new ColorSetting(this, "HealthColorNew", color7).getSetting("HealthColor");
        this.settingHealthColorNew = (ColorSetting)(((ColorSetting)setting9).getSetting(() -> this.isHealthColorNewVisible()));
        Color color8 = new Color(255, 0, 0);
        Setting setting11 = new ColorSetting(this, "HeadDamageColorNew", color8).getSetting("HeadDamageColor");
        this.settingHeadDamageColorNew = (ColorSetting)(((ColorSetting)setting11).getSetting(() -> this.isHeadDamageColorNewVisible()));
        Color color9 = new Color(255, 255, 255);
        Setting setting13 = new ColorSetting(this, "TextColorNew", color9).getSetting("TextColor");
        this.settingTextColorNew = (ColorSetting)(((ColorSetting)setting13).getSetting(() -> this.isTextColorNewVisible()));
        this.settingTrackEntity = new BooleanSetting(this, "TrackEntity", true);
        this.settingTrackY = new NumberSetting(this, "TrackY", 0.0, -2.0, 2.0, 2).getNumberSetting(() -> this.settingTrackEntity.isEnabled());
        this.settingXCoord = new NumberSetting(this, "XCoord", 60.0, 0.0, 100.0, 2).getNumberSetting(() -> this.isXCoordVisible());
        this.settingYCoord = new NumberSetting(this, "YCoord", 50.0, 0.0, 100.0, 2).getNumberSetting(() -> this.isYCoordVisible());
        String[] stringArray = KeybindAnimation.getStringArray(true);
        this.settingToggleAnimation = new ModeSetting(this, "ToggleAnimation", "easeOutBack", stringArray);
        this.settingAnimationTime = new NumberSetting(this, "AnimationTime", 500.0, 0.0, 2000.0, 0).getNumberSetting(() -> this.isAnimationTimeVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingAnimationTime2 = shaderOptions.getShaderOptions(black);
        this.tickDelay = -1;
        this.abstractClientPlayer = (AbstractClientPlayer)null;
        this.abstractClientPlayer2 = (AbstractClientPlayer)null;
        Vec3 vec = new Vec3(0.0, 0.0, 0.0);
        this.positionVec = vec;
        this.keybindAnimation3 = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
        this.keybindAnimation2 = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.tickDelay = -1;
        this.abstractClientPlayer = null;
        this.abstractClientPlayer2 = null;
    }

    @Override
    public void onPreDisable() {
        this.keybindAnimation3
            .updateEasingFunction(
                (int)this.settingAnimationTime.getValue(),
                KeybindAnimation.getEasingFunction(this.settingToggleAnimation.getSelected()),
                1,
                true
            );
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        AbstractClientPlayer abstractClientPlayer3;
        if (ClientGlobals.moduleManager.killAura.target instanceof AbstractClientPlayer) {
            abstractClientPlayer3 = (AbstractClientPlayer)ClientGlobals.moduleManager.killAura.target;
        } else if (ClientGlobals.moduleManager.aimBot.target instanceof AbstractClientPlayer) {
            abstractClientPlayer3 = (AbstractClientPlayer)ClientGlobals.moduleManager.aimBot.target;
        } else if (!(ClientGlobals.moduleManager.aimAssist.target instanceof AbstractClientPlayer)) {
            abstractClientPlayer3 = null;
        } else {
            abstractClientPlayer3 = (AbstractClientPlayer)ClientGlobals.moduleManager.aimAssist.target;
        }

        AbstractClientPlayer abstractClientPlayer4 = (AbstractClientPlayer)TargetHudRepair.resolve(abstractClientPlayer3);
        if (abstractClientPlayer4 != this.abstractClientPlayer2) {
            KeybindAnimation keybindAnimation32 = this.keybindAnimation3;
            int animationTime = (int)this.settingAnimationTime.getValue();
            keybindAnimation32.updateEasingFunction(animationTime, KeybindAnimation.getEasingFunction(this.settingToggleAnimation.getSelected()), abstractClientPlayer4 != null ? 1 : 0, true);
        }

        if (abstractClientPlayer4 != null) {
            this.abstractClientPlayer = abstractClientPlayer4;
        }

        if ((!this.settingTrackEntity.isEnabled() || this.positionVec.zCoord >= 0.0 && this.positionVec.zCoord <= 1.0) && this.abstractClientPlayer != null) {
            if (this.settingModes.getSelected().equals("Old")) {
                AbstractClientPlayer abstractClientPlayer5 = this.abstractClientPlayer;
                this.applyAbstractClientPlayer3(abstractClientPlayer5, abstractClientPlayer4, scaledResolution);
            } else {
                AbstractClientPlayer abstractClientPlayer6 = this.abstractClientPlayer;
                this.applyAbstractClientPlayer2(abstractClientPlayer6, abstractClientPlayer4, scaledResolution);
            }

            this.tickDelay = this.abstractClientPlayer.getEntityId();
        }

        this.abstractClientPlayer2 = abstractClientPlayer4;
        RenderUtils.processAction();
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.settingTrackEntity.isEnabled()) {
            if (this.abstractClientPlayer != null) {
                if (this.keybindAnimation3.getDouble2() != 1.0 || this.abstractClientPlayer2 != null) {
                    Vec3 vec = new Vec3(
                        this.abstractClientPlayer.lastTickPosX
                            + (this.abstractClientPlayer.posX - this.abstractClientPlayer.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6
                            - minecraft.getRenderManager().getRenderPosX(),
                        this.abstractClientPlayer.lastTickPosY
                            + (this.abstractClientPlayer.posY - this.abstractClientPlayer.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6
                            - minecraft.getRenderManager().getRenderPosY(),
                        this.abstractClientPlayer.lastTickPosZ
                            + (this.abstractClientPlayer.posZ - this.abstractClientPlayer.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6
                            - minecraft.getRenderManager().getRenderPosZ()
                    );
                    this.positionVec = RuntimeBridge.projectPoint(
                        vec.xCoord,
                        vec.yCoord + (double)(this.abstractClientPlayer.height / 2.0F) + this.settingTrackY.getValue(),
                        vec.zCoord,
                        new ScaledResolution(minecraft)
                    );
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        String selected = this.settingModes.getSelected();
        this.applyString4(selected);
    }

    private boolean isHealthColorNewVisible() {
        return this.settingModes.getSelected().equals("New");
    }

    private boolean isXCoordVisible() {
        return !this.settingTrackEntity.isEnabled();
    }

    private boolean isBackGroundColorVisible() {
        return this.settingModes.getSelected().equals("Old");
    }

    private void applyAbstractClientPlayer(int x, int y, int z, AbstractClientPlayer abstractClientPlayer3, boolean enabled, RenderPass renderPass) {
        float value = (float)(-x);
        float value2 = (float)(-y);
        float value3 = (float)x;
        float value4 = (float)y;
        int index = RandomUtil.getIndex(renderPass, z);
        RenderUtils.processFloat9(value, value2, value3, value4, 20.0F, 1, this.settingAnimationTime2.getIndex(renderPass, index));
        if (renderPass == RenderPass.NONE) {
            int indexOffset4 = abstractClientPlayer3.hurtTime;
            int index2 = 66 - indexOffset4;
            int index3 = -113 + indexOffset4 / 2;
            int index4 = -33 + indexOffset4 / 2;
            StencilBlurUtils.updateState();
            StencilBlurUtils.updateState2();
            RenderUtils.processAction((float)index3, (float)index4, (float)(index3 + index2), (float)(index4 + index2), 15.0F, 1, Color.white);
            StencilBlurUtils.updateInt(1);
            ResourceLocation resourceLocation2 = abstractClientPlayer3.getResourceLocation2();
            this.applyResourceLocation(resourceLocation2, abstractClientPlayer3, enabled, index3, index4, index2);
            StencilBlurUtils.updateState3();
            if (indexOffset4 > 0) {
                int int12 = this.settingHeadDamageColorNew.getInt12(0);
                float value5 = (float)index3;
                float value6 = (float)index4;
                float value7 = (float)(index3 + index2);
                float value8 = (float)(index4 + index2);
                int index5 = 20 * indexOffset4;
                RenderUtils.processFloat9(value5, value6, value7, value8, 15.0F, 1, RandomUtil.getInt13(int12, index5));
            }
        }

        short id = 147;
        if (renderPass == RenderPass.NONE) {
            float scaleFactor = CombatUtil.getScaleFactor(this.settingHealthMode, abstractClientPlayer3);
            if (Integer.signum(this.tickDelay) == -1 || this.tickDelay != abstractClientPlayer3.getEntityId()) {
                this.motionMultiplier1 = scaleFactor;
                this.motionMultiplier = scaleFactor;
            }

            if (this.motionMultiplier1 != scaleFactor) {
                this.motionMultiplier = this.motionMultiplier1;
                this.motionMultiplier1 = scaleFactor;
                this.keybindAnimation2.updateEasingFunction(400, EasingFunction.easeOutCubic, 1, false);
            }
        }

        float motionMultiplier12 = this.motionMultiplier1;
        float floatEntry = abstractClientPlayer3.getFloat();
        float value9 = motionMultiplier12 / floatEntry;
        if (renderPass == RenderPass.NONE) {
            float value10 = this.motionMultiplier / abstractClientPlayer3.getFloat();
            this.keybindAnimation2.processFloat((float)id * value10, 0.0F, (float)id * value9, 0.0F);
        }

        RenderPass renderPass2 = RenderPass.BLUR;
        if (renderPass != renderPass2 && (float)id * value9 > -40.0F) {
            ServerData serverData = minecraft.getServerData();
            if (serverData == null || !serverData.text.contains("gommehd.net")) {
                int int122 = this.settingHealthBackGroundColorNew.getInt12(0);
                RenderUtils.processFloat9(-37.0F, 0.0F, 110.0F, 20.0F, 8.0F, 1, this.settingAnimationTime2.getIndex(renderPass, int122));
                float floatEntry2 = MathHelper.clamp_float(-37.0F + this.keybindAnimation2.getFloat(), -37.0F, 110.0F);
                int int123 = this.settingHealthColorNew.getInt12(0);
                RenderUtils.processFloat9(-37.0F, 0.0F, floatEntry2, 20.0F, 8.0F, 1, this.settingAnimationTime2.getIndex(renderPass, int123));
            }
        }
    }

    private void applyResourceLocation(ResourceLocation location, AbstractClientPlayer abstractClientPlayer3, boolean enabled, int x, int y, int z) {
        minecraft.getTextureManager().applyResourceLocation3(location);
        byte state = (byte)(enabled ? 8 : 0);
        int index = 8 + state;
        byte state2 = (byte)(enabled ? -1 : 1);
        int index2 = 8 * state2;
        TexturesGuiIconsPngManager.processAction(x, y, 8.0F, (float)index, 8, index2, z, z, 64.0F, 64.0F);
    }

    private boolean isScaleVisible() {
        return this.settingModes.getSelected().equals("Old");
    }

    private void applyNetworkPlayerInfo(int index2, NetworkPlayerInfo networkPlayerInfo, AbstractClientPlayer abstractClientPlayer3, boolean enabled, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index2);
        RenderUtils.processFloat9(-156.0F, -56.0F, 156.0F, 56.0F, 10.0F, 1, this.settingAnimationTime2.getIndex(renderPass, index));
        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
        if (renderPass == RenderPass.NONE) {
            StencilBlurUtils.updateState();
            StencilBlurUtils.updateState2();
            RenderUtils.processAction(50.0F, -50.0F, 150.0F, 50.0F, 8.0F, 1, Color.white);
            StencilBlurUtils.updateInt(1);
            ResourceLocation resourceLocation = networkPlayerInfo.getResourceLocation();
            this.applyResourceLocation(resourceLocation, abstractClientPlayer3, enabled, 50, -50, 100);
            StencilBlurUtils.updateState3();
        }

        if (renderPass == RenderPass.NONE) {
            GL11.glPushMatrix();
            GL11.glScaled((double)2, (double)2, 1.0);
            FontRenderer fontRendererObj = minecraft.fontRendererObj;
            String name = abstractClientPlayer3.getGameProfile().getName();
            int index3 = -145 / 2;
            int index4 = -45 / 2;
            fontRendererObj.getIndex(name, index3, index4, this.settingTextColor.getInt12(0));
            GL11.glPopMatrix();
        }

        byte state = 0;
        if (renderPass == RenderPass.NONE) {
            float scaleFactor = CombatUtil.getScaleFactor(this.settingHealthMode, abstractClientPlayer3);
            if (Integer.signum(this.tickDelay) == -1 || this.tickDelay != abstractClientPlayer3.getEntityId()) {
                this.motionMultiplier1 = scaleFactor;
                this.motionMultiplier = scaleFactor;
            }

            if (this.motionMultiplier1 != scaleFactor) {
                this.motionMultiplier = this.motionMultiplier1;
                this.motionMultiplier1 = scaleFactor;
                this.keybindAnimation2.updateEasingFunction(400, EasingFunction.easeOutCubic, 1, false);
            }
        }

        float value = this.motionMultiplier1 / abstractClientPlayer3.getFloat();
        if (renderPass == RenderPass.NONE) {
            float value2 = this.motionMultiplier / abstractClientPlayer3.getFloat();
            this.keybindAnimation2.processFloat((float)190 * value2, 0.0F, (float)190 * value, 0.0F);
        }

        if (renderPass != RenderPass.BLUR) {
            if ((float)190 * value > -150.0F && minecraft.getServerData() != null && !minecraft.getServerData().text.contains("gommehd.net")) {
                int int12 = this.settingHealthBackGroundColor.getInt12(0);
                RenderUtils.processFloat9(-150.0F, -17.0F, 40.0F, 3.0F, 3.0F, 1, this.settingAnimationTime2.getIndex(renderPass, int12));
                float floatEntry = MathHelper.clamp_float(-150.0F + this.keybindAnimation2.getFloat(), -150.0F, 40.0F);
                int int122 = this.settingHealthColor.getInt12(0);
                RenderUtils.processFloat9(-150.0F, -17.0F, floatEntry, 3.0F, 3.0F, 1, this.settingAnimationTime2.getIndex(renderPass, int122));
                state = (byte)(state + 10);
            }

            int index5 = -2 + state;
            GL11.glPushMatrix();
            GL11.glScaled((double)2, (double)2, 1.0);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();
            if (abstractClientPlayer3.getItemStack3(3) != null) {
                RenderUtils.applyItemStack(abstractClientPlayer3.getItemStack3(3), -77, index5, false);
            }

            if (abstractClientPlayer3.getItemStack3(2) != null) {
                RenderUtils.applyItemStack(abstractClientPlayer3.getItemStack3(2), -59, index5, false);
            }

            if (abstractClientPlayer3.getItemStack3(1) != null) {
                RenderUtils.applyItemStack(abstractClientPlayer3.getItemStack3(1), -41, index5, false);
            }

            if (abstractClientPlayer3.getItemStack3(0) != null) {
                RenderUtils.applyItemStack(abstractClientPlayer3.getItemStack3(0), -23, index5, false);
            }

            if (abstractClientPlayer3.getHeldItem() != null) {
                RenderUtils.applyItemStack(abstractClientPlayer3.getHeldItem(), 0, index5 - 1, false);
            }

            GlStateManager.popMatrix();
            GL11.glPopMatrix();
        }

        MovementHandler.processAction8();
    }

    private boolean isAnimationTimeVisible() {
        return !this.settingToggleAnimation.getSelected().equals("None");
    }

    public void applyAbstractClientPlayer2(AbstractClientPlayer abstractClientPlayer3, AbstractClientPlayer abstractClientPlayer4, ScaledResolution scaledResolution) {
        if (abstractClientPlayer3 != null) {
            if (this.keybindAnimation3.getDouble2() == 1.0 && abstractClientPlayer4 == null) {
                return;
            }

            GameProfile gameProfile = abstractClientPlayer3.getGameProfile();
            boolean enabled;
            if (abstractClientPlayer3.checkTabListCapeType(TabListCapeType.CAPE) && (gameProfile.getName().equals("Dinnerbone") || gameProfile.getName().equals("Grumm"))) {
                enabled = true;
            } else {
                enabled = false;
            }

            if (minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(abstractClientPlayer3.getEntityUniqueID()) == null) {
                return;
            }

            GL11.glPushMatrix();
            GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
            int index = minecraft.getFramebuffer().indexOffset2 - 240;
            double offset;
            if (this.settingTrackEntity.isEnabled()) {
                offset = this.positionVec.xCoord / (double)scaledResolution.getScaledWidth();
            } else {
                offset = this.settingXCoord.getValue() / 100.0;
            }

            int index2 = 120 + (int)((double)index * offset);
            int index3 = minecraft.getFramebuffer().indexOffset1 - 80;
            double offset2;
            if (this.settingTrackEntity.isEnabled()) {
                offset2 = this.positionVec.yCoord / (double)scaledResolution.getScaledHeight();
            } else {
                offset2 = this.settingYCoord.getValue() / 100.0;
            }

            int index4 = 40 + (int)((double)index3 * offset2);
            GL11.glTranslatef((float)index2, (float)index4, 0.0F);
            double double3 = this.keybindAnimation3.getDouble3();
            if (this.keybindAnimation3.getDouble2() < 1.0) {
                GL11.glScaled(double3, double3, 1.0);
            }

            int int12 = this.settingBackGroundColorNew.getInt12(0);
            boolean enabled2 = this.settingAnimationTime2.canExecute();
            boolean enabled3 = this.settingAnimationTime2.canExecute2();
            MovementHandler.processBoolean(enabled2, enabled3);
            boolean enabled4 = enabled;
            RunnableTask runnableTask = renderPass -> this.applyAbstractClientPlayer(120, 40, int12, abstractClientPlayer3, enabled4, renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
            FontFace fontFace8 = FontRegistry.fontFace8;
            String name = abstractClientPlayer3.getGameProfile().getName();
            int scaleFactor = (int)fontFace8.getScaleFactor(name);
            FontFace fontFace82 = FontRegistry.fontFace8;
            String name2 = abstractClientPlayer3.getGameProfile().getName();
            double offset3 = (double)((int)(33.0F + (float)(-scaleFactor) / 2.0F));
            int int122 = this.settingTextColorNew.getInt12(0);
            double offset4 = -25.0;
            fontFace82.applyString8(name2, offset3, offset4, int122);
            GL11.glPopMatrix();
        }
    }

    private boolean isTextColorNewVisible() {
        return this.settingModes.getSelected().equals("New");
    }

    public void applyAbstractClientPlayer3(AbstractClientPlayer abstractClientPlayer3, AbstractClientPlayer abstractClientPlayer4, ScaledResolution scaledResolution) {
        if (abstractClientPlayer3 != null) {
            if (this.keybindAnimation3.getDouble2() == 1.0 && abstractClientPlayer4 == null) {
                return;
            }

            GameProfile gameProfile = abstractClientPlayer3.getGameProfile();
            boolean enabled;
            if (abstractClientPlayer3.checkTabListCapeType(TabListCapeType.CAPE) && (gameProfile.getName().equals("Dinnerbone") || gameProfile.getName().equals("Grumm"))) {
                enabled = true;
            } else {
                enabled = false;
            }

            NetworkPlayerInfo networkPlayerInfo = minecraft.getNetHandlerPlayClient().getNetworkPlayerInfo(abstractClientPlayer3.getEntityUniqueID());
            if (networkPlayerInfo == null) {
                return;
            }

            GL11.glPushMatrix();
            GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
            int index = minecraft.getFramebuffer().indexOffset2 - 312;
            double offset;
            if (this.settingTrackEntity.isEnabled()) {
                offset = this.positionVec.xCoord / (double)scaledResolution.getScaledWidth();
            } else {
                offset = this.settingXCoord.getValue() / 100.0;
            }

            int index2 = 156 + (int)((double)index * offset);
            int index3 = minecraft.getFramebuffer().indexOffset1 - 112;
            double offset2;
            if (this.settingTrackEntity.isEnabled()) {
                offset2 = this.positionVec.yCoord / (double)scaledResolution.getScaledHeight();
            } else {
                offset2 = this.settingYCoord.getValue() / 100.0;
            }

            int index4 = 56 + (int)((double)index3 * offset2);
            GL11.glTranslatef((float)index2, (float)index4, 0.0F);
            double value = this.settingScale.getValue();
            GL11.glScaled(value, this.settingScale.getValue(), 1.0);
            double double3 = this.keybindAnimation3.getDouble3();
            if (this.keybindAnimation3.getDouble2() < 1.0) {
                GL11.glScaled(double3, double3, 1.0);
            }

            int int12 = this.settingBackGroundColor.getInt12(0);
            boolean enabled2 = this.settingAnimationTime2.canExecute();
            boolean enabled3 = this.settingAnimationTime2.canExecute2();
            MovementHandler.processBoolean(enabled2, enabled3);
            boolean enabled4 = enabled;
            RunnableTask runnableTask = renderPass -> this.applyNetworkPlayerInfo(int12, networkPlayerInfo, abstractClientPlayer3, enabled4, renderPass);
            MovementHandler.applyRunnableTask(true, runnableTask);
            GL11.glPopMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
        }
    }

    private boolean isHeadDamageColorNewVisible() {
        return this.settingModes.getSelected().equals("New");
    }

    private boolean isYCoordVisible() {
        return !this.settingTrackEntity.isEnabled();
    }

    private boolean isHealthBackGroundColorVisible() {
        return this.settingModes.getSelected().equals("Old");
    }

    private boolean isTextColorVisible() {
        return this.settingModes.getSelected().equals("Old");
    }

    private boolean isHealthBackGroundColorNewVisible() {
        return this.settingModes.getSelected().equals("New");
    }

    private boolean isHealthColorVisible() {
        return this.settingModes.getSelected().equals("Old");
    }

    private boolean isBackGroundColorNewVisible() {
        return this.settingModes.getSelected().equals("New");
    }
}
