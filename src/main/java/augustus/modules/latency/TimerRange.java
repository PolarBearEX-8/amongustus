package augustus.modules.latency;

import augustus.modules.combat.KillAura;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventRenderHand;
import client.event.EventRenderHandCallback;
import client.event.EventSendPacket;
import client.event.MotionUpdateListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.module.Category;
import client.module.Module;
import client.module.ModuleManager;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.util.ChatUtil;
import client.util.CombatUtil;
import client.util.Packet;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.BlockCache;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimerRange extends Module implements NetworkPacketListener, MoveListener, MotionUpdateListener, PacketListener, EventRenderHandCallback {
    public long timerDelayMs = 0L;
    public long timestampMillis4;
    public TimeHelper timer = new TimeHelper();
    public TimeHelper timer2 = new TimeHelper();
    public TimeHelper timer3 = new TimeHelper();
    public WorldClient worldClient = null;
    public NumberSetting settingDelay = new NumberSetting(this, "Delay", 0.0, 0.0, 20000.0, 0);
    public NumberSetting settingTimerTicksForward = new NumberSetting(this, "TimerTicksForward", 5.0, 1.0, 10.0, 0);
    public NumberSetting settingTolerance = new NumberSetting(this, "Tolerance", 0.0, -1.0, 1.0, 2);
    public BooleanSetting settingBalanceCheck = new BooleanSetting(this, "BalanceCheck", true);
    public ActionSetting settingAdditionalStop = new ActionSetting(this, "AdditionalStop", 1.0, 0.0, 0.0, 300.0, 0);
    public BooleanSetting settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false);
    public BooleanSetting settingDynamic = new BooleanSetting(this, "Dynamic", true);
    public BooleanSetting settingOnlyKillAura = new BooleanSetting(this, "OnlyKillAura", true);
    public BooleanSetting settingClickCheck = new BooleanSetting(this, "ClickCheck", false);
    public BooleanSetting settingForbiddenSneak = new BooleanSetting(this, "ForbiddenSneak", true);
    public ModeSetting settingFixMode = new ModeSetting(this, "FixMode", "None", new String[]{"None", "Fix1", "Fix2"});
    public int tickDelay3 = 0;
    public long timestampMillis = System.currentTimeMillis();
    public boolean expanded2 = false;
    public int tickDelay2 = 0;
    public int tickDelay1 = 0;
    public int tickDelay = 0;
    public ArrayList itemList3 = new ArrayList();

    public TimerRange() {
        super("TimerRange", "ZeitDistanz", new Color(23, 233, 123), Category.LATENCY);
        this.itemList3.add(new float[]{1.0F, 1.0F});
        this.itemList3.add(new float[]{0.0F, 1.0F});
        this.itemList3.add(new float[]{0.0F, 0.0F});
        this.itemList3.add(new float[]{-1.0F, 0.0F});
        this.itemList3.add(new float[]{-1.0F, -1.0F});
        this.itemList3.add(new float[]{-1.0F, 1.0F});
    }

    @Override
    public void onEnable() {
        this.registerEvent(25);
        this.registerEvent(21);
        this.registerEvent(18);
        this.registerEvent(26);
        this.registerEvent(42);
    }

    @Override
    public void onDisable() {
        this.worldClient = null;
    }

    @Override
    public void onPreDisable() {
        this.timerDelayMs = -1L;
        this.timestampMillis4 = System.currentTimeMillis();
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
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C06PacketPlayerPosLook) {
            this.timestampMillis = System.currentTimeMillis();
        }

        if (packet2 instanceof C02PacketUseEntity) {
            long time2 = System.currentTimeMillis() - this.timestampMillis;
            if (time2 < 23L && time2 > 0L) {
                this.tickDelay3++;
                if (this.tickDelay3 <= 10) {
                    return;
                }

                this.tickDelay3 = 10;
                return;
            }

            if (this.tickDelay3 <= 0) {
                return;
            }

            this.tickDelay3--;
        }
    }

    @Override
    public void onRenderHand(EventRenderHand event) {
        this.expanded2 = false;
        boolean enabled;
        if (ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.settingSimple.getSelected().equals("Grim")
        && ClientGlobals.moduleManager.killAura.activeState14) {
            enabled = this.settingBalanceCheck.isEnabled();
        } else {
            if (!BlockCache.canExecute()
            && minecraft.isIntegratedServerRunning()
            && minecraft.theWorld != null
            && minecraft.thePlayer != null
            && !ClientGlobals.moduleManager.airStuck.isToggled()
            && (!ClientGlobals.moduleManager.bugUp.activeState1 || !ClientGlobals.moduleManager.bugUp.isToggled())
            && (!this.settingForbiddenSneak.isEnabled() || !minecraft.thePlayer.isSprinting())
            && (!this.settingClickCheck.isEnabled() || minecraft.gameSettings.keyBindAttack.isKeyDown())
            && (!this.settingOnlyOnGround.isEnabled() || minecraft.thePlayer.onGround)
            && this.timer2.hasReached((long)this.settingDelay.getValue())
            && (
            ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null
            || ClientGlobals.moduleManager.aimBot.isToggled() && ClientGlobals.moduleManager.aimBot.target != null
            || !this.settingOnlyKillAura.isEnabled()
            )) {
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
                                float scaleFactor1 = minecraft.thePlayer.secondaryFloatEntry.moveStrafe;
                                float[] factorArray = new float[i];
                                float[] factorArray2 = new float[i];

                                for (int i3 = 0; i3 < i; i3++) {
                                    factorArray[i3] = scaleFactor1;
                                }

                                int index = -1337;
                                double offset4 = 0.0;
                                int index2 = 0;

                                int index3;
                                while (true) {
                                    if (index2 >= this.itemList3.size()) {
                                        index3 = index;
                                        break;
                                    }

                                    int index4 = 0;

                                    while (index4 < i) {
                                        int length = ((float[])this.itemList3.get(index2)).length;
                                        if (index4 >= length) {
                                            factorArray2[index4] = minecraft.thePlayer.secondaryFloatEntry.moveForward;
                                            index4++;
                                        } else {
                                            factorArray2[index4] = minecraft.thePlayer.secondaryFloatEntry.moveForward * ((float[])this.itemList3.get(index2))[index4];
                                            index4++;
                                        }
                                    }

                                    int index5 = i - 1;
                                    double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(Math.max(0, index5), minecraft.thePlayer, factorArray2, factorArray);
                                    double offset5 = minecraft.thePlayer.getPositionVec8().xCoord
                                    + (coordOffsetArray[0] - minecraft.thePlayer.getPositionVec8().xCoord) / 3.0;
                                    double offset6 = minecraft.thePlayer.getPositionVec8().yCoord
                                    + (coordOffsetArray[1] - minecraft.thePlayer.getPositionVec8().yCoord) / 3.0;
                                    double offset7 = minecraft.thePlayer.getPositionVec8().zCoord
                                    + (coordOffsetArray[2] - minecraft.thePlayer.getPositionVec8().zCoord) / 3.0;
                                    Vec3 vec = new Vec3(coordOffsetArray[3], coordOffsetArray[4] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray[5]);
                                    Vec3 vec2 = new Vec3(
                                    MathHelper.getDouble7(vec.xCoord, axisAlignedBB.minX, axisAlignedBB.maxX),
                                    MathHelper.getDouble7(vec.yCoord, axisAlignedBB.minY, axisAlignedBB.maxY),
                                    MathHelper.getDouble7(vec.zCoord, axisAlignedBB.minZ, axisAlignedBB.maxZ)
                                    );
                                    AxisAlignedBB axisAlignedBB2 = abstractClientPlayerAdapter.getBoundingBox2().offset((double)height2, (double)height2, (double)height2);
                                    Vec3 vec3 = minecraft.thePlayer.getVec35(1.0F);
                                    Vec3 vec4 = new Vec3(
                                    MathHelper.getDouble7(vec3.xCoord, axisAlignedBB2.minX, axisAlignedBB2.maxX),
                                    MathHelper.getDouble7(vec3.yCoord, axisAlignedBB2.minY, axisAlignedBB2.maxY),
                                    MathHelper.getDouble7(vec3.zCoord, axisAlignedBB2.minZ, axisAlignedBB2.maxZ)
                                    );
                                    Vec3 vec5 = new Vec3(abstractClientPlayerAdapter.coordOffset3, abstractClientPlayerAdapter.coordOffset1 + (double)abstractClientPlayerAdapter.getHeight2(), abstractClientPlayerAdapter.coordOffset4);
                                    double offset8;
                                    if (ClientGlobals.moduleManager.killAura.isToggled() && ClientGlobals.moduleManager.killAura.target != null
                                    )
                                    {
                                        KillAura killAura = ClientGlobals.moduleManager.killAura;
                                        offset8 = killAura.reachDistance;
                                    } else {
                                        ModuleManager moduleManager = ClientGlobals.moduleManager;
                                        if (!moduleManager.range.checkCondition()) {
                                            offset8 = 3.0;
                                        } else {
                                            offset8 = ClientGlobals.moduleManager.range.getCoordinate();
                                        }
                                    }

                                    AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(
                                    offset5 - (double)value,
                                    offset6,
                                    offset7 - (double)value,
                                    offset5 + (double)value,
                                    offset6 + (double)height,
                                    offset7 + (double)value
                                    )
                                    .offset((double)height2, (double)height2, (double)height2);
                                    Vec3 vec6 = new Vec3(
                                    MathHelper.getDouble7(vec5.xCoord, axisAlignedBB3.minX, axisAlignedBB3.maxX),
                                    MathHelper.getDouble7(vec5.yCoord, axisAlignedBB3.minY, axisAlignedBB3.maxY),
                                    MathHelper.getDouble7(vec5.zCoord, axisAlignedBB3.minZ, axisAlignedBB3.maxZ)
                                    );
                                    boolean enabled2 = false;
                                    if (vec.getCoordinate4(vec2) < offset8
                                    && vec5.getCoordinate4(vec6) > 3.0 - this.settingTolerance.getValue()
                                    && vec3.getCoordinate4(vec4) > 3.0) {
                                        if (index2 == 0) {
                                            enabled2 = true;
                                        }

                                        if (event.getIndex() == 1) {
                                            double coordinate4 = vec5.getCoordinate4(vec6);
                                            if (coordinate4 > offset4) {
                                                offset4 = coordinate4;
                                                index = index2;
                                                vec5.getCoordinate4(vec6);
                                                vec.getCoordinate4(vec2);
                                            }
                                        }
                                    }

                                    if (!enabled2 || i <= 2) {
                                        index3 = index;
                                        break;
                                    }

                                    index2++;
                                }

                                if (index3 >= 0) {
                                    this.tickDelay2 = index;
                                    event.processAction((float)i);
                                    this.tickDelay = SettingContainer.settingProvider
                                    .getInt(
                                    
                                    this.settingAdditionalStop.getDouble3(),
                                    this.settingAdditionalStop.getDouble6()
                                    );
                                    if (ClientGlobals.moduleManager.debug.isToggled()
                                    && ClientGlobals.moduleManager.debug.settingTimerRangeDebug.isEnabled()) {
                                        ClientGlobals.moduleManager.debug.applyString6((String)null);
                                    }

                                    this.expanded2 = this.settingFixMode.getSelected().equals("Fix2");
                                    this.timer.reset();
                                    this.timer2.reset();
                                    this.tickDelay1 = 0;
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

            enabled = this.settingBalanceCheck.isEnabled();
        }

        if (enabled) {
            long settingZeitDistanz2 = this.timerDelayMs;
            if ((this.timerDelayMs == 0L ? 0 : (settingZeitDistanz2 < 0L ? -1 : 1)) > 0) {
                if (!this.timer.hasReached(this.timerDelayMs + (long)this.tickDelay)) {
                    event.processAction(0.0F);
                    this.expanded2 = true;
                }
            }
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (this.worldClient == null || this.worldClient != minecraft.theWorld) {
            this.timer3.reset();
        }

        if (!this.timer3.hasReached(500L)) {
            this.timerDelayMs = -1L;
            this.timestampMillis4 = System.currentTimeMillis();
        }

        this.timer.reset();
        long time2 = System.currentTimeMillis() - this.timestampMillis4;
        this.timerDelayMs += (time2 - 50L) * -1L;
        this.timestampMillis4 = System.currentTimeMillis();
        this.timerDelayMs = Math.max(-100L, this.timerDelayMs);
        this.worldClient = minecraft.theWorld;
    }

    @Override
    public void onMove(EventMove event) {
        if (this.tickDelay1 < 2) {
            event.setScaleFactor7(((float[])this.itemList3.get(this.tickDelay2))[this.tickDelay1] * event.getScaleFactor7());
        }

        this.tickDelay1++;
    }
}
