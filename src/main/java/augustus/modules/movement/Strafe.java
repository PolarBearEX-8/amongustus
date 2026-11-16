package augustus.modules.movement;

import client.event.EventMoveEntity;
import client.event.MoveEntityListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.MoveUtil;
import java.awt.Color;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Strafe extends Module implements MoveEntityListener, UpdateStateListener {
    public ModeSetting settingMode;
    public ModeSetting settingEvent;
    public NumberSetting settingPercentage;
    public BooleanSetting settingStrafeOnGround;
    public ModeSetting settingTimingOnGround;
    public ActionSetting settingHurtTimeStrafeOnGround;
    public BooleanSetting settingStrafeOnAir;
    public ModeSetting settingTiminInAir;
    public ActionSetting settingHurtTimeStrafeInAir;
    public BooleanSetting settingBackwardsStrafe;

    public Strafe() {
        super("Strafe", "Bestrafung", new Color(50, 168, 82), Category.MOVEMENT);
        String[] textArray = new String[3];
        textArray[0] = "Normal";
        textArray[1] = "Matrix";
        textArray[2] = "Intave";
        this.settingMode = new ModeSetting(this, "Mode", "Normal", textArray);
        String[] textArray2 = new String[4];
        textArray2[0] = "Pre";
        textArray2[1] = "Mid";
        textArray2[2] = "OtherMid";
        textArray2[3] = "Post";
        this.settingEvent = new ModeSetting(this, "Event", "Pre", textArray2);
        this.settingPercentage = new NumberSetting(this, "Percentage", 100.0, 0.0, 100.0, 0).getNumberSetting(() -> this.isPercentageVisible());
        this.settingStrafeOnGround = new BooleanSetting(this, "StrafeOnGround", false);
        String[] textArray3 = new String[3];
        textArray3[0] = "Always";
        textArray3[1] = "NotWhileKB";
        textArray3[2] = "OnlyWhileKB";
        this.settingTimingOnGround = new ModeSetting(this, "TimingOnGround", "Always", textArray3).getModeSetting(() -> this.settingStrafeOnGround.isEnabled());
        this.settingHurtTimeStrafeOnGround = new ActionSetting(this, "HurtTimeStrafeOnGround", 0.0, 10.0, 0.0, 10.0, 0).getActionSetting(() -> this.isHurtTimeStrafeOnGroundVisible());
        this.settingStrafeOnAir = new BooleanSetting(this, "StrafeOnAir", true);
        String[] textArray4 = new String[3];
        textArray4[0] = "Always";
        textArray4[1] = "NotWhileKB";
        textArray4[2] = "OnlyWhileKB";
        this.settingTiminInAir = new ModeSetting(this, "TiminInAir", "Always", textArray4).getModeSetting(() -> this.settingStrafeOnAir.isEnabled());
        this.settingHurtTimeStrafeInAir = new ActionSetting(this, "HurtTimeStrafeInAir", 0.0, 10.0, 0.0, 10.0, 0).getActionSetting(() -> this.isHurtTimeStrafeInAirVisible());
        this.settingBackwardsStrafe = new BooleanSetting(this, "BackwardsStrafe", true);
    }

    @Override
    public void onEnable() {
        this.registerEvent(20);
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (this.checkCondition()) {
            this.onMoveEntity2(event, false);
        }
    }

    @Override
    public void onPreUpdateState() {
        this.updateState();
    }

    @Override
    public void onPostUpdateState() {
        this.updateState();
    }

    private void updateState() {
        if (this.checkCondition() && !BlockCache.canExecute()) {
            double offset = 0.0;
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "Matrix":
                MoveUtil.processAction((float)(this.settingPercentage.getValue() / 100.0));
                return;
            case "Normal":
                MoveUtil.processAction();
                return;
            case "Intave":
                if (minecraft.thePlayer.onGround) {
                            return;
                        }
                offset = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer)[9];
                break;
            default:
                return;
        }


            if (offset == 1.0) {
                MoveUtil.processAction((float)(this.settingPercentage.getValue() / 100.0));
            }
        }
    }

    private static double getDouble() {
        float float2 = MoveUtil.getFloat2();
        if (!(minecraft.thePlayer.scaleFactor3 >= 0.0F)) {
            return Math.toRadians((double)(float2 + 180.0F));
        } else {
            float value = 1.0F;
            if (minecraft.thePlayer.scaleFactor3 > 0.0F) {
                value = 0.5F;
            }

            if (minecraft.thePlayer.scaleFactor24 > 0.0F) {
                float2 -= 90.0F * value;
            }

            if (minecraft.thePlayer.scaleFactor24 < 0.0F) {
                float2 += 90.0F * value;
            }

            return Math.toRadians((double)float2);
        }
    }

    private void onMoveEntity2(EventMoveEntity event, boolean enabled) {
        double offset = 0.0;
        boolean completed4 = false;
        double offset2 = 0.0;
        double offset3 = 0.0;
        boolean enabled2 = false;
        boolean completed3 = false;
        double offset4 = 0.0;
        double offset5 = 0.0;
        boolean completed2 = false;
        byte state = (byte)0;
        offset2 = event.getCoordOffset2();
        offset3 = event.getCoordOffset();
        offset = Math.sqrt(offset2 * offset2 + offset3 * offset3);
        String selected = this.settingMode.getSelected();
switch (selected) {
            case "Normal":
                {
                            boolean enabled3 = MoveUtil.canExecute();
                            if (!enabled3) {
                                return;
                            }
                            float value = (float)(this.settingPercentage.getValue() / 100.0);
                            double offset6 = offset * (double)value;
                            double offset7 = (double)(1.0F - value);
                            double offset8 = offset2 * offset7;
                            double offset9 = offset3 * offset7;
                            double double22 = MoveUtil.getDouble2();
                            offset4 = offset8 - Math.sin(double22) * offset6;
                            offset5 = offset9 + Math.cos(double22) * offset6;
                            event.setCoordOffset2(offset4);
                            event.setCoordOffset(offset5);
                            { completed2 = true; break; }
                        }
            case "Intave":
                {
                            enabled2 = MoveUtil.canExecute();
                            { completed3 = true; break; }
                        }
            case "Matrix":
                {
                            { completed4 = true; break; }
                        }
            default:
                return;
        }
        if (!completed4) {
            if (!completed3) {
                if (enabled) {
                    minecraft.thePlayer.motionX = offset4;
                    minecraft.thePlayer.motionZ = offset5;
                }
                return;
            }
            if (!enabled2) {
                return;
            }
            if (minecraft.thePlayer.onGround) {
                return;
            }
            if (!minecraft.gameSettings.keyBindLeft.pressed) {
                if (!minecraft.gameSettings.keyBindRight.pressed) {
                    return;
                }
            }
            if (minecraft.thePlayer.hurtTime != 0) {
                return;
            }
            if (minecraft.thePlayer.isCollidedVertically) {
                return;
            }
            if (minecraft.thePlayer.canExecute2()) {
                return;
            }
            if (CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer)[9] == 1.0) {
                float value2 = (float)(this.settingPercentage.getValue() / 100.0);
                double offset10 = offset * (double)value2;
                double offset11 = (double)(1.0F - value2);
                double offset12 = offset2 * offset11;
                double offset13 = offset3 * offset11;
                double double24 = MoveUtil.getDouble2();
                double offset14 = offset12 - Math.sin(double24) * offset10;
                double offset15 = offset13 + Math.cos(double24) * offset10;
                event.setCoordOffset2(offset14);
                event.setCoordOffset(offset15);
                if (enabled) {
                    minecraft.thePlayer.motionX = offset14;
                    minecraft.thePlayer.motionZ = offset15;
                }
            }
            return;
        }


        if (MoveUtil.canExecute()) {
            double doubleEntry = getDouble();
            double offset16 = -Math.sin(doubleEntry) * offset;
            double offset17 = Math.cos(doubleEntry) * offset;
            event.setCoordOffset2(offset16);
            event.setCoordOffset(offset17);
            if (enabled) {
                minecraft.thePlayer.motionX = offset16;
                minecraft.thePlayer.motionZ = offset17;
            }
        }
    }

    private boolean isHurtTimeStrafeOnGroundVisible() {
        return this.settingStrafeOnGround.isEnabled();
    }

    private boolean isHurtTimeStrafeInAirVisible() {
        return this.settingStrafeOnAir.isEnabled();
    }

    private boolean isPercentageVisible() {
        return this.settingMode.getSelected().equals("Matrix");
    }

    private boolean checkCondition() {
        if (MoveUtil.getIntArray()[0] < 0 && !this.settingBackwardsStrafe.isEnabled()) {
            return false;
        } else if (minecraft.thePlayer.onGround) {
            if (!this.settingStrafeOnGround.isEnabled()) {
                return false;
            } else if (minecraft.thePlayer.hurtTime != 0 && this.settingTimingOnGround.getSelected().equals("NotWhileKB")) {
                return false;
            } else if (this.settingTimingOnGround.getSelected().equals("OnlyWhileKB")) {
                if (minecraft.thePlayer.hurtTime == 0) {
                    return false;
                } else {
                    double hurtTime = (double)minecraft.thePlayer.hurtTime;
                    if (hurtTime < this.settingHurtTimeStrafeOnGround.getDouble3()) {
                        return false;
                    } else {
                        double hurtTime2 = (double)minecraft.thePlayer.hurtTime;
                        return hurtTime2 <= this.settingHurtTimeStrafeOnGround.getDouble6();
                    }
                }
            } else {
                if (minecraft.thePlayer.hurtTime != 0) {
                    double hurtTime3 = (double)minecraft.thePlayer.hurtTime;
                    if (hurtTime3 < this.settingHurtTimeStrafeOnGround.getDouble3()) {
                        return false;
                    }

                    double hurtTime4 = (double)minecraft.thePlayer.hurtTime;
                    if (hurtTime4 > this.settingHurtTimeStrafeOnGround.getDouble6()) {
                        return false;
                    }
                }

                return true;
            }
        } else if (!this.settingStrafeOnAir.isEnabled()) {
            return false;
        } else if (minecraft.thePlayer.hurtTime != 0 && this.settingTiminInAir.getSelected().equals("NotWhileKB")) {
            return false;
        } else if (this.settingTiminInAir.getSelected().equals("OnlyWhileKB")) {
            if (minecraft.thePlayer.hurtTime == 0) {
                return false;
            } else {
                double hurtTime5 = (double)minecraft.thePlayer.hurtTime;
                if (hurtTime5 < this.settingHurtTimeStrafeInAir.getDouble3()) {
                    return false;
                } else {
                    double hurtTime6 = (double)minecraft.thePlayer.hurtTime;
                    return hurtTime6 <= this.settingHurtTimeStrafeInAir.getDouble6();
                }
            }
        } else {
            if (minecraft.thePlayer.hurtTime != 0) {
                double hurtTime7 = (double)minecraft.thePlayer.hurtTime;
                if (hurtTime7 < this.settingHurtTimeStrafeInAir.getDouble3()) {
                    return false;
                }

                double hurtTime8 = (double)minecraft.thePlayer.hurtTime;
                if (hurtTime8 > this.settingHurtTimeStrafeInAir.getDouble6()) {
                    return false;
                }
            }

            return true;
        }
    }
}
