package augustus.modules.latency;

import client.Client;
import client.ClientGlobals;
import client.event.EventPacket;
import client.event.EventRenderEntity;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderEntityListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BlinkTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.RunnableTask;
import client.util.UseEntityAction;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.Vec3;

public class Blink extends Module implements NetworkPacketListener, PacketListener, RenderListener, RenderEntityListener, TickListener {
    public ModeSetting settingPacketDirection;
    public NumberSetting settingAutoDisable;
    public BooleanSetting settingDisableOnAttack;
    public BooleanSetting settingDisableKillAura;
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
    public ShaderOptions settingPlayer;
    public ArrayList itemList3;
    public int tickDelay;
    public long timestampMillis;

    public Blink() {
        super("Blink", "Laag", new Color(75, 5, 161), Category.LATENCY);
        String[] textArray = new String[3];
        textArray[0] = "OnlyIncoming";
        textArray[1] = "OnlyOutgoing";
        textArray[2] = "Both";
        this.settingPacketDirection = new ModeSetting(this, "PacketDirection", "OnlyOutgoing", textArray);
        this.settingAutoDisable = new NumberSetting(this, "AutoDisable", 0.0, 0.0, 100.0, 0);
        this.settingDisableOnAttack = new BooleanSetting(this, "DisableOnAttack", false);
        this.settingDisableKillAura = new BooleanSetting(this, "DisableKillAura", false);
        this.settingTargetESPs = new SettingGroup(this, "TargetESPs");
        String[] textArray2 = new String[4];
        textArray2[0] = "None";
        textArray2[1] = "Classic";
        textArray2[2] = "PlayerModel";
        textArray2[3] = "PlayerModelArmor";
        this.settingTargetESP = new ModeSetting(this, "TargetESP", "Classic", textArray2, this.settingTargetESPs);
        Color color = new Color(0, 255, 0, 0);
        ColorSetting colorSetting = new ColorSetting(this, "ClassicColorTarget", color, this.settingTargetESPs);
        String text = "ClassicColor";
        Setting setting = colorSetting.getSetting(text);
        this.settingClassicColorTarget = (ColorSetting)(((ColorSetting)setting).getSetting(() -> this.isClassicColorTargetVisible()));
        NumberSetting numberSetting2 = new NumberSetting(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.settingTargetESPs);
        String text2 = "LineWidth";
        this.settingLineWidthTarget = numberSetting2.getNumberSetting(text2).getNumberSetting(() -> this.isLineWidthTargetVisible());
        this.settingSmoothRendering = (BooleanSetting)new BooleanSetting(this, "SmoothRendering", false, this.settingTargetESPs).getSetting(() -> this.isSmoothRenderingVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this, "Target", this.settingTargetESPs);
        this.settingTarget = ((ShaderOptions)shaderOptions.getSetting(() -> this.isTargetVisible())).getShaderOptions5();
        BlinkTargetEntity[] blinkTargetEntityArray = BlinkTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", blinkTargetEntityArray, this.settingTargetESPs).getMultiSelectSetting(() -> this.isTargetsVisible());
        this.settingPlayerESPs = new SettingGroup(this, "PlayerESPs");
        String[] textArray3 = new String[4];
        textArray3[0] = "None";
        textArray3[1] = "Classic";
        textArray3[2] = "PlayerModel";
        textArray3[3] = "PlayerModelArmor";
        this.settingPlayerESP = new ModeSetting(this, "PlayerESP", "Classic", textArray3, this.settingPlayerESPs);
        Color color2 = new Color(0, 255, 0, 0);
        ColorSetting colorSetting2 = new ColorSetting(this, "ClassicColorMe", color2, this.settingPlayerESPs);
        String text3 = "ClassicColor";
        Setting setting4 = colorSetting2.getSetting(text3);
        this.settingClassicColorMe = (ColorSetting)(((ColorSetting)setting4).getSetting(() -> this.isClassicColorMeVisible()));
        NumberSetting numberSetting5 = new NumberSetting(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.settingPlayerESPs);
        String text4 = "LineWidth";
        this.settingLineWidthMe = numberSetting5.getNumberSetting(text4).getNumberSetting(() -> this.isLineWidthMeVisible());
        ShaderOptions shaderOptions2 = new ShaderOptions(this, "Player", this.settingPlayerESPs);
        this.settingPlayer = ((ShaderOptions)shaderOptions2.getSetting(() -> this.isPlayerVisible())).getShaderOptions5();
        this.itemList3 = new ArrayList();
        this.tickDelay = 0;
        this.timestampMillis = 0L;
    }

    @Override
    public void onEnable() {
        this.registerEvent(25);
        this.registerEvent(26);
        this.registerEvent(32);
        this.registerEvent(31);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.tickDelay = 0;
        if (Client.getClient().getModuleManager() != null
        && ClientGlobals.moduleManager.debug.isToggled()
        && ClientGlobals.moduleManager.debug.settingBlink.isEnabled()) {
            RayTraceUtil.applyString(System.currentTimeMillis() - this.timestampMillis + " ");
        }

        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
        this.tickDelay = 0;
        this.timestampMillis = System.currentTimeMillis();
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (minecraft.theWorld == null || minecraft.thePlayer == null) {
            this.setActive(false);
        }

        if (this.settingPacketDirection.getSelected().equals("OnlyIncoming")
        || this.settingPacketDirection.getSelected().equals("Both")) {
            packet.setCancelled(true);
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
            if (this.checkModeSetting(settingPlayerESP2)) {
                Vec3 positionVec8 = minecraft.thePlayer.getPositionVec8();
                boolean enabled2 = this.settingPlayerESP.getSelected().equals("PlayerModelArmor");
                this.onRenderEntity4(event, positionVec8, enabled2);
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        String text = "" + this.tickDelay;
        this.applyString4(text);
        if (this.settingAutoDisable.getValue() > 0.0) {
            double tickDelay2 = (double)this.tickDelay;
            NumberSetting settingAutoDisable2 = this.settingAutoDisable;
            if (tickDelay2 > settingAutoDisable2.getValue()) {
                this.tickDelay = 0;
                this.onReset();
            }
        }

        if (packet2 instanceof C03PacketPlayer) {
            this.tickDelay++;
        }

        if (this.settingDisableOnAttack.isEnabled()) {
            if (packet2 instanceof C02PacketUseEntity) {
                if (((C02PacketUseEntity)packet2).getUseEntityAction() == UseEntityAction.ATTACK) {
                    this.setActive(false);
                }
            }
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (this.settingPacketDirection.getSelected().equals("OnlyOutgoing")
        || this.settingPacketDirection.getSelected().equals("Both")) {
            packet.setCancelled(true);
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        RenderUtils.updateState();
        if (ClientGlobals.moduleManager.packetHandler.isEnabled2() || ClientGlobals.moduleManager.packetHandler.canExecute5()) {
            if (this.settingTargetESP.getSelected().equals("Classic") && ClientGlobals.moduleManager.packetHandler.isEnabled2()) {
                boolean enabled = this.settingTarget.canExecute();
                boolean enabled2 = this.settingTarget.canExecute2();
                MovementHandler.processBoolean(enabled, enabled2);
                RunnableTask runnableTask = renderPass -> this.applyRenderPass(renderPass);
                MovementHandler.applyRunnableTask(false, runnableTask);
            }

            if (ClientGlobals.moduleManager.packetHandler.canExecute5()
            && this.settingPlayerESP.getSelected().equals("Classic")
            && minecraft.thePlayer != null) {
                boolean enabled3 = this.settingPlayer.canExecute();
                boolean enabled4 = this.settingPlayer.canExecute2();
                MovementHandler.processBoolean(enabled3, enabled4);
                MovementHandler.applyRunnableTask(false, renderPass2 -> this.applyRenderPass2(renderPass2));
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

    private boolean isClassicColorMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean checkModeSetting(ModeSetting setting) {
        return setting.getSelected().equals("PlayerModel") || setting.getSelected().equals("PlayerModelArmor");
    }

    private void applyRenderPass(RenderPass renderPass) {
        for (EntityLivingBase livingEntity : (Iterable<EntityLivingBase>) this.itemList3) {
            if (!(livingEntity instanceof EntityPlayerSP)) {
                NumberSetting settingLineWidthTarget2 = this.settingLineWidthTarget;
                BooleanSetting settingSmoothRendering2 = this.settingSmoothRendering;
                RenderUtils.applyEntityLivingBase3(livingEntity, this.settingClassicColorTarget, (float)settingLineWidthTarget2.getValue(), 255, settingSmoothRendering2.isEnabled());
                boolean enabled = this.settingTarget.canExecute();
                ShaderOptions settingTarget2 = this.settingTarget;
                MovementHandler.processBoolean(enabled, settingTarget2.canExecute2());
            }
        }
    }

    private static void onRenderEntity2(
    EventRenderEntity event, EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, double x, double y, double z, boolean enabled, RenderPass renderPass
    ) {
        event.applyEntityLivingBase(livingEntity, rendererLivingEntity, x, y, z, enabled);
    }

    private void applyRenderPass2(RenderPass renderPass) {
        Minecraft mc = minecraft;
        NumberSetting settingLineWidthMe2 = this.settingLineWidthMe;
        RenderUtils.applyEntityLivingBase3(mc.thePlayer, this.settingClassicColorMe, (float)settingLineWidthMe2.getValue(), 255, false);
    }

    private boolean isLineWidthMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean checkEntity(Entity entity) {
        return entity == minecraft.thePlayer
        ? false
        : ChatUtil.checkEntity3(
        entity,
        BlinkTargetEntity.PLAYER.isSelected(),
        BlinkTargetEntity.MOB.isSelected(),
        BlinkTargetEntity.ANIMAL.isSelected(),
        BlinkTargetEntity.VILLAGER.isSelected(),
        false,
        BlinkTargetEntity.INVISIBLE.isSelected(),
        false,
        false,
        
        true,
        BlinkTargetEntity.DEAD.isSelected(),
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

    private boolean isSmoothRenderingVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    public void onRenderEntity4(EventRenderEntity event, Vec3 vec, boolean enabled) {
        boolean enabled2 = this.settingPlayer.canExecute();
        boolean enabled3 = this.settingPlayer.canExecute2();
        MovementHandler.processBoolean(enabled2, enabled3);
        RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
        RunnableTask runnableTask = renderPass -> onRenderEntity5(event, rendererLivingEntity, vec, enabled, renderPass);
        MovementHandler.applyRunnableTask(false, runnableTask);
    }

    private static void onRenderEntity5(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, Vec3 vec, boolean enabled, RenderPass renderPass) {
        Minecraft mc = minecraft;
        double zCoord = vec.zCoord;
        double yCoord = vec.yCoord;
        double xCoord = vec.xCoord;
        EntityPlayerSP player = mc.thePlayer;
        event.applyEntityLivingBase(player, rendererLivingEntity, xCoord, yCoord, zCoord, enabled);
    }

    private boolean isTargetVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean isLineWidthTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private boolean isTargetsVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean isClassicColorTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private boolean isPlayerVisible() {
        return !this.settingPlayerESP.getSelected().equals("None");
    }
}
