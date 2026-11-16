package augustus.modules.movement;

import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.RangeSetting;
import client.util.KeyUtils;
import client.util.Pair;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.server.S07PacketRespawn;

public class SaveMoveKeys extends Module implements PacketListener {
    public HashMap savedKeys;
    public int keyCode2;
    public RangeSetting settingLoadKeyDelay;

    public SaveMoveKeys() {
        super("SaveMoveKeys", "SchlüsselSpeicherung", new Color(235, 64, 52), Category.MOVEMENT);
        this.savedKeys = new HashMap();
        this.keyCode2 = 0;
        this.settingLoadKeyDelay = new RangeSetting(this, "LoadKeyDelay", 1.0, 3.0, 0.0, 5.0, 0).getRangeSetting2();
    }

    @Override
    public void onEnable() {
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.savedKeys.clear();
    }

    @Override
    public void onPreDisable() {
        this.keyCode2 = 0;
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S07PacketRespawn) {
            this.savedKeys.clear();
        }
    }

    public void updateState() {
        this.keyCode2 = 0;
    }

    public void updateState3() {
        if (minecraft.currentScreen == null) {
            this.keyCode2++;
        }
    }

    public void applyScorePlayerTeam(KeyBinding keyBinding) {
        if (this.isToggled()) {
            if (keyBinding.getKeyDescription().equals("key.forward")
            || keyBinding.getKeyDescription().equals("key.left")
            || keyBinding.getKeyDescription().equals("key.back")
            || keyBinding.getKeyDescription().equals("key.back")
            || keyBinding.getKeyDescription().equals("key.back")) {
                this.savedKeys.put(keyBinding, new Pair(keyBinding.getPressTime(), keyBinding.pressed));
            }
        }
    }

    public void updateState2() {
        if (this.isToggled() && this.keyCode2 == this.settingLoadKeyDelay.getIndexOffset2() && minecraft.currentScreen == null) {
            this.keyCode2 = 10000;
            this.settingLoadKeyDelay.recalculate();

            for (KeyBinding keyBinding : (Iterable<KeyBinding>) KeyBinding.keybindArray) {
                Pair pair = (Pair)this.savedKeys.get(keyBinding);
                if (pair != null && KeyUtils.checkCondition(keyBinding.getKeyCode())) {
                    keyBinding.pressed = (Boolean)pair.getFirst();
                    int second = (Integer)pair.getSecond();
                    keyBinding.setPressTime(second);
                }
            }
        }
    }
}
