package augustus.modules.latency;

import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventPacket;
import client.event.EventRenderEntity;
import client.event.EventSendPacket;
import client.event.MotionUpdateListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderEntityListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.LatencyPacket;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.PulseBlinkStandingStillType;
import client.setting.PulseBlinkTargetEntity;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatTargetFilter;
import client.util.CombatUtil;
import client.util.DisablerCondition;
import client.util.MoveUtil;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.RunnableTask;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class PulseBlink
extends Module
implements NetworkPacketListener,
RenderListener,
RenderEntityListener,
TickListener,
PacketListener,
MotionUpdateListener {
    public ModeSetting settingPacketDirection;
    public RangeSetting settingLagBreakTicks;
    public RangeSetting settingLagTicks;
    public ModeSetting settingModes;
    public MultiSelectSetting settingFlushConditions;
    public SettingGroup settingTargetESPs;
    public ModeSetting settingTargetESP;
    public ColorSetting settingClassicColorTarget;
    public NumberSetting settingLineWidthTarget;
    public BooleanSetting settingSmoothRendering;
    public ShaderOptions settingTarget;
    public MultiSelectSetting settingTargets;
    public SettingGroup settingPlayerESPs;
    public ModeSetting settingPlayerESP;
    public ColorSetting settingClassicColorMe;
    public NumberSetting settingLineWidthMe;
    public BooleanSetting settingOnlyInThirdPerson;
    public ShaderOptions settingPlayer;
    public ArrayList itemList3;
    public int tickDelay;
    public boolean expanded2;

    public PulseBlink() {
        super("PulseBlink", "Vodafone", Color.black, Category.LATENCY);
        String[] textArray = new String[3];
        textArray[0] = "OnlyIncoming";
        textArray[1] = "OnlyOutgoing";
        textArray[2] = "Both";
        this.settingPacketDirection = new ModeSetting(this, "PacketDirection", "OnlyOutgoing", textArray);
        RangeSetting rangeSetting = new RangeSetting(this, "LagBreakTicks", 0.0, 0.0, 0.0, 20.0, 0);
        String text = "TickDelayBetweenLags";
        this.settingLagBreakTicks = rangeSetting.getRangeSetting(text).getRangeSetting2();
        this.settingLagTicks = new RangeSetting(this, "LagTicks", 5.0, 6.0, 1.0, 50.0, 0).getRangeSetting2();
        String[] textArray2 = new String[3];
        textArray2[0] = "Default";
        textArray2[1] = "DefaultCombat";
        textArray2[2] = "CombatOnly";
        this.settingModes = new ModeSetting(this, "Modes", "DefaultCombat", textArray2).getModeSetting(() -> this.isModesVisible());
        PulseBlinkStandingStillType[] pulseBlinkStandingStillTypeArray = PulseBlinkStandingStillType.values();
        this.settingFlushConditions = new MultiSelectSetting(this, "FlushConditions", pulseBlinkStandingStillTypeArray);
        this.settingTargetESPs = new SettingGroup(this, "TargetESPs");
        String[] textArray3 = new String[4];
        textArray3[0] = "None";
        textArray3[1] = "Classic";
        textArray3[2] = "PlayerModel";
        textArray3[3] = "PlayerModelArmor";
        this.settingTargetESP = new ModeSetting(this, "TargetESP", "Classic", textArray3, this.settingTargetESPs);
        Color color = new Color(0, 255, 0, 0);
        ColorSetting colorSetting = new ColorSetting(this, "ClassicColorTarget", color, this.settingTargetESPs);
        String text2 = "ClassicColor";
        Setting setting = colorSetting.getSetting(text2);
        this.settingClassicColorTarget = (ColorSetting)(((ColorSetting)setting).getSetting(() -> this.isClassicColorTargetVisible()));
        NumberSetting numberSetting = new NumberSetting(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.settingTargetESPs);
        String text3 = "LineWidth";
        this.settingLineWidthTarget = numberSetting.getNumberSetting(text3).getNumberSetting(() -> this.isLineWidthTargetVisible());
        this.settingSmoothRendering = (BooleanSetting)new BooleanSetting(this, "SmoothRendering", false, this.settingTargetESPs).getSetting(() -> this.isSmoothRenderingVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this, "Target", this.settingTargetESPs);
        this.settingTarget = ((ShaderOptions)shaderOptions.getSetting(() -> this.isTargetVisible())).getShaderOptions5();
        PulseBlinkTargetEntity[] pulseBlinkTargetEntityArray = PulseBlinkTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", pulseBlinkTargetEntityArray, this.settingTargetESPs).getMultiSelectSetting(() -> this.isTargetsVisible());
        this.settingPlayerESPs = new SettingGroup(this, "PlayerESPs");
        String[] textArray4 = new String[4];
        textArray4[0] = "None";
        textArray4[1] = "Classic";
        textArray4[2] = "PlayerModel";
        textArray4[3] = "PlayerModelArmor";
        this.settingPlayerESP = new ModeSetting(this, "PlayerESP", "Classic", textArray4, this.settingPlayerESPs);
        Color color2 = new Color(0, 255, 0, 0);
        ColorSetting colorSetting2 = new ColorSetting(this, "ClassicColorMe", color2, this.settingPlayerESPs);
        String text4 = "ClassicColor";
        Setting setting4 = colorSetting2.getSetting(text4);
        this.settingClassicColorMe = (ColorSetting)(((ColorSetting)setting4).getSetting(() -> this.isClassicColorMeVisible()));
        NumberSetting numberSetting4 = new NumberSetting(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.settingPlayerESPs);
        String text5 = "LineWidth";
        this.settingLineWidthMe = numberSetting4.getNumberSetting(text5).getNumberSetting(() -> this.isLineWidthMeVisible());
        this.settingOnlyInThirdPerson = new BooleanSetting(this, "OnlyInThirdPerson", true, this.settingPlayerESPs);
        ShaderOptions shaderOptions2 = new ShaderOptions(this, "Player", this.settingPlayerESPs);
        this.settingPlayer = ((ShaderOptions)shaderOptions2.getSetting(() -> this.isPlayerVisible())).getShaderOptions5();
        this.itemList3 = new ArrayList();
        this.tickDelay = 0;
        this.expanded2 = true;
    }

    @Override
    public void onEnable() {
        this.registerEvent(25);
        this.registerEvent(32);
        this.registerEvent(31);
        this.registerEvent(40);
        this.registerEvent(26);
        this.registerEvent(18);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.processChar();
        this.expanded2 = false;
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        Packet packet2 = packet.getPacket();
        if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.VELOCITY) && packet2 instanceof S12PacketEntityVelocity) {
            int entityId = ((S12PacketEntityVelocity)packet2).getEntityId();
            Minecraft mc = minecraft;
            if (entityId == mc.thePlayer.getEntityId()) {
                this.updateState();
            }
        }

        if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.EXPLOSION) && packet2 instanceof S27PacketExplosion) {
            S27PacketExplosion s27PacketExplosion = (S27PacketExplosion)packet2;
            if (s27PacketExplosion.getScaleFactor2() != 0.0F
            || s27PacketExplosion.getScaleFactor3() != 0.0F
            || s27PacketExplosion.getScaleFactor() != 0.0F) {
                this.updateState();
            }
        }

        if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.TELEPORT) && packet2 instanceof S08PacketPlayerPosLook) {
            this.updateState();
        }

        if (!this.settingPacketDirection.getSelected().equals("OnlyOutgoing")) {
            if (minecraft.thePlayer == null) {
                return;
            }

            if (minecraft.theWorld != null) {
                if (this.expanded2) {
                    packet.setCancelled(true);
                }

                return;
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if (packet.getPacket() instanceof C03PacketPlayer) {
            this.tickDelay++;
        }
    }

    @Override
    public void onRenderEntity(EventRenderEntity event) {
        EntityLivingBase targetEntity = event.getTargetEntity();
        if (ClientGlobals.moduleManager.packetHandler.isEnabled2() && this.checkEntity(targetEntity)) {
            ModeSetting settingTargetESP2 = this.settingTargetESP;
            if (this.checkModeSetting(settingTargetESP2)) {
                boolean enabled = this.settingTargetESP.getSelected().equals("PlayerModelArmor");
                this.onRenderEntity3(event, targetEntity, enabled);
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
            boolean enabled2 = this.settingPlayerESP.getSelected().equals("PlayerModelArmor");
            this.onRenderEntity5(event, positionVec8, enabled2);
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (!this.settingPacketDirection.getSelected().equals("OnlyIncoming")) {
            if (minecraft.thePlayer != null) {
                if (minecraft.theWorld != null) {
                    Packet packet2 = packet.getPacket();
                    if (packet2 instanceof C02PacketUseEntity
                    && !this.settingModes.getSelected().equals("Default")
                    && this.settingPacketDirection.getSelected().equals("OnlyOutgoing")
                    && this.expanded2) {
                        this.updateState();
                    }

                    if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.INVENTORY_ACTIONS)
                    && (packet2 instanceof C0EPacketClickWindow || packet2 instanceof C0DPacketCloseWindow)) {
                        this.updateState();
                    }

                    if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.BREAK_BLOCKS)
                    && packet2 instanceof C07PacketPlayerDigging) {
                        this.updateState();
                    }

                    if (DisablerCondition.USE_ITEM.isSelected() && minecraft.thePlayer.isUsingItem()) {
                        this.updateState();
                    }

                    if (DisablerCondition.OPEN_GUI.isSelected() && !minecraft.isIntegratedServerRunning()) {
                        this.updateState();
                    }

                    if (packet2 instanceof C08PacketPlayerBlockPlacement
                    && PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.PLACE_BLOCK)) {
                        C08PacketPlayerBlockPlacement c08PacketPlayerBlockPlacement = (C08PacketPlayerBlockPlacement)packet2;
                        if (c08PacketPlayerBlockPlacement.getYawRotation2() != 0.0F && c08PacketPlayerBlockPlacement.getYawRotation() != 0.0F && c08PacketPlayerBlockPlacement.getYawRotation1() != 0.0F && c08PacketPlayerBlockPlacement.getEntityId() != 255) {
                            this.updateState();
                        }
                    }

                    if (this.expanded2) {
                        packet.setCancelled(true);
                    }
                }
            }
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.SPRINT_RESET)) {
            boolean riding = minecraft.thePlayer.isRiding();
            Minecraft mc = minecraft;
            if (riding != mc.thePlayer.isInWeb2()) {
                this.updateState();
            }
        }

        if (PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.SPLASH_POTION)) {
            minecraft.theWorld.loadedEntityList.stream().filter(object -> checkEntity2((Entity)object)).forEach(object2 -> this.applyEntity((Entity)object2));
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
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

        for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
            if (this.checkEntity(entity)) {
                this.itemList3.add((EntityLivingBase)entity);
            }
        }
    }

    @Override
    public void onPostTick() {
        if (minecraft.thePlayer != null
        && (
        !PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.STANDING_STILL)
        || MoveUtil.canExecute()
        )
        && (
        !BlockCache.canExecute()
        || !PulseBlinkStandingStillType.checkPulseBlinkStandingStillType(PulseBlinkStandingStillType.SCAFFOLD_TOGGLED)
        )) {
            if (this.expanded2) {
                if (this.tickDelay >= this.settingLagTicks.getIndexOffset2()) {
                    this.expanded2 = false;
                    if (minecraft.getNetHandlerPlayClient() != null && minecraft.getNetHandlerPlayClient().getNetworkManager() != null) {
                        minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
                    }

                    this.processChar();
                }
            } else if (this.tickDelay >= this.settingLagBreakTicks.getIndexOffset2()) {
                this.expanded2 = true;
                this.processChar();
            }

            if (this.settingPacketDirection.getSelected().equals("OnlyOutgoing")) {
                if (this.canExecute5()) {
                    if (minecraft.getNetHandlerPlayClient() != null && minecraft.getNetHandlerPlayClient().getNetworkManager() != null) {
                        minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
                    }

                    this.updateState();
                }
            }
        } else {
            this.expanded2 = false;
            this.tickDelay = 0;
        }
    }

    private void applyRenderPass(RenderPass renderPass) {
        Minecraft mc = minecraft;
        NumberSetting settingLineWidthMe2 = this.settingLineWidthMe;
        RenderUtils.applyEntityLivingBase3(mc.thePlayer, this.settingClassicColorMe, (float)settingLineWidthMe2.getValue(), 255, false);
    }

    private void applyEntity(Entity entity) {
        double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
        double scaleFactor4 = (double)minecraft.thePlayer.getScaleFactor4(entity);
        if (!(distance >= 2.0)) {
            if (scaleFactor4 < 1.0) {
                this.updateState();
            }
        }
    }

    private void applyRenderPass2(RenderPass renderPass) {
        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            if (!(livingEntity instanceof EntityPlayerSP)) {
                ColorSetting settingClassicColorTarget2 = this.settingClassicColorTarget;
                float lineWidthTarget = (float)this.settingLineWidthTarget.getValue();
                BooleanSetting settingSmoothRendering2 = this.settingSmoothRendering;
                RenderUtils.applyEntityLivingBase3(livingEntity, settingClassicColorTarget2, lineWidthTarget, 255, settingSmoothRendering2.isEnabled());
                boolean enabled = this.settingTarget.canExecute();
                boolean enabled2 = this.settingTarget.canExecute2();
                MovementHandler.processBoolean(enabled, enabled2);
            }
        }
    }

    private static void onRenderEntity2(
    EventRenderEntity event, EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, double x, double y, double z, boolean enabled, RenderPass renderPass
    ) {
        event.applyEntityLivingBase(livingEntity, rendererLivingEntity, x, y, z, enabled);
    }

    private boolean checkEntity(Entity entity) {
        return entity == minecraft.thePlayer
        ? false
        : ChatUtil.checkEntity3(
        entity,
        PulseBlinkTargetEntity.PLAYER.isSelected(),
        PulseBlinkTargetEntity.MOB.isSelected(),
        PulseBlinkTargetEntity.ANIMAL.isSelected(),
        PulseBlinkTargetEntity.VILLAGER.isSelected(),
        false,
        PulseBlinkTargetEntity.INVISIBLE.isSelected(),
        false,
        false,
        
        true,
        PulseBlinkTargetEntity.DEAD.isSelected(),
        false
        );
    }

    public void onRenderEntity3(EventRenderEntity event, EntityLivingBase livingEntity, boolean enabled) {
        double offset = livingEntity.positionVec3.xCoord - livingEntity.positionVec.xCoord;
        Minecraft mc = minecraft;
        double offset2 = livingEntity.positionVec.xCoord + offset * (double)mc.getTimer().scaleFactor6;
        double offset3 = livingEntity.positionVec3.yCoord - livingEntity.positionVec.yCoord;
        Minecraft mc2 = minecraft;
        double offset4 = livingEntity.positionVec.yCoord + offset3 * (double)mc2.getTimer().scaleFactor6;
        double offset5 = livingEntity.positionVec3.zCoord - livingEntity.positionVec.zCoord;
        Minecraft mc3 = minecraft;
        double offset6 = livingEntity.positionVec.zCoord + offset5 * (double)mc3.getTimer().scaleFactor6;
        boolean enabled2 = this.settingTarget.canExecute();
        boolean enabled3 = this.settingTarget.canExecute2();
        MovementHandler.processBoolean(enabled2, enabled3);
        RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
        RunnableTask runnableTask = renderPass -> onRenderEntity2(event, livingEntity, rendererLivingEntity, offset2, offset4, offset6, enabled, renderPass);
        MovementHandler.applyRunnableTask(false, runnableTask);
    }

    private void updateState() {
        this.expanded2 = false;
        this.tickDelay = 0;
        this.processChar();
    }

    private static void onRenderEntity4(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, Vec3 vec, boolean enabled, RenderPass renderPass) {
        Minecraft mc = minecraft;
        double zCoord = vec.zCoord;
        double yCoord = vec.yCoord;
        double xCoord = vec.xCoord;
        EntityPlayerSP player = mc.thePlayer;
        event.applyEntityLivingBase(player, rendererLivingEntity, xCoord, yCoord, zCoord, enabled);
    }

    private boolean isClassicColorTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private static boolean checkEntity2(Entity entity) {
        return entity instanceof EntityThrownpotion;
    }

    private EntityLivingBase getEntityLivingBase() {
        return ChatUtil.getEntityLivingBase();
    }

    private boolean isClassicColorMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean checkEntityLivingBase(EntityLivingBase livingEntity) {
        if (livingEntity instanceof EntityPlayer && !CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, livingEntity)) {
            EntityPlayer player = (EntityPlayer)livingEntity;
            float height = player.getHeight();
            AxisAlignedBB axisAlignedBB = player.getBoundingBox2().offset((double)height, (double)height, (double)height);
            Vec3 vec3 = new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
            Vec3 vec4 = SimulatedPlayer.getVec3(player, (short)20072, -521758288);
            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer);
            Vec3 vec5 = new Vec3(coordOffsetArray[0], coordOffsetArray[1] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray[2]);
            Vec3 vec = SimulatedPlayer.processAction(vec5, axisAlignedBB);
            if (vec5.getCoordinate4(vec) < vec3.getCoordinate4(vec4) && minecraft.thePlayer.getDistanceToEntity(player) > 3.0F) {
                for (int i = this.settingLagTicks.getIndexOffset2(); i > 0; i--) {
                    double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(i - 1, minecraft.thePlayer);
                    Vec3 vec6 = CombatTargetFilter.getVec3(-1266944806, player, i);
                    Vec3 vec7 = new Vec3(coordOffsetArray2[0], coordOffsetArray2[1] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray2[2]);
                    float value = player.width / 2.0F;
                    AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(
                    vec6.xCoord - (double)value,
                    vec6.yCoord,
                    vec6.zCoord - (double)value,
                    vec6.xCoord + (double)value,
                    vec6.yCoord + (double)player.height,
                    vec6.zCoord + (double)value
                    )
                    .offset((double)height, (double)height, (double)height);
                    Vec3 vec2 = SimulatedPlayer.processAction(vec7, axisAlignedBB2);
                    if (vec7.getCoordinate4(vec2) < 3.0) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return true;
        }
    }

    private void processChar() {
        this.settingLagTicks.recalculate();
        this.settingLagBreakTicks.recalculate();
        this.tickDelay = 0;
    }

    public void onRenderEntity5(EventRenderEntity event, Vec3 vec, boolean enabled) {
        boolean enabled2 = this.settingPlayer.canExecute();
        boolean enabled3 = this.settingPlayer.canExecute2();
        MovementHandler.processBoolean(enabled2, enabled3);
        RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
        RunnableTask runnableTask = renderPass -> onRenderEntity4(event, rendererLivingEntity, vec, enabled, renderPass);
        MovementHandler.applyRunnableTask(false, runnableTask);
    }

    private boolean checkModeSetting(ModeSetting setting) {
        return setting.getSelected().equals("PlayerModel") || setting.getSelected().equals("PlayerModelArmor");
    }

    private boolean checkEntityLivingBase2(EntityLivingBase livingEntity) {
        short id = 20072;
        if (livingEntity instanceof EntityPlayer && !CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, livingEntity)) {
            EntityPlayer player = (EntityPlayer)livingEntity;
            float height = player.getHeight();
            AxisAlignedBB axisAlignedBB = player.getBoundingBox2().offset((double)height, (double)height, (double)height);
            Vec3 vec3 = new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
            Vec3 vec4 = SimulatedPlayer.getVec3(player, (short)id, -521758288);
            double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer);
            Vec3 vec5 = new Vec3(coordOffsetArray[0], coordOffsetArray[1] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray[2]);
            Vec3 vec = SimulatedPlayer.processAction(vec5, axisAlignedBB);
            if (vec5.getCoordinate4(vec) < vec3.getCoordinate4(vec4)
            && minecraft.thePlayer.getDistanceToEntity(player) > 3.0F
            && !CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, player)) {
                for (int i = this.settingLagTicks.getIndexOffset2(); i > 0; i--) {
                    double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(i - 1, minecraft.thePlayer);
                    Vec3 vec6 = CombatTargetFilter.getVec3(-1266944806, player, i);
                    Vec3 vec7 = new Vec3(coordOffsetArray2[0], coordOffsetArray2[1] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray2[2]);
                    float value = player.width / 2.0F;
                    AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(
                    vec6.xCoord - (double)value,
                    vec6.yCoord,
                    vec6.zCoord - (double)value,
                    vec6.xCoord + (double)value,
                    vec6.yCoord + (double)player.height,
                    vec6.zCoord + (double)value
                    )
                    .offset((double)height, (double)height, (double)height);
                    Vec3 vec2 = SimulatedPlayer.processAction(vec7, axisAlignedBB2);
                    if (vec7.getCoordinate4(vec2) < 3.0) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                if ((byte)((entity instanceof EntityPlayer) ? 1 : 0) != 0) {
                    EntityPlayer player2 = (EntityPlayer)entity;
                    if ((byte)((player2 instanceof EntityPlayerSP) ? 1 : 0) == 0
                    && !ClientGlobals.moduleManager.midClick.checkEntity(player2)
                    && !ClientGlobals.moduleManager.antiBot.checkEntity2(player2)
                    && !ClientGlobals.moduleManager.teams.checkEntity2(player2)
                    && minecraft.thePlayer.getPositionVec12().getDouble2(player2.posX, player2.posY, player2.posZ) < 6.1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private boolean isLineWidthMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean isTargetVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean isModesVisible() {
        return this.settingPacketDirection.getSelected().equals("OnlyOutgoing");
    }

    private boolean isPlayerVisible() {
        return !this.settingPlayerESP.getSelected().equals("None");
    }

    private boolean isLineWidthTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private boolean isTargetsVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean canExecute5() {
        EntityLivingBase entityLivingBase = this.getEntityLivingBase();
        if (this.settingModes.getSelected().equals("DefaultCombat")) {
            return this.checkEntityLivingBase2(entityLivingBase);
        } else {
            return !this.settingModes.getSelected().equals("CombatOnly") ? false : this.checkEntityLivingBase(entityLivingBase);
        }
    }

    private boolean isSmoothRenderingVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }
}
