package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.EventMove;
import client.event.EventStrafe;
import client.event.MoveListener;
import client.event.StrafeListener;
import client.event.UpdateStateListener;
import client.setting.BooleanSetting;
import client.setting.ModeOptionSupplier;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.CommonUpdateStateListenerAdapter;
import client.util.MinecraftAccessor;

public class FlyIntaveoldMode
extends CommonUpdateStateListenerAdapter
implements UpdateStateListener,
MoveListener,
StrafeListener {
    public final BooleanSetting settingHideSneakAnimation;

    @Override
    public void onStrafe(EventStrafe eventStrafe) {
        CommonUpdateStateListenerAdapter.getIndexArray();
        if (MinecraftAccessor.minecraft.thePlayer.isInWeb() && this.settingHideSneakAnimation.isEnabled()) {
            float f = 1.62f;
            eventStrafe.processAction3(f);
        }
    }

    @Override
    public void clear() {
    }

    @Override
    public void onMove(EventMove eventMove) {
        CommonUpdateStateListenerAdapter.getIndexArray();
        if (MinecraftAccessor.minecraft.thePlayer.isCollidedVertically) {
            if (MinecraftAccessor.minecraft.thePlayer.isInWeb() && eventMove.getScaleFactor2() == 0.0f && MinecraftAccessor.minecraft.gameSettings.keyBindForward.isKeyDown()) {
                float f = MinecraftAccessor.minecraft.thePlayer.ticksExisted % 2 == 0 ? 0.0f : 1.0f;
                eventMove.setScaleFactor7(f);
            }
        } else if (MinecraftAccessor.minecraft.thePlayer.isInWeb()) {
            if (MinecraftAccessor.minecraft.thePlayer.isRiding()) {
                float f = 0.0f;
                eventMove.setScaleFactor7(f);
            }
            eventMove.setState4(true);
        }
    }

    @Override
    public void updateState2() {
        super.updateState2();
        int n = 21;
        ((ModeOptionSupplier)this).applyIndex(n);
        int n2 = 24;
        ((ModeOptionSupplier)this).applyIndex(n2);
    }

    @Override
    public void onPostUpdateState() {
        CommonUpdateStateListenerAdapter.getIndexArray();
        if (!MinecraftAccessor.minecraft.thePlayer.isInWeb()) {
            return;
        }
        MinecraftAccessor.minecraft.thePlayer.onGround = false;
        if (MinecraftAccessor.minecraft.thePlayer.isCollidedVertically) {
            if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe == 0.0f && MinecraftAccessor.minecraft.gameSettings.keyBindForward.isKeyDown()) {
                MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = 0.74f;
            } else {
                MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = 0.2f;
                MinecraftAccessor.minecraft.thePlayer.onGround = true;
            }
        } else {
            MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = 1.0f;
        }
    }

    public FlyIntaveoldMode(NoWeb noWeb, SettingGroup settingGroup) {
        super(noWeb, settingGroup, "IntaveOld");
        String string = "HideSneakAnimation";
        BooleanSupplier booleanSupplier = this::booleanValue;
        this.settingHideSneakAnimation = (BooleanSetting)((Setting)((BooleanSetting)((Setting)new BooleanSetting(this.module, "IntaveOldHideSneakAnimation", true, this.settingGroup)).getSetting(string))).getSetting(booleanSupplier);
    }
}

