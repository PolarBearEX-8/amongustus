package net.augustus.modules.modes.movementrecorder;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import net.augustus.settings.property.codec.JsonListPropertyCodec;
import net.augustus.utils.file.ClientFileUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.movement.MovementRecorder;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Vec3;

public class MovementRecordMode extends AbstractMovementRecorderMode implements TickListener, PacketListener, UpdateListener {
   private final BooleanValue recordDebug;
   private final DoubleValue minTeleportDistanceToResetRecording;
   private final ArrayList recordedInputs;
   private RecordedMovementInput recordedMovementInput;
   private boolean recordingStarted;
   private JsonObject recordingJson;

   public MovementRecordMode(MovementRecorder movementRecorder, SettingGroup doubleValue) {
       super(movementRecorder, doubleValue, "Record");
      this.recordDebug = (BooleanValue)((BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "RecordDebug", true, this.modeSetting).withDisplayName("Debug")).withVisibility(() -> this.isSelected())).markVisualOnly();
      this.minTeleportDistanceToResetRecording = (DoubleValue)new DoubleValue(this.parentModule, "Min teleport distance to reset recording", 0.0, 0.0, 1.0, 2).visibleWhen(() -> this.isSelected()).markVisualOnly();
      this.recordedInputs = new ArrayList();
      this.recordedMovementInput = null;
      this.recordingStarted = false;
      this.recordingJson = null;
      ClientFileUtil.ensureDirectory(MovementRecorder.RECORDINGS_DIR);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
      this.resetRecording();
      mc.getTimer().timerSpeed = 1.0F;
   }

   @Override
   public void onDisable() {
      this.saveRecording();
      this.resetRecording();
   }

   private void saveRecording() {
      if (this.recordingJson == null) {
         ((MovementRecorder)this.parentModule).sendChatMessage("Failed to save");
      } else {
         JsonArray array = new JsonArray();

         for (RecordedMovementInput recordedMovementInput2 : (Iterable<RecordedMovementInput>)(this.recordedInputs)) {
            array.add(recordedMovementInput2.toJson());
         }

         this.recordingJson.add("InputObjects", array);
         ClientFileUtil.writeJsonObject(MovementRecorder.RECORDINGS_DIR, System.currentTimeMillis() + ".json", this.recordingJson);
         this.resetRecording();
      }
   }

   @Override
   public void onPostUpdate() {
      if (this.recordedMovementInput != null) {
         MovementInput movementInput = mc.thePlayer.movementInput;
         float moveForward = movementInput.moveForward;
         this.recordedMovementInput.setForward(moveForward);
         float moveStrafe = movementInput.moveStrafe;
         this.recordedMovementInput.setStrafe(moveStrafe);
         boolean sneak = movementInput.sneak;
         this.recordedMovementInput.setSneaking(sneak);
         boolean jump = movementInput.jump;
         this.recordedMovementInput.setJumping(jump);
         this.recordedMovementInput.setSprinting(mc.thePlayer.isSprinting());
         if (this.recordingStarted || movementInput.moveForward != 0.0F || movementInput.moveStrafe != 0.0F || movementInput.jump || movementInput.sneak) {
            if (this.recordingJson == null) {
               this.recordingJson = this.createRecordingHeader();
            }

            this.recordingStarted = true;
            this.recordedInputs.add(this.recordedMovementInput.copy());
            if (this.recordDebug.getValue()) {
               ((MovementRecorder)this.parentModule).sendChatMessage(this.recordedMovementInput.toString());
            }
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn) {
         this.resetRecording();
      } else if (packet instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)packet;
         if (new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ).distanceTo(new Vec3(packet2.getX(), packet2.getY(), packet2.getZ())) >= this.minTeleportDistanceToResetRecording.getValue()) {
            this.resetRecording();
         }
      }
   }

   @Override
   public void onTick() {
      if (this.recordedMovementInput == null) {
         this.recordedMovementInput = new RecordedMovementInput();
      }

      float rotationYaw = mc.thePlayer.rotationYaw;
      this.recordedMovementInput.setYaw(rotationYaw);
      float rotationPitch = mc.thePlayer.rotationPitch;
      this.recordedMovementInput.setPitch(rotationPitch);
   }

   private void resetRecording() {
      this.recordingJson = null;
      this.recordingStarted = false;
      this.recordedInputs.clear();
      this.recordedMovementInput = null;
   }

   private JsonObject createRecordingHeader() {
      List list = this.captureSurroundingBlocks();
      JsonObject json = new JsonObject();
      JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanListCodec().writeList("Blocks", list, json);
      return json;
   }

}
