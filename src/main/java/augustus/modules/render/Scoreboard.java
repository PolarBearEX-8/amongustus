package augustus.modules.render;

import client.ClientGlobals;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ScoreboardData;
import client.util.ThePlayerNameManager;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.lwjgl.opengl.GL11;

public class Scoreboard extends Module implements RenderListener {
    public BooleanSetting settingRemove = new BooleanSetting(this, "Remove", false);
    public BooleanSetting settingStick;
    public BooleanSetting settingTransparent;
    public BooleanSetting settingRounded;
    public NumberSetting settingRoundFactor;
    public NumberSetting settingPositionX;
    public NumberSetting settingPositionY;
    public NumberSetting settingScale;
    public ShaderOptions settingScale2;

    public Scoreboard() {
        super("Scoreboard", "Spielstandanzeige", new Color(34, 162, 162), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "Stick", false);
        this.settingStick = (BooleanSetting)booleanSetting.getSetting(() -> this.canExecute6());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "Transparent", false);
        this.settingTransparent = (BooleanSetting)booleanSetting2.getSetting(() -> this.canExecute7());
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "Rounded", false);
        this.settingRounded = (BooleanSetting)booleanSetting3.getSetting(() -> this.canExecute5());
        this.settingRoundFactor = new NumberSetting(this, "RoundFactor", 3.0, 0.0, 8.0, 1).getNumberSetting(() -> this.isRoundFactorVisible());
        this.settingPositionX = new NumberSetting(this, "PositionX", 100.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isPositionXVisible());
        this.settingPositionY = new NumberSetting(this, "PositionY", 60.0, 0.0, 100.0, 1).getNumberSetting(() -> this.isPositionYVisible());
        this.settingScale = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 2).getNumberSetting(() -> this.isScaleVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        ShaderOptions shaderOptions2 = shaderOptions.getShaderOptions(black);
        this.settingScale2 = (ShaderOptions)shaderOptions2.getSetting(() -> this.isScaleVisible2());
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
        if (!this.settingRemove.isEnabled()) {
            if (!ClientGlobals.moduleManager.antiStrike.checkCondition()) {
                GL11.glPushMatrix();
                ScaledResolution scaledResolution2 = new ScaledResolution(minecraft);
                GL11.glScaled((double)(1.0F / (float)scaledResolution2.getScaleFactor()), (double)(1.0F / (float)scaledResolution2.getScaleFactor()), 1.0);
                GL11.glScaled(this.settingScale.getValue(), this.settingScale.getValue(), 1.0);
                GlStateManager.pushMatrix();
                GlStateManager.applyState14();
                GlStateManager.alphaFunc(516, 0.1F);
                ScaledResolution scaledResolution3 = new ScaledResolution(
                    (double)minecraft.displayWidth * (1.0 / this.settingScale.getValue()),
                    (double)minecraft.displayHeight * (1.0 / this.settingScale.getValue()),
                    (int)this.settingScale.getValue()
                );
                ThePlayerNameManager thePlayerNameManager = minecraft.theWorld.getThePlayerNameManager();
                if (thePlayerNameManager != null) {
                    ScoreboardData customItemEntry = null;
                    ScorePlayerTeam scorePlayerTeam = thePlayerNameManager.getScorePlayerTeam(minecraft.thePlayer.getText());
                    if (scorePlayerTeam != null) {
                        int index = scorePlayerTeam.getClientSettingsBlackType().getIndex();
                        if (index >= 0) {
                            customItemEntry = thePlayerNameManager.getCustomItemEntry(3 + index);
                        }
                    }

                    ScoreboardData customItemEntry2 = customItemEntry != null ? customItemEntry : thePlayerNameManager.getCustomItemEntry(1);
                    if (customItemEntry2 != null) {
                        minecraft.ingameGUI.updateCustomItemEntry(customItemEntry2, scaledResolution3, this.settingTransparent.isEnabled());
                    }
                }

                GlStateManager.popMatrix();
                GL11.glPopMatrix();
                RenderUtils.processAction();
            }
        }
    }

    private boolean canExecute5() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isRoundFactorVisible() {
        return this.settingRemove.isEnabled() ? false : this.settingRounded.isEnabled();
    }

    private boolean canExecute6() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isScaleVisible() {
        return !this.settingRemove.isEnabled();
    }

    private boolean canExecute7() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isPositionYVisible() {
        return this.settingRemove.isEnabled() ? false : !this.settingStick.isEnabled();
    }

    private boolean isPositionXVisible() {
        return !this.settingRemove.isEnabled();
    }

    private boolean isScaleVisible2() {
        return !this.settingRemove.isEnabled();
    }
}
