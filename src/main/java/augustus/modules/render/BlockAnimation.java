package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import java.awt.Color;

public class BlockAnimation extends Module {
    public ModeSetting settingBlockAnimations = new ModeSetting(
        this,
        "BlockAnimations",
        
        "Minimalistic",
        new String[]{"None", "1.7", "Wiggle", "Minimalistic", "Swong", "Jello", "Exhibition", "Exhibition2", "Forward", "Jigsaw", "Violence", "Sigma", "Spin"}
    );
    public NumberSetting settingHeight = new NumberSetting(this, "Height", 0.1, -0.5, 1.5, 3);
    public BooleanSetting settingFakeBlock = new BooleanSetting(this, "FakeBlock", false);
    public BooleanSetting settingOnlyWhileKillAura;
    public NumberSetting settingSpeed;

    public BlockAnimation() {
        super("BlockAnimation", "BlockAnimation", new Color(136, 29, 163), Category.RENDER);
        BooleanSetting booleanSetting = new BooleanSetting(this, "OnlyWhileKillAura", false);
        this.settingOnlyWhileKillAura = (BooleanSetting)booleanSetting.getSetting(this.settingFakeBlock::isEnabled);
        this.settingSpeed = new NumberSetting(this, "Speed", 1.0, 0.1, 3.0, 3);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }
}
