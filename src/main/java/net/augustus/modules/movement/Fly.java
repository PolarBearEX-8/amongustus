package net.augustus.modules.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.events.EventMove;
import net.augustus.events.listeners.MotionListener;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.DirectoryWatcher;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.utils.file.ClientPaths;
import net.augustus.events.EventClick;
import net.augustus.settings.Setting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.render.RenderUtil;
import net.augustus.config.FeatureListJsonCodec;
import net.augustus.modules.modes.fly.Verus3FlyMode;
import net.augustus.modules.modes.fly.Verus1FlyMode;
import net.augustus.modules.modes.fly.Vanilla1FlyMode;
import net.augustus.modules.modes.fly.Verus2FlyMode;
import net.augustus.modules.modes.fly.VerusCombinedFlyMode;
import net.augustus.modules.modes.fly.UniversoCraftFlyMode;
import net.augustus.modules.modes.fly.MotionModifierFlyMode;
import net.augustus.modules.modes.fly.FlyMode;
import net.augustus.modules.modes.fly.AirJumpFlyMode;
import net.augustus.modules.modes.fly.CubeCraftFlyMode;
import net.augustus.modules.modes.fly.GrimFlyMode;
import net.augustus.modules.modes.fly.AirPlaceFlyMode;
import net.augustus.modules.modes.fly.Polar2FlyMode;
import net.augustus.modules.modes.fly.Minemora1FlyMode;
import net.augustus.modules.modes.fly.OldGommeFlyMode;
import net.augustus.modules.modes.fly.BedwarsPracticeFlyMode;
import net.augustus.modules.modes.fly.VulcanFlyMode;
import net.augustus.modules.modes.fly.GroundCollisionFlyMode;
import net.augustus.modules.modes.fly.Polar3FlyMode;
import net.augustus.modules.modes.fly.Vanilla2FlyMode;
import net.augustus.modules.modes.fly.IntaveBoatFlyMode;
import net.augustus.modules.modes.fly.OldVerusFlyMode;
import net.augustus.modules.modes.fly.PolarFlyMode;
import net.augustus.modules.modes.fly.Minemora2FlyMode;
import net.augustus.Augustus;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.UnaryFunction;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;

public class Fly
   extends Module
   implements ClickListener,
   SilentSlotListener,
   MoveListener,
   BlockBoundingBoxListener,
   MoveEntityListener,
   MoveInputListener,
   UpdateListener,
   MotionListener,
   RenderListener,
   PacketListener,
   PacketDelayListener,
   TickListener {
   private EntityDragon spawnedEnderDragon;
   public final StringValue mode;
   public final BooleanValue waitForDamage;
   public final BooleanValue autoJump;
   public final BooleanValue onGroundPacket;
   public final DoubleValue vanillaSpeed;
   public final DoubleValue vanillaSpeedHorizontal;
   public final DoubleValue vanillaSpeedVertical;
   public final DoubleValue verusSpeed;
   public final BooleanValue onlyOnGround;
   public final DoubleValue verusSpeedCombined;
   public final DoubleValue cubeCraftSpeed;
   public final DoubleValue cubeCraftStopDuration;
   public final DoubleValue collisionY;
   public final DoubleValue height;
   public final DoubleValue boost;
   public final BooleanValue onWater;
   public final BooleanValue onWeb;
   public final BooleanValue helpMessage;
   public final DoubleValue vulcanCollisionY;
   public final BooleanValue timer;
   public final DoubleValue timerSpeed;
   public final DoubleValue timerSpeedEnd;
   public final BooleanValue disableAura;
   public final BooleanValue cancelS08;
   public final StringValue blinkMode;
   private final BooleanValue distanceFlown;
   private final BooleanValue spigotFlyKickBypass;
   private final BooleanValue disableAtSetback;
   private final BooleanValue enderDragon;
   private final BooleanValue enderDragonSound;
   private final DecimalFormat distanceFormat;
   private Vec3 lastPosition;
   private double traveledDistance;
   private long flyStartTimeMs;
   private final TimeHelper timeHelper;
   private final HashMap modesByName;
   private final ConcurrentHashMap customModeHashes;
   private final HashMap builtinModes;
   private final AirJumpFlyMode airJumpFlyMode;
   private final String customModesDir;
   private static final FeatureListJsonCodec JSON_CODEC = new FeatureListJsonCodec();

   public Fly() {
       super("Fly", "Fliegen", new Color(123, 240, 156), Categorys.MOVEMENT);
      this.spawnedEnderDragon = null;
      this.mode = new StringValue(this, "Mode", "", new String[0]);
      this.waitForDamage = (BooleanValue)new BooleanValue(this, "WaitForDamage", true).withVisibility(() -> this.isWaitForDamageVisible());
      this.autoJump = (BooleanValue)new BooleanValue(this, "AutoJump", true).withVisibility(() -> this.isAutoJumpVisible());
      this.onGroundPacket = (BooleanValue)new BooleanValue(this, "OnGroundPacket", true).withVisibility(() -> this.isOnGroundPacketVisible());
      this.vanillaSpeed = new DoubleValue(this, "VanillaSpeed", 1.0, 0.1, 9.0, 1).visibleWhen(() -> this.isVanillaSpeedVisible());
      this.vanillaSpeedHorizontal = new DoubleValue(this, "VanillaSpeedHorizontal", 1.0, 0.1, 9.0, 1).visibleWhen(() -> this.isVanillaSpeedHorizontalVisible());
      this.vanillaSpeedVertical = new DoubleValue(this, "VanillaSpeedVertical", 0.4, 0.1, 9.0, 1).visibleWhen(() -> this.isVanillaSpeedVerticalVisible());
      this.verusSpeed = new DoubleValue(this, "VerusSpeed", 1.0, 0.1, 9.0, 1).visibleWhen(() -> this.isVerusSpeedVisible());
      this.onlyOnGround = (BooleanValue)new BooleanValue(this, "OnlyOnGround", true).withVisibility(() -> this.isOnlyOnGroundVisible());
      this.verusSpeedCombined = new DoubleValue(this, "VerusSpeedCombined", 1.0, 0.1, 9.0, 1).named("VerusSpeed").visibleWhen(() -> this.isVerusSpeedCombinedVisible());
      this.cubeCraftSpeed = new DoubleValue(this, "CubeCraftSpeed", 3.0, 0.0, 5.0, 2).visibleWhen(() -> this.isCubeCraftSpeedVisible());
      this.cubeCraftStopDuration = new DoubleValue(this, "CubeCraftStopDuration", 4.0, 0.0, 5.0, 1).visibleWhen(() -> this.isCubeCraftStopDurationVisible());
      this.collisionY = new DoubleValue(this, "CollisionY", 1.0, 0.0, 1.0, 2).visibleWhen(() -> this.isCollisionYVisible());
      DoubleValue heightSetting = new DoubleValue(this, "Height", 1.0, 1.0, 8.0, 2);
      SettingVisibilityCondition condition13 = () -> {
         return this.mode.getSelectedValue().equals("OldGomme");
      };
      this.height = heightSetting.visibleWhen(condition13);
      DoubleValue boostSetting = new DoubleValue(this, "Boost", 1.0, 1.0, 8.0, 2);
      this.boost = boostSetting.visibleWhen(() -> this.isBoostVisible());
      this.onWater = (BooleanValue)new BooleanValue(this, "OnWater", true).withVisibility(() -> this.isOnWaterVisible());
      this.onWeb = (BooleanValue)new BooleanValue(this, "OnWeb", true).withVisibility(() -> this.isOnWebVisible());
      this.helpMessage = (BooleanValue)new BooleanValue(this, "HelpMessage", true).withVisibility(() -> this.isHelpMessageVisible());
      boostSetting = new DoubleValue(this, "VulcanCollisionY", 1.0, 0.0, 1.0, 2);
      boostSetting = boostSetting.named("CollisionY");
      this.vulcanCollisionY = boostSetting.visibleWhen(() -> this.isVulcanCollisionYVisible());
      this.timer = (BooleanValue)new BooleanValue(this, "Timer", true).withVisibility(() -> this.isTimerVisible());
      boostSetting = new DoubleValue(this, "TimerSpeed", 5.0, 0.2, 10.0, 2);
      SettingVisibilityCondition condition20 = () -> {
         return this.mode.getSelectedValue().equals("Vulcan") && this.timer.getValue();
      };
      this.timerSpeed = boostSetting.visibleWhen(condition20);
      boostSetting = new DoubleValue(this, "TimerSpeedEnd", 1.0, 0.2, 50.0, 2);
      this.timerSpeedEnd = boostSetting.visibleWhen(() -> this.isTimerSpeedEndVisible());
      this.disableAura = new BooleanValue(this, "DisableAura", false);
      this.cancelS08 = new BooleanValue(this, "CancelS08", false);
      this.blinkMode = new StringValue(this, "BlinkMode", "None", new String[]{"None", "OnlyIncoming", "OnlyOutgoing", "Both"});
      this.distanceFlown = new BooleanValue(this, "DistanceFlown", false);
      this.spigotFlyKickBypass = new BooleanValue(this, "SpigotFlyKickBypass", false);
      this.disableAtSetback = new BooleanValue(this, "DisableAtSetback", false);
      this.enderDragon = (BooleanValue)new BooleanValue(this, "EnderDragon", false).withChangeListener(() -> this.despawnEnderDragon());
      BooleanValue booleanValue10 = new BooleanValue(this, "EnderDragonSound", false);
      BooleanValue enderDragon3 = this.enderDragon;
      this.enderDragonSound = (BooleanValue)booleanValue10.withVisibility(() -> enderDragon3.getValue());
      this.distanceFormat = new DecimalFormat("0.0");
      this.lastPosition = new Vec3(0.0, 0.0, 0.0);
      this.flyStartTimeMs = 0L;
      this.timeHelper = new TimeHelper();
      this.modesByName = new HashMap();
      this.customModeHashes = new ConcurrentHashMap();
      this.customModesDir = ClientPaths.CLIENT_DIR + File.separator + "motionmodifier" + File.separator + "fly";
      ClientFileUtil.ensureDirectory(this.customModesDir);
      this.airJumpFlyMode = new AirJumpFlyMode("AirJump", this);
      this.registerMode(this.airJumpFlyMode);
      this.registerMode(new Vanilla1FlyMode("Vanilla1", this));
      this.registerMode(new Vanilla2FlyMode("Vanilla2", this));
      this.registerMode(new GroundCollisionFlyMode("GroundCollision", this));
      this.registerMode(new Minemora1FlyMode("Minemora1", this));
      this.registerMode(new Minemora2FlyMode("Minemora2", this));
      this.registerMode(new OldVerusFlyMode("OldVerus", this));
      this.registerMode(new Verus1FlyMode("Verus1", this));
      this.registerMode(new Verus2FlyMode("Verus2", this));
      this.registerMode(new Verus3FlyMode("Verus3", this));
      this.registerMode(new VerusCombinedFlyMode("VerusCombined", this));
      this.registerMode(new UniversoCraftFlyMode("UniversoCraft", this));
      this.registerMode(new CubeCraftFlyMode("CubeCraft", this));
      this.registerMode(new OldGommeFlyMode("OldGomme", this));
      this.registerMode(new GrimFlyMode("GrimFly", this));
      this.registerMode(new VulcanFlyMode("Vulcan", this));
      this.registerMode(new IntaveBoatFlyMode("IntaveBoat", this));
      this.registerMode(new BedwarsPracticeFlyMode("BedwarsPractice", this));
      this.registerMode(new AirPlaceFlyMode("AirPlace", this));
      this.registerMode(new PolarFlyMode("PolarFly", this));
      this.registerMode(new Polar2FlyMode("PolarFly2", this));
      this.registerMode(new Polar3FlyMode("PolarFly3", this));
      this.builtinModes = new HashMap(this.modesByName);
      this.reloadCustomModes();
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(this.customModesDir);
      directoryWatcher = directoryWatcher.onCreate(obj -> this.onCustomModeCreated((Path)obj));
      directoryWatcher = directoryWatcher.onModify(obj2 -> this.onCustomModeModified((Path)obj2));
      Consumer consumer3 = obj3 -> {
         String name2 = ((Path)obj3).toFile().getName();
         short s = 14605;
         if (name2.endsWith(".json")) {
            String str3 = name2.substring(0, name2.length() - 5);
            this.modesByName.remove(str3);
            this.syncModeSettingOptions();
         }
      };
      directoryWatcher.onDelete(consumer3).register();
   }

   @Override
   public void onEnable() {
      this.timeHelper.reset();
      mc.getTimer().timerSpeed = 1.0F;
      if (mc.thePlayer != null) {
         mc.thePlayer.setSpeedInAir(0.02F);
         mc.thePlayer.setSpeedOnGround(0.1F);
         mc.thePlayer.stepHeight = 0.6F;
         this.lastPosition = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
      }

      if (mc.thePlayer != null) {
         this.getActiveMode().onEnable();
      }

      this.traveledDistance = 0.0;
      this.flyStartTimeMs = System.currentTimeMillis();
      if (mc.theWorld != null && mc.thePlayer != null && this.enderDragon.getValue()) {
         this.spawnedEnderDragon = new FlyDragonEntity(this, mc.theWorld);
         mc.theWorld.addEntityToWorld(-1337, this.spawnedEnderDragon);
         this.spawnedEnderDragon.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.posY - 2.6, mc.thePlayer.posZ, mc.thePlayer.rotationYaw - 180.0F, mc.thePlayer.rotationPitch);
      }
   }

   @Override
   public void onDisable() {
      mc.getTimer().timerSpeed = 1.0F;
      if (mc.thePlayer != null) {
         mc.thePlayer.setSpeedInAir(0.02F);
         mc.thePlayer.setSpeedOnGround(0.1F);
         mc.thePlayer.stepHeight = 0.6F;
      }

      if (mc.thePlayer != null) {
         this.getActiveMode().onDisable();
      }

      mc.getTimer().timerSpeed = 1.0F;
      this.despawnEnderDragon();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(3);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onTick() {
      this.setSuffix(this.mode.getSelectedValue());
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (mc.theWorld == null || mc.thePlayer == null || this.disableAtSetback.getValue() && event.getPacket() instanceof S08PacketPlayerPosLook) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> {
            this.setToggled(false);
         });
      }

      if (this.blinkMode.getSelectedValue().equals("OnlyIncoming") || this.blinkMode.getSelectedValue().equals("Both")) {
         event.setDelayed(true);
      }

      this.getActiveMode().onIncomingPacketDelay(event);
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.blinkMode.getSelectedValue().equals("OnlyOutgoing") || this.blinkMode.getSelectedValue().equals("Both")) {
         event.setDelayed(true);
      }

      this.getActiveMode().onOutgoingPacketDelay(event);
   }

   private double absDiff(double d, double d2) {
      double d3;
      if (d >= d2) {
         d3 = d - d2;
      } else {
         d3 = d2 - d;
      }

      return d3;
   }

   @Override
   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      this.getActiveMode().onBlockBoundingBox(event);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      this.getActiveMode().onMotion(event);
      double posX = mc.thePlayer.posX;
      double d = this.absDiff(this.lastPosition.xCoord, posX);
      double posZ = mc.thePlayer.posZ;
      double d2 = this.absDiff(this.lastPosition.zCoord, posZ);
      this.traveledDistance = this.traveledDistance + Math.sqrt(d * d + d2 * d2);
      this.lastPosition = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      this.getActiveMode().onMoveEntity(event);
   }

   @Override
   public void onPreUpdate() {
      this.getActiveMode().onPreUpdate();
      this.performSpigotKickBypass();
   }

   @Override
   public void onEarlyTick() {
      this.getActiveMode().onEarlyTick();
   }

   @Override
   public void onPostUpdate() {
      this.getActiveMode().onPostUpdate();
      if (this.enderDragon.getValue() && this.spawnedEnderDragon != null) {
         this.spawnedEnderDragon.lastTickPosX = mc.thePlayer.lastTickPosX;
         this.spawnedEnderDragon.lastTickPosY = mc.thePlayer.lastTickPosY - 2.6;
         this.spawnedEnderDragon.lastTickPosZ = mc.thePlayer.lastTickPosZ;
         this.spawnedEnderDragon.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - 2.6, mc.thePlayer.posZ);
         this.spawnedEnderDragon.setRotation(mc.thePlayer.rotationYaw - 180.0F, mc.thePlayer.rotationPitch);
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.distanceFlown.getValue()) {
         String str = "flown " + this.distanceFormat.format(this.traveledDistance) + ";  Seconds: " + (System.currentTimeMillis() - this.flyStartTimeMs) / 1000L;
         mc.fontRendererObj.drawString(str, (int)((float)sr.getScaledWidth() / 2.0F - (float)mc.fontRendererObj.getStringWidth(str) / 2.0F), sr.getScaledHeight() - 65, -1);
      }

      RenderUtil.resetGlColorState();
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.getActiveMode().onMoveInput(event);
   }

   @Override
   public void onMoveFlying(EventMove event) {
      this.getActiveMode().onMoveFlying(event);
      if (!BlockUtil.isScaffoldActiveAndValid()) {
         event.setYaw(MoveUtil.getMovementYaw());
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (!BlockUtil.isScaffoldActiveAndValid()) {
         event.setYaw(MoveUtil.getMovementYaw());
      }

      this.getActiveMode().onJumpMove(event);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.cancelS08.getValue() && event.getPacket() instanceof S08PacketPlayerPosLook) {
         event.cancel();
      }

      this.getActiveMode().onReadPacket(event);
   }

   @Override
   public void onPacketSend(EventPacket event) {
      this.getActiveMode().onSendPacket(event);
   }

   @Override
   public void onClick(EventClick event) {
      this.getActiveMode().onClick(event);
   }

   @Override
   public void onPostSilentSlot() {
      this.getActiveMode().onSilentSlot();
   }

   private FlyMode getActiveMode() {
      return (FlyMode)(this.modesByName.getOrDefault(this.mode.getSelectedValue(), this.airJumpFlyMode));
   }

   private void performSpigotKickBypass() {
      if (this.spigotFlyKickBypass.getValue()) {
         if (this.timeHelper.reached(1000L) && mc.thePlayer != null && mc.theWorld != null) {
            double d = 0.0;

            for (int i = (int)mc.thePlayer.posY; i > 0; i--) {
               Block block = mc.theWorld.getBlockState(mc.thePlayer.posX, (double)i, mc.thePlayer.posZ).getBlock();
               if (block.isFullBlock()) {
                  d = (double)i + block.getBlockBoundsMaxY();
                  break;
               }
            }

            for (double j = mc.thePlayer.posY; j > d; j -= 8.0) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, j, mc.thePlayer.posZ, true));
            }

            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, d, mc.thePlayer.posZ, true));

            for (double k = d; k < mc.thePlayer.posY; k += 8.0) {
               mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, k, mc.thePlayer.posZ, true));
            }

            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
            this.timeHelper.reset();
            return;
         }
      }
   }

   public void reloadCustomModes() {
      this.modesByName.clear();
      this.modesByName.putAll(this.builtinModes);
      this.customModeHashes.clear();
      File[] files = new File(this.customModesDir).listFiles();
      if (files != null) {
         for (File file2 : files) {
            this.loadCustomModeFile(file2);
         }
      }

      String[] strings = (String[])this.modesByName.keySet().stream().sorted().toArray(String[]::new);
      if (this.modesByName.get(this.mode.getSelectedValue()) == null) {
         String str = strings[0];
         this.mode.setSelectedValue(str);
      }

      this.mode.setOptions(strings);
   }

   private void loadCustomModeFile(File file) {
      if (file.getName().endsWith(".json")) {
         MotionModifierFlyMode motionModifierFlyMode = this.parseCustomModeFile(file, true);
         if (motionModifierFlyMode == null) {
            return;
         }

         this.modesByName.put(motionModifierFlyMode.getName(), motionModifierFlyMode);
      }
   }

   private MotionModifierFlyMode parseCustomModeFile(File file, boolean flag) {
      String str = file.getName().substring(0, Math.max(file.getName().length() - 5, 0));
      MotionModifierFlyMode motionModifierFlyMode = new MotionModifierFlyMode(str, this);
      JsonObject json = ClientFileUtil.readJsonObject(this.customModesDir, file.getName(), (Runnable)null);
      if (json == null) {
         return null;
      } else {
         JSON_CODEC.setFeatures2(new Module[]{motionModifierFlyMode.dummy});
         if (!JSON_CODEC.deserializeFromObject(json, true, obj2 -> true)) {
            System.out.println("Failed to read fly");
            return null;
         } else if (!flag) {
            return motionModifierFlyMode;
         } else {
            try {
               this.customModeHashes.put(str, MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(file.toPath())));
               return motionModifierFlyMode;
            } catch (IOException | NoSuchAlgorithmException e) {
               throw new RuntimeException(e);
            }
         }
      }
   }

   private void despawnEnderDragon() {
      if (this.spawnedEnderDragon != null && mc.theWorld != null && this.enderDragon.getValue()) {
         mc.theWorld.removeEntity(this.spawnedEnderDragon);
      }
   }

   private void registerMode(FlyMode flyMode) {
      this.modesByName.put(flyMode.getName(), flyMode);
   }

   private void syncModeSettingOptions() {
      if (!this.modesByName.containsKey(this.mode.getSelectedValue())) {
         this.mode.setSelectedValue((String)this.modesByName.keySet().iterator().next());
      }

      StringValue mode3 = this.mode;
      String[] strings = ((Map<String, ?>)this.modesByName).keySet().stream().sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
      mode3.setOptions(strings);
   }

   private void onCustomModeModified(Path path) {
      this.loadCustomModeFile(path.toFile());
      this.syncModeSettingOptions();
   }

   private void onCustomModeCreated(Path path) {
      this.loadCustomModeFile(path.toFile());
      this.syncModeSettingOptions();
   }

   private boolean isTimerSpeedEndVisible() {
      return this.mode.getSelectedValue().equals("Vulcan") && this.timer.getValue();
   }

   private boolean isTimerVisible() {
      return this.mode.getSelectedValue().equals("Vulcan");
   }

   private boolean isVulcanCollisionYVisible() {
      return this.mode.getSelectedValue().equals("Vulcan");
   }

   private boolean isHelpMessageVisible() {
      return this.mode.getSelectedValue().equals("IntaveBoat");
   }

   private boolean isOnWebVisible() {
      return this.mode.getSelectedValue().equals("GroundCollision");
   }

   private boolean isOnWaterVisible() {
      return this.mode.getSelectedValue().equals("GroundCollision");
   }

   private boolean isBoostVisible() {
      return this.mode.getSelectedValue().equals("OldGomme");
   }

   private boolean isCollisionYVisible() {
      return this.mode.getSelectedValue().equals("GroundCollision");
   }

   private boolean isCubeCraftStopDurationVisible() {
      return this.mode.getSelectedValue().equals("CubeCraft");
   }

   private boolean isCubeCraftSpeedVisible() {
      return this.mode.getSelectedValue().equals("CubeCraft");
   }

   private boolean isVerusSpeedCombinedVisible() {
      return this.mode.getSelectedValue().equals("VerusCombined");
   }

   private boolean isOnlyOnGroundVisible() {
      return this.mode.getSelectedValue().equals("OldVerus");
   }

   private boolean isVerusSpeedVisible() {
      return this.mode.getSelectedValue().equals("OldVerus");
   }

   private boolean isVanillaSpeedVerticalVisible() {
      return this.mode.getSelectedValue().equals("Vanilla2");
   }

   private boolean isVanillaSpeedHorizontalVisible() {
      return this.mode.getSelectedValue().equals("Vanilla2");
   }

   private boolean isVanillaSpeedVisible() {
      return this.mode.getSelectedValue().equals("Vanilla1");
   }

   private boolean isOnGroundPacketVisible() {
      return this.mode.getSelectedValue().equals("AirJump");
   }

   private boolean isAutoJumpVisible() {
      return this.mode.getSelectedValue().equals("AirJump");
   }

   private boolean isWaitForDamageVisible() {
      return this.mode.getSelectedValue().equals("Vanilla1") || this.mode.getSelectedValue().equals("Vanilla2");
   }

   public static BooleanValue getEnderDragonSoundSetting(Fly fly) {
      return fly.enderDragonSound;
   }

}
