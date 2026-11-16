package augustus.modules.latency;

import augustus.custom.RuntimeBridge;
import client.ClientGlobals;
import client.event.EventMouseClick;
import client.event.EventSendPacket;
import client.event.MouseClickListener;
import client.event.NetworkPacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.LagRangeTargetEntity;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatTargetFilter;
import client.util.CombatUtil;
import client.util.LvlManager;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TimerRangeV2 extends Module implements MouseClickListener, NetworkPacketListener {
    public BooleanSetting settingDynamicLagTicks;
    public NumberSetting settingLagTickDiff;
    public NumberSetting settingLagTicks;
    public NumberSetting settingTimerTicks;
    public NumberSetting settingMaxTargetHurttime;
    public NumberSetting settingDelay;
    public BooleanSetting settingCorrect;
    public BooleanSetting settingDynamic;
    public BooleanSetting settingAccurate;
    public BooleanSetting settingDebug;
    public BooleanSetting settingOnlyOnGround;
    public ModeSetting settingFixMode;
    public BooleanSetting settingExtreme;
    public SettingGroup settingTargets;
    public BooleanSetting settingOnlyWhileKillAura;
    public MultiSelectSetting settingTargets2;
    public NumberSetting settingPreAimRange;
    public TimeHelper timer;
    public TimeHelper timer2;
    public boolean expanded2;
    public boolean activeState;
    public boolean enabled2;
    public int tickDelay;
    public int tickDelay4;
    public int tickDelay2;
    public int keyCode2;
    public int tickDelay3;
    public int tickDelay1;
    public AxisAlignedBB boundingBox;
    public boolean visible2;

    public TimerRangeV2() {
        super("TimerRangeV2", "ZeitDistanzV2", new Color(23, 233, 123), Category.LATENCY);
        this.settingDynamicLagTicks = new BooleanSetting(this, "DynamicLagTicks", true);
        this.settingLagTickDiff = new NumberSetting(this, "LagTickDiff", 3.0, 0.0, 10.0, 0).getNumberSetting(() -> this.settingDynamicLagTicks.isEnabled());
        this.settingLagTicks = new NumberSetting(this, "LagTicks", 1.0, 0.0, 10.0, 0).getNumberSetting(() -> this.isLagTicksVisible());
        this.settingTimerTicks = new NumberSetting(this, "TimerTicks", 5.0, 0.0, 10.0, 0);
        this.settingMaxTargetHurttime = new NumberSetting(this, "MaxTargetHurttime", 3.0, 0.0, 10.0, 0);
        this.settingDelay = new NumberSetting(this, "Delay", 500.0, 0.0, 4000.0, 0);
        this.settingCorrect = new BooleanSetting(this, "Correct", false);
        this.settingDynamic = new BooleanSetting(this, "Dynamic", true);
        this.settingAccurate = new BooleanSetting(this, "Accurate", false);
        this.settingDebug = new BooleanSetting(this, "Debug", false);
        this.settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false);
        String[] textArray = new String[3];
        textArray[0] = "None";
        textArray[1] = "Fix1";
        textArray[2] = "Fix2";
        this.settingFixMode = new ModeSetting(this, "FixMode", "None", textArray);
        this.settingExtreme = new BooleanSetting(this, "Extreme", false);
        this.settingTargets = new SettingGroup(this, "Targets");
        this.settingOnlyWhileKillAura = new BooleanSetting(this, "OnlyWhileKillAura", true, this.settingTargets);
        LagRangeTargetEntity[] lagRangeTargetEntityArray = LagRangeTargetEntity.values();
        this.settingTargets2 = new MultiSelectSetting(this, "Targets", lagRangeTargetEntityArray, this.settingTargets).getMultiSelectSetting(() -> this.isTargetsVisible());
        this.settingPreAimRange = new NumberSetting(this, "PreAimRange", 4.0, 0.0, 15.0, 1, this.settingTargets).getNumberSetting(() -> this.isPreAimRangeVisible());
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.expanded2 = false;
        this.activeState = false;
        this.enabled2 = false;
        this.tickDelay = 0;
        this.tickDelay4 = 0;
        this.tickDelay2 = 0;
        this.keyCode2 = 0;
        this.tickDelay3 = 0;
        this.tickDelay1 = 0;
        this.boundingBox = (AxisAlignedBB)null;
        this.visible2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(16);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.activeState = false;
        this.expanded2 = false;
        this.enabled2 = false;
        this.tickDelay1 = 0;
        this.processChar();
        this.tickDelay4 = 0;
        this.keyCode2 = 0;
        this.tickDelay3 = 0;
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (!this.settingFixMode.getSelected().equals("None")) {
            if (minecraft.thePlayer == null) {
                return;
            }

            if (minecraft.thePlayer.ticksExisted > 10) {
                packet.setCancelled(this.expanded2);
            }
        }
    }

    @Override
    public void onMouseClick(EventMouseClick event) {
        this.getEntityLivingBase();
        boolean enabled = this.settingCorrect.isEnabled();
        event.processBoolean(enabled & true);
        this.settingTimerTicks.getValue();
        double value = this.settingLagTicks.getValue();
        boolean enabled3;
        if (((!Double.isNaN(value) && !Double.isNaN(0.0) ? (value > 0.0 ? 1 : (value == 0.0 ? 0 : -1)) : 1) > 0 || this.settingDynamicLagTicks.isEnabled())
        && !this.enabled2) {
            enabled3 = false;
        } else {
            enabled3 = true;
        }

        this.enabled2 = enabled3 & true;
        if (!this.settingDynamicLagTicks.isEnabled()) {
            this.keyCode2 = (int)this.settingLagTicks.getValue();
        }

        int state = (byte)0;
        int index = 0;
        boolean completed = false;
        if (event.getIndex() >= 1) {
            if (!this.enabled2) {
                return;
            }
            double offset = 0.0;
            if (this.settingDynamicLagTicks.isEnabled()) {
                double tickDelay42 = (double)this.tickDelay4;
                double value2 = this.settingLagTickDiff.getValue();
                offset = tickDelay42 - value2;
            } else {
                offset = this.settingLagTicks.getValue();
            }
            int index2 = (int)offset;
            if (this.tickDelay1 >= index2) {
                index = this.tickDelay;
                state = 3;
                completed = true;
            }
        }
        if (!completed) {
            index = this.tickDelay;
            state = 1;
        }


        if (index == state) {
            if (this.activeState) {
                double tickDelay43 = (double)this.tickDelay4;
                double value3 = this.settingLagTickDiff.getValue();
                if ((int)(tickDelay43 - value3) > 0) {
                    int lagTicks2 = (int)this.settingLagTicks.getValue();
                    event.processAction(lagTicks2);
                    this.processChar();
                    this.enabled2 = false;
                    this.expanded2 = false;
                }
            } else {
                double value4 = this.settingLagTicks.getValue();
                if ((!Double.isNaN(value4) && !Double.isNaN(0.0) ? (value4 > 0.0 ? 1 : (value4 == 0.0 ? 0 : -1)) : -1) > 0) {
                    int lagTicks3 = (int)this.settingLagTicks.getValue();
                    event.processAction(lagTicks3);
                    this.processChar();
                    this.enabled2 = false;
                    this.tickDelay3 = 0;
                }
            }
        } else if (this.tickDelay == 3) {
            int index3;
            if (this.settingDynamicLagTicks.isEnabled()) {
                double tickDelay44 = (double)this.tickDelay4;
                double tickDelay45 = (double)this.tickDelay4;
                double value5 = this.settingLagTickDiff.getValue();
                double offset2 = tickDelay45 - value5;
                double offset3 = Math.max(0.0, offset2);
                index3 = (int)(tickDelay44 - offset3);
            } else {
                double tickDelay46 = (double)this.tickDelay4;
                double value6 = this.settingLagTicks.getValue();
                index3 = (int)(tickDelay46 - value6);
            }

            if (this.tickDelay1 <= index3) {
                this.boundingBox = (AxisAlignedBB)null;
                event.processAction(0);
                event.processAction(0.0F);
                event.setActive(true);
                this.enabled2 = false;
                this.timer.reset();
                this.expanded2 = true;
                if (this.settingDebug.isEnabled()) {
                    RayTraceUtil.applyString("LastTimerStop");
                }
            }

            this.expanded2 = false;
        }

        int tickDelay12 = this.tickDelay1;
        int indexOffset = minecraft.getTimer().indexOffset;
        int index4 = tickDelay12 + indexOffset;
        this.tickDelay1 = index4;
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        Packet packet2 = packet.getPacket();
        if (!this.settingExtreme.isEnabled() || this.tickDelay != 3) {
            this.visible2 = false;
        } else if (packet2 instanceof C02PacketUseEntity && !this.visible2) {
            this.visible2 = true;
        } else if (this.visible2) {
            packet.setCancelled(true);
        }
    }

    private boolean checkEntityLivingBase(EntityLivingBase livingEntity, int index2, int index3) {
        if ((!this.settingOnlyOnGround.isEnabled() || minecraft.thePlayer.onGround)
        && (
        !ClientGlobals.moduleManager.killAura.isToggled()
        || !ClientGlobals.moduleManager.killAura.settingSimple.getSelected().equals("Grim")
        || !ClientGlobals.moduleManager.killAura.activeState14
        )
        && livingEntity.ticksExisted >= 20) {
            double posX = livingEntity.posX;
            double posY = livingEntity.posY;
            double posZ = livingEntity.posZ;
            Vec3 vec2 = new Vec3(posX, posY, posZ);
            Vec3 vec;
            if (index3 <= 0) {
                double coordOffset5 = livingEntity.coordOffset5;
                double coordOffset20 = livingEntity.coordOffset20;
                double coordOffset2 = livingEntity.coordOffset2;
                vec = new Vec3(coordOffset5, coordOffset20, coordOffset2);
            } else {
                EntityLivingBase livingEntity2 = livingEntity;
                Vec3 vec3 = CombatTargetFilter.getVec3(-1266944806, livingEntity2, index3);
                vec = new Vec3(vec3);
            }

            double xCoord = vec2.xCoord;
            double yCoord = vec2.yCoord;
            double zCoord = vec2.zCoord;
            int indexOffset23 = livingEntity.indexOffset23;
            int index4 = 0;

            while (true) {
                int index5 = index2 - 1;
                if (index4 >= index5) {
                    float value3 = livingEntity.width / 2.0F;
                    float height = livingEntity.getHeight();
                    double offset = (double)value3;
                    double offset2 = xCoord - offset;
                    double offset3 = (double)value3;
                    double offset4 = zCoord - offset3;
                    double offset5 = (double)value3;
                    double offset6 = xCoord + offset5;
                    double height3 = (double)livingEntity.height;
                    double offset7 = yCoord + height3;
                    double offset8 = (double)value3;
                    double offset9 = zCoord + offset8;
                    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(offset2, yCoord, offset4, offset6, offset7, offset9);
                    double offset10 = (double)height;
                    double offset11 = (double)height;
                    double offset12 = (double)height;
                    AxisAlignedBB axisAlignedBB2 = axisAlignedBB.offset(offset10, offset11, offset12);
                    Vec3 positionVec8 = minecraft.thePlayer.getPositionVec8();
                    Vec3 positionVec12 = minecraft.thePlayer.getPositionVec12();
                    double xCoord2 = positionVec12.xCoord;
                    double xCoord3 = positionVec8.xCoord;
                    double xCoord4 = positionVec12.xCoord;
                    double offset13 = (xCoord3 - xCoord4) / 2.0;
                    double offset14 = xCoord2 + offset13;
                    double yCoord2 = positionVec12.yCoord;
                    double yCoord3 = positionVec8.yCoord;
                    double yCoord4 = positionVec12.yCoord;
                    double offset15 = (yCoord3 - yCoord4) / 2.0;
                    double offset16 = yCoord2 + offset15;
                    double zCoord2 = positionVec12.zCoord;
                    double zCoord3 = positionVec8.zCoord;
                    double zCoord4 = positionVec12.zCoord;
                    double offset17 = (zCoord3 - zCoord4) / 2.0;
                    double offset18 = zCoord2 + offset17;
                    Vec3 vec4 = new Vec3(offset14, offset16, offset18);
                    if (this.tickDelay3 > 0 || index3 > 0) {
                        double xCoord5 = positionVec8.xCoord;
                        double yCoord5 = positionVec8.yCoord;
                        double zCoord5 = positionVec8.zCoord;
                        vec4 = new Vec3(xCoord5, yCoord5, zCoord5);
                    }

                    int index6 = index2 - 1;
                    EntityLivingBase livingEntity3 = livingEntity;
                    double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(index6, minecraft.thePlayer, livingEntity3);
                    double offset19 = coordOffsetArray[0];
                    double offset20 = coordOffsetArray[1];
                    double offset21 = coordOffsetArray[2];
                    Vec3 vec5 = new Vec3(offset19, offset20, offset21);
                    double xCoord6 = vec4.xCoord;
                    double yCoord6 = vec4.yCoord;
                    double zCoord6 = vec4.zCoord;
                    int state = 3;

                    do {
                        double offset22 = vec5.xCoord - xCoord6;
                        double offset23 = (double)state;
                        double offset24 = offset22 / offset23;
                        xCoord6 += offset24;
                        double offset25 = vec5.yCoord - yCoord6;
                        double offset26 = (double)state;
                        double offset27 = offset25 / offset26;
                        yCoord6 += offset27;
                        double offset28 = vec5.zCoord - zCoord6;
                        double offset29 = (double)state;
                        double offset30 = offset28 / offset29;
                        zCoord6 += offset30;
                        state += -1;
                    } while (state >= 2);

                    Vec3 vec6 = new Vec3(xCoord6, yCoord6, zCoord6);
                    double xCoord7 = vec4.xCoord;
                    double xCoord8 = vec5.xCoord;
                    double xCoord9 = vec4.xCoord;
                    double offset31 = (xCoord8 - xCoord9) / 3.0;
                    double offset32 = xCoord7 + offset31;
                    double yCoord7 = vec4.yCoord;
                    double yCoord8 = vec5.yCoord;
                    double yCoord9 = vec4.yCoord;
                    double offset33 = (yCoord8 - yCoord9) / 3.0;
                    double offset34 = yCoord7 + offset33;
                    double zCoord7 = vec4.zCoord;
                    double zCoord8 = vec5.zCoord;
                    double zCoord9 = vec4.zCoord;
                    double offset35 = (zCoord8 - zCoord9) / 3.0;
                    double offset36 = zCoord7 + offset35;
                    Vec3 vec7 = new Vec3(offset32, offset34, offset36);
                    float value4 = minecraft.thePlayer.width / 2.0F;
                    float height2 = minecraft.thePlayer.getHeight();
                    double xCoord10 = vec6.xCoord;
                    double offset37 = (double)value4;
                    double offset38 = xCoord10 - offset37;
                    double yCoord10 = vec6.yCoord;
                    double zCoord10 = vec6.zCoord;
                    double offset39 = (double)value4;
                    double offset40 = zCoord10 - offset39;
                    double xCoord11 = vec6.xCoord;
                    double offset41 = (double)value4;
                    double offset42 = xCoord11 + offset41;
                    double yCoord11 = vec6.yCoord;
                    double height4 = (double)minecraft.thePlayer.height;
                    double offset43 = yCoord11 + height4;
                    double zCoord11 = vec6.zCoord;
                    double offset44 = (double)value4;
                    double offset45 = zCoord11 + offset44;
                    AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(offset38, yCoord10, offset40, offset42, offset43, offset45);
                    double offset46 = (double)height2;
                    double offset47 = (double)height2;
                    double offset48 = (double)height2;
                    AxisAlignedBB axisAlignedBB4 = axisAlignedBB3.offset(offset46, offset47, offset48);
                    double xCoord12 = vec7.xCoord;
                    double offset49 = (double)value4;
                    double offset50 = xCoord12 - offset49;
                    double yCoord12 = vec7.yCoord;
                    double zCoord12 = vec7.zCoord;
                    double offset51 = (double)value4;
                    double offset52 = zCoord12 - offset51;
                    double xCoord13 = vec7.xCoord;
                    double offset53 = (double)value4;
                    double offset54 = xCoord13 + offset53;
                    double yCoord13 = vec7.yCoord;
                    double height5 = (double)minecraft.thePlayer.height;
                    double offset55 = yCoord13 + height5;
                    double zCoord13 = vec7.zCoord;
                    double offset56 = (double)value4;
                    double offset57 = zCoord13 + offset56;
                    AxisAlignedBB axisAlignedBB5 = new AxisAlignedBB(offset50, yCoord12, offset52, offset54, offset55, offset57);
                    double offset58 = (double)height2;
                    double offset59 = (double)height2;
                    double offset60 = (double)height2;
                    AxisAlignedBB axisAlignedBB6 = axisAlignedBB5.offset(offset58, offset59, offset60);
                    int index7 = index3 + 1;
                    EntityLivingBase livingEntity4 = livingEntity;
                    Vec3 vec8 = CombatTargetFilter.getVec3(-1266944806, livingEntity4, index7);
                    EntityLivingBase livingEntity5 = livingEntity;
                    Vec3 vec9 = CombatTargetFilter.getVec3(-1266944806, livingEntity5, index3);
                    int index8 = index3 + 1;
                    double offset61 = CombatTargetFilter.getCoordOffsetArray(livingEntity, index8)[9];
                    boolean enabled;
                    if ((!Double.isNaN(offset61) && !Double.isNaN(1.0) ? (offset61 > 1.0 ? 1 : (offset61 == 1.0 ? 0 : -1)) : -1) == 0) {
                        enabled = true;
                    } else {
                        enabled = false;
                    }

                    Vec3 vec10 = new Vec3(vec8);
                    Vec3 vec11 = new Vec3(vec9);
                    float value5 = 0.91F;
                    if (enabled) {
                        World worldObj = livingEntity.worldObj;
                        int int15 = MathHelper.ceiling_double_int(vec10.xCoord);
                        int index9 = MathHelper.ceiling_double_int(vec10.yCoord) - 1;
                        int int152 = MathHelper.ceiling_double_int(vec10.zCoord);
                        BlockPos blockPos = new BlockPos(int15, index9, int152);
                        value5 = worldObj.getBlockState(blockPos).getBlock().scaleFactor * 0.91F;
                    }

                    double offset62 = 0.0;
                    double offset63 = 0.0;
                    double offset64 = 0.0;
                    {
                        boolean completed2 = false;
                        double offset65 = 0.0;
                        double xCoord14 = vec10.xCoord;
                        double xCoord15 = vec11.xCoord;
                        offset62 = xCoord14 - xCoord15;
                        double yCoord14 = vec10.yCoord;
                        double yCoord15 = vec11.yCoord;
                        offset65 = yCoord14 - yCoord15;
                        double zCoord14 = vec10.zCoord;
                        double zCoord15 = vec11.zCoord;
                        offset64 = zCoord14 - zCoord15;
                        boolean completed = false;
                        if (minecraft.theWorld.state) {
                            int x = (int)vec10.xCoord;
                            int z = (int)vec10.zCoord;
                            BlockPos blockPos2 = new BlockPos(x, 0, z);
                            if (minecraft.theWorld.checkBlockPos12(blockPos2)) {
                                int x2 = (int)vec10.xCoord;
                                int z2 = (int)vec10.zCoord;
                                BlockPos blockPos3 = new BlockPos(x2, 0, z2);
                                if (minecraft.theWorld.getLoggerEntry(blockPos3).canExecute2()) {
                                    completed = true;
                                }
                            }
                            if (!completed) {
                                double yCoord16 = vec10.yCoord;
                                if ((!Double.isNaN(yCoord16) && !Double.isNaN(0.0) ? (yCoord16 > 0.0 ? 1 : (yCoord16 == 0.0 ? 0 : -1)) : -1) > 0) {
                                    offset63 = -0.1;
                                } else {
                                    offset63 = 0.0;
                                }
                                completed2 = true;
                            }
                        }
                        if (!completed2) {
                            offset63 = offset65 - 0.08;
                        }

                    }

                    double offset66 = offset63 * 0.98F;
                    double offset67 = (double)value5;
                    double offset68 = offset62 * offset67;
                    double offset69 = (double)value5;
                    double offset70 = offset64 * offset69;
                    double posX2 = minecraft.thePlayer.posX;
                    double xCoord16 = vec11.xCoord;
                    double offset71 = posX2 - xCoord16;
                    double posZ2 = minecraft.thePlayer.posZ;
                    double zCoord16 = vec11.zCoord;
                    double offset72 = posZ2 - zCoord16;
                    double offset73 = offset71 * offset71;
                    double offset74 = offset72 * offset72;
                    float value6 = MathHelper.sqrt_double(offset73 + offset74);
                    float value7 = 0.4F;
                    double offset75 = offset68 / 2.0;
                    double offset76 = offset66 / 2.0;
                    double offset77 = offset70 / 2.0;
                    double offset78 = (double)value6;
                    double offset79 = offset71 / offset78;
                    double offset80 = (double)value7;
                    double offset81 = offset79 * offset80;
                    double offset82 = offset75 - offset81;
                    double offset83 = (double)value7;
                    double offset84 = offset76 + offset83;
                    double offset85 = (double)value6;
                    double offset86 = offset72 / offset85;
                    double offset87 = (double)value7;
                    double offset88 = offset86 * offset87;
                    double offset89 = offset77 - offset88;
                    double offset90 = 0.4F;
                    if ((!Double.isNaN(offset84) && !Double.isNaN(offset90) ? (offset84 > offset90 ? 1 : (offset84 == offset90 ? 0 : -1)) : -1) > 0) {
                        offset84 = 0.4F;
                    }

                    Vec3 vec12 = new Vec3(offset82, offset84, offset89);
                    double offset91 = coordOffsetArray[3];
                    double offset92 = coordOffsetArray[4];
                    double height22 = (double)minecraft.thePlayer.getHeight2();
                    double offset93 = offset92 + height22;
                    double offset94 = coordOffsetArray[5];
                    Vec3 vec13 = new Vec3(offset91, offset93, offset94);
                    double xCoord17 = vec13.xCoord;
                    double minX = axisAlignedBB2.minX;
                    double maxX = axisAlignedBB2.maxX;
                    double double7 = MathHelper.getDouble7(xCoord17, minX, maxX);
                    double zCoord17 = vec13.zCoord;
                    double minZ = axisAlignedBB2.minZ;
                    double maxZ = axisAlignedBB2.maxZ;
                    double double72 = MathHelper.getDouble7(zCoord17, minZ, maxZ);
                    double offset95 = coordOffsetArray[3];
                    double offset96 = double7 - offset95;
                    double offset97 = coordOffsetArray[5];
                    float value8 = (float)(MathHelper.getDouble6(double72 - offset97, offset96) * 180.0 / Math.PI - 90.0);
                    int index = LvlManager.getIndex(minecraft.thePlayer);
                    int index10 = 1 + index;
                    float value9 = -MathHelper.abs(value8 * (float) Math.PI / 180.0F);
                    float value10 = (float)index10;
                    double offset98 = (double)(value9 * value10 * 0.5F);
                    float float5 = MathHelper.cos(value8 * (float) Math.PI / 180.0F);
                    float value11 = (float)index10;
                    double offset99 = (double)(float5 * value11 * 0.5F);
                    Vec3 vec14 = vec12.getVec312(offset98, 0.1, offset99);
                    float value12 = 0.91F;
                    if (enabled) {
                        World worldObj2 = livingEntity.worldObj;
                        int int153 = MathHelper.ceiling_double_int(vec10.xCoord);
                        int index11 = MathHelper.ceiling_double_int(vec10.yCoord) - 1;
                        int int154 = MathHelper.ceiling_double_int(vec10.zCoord);
                        BlockPos blockPos4 = new BlockPos(int153, index11, int154);
                        value12 = worldObj2.getBlockState(blockPos4).getBlock().scaleFactor * 0.91F;
                    }

                    float value13 = value12 * value12 * value12;
                    float value14 = 0.16277136F / value13;
                    float value15;
                    if (enabled) {
                        value15 = livingEntity.getScaleFactor6() * value14;
                    } else {
                        value15 = livingEntity.scaleFactor10;
                    }

                    float value16 = 0.0F;
                    float value17 = 0.98F;
                    float value18 = value16 * value16;
                    float value19 = value17 * value17;
                    float value20 = value18 + value19;
                    if ((!Float.isNaN(value20) && !Float.isNaN(1.0E-4F) ? (value20 > 1.0E-4F ? 1 : (value20 == 1.0E-4F ? 0 : -1)) : -1) >= 0) {
                        float value21 = MathHelper.sin(value20);
                        float value22 = 1.0F;
                        if ((!Float.isNaN(value21) && !Float.isNaN(value22) ? (value21 > value22 ? 1 : (value21 == value22 ? 0 : -1)) : 1) < 0) {
                            value21 = 1.0F;
                        }

                        float value23 = value15 / value21;
                        float value24 = value16 * value23;
                        float value25 = value17 * value23;
                        double xCoord18 = vec10.xCoord;
                        double yCoord17 = vec10.yCoord;
                        double height23 = (double)livingEntity.getHeight2();
                        double offset100 = yCoord17 + height23;
                        double zCoord18 = vec10.zCoord;
                        Vec3 vec15 = new Vec3(xCoord18, offset100, zCoord18);
                        double xCoord19 = vec15.xCoord;
                        double minX2 = axisAlignedBB4.minX;
                        double maxX2 = axisAlignedBB4.maxX;
                        double double73 = MathHelper.getDouble7(xCoord19, minX2, maxX2);
                        double yCoord18 = vec15.yCoord;
                        double minY = axisAlignedBB4.minY;
                        double maxY = axisAlignedBB4.maxY;
                        double double74 = MathHelper.getDouble7(yCoord18, minY, maxY);
                        double zCoord19 = vec15.zCoord;
                        double minZ2 = axisAlignedBB4.minZ;
                        double maxZ2 = axisAlignedBB4.maxZ;
                        double double75 = MathHelper.getDouble7(zCoord19, minZ2, maxZ2);
                        new Vec3(double73, double74, double75);
                        double offset101 = vec14.xCoord * -1.0;
                        float value26 = (float)(MathHelper.getDouble6(vec14.zCoord * -1.0, offset101) * 180.0 / Math.PI - 90.0);
                        float float7 = MathHelper.abs(value26 * (float) Math.PI / 180.0F);
                        float float52 = MathHelper.cos(value26 * (float) Math.PI / 180.0F);
                        float value27 = value24 * float52;
                        float value28 = value25 * float7;
                        double offset102 = (double)(value27 - value28);
                        float value29 = value25 * float52;
                        float value30 = value24 * float7;
                        double offset103 = (double)(value29 + value30);
                        vec14 = vec14.getVec312(offset102, 0.0, offset103);
                    }

                    Vec3 vec16 = vec10.getVec36(vec14);
                    double xCoord20 = vec16.xCoord;
                    double yCoord19 = vec16.yCoord;
                    double height24 = (double)livingEntity.getHeight2();
                    double offset104 = yCoord19 + height24;
                    double zCoord20 = vec16.zCoord;
                    Vec3 vec17 = new Vec3(xCoord20, offset104, zCoord20);
                    double xCoord21 = vec17.xCoord;
                    double minX3 = axisAlignedBB4.minX;
                    double maxX3 = axisAlignedBB4.maxX;
                    double double76 = MathHelper.getDouble7(xCoord21, minX3, maxX3);
                    double yCoord20 = vec17.yCoord;
                    double minY2 = axisAlignedBB4.minY;
                    double maxY2 = axisAlignedBB4.maxY;
                    double double77 = MathHelper.getDouble7(yCoord20, minY2, maxY2);
                    double zCoord21 = vec17.zCoord;
                    double minZ3 = axisAlignedBB4.minZ;
                    double maxZ3 = axisAlignedBB4.maxZ;
                    double double78 = MathHelper.getDouble7(zCoord21, minZ3, maxZ3);
                    Vec3 vec18 = new Vec3(double76, double77, double78);
                    double xCoord22 = vec10.xCoord;
                    double yCoord21 = vec10.yCoord;
                    double height25 = (double)livingEntity.getHeight2();
                    double offset105 = yCoord21 + height25;
                    double zCoord22 = vec10.zCoord;
                    Vec3 vec19 = new Vec3(xCoord22, offset105, zCoord22);
                    double xCoord23 = vec19.xCoord;
                    double minX4 = axisAlignedBB6.minX;
                    double maxX4 = axisAlignedBB6.maxX;
                    double double79 = MathHelper.getDouble7(xCoord23, minX4, maxX4);
                    double yCoord22 = vec19.yCoord;
                    double minY3 = axisAlignedBB6.minY;
                    double maxY3 = axisAlignedBB6.maxY;
                    double double710 = MathHelper.getDouble7(yCoord22, minY3, maxY3);
                    double zCoord23 = vec19.zCoord;
                    double minZ4 = axisAlignedBB6.minZ;
                    double maxZ4 = axisAlignedBB6.maxZ;
                    double double711 = MathHelper.getDouble7(zCoord23, minZ4, maxZ4);
                    Vec3 vec20 = new Vec3(double79, double710, double711);
                    double offset106 = coordOffsetArray[3];
                    double offset107 = coordOffsetArray[4];
                    double offset108 = coordOffsetArray[5];
                    Vec3 vec21 = new Vec3(offset106, offset107, offset108);
                    double xCoord24 = vec21.xCoord;
                    double yCoord23 = vec21.yCoord;
                    double height26 = (double)minecraft.thePlayer.getHeight2();
                    double offset109 = yCoord23 + height26;
                    double zCoord24 = vec21.zCoord;
                    Vec3 vec22 = new Vec3(xCoord24, offset109, zCoord24);
                    double xCoord25 = vec22.xCoord;
                    double minX5 = axisAlignedBB2.minX;
                    double maxX5 = axisAlignedBB2.maxX;
                    double double712 = MathHelper.getDouble7(xCoord25, minX5, maxX5);
                    double yCoord24 = vec22.yCoord;
                    double minY4 = axisAlignedBB2.minY;
                    double maxY4 = axisAlignedBB2.maxY;
                    double double713 = MathHelper.getDouble7(yCoord24, minY4, maxY4);
                    double zCoord25 = vec22.zCoord;
                    double minZ5 = axisAlignedBB2.minZ;
                    double maxZ5 = axisAlignedBB2.maxZ;
                    double double714 = MathHelper.getDouble7(zCoord25, minZ5, maxZ5);
                    Vec3 vec23 = new Vec3(double712, double713, double714);
                    double offset110;
                    if (ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null) {
                        offset110 = ClientGlobals.moduleManager.killAura.reachDistance;
                    } else if (ClientGlobals.moduleManager.range.checkCondition()) {
                        offset110 = ClientGlobals.moduleManager.range.getCoordinate();
                    } else {
                        offset110 = 3.0;
                    }

                    if (this.settingDebug.isEnabled()) {
                        double hurtTime = (double)livingEntity.hurtTime;
                        double value = this.settingMaxTargetHurttime.getValue();
                        if ((!Double.isNaN(hurtTime) && !Double.isNaN(value) ? (hurtTime > value ? 1 : (hurtTime == value ? 0 : -1)) : 1) <= 0) {
                            double coordinate4 = vec19.getCoordinate4(vec20);
                            if ((!Double.isNaN(coordinate4) && !Double.isNaN(3.0) ? (coordinate4 > 3.0 ? 1 : (coordinate4 == 3.0 ? 0 : -1)) : -1) > 0
                            || !this.settingAccurate.isEnabled()) {
                                double coordinate42 = vec17.getCoordinate4(vec18);
                                if ((!Double.isNaN(coordinate42) && !Double.isNaN(3.0) ? (coordinate42 > 3.0 ? 1 : (coordinate42 == 3.0 ? 0 : -1)) : -1) > 0) {
                                    double coordinate43 = vec22.getCoordinate4(vec23);
                                    if ((!Double.isNaN(coordinate43) && !Double.isNaN(offset110) ? (coordinate43 > offset110 ? 1 : (coordinate43 == offset110 ? 0 : -1)) : 1) < 0) {
                                        float scaleFactor = minecraft.thePlayer.getDistanceToEntity(livingEntity);
                                        if ((!Float.isNaN(scaleFactor) && !Float.isNaN(3.0F) ? (scaleFactor > 3.0F ? 1 : (scaleFactor == 3.0F ? 0 : -1)) : -1) > 0) {
                                            StringBuilder stringBuilder = new StringBuilder();
                                            String text = RuntimeBridge.formatNumberString(vec19.getCoordinate4(vec20), 4);
                                            StringBuilder sb = stringBuilder.append(text).append("  ");
                                            String text2 = RuntimeBridge.formatNumberString(vec17.getCoordinate4(vec18), 4);
                                            StringBuilder sb2 = sb.append(text2).append("  ");
                                            String text3 = RuntimeBridge.formatNumberString(vec22.getCoordinate4(vec23), 4);
                                            String text4 = sb2.append(text3).toString();
                                            RayTraceUtil.applyString(text4);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    boolean enabled3 = false;
                    boolean completed = false;
                    double hurtTime2 = (double)livingEntity.hurtTime;
                    double value2 = this.settingMaxTargetHurttime.getValue();
                    if ((!Double.isNaN(hurtTime2) && !Double.isNaN(value2) ? (hurtTime2 > value2 ? 1 : (hurtTime2 == value2 ? 0 : -1)) : 1) <= 0) {
                        double coordinate44 = vec19.getCoordinate4(vec20);
                        if ((!Double.isNaN(coordinate44) && !Double.isNaN(3.0) ? (coordinate44 > 3.0 ? 1 : (coordinate44 == 3.0 ? 0 : -1)) : -1) > 0
                        || !this.settingAccurate.isEnabled()) {
                            double coordinate45 = vec17.getCoordinate4(vec18);
                            if ((!Double.isNaN(coordinate45) && !Double.isNaN(3.0) ? (coordinate45 > 3.0 ? 1 : (coordinate45 == 3.0 ? 0 : -1)) : -1) > 0) {
                                double coordinate46 = vec22.getCoordinate4(vec23);
                                if ((!Double.isNaN(coordinate46) && !Double.isNaN(offset110) ? (coordinate46 > offset110 ? 1 : (coordinate46 == offset110 ? 0 : -1)) : 1) < 0) {
                                    float scaleFactor2 = minecraft.thePlayer.getDistanceToEntity(livingEntity);
                                    if ((!Float.isNaN(scaleFactor2) && !Float.isNaN(3.0F) ? (scaleFactor2 > 3.0F ? 1 : (scaleFactor2 == 3.0F ? 0 : -1)) : -1) > 0) {
                                        enabled3 = true;
                                        completed = true;
                                    }
                                }
                            }
                        }
                    }
                    if (!completed) {
                        enabled3 = false;
                    }


                    if (!enabled3) {
                        axisAlignedBB2 = null;
                    }

                    this.boundingBox = axisAlignedBB2;
                    return enabled3 & true;
                }

                double offset111 = vec.xCoord - xCoord;
                double offset112 = (double)Math.max(1, indexOffset23);
                double offset113 = offset111 / offset112;
                xCoord += offset113;
                double offset114 = vec.yCoord - yCoord;
                double offset115 = (double)Math.max(1, indexOffset23);
                double offset116 = offset114 / offset115;
                yCoord += offset116;
                double offset117 = vec.zCoord - zCoord;
                double offset118 = (double)Math.max(1, indexOffset23);
                double offset119 = offset117 / offset118;
                zCoord += offset119;
                indexOffset23 += -1;
                index4++;
            }
        } else {
            return false;
        }
    }

    private void processChar() {
        this.tickDelay = 0;
        this.timer2.reset();
    }

    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    private boolean isTargetsVisible() {
        return !this.settingOnlyWhileKillAura.isEnabled();
    }

    public boolean checkCondition() {
        return !this.isToggled() ? false : this.boundingBox != null;
    }

    private EntityLivingBase getEntityLivingBase() {
        EntityLivingBase entityLivingBase = ChatUtil.getEntityLivingBase();
        if (!this.settingOnlyWhileKillAura.isEnabled() && entityLivingBase == null) {
            double offset = 10000.0;

            for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                if (this.checkEntity(entity) && offset > (double)minecraft.thePlayer.getDistanceSqToEntity(entity)) {
                    entityLivingBase = (EntityLivingBase)entity;
                    offset = (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
                }
            }

            return entityLivingBase;
        } else {
            return entityLivingBase;
        }
    }

    private boolean checkEntity(Entity entity) {
        if (entity instanceof EntityPlayerSP) {
            return false;
        } else {
            return !ChatUtil.checkEntity3(
            entity,
            LagRangeTargetEntity.PLAYER.isSelected(),
            LagRangeTargetEntity.MOB.isSelected(),
            LagRangeTargetEntity.ANIMAL.isSelected(),
            LagRangeTargetEntity.VILLAGER.isSelected(),
            LagRangeTargetEntity.ARMOR_STAND.isSelected(),
            true,
            true,
            true,
            
            true,
            true,
            true
            )
            ? false
            : entity != minecraft.thePlayer
            && (double)minecraft.thePlayer.getDistanceSqToEntity(entity) < this.settingPreAimRange.getValue();
        }
    }

    private boolean isLagTicksVisible() {
        return !this.settingDynamicLagTicks.isEnabled();
    }

    private boolean isPreAimRangeVisible() {
        return !this.settingOnlyWhileKillAura.isEnabled();
    }
}
