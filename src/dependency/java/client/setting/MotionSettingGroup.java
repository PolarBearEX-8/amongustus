package client.setting;

import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.module.Module;
import client.util.AuxiliaryModuleAdapter;
import client.util.CombatUtil;
import client.util.MinecraftAccessor;
import client.util.MinecraftTimer;
import client.util.MoveUtil;
import client.util.Packet;
import client.util.SetPositionManager;
import client.util.TimeHelper;
import java.util.HashMap;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.util.MathHelper;

public class MotionSettingGroup {
   public final BooleanSetting settingBlockJumpInput;
   public boolean enabled7;
   public boolean enabled8;
   public int count8;
   public int count7;
   public final BooleanSetting settingOnlyInWater;
   public final HashMap hashMap2;
   public boolean enabled;
   public final BooleanSetting settingOnlyAtLadder;
   public final BooleanSetting settingWaitForDamage;
   public final BooleanSetting settingOnlyInWeb;
   public final BooleanSetting settingOnlyWhileJumpKeyPressed;
   public final BooleanSetting settingOnlyWhileMoving;
   public int count6;
   public int count5;
   public boolean enabled6;
   public final SetPositionManager setPositionManager;
   public int count9;
   public boolean enabled3;
   public int count2;
   public boolean enabled4;
   public int count4;
   public final HashMap hashMap = new HashMap();
   public boolean enabled2;
   public final Module module;
   public int count;
   public int count3;
   public boolean enabled5;

   private void onMoveEntity(SetPositionManager setPositionManager2, EventMoveEntity event) {
      long value = 0x471d747c4391L;
      long keySalt = 0x343432eacb7dL ^ value;
      int count10 = (int)((0x343432eacb7dL ^ value ^ 0x2cbf6d5d2768L) >>> 56);
      if (MinecraftAccessor.minecraft.thePlayer != null && setPositionManager2.settingEditTick.isEnabled()) {
         if ((!setPositionManager2.booleanSetting5.isEnabled() || MinecraftAccessor.minecraft.thePlayer.hurtTime <= 0)
            && (!setPositionManager2.booleanSetting6.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWeb())
            && (!setPositionManager2.booleanSetting11.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWater())
            && (!setPositionManager2.booleanSetting8.isEnabled() || MinecraftAccessor.minecraft.thePlayer.canExecute9())) {
            float factor = MathHelper.clamp_float(0.0F, (float)setPositionManager2.numberSetting.getValue(), 100.0F);
            if (setPositionManager2.booleanSetting12.isEnabled()) {
               SettingProvider settingProvider = SettingContainer.settingProvider;
               double doubleItem = setPositionManager2.actionSetting9.getDouble3();
               double doubleItem2 = setPositionManager2.actionSetting9.getDouble6();
               double offset = doubleItem;
               double floatItem = (double)settingProvider.getFloat3(offset, doubleItem2);
               event.setCoordOffset1(floatItem);
            }

            if (setPositionManager2.booleanSetting14.isEnabled()) {
               MinecraftAccessor.minecraft.thePlayer.closeScreen();
            }

            this.applySetPositionManagerBySetPositionManag(setPositionManager2);
            if (setPositionManager2.booleanSetting9.isEnabled()) {
               MinecraftAccessor.minecraft.thePlayer.onGround = true;
            }

            if (setPositionManager2.booleanSetting7.isEnabled()) {
               SettingProvider settingProvider2 = SettingContainer.settingProvider;
               double doubleItem3 = setPositionManager2.actionSetting7.getDouble6();
               double doubleItem4 = setPositionManager2.actionSetting7.getDouble3();
               double doubleItem5 = settingProvider2.getDouble4(doubleItem4, doubleItem3);
               if (factor == 0.0F) {
                  MoveUtil.onMoveEntity(doubleItem5, false, event);
               } else {
                  MoveUtil.onMoveEntity(doubleItem5, factor / 100.0F, event);
               }
            }

            if (setPositionManager2.booleanSetting.isEnabled()) {
               double coordOffset = event.getCoordOffset1();
               SettingProvider settingProvider3 = SettingContainer.settingProvider;
               double doubleItem6 = setPositionManager2.actionSetting5.getDouble3();
               double doubleItem7 = setPositionManager2.actionSetting5.getDouble6();
               double offset2 = doubleItem6;
               double offset3 = coordOffset + (double)settingProvider3.getFloat3(offset2, doubleItem7);
               event.setCoordOffset1(offset3);
            }

            if (setPositionManager2.booleanSetting13.isEnabled()) {
               SettingProvider settingProvider4 = SettingContainer.settingProvider;
               double doubleItem8 = setPositionManager2.actionSetting6.getDouble6();
               double doubleItem9 = setPositionManager2.actionSetting6.getDouble3();
               MoveUtil.onMoveEntityByEventMoveEntity(settingProvider4.getDouble4(doubleItem9, doubleItem8), false, event);
            }

            if (setPositionManager2.booleanSetting2.isEnabled()) {
               double coordOffset2 = event.getCoordOffset1();
               SettingProvider settingProvider5 = SettingContainer.settingProvider;
               double doubleItem10 = setPositionManager2.actionSetting3.getDouble3();
               double doubleItem11 = setPositionManager2.actionSetting3.getDouble6();
               double offset4 = doubleItem10;
               double offset5 = coordOffset2 * (double)settingProvider5.getFloat3(offset4, doubleItem11);
               event.setCoordOffset1(offset5);
            }

            if (setPositionManager2.booleanSetting4.isEnabled()) {
               SettingProvider settingProvider6 = SettingContainer.settingProvider;
               double doubleItem12 = setPositionManager2.actionSetting2.getDouble6();
               double doubleItem13 = setPositionManager2.actionSetting2.getDouble3();
               double doubleItem14 = settingProvider6.getDouble4(doubleItem13, doubleItem12);
               double offset6 = event.getCoordOffset2() * doubleItem14;
               event.setCoordOffset2(offset6);
               double offset7 = event.getCoordOffset() * doubleItem14;
               event.setCoordOffset(offset7);
            }

            if (setPositionManager2.booleanSetting3.isEnabled()) {
               MinecraftTimer timer = MinecraftAccessor.minecraft.getTimer();
               SettingProvider settingProvider7 = SettingContainer.settingProvider;
               double doubleItem15 = setPositionManager2.actionSetting4.getDouble3();
               double doubleItem16 = setPositionManager2.actionSetting4.getDouble6();
               double offset8 = doubleItem15;
               timer.scaleFactor2 = Math.max(0.01F, settingProvider7.getFloat3(offset8, doubleItem16));
            }

            if (factor > 0.0F) {
               MoveUtil.onMoveEntity(
                  Math.sqrt(event.getCoordOffset2() * event.getCoordOffset2() + event.getCoordOffset() * event.getCoordOffset()), factor / 100.0F, event
               );
            }
         }
      }
   }

   private void applyText(String text) {
      try {
         HashMap map = (HashMap)((HashMap)this.hashMap2.get(text)).get(text);
         SetPositionManager setPositionManager2 = (SetPositionManager)map.get(text);
         this.applySetPositionManager(setPositionManager2);
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public HashMap getEntryMap2() {
      return this.hashMap;
   }

   public void onMove(EventMove event) {
      if (this.settingBlockJumpInput.isEnabled()) {
         event.setState(false);
      }

      if (!this.enabled8
         && this.setPositionManager.settingBlockMovementInput.isEnabled()
         && this.setPositionManager.settingSelfDamageMode.getSelected().equals("3Jump")) {
         float factor = 0.0F;
         event.setScaleFactor2(factor);
         event.setState(false);
         float factor2 = 0.0F;
         event.setScaleFactor7(factor2);
      }
   }

   public void processAction() {
      MinecraftAccessor.minecraft.getTimer().scaleFactor2 = 1.0F;
      if (MinecraftAccessor.minecraft.thePlayer != null) {
         MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02F);
      }

      String text = "OnDisable";
      this.applyText(text);
   }

   private void applySetPositionManager(SetPositionManager setPositionManager2) {
      long value = 0x1f6d6da460f3L;
      long keySalt = 0x343432eacb7dL ^ value;
      int count10 = (int)((0x343432eacb7dL ^ value ^ 0x74cf7485040aL) >>> 56);
      if (setPositionManager2 != null && MinecraftAccessor.minecraft.thePlayer != null && setPositionManager2.settingEditTick.isEnabled()) {
         if ((!setPositionManager2.booleanSetting5.isEnabled() || MinecraftAccessor.minecraft.thePlayer.hurtTime <= 0)
            && (setPositionManager2.booleanSetting6 == null || !setPositionManager2.booleanSetting6.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWeb())
            && (setPositionManager2.booleanSetting11 == null || !setPositionManager2.booleanSetting11.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWater())
            && (setPositionManager2.booleanSetting8 == null || !setPositionManager2.booleanSetting8.isEnabled() || MinecraftAccessor.minecraft.thePlayer.canExecute9())) {
            float factor = MathHelper.clamp_float(0.0F, (float)setPositionManager2.numberSetting.getValue(), 100.0F);
            if (setPositionManager2.booleanSetting14 != null && setPositionManager2.booleanSetting14.isEnabled()) {
               MinecraftAccessor.minecraft.thePlayer.closeScreen();
            }

            this.applySetPositionManagerBySetPositionManag(setPositionManager2);
            if (setPositionManager2.booleanSetting10 != null && setPositionManager2.booleanSetting10.isEnabled()) {
               EntityPlayerSP player = MinecraftAccessor.minecraft.thePlayer;
               SettingProvider settingProvider = SettingContainer.settingProvider;
               double doubleItem = setPositionManager2.actionSetting.getDouble3();
               double doubleItem2 = setPositionManager2.actionSetting.getDouble6();
               double offset = doubleItem;
               player.setScaleFactor2(settingProvider.getFloat3(offset, doubleItem2));
            } else {
               MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02F);
            }

            if (setPositionManager2.booleanSetting9 != null && setPositionManager2.booleanSetting9.isEnabled()) {
               MinecraftAccessor.minecraft.thePlayer.onGround = true;
            }

            if (setPositionManager2.booleanSetting12 != null && setPositionManager2.booleanSetting12.isEnabled()) {
               EntityPlayerSP player2 = MinecraftAccessor.minecraft.thePlayer;
               SettingProvider settingProvider2 = SettingContainer.settingProvider;
               double doubleItem3 = setPositionManager2.actionSetting9.getDouble3();
               double doubleItem4 = setPositionManager2.actionSetting9.getDouble6();
               double offset2 = doubleItem3;
               player2.motionY = (double)settingProvider2.getFloat3(offset2, doubleItem4);
            }

            if (setPositionManager2.booleanSetting7 != null && setPositionManager2.booleanSetting7.isEnabled()) {
               SettingProvider settingProvider3 = SettingContainer.settingProvider;
               double doubleItem5 = setPositionManager2.actionSetting7.getDouble3();
               double doubleItem6 = setPositionManager2.actionSetting7.getDouble6();
               double offset3 = doubleItem5;
               float floatItem = settingProvider3.getFloat3(offset3, doubleItem6);
               if (factor == 0.0F) {
                  MoveUtil.processFloat2(floatItem, false);
               } else {
                  MoveUtil.applyDouble((double)floatItem, factor / 100.0F);
               }
            }

            if (setPositionManager2.booleanSetting != null && setPositionManager2.booleanSetting.isEnabled()) {
               EntityPlayerSP player3 = MinecraftAccessor.minecraft.thePlayer;
               double motionY = MinecraftAccessor.minecraft.thePlayer.motionY;
               SettingProvider settingProvider4 = SettingContainer.settingProvider;
               double doubleItem7 = setPositionManager2.actionSetting5.getDouble3();
               double doubleItem8 = setPositionManager2.actionSetting5.getDouble6();
               double offset4 = doubleItem7;
               player3.motionY = motionY + (double)settingProvider4.getFloat3(offset4, doubleItem8);
            }

            if (setPositionManager2.booleanSetting13 != null && setPositionManager2.booleanSetting13.isEnabled()) {
               SettingProvider settingProvider5 = SettingContainer.settingProvider;
               double doubleItem9 = setPositionManager2.actionSetting6.getDouble6();
               double doubleItem10 = setPositionManager2.actionSetting6.getDouble3();
               double doubleItem11 = settingProvider5.getDouble4(doubleItem10, doubleItem9);
               MoveUtil.processAction(doubleItem11, false);
            }

            if (setPositionManager2.booleanSetting2 != null && setPositionManager2.booleanSetting2.isEnabled()) {
               EntityPlayerSP player4 = MinecraftAccessor.minecraft.thePlayer;
               double motionY2 = MinecraftAccessor.minecraft.thePlayer.motionY;
               SettingProvider settingProvider6 = SettingContainer.settingProvider;
               double doubleItem12 = setPositionManager2.actionSetting3.getDouble3();
               double doubleItem13 = setPositionManager2.actionSetting3.getDouble6();
               double offset5 = doubleItem12;
               player4.motionY = motionY2 * (double)settingProvider6.getFloat3(offset5, doubleItem13);
            }

            if (setPositionManager2.booleanSetting4 != null && setPositionManager2.booleanSetting4.isEnabled()) {
               SettingProvider settingProvider7 = SettingContainer.settingProvider;
               double doubleItem14 = setPositionManager2.actionSetting2.getDouble3();
               double doubleItem15 = setPositionManager2.actionSetting2.getDouble6();
               double offset6 = doubleItem14;
               double floatItem2 = (double)settingProvider7.getFloat3(offset6, doubleItem15);
               MinecraftAccessor.minecraft.thePlayer.motionX *= floatItem2;
               MinecraftAccessor.minecraft.thePlayer.motionZ *= floatItem2;
            }

            if (setPositionManager2.booleanSetting3 != null && setPositionManager2.booleanSetting3.isEnabled()) {
               MinecraftTimer timer = MinecraftAccessor.minecraft.getTimer();
               SettingProvider settingProvider8 = SettingContainer.settingProvider;
               double doubleItem16 = setPositionManager2.actionSetting4.getDouble3();
               double doubleItem17 = setPositionManager2.actionSetting4.getDouble6();
               double offset7 = doubleItem16;
               timer.scaleFactor2 = Math.max(0.01F, settingProvider8.getFloat3(offset7, doubleItem17));
            }

            if (factor > 0.0F) {
               MoveUtil.processAction(factor / 100.0F);
            }
         }
      }
   }

   public void updateState() {
      if (!this.enabled4
         && (MinecraftAccessor.minecraft.thePlayer.onGround || !this.setPositionManager.settingOnlyOnGround.isEnabled())
         && !this.setPositionManager.settingSelfDamageMode.getSelected().equals("3Jump")) {
         SetPositionManager setPositionManager2 = this.setPositionManager;
         this.applySetPositionManagerBySetPositionManag(setPositionManager2);
         this.enabled4 = true;
      }

      if (MinecraftAccessor.minecraft.thePlayer.hurtTime != 0 || !this.settingWaitForDamage.isEnabled()) {
         this.enabled8 = true;
      }

      if ((MoveUtil.canExecute() || !this.settingOnlyWhileMoving.isEnabled())
         && (
            this.settingOnlyWhileJumpKeyPressed == null
               || !this.settingOnlyWhileJumpKeyPressed.isEnabled()
               || MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed
         )
         && (!this.settingOnlyInWeb.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWeb())
         && (!this.settingOnlyInWater.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWater())
         && (!this.settingOnlyAtLadder.isEnabled() || MinecraftAccessor.minecraft.thePlayer.canExecute9())
         && this.enabled8) {
         HashMap map = (HashMap)this.hashMap2.get("PreUpdate");
         if (MinecraftAccessor.minecraft.thePlayer.motionY < 0.0) {
            HashMap map2 = (HashMap)map.get("MotionYLessZero");
            SetPositionManager setPositionManager3 = (SetPositionManager)map2.get("MotionYLessZero");
            this.applySetPositionManager(setPositionManager3);
         } else {
            HashMap map3 = (HashMap)map.get("MotionYHigherZero");
            SetPositionManager setPositionManager4 = (SetPositionManager)map3.get("MotionYHigherZero");
            this.applySetPositionManager(setPositionManager4);
         }

         HashMap map4 = (HashMap)map.get("TicksOnGround");
         HashMap map5 = (HashMap)map.get("TicksInAir");
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            SetPositionManager setPositionManager5 = (SetPositionManager)map5.get(-1);
            this.count2++;
            if (setPositionManager5.settingResetAtOppositeState.isEnabled()) {
               this.count = -1;
            }

            if (this.count2 >= 0) {
               SetPositionManager setPositionManager6 = (SetPositionManager)map4.get(-1);
               if ((double)this.count2 > setPositionManager6.actionSetting8.getDouble6() || (double)this.count2 < setPositionManager6.actionSetting8.getDouble3() && this.enabled2) {
                  this.count2 = (int)Math.max(0.0, setPositionManager6.actionSetting8.getDouble3());
                  this.enabled2 = true;
               }

               SetPositionManager setPositionManager7 = (SetPositionManager)map4.get(this.count2);
               this.applySetPositionManager(setPositionManager7);
            }
         } else {
            this.count++;
            if (((SetPositionManager)map4.get(-1)).settingResetAtOppositeState.isEnabled()) {
               this.count2 = -1;
            }

            if (this.count >= 0) {
               SetPositionManager setPositionManager8 = (SetPositionManager)map5.get(-1);
               if ((double)this.count > setPositionManager8.actionSetting8.getDouble6() || (double)this.count < setPositionManager8.actionSetting8.getDouble3() && this.enabled7) {
                  this.count = (int)Math.max(0.0, setPositionManager8.actionSetting8.getDouble3());
                  this.enabled7 = true;
               }

               SetPositionManager setPositionManager9 = (SetPositionManager)map5.get(this.count);
               this.applySetPositionManager(setPositionManager9);
            }
         }
      }
   }

   private void applySetPositionManagerBySetPositionManag(SetPositionManager setPositionManager2) {
      if (!setPositionManager2.settingSelfDamageMode.getSelected().equals("None")) {
         String selected = setPositionManager2.settingSelfDamageMode.getSelected();
         switch (selected) {
            case "Motion":
               MinecraftAccessor.minecraft.thePlayer.motionY = (double)((float)setPositionManager2.settingMotion.getValue());
               break;
            case "Packet1":
               CombatUtil.updateState3();
               break;
            case "Packet2":
               CombatUtil.updateState();
               break;
            case "Fake":
               MinecraftAccessor.minecraft.thePlayer.processByte((byte)2);
               break;
            case "Custom":
               for (int index = 0; (double)index < setPositionManager2.settingLoops.getValue(); index++) {
                  MinecraftAccessor.minecraft
                     .thePlayer
                     .netHandlerPlayClient
                     .handlePacket2(
                        new C04PacketPlayerPosition(
                           MinecraftAccessor.minecraft.thePlayer.posX,
                           MinecraftAccessor.minecraft.thePlayer.posY + setPositionManager2.settingYAddInLoopFirst.getValue(),
                           MinecraftAccessor.minecraft.thePlayer.posZ,
                           false
                        )
                     );
                  if (setPositionManager2.settingSecondPacket.isEnabled()) {
                     MinecraftAccessor.minecraft
                        .thePlayer
                        .netHandlerPlayClient
                        .handlePacket2(
                           new C04PacketPlayerPosition(
                              MinecraftAccessor.minecraft.thePlayer.posX,
                              MinecraftAccessor.minecraft.thePlayer.posY + setPositionManager2.settingYAddInLoopSecond.getValue(),
                              MinecraftAccessor.minecraft.thePlayer.posZ,
                              false
                           )
                        );
                  }
               }

               if (setPositionManager2.settingFinishPacket.isEnabled()) {
                  MinecraftAccessor.minecraft
                     .thePlayer
                     .netHandlerPlayClient
                     .handlePacket2(
                        new C04PacketPlayerPosition(
                           MinecraftAccessor.minecraft.thePlayer.posX,
                           MinecraftAccessor.minecraft.thePlayer.posY + setPositionManager2.settingYAddFinish.getValue(),
                           MinecraftAccessor.minecraft.thePlayer.posZ,
                           true
                        )
                     );
               }
               break;
            case "SetPosition":
               MinecraftAccessor.minecraft
                  .thePlayer
                  .processDouble2(
                     MinecraftAccessor.minecraft.thePlayer.posX,
                     MinecraftAccessor.minecraft.thePlayer.posY + setPositionManager2.settingYAdd.getValue(),
                     MinecraftAccessor.minecraft.thePlayer.posZ
                  );
         }
      }
   }

   public void updateState2() {
      this.count = -1;
      this.count2 = -1;
      this.count4 = -1;
      this.count9 = -1;
      this.count5 = -1;
      this.count7 = -1;
      this.enabled6 = false;
      this.enabled7 = false;
      this.enabled5 = false;
      this.enabled = false;
      this.enabled3 = false;
      this.enabled2 = false;
      this.enabled8 = !this.settingWaitForDamage.isEnabled();
      this.enabled4 = false;
      this.count8 = 0;
      this.count6 = 0;
      this.count3 = 0;
      String text = "OnEnable";
      this.applyText(text);
   }

   public void onPacket(EventPacket packet) {
      if (this.setPositionManager.settingSelfDamageMode.getSelected().equals("3Jump") && this.count3 < 4) {
         Packet packet2 = packet.getPacket();
         if (packet2 instanceof C03PacketPlayer) {
            ((C03PacketPlayer)packet2).setOnGround(false);
         }
      }
   }

   public void updateState3() {
      String[] textArray = new String[]{"PreUpdate", "PostUpdate", "MoveEntity"};
      String[] textArray2 = new String[]{"TicksOnGround", "TicksInAir"};
      String[] textArray3 = new String[]{"MotionYLessZero", "MotionYHigherZero"};
      String[] textArray4 = new String[]{"OnEnable", "OnDisable"};

      for (String text : textArray) {
         SettingGroup settingGroup = new SettingGroup(this.module, text);
         HashMap map = new HashMap();
         this.hashMap2.put(text, map);

         for (String text2 : textArray3) {
            HashMap map2 = new HashMap();
            map.put(text2, map2);
            SettingGroup setting = (SettingGroup)(new SettingGroup(this.module, text2 + text, settingGroup)).getSetting(text2);
            map2.put(text2, new SetPositionManager(this.module, text2 + text, setting, text.equals("PreUpdate"), this.hashMap));
         }

         for (String text3 : textArray2) {
            HashMap map3 = new HashMap();
            map.put(text3, map3);
            SettingGroup setting2 = (SettingGroup)(new SettingGroup(this.module, text3 + text, settingGroup)).getSetting(text3);
            map3.put(-1, new SetPositionManager(this.module, text3 + text, setting2, this.hashMap));

            for (int index = 0; index < 60; index++) {
               String text4 = "Tick" + index;
               SettingGroup setting3 = (SettingGroup)(new SettingGroup(this.module, text4 + text3 + text, setting2)).getSetting(text4);
               Integer count10 = index;
               Module module2 = this.module;
               String text5 = text4 + text3 + text;
               boolean flag = text.equals("PreUpdate") && text3.equals("TicksInAir");
               HashMap hashMap3 = this.hashMap;
               boolean flag2 = flag;
               String text6 = text5;
               Module module3 = module2;
               map3.put(count10, new SetPositionManager(module3, text6, setting3, flag2, hashMap3));
            }
         }
      }

      for (String text7 : textArray4) {
         HashMap map4 = new HashMap();
         this.hashMap2.put(text7, map4);
         HashMap map5 = new HashMap();
         map4.put(text7, map5);
         SettingGroup settingGroup2 = new SettingGroup(this.module, text7);
         map5.put(text7, new SetPositionManager(this.module, text7, settingGroup2, text7.equals("OnEnable"), this.hashMap));
      }
   }

   public void updateState4() {
      if (this.setPositionManager.settingSelfDamageMode.getSelected().equals("3Jump")) {
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            if (this.count3 < 3) {
               MinecraftAccessor.minecraft.thePlayer.closeScreen();
               CombatUtil.updateState2();
            }

            this.count3++;
         }

         this.enabled4 = true;
      }

      if ((MoveUtil.canExecute() || !this.settingOnlyWhileMoving.isEnabled())
         && (
            this.settingOnlyWhileJumpKeyPressed == null
               || !this.settingOnlyWhileJumpKeyPressed.isEnabled()
               || MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed
         )
         && (!this.settingOnlyInWeb.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWeb())
         && (!this.settingOnlyInWater.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWater())
         && (!this.settingOnlyAtLadder.isEnabled() || MinecraftAccessor.minecraft.thePlayer.canExecute9())
         && this.enabled8) {
         HashMap map = (HashMap)this.hashMap2.get("PostUpdate");
         if (MinecraftAccessor.minecraft.thePlayer.motionY < 0.0) {
            HashMap map2 = (HashMap)map.get("MotionYLessZero");
            SetPositionManager setPositionManager2 = (SetPositionManager)map2.get("MotionYLessZero");
            this.applySetPositionManager(setPositionManager2);
         } else {
            HashMap map3 = (HashMap)map.get("MotionYHigherZero");
            SetPositionManager setPositionManager3 = (SetPositionManager)map3.get("MotionYHigherZero");
            this.applySetPositionManager(setPositionManager3);
         }

         HashMap map4 = (HashMap)map.get("TicksOnGround");
         HashMap map5 = (HashMap)map.get("TicksInAir");
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            this.count9++;
            if (((SetPositionManager)map5.get(-1)).settingResetAtOppositeState.isEnabled()) {
               this.count4 = -1;
            }

            if (this.count9 >= 0) {
               SetPositionManager setPositionManager4 = (SetPositionManager)map4.get(-1);
               if ((double)this.count9 > setPositionManager4.actionSetting8.getDouble6() || (double)this.count9 < setPositionManager4.actionSetting8.getDouble3() && this.enabled3) {
                  this.count9 = (int)Math.max(0.0, setPositionManager4.actionSetting8.getDouble3());
                  this.enabled3 = true;
               }

               SetPositionManager setPositionManager5 = (SetPositionManager)map4.get(this.count9);
               this.applySetPositionManager(setPositionManager5);
            }
         } else {
            this.count4++;
            if (((SetPositionManager)map4.get(-1)).settingResetAtOppositeState.isEnabled()) {
               this.count9 = -1;
            }

            if (this.count4 >= 0) {
               SetPositionManager setPositionManager6 = (SetPositionManager)map5.get(-1);
               if ((double)this.count4 > setPositionManager6.actionSetting8.getDouble6() || (double)this.count4 < setPositionManager6.actionSetting8.getDouble3() && this.enabled5) {
                  this.count4 = (int)Math.max(0.0, setPositionManager6.actionSetting8.getDouble3());
                  this.enabled5 = true;
               }

               SetPositionManager setPositionManager7 = (SetPositionManager)map5.get(this.count4);
               this.applySetPositionManager(setPositionManager7);
            }
         }
      }
   }

   public void onSendPacket(EventSendPacket packet) {
      if (this.setPositionManager.settingSelfDamageMode.getSelected().equals("3Jump") && this.count3 < 4) {
         packet.setCancelled(true);
      }
   }

   public void onMoveEntity(EventMoveEntity event) {
      long value = 1053969747L;
      short shortValue = 19046;
      if ((MoveUtil.canExecute() || !this.settingOnlyWhileMoving.isEnabled())
         && (!this.settingOnlyWhileJumpKeyPressed.isEnabled() || MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed)
         && (!this.settingOnlyInWeb.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWeb())
         && (!this.settingOnlyInWater.isEnabled() || MinecraftAccessor.minecraft.thePlayer.isInWater())
         && (!this.settingOnlyAtLadder.isEnabled() || MinecraftAccessor.minecraft.thePlayer.canExecute9())
         && this.enabled8) {
         HashMap map = (HashMap)this.hashMap2.get("MoveEntity");
         if (event.getCoordOffset1() < 0.0) {
            HashMap map2 = (HashMap)map.get("MotionYLessZero");
            SetPositionManager setPositionManager2 = (SetPositionManager)map2.get("MotionYLessZero");
            this.onMoveEntity(setPositionManager2, event);
         } else {
            HashMap map3 = (HashMap)map.get("MotionYHigherZero");
            SetPositionManager setPositionManager3 = (SetPositionManager)map3.get("MotionYHigherZero");
            this.onMoveEntity(setPositionManager3, event);
         }

         HashMap map4 = (HashMap)map.get("TicksOnGround");
         HashMap map5 = (HashMap)map.get("TicksInAir");
         if (MinecraftAccessor.minecraft.thePlayer.onGround) {
            this.count7++;
            if (((SetPositionManager)map5.get(-1)).settingResetAtOppositeState.isEnabled()) {
               this.count5 = -1;
            }

            if (this.count7 >= 0) {
               SetPositionManager setPositionManager4 = (SetPositionManager)map4.get(-1);
               if ((double)this.count7 > setPositionManager4.actionSetting8.getDouble6() || (double)this.count7 < setPositionManager4.actionSetting8.getDouble3() && this.enabled) {
                  this.count7 = (int)Math.max(0.0, setPositionManager4.actionSetting8.getDouble3());
                  this.enabled = true;
               }

               SetPositionManager setPositionManager5 = (SetPositionManager)map4.get(this.count7);
               this.onMoveEntity(setPositionManager5, event);
            }
         } else {
            this.count5++;
            if (((SetPositionManager)map4.get(-1)).settingResetAtOppositeState.isEnabled()) {
               this.count7 = -1;
            }

            if (this.count5 >= 0) {
               SetPositionManager setPositionManager6 = (SetPositionManager)map5.get(-1);
               if ((double)this.count5 > setPositionManager6.actionSetting8.getDouble6() || (double)this.count5 < setPositionManager6.actionSetting8.getDouble3() && this.enabled6) {
                  this.count5 = (int)Math.max(0.0, setPositionManager6.actionSetting8.getDouble3());
                  this.enabled6 = true;
               }

               SetPositionManager setPositionManager7 = (SetPositionManager)map5.get(this.count5);
               this.onMoveEntity(setPositionManager7, event);
            }
         }
      }
   }

   public MotionSettingGroup(Module module2) {
      this.hashMap2 = new HashMap();
      this.count2 = 0;
      this.enabled2 = false;
      this.count = 0;
      this.enabled7 = false;
      this.count9 = 0;
      this.enabled3 = false;
      this.count4 = 0;
      this.enabled5 = false;
      this.count7 = 0;
      this.enabled = false;
      this.count5 = 0;
      this.enabled6 = false;
      this.enabled8 = false;
      this.enabled4 = false;
      this.count8 = 0;
      this.count6 = 0;
      this.count3 = 0;
      this.module = module2;
      this.settingBlockJumpInput = new BooleanSetting(this.module, "BlockJumpInput", true);
      this.settingOnlyWhileMoving = new BooleanSetting(this.module, "OnlyWhileMoving", true);
      this.settingOnlyWhileJumpKeyPressed = new BooleanSetting(this.module, "OnlyWhileJumpKeyPressed", false);
      this.settingOnlyInWater = new BooleanSetting(this.module, "OnlyInWater", false);
      this.settingOnlyInWeb = new BooleanSetting(module2, "OnlyInWeb", false);
      this.settingOnlyAtLadder = new BooleanSetting(module2, "OnlyAtLadder", false);
      this.settingWaitForDamage = new BooleanSetting(this.module, "WaitForDamage", false);
      this.setPositionManager = new SetPositionManager(this.module, "SelfDamageSavings", new SettingGroup(module2, "SelfDamageStart"), 0.0, this.hashMap);
      if (this.module instanceof AuxiliaryModuleAdapter) {
         this.hashMap.put(this.settingBlockJumpInput.getString(), this.settingBlockJumpInput);
         this.hashMap.put(this.settingOnlyWhileMoving.getString(), this.settingOnlyWhileMoving);
         this.hashMap.put(this.settingOnlyWhileJumpKeyPressed.getString(), this.settingOnlyWhileJumpKeyPressed);
         this.hashMap.put(this.settingOnlyInWater.getString(), this.settingOnlyInWater);
         this.hashMap.put(this.settingOnlyInWeb.getString(), this.settingOnlyInWeb);
         this.hashMap.put(this.settingOnlyAtLadder.getString(), this.settingOnlyAtLadder);
         this.hashMap.put(this.settingWaitForDamage.getString(), this.settingWaitForDamage);
      }
   }
}
