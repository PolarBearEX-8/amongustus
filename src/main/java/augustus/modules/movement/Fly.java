package augustus.modules.movement;

import augustus.modes.movement.FlyAirJumpMode;
import augustus.modes.movement.FlyAirPlaceMode;
import augustus.modes.movement.FlyBedwarsPracticeMode;
import augustus.modes.movement.FlyCubeCraftMode;
import augustus.modes.movement.FlyCustomMode;
import augustus.modes.movement.FlyGrimFlyMode;
import augustus.modes.movement.FlyGroundCollisionMode;
import augustus.modes.movement.FlyIntaveBoatMode;
import augustus.modes.movement.FlyMinemoraBasicMode;
import augustus.modes.movement.FlyMinemoraFastMode;
import augustus.modes.movement.FlyModeHandler;
import augustus.modes.movement.FlyOldGommeMode;
import augustus.modes.movement.FlyOldVerusMode;
import augustus.modes.movement.FlyUniversoCraftMode;
import augustus.modes.movement.FlyVanillaBasicMode;
import augustus.modes.movement.FlyVanillaExtendedMode;
import augustus.modes.movement.FlyVerusBasicMode;
import augustus.modes.movement.FlyVerusCombinedMode;
import augustus.modes.movement.FlyVerusDamageMode;
import augustus.modes.movement.FlyVerusFastMode;
import augustus.modes.movement.FlyVulcanMode;
import client.event.ClickListener;
import client.event.EventBlockCollision;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSendPacket;
import client.event.MotionListener;
import client.event.MotionUpdateListener;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.RotationListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ModeOptionConfigurable;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import client.util.BooleanSupplier;
import client.util.ConfigManager;
import client.util.ConsumerTask;
import client.util.ModeListTracker;
import client.util.MoveUtil;
import client.util.OnlineConfigsManager;
import client.util.TimeHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.BlockCache;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.util.Vec3;

public class Fly
    extends Module
    implements ClickListener,
    MotionListener,
    RotationListener,
    BlockCollisionListener,
    MoveEntityListener,
    MoveListener,
    UpdateStateListener,
    MotionUpdateListener,
    RenderListener,
    PacketListener,
    NetworkPacketListener,
    TickListener {
    public EntityEnderdragon fakeDragon = (EntityEnderdragon)null;
    public ModeSetting settingMode;
    public BooleanSetting settingWaitForDamage;
    public BooleanSetting settingAutoJump;
    public BooleanSetting settingOnGroundPacket;
    public NumberSetting settingVanillaSpeed;
    public NumberSetting settingVerusSpeed;
    public BooleanSetting settingOnlyOnGround;
    public NumberSetting settingVerusSpeedCombined;
    public NumberSetting settingCubeCraftSpeed;
    public NumberSetting settingCubeCraftStopDuration;
    public NumberSetting settingCollisionY;
    public NumberSetting settingHeight;
    public NumberSetting settingBoost;
    public BooleanSetting settingHelpMessage;
    public NumberSetting settingVulcanCollisionY;
    public BooleanSetting settingTimer;
    public NumberSetting settingTimerSpeed;
    public NumberSetting settingTimerSpeedEnd;
    public BooleanSetting settingDisableAura;
    public BooleanSetting settingCancelS08;
    public ModeSetting settingBlinkMode;
    public BooleanSetting settingDistanceFlown;
    public BooleanSetting settingSpigotFlyKickBypass;
    public BooleanSetting settingEnderDragon;
    public BooleanSetting settingEnderDragonSound;
    public DecimalFormat speedFormat;
    public Vec3 positionVec;
    public double reachDistance;
    public long timestampMillis;
    public TimeHelper timer;
    public HashMap entryMap;
    public ConcurrentHashMap entryMap2;
    public HashMap placedBlocks;
    public FlyAirJumpMode airJumpHandler;
    public String lastFlyMode;
    public static ModeListTracker modeListTracker = new ModeListTracker();
    public static boolean activeState;

    public Fly() {
        super("Fly", "Fliegen", new Color(123, 240, 156), Category.MOVEMENT);
        String[] textArray = new String[0];
        this.settingMode = new ModeSetting(this, "Mode", "", textArray);
        BooleanSetting booleanSetting = new BooleanSetting(this, "WaitForDamage", true);
        BooleanSupplier booleanSupplier = () -> this.isModeVisible();
        Setting setting = booleanSetting;
        BooleanSupplier booleanSupplier2 = booleanSupplier;
        this.settingWaitForDamage = (BooleanSetting)setting.getSetting(booleanSupplier2);
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "AutoJump", true);
        BooleanSupplier booleanSupplier3 = () -> this.isModeVisible2();
        Setting setting3 = booleanSetting2;
        BooleanSupplier booleanSupplier4 = booleanSupplier3;
        this.settingAutoJump = (BooleanSetting)setting3.getSetting(booleanSupplier4);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "OnGroundPacket", true);
        BooleanSupplier booleanSupplier5 = () -> this.isModeVisible3();
        Setting setting5 = booleanSetting3;
        BooleanSupplier booleanSupplier6 = booleanSupplier5;
        this.settingOnGroundPacket = (BooleanSetting)setting5.getSetting(booleanSupplier6);
        NumberSetting numberSetting = new NumberSetting(this, "VanillaSpeed", 1.0, 0.1, 9.0, 1);
        BooleanSupplier booleanSupplier7 = () -> this.isVanillaSpeedVisible();
        NumberSetting setting7 = numberSetting;
        BooleanSupplier booleanSupplier8 = booleanSupplier7;
        this.settingVanillaSpeed = setting7.getNumberSetting(booleanSupplier8);
        NumberSetting numberSetting3 = new NumberSetting(this, "VerusSpeed", 1.0, 0.1, 9.0, 1);
        BooleanSupplier booleanSupplier9 = () -> this.isVerusSpeedVisible2();
        NumberSetting setting8 = numberSetting3;
        BooleanSupplier booleanSupplier10 = booleanSupplier9;
        this.settingVerusSpeed = setting8.getNumberSetting(booleanSupplier10);
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "OnlyOnGround", true);
        BooleanSupplier booleanSupplier11 = () -> this.isVerusSpeedVisible();
        Setting setting9 = booleanSetting4;
        BooleanSupplier booleanSupplier12 = booleanSupplier11;
        this.settingOnlyOnGround = (BooleanSetting)setting9.getSetting(booleanSupplier12);
        NumberSetting numberSetting5 = new NumberSetting(this, "VerusSpeedCombined", 1.0, 0.1, 9.0, 1);
        NumberSetting setting11 = numberSetting5;
        String text = "VerusSpeed";
        NumberSetting numberSetting6 = setting11.getNumberSetting(text);
        BooleanSupplier booleanSupplier13 = () -> this.isVerusSpeedCombinedVisible();
        NumberSetting setting12 = numberSetting6;
        BooleanSupplier booleanSupplier14 = booleanSupplier13;
        this.settingVerusSpeedCombined = setting12.getNumberSetting(booleanSupplier14);
        NumberSetting numberSetting8 = new NumberSetting(this, "CubeCraftSpeed", 3.0, 0.0, 5.0, 2);
        BooleanSupplier booleanSupplier15 = () -> this.isCubeCraftSpeedVisible();
        NumberSetting setting13 = numberSetting8;
        BooleanSupplier booleanSupplier16 = booleanSupplier15;
        this.settingCubeCraftSpeed = setting13.getNumberSetting(booleanSupplier16);
        NumberSetting numberSetting10 = new NumberSetting(this, "CubeCraftStopDuration", 4.0, 0.0, 5.0, 1);
        BooleanSupplier booleanSupplier17 = () -> this.isCubeCraftStopDurationVisible();
        NumberSetting setting14 = numberSetting10;
        BooleanSupplier booleanSupplier18 = booleanSupplier17;
        this.settingCubeCraftStopDuration = setting14.getNumberSetting(booleanSupplier18);
        NumberSetting numberSetting12 = new NumberSetting(this, "CollisionY", 1.0, 0.0, 1.0, 2);
        BooleanSupplier booleanSupplier19 = () -> this.isCollisionYVisible();
        NumberSetting setting15 = numberSetting12;
        BooleanSupplier booleanSupplier20 = booleanSupplier19;
        this.settingCollisionY = setting15.getNumberSetting(booleanSupplier20);
        NumberSetting numberSetting14 = new NumberSetting(this, "Height", 1.0, 1.0, 8.0, 2);
        BooleanSupplier booleanSupplier21 = () -> this.isHeightVisible();
        NumberSetting setting16 = numberSetting14;
        BooleanSupplier booleanSupplier22 = booleanSupplier21;
        this.settingHeight = setting16.getNumberSetting(booleanSupplier22);
        NumberSetting numberSetting16 = new NumberSetting(this, "Boost", 1.0, 1.0, 8.0, 2);
        BooleanSupplier booleanSupplier23 = () -> this.isBoostVisible();
        NumberSetting setting17 = numberSetting16;
        BooleanSupplier booleanSupplier24 = booleanSupplier23;
        this.settingBoost = setting17.getNumberSetting(booleanSupplier24);
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "HelpMessage", true);
        BooleanSupplier booleanSupplier25 = () -> this.isBoostVisible2();
        Setting setting18 = booleanSetting5;
        BooleanSupplier booleanSupplier26 = booleanSupplier25;
        this.settingHelpMessage = (BooleanSetting)setting18.getSetting(booleanSupplier26);
        NumberSetting numberSetting18 = new NumberSetting(this, "VulcanCollisionY", 1.0, 0.0, 1.0, 2);
        NumberSetting setting20 = numberSetting18;
        String text2 = "CollisionY";
        NumberSetting numberSetting19 = setting20.getNumberSetting(text2);
        BooleanSupplier booleanSupplier27 = () -> this.isVulcanCollisionYVisible();
        NumberSetting setting21 = numberSetting19;
        BooleanSupplier booleanSupplier28 = booleanSupplier27;
        this.settingVulcanCollisionY = setting21.getNumberSetting(booleanSupplier28);
        BooleanSetting booleanSetting6 = new BooleanSetting(this, "Timer", true);
        BooleanSupplier booleanSupplier29 = () -> this.isVulcanCollisionYVisible2();
        Setting setting22 = booleanSetting6;
        BooleanSupplier booleanSupplier30 = booleanSupplier29;
        this.settingTimer = (BooleanSetting)setting22.getSetting(booleanSupplier30);
        NumberSetting numberSetting21 = new NumberSetting(this, "TimerSpeed", 5.0, 0.2, 10.0, 2);
        BooleanSupplier booleanSupplier31 = () -> this.isTimerSpeedVisible();
        NumberSetting setting24 = numberSetting21;
        BooleanSupplier booleanSupplier32 = booleanSupplier31;
        this.settingTimerSpeed = setting24.getNumberSetting(booleanSupplier32);
        NumberSetting numberSetting23 = new NumberSetting(this, "TimerSpeedEnd", 1.0, 0.2, 50.0, 2);
        BooleanSupplier booleanSupplier33 = () -> this.isTimerSpeedEndVisible();
        NumberSetting setting25 = numberSetting23;
        BooleanSupplier booleanSupplier34 = booleanSupplier33;
        this.settingTimerSpeedEnd = setting25.getNumberSetting(booleanSupplier34);
        this.settingDisableAura = new BooleanSetting(this, "DisableAura", false);
        this.settingCancelS08 = new BooleanSetting(this, "CancelS08", false);
        String[] textArray2 = new String[4];
        textArray2[0] = "None";
        textArray2[1] = "OnlyIncoming";
        textArray2[2] = "OnlyOutgoing";
        textArray2[3] = "Both";
        this.settingBlinkMode = new ModeSetting(this, "BlinkMode", "None", textArray2);
        this.settingDistanceFlown = new BooleanSetting(this, "DistanceFlown", false);
        this.settingSpigotFlyKickBypass = new BooleanSetting(this, "SpigotFlyKickBypass", false);
        BooleanSetting booleanSetting11 = new BooleanSetting(this, "EnderDragon", false);
        ConsumerTask consumerTask = () -> this.onBlinkModeAction();
        Setting setting26 = booleanSetting11;
        ConsumerTask consumerTask2 = consumerTask;
        this.settingEnderDragon = (BooleanSetting)setting26.getSetting(consumerTask2);
        BooleanSetting booleanSetting12 = new BooleanSetting(this, "EnderDragonSound", false);
        BooleanSupplier booleanSupplier35 = () -> ((BooleanSetting)this.settingEnderDragon).isEnabled();
        Setting setting28 = booleanSetting12;
        BooleanSupplier booleanSupplier36 = booleanSupplier35;
        this.settingEnderDragonSound = (BooleanSetting)setting28.getSetting(booleanSupplier36);
        this.speedFormat = new DecimalFormat("0.0");
        this.positionVec = new Vec3(0.0, 0.0, 0.0);
        this.timestampMillis = 0L;
        this.timer = new TimeHelper();
        HashMap hashMap = new HashMap();
        this.entryMap = hashMap;
        this.entryMap2 = new ConcurrentHashMap();
        StringBuilder stringBuilder = new StringBuilder();
        String text3 = OnlineConfigsManager.text;
        StringBuilder sb = stringBuilder.append(text3);
        String separator = File.separator;
        StringBuilder sb2 = sb.append(separator).append("motionmodifier");
        String separator2 = File.separator;
        this.lastFlyMode = sb2.append(separator2).append("fly").toString();
        this.airJumpHandler = new FlyAirJumpMode("AirJump", this);
        FlyModeHandler settingAirJump2 = this.airJumpHandler;
        this.applyFlyModeHandler(settingAirJump2);
        FlyModeHandler flyVanillaBasicMode = new FlyVanillaBasicMode("Vanilla1", this);
        this.applyFlyModeHandler(flyVanillaBasicMode);
        FlyModeHandler flyVanillaExtendedMode = new FlyVanillaExtendedMode("Vanilla2", this);
        this.applyFlyModeHandler(flyVanillaExtendedMode);
        FlyModeHandler flyGroundCollisionMode = new FlyGroundCollisionMode("GroundCollision", this);
        this.applyFlyModeHandler(flyGroundCollisionMode);
        FlyModeHandler flyMinemoraBasicMode = new FlyMinemoraBasicMode("Minemora1", this);
        this.applyFlyModeHandler(flyMinemoraBasicMode);
        FlyModeHandler flyMinemoraFastMode = new FlyMinemoraFastMode("Minemora2", this);
        this.applyFlyModeHandler(flyMinemoraFastMode);
        FlyModeHandler flyOldVerusMode = new FlyOldVerusMode("OldVerus", this);
        this.applyFlyModeHandler(flyOldVerusMode);
        FlyModeHandler flyVerusBasicMode = new FlyVerusBasicMode("Verus1", this);
        this.applyFlyModeHandler(flyVerusBasicMode);
        FlyModeHandler flyVerusFastMode = new FlyVerusFastMode("Verus2", this);
        this.applyFlyModeHandler(flyVerusFastMode);
        FlyModeHandler flyVerusDamageMode = new FlyVerusDamageMode("Verus3", this);
        this.applyFlyModeHandler(flyVerusDamageMode);
        FlyModeHandler flyVerusCombinedMode = new FlyVerusCombinedMode("VerusCombined", this);
        this.applyFlyModeHandler(flyVerusCombinedMode);
        FlyModeHandler flyUniversoCraftMode = new FlyUniversoCraftMode("UniversoCraft", this);
        this.applyFlyModeHandler(flyUniversoCraftMode);
        FlyModeHandler flyCubeCraftMode = new FlyCubeCraftMode("CubeCraft", this);
        this.applyFlyModeHandler(flyCubeCraftMode);
        FlyModeHandler flyOldGommeMode = new FlyOldGommeMode("OldGomme", this);
        this.applyFlyModeHandler(flyOldGommeMode);
        FlyModeHandler flyGrimFlyMode = new FlyGrimFlyMode("GrimFly", this);
        this.applyFlyModeHandler(flyGrimFlyMode);
        FlyModeHandler flyVulcanMode = new FlyVulcanMode("Vulcan", this);
        this.applyFlyModeHandler(flyVulcanMode);
        FlyModeHandler auxiliaryFlyIntaveBoatMode = new FlyIntaveBoatMode("IntaveBoat", this);
        this.applyFlyModeHandler(auxiliaryFlyIntaveBoatMode);
        FlyModeHandler flyBedwarsPracticeMode = new FlyBedwarsPracticeMode("BedwarsPractice", this);
        this.applyFlyModeHandler(flyBedwarsPracticeMode);
        FlyModeHandler flyAirPlaceMode = new FlyAirPlaceMode("AirPlace", this);
        this.applyFlyModeHandler(flyAirPlaceMode);
        Set set = this.entryMap.keySet();
        String[] textArray3 = new String[0];
        String[] textArray4 = ((String[])(set.toArray(textArray3)));
        Object object = textArray4[0];
        ModeSetting setting30 = this.settingMode;
        String text5 = (String)object;
        setting30.setSelected(text5);
        String[] textArray5 = textArray4;
        this.settingMode.applyStringArray2(textArray5);
        HashMap entryMap3 = this.entryMap;
        this.placedBlocks = new HashMap(entryMap3);
        this.updateState2();
        String settingFly2 = this.lastFlyMode;
        String[] textArray6 = new String[0];
        Path path = Paths.get(settingFly2, textArray6);
        LinkOption[] linkOptionArray = new LinkOption[0];
        if (!Files.exists(path, linkOptionArray)) {
            String settingFly3 = this.lastFlyMode;
            String[] textArray7 = new String[0];
            Path path2 = Paths.get(settingFly3, textArray7);
            FileAttribute[] fileAttributeArray = new FileAttribute[0];

            try {
                Files.createDirectories(path2, fileAttributeArray);
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(3);
        this.registerEvent(20);
        this.registerEvent(21);
        this.registerEvent(43);
        this.registerEvent(18);
        this.registerEvent(32);
        this.registerEvent(26);
        this.registerEvent(25);
        this.registerEvent(40);
        this.registerEvent(7);
        this.registerEvent(34);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
        if (minecraft.thePlayer != null) {
            this.getFlyModeHandler().updateState3();
        }

        minecraft.getTimer().scaleFactor2 = 1.0F;
        this.onBlinkModeAction();
    }

    @Override
    public void onPreDisable() {
        this.timer.reset();
        minecraft.getTimer().scaleFactor2 = 1.0F;
        if (minecraft.thePlayer != null) {
            this.getFlyModeHandler().updateState2();
        }

        this.reachDistance = 0.0;
        this.timestampMillis = System.currentTimeMillis();
        if (minecraft.thePlayer != null) {
            double offset = minecraft.thePlayer.posY - 2.6;
            float value = minecraft.thePlayer.rotationYaw - 180.0F;
            this.fakeDragon.setLocationAndAngles(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ, value, minecraft.thePlayer.rotationPitch);
        }
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        if (minecraft.theWorld == null || minecraft.thePlayer == null) {
            this.setActive(false);
        }

        if (this.settingBlinkMode.getSelected().equals("OnlyIncoming") || this.settingBlinkMode.getSelected().equals("Both")) {
            packet.setCancelled(true);
        }

        this.getFlyModeHandler().onSendPacket(packet);
    }

    @Override
    public void onRotation(EventRotation event) {
        this.getFlyModeHandler().onRotation2(event);
        if (!BlockCache.canExecute()) {
            event.setScaleFactor(MoveUtil.getFloat2());
        }
    }

    @Override
    public void onClick(EventClick event) {
        this.getFlyModeHandler().onClick(event);
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        this.getFlyModeHandler().onPacket2(packet);
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (!BlockCache.canExecute()) {
            event.setScaleFactor(MoveUtil.getFloat2());
        }

        this.getFlyModeHandler().onRotation(event);
    }

    @Override
    public void onBlockCollision(EventBlockCollision event) {
        this.getFlyModeHandler().onBlockCollision(event);
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (this.settingBlinkMode.getSelected().equals("OnlyOutgoing")) {
            packet.setCancelled(true);
        }

        this.getFlyModeHandler().onSendPacket2(packet);
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        this.getFlyModeHandler().onMotionUpdate(event);
        double doubleEntry = this.getDouble(this.positionVec.xCoord, minecraft.thePlayer.posX);
        double doubleEntry2 = this.getDouble(this.positionVec.zCoord, minecraft.thePlayer.posZ);
        this.reachDistance = this.reachDistance + Math.sqrt(doubleEntry * doubleEntry + doubleEntry2 * doubleEntry2);
        this.positionVec = new Vec3(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ);
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingDistanceFlown.isEnabled()) {
            String text = "flown "
                + this.speedFormat.format(this.reachDistance)
                + ";  Seconds: "
                + (System.currentTimeMillis() - this.timestampMillis) / 1000L;
            minecraft.fontRendererObj
                .getIndex(
                    text, (int)((float)scaledResolution.getScaledWidth() / 2.0F - (float)minecraft.fontRendererObj.getIndex(text) / 2.0F), scaledResolution.getScaledHeight() - 65, -1
                );
        }

        RenderUtils.processAction();
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        this.getFlyModeHandler().onMoveEntity(event);
    }

    @Override
    public void onMove(EventMove event) {
        this.getFlyModeHandler().onMove(event);
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        this.getFlyModeHandler().onPacket(packet);
    }

    @Override
    public void onPostMotion() {
        this.getFlyModeHandler().processAction();
    }

    @Override
    public void onPreUpdateState() {
        this.getFlyModeHandler().processAction(4556, '\u0000', '\u0000');
        EntityEnderdragon settingFliegen2 = this.fakeDragon;
        float value = minecraft.thePlayer.rotationYaw - 180.0F;
        settingFliegen2.processFloat4(value, minecraft.thePlayer.rotationPitch);
    }

    @Override
    public void onPostUpdateState() {
        this.getFlyModeHandler().processChar();
        this.processAction();
    }

    @Override
    public void onRunTick() {
        this.applyString4(this.settingMode.getSelected());
    }

    @Override
    public void onPreTick() {
        this.getFlyModeHandler().updateState();
    }

    private boolean isVulcanCollisionYVisible() {
        return this.settingMode.getSelected().equals("Vulcan");
    }

    private boolean isCollisionYVisible() {
        return this.settingMode.getSelected().equals("GroundCollision");
    }

    private boolean isModeVisible() {
        return this.settingMode.getSelected().equals("Vanilla1");
    }

    private boolean isTimerSpeedEndVisible() {
        return this.settingMode.getSelected().equals("Vulcan");
    }

    private void processAction() {
        if (this.settingSpigotFlyKickBypass.isEnabled() && minecraft.theWorld != null) {
            double offset = 0.0;
            Minecraft mc;
            if ((int)minecraft.thePlayer.posY > 0) {
                mc = minecraft;
            } else {
                mc = minecraft;
            }

            double posY = mc.thePlayer.posY;
            if (posY > offset) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C04PacketPlayerPosition(minecraft.thePlayer.posX, posY, minecraft.thePlayer.posZ, true));
            } else {
                NetHandlerPlayClient netHandlerPlayClient = minecraft.thePlayer.netHandlerPlayClient;
                C04PacketPlayerPosition c04PacketPlayerPosition = new C04PacketPlayerPosition(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ, true);
                netHandlerPlayClient.handlePacket2(c04PacketPlayerPosition);
            }

            if (offset < minecraft.thePlayer.posY) {
                minecraft.thePlayer.netHandlerPlayClient.handlePacket2(new C04PacketPlayerPosition(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ, true));
            } else {
                minecraft.thePlayer
                    .netHandlerPlayClient
                    .handlePacket2(new C04PacketPlayerPosition(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ, true));
                this.timer.reset();
            }
        }
    }

    private static String[] getStringArray(int index) {
        return new String[index];
    }

    private static Boolean getBoolean(ModeOptionConfigurable advancedListener) {
        return true;
    }

    public synchronized void updateState() {
        HashMap hashMap = new HashMap(this.placedBlocks);
        HashSet hashSet = new HashSet();
        File[] fileArray = new File(this.lastFlyMode).listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                String name = file.getName();
                if (name.contains(".json")) {
                    String text = name.substring(0, Math.max(name.length() - 5, 0));
                    hashSet.add(text);
                    if (this.entryMap2.containsKey(text)) {
                        byte[] bArray = (byte[])this.entryMap2.get(text);
                        byte[] bArray2 = new byte[0];

                        byte[] bArray3;
                        try {
                            bArray3 = MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(file.toPath()));
                        } catch (IOException | NoSuchAlgorithmException iOExceptionNoSuchAlgorithmException) {
                            throw new RuntimeException(iOExceptionNoSuchAlgorithmException);
                        }

                        if (Arrays.equals(bArray3, bArray)) {
                            hashMap.put(text, this.entryMap.get(text));
                        } else {
                            FlyCustomMode flyCustomMode = this.getFlyCustomMode(file, false);
                            if (flyCustomMode != null) {
                                hashMap.put(flyCustomMode.getText(), flyCustomMode);
                                this.entryMap2.put(text, bArray3);
                            }
                        }
                    } else {
                        FlyCustomMode flyCustomMode2 = this.getFlyCustomMode(file, true);
                        if (flyCustomMode2 != null) {
                            hashMap.put(flyCustomMode2.getText(), flyCustomMode2);
                        }
                    }
                }
            }
        }

        this.entryMap.clear();
        this.entryMap.putAll(hashMap);

        for (Object object : this.entryMap2.keySet()) {
            String text2 = (String)object;
            if (!hashSet.contains(text2)) {
                this.entryMap2.remove(text2);
            }
        }

        hashSet.clear();
        hashMap.clear();
        byte state = ((byte)(this.entryMap.containsKey(this.settingMode.getSelected()) ? 1 : 0));
        byte state2 = (byte)state;
        String[] textArray = ((String[])(this.entryMap.keySet().stream().sorted().toArray(index -> getStringArray2(index))));
        if (state2 == 0) {
            this.settingMode.setSelected(textArray[0]);
        }

        this.settingMode.applyStringArray2(textArray);
    }

    private FlyCustomMode getFlyCustomMode(File file, boolean enabled) {
        String text = file.getName().substring(0, Math.max(file.getName().length() - 5, 0));
        JsonArray jsonArray = ConfigManager.getJsonArray(this.lastFlyMode, file.getName(), (Runnable)null);
        FlyCustomMode flyCustomMode = new FlyCustomMode(text, this);
        if (jsonArray == null) {
            JsonObject jsonObject = ConfigManager.getJsonObject(this.lastFlyMode, file.getName(), (Runnable)null);
            if (jsonObject == null) {
                return null;
            }

            modeListTracker.processAction(new Module[]{flyCustomMode.auxiliaryModuleAdapter});
            if (!modeListTracker.checkJsonObject(jsonObject, true, object -> getBoolean((ModeOptionConfigurable)object))) {
                System.out.println("Failed to read fly");
                return null;
            }
        } else {
            jsonArray.forEach(jsonElement -> updateFlyCustomMode(flyCustomMode, jsonElement));
        }

        if (!enabled) {
            return flyCustomMode;
        } else {
            try {
                this.entryMap2.put(text, MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(file.toPath())));
                return flyCustomMode;
            } catch (IOException | NoSuchAlgorithmException iOExceptionNoSuchAlgorithmException) {
                throw new RuntimeException(iOExceptionNoSuchAlgorithmException);
            }
        }
    }

    private static String[] getStringArray2(int index) {
        return new String[index];
    }

    private static void updateFlyCustomMode(FlyCustomMode flyCustomMode, JsonElement jsonElement) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String asString = asJsonObject.get("Name").getAsString();
        Setting setting = (Setting)flyCustomMode.getBooleanSettingEntry().getEntryMap2().get(asString);

        try {
            SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
            setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
        } catch (Exception e) {
            PrintStream err = System.err;
            StringBuilder sb = new StringBuilder().append("Failed to load Setting: ");
            err.println(sb.append(setting.getString()).toString());
        }
    }

    private boolean isVerusSpeedVisible() {
        return this.settingMode.getSelected().equals("OldVerus");
    }

    private boolean isModeVisible2() {
        return this.settingMode.getSelected().equals("AirJump");
    }

    private FlyModeHandler getFlyModeHandler() {
        return ((FlyModeHandler)(this.entryMap.getOrDefault(this.settingMode.getSelected(), this.airJumpHandler)));
    }

    private void applyFlyModeHandler(FlyModeHandler flyModeHandler) {
        this.entryMap.put(flyModeHandler.getText(), flyModeHandler);
    }

    public void updateState2() {
        this.entryMap.clear();
        this.entryMap.putAll(this.placedBlocks);
        this.entryMap2.clear();
        File[] fileArray = new File(this.lastFlyMode).listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.getName().contains(".json")) {
                    FlyCustomMode flyCustomMode = this.getFlyCustomMode(file, true);
                    if (flyCustomMode != null) {
                        this.entryMap.put(flyCustomMode.getText(), flyCustomMode);
                    }
                }
            }
        }

        String[] textArray = ((String[])(this.entryMap.keySet().stream().sorted().toArray(index -> getStringArray(index))));
        if (this.entryMap.get(this.settingMode.getSelected()) == null) {
            this.settingMode.setSelected(textArray[0]);
        }

        this.settingMode.applyStringArray2(textArray);
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute5() {
        return activeState;
    }

    private boolean isVerusSpeedCombinedVisible() {
        return this.settingMode.getSelected().equals("VerusCombined");
    }

    public static BooleanSetting getBooleanSetting(Fly fly) {
        return fly.settingEnderDragonSound;
    }

    private double getDouble(double offset, double offset2) {
        return offset - offset2;
    }

    private void onBlinkModeAction() {
        if (this.fakeDragon != null && minecraft.theWorld != null && this.settingEnderDragon.isEnabled()) {
            minecraft.theWorld.spawnEntityInWorld(this.fakeDragon);
        }
    }

    private boolean isModeVisible3() {
        return this.settingMode.getSelected().equals("AirJump");
    }

    private boolean isTimerSpeedVisible() {
        return this.settingMode.getSelected().equals("Vulcan");
    }

    private boolean isVerusSpeedVisible2() {
        return this.settingMode.getSelected().equals("OldVerus");
    }

    private boolean isCubeCraftSpeedVisible() {
        return this.settingMode.getSelected().equals("CubeCraft");
    }

    private boolean isBoostVisible() {
        return this.settingMode.getSelected().equals("OldGomme");
    }

    private boolean isHeightVisible() {
        return this.settingMode.getSelected().equals("OldGomme");
    }

    private boolean isVanillaSpeedVisible() {
        return this.settingMode.getSelected().equals("Vanilla1");
    }

    private boolean isVulcanCollisionYVisible2() {
        return this.settingMode.getSelected().equals("Vulcan");
    }

    private boolean isBoostVisible2() {
        return this.settingMode.getSelected().equals("IntaveBoat");
    }

    private boolean isCubeCraftStopDurationVisible() {
        return this.settingMode.getSelected().equals("CubeCraft");
    }

    public static boolean canExecute6() {
        return !canExecute5();
    }
}
