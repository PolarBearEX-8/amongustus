package net.augustus.modules.modes.motion;

import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.modules.modes.MotionModifierRuleSettings;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.player.PlayerUtil;
import java.util.HashMap;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Timer;

public class MotionModifierEngine {
   private final BooleanValue blockJumpInput;
   private boolean moveEntityAirLooped;
   private final HashMap rulesByPhase;
   private final BooleanValue onlyWhileJumpKeyPressed;
   private boolean preUpdateAirLooped;
   private int moveEntityAirTicks;
   private boolean moveEntityGroundLooped;
   private boolean preUpdateGroundLooped;
   private int moveEntityGroundTicks;
   private final BooleanValue waitForDamage;
   private final BooleanValue onlyAtLadder;
   private boolean damageConditionMet;
   private final BooleanValue onlyInWeb;
   private int preUpdateGroundTicks;
   private boolean initialSelfDamageApplied;
   private int threeJumpCounter;
   private final MotionModifierRuleSettings motionModifierRuleSettings;
   private final Module ownerModule;
   private boolean postUpdateGroundLooped;
   private int postUpdateGroundTicks;
   private final BooleanValue onlyInWater;
   private int postUpdateAirTicks;
   private final BooleanValue onlyWhileMoving;
   private boolean postUpdateAirLooped;
   private int selfDamageStep;
   private int selfDamageTicks;
   private int preUpdateAirTicks;

   public void onSendPacket(EventPacket event) {
      if (this.motionModifierRuleSettings.selfDamageMode.getSelectedValue().equals("3Jump") && this.threeJumpCounter < 4) {
         Packet packet = event.getPacket();
         if (packet instanceof C03PacketPlayer) {
            ((C03PacketPlayer)packet).setOnGround(false);
         }
      }
   }

   public void onMoveEntity(EventMoveEntity event) {
      if ((MoveUtil.isMoving() || !this.onlyWhileMoving.getValue())
         && (!this.onlyWhileJumpKeyPressed.getValue() || MC.mc.gameSettings.keyBindJump.isKeyDown())
         && (!this.onlyInWeb.getValue() || MC.mc.thePlayer.isInWeb())
         && (!this.onlyInWater.getValue() || MC.mc.thePlayer.isInWater())
         && (!this.onlyAtLadder.getValue() || this.isOnLadder())
         && this.damageConditionMet) {
         HashMap map = (HashMap)this.rulesByPhase.get("MoveEntity");
         if (event.getY() < 0.0) {
            this.applyMoveEntityRule((MotionModifierRuleSettings)((HashMap)map.get("MotionYLessZero")).get("MotionYLessZero"), event);
         } else {
            this.applyMoveEntityRule((MotionModifierRuleSettings)((HashMap)map.get("MotionYHigherZero")).get("MotionYHigherZero"), event);
         }

         HashMap map4 = (HashMap)map.get("TicksOnGround");
         HashMap map5 = (HashMap)map.get("TicksInAir");
         if (MC.mc.thePlayer.onGround) {
            this.moveEntityGroundTicks++;
            if (((MotionModifierRuleSettings)map5.get(-1)).resetAtOppositeState.getValue()) {
               this.moveEntityAirTicks = -1;
            }

            if (this.moveEntityGroundTicks >= 0) {
               MotionModifierRuleSettings settings6 = (MotionModifierRuleSettings)map4.get(-1);
               if (((double)this.moveEntityGroundTicks) > settings6.resetTicksAt.getMaxValue() || (double)this.moveEntityGroundTicks < settings6.resetTicksAt.getMinValue() && this.moveEntityGroundLooped) {
                  this.moveEntityGroundTicks = (int)Math.max(0.0, settings6.resetTicksAt.getMinValue());
                  this.moveEntityGroundLooped = true;
               }

               this.applyMoveEntityRule((MotionModifierRuleSettings)map4.get(this.moveEntityGroundTicks), event);
            }
         } else {
            this.moveEntityAirTicks++;
            if (((MotionModifierRuleSettings)map4.get(-1)).resetAtOppositeState.getValue()) {
               this.moveEntityGroundTicks = -1;
            }

            if (this.moveEntityAirTicks >= 0) {
               MotionModifierRuleSettings settings10 = (MotionModifierRuleSettings)map5.get(-1);
               if (((double)this.moveEntityAirTicks) > settings10.resetTicksAt.getMaxValue() || (double)this.moveEntityAirTicks < settings10.resetTicksAt.getMinValue() && this.moveEntityAirLooped) {
                  this.moveEntityAirTicks = (int)Math.max(0.0, settings10.resetTicksAt.getMinValue());
                  this.moveEntityAirLooped = true;
               }

               this.applyMoveEntityRule((MotionModifierRuleSettings)map5.get(this.moveEntityAirTicks), event);
            }
         }
      }
   }

   public MotionModifierEngine(Module module) {
       super();
      this.rulesByPhase = new HashMap();
      this.preUpdateGroundTicks = 0;
      this.preUpdateGroundLooped = false;
      this.preUpdateAirTicks = 0;
      this.preUpdateAirLooped = false;
      this.postUpdateGroundTicks = 0;
      this.postUpdateGroundLooped = false;
      this.postUpdateAirTicks = 0;
      this.postUpdateAirLooped = false;
      this.moveEntityGroundTicks = 0;
      this.moveEntityGroundLooped = false;
      this.moveEntityAirTicks = 0;
      this.moveEntityAirLooped = false;
      this.damageConditionMet = false;
      this.initialSelfDamageApplied = false;
      this.selfDamageStep = 0;
      this.selfDamageTicks = 0;
      this.threeJumpCounter = 0;
      this.ownerModule = module;
      this.blockJumpInput = new BooleanValue(this.ownerModule, "BlockJumpInput", true);
      this.onlyWhileMoving = new BooleanValue(this.ownerModule, "OnlyWhileMoving", true);
      this.onlyWhileJumpKeyPressed = new BooleanValue(this.ownerModule, "OnlyWhileJumpKeyPressed", false);
      this.onlyInWater = new BooleanValue(this.ownerModule, "OnlyInWater", false);
      this.onlyInWeb = new BooleanValue(this.ownerModule, "OnlyInWeb", false);
      this.onlyAtLadder = new BooleanValue(this.ownerModule, "OnlyAtLadder", false);
      this.waitForDamage = new BooleanValue(this.ownerModule, "WaitForDamage", false);
      this.motionModifierRuleSettings = new MotionModifierRuleSettings(this.ownerModule, "SelfDamageSavings", new SettingGroup(module, "SelfDamageStart"), 0.0);
   }

   public void onEnable() {
      this.preUpdateAirTicks = -1;
      this.preUpdateGroundTicks = -1;
      this.postUpdateAirTicks = -1;
      this.postUpdateGroundTicks = -1;
      this.moveEntityAirTicks = -1;
      this.moveEntityGroundTicks = -1;
      this.moveEntityAirLooped = false;
      this.preUpdateAirLooped = false;
      this.postUpdateAirLooped = false;
      this.moveEntityGroundLooped = false;
      this.postUpdateGroundLooped = false;
      this.preUpdateGroundLooped = false;
      this.damageConditionMet = !this.waitForDamage.getValue();
      this.initialSelfDamageApplied = false;
      this.selfDamageStep = 0;
      this.selfDamageTicks = 0;
      this.threeJumpCounter = 0;
      this.applyLifecyclePhaseRule("OnEnable");
   }

   private void applyMoveEntityRule(MotionModifierRuleSettings settings, EventMoveEntity event) {
      if (MC.mc.thePlayer != null && settings.editTick.getValue()) {
         if ((!settings.notWhileKB.getValue() || MC.mc.thePlayer.hurtTime <= 0)
            && (!settings.onlyInWeb.getValue() || MC.mc.thePlayer.isInWeb())
            && (!settings.onlyInWater.getValue() || MC.mc.thePlayer.isInWater())
            && (!settings.onlyAtLadder.getValue() || this.isOnLadder())) {
            float f = MathHelper.clamp_float(0.0F, (float)settings.strafePercentage.getValue(), 100.0F);
            if (settings.editStaticY.getValue()) {
               event.setY((double)DefaultRandomGenerator.instance.nextFloat(settings.staticY.getMinValue(), settings.staticY.getMaxValue()));
            }

            if (settings.jump.getValue()) {
               MC.mc.thePlayer.jump();
            }

            this.applySelfDamage(settings);
            if (settings.spoofGround.getValue()) {
               MC.mc.thePlayer.onGround = true;
            }

            if (settings.editStaticXZ.getValue()) {
               double d5 = DefaultRandomGenerator.instance.nextDouble(settings.staticXZ.getMinValue(), settings.staticXZ.getMaxValue());
               if (f == 0.0F) {
                  MoveUtil.setEventSpeed(d5, false, event);
               } else {
                  MoveUtil.strafeEvent(d5, f / 100.0F, event);
               }
            }

            if (settings.editAddY.getValue()) {
               event.setY(event.getY() + (double)DefaultRandomGenerator.instance.nextFloat(settings.addY.getMinValue(), settings.addY.getMaxValue()));
            }

            if (settings.editAddXZ.getValue()) {
               MoveUtil.addEventMotion(DefaultRandomGenerator.instance.nextDouble(settings.addXZ.getMinValue(), settings.addXZ.getMaxValue()), false, event);
            }

            if (settings.editMultiplyY.getValue()) {
               event.setY(event.getY() * (double)DefaultRandomGenerator.instance.nextFloat(settings.multiplyY.getMinValue(), settings.multiplyY.getMaxValue()));
            }

            if (settings.editMultiplyXZ.getValue()) {
               double d15 = DefaultRandomGenerator.instance.nextDouble(settings.multiplyXZ.getMinValue(), settings.multiplyXZ.getMaxValue());
               event.setX(event.getX() * d15);
               event.setZ(event.getZ() * d15);
            }

            if (settings.editTimerSpeed.getValue()) {
               Timer timer = MC.mc.getTimer();
               RandomNumberGenerator instance7 = DefaultRandomGenerator.instance;
               double minValue7 = settings.timerSpeed.getMinValue();
               double maxValue7 = settings.timerSpeed.getMaxValue();
               timer.timerSpeed = Math.max(0.01F, instance7.nextFloat(minValue7, maxValue7));
            }

            if (f > 0.0F) {
               MoveUtil.strafeEvent(Math.sqrt(event.getX() * event.getX() + event.getZ() * event.getZ()), f / 100.0F, event);
            }
         }
      }
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.motionModifierRuleSettings.selfDamageMode.getSelectedValue().equals("3Jump") && this.threeJumpCounter < 4) {
         event.setDelayed(true);
      }
   }

   public void initRuleSettings() {
      String[] strings = new String[]{"PreUpdate", "PostUpdate", "MoveEntity"};
      String[] strings2 = new String[]{"TicksOnGround", "TicksInAir"};
      String[] strings3 = new String[]{"MotionYLessZero", "MotionYHigherZero"};
      String[] strings4 = new String[]{"OnEnable", "OnDisable"};

      for (String str : strings) {
         SettingGroup settingGroup = new SettingGroup(this.ownerModule, str);
         HashMap map = new HashMap();
         this.rulesByPhase.put(str, map);

         for (String str2 : strings3) {
            HashMap map2 = new HashMap();
            map.put(str2, map2);
            SettingGroup settingGroup2 = (SettingGroup)(new SettingGroup(this.ownerModule, str2 + str, settingGroup)).withDisplayName(str2);
            map2.put(str2, new MotionModifierRuleSettings(this.ownerModule, str2 + str, settingGroup2, str.equals("PreUpdate")));
         }

         for (String str3 : strings2) {
            HashMap map3 = new HashMap();
            map.put(str3, map3);
            SettingGroup settingGroup3 = (SettingGroup)(new SettingGroup(this.ownerModule, str3 + str, settingGroup)).withDisplayName(str3);
            map3.put(-1, new MotionModifierRuleSettings(this.ownerModule, str3 + str, settingGroup3));

            for (int i = 0; i < 60; i++) {
               String str4 = "Tick" + i;
               SettingGroup settingGroup4 = (SettingGroup)(new SettingGroup(this.ownerModule, str4 + str3 + str, settingGroup3)).withDisplayName(str4);
               Integer num = i;
               Module ownerModule2 = this.ownerModule;
               String str5 = str4 + str3 + str;
               boolean flag = str.equals("PreUpdate") && str3.equals("TicksInAir");
               map3.put(num, new MotionModifierRuleSettings(ownerModule2, str5, settingGroup4, flag));
            }
         }
      }

      for (String str6 : strings4) {
         HashMap map4 = new HashMap();
         this.rulesByPhase.put(str6, map4);
         HashMap map5 = new HashMap();
         map4.put(str6, map5);
         SettingGroup settingGroup5 = new SettingGroup(this.ownerModule, str6);
         map5.put(str6, new MotionModifierRuleSettings(this.ownerModule, str6, settingGroup5, str6.equals("OnEnable")));
      }
   }

   public void onDisable() {
      MC.mc.getTimer().timerSpeed = 1.0F;
      if (MC.mc.thePlayer != null) {
         MC.mc.thePlayer.setSpeedInAir(0.02F);
      }

      this.applyLifecyclePhaseRule("OnDisable");
   }

   public void onPostUpdate() {
      if (this.motionModifierRuleSettings.selfDamageMode.getSelectedValue().equals("3Jump")) {
         if (MC.mc.thePlayer.onGround) {
            if (this.threeJumpCounter < 3) {
               MC.mc.thePlayer.jump();
               PlayerUtil.triggerJumpStat();
            }

            this.threeJumpCounter++;
         }

         this.initialSelfDamageApplied = true;
      }

      if ((MoveUtil.isMoving() || !this.onlyWhileMoving.getValue())
         && (this.onlyWhileJumpKeyPressed == null || !this.onlyWhileJumpKeyPressed.getValue() || MC.mc.gameSettings.keyBindJump.isKeyDown())
         && (!this.onlyInWeb.getValue() || MC.mc.thePlayer.isInWeb())
         && (!this.onlyInWater.getValue() || MC.mc.thePlayer.isInWater())
         && (!this.onlyAtLadder.getValue() || this.isOnLadder())
         && this.damageConditionMet) {
         HashMap map = (HashMap)this.rulesByPhase.get("PostUpdate");
         if (MC.mc.thePlayer.motionY < 0.0) {
            this.applyUpdateRule((MotionModifierRuleSettings)((HashMap)map.get("MotionYLessZero")).get("MotionYLessZero"));
         } else {
            this.applyUpdateRule((MotionModifierRuleSettings)((HashMap)map.get("MotionYHigherZero")).get("MotionYHigherZero"));
         }

         HashMap map4 = (HashMap)map.get("TicksOnGround");
         HashMap map5 = (HashMap)map.get("TicksInAir");
         if (MC.mc.thePlayer.onGround) {
            this.postUpdateGroundTicks++;
            if (((MotionModifierRuleSettings)map5.get(-1)).resetAtOppositeState.getValue()) {
               this.postUpdateAirTicks = -1;
            }

            if (this.postUpdateGroundTicks >= 0) {
               MotionModifierRuleSettings settings4 = (MotionModifierRuleSettings)map4.get(-1);
               if (((double)this.postUpdateGroundTicks) > settings4.resetTicksAt.getMaxValue() || (double)this.postUpdateGroundTicks < settings4.resetTicksAt.getMinValue() && this.postUpdateGroundLooped) {
                  this.postUpdateGroundTicks = (int)Math.max(0.0, settings4.resetTicksAt.getMinValue());
                  this.postUpdateGroundLooped = true;
               }

               this.applyUpdateRule((MotionModifierRuleSettings)map4.get(this.postUpdateGroundTicks));
            }
         } else {
            this.postUpdateAirTicks++;
            if (((MotionModifierRuleSettings)map4.get(-1)).resetAtOppositeState.getValue()) {
               this.postUpdateGroundTicks = -1;
            }

            if (this.postUpdateAirTicks >= 0) {
               MotionModifierRuleSettings settings7 = (MotionModifierRuleSettings)map5.get(-1);
               if (((double)this.postUpdateAirTicks) > settings7.resetTicksAt.getMaxValue() || (double)this.postUpdateAirTicks < settings7.resetTicksAt.getMinValue() && this.postUpdateAirLooped) {
                  this.postUpdateAirTicks = (int)Math.max(0.0, settings7.resetTicksAt.getMinValue());
                  this.postUpdateAirLooped = true;
               }

               this.applyUpdateRule((MotionModifierRuleSettings)map5.get(this.postUpdateAirTicks));
            }
         }
      }
   }

   public void onPreUpdate() {
      if (!this.initialSelfDamageApplied && (MC.mc.thePlayer.onGround || !this.motionModifierRuleSettings.onlyOnGround.getValue()) && !this.motionModifierRuleSettings.selfDamageMode.getSelectedValue().equals("3Jump")) {
         this.applySelfDamage(this.motionModifierRuleSettings);
         this.initialSelfDamageApplied = true;
      }

      if (MC.mc.thePlayer.hurtTime != 0 || !this.waitForDamage.getValue()) {
         this.damageConditionMet = true;
      }

      if ((MoveUtil.isMoving() || !this.onlyWhileMoving.getValue())
         && (this.onlyWhileJumpKeyPressed == null || !this.onlyWhileJumpKeyPressed.getValue() || MC.mc.gameSettings.keyBindJump.isKeyDown())
         && (!this.onlyInWeb.getValue() || MC.mc.thePlayer.isInWeb())
         && (!this.onlyInWater.getValue() || MC.mc.thePlayer.isInWater())
         && (!this.onlyAtLadder.getValue() || this.isOnLadder())
         && this.damageConditionMet) {
         HashMap map = (HashMap)this.rulesByPhase.get("PreUpdate");
         if (MC.mc.thePlayer.motionY < 0.0) {
            this.applyUpdateRule((MotionModifierRuleSettings)((HashMap)map.get("MotionYLessZero")).get("MotionYLessZero"));
         } else {
            this.applyUpdateRule((MotionModifierRuleSettings)((HashMap)map.get("MotionYHigherZero")).get("MotionYHigherZero"));
         }

         HashMap map4 = (HashMap)map.get("TicksOnGround");
         HashMap map5 = (HashMap)map.get("TicksInAir");
         if (MC.mc.thePlayer.onGround) {
            MotionModifierRuleSettings settings4 = (MotionModifierRuleSettings)map5.get(-1);
            this.preUpdateGroundTicks++;
            if (settings4.resetAtOppositeState.getValue()) {
               this.preUpdateAirTicks = -1;
            }

            if (this.preUpdateGroundTicks >= 0) {
               MotionModifierRuleSettings settings5 = (MotionModifierRuleSettings)map4.get(-1);
               if (((double)this.preUpdateGroundTicks) > settings5.resetTicksAt.getMaxValue() || (double)this.preUpdateGroundTicks < settings5.resetTicksAt.getMinValue() && this.preUpdateGroundLooped) {
                  this.preUpdateGroundTicks = (int)Math.max(0.0, settings5.resetTicksAt.getMinValue());
                  this.preUpdateGroundLooped = true;
               }

               this.applyUpdateRule((MotionModifierRuleSettings)map4.get(this.preUpdateGroundTicks));
            }
         } else {
            this.preUpdateAirTicks++;
            if (((MotionModifierRuleSettings)map4.get(-1)).resetAtOppositeState.getValue()) {
               this.preUpdateGroundTicks = -1;
            }

            if (this.preUpdateAirTicks >= 0) {
               MotionModifierRuleSettings settings8 = (MotionModifierRuleSettings)map5.get(-1);
               if (((double)this.preUpdateAirTicks) > settings8.resetTicksAt.getMaxValue() || (double)this.preUpdateAirTicks < settings8.resetTicksAt.getMinValue() && this.preUpdateAirLooped) {
                  this.preUpdateAirTicks = (int)Math.max(0.0, settings8.resetTicksAt.getMinValue());
                  this.preUpdateAirLooped = true;
               }

               this.applyUpdateRule((MotionModifierRuleSettings)map5.get(this.preUpdateAirTicks));
            }
         }
      }
   }

   public boolean isOnLadder() {
      return MC.mc.thePlayer.isOnLadder();
   }

   private void applyUpdateRule(MotionModifierRuleSettings settings) {
      if (settings != null && MC.mc.thePlayer != null && settings.editTick.getValue()) {
         if ((!settings.notWhileKB.getValue() || MC.mc.thePlayer.hurtTime <= 0)
            && (settings.onlyInWeb == null || !settings.onlyInWeb.getValue() || MC.mc.thePlayer.isInWeb())
            && (settings.onlyInWater == null || !settings.onlyInWater.getValue() || MC.mc.thePlayer.isInWater())
            && (settings.onlyAtLadder == null || !settings.onlyAtLadder.getValue() || this.isOnLadder())) {
            float f = MathHelper.clamp_float(0.0F, (float)settings.strafePercentage.getValue(), 100.0F);
            if (settings.jump != null && settings.jump.getValue()) {
               MC.mc.thePlayer.jump();
            }

            this.applySelfDamage(settings);
            if (settings.editSpeedInAir != null && settings.editSpeedInAir.getValue()) {
               MC.mc.thePlayer.setSpeedInAir(DefaultRandomGenerator.instance.nextFloat(settings.speedInAir.getMinValue(), settings.speedInAir.getMaxValue()));
            } else {
               MC.mc.thePlayer.setSpeedInAir(0.02F);
            }

            if (settings.spoofGround != null && settings.spoofGround.getValue()) {
               MC.mc.thePlayer.onGround = true;
            }

            if (settings.editStaticY != null && settings.editStaticY.getValue()) {
               MC.mc.thePlayer.motionY = (double)DefaultRandomGenerator.instance.nextFloat(settings.staticY.getMinValue(), settings.staticY.getMaxValue());
            }

            if (settings.editStaticXZ != null && settings.editStaticXZ.getValue()) {
               float f2 = DefaultRandomGenerator.instance.nextFloat(settings.staticXZ.getMinValue(), settings.staticXZ.getMaxValue());
               if (f == 0.0F) {
                  MoveUtil.setSpeed(f2, false);
               } else {
                  MoveUtil.strafe((double)f2, f / 100.0F);
               }
            }

            if (settings.editAddY != null && settings.editAddY.getValue()) {
               MC.mc.thePlayer.motionY = MC.mc.thePlayer.motionY + (double)DefaultRandomGenerator.instance.nextFloat(settings.addY.getMinValue(), settings.addY.getMaxValue());
            }

            if (settings.editAddXZ != null && settings.editAddXZ.getValue()) {
               MoveUtil.addMotion(DefaultRandomGenerator.instance.nextDouble(settings.addXZ.getMinValue(), settings.addXZ.getMaxValue()), false);
            }

            if (settings.editMultiplyY != null && settings.editMultiplyY.getValue()) {
               MC.mc.thePlayer.motionY = MC.mc.thePlayer.motionY * (double)DefaultRandomGenerator.instance.nextFloat(settings.multiplyY.getMinValue(), settings.multiplyY.getMaxValue());
            }

            if (settings.editMultiplyXZ != null && settings.editMultiplyXZ.getValue()) {
               double d10 = (double)DefaultRandomGenerator.instance.nextFloat(settings.multiplyXZ.getMinValue(), settings.multiplyXZ.getMaxValue());
               MC.mc.thePlayer.motionX *= d10;
               MC.mc.thePlayer.motionZ *= d10;
            }

            if (settings.editTimerSpeed != null && settings.editTimerSpeed.getValue()) {
               Timer timer = MC.mc.getTimer();
               RandomNumberGenerator instance8 = DefaultRandomGenerator.instance;
               double minValue8 = settings.timerSpeed.getMinValue();
               double maxValue8 = settings.timerSpeed.getMaxValue();
               timer.timerSpeed = Math.max(0.01F, instance8.nextFloat(minValue8, maxValue8));
            }

            if (f > 0.0F) {
               MoveUtil.strafe(f / 100.0F);
            }
         }
      }
   }

   private void applyLifecyclePhaseRule(String str) {

      try {
         this.applyUpdateRule((MotionModifierRuleSettings)((HashMap)((HashMap)this.rulesByPhase.get(str)).get(str)).get(str));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void onMoveInput(EventMoveInput event) {
      if (this.blockJumpInput.getValue()) {
         event.setJump(false);
      }

      if (!this.damageConditionMet && this.motionModifierRuleSettings.blockMovementInput.getValue() && this.motionModifierRuleSettings.selfDamageMode.getSelectedValue().equals("3Jump")) {
         event.setMoveStrafe(0.0F);
         event.setJump(false);
         event.setMoveForward(0.0F);
      }
   }

   private void applySelfDamage(MotionModifierRuleSettings settings) {
      if (!settings.selfDamageMode.getSelectedValue().equals("None")) {
         switch (settings.selfDamageMode.getSelectedValue()) {
            case "Motion":
               MC.mc.thePlayer.motionY = (double)((float)settings.motion.getValue());
               break;
            case "Packet1":
               PlayerUtil.damageSelfShort();
               break;
            case "Packet2":
               PlayerUtil.damageSelfPacket();
               break;
            case "Fake":
               MC.mc.thePlayer.handleStatusUpdate((byte)2);
               break;
            case "Custom":
               for (int i = 0; (double)i < settings.loops.getValue(); i++) {
                  MC.mc
                     .thePlayer
                     .sendQueue
                     .addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(MC.mc.thePlayer.posX, MC.mc.thePlayer.posY + settings.yAddInLoopFirst.getValue(), MC.mc.thePlayer.posZ, false));
                  if (settings.secondPacket.getValue()) {
                     MC.mc
                        .thePlayer
                        .sendQueue
                        .addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(MC.mc.thePlayer.posX, MC.mc.thePlayer.posY + settings.yAddInLoopSecond.getValue(), MC.mc.thePlayer.posZ, false));
                  }
               }

               if (settings.finishPacket.getValue()) {
                  MC.mc
                     .thePlayer
                     .sendQueue
                     .addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(MC.mc.thePlayer.posX, MC.mc.thePlayer.posY + settings.yAddFinish.getValue(), MC.mc.thePlayer.posZ, true));
               }
               break;
            case "SetPosition":
               MC.mc.thePlayer.setPosition(MC.mc.thePlayer.posX, MC.mc.thePlayer.posY + settings.setPositionYAdd.getValue(), MC.mc.thePlayer.posZ);
         }
      }
   }


}
