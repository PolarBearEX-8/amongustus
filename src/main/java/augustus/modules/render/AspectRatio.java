package augustus.modules.render;

import client.event.EventTimerSpeed;
import client.event.EventTimerSpeedCallback;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import java.awt.Color;

public class AspectRatio extends Module implements EventTimerSpeedCallback {
    public NumberSetting settingAspect = new NumberSetting(this, "Aspect", 1.0, 0.0, 5.0, 2);
    public BooleanSetting settingHand = new BooleanSetting(this, "Hand", true);

    public AspectRatio() {
        super("AspectRatio", "BildschirmVerzerrung", new Color(235, 119, 110), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(48);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onTimerSpeed(EventTimerSpeed event) {
        if (!event.canExecute() || this.settingHand.isEnabled()) {
            event.processAction(event.getScaleFactor() * (float)this.settingAspect.getValue());
        }
    }
}
