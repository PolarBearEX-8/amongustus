package augustus.modules.fun;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.MotionUpdateListener;
import client.event.MoveListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.MultiSelectSetting;
import client.setting.RangeSetting;
import client.setting.SpinBotClickType;
import client.util.BooleanSupplier;
import client.util.MouseUtils;
import client.util.MovingObjectType;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCache;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.util.MovingObjectPosition;

public class SpinBot extends Module implements RotationListener, ClickListener, TickListener, MoveListener, MotionUpdateListener {
    public RangeSetting settingYawSpeed;
    public RangeSetting settingPitchValue;
    public BooleanSetting settingServerside;
    public BooleanSetting settingMoveFix;
    public MultiSelectSetting settingStopSpinningOn;
    public TimeHelper timer;
    public TimeHelper timer2;
    public Rotation rotation;
    public Rotation rotation2;
    public boolean expanded2;

    public SpinBot() {
        super("SpinBot", "Drehen", new Color(50, 168, 82), Category.FUN);
        this.settingYawSpeed = new RangeSetting(this, "YawSpeed", 90.0, 90.0, 0.0, 180.0, 1).getRangeSetting2();
        this.settingPitchValue = new RangeSetting(this, "PitchValue", 90.0, 90.0, -90.0, 90.0, 1).getRangeSetting2();
        this.settingServerside = new BooleanSetting(this, "Serverside", true);
        BooleanSetting booleanSetting = new BooleanSetting(this, "MoveFix", false);
        this.settingMoveFix = (BooleanSetting)booleanSetting.getSetting(() -> this.settingServerside.isEnabled());
        this.settingStopSpinningOn = new MultiSelectSetting(this, "StopSpinningOn", SpinBotClickType.values()).getMultiSelectSetting(() -> this.settingServerside.isEnabled());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.rotation = new Rotation();
        this.rotation2 = new Rotation();
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(21);
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        if (minecraft.thePlayer != null) {
            this.rotation = new Rotation(minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch);
            this.rotation2 = new Rotation(minecraft.thePlayer.prevRotationYaw, minecraft.thePlayer.prevRotationPitch);
        }
    }

    @Override
    public void onRotation(EventRotation event) {
        if (this.expanded2) {
            if (!this.canExecute6()) {
                event.setScaleFactor(Client.getClient().getViaProtocolHelper().scaleFactor1);
            }
        }
    }

    @Override
    public void onClick(EventClick event) {
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (this.expanded2) {
            if (!this.canExecute6()) {
                event.setScaleFactor(Client.getClient().getViaProtocolHelper().scaleFactor1);
            }
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (!this.settingMoveFix.isEnabled()) {
            if (this.expanded2) {
                this.onMotionUpdate2(event);
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.expanded2) {
            if (this.canExecute6()) {
                event.setAuraRenderMode(AuraRenderMode.DEFAULT);
            }
        }
    }

    @Override
    public void onPreTick() {
        this.updateState2();
        if (this.expanded2) {
            this.updateState();
            this.processAction();
        }
    }

    private void onMotionUpdate2(EventMotionUpdate event) {
        if (this.settingServerside.isEnabled()) {
            event.setScaleFactor(this.rotation.yaw);
            event.setScaleFactor2(this.rotation.pitch);
        }

        minecraft.thePlayer.scaleFactor12 = this.rotation.yaw;
        minecraft.thePlayer.scaleFactor21 = this.rotation.pitch;
        minecraft.thePlayer.scaleFactor8 = this.rotation.yaw;
    }

    private void processAction() {
        if (this.timer2.hasReached(1000L)) {
            this.rotation = SimulatedPlayer.getRotation5(this.rotation, this.rotation2);
            if (this.canExecute6()) {
                ClientGlobals.rotationManager.applyRotation2(this.rotation, 8, 1898960900, this);
            }
        }
    }

    private boolean canExecute5() {
        if (!this.settingServerside.isEnabled()) {
            return true;
        } else {
            MovingObjectPosition movingObjectPosition = minecraft.thePlayer
                .getMovingObjectPosition(
                    4.5, 1.0F, Client.getClient().getViaProtocolHelper().scaleFactor1, Client.getClient().getViaProtocolHelper().scaleFactor2
                );
            if (!SpinBotClickType.CLICK.isSelected()
                || !minecraft.gameSettings.keyBindUseItem.isKeyDown()
                    && !minecraft.gameSettings.keyBindAttack.isKeyDown()
                    && !MouseUtils.checkCondition(0)
                    && !MouseUtils.checkCondition(1)) {
                if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK) {
                    if (SpinBotClickType.CHEST.isSelected() && minecraft.theWorld.getBlockState(movingObjectPosition.getBlockPos()).getBlock() instanceof BlockTrappedChest) {
                        return false;
                    }

                    if (SpinBotClickType.CRAFTER.isSelected()
                        && minecraft.theWorld.getBlockState(movingObjectPosition.getBlockPos()).getBlock().equals(Block.processAction3(58))) {
                        return false;
                    }

                    if (SpinBotClickType.FURNACE.isSelected()
                        && minecraft.theWorld.getBlockState(movingObjectPosition.getBlockPos()).getBlock().equals(Block.processAction3(61))) {
                        return false;
                    }
                }

                if (ClientGlobals.moduleManager.killAura.isToggled()
                    && ClientGlobals.moduleManager.killAura.target != null
                    && !ClientGlobals.moduleManager.killAura.settingNoRotation.isEnabled()) {
                    return false;
                } else {
                    return BlockCache.canExecute() ? false : !ClientGlobals.moduleManager.teleport.isToggled();
                }
            } else {
                return false;
            }
        }
    }

    private boolean canExecute6() {
        return !this.settingMoveFix.isEnabled() ? false : this.settingServerside.isEnabled();
    }

    private void updateState() {
        if (this.timer2.hasReached(1000L)) {
            this.rotation.yaw = this.rotation.yaw + (float)this.settingYawSpeed.recalculate();
            this.rotation.pitch = (float)this.settingPitchValue.recalculate();
        }
    }

    private void updateState2() {
        this.expanded2 = false;
        if (this.canExecute5()) {
            this.expanded2 = true;
        } else {
            this.timer2.reset();
            if (this.expanded2 && this.settingServerside.isEnabled()) {
                this.rotation = new Rotation(ClientGlobals.rotationManager.getRotation3());
            }

            this.expanded2 = false;
        }
    }
}
