package net.augustus.modules.modes.anticheat.autoclicker;

import net.minecraft.util.MovingObjectPosition;
import java.util.ArrayList;
import java.util.HashMap;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public abstract class AbstractHurtTimeClickCheck extends ModuleMode {
   private EntityLivingBase currentTarget;
   private final TimeHelper timeHelper;
   private int attacksThisTick;
   private int sampleWindowSize;
   private final HashMap hurtTimeAttackSamples = new HashMap();
   private int lastTargetHurtTime;

   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         EntityLivingBase lookedAtTarget = this.getLookedAtTarget();
         if (lookedAtTarget != null) {
            this.currentTarget = lookedAtTarget;
            this.timeHelper.reset();
         }

         if (this.timeHelper.reached(3000L)) {
            this.currentTarget = null;
         }

         if (this.currentTarget != null) {
            if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof EntityLivingBase && this.currentTarget.hurtTime != this.lastTargetHurtTime) {
               ((ArrayList)this.hurtTimeAttackSamples.get(this.currentTarget.hurtTime)).add(this.attacksThisTick);
            }

            this.lastTargetHurtTime = this.currentTarget.hurtTime;
         }

         this.attacksThisTick = 0;
         if (this.hurtTimeAttackSamples.isEmpty()) {
            this.resetHurtTimeSamples();
         }

         for (int i = 0; i < 11; i++) {
            ArrayList list2 = (ArrayList)this.hurtTimeAttackSamples.get(i);

            while (list2.size() > this.sampleWindowSize) {
               list2.remove(0);
            }
         }

         this.analyzeHurtTimeSamples(this.hurtTimeAttackSamples);
      } else if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
         this.attacksThisTick++;
      }
   }

   private void resetHurtTimeSamples() {
      this.hurtTimeAttackSamples.clear();

      for (int i = 0; i < 11; i++) {
         this.hurtTimeAttackSamples.put(i, new ArrayList());
      }
   }

   private EntityLivingBase getLookedAtTarget() {
      EntityLivingBase entity = null;
      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
         entity = (EntityLivingBase)mc.objectMouseOver.entityHit;
      }

      return CombatTargetUtil.getActiveTargetOrDefault(entity);
   }

   public void onTick() {
   }

   public void onClearTarget() {
      this.currentTarget = null;
   }

   public abstract void analyzeHurtTimeSamples(HashMap map);

   public void resetState() {
      this.resetHurtTimeSamples();
   }

   public AbstractHurtTimeClickCheck(String str, AntiCheatPlayerData antiCheatPlayerData2, int n) {
      super(str, antiCheatPlayerData2);
      this.timeHelper = new TimeHelper();
      this.lastTargetHurtTime = 0;
      this.sampleWindowSize = n;
      this.visibleInGui = true;
   }




}
