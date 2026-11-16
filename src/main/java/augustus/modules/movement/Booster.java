package augustus.modules.movement;

import client.event.EventSlowDown;
import client.event.SlowDownListener;
import client.module.Category;
import client.module.Module;
import client.setting.NumberSetting;
import client.setting.StringSetting;
import client.util.KeyUtils;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.awt.Color;

public class Booster extends Module implements SlowDownListener {
    public NumberSetting settingBoostTicks = new NumberSetting(this, "BoostTicks", 2.0, 0.0, 20.0, 0);
    public NumberSetting settingDelay = new NumberSetting(this, "Delay", 2000.0, 0.0, 10000.0, 0);
    public StringSetting settingKeyToToggleBoost = new StringSetting(this, "Key to toggle boost");
    public TimeHelper timer = new TimeHelper();

    public Booster() {
        super("Booster", "Beschläuniger", new Color(214, 84, 186), Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(41);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onSlowDown(EventSlowDown event) {
    }

    @Override
    public void onCustomSlowDown(EventSlowDown event) {
        if (KeyUtils.checkCondition(this.settingKeyToToggleBoost.getInt3())) {
            event.processAction((int)Math.max(0.0, this.settingBoostTicks.getValue()));
            this.timer.reset();
            RayTraceUtil.applyString("Boosted");
        }
    }
}
