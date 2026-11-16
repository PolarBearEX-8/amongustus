package augustus.modules.misc;

import client.event.EventClickGuiListener;
import client.event.EventPacket;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class Fixes extends Module implements EventClickGuiListener, PacketListener {
    public BooleanSetting settingIntave = new BooleanSetting(this, "Intave", false);
    public BooleanSetting settingIntaveBlocking = new BooleanSetting(this, "IntaveBlocking", false);
    public BooleanSetting settingMouseDelayFix = new BooleanSetting(this, "MouseDelayFix", true);

    public ModeSetting settingHitDelayFixMode = new ModeSetting(
        this, "HitDelayFixMode", "Default", new String[]{"None", "Default", "WithoutDoubleClick", "OnlyAtTarget", "OnlyAtTargetLite"}
    );
    public BooleanSetting settingMemoryFix = new BooleanSetting(this, "MemoryFix", false);
    public BooleanSetting settingPlaceWhileBreak = new BooleanSetting(this, "PlaceWhileBreak", true);
    public BooleanSetting settingNoTerrainDownload = new BooleanSetting(this, "NoTerrainDownload", true);
    public BooleanSetting settingNoReducedDebugInformation = new BooleanSetting(this, "NoReducedDebugInformation", true);
    public TimeHelper timer = new TimeHelper();
    public TimeHelper timer2 = new TimeHelper();

    public Fixes() {
        super("Fixes", "Reparieren", new Color(169, 66, 237), Category.MISC);
    }

    @Override
    public void onEnable() {
        this.registerEvent(8);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void processAction() {
        if (this.settingMemoryFix.isEnabled()) {
            long time = Runtime.getRuntime().maxMemory();
            long time2 = Runtime.getRuntime().totalMemory();
            long time3 = Runtime.getRuntime().freeMemory();
            if ((time2 - time3) * 100L / time > 50L) {
                System.gc();
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
            this.timer2.reset();
        }
    }

    @Override
    public void onUpdate() {
        this.setActive(true);
    }

    public static void updateState() {
    }

    public boolean canExecute5() {
        return !this.isToggled() ? false : this.settingPlaceWhileBreak.isEnabled();
    }

    public boolean canExecute6() {
        return !this.isToggled() ? false : this.settingNoTerrainDownload.isEnabled();
    }

    public boolean canExecute7() {
        return !this.isToggled() ? false : this.settingIntave.isEnabled();
    }

    public boolean canExecute8() {
        if (!this.isToggled()) {
            return false;
        } else {
            return !this.settingIntaveBlocking.isEnabled() ? false : !this.timer2.hasReached(500L);
        }
    }

    public boolean canExecute9() {
        return !this.isToggled() ? false : this.settingNoReducedDebugInformation.isEnabled();
    }

    public boolean canExecute10() {
        return !this.isToggled() ? false : this.settingMouseDelayFix.isEnabled();
    }
}
