package net.augustus.modules.movement;

import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.events.EventMove;
import net.augustus.modules.modes.speed.StrafeSpeedMode;
import net.augustus.modules.modes.speed.OldNCPSpeedMode;
import net.augustus.events.listeners.MotionListener;
import net.augustus.modules.modes.speed.VulcanHopSpeedMode;
import net.augustus.modules.modes.speed.VBhopSpeedMode;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.modules.modes.speed.OldMinemoraSpeedMode;
import net.augustus.modules.modes.speed.SentrySpeedMode;
import net.augustus.modules.modes.speed.VerusSpeedMode;
import net.augustus.modules.modes.speed.MotionSpeedMode;
import net.augustus.modules.modes.speed.VerusLowHopSpeedMode;
import net.augustus.modules.modes.speed.MatrixSpeedMode;
import net.augustus.utils.file.DirectoryWatcher;
import net.augustus.modules.modes.speed.VulcanYPortSpeedMode;
import net.augustus.modules.modes.speed.OldMineplexSpeedMode;
import net.augustus.modules.modes.speed.OldMatrixSpeedMode;
import net.augustus.modules.modes.speed.VOnGroundSpeedMode;
import net.augustus.modules.modes.speed.VulcanLowHopSpeedMode;
import net.augustus.modules.modes.speed.TimeManipulationSpeedMode;
import net.augustus.modules.modes.speed.VulcanOnGroundSpeedMode;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.Setting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.TimerListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.AIMoveSpeedListener;
import net.augustus.events.EventTimer;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.config.FeatureListJsonCodec;
import net.augustus.events.EventAIMoveSpeed;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.UnaryFunction;
import net.augustus.events.EventMoveEntity;
import net.augustus.modules.modes.speed.FortyFiveDegreeSpeedMode;
import net.augustus.modules.modes.speed.SpeedMode;
import net.augustus.modules.modes.speed.DojoSpeedMode;
import net.augustus.modules.modes.speed.EntitySpeedMode;
import net.augustus.modules.modes.speed.MotionModifierSpeedMode;
import net.augustus.modules.modes.speed.KarhuSpeedMode;
import net.augustus.events.listeners.PacketListener;
import net.augustus.modules.modes.speed.IntaveSpeedMode;
import net.augustus.modules.modes.speed.OldHypixelSpeedMode;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.modes.speed.CubeCraftSpeedMode;
import net.augustus.modules.modes.speed.InteractSpeedMode;
import net.augustus.modules.modes.speed.LegitHopSpeedMode;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public class Speed
   extends Module
   implements AIMoveSpeedListener,
   MoveListener,
   TickListener,
   MoveEntityListener,
   MoveInputListener,
   UpdateListener,
   MotionListener,
   PacketListener,
   TimerListener,
   PacketDelayListener {
   public final StringValue mode;
   public final DoubleValue vanillaSpeed;
   public final DoubleValue dojoSpeed;
   public final DoubleValue height;
   public final BooleanValue damageBoost;
   public final DoubleValue dmgSpeed;
   public final BooleanValue strafe;
   public final DoubleValue timeManipulationTimer;
   public final DoubleValue timeManipulationTPTicks;
   public final DoubleValue interactSpeed;
   public final BooleanValue mouseSensi;
   public final BooleanValue autoJump;
   public final BooleanValue onlyWhileMove;
   public final BooleanValue allDirectionSprint;
   public final DoubleValue additionalSpeed;
   public final BooleanValue correctMovement;
   public final BooleanValue notWhileInCobweb;
   public final BooleanValue notWhileInWater;
   private final HashMap modesByName;
   private final HashMap builtinModes;
   private final String customModesDir;
   private final VOnGroundSpeedMode vOnGroundSpeedMode;
   private static final FeatureListJsonCodec JSON_CODEC = new FeatureListJsonCodec();
   private AtomicBoolean suppressedByEnvironment;

   public Speed() {
       super("Speed", "Schnell", new Color(74, 133, 93), Categorys.MOVEMENT);
      this.mode = new StringValue(this, "Mode", "VBhop", new String[0]);
      this.vanillaSpeed = new DoubleValue(this, "VanillaSpeed", 1.0, 0.0, 10.0, 2).named("Speed").visibleWhen(() -> this.isVanillaSpeedVisible());
      this.dojoSpeed = new DoubleValue(this, "DojoSpeed", 5.0, 1.0, 10.0, 2).visibleWhen(() -> this.isDojoSpeedVisible());
      DoubleValue heightSetting = new DoubleValue(this, "Height", 1.0, 0.0, 5.0, 2);
      SettingVisibilityCondition condition3 = () -> {
         return this.mode.getSelectedValue().equals("VBhop");
      };
      this.height = heightSetting.visibleWhen(condition3);
      this.damageBoost = (BooleanValue)new BooleanValue(this, "DamageBoost", false).withVisibility(() -> this.isDamageBoostVisible());
      this.dmgSpeed = new DoubleValue(this, "DMGSpeed", 1.0, 0.0, 2.0, 2).visibleWhen(() -> this.isDamageSpeedVisible());
      this.strafe = (BooleanValue)new BooleanValue(this, "Strafe", false).withVisibility(() -> this.isStrafeVisible());
      DoubleValue timeManipulationTimerSetting = new DoubleValue(this, "TimeManipulationTimer", 1.0, 0.0, 10.0, 2).named("TimerSpeed");
      SettingVisibilityCondition condition7 = () -> {
         return this.mode.getSelectedValue().equals("TimeManipulation");
      };
      this.timeManipulationTimer = timeManipulationTimerSetting.visibleWhen(condition7);
      this.timeManipulationTPTicks = new DoubleValue(this, "TimeManipulationTPTicks", 0.0, 0.0, 10.0, 0).named("TPTicks").visibleWhen(() -> this.isTimeManipulationTpTicksVisible());
      this.interactSpeed = new DoubleValue(this, "InteractSpeed", 1.0, 1.0, 10.0, 0).visibleWhen(() -> this.isInteractSpeedVisible());
      this.mouseSensi = (BooleanValue)new BooleanValue(this, "MouseSensi", true).withVisibility(() -> this.isMouseSensiVisible());
      this.autoJump = (BooleanValue)new BooleanValue(this, "AutoJump", true).withVisibility(() -> this.isAutoJumpVisible());
      BooleanValue booleanValue5 = new BooleanValue(this, "OnlyWhileMove", true);
      SettingVisibilityCondition condition12 = () -> {
         return this.mode.getSelectedValue().equals("45Degree");
      };
      this.onlyWhileMove = (BooleanValue)booleanValue5.withVisibility(condition12);
      this.allDirectionSprint = (BooleanValue)new BooleanValue(this, "AllDirectionSprint", false).withVisibility(() -> this.isAllDirectionSprintVisible());
      this.additionalSpeed = new DoubleValue(this, "AdditionalSpeed", 0.2, 0.0, 2.0, 4).visibleWhen(() -> this.isAdditionalSpeedVisible());
      this.correctMovement = new BooleanValue(this, "CorrectMovement", true);
      this.notWhileInCobweb = new BooleanValue(this, "NotWhileInCobweb", true);
      this.notWhileInWater = new BooleanValue(this, "NotWhileInWater", true);
      this.modesByName = new HashMap();
      this.customModesDir = ClientPaths.CLIENT_DIR + File.separator + "motionmodifier" + File.separator + "speed";
      this.suppressedByEnvironment = new AtomicBoolean(false);
      ClientFileUtil.ensureDirectory(this.customModesDir);
      this.registerMode(this.vOnGroundSpeedMode = new VOnGroundSpeedMode("VOnGround", this));
      this.registerMode(new VBhopSpeedMode("VBhop", this));
      this.registerMode(new OldMineplexSpeedMode("OldMineplex", this));
      this.registerMode(new OldMatrixSpeedMode("OldMatrix", this));
      this.registerMode(new MatrixSpeedMode("Matrix", this));
      this.registerMode(new OldMinemoraSpeedMode("OldMinemora", this));
      this.registerMode(new OldNCPSpeedMode("OldNCP", this));
      this.registerMode(new CubeCraftSpeedMode("CubeCraft", this));
      this.registerMode(new LegitHopSpeedMode("LegitHop", this));
      this.registerMode(new DojoSpeedMode("Dojo", this));
      this.registerMode(new StrafeSpeedMode("Strafe", this));
      this.registerMode(new SentrySpeedMode("Sentry", this));
      this.registerMode(new TimeManipulationSpeedMode("TimeManipulation", this));
      this.registerMode(new OldHypixelSpeedMode("OldHypixel", this));
      this.registerMode(new IntaveSpeedMode("Intave", this));
      this.registerMode(new VulcanLowHopSpeedMode("VulcanLowHop", this));
      this.registerMode(new VulcanOnGroundSpeedMode("VulcanOnground", this));
      this.registerMode(new VulcanYPortSpeedMode("VulcanYPort", this));
      this.registerMode(new VulcanHopSpeedMode("VulcanHop", this));
      this.registerMode(new VerusSpeedMode("Verus", this));
      this.registerMode(new VerusLowHopSpeedMode("VerusLowHop", this));
      this.registerMode(new InteractSpeedMode("Interact", this));
      this.registerMode(new KarhuSpeedMode("Karhu", this));
      this.registerMode(new FortyFiveDegreeSpeedMode("45Degree", this));
      this.registerMode(new EntitySpeedMode("EntitySpeed", this));
      this.registerMode(new MotionSpeedMode("Motion", this));
      this.builtinModes = new HashMap(this.modesByName);
      this.reloadCustomModes();
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(this.customModesDir);
      directoryWatcher = directoryWatcher.onCreate(obj -> this.onCustomModeCreated((Path)obj));
      Consumer consumer2 = obj2 -> {
         this.loadCustomModeFile(((Path)obj2).toFile());
         this.syncModeSettingOptions();
      };
      directoryWatcher = directoryWatcher.onModify(consumer2);
      Consumer consumer3 = obj3 -> {
         String name2 = ((Path)obj3).toFile().getName();
         if (name2.endsWith(".json")) {
            String str4 = name2.substring(0, name2.length() - 5);
            this.modesByName.remove(str4);
            this.syncModeSettingOptions();
         }
      };
      directoryWatcher.onDelete(consumer3).register();
   }

   @Override
   public void onEnable() {
      mc.getTimer().timerSpeed = 1.0F;
      if (mc.thePlayer != null) {
         mc.thePlayer.setSpeedInAir(0.02F);
         mc.thePlayer.setSpeedOnGround(0.1F);
         mc.thePlayer.stepHeight = 0.6F;
      }

      this.getActiveMode().onEnable();
   }

   @Override
   public void onDisable() {
      mc.getTimer().timerSpeed = 1.0F;
      if (mc.thePlayer != null) {
         mc.thePlayer.setSpeedInAir(0.02F);
         mc.thePlayer.setSpeedOnGround(0.1F);
      }

      this.getActiveMode().onDisable();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(0, 1);
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(18, 1);
      this.toggleEventSubscription(26, 1);
      this.toggleEventSubscription(41);
      this.toggleEventSubscription(25);
   }

   public void reloadCustomModes() {
      this.modesByName.clear();
      this.modesByName.putAll(this.builtinModes);
      File[] files = new File(this.customModesDir).listFiles();
      if (files != null) {
         for (File file2 : files) {
            this.loadCustomModeFile(file2);
         }
      }

      this.syncModeSettingOptions();
   }

   private void loadCustomModeFile(File file) {
      if (file.getName().endsWith(".json")) {
         MotionModifierSpeedMode motionModifierSpeedMode = this.parseCustomModeFile(file);
         if (motionModifierSpeedMode == null) {
            return;
         }

         this.modesByName.put(motionModifierSpeedMode.getName(), motionModifierSpeedMode);
      }
   }

   private MotionModifierSpeedMode parseCustomModeFile(File file) {
      String name2 = file.getName();
      String str = name2.substring(0, Math.max(name2.length() - 5, 0));
      MotionModifierSpeedMode motionModifierSpeedMode = new MotionModifierSpeedMode(str, this);
      JsonObject json = ClientFileUtil.readJsonObject(this.customModesDir, name2, (Runnable)null);
      if (json == null) {
         return null;
      } else {
         FeatureListJsonCodec featureListJsonCodec = JSON_CODEC;
         featureListJsonCodec.setFeatures2(new Module[]{motionModifierSpeedMode.dummy});
         featureListJsonCodec = JSON_CODEC;
         if (!featureListJsonCodec.deserializeFromObject(json, true, obj2 -> true)) {
            System.out.println("Failed to read speed");
            return null;
         } else {
            return motionModifierSpeedMode;
         }
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onOutgoingPacketDelay(event);
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      this.setSuffix(this.mode.getSelectedValue());
      if (!this.isSuppressed()) {
         this.getActiveMode().onMotion(event);
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onMoveEntity(event);
      }
   }

   @Override
   public void onPreUpdate() {
      if (!this.isSuppressed()) {
         this.getActiveMode().onPreUpdate();
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onPreMoveInput(event);
      }
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onPostMoveInput(event);
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onMoveFlying(event);
         if (mc.thePlayer.fallDistance > 4.0F && !this.mode.getSelectedValue().equals("LegitHop")) {
            event.cancel();
         }

         if (!BlockUtil.isScaffoldActiveAndValid() && !this.correctMovement.getValue()) {
            event.setYaw(MoveUtil.getMovementYaw());
         }
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (!this.isSuppressed()) {
         if (!BlockUtil.isScaffoldActiveAndValid() && !this.correctMovement.getValue()) {
            event.setYaw(MoveUtil.getMovementYaw());
         }

         this.getActiveMode().onJumpMove(event);
      }
   }

   @Override
   public void onEarlyTick() {
      this.updateSuppressionState();
      if (!this.isSuppressed()) {
         this.getActiveMode().onEarlyTick();
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onSendPacket(event);
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onReadPacket(event);
      }
   }

   @Override
   public void onTimerSpeed(EventTimer event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onTimer(event);
      }
   }

   @Override
   public void onTimerTick(EventTimer event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onTimer(event);
      }
   }

   @Override
   public void onPostUpdate() {
      if (!this.isSuppressed()) {
         this.getActiveMode().onPostUpdate();
      }
   }

   @Override
   public void onAIMoveSpeed(EventAIMoveSpeed event) {
      if (!this.isSuppressed()) {
         this.getActiveMode().onAIMoveSpeed(event);
      }
   }

   private void registerMode(SpeedMode speedMode) {
      this.modesByName.put(speedMode.getName(), speedMode);
   }

   private SpeedMode getActiveMode() {
      return (SpeedMode)(this.modesByName.getOrDefault(this.mode.getSelectedValue(), this.vOnGroundSpeedMode));
   }

   private void updateSuppressionState() {
      this.suppressedByEnvironment.set(mc.thePlayer != null && (mc.thePlayer.isInWater() && this.notWhileInWater.getValue() || mc.thePlayer.isInWeb() && this.notWhileInCobweb.getValue()));
   }

   private boolean isSuppressed() {
      return this.suppressedByEnvironment.get();
   }

   private void syncModeSettingOptions() {
      if (!this.modesByName.containsKey(this.mode.getSelectedValue())) {
         this.mode.setSelectedValue((String)this.modesByName.keySet().iterator().next());
      }

      StringValue mode3 = this.mode;
      String[] strings = ((Map<String, ?>)this.modesByName).keySet().stream().sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
      mode3.setOptions(strings);
   }

   private void onCustomModeCreated(Path path) {
      this.loadCustomModeFile(path.toFile());
      this.syncModeSettingOptions();
   }

   private boolean isAdditionalSpeedVisible() {
      return this.mode.getSelectedValue().equals("EntitySpeed");
   }

   private boolean isAllDirectionSprintVisible() {
      return this.mode.getSelectedValue().equals("45Degree");
   }

   private boolean isAutoJumpVisible() {
      return this.mode.getSelectedValue().equals("45Degree");
   }

   private boolean isMouseSensiVisible() {
      return this.mode.getSelectedValue().equals("45Degree");
   }

   private boolean isInteractSpeedVisible() {
      return this.mode.getSelectedValue().equals("Interact");
   }

   private boolean isTimeManipulationTpTicksVisible() {
      return this.mode.getSelectedValue().equals("TimeManipulation");
   }

   private boolean isStrafeVisible() {
      return this.mode.getSelectedValue().equals("VOnGround") || this.mode.getSelectedValue().equals("VBhop");
   }

   private boolean isDamageSpeedVisible() {
      return this.mode.getSelectedValue().equals("Verus") && this.damageBoost.getValue();
   }

   private boolean isDamageBoostVisible() {
      return this.mode.getSelectedValue().equals("Verus");
   }

   private boolean isDojoSpeedVisible() {
      return this.mode.getSelectedValue().equals("Dojo");
   }

   private boolean isVanillaSpeedVisible() {
      return this.mode.getSelectedValue().equals("VOnGround") || this.mode.getSelectedValue().equals("VBhop");
   }





}
