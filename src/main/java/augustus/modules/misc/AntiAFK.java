package augustus.modules.misc;

import client.event.EventMove;
import client.event.MoveListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.util.BooleanSupplier;
import client.util.TimeHelper;
import java.awt.Color;

public class AntiAFK extends Module implements MoveListener {
    public RangeSetting settingAFKDelay;
    public BooleanSetting settingMove;
    public RangeSetting settingMoveDelay;
    public RangeSetting settingMoveDuration;
    public BooleanSetting settingInInv;
    public BooleanSetting settingOnlyIfAfk;
    public NumberSetting settingDelayToStartAntiAfkAfterBeingAFK;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public int keyCode2;
    public boolean expanded2;

    public AntiAFK() {
        super("AntiAFK", "NichtAbwesend", new Color(50, 168, 149), Category.MISC);
        this.settingAFKDelay = new RangeSetting(this, "AFKDelay", 10000.0, 15000.0, 0.0, 20000.0, 0)
        .getRangeSetting2();
        this.settingMove = new BooleanSetting(this, "Move", true);
        this.settingMoveDelay = new RangeSetting(this, "MoveDelay", 4000.0, 6000.0, 0.0, 20000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingMove.isEnabled());
        this.settingMoveDuration = new RangeSetting(this, "MoveDuration", 200.0, 400.0, 0.0, 1000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingMove.isEnabled());
        this.settingInInv = new BooleanSetting(this, "InInv", false);
        this.settingOnlyIfAfk = new BooleanSetting(this, "OnlyIfAfk", false);
        this.settingDelayToStartAntiAfkAfterBeingAFK = new NumberSetting(this, "DelayToStartAntiAfkAfterBeingAFK", 10000.0, 0.0, 20000.0, 0).getNumberSetting(() -> this.settingOnlyIfAfk.isEnabled());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.keyCode2 = 0;
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.keyCode2 = 0;
        this.expanded2 = false;
        this.settingAFKDelay.recalculate();
        this.settingMoveDelay.recalculate();
    }

    @Override
    public void onMove(EventMove event) {
        if (!this.checkEventMove(event)) {
            if (this.settingInInv.isEnabled() || minecraft.currentScreen == null) {
                if (!this.settingMove.isEnabled() && (event.getScaleFactor7() != 0.0F || event.getScaleFactor2() != 0.0F)) {
                    this.timer.reset();
                }

                if (this.timer.hasReached((long)this.settingAFKDelay.getIndexOffset2())) {
                    event.setState(true);
                    this.timer.reset();
                }

                if (this.timer2.hasReached((long)(this.settingMoveDelay.getIndexOffset2() + this.settingMoveDuration.getIndexOffset2()))) {
                    this.keyCode2++;
                    this.settingMoveDelay.recalculate();
                    this.settingMoveDuration.recalculate();
                    this.timer2.reset();
                    this.expanded2 = false;
                }

                if (this.timer2.hasReached((long)this.settingMoveDelay.getIndexOffset2())) {
                    this.expanded2 = true;
                }

                if (this.expanded2) {
                    int index = this.keyCode2 % 4;
                    if (index < 2) {
                        event.setScaleFactor7(index == 0 ? 1.0F : -1.0F);
                    } else {
                        event.setScaleFactor2(index == 2 ? 1.0F : -1.0F);
                    }
                }
            }
        }
    }

    private boolean checkEventMove(EventMove event) {
        if (event.getScaleFactor7() != 0.0F
        || event.getScaleFactor2() != 0.0F
        || event.isState()
        || event.isState4()
        || minecraft.thePlayer.rotationYaw != minecraft.thePlayer.scaleFactor11
        || minecraft.thePlayer.rotationPitch != minecraft.thePlayer.scaleFactor27) {
            this.timer3.reset();
        }

        return !this.timer3.hasReachedDouble(this.settingDelayToStartAntiAfkAfterBeingAFK.getValue());
    }
}
