package net.augustus.modules.latency;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventGameLoop;
import net.augustus.modules.latency.helper.TimerRangeV3State;
import net.augustus.ClientManagers;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimerRangeV3 extends Module implements PacketDelayListener, GameLoopListener {
   private final DoubleValue blinkTicks;
   private final DoubleValue timerTicks;
   private final DoubleValue maxTargetHurttime;
   private final BooleanValue correct;
   private final BooleanValue dynamic;
   private final BooleanValue debug;
   private TimerRangeV3State timerRangeV3State;
   private int gameLagElapsedTicks;
   private int plannedTimerTicks;
   private int plannedConnectionLagTicks;
   private int connectionLagElapsedTicks;

   public TimerRangeV3() {
       super("TimerRangeV3", "ZeitDistanzV3", new Color(23, 233, 123), Categorys.LATENCY);
      this.blinkTicks = new DoubleValue(this, "BlinkTicks", 2.0, 0.0, 10.0, 0);
      this.timerTicks = new DoubleValue(this, "TimerTicks", 5.0, 0.0, 10.0, 0);
      this.maxTargetHurttime = new DoubleValue(this, "MaxTargetHurttime", 3.0, 0.0, 10.0, 0);
      this.correct = new BooleanValue(this, "Correct", false);
      this.dynamic = new BooleanValue(this, "Dynamic", true);
      this.debug = new BooleanValue(this, "Debug", false);
      this.gameLagElapsedTicks = 0;
      this.plannedTimerTicks = 0;
      this.plannedConnectionLagTicks = 0;
      this.connectionLagElapsedTicks = 0;
   }

   public void onEnable() {
      this.timerRangeV3State = TimerRangeV3State.NOTHING;
      this.gameLagElapsedTicks = 0;
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(16);
      this.toggleEventSubscription(25);
   }

   public void onGameLoop(EventGameLoop event) {
      EntityLivingBase activeTarget = CombatTargetUtil.getActiveTarget();
      event.setAllowSkipRender(this.correct.getValue());
      if (this.timerRangeV3State == TimerRangeV3State.NOTHING && activeTarget != null && event.getElapsedTicks() >= 1) {
         boolean flag = false;
         if (!(this.blinkTicks.getValue() > 0.0)) {
            for (int i = (int)this.timerTicks.getValue(); i > 0; i--) {
               if (this.simulateTimerAdvantage(activeTarget, i, (byte)0, (byte)0)) {
                  this.timerRangeV3State = TimerRangeV3State.TIMER;
                  this.plannedTimerTicks = i;
                  flag = true;
                  break;
               }

               if (!this.dynamic.getValue()) {
                  break;
               }
            }
         } else {
            this.connectionLagElapsedTicks = 0;

            for (int j = (int)((double)((int)this.timerTicks.getValue()) + this.blinkTicks.getValue()); j > 0; j--) {
               if (this.simulateTimerAdvantage(activeTarget, (int)Math.min((double)j, this.timerTicks.getValue()), (int)Math.max((double)j - this.timerTicks.getValue(), 0.0), (byte)0)) {
                  this.timerRangeV3State = TimerRangeV3State.CONNECTION_LAG;
                  this.plannedTimerTicks = (int)Math.min((double)j, this.timerTicks.getValue());
                  this.plannedConnectionLagTicks = (int)Math.max((double)j - this.timerTicks.getValue(), 0.0);
                  flag = true;
                  break;
               }

               if (!this.dynamic.getValue()) {
                  break;
               }
            }
         }

         if (!flag) {
            this.timerRangeV3State = TimerRangeV3State.NOTHING;
         }
      }

      if (this.timerRangeV3State == TimerRangeV3State.CONNECTION_LAG) {
         if (activeTarget != null) {
            if (this.connectionLagElapsedTicks >= this.plannedConnectionLagTicks && event.getElapsedTicks() >= 1) {
               this.connectionLagElapsedTicks = 0;
               boolean flag2 = false;

               for (int k = (int)this.timerTicks.getValue(); k > 0; k--) {
                  if (this.simulateTimerAdvantage(activeTarget, k, (byte)0, (byte)0)) {
                     this.timerRangeV3State = TimerRangeV3State.TIMER;
                     this.plannedTimerTicks = k;
                     flag2 = true;
                     break;
                  }

                  if (!this.dynamic.getValue()) {
                     break;
                  }
               }

               if (!flag2) {
                  this.timerRangeV3State = TimerRangeV3State.NOTHING;
                  if (this.debug.getValue()) {
                     this.sendChatMessage("Abort Timer After Connection Lag");
                  }
               }
            } else {
               this.sendChatMessage("LAAG..");
            }

            this.connectionLagElapsedTicks = this.connectionLagElapsedTicks + mc.getTimer().elapsedTicks;
         } else {
            this.timerRangeV3State = TimerRangeV3State.NOTHING;
            this.connectionLagElapsedTicks = 0;
         }
      }

      if (this.timerRangeV3State == TimerRangeV3State.TIMER) {
         if (activeTarget != null) {
            event.setElapsedTicks(this.plannedTimerTicks);
            this.timerRangeV3State = TimerRangeV3State.GAME_LAG;
            this.gameLagElapsedTicks = 0;
            if (this.debug.getValue()) {
               this.sendChatMessage("TimerSuccessful " + this.plannedTimerTicks);
            }
         } else {
            this.timerRangeV3State = TimerRangeV3State.NOTHING;
         }
      } else if (this.timerRangeV3State == TimerRangeV3State.GAME_LAG) {
         if (this.gameLagElapsedTicks <= this.plannedTimerTicks) {
            event.setElapsedTicks((byte)0);
            event.setRenderPartialTicks(0.0F);
            event.setSkipRender(true);
         } else {
            this.timerRangeV3State = TimerRangeV3State.NOTHING;
            if (this.debug.getValue()) {
               this.sendChatMessage("LastTimerStop");
            }
         }
      }

      this.gameLagElapsedTicks = this.gameLagElapsedTicks + mc.getTimer().elapsedTicks;
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.timerRangeV3State == TimerRangeV3State.CONNECTION_LAG) {
         if (event.getPacket() instanceof C03PacketPlayer) {
            this.sendChatMessage("C03");
         }

         event.setDelayed(true);
      } else {
         this.connectionLagElapsedTicks = 0;
      }
   }

   private boolean simulateTimerAdvantage(EntityLivingBase entity, int n, int n2, int n3) {
      Vec3 vec;
      Vec3 vec2;
      if (n2 <= 0) {
         vec2 = new Vec3(entity.realPosX, entity.realPosY, entity.realPosZ);
         vec = new Vec3(entity.posX, entity.posY, entity.posZ);
      } else {
         double[] doubles = PlayerUtil.predictPlayerPosition(n2 - 1, mc.thePlayer);
         vec = new Vec3(doubles[0], doubles[1], doubles[2]);
         double[] doubles2 = PlayerUtil.predictTargetPosition(entity, n2 - 1);
         vec2 = new Vec3(doubles2[0], doubles2[1], doubles2[2]);
      }

      double xCoord = vec.xCoord;
      double yCoord = vec.yCoord;
      double zCoord = vec.zCoord;
      int realPosRotationIncrements = entity.realPosRotationIncrements;

      for (int i = 0; i < n - 1; i++) {
         xCoord = vec.xCoord + (vec2.xCoord - vec.xCoord) / (double)Math.max(1, realPosRotationIncrements);
         yCoord = vec.yCoord + (vec2.yCoord - vec.yCoord) / (double)Math.max(1, realPosRotationIncrements);
         zCoord = vec.zCoord + (vec2.zCoord - vec.zCoord) / (double)Math.max(1, realPosRotationIncrements);
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
      Vec3 vec3 = new Vec3(
         prevServerPos.xCoord + (serverPos.xCoord - prevServerPos.xCoord) / 2.0,
         prevServerPos.yCoord + (serverPos.yCoord - prevServerPos.yCoord) / 2.0,
         prevServerPos.zCoord + (serverPos.zCoord - prevServerPos.zCoord) / 2.0
      );
      if (n2 > 0 || n3 > 0) {
         vec3 = new Vec3(serverPos.xCoord, serverPos.yCoord, serverPos.zCoord);
      }

      double[] doubles3 = PlayerUtil.predictPlayerTowardsTarget(n - 1, mc.thePlayer, entity);
      Vec3 vec4 = new Vec3(doubles3[0], doubles3[1], doubles3[2]);
      Vec3 vec5 = new Vec3(
         vec3.xCoord + (vec4.xCoord - vec3.xCoord) / 3.0,
         vec3.yCoord + (vec4.yCoord - vec3.yCoord) / 3.0,
         vec3.zCoord + (vec4.zCoord - vec3.zCoord) / 3.0
      );
      float f2 = mc.thePlayer.width / 2.0F;
      float collisionBorderSize2 = mc.thePlayer.getCollisionBorderSize();
      AxisAlignedBB box2 = new AxisAlignedBB(
            vec5.xCoord - (double)f2,
            vec5.yCoord,
            vec5.zCoord - (double)f2,
            vec5.xCoord + (double)f2,
            vec5.yCoord + (double)mc.thePlayer.height,
            vec5.zCoord + (double)f2
         )
         .expand((double)collisionBorderSize2, (double)collisionBorderSize2, (double)collisionBorderSize2);
      double[] doubles4 = PlayerUtil.predictTargetPosition(entity, n2);
      boolean flag = doubles4[9] == 1.0;
      Vec3 vec6 = new Vec3(doubles4[0], doubles4[1], doubles4[2]);
      Vec3 vec7 = new Vec3(doubles4[3], doubles4[4], doubles4[5]);
      float f3 = 0.91F;
      if (entity.onGround) {
         f3 = entity.entityWorld
               .getBlockState(
                  new BlockPos(MathHelper.floor_double(vec6.xCoord), MathHelper.floor_double(vec6.yCoord) - 1, MathHelper.floor_double(vec6.zCoord))
               )
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      double d = vec6.xCoord - vec7.xCoord;
      double d2 = vec6.yCoord - vec7.yCoord;
      double d3 = vec6.zCoord - vec7.zCoord;
      if (mc.theWorld.isRemote
         && (
            !mc.theWorld.isBlockLoaded(new BlockPos((int)vec6.xCoord, 0, (int)vec6.zCoord))
               || !mc.theWorld.getChunkFromBlockCoords(new BlockPos((int)vec6.xCoord, 0, (int)vec6.zCoord)).isLoaded()
         )) {
         if (vec6.yCoord > 0.0) {
            d2 = -0.1;
         } else {
            d2 = 0.0;
         }
      } else {
         d2 -= 0.08;
      }

      d2 *= 0.9800000190734863;
      d *= (double)f3;
      d3 *= (double)f3;
      double d4 = mc.thePlayer.posX - entity.posX;

      double d5;
      for (d5 = mc.thePlayer.posZ - entity.posZ; d4 * d4 + d5 * d5 < 1.0E-4; d5 = (Math.random() - Math.random()) * 0.01) {
         d4 = (Math.random() - Math.random()) * 0.01;
      }

      float f4 = MathHelper.sqrt_double(d4 * d4 + d5 * d5);
      float f5 = 0.4F;
      d /= 2.0;
      d2 /= 2.0;
      d3 /= 2.0;
      d -= d4 / (double)f4 * (double)f5;
      d2 += (double)f5;
      d3 -= d5 / (double)f4 * (double)f5;
      if (d2 > 0.4000000059604645) {
         d2 = 0.4000000059604645;
      }

      Vec3 vec8 = new Vec3(d, d2, d3);
      Vec3 vec9 = new Vec3(doubles3[3], doubles3[4] + (double)mc.thePlayer.getEyeHeight(), doubles3[5]);
      double d6 = MathHelper.clamp_double(vec9.xCoord, box.minX, box.maxX);
      double d7 = MathHelper.clamp_double(vec9.zCoord, box.minZ, box.maxZ);
      double d8 = d6 - doubles3[3];
      float f6 = (float)(MathHelper.atan2(d7 - doubles3[5], d8) * 180.0 / 3.141592653589793 - 90.0);
      int n6 = 1 + EnchantmentHelper.getKnockbackModifier(mc.thePlayer);
      vec8 = vec8.addVector(
         (double)(-MathHelper.sin(f6 * 3.1415927F / 180.0F) * (float)n6 * 0.5F),
         0.1,
         (double)(MathHelper.cos(f6 * 3.1415927F / 180.0F) * (float)n6 * 0.5F)
      );
      f3 = 0.91F;
      if (flag) {
         f3 = entity.entityWorld
               .getBlockState(
                  new BlockPos(MathHelper.floor_double(vec6.xCoord), MathHelper.floor_double(vec6.yCoord) - 1, MathHelper.floor_double(vec6.zCoord))
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
         Vec3 vec10 = new Vec3(vec6.xCoord, vec6.yCoord + (double)entity.getEyeHeight(), vec6.zCoord);
         new Vec3(
            MathHelper.clamp_double(vec10.xCoord, box2.minX, box2.maxX),
            MathHelper.clamp_double(vec10.yCoord, box2.minY, box2.maxY),
            MathHelper.clamp_double(vec10.zCoord, box2.minZ, box2.maxZ)
         );
         double d10 = vec8.xCoord * -1.0;
         float f12 = (float)(MathHelper.atan2(vec8.zCoord * -1.0, d10) * 180.0 / 3.141592653589793 - 90.0);
         float f13 = MathHelper.sin(f12 * 3.1415927F / 180.0F);
         float f14 = MathHelper.cos(f12 * 3.1415927F / 180.0F);
         vec8 = vec8.addVector((double)(f9 * f14 - f10 * f13), 0.0, (double)(f10 * f14 + f9 * f13));
      }

      Vec3 vec11 = vec6.add(vec8);
      Vec3 vec12 = new Vec3(vec11.xCoord, vec11.yCoord + (double)entity.getEyeHeight(), vec11.zCoord);
      Vec3 vec13 = new Vec3(
         MathHelper.clamp_double(vec12.xCoord, box2.minX, box2.maxX),
         MathHelper.clamp_double(vec12.yCoord, box2.minY, box2.maxY),
         MathHelper.clamp_double(vec12.zCoord, box2.minZ, box2.maxZ)
      );
      Vec3 vec14 = new Vec3(doubles3[3], doubles3[4], doubles3[5]);
      Vec3 vec15 = new Vec3(vec14.xCoord, vec14.yCoord + (double)mc.thePlayer.getEyeHeight(), vec14.zCoord);
      Vec3 vec16 = new Vec3(
         MathHelper.clamp_double(vec15.xCoord, box.minX, box.maxX),
         MathHelper.clamp_double(vec15.yCoord, box.minY, box.maxY),
         MathHelper.clamp_double(vec15.zCoord, box.minZ, box.maxZ)
      );
      double d12 = ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
         ? ClientManagers.moduleManager.killAura.currentRange
         : (ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
      if (this.debug.getValue()
         && (double)entity.hurtTime <= this.maxTargetHurttime.getValue()
         && vec12.distanceTo(vec13) > 3.0
         && vec15.distanceTo(vec16) < d12
         && mc.thePlayer.getDistanceToEntityBox(entity) > 3.0F) {
         this.sendChatMessage(vec12.distanceTo(vec13) + "  " + vec15.distanceTo(vec16));
      }

      return (double)entity.hurtTime <= this.maxTargetHurttime.getValue()
         && vec12.distanceTo(vec13) > 3.0
         && vec15.distanceTo(vec16) < d12
         && mc.thePlayer.getDistanceToEntityBox(entity) > 3.0F;
   }

}
