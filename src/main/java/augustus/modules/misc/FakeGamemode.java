package augustus.modules.misc;

import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.ModeSetting;
import client.setting.WorldSettingsGameType;
import java.awt.Color;

public class FakeGamemode extends Module implements TickListener {
    public WorldSettingsGameType savedGameType = WorldSettingsGameType.SURVIVAL;

    public ModeSetting settingGamemode = new ModeSetting(
        this, "Gamemode", "Creative", new String[]{"Survival", "Creative", "Adventure", "Spectator"}
    );

    public FakeGamemode() {
        super("FakeGamemode", "UnechterSpielmodus", new Color(243, 245, 152), Category.MISC);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        minecraft.playerController.applyWorldSettingsGameType(this.savedGameType);
    }

    @Override
    public void onPreDisable() {
        if (minecraft.playerController != null) {
            this.savedGameType = minecraft.playerController.getWorldSettingsGameType();
        }
    }

    @Override
    public void onRunTick() {
        String selected = this.settingGamemode.getSelected();
        byte state = -1;
        switch (selected.hashCode()) {
            case -1535938722:
                if (selected.equals("Survival")) {
                    state = 0;
                }
                break;
            case 319374543:
                if (selected.equals("Spectator")) {
                    state = 3;
                }
                break;
            case 1309873904:
                if (selected.equals("Adventure")) {
                    state = 2;
                }
                break;
            case 1885066191:
                boolean enabled = selected.equals("Creative");
                if (enabled) {
                    state = 1;
                }
        }

        WorldSettingsGameType worldSettingsGameTypeKind;
        switch (state) {
            case 0:
                worldSettingsGameTypeKind = WorldSettingsGameType.SURVIVAL;
                break;
            case 1:
                worldSettingsGameTypeKind = WorldSettingsGameType.CREATIVE;
                break;
            case 2:
                worldSettingsGameTypeKind = WorldSettingsGameType.ADVENTURE;
                break;
            case 3:
                worldSettingsGameTypeKind = WorldSettingsGameType.SPECTATOR;
                break;
            default:
                worldSettingsGameTypeKind = minecraft.playerController.getWorldSettingsGameType();
        }

        minecraft.playerController.applyWorldSettingsGameType(worldSettingsGameTypeKind);
    }
}
