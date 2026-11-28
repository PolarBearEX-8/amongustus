package net.augustus.modules.latency;

import java.awt.Color;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventGameLoop;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.ClientManagers;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimerRangeV2 extends Module implements GameLoopListener, PacketDelayListener, LoadWorldListener {
   private final BooleanValue dynamicLagTicks;
   private final DoubleValue lagTickDiff;
   private final DoubleValue lagTicks;
   private final DoubleValue timerTicks;
   private final DoubleValue maxTargetHurttime;
   public final DoubleValue delay;
   private final BooleanValue correct;
   private final BooleanValue dynamic;
   private final BooleanValue accurate;
   private final BooleanValue debug;
   private final BooleanValue onlyOnGround;
   private final BooleanValue notInLiquid;
   private final StringValue fixMode;
   private final BooleanValue extreme;
   private final SettingGroup targets;
   private final BooleanValue onlyWhileKillAura;
   private final TargetSelectionSettings targetSelection;
   private final DoubleValue preAimRange;
   public final TimeHelper lastPhaseChangeTimer;
   private final TimeHelper cooldownTimer;
   private boolean delayingIncoming;
   private boolean canTimerTarget;
   private boolean preStopCompleted;
   public int stage;
   private int plannedTimerTicks;
   private int unusedCounter;
   private int configuredLagTicks;
   private int initialPlannedTicks;
   private int elapsedStageTicks;
   private AxisAlignedBB predictedTargetBox;
   private boolean extremeDelayActive;

   public TimerRangeV2() {
       super("TimerRangeV2", "ZeitDistanzV2", new Color(23, 233, 123), Categorys.LATENCY);
      this.dynamicLagTicks = new BooleanValue(this, "DynamicLagTicks", true);
      DoubleValue lagTickDiffSetting = new DoubleValue(this, "LagTickDiff", 3.0, 0.0, 10.0, 0);
      BooleanValue dynamicLagTicks2 = this.dynamicLagTicks;
      this.lagTickDiff = lagTickDiffSetting.visibleWhen(() -> dynamicLagTicks2.getValue());
      DoubleValue lagTicksSetting = new DoubleValue(this, "LagTicks", 1.0, 0.0, 10.0, 0);
      SettingVisibilityCondition condition2 = () -> {
         return !this.dynamicLagTicks.getValue();
      };
      this.lagTicks = lagTicksSetting.visibleWhen(condition2);
      this.timerTicks = new DoubleValue(this, "TimerTicks", 5.0, 0.0, 10.0, 0);
      this.maxTargetHurttime = new DoubleValue(this, "MaxTargetHurttime", 3.0, 0.0, 10.0, 0);
      this.delay = new DoubleValue(this, "Delay", 500.0, 0.0, 4000.0, 0);
      this.correct = new BooleanValue(this, "Correct", false);
      this.dynamic = new BooleanValue(this, "Dynamic", true);
      this.accurate = new BooleanValue(this, "Accurate", false);
      this.debug = new BooleanValue(this, "Debug", false);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false);
      this.notInLiquid = new BooleanValue(this, "NotInLiquid", false);
      this.fixMode = new StringValue(this, "FixMode", "None", new String[]{"None", "Fix1", "Fix2"});
      this.extreme = new BooleanValue(this, "Extreme", false);
      this.targets = new SettingGroup(this, "Targets");
      this.onlyWhileKillAura = new BooleanValue(this, "OnlyWhileKillAura", true, this.targets);
      TargetSelectionSettings targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter(), this.targets)).withSorting();
      SettingVisibilityCondition condition3 = () -> {
         return !this.onlyWhileKillAura.getValue();
      };
      this.targetSelection = targetSelectionSettings.withVisibility(condition3);
      DoubleValue preAimRangeSetting = new DoubleValue(this, "PreAimRange", 4.0, 0.0, 15.0, 1, this.targets);
      SettingVisibilityCondition condition4 = () -> {
         return !this.onlyWhileKillAura.getValue();
      };
      this.preAimRange = preAimRangeSetting.visibleWhen(condition4);
      this.lastPhaseChangeTimer = new TimeHelper();
      this.cooldownTimer = new TimeHelper();
      this.delayingIncoming = false;
      this.canTimerTarget = false;
      this.preStopCompleted = false;
      this.stage = 0;
      this.plannedTimerTicks = 0;
      this.unusedCounter = 0;
      this.configuredLagTicks = 0;
      this.initialPlannedTicks = 0;
      this.elapsedStageTicks = 0;
      this.predictedTargetBox = null;
      this.extremeDelayActive = false;
   }

   @Override
   public void onEnable() {
      this.canTimerTarget = false;
      this.delayingIncoming = false;
      this.preStopCompleted = false;
      this.elapsedStageTicks = 0;
      this.resetStageAndCooldown();
      this.plannedTimerTicks = 0;
      this.configuredLagTicks = 0;
      this.initialPlannedTicks = 0;
   }

   @Override
   public void onDisable() {
      this.targetSelection.setCurrentTarget((Entity)null);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(16);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (!this.fixMode.getSelectedValue().equals("None") && thePlayer != null && thePlayer.ticksExisted > 10) {
         event.setDelayed(this.delayingIncoming);
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      if (!this.extreme.getValue() || this.stage != 3) {
         this.extremeDelayActive = false;
      } else if (packet instanceof C02PacketUseEntity && !this.extremeDelayActive) {
         this.extremeDelayActive = true;
      } else if (this.extremeDelayActive) {
         event.setDelayed(true);
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.targetSelection.setCurrentTarget((Entity)null);
   }

   @Override
   public void onGameLoop(EventGameLoop event) {
      EntityLivingBase entity = this.findTarget();
      event.setAllowSkipRender(this.correct.getValue());
      int value2 = (int)this.timerTicks.getValue();
      this.preStopCompleted = this.lagTicks.getValue() <= 0.0 && !this.dynamicLagTicks.getValue() || this.preStopCompleted;
      if (!this.dynamicLagTicks.getValue()) {
         this.configuredLagTicks = (int)this.lagTicks.getValue();
      }

      if (event.getElapsedTicks() >= 1) {
         if (!this.preStopCompleted) {
            if (this.stage == 0) {
               if (!this.cooldownTimer.reached(this.delay.getValue())) {
                  return;
               }

               int n = (int)(this.dynamicLagTicks.getValue() ? (double)value2 - this.lagTickDiff.getValue() : this.lagTicks.getValue());
               if (entity == null) {
                  this.canTimerTarget = false;
               } else {
                  for (int i = value2; i > Math.max(1, n); i--) {
                     if (this.simulateTimerAdvantage(entity, i, Math.max(0, n))) {
                        this.canTimerTarget = true;
                        this.plannedTimerTicks = i;
                        this.initialPlannedTicks = i;
                        break;
                     }

                     this.canTimerTarget = false;
                     if (!this.dynamic.getValue()) {
                        this.canTimerTarget = false;
                        break;
                     }
                  }
               }

               if (this.canTimerTarget) {
                  this.stage = n > 0 ? 1 : 2;
                  this.elapsedStageTicks = 0;
                  if (this.debug.getValue()) {
                     short s = 13673;
                     this.sendChatMessage("PreStop");
                  }
               }
            }
         } else {
            if (!this.cooldownTimer.reached(this.delay.getValue())) {
               return;
            }

            int n3 = (int)(this.dynamicLagTicks.getValue() ? (double)this.plannedTimerTicks - this.lagTickDiff.getValue() : this.lagTicks.getValue());
            if (this.elapsedStageTicks >= n3 && this.stage != 3) {
               if (entity == null) {
                  this.canTimerTarget = false;
               } else {
                  int n4 = this.dynamicLagTicks.getValue() ? (int)Math.max((double)this.plannedTimerTicks, this.lagTickDiff.getValue()) : value2;

                  for (int j = n4; j > Math.max(1, n3); j--) {
                     if (this.simulateTimerAdvantage(entity, j, (byte)0)) {
                        this.canTimerTarget = true;
                        this.plannedTimerTicks = j;
                        break;
                     }

                     this.canTimerTarget = false;
                     if (!this.dynamic.getValue()) {
                        this.canTimerTarget = false;
                        break;
                     }
                  }
               }

               this.stage = 2;
            }
         }
      }

      if (this.stage == 1) {
         if (this.canTimerTarget) {
            this.predictedTargetBox = null;
            event.setElapsedTicks((byte)0);
            event.setRenderPartialTicks(0.0F);
            event.setSkipRender(true);
            this.preStopCompleted = true;
            this.delayingIncoming = true;
            this.lastPhaseChangeTimer.reset();
         }
      } else if (this.stage == 2) {
         if (this.canTimerTarget) {
            event.setElapsedTicks(this.plannedTimerTicks);
            this.stage = 3;
            this.elapsedStageTicks = 0;
            this.initialPlannedTicks = 0;
            if (this.debug.getValue()) {
               this.sendChatMessage("TimerSuccessful " + this.plannedTimerTicks);
            }

            this.lastPhaseChangeTimer.reset();
            if (this.fixMode.getSelectedValue().equals("Fix2")) {
               this.delayingIncoming = true;
            }
         } else if (this.dynamicLagTicks.getValue()) {
            if (((int)((double)this.plannedTimerTicks - this.lagTickDiff.getValue())) > 0) {
               event.setElapsedTicks((int)this.lagTicks.getValue());
               this.resetStageAndCooldown();
               this.preStopCompleted = false;
               this.initialPlannedTicks = 0;
               if (this.debug.getValue()) {
                  this.sendChatMessage("TimerAbort");
               }

               this.lastPhaseChangeTimer.reset();
               this.delayingIncoming = false;
            }
         } else if (this.lagTicks.getValue() > 0.0) {
            event.setElapsedTicks((int)this.lagTicks.getValue());
            this.resetStageAndCooldown();
            this.preStopCompleted = false;
            this.initialPlannedTicks = 0;
            if (this.debug.getValue()) {
               this.sendChatMessage("TimerAbort");
            }

            this.lastPhaseChangeTimer.reset();
            this.delayingIncoming = false;
         }
      } else if (this.stage == 3) {
         int n6 = this.dynamicLagTicks.getValue()
            ? (int)((double)this.plannedTimerTicks - Math.max(0.0, (double)this.plannedTimerTicks - this.lagTickDiff.getValue()))
            : (int)((double)this.plannedTimerTicks - this.lagTicks.getValue());
         if (this.elapsedStageTicks <= n6) {
            this.predictedTargetBox = null;
            event.setElapsedTicks((byte)0);
            event.setRenderPartialTicks(0.0F);
            event.setSkipRender(true);
            this.preStopCompleted = false;
            this.lastPhaseChangeTimer.reset();
            this.delayingIncoming = true;
         } else {
            this.resetStageAndCooldown();
            if (this.debug.getValue()) {
               this.sendChatMessage("LastTimerStop");
            }

            this.delayingIncoming = false;
         }
      }

      this.elapsedStageTicks = this.elapsedStageTicks + mc.getTimer().elapsedTicks;
      this.targetSelection.setCurrentTarget(entity);
   }

   private EntityLivingBase findTarget() {
      EntityLivingBase activeTarget = CombatTargetUtil.getActiveTarget();
      if (!this.onlyWhileKillAura.getValue() && activeTarget == null) {
         EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
         entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter()).filterFocus();
         entityTargetSelector = entityTargetSelector.maxDistance(this.preAimRange.getValue());
         ToDoubleFunction toDoubleFunction = this.targetSelection.createTargetComparatorKey(ClientManagers.rotationController.getPreviousRotation(), ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
         Optional optional = entityTargetSelector.sortBy(toDoubleFunction).findBest();
         return optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
      } else {
         return activeTarget;
      }
   }

   private boolean simulateTimerAdvantage(EntityLivingBase entity, int n, int n2) {
      if ((!this.onlyOnGround.getValue() || mc.thePlayer.onGround)
         && (!this.notInLiquid.getValue() || !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava())
         && (!ClientManagers.moduleManager.killAura.isToggled() || !ClientManagers.moduleManager.killAura.rangeMode.getSelectedValue().equals("Grim") || !ClientManagers.moduleManager.killAura.grimRangeActive)
         && entity.ticksExisted >= 20) {
         Vec3 vec = new Vec3(entity.posX, entity.posY, entity.posZ);
         Vec3 vec2;
         if (n2 <= 0) {
            vec2 = new Vec3(entity.realPosX, entity.realPosY, entity.realPosZ);
         } else {
            Vec3 vec3 = EntityMovementPredictor.predictConstantHorizontalPosition(entity, n2);
            vec2 = new Vec3(vec3);
         }

         double xCoord = vec.xCoord;
         double yCoord = vec.yCoord;
         double zCoord = vec.zCoord;
         int realPosRotationIncrements = entity.realPosRotationIncrements;

         for (int i = 0; i < n - 1; i++) {
            xCoord += (vec2.xCoord - xCoord) / (double)Math.max(1, realPosRotationIncrements);
            yCoord += (vec2.yCoord - yCoord) / (double)Math.max(1, realPosRotationIncrements);
            zCoord += (vec2.zCoord - zCoord) / (double)Math.max(1, realPosRotationIncrements);
            realPosRotationIncrements--;
         }

         float f = entity.width / 2.0F;
         float collisionBorderSize = entity.getCollisionBorderSize();
         AxisAlignedBB box = new AxisAlignedBB(
               xCoord - (double)f, yCoord, zCoord - (double)f, xCoord + (double)f, yCoord + (double)entity.height, zCoord + (double)f
            )
            .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
         Vec3 serverPos = mc.thePlayer.getServerPos();
         Vec3 prevServerPos = mc.thePlayer.getPrevServerPos();
         Vec3 vec4 = new Vec3(
            prevServerPos.xCoord + (serverPos.xCoord - prevServerPos.xCoord) / 2.0,
            prevServerPos.yCoord + (serverPos.yCoord - prevServerPos.yCoord) / 2.0,
            prevServerPos.zCoord + (serverPos.zCoord - prevServerPos.zCoord) / 2.0
         );
         if (this.initialPlannedTicks > 0 || n2 > 0) {
            vec4 = new Vec3(serverPos.xCoord, serverPos.yCoord, serverPos.zCoord);
         }

         double[] doubles = PlayerUtil.predictPlayerTowardsTarget(n - 1, mc.thePlayer, entity);
         Vec3 vec5 = new Vec3(doubles[0], doubles[1], doubles[2]);
         double xCoord2 = vec4.xCoord;
         double yCoord2 = vec4.yCoord;
         double zCoord2 = vec4.zCoord;
         int n4 = 3;

         do {
            xCoord2 += (vec5.xCoord - xCoord2) / (double)n4;
            yCoord2 += (vec5.yCoord - yCoord2) / (double)n4;
            zCoord2 += (vec5.zCoord - zCoord2) / (double)n4;
         } while (--n4 >= 2);

         Vec3 vec6 = new Vec3(xCoord2, yCoord2, zCoord2);
         Vec3 vec7 = new Vec3(
            vec4.xCoord + (vec5.xCoord - vec4.xCoord) / 3.0,
            vec4.yCoord + (vec5.yCoord - vec4.yCoord) / 3.0,
            vec4.zCoord + (vec5.zCoord - vec4.zCoord) / 3.0
         );
         float f2 = mc.thePlayer.width / 2.0F;
         float collisionBorderSize2 = mc.thePlayer.getCollisionBorderSize();
         AxisAlignedBB box2 = new AxisAlignedBB(
               vec6.xCoord - (double)f2,
               vec6.yCoord,
               vec6.zCoord - (double)f2,
               vec6.xCoord + (double)f2,
               vec6.yCoord + (double)mc.thePlayer.height,
               vec6.zCoord + (double)f2
            )
            .expand((double)collisionBorderSize2, (double)collisionBorderSize2, (double)collisionBorderSize2);
         AxisAlignedBB box3 = new AxisAlignedBB(
               vec7.xCoord - (double)f2,
               vec7.yCoord,
               vec7.zCoord - (double)f2,
               vec7.xCoord + (double)f2,
               vec7.yCoord + (double)mc.thePlayer.height,
               vec7.zCoord + (double)f2
            )
            .expand((double)collisionBorderSize2, (double)collisionBorderSize2, (double)collisionBorderSize2);
         Vec3 vec8 = EntityMovementPredictor.predictConstantHorizontalPosition(entity, n2 + 1);
         Vec3 vec9 = EntityMovementPredictor.predictConstantHorizontalPosition(entity, n2);
         boolean flag = EntityMovementPredictor.predictFullState(entity, n2 + 1)[9] == 1.0;
         Vec3 vec10 = new Vec3(vec8);
         Vec3 vec11 = new Vec3(vec9);
         float f3 = 0.91F;
         if (flag) {
            f3 = entity.entityWorld
                  .getBlockState(
                     new BlockPos(MathHelper.floor_double(vec10.xCoord), MathHelper.floor_double(vec10.yCoord) - 1, MathHelper.floor_double(vec10.zCoord))
                  )
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d = vec10.xCoord - vec11.xCoord;
         double d2 = vec10.yCoord - vec11.yCoord;
         double d3 = vec10.zCoord - vec11.zCoord;
         if (mc.theWorld.isRemote
            && (
               !mc.theWorld.isBlockLoaded(new BlockPos((int)vec10.xCoord, 0, (int)vec10.zCoord))
                  || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)vec10.xCoord, 0, (int)vec10.zCoord)).isLoaded()
            )) {
            if (vec10.yCoord > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.98F;
         d *= (double)f3;
         d3 *= (double)f3;
         double d4 = mc.thePlayer.posX - vec11.xCoord;
         double d5 = mc.thePlayer.posZ - vec11.zCoord;
         float f4 = MathHelper.sqrt_double(d4 * d4 + d5 * d5);
         float f5 = 0.4F;
         d /= 2.0;
         d2 /= 2.0;
         d3 /= 2.0;
         d -= d4 / (double)f4 * (double)f5;
         d2 += (double)f5;
         d3 -= d5 / (double)f4 * (double)f5;
         if (d2 > 0.4F) {
            d2 = 0.4F;
         }

         Vec3 vec12 = new Vec3(d, d2, d3);
         Vec3 vec13 = new Vec3(doubles[3], doubles[4] + (double)mc.thePlayer.getEyeHeight(), doubles[5]);
         double d6 = MathHelper.clamp_double(vec13.xCoord, box.minX, box.maxX);
         double d7 = MathHelper.clamp_double(vec13.zCoord, box.minZ, box.maxZ);
         double d8 = d6 - doubles[3];
         float f6 = (float)(MathHelper.atan2(d7 - doubles[5], d8) * 180.0 / Math.PI - 90.0);
         int n8 = 1 + EnchantmentHelper.getKnockbackModifier(mc.thePlayer);
         vec12 = vec12.addVector(
            (double)(-MathHelper.sin(f6 * (float) Math.PI / 180.0F) * (float)n8 * 0.5F),
            0.1,
            (double)(MathHelper.cos(f6 * (float) Math.PI / 180.0F) * (float)n8 * 0.5F)
         );
         f3 = 0.91F;
         if (flag) {
            f3 = entity.entityWorld
                  .getBlockState(
                     new BlockPos(MathHelper.floor_double(vec10.xCoord), MathHelper.floor_double(vec10.yCoord) - 1, MathHelper.floor_double(vec10.zCoord))
                  )
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f7 = 0.16277136F / (f3 * f3 * f3);
         float f8;
         if (flag) {
            f8 = entity.getAIMoveSpeed() * f7;
         } else {
            f8 = entity.jumpMovementFactor;
         }

         float f9 = 0.0F;
         float f10 = 0.98F;
         float f11 = f9 * f9 + f10 * f10;
         if (f11 >= 1.0E-4F) {
            f11 = MathHelper.sqrt_float(f11);
            if (f11 < 1.0F) {
               f11 = 1.0F;
            }

            f11 = f8 / f11;
            f9 *= f11;
            f10 *= f11;
            Vec3 vec14 = new Vec3(vec10.xCoord, vec10.yCoord + (double)entity.getEyeHeight(), vec10.zCoord);
            new Vec3(
               MathHelper.clamp_double(vec14.xCoord, box2.minX, box2.maxX),
               MathHelper.clamp_double(vec14.yCoord, box2.minY, box2.maxY),
               MathHelper.clamp_double(vec14.zCoord, box2.minZ, box2.maxZ)
            );
            double d10 = vec12.xCoord * -1.0;
            float f12 = (float)(MathHelper.atan2(vec12.zCoord * -1.0, d10) * 180.0 / Math.PI - 90.0);
            float f13 = MathHelper.sin(f12 * (float) Math.PI / 180.0F);
            float f14 = MathHelper.cos(f12 * (float) Math.PI / 180.0F);
            vec12 = vec12.addVector((double)(f9 * f14 - f10 * f13), 0.0, (double)(f10 * f14 + f9 * f13));
         }

         Vec3 vec15 = vec10.add(vec12);
         Vec3 vec16 = new Vec3(vec15.xCoord, vec15.yCoord + (double)entity.getEyeHeight(), vec15.zCoord);
         Vec3 vec17 = new Vec3(
            MathHelper.clamp_double(vec16.xCoord, box2.minX, box2.maxX),
            MathHelper.clamp_double(vec16.yCoord, box2.minY, box2.maxY),
            MathHelper.clamp_double(vec16.zCoord, box2.minZ, box2.maxZ)
         );
         Vec3 vec18 = new Vec3(vec10.xCoord, vec10.yCoord + (double)entity.getEyeHeight(), vec10.zCoord);
         Vec3 vec19 = new Vec3(
            MathHelper.clamp_double(vec18.xCoord, box3.minX, box3.maxX),
            MathHelper.clamp_double(vec18.yCoord, box3.minY, box3.maxY),
            MathHelper.clamp_double(vec18.zCoord, box3.minZ, box3.maxZ)
         );
         Vec3 vec20 = new Vec3(doubles[3], doubles[4], doubles[5]);
         Vec3 vec21 = new Vec3(vec20.xCoord, vec20.yCoord + (double)mc.thePlayer.getEyeHeight(), vec20.zCoord);
         Vec3 vec22 = new Vec3(
            MathHelper.clamp_double(vec21.xCoord, box.minX, box.maxX),
            MathHelper.clamp_double(vec21.yCoord, box.minY, box.maxY),
            MathHelper.clamp_double(vec21.zCoord, box.minZ, box.maxZ)
         );
         double d12 = ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
            ? ClientManagers.moduleManager.killAura.currentRange
            : (ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
         if (this.debug.getValue()
            && (double)entity.hurtTime <= this.maxTargetHurttime.getValue()
            && (vec18.distanceTo(vec19) > 3.0 || !this.accurate.getValue())
            && vec16.distanceTo(vec17) > 3.0
            && vec21.distanceTo(vec22) < d12
            && mc.thePlayer.getDistanceToEntityBox(entity) > 3.0F) {
            String str = NumberFormatUtil.formatDecimal(vec18.distanceTo(vec19), 4)
               + "  "
               + NumberFormatUtil.formatDecimal(vec16.distanceTo(vec17), 4)
               + "  "
               + NumberFormatUtil.formatDecimal(vec21.distanceTo(vec22), 4);
            this.sendChatMessage(str);
         }

         boolean flag2 = (double)entity.hurtTime <= this.maxTargetHurttime.getValue()
            && (vec18.distanceTo(vec19) > 3.0 || !this.accurate.getValue())
            && vec16.distanceTo(vec17) > 3.0
            && vec21.distanceTo(vec22) < d12
            && mc.thePlayer.getDistanceToEntityBox(entity) > 3.0F;
         this.predictedTargetBox = flag2 ? box : null;
         return flag2;
      } else {
         return false;
      }
   }

   private void resetStageAndCooldown() {
      this.stage = 0;
      this.cooldownTimer.reset();
   }

   public boolean hasPredictedTargetBox() {
      return this.isToggled() && this.predictedTargetBox != null;
   }

   public AxisAlignedBB getPredictedTargetBox() {
      return this.predictedTargetBox;
   }

}
