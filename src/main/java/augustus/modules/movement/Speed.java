package augustus.modules.movement;

import augustus.modes.movement.SpeedAngleStrafeMode;
import augustus.modes.movement.SpeedCubeCraftMode;
import augustus.modes.movement.SpeedCustomMode;
import augustus.modes.movement.SpeedDojoMode;
import augustus.modes.movement.SpeedEntitySpeedMode;
import augustus.modes.movement.SpeedIntaveMode;
import augustus.modes.movement.SpeedInteractMode;
import augustus.modes.movement.SpeedKarhuMode;
import augustus.modes.movement.SpeedLegitHopMode;
import augustus.modes.movement.SpeedMatrixMode;
import augustus.modes.movement.SpeedModeHandler;
import augustus.modes.movement.SpeedOldHypixelMode;
import augustus.modes.movement.SpeedOldMatrixMode;
import augustus.modes.movement.SpeedOldMinemoraMode;
import augustus.modes.movement.SpeedOldMineplexMode;
import augustus.modes.movement.SpeedOldNCPMode;
import augustus.modes.movement.SpeedSentryMode;
import augustus.modes.movement.SpeedStrafeMode;
import augustus.modes.movement.SpeedTimeManipulationMode;
import augustus.modes.movement.SpeedVBhopMode;
import augustus.modes.movement.SpeedVOnGroundMode;
import augustus.modes.movement.SpeedVerusHopMode;
import augustus.modes.movement.SpeedVerusLowHopMode;
import augustus.modes.movement.SpeedVulcanHopMode;
import augustus.modes.movement.SpeedVulcanLowHopMode;
import augustus.modes.movement.SpeedVulcanOngroundMode;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSendPacket;
import client.event.EventSlowDown;
import client.event.EventStep;
import client.event.MotionUpdateListener;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RotationListener;
import client.event.SlowDownListener;
import client.event.StepListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeOptionConfigurable;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import client.util.BooleanSupplier;
import client.util.ConfigManager;
import client.util.ModeListTracker;
import client.util.MoveUtil;
import client.util.OnlineConfigsManager;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.BlockCache;

public class Speed
extends Module
implements StepListener,
RotationListener,
TickListener,
MoveEntityListener,
MoveListener,
UpdateStateListener,
MotionUpdateListener,
PacketListener,
SlowDownListener,
NetworkPacketListener {
    public ModeSetting settingMode;
    public NumberSetting settingVanillaSpeed;
    public NumberSetting settingDojoSpeed;
    public NumberSetting settingHeight;
    public BooleanSetting settingDamageBoost;
    public NumberSetting settingDMGSpeed;
    public BooleanSetting settingStrafe;
    public NumberSetting settingTimeManipulationTimer;
    public NumberSetting settingTimeManipulationTPTicks;
    public NumberSetting settingInteractSpeed;
    public BooleanSetting settingMouseSensi;
    public BooleanSetting settingAutoJump;
    public BooleanSetting settingOnlyWhileMove;
    public NumberSetting settingAdditionalSpeed;
    public BooleanSetting settingCorrectMovement;
    public HashMap entryMap;
    public HashMap entitySpeedMap;
    public ConcurrentHashMap entryMap3;
    public String lastSpeedMode;
    public SpeedVOnGroundMode vOnGroundHandler;
    public static ModeListTracker modeListTracker = new ModeListTracker();
    public static String displayName2;

    public Speed() {
        super("Speed", "Schnell", new Color(74, 133, 93), Category.MOVEMENT);
        String[] textArray = new String[0];
        this.settingMode = new ModeSetting(this, "Mode", "", textArray);
        NumberSetting numberSetting = new NumberSetting(this, "VanillaSpeed", 1.0, 0.0, 10.0, 2);
        NumberSetting setting = numberSetting;
        String text = "Speed";
        NumberSetting numberSetting2 = setting.getNumberSetting(text);
        BooleanSupplier booleanSupplier = () -> this.isVanillaSpeedVisible();
        NumberSetting setting2 = numberSetting2;
        BooleanSupplier booleanSupplier2 = booleanSupplier;
        this.settingVanillaSpeed = setting2.getNumberSetting(booleanSupplier2);
        NumberSetting numberSetting4 = new NumberSetting(this, "DojoSpeed", 5.0, 1.0, 10.0, 2);
        BooleanSupplier booleanSupplier3 = () -> this.isDojoSpeedVisible();
        NumberSetting setting3 = numberSetting4;
        BooleanSupplier booleanSupplier4 = booleanSupplier3;
        this.settingDojoSpeed = setting3.getNumberSetting(booleanSupplier4);
        NumberSetting numberSetting6 = new NumberSetting(this, "Height", 1.0, 0.0, 5.0, 2);
        BooleanSupplier booleanSupplier5 = () -> this.isHeightVisible2();
        NumberSetting setting4 = numberSetting6;
        BooleanSupplier booleanSupplier6 = booleanSupplier5;
        this.settingHeight = setting4.getNumberSetting(booleanSupplier6);
        BooleanSetting booleanSetting = new BooleanSetting(this, "DamageBoost", false);
        BooleanSupplier booleanSupplier7 = () -> this.isHeightVisible();
        Setting setting5 = booleanSetting;
        BooleanSupplier booleanSupplier8 = booleanSupplier7;
        this.settingDamageBoost = (BooleanSetting)setting5.getSetting(booleanSupplier8);
        NumberSetting numberSetting8 = new NumberSetting(this, "DMGSpeed", 1.0, 0.0, 2.0, 2);
        BooleanSupplier booleanSupplier9 = () -> this.isDMGSpeedVisible2();
        NumberSetting setting7 = numberSetting8;
        BooleanSupplier booleanSupplier10 = booleanSupplier9;
        this.settingDMGSpeed = setting7.getNumberSetting(booleanSupplier10);
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "Strafe", false);
        BooleanSupplier booleanSupplier11 = () -> this.isDMGSpeedVisible();
        Setting setting8 = booleanSetting2;
        BooleanSupplier booleanSupplier12 = booleanSupplier11;
        this.settingStrafe = (BooleanSetting)setting8.getSetting(booleanSupplier12);
        NumberSetting numberSetting10 = new NumberSetting(this, "TimeManipulationTimer", 1.0, 0.0, 10.0, 2);
        NumberSetting setting10 = numberSetting10;
        String text2 = "TimerSpeed";
        NumberSetting numberSetting11 = setting10.getNumberSetting(text2);
        BooleanSupplier booleanSupplier13 = () -> this.isTimeManipulationTimerVisible();
        NumberSetting setting11 = numberSetting11;
        BooleanSupplier booleanSupplier14 = booleanSupplier13;
        this.settingTimeManipulationTimer = setting11.getNumberSetting(booleanSupplier14);
        NumberSetting numberSetting13 = new NumberSetting(this, "TimeManipulationTPTicks", 0.0, 0.0, 10.0, 0);
        NumberSetting setting12 = numberSetting13;
        String text3 = "TPTicks";
        NumberSetting numberSetting14 = setting12.getNumberSetting(text3);
        BooleanSupplier booleanSupplier15 = () -> this.isTimeManipulationTPTicksVisible();
        NumberSetting setting13 = numberSetting14;
        BooleanSupplier booleanSupplier16 = booleanSupplier15;
        this.settingTimeManipulationTPTicks = setting13.getNumberSetting(booleanSupplier16);
        NumberSetting numberSetting16 = new NumberSetting(this, "InteractSpeed", 1.0, 1.0, 10.0, 0);
        BooleanSupplier booleanSupplier17 = () -> this.isInteractSpeedVisible2();
        NumberSetting setting14 = numberSetting16;
        BooleanSupplier booleanSupplier18 = booleanSupplier17;
        this.settingInteractSpeed = setting14.getNumberSetting(booleanSupplier18);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "MouseSensi", true);
        BooleanSupplier booleanSupplier19 = () -> this.isInteractSpeedVisible3();
        Setting setting15 = booleanSetting3;
        BooleanSupplier booleanSupplier20 = booleanSupplier19;
        this.settingMouseSensi = (BooleanSetting)setting15.getSetting(booleanSupplier20);
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "AutoJump", true);
        BooleanSupplier booleanSupplier21 = () -> this.isInteractSpeedVisible();
        Setting setting17 = booleanSetting4;
        BooleanSupplier booleanSupplier22 = booleanSupplier21;
        this.settingAutoJump = (BooleanSetting)setting17.getSetting(booleanSupplier22);
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "OnlyWhileMove", true);
        BooleanSupplier booleanSupplier23 = () -> this.isInteractSpeedVisible4();
        Setting setting19 = booleanSetting5;
        BooleanSupplier booleanSupplier24 = booleanSupplier23;
        this.settingOnlyWhileMove = (BooleanSetting)setting19.getSetting(booleanSupplier24);
        NumberSetting numberSetting18 = new NumberSetting(this, "AdditionalSpeed", 0.2, 0.0, 2.0, 4);
        BooleanSupplier booleanSupplier25 = () -> this.isAdditionalSpeedVisible();
        NumberSetting setting21 = numberSetting18;
        BooleanSupplier booleanSupplier26 = booleanSupplier25;
        this.settingAdditionalSpeed = setting21.getNumberSetting(booleanSupplier26);
        this.settingCorrectMovement = new BooleanSetting(this, "CorrectMovement", true);
        HashMap hashMap = new HashMap();
        this.entryMap = hashMap;
        this.entryMap3 = new ConcurrentHashMap();
        StringBuilder stringBuilder = new StringBuilder();
        String text4 = OnlineConfigsManager.text;
        StringBuilder sb = stringBuilder.append(text4);
        String separator = File.separator;
        StringBuilder sb2 = sb.append(separator).append("motionmodifier");
        String separator2 = File.separator;
        this.lastSpeedMode = sb2.append(separator2).append("speed").toString();
        SpeedVOnGroundMode speedVOnGroundMode = new SpeedVOnGroundMode("VOnGround", this);
        this.vOnGroundHandler = speedVOnGroundMode;
        SpeedModeHandler speedModeHandler = speedVOnGroundMode;
        this.applySpeedModeHandler(speedModeHandler);
        SpeedModeHandler speedVBhopMode = new SpeedVBhopMode("VBhop", this);
        this.applySpeedModeHandler(speedVBhopMode);
        SpeedModeHandler speedOldMineplexMode = new SpeedOldMineplexMode("OldMineplex", this);
        this.applySpeedModeHandler(speedOldMineplexMode);
        SpeedModeHandler speedOldMatrixMode = new SpeedOldMatrixMode("OldMatrix", this);
        this.applySpeedModeHandler(speedOldMatrixMode);
        SpeedModeHandler speedMatrixMode = new SpeedMatrixMode("Matrix", this);
        this.applySpeedModeHandler(speedMatrixMode);
        SpeedModeHandler speedOldMinemoraMode = new SpeedOldMinemoraMode("OldMinemora", this);
        this.applySpeedModeHandler(speedOldMinemoraMode);
        SpeedModeHandler speedOldNCPMode = new SpeedOldNCPMode("OldNCP", this);
        this.applySpeedModeHandler(speedOldNCPMode);
        SpeedModeHandler speedCubeCraftMode = new SpeedCubeCraftMode("CubeCraft", this);
        this.applySpeedModeHandler(speedCubeCraftMode);
        SpeedModeHandler speedLegitHopMode = new SpeedLegitHopMode("LegitHop", this);
        this.applySpeedModeHandler(speedLegitHopMode);
        SpeedModeHandler speedDojoMode = new SpeedDojoMode("Dojo", this);
        this.applySpeedModeHandler(speedDojoMode);
        SpeedModeHandler speedStrafeMode = new SpeedStrafeMode("Strafe", this);
        this.applySpeedModeHandler(speedStrafeMode);
        SpeedModeHandler speedSentryMode = new SpeedSentryMode("Sentry", this);
        this.applySpeedModeHandler(speedSentryMode);
        SpeedModeHandler speedTimeManipulationMode = new SpeedTimeManipulationMode("TimeManipulation", this);
        this.applySpeedModeHandler(speedTimeManipulationMode);
        SpeedModeHandler speedOldHypixelMode = new SpeedOldHypixelMode("OldHypixel", this);
        this.applySpeedModeHandler(speedOldHypixelMode);
        SpeedModeHandler speedIntaveMode = new SpeedIntaveMode("Intave", this);
        this.applySpeedModeHandler(speedIntaveMode);
        SpeedModeHandler speedVulcanLowHopMode = new SpeedVulcanLowHopMode("VulcanLowHop", this);
        this.applySpeedModeHandler(speedVulcanLowHopMode);
        SpeedModeHandler speedVulcanOngroundMode = new SpeedVulcanOngroundMode("VulcanOnground", this);
        this.applySpeedModeHandler(speedVulcanOngroundMode);
        SpeedModeHandler speedVulcanHopMode = new SpeedVulcanHopMode("VulcanHop", this);
        this.applySpeedModeHandler(speedVulcanHopMode);
        SpeedModeHandler auxiliarySpeedVerusMode = new SpeedVerusHopMode("Verus", this);
        this.applySpeedModeHandler(auxiliarySpeedVerusMode);
        SpeedModeHandler speedVerusLowHopMode = new SpeedVerusLowHopMode("VerusLowHop", this);
        this.applySpeedModeHandler(speedVerusLowHopMode);
        SpeedModeHandler speedInteractMode = new SpeedInteractMode("Interact", this);
        this.applySpeedModeHandler(speedInteractMode);
        SpeedModeHandler speedKarhuMode = new SpeedKarhuMode("Karhu", this);
        this.applySpeedModeHandler(speedKarhuMode);
        SpeedModeHandler speedAngleStrafeMode = new SpeedAngleStrafeMode("45Degree", this);
        this.applySpeedModeHandler(speedAngleStrafeMode);
        SpeedModeHandler speedEntitySpeedMode = new SpeedEntitySpeedMode("EntitySpeed", this);
        this.applySpeedModeHandler(speedEntitySpeedMode);
        Set set = this.entryMap.keySet();
        String[] textArray2 = new String[0];
        String[] textArray3 = ((String[])(set.toArray(textArray2)));
        Object object = textArray3[0];
        ModeSetting setting22 = this.settingMode;
        String text6 = (String)object;
        setting22.setSelected(text6);
        String[] textArray4 = textArray3;
        this.settingMode.applyStringArray2(textArray4);
        HashMap entryMap2 = this.entryMap;
        this.entitySpeedMap = new HashMap(entryMap2);
        this.updateState2();
        String settingSpeed2 = this.lastSpeedMode;
        String[] textArray5 = new String[0];
        Path path = Paths.get(settingSpeed2, textArray5);
        LinkOption[] linkOptionArray = new LinkOption[0];
        if (!Files.exists(path, linkOptionArray)) {
            String settingSpeed3 = this.lastSpeedMode;
            String[] textArray6 = new String[0];
            Path path2 = Paths.get(settingSpeed3, textArray6);
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
        this.registerEvent(0, 1);
        this.registerEvent(28);
        this.registerEvent(40);
        this.registerEvent(20);
        this.registerEvent(21);
        this.registerEvent(43);
        this.registerEvent(18, 1);
        this.registerEvent(26, 1);
        this.registerEvent(41);
        this.registerEvent(25);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onPreDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
    }

    @Override
    public void onRotation(EventRotation event) {
        this.getSpeedModeHandler().onRotation2(event);
        if (minecraft.thePlayer.fallDistance > 4.0F
        && !this.settingMode.getSelected().equals("LegitHop")) {
            event.cancel();
        }

        if (!BlockCache.canExecute()) {
            if (!this.settingCorrectMovement.isEnabled()) {
                event.setScaleFactor(MoveUtil.getFloat2());
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        this.getSpeedModeHandler().onPacket(packet);
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (!BlockCache.canExecute()) {
            event.setScaleFactor(MoveUtil.getFloat2());
        }

        this.getSpeedModeHandler().onRotation(event);
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        this.getSpeedModeHandler().onSendPacket(packet);
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        this.applyString4(this.settingMode.getSelected());
        this.getSpeedModeHandler().onMotionUpdate(event);
    }

    @Override
    public void onStep(EventStep event) {
        this.getSpeedModeHandler().onStep(event);
    }

    @Override
    public void onSlowDown(EventSlowDown event) {
        this.getSpeedModeHandler().onSlowDown(event);
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        this.getSpeedModeHandler().onMoveEntity(event);
    }

    @Override
    public void onMove(EventMove event) {
        this.getSpeedModeHandler().onMove(event);
    }

    @Override
    public void onCustomSlowDown(EventSlowDown event) {
        this.getSpeedModeHandler().onSlowDown(event);
    }

    @Override
    public void onPreUpdateState() {
        this.getSpeedModeHandler().processAction();
    }

    @Override
    public void onPostUpdateState() {
        this.getSpeedModeHandler().processChar();
    }

    @Override
    public void onPreTick() {
        this.getSpeedModeHandler().updateState();
    }

    private static String[] getStringArray(int index) {
        return new String[index];
    }

    private SpeedCustomMode getSpeedCustomMode(File file, boolean enabled) {
        String text = file.getName().substring(0, Math.max(file.getName().length() - 5, 0));
        JsonArray jsonArray = ConfigManager.getJsonArray(this.lastSpeedMode, file.getName(), (Runnable)null);
        SpeedCustomMode speedCustomMode = new SpeedCustomMode(text, this);
        if (jsonArray == null) {
            JsonObject jsonObject = ConfigManager.getJsonObject(this.lastSpeedMode, file.getName(), (Runnable)null);
            if (jsonObject == null) {
                return null;
            }

            modeListTracker.processAction(new Module[]{speedCustomMode.auxiliaryModuleAdapter});
            if (!modeListTracker.checkJsonObject(jsonObject, true, object -> getBoolean((ModeOptionConfigurable)object))) {
                System.out.println("Failed to read speed");
                return null;
            }
        } else {
            jsonArray.forEach(jsonElement -> updateSpeedCustomMode(speedCustomMode, jsonElement));
        }

        if (!enabled) {
            return speedCustomMode;
        } else {
            try {
                this.entryMap3.put(text, MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(file.toPath())));
                return speedCustomMode;
            } catch (IOException | NoSuchAlgorithmException iOExceptionNoSuchAlgorithmException) {
                throw new RuntimeException(iOExceptionNoSuchAlgorithmException);
            }
        }
    }

    public synchronized void updateState() {
        HashMap hashMap = new HashMap(this.entitySpeedMap);
        ArrayList arrayList = new ArrayList();
        new File(this.lastSpeedMode).listFiles();
        Iterator iterator = this.entryMap3.keySet().iterator();
        if (iterator.hasNext()) {
            String text = (String)iterator.next();
        }

        arrayList.clear();
        hashMap.clear();
        this.entryMap.containsKey(this.settingMode.getSelected());
        String[] textArray = ((String[])(this.entryMap.keySet().stream().sorted().toArray(index -> getStringArray2(index))));
    }

    public static void updateText(String text) {
        displayName2 = text;
    }

    private static String[] getStringArray2(int index) {
        return new String[index];
    }

    private static Boolean getBoolean(ModeOptionConfigurable advancedListener) {
        return true;
    }

    public static String getText2() {
        return displayName2;
    }

    private boolean isAdditionalSpeedVisible() {
        return this.settingMode.getSelected().equals("EntitySpeed");
    }

    private SpeedModeHandler getSpeedModeHandler() {
        return ((SpeedModeHandler)(this.entryMap.getOrDefault(this.settingMode.getSelected(), this.vOnGroundHandler)));
    }

    private boolean isDojoSpeedVisible() {
        return this.settingMode.getSelected().equals("Dojo");
    }

    private static void updateSpeedCustomMode(SpeedCustomMode speedCustomMode, JsonElement jsonElement) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String asString = asJsonObject.get("Name").getAsString();
        Setting setting = (Setting)speedCustomMode.getBooleanSettingEntry().getEntryMap2().get(asString);

        try {
            SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
            setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
        } catch (Exception e) {
            PrintStream err = System.err;
            StringBuilder sb = new StringBuilder().append("Failed to load Setting: ");
            err.println(sb.append(setting.getString()).toString());
        }
    }

    private void applySpeedModeHandler(SpeedModeHandler speedModeHandler) {
        this.entryMap.put(speedModeHandler.getText(), speedModeHandler);
    }

    public void updateState2() {
        boolean enabled = false;
        String[] textArray = null;
        boolean completed = false;
        this.entryMap.clear();
        this.entryMap.putAll(this.entitySpeedMap);
        File[] fileArray = new File(this.lastSpeedMode).listFiles();
        if (fileArray != null) {
            int length = fileArray.length;
            int index = 0;
            if (0 < length) {
                File file = fileArray[index];
                while (true) {
                    if (file.getName().contains(".json")) {
                        SpeedCustomMode speedCustomMode = this.getSpeedCustomMode(file, true);
                        if (speedCustomMode != null) {
                            this.entryMap.put(speedCustomMode.getText(), speedCustomMode);
                        }
                    }
                    if (++index >= length) {
                        enabled = this.entryMap.containsKey(this.settingMode.getSelected());
                        textArray = ((String[])(this.entryMap.keySet().stream().sorted().toArray(index2 -> getStringArray(index2))));
                        { completed = true; break; }
                    }
                    if (!completed) {
                        file = fileArray[index];
                    }
                }
            }
        }
        if (!completed) {
            enabled = this.entryMap.containsKey(this.settingMode.getSelected());
            textArray = ((String[])(this.entryMap.keySet().stream().sorted().toArray(index3 -> getStringArray(index3))));
        }


        if (!enabled) {
            this.settingMode.setSelected(textArray[0]);
        }

        this.settingMode.applyStringArray2(textArray);
    }

    private boolean isInteractSpeedVisible() {
        return this.settingMode.getSelected().equals("45Degree");
    }

    private boolean isHeightVisible() {
        return this.settingMode.getSelected().equals("Verus");
    }

    private boolean isTimeManipulationTPTicksVisible() {
        return this.settingMode.getSelected().equals("TimeManipulation");
    }

    private boolean isInteractSpeedVisible2() {
        return this.settingMode.getSelected().equals("Interact");
    }

    private boolean isVanillaSpeedVisible() {
        return this.settingMode.getSelected().equals("VOnGround");
    }

    private boolean isInteractSpeedVisible3() {
        return this.settingMode.getSelected().equals("45Degree");
    }

    private boolean isHeightVisible2() {
        return this.settingMode.getSelected().equals("VBhop");
    }

    private boolean isDMGSpeedVisible() {
        return this.settingMode.getSelected().equals("VOnGround");
    }

    private boolean isDMGSpeedVisible2() {
        return this.settingMode.getSelected().equals("Verus");
    }

    private boolean isTimeManipulationTimerVisible() {
        return this.settingMode.getSelected().equals("TimeManipulation");
    }

    private boolean isInteractSpeedVisible4() {
        return this.settingMode.getSelected().equals("45Degree");
    }
}
