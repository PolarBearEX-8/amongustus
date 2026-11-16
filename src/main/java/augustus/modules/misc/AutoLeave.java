package augustus.modules.misc;

import augustus.custom.RuntimeBridge;
import client.Client;
import client.ClientGlobals;
import client.event.EventKeyInput;
import client.event.EventSafeWalk;
import client.event.EventWorldLoad;
import client.event.KeyInputListener;
import client.event.RenderListener;
import client.event.SafeWalkListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.StringSetting;
import client.util.BooleanSupplier;
import client.util.PlayerUtil;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;

public class AutoLeave extends Module implements KeyInputListener, RenderListener, WorldLoadListener, TickListener, SafeWalkListener {
    public BooleanSetting settingCalculateWithHit = new BooleanSetting(this, "CalculateWithHit", true);
    public NumberSetting settingTime;
    public BooleanSetting settingDisplayTime;
    public NumberSetting settingXCoord;
    public NumberSetting settingYCoord;
    public BooleanSetting settingAutoLeaveBool;
    public NumberSetting settingHeats;
    public ModeSetting settingMode;
    public BooleanSetting settingLeaveOnKey;
    public BooleanSetting settingLeaveOnDeath;
    public BooleanSetting settingLeaveIfStaffArrives;
    public StringSetting settingKey;
    public long timestampMillis;
    public TimeHelper timer;
    public boolean expanded2;
    public TimeHelper timer2;

    public AutoLeave() {
        super("AutoLeave", "AutomatischesVerlassen", new Color(50, 168, 82), Category.MISC);
        this.settingTime = new NumberSetting(this, "Time", 12000.0, 0.0, 20000.0, 0).getNumberSetting(() -> this.settingCalculateWithHit.isEnabled());
        BooleanSetting booleanSetting = new BooleanSetting(this, "DisplayTime", true);
        this.settingDisplayTime = (BooleanSetting)booleanSetting.getSetting(() -> this.settingCalculateWithHit.isEnabled());
        this.settingXCoord = new NumberSetting(this, "X-Coord", 100.0, 0.0, 100.0, 2).getNumberSetting(() -> this.isXCoordVisible());
        this.settingYCoord = new NumberSetting(this, "Y-Coord", 100.0, 0.0, 100.0, 2).getNumberSetting(() -> this.isYCoordVisible());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "AutoLeaveBool", false);
        this.settingAutoLeaveBool = (BooleanSetting)booleanSetting2.getSetting("AutoLeave");
        this.settingHeats = new NumberSetting(this, "Heats", 2.0, 0.0, 20.0, 0).getNumberSetting(() -> this.settingAutoLeaveBool.isEnabled());
        this.settingMode = new ModeSetting(this, "Mode", "Lobby", new String[]{"Lobby", "Server"});
        this.settingLeaveOnKey = new BooleanSetting(this, "LeaveOnKey", false);
        this.settingLeaveOnDeath = new BooleanSetting(this, "LeaveOnDeath", false);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "LeaveIfStaffArrives", false);
        this.settingLeaveIfStaffArrives = (BooleanSetting)booleanSetting3.getSetting(() -> canExecute5());
        this.settingKey = new StringSetting(this, "Key");
        this.timestampMillis = System.currentTimeMillis();
        this.timer = new TimeHelper();
        this.expanded2 = false;
        this.timer2 = new TimeHelper();
    }

    @Override
    public void onEnable() {
        this.registerEvent(14);
        this.registerEvent(32);
        this.registerEvent(17);
        this.registerEvent(40);
        this.registerEvent(35);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
    }

    @Override
    public void onKeyInput(EventKeyInput event) {
        if (this.settingLeaveOnKey.isEnabled()) {
            int index = event.getIndex();
            if (index == this.settingKey.getInt3()) {
                this.updateState();
            }
        }
    }

    @Override
    public void onSafeWalk(EventSafeWalk event) {
        double coordinate3 = event.getCoordinate3();
        if (PlayerUtil.checkCondition(coordinate3, minecraft.thePlayer.posX, 0.01)) {
            double coordinate = event.getCoordinate();
            if (PlayerUtil.checkCondition(coordinate, minecraft.thePlayer.posY, 0.01)) {
                double coordinate2 = event.getCoordinate2();
                if (PlayerUtil.checkCondition(coordinate2, minecraft.thePlayer.posZ, 0.01)) {
                    if (event.getText().equals("game.player.hurt.fall.small") || event.getText().equals("game.player.hurt.fall.big")) {
                        this.timer.reset();
                    }

                    if (event.getText().equals("game.player.hurt")) {
                        if (this.timer.hasReached(100L)) {
                            this.timestampMillis = System.currentTimeMillis();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingCalculateWithHit.isEnabled() && this.settingDisplayTime.isEnabled()) {
            double offset = (double)((int)(System.currentTimeMillis() - this.timestampMillis));
            String text = RuntimeBridge.formatNumberString(offset / 1000.0, 1);
            FontRenderer fontRendererObj = minecraft.fontRendererObj;
            int index = fontRendererObj.getIndex(text);
            int index2 = fontRendererObj.indexOffset - 1;
            int index3 = (int)((double)(scaledResolution.getScaledWidth() - index) / 100.0 * this.settingXCoord.getValue());
            int index4 = (int)((double)(scaledResolution.getScaledHeight() - index2) / 100.0 * this.settingYCoord.getValue());
            int index5 = offset > this.settingTime.getValue()
            ? Color.GREEN.getRGB()
            : Color.RED.getRGB();
            fontRendererObj.getIndex(text, index3, index4, index5);
        }

        RenderUtils.processAction();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.timestampMillis = System.currentTimeMillis();
    }

    @Override
    public void onPreTick() {
        if (this.settingAutoLeaveBool.isEnabled()) {
            double float2 = (double)minecraft.thePlayer.getFloat2();
            if (float2 <= this.settingHeats.getValue()) {
                this.updateState();
            }
        }

        if (this.settingLeaveIfStaffArrives.isEnabled()
        && ClientGlobals.moduleManager.staffDetector.isToggled()
        && ClientGlobals.moduleManager.staffDetector.canExecute5()
        && this.timer2.hasReached(1000L)) {
            this.updateState2();
            this.timer2.reset();
        }

        if (minecraft.thePlayer.isDead && !this.expanded2 && this.settingLeaveOnDeath.isEnabled()) {
            this.updateState2();
        }

        this.expanded2 = minecraft.thePlayer.isDead;
    }

    private void updateState() {
        if ((double)((int)(System.currentTimeMillis() - this.timestampMillis)) > this.settingTime.getValue()
        || !this.settingCalculateWithHit.isEnabled()) {
            this.updateState2();
            this.timestampMillis = System.currentTimeMillis();
        }
    }

    private boolean isXCoordVisible() {
        return !this.settingCalculateWithHit.isEnabled() ? false : this.settingDisplayTime.isEnabled();
    }

    private void updateState2() {
        String selected = this.settingMode.getSelected();
switch (selected) {
            case "Lobby":
                minecraft.thePlayer.sendChatMessage("/hub");
                return;
            case "Server":
                minecraft.state = true;
                boolean gamePaused = minecraft.isGamePaused();
                minecraft.theWorld.updateState();
                minecraft.loadWorld((WorldClient)null);
                if (gamePaused) {
                    GuiMainMenu guiMainMenu = new GuiMainMenu();
                    minecraft.displayGuiScreen(guiMainMenu);
                    return;
                }
                GuiMultiplayer auxiliaryGuiScreenAdapter = new GuiMultiplayer(new GuiMainMenu());
                minecraft.displayGuiScreen(auxiliaryGuiScreenAdapter);
                return;
        }
    }

    private boolean isYCoordVisible() {
        return !this.settingCalculateWithHit.isEnabled() ? false : this.settingDisplayTime.isEnabled();
    }

    private static boolean canExecute5() {
        return Client.getClient().getModuleManager().staffDetector.isToggled();
    }
}
