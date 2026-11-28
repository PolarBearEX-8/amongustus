package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.ClientManagers;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.listeners.TimerListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.EventTimer;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimeManipulation extends Module implements RenderListener, LoadWorldListener, TimerListener {
   private final TimeHelper idleChargeTimer;
   public final TimeHelper timerRangeCooldown;
   public final DoubleValue minTicksAvailable;
   public final DoubleValue maxChargingTicks;
   public final BooleanValue correct;
   public final BooleanValue autoCharge;
   public final BooleanValue sneakCharge;
   public final BooleanValue updateEntityPositions;
   public final BooleanValue resetAtWorldChange;
   public final BooleanValue timerRangeBool;
   public final SettingGroup timerRange;
   public final DoubleValue delay;
   public final DoubleValue timerTicksForward;
   public final DoubleValue tolerance;
   public final BooleanValue onlyOnGround;
   public final BooleanValue dynamic;
   public final BooleanValue onlyKillAura;
   public final BooleanValue clickCheck;
   public final BooleanValue forbiddenSneak;

   public TimeManipulation() {
       super("TimeManipulation", "ZeitManipulation", new Color(50, 168, 82), Categorys.MISC);
      this.idleChargeTimer = new TimeHelper();
      this.timerRangeCooldown = new TimeHelper();
      this.minTicksAvailable = new DoubleValue(this, "MinTicksAvailable", 0.0, -10.0, 10.0, 0);
      this.maxChargingTicks = new DoubleValue(this, "MaxChargingTicks", 8.0, 1.0, 100.0, 0);
      this.correct = new BooleanValue(this, "Correct", true);
      this.autoCharge = new BooleanValue(this, "AutoCharge", false);
      this.sneakCharge = new BooleanValue(this, "SneakCharge", true);
      this.updateEntityPositions = new BooleanValue(this, "UpdateEntityPositions", false);
      this.resetAtWorldChange = new BooleanValue(this, "ResetAtWorldChange", true);
      this.timerRangeBool = (BooleanValue)new BooleanValue(this, "TimerRangeBool", false).withDisplayName("TimerRange");
      SettingGroup timerRangeGroup = new SettingGroup(this, "TimerRange");
      BooleanValue timerRangeBool2 = this.timerRangeBool;
      this.timerRange = timerRangeGroup.visibleWhen(() -> timerRangeBool2.getValue());
      this.delay = new DoubleValue(this, "Delay", 0.0, 0.0, 20000.0, 0, this.timerRange);
      this.timerTicksForward = new DoubleValue(this, "TimerTicksForward", 3.0, 1.0, 10.0, 0, this.timerRange);
      this.tolerance = new DoubleValue(this, "Tolerance", 0.0, -1.0, 1.0, 2, this.timerRange);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false, this.timerRange);
      this.dynamic = new BooleanValue(this, "Dynamic", true, this.timerRange);
      this.onlyKillAura = new BooleanValue(this, "OnlyKillAura", true, this.timerRange);
      this.clickCheck = new BooleanValue(this, "ClickCheck", false, this.timerRange);
      this.forbiddenSneak = new BooleanValue(this, "ForbiddenSneak", true, this.timerRange);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      mc.gameElapsedTicks = 0L;
      mc.realElapsedTicks = 0L;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(41);
   }

   @Override
   public void onTimerTick(EventTimer event) {
      if (this.timerRangeBool.getValue()) {
         if (!BlockUtil.isScaffoldActiveAndValid()
            && mc.isNotInGui()
            && mc.theWorld != null
            && mc.thePlayer != null
            && !ClientManagers.moduleManager.airStuck.isToggled()
            && (!ClientManagers.moduleManager.bugUp.buggingUp || !ClientManagers.moduleManager.bugUp.isToggled())
            && (!this.forbiddenSneak.getValue() || !mc.thePlayer.isSneaking())
            && (!this.clickCheck.getValue() || mc.gameSettings.keyBindAttack.isKeyDown())
            && (!this.onlyOnGround.getValue() || mc.thePlayer.onGround)) {
            if (this.timerRangeCooldown.reached((long)this.delay.getValue())
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
                           double[] doubles = PlayerUtil.predictPlayerPosition(i, mc.thePlayer);
                           double d4 = mc.thePlayer.getServerPos().xCoord + (doubles[0] - mc.thePlayer.getServerPos().xCoord) / 3.0;
                           double d5 = mc.thePlayer.getServerPos().yCoord + (doubles[1] - mc.thePlayer.getServerPos().yCoord) / 3.0;
                           double d6 = mc.thePlayer.getServerPos().zCoord + (doubles[2] - mc.thePlayer.getServerPos().zCoord) / 3.0;
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
                           double d7 = ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
                              ? ClientManagers.moduleManager.killAura.currentRange
                              : (ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
                           AxisAlignedBB box3 = new AxisAlignedBB(
                                 d4 - (double)f, d5, d6 - (double)f, d4 + (double)f, d5 + (double)height, d6 + (double)f
                              )
                              .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                           Vec3 vec5 = new Vec3(
                              MathHelper.clamp_double(vec4.xCoord, box3.minX, box3.maxX),
                              MathHelper.clamp_double(vec4.yCoord, box3.minY, box3.maxY),
                              MathHelper.clamp_double(vec4.zCoord, box3.minZ, box3.maxZ)
                           );
                           if (vec.distanceTo(vec2) < d7 && vec4.distanceTo(vec5) > 3.0 - this.tolerance.getValue() && positionEyes.distanceTo(vec3) > 3.0) {
                              event.setExtraTicks(i);
                              if (ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.timerRangeDebug.getValue()) {
                                 ClientManagers.moduleManager.debug.outputDebugMessage("Timered:  " + vec4.distanceTo(vec5) + "  " + vec.distanceTo(vec2));
                              }

                              this.timerRangeCooldown.reset();
                              break;
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
      }
   }

   public boolean shouldChargeTick(int n) {
      boolean flag;
      if (mc.thePlayer != null && this.isChargeConditionMet()) {
if (((double)n) < this.maxChargingTicks.getValue(true)) {
flag = true;
} else {
flag = false;
}
} else {
flag = false;
}

      boolean flag3 = flag;
      if (mc.thePlayer != null && ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.timeManipulation.getValue()) {
         String str = "TickBuffer: " + n;
         ClientManagers.moduleManager.debug.outputDebugMessage(str);
      }

      return flag3;
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      if (this.resetAtWorldChange.getValue()) {
         mc.gameElapsedTicks = 0L;
         mc.realElapsedTicks = 0L;
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      int n = (int)(mc.realElapsedTicks - mc.gameElapsedTicks);
      mc.fontRendererObj.drawString("" + n, sr.getScaledWidth() - mc.fontRendererObj.getStringWidth("" + n), sr.getScaledHeight() - 30, -1);
      RenderUtil.resetGlColorState();
   }

   private boolean isChargeConditionMet() {
      boolean flag = this.sneakCharge.getValue() && KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && mc.isNotInGui();
      if (this.autoCharge.getValue()) {
         if (mc.currentScreen == null
            && mc.thePlayer != null
            && mc.theWorld != null
            && !MoveUtil.hasHorizontalMotion()
            && mc.thePlayer.ticksExisted > 20
            && !MouseInputUtil.isButtonDown(0)
            && !MouseInputUtil.isButtonDown(1)) {
            for (int i = 0; i < 223; i++) {
               if (KeyboardInputUtil.isKeyDown(i)) {
                  this.idleChargeTimer.reset();
                  break;
               }
            }

            if (this.idleChargeTimer.reached(100L)) {
               flag = true;
            }
         } else {
            this.idleChargeTimer.reset();
         }
      }

      return this.timerRangeBool.getValue() ? true : flag;
   }

   public void updateOtherEntities() {
      if (mc.theWorld != null) {
         for (int i = 0; i < mc.theWorld.loadedEntityList.size(); i++) {
            Entity entity = (Entity)mc.theWorld.loadedEntityList.get(i);
            if (!(entity instanceof EntityPlayerSP)) {
               mc.theWorld.updateEntity(entity);
            }
         }
      }
   }

   @Override
   public void onTimerSpeed(EventTimer event) {
   }

}
