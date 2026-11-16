package augustus.modes.movement;

import augustus.modes.combat.AuraRenderMode;
import augustus.modules.movement.MovementRecorder;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveInput;
import client.event.EventSendPacket;
import client.event.EventSlowDown;
import client.event.MotionUpdateListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.SlowDownListener;
import client.event.TickListener;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.CustomSettingArrayEntry;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.ClientManager;
import client.util.ConfigManager;
import client.util.ConsumerTask;
import client.util.Listener123Extension;
import client.util.MysteryBoxService;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.SimulatedPlayer;
import client.util.SystemStringEntry;
import client.util.TargetPredictor;
import client.util.TimeHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class MovementReplayMode
   extends CustomSettingArrayEntry
   implements TickListener,
   NetworkPacketListener,
   MoveListener,
   MoveInputListener,
   SlowDownListener,
   MotionUpdateListener {
   public final ModeSetting settingRecordings;
   public final NumberSetting settingStartDelay;
   public final BooleanSetting settingAutoDetect;
   public final BooleanSetting settingMouseSensi;
   public final BooleanSetting settingLoop;
   public final BooleanSetting settingDebug;
   public final SettingGroup settingTimer;
   public final ModeSetting settingBalanced;
   public final NumberSetting numberSetting;
   public final NumberSetting settingTimeSpeed2;
   public final NumberSetting settingTimeSpeed;
   public final HashMap hashMap;
   public boolean enabled3;
   public boolean enabled4;
   public final TimeHelper timer;
   public TargetPredictor targetPredictor;
   public int count2;
   public boolean enabled2;

   @Override
   public void onNetworkPacket(EventSendPacket packet) {
      if (!packet.isState()) {
         Packet packet2 = packet.getPacket();
         if (packet2 instanceof S08PacketPlayerPosLook || packet2 instanceof S07PacketRespawn) {
            ClientManager.applyRunnable2(this::updateState4);
         }
      }
   }

   private static void updateState3() {
      minecraft.getTimer().scaleFactor2 = 1.0F;
   }

   @Override
   public void updateState() {
      this.updateState5();
      this.updateState4();
      if (this.settingBalanced.getSelected().equals("Balanced") && !((Module)ClientGlobals.moduleManager.timeManipulation).isToggled()) {
         RayTraceUtil.applyString("Please enable \"TimeManipulation\" module to use the balance timer option.");
      }
   }

   private SystemStringEntry getSystemStringEntry() {
      return (SystemStringEntry)this.hashMap.get(this.settingRecordings.getSelected());
   }

   private static String[] resolveTextArray(int count) {
      return new String[count];
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (this.settingBalanced.getSelected().equals("Default")) {
         if ((double)this.count2 > this.numberSetting.getValue() && this.enabled4) {
            minecraft.getTimer().scaleFactor2 = (float)this.settingTimeSpeed2.getDouble2(true);
            this.enabled2 = true;
         } else if (this.enabled2) {
            this.enabled2 = false;
            minecraft.getTimer().scaleFactor2 = 1.0F;
         }
      }
   }

   private boolean isActive2() {
      return this.booleanValue() && this.settingBalanced.getSelected().equals("Balanced");
   }

   private void updateState5() {
      this.hashMap.clear();
      ConfigManager.applyString2(MovementRecorder.displayName2);
      File[] fileArray = new File(MovementRecorder.displayName2).listFiles(File::isFile);
      if (fileArray != null) {
         for (File file : fileArray) {
            if (file.getName().endsWith(".json")) {
               SystemStringEntry systemStringEntry = this.resolveSystemStringEntry(file);
               if (systemStringEntry != null) {
                  this.hashMap.put(SystemStringEntry.resolveText(systemStringEntry), systemStringEntry);
               }
            }
         }

         boolean flag = this.hashMap.containsKey(this.settingRecordings.getSelected());
         String[] textArray = ((String[])this.hashMap.keySet().stream().sorted().toArray(MovementReplayMode::resolveTextArray));
         if (!flag && textArray.length > 0) {
            String text = textArray[0];
            this.settingRecordings.setSelected(text);
         }

         this.settingRecordings.applyStringArray2(textArray);
      }
   }

   private boolean isActive3() {
      return this.booleanValue() && this.settingBalanced.getSelected().equals("Default");
   }

   @Override
   public void onSlowDown(EventSlowDown event) {
      if (this.settingBalanced.getSelected().equals("Balanced") && (double)this.count2 > this.numberSetting.getValue() && this.enabled4) {
         float doubleItem = (float)this.settingTimeSpeed.getDouble2(true);
         event.setFactor(doubleItem);
      }
   }

   @Override
   public void clear() {
      minecraft.getTimer().scaleFactor2 = 1.0F;
   }

   private boolean isActive() {
      SystemStringEntry systemStringEntry = this.getSystemStringEntry();
      if (minecraft.isIntegratedServerRunning() && systemStringEntry != null && this.targetPredictor != null && this.enabled4) {
         TimeHelper timer2 = this.timer;
         double value = this.settingStartDelay.getValue();
         if (timer2.hasReachedDouble(value)) {
            return false;
         }
      }

      return true;
   }

   private boolean isActiveAsFlag() {
      return this.booleanValue() && !this.settingBalanced.getSelected().equals("None");
   }

   @Override
   public void onMove(EventMove event) {
      if (!this.isActive()) {
         AuraRenderMode auraRenderMode = AuraRenderMode.NONE;
         event.setAuraRenderMode(auraRenderMode);
         float factor = this.targetPredictor.getFactor3();
         event.setScaleFactor7(factor);
         float factor2 = this.targetPredictor.getFactor4();
         event.setScaleFactor2(factor2);
         boolean enabled = this.targetPredictor.isEnabled2();
         event.setState(enabled);
         boolean enabled5 = this.targetPredictor.isEnabled3();
         event.setState4(enabled5);
      }
   }

   private void updateState4() {
      this.count2 = 0;
      this.targetPredictor = null;
      this.enabled3 = true;
      this.enabled4 = false;
      this.timer.reset();
   }

   private SystemStringEntry resolveSystemStringEntry(File file) {
      String absolutePath = file.getParentFile().getAbsolutePath();
      String name = file.getName();
      JsonObject jsonObject = ConfigManager.getJsonObject(absolutePath, name, (Runnable)null);
      if (jsonObject == null) {
         return null;
      } else {
         Listener123Extension listener123Extension = MysteryBoxService.mysteryBoxPacketListener.getListener123Extension4();
         ArrayList list = new ArrayList();
         String text = "Blocks";
         List list2 = listener123Extension.resolveList(text, list, jsonObject);
         if (list2.isEmpty()) {
            return null;
         } else {
            JsonArray asJsonArray = jsonObject.get("InputObjects").getAsJsonArray();
            if (asJsonArray == null) {
               return null;
            } else {
               ArrayList list3 = new ArrayList();

               for (JsonElement jsonElement : asJsonArray) {
                  if (jsonElement.isJsonObject()) {
                     TargetPredictor targetPredictor2 = new TargetPredictor();
                     JsonObject jsonObject2 = (JsonObject)jsonElement;
                     targetPredictor2.applyJsonObject(jsonObject2);
                     list3.add(targetPredictor2);
                  }
               }

               return new SystemStringEntry(this, file.getName().substring(0, Math.max(file.getName().length() - 5, 0)), list3, list2);
            }
         }
      }
   }

   @Override
   public void onCustomSlowDown(EventSlowDown event) {
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      if (!this.isActive()) {
         boolean enabled = this.targetPredictor.isEnabled();
         event.setState(enabled);
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 40;
      this.applyIndex(byteValue);
      this.applyIndex(25, 100);
      byte byteValue2 = 36;
      this.applyIndex(byteValue2);
      this.applyIndex(21, 100);
      byte byteValue3 = 18;
      this.applyIndex(byteValue3);
      byte byteValue4 = 41;
      this.applyIndex(byteValue4);
   }

   @Override
   public void onPreTick() {
      SystemStringEntry systemStringEntry = this.getSystemStringEntry();
      if (minecraft.isIntegratedServerRunning() && systemStringEntry != null) {
         TimeHelper timer2 = this.timer;
         double value = this.settingStartDelay.getValue();
         if (timer2.hasReachedDouble(value)) {
            if (this.enabled3 && minecraft.thePlayer.onGround) {
               this.enabled4 = !this.settingAutoDetect.isEnabled();
               if (this.settingAutoDetect.isEnabled()) {
                  Iterator iterator = this.hashMap.values().iterator();

                  while (true) {
                     if (!iterator.hasNext()) {
                        RayTraceUtil.applyString("No equal map found. Sorry :/");
                        break;
                     }

                     SystemStringEntry systemStringEntry2 = (SystemStringEntry)iterator.next();
                     List list = this.getList();
                     if (Arrays.equals(SystemStringEntry.resolveListBySystemStringEntr(systemStringEntry2).toArray(new Boolean[0]), list.toArray(new Boolean[0]))) {
                        this.enabled4 = true;
                        ModeSetting settingRecordings2 = this.settingRecordings;
                        String text = SystemStringEntry.resolveText(systemStringEntry2);
                        settingRecordings2.setSelected(text);
                        break;
                     }
                  }
               }

               this.enabled3 = false;
            }

            if (!this.enabled4) {
               return;
            }

            SystemStringEntry systemStringEntry3 = this.getSystemStringEntry();
            if (this.count2 >= SystemStringEntry.resolveList(systemStringEntry3).size()) {
               if (!this.settingLoop.isEnabled()) {
                  this.enabled4 = false;
                  RayTraceUtil.applyString("Finished!");
               } else {
                  this.count2 = 0;
               }

               return;
            }

            this.targetPredictor = (TargetPredictor)SystemStringEntry.resolveList(systemStringEntry3).get(this.count2);
            if (this.targetPredictor == null) {
               return;
            }

            if (this.settingDebug.isEnabled()) {
               RayTraceUtil.applyString(this.targetPredictor.toString());
            }

            this.count2++;
            Rotation rotation = new Rotation(
               SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, this.targetPredictor.getFactor(), 180.0F),
               SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, this.targetPredictor.getFactor2(), 180.0F)
            );
            if (this.settingMouseSensi.isEnabled()) {
               Rotation rotation2 = ClientGlobals.rotationManager.getRotation3();
               rotation = SimulatedPlayer.getRotation5(rotation, rotation2);
            }

            Module module = this.module;
            byte byteValue = -20;
            ClientGlobals.rotationManager.applyRotation2(rotation, byteValue, 1898960900, module);
            return;
         }
      }
   }

   public MovementReplayMode(MovementRecorder movementRecorder, SettingGroup settingGroup) {
        super(movementRecorder, settingGroup, "Replay");
      
      ModeSetting setting = new ModeSetting(this.module, "Recordings", "", new String[0], this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingRecordings = setting.getModeSetting(booleanSupplier);
      NumberSetting setting2 = new NumberSetting(this.module, "StartDelay", 3000.0, 0.0, 10000.0, 0, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingStartDelay = setting2.getNumberSetting(booleanSupplier2);
      BooleanSetting setting3 = new BooleanSetting(this.module, "AutoDetect", true, this.settingGroup);
      BooleanSupplier booleanSupplier3 = this::booleanValue;
      this.settingAutoDetect = (BooleanSetting)setting3.getSetting(booleanSupplier3);
      BooleanSetting setting4 = new BooleanSetting(this.module, "MouseSensi", true, this.settingGroup);
      BooleanSupplier booleanSupplier4 = this::booleanValue;
      this.settingMouseSensi = (BooleanSetting)setting4.getSetting(booleanSupplier4);
      BooleanSetting setting5 = new BooleanSetting(this.module, "Loop", false, this.settingGroup);
      BooleanSupplier booleanSupplier5 = this::booleanValue;
      this.settingLoop = (BooleanSetting)setting5.getSetting(booleanSupplier5);
      BooleanSetting setting6 = new BooleanSetting(this.module, "ReplayDebug", true, this.settingGroup);
      String text = "Debug";
      BooleanSetting setting7 = (BooleanSetting)setting6.getSetting(text);
      BooleanSupplier booleanSupplier6 = this::booleanValue;
      this.settingDebug = (BooleanSetting)setting7.getSetting(booleanSupplier6);
      SettingGroup settingGroup2 = new SettingGroup(this.module, "Timer", true, this.settingGroup);
      BooleanSupplier booleanSupplier7 = this::booleanValue;
      BooleanSupplier booleanSupplier8 = booleanSupplier7;
      this.settingTimer = settingGroup2.getSettingGroup(booleanSupplier8);
      ModeSetting setting8 = new ModeSetting(this.module, "TimerMode", "Default", new String[]{"None", "Default", "Balanced"}, this.settingTimer);
      BooleanSupplier booleanSupplier9 = this::booleanValue;
      ModeSetting modeSetting = setting8.getModeSetting(booleanSupplier9);
      ConsumerTask consumerTask = MovementReplayMode::updateState3;
      this.settingBalanced = modeSetting.getModeSetting(consumerTask);
      NumberSetting setting9 = new NumberSetting(this.module, "StartTimerAfterXTicksOfTheRecording", 40.0, 0.0, 100.0, 0, this.settingTimer);
      BooleanSupplier booleanSupplier10 = this::isActiveAsFlag;
      this.numberSetting = setting9.getNumberSetting(booleanSupplier10);
      NumberSetting setting10 = new NumberSetting(this.module, "TimerSpeedDefault", 1.0, 0.1, 10.0, 8, this.settingTimer);
      String text2 = "TimeSpeed";
      NumberSetting numberSetting2 = setting10.getNumberSetting(text2);
      BooleanSupplier booleanSupplier11 = this::isActive3;
      this.settingTimeSpeed2 = numberSetting2.getNumberSetting(booleanSupplier11);
      NumberSetting setting11 = new NumberSetting(this.module, "TimerSpeedBalanced", 1.0, 0.1, 10.0, 8, this.settingTimer);
      String text3 = "TimeSpeed";
      NumberSetting numberSetting3 = setting11.getNumberSetting(text3);
      BooleanSupplier booleanSupplier12 = this::isActive2;
      this.settingTimeSpeed = numberSetting3.getNumberSetting(booleanSupplier12);
      this.hashMap = new HashMap();
      this.enabled3 = false;
      this.enabled4 = false;
      this.timer = new TimeHelper();
      this.count2 = 0;
      this.enabled2 = false;
      this.updateState5();
   }
}
