package net.augustus.modules.misc;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.intave14.autoclicker.Intave14AutoClickerLowDeviationCheck;
import net.augustus.modules.modes.anticheat.intave13.Intave13P56RotationSmoothnessCheck;
import net.augustus.modules.modes.anticheat.intave14.autoclicker.Intave14AutoClickerInvariantCheck;
import net.augustus.modules.modes.anticheat.intave14.autoclicker.Intave14AutoClickerRelativeCheck;
import net.augustus.modules.modes.anticheat.intave13.Intave13P19AttackTimingCheck;
import net.augustus.modules.modes.anticheat.intave14.autoclicker.Intave14AutoClickerDoubleClickCheck;
import net.augustus.modules.modes.anticheat.intave14.inventory.Intave14InventoryAnalysisCheck;
import net.augustus.modules.modes.anticheat.intave14.autoclicker.Intave14AutoClickerVarianceCheck;
import net.augustus.modules.modes.anticheat.intave14.autoclicker.Intave14AutoClickerSpikeCheck;
import net.augustus.modules.modes.anticheat.intave14.scaffold.Intave14InvalidBlockPlaceCheck;
import net.augustus.modules.modes.anticheat.intave14.inventory.Intave14InventorySlotCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P51CornerAccuracyCheck;
import net.augustus.modules.modes.anticheat.movement.NoJumpDelayACheck;
import net.augustus.modules.modes.anticheat.intave14.inventory.Intave14InventorySlotDupeCheck;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerECheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P200AttackSprintCheck;
import net.augustus.modules.modes.anticheat.rias.RiasP100PerfectHitCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P92PreciseYawCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P81EntityTrackingCheck;
import net.augustus.modules.modes.anticheat.aim.AimDistanceCheck;
import net.augustus.modules.modes.anticheat.combat.HitDelayFixACheck;
import net.augustus.modules.modes.anticheat.aim.AimP4ServerYawCheck;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP3Check;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P83YawAccuracyCheck;
import net.augustus.modules.modes.anticheat.aim.TargetSelectionCheck;
import net.augustus.modules.modes.anticheat.aim.AimP2YawCheck;
import net.augustus.modules.modes.anticheat.intave14.inventory.Intave14P131FastCloseCheck;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP7Check;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P41ActionToggleCheck;
import net.augustus.modules.modes.anticheat.intave14.scaffold.Intave14PostBlockPlaceCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P85CornerYawCheck;
import net.augustus.modules.modes.anticheat.intave14.movement.Intave14TimerCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P141To144KillAuraCheck;
import net.augustus.modules.modes.anticheat.combat.HitDelayFixCCheck;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP5Check;
import net.augustus.modules.modes.anticheat.eagle.EagleCEdgeDeviationCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P84SustainedYawCheck;
import net.augustus.modules.modes.anticheat.rias.RiasP106CombinedCheck;
import net.augustus.modules.modes.anticheat.intave14.inventory.Intave14InventoryWalkingCheck;
import net.augustus.modules.modes.anticheat.combat.HitDelayFixBCheck;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP6Check;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14InvalidPitchCheck;
import net.augustus.modules.modes.anticheat.aim.AimP3ServerPitchCheck;
import net.augustus.modules.modes.anticheat.combat.HurtTimeHitCheck;
import net.augustus.modules.modes.anticheat.rias.RiasP104FirstHitCheck;
import net.augustus.modules.modes.anticheat.rias.RiasP101TriggerCheck;
import net.augustus.modules.modes.anticheat.intave14.scaffold.Intave14RotationBlockPlaceCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P231AutomatedAttackCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P82ZeroYawDiffCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P86RotationSnapCheck;
import net.augustus.modules.modes.anticheat.eagle.EagleBUnsneakTimingCheck;
import net.augustus.modules.modes.anticheat.intave14.scaffold.Intave14TimeBlockPlaceCheck;
import net.augustus.modules.modes.anticheat.rias.RiasP105FirstHitCpsCheck;
import net.augustus.modules.modes.anticheat.eagle.EagleASneakTimingCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P71PitchPrecisionCheck;
import net.augustus.modules.modes.anticheat.aim.AimP1PitchCheck;
import net.augustus.modules.modes.anticheat.intave14.combat.Intave14P91SuspiciousRotationCheck;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventMove;
import net.augustus.settings.Setting;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventAttackEntity;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.modules.modes.anticheat.aim.TrackAimCheck;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP4Check;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP1Check;
import net.augustus.modules.modes.anticheat.autoclicker.AutoClickerP2Check;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ClientAntiCheat extends Module implements LoadWorldListener, AttackEntityListener, MoveListener, MoveInputListener, UpdateListener, RenderListener, PacketListener, TickListener {
   private final AntiCheatPlayerData antiCheatPlayerData;
   private final HashMap checkToggleSettings;
   private final ArrayList checks;
   private EntityLivingBase lastTarget;

   public ClientAntiCheat() {
       super("ClientAntiCheat", "KundenAntiSchummel", new Color(78, 60, 102), Categorys.MISC);
      this.antiCheatPlayerData = new AntiCheatPlayerData();
      this.checkToggleSettings = new HashMap();
      this.checks = new ArrayList();
      this.lastTarget = null;
      this.checks.add(new Intave13P19AttackTimingCheck("Intave13P_19", this.antiCheatPlayerData));
      this.checks.add(new Intave13P56RotationSmoothnessCheck("Intave13P_56", this.antiCheatPlayerData));
      this.checks.add(new Intave14P41ActionToggleCheck("Intave14P_41", this.antiCheatPlayerData));
      this.checks.add(new Intave14P51CornerAccuracyCheck("Intave14P_51", this.antiCheatPlayerData));
      this.checks.add(new Intave14P71PitchPrecisionCheck("Intave14P_71", this.antiCheatPlayerData));
      this.checks.add(new Intave14P81EntityTrackingCheck("Intave14P_81", this.antiCheatPlayerData));
      this.checks.add(new Intave14P82ZeroYawDiffCheck("Intave14P_82", this.antiCheatPlayerData));
      this.checks.add(new Intave14P83YawAccuracyCheck("Intave14P_83", this.antiCheatPlayerData));
      this.checks.add(new Intave14P84SustainedYawCheck("Intave14P_84", this.antiCheatPlayerData));
      this.checks.add(new Intave14P85CornerYawCheck("Intave14P_85", this.antiCheatPlayerData));
      this.checks.add(new Intave14P86RotationSnapCheck("Intave14P_86", this.antiCheatPlayerData));
      this.checks.add(new Intave14P91SuspiciousRotationCheck("Intave14P_91", this.antiCheatPlayerData));
      this.checks.add(new Intave14P92PreciseYawCheck("Intave14P_92", this.antiCheatPlayerData));
      this.checks.add(new Intave14PostBlockPlaceCheck("Intave14PostBlockPlaceCheck", this.antiCheatPlayerData));
      this.checks.add(new Intave14TimeBlockPlaceCheck("Intave14TimeBlockPlaceCheck", this.antiCheatPlayerData, true));
      this.checks.add(new Intave14InvalidBlockPlaceCheck("Intave14InvalidBlockPlace", this.antiCheatPlayerData));
      this.checks.add(new Intave14RotationBlockPlaceCheck("Intave14RotationBlockCheck", this.antiCheatPlayerData));
      this.checks.add(new Intave14AutoClickerInvariantCheck("Intave14ACInvariant", this.antiCheatPlayerData));
      this.checks.add(new Intave14AutoClickerRelativeCheck("Intave14ACRelative", this.antiCheatPlayerData));
      this.checks.add(new Intave14AutoClickerSpikeCheck("Intave14ACSpike", this.antiCheatPlayerData));
      this.checks.add(new Intave14AutoClickerVarianceCheck("Intave14ACVariance", this.antiCheatPlayerData));
      this.checks.add(new Intave14AutoClickerLowDeviationCheck("Intave14ACLowDeviation", this.antiCheatPlayerData));
      this.checks.add(new Intave14AutoClickerDoubleClickCheck("Intave14ACDoubleClick", this.antiCheatPlayerData));
      this.checks.add(new Intave14P131FastCloseCheck("Intave14P_131", this.antiCheatPlayerData));
      this.checks.add(new Intave14InventoryAnalysisCheck("Intave14InventoryAnalysis", this.antiCheatPlayerData));
      this.checks.add(new Intave14InventoryWalkingCheck("Intave14InventoryWalking", this.antiCheatPlayerData));
      this.checks.add(new Intave14InventorySlotCheck("Intave14InventorySlot", this.antiCheatPlayerData));
      this.checks.add(new Intave14P200AttackSprintCheck("Intave14P_200", this.antiCheatPlayerData));
      this.checks.add(new Intave14P141To144KillAuraCheck("Intave14P141_144", this.antiCheatPlayerData));
      this.checks.add(new Intave14P231AutomatedAttackCheck("Intave14P_231", this.antiCheatPlayerData));
      this.checks.add(new Intave14InvalidPitchCheck("Intave14InvalidPitch", this.antiCheatPlayerData));
      this.checks.add(new Intave14InventorySlotDupeCheck("Intave14InventorySlotDupe", this.antiCheatPlayerData));
      this.checks.add(new Intave14TimerCheck("Intave14Timer", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP1Check("AutoClickerP1", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP2Check("AutoClickerP2", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP3Check("AutoClickerP3", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP4Check("AutoClickerP4", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP5Check("AutoClickerP5", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP6Check("AutoClickerP6", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerP7Check("AutoClickerP7", this.antiCheatPlayerData));
      this.checks.add(new HurtTimeHitCheck("HurtTimeHit", this.antiCheatPlayerData));
      this.checks.add(new AimP1PitchCheck("AimP1", this.antiCheatPlayerData));
      this.checks.add(new AimP2YawCheck("AimP2", this.antiCheatPlayerData));
      this.checks.add(new AimP3ServerPitchCheck("AimP3", this.antiCheatPlayerData));
      this.checks.add(new AimP4ServerYawCheck("AimP4", this.antiCheatPlayerData));
      this.checks.add(new TargetSelectionCheck("SelectionCheck", this.antiCheatPlayerData));
      this.checks.add(new RiasP101TriggerCheck("Trigger", this.antiCheatPlayerData));
      this.checks.add(new RiasP100PerfectHitCheck("PerfectHit1", this.antiCheatPlayerData));
      this.checks.add(new RiasP105FirstHitCpsCheck("FirstHitCPS", this.antiCheatPlayerData));
      this.checks.add(new RiasP104FirstHitCheck("FirstHit", this.antiCheatPlayerData));
      this.checks.add(new RiasP106CombinedCheck("Combined", this.antiCheatPlayerData));
      this.checks.add(new AutoClickerECheck("AutoClickerE", this.antiCheatPlayerData));
      this.checks.add(new EagleASneakTimingCheck("EagleA", this.antiCheatPlayerData));
      this.checks.add(new EagleBUnsneakTimingCheck("EagleB", this.antiCheatPlayerData));
      this.checks.add(new EagleCEdgeDeviationCheck("EagleC", this.antiCheatPlayerData));
      this.checks.add(new TrackAimCheck("TrackAim", this.antiCheatPlayerData));
      this.checks.add(new HitDelayFixACheck("HitDelayFixA", this.antiCheatPlayerData));
      this.checks.add(new HitDelayFixBCheck("HitDelayFixB", this.antiCheatPlayerData));
      this.checks.add(new HitDelayFixCCheck("HitDelayFixC", this.antiCheatPlayerData));
      this.checks.add(new AimDistanceCheck("AimDistance", this.antiCheatPlayerData));
      this.checks.add(new NoJumpDelayACheck("NoJumpDelayA", this.antiCheatPlayerData));
      SettingGroup checksForMyselfGroup = new SettingGroup(this, "ChecksForMyself");

      for (ModuleMode moduleMode : (Iterable<ModuleMode>)(this.checks)) {
         BooleanValue booleanValue = new BooleanValue(this, moduleMode.getName(), true, checksForMyselfGroup);
         SettingVisibilityCondition condition = () -> {
            return moduleMode.visibleInGui;
         };
         BooleanValue booleanValue2 = (BooleanValue)booleanValue.withVisibility(condition);
         this.checkToggleSettings.put(moduleMode, booleanValue2);
      }

   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(28, 20);
      this.toggleEventSubscription(21, 20);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(32, 10);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onPostUpdate() {
      boolean flag = false;

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity.getEntityId() == this.antiCheatPlayerData.getTargetEntityId()) {
            flag = true;
            break;
         }
      }

      if (!flag) {
         this.antiCheatPlayerData.resetTarget();
      }

      this.antiCheatPlayerData.updateTracking();
   }

   @Override
   public void onPacketSend(EventPacket event) {
   }

   @Override
   public void onPacketSendNettyDelay(EventPacket event) {
   }

   @Override
   public void onPacketReceiveNetty(EventPacket event) {
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }

   @Override
   public void onRender3D(float partialTicks) {
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (this.antiCheatPlayerData != null && event.getTarget() instanceof EntityLivingBase && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
         AntiCheatPlayerData antiCheatPlayerData2 = this.antiCheatPlayerData;
         antiCheatPlayerData2.onAttackEntity(event.getTarget().getEntityId());
         antiCheatPlayerData2 = this.antiCheatPlayerData;
         antiCheatPlayerData2.setLastAttackRange(mc.thePlayer.getPositionEyes(1.0F).distanceTo(mc.objectMouseOver.hitVec));
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
   }

   @Override
   public void onJumpMove(EventMove event) {
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.antiCheatPlayerData.resetTarget();
   }

}
