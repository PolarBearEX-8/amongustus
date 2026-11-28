package net.augustus.modules.legit;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.EventMouseMove;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.LookDownDisableSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.utils.TickCounter;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationUtil;
import java.awt.Color;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.EntityRotationModule;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AimBot extends EntityRotationModule implements LoadWorldListener, TickListener, MouseMoveListener {
   public final DoubleValue preAimRange;
   public final DoubleValue ownYawMul;
   public final DoubleValue ownPitchMul;
   public final DoubleValue assistYawMul;
   public final DoubleValue assistPitchMul;
   public final DoubleValue yawFov;
   public final DoubleValue pitchFov;
   public final BooleanValue click;
   public final BooleanValue diggingCheck;
   private final DoubleValue aimTickDelayAfterDigging;
   private final TargetSelectionSettings targetSelectionSettings;
   private final BooleanValue selectTargetThroughWalls;
   private final WeaponFilterSettings weaponFilterSettings;
   private final LookDownDisableSettings lookDownDisableSettings;
   private final TickCounter tickCounter;
   public EntityLivingBase target;
   private long lastFrameTimeMs;
   private long lastTickTimeMs;
   private int tickDurationMs;
   private final double[] assistDeltaRemainder;
   private final double[] ownDeltaRemainder;
   private final double[] accumulatedMouseDelta;
   private final int[] appliedMouseDelta;
   private int[] targetMouseDelta;

   public AimBot() {
       super("AimBot", "Ziehlhilfe", new Color(66, 135, 245), Categorys.LEGIT);
      this.preAimRange = new DoubleValue(this, "PreAimRange", 5.0, 0.0, 10.0, 1);
      this.ownYawMul = new DoubleValue(this, "OwnYawMul", 1.0, 0.0, 1.0, 2);
      this.ownPitchMul = new DoubleValue(this, "OwnPitchMul", 1.0, 0.0, 1.0, 2);
      this.assistYawMul = new DoubleValue(this, "AssistYawMul", 0.5, 0.0, 1.0, 2);
      this.assistPitchMul = new DoubleValue(this, "AssistPitchMul", 0.5, 0.0, 1.0, 2);
      this.yawFov = (DoubleValue)(new DoubleValue(this, "YawFov", 45.0, 0.0, 180.0, 0)).markVisualOnly();
      this.pitchFov = (DoubleValue)(new DoubleValue(this, "PitchFov", 26.0, 0.0, 180.0, 0)).markVisualOnly();
      this.click = (BooleanValue)(new BooleanValue(this, "Click", true)).markVisualOnly();
      this.diggingCheck = (BooleanValue)(new BooleanValue(this, "DiggingCheck", true)).markVisualOnly();
      DoubleValue aimTickDelayAfterDigging2 = new DoubleValue(this, "Aim tick delay after digging", 0.0, 0.0, 20.0, 0);
      BooleanValue diggingCheck2 = this.diggingCheck;
      this.aimTickDelayAfterDigging = (DoubleValue)aimTickDelayAfterDigging2.visibleWhen(() -> diggingCheck2.getValue()).markVisualOnly();
      this.targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter())).withSorting();
      this.selectTargetThroughWalls = new BooleanValue(this, "SelectTargetThroughWalls", true);
      this.weaponFilterSettings = new WeaponFilterSettings(this);
      this.lookDownDisableSettings = new LookDownDisableSettings(this);
      this.tickCounter = new TickCounter();
      this.target = null;
      this.lastFrameTimeMs = 0L;
      this.lastTickTimeMs = 0L;
      this.tickDurationMs = 0;
      this.assistDeltaRemainder = new double[2];
      this.ownDeltaRemainder = new double[2];
      this.accumulatedMouseDelta = new double[2];
      this.appliedMouseDelta = new int[2];
      this.targetMouseDelta = new int[2];
   }

   @Override
   public void onEnable() {
      this.moveFixInRotationModule.setValue(true);
      this.moveFixInRotationModule.withVisibility(() -> false);
      String str = "None";
      this.silentMoveFix.setSelectedValue(str);
      this.rotating = true;
      super.onEnable();
      this.target = null;
      this.lastFrameTimeMs = System.currentTimeMillis();
      this.lastTickTimeMs = System.currentTimeMillis();
      this.targetMouseDelta[0] = 0;
      this.targetMouseDelta[1] = 0;
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[1] = 0.0;
   }

   @Override
   public void onDisable() {
      super.onDisable();
      this.target = null;
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (ClientManagers.moduleManager.freelook.isToggled()) {
         event.setDeltaX((byte)0);
         event.setDeltaY((byte)0);
      }

      if (mc.thePlayer != null
         && !mc.thePlayer.isDead
         && mc.thePlayer.ticksExisted > 20
         && this.target != null
         && mc.isNotInGui()
         && (!this.click.getValue() || mc.gameSettings.keyBindAttack.isKeyDown())) {
         AutoRod autoRod = ClientManagers.moduleManager.autoRod;
         if (!ClientManagers.rotationController.isOwnedBy(autoRod) && this.passesDiggingCheck()) {
            int n = (int)(System.currentTimeMillis() - this.lastFrameTimeMs);
            EntityLivingBase target2 = this.target;
            float wrappedYaw = this.getWrappedYaw();
            Rotation rotation = new Rotation(wrappedYaw, this.getPitch());
            float partialTicks = event.getPartialTicks();
            float f = 180.0F;
            Rotation rotation3 = this.calculateSmoothRotationToEntity(target2, rotation, partialTicks, 180.0F, f);
            if (rotation3 == null) {
               return;
            }

            float yaw = rotation3.yaw;
            float pitch = rotation3.pitch;
            partialTicks = this.getWrappedYaw();
            int[] ints = RotationUtil.computeMouseDeltas(yaw, pitch, partialTicks, this.getPitch());
            double d = (double)this.targetMouseDelta[0] / (double)this.tickDurationMs * (double)n;
            double d2 = (double)this.targetMouseDelta[1] / (double)this.tickDurationMs * (double)n;
            this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[0] + d;
            this.accumulatedMouseDelta[1] = this.accumulatedMouseDelta[1] + d2;
            int n2 = (int)Math.round(this.accumulatedMouseDelta[0] - (double)this.appliedMouseDelta[0]);
            int n3 = (int)Math.round(this.accumulatedMouseDelta[1] - (double)this.appliedMouseDelta[1]);
            this.appliedMouseDelta[0] = this.appliedMouseDelta[0] + n2;
            this.appliedMouseDelta[1] = this.appliedMouseDelta[1] + n3;
            if (event.getDeltaX() != 0) {
               int n4 = ints[0];
               if (n4 < 0) {
                  if (n4 - event.getDeltaX() <= n4) {
                     double d4 = ((double)event.getDeltaX() * this.assistYawMul.getValue()) + this.assistDeltaRemainder[0];
                     int n5 = (int)Math.round(d4);
                     event.setDeltaX(n5);
                     this.assistDeltaRemainder[0] = d4 - (double)n5;
                  }
               } else if (n4 - event.getDeltaX() >= n4) {
                  double d6 = ((double)event.getDeltaX() * this.assistYawMul.getValue()) + this.assistDeltaRemainder[0];
                  int n6 = (int)Math.round(d6);
                  event.setDeltaX(n6);
                  this.assistDeltaRemainder[0] = d6 - (double)n6;
               }
            }

            if (event.getDeltaY() != 0) {
               int n7 = ints[1];
               if (n7 < 0) {
                  if (n7 - event.getDeltaY() <= n7) {
                     double d8 = ((double)event.getDeltaY() * this.assistPitchMul.getValue()) + this.assistDeltaRemainder[1];
                     int n8 = (int)Math.round(d8);
                     event.setDeltaY(n8);
                     this.assistDeltaRemainder[1] = d8 - (double)n8;
                  }
               } else if (n7 - event.getDeltaY() >= n7) {
                  double d10 = ((double)event.getDeltaY() * this.assistPitchMul.getValue()) + this.assistDeltaRemainder[1];
                  int n9 = (int)Math.round(d10);
                  event.setDeltaY(n9);
                  this.assistDeltaRemainder[1] = d10 - (double)n9;
               }
            }

            double d12 = ((double)event.getDeltaX() * this.ownYawMul.getValue()) + this.ownDeltaRemainder[0];
            int n10 = (int)Math.round(d12);
            event.setDeltaX(n10);
            this.ownDeltaRemainder[0] = d12 - (double)n10;
            double d14 = ((double)event.getDeltaY() * this.ownPitchMul.getValue()) + this.ownDeltaRemainder[1];
            int n11 = (int)Math.round(d14);
            event.setDeltaY(n11);
            this.ownDeltaRemainder[1] = d14 - (double)n11;
            event.setDeltaX(n2 + event.getDeltaX());
            event.setDeltaY(n3 + event.getDeltaY());
         }
      }

      this.lastFrameTimeMs = System.currentTimeMillis();
   }

   @Override
   public void onPreTick() {
      this.tickDurationMs = (int)Math.abs(this.lastTickTimeMs - System.currentTimeMillis());
      this.lastTickTimeMs = System.currentTimeMillis();
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[1] = 0.0;
      this.firstRotationTick = true;
   }

   @Override
   public void onLateTick() {
      this.tickCounter.increment();
      EntityLivingBase target2 = this.target;
      this.targetSelectionSettings.setCurrentTarget(target2);
      this.target = null;
      if (mc.isNotInGui() && (!this.click.getValue() || mc.gameSettings.keyBindAttack.isKeyDown()) && this.weaponFilterSettings.isHeldItemAllowed() && !this.lookDownDisableSettings.shouldDisable()) {
         this.target = this.findTarget();
         if (this.target != null) {
            RandomNumberGenerator instance = DefaultRandomGenerator.instance;
            float f = instance.nextSecureFloat(this.yawSpeedInRotationModule.getMinValue(), this.yawSpeedInRotationModule.getMaxValue()) / 2.0F;
            instance = DefaultRandomGenerator.instance;
            float f2 = instance.nextSecureFloat(this.pitchSpeedInRotationModule.getMinValue(), this.pitchSpeedInRotationModule.getMaxValue()) / 2.0F;
            EntityLivingBase target3 = this.target;
            float wrappedYaw = this.getWrappedYaw();
            Rotation rotation3 = this.calculateSmoothRotationToEntity(target3, new Rotation(wrappedYaw, this.getPitch()), 1.0F, f, f2);
            float yaw = rotation3.yaw;
            float pitch = rotation3.pitch;
            wrappedYaw = this.getWrappedYaw();
            this.targetMouseDelta = RotationUtil.computeMouseDeltas(yaw, pitch, wrappedYaw, this.getPitch());
            System.out.println(Arrays.toString(this.targetMouseDelta));
            return;
         }
      }

      this.targetMouseDelta[0] = 0;
      this.targetMouseDelta[1] = 1;
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.target = null;
      this.targetSelectionSettings.setCurrentTarget((Entity)null);
   }

   private float getWrappedYaw() {
      float realYaw = Augustus.getInstance().getYawPitchHelper().realYaw;

      while (mc.thePlayer.rotationYaw - realYaw >= 180.0F) {
         realYaw += 360.0F;
      }

      while (mc.thePlayer.rotationYaw - realYaw < -180.0F) {
         realYaw -= 360.0F;
      }

      return realYaw;
   }

   private float getPitch() {
      return Augustus.getInstance().getYawPitchHelper().realPitch;
   }

   private boolean passesDiggingCheck() {
      boolean flag = this.diggingCheck.getValue() && mc.gameSettings.keyBindAttack.isKeyDown() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK;
      if (flag) {
         this.tickCounter.reset();
      }

      return this.tickCounter.hasReached(this.aimTickDelayAfterDigging.getValue()) && !flag;
   }

   private EntityLivingBase findTarget() {
      EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
      entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelectionSettings.getTargetFilter());
      entityTargetSelector = entityTargetSelector.throughWalls(this.selectTargetThroughWalls.getValue());
      entityTargetSelector = entityTargetSelector.maxDistance(this.preAimRange.getValue());
      entityTargetSelector = entityTargetSelector.withinFov(this.yawFov.getValue(), this.pitchFov.getValue()).filterFocus();
      ToDoubleFunction toDoubleFunction = this.targetSelectionSettings.createTargetComparatorKey(new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch), ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
      Optional optional = entityTargetSelector.sortBy(toDoubleFunction).findBest();
      return optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
   }

}
