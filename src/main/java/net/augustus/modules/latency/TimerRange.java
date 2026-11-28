package net.augustus.modules.latency;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.RangeSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventTimerUpdate;
import net.augustus.events.listeners.TimerUpdateListener;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimerRange extends Module implements PacketDelayListener, MoveInputListener, MotionListener, PacketListener, TimerUpdateListener {
   public long timerBalanceMs;
   public long lastMotionTimeMs;
   public final TimeHelper balanceRecoveryTimer;
   public final TimeHelper activationDelayTimer;
   public final TimeHelper worldChangeTimer;
   private WorldClient lastWorld;
   public final DoubleValue delay;
   public final DoubleValue timerTicksForward;
   public final DoubleValue tolerance;
   public final BooleanValue balanceCheck;
   public final RangeSetting additionalStop;
   public final BooleanValue onlyOnGround;
   public final BooleanValue dynamic;
   public final BooleanValue onlyKillAura;
   public final BooleanValue notInLiquid;
   public final BooleanValue clickCheck;
   public final BooleanValue forbiddenSneak;
   public final StringValue fixMode;
   public int fastAttackScore;
   private long lastPosLookTimeMs;
   private boolean delayingIncoming;
   private int selectedPatternIndex;
   private int patternTickIndex;
   private int additionalStopMs;
   private final ArrayList forwardMovePatterns;

   public TimerRange() {
       super("TimerRange", "ZeitDistanz", new Color(23, 233, 123), Categorys.LATENCY);
      this.timerBalanceMs = 0L;
      this.balanceRecoveryTimer = new TimeHelper();
      this.activationDelayTimer = new TimeHelper();
      this.worldChangeTimer = new TimeHelper();
      this.lastWorld = null;
      this.delay = new DoubleValue(this, "Delay", 0.0, 0.0, 20000.0, 0);
      this.timerTicksForward = new DoubleValue(this, "TimerTicksForward", 5.0, 1.0, 10.0, 0);
      this.tolerance = new DoubleValue(this, "Tolerance", 0.0, -1.0, 1.0, 2);
      this.balanceCheck = new BooleanValue(this, "BalanceCheck", true);
      this.additionalStop = new RangeSetting(this, "AdditionalStop", 1.0, 0.0, 0.0, 300.0, 0);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false);
      this.dynamic = new BooleanValue(this, "Dynamic", true);
      this.onlyKillAura = new BooleanValue(this, "OnlyKillAura", true);
      this.notInLiquid = new BooleanValue(this, "NotInLiquid", false);
      this.clickCheck = new BooleanValue(this, "ClickCheck", false);
      this.forbiddenSneak = new BooleanValue(this, "ForbiddenSneak", true);
      this.fixMode = new StringValue(this, "FixMode", "None", new String[]{"None", "Fix1", "Fix2"});
      this.fastAttackScore = 0;
      this.lastPosLookTimeMs = System.currentTimeMillis();
      this.delayingIncoming = false;
      this.selectedPatternIndex = 0;
      this.patternTickIndex = 0;
      this.additionalStopMs = 0;
      this.forwardMovePatterns = new ArrayList();
      this.forwardMovePatterns.add(new float[]{1.0F, 1.0F});
      this.forwardMovePatterns.add(new float[]{0.0F, 1.0F});
      this.forwardMovePatterns.add(new float[]{0.0F, 0.0F});
      this.forwardMovePatterns.add(new float[]{-1.0F, 0.0F});
      this.forwardMovePatterns.add(new float[]{-1.0F, -1.0F});
      this.forwardMovePatterns.add(new float[]{-1.0F, 1.0F});
   }

   public void onEnable() {
      this.timerBalanceMs = -1L;
      this.lastMotionTimeMs = System.currentTimeMillis();
   }

   public void onDisable() {
      this.lastWorld = null;
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(42);
   }

   public void onPreMotion(EventMotion event) {
      if (this.lastWorld == null || this.lastWorld != mc.theWorld) {
         this.worldChangeTimer.reset();
      }

      if (!this.worldChangeTimer.reached(500L)) {
         this.timerBalanceMs = -1L;
         this.lastMotionTimeMs = System.currentTimeMillis();
      }

      this.balanceRecoveryTimer.reset();
      long l2 = System.currentTimeMillis() - this.lastMotionTimeMs;
      this.timerBalanceMs += (l2 - 50L) * -1L;
      this.lastMotionTimeMs = System.currentTimeMillis();
      this.timerBalanceMs = Math.max(-100L, this.timerBalanceMs);
      this.lastWorld = mc.theWorld;
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (!this.fixMode.getSelectedValue().equals("None") && thePlayer != null && thePlayer.ticksExisted > 10) {
         event.setDelayed(this.delayingIncoming);
      }
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (this.patternTickIndex < 2) {
         event.setMoveForward(((float[])this.forwardMovePatterns.get(this.selectedPatternIndex))[this.patternTickIndex] * event.getMoveForward());
      }

      this.patternTickIndex++;
   }

   public void onTimerUpdate(EventTimerUpdate event) {
      this.delayingIncoming = false;
      if ((!ClientManagers.moduleManager.killAura.isToggled() || !ClientManagers.moduleManager.killAura.rangeMode.getSelectedValue().equals("Grim") || !ClientManagers.moduleManager.killAura.grimRangeActive)
         && !BlockUtil.isScaffoldActiveAndValid()
         && mc.isNotInGui()
         && mc.theWorld != null
         && mc.thePlayer != null
         && (!this.notInLiquid.getValue() || !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava())
         && !ClientManagers.moduleManager.airStuck.isToggled()
         && (!ClientManagers.moduleManager.bugUp.buggingUp || !ClientManagers.moduleManager.bugUp.isToggled())
         && (!this.forbiddenSneak.getValue() || !mc.thePlayer.isSneaking())
         && (!this.clickCheck.getValue() || mc.gameSettings.keyBindAttack.isKeyDown())
         && (!this.onlyOnGround.getValue() || mc.thePlayer.onGround)) {
         if (this.activationDelayTimer.reached((long)this.delay.getValue())
            && (
               ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
                  || ClientManagers.moduleManager.aimBot.isToggled() && ClientManagers.moduleManager.aimBot.target != null
                  || !this.onlyKillAura.getValue()
            )) {
            for (int i = (int)this.timerTicksForward.getValue(); i >= 1; i--) {
               for (Object obj : mc.theWorld.loadedEntityList) {
                  if (obj instanceof EntityLivingBase) {
                     if (this.onlyKillAura.getValue()) {
                        obj = CombatTargetUtil.getActiveTargetOrDefault((EntityLivingBase)obj);
                     }

                     if (obj instanceof EntityOtherPlayerMP) {
                        EntityOtherPlayerMP entityOtherPlayerMP = (EntityOtherPlayerMP)obj;
                        float f = entityOtherPlayerMP.width / 2.0F;
                        float height = entityOtherPlayerMP.height;
                        int n = entityOtherPlayerMP.otherPlayerMPPosRotationIncrements;
                        double d = entityOtherPlayerMP.posX + (entityOtherPlayerMP.otherPlayerMPX - entityOtherPlayerMP.posX) / (double)Math.max(1, n);
                        double d2 = entityOtherPlayerMP.posY + (entityOtherPlayerMP.otherPlayerMPY - entityOtherPlayerMP.posY) / (double)Math.max(1, n);
                        double d3 = entityOtherPlayerMP.posZ + (entityOtherPlayerMP.otherPlayerMPZ - entityOtherPlayerMP.posZ) / (double)Math.max(1, n);
                        n--;

                        for (int j = 0; j < i; j++) {
                           d = entityOtherPlayerMP.posX + (entityOtherPlayerMP.otherPlayerMPX - entityOtherPlayerMP.posX) / (double)Math.max(1, n);
                           d2 = entityOtherPlayerMP.posY + (entityOtherPlayerMP.otherPlayerMPY - entityOtherPlayerMP.posY) / (double)Math.max(1, n);
                           d3 = entityOtherPlayerMP.posZ + (entityOtherPlayerMP.otherPlayerMPZ - entityOtherPlayerMP.posZ) / (double)Math.max(1, n);
                           n--;
                        }

                        float collisionBorderSize = ((Entity)obj).getCollisionBorderSize();
                        AxisAlignedBB box = new AxisAlignedBB(
                           d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)height, d3 + (double)f
                        );
                        box = box.expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                        float moveStrafe = mc.thePlayer.movementInput.moveStrafe;
                        float[] floats = new float[i];
                        float[] floats2 = new float[i];

                        for (int k = 0; k < i; k++) {
                           floats[k] = moveStrafe;
                        }

                        int n2 = -1337;
                        double d4 = 0.0;
                        double d5 = 0.0;
                        double d6 = 0.0;

                        for (int m = 0; m < this.forwardMovePatterns.size(); m++) {
                           for (int idx = 0; idx < i; idx++) {
                              if (idx >= ((float[])this.forwardMovePatterns.get(m)).length) {
                                 floats2[idx] = mc.thePlayer.movementInput.moveForward;
                              } else {
                                 floats2[idx] = mc.thePlayer.movementInput.moveForward * ((float[])this.forwardMovePatterns.get(m))[idx];
                              }
                           }

                           double[] doubles = PlayerUtil.predictPlayerWithInputArrays(Math.max(0, i - 1), mc.thePlayer, floats2, floats);
                           double d7 = mc.thePlayer.getServerPos().xCoord + (doubles[0] - mc.thePlayer.getServerPos().xCoord) / 3.0;
                           double d8 = mc.thePlayer.getServerPos().yCoord + (doubles[1] - mc.thePlayer.getServerPos().yCoord) / 3.0;
                           double d9 = mc.thePlayer.getServerPos().zCoord + (doubles[2] - mc.thePlayer.getServerPos().zCoord) / 3.0;
                           Vec3 vec = new Vec3(doubles[3], doubles[4] + (double)mc.thePlayer.getEyeHeight(), doubles[5]);
                           Vec3 vec2 = new Vec3(
                              MathHelper.clamp_double(vec.xCoord, box.minX, box.maxX),
                              MathHelper.clamp_double(vec.yCoord, box.minY, box.maxY),
                              MathHelper.clamp_double(vec.zCoord, box.minZ, box.maxZ)
                           );
                           AxisAlignedBB box2 = entityOtherPlayerMP.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                           Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
                           Vec3 vec3 = new Vec3(
                              MathHelper.clamp_double(positionEyes.xCoord, box2.minX, box2.maxX),
                              MathHelper.clamp_double(positionEyes.yCoord, box2.minY, box2.maxY),
                              MathHelper.clamp_double(positionEyes.zCoord, box2.minZ, box2.maxZ)
                           );
                           Vec3 vec4 = new Vec3(entityOtherPlayerMP.otherPlayerMPX, entityOtherPlayerMP.otherPlayerMPY + (double)entityOtherPlayerMP.getEyeHeight(), entityOtherPlayerMP.otherPlayerMPZ);
                           double d10 = ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
                              ? ClientManagers.moduleManager.killAura.currentRange
                              : (ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
                           AxisAlignedBB box3 = new AxisAlignedBB(
                                 d7 - (double)f, d8, d9 - (double)f, d7 + (double)f, d8 + (double)height, d9 + (double)f
                              )
                              .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                           Vec3 vec5 = new Vec3(
                              MathHelper.clamp_double(vec4.xCoord, box3.minX, box3.maxX),
                              MathHelper.clamp_double(vec4.yCoord, box3.minY, box3.maxY),
                              MathHelper.clamp_double(vec4.zCoord, box3.minZ, box3.maxZ)
                           );
                           boolean flag = false;
                           if (vec.distanceTo(vec2) < d10 && vec4.distanceTo(vec5) > 3.0 - this.tolerance.getValue() && positionEyes.distanceTo(vec3) > 3.0) {
                              if (m == 0) {
                                 flag = true;
                              }

                              if (event.getElapsedTicks() == 1) {
                                 double distance = vec4.distanceTo(vec5);
                                 if (distance > d4) {
                                    d4 = distance;
                                    n2 = m;
                                    d5 = vec4.distanceTo(vec5);
                                    d6 = vec.distanceTo(vec2);
                                 }
                              }
                           }

                           if (!flag || i <= 2) {
                              break;
                           }
                        }

                        if (n2 >= 0) {
                           this.selectedPatternIndex = n2;
                           event.setElapsedPartialTicks((float)i);
                           this.additionalStopMs = DefaultRandomGenerator.instance.nextInt(this.additionalStop.getMinValue(), this.additionalStop.getMaxValue());
                           if (ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.timerRangeDebug.getValue()) {
                              String str = "Timered:  " + d5 + "  " + d6;
                              ClientManagers.moduleManager.debug.outputDebugMessage(str);
                           }

                           this.delayingIncoming = this.fixMode.getSelectedValue().equals("Fix2");
                           this.balanceRecoveryTimer.reset();
                           this.activationDelayTimer.reset();
                           this.patternTickIndex = 0;
                        }

                        if (!this.dynamic.getValue()
                           || this.onlyKillAura.getValue() && (obj == ClientManagers.moduleManager.killAura.target || obj == ClientManagers.moduleManager.aimBot.target)) {
                           break;
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.balanceCheck.getValue() && this.timerBalanceMs > 0L) {
         long l = this.timerBalanceMs + (long)this.additionalStopMs;
         if (!this.balanceRecoveryTimer.reached(l)) {
            event.setElapsedPartialTicks(0.0F);
            this.delayingIncoming = true;
         }
      }
   }

   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
         this.lastPosLookTimeMs = System.currentTimeMillis();
      }

      if (packet instanceof C02PacketUseEntity) {
         long l = System.currentTimeMillis() - this.lastPosLookTimeMs;
         if (l < 23L && l > 0L) {
            this.fastAttackScore++;
            if (this.fastAttackScore > 10) {
               this.fastAttackScore = 10;
            }
         } else if (this.fastAttackScore > 0) {
            this.fastAttackScore--;
         }
      }
   }




}
