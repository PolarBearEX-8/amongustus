package augustus.modules.fun;

import augustus.modules.misc.ClientSettings;
import client.ClientGlobals;
import client.event.EventBusManager;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.util.AudioClipPlayer;
import client.util.ClientManager;
import client.util.KeybindAnimation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class ShuffleMode extends Module implements TickListener {
    public AudioClipPlayer audioPlayer;
    public boolean expanded2;

    public ShuffleMode() {
        super("ShuffleMode", "MischMode", new Color(102, 242, 207), Category.FUN);
        AudioClipPlayer audioClipEntry = new AudioClipPlayer();
        this.audioPlayer = audioClipEntry;
        this.expanded2 = false;
        this.setState4(false);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        EventBusManager.getDietrichEvents2().subscribe(40, this);
        this.expanded2 = true;
    }

    @Override
    public void setActive3(boolean enabled) {
        if (!this.expanded2) {
            super.setActive3(enabled);
        } else {
            String selected = ClientGlobals.moduleManager.itemList.settingToggleAnimation.getSelected();
            if ((Boolean)this.enabledProperty.getValue()) {
                this.enabledProperty.applyObject2(false);
                if (minecraft.theWorld != null
                && ClientGlobals.moduleManager.clientSettings.settingModuleToggleSound.isEnabled()
                && this.canExecute()) {
                    ClientManager.applyRunnable(() -> ClientSettings.updateState());
                }

                this.onDisable();
                if (!selected.equals("None")) {
                    this.keybindAnimation
                    .updateEasingFunction(
                    (int)ClientGlobals.moduleManager.itemList.settingAnimationTime.getValue(),
                    KeybindAnimation.getEasingFunction(selected),
                    0,
                    false
                    );
                }
            } else {
                this.enabledProperty.applyObject2(true);
                if (minecraft.theWorld != null
                && ClientGlobals.moduleManager.clientSettings.settingModuleToggleSound.isEnabled()
                && this.canExecute()) {
                    ClientManager.applyRunnable(() -> ClientSettings.updateState2());
                }

                this.onPreDisable();
                if (!selected.equals("None")) {
                    this.keybindAnimation
                    .updateEasingFunction(
                    (int)ClientGlobals.moduleManager.itemList.settingAnimationTime.getValue(),
                    KeybindAnimation.getEasingFunction(selected),
                    1,
                    false
                    );
                }
            }
        }
    }

    @Override
    public void onRunTick() {
        boolean completed = false;
        if (!this.audioPlayer.checkCondition()) {
            AudioClipPlayer audioClipEntry = null;
            try {
                audioClipEntry = this.audioPlayer;
            } catch (Exception e) {
                completed = true;
            }
            if (!completed) {
                AudioClipPlayer audioClipEntry2 = null;
                try {
                    audioClipEntry.applyString2("sounds/shuffle.mp3", true);
                    audioClipEntry2 = this.audioPlayer;
                } catch (Exception e2) {
                    completed = true;
                }
                if (!completed) {
                    try {
                        audioClipEntry2.processAction(10000);
                    } catch (Exception e3) {
                    }
                }
            }
        }


        ArrayList arrayList = new ArrayList();

        for (Object object : ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
            Module module = (Module)object;
            arrayList.add(module.getDescription());
        }

        Collections.shuffle(arrayList);
        int index = 0;

        for (Object object2 : ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
            ((Module)object2).applyString3((String)arrayList.get(index));
            index++;
        }

        ClientGlobals.moduleManager.updateState();
    }
}
