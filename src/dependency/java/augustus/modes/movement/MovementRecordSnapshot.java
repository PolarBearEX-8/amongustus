package augustus.modes.movement;

import augustus.modules.movement.MovementRecorder;
import client.event.EventPacket;
import client.event.PacketListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.setting.BooleanSetting;
import client.setting.CustomSettingArrayEntry;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ConfigManager;
import client.util.Listener123Extension;
import client.util.MysteryBoxService;
import client.util.RayTraceUtil;
import client.util.TargetPredictor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.MovementInput;

public class MovementRecordSnapshot extends CustomSettingArrayEntry implements TickListener, PacketListener, UpdateStateListener {
   public final BooleanSetting settingDebug;
   public final ArrayList arrayList;
   public TargetPredictor targetPredictor;
   public boolean enabled2;
   public JsonObject jsonObject;

   @Override
   public void updateState() {
      this.clear2();
      minecraft.getTimer().scaleFactor2 = 1.0F;
   }

   private void clear2() {
      this.jsonObject = null;
      this.enabled2 = false;
      this.arrayList.clear();
      this.targetPredictor = null;
   }

   private JsonObject getJsonObject() {
      List list = this.getList();
      JsonObject jsonObject2 = new JsonObject();
      Listener123Extension listener123Extension = MysteryBoxService.mysteryBoxPacketListener.getListener123Extension4();
      String text = "Blocks";
      listener123Extension.applyText(text, list, jsonObject2);
      return jsonObject2;
   }

   private void updateState3() {
      if (this.jsonObject == null) {
         RayTraceUtil.applyString("Failed to save");
      } else {
         JsonArray jsonArray = new JsonArray();

         for (TargetPredictor targetPredictor2 : (Iterable<TargetPredictor>)(Iterable<?>)this.arrayList) {
            jsonArray.add(targetPredictor2.getJsonObject());
         }

         this.jsonObject.add("InputObjects", jsonArray);
         String displayName = MovementRecorder.displayName2;
         String text = System.currentTimeMillis() + ".json";
         JsonObject jsonObject2 = this.jsonObject;
         String text2 = text;
         ConfigManager.applyText(displayName, text2, jsonObject2);
         this.clear2();
      }
   }

   @Override
   public void clear() {
      this.updateState3();
      this.clear2();
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      if (packet.getPacket() instanceof S08PacketPlayerPosLook || packet.getPacket() instanceof S07PacketRespawn) {
         this.clear2();
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 40;
      this.applyIndex(byteValue);
      byte byteValue2 = 26;
      this.applyIndex(byteValue2);
      byte byteValue3 = 43;
      this.applyIndex(byteValue3);
   }

   @Override
   public void onPreUpdateState() {
      if (this.targetPredictor != null) {
         MovementInput secondaryFloatEntry = minecraft.thePlayer.secondaryFloatEntry;
         float moveForward = secondaryFloatEntry.moveForward;
         this.targetPredictor.setFactor3(moveForward);
         float moveStrafe = secondaryFloatEntry.moveStrafe;
         this.targetPredictor.setFactor4(moveStrafe);
         boolean state = secondaryFloatEntry.state2;
         this.targetPredictor.setEnabled3(state);
         boolean state2 = secondaryFloatEntry.state;
         this.targetPredictor.setEnabled2(state2);
         TargetPredictor targetPredictor2 = this.targetPredictor;
         boolean riding = minecraft.thePlayer.isRiding();
         targetPredictor2.setEnabled(riding);
         if (this.enabled2 || secondaryFloatEntry.moveForward != 0.0F || secondaryFloatEntry.moveStrafe != 0.0F || secondaryFloatEntry.state || secondaryFloatEntry.state2) {
            if (this.jsonObject == null) {
               this.jsonObject = this.getJsonObject();
            }

            this.enabled2 = true;
            this.arrayList.add(this.targetPredictor.getTargetPredictor());
            if (this.settingDebug.isEnabled()) {
               RayTraceUtil.applyString(this.targetPredictor.toString());
            }
         }
      }
   }

   @Override
   public void onRunTick() {
      if (this.targetPredictor == null) {
         this.targetPredictor = new TargetPredictor();
      }

      float yaw = minecraft.thePlayer.rotationYaw;
      this.targetPredictor.setFactor(yaw);
      float pitch = minecraft.thePlayer.rotationPitch;
      this.targetPredictor.setFactor2(pitch);
   }

   public MovementRecordSnapshot(MovementRecorder movementRecorder, SettingGroup settingGroup) {
        super(movementRecorder, settingGroup, "Record");
      
      BooleanSetting setting = new BooleanSetting(this.module, "RecordDebug", true, this.settingGroup);
      String text = "Debug";
      setting = (BooleanSetting)setting.getSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingDebug = (BooleanSetting)setting.getSetting(booleanSupplier);
      this.arrayList = new ArrayList();
      this.targetPredictor = null;
      this.enabled2 = false;
      this.jsonObject = null;
      ConfigManager.applyString2(MovementRecorder.displayName2);
   }
}
