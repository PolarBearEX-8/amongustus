package augustus.modules.player;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.MotionListener;
import client.event.PacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.SlotManager;
import java.awt.Color;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class AutoEnderPearl extends Module implements ClickListener, TickListener, PacketListener, MotionListener {
    public BooleanSetting settingAutoAirstuck;
    public BooleanSetting settingUnstuckIfCombatPearlDidnTLand;
    public BooleanSetting settingOnlyIfPlayerIsFallingIntoTheVoid;
    public BooleanSetting settingAutoDisable;
    public int keyCode2;
    public boolean expanded2;
    public boolean activeState;
    public boolean enabled2;
    public long timestampMillis;
    public long timestampMillis1;

    public AutoEnderPearl() {
        super("AutoEnderPearl", "AutomatischeEnderperle", new Color(155, 66, 245), Category.PLAYER);
        this.settingAutoAirstuck = new BooleanSetting(this, "Auto airstuck", true);
        this.settingUnstuckIfCombatPearlDidnTLand = (BooleanSetting)new BooleanSetting(this, "Unstuck if combatPearl didn't land", true).getSetting(() -> this.settingAutoAirstuck.isEnabled());
        this.settingOnlyIfPlayerIsFallingIntoTheVoid = new BooleanSetting(this, "Only if player is falling into the void", false);
        this.settingAutoDisable = (BooleanSetting)new BooleanSetting(this, "Auto disable", true).getSetting(() -> this.settingOnlyIfPlayerIsFallingIntoTheVoid.isEnabled());
        this.keyCode2 = -1;
        this.expanded2 = false;
        this.activeState = false;
        this.enabled2 = false;
        this.timestampMillis = 0L;
        this.timestampMillis1 = 0L;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(26);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
        if (this.settingAutoAirstuck.isEnabled()) {
            ClientGlobals.moduleManager.airStuck.setActive(false);
        }
    }

    @Override
    public void onPreDisable() {
        this.expanded2 = false;
        this.activeState = false;
        this.enabled2 = false;
    }

    @Override
    public void onClick(EventClick event) {
        if (ClientGlobals.rotationManager.checkModule(this)) {
            AutoEnderPearl autoEnderPearl;
            if (!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemEnderPearl)) {
                autoEnderPearl = this;
            } else {
                this.setActive(false);
                this.keyCode2 = -1;
                autoEnderPearl = this;
            }

            autoEnderPearl.enabled2 = true;
        }

        this.expanded2 = true;
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        boolean enabled;
        if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
            enabled = this.settingAutoDisable.isEnabled();
        } else {
            this.setActive(false);
            enabled = this.settingAutoAirstuck.isEnabled();
        }

        if (enabled) {
            ClientGlobals.moduleManager.airStuck.setActive(false);
        }
    }

    @Override
    public void onPostMotion() {
        int keyCode22 = this.keyCode2;
        SlotManager.applyModule3(this, -2, keyCode22);
    }

    @Override
    public void onPreTick() {
        if (this.settingAutoAirstuck.isEnabled()) {
            ClientGlobals.moduleManager.airStuck.setActive(false);
        }
    }

    public int getInt2() {
        if (36 < minecraft.thePlayer.container.itemList2.size()) {
            minecraft.thePlayer.container.getSlot(36).getStack();
        }

        return -1;
    }

    public boolean checkCondition() {
        if (9 < minecraft.thePlayer.container.itemList2.size()) {
            minecraft.thePlayer.container.getSlot(9).getStack();
        }

        return false;
    }
}
