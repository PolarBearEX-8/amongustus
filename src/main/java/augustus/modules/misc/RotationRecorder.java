package augustus.modules.misc;

import client.Client;
import client.ClientGlobals;
import client.config.RotationExclusionStrategy;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventPacket;
import client.event.EventSilentMove;
import client.event.PacketListener;
import client.event.SilentMoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.GameSettings;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RotationRecorderTargetEntity;
import client.setting.Setting;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.ConfigManager;
import client.util.MinecraftTimer;
import client.util.OnlineConfigsManager;
import client.util.RecordedRotation;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.Vec3;

public class RotationRecorder extends Module implements TickListener, ClickListener, SilentMoveListener, PacketListener {
    public BooleanSetting settingCreateNewDatasetIfTargetIsNull;
    public BooleanSetting settingFrameRecording;
    public BooleanSetting settingRecordPredictions;
    public BooleanSetting settingRecordAura;
    public BooleanSetting settingKillAuraTarget;
    public NumberSetting settingSearchReach;
    public BooleanSetting settingOnlyOnClick;
    public NumberSetting settingYawFov;
    public NumberSetting settingPitchFov;
    public MultiSelectSetting settingTargets;
    public TimeHelper timer;
    public DataOutputStream dataOutputStream;
    public int tickDelay;
    public RecordedRotation customFloatEntry;
    public Rotation rotation;
    public HashSet entrySet;
    public JsonArray jsonArray;
    public Gson gson;
    public boolean expanded2;
    public static String displayName2;

    public RotationRecorder() {
        super("RotationRecorder", "DrehungsAufzeichner", new Color(252, 186, 3), Category.MISC);
        this.settingCreateNewDatasetIfTargetIsNull = new BooleanSetting(this, "Create new dataset if target is null", true);
        this.settingFrameRecording = new BooleanSetting(this, "FrameRecording", true);
        this.settingRecordPredictions = (BooleanSetting)new BooleanSetting(this, "Record Predictions", false).getSetting(() -> this.canExecute6());
        this.settingRecordAura = (BooleanSetting)new BooleanSetting(this, "Record Aura", false).getSetting(() -> this.canExecute5());
        this.settingKillAuraTarget = new BooleanSetting(this, "KillAuraTarget", true);
        this.settingSearchReach = new NumberSetting(this, "SearchReach", 7.0, 3.0, 10.0, 1).getNumberSetting(() -> this.isSearchReachVisible());
        this.settingOnlyOnClick = (BooleanSetting)new BooleanSetting(this, "OnlyOnClick", true).getSetting(() -> this.isSearchReachVisible2());
        this.settingYawFov = new NumberSetting(this, "YawFov", 90.0, 0.0, 180.0, 0).getNumberSetting(() -> this.isYawFovVisible());
        this.settingPitchFov = new NumberSetting(this, "PitchFov", 90.0, 0.0, 180.0, 0).getNumberSetting(() -> this.isPitchFovVisible());
        RotationRecorderTargetEntity[] rotationRecorderTargetEntityArray = RotationRecorderTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", rotationRecorderTargetEntityArray).getMultiSelectSetting(() -> this.isTargetsVisible());
        this.timer = new TimeHelper();
        this.dataOutputStream = (DataOutputStream)null;
        this.tickDelay = 0;
        this.customFloatEntry = new RecordedRotation();
        this.entrySet = new HashSet();
        this.jsonArray = (JsonArray)null;
        RotationExclusionStrategy rotationExclusionStrategy = new RotationExclusionStrategy(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        ExclusionStrategy[] exclusionStrategyArray = new ExclusionStrategy[1];
        exclusionStrategyArray[0] = rotationExclusionStrategy;
        this.gson = gsonBuilder.setExclusionStrategies(exclusionStrategyArray).create();
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(7);
        this.registerEvent(19);
        this.registerEvent(26);
    }

    @Override
    public void onDisable() {
        this.updateState3();
        minecraft.gameSettings.indexOffset35 = this.tickDelay;
    }

    @Override
    public void onPreDisable() {
        this.updateState3();
        this.tickDelay = minecraft.gameSettings.indexOffset35;
        minecraft.gameSettings.indexOffset35 = 60;
    }

    @Override
    public void onClick(EventClick event) {
        boolean pressed = minecraft.gameSettings.keyBindAttack.pressed;
        if (!pressed) {
            pressed = minecraft.gameSettings.keyBindAttack.getPressTime() > 0;
        }

        if (pressed) {
            this.timer.reset();
        }
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (packet.getPacket() instanceof S12PacketEntityVelocity) {
            int entityId = ((S12PacketEntityVelocity)packet.getPacket()).getEntityId();
            if (entityId == minecraft.thePlayer.getEntityId()) {
                this.expanded2 = true;
            }
        } else if (packet.getPacket() instanceof S27PacketExplosion) {
            S27PacketExplosion s27PacketExplosion = (S27PacketExplosion)packet.getPacket();
            if (Math.abs(s27PacketExplosion.getScaleFactor2()) > 0.0F) {
                if (Math.abs(s27PacketExplosion.getScaleFactor3()) > 0.0F) {
                    this.expanded2 = true;
                }
            }
        }
    }

    @Override
    public void onRunTick() {
        if (!this.settingFrameRecording.isEnabled()) {
            boolean enabled = this.settingFrameRecording.isEnabled();
            this.setActive4(enabled);
        }
    }

    @Override
    public void onUpdate() {
        Client.getClient().getClass();
        Client.getClient().getCopyOnWriteArrayListEntry().getCopyOnWriteArrayList().remove(this);
        Client.getClient().getCopyOnWriteArrayListEntry().getCopyOnWriteArrayList2().remove(this);
    }

    @Override
    public void onPostSilentMove() {
        if (this.settingFrameRecording.isEnabled()) {
            boolean enabled = this.settingFrameRecording.isEnabled();
            this.setActive4(enabled);
        }
    }

    private void updateState5() {
        try {
            DataOutputStream dataOutputStream2 = this.dataOutputStream;
            byte[] byteArray = getByteArray(this.jsonArray.toString().getBytes(StandardCharsets.UTF_8));
            dataOutputStream2.write(byteArray);
            this.dataOutputStream.flush();
            this.jsonArray = null;
        } catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static void updateText(String text) {
        displayName2 = text;
    }

    private EntityLivingBase getEntityLivingBase() {
        if (!minecraft.isIntegratedServerRunning()) {
            return null;
        } else if (this.timer.hasReached(1000L) && this.settingOnlyOnClick.isEnabled()) {
            return null;
        } else {
            Stream stream = minecraft.theWorld.loadedEntityList.stream().filter(object -> this.checkEntity((Entity)object));
            Comparator comparator = Comparator.comparingDouble(object2 -> this.getCoordinate((Entity)object2));
            Object[] itemArray = stream.sorted(comparator).toArray();
            return itemArray.length == 0 ? null : (EntityLivingBase)itemArray[0];
        }
    }

    private static byte[] getByteArray(byte[] bArray) {
        Deflater deflater = new Deflater(9, true);
        deflater.setInput(bArray);
        deflater.finish();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArray2 = new byte[1024];

        while (!deflater.finished()) {
            int index = deflater.deflate(bArray2);
            byteArrayOutputStream.write(bArray2, 0, index);
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void setActive4(boolean enabled) {
        EntityLivingBase livingEntity;
        if (this.settingKillAuraTarget.isEnabled()) {
            livingEntity = ClientGlobals.moduleManager.killAura.target;
        } else {
            livingEntity = this.getEntityLivingBase();
        }

        if (livingEntity == null) {
            if (this.settingCreateNewDatasetIfTargetIsNull.isEnabled()) {
                if (this.dataOutputStream != null) {
                    this.updateState3();
                }
            }
        } else {
            if (this.dataOutputStream == null) {
                this.updateState2();
            }

            if (enabled) {
                this.applyEntityLivingBase2(livingEntity);
            } else {
                this.applyEntityLivingBase2(livingEntity);
            }
        }
    }

    private boolean isSearchReachVisible() {
        return !this.settingKillAuraTarget.isEnabled();
    }

    public static byte[] getByteArray2(byte[] bArray) {
        try {
            Inflater inflater = new Inflater(true);
            inflater.setInput(bArray);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArray2 = new byte[1024];

            while (!inflater.finished()) {
                int index = inflater.inflate(bArray2);
                byteArrayOutputStream.write(bArray2, 0, index);
            }

            inflater.end();
            return byteArrayOutputStream.toByteArray();
        } catch (DataFormatException dataFormatException) {
            return null;
        }
    }

    private double getCoordinate(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else {
            double posX = entity.posX;
            double offset = entity.posY + (double)entity.getHeight2();
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            float scaleFactor2 = Client.getClient().getViaProtocolHelper().scaleFactor2;
            float[] floatArray = SimulatedPlayer.getFloatArray(posX, offset, posZ, scaleFactor1, scaleFactor2);
            return Math.sqrt((double)(floatArray[0] * floatArray[0] + floatArray[1] * floatArray[1]));
        }
    }

    private void updateState2() {
        this.jsonArray = new JsonArray();
        this.expanded2 = false;
        String text = OnlineConfigsManager.text + File.separator + "rotation";
        ConfigManager.applyString2(text);

        try {
            File file = new File(text + File.separator + "data" + System.currentTimeMillis());
            file.createNewFile();
            this.dataOutputStream = new DataOutputStream(Files.newOutputStream(file.toPath()));
        } catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    private boolean checkEntity(Entity entity) {
        boolean enabled = entity instanceof EntityLivingBase;
        if (!enabled) {
            return enabled;
        } else {
            boolean selected = RotationRecorderTargetEntity.PLAYER.isSelected();
            boolean selected2 = RotationRecorderTargetEntity.MOB.isSelected();
            boolean selected3 = RotationRecorderTargetEntity.ANIMAL.isSelected();
            boolean selected4 = RotationRecorderTargetEntity.VILLAGER.isSelected();
            boolean selected5 = RotationRecorderTargetEntity.ARMOR_STAND.isSelected();
            boolean selected6 = RotationRecorderTargetEntity.INVISIBLE.isSelected();
            boolean selected7 = RotationRecorderTargetEntity.DEAD.isSelected();
            if (!ChatUtil.checkEntity3(entity, selected, selected2, selected3, selected4, selected5, selected6, true, true, true, selected7, true)) {
                return false;
            } else {
                float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
                float scaleFactor2 = Client.getClient().getViaProtocolHelper().scaleFactor2;
                float[] scaleFactorArray = SimulatedPlayer.getScaleFactorArray(entity, scaleFactor1, 263465940, scaleFactor2, 6342);
                return entity instanceof EntityPlayerSP
                    ? false
                    : (double)minecraft.thePlayer.getDistanceToEntity(entity) < this.settingSearchReach.getValue()
                        && (double)Math.abs(scaleFactorArray[0]) <= this.settingYawFov.getValue()
                        && (double)Math.abs(scaleFactorArray[1]) <= this.settingPitchFov.getValue();
            }
        }
    }

    public static String getText2() {
        return displayName2;
    }

    private void updateState3() {
        if (this.dataOutputStream != null) {
            this.updateState5();
            this.updateState();
            this.dataOutputStream = null;
            this.entrySet.clear();
        }
    }

    private void applyEntityLivingBase(EntityLivingBase livingEntity) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ticksExisted", minecraft.thePlayer.ticksExisted);
        jsonObject.addProperty("yaw", minecraft.thePlayer.rotationYaw);
        jsonObject.addProperty("pitch", minecraft.thePlayer.rotationPitch);
        jsonObject.addProperty("playerX", minecraft.thePlayer.posX);
        jsonObject.addProperty("playerY", minecraft.thePlayer.posY);
        jsonObject.addProperty("playerZ", minecraft.thePlayer.posZ);
        jsonObject.addProperty("targetX", livingEntity.posX);
        jsonObject.addProperty("targetY", livingEntity.posY);
        jsonObject.addProperty("targetZ", livingEntity.posZ);
        jsonObject.addProperty("applyVelocity", this.expanded2);
        jsonObject.addProperty("AI_MOVESPEED", minecraft.thePlayer.getScaleFactor6());
        jsonObject.addProperty("jumpMovementFactor", minecraft.thePlayer.scaleFactor10);
        jsonObject.addProperty("motionX", minecraft.thePlayer.motionX);
        jsonObject.addProperty("motionY", minecraft.thePlayer.motionY);
        jsonObject.addProperty("motionZ", minecraft.thePlayer.motionZ);
        jsonObject.addProperty("isSprinting", minecraft.thePlayer.isRiding());
        jsonObject.add("entityState", this.gson.toJsonTree(livingEntity.backtrackRecord3.getObject3(0)));
        jsonObject.add("movementInput", this.gson.toJsonTree(minecraft.thePlayer.secondaryFloatEntry));
        jsonObject.add("playerState", this.gson.toJsonTree(minecraft.thePlayer.backtrackRecord5.getObject3(0)));
        jsonObject.add("targetState", this.gson.toJsonTree(livingEntity.backtrackRecord.getObject3(0)));
        this.jsonArray.add(jsonObject);
        this.expanded2 = false;
    }

    private boolean isSearchReachVisible2() {
        return !this.settingKillAuraTarget.isEnabled();
    }

    private void applyEntityLivingBase2(EntityLivingBase livingEntity) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ticksExisted", minecraft.thePlayer.ticksExisted);
        jsonObject.addProperty("yaw", minecraft.thePlayer.rotationYaw);
        jsonObject.addProperty("pitch", minecraft.thePlayer.rotationPitch);
        MinecraftTimer timer2 = minecraft.getTimer();
        Vec3 vec = minecraft.thePlayer.getVec32(timer2.scaleFactor6);
        jsonObject.addProperty("playerX", vec.xCoord);
        jsonObject.addProperty("playerY", vec.yCoord);
        jsonObject.addProperty("playerZ", vec.zCoord);
        MinecraftTimer timer3 = minecraft.getTimer();
        Vec3 vec2 = livingEntity.getVec32(timer3.scaleFactor6);
        jsonObject.addProperty("targetX", vec2.xCoord);
        jsonObject.addProperty("targetY", vec2.yCoord);
        jsonObject.addProperty("targetZ", vec2.zCoord);
        this.jsonArray.add(jsonObject);
    }

    private void updateState() {
        try {
            this.dataOutputStream.close();
        } catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    private boolean isTargetsVisible() {
        return !this.settingKillAuraTarget.isEnabled();
    }

    private boolean isPitchFovVisible() {
        return !this.settingKillAuraTarget.isEnabled();
    }

    private boolean isYawFovVisible() {
        return !this.settingKillAuraTarget.isEnabled();
    }

    private boolean canExecute5() {
        return !this.settingFrameRecording.isEnabled();
    }

    private boolean canExecute6() {
        return !this.settingFrameRecording.isEnabled();
    }
}
