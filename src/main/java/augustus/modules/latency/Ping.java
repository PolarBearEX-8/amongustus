package augustus.modules.latency;

import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.ClickListener;
import client.event.EventAttackEntity;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventRenderEntity;
import client.event.EventSendPacket;
import client.event.EventWorldLoad;
import client.event.MotionUpdateListener;
import client.event.NetworkPacketListener;
import client.event.RenderEntityListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.network.LatencyPacket;
import client.network.StateUpdateListener;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.PingTargetEntity;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.setting.TargetEntityType;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.DisablerCondition;
import client.util.InstantManager;
import client.util.MoveUtil;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.RunnableTask;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class Ping
extends Module
implements NetworkPacketListener,
WorldLoadListener,
AttackEntityListener,
RenderListener,
RenderEntityListener,
TickListener,
MotionUpdateListener,
ClickListener,
StateUpdateListener {
    public SettingGroup settingIncomingGroup;
    public InstantManager incomingPacketBuffer;
    public SettingGroup settingOutgoingGroup;
    public InstantManager outgoingPacketBuffer;
    public BooleanSetting settingCombatMode;
    public RangeSetting settingPingStartDelay;
    public NumberSetting settingResetPingAtDistanceToTarget;
    public BooleanSetting settingFailRate;
    public RangeSetting settingFailInterval;
    public MultiSelectSetting settingTargets;
    public SettingGroup settingFlushConditionsGroup;
    public MultiSelectSetting settingFlushConditions;
    public SettingGroup settingTargetESPs;
    public ModeSetting settingTargetESP;
    public ColorSetting settingClassicColorTarget;
    public NumberSetting settingLineWidthTarget;
    public BooleanSetting settingSmoothRendering;
    public ShaderOptions settingTarget;
    public MultiSelectSetting settingRenderTargets;
    public SettingGroup settingPlayerESPs;
    public ModeSetting settingPlayerESP;
    public ColorSetting settingClassicColorMe;
    public NumberSetting settingLineWidthMe;
    public ShaderOptions settingPlayer;
    public BooleanSetting settingOnlyInThirdPerson;
    public TimeHelper timer;
    public ArrayList itemList3;
    public boolean expanded2;
    public TimeHelper timer2;
    public boolean activeState;
    public int tickDelay;
    public EntityLivingBase targetEntity;
    public TimeHelper timer3;
    public AtomicLong atomicLong;
    public AtomicBoolean atomicBoolean;
    public boolean enabled2;

    public Ping() {
        super("Ping", "Verzögerung", new Color(235, 64, 52), Category.LATENCY);
        this.settingIncomingGroup = new SettingGroup(this, "IncomingGroup");
        this.incomingPacketBuffer = new InstantManager(this, "Incoming", this.settingIncomingGroup);
        this.settingOutgoingGroup = new SettingGroup(this, "OutgoingGroup");
        this.outgoingPacketBuffer = new InstantManager(this, "Outgoing", this.settingOutgoingGroup);
        this.settingCombatMode = new BooleanSetting(this, "CombatMode", false, this.settingOutgoingGroup);
        SettingGroup settingOutgoingGroup2 = this.settingOutgoingGroup;
        this.settingPingStartDelay = new RangeSetting(this, "PingStartDelay", 100.0, 150.0, 0.0, 1000.0, 0, settingOutgoingGroup2)
        .getRangeSetting2().getRangeSetting(15343, () -> this.settingCombatMode.isEnabled());
        this.settingResetPingAtDistanceToTarget = new NumberSetting(this, "ResetPingAtDistanceToTarget", 3.05, 3.0, 4.0, 2, this.settingOutgoingGroup).getNumberSetting(() -> this.settingCombatMode.isEnabled());
        Setting setting = new BooleanSetting(this, "FailRate", false, this.settingOutgoingGroup).getSetting(() -> this.settingCombatMode.isEnabled());
        this.settingFailRate = (BooleanSetting)setting;
        SettingGroup settingOutgoingGroup3 = this.settingOutgoingGroup;
        this.settingFailInterval = new RangeSetting(this, "FailInterval", 3.0, 4.0, 0.0, 10.0, 0, settingOutgoingGroup3).getRangeSetting2().getRangeSetting(15343, () -> this.isFailIntervalVisible());
        TargetEntityType[] auxiliaryPingTargetEntityArray = TargetEntityType.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", auxiliaryPingTargetEntityArray, this.settingOutgoingGroup).getMultiSelectSetting(() -> this.settingCombatMode.isEnabled());
        SettingGroup settingGroup3 = new SettingGroup(this, "FlushConditionsGroup");
        String text = "FlushConditions";
        this.settingFlushConditionsGroup = (SettingGroup)settingGroup3.getSetting(text);
        DisablerCondition[] disablerConditionArray = DisablerCondition.values();
        this.settingFlushConditions = new MultiSelectSetting(this, "FlushConditions", disablerConditionArray, this.settingFlushConditionsGroup);
        this.settingTargetESPs = new SettingGroup(this, "TargetESPs");
        String[] textArray = new String[4];
        textArray[0] = "None";
        textArray[1] = "Classic";
        textArray[2] = "PlayerModel";
        textArray[3] = "PlayerModelArmor";
        this.settingTargetESP = new ModeSetting(this, "TargetESP", "Classic", textArray, this.settingTargetESPs);
        Color color = new Color(0, 255, 0, 0);
        ColorSetting colorSetting = new ColorSetting(this, "ClassicColorTarget", color, this.settingTargetESPs);
        String text2 = "ClassicColor";
        Setting setting3 = colorSetting.getSetting(text2);
        this.settingClassicColorTarget = (ColorSetting)(((ColorSetting)setting3).getSetting(() -> this.isClassicColorTargetVisible()));
        NumberSetting numberSetting3 = new NumberSetting(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.settingTargetESPs);
        String text3 = "LineWidth";
        this.settingLineWidthTarget = numberSetting3.getNumberSetting(text3).getNumberSetting(() -> this.isLineWidthTargetVisible());
        this.settingSmoothRendering = (BooleanSetting)new BooleanSetting(this, "SmoothRendering", false, this.settingTargetESPs).getSetting(() -> this.isSmoothRenderingVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this, "Target", this.settingTargetESPs);
        this.settingTarget = ((ShaderOptions)shaderOptions.getSetting(() -> this.isTargetVisible())).getShaderOptions5();
        PingTargetEntity[] pingTargetEntityArray = PingTargetEntity.values();
        MultiSelectSetting multiSelectSetting4 = new MultiSelectSetting(this, "RenderTargets", pingTargetEntityArray, this.settingTargetESPs);
        String text4 = "Targets";
        this.settingRenderTargets = multiSelectSetting4.getMultiSelectSetting(text4).getMultiSelectSetting(() -> this.isRenderTargetsVisible());
        this.settingPlayerESPs = new SettingGroup(this, "PlayerESPs");
        String[] textArray2 = new String[4];
        textArray2[0] = "None";
        textArray2[1] = "Classic";
        textArray2[2] = "PlayerModel";
        textArray2[3] = "PlayerModelArmor";
        this.settingPlayerESP = new ModeSetting(this, "PlayerESP", "Classic", textArray2, this.settingPlayerESPs);
        Color color2 = new Color(0, 255, 0, 0);
        ColorSetting colorSetting2 = new ColorSetting(this, "ClassicColorMe", color2, this.settingPlayerESPs);
        String text5 = "ClassicColor";
        Setting setting6 = colorSetting2.getSetting(text5);
        this.settingClassicColorMe = (ColorSetting)(((ColorSetting)setting6).getSetting(() -> this.isClassicColorMeVisible()));
        NumberSetting numberSetting6 = new NumberSetting(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.settingPlayerESPs);
        String text6 = "LineWidth";
        this.settingLineWidthMe = numberSetting6.getNumberSetting(text6).getNumberSetting(() -> this.isLineWidthMeVisible());
        ShaderOptions shaderOptions2 = new ShaderOptions(this, "Player", this.settingPlayerESPs);
        this.settingPlayer = ((ShaderOptions)shaderOptions2.getSetting(() -> this.isPlayerVisible())).getShaderOptions5();
        this.settingOnlyInThirdPerson = new BooleanSetting(this, "OnlyInThirdPerson", true, this.settingPlayerESPs);
        this.timer = new TimeHelper();
        this.itemList3 = new ArrayList();
        this.expanded2 = false;
        this.timer2 = new TimeHelper();
        this.activeState = false;
        this.tickDelay = 0;
        this.targetEntity = (EntityLivingBase)null;
        this.timer3 = new TimeHelper();
        long time = System.currentTimeMillis();
        this.atomicLong = new AtomicLong(time);
        this.atomicBoolean = new AtomicBoolean(false);
        this.enabled2 = false;
        DisablerCondition[] disablerConditionArray2 = DisablerCondition.values();
        int length = Array.getLength(disablerConditionArray2);
        Ping ping = this;

        for (int i = 0; i < length; i++) {
            Object object = disablerConditionArray2[i];
            StringBuilder sb = new StringBuilder().append("Group");
            String text7 = ((DisablerCondition)object).getText();
            String text8 = sb.append(text7).toString();
            SettingGroup settingGroup6 = new SettingGroup(ping, text8, ping.settingFlushConditionsGroup);
            String text9 = ((DisablerCondition)object).getText();
            SettingGroup settingGroup8 = ((SettingGroup)settingGroup6.getSetting(text9)).getSettingGroup(() -> ((DisablerCondition)object).isSelected());
            StringBuilder sb2 = new StringBuilder().append("FlushDuration");
            String text10 = ((DisablerCondition)object).getText();
            String text11 = sb2.append(text10).toString();
            NumberSetting numberSetting9 = new NumberSetting(ping, text11, 200.0, 20.0, 1000.0, 0, settingGroup8);
            String text12 = "FlushCondition";
            NumberSetting numberSetting10 = numberSetting9.getNumberSetting(text12);
            DisablerCondition.getNumberSetting((DisablerCondition)object, numberSetting10);
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(25);
        this.registerEvent(32);
        this.registerEvent(31);
        this.registerEvent(40);
        this.registerEvent(18);
        this.registerEvent(7);
        this.registerEvent(61);
        this.registerEvent(1);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        RayTraceUtil.applyString("Pong");
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        RayTraceUtil.applyString("Ping");
        this.expanded2 = false;
        this.outgoingPacketBuffer.updateState();
        this.incomingPacketBuffer.updateState();
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        this.handlePacket(packet.getPacket());
        if (this.canExecute5()) {
            packet.setIndexOffset(this.incomingPacketBuffer.getInt2());
        } else {
            int intEntry = this.incomingPacketBuffer.getInt();
            if (intEntry > 0) {
                packet.setIndexOffset(intEntry);
            }
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (DisablerCondition.HOLDING_BLOCKS.isSelected()) {
            Minecraft mc = minecraft;
            if (mc.thePlayer == null) {
                return;
            }

            if (minecraft.thePlayer.getHeldItem() == null) {
                return;
            }

            if (minecraft.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
                DisablerCondition.getTime(DisablerCondition.HOLDING_BLOCKS).reset();
            }
        }
    }

    @Override
    public void onRenderEntity(EventRenderEntity event) {
        EntityLivingBase targetEntity2 = event.getTargetEntity();
        if (ClientGlobals.moduleManager.packetHandler.isEnabled2() && this.checkEntity3(targetEntity2)) {
            ModeSetting settingTargetESP2 = this.settingTargetESP;
            if (this.checkModeSetting(settingTargetESP2)) {
                boolean enabled = this.settingTargetESP.getSelected().equals("PlayerModelArmor");
                this.onRenderEntity4(event, targetEntity2, enabled);
            }
        }

        if (minecraft.thePlayer != null) {
            if (!ClientGlobals.moduleManager.packetHandler.canExecute5()) {
                return;
            }

            if (!minecraft.thePlayer.equals(event.getTargetEntity())) {
                return;
            }

            ModeSetting settingPlayerESP2 = this.settingPlayerESP;
            if (!this.checkModeSetting(settingPlayerESP2)) {
                return;
            }

            if (minecraft.gameSettings.indexOffset64 == 0 && this.settingOnlyInThirdPerson.isEnabled()) {
                return;
            }

            Vec3 positionVec8 = minecraft.thePlayer.getPositionVec8();
            boolean enabled3 = this.settingPlayerESP.getSelected().equals("PlayerModelArmor");
            this.onRenderEntity2(event, positionVec8, enabled3);
        }
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (minecraft.theWorld != null) {
            if (!this.settingCombatMode.isEnabled()) {
                return;
            }

            if (this.outgoingPacketBuffer.getDouble() <= 0.0) {
                return;
            }

            if (event.getEntity() instanceof EntityLivingBase) {
                this.targetEntity = (EntityLivingBase)event.getEntity();
                this.timer3.reset();
            }
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        this.handlePacket2(packet.getPacket());
        if ((
        !this.settingCombatMode.isEnabled()
        || !this.expanded2 && this.timer2.hasReached((long)this.settingPingStartDelay.getIndexOffset2())
        )
        && !this.canExecute5()) {
            int intEntry = this.outgoingPacketBuffer.getInt();
            if (intEntry > 0) {
                this.atomicLong.set(System.currentTimeMillis());
                this.atomicBoolean.set(false);
                this.enabled2 = false;
                packet.setIndexOffset2(intEntry);
                this.activeState = true;
            }
        } else {
            packet.setIndexOffset2(this.outgoingPacketBuffer.getInt2());
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (DisablerCondition.checkDisablerCondition(DisablerCondition.SPRINT_RESET) && minecraft.thePlayer.isRiding() != minecraft.thePlayer.isInWeb2()) {
            DisablerCondition.getTime(DisablerCondition.SPRINT_RESET).reset();
        }

        if (DisablerCondition.checkDisablerCondition(DisablerCondition.SPLASH_POTION)) {
            minecraft.theWorld.loadedEntityList.stream().filter(object -> checkEntity2((Entity)object)).forEach(object2 -> applyEntity2((Entity)object2));
        }

        if (DisablerCondition.USE_ITEM.isSelected() && minecraft.thePlayer.isUsingItem()) {
            DisablerCondition.getTime(DisablerCondition.USE_ITEM).reset();
        }

        if (DisablerCondition.checkDisablerCondition(DisablerCondition.OPEN_GUI) && !minecraft.isIntegratedServerRunning()) {
            DisablerCondition.getTime(DisablerCondition.OPEN_GUI).reset();
        }

        if (minecraft.thePlayer == null) {
            if (DisablerCondition.checkDisablerCondition(DisablerCondition.STANDING_STILL) && !MoveUtil.canExecute()) {
                DisablerCondition.getTime(DisablerCondition.STANDING_STILL).reset();
            }

            if (BlockCache.canExecute() && DisablerCondition.checkDisablerCondition(DisablerCondition.SCAFFOLD_TOGGLED)) {
                DisablerCondition.getTime(DisablerCondition.SCAFFOLD_TOGGLED).reset();
            }
        }

        if (minecraft.getNetHandlerPlayClient() != null) {
            if (minecraft.getNetHandlerPlayClient().getNetworkManager() != null) {
                minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
                minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket6(new LatencyPacket());
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.timer.hasReached(3L)) {
            this.timer.reset();
            minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
            minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket6(new LatencyPacket());
        }

        RenderUtils.updateState();
        if (ClientGlobals.moduleManager.packetHandler.isEnabled2() || ClientGlobals.moduleManager.packetHandler.canExecute5()) {
            if (this.settingTargetESP.getSelected().equals("Classic") && ClientGlobals.moduleManager.packetHandler.isEnabled2()) {
                ShaderOptions settingTarget2 = this.settingTarget;
                ShaderOptions settingTarget3 = this.settingTarget;
                MovementHandler.processBoolean(settingTarget2.canExecute(), settingTarget3.canExecute2());
                RunnableTask runnableTask = renderPass -> this.applyRenderPass2(renderPass);
                MovementHandler.applyRunnableTask(false, runnableTask);
            }

            if (ClientGlobals.moduleManager.packetHandler.canExecute5()
            && this.settingPlayerESP.getSelected().equals("Classic")
            && minecraft.thePlayer != null
            && (minecraft.gameSettings.indexOffset64 != 0 || !this.settingOnlyInThirdPerson.isEnabled())) {
                ShaderOptions settingPlayer2 = this.settingPlayer;
                ShaderOptions settingPlayer3 = this.settingPlayer;
                MovementHandler.processBoolean(settingPlayer2.canExecute(), settingPlayer3.canExecute2());
                MovementHandler.applyRunnableTask(false, renderPass2 -> this.applyRenderPass(renderPass2));
            }
        }

        RenderUtils.prepareScissor();
    }

    @Override
    public void onRunTick() {
        this.itemList3.clear();
        boolean enabled = false;
        if (this.timer3.hasReached(2000L)) {
            this.targetEntity = null;
        }

        if (this.settingFailRate.isEnabled() && this.targetEntity != null) {
            if ((double)minecraft.thePlayer.getDistanceSqToEntity(this.targetEntity) > 4.5) {
                RangeSetting settingFailIntervalProp = this.settingFailInterval;
                if (this.tickDelay >= settingFailIntervalProp.getIndexOffset2()) {
                    this.enabled2 = true;
                }
            }

            boolean completed = false;
            if (this.targetEntity.hurtTime == 10) {
                long time = 0L;
                if (((time = System.currentTimeMillis() - this.atomicLong.get() - 200L) == 0L ? 0 : (time < 0L ? -1 : 1)) > 0 || this.atomicBoolean.get()) {
                    int tickDelay2 = this.tickDelay;
                    RangeSetting settingFailIntervalProp2 = this.settingFailInterval;
                    if (tickDelay2 < settingFailIntervalProp2.getIndexOffset2() || !this.enabled2) {
                        completed = true;
                    }
                }
                if (!completed) {
                    this.tickDelay++;
                    this.atomicBoolean.set(true);
                }
            }

        }

        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (this.checkEntity3(entity)) {
                this.itemList3.add((EntityLivingBase)entity);
            }

            if (this.checkEntity(entity)
            && !(this.outgoingPacketBuffer.getDouble() <= 0.0)
            && this.settingCombatMode.isEnabled()) {
                EntityLivingBase livingEntity = (EntityLivingBase)entity;
                double distance = (double)minecraft.thePlayer.getDistanceToEntity(livingEntity);
                NumberSetting settingResetPingAtDistanceToTarget2 = this.settingResetPingAtDistanceToTarget;
                if (distance < settingResetPingAtDistanceToTarget2.getValue()) {
                    if (!this.expanded2) {
                        this.settingPingStartDelay.getInt2();
                    }

                    this.expanded2 = true;
                    minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket6(new LatencyPacket());
                    this.timer2.reset();
                    enabled = true;
                } else if (minecraft.thePlayer.getDistanceToEntity(livingEntity) < 15.0F) {
                    double lastTickPosX = minecraft.thePlayer.lastTickPosX;
                    Minecraft mc = minecraft;
                    Minecraft mc2 = minecraft;
                    double offset = mc.thePlayer.lastTickPosY + (double)mc2.thePlayer.getHeight2();
                    Minecraft mc3 = minecraft;
                    Vec3 vec3 = new Vec3(lastTickPosX, offset, mc3.thePlayer.lastTickPosZ);
                    float height = livingEntity.getHeight();
                    AxisAlignedBB axisAlignedBB = livingEntity.getBoundingBox2().offset((double)height, (double)height, (double)height);
                    Vec3 vec = SimulatedPlayer.processAction(vec3, axisAlignedBB);
                    boolean enabled3 = distance > vec3.getCoordinate4(vec);
                    float value = minecraft.thePlayer.width / 2.0F;
                    float height2 = minecraft.thePlayer.getHeight();
                    double offset2 = minecraft.thePlayer.getPositionVec8().xCoord - (double)value;
                    double yCoord = minecraft.thePlayer.getPositionVec8().yCoord;
                    double offset3 = minecraft.thePlayer.getPositionVec8().zCoord - (double)value;
                    double offset4 = minecraft.thePlayer.getPositionVec8().xCoord + (double)value;
                    Minecraft mc4 = minecraft;
                    Minecraft mc5 = minecraft;
                    double offset5 = mc4.thePlayer.getPositionVec8().yCoord + (double)mc5.thePlayer.height;
                    Minecraft mc6 = minecraft;
                    AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(offset2, yCoord, offset3, offset4, offset5, mc6.thePlayer.getPositionVec8().zCoord + (double)value)
                    .offset((double)height2, (double)height2, (double)height2);
                    Vec3 vec4 = new Vec3(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2);
                    Vec3 vec2 = SimulatedPlayer.processAction(vec4, axisAlignedBB2);
                    if (enabled3 && !(vec4.getCoordinate4(vec2) >= 5.0)) {
                        this.expanded2 = true;
                        minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket6(new LatencyPacket());
                        enabled = true;
                    }
                }
            }
        }

        if (!enabled) {
            if (this.settingFailRate.isEnabled()) {
                RangeSetting settingFailIntervalProp3 = this.settingFailInterval;
                if (this.tickDelay == settingFailIntervalProp3.getIndexOffset2()) {
                    this.expanded2 = true;
                    return;
                }

                RangeSetting settingFailIntervalProp4 = this.settingFailInterval;
                if (this.tickDelay > settingFailIntervalProp4.getIndexOffset2()) {
                    this.tickDelay = 0;
                    this.settingFailInterval.recalculate();
                }
            }

            this.expanded2 = false;
        }
    }

    @Override
    public void updateState() {
        this.incomingPacketBuffer.updateState2();
        this.outgoingPacketBuffer.updateState2();
    }

    public void onRenderEntity2(EventRenderEntity event, Vec3 vec, boolean enabled) {
        boolean enabled3 = this.settingPlayer.canExecute();
        boolean enabled4 = this.settingPlayer.canExecute2();
        MovementHandler.processBoolean(enabled3, enabled4);
        RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
        RunnableTask runnableTask = renderPass -> onRenderEntity3(event, rendererLivingEntity, vec, enabled, renderPass);
        MovementHandler.applyRunnableTask(false, runnableTask);
    }

    private void applyRenderPass(RenderPass renderPass) {
        Minecraft mc = minecraft;
        NumberSetting settingLineWidthMe2 = this.settingLineWidthMe;
        RenderUtils.applyEntityLivingBase3(mc.thePlayer, this.settingClassicColorMe, (float)settingLineWidthMe2.getValue(), 255, true);
    }

    private void applyRenderPass2(RenderPass renderPass) {
        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            if (!(livingEntity instanceof EntityPlayerSP)) {
                ColorSetting settingClassicColorTarget2 = this.settingClassicColorTarget;
                float lineWidthTarget = (float)this.settingLineWidthTarget.getValue();
                BooleanSetting settingSmoothRendering2 = this.settingSmoothRendering;
                RenderUtils.applyEntityLivingBase3(livingEntity, settingClassicColorTarget2, lineWidthTarget, 255, settingSmoothRendering2.isEnabled());
                boolean enabled = this.settingTarget.canExecute();
                boolean enabled3 = this.settingTarget.canExecute2();
                MovementHandler.processBoolean(enabled, enabled3);
            }
        }
    }

    private boolean checkEntity(Entity entity) {
        if (entity == minecraft.thePlayer) {
            return false;
        } else {
            boolean selected = TargetEntityType.PLAYER.isSelected();
            boolean selected2 = TargetEntityType.MOB.isSelected();
            boolean selected3 = TargetEntityType.ANIMAL.isSelected();
            boolean selected4 = TargetEntityType.VILLAGER.isSelected();
            boolean selected5 = TargetEntityType.ARMOR_STAND.isSelected();
            boolean selected6 = TargetEntityType.INVISIBLE.isSelected();
            boolean enabled = !TargetEntityType.TEAMMATES.isSelected();
            boolean enabled3 = !TargetEntityType.TEAMMATES.isSelected();
            return ChatUtil.checkEntity3(
            entity, selected, selected2, selected3, selected4, selected5, selected6, enabled, enabled3, true, TargetEntityType.DEAD.isSelected(), false
            );
        }
    }

    private static void applyEntity2(Entity entity) {
        double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
        double scaleFactor4 = (double)minecraft.thePlayer.getScaleFactor4(entity);
        if (!(distance >= 2.0)) {
            if (scaleFactor4 < 1.0) {
                DisablerCondition.getTime(DisablerCondition.SPLASH_POTION).reset();
            }
        }
    }

    private void handlePacket(Packet packet) {
        if (DisablerCondition.checkDisablerCondition(DisablerCondition.VELOCITY) && packet instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity s12PacketEntityVelocity = (S12PacketEntityVelocity)packet;
            if (minecraft.thePlayer != null) {
                int entityId = s12PacketEntityVelocity.getEntityId();
                Minecraft mc = minecraft;
                if (entityId == mc.thePlayer.getEntityId()) {
                    DisablerCondition.getTime(DisablerCondition.VELOCITY).reset();
                }
            }
        }

        if (DisablerCondition.checkDisablerCondition(DisablerCondition.EXPLOSION) && packet instanceof S27PacketExplosion) {
            S27PacketExplosion s27PacketExplosion = (S27PacketExplosion)packet;
            if (s27PacketExplosion.getScaleFactor2() != 0.0F || s27PacketExplosion.getScaleFactor3() != 0.0F || s27PacketExplosion.getScaleFactor() != 0.0F) {
                DisablerCondition.getTime(DisablerCondition.EXPLOSION).reset();
            }
        }

        if (DisablerCondition.checkDisablerCondition(DisablerCondition.TELEPORT)) {
            if (packet instanceof S08PacketPlayerPosLook) {
                DisablerCondition.getTime(DisablerCondition.TELEPORT).reset();
            }
        }
    }

    private boolean isTargetVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private static boolean checkEntity2(Entity entity) {
        return entity instanceof EntityThrownpotion;
    }

    private static void onRenderEntity3(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, Vec3 vec, boolean enabled, RenderPass renderPass) {
        Minecraft mc = minecraft;
        double zCoord = vec.zCoord;
        double yCoord = vec.yCoord;
        double xCoord = vec.xCoord;
        EntityPlayerSP player = mc.thePlayer;
        event.applyEntityLivingBase(player, rendererLivingEntity, xCoord, yCoord, zCoord, enabled);
    }

    private boolean isLineWidthTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private boolean checkModeSetting(ModeSetting setting) {
        return setting.getSelected().equals("PlayerModel") || setting.getSelected().equals("PlayerModelArmor");
    }

    public void onRenderEntity4(EventRenderEntity event, EntityLivingBase livingEntity, boolean enabled) {
        double offset;
        double offset2;
        double offset3;
        if (this.settingSmoothRendering.isEnabled() && livingEntity.positionVec3 != null && livingEntity.positionVec != null) {
            double offset4 = livingEntity.positionVec3.xCoord - livingEntity.positionVec.xCoord;
            Minecraft mc = minecraft;
            offset = livingEntity.positionVec.xCoord + offset4 * (double)mc.getTimer().scaleFactor6;
            double offset5 = livingEntity.positionVec3.yCoord - livingEntity.positionVec.yCoord;
            Minecraft mc2 = minecraft;
            offset2 = livingEntity.positionVec.yCoord + offset5 * (double)mc2.getTimer().scaleFactor6;
            double offset6 = livingEntity.positionVec3.zCoord - livingEntity.positionVec.zCoord;
            Minecraft mc3 = minecraft;
            offset3 = livingEntity.positionVec.zCoord + offset6 * (double)mc3.getTimer().scaleFactor6;
        } else {
            Vec3 positionVec5 = livingEntity.positionVec5;
            double offset7 = livingEntity.positionVec4.xCoord - livingEntity.positionVec5.xCoord;
            Minecraft mc4 = minecraft;
            offset = positionVec5.xCoord + offset7 * (double)mc4.getTimer().scaleFactor6;
            double offset8 = livingEntity.positionVec4.yCoord - livingEntity.positionVec5.yCoord;
            Minecraft mc5 = minecraft;
            offset2 = livingEntity.positionVec5.yCoord + offset8 * (double)mc5.getTimer().scaleFactor6;
            double offset9 = livingEntity.positionVec4.zCoord - livingEntity.positionVec5.zCoord;
            Minecraft mc6 = minecraft;
            offset3 = livingEntity.positionVec5.zCoord + offset9 * (double)mc6.getTimer().scaleFactor6;
        }

        boolean enabled3 = this.settingTarget.canExecute();
        boolean enabled4 = this.settingTarget.canExecute2();
        MovementHandler.processBoolean(enabled3, enabled4);
        RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
        double offset10 = offset3;
        double offset11 = offset2;
        double offset12 = offset;
        RunnableTask runnableTask = renderPass -> onRenderEntity5(event, livingEntity, rendererLivingEntity, offset12, offset11, offset10, enabled, renderPass);
        MovementHandler.applyRunnableTask(false, runnableTask);
    }

    private boolean checkEntity3(Entity entity) {
        return entity == minecraft.thePlayer
        ? false
        : ChatUtil.checkEntity3(
        entity,
        PingTargetEntity.PLAYER.isSelected(),
        PingTargetEntity.MOB.isSelected(),
        PingTargetEntity.ANIMAL.isSelected(),
        PingTargetEntity.VILLAGER.isSelected(),
        false,
        PingTargetEntity.INVISIBLE.isSelected(),
        false,
        false,
        
        true,
        PingTargetEntity.DEAD.isSelected(),
        false
        );
    }

    private static void onRenderEntity5(
    EventRenderEntity event, EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, double x, double y, double z, boolean enabled, RenderPass renderPass
    ) {
        event.applyEntityLivingBase(livingEntity, rendererLivingEntity, x, y, z, enabled);
    }

    private void handlePacket2(Packet packet) {
        int index = 0;
        C08PacketPlayerBlockPlacement c08PacketPlayerBlockPlacement = null;
        boolean completed5 = false;
        boolean completed4 = false;
        boolean enabled = false;
        boolean completed3 = false;
        boolean enabled3 = false;
        boolean completed2 = false;
        boolean completed = false;
        boolean enabled4 = false;
        if (this.settingCombatMode.isEnabled()
        && packet instanceof C02PacketUseEntity
        && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
            if (this.activeState) {
                this.activeState = false;
                this.timer2.reset();
            }
            if (!DisablerCondition.checkDisablerCondition(DisablerCondition.ATTACK)) {
                completed = true;
            }
            if (!completed) {
                enabled4 = packet instanceof C02PacketUseEntity;
            }
        } else {
            if (!DisablerCondition.checkDisablerCondition(DisablerCondition.ATTACK)) {
                boolean enabled5 = DisablerCondition.checkDisablerCondition(DisablerCondition.INVENTORY_ACTIONS);
                if (!enabled5) {
                    boolean enabled6 = DisablerCondition.checkDisablerCondition(DisablerCondition.BREAK_BLOCKS);
                    if (!enabled6) {
                        boolean enabled7 = packet instanceof C08PacketPlayerBlockPlacement;
                        if (!enabled7) {
                            return;
                        }
                        if (!DisablerCondition.checkDisablerCondition(DisablerCondition.PLACE_BLOCK)) {
                            return;
                        }
                        c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet;
                        index = Float.compare(c08PacketPlayerBlockPlacement.getYawRotation2(), 0.0F);
                        completed5 = true;
                    }
                    if (!completed5) {
                        enabled = packet instanceof C07PacketPlayerDigging;
                        completed3 = true;
                    }
                }
                if (!completed5) {
                    if (!completed3) {
                        enabled3 = packet instanceof C0EPacketClickWindow;
                        completed2 = true;
                    }
                }
            }
            if (!completed5) {
                if (!completed3) {
                    if (!completed2) {
                        enabled4 = packet instanceof C02PacketUseEntity;
                    }
                }
            }
        }
        if (!completed5) {
            if (!completed3) {
                if (!completed2) {
                    if (!completed) {
                        if (enabled4 && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
                            DisablerCondition.getTime(DisablerCondition.ATTACK).reset();
                        }
                    }
                    boolean enabled8 = DisablerCondition.checkDisablerCondition(DisablerCondition.INVENTORY_ACTIONS);
                    if (!enabled8) {
                        boolean enabled9 = DisablerCondition.checkDisablerCondition(DisablerCondition.BREAK_BLOCKS);
                        if (!enabled9) {
                            boolean enabled10 = packet instanceof C08PacketPlayerBlockPlacement;
                            if (!enabled10) {
                                return;
                            }
                            if (!DisablerCondition.checkDisablerCondition(DisablerCondition.PLACE_BLOCK)) {
                                return;
                            }
                            c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet;
                            index = Float.compare(c08PacketPlayerBlockPlacement.getYawRotation2(), 0.0F);
                            completed5 = true;
                        }
                        if (!completed5) {
                            enabled = packet instanceof C07PacketPlayerDigging;
                            completed3 = true;
                        }
                    }
                    if (!completed5) {
                        if (!completed3) {
                            enabled3 = packet instanceof C0EPacketClickWindow;
                        }
                    }
                }
                if (!completed5) {
                    if (!completed3) {
                        if (enabled3 || packet instanceof C0DPacketCloseWindow) {
                            DisablerCondition.getTime(DisablerCondition.INVENTORY_ACTIONS).reset();
                        }
                        if (!DisablerCondition.checkDisablerCondition(DisablerCondition.BREAK_BLOCKS)) {
                            completed4 = true;
                        }
                        if (!completed4) {
                            enabled = packet instanceof C07PacketPlayerDigging;
                        }
                    }
                }
            }
            if (!completed5) {
                if (!completed4) {
                    if (enabled) {
                        DisablerCondition.getTime(DisablerCondition.BREAK_BLOCKS).reset();
                    }
                }
                if (!(packet instanceof C08PacketPlayerBlockPlacement)) {
                    return;
                }
                if (!DisablerCondition.checkDisablerCondition(DisablerCondition.PLACE_BLOCK)) {
                    return;
                }
                c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet;
                index = Float.compare(c08PacketPlayerBlockPlacement.getYawRotation2(), 0.0F);
            }
        }


        if (index != 0) {
            if (c08PacketPlayerBlockPlacement.getYawRotation() != 0.0F) {
                if (c08PacketPlayerBlockPlacement.getYawRotation1() != 0.0F) {
                    if (c08PacketPlayerBlockPlacement.getEntityId() != 255) {
                        DisablerCondition.getTime(DisablerCondition.PLACE_BLOCK).reset();
                    }
                }
            }
        }
    }

    private boolean isClassicColorMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean isPlayerVisible() {
        return !this.settingPlayerESP.getSelected().equals("None");
    }

    private boolean isLineWidthMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean isRenderTargetsVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean canExecute5() {
        for (DisablerCondition disablerCondition : DisablerCondition.values()) {
            TimeHelper time2 = DisablerCondition.getTime(disablerCondition);
            double value = DisablerCondition.getNumberSetting(disablerCondition).getValue();
            if (!time2.hasReachedDouble(value)) {
                return true;
            }
        }

        return false;
    }

    private boolean isFailIntervalVisible() {
        return !this.settingFailRate.isEnabled() ? false : this.settingFailRate.isVisible();
    }

    private boolean isSmoothRenderingVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean isClassicColorTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }
}
