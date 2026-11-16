package augustus.modules.combat;

import augustus.custom.RuntimeBridge;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventMouseClick;
import client.event.MouseClickListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import java.awt.Color;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class SilentMurder extends Module implements AttackEntityListener, MouseClickListener, TickListener {
    public NumberSetting settingTickDelay = new NumberSetting(this, "TickDelay", 10.0, 0.0, 20.0, 0);
    public BooleanSetting settingTimer = new BooleanSetting(this, "Timer", false);
    public BooleanSetting settingBypassBalanceChecks;
    public int keyCode2;
    public int tickDelay;
    public boolean expanded2;
    public boolean enabled2;

    public SilentMurder() {
        super("SilentMurder", "LautloserMord", new Color(209, 140, 171), Category.COMBAT);
        BooleanSetting booleanSetting = new BooleanSetting(this, "Bypass balance checks", true);
        this.settingBypassBalanceChecks = (BooleanSetting)booleanSetting.getSetting(this.settingTimer::isEnabled);
        this.keyCode2 = 0;
        this.tickDelay = -1;
        this.expanded2 = false;
        this.enabled2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(1);
        this.registerEvent(16);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMouseClick(EventMouseClick event) {
        if (this.expanded2) {
            if (event.getIndex() > 0) {
                event.processAction(event.getIndex() - 1);
                event.processAction(0.0F);
                this.expanded2 = false;
            }
        }
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        this.tickDelay = -1;
        int slotByKey = RuntimeBridge.getSlotByKey();
        if (slotByKey != -1) {
            int indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem;
            if (slotByKey != indexOffset) {
                int keyCode22 = this.keyCode2;
                int ticksExisted = minecraft.thePlayer.ticksExisted;
                double offset;
                if (keyCode22 > ticksExisted
                    || (
                            (offset = (double)Math.abs(minecraft.thePlayer.ticksExisted - this.keyCode2) - this.settingTickDelay.getValue())
                                    == 0.0
                                ? 0
                                : (offset < 0.0 ? -1 : 1)
                        )
                        > 0) {
                    this.keyCode2 = minecraft.thePlayer.ticksExisted;
                    this.tickDelay = minecraft.thePlayer.inventoryPlayer.currentItem;
                    minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(slotByKey));
                    if (this.settingTimer.isEnabled()) {
                        if (minecraft.getTimer().indexOffset == 1) {
                            minecraft.getTimer().indexOffset = 2;
                            this.enabled2 = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPostAttackEntity() {
        if (!this.settingTimer.isEnabled()) {
            if (RuntimeBridge.getSlotByKey() != -1 && this.tickDelay != -1) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(this.tickDelay));
            }

            this.tickDelay = -1;
        }
    }

    @Override
    public void onPreTick() {
        if (minecraft.thePlayer != null) {
            if (this.tickDelay == -1) {
                return;
            }

            if (!this.settingTimer.isEnabled()) {
                return;
            }

            minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(this.tickDelay));
            this.tickDelay = -1;
            if (!this.enabled2) {
                return;
            }

            if (this.settingBypassBalanceChecks.isEnabled()) {
                this.expanded2 = true;
            }

            this.enabled2 = false;
        }
    }
}
