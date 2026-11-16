package augustus.modules.misc;

import augustus.modules.combat.KillAura;
import client.ClientGlobals;
import client.event.EventSlowDown;
import client.event.EventWorldLoad;
import client.event.RenderListener;
import client.event.SlowDownListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatUtil;
import client.util.KeyUtils;
import client.util.MouseUtils;
import client.util.MoveUtil;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.block.BlockCache;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimeManipulation extends Module implements RenderListener, WorldLoadListener, SlowDownListener {
    public TimeHelper manipulationTimer;
    public TimeHelper timer2;
    public NumberSetting settingMinTicksAvailable;
    public NumberSetting settingMaxChargingTicks;
    public BooleanSetting settingCorrect;
    public BooleanSetting settingAutoCharge;
    public BooleanSetting settingSneakCharge;
    public BooleanSetting settingUpdateEntityPositions;
    public BooleanSetting settingResetAtWorldChange;
    public BooleanSetting settingTimerRangeBool;
    public SettingGroup settingTimerRange;
    public NumberSetting settingDelay;
    public NumberSetting settingTimerTicksForward;
    public NumberSetting settingTolerance;
    public BooleanSetting settingOnlyOnGround;
    public BooleanSetting settingDynamic;
    public BooleanSetting settingOnlyKillAura;
    public BooleanSetting settingClickCheck;
    public BooleanSetting settingForbiddenSneak;
    public static String[] stringArray;

    public TimeManipulation() {
        super("TimeManipulation", "ZeitManipulation", new Color(50, 168, 82), Category.MISC);
        this.manipulationTimer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.settingMinTicksAvailable = new NumberSetting(this, "MinTicksAvailable", 0.0, -10.0, 10.0, 0);
        this.settingMaxChargingTicks = new NumberSetting(this, "MaxChargingTicks", 8.0, 1.0, 100.0, 0);
        this.settingCorrect = new BooleanSetting(this, "Correct", true);
        this.settingAutoCharge = new BooleanSetting(this, "AutoCharge", false);
        this.settingSneakCharge = new BooleanSetting(this, "SneakCharge", true);
        this.settingUpdateEntityPositions = new BooleanSetting(this, "UpdateEntityPositions", false);
        this.settingResetAtWorldChange = new BooleanSetting(this, "ResetAtWorldChange", true);
        this.settingTimerRangeBool = (BooleanSetting)new BooleanSetting(this, "TimerRangeBool", false).getSetting("TimerRange");
        this.settingTimerRange = new SettingGroup(this, "TimerRange").getSettingGroup(() -> this.settingTimerRangeBool.isEnabled());
        this.settingDelay = new NumberSetting(this, "Delay", 0.0, 0.0, 20000.0, 0, this.settingTimerRange);
        this.settingTimerTicksForward = new NumberSetting(this, "TimerTicksForward", 3.0, 1.0, 10.0, 0, this.settingTimerRange);
        this.settingTolerance = new NumberSetting(this, "Tolerance", 0.0, -1.0, 1.0, 2, this.settingTimerRange);
        this.settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false, this.settingTimerRange);
        this.settingDynamic = new BooleanSetting(this, "Dynamic", true, this.settingTimerRange);
        this.settingOnlyKillAura = new BooleanSetting(this, "OnlyKillAura", true, this.settingTimerRange);
        this.settingClickCheck = new BooleanSetting(this, "ClickCheck", false, this.settingTimerRange);
        this.settingForbiddenSneak = new BooleanSetting(this, "ForbiddenSneak", true, this.settingTimerRange);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(17);
        this.registerEvent(41);
    }

    @Override
    public void onDisable() {
        minecraft.epochMillis2 = 0L;
        minecraft.epochMillis = 0L;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        int index = (int)(minecraft.epochMillis - minecraft.epochMillis2);
        minecraft.fontRendererObj.getIndex("" + index, scaledResolution.getScaledWidth() - minecraft.fontRendererObj.getIndex("" + index), scaledResolution.getScaledHeight() - 30, -1);
        RenderUtils.processAction();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        if (this.settingResetAtWorldChange.isEnabled()) {
            minecraft.epochMillis2 = 0L;
            minecraft.epochMillis = 0L;
        }
    }

    @Override
    public void onSlowDown(EventSlowDown event) {
    }

    @Override
    public void onCustomSlowDown(EventSlowDown event) {
        if (this.settingTimerRangeBool.isEnabled()) {
            if (!BlockCache.canExecute()) {
                if (minecraft.isIntegratedServerRunning()) {
                    if (minecraft.theWorld != null) {
                        if (minecraft.thePlayer != null) {
                            if (!ClientGlobals.moduleManager.airStuck.isToggled()) {
                                if (!ClientGlobals.moduleManager.bugUp.activeState1 || !ClientGlobals.moduleManager.bugUp.isToggled()) {
                                    if (!this.settingForbiddenSneak.isEnabled() || !minecraft.thePlayer.isSprinting()) {
                                        if (!this.settingClickCheck.isEnabled() || minecraft.gameSettings.keyBindAttack.isKeyDown()) {
                                            if (!this.settingOnlyOnGround.isEnabled() || minecraft.thePlayer.onGround) {
                                                if (this.timer2.hasReached((long)this.settingDelay.getValue())) {
                                                    if (ClientGlobals.moduleManager.killAura.isToggled()
                                                    && ClientGlobals.moduleManager.killAura.target != null
                                                    || ClientGlobals.moduleManager.aimBot.isToggled()
                                                    && ClientGlobals.moduleManager.aimBot.target != null
                                                    || !this.settingOnlyKillAura.isEnabled()) {
                                                        for (int i = (int)this.settingTimerTicksForward.getValue(); i >= 1; i--) {
                                                            for (Object object : minecraft.theWorld.loadedEntityList) {
                                                                if (object instanceof EntityLivingBase) {
                                                                    if (this.settingOnlyKillAura.isEnabled()) {
                                                                        object = ChatUtil.getEntityLivingBase2((EntityLivingBase)object);
                                                                    }

                                                                    if (object instanceof EntityOtherPlayerMP) {
                                                                        EntityOtherPlayerMP abstractClientPlayerAdapter = (EntityOtherPlayerMP)object;
                                                                        float value = abstractClientPlayerAdapter.width / 2.0F;
                                                                        float height = abstractClientPlayerAdapter.height;
                                                                        int indexOffset10 = abstractClientPlayerAdapter.otherPlayerMPPosRotationIncrements;
                                                                        double offset = abstractClientPlayerAdapter.posX + (abstractClientPlayerAdapter.coordOffset3 - abstractClientPlayerAdapter.posX) / (double)Math.max(1, indexOffset10);
                                                                        double offset2 = abstractClientPlayerAdapter.posY + (abstractClientPlayerAdapter.coordOffset1 - abstractClientPlayerAdapter.posY) / (double)Math.max(1, indexOffset10);
                                                                        double offset3 = abstractClientPlayerAdapter.posZ + (abstractClientPlayerAdapter.coordOffset4 - abstractClientPlayerAdapter.posZ) / (double)Math.max(1, indexOffset10);
                                                                        indexOffset10--;

                                                                        for (int i2 = 0; i2 < i; i2++) {
                                                                            offset = abstractClientPlayerAdapter.posX + (abstractClientPlayerAdapter.coordOffset3 - abstractClientPlayerAdapter.posX) / (double)Math.max(1, indexOffset10);
                                                                            offset2 = abstractClientPlayerAdapter.posY + (abstractClientPlayerAdapter.coordOffset1 - abstractClientPlayerAdapter.posY) / (double)Math.max(1, indexOffset10);
                                                                            offset3 = abstractClientPlayerAdapter.posZ + (abstractClientPlayerAdapter.coordOffset4 - abstractClientPlayerAdapter.posZ) / (double)Math.max(1, indexOffset10);
                                                                            indexOffset10--;
                                                                        }

                                                                        float height2 = ((EntityOtherPlayerMP)(object)).getHeight();
                                                                        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
                                                                        offset - (double)value,
                                                                        offset2,
                                                                        offset3 - (double)value,
                                                                        offset + (double)value,
                                                                        offset2 + (double)height,
                                                                        offset3 + (double)value
                                                                        )
                                                                        .offset((double)height2, (double)height2, (double)height2);
                                                                        double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(
                                                                        i, minecraft.thePlayer
                                                                        );
                                                                        double offset4 = minecraft.thePlayer.getPositionVec8().xCoord
                                                                        + (coordOffsetArray[0] - minecraft.thePlayer.getPositionVec8().xCoord) / 3.0;
                                                                        double offset5 = minecraft.thePlayer.getPositionVec8().yCoord
                                                                        + (coordOffsetArray[1] - minecraft.thePlayer.getPositionVec8().yCoord) / 3.0;
                                                                        double offset6 = minecraft.thePlayer.getPositionVec8().zCoord
                                                                        + (coordOffsetArray[2] - minecraft.thePlayer.getPositionVec8().zCoord) / 3.0;
                                                                        Vec3 vec = new Vec3(
                                                                        coordOffsetArray[3], coordOffsetArray[4] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray[5]
                                                                        );
                                                                        Vec3 vec2 = new Vec3(
                                                                        MathHelper.getDouble7(vec.xCoord, axisAlignedBB.minX, axisAlignedBB.maxX),
                                                                        MathHelper.getDouble7(vec.yCoord, axisAlignedBB.minY, axisAlignedBB.maxY),
                                                                        MathHelper.getDouble7(vec.zCoord, axisAlignedBB.minZ, axisAlignedBB.maxZ)
                                                                        );
                                                                        AxisAlignedBB axisAlignedBB2 = abstractClientPlayerAdapter.getBoundingBox2()
                                                                        .offset((double)height2, (double)height2, (double)height2);
                                                                        Vec3 vec3 = minecraft.thePlayer.getVec35(1.0F);
                                                                        Vec3 vec4 = new Vec3(
                                                                        MathHelper.getDouble7(vec3.xCoord, axisAlignedBB2.minX, axisAlignedBB2.maxX),
                                                                        MathHelper.getDouble7(vec3.yCoord, axisAlignedBB2.minY, axisAlignedBB2.maxY),
                                                                        MathHelper.getDouble7(vec3.zCoord, axisAlignedBB2.minZ, axisAlignedBB2.maxZ)
                                                                        );
                                                                        Vec3 vec5 = new Vec3(
                                                                        abstractClientPlayerAdapter.coordOffset3, abstractClientPlayerAdapter.coordOffset1 + (double)abstractClientPlayerAdapter.getHeight2(), abstractClientPlayerAdapter.coordOffset4
                                                                        );
                                                                        double offset7;
                                                                        if (ClientGlobals.moduleManager.killAura.isToggled()
                                                                        && ClientGlobals.moduleManager.killAura.target != null) {
                                                                            KillAura killAura = ClientGlobals.moduleManager.killAura;
                                                                            offset7 = killAura.reachDistance;
                                                                        } else {
                                                                            ModuleManager moduleManager = ClientGlobals.moduleManager;
                                                                            if (!moduleManager.range.checkCondition()) {
                                                                                offset7 = 3.0;
                                                                            } else {
                                                                                offset7 = ClientGlobals.moduleManager.range.getCoordinate();
                                                                            }
                                                                        }

                                                                        AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(
                                                                        offset4 - (double)value,
                                                                        offset5,
                                                                        offset6 - (double)value,
                                                                        offset4 + (double)value,
                                                                        offset5 + (double)height,
                                                                        offset6 + (double)value
                                                                        )
                                                                        .offset((double)height2, (double)height2, (double)height2);
                                                                        Vec3 vec6 = new Vec3(
                                                                        MathHelper.getDouble7(vec5.xCoord, axisAlignedBB3.minX, axisAlignedBB3.maxX),
                                                                        MathHelper.getDouble7(vec5.yCoord, axisAlignedBB3.minY, axisAlignedBB3.maxY),
                                                                        MathHelper.getDouble7(vec5.zCoord, axisAlignedBB3.minZ, axisAlignedBB3.maxZ)
                                                                        );
                                                                        if (vec.getCoordinate4(vec2) < offset7
                                                                        && vec5.getCoordinate4(vec6) > 3.0 - this.settingTolerance.getValue()
                                                                        && vec3.getCoordinate4(vec4) > 3.0) {
                                                                            event.processAction(i);
                                                                            if (ClientGlobals.moduleManager.debug.isToggled()
                                                                            && ClientGlobals.moduleManager.debug.settingTimerRangeDebug.isEnabled()) {
                                                                                ClientGlobals.moduleManager
                                                                                .debug
                                                                                .applyString6(
                                                                                
                                                                                
                                                                                "Timered:  "
                                                                                + vec5.getCoordinate4(vec6)
                                                                                + "  "
                                                                                + vec.getCoordinate4(vec2)
                                                                                );
                                                                            }

                                                                            this.timer2.reset();
                                                                            break;
                                                                        }

                                                                        if (!this.settingDynamic.isEnabled()) {
                                                                            break;
                                                                        }

                                                                        if (this.settingOnlyKillAura.isEnabled()) {
                                                                            if (object == ClientGlobals.moduleManager.killAura.target) {
                                                                                break;
                                                                            }

                                                                            EntityLivingBase target = ClientGlobals.moduleManager.aimBot.target;
                                                                            if (object == target) {
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkCondition(int index2) {
        boolean enabled;
        if (minecraft.thePlayer != null
        && this.checkCondition()
        && (double)index2 < this.settingMaxChargingTicks.getDouble2(true)) {
            enabled = true;
        } else {
            enabled = false;
        }

        if (minecraft.thePlayer != null
        && ClientGlobals.moduleManager.debug.isToggled()
        && ClientGlobals.moduleManager.debug.settingTimeManipulation.isEnabled()) {
            ClientGlobals.moduleManager.debug.applyString6("TickBuffer: " + index2);
        }

        return enabled;
    }

    public void updateState() {
        if (minecraft.theWorld != null) {
            for (int i = 0; i < minecraft.theWorld.loadedEntityList.size(); i++) {
                Entity entity = (Entity)minecraft.theWorld.loadedEntityList.get(i);
                if (!(entity instanceof EntityPlayerSP)) {
                    minecraft.theWorld.removeEntity(entity);
                }
            }
        }
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }

    public static String[] getStringArray() {
        return stringArray;
    }

    private boolean checkCondition() {
        boolean enabled = this.settingSneakCharge.isEnabled();
        if (enabled) {
            enabled = KeyUtils.checkCondition(minecraft.gameSettings.keyBindSneak.getKeyCode());
            if (enabled) {
                enabled = minecraft.isIntegratedServerRunning();
            }
        }

        if (this.settingAutoCharge.isEnabled()) {
            boolean enabled2 = true;
            if (minecraft.currentScreen == null
            && minecraft.thePlayer != null
            && minecraft.theWorld != null
            && !MoveUtil.canExecute2()
            && minecraft.thePlayer.ticksExisted > 20
            && !MouseUtils.checkCondition(0)
            && !MouseUtils.checkCondition(1)) {
                for (int i = 0; i < 223; i++) {
                    if (KeyUtils.checkCondition(i)) {
                        this.manipulationTimer.reset();
                        break;
                    }
                }

                if (this.manipulationTimer.hasReached(100L)) {
                    enabled = true;
                }

                enabled2 = false;
            }

            if (enabled2) {
                this.manipulationTimer.reset();
            }
        }

        return this.settingTimerRangeBool.isEnabled() || enabled;
    }
}
