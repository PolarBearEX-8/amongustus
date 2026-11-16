package augustus.modules.render;

import client.Client;
import client.event.EventRenderHand;
import client.event.EventRenderHandCallback;
import client.module.Category;
import client.module.Module;
import client.setting.ColorSetting;
import java.awt.Color;

public class CustomClientColor extends Module implements EventRenderHandCallback {
    public ColorSetting settingColorSetting = new ColorSetting(this, "ColorSetting", new Color(41, 146, 222));

    public CustomClientColor() {
        super("CustomClientColor", "EinstellbareKundenFarbe", new Color(145, 100, 250), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(42);
    }

    @Override
    public void onDisable() {
        Client.getClient().applyColor(new Color(41, 146, 222));
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRenderHand(EventRenderHand event) {
        Client.getClient().applyColor(this.settingColorSetting.getColor(0));
    }
}
