package net.augustus.modules.modes.movementrecorder;

import net.augustus.ClientManagers;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.EventSprint;
import net.augustus.events.EventTimer;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.SprintListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.TimerListener;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import net.augustus.settings.property.codec.JsonListPropertyCodec;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.augustus.modules.Module;
import net.augustus.modules.movement.MovementRecorder;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;

public class MovementReplayMode extends AbstractMovementRecorderMode implements TickListener, PacketDelayListener, MoveInputListener, SprintListener, TimerListener, MotionListener {
   private final StringValue recordings;
   private final DoubleValue startDelay;
   private final BooleanValue autoDetect;
   private final DoubleValue minTeleportDistanceToStartReplay;
   private final BooleanValue mouseSensi;
   private final BooleanValue loop;
   private final BooleanValue replayDebug;
   private final SettingGroup timer;
   private final StringValue timerMode;
   private final DoubleValue startTimerAfterXTicksOfTheRecording;
   private final DoubleValue timerSpeedDefault;
   private final DoubleValue timerSpeedBalanced;
   private final HashMap recordingsByName;
   private boolean awaitingStartOnGround;
   private boolean replaying;
   private final TimeHelper timeHelper;
   private RecordedMovementInput recordedMovementInput;
   private int replayTickIndex;
   private boolean defaultTimerApplied;

   public MovementReplayMode(MovementRecorder movementRecorder, SettingGroup doubleValue) {
       super(movementRecorder, doubleValue, "Replay");
      this.recordings = (StringValue)((StringValue)new StringValue(this.parentModule, "Recordings", "", new String[0], this.modeSetting).withVisibility(() -> this.isSelected())).markVisualOnly();
      this.startDelay = (DoubleValue)new DoubleValue(this.parentModule, "StartDelay", 3000.0, 0.0, 10000.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected()).markVisualOnly();
      this.autoDetect = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "AutoDetect", true, this.modeSetting).withVisibility(() -> this.isSelected())).markVisualOnly();
      this.minTeleportDistanceToStartReplay = (DoubleValue)new DoubleValue(this.parentModule, "Min teleport distance to start replay", 0.0, 0.0, 1.0, 2).visibleWhen(() -> this.isSelected()).markVisualOnly();
      this.mouseSensi = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "MouseSensi", true, this.modeSetting).withVisibility(() -> this.isSelected())).markVisualOnly();
      this.loop = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "Loop", false, this.modeSetting).withVisibility(() -> this.isSelected())).markVisualOnly();
      this.replayDebug = (BooleanValue)((BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "ReplayDebug", true, this.modeSetting).withDisplayName("Debug")).withVisibility(() -> this.isSelected())).markVisualOnly();
      this.timer = (SettingGroup)new SettingGroup(this.parentModule, "Timer", true, this.modeSetting).visibleWhen(() -> this.isSelected()).markVisualOnly();
      this.timerMode = (StringValue)((StringValue)((StringValue)new StringValue(this.parentModule, "TimerMode", "Default", new String[]{"None", "Default", "Balanced"}, this.timer).withVisibility(() -> this.isSelected())).withChangeListener(() -> mc.getTimer().timerSpeed = 1.0F)).markVisualOnly();
      DoubleValue doubleSetting = new DoubleValue(this.parentModule, "StartTimerAfterXTicksOfTheRecording", 40.0, 0.0, 100.0, 0, this.timer);
      SettingVisibilityCondition condition10 = () -> {
         return this.isSelected() && !this.timerMode.getSelectedValue().equals("None");
      };
      this.startTimerAfterXTicksOfTheRecording = (DoubleValue)doubleSetting.visibleWhen(condition10).markVisualOnly();
      this.timerSpeedDefault = (DoubleValue)new DoubleValue(this.parentModule, "TimerSpeedDefault", 1.0, 0.1, 10.0, 8, this.timer).named("TimeSpeed").visibleWhen(() -> this.isDefaultTimerSpeedVisible()).markVisualOnly();
      DoubleValue timerSpeedBalancedSetting = new DoubleValue(this.parentModule, "TimerSpeedBalanced", 1.0, 0.1, 10.0, 8, this.timer).named("TimeSpeed");
      SettingVisibilityCondition condition12 = () -> {
         return this.isSelected() && this.timerMode.getSelectedValue().equals("Balanced");
      };
      this.timerSpeedBalanced = (DoubleValue)timerSpeedBalancedSetting.visibleWhen(condition12).markVisualOnly();
      this.recordingsByName = new HashMap();
      this.awaitingStartOnGround = false;
      this.replaying = false;
      this.timeHelper = new TimeHelper();
      this.replayTickIndex = 0;
      this.defaultTimerApplied = false;
      this.reloadRecordings();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(25, 100);
      this.toggleEventSubscription(36);
      this.toggleEventSubscription(21, 100);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(41);
   }

   @Override
   public void onEnable() {
      this.reloadRecordings();
      this.resetReplayState();
      if (this.timerMode.getSelectedValue().equals("Balanced") && !ClientManagers.moduleManager.timeManipulation.isToggled()) {
         ((MovementRecorder)this.parentModule).sendChatMessage("Please enable \"TimeManipulation\" module to use the balance timer option.");
      }
   }

   @Override
   public void onDisable() {
      mc.getTimer().timerSpeed = 1.0F;
   }

   @Override
   public void onEarlyTick() {
      MovementRecording selectedRecording = this.getSelectedRecording();
      if (mc.isNotInGui() && selectedRecording != null) {
         if (this.timeHelper.reached(this.startDelay.getValue())) {
            if (this.awaitingStartOnGround && mc.thePlayer.onGround) {
               this.replaying = !this.autoDetect.getValue();
               if (this.autoDetect.getValue()) {
                  boolean flag = false;

                  for (MovementRecording movementRecording : (Iterable<MovementRecording>)(this.recordingsByName.values())) {
                     List list = this.captureSurroundingBlocks();
                     if (Arrays.equals(MovementRecording.accessBlockSignature(movementRecording).toArray(new Boolean[0]), list.toArray(new Boolean[0]))) {
                        this.replaying = true;
                        this.recordings.setSelectedValue(MovementRecording.accessName(movementRecording));
                        flag = true;
                        break;
                     }
                  }

                  if (!flag) {
                     ((MovementRecorder)this.parentModule).sendChatMessage("No equal map found. Sorry :/");
                  }
               }

               this.awaitingStartOnGround = false;
            }

            if (!this.replaying) {
               return;
            }

            selectedRecording = this.getSelectedRecording();
            if (this.replayTickIndex >= MovementRecording.accessInputs(selectedRecording).size()) {
               if (!this.loop.getValue()) {
                  this.replaying = false;
                  ((MovementRecorder)this.parentModule).sendChatMessage("Finished!");
               } else {
                  this.replayTickIndex = 0;
               }

               return;
            }

            this.recordedMovementInput = (RecordedMovementInput)MovementRecording.accessInputs(selectedRecording).get(this.replayTickIndex);
            if (this.recordedMovementInput == null) {
               return;
            }

            if (this.replayDebug.getValue()) {
               ((MovementRecorder)this.parentModule).sendChatMessage(this.recordedMovementInput.toString());
            }

            this.replayTickIndex++;
            Rotation rotation = new Rotation(
               RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, this.recordedMovementInput.getYaw(), 180.0F),
               RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, this.recordedMovementInput.getPitch(), 180.0F)
            );
            if (this.mouseSensi.getValue()) {
               rotation = RotationUtil.applyGcdFix(rotation, ClientManagers.rotationController.getPreviousRotation());
            }

            Module parentModule5 = this.parentModule;
            ClientManagers.rotationController.requestRotation(rotation, (byte)-20, parentModule5);
            return;
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (!this.isReplayInactive()) {
         event.setMoveFixMode(MoveFixMode.NONE);
         event.setMoveForward(this.recordedMovementInput.getForward());
         event.setMoveStrafe(this.recordedMovementInput.getStrafe());
         event.setJump(this.recordedMovementInput.isJumping());
         event.setSneak(this.recordedMovementInput.isSneaking());
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (!event.isDelayed()) {
         Packet packet = event.getPacket();
         if (packet instanceof S07PacketRespawn) {
            PacketThreadUtil.runOnMainThreadImmediate(() -> this.resetReplayState());
         } else if (packet instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)packet;
            if (new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ).distanceTo(new Vec3(packet2.getX(), packet2.getY(), packet2.getZ())) >= this.minTeleportDistanceToStartReplay.getValue()) {
               PacketThreadUtil.runOnMainThreadImmediate(() -> this.resetReplayState());
            }
         }
      }
   }

   @Override
   public void onSprint(EventSprint event) {
      if (!this.isReplayInactive()) {
         event.setSprintKeyDown(this.recordedMovementInput.isSprinting());
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.timerMode.getSelectedValue().equals("Default")) {
         if ((double)this.replayTickIndex > this.startTimerAfterXTicksOfTheRecording.getValue() && this.replaying) {
            mc.getTimer().timerSpeed = (float)this.timerSpeedDefault.getValue(true);
            this.defaultTimerApplied = true;
         } else if (this.defaultTimerApplied) {
            this.defaultTimerApplied = false;
            mc.getTimer().timerSpeed = 1.0F;
         }
      }
   }

   @Override
   public void onTimerSpeed(EventTimer event) {
      if (this.timerMode.getSelectedValue().equals("Balanced") && (double)this.replayTickIndex > this.startTimerAfterXTicksOfTheRecording.getValue() && this.replaying) {
         event.setTimerSpeed((float)this.timerSpeedBalanced.getValue(true));
      }
   }

   @Override
   public void onTimerTick(EventTimer event) {
   }

   private void resetReplayState() {
      this.replayTickIndex = 0;
      this.recordedMovementInput = null;
      this.awaitingStartOnGround = true;
      this.replaying = false;
      this.timeHelper.reset();
   }

   private void reloadRecordings() {
      this.recordingsByName.clear();
      ClientFileUtil.ensureDirectory(MovementRecorder.RECORDINGS_DIR);
      File[] files = new File(MovementRecorder.RECORDINGS_DIR).listFiles(File::isFile);
      if (files != null) {
         for (File file2 : files) {
            if (file2.getName().endsWith(".json")) {
               MovementRecording movementRecording = this.loadRecordingFile(file2);
               if (movementRecording != null) {
                  this.recordingsByName.put(MovementRecording.accessName(movementRecording), movementRecording);
               }
            }
         }

         boolean flag = this.recordingsByName.containsKey(this.recordings.getSelectedValue());
         String[] strings = (String[])this.recordingsByName.keySet().stream().sorted().toArray(String[]::new);
         if (!flag && strings.length > 0) {
            String str = strings[0];
            this.recordings.setSelectedValue(str);
         }

         this.recordings.setOptions(strings);
      }
   }

   private MovementRecording loadRecordingFile(File file) {
      JsonObject json = ClientFileUtil.readJsonObject(file.getParentFile().getAbsolutePath(), file.getName(), (Runnable)null);
      if (json == null) {
         return null;
      } else {
         JsonListPropertyCodec booleanListCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanListCodec();
         ArrayList list = new ArrayList();
         List list2 = booleanListCodec.readList("Blocks", list, json);
         if (list2.isEmpty()) {
            return null;
         } else {
            JsonArray asJsonArray = json.get("InputObjects").getAsJsonArray();
            if (asJsonArray == null) {
               return null;
            } else {
               ArrayList list3 = new ArrayList();

               for (JsonElement elem : asJsonArray) {
                  if (elem.isJsonObject()) {
                     RecordedMovementInput recordedMovementInput2 = new RecordedMovementInput();
                     recordedMovementInput2.fromJson((JsonObject)elem);
                     list3.add(recordedMovementInput2);
                  }
               }

               return new MovementRecording(this, file.getName().substring(0, Math.max(file.getName().length() - 5, 0)), list3, list2);
            }
         }
      }
   }

   private MovementRecording getSelectedRecording() {
      return (MovementRecording)this.recordingsByName.get(this.recordings.getSelectedValue());
   }

   private boolean isReplayInactive() {
      MovementRecording selectedRecording = this.getSelectedRecording();
      if (mc.isNotInGui() && selectedRecording != null && this.recordedMovementInput != null && this.replaying) {
         if (this.timeHelper.reached(this.startDelay.getValue())) {
            return false;
         }
      }

      return true;
   }

   private boolean isDefaultTimerSpeedVisible() {
      return this.isSelected() && this.timerMode.getSelectedValue().equals("Default");
   }

}
