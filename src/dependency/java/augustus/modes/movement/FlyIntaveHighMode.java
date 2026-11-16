package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.UpdateStateListener;
import client.setting.BooleanSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CommonUpdateStateListenerAdapter;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class FlyIntaveHighMode
extends CommonUpdateStateListenerAdapter
implements UpdateStateListener {
    public final BooleanSetting settingStopOnHit;

    @Override
    public void updateState() {
        super.updateState();
    }

    @Override
    public void clear() {
    }

    @Override
    public void updateState2() {
        super.updateState2();
    }

    @Override
    public void onPostUpdateState() {
        CommonUpdateStateListenerAdapter.getIndexArray();
        if (!MinecraftAccessor.minecraft.thePlayer.isInWeb()) {
            this.timer.reset();
        }
        if (!MinecraftAccessor.minecraft.thePlayer.isInWeb() && this.enabled2) {
            MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02f);
            return;
        }
        if (MoveUtil.canExecute() && MinecraftAccessor.minecraft.thePlayer.ticksExisted % 4 != 0 && this.timer.hasReached(250L)) {
            if (MinecraftAccessor.minecraft.thePlayer.hurtTime > 0) {
                if (!this.settingStopOnHit.isEnabled()) {
                    MoveUtil.processFloat2(0.2f, false);
                    MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02f);
                }
            } else {
                MoveUtil.processFloat2(0.3f, false);
                MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.08f);
            }
        }
    }

    public FlyIntaveHighMode(NoWeb noWeb, SettingGroup settingGroup) {
        super(noWeb, settingGroup, "Intave 14.7.7");
        String string = "StopOnHit";
        BooleanSupplier booleanSupplier = this::booleanValue;
        this.settingStopOnHit = (BooleanSetting)((Setting)((BooleanSetting)((Setting)new BooleanSetting(this.module, "Intave1477StopOnHit", false, this.settingGroup)).getSetting(string))).getSetting(booleanSupplier);
    }
}

