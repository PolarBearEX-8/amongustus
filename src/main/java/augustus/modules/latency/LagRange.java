package augustus.modules.latency;

import augustus.custom.RuntimeBridge;
import client.ClientGlobals;
import client.event.EventMouseClick;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.MouseClickListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderManager;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.LagRangeNothingType;
import client.setting.LagRangeTargetEntity;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatUtil;
import client.util.HitBoxUtils;
import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class LagRange extends Module implements MouseClickListener, PacketListener, MoveListener, RenderListener, TickListener {
    public NumberSetting settingPredictTicks;
    public NumberSetting settingTicks;
    public BooleanSetting settingCorrect;
    public BooleanSetting settingOnlyOnGround;
    public BooleanSetting settingStop;
    public BooleanSetting settingSTap;
    public NumberSetting settingDelayAfterAttack;
    public NumberSetting settingDelayBetweenLags;
    public NumberSetting settingStopEarly;
    public SettingGroup settingTargets;
    public BooleanSetting settingOnlyWhileKillAura;
    public MultiSelectSetting settingTargets2;
    public NumberSetting settingPreAimRange;
    public SettingGroup settingDebug;
    public BooleanSetting settingDebug2;
    public BooleanSetting settingOnlyInThirdPerson;
    public NumberSetting settingTimeDebugGetsDisplayed;
    public long timestampMillis5;
    public AxisAlignedBB boundingBox;
    public AxisAlignedBB boundingBox2;
    public Vec3 positionVec;
    public Vec3 positionVec2;
    public int tickDelay1;
    public int tickDelay3;
    public int tickDelay2;
    public boolean enabled2;
    public boolean expanded2;
    public LagRangeNothingType lagRangeNothingType;
    public int tickDelay;
    public int keyCode2;
    public Vec3 positionVec3;
    public long timestampMillis4;
    public long timestampMillis;

    public LagRange() {
        super("LagRange", "LagDistanz", new Color(23, 233, 123), Category.LATENCY);
        this.settingPredictTicks = new NumberSetting(this, "PredictTicks", 5.0, 1.0, 7.0, 0);
        this.settingTicks = new NumberSetting(this, "Ticks", 3.0, 1.0, 7.0, 0);
        this.settingCorrect = new BooleanSetting(this, "Correct", false);
        this.settingOnlyOnGround = new BooleanSetting(this, "OnlyOnGround", false);
        this.settingStop = new BooleanSetting(this, "Stop", false);
        this.settingSTap = (BooleanSetting)new BooleanSetting(this, "STap", false).getSetting(() -> this.settingStop.isEnabled());
        this.settingDelayAfterAttack = new NumberSetting(this, "Delay after attack", 0.0, 0.0, 10000.0, 0);
        this.settingDelayBetweenLags = new NumberSetting(this, "Delay between lags", 0.0, 0.0, 10000.0, 0);
        this.settingStopEarly = new NumberSetting(this, "Stop early", 0.0, 0.0, 3.0, 0);
        this.settingTargets = new SettingGroup(this, "Targets");
        this.settingOnlyWhileKillAura = new BooleanSetting(this, "OnlyWhileKillAura", true, this.settingTargets);
        LagRangeTargetEntity[] lagRangeTargetEntityArray = LagRangeTargetEntity.values();
        this.settingTargets2 = new MultiSelectSetting(this, "Targets", lagRangeTargetEntityArray, this.settingTargets).getMultiSelectSetting(() -> this.isTargetsVisible());
        this.settingPreAimRange = new NumberSetting(this, "PreAimRange", 4.0, 0.0, 15.0, 1, this.settingTargets).getNumberSetting(() -> this.isPreAimRangeVisible());
        this.settingDebug = new SettingGroup(this, "Debug");
        this.settingDebug2 = new BooleanSetting(this, "Debug", false, this.settingDebug);
        this.settingOnlyInThirdPerson = (BooleanSetting)new BooleanSetting(this, "OnlyInThirdPerson", true, this.settingDebug).getSetting(() -> this.settingDebug2.isEnabled());
        this.settingTimeDebugGetsDisplayed = new NumberSetting(this, "Time debug gets displayed", 1.0, 0.1, 10.0, 1, this.settingDebug).getNumberSetting(() -> this.settingDebug2.isEnabled());
        this.timestampMillis5 = 0L;
        this.tickDelay1 = 0;
        this.tickDelay3 = 0;
        this.tickDelay2 = 0;
        this.enabled2 = true;
        this.expanded2 = false;
        this.lagRangeNothingType = LagRangeNothingType.NOTHING;
        this.tickDelay = 0;
        this.positionVec3 = (Vec3)null;
        this.timestampMillis4 = 0L;
        this.timestampMillis = 0L;
    }

    @Override
    public void onEnable() {
        this.registerEvent(16);
        this.registerEvent(26);
        this.registerEvent(21);
        this.registerEvent(32);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.lagRangeNothingType = LagRangeNothingType.NOTHING;
        this.tickDelay2 = 0;
        this.keyCode2 = 0;
        this.tickDelay = 0;
    }

    @Override
    public void onMouseClick(EventMouseClick event) {
        boolean enabled = this.settingCorrect.isEnabled();
        event.processBoolean(enabled);
        int index = event.getIndex();
        EntityLivingBase entityLivingBase = this.getEntityLivingBase();
        if (this.lagRangeNothingType == LagRangeNothingType.CHARGE2) {
            event.processAction(0);
            event.processAction(0.0F);
            event.setActive(true);
            this.timestampMillis = System.currentTimeMillis();
        }

        boolean enabled3 = false;
        if (index >= 1) {
            if (this.lagRangeNothingType == LagRangeNothingType.NOTHING) {
                if (this.expanded2) {
                    this.expanded2 = false;
                }

                if (index == 1) {
                    double offset = (double)(System.currentTimeMillis() - this.timestampMillis);
                    if (!(offset <= this.settingDelayBetweenLags.getValue())) {
                        int predictTicks = (int)this.settingPredictTicks.getValue();
                        if (this.checkEntityLivingBase2(predictTicks, entityLivingBase)) {
                            this.tickDelay2 = 0;
                            this.lagRangeNothingType = LagRangeNothingType.CHARGE1;
                            this.enabled2 = false;
                        }
                    }
                }
            }

            LagRangeNothingType lagRangeNothingTypeKind = this.lagRangeNothingType;
            LagRangeNothingType lagRangeNothingTypeKind2 = LagRangeNothingType.CHARGE1;
            if (lagRangeNothingTypeKind == lagRangeNothingTypeKind2) {
                this.tickDelay2 += index;
                int tickDelay22 = this.tickDelay2;
                boolean enabled4 = this.checkEntityLivingBase3(tickDelay22, entityLivingBase);
                double tickDelay25 = (double)this.tickDelay2;
                if (!(tickDelay25 < this.settingTicks.getValue()) || enabled4 || this.enabled2) {
                    this.expanded2 = false;
                    if (this.tickDelay2 >= 1 && !this.enabled2) {
                        int tickDelay23 = this.tickDelay2;
                        this.expanded2 = this.checkEntityLivingBase(entityLivingBase, tickDelay23);
                    }

                    this.lagRangeNothingType = LagRangeNothingType.TIMER1;
                }
            }

            LagRangeNothingType lagRangeNothingTypeKind3 = this.lagRangeNothingType;
            LagRangeNothingType lagRangeNothingTypeKind4 = LagRangeNothingType.CHARGE2;
            if (lagRangeNothingTypeKind3 == lagRangeNothingTypeKind4) {
                this.keyCode2 += index;
                if (this.keyCode2 >= this.tickDelay2 || this.enabled2) {
                    if (this.enabled2
                    && ClientGlobals.moduleManager.debug.isToggled()
                    && ClientGlobals.moduleManager.debug.settingLagRangeDebug.isEnabled()) {
                        String text = "Abort " + this.keyCode2;
                        RayTraceUtil.applyString(text);
                    }

                    this.lagRangeNothingType = LagRangeNothingType.TIMER2;
                }
            }

            LagRangeNothingType lagRangeNothingTypeKind5 = this.lagRangeNothingType;
            LagRangeNothingType lagRangeNothingTypeKind6 = LagRangeNothingType.TIMER2;
            if (lagRangeNothingTypeKind5 == lagRangeNothingTypeKind6) {
                int keyCode22 = this.keyCode2;
                event.processAction(keyCode22);
                this.keyCode2 = 0;
                this.lagRangeNothingType = LagRangeNothingType.NOTHING;
                this.tickDelay2 = 0;
                enabled3 = true;
            }

            LagRangeNothingType lagRangeNothingTypeKind7 = this.lagRangeNothingType;
            LagRangeNothingType lagRangeNothingTypeKind8 = LagRangeNothingType.TIMER1;
            if (lagRangeNothingTypeKind7 == lagRangeNothingTypeKind8) {
                int tickDelay24 = this.tickDelay2;
                event.processAction(tickDelay24);
                if (this.expanded2 && this.settingStop.isEnabled()) {
                    this.lagRangeNothingType = LagRangeNothingType.CHARGE2;
                    if (ClientGlobals.moduleManager.debug.isToggled() && ClientGlobals.moduleManager.debug.settingLagRangeDebug.isEnabled()) {
                        StringBuilder sb = new StringBuilder().append("TimerRange ");
                        String text2 = sb.append(minecraft.thePlayer.ticksExisted).toString();
                        RayTraceUtil.applyString(text2);
                    }

                    this.keyCode2 = 0;
                } else {
                    this.lagRangeNothingType = LagRangeNothingType.NOTHING;
                    this.tickDelay2 = 0;
                }

                enabled3 = true;
            }
        }

        LagRangeNothingType lagRangeNothingTypeKind9 = this.lagRangeNothingType;
        LagRangeNothingType lagRangeNothingTypeKind10 = LagRangeNothingType.CHARGE1;
        if (lagRangeNothingTypeKind9 == lagRangeNothingTypeKind10) {
            event.processAction(0);
            event.processAction(0.0F);
            event.setActive(true);
            this.timestampMillis = System.currentTimeMillis();
        }

        if (enabled3) {
            if (event.getIndex() > 1) {
                if (this.settingDebug2.isEnabled()) {
                    this.tickDelay1 = event.getIndex();
                    this.boundingBox = minecraft.thePlayer.getBoundingBox2();
                    this.positionVec = minecraft.thePlayer.getVec32(1.0F);
                }
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if (packet.getPacket() instanceof C02PacketUseEntity) {
            this.timestampMillis4 = System.currentTimeMillis();
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.lagRangeNothingType == LagRangeNothingType.NOTHING) {
            if (this.expanded2) {
                if (this.settingStop.isEnabled()) {
                    if (!this.enabled2) {
                        if (this.settingSTap.isEnabled()) {
                            event.setScaleFactor7(-1.0F);
                            event.setScaleFactor2(0.0F);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity s12PacketEntityVelocity = (S12PacketEntityVelocity)packet2;
            if (minecraft.thePlayer != null && s12PacketEntityVelocity.getEntityId() == minecraft.thePlayer.getEntityId()) {
                this.enabled2 = true;
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.settingDebug2.isEnabled()) {
            if (!((double)System.currentTimeMillis() >= (double)this.timestampMillis5 - this.settingTimeDebugGetsDisplayed.getValue() * 1000.0)) {
                if (minecraft.gameSettings.indexOffset64 != 0 || !this.settingOnlyInThirdPerson.isEnabled()) {
                    RenderManager renderManager = minecraft.getRenderManager();
                    GL11.glPushMatrix();
                    RenderUtils.updateState();
                    GL11.glTranslated(-renderManager.getRenderPosX(), -renderManager.getRenderPosY(), -renderManager.getRenderPosZ());
                    GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 1.0F);
                    RenderUtils.processAction(this.boundingBox);
                    GlStateManager.color4f2(0.0F, 0.0F, 1.0F, 1.0F);
                    RenderUtils.processAction(this.boundingBox2);
                    RenderUtils.prepareScissor();
                    GL11.glPopMatrix();
                }
            }
        }
    }

    @Override
    public void onTick() {
        if (this.settingDebug2.isEnabled()) {
            if (this.tickDelay1 > 0) {
                this.tickDelay3++;
                if (this.tickDelay1 == this.tickDelay3) {
                    this.timestampMillis5 = System.currentTimeMillis();
                    this.boundingBox2 = minecraft.thePlayer.getBoundingBox2();
                    this.positionVec2 = minecraft.thePlayer.getVec32(1.0F);
                    StringBuilder sb = new StringBuilder().append("Teleported ");
                    double coordinate4 = this.positionVec.getCoordinate4(this.positionVec2);
                    String text = sb.append(PlayerUtil.getDouble5(coordinate4, 3)).append(" blocks in ").append(this.tickDelay1).append(" ticks").toString();
                    RayTraceUtil.applyString(text);
                    this.tickDelay1 = 0;
                    this.tickDelay3 = 0;
                }
            }
        } else {
            this.tickDelay1 = 0;
            this.tickDelay3 = 0;
        }
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

    private boolean checkEntityLivingBase(EntityLivingBase livingEntity, int index) {
        if ((double)System.currentTimeMillis() < (double)this.timestampMillis4 - this.settingDelayAfterAttack.getValue()) {
            return false;
        } else if (this.settingOnlyOnGround.isEnabled() && !minecraft.thePlayer.onGround) {
            return false;
        } else if (ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.settingSimple.getSelected().equals("Grim")
        && ClientGlobals.moduleManager.killAura.activeState14) {
            return false;
        } else if (livingEntity.ticksExisted >= 20) {
            Vec3 vec2 = minecraft.thePlayer.getVec35(1.0F);
            Vec3 vec3 = SimulatedPlayer.getVec3(livingEntity, 1.0F);
            if (vec2.getCoordinate4(vec3) > 3.0) {
                ArrayList arrayList = RuntimeBridge.calculateTrajectory(
                minecraft.thePlayer, livingEntity, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index, 136
                );
                AxisAlignedBB axisAlignedBB = HitBoxUtils.getAxisAlignedBB(minecraft.thePlayer.getVec32(1.0F), true);

                for (Object object : arrayList) {
                    Vec3 vec4 = ((Vec3)object).getVec312(0.0, (double)livingEntity.getHeight2(), 0.0);
                    if (SimulatedPlayer.processAction(vec4, axisAlignedBB).getCoordinate4(vec4) <= 3.0) {
                        return false;
                    }
                }

                Vec3 trajectoryPoint = RuntimeBridge.getTrajectoryPoint(arrayList, 999999);
                Vec3 vec5 = RuntimeBridge.getTrajectoryPoint(
                RuntimeBridge.findTargetTrajectories(
                minecraft.thePlayer, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted, minecraft.thePlayer.ticksExisted + index
                ),
                9999
                )
                .getVec312(0.0, (double)minecraft.thePlayer.getHeight2(), 0.0);
                AxisAlignedBB axisAlignedBB2 = HitBoxUtils.getAxisAlignedBB(trajectoryPoint, true);
                Vec3 vec = SimulatedPlayer.processAction(vec5, axisAlignedBB2);
                return vec5.getCoordinate4(vec) <= 3.0;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkEntityLivingBase2(int index2, EntityLivingBase livingEntity) {
        if (this.settingOnlyOnGround.isEnabled() && !minecraft.thePlayer.onGround) {
            return false;
        } else {
            if (livingEntity != null && minecraft.thePlayer.getDistanceToEntity(livingEntity) >= 3.0F) {
                if (ClientGlobals.moduleManager.killAura.isToggled()
                && ClientGlobals.moduleManager.killAura.settingSimple.getSelected().equals("Grim")
                && ClientGlobals.moduleManager.killAura.activeState14) {
                    return false;
                }

                if (livingEntity.ticksExisted >= 20) {
                    for (int i = index2; i >= 1; i--) {
                        if (this.checkEntityLivingBase(livingEntity, index2)) {
                            return true;
                        }
                    }

                    return false;
                }
            }

            return false;
        }
    }

    private boolean checkEntityLivingBase3(int index2, EntityLivingBase livingEntity) {
        if (livingEntity == null) {
            return true;
        } else if (ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.settingSimple.getSelected().equals("Grim")
        && ClientGlobals.moduleManager.killAura.activeState14) {
            return true;
        } else if (livingEntity.ticksExisted < 20) {
            return true;
        } else {
            int index3 = index2 + (int)this.settingStopEarly.getValue();
            int index4 = index3 - 1;
            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(index4, minecraft.thePlayer, livingEntity);
            double offset = coordOffsetArray[3];
            double offset2 = coordOffsetArray[4];
            Vec3 vec = new Vec3(offset, offset2 + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray[5]);
            float height = livingEntity.getHeight();
            float value = livingEntity.width / 2.0F;
            double posX = livingEntity.posX;
            double posY = livingEntity.posY;
            double posZ = livingEntity.posZ;
            int indexOffset23 = livingEntity.indexOffset23;

            for (int i = 0; i < index3 && indexOffset23 > 0; i++) {
                posX += (livingEntity.coordOffset5 - posX) / (double)indexOffset23;
                posY += (livingEntity.coordOffset20 - posY) / (double)indexOffset23;
                posZ += (livingEntity.coordOffset2 - posZ) / (double)indexOffset23;
                indexOffset23--;
            }

            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
            posX - (double)value, posY, posZ - (double)value, posX + (double)value, posY + (double)livingEntity.height, posZ + (double)value
            )
            .offset((double)height, (double)height, (double)height);
            Vec3 vec2 = new Vec3(
            MathHelper.getDouble7(vec.xCoord, axisAlignedBB.minX, axisAlignedBB.maxX),
            MathHelper.getDouble7(vec.yCoord, axisAlignedBB.minY, axisAlignedBB.maxY),
            MathHelper.getDouble7(vec.zCoord, axisAlignedBB.minZ, axisAlignedBB.maxZ)
            );
            if (ClientGlobals.moduleManager.debug.isToggled()) {
                if (ClientGlobals.moduleManager.debug.settingLagRangeDebug.isEnabled()) {
                    StringBuilder sb = new StringBuilder().append(index3 + 1).append("  ").append(vec.getCoordinate4(vec2)).append(" ");
                    String text = sb.append(minecraft.thePlayer.getDouble4(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2))
                    .append(" ")
                    .append(vec.getCoordinate4(vec2) < 3.0)
                    .toString();
                    RayTraceUtil.applyString(text);
                }

                return ((byte)(vec.getCoordinate4(vec2) <= 3.0 ? 1 : 0) & 1) != 0;
            } else {
                return vec.getCoordinate4(vec2) <= 3.0 & true;
            }
        }
    }

    private boolean isTargetsVisible() {
        return !this.settingOnlyWhileKillAura.isEnabled();
    }

    private boolean isPreAimRangeVisible() {
        return !this.settingOnlyWhileKillAura.isEnabled();
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
}
