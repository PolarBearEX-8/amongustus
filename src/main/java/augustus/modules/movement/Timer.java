package augustus.modules.movement;

import client.event.EventMotionUpdate;
import client.event.EventSafeWalk;
import client.event.EventSendPacket;
import client.event.EventWorldLoad;
import client.event.MotionUpdateListener;
import client.event.NetworkPacketListener;
import client.event.SafeWalkListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.TimeHelper;
import java.awt.Color;

public class Timer extends Module implements MotionUpdateListener, WorldLoadListener, SafeWalkListener, NetworkPacketListener {
    public NumberSetting settingTime = new NumberSetting(this, "Time", 1.0, 0.1, 10.0, 2);
    public BooleanSetting settingTimerAfterVelocity = new BooleanSetting(this, "TimerAfterVelocity", false);
    public NumberSetting settingTimerAfterVelocityVal;
    public BooleanSetting settingTimerAfterFallDamage;
    public NumberSetting settingTimerAfterFallDamageVal;
    public BooleanSetting settingTimerAtBlink;
    public NumberSetting settingTimerAtBlinkVal;
    public TimeHelper timer;
    public boolean enabled2;
    public boolean expanded2;

    public Timer() {
        super("Timer", "Zeitschaltuhr", new Color(180, 176, 119), Category.MOVEMENT);
        this.settingTimerAfterVelocityVal = new NumberSetting(this, "TimerAfterVelocityVal", 1.0, 0.1, 10.0, 2).getNumberSetting(() -> this.settingTimerAfterVelocity.isEnabled());
        this.settingTimerAfterFallDamage = new BooleanSetting(this, "TimerAfterFallDamage", false);
        this.settingTimerAfterFallDamageVal = new NumberSetting(this, "TimerAfterFallDamageVal", 1.0, 0.1, 10.0, 2).getNumberSetting(() -> this.settingTimerAfterFallDamage.isEnabled());
        this.settingTimerAtBlink = new BooleanSetting(this, "TimerAtBlink", false);
        this.settingTimerAtBlinkVal = new NumberSetting(this, "TimerAtBlinkVal", 1.0, 0.1, 10.0, 2).getNumberSetting(() -> this.settingTimerAtBlink.isEnabled());
        this.timer = new TimeHelper();
        this.enabled2 = false;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(18, -4);
        this.registerEvent(17);
        this.registerEvent(35);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (packet.isState()) {
            this.expanded2 = true;
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (packet.isState()) {
            this.expanded2 = true;
        }
    }

    @Override
    public void onSafeWalk(EventSafeWalk event) {
        if (this.checkCondition(event.getCoordinate3(), minecraft.thePlayer.posX, 0.01)) {
            this.enabled2 = this.timer.hasReached(100L);
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        this.applyString4(String.valueOf(minecraft.getTimer().scaleFactor2));
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        if (event.getWorld() == null) {
            minecraft.getTimer().scaleFactor2 = 1.0F;
        }
    }

    private boolean checkCondition(double x, double y, double z) {
        return Math.abs(x - y) != z;
    }
}
