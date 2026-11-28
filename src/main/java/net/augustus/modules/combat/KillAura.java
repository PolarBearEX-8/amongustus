package net.augustus.modules.combat;

import com.google.common.base.Predicates;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.render.Line;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.utils.EntityCollidablePredicate;
import net.augustus.events.listeners.LeftClickListener;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.LookDownDisableSettings;
import net.augustus.utils.ActionRateAccumulator;
import net.augustus.render.RenderPassTask;
import net.augustus.settings.options.KillAuraActiveSlotOption;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.events.EventMove;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.listeners.ClickGuiListener;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.listeners.AttackSlowdownListener;
import net.augustus.ai.OnnxModelRegistry;
import net.augustus.utils.file.ClientPaths;
import net.augustus.events.EventClick;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.combat.helper.ClickModuleContext;
import net.augustus.settings.ActionSetting;
import net.augustus.modules.combat.killaura.HitVecCandidate;
import net.augustus.utils.combat.ProtectedPlayerCheck;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.settings.options.MoveHelperModeOption;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.TuplePair;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.modules.combat.killaura.HysteresisRangeTracker;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.utils.math.MathStatisticsUtil;

import net.augustus.utils.rotation.FullBlockRaytraceFilter;
import net.augustus.modules.modes.clicking.SimpleClickPatternMode;
import net.augustus.modules.modes.clicking.DistributionClickPatternMode;
import net.augustus.modules.modes.clicking.SimpleV2ClickPatternMode;
import net.augustus.modules.modes.clicking.SmartClickPatternMode;
import net.augustus.modules.modes.clicking.DeviationClickPatternMode;
import net.augustus.modules.modes.clicking.RecordedClickPatternMode;
import net.augustus.modules.modes.clicking.AbstractClickPatternMode;
import net.augustus.modules.modes.clicking.AdvancedClickPatternMode;
import net.augustus.modules.modes.clicking.DragClickPatternMode;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.modules.modes.killaura.unoise3.UNoise3RotationEngine;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventMouseMove;
import net.augustus.render.RenderUtil;
import net.augustus.modules.modes.killaura.unoise3.UNoise3SettingContext;
import net.augustus.utils.TickCounter;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.modules.combat.helper.ClickSpeedMonitor;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.ai.AiRotationPredictor;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class KillAura
   extends Module
   implements ClickModuleContext,
   LoadWorldListener,
   MoveListener,
   AttackSlowdownListener,
   ClickListener,
   ClickGuiListener,
   TickListener,
   MoveInputListener,
   MotionListener,
   RenderListener,
   RayCastListener,
   PacketListener,
   MouseMoveListener,
   SilentSlotListener,
   MoveEntityListener {
   public final RotationUtil rotationUtil;
   public final SettingGroup range;
   public final RandomizedRangeSettings preAimRange;
   public final DoubleValue range2;
   public final StringValue rangeMode;
   public final DoubleValue hazeAdd;
   public final DoubleValue hazeMax;
   public final StringValue actionToTrackInterval;
   private final DoubleValue interval;
   private final DoubleValue rangeAtInterval;
   private final DoubleValue minReachToIntervalHit;
   private final DoubleValue hurtTimeToHitWithReach;
   private final RangeSetting baseRange;
   private final DoubleValue chanceOfExtendedRange;
   public final SettingGroup rotations;
   public final DoubleValue yawFov;
   public final DoubleValue pitchFov;
   public final RandomizedRangeSettings yawSpeed;
   public final RandomizedRangeSettings pitchSpeed;
   public final RandomizedRangeSettings yawAcceleration;
   public final RandomizedRangeSettings pitchAcceleration;
   public final BooleanValue betterAcceleration;
   public final SettingGroup fixRotationGroup;
   public final StringValue fixRotationMode;
   public final DoubleValue fixRotationChance;
   public final RandomizedRangeSettings maxFixRotationReach;
   public final DoubleValue minFixRotationAngle;
   public final StringValue minDistanceToBestVecCalculation;
   public final DoubleValue minDistanceToBestVecYaw;
   public final DoubleValue minDistanceToBestVecPitch;
   public final DoubleValue minDistanceToBestVec;
   public final StringValue heuristics;
   public final StringValue rotationMode;
   public final DoubleValue hitBoxPercentageVertical;
   public final DoubleValue hitBoxPercentageHorizontal;
   public final DoubleValue hitBoxPercentageVertical1;
   public final DoubleValue hitBoxPercentageHorizontal1;
   private final SettingGroup randomize;
   public final StringValue randomType;
   public StringValue randomizeMode;
   public final DoubleValue randomStrength;
   public final BooleanValue reducedRotate;
   public final DoubleValue reduceRotationPercentage;
   public final BooleanValue smartRotate;
   public final BooleanValue pitchOutliers;
   public final RandomizedRangeSettings pitchOutliersAdd;
   public final RandomizedRangeSettings pitchOutliersDelay;
   public final RangeSetting innerRadius;
   public final DoubleValue bestHitXMinDist;
   public final DoubleValue bestHitYMInDist;
   public final DoubleValue minAbsDist;
   public final DoubleValue maxXAbsDist;
   public final DoubleValue maxYAbsDist;
   public final RangeSetting xRelDist;
   public final RangeSetting yRelDist;
   public final DoubleValue maxRotationAngle;
   public final DoubleValue additionalMinAimspeed;
   public final DoubleValue additionalDynamicSpeed;
   public final DoubleValue additionalAimspeedIfNeeded;
   public final DoubleValue probabilityOfAimingEvenWhenNotNeeded;
   public final BooleanValue test;
   public final DoubleValue aimSpeed;
   public final DoubleValue decelerationfactorPre;
   public final DoubleValue decelerationfactorPost;
   public final RangeSetting reactionTime;
   public final RangeSetting lookaheadTime;
   public final DoubleValue attention;
   public final RangeSetting predictionInaccuracy;
   public final DoubleValue predictionInaccuracySmoothness;
   public final SettingGroup superCorrection;
   public final DoubleValue supercorrectionProbability;
   public final RandomizedRangeSettings supercorrectionDelay;
   public final BooleanValue additionalAccuracy;
   public final SettingGroup rotationOffset;
   public final RangeSetting rotationYawOffset;
   public final RangeSetting rotationPitchOffset;
   public final DoubleValue rotationYawOffsetSpeed;
   public final DoubleValue rotationPitchOffsetSpeed;
   public final DoubleValue additionalInaccuracy;
   public final RangeSetting orthogonalScale;
   public final DoubleValue maxOrthogonalMovement;
   public final DoubleValue maxAimpointAnglediff;
   public final RandomizedRangeSettings bestvecPitchinterpolation;
   public final RangeSetting rotationOnTarget;
   public final BooleanValue noYawAdjustmentIfTargetOutOfReach;
   public final BooleanValue noPitchAdjustmentIfTargetOutOfReach;
   public final BooleanValue applyRecordedMouseJitterMovementIfAvailable;
   public final BooleanValue ultimateTrashcodeBypass;
   public final BooleanValue adaptCurveIfNeeded;
   public final RandomizedRangeSettings noise3AimSpeed;
   public final RandomizedRangeSettings noise3Acceleration;
   public final DoubleValue noise33BetterAcceleration;
   public final DoubleValue noise3Scale;
   public final RangeSetting noise3ResponseTarget;
   public final RangeSetting noise3PlayerResponse;
   private final SettingGroup aiSettingsGroup;
   public final StringValue aiModel;
   private final ActionSetting aiRefreshModels;
   public final BooleanValue smartAim;
   public final BooleanValue smartAimEntityCheck;
   public final BooleanValue advancedRots;
   public final BooleanValue advancedRotsNotAtTarget;
   public final BooleanValue timerRangeV2Fix;
   public final BooleanValue interpolation;
   public final BooleanValue lockView;
   public final BooleanValue stopRotationInsideTarget;
   public final SettingGroup throughWallsGroup;
   public final StringValue throughWallsMode;
   public final DoubleValue throughWallsRange;
   public final BooleanValue aimThroughWalls;
   public final RandomizedRangeSettings aimThroughWallsRange;
   public final BooleanValue noRotation;
   public final SettingGroup clicking;
   public final SettingGroup clickSettings;
   public final ModeSetting clickMode;
   public final DoubleValue cpsLimiter;
   public final BooleanValue cpsLimiterIntave;
   private final SettingGroup interactions;
   public final StringValue attackMode;
   public final DoubleValue maxTargets;
   public final BooleanValue optifineHitbox;
   public final StringValue blockMode;
   public final BooleanValue smartBlock;
   public final BooleanValue interactAutoBlock;
   public final BooleanValue onlyRightClickBlock;
   public final BooleanValue onlyWhileSwingInProgress;
   public final BooleanValue onlyWhileHurt;
   public final DoubleValue unblockAAC;
   public final BooleanValue preHit;
   public final BooleanValue smartPreHit;
   private final RandomizedRangeSettings prediction;
   private final RandomizedRangeSettings loseDelay;
   private final RandomizedRangeSettings endurance;
   private final RandomizedRangeSettings enduranceReduction;
   public final RandomizedRangeSettings preHitRange;
   public final RandomizedRangeSettings preHitFovYaw;
   public final RandomizedRangeSettings preHitFovPitch;
   public final BooleanValue missClick;
   public final DoubleValue missClickPercentage;
   public final BooleanValue perfectHit;
   public final BooleanValue longClick;
   public final BooleanValue critFix;
   public final BooleanValue critFixPacketCheck;
   public final DoubleValue critFixChance;
   public final BooleanValue noLoopHit;
   public final BooleanValue fixBeta;
   private final BooleanValue breakBlocks;
   public final BooleanValue doubleClickFirstHit;
   public final BooleanValue reduceCPSIfYouCanNotHitATargett;
   public final BooleanValue noSwing;
   public final BooleanValue slowDown;
   public final BooleanValue coolDown;
   public final SettingGroup movement;
   private final BooleanValue jumpFix;
   public final BooleanValue moveFix;
   public final StringValue silentMoveFix;
   public final BooleanValue silentMoveFixIntave;
   public final SettingGroup targets;
   public final TargetSelectionSettings targetSelection;
   public final DoubleValue targetDelay;
   public final RandomizedRangeSettings targetLoseDelay;
   public final RandomizedRangeSettings maxDeathTime;
   public final SettingGroup miscellaneous;
   public final BooleanValue closeGuisIfTargetArrives;
   public final BooleanValue inInv;
   public final MultiOptionSetting activeSlots;
   public final WeaponFilterSettings weaponFilter;
   public final LookDownDisableSettings lookDownDisable;
   public final BooleanValue intave13;
   private final RandomizedRangeSettings delayRotationsAfterClosingGuiScreen;
   private final RandomizedRangeSettings delayClicksAfterClosingGuiScreen;
   private final RandomizedRangeSettings delayRotationsAfterRespawn;
   private final RandomizedRangeSettings delayClicksAfterRespawn;
   private final SettingGroup visuals;
   public final BooleanValue dot;
   public final DoubleValue dotSize;
   public final ColorSetting dotColor;
   public final BlurShadowSettings dotShadowSettings;
   private final TimeHelper targetSwitchTimer;
   private final TimeHelper lastAttackTimer;
   private final TimeHelper clickCycleTimer;
   public double currentRange;
   public EntityLivingBase target;
   private Vec3 targetHitVec;
   private long lastHazeHitTime;
   private long lastMouseMoveTime;
   private EntityLivingBase previousTarget;
   private EntityLivingBase lastValidTarget;
   private int intaveRotationFlags;
   private OnnxModelRegistry aiModelRegistry;
   private String loadedAiModelName;
   public final CopyOnWriteArrayList rotationTrailPoints;
   private int ticksSinceBlockAttack;
   private final TimeHelper lastSwingPacketTimer;
   private final TimeHelper doubleClickFirstHitTimer;
   public final ClickSpeedMonitor clickSpeedMonitor;
   private Rotation currentRotation;
   private int[] lockViewMouseDelta;
   private long lastTickTimestamp;
   private int lastTickDurationMs;
   private boolean firstHitReady;
   private MovingObjectPosition lastObjectMouseOver;
   private boolean holdingLeftClick;
   public boolean itemUseCleared;
   public int ticksSinceBugBlockSwitch;
   public boolean grimRangeActive;
   private List lastTickTargets;
   private String activeAttackMode;
   private boolean grimAttackPending;
   private boolean pendingLongClickRelease;
   private int intervalActionCount;
   private int betaFixHitCount;
   private boolean attackedThisTick;
   private Vec3 previousHitVec;
   private final TimeHelper targetLoseTimer;
   private final TickCounter tickCounter;
   private int longClickBlockDelay;
   private boolean useExtendedDualRange;
   private final ActionRateAccumulator actionRateAccumulator;
   private BoundedHistoryList debugPosHistory;
   private int ticksSinceTeleport;
   private final HysteresisRangeTracker preHitRangeTracker;
   private final HysteresisRangeTracker preAimRangeTracker;
   private final HysteresisRangeTracker aimThroughWallsRangeTracker;
   private final HysteresisRangeTracker preHitFovYawTracker;
   private final HysteresisRangeTracker preHitFovPitchTracker;
   private boolean needsClickPatternReset;
   private int consecutiveHits;
   private int[] appliedMouseDelta;
   private double[] fractionalMouseDelta;
   private boolean critLanded;
   private int preHitEnduranceUsed;
   private boolean preHitEnduranceExhausted;

   public KillAura() {
      super("KillAura", "AutomatischerTöter", new Color(102, 38, 28, 255), Categorys.COMBAT);
      this.rotationUtil = new RotationUtil();
      this.range = new SettingGroup(this, "Range", true);
      this.preAimRange = (new RandomizedRangeSettings(this, "PreAimRange", 6.0, 6.0, 0.0, 15.0, 1, this.range)).withoutNotInARow();
      this.range2 = new DoubleValue(this, "Range", 3.0, 3.0, 6.0, 3, this.range);
      this.rangeMode = new StringValue(this, "RangeMode", "Simple", new String[]{"Simple", "HazeRange", "Grim", "IntervalRange", "Dual"}, this.range);
      DoubleValue hazeAddSetting = new DoubleValue(this, "HazeAdd", 0.5, 0.0, 1.0, 2, this.range);
      SettingVisibilityCondition condition = () -> {
         return this.rangeMode.getSelectedValue().equals("HazeRange");
      };
      this.hazeAdd = hazeAddSetting.visibleWhen(condition);
      DoubleValue hazeMaxSetting = new DoubleValue(this, "HazeMax", 4.5, 3.0, 6.0, 2, this.range);
      this.hazeMax = hazeMaxSetting.visibleWhen(() -> this.isHazeMaxVisible());
      this.actionToTrackInterval = (StringValue)new StringValue(this, "ActionToTrackInterval", "Attacks", new String[]{"Attacks", "Swings"}, this.range).withVisibility(() -> this.isActionToTrackIntervalVisible());
      hazeMaxSetting = new DoubleValue(this, "Interval", 2.0, 1.0, 10.0, 0, this.range);
      this.interval = hazeMaxSetting.visibleWhen(() -> this.isIntervalVisible());
      hazeMaxSetting = new DoubleValue(this, "RangeAtInterval", 3.0, 3.0, 6.0, 3, this.range);
      SettingVisibilityCondition condition5 = () -> {
         return this.rangeMode.getSelectedValue().equals("IntervalRange");
      };
      this.rangeAtInterval = hazeMaxSetting.visibleWhen(condition5);
      hazeMaxSetting = new DoubleValue(this, "MinReachToIntervalHit", 4.0, 3.0, 6.0, 3, this.range);
      this.minReachToIntervalHit = hazeMaxSetting.visibleWhen(() -> this.isMinReachToIntervalHitVisible());
      hazeMaxSetting = new DoubleValue(this, "HurtTimeToHitWithReach", 2.0, 0.0, 9.0, 0, this.range);
      this.hurtTimeToHitWithReach = hazeMaxSetting.visibleWhen(() -> this.isHurtTimeToHitWithReachVisible());
      RangeSetting rangeSetting = new RangeSetting(this, "BaseRange", "ExtendedRange", 3.0, 3.7, 3.0, 6.0, 2, this.range);
      SettingVisibilityCondition condition8 = () -> {
         return this.rangeMode.getSelectedValue().equals("Dual");
      };
      this.baseRange = rangeSetting.visibleWhen(condition8);
      hazeMaxSetting = new DoubleValue(this, "Chance of Extended Range", 50.0, 0.0, 100.0, 0, this.range);
      SettingVisibilityCondition condition9 = () -> {
         return this.rangeMode.getSelectedValue().equals("Dual");
      };
      this.chanceOfExtendedRange = hazeMaxSetting.visibleWhen(condition9);
      this.rotations = new SettingGroup(this, "Rotations", true);
      this.yawFov = (DoubleValue)(new DoubleValue(this, "YawFov", 180.0, 0.0, 180.0, 0, this.rotations)).markVisualOnly();
      this.pitchFov = (DoubleValue)(new DoubleValue(this, "PitchFov", 180.0, 0.0, 180.0, 0, this.rotations)).markVisualOnly();
      this.yawSpeed = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "YawSpeed", 30.0, 50.0, 1.0, 180.0, 0, this.rotations)).withoutNotInARow().withVisibility(() -> this.isYawSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this, "PitchSpeed", 30.0, 50.0, 1.0, 180.0, 0, this.rotations)).withoutNotInARow();
      SettingVisibilityCondition condition11 = () -> {
         return !this.randomizeMode.getSelectedValue().contains("Noise") && !this.randomizeMode.getSelectedValue().equals("Turbo") && !this.randomizeMode.getSelectedValue().equals("AI");
      };
      this.pitchSpeed = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(condition11);
      this.yawAcceleration = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "YawAcceleration", 15.0, 20.0, 1.0, 180.0, 1, this.rotations)).withoutNotInARow().withVisibility(() -> this.isYawAccelerationVisible());
      this.pitchAcceleration = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "PitchAcceleration", 15.0, 20.0, 1.0, 180.0, 1, this.rotations)).withoutNotInARow().withVisibility(() -> this.isPitchAccelerationVisible());
      BooleanValue booleanValue = new BooleanValue(this, "BetterAcceleration", false, this.rotations);
      SettingVisibilityCondition condition14 = () -> {
         return !this.isInterpolationEnabled()
            && !this.randomizeMode.getSelectedValue().contains("Noise")
            && !this.randomizeMode.getSelectedValue().equals("Turbo")
            && !this.randomizeMode.getSelectedValue().equals("AI");
      };
      this.betterAcceleration = (BooleanValue)booleanValue.withVisibility(condition14);
      this.fixRotationGroup = (SettingGroup)new SettingGroup(this, "FixRotationGroup", true, this.rotations).withDisplayName("FixRotations");
      this.fixRotationMode = new StringValue(this, "FixRotationMode", "None", new String[]{"None", "OnlyWhenNeed", "Always"}, this.fixRotationGroup);
      this.fixRotationChance = new DoubleValue(this, "FixRotationChance", 100.0, 1.0, 100.0, 0, this.fixRotationGroup).visibleWhen(() -> this.isFixRotationChanceVisible());
      this.maxFixRotationReach = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MaxFixRotationReach", 2.96, 2.98, 2.5, 3.0, 3, this.fixRotationGroup)).withoutNotInARow().withVisibility(() -> this.isMaxFixRotationReachVisible());
      this.minFixRotationAngle = new DoubleValue(this, "MinFixRotationAngle", 90.0, 0.0, 180.0, 0, this.fixRotationGroup).visibleWhen(() -> this.isMinFixRotationAngleVisible());
      this.minDistanceToBestVecCalculation = (StringValue)new StringValue(
         this, "MinDistanceToBestVecCalculation", "None", new String[]{"None", "Separate", "Combined"}, this.fixRotationGroup
      ).withVisibility(() -> this.isMinDistanceCalculationVisible());
      this.minDistanceToBestVecYaw = new DoubleValue(this, "MinDistanceToBestVecYaw", 2.5, 0.0, 10.0, 2, this.fixRotationGroup).visibleWhen(() -> this.isMinDistanceYawVisible());
      this.minDistanceToBestVecPitch = new DoubleValue(this, "MinDistanceToBestVecPitch", 2.5, 0.0, 10.0, 2, this.fixRotationGroup).visibleWhen(() -> this.isMinDistancePitchVisible());
      this.minDistanceToBestVec = new DoubleValue(this, "MinDistanceToBestVec", 2.5, 0.0, 10.0, 2, this.fixRotationGroup).visibleWhen(() -> this.isMinDistanceCombinedVisible());
      this.heuristics = new StringValue(this, "Heuristics", "None", new String[]{"None", "Pattern1", "IntaveTest"}, this.rotations);
      this.rotationMode = new StringValue(this, "RotationMode", "BestHitVec", new String[]{"Head", "BestHitVec", "NearestHitVec"}, this.rotations);
      this.hitBoxPercentageVertical = new DoubleValue(this, "HitBoxPercentageVertical", 100.0, 1.0, 100.0, 0, this.rotations).visibleWhen(() -> this.isBestHitVecVerticalVisible());
      DoubleValue hitBoxPercentageHorizontal2 = new DoubleValue(this, "HitBoxPercentageHorizontal", 100.0, 1.0, 100.0, 0, this.rotations);
      SettingVisibilityCondition condition23 = () -> {
         return this.rotationMode.getSelectedValue().equalsIgnoreCase("BestHitVec");
      };
      this.hitBoxPercentageHorizontal = hitBoxPercentageHorizontal2.visibleWhen(condition23);
      this.hitBoxPercentageVertical1 = new DoubleValue(this, "HitBoxPercentageVertical1", 100.0, 1.0, 100.0, 0, this.rotations).named("HitBoxPercentageVertical").visibleWhen(() -> this.isNearestHitVecVerticalVisible());
      this.hitBoxPercentageHorizontal1 = new DoubleValue(this, "HitBoxPercentageHorizontal1", 100.0, 1.0, 100.0, 0, this.rotations).named("HitBoxPercentageHorizontal").visibleWhen(() -> this.isNearestHitVecHorizontalVisible());
      this.randomize = new SettingGroup(this, "Randomize", true, this.rotations);
      this.randomType = new StringValue(this, "RandomType", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"}, this.randomize);
      String[] strings = !Augustus.getInstance().getUsername().equals("7yns")
            && !Augustus.getInstance().getUsername().equals("xaelo")
            && !Augustus.getInstance().getUsername().equals("goatqjudoo")
         ? new String[]{
            "None", "Basic", "Doubled", "OnlyRotation", "Hybrid", "PolarTest", "Circle", "Advanced", "Noise", "Turbo", "Noise2", "Noise3", "LegitTest"
         }
         : new String[]{
            "None", "Basic", "Doubled", "OnlyRotation", "Hybrid", "PolarTest", "Circle", "Advanced", "Noise", "Turbo", "Noise2", "Noise3", "LegitTest", "AI"
         };
      SettingGroup randomize2 = this.randomize;
      String str4 = "OnlyRotation";
      String str5 = "Randomize";
      this.randomizeMode = new StringValue(this, str5, str4, strings, randomize2);
      DoubleValue randomStrengthSetting = new DoubleValue(this, "RandomStrength", 1.25, 0.01, 5.0, 2, this.randomize);
      SettingVisibilityCondition condition26 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Basic")
            || this.randomizeMode.getSelectedValue().equals("Doubled")
            || this.randomizeMode.getSelectedValue().equals("OnlyRotation")
            || this.randomizeMode.getSelectedValue().equals("Hybrid");
      };
      this.randomStrength = randomStrengthSetting.visibleWhen(condition26);
      BooleanValue booleanValue2 = new BooleanValue(this, "ReducedRotate", false, this.randomize);
      SettingVisibilityCondition condition27 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced");
      };
      this.reducedRotate = (BooleanValue)booleanValue2.withVisibility(condition27);
      DoubleValue reduceRotationPercentage2 = new DoubleValue(this, "ReduceRotationPercentage", 100.0, 0.0, 100.0, 0, this.randomize);
      SettingVisibilityCondition condition28 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced") && this.reducedRotate.getValue();
      };
      this.reduceRotationPercentage = reduceRotationPercentage2.visibleWhen(condition28);
      this.smartRotate = (BooleanValue)new BooleanValue(this, "SmartRotate", false, this.randomize).withVisibility(() -> this.isSmartRotateVisible());
      this.pitchOutliers = (BooleanValue)new BooleanValue(this, "PitchOutliers", false, this.randomize).withVisibility(() -> this.isPitchOutliersVisible());
      this.pitchOutliersAdd = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "pitchOutliersAdd", 4.0, 10.0, 0.0, 20.0, 2, this.randomize).withVisibility(() -> this.isPitchOutliersAddVisible());
      RandomizedRangeSettings randomizedRangeSettings7 = (new RandomizedRangeSettings(this, "PitchOutliersDelay", 15.0, 20.0, 1.0, 40.0, 0, this.randomize)).withoutNotInARow();
      SettingVisibilityCondition condition32 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced") && this.pitchOutliers.getValue();
      };
      this.pitchOutliersDelay = (RandomizedRangeSettings)randomizedRangeSettings7.withVisibility(condition32);
      RangeSetting rangeSetting2 = new RangeSetting(this, "InnerRadius", "Radius", 4.0, 9.0, 0.0, 20.0, 2, this.randomize);
      SettingVisibilityCondition condition33 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced");
      };
      this.innerRadius = rangeSetting2.visibleWhen(condition33);
      DoubleValue bestHitXMinDistSetting = new DoubleValue(this, "BestHitXMinDist", 2.0, 0.0, 10.0, 2, this.randomize);
      SettingVisibilityCondition condition34 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced");
      };
      this.bestHitXMinDist = bestHitXMinDistSetting.visibleWhen(condition34);
      DoubleValue bestHitYMInDistSetting = new DoubleValue(this, "BestHitYMInDist", 2.0, 0.0, 10.0, 2, this.randomize);
      SettingVisibilityCondition condition35 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced");
      };
      this.bestHitYMInDist = bestHitYMInDistSetting.visibleWhen(condition35);
      this.minAbsDist = new DoubleValue(this, "MinAbsDist", 0.5, 0.0, 10.0, 2, this.randomize).visibleWhen(() -> this.isMinAbsDistVisible());
      this.maxXAbsDist = new DoubleValue(this, "MaxXAbsDist", 5.0, 0.0, 20.0, 2, this.randomize).visibleWhen(() -> this.isMaxXAbsDistVisible());
      this.maxYAbsDist = new DoubleValue(this, "MaxYAbsDist", 20.0, 0.0, 20.0, 2, this.randomize).visibleWhen(() -> this.isMaxYAbsDistVisible());
      this.xRelDist = new RangeSetting(this, "XRelDist", 0.5, 10.0, 0.0, 20.0, 2, this.randomize).visibleWhen(() -> this.isXRelDistVisible());
      this.yRelDist = new RangeSetting(this, "YRelDist", 0.5, 10.0, 0.0, 20.0, 2, this.randomize).visibleWhen(() -> this.isYRelDistVisible());
      DoubleValue maxRotationAngleSetting = new DoubleValue(this, "MaxRotationAngle", 90.0, 0.0, 180.0, 0, this.randomize);
      SettingVisibilityCondition condition41 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Advanced");
      };
      this.maxRotationAngle = maxRotationAngleSetting.visibleWhen(condition41);
      this.additionalMinAimspeed = new DoubleValue(this, "Additional min Aimspeed", 100.0, 0.0, 100.0, 0, this.randomize).visibleWhen(() -> this.isAdditionalMinAimSpeedVisible());
      DoubleValue additionalDynamicSpeed2 = new DoubleValue(this, "Additional dynamic Speed", 100.0, 0.0, 100.0, 0, this.randomize);
      SettingVisibilityCondition condition43 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise");
      };
      this.additionalDynamicSpeed = additionalDynamicSpeed2.visibleWhen(condition43);
      this.additionalAimspeedIfNeeded = new DoubleValue(this, "Additional Aimspeed if needed", 100.0, 0.0, 100.0, 0, this.randomize).visibleWhen(() -> this.isAdditionalAimSpeedIfNeededVisible());
      this.probabilityOfAimingEvenWhenNotNeeded = new DoubleValue(this, "Probability of aiming even when not needed", 100.0, 0.0, 100.0, 0, this.randomize).visibleWhen(() -> this.isAimProbabilityWhenNotNeededVisible());
      this.test = (BooleanValue)new BooleanValue(this, "Test", false, this.randomize).withVisibility(() -> this.isNoiseTestVisible());
      this.aimSpeed = new DoubleValue(this, "AimSpeed", 75.0, 0.0, 100.0, 0, this.randomize).visibleWhen(() -> this.isNoise2AimSpeedVisible());
      this.decelerationfactorPre = new DoubleValue(this, "Decelerationfactor Pre", 12.5, 0.0, 100.0, 1, this.randomize).visibleWhen(() -> this.isDecelerationPreVisible());
      this.decelerationfactorPost = new DoubleValue(this, "Decelerationfactor Post", 0.0, 0.0, 100.0, 1, this.randomize).visibleWhen(() -> this.isDecelerationPostVisible());
      this.reactionTime = new RangeSetting(this, "Reaction Time", 2.0, 4.0, 0.0, 6.0, 0, this.randomize).named(" Reaction Time").visibleWhen(() -> this.isReactionTimeVisible());
      this.lookaheadTime = new RangeSetting(this, "Lookahead Time", 2.0, 3.0, 0.0, 10.0, 0, this.randomize).named(" Lookahead Time").visibleWhen(() -> this.isLookaheadTimeVisible());
      this.attention = new DoubleValue(this, "Attention", 3.0, 2.0, 4.0, 0, this.randomize).visibleWhen(() -> this.isAttentionVisible());
      this.predictionInaccuracy = new RangeSetting(this, "Prediction Inaccuracy", 0.0, 0.0, -100.0, 100.0, 0, this.randomize).named(" Prediction Inaccuracy").visibleWhen(() -> this.isPredictionInaccuracyVisible());
      this.predictionInaccuracySmoothness = new DoubleValue(this, "Prediction Inaccuracy Smoothness", 0.0, 0.0, 100.0, 0, this.randomize).visibleWhen(() -> this.isPredictionInaccuracySmoothnessVisible());
      this.superCorrection = new SettingGroup(this, "SuperCorrection", this.randomize).visibleWhen(() -> this.isSuperCorrectionGroupVisible());
      this.supercorrectionProbability = new DoubleValue(this, "SuperCorrection Probability", 100.0, 0.0, 100.0, 0, this.superCorrection).visibleWhen(() -> this.isSuperCorrectionProbabilityVisible());
      this.supercorrectionDelay = ((RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "SuperCorrection Delay", 0.0, 0.0, 0.0, 5.0, 0, this.superCorrection).withDisplayNamePrefix(" SuperCorrection Delay")).withVisibility(() -> this.isSuperCorrectionDelayVisible())).withoutNotInARow();
      BooleanValue booleanValue6 = new BooleanValue(this, "Additional Accuracy", false, this.superCorrection);
      SettingVisibilityCondition condition58 = () -> {
         if (this.randomizeMode.getSelectedValue().equals("Noise2")) {
            if (this.supercorrectionProbability.getValue(true) > 0.0) {
               return true;
            }
         }

         return false;
      };
      this.additionalAccuracy = (BooleanValue)booleanValue6.withVisibility(condition58);
      this.rotationOffset = new SettingGroup(this, "Rotation Offset", this.randomize).visibleWhen(() -> this.isRotationOffsetGroupVisible());
      this.rotationYawOffset = new RangeSetting(this, "Rotation Yaw Offset", 0.0, 0.0, -30.0, 30.0, 1, this.rotationOffset).named(" Rotation Yaw Offset").visibleWhen(() -> this.isRotationYawOffsetVisible());
      this.rotationPitchOffset = new RangeSetting(this, "Rotation Pitch Offset", 0.0, 0.0, -30.0, 30.0, 1, this.rotationOffset).named(" Rotation Pitch Offset").visibleWhen(() -> this.isRotationPitchOffsetVisible());
      this.rotationYawOffsetSpeed = new DoubleValue(this, "Rotation Yaw Offset Speed", 0.0, 0.0, 100.0, 1, this.rotationOffset).visibleWhen(() -> this.isRotationYawOffsetSpeedVisible());
      this.rotationPitchOffsetSpeed = new DoubleValue(this, "Rotation Pitch Offset Speed", 0.0, 0.0, 100.0, 1, this.rotationOffset).visibleWhen(() -> this.isRotationPitchOffsetSpeedVisible());
      this.additionalInaccuracy = new DoubleValue(this, "Additional inaccuracy", 0.0, 0.0, 100.0, 0, this.randomize).visibleWhen(() -> this.isAdditionalInaccuracyVisible());
      RangeSetting rangeSetting16 = new RangeSetting(this, "Orthogonal Scale", 0.0, 30.0, 0.0, 100.0, 0, this.randomize).named(" Orthogonal Scale");
      SettingVisibilityCondition condition65 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise2");
      };
      this.orthogonalScale = rangeSetting16.visibleWhen(condition65);
      this.maxOrthogonalMovement = new DoubleValue(this, "Max Orthogonal Movement", 10.0, 0.0, 90.0, 1, this.randomize).visibleWhen(() -> this.isMaxOrthogonalMovementVisible());
      this.maxAimpointAnglediff = new DoubleValue(this, "Max Aimpoint Anglediff", 60.0, 1.0, 180.0, 0, this.randomize).visibleWhen(() -> this.isMaxAimpointAngleDiffVisible());
      RandomizedRangeSettings randomizedRangeSettings10 = (new RandomizedRangeSettings(this, "Bestvec Pitchinterpolation", 0.0, 0.0, 0.0, 100.0, 0, this.randomize)).withoutNotInARow();
      SettingVisibilityCondition condition68 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise2");
      };
      this.bestvecPitchinterpolation = (RandomizedRangeSettings)randomizedRangeSettings10.withVisibility(condition68);
      RangeSetting rangeSetting18 = new RangeSetting(this, "Rotation on Target", 100.0, 100.0, 0.0, 100.0, 0, this.randomize).named(" Rotation on Target");
      SettingVisibilityCondition condition69 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise2");
      };
      this.rotationOnTarget = rangeSetting18.visibleWhen(condition69);
      this.noYawAdjustmentIfTargetOutOfReach = (BooleanValue)new BooleanValue(this, "No yaw adjustment if target out of reach", true, this.randomize).withVisibility(() -> this.isNoYawOutOfReachVisible());
      BooleanValue booleanValue8 = new BooleanValue(this, "No pitch adjustment if target out of reach", true, this.randomize);
      SettingVisibilityCondition condition71 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise2");
      };
      this.noPitchAdjustmentIfTargetOutOfReach = (BooleanValue)booleanValue8.withVisibility(condition71);
      BooleanValue booleanValue9 = new BooleanValue(this, "Apply recorded mouse jitter movement if available", true, this.randomize);
      SettingVisibilityCondition condition72 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise2");
      };
      this.applyRecordedMouseJitterMovementIfAvailable = (BooleanValue)booleanValue9.withVisibility(condition72);
      this.ultimateTrashcodeBypass = (BooleanValue)new BooleanValue(this, "Ultimate Trashcode Bypass", true, this.randomize).withVisibility(() -> this.isTrashcodeBypassVisible());
      BooleanValue booleanValue11 = new BooleanValue(this, "Adapt Curve if needed", false, this.randomize);
      SettingVisibilityCondition condition74 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise2");
      };
      this.adaptCurveIfNeeded = (BooleanValue)booleanValue11.withVisibility(condition74);
      this.noise3AimSpeed = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "Noise3AimSpeed", 20.0, 30.0, 0.0, 180.0, 0, this.randomize).withDisplayNamePrefix("AimSpeed")).withoutNotInARow().withVisibility(() -> this.isTurboAimSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings14 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "Noise3Acceleration", 2.0, 4.0, 2.0, 20.0, 2, this.randomize).withDisplayNamePrefix("Acceleration")).withoutNotInARow();
      SettingVisibilityCondition condition76 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Turbo");
      };
      this.noise3Acceleration = (RandomizedRangeSettings)randomizedRangeSettings14.withVisibility(condition76);
      DoubleValue noise33BetterAcceleration2 = new DoubleValue(this, "Noise33BetterAcceleration", 0.0, 0.0, 1.0, 2, this.randomize).named("BetterAcceleration");
      SettingVisibilityCondition condition77 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Turbo");
      };
      this.noise33BetterAcceleration = noise33BetterAcceleration2.visibleWhen(condition77);
      DoubleValue noise3ScaleSetting = new DoubleValue(this, "Noise3Scale", 1.5, 1.0, 7.0, 2, this.randomize).named("Scale");
      SettingVisibilityCondition condition78 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Turbo");
      };
      this.noise3Scale = noise3ScaleSetting.visibleWhen(condition78);
      RangeSetting rangeSetting20 = new RangeSetting(this, "Noise3ResponseTarget", 3.0, 5.0, 1.0, 10.0, 0, this.randomize).named("TargetResponse");
      SettingVisibilityCondition condition79 = () -> {
         return this.randomizeMode.getSelectedValue().equals("Turbo");
      };
      this.noise3ResponseTarget = rangeSetting20.visibleWhen(condition79);
      this.noise3PlayerResponse = new RangeSetting(this, "Noise3PlayerResponse", 3.0, 5.0, 1.0, 10.0, 0, this.randomize).named("PlayerResponse").visibleWhen(() -> this.isTurboPlayerResponseVisible());
      this.aiSettingsGroup = ((SettingGroup)new SettingGroup(this, "AISettingsGroup", this.randomize).withDisplayName("AI Settings")).visibleWhen(() -> this.isAiSettingsGroupVisible());
      this.aiModel = (StringValue)new StringValue(this, "AIModel", "None", new String[]{"None"}, this.aiSettingsGroup).withDisplayName("Model");
      this.aiRefreshModels = (ActionSetting)new ActionSetting(this, "AIRefreshModels", () -> this.refreshAiModels(), this.aiSettingsGroup).withDisplayName("Refresh Models");
      this.smartAim = new BooleanValue(this, "SmartAim", true, this.rotations);
      BooleanValue booleanValue12 = new BooleanValue(this, "SmartAimEntityCheck", true, this.rotations);
      BooleanValue smartAim2 = this.smartAim;
      this.smartAimEntityCheck = (BooleanValue)booleanValue12.withVisibility(() -> smartAim2.getValue());
      this.advancedRots = new BooleanValue(this, "AdvancedRots", true, this.rotations);
      BooleanValue booleanValue14 = new BooleanValue(this, "AdvancedRotsNotAtTarget", false, this.rotations);
      BooleanValue advancedRots2 = this.advancedRots;
      this.advancedRotsNotAtTarget = (BooleanValue)booleanValue14.withVisibility(() -> advancedRots2.getValue());
      this.timerRangeV2Fix = new BooleanValue(this, "TimerRangeV2Fix", false, this.rotations);
      this.interpolation = new BooleanValue(this, "Interpolation", false, this.rotations);
      this.lockView = new BooleanValue(this, "LockView", false, this.rotations);
      this.stopRotationInsideTarget = new BooleanValue(this, "StopRotationInsideTarget", false, this.rotations);
      this.throughWallsGroup = (SettingGroup)((SettingGroup)new SettingGroup(this, "ThroughWallsGroup", true, this.rotations).withDisplayName("ThroughWalls")).markVisualOnly();
      this.throughWallsMode = (StringValue)(new StringValue(
            this, "ThroughWallsMode", "None", new String[]{"None", "NoneFullBlocks", "AllBlocks"}, this.throughWallsGroup
         ))
         .markVisualOnly();
      this.throughWallsRange = (DoubleValue)new DoubleValue(this, "ThroughWallsRange", 3.0, 0.0, 3.0, 3, this.throughWallsGroup).visibleWhen(() -> this.isThroughWallsRangeVisible()).markVisualOnly();
      BooleanValue booleanValue16 = new BooleanValue(this, "AimThroughWalls", false, this.throughWallsGroup);
      SettingVisibilityCondition condition85 = () -> {
         return !this.throughWallsMode.getSelectedValue().equals("AllBlocks");
      };
      this.aimThroughWalls = (BooleanValue)((BooleanValue)booleanValue16.withVisibility(condition85)).markVisualOnly();
      this.aimThroughWallsRange = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "AimThroughWallsRange", 6.0, 6.0, 0.0, 6.0, 3, this.throughWallsGroup)).withoutNotInARow().withVisibility(() -> this.isAimThroughWallsRangeVisible())).markAllVisualOnly();
      this.noRotation = new BooleanValue(this, "NoRotation", false, this.rotations);
      this.clicking = new SettingGroup(this, "Clicking", true);
      this.clickSettings = new SettingGroup(this, "ClickSettings", true, this.clicking);
      this.clickMode = new ModeSetting(
         this,
         "ClickMode",
         "Advanced",
         this.clickSettings,
         new BiFunction[]{
            (obj, obj2) -> new DeviationClickPatternMode((Module)obj, (SettingGroup)obj2),
            (obj3, obj4) -> new SimpleClickPatternMode((Module)obj3, (SettingGroup)obj4),
            (obj5, obj6) -> new SimpleV2ClickPatternMode((Module)obj5, (SettingGroup)obj6),
            (obj7, obj8) -> new AdvancedClickPatternMode((Module)obj7, (SettingGroup)obj8),
            (obj9, obj10) -> new RecordedClickPatternMode((Module)obj9, (SettingGroup)obj10),
            (obj11, obj12) -> new SmartClickPatternMode((Module)obj11, (SettingGroup)obj12),
            (obj13, obj14) -> new DragClickPatternMode((Module)obj13, (SettingGroup)obj14),
            (obj15, obj16) -> new DistributionClickPatternMode((Module)obj15, (SettingGroup)obj16)
         }
      );
      this.cpsLimiter = new DoubleValue(this, "CPSLimiter", 15.0, 1.0, 40.0, 0, this.clickSettings);
      this.cpsLimiterIntave = new BooleanValue(this, "CPSLimiterIntave", true, this.clickSettings);
      this.interactions = new SettingGroup(this, "Interactions", true, this.clicking);
      this.attackMode = new StringValue(this, "AttackMode", "Legit", new String[]{"Legit", "Packet", "Multi"}, this.interactions);
      this.maxTargets = new DoubleValue(this, "MaxTargets", 10.0, 1.0, 20.0, 0, this.interactions).visibleWhen(() -> this.isMaxTargetsVisible());
      this.optifineHitbox = new BooleanValue(this, "OptifineHitbox", false, this.interactions);
      this.blockMode = new StringValue(
         this,
         "BlockMode",
         "None",
         new String[]{
            "None",
            "Constant",
            "Constant2",
            "Pre",
            "Post",
            "UnblockOnHit",
            "UnblockOnHit2",
            "Spam",
            "SlowSpam",
            "OldHypixel",
            "AAC",
            "AACOld",
            "Legit",
            "Bug",
            "Test"
         },
         this.interactions
      );
      this.smartBlock = (BooleanValue)new BooleanValue(this, "SmartBlock", true, this.interactions).withVisibility(() -> this.isSmartBlockVisible());
      this.interactAutoBlock = (BooleanValue)new BooleanValue(this, "InteractAutoBlock", true, this.interactions).withVisibility(() -> this.isInteractAutoBlockVisible());
      BooleanValue booleanValue19 = new BooleanValue(this, "OnlyRightClickBlock", false, this.interactions);
      SettingVisibilityCondition condition90 = () -> {
         return !this.blockMode.getSelectedValue().equals("None");
      };
      this.onlyRightClickBlock = (BooleanValue)booleanValue19.withVisibility(condition90);
      BooleanValue booleanValue20 = new BooleanValue(this, "OnlyWhileSwingInProgress", false, this.interactions);
      SettingVisibilityCondition condition91 = () -> {
         return !this.blockMode.getSelectedValue().equals("None");
      };
      this.onlyWhileSwingInProgress = (BooleanValue)booleanValue20.withVisibility(condition91);
      this.onlyWhileHurt = (BooleanValue)new BooleanValue(this, "OnlyWhileHurt", false, this.interactions).withVisibility(() -> this.isOnlyWhileHurtVisible());
      this.unblockAAC = new DoubleValue(this, "UnblockAAC", 4.0, 0.0, 10.0, 0, this.interactions).visibleWhen(() -> this.isUnblockAacVisible());
      this.preHit = new BooleanValue(this, "PreHit", true, this.clicking);
      BooleanValue booleanValue22 = new BooleanValue(this, "SmartPreHit", false, this.clicking);
      BooleanValue preHit2 = this.preHit;
      this.smartPreHit = (BooleanValue)booleanValue22.withVisibility(() -> preHit2.getValue());
      this.prediction = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "Prediction", 3.0, 5.0, 1.0, 10.0, 0, this.clicking)).withoutNotInARow().withVisibility(() -> this.isPreHitPredictionVisible());
      this.loseDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "LoseDelay", 3.0, 5.0, 1.0, 10.0, 0, this.clicking)).withoutNotInARow().withVisibility(() -> this.isPreHitLoseDelayVisible());
      this.endurance = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "Endurance", 90.0, 110.0, 1.0, 400.0, 0, this.clicking)).withoutNotInARow().withVisibility(() -> this.isPreHitEnduranceVisible());
      RandomizedRangeSettings randomizedRangeSettings19 = (new RandomizedRangeSettings(this, "EnduranceReduction", 5.0, 10.0, 1.0, 20.0, 0, this.clicking)).withoutNotInARow();
      SettingVisibilityCondition condition98 = () -> {
         return this.smartPreHit.getValue() && this.smartPreHit.isVisible();
      };
      this.enduranceReduction = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(condition98);
      RandomizedRangeSettings randomizedRangeSettings20 = (new RandomizedRangeSettings(this, "PreHitRange", 6.0, 6.0, 3.0, 8.0, 2, this.clicking)).withoutNotInARow();
      BooleanValue preHit3 = this.preHit;
      this.preHitRange = (RandomizedRangeSettings)randomizedRangeSettings20.withVisibility(() -> preHit3.getValue());
      RandomizedRangeSettings randomizedRangeSettings21 = (new RandomizedRangeSettings(this, "PreHitFovYaw", 50.0, 60.0, 0.0, 180.0, 0, this.clicking)).withoutNotInARow();
      BooleanValue preHit4 = this.preHit;
      this.preHitFovYaw = (RandomizedRangeSettings)randomizedRangeSettings21.withVisibility(() -> preHit4.getValue());
      RandomizedRangeSettings randomizedRangeSettings22 = (new RandomizedRangeSettings(this, "PreHitFovPitch", 50.0, 60.0, 0.0, 180.0, 0, this.clicking)).withoutNotInARow();
      BooleanValue preHit5 = this.preHit;
      this.preHitFovPitch = (RandomizedRangeSettings)randomizedRangeSettings22.withVisibility(() -> preHit5.getValue());
      this.missClick = new BooleanValue(this, "MissClick", false, this.clicking);
      DoubleValue missClickPercentageSetting = new DoubleValue(this, "MissClickPercentage", 5.0, 0.0, 100.0, 0, this.clicking);
      BooleanValue missClick2 = this.missClick;
      this.missClickPercentage = missClickPercentageSetting.visibleWhen(() -> missClick2.getValue());
      this.perfectHit = new BooleanValue(this, "PerfectHit", true, this.clicking);
      BooleanValue booleanValue28 = new BooleanValue(this, "LongClick", false, this.clicking);
      SettingVisibilityCondition condition103 = () -> {
         return !this.supportsHoldClicking();
      };
      this.longClick = (BooleanValue)booleanValue28.withVisibility(condition103);
      this.critFix = new BooleanValue(this, "CritFix", true, this.clicking);
      BooleanValue booleanValue29 = new BooleanValue(this, "CritFixPacketCheck", true, this.clicking);
      BooleanValue critFix2 = this.critFix;
      this.critFixPacketCheck = (BooleanValue)booleanValue29.withVisibility(() -> critFix2.getValue());
      DoubleValue critFixChanceSetting = new DoubleValue(this, "CritFixChance", 100.0, 0.0, 100.0, 1, this.clicking);
      BooleanValue critFix3 = this.critFix;
      this.critFixChance = critFixChanceSetting.visibleWhen(() -> critFix3.getValue());
      this.noLoopHit = new BooleanValue(this, "NoLoopHit", true, this.clicking);
      this.fixBeta = new BooleanValue(this, "Fix (Beta)", false, this.clicking);
      this.breakBlocks = new BooleanValue(this, "BreakBlocks", false, this.clicking);
      this.doubleClickFirstHit = (BooleanValue)new BooleanValue(this, "DoubleClickFirstHit", true, this.clicking).withVisibility(() -> this.isDoubleClickFirstHitVisible());
      BooleanValue booleanValue34 = (BooleanValue)new BooleanValue(this, "ReduceCPSIfYouCanNotHitATargett", false, this.clicking).withDisplayName("ReduceCPSIfYouCanNotHitATarget");
      SettingVisibilityCondition condition107 = () -> {
         return this.clickMode.getSelectedValue().equals("Advanced")
            || this.clickMode.getSelectedValue().equals("Smart")
            || this.clickMode.getSelectedValue().equals("Recorded")
            || this.clickMode.getSelectedValue().equals("Deviation")
            || this.clickMode.getSelectedValue().equals("Distribution");
      };
      this.reduceCPSIfYouCanNotHitATargett = (BooleanValue)booleanValue34.withVisibility(condition107);
      this.noSwing = new BooleanValue(this, "NoSwing", false, this.clicking);
      this.slowDown = new BooleanValue(this, "SlowDown", true, this.clicking);
      BooleanValue booleanValue35 = new BooleanValue(this, "CoolDown", false, this.clicking);
      SettingVisibilityCondition condition108 = () -> {
         return !this.attackMode.getSelectedValue().equals("Multi");
      };
      this.coolDown = (BooleanValue)booleanValue35.withVisibility(condition108);
      this.movement = (SettingGroup)(new SettingGroup(this, "Movement", true)).markVisualOnly();
      this.jumpFix = (BooleanValue)(new BooleanValue(this, "JumpFix", true, this.movement)).markVisualOnly();
      this.moveFix = (BooleanValue)((BooleanValue)new BooleanValue(this, "MoveFix", true, this.movement).withVisibility(() -> this.isMoveFixVisible())).markVisualOnly();
      this.silentMoveFix = (StringValue)((StringValue)new StringValue(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}, this.movement).withVisibility(() -> this.isSilentMoveFixVisible())).markVisualOnly();
      BooleanValue booleanValue37 = new BooleanValue(this, "SilentMoveFixIntave", true, this.movement);
      SettingVisibilityCondition condition111 = () -> {
         return this.silentMoveFix.isVisible() && !this.silentMoveFix.getSelectedValue().equals("None");
      };
      this.silentMoveFixIntave = (BooleanValue)((BooleanValue)booleanValue37.withVisibility(condition111)).markVisualOnly();
      this.targets = (SettingGroup)(new SettingGroup(this, "Targets", true)).markVisualOnly();
      this.targetSelection = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter(), this.targets)).withSorting();
      this.targetDelay = (DoubleValue)(new DoubleValue(this, "TargetDelay", 0.0, 0.0, 1000.0, 0, this.targets)).markVisualOnly();
      this.targetLoseDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "TargetLoseDelay", 0.0, 0.0, 0.0, 1000.0, 0, this.targets))
         .withoutNotInARow()
         .markAllVisualOnly();
      this.maxDeathTime = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "MaxDeathTime", 0.0, 0.0, 0.0, 30.0, 0, this.targets)).withoutNotInARow().markAllVisualOnly();
      this.miscellaneous = (SettingGroup)(new SettingGroup(this, "Miscellaneous")).markVisualOnly();
      this.closeGuisIfTargetArrives = (BooleanValue)(new BooleanValue(this, "CloseGuisIfTargetArrives", false, this.miscellaneous)).markVisualOnly();
      this.inInv = new BooleanValue(this, "InInv", false, this.miscellaneous);
      this.activeSlots = (MultiOptionSetting)(new MultiOptionSetting(this, "ActiveSlots", KillAuraActiveSlotOption.values(), this.miscellaneous)).markVisualOnly();
      this.weaponFilter = (WeaponFilterSettings)(new WeaponFilterSettings(this, this.miscellaneous)).markAllVisualOnly();
      this.lookDownDisable = new LookDownDisableSettings(this, this.miscellaneous);
      this.intave13 = (BooleanValue)new BooleanValue(this, "Intave13", false, this.miscellaneous).withVisibility(() -> this.isIntave13Visible());
      this.delayRotationsAfterClosingGuiScreen = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DelayRotationsAfterClosingGuiScreen", 3.0, 5.0, 1.0, 20.0, 0, this.miscellaneous)).withoutNotInARow().withVisibility(() -> this.isDelayRotationsAfterGuiVisible());
      this.delayClicksAfterClosingGuiScreen = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DelayClicksAfterClosingGuiScreen", 3.0, 5.0, 1.0, 20.0, 0, this.miscellaneous)).withoutNotInARow().withVisibility(() -> this.isDelayClicksAfterGuiVisible());
      this.delayRotationsAfterRespawn = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DelayRotationsAfterRespawn", 4.0, 6.0, 1.0, 20.0, 0, this.miscellaneous)).withoutNotInARow().withVisibility(() -> this.isDelayRotationsAfterRespawnVisible());
      this.delayClicksAfterRespawn = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DelayClicksAfterRespawn", 4.0, 6.0, 1.0, 20.0, 0, this.miscellaneous)).withoutNotInARow().withVisibility(() -> this.isDelayClicksAfterRespawnVisible());
      this.visuals = (SettingGroup)(new SettingGroup(this, "Visuals")).markVisualOnly();
      this.dot = (BooleanValue)(new BooleanValue(this, "Dot", false, this.visuals)).markVisualOnly();
      DoubleValue dotSizeSetting = new DoubleValue(this, "DotSize", 0.3, 0.01, 1.0, 2, this.visuals);
      BooleanValue dot2 = this.dot;
      this.dotSize = (DoubleValue)dotSizeSetting.visibleWhen(() -> dot2.getValue()).markVisualOnly();
      Color color2 = new Color(41, 146, 222);
      randomize2 = this.visuals;
      str4 = "DotColor";
      ColorSetting colorSetting = new ColorSetting(this, str4, color2, randomize2);
      BooleanValue dot3 = this.dot;
      this.dotColor = (ColorSetting)((ColorSetting)colorSetting.withVisibility(() -> dot3.getValue())).markVisualOnly();
      BlurShadowSettings blurShadowSettings = new BlurShadowSettings(this, this.visuals);
      BooleanValue dot4 = this.dot;
      this.dotShadowSettings = (BlurShadowSettings)((BlurShadowSettings)blurShadowSettings.withVisibility(() -> dot4.getValue())).disableBlurByDefault().withCustomShadowColor(Color.black).markAllVisualOnly();
      this.targetSwitchTimer = new TimeHelper();
      this.lastAttackTimer = new TimeHelper();
      this.clickCycleTimer = new TimeHelper();
      this.currentRange = 3.0;
      this.target = null;
      this.previousTarget = null;
      this.lastValidTarget = null;
      this.intaveRotationFlags = 0;
      this.loadedAiModelName = null;
      this.rotationTrailPoints = new CopyOnWriteArrayList();
      this.ticksSinceBlockAttack = 0;
      this.lastSwingPacketTimer = new TimeHelper();
      this.doubleClickFirstHitTimer = new TimeHelper();
      this.clickSpeedMonitor = new ClickSpeedMonitor(60);
      this.currentRotation = new Rotation();
      this.lockViewMouseDelta = new int[2];
      this.lastTickTimestamp = 0L;
      this.lastTickDurationMs = 0;
      this.firstHitReady = false;
      this.lastObjectMouseOver = null;
      this.holdingLeftClick = false;
      this.itemUseCleared = false;
      this.ticksSinceBugBlockSwitch = 0;
      this.grimRangeActive = false;
      this.lastTickTargets = new ArrayList();
      this.grimAttackPending = false;
      this.pendingLongClickRelease = false;
      this.intervalActionCount = 0;
      this.betaFixHitCount = 0;
      this.attackedThisTick = false;
      this.targetLoseTimer = new TimeHelper();
      this.tickCounter = new TickCounter();
      this.longClickBlockDelay = 0;
      this.useExtendedDualRange = false;
      this.actionRateAccumulator = new ActionRateAccumulator(100, 0.0);
      this.debugPosHistory = new BoundedHistoryList(23);
      this.ticksSinceTeleport = 0;
      this.preHitRangeTracker = new HysteresisRangeTracker();
      this.preAimRangeTracker = new HysteresisRangeTracker();
      this.aimThroughWallsRangeTracker = new HysteresisRangeTracker();
      this.preHitFovYawTracker = new HysteresisRangeTracker();
      this.preHitFovPitchTracker = new HysteresisRangeTracker();
      this.needsClickPatternReset = false;
      this.consecutiveHits = 0;
      this.appliedMouseDelta = new int[2];
      this.fractionalMouseDelta = new double[2];
      this.critLanded = false;
      this.preHitEnduranceUsed = 0;
      this.preHitEnduranceExhausted = false;
      RotationUtil rotationUtil2 = this.rotationUtil;
      UNoise3SettingContext uNoise3SettingContext = new UNoise3SettingContext(this, this.randomize);
      rotationUtil2.uNoise3RotationEngine = new UNoise3RotationEngine(uNoise3SettingContext, () -> {
         return this.randomizeMode.getSelectedValue().equals("Noise3");
      });
      this.aiModelRegistry = new OnnxModelRegistry(this.aiModel);
   }

   @Override
   public void onEnable() {
      this.firstHitReady = true;
      this.currentRange = this.range2.getValue();
      this.lockViewMouseDelta[0] = this.lockViewMouseDelta[1] = 0;
      this.setSuffix("0");
      this.target = null;
      this.currentRotation = ClientManagers.rotationController.getPreviousRotation();
      this.previousTarget = null;
      this.needsClickPatternReset = true;
      this.resetClickPattern();
      RotationUtil rotationUtil2 = this.rotationUtil;
      float f;
      if (this.randomizeMode.getSelectedValue().equals("Advanced")) {
         f = (float)this.innerRadius.getMaxValue();
      } else {
         f = 20.0F;
      }

      rotationUtil2.generateDeltaCandidates(f, (short)1000);
      this.activeAttackMode = this.attackMode.getSelectedValue();
      this.rotationUtil.reset();
      RandomizedRangeSettings preAimRange2 = this.preAimRange;
      this.preAimRangeTracker.reset(preAimRange2);
      RandomizedRangeSettings preHitRange2 = this.preHitRange;
      this.preHitRangeTracker.reset(preHitRange2);
      RandomizedRangeSettings aimThroughWallsRange2 = this.aimThroughWallsRange;
      this.aimThroughWallsRangeTracker.reset(aimThroughWallsRange2);
      RandomizedRangeSettings preHitFovYaw2 = this.preHitFovYaw;
      this.preHitFovYawTracker.reset(preHitFovYaw2);
      RandomizedRangeSettings preHitFovPitch2 = this.preHitFovPitch;
      this.preHitFovPitchTracker.reset(preHitFovPitch2);
      this.maxDeathTime.nextValue();
      this.refreshAiModels();
      if (this.rotationUtil.aiRotationPredictor != null) {
         this.rotationUtil.aiRotationPredictor.reset();
      }
   }

   @Override
   public void onDisable() {
      this.rotationTrailPoints.clear();
      this.lockViewMouseDelta[0] = this.lockViewMouseDelta[1] = 0;
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.fractionalMouseDelta[0] = this.fractionalMouseDelta[1] = 0.0;
      this.clearTargets();
      this.disableGrimRange();
      this.rotationUtil.reset();
      this.unloadAiModel();
      this.preHitEnduranceUsed = 0;
      this.preHitEnduranceExhausted = false;
      this.targetSelection.setCurrentTarget((Entity)null);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28, 18);
      this.toggleEventSubscription(2);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(6);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(29, 18);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.clearTargets();
      this.rerollRespawnDelays();
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.critFix.getValue() && this.critFixPacketCheck.getValue()) {
         Packet packet = event.getPacket();
         if (packet instanceof S0BPacketAnimation && mc.theWorld != null) {
            S0BPacketAnimation packet2 = (S0BPacketAnimation)packet;
            if (packet2.getAnimationType() != 4) {
               return;
            }

            Entity entityByID = mc.theWorld.getEntityByID(packet2.getEntityID());
            if (this.target != null && entityByID != null && entityByID.getEntityId() == this.target.getEntityId()) {
               this.critLanded = true;
            }
         }

         if (packet instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook packet3 = (S08PacketPlayerPosLook)packet;
            if (new Vec3(packet3.getX(), packet3.getY(), packet3.getZ()).distanceTo(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ) > 4.0) {
               this.ticksSinceTeleport = 0;
               this.rerollRespawnDelays();
            }
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      this.ticksSinceBugBlockSwitch++;
      if (this.canOperate()
         && this.target != null
         && this.blockMode.getSelectedValue().equals("Bug")
         && mc.thePlayer.getHeldItem() != null
         && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword
         && this.target.hurtTime <= 2
         && this.itemUseCleared) {
         int n = SilentSlotManager.realHotbarSlot == 0 ? 1 : -1;
         SilentSlotManager.requestSlot(this, (byte)1, SilentSlotManager.realHotbarSlot + n);
         this.itemUseCleared = false;
         this.ticksSinceBugBlockSwitch = 0;
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C03PacketPlayer && this.grimRangeActive && !BlockUtil.isScaffoldActiveAndValid()) {
         event.cancel();
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.grimRangeActive && !BlockUtil.isScaffoldActiveAndValid()) {
         event.setX(0.0);
         event.setY(0.0);
         event.setZ(0.0);
      }
   }

   public List getSortedTargets() {
      return this.findTargetsInRange(Math.max(this.preHitRange.getMaxValue(), this.preAimRange.getMaxValue()));
   }

   private List findTargetsInRange(double d) {
      EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
      entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter()).maxDistance(d).filterFocus();
      entityTargetSelector = entityTargetSelector.withinFov(this.yawFov.getValue(), this.pitchFov.getValue());
      entityTargetSelector = entityTargetSelector.maxDeathTime(this.maxDeathTime.getLastIntValue());
      entityTargetSelector = entityTargetSelector.filter(obj -> this.isEntityReachableThroughWalls((Entity)obj));
      return entityTargetSelector.sortBy(this.targetSelection.createTargetComparatorKey(this.getEffectiveRotation(), this.currentRange)).toList();
   }

   @Override
   public void onEarlyTick() {
      if (mc.currentScreen != null && mc.previousScreen == null) {
         this.delayClicksAfterClosingGuiScreen.nextValue();
         this.delayRotationsAfterClosingGuiScreen.nextValue();
      }

      RandomizedRangeSettings preHitRange2 = this.preHitRange;
      EntityLivingBase previousTarget2 = this.previousTarget;
      EntityLivingBase target2 = this.target;
      this.preHitRangeTracker.updateRange(target2, previousTarget2, preHitRange2);
      RandomizedRangeSettings preAimRange2 = this.preAimRange;
      EntityLivingBase previousTarget3 = this.previousTarget;
      EntityLivingBase target3 = this.target;
      this.preAimRangeTracker.updateRange(target3, previousTarget3, preAimRange2);
      RandomizedRangeSettings aimThroughWallsRange2 = this.aimThroughWallsRange;
      EntityLivingBase previousTarget4 = this.previousTarget;
      EntityLivingBase target4 = this.target;
      this.aimThroughWallsRangeTracker.updateRange(target4, previousTarget4, aimThroughWallsRange2);
      float[] floats;
      if (this.target == null) {
         floats = new float[]{0.0F, 0.0F};
      } else {
         float rotationPitch = mc.thePlayer.rotationPitch;
         float rotationYaw = mc.thePlayer.rotationYaw;
         floats = RotationUtil.getAngleDiffToEntityNearestPoint(this.target, rotationYaw, rotationPitch);
      }

      float[] floats2 = floats;
      HysteresisRangeTracker preHitFovYawTracker2 = this.preHitFovYawTracker;
      EntityLivingBase target5 = this.target;
      EntityLivingBase previousTarget5 = this.previousTarget;
      RandomizedRangeSettings preHitFovYaw2 = this.preHitFovYaw;
      preHitFovYawTracker2.updateRangeWithDistance(target5, previousTarget5, preHitFovYaw2, (double)Math.abs(floats2[0]));
      HysteresisRangeTracker preHitFovPitchTracker2 = this.preHitFovPitchTracker;
      EntityLivingBase target6 = this.target;
      previousTarget5 = this.previousTarget;
      preHitFovYaw2 = this.preHitFovPitch;
      preHitFovPitchTracker2.updateRangeWithDistance(target6, previousTarget5, preHitFovYaw2, (double)Math.abs(floats2[1]));
      this.lockViewMouseDelta[0] = this.lockViewMouseDelta[1] = 0;
      this.previousTarget = this.target;
      EntityLivingBase previousTarget6 = this.previousTarget;
      this.targetSelection.setCurrentTarget(previousTarget6);
      this.target = null;
      if (!this.canOperate() && !this.closeGuisIfTargetArrives.getValue()) {
         this.currentRotation = ClientManagers.rotationController.getCurrentRotation();
      } else {
         List sortedTargets = this.getSortedTargets();
         Vec3 targetHitVec2 = this.targetHitVec;
         boolean flag = this.throughWallsMode.getSelectedValue().equals("NoneFullBlocks");
         if (flag) {
            FullBlockRaytraceFilter.INSTANCE.enable();
         }

         TuplePair tuplePair = this.selectTargetAndHitVec(sortedTargets);
         if (flag) {
            FullBlockRaytraceFilter.INSTANCE.disable();
         }

         this.targetHitVec = (Vec3)tuplePair.getFirst();
         EntityLivingBase second = (EntityLivingBase)tuplePair.getSecond();
         this.setSuffix("" + (sortedTargets.isEmpty() ? 0 : sortedTargets.size()));
         if (this.target != null) {
            if (this.closeGuisIfTargetArrives.getValue() && !this.canOperate() && !(mc.currentScreen instanceof ClickGuiScreen) && this.target != null) {
               mc.thePlayer.closeScreen();
               this.currentRotation = ClientManagers.rotationController.getCurrentRotation();
               this.target = null;
               return;
            }

            EntityLivingBase target7 = this.target;
            if (this.rangeMode.getSelectedValue().equals("Grim") && this.target != null) {
               ArrayList list = new ArrayList();

               for (Object obj : sortedTargets) {
                  if (obj instanceof EntityLivingBase && !this.lastTickTargets.contains(obj)) {
                     EntityLivingBase entity5 = (EntityLivingBase)obj;
                     if (entity5.getDistanceToEntityBox(mc.thePlayer) <= 3.0F) {
                        list.add(entity5);
                     }
                  }
               }

               if (target7 != null && target7.hurtTime != 0 && this.grimAttackPending && mc.thePlayer.onGround) {
                  this.grimAttackPending = false;
                  this.enableGrimRange();
               }

               if (!list.isEmpty()) {
                  this.disableGrimRange();
               }
            }
         }

         this.lastTickTargets.clear();
         this.lastTickTargets.addAll(sortedTargets);
         if (second != null) {
            this.targetLoseTimer.reset();
            this.targetLoseDelay.nextValue();
            this.maxDeathTime.nextValue();
         }

         if (second == null && this.lastValidTarget != null) {
            if (!this.targetLoseTimer.reached((long)this.targetLoseDelay.getLastIntValue()) && (double)mc.thePlayer.getDistanceToEntityBox(this.lastValidTarget) < this.preAimRangeTracker.getCurrentThreshold()) {
               second = this.lastValidTarget;
               this.target = second;
            }
         }

         this.target = this.filterByActiveSlotAndWeapon(this.target);
         second = this.filterByActiveSlotAndWeapon(second);
         if (second != null && this.previousTarget != null && this.target != null && !this.target.getName().equals(this.previousTarget.getName()) && this.targetDelay.getValue() != 0.0) {
            TimeHelper targetSwitchTimer2 = this.targetSwitchTimer;
            long targetDelayMs = (long)this.targetDelay.getValue();
            if (!targetSwitchTimer2.reached(targetDelayMs + DefaultRandomGenerator.instance.nextLong(0L, 60L))) {
               this.target = this.previousTarget;
               second = this.target;
               this.targetHitVec = targetHitVec2;
            } else {
               this.targetSwitchTimer.reset();
            }
         }

         this.lastValidTarget = second;
         if (second != null && this.target != null) {
            if (!(mc.getTicksWithoutScreen() > this.delayRotationsAfterClosingGuiScreen.getLastIntValue() && !this.isRespawnDelayActive(this.delayRotationsAfterRespawn))) {
if (!this.inInv.getValue()) {
                  this.currentRotation = ClientManagers.rotationController.getPreviousRotation();
                  if (!this.noRotation.getValue()) {
                     ClientManagers.rotationController.requestRotation(this.currentRotation, this.getRotationPriority(), this);
                  }

                  return;
               }
}

            this.rotationUtil.noiseMinAimSpeed = this.additionalMinAimspeed.getValue();
            this.rotationUtil.noiseDynamicSpeed = this.additionalDynamicSpeed.getValue();
            this.rotationUtil.noiseExtraAimSpeed = this.additionalAimspeedIfNeeded.getValue();
            this.rotationUtil.noiseIdleAimProbability = this.probabilityOfAimingEvenWhenNotNeeded.getValue(true) / 100.0;
            this.rotationUtil.noiseTestJitterEnabled = this.test.getValue();
            this.rotationUtil.bezierAimSpeed = this.aimSpeed.getValue(true) / 100.0 * 8.0 + 2.0;
            this.rotationUtil.bezierPreDecelerationFactor = this.decelerationfactorPre.getValue(true) / 100.0 * 4.0 + 0.5;
            this.rotationUtil.bezierPostDecelerationFactor = this.decelerationfactorPost.getValue(true) / 100.0 * 4.0 + 1.0;
            this.rotationUtil.bezierMinReactionTime = MathHelper.clamp_int((int)this.reactionTime.getMinValue(), 0, 6);
            this.rotationUtil.bezierMaxReactionTime = MathHelper.clamp_int((int)this.reactionTime.getMaxValue(), 0, 6);
            this.rotationUtil.bezierAttentionWindow = (int)this.attention.getValue(true);
            this.rotationUtil.bezierAdditionalInaccuracy = this.additionalInaccuracy.getValue(true) / 100.0 * 20.0;
            this.rotationUtil.bezierSuperCorrectionDelay = this.supercorrectionDelay.nextInt(true);
            this.rotationUtil.bezierSuperCorrectionProbability = this.supercorrectionProbability.getValue(true) / 100.0;
            this.rotationUtil.bezierAdditionalAccuracy = this.additionalAccuracy.getValue();
            this.rotationUtil.bezierMinLookaheadTicks = (int)this.lookaheadTime.getMinValue(true);
            this.rotationUtil.bezierMaxLookaheadTicks = (int)this.lookaheadTime.getMaxValue(true);
            this.rotationUtil.bezierMinPredictionInaccuracy = this.predictionInaccuracy.getMinValue(true) / 100.0;
            this.rotationUtil.bezierMaxPredictionInaccuracy = this.predictionInaccuracy.getMaxValue(true) / 100.0;
            this.rotationUtil.bezierPredictionInaccuracySmoothness = (float)MathStatisticsUtil.lerp(0.016666666666666666, 0.05, 1.0 - this.predictionInaccuracySmoothness.getValue() / 100.0);
            this.rotationUtil.bezierBestVecPitchInterpolation = this.bestvecPitchinterpolation.nextDouble(true) / 100.0;
            this.rotationUtil.bezierMinRotationOnTarget = this.rotationOnTarget.getMinValue(true) / 100.0;
            this.rotationUtil.bezierMaxRotationOnTarget = this.rotationOnTarget.getMaxValue(true) / 100.0;
            this.rotationUtil.bezierMaxOrthogonalMovement = this.maxOrthogonalMovement.getValue(true);
            this.rotationUtil.bezierMinOrthogonalScale = this.orthogonalScale.getMinValue(true) / 100.0;
            this.rotationUtil.bezierMaxOrthogonalScale = this.orthogonalScale.getMaxValue(true) / 100.0;
            this.rotationUtil.bezierMaxAimpointAngleDiff = this.maxAimpointAnglediff.getValue(true);
            this.rotationUtil.bezierMinYawOffset = this.rotationYawOffset.getMinValue(true);
            this.rotationUtil.bezierMaxYawOffset = this.rotationYawOffset.getMaxValue(true);
            this.rotationUtil.bezierMinPitchOffset = this.rotationPitchOffset.getMinValue(true);
            this.rotationUtil.bezierMaxPitchOffset = this.rotationPitchOffset.getMaxValue(true);
            this.rotationUtil.bezierYawOffsetSpeed = this.rotationYawOffsetSpeed.getValue(true) / 100.0 * 0.2;
            this.rotationUtil.bezierPitchOffsetSpeed = this.rotationPitchOffsetSpeed.getValue(true) / 100.0 * 0.2;
            this.rotationUtil.bezierSkipYawOutOfReach = this.noYawAdjustmentIfTargetOutOfReach.getValue();
            this.rotationUtil.bezierSkipPitchOutOfReach = this.noPitchAdjustmentIfTargetOutOfReach.getValue();
            this.rotationUtil.bezierApplyRecordedMouseJitter = this.applyRecordedMouseJitterMovementIfAvailable.getValue();
            this.rotationUtil.bezierDuplicateEndControlPoint = this.ultimateTrashcodeBypass.getValue();
            this.rotationUtil.bezierAdaptCurveIfNeeded = this.adaptCurveIfNeeded.getValue();
            this.rotationUtil.turboMinTargetResponseTicks = (int)this.noise3ResponseTarget.getMinValue(true);
            this.rotationUtil.turboMaxTargetResponseTicks = (int)this.noise3ResponseTarget.getMaxValue(true);
            this.rotationUtil.turboMinPlayerResponseTicks = (int)this.noise3PlayerResponse.getMinValue(true);
            this.rotationUtil.turboMaxPlayerResponseTicks = (int)this.noise3PlayerResponse.getMaxValue(true);
            this.rotationUtil.turboMinAcceleration = (float)((int)this.noise3Acceleration.getMinValue());
            this.rotationUtil.turboMaxAcceleration = (float)((int)this.noise3Acceleration.getMaxValue());
            this.rotationUtil.turboMinAimSpeed = (float)((int)this.noise3AimSpeed.getMinValue());
            this.rotationUtil.turboMaxAimSpeed = (float)((int)this.noise3AimSpeed.getMaxValue());
            this.rotationUtil.turboMinLeadDistanceScale = this.noise3Scale.getValue(true);
            this.rotationUtil.turboBetterAccelerationFactor = this.noise33BetterAcceleration.getValue(true);
            if (this.randomizeMode.getSelectedValue().equals("AI")) {
               this.ensureAiModelLoaded();
               if (this.rotationUtil.aiRotationPredictor != null && this.target != null && this.target != this.previousTarget) {
                  EntityLivingBase target9 = this.target;
                  this.rotationUtil.aiRotationPredictor.syncTarget(target9);
               }
            } else {
               this.unloadAiModel();
            }

            if (second != this.target) {
               this.sendChatMessage("ERROR OCCURED. Please report this to the developer.");
            }

            RotationUtil rotationUtil2 = this.rotationUtil;
            EntityLivingBase target10 = this.target;
            RandomizedRangeSettings yawAcceleration2 = this.yawAcceleration;
            RandomizedRangeSettings pitchAcceleration2 = this.pitchAcceleration;
            preHitFovYaw2 = this.yawSpeed;
            RandomizedRangeSettings pitchSpeed2 = this.pitchSpeed;
            float yaw = this.getEffectiveRotation().yaw;
            float pitch = this.getEffectiveRotation().pitch;
            String selectedValue = this.randomizeMode.getSelectedValue();
            boolean value = this.interpolation.getValue();
            boolean value2 = this.smartAim.getValue();
            boolean value3 = this.intave13.getValue();
            float value4 = (float)this.randomStrength.getValue();
            Vec3 targetHitVec3 = this.targetHitVec;
            boolean value5 = this.advancedRots.getValue();
            boolean value6 = this.advancedRotsNotAtTarget.getValue();
            String selectedValue2 = this.heuristics.getSelectedValue();
            String selectedValue3 = this.rotationMode.getSelectedValue();
            double currentRange2 = this.currentRange;
            int value7 = (int)this.hitBoxPercentageVertical.getValue();
            int value8 = (int)this.hitBoxPercentageHorizontal.getValue();
            int value9 = (int)this.hitBoxPercentageVertical1.getValue();
            int value10 = (int)this.hitBoxPercentageHorizontal1.getValue();
            int intaveRotationFlags2 = this.intaveRotationFlags;
            String selectedValue4 = this.randomType.getSelectedValue();
            String selectedValue5 = this.fixRotationMode.getSelectedValue();
            boolean value11 = this.smartRotate.getValue();
            boolean value12 = this.reducedRotate.getValue();
            boolean value13 = this.pitchOutliers.getValue();
            RandomizedRangeSettings pitchOutliersAdd2 = this.pitchOutliersAdd;
            RandomizedRangeSettings pitchOutliersDelay2 = this.pitchOutliersDelay;
            double value14 = this.reduceRotationPercentage.getValue();
            float value15 = (float)this.maxXAbsDist.getValue();
            float value16 = (float)this.maxYAbsDist.getValue();
            float value17 = (float)this.minAbsDist.getValue();
            float minValue = (float)this.xRelDist.getMinValue();
            float minValue2 = (float)this.yRelDist.getMinValue();
            float maxValue = (float)this.xRelDist.getMaxValue();
            float maxValue2 = (float)this.yRelDist.getMaxValue();
            float value18 = (float)this.maxRotationAngle.getValue();
            float minValue3 = (float)this.innerRadius.getMinValue();
            float value19 = (float)this.bestHitXMinDist.getValue();
            float value20 = (float)this.bestHitYMInDist.getValue();
            boolean value21 = this.betterAcceleration.getValue();
            int value22 = (int)this.fixRotationChance.getValue(true);
            double d3 = this.maxFixRotationReach.nextValue();
            Rotation rotation2 = rotationUtil2.computeKillAuraRotation(
               target10,
               yawAcceleration2,
               pitchAcceleration2,
               preHitFovYaw2,
               pitchSpeed2,
               yaw,
               pitch,
               selectedValue,
               value,
               value2,
               value3,
               value4,
               targetHitVec3,
               value5,
               value6,
               selectedValue2,
               selectedValue3,
               currentRange2,
               value7,
               value8,
               value9,
               value10,
               intaveRotationFlags2,
               selectedValue4,
               selectedValue5,
               value11,
               value12,
               value13,
               pitchOutliersAdd2,
               pitchOutliersDelay2,
               value14,
               value15,
               value16,
               value17,
               minValue,
               minValue2,
               maxValue,
               maxValue2,
               value18,
               minValue3,
               value19,
               value20,
               value21,
               value22,
               d3,
               this.minFixRotationAngle.getValue(),
               this.minDistanceToBestVecYaw.getValue(),
               this.minDistanceToBestVecPitch.getValue(),
               this.minDistanceToBestVec.getValue(),
               this.minDistanceToBestVecCalculation.getSelectedValue(),
               this.stopRotationInsideTarget.getValue()
            );
            if (!this.noRotation.getValue()) {
               ClientManagers.rotationController.requestRotation(rotation2, this.getRotationPriority(), this);
            }

            this.lockViewMouseDelta = RotationUtil.computeMouseDeltas(rotation2.yaw, rotation2.pitch, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
            this.currentRotation = rotation2;
         } else {
            this.rotationUtil.reset();
            this.currentRotation = ClientManagers.rotationController.getCurrentRotation();
         }
      }
   }

   private void refreshAiModels() {
      String absolutePath = new File(ClientPaths.CLIENT_DIR, "ai-7yns").getAbsolutePath();
      this.aiModelRegistry.setModelsDirectory(new File(absolutePath.trim()));
   }

   private void ensureAiModelLoaded() {
      String selectedValue = this.aiModel.getSelectedValue();
      if (selectedValue != null && !selectedValue.equals("None") && this.randomizeMode.getSelectedValue().equals("AI")) {
         if (!selectedValue.equals(this.loadedAiModelName)) {
            this.unloadAiModel();
            File modelFile = this.aiModelRegistry.getModelFile(selectedValue);
            File normalizationFile = this.aiModelRegistry.getNormalizationFile(selectedValue);
            if (modelFile.exists() && normalizationFile.exists()) {
               this.rotationUtil.aiRotationPredictor = new AiRotationPredictor(modelFile, normalizationFile);
               this.loadedAiModelName = selectedValue;
            }
         }
      } else {
         this.unloadAiModel();
      }
   }

   private void unloadAiModel() {
      if (this.rotationUtil.aiRotationPredictor != null) {
         this.rotationUtil.aiRotationPredictor.close();
         this.rotationUtil.aiRotationPredictor = null;
      }

      this.loadedAiModelName = null;
   }

   private TuplePair selectTargetAndHitVec(List list) {
      List list2 = EntityTargetSelector.of(list).maxDistance(this.preAimRangeTracker.getCurrentThreshold()).toList();
      this.targetHitVec = null;
      if (this.smartAim.getValue() && !this.throughWallsMode.getSelectedValue().equals("AllBlocks")) {
         Vec3 vec = this.findVisibleHitVec(list2);
         if (vec != null) {
            return new TuplePair(vec, this.target);
         }

         ArrayList list3 = new ArrayList(list);
         list3.removeAll(list2);
         vec = this.findVisibleHitVec(list3);
         if (vec != null) {
            return new TuplePair(vec, null);
         }
      }

      if (this.canAimThroughWalls() || !this.smartAim.getValue()) {
         if (!list2.isEmpty()) {
            this.target = (EntityLivingBase)list2.get(0);
            return new TuplePair(null, this.target);
         }

         if (!list.isEmpty()) {
            this.target = (EntityLivingBase)list.get(0);
            return new TuplePair(null, null);
         }
      }

      this.target = null;
      return new TuplePair(null, null);
   }

   private Vec3 findVisibleHitVec(List list) {
      boolean flag = false;

      for (int i = 0; i < list.size(); i++) {
         EntityLivingBase entity = (EntityLivingBase)list.get(i);
         if (mc.thePlayer.getDistanceToEntity(entity) <= 6.0F) {
            flag = true;
            break;
         }
      }

      for (int j = 0; j < list.size(); j++) {
         EntityLivingBase entity2 = (EntityLivingBase)list.get(j);
         if (!(mc.thePlayer.getDistanceToEntity(entity2) > 6.0F) || !flag) {
            Vec3 vec = this.rotationUtil.selectEntityAimPoint(entity2, this.rotationMode.getSelectedValue(), (int)this.hitBoxPercentageVertical.getValue(), (int)this.hitBoxPercentageHorizontal.getValue(), (int)this.hitBoxPercentageVertical1.getValue(), (int)this.hitBoxPercentageHorizontal1.getValue(), ClientManagers.rotationController.getPreviousRotation());
            double d = vec.xCoord - mc.thePlayer.posX;
            double d2 = vec.yCoord - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
            double d3 = vec.zCoord - mc.thePlayer.posZ;
            Rotation rotation2 = new Rotation(
               (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI - 90.0),
               (float)(-(MathHelper.atan2(d2, (double)MathHelper.sqrt_double(d * d + d3 * d3)) * 180.0 / Math.PI))
            );
            double d4 = Math.max(this.preAimRange.getMaxValue(), this.preHitRange.getMaxValue()) + 3.0;
            double d5 = 0.0;
            MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, rotation2, d4, d5);
            if (hit != null
               && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
               && hit.entityHit != null
               && (hit.entityHit.getEntityId() == entity2.getEntityId() || !this.smartAimEntityCheck.getValue())) {
               this.target = (EntityLivingBase)list.get(j);
               return new Vec3(vec.xCoord, vec.yCoord, vec.zCoord);
            }

            float collisionBorderSize = entity2.getCollisionBorderSize();
            List list2 = MathStatisticsUtil.sampleBoxSurfacePoints(entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize), new HitVecCandidate(
               entity2.posX - mc.thePlayer.posX, mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(), entity2.posZ - mc.thePlayer.posZ
            ));
            boolean flag2 = false;
            byte b = 1;

            for (HitVecCandidate hitVecCandidate2 : (Iterable<HitVecCandidate>)(list2)) {
               double d7 = hitVecCandidate2.z;
               double d8 = hitVecCandidate2.y;
               hitVecCandidate2.visibilityFlags = this.evaluateHitVecVisibility(hitVecCandidate2.x, d8, d7, entity2);
               if (hitVecCandidate2.visibilityFlags[0] || hitVecCandidate2.visibilityFlags[1]) {
                  flag2 = true;
               }

               if (hitVecCandidate2.visibilityFlags[0]) {
                  b = 0;
               }
            }

            if (flag2 && (b == 0 || this.canAimThroughWalls())) {
               HitVecCandidate hitVecCandidate3 = MathStatisticsUtil.findDensestHitVecCandidate(list2, b);
               this.target = (EntityLivingBase)list.get(j);
               return new Vec3(hitVecCandidate3.x, hitVecCandidate3.y, hitVecCandidate3.z);
            }
         }
      }

      return null;
   }

   private EntityLivingBase filterByActiveSlotAndWeapon(EntityLivingBase entity) {
      int n = mc.thePlayer.inventory.currentItem + 1;
      if ((n != 1 || KillAuraActiveSlotOption.SLOT1.isSelected())
         && (n != 2 || KillAuraActiveSlotOption.SLOT2.isSelected())
         && (n != 3 || KillAuraActiveSlotOption.SLOT3.isSelected())
         && (n != 4 || KillAuraActiveSlotOption.SLOT4.isSelected())
         && (n != 5 || KillAuraActiveSlotOption.SLOT5.isSelected())
         && (n != 6 || KillAuraActiveSlotOption.SLOT6.isSelected())
         && (n != 7 || KillAuraActiveSlotOption.SLOT7.isSelected())
         && (n != 8 || KillAuraActiveSlotOption.SLOT8.isSelected())
         && (n != 9 || KillAuraActiveSlotOption.SLOT9.isSelected())) {
         return this.weaponFilter.isHeldItemAllowed() && !this.lookDownDisable.shouldDisable() ? entity : null;
      } else {
         return null;
      }
   }

   @Override
   public void onClickGuiUpdate() {
      this.currentRange = this.range2.getValue();
      this.activeAttackMode = this.attackMode.getSelectedValue();
   }

   @Override
   public void onPostMotion() {
      if (this.lastObjectMouseOver != null) {
         this.previousHitVec = this.lastObjectMouseOver.hitVec;
      }

      this.lastObjectMouseOver = mc.objectMouseOver;
      if (this.target != null && this.canOperate() && !BlockUtil.isScaffoldActiveAndValid()) {
         switch (this.blockMode.getSelectedValue()) {
            case "Spam":
            case "Post":
               this.blockSword();
         }
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      AbstractClickPatternMode abstractSubMode = (AbstractClickPatternMode)this.clickMode.getAbstractSubMode();
      if (abstractSubMode != null) {
         abstractSubMode.onTick();
      }

      this.tickCounter.increment();
      if (this.target != null && this.canOperate() && !BlockUtil.isScaffoldActiveAndValid()) {
         if ("Pre".equals(this.blockMode.getSelectedValue())) {
            this.blockSword();
         }
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (this.hasRotationControl()
         && !BlockUtil.isScaffoldActiveAndValid()
         && (!ClientManagers.moduleManager.targetStrafe.isToggled() || ClientManagers.moduleManager.targetStrafe.target == null)
         && !this.moveFix.getValue()) {
         event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (this.hasRotationControl()
         && !BlockUtil.isScaffoldActiveAndValid()
         && (!ClientManagers.moduleManager.targetStrafe.isToggled() || ClientManagers.moduleManager.targetStrafe.target == null)
         && !this.jumpFix.getValue()) {
         event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.hasRotationControl()
         && !BlockUtil.isScaffoldActiveAndValid()
         && (!ClientManagers.moduleManager.targetStrafe.isToggled() || ClientManagers.moduleManager.targetStrafe.target == null)
         && this.moveFix.getValue()
         && !this.silentMoveFix.getSelectedValue().equals("None")) {
         if (this.silentMoveFix.getSelectedValue().equals("Advanced")) {
            event.setMoveFixMode(MoveFixMode.ADVANCED);
         } else {
            event.setMoveFixMode(MoveFixMode.DEFAULT);
         }

         if (this.silentMoveFixIntave.getValue()) {
            event.setPreventInstantReverse(true);
         }
      }
   }

   @Override
   public void onAttackSlowdownFirst(EventAttackSlowdown event) {
      if (!this.slowDown.getValue() && this.target != null) {
         event.setSlowDownFactor(1.0);
         event.setSprint(true);
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.attackedThisTick = false;
      if ((this.hasRotationControl() || this.noRotation.getValue()) && (!ClientManagers.moduleManager.autoGapple.isToggled() || ClientManagers.moduleManager.autoGapple.getGappleSlot() == -1) && this.canOperate()) {
         if (this.target != null && (mc.getTicksWithoutScreen() > this.delayClicksAfterClosingGuiScreen.getLastIntValue() && mc.currentScreen == null || this.inInv.getValue()) && (!this.isRespawnDelayActive(this.delayClicksAfterRespawn) || this.inInv.getValue())) {
if (this.needsClickPatternReset) {
                     this.resetClickPattern();
                     this.needsClickPatternReset = false;
                  }

                  this.handleCombatClick(event);
} else {
this.decayPreHitEndurance();
            this.needsClickPatternReset = true;
            this.resetClickPattern();
            this.firstHitReady = true;
}

         switch (this.rangeMode.getSelectedValue()) {
            case "HazeRange":
               if (this.target != null && this.target.hurtTime == 10) {
                  this.currentRange = Math.min(this.currentRange + this.hazeAdd.getValue(), this.hazeMax.getValue());
                  this.lastHazeHitTime = System.currentTimeMillis();
               }

               if (this.target != this.previousTarget || this.previousTarget == null) {
                  this.currentRange = this.range2.getValue();
               }

               if (Math.abs(System.currentTimeMillis() - this.lastHazeHitTime) > 1000L) {
                  this.currentRange = this.range2.getValue();
               }
               break;
            case "IntervalRange":
               this.currentRange = this.range2.getValue();
               if ((double)this.intervalActionCount >= this.interval.getValue()
                  && this.target != null
                  && (double)this.target.hurtTime <= this.hurtTimeToHitWithReach.getValue()
                  && (double)mc.thePlayer.getDistanceToEntityBox(this.target) > this.minReachToIntervalHit.getValue()
                  && mc.thePlayer.getDistanceToEntity(this.target) < 6.0F) {
                  this.currentRange = this.rangeAtInterval.getValue();
                  this.intervalActionCount = 0;
               }
            case "Grim":
               break;
            case "Dual":
               if (this.target != null) {
                  double d;
                  if (this.useExtendedDualRange) {
                     d = this.baseRange.getMaxValue();
                  } else {
                     d = this.baseRange.getMinValue();
                  }

                  this.currentRange = d;
                  if (this.target.hurtTime == 9) {
                     int n = MathHelper.clamp_int((int)this.chanceOfExtendedRange.getValue(), 0, 100);
                     this.useExtendedDualRange = this.actionRateAccumulator.shouldTrigger(n);
                  }
               }
               break;
            default:
               this.currentRange = this.range2.getValue();
         }
      } else {
         this.needsClickPatternReset = true;
         this.resetClickPattern();
         this.decayPreHitEndurance();
      }
   }

   @Override
   public void onRayCast() {
      if (this.target == null) {
         this.disableGrimRange();
      }

      if (this.target != null && (ClientManagers.rotationController.getCurrentPriority() >= this.getRotationPriority() || this.noRotation.getValue())) {
         float rotationYaw = mc.thePlayer.rotationYaw;
         float rotationPitch = mc.thePlayer.rotationPitch;
         if (this.noRotation.getValue()) {
            mc.thePlayer.rotationYaw = this.currentRotation.yaw;
            mc.thePlayer.rotationPitch = this.currentRotation.pitch;
         }

         this.updateRayTraceTarget(1.0F);
         if (this.optifineHitbox.getValue() && (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY)) {
            MovingObjectPosition hit = RayCastUtil.rayCast(1.0F, ClientManagers.rotationController.getPreviousRotation().yaw, ClientManagers.rotationController.getCurrentRotation().pitch);
            if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
               mc.objectMouseOver = hit;
            }
         }

         if (this.noRotation.getValue()) {
            mc.thePlayer.rotationYaw = rotationYaw;
            mc.thePlayer.rotationPitch = rotationPitch;
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (ClientManagers.moduleManager.line.isToggled() && ClientManagers.moduleManager.line.activateAuraLine.getValue() && !ClientManagers.moduleManager.line.displayLegitimateRotations.getValue()) {
         BlurShadowManager.enableOneShotPass(ClientManagers.moduleManager.line.blurShadowSettings.isBlurActive(), ClientManagers.moduleManager.line.blurShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderRotationLineTrail(shaderRenderPass));
      }

      if (this.target != null) {
         if (this.randomizeMode.getSelectedValue().equals("Noise3")) {
            this.rotationUtil.uNoise3RotationEngine.onRender3D(partialTicks);
         }

         BlurShadowManager.enableOneShotPass(this.dotShadowSettings.isBlurActive(), this.dotShadowSettings.isShadowActive());
         BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderTargetDotPass(partialTicks, shaderRenderPass2));
      }

      if (RotationUtil.debugAimPoint1 != null) {
         RenderUtil.begin3DRender();
         GL11.glPushMatrix();
         GL11.glLineWidth(3.0F);
         GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint1, 0.05));
         GL11.glPopMatrix();
         RenderUtil.end3DRender();
      }

      if (RotationUtil.targetPositionTracker != null && RotationUtil.targetPositionTracker.targetEntity == this.target && RotationUtil.debugAimPoint1Prev != null) {
         RenderUtil.begin3DRender();
         GL11.glPushMatrix();
         GL11.glLineWidth(3.0F);
         GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint1Prev.lerp(RotationUtil.debugAimPoint1, (double)partialTicks), 0.05));
         GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
         RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint2Prev.lerp(RotationUtil.debugAimPoint2, (double)partialTicks), 0.05));
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint3Prev.lerp(RotationUtil.debugAimPoint3, (double)partialTicks), 0.05));
         GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
         RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint4Prev.lerp(RotationUtil.debugAimPoint4, (double)partialTicks), 0.05));
         GL11.glPopMatrix();
         RenderUtil.end3DRender();
      }

      if (this.target != null) {
         RenderUtil.begin3DRender();
         GL11.glPushMatrix();
         GL11.glLineWidth(3.0F);
         GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());

         for (int i = 0; i < this.target.targetPosHistory.size(); i++) {
            Vec3 vec = (Vec3)this.target.targetPosHistory.get(i);
            if (this.debugPosHistory.getEntries().size() > i) {
               vec = vec.lerp((Vec3)this.debugPosHistory.get(i), (double)partialTicks);
            }

            RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(vec, 0.05));
         }

         for (Vec3 vec2 : (Iterable<Vec3>)(this.target.targetPosHistory.getEntries())) {
            this.debugPosHistory.add(vec2);
         }

         if (RotationUtil.debugAimPoint4Prev != null) {
            GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint4Prev.lerp(RotationUtil.debugAimPoint4, (double)partialTicks), 0.05));
         }

         if (RotationUtil.debugAimPoint4Prev != null) {
            GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
            RenderUtil.drawFilledBox(AxisAlignedBB.fromCenter(RotationUtil.debugAimPoint2Prev.lerp(RotationUtil.debugAimPoint2, (double)partialTicks), 0.05));
         }

         GL11.glPopMatrix();
         RenderUtil.end3DRender();
      }
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (this.lastTickDurationMs > 10
         && this.lockView.getValue()
         && this.canOperate()
         && this.target != null
         && ClientManagers.rotationController.isOwnedBy(this)
         && !this.randomizeMode.getSelectedValue().equals("LegitTest")
         && !this.randomizeMode.getSelectedValue().equals("Noise2")) {
         int n = (int)(System.currentTimeMillis() - this.lastMouseMoveTime);
         double d = (double)this.lockViewMouseDelta[0] / (double)this.lastTickDurationMs * (double)n;
         double d2 = (double)this.lockViewMouseDelta[1] / (double)this.lastTickDurationMs * (double)n;
         this.fractionalMouseDelta[0] = this.fractionalMouseDelta[0] + d;
         this.fractionalMouseDelta[1] = this.fractionalMouseDelta[1] + d2;
         int n2 = (int)Math.round(this.fractionalMouseDelta[0] - (double)this.appliedMouseDelta[0]);
         int n3 = (int)Math.round(this.fractionalMouseDelta[1] - (double)this.appliedMouseDelta[1]);
         this.appliedMouseDelta[0] = this.appliedMouseDelta[0] + n2;
         this.appliedMouseDelta[1] = this.appliedMouseDelta[1] + n3;
         event.setDeltaX(n2);
         event.setDeltaY(n3);
         event.setIgnoreInvertMouse(true);
         event.setBypassSmoothCamera(true);
      } else {
         this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
         this.fractionalMouseDelta[0] = this.fractionalMouseDelta[1] = 0.0;
         this.lockViewMouseDelta[0] = this.lockViewMouseDelta[1] = 0;
      }

      this.lastMouseMoveTime = System.currentTimeMillis();
   }

   @Override
   public void onPostTick() {
      this.lastTickDurationMs = (int)Math.abs(this.lastTickTimestamp - System.currentTimeMillis());
      this.lastTickTimestamp = System.currentTimeMillis();
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.fractionalMouseDelta[0] = this.fractionalMouseDelta[1] = 0.0;
   }

   private void enableGrimRange() {
      this.grimRangeActive = true;
      this.currentRange = 6.0;
      this.activeAttackMode = "Multi";
   }

   private void disableGrimRange() {
      this.grimRangeActive = false;
      this.currentRange = 3.0;
      this.activeAttackMode = this.attackMode.getSelectedValue();
   }

   private boolean[] evaluateHitVecVisibility(double d, double d2, double d3, EntityLivingBase entity) {
      if (mc.thePlayer.canEntityBeSeen(d, d2, d3)) {
         double d4 = d - mc.thePlayer.posX;
         double d5 = d2 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
         double d6 = d3 - mc.thePlayer.posZ;
         Rotation rotation = new Rotation(
            (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0),
            (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI))
         );
         double d7 = 0.0;
         double currentRange2 = this.currentRange;
         MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, rotation, currentRange2, d7);
         if (hit == null
            || hit.typeOfHit == MovingObjectPosition.MovingObjectType.MISS
            || hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit.getEntityId() == entity.getEntityId()) {
            return new boolean[]{hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY, true};
         }
      }

      return new boolean[]{false, false};
   }

   private boolean shouldMissClick() {
      return (double)DefaultRandomGenerator.instance.nextInt((byte)0, (byte)99) > 100.0 - this.missClickPercentage.getValue();
   }

   private boolean hasPendingClicks(int n) {
      return n > 0;
   }

   private boolean isEventBasedBlockMode(String str) {
      return str.equals("AAC") || str.equals("Legit") || str.equals("None") || str.equals("Test");
   }

   private void handleCombatClick(EventClick event) {
      if (this.target != null && !BlockUtil.isScaffoldActiveAndValid()) {
         boolean canAttackTarget = this.canAttackTarget();
         int n = this.computeClickCount(canAttackTarget);
         if (!this.isEventBasedBlockMode(this.blockMode.getSelectedValue())
            && this.canAutoBlock()
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) {
            if (mc.thePlayer.getCurrentEquippedItem() != null
                  && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword
                  && !ClientManagers.moduleManager.fixes.shouldApplyIntaveBlockingFix()
               || this.blockMode.getSelectedValue().equals("Bug") && Math.abs(mc.thePlayer.inventory.currentItem - SilentSlotManager.realHotbarSlot) == 1 && this.ticksSinceBugBlockSwitch < 4) {
               switch (this.blockMode.getSelectedValue()) {
                  case "Constant":
                     if (this.hasPendingClicks(n)) {
                        this.performDirectAttacks(n);
                     }

                     this.blockSword();
                     break;
                  case "Constant2":
                     if (this.hasPendingClicks(n)) {
                        this.performDirectAttacks(n);
                     }

                     if (!mc.thePlayer.isUsingItem()) {
                        this.blockSword();
                     }
                     break;
                  case "UnblockOnHit":
                     if (this.hasPendingClicks(n)) {
                        this.unblockSword();
                        this.performDirectAttacks(n);
                     }

                     this.blockSword();
                     break;
                  case "UnblockOnHit2":
                     if (this.hasPendingClicks(n)) {
                        this.unblockSword();
                        this.performDirectAttacks(n);
                     }

                     if (!mc.thePlayer.isUsingItem()) {
                        this.blockSword();
                     }
                     break;
                  case "Spam":
                     this.unblockSword();
                     if (this.hasPendingClicks(n)) {
                        this.performDirectAttacks(n);
                     }
                     break;
                  case "SlowSpam":
                     if (mc.thePlayer.ticksExisted % 2 == 0) {
                        this.unblockSword();
                     }

                     this.blockSword();
                     if (this.hasPendingClicks(n)) {
                        this.performDirectAttacks(n);
                     }
                     break;
                  case "OldHypixel":
                     if (mc.thePlayer.hurtTime > 0) {
                        if (!mc.thePlayer.isUsingItem()) {
                           this.blockSword();
                        }

                        if (this.hasPendingClicks(n)) {
                           this.performDirectAttacks(n);
                        }
                     } else if (mc.thePlayer.isUsingItem()) {
                        this.unblockSword();
                     } else if (this.hasPendingClicks(n)) {
                        this.performDirectAttacks(n);
                     }
                     break;
                  case "Hypixel":
                     if (mc.thePlayer.hurtTime > 0) {
                        if (!mc.thePlayer.isUsingItem()) {
                           this.blockSword();
                        }

                        if (this.hasPendingClicks(n)) {
                           this.performDirectAttacks(n);
                        }
                     } else {
                        this.unblockSword();
                        if (this.hasPendingClicks(n)) {
                           this.performDirectAttacks(n);
                        }
                     }
                     break;
                  case "Post":
                  case "Pre":
                     if (this.hasPendingClicks(n)) {
                        this.performDirectAttacks(n);
                     }
                     break;
                  case "AACOld":
                     if (!this.canAutoBlock()) {
                        this.unblockSword();
                        if (this.hasPendingClicks(n)) {
                           this.performDirectAttacks(n);
                        }
                     } else if ((this.target.hurtTime == 0 && this.ticksSinceBlockAttack > 4 || (double)this.target.clientHurtTime == this.unblockAAC.getValue())
                        && mc.objectMouseOver != null
                        && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                        this.unblockSword();
                        this.performDirectAttacks(n);
                        this.blockSword();
                        this.ticksSinceBlockAttack = 0;
                     } else if (!mc.thePlayer.isUsingItem()) {
                        this.blockSword();
                     }
                     break;
                  case "Bug":
                     if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                        if (this.target.hurtTime <= 1 && !this.itemUseCleared && this.ticksSinceBugBlockSwitch > 1) {
                           mc.clickMouse();
                           if (this.interactAutoBlock.getValue()) {
                              mc.rightClickMouse();
                           } else {
                              mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
                           }
                        }
                     } else if (n > 0) {
                        this.performDirectAttacks(n);
                     }
               }

               event.cancel();
               event.setBlockAllClicks(true);
               event.setLeftMouseDown(this.shouldReleaseLongClick());
            }

            this.ticksSinceBlockAttack++;
         } else {
            if (!this.isBreakingBlock()) {
               if (!this.blockMode.getSelectedValue().equals("None")
                  && mc.thePlayer.getCurrentEquippedItem() != null
                  && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword
                  && !ClientManagers.moduleManager.fixes.shouldApplyIntaveBlockingFix()
                  && this.canAutoBlock()) {
                  switch (this.blockMode.getSelectedValue()) {
                     case "Legit":
                        if (mc.thePlayer.isUsingItem()) {
                           event.setRightMouseDown(false);
                        } else {
                           this.performAttacks((byte)1, event);
                           this.blockSwordWithEvent(event);
                        }
                        break;
                     case "AAC":
                        MovingObjectPosition hit = RayCastUtil.rayCastPredicted(1, ClientManagers.rotationController.getCurrentRotation(), this.currentRange);
                        if ((
                              this.target.hurtTime == 0 && this.ticksSinceBlockAttack > 4
                                 || (double)this.target.clientHurtTime == this.unblockAAC.getValue() + 1.0
                                 || (double)this.target.clientHurtTime == this.unblockAAC.getValue()
                           )
                           && (
                              mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
                                 || hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
                           )) {
                           if (mc.thePlayer.isUsingItem()) {
                              event.setRightMouseDown(false);
                           } else {
                              this.performAttacks((byte)1, event);
                              this.ticksSinceBlockAttack = 0;
                              this.blockSwordWithEvent(event);
                           }
                        } else {
                           this.blockSwordWithEvent(event);
                        }
                        break;
                     case "Test":
                        if (mc.thePlayer.isUsingItem()) {
                           event.setRightMouseDown(false);
                        } else if (mc.thePlayer.hurtTime == 2) {
                           this.performAttacks((byte)1, event);
                           this.blockSwordWithEvent(event);
                        } else if (this.hasPendingClicks(n)) {
                           this.performAttacks(n, event);
                        }
                  }
               } else if (!mc.thePlayer.isUsingItem()) {
                  this.performAttacks(n, event);
               }

               event.setDelayBlockBreak(this.longClick.getValue());
               if (this.supportsHoldClicking() && canAttackTarget) {
                  event.setOverrideBlockBreak(true);
                  event.setBreakingBlock(this.holdingLeftClick);
               }
            } else {
               event.setLeftMouseDown(true);
            }

            this.ticksSinceBlockAttack++;
         }
      } else {
         this.decayPreHitEndurance();
      }
   }

   private boolean shouldReleaseLongClick() {
      if (this.pendingLongClickRelease && this.longClick.getValue()) {
         if (mc.currentScreen == null && mc.inGameHasFocus && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int longClickBlockDelay2 = this.longClickBlockDelay;
            if (this.tickCounter.hasReached(longClickBlockDelay2)) {
               this.longClickBlockDelay = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)3);
               this.tickCounter.reset();
               this.pendingLongClickRelease = false;
               return true;
            }
         } else {
            this.tickCounter.reset();
            this.pendingLongClickRelease = false;
         }

         return mc.currentScreen == null && mc.inGameHasFocus;
      } else {
         this.tickCounter.reset();
         return false;
      }
   }

   private int computeClickCount(boolean flag) {
      int n = 0;
      if (flag) {
         n = this.sampleClickPattern();
      } else {
         this.needsClickPatternReset = true;
         this.resetClickPattern();
      }

      if (ClientManagers.moduleManager.moveHelper.isToggled() && MoveHelperModeOption.HIT_SELECTION.isSelected() && ClientManagers.moduleManager.moveHelper.shouldHitSelect()) {
         n = 0;
      }

      if (n == 0
         && ClientManagers.moduleManager.moveHelper.isToggled()
         && MoveHelperModeOption.HIT_SELECTION.isSelected()
         && !ClientManagers.moduleManager.moveHelper.shouldHitSelect()
         && ClientManagers.moduleManager.moveHelper.isPreviousShouldHitSelect()) {
         n = 1;
      }

      int n2 = (int)((double)n + EventClick.recentClicksBuffer.sum());
      if (this.cpsLimiterIntave.getValue() ? !this.clickSpeedMonitor.isExceedsCpsLimit() : !((double)n2 > this.cpsLimiter.getValue())) {
         if (n == 0 && this.critFix.getValue() && mc.objectMouseOver != null) {
            if (this.critFixChance.getValue(true) != 100.0) {
               if (!(Math.random() < this.critFixChance.getValue(true) / 100.0)) {
                  return n;
               }
            }

            boolean flag4 = ClientManagers.moduleManager.criticals.isToggled()
               || mc.thePlayer.fallDistance > 0.0F
                  && !mc.thePlayer.onGround
                  && !mc.thePlayer.isOnLadder()
                  && !mc.thePlayer.isInWater()
                  && !mc.thePlayer.isPotionActive(Potion.blindness)
                  && mc.thePlayer.ridingEntity == null
                  && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
            if (!this.critLanded && flag4) {
               return 1;
            }
         }

         return n;
      } else {
         return -1337;
      }
   }

   private int sampleClickPattern() {
      if (this.target.hurtTime == 10) {
         this.critLanded = false;
      }

      if (this.perfectHit.getValue()
         && mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY
         && mc.objectMouseOver.entityHit instanceof EntityLivingBase
         && !this.shouldReduceHits()) {
         EntityLivingBase entityHit = (EntityLivingBase)mc.objectMouseOver.entityHit;
         if (entityHit.hurtTime == 0 || entityHit.hurtTime == 1) {
            return 1;
         }
      }

      if (this.intave13.getValue() && ClientManagers.moduleManager.timerRange.isToggled() && ClientManagers.moduleManager.timerRange.fastAttackScore >= 4) {
         return 0;
      } else {
         AbstractClickPatternMode abstractSubMode = (AbstractClickPatternMode)this.clickMode.getAbstractSubMode();
         if (abstractSubMode != null) {
            return abstractSubMode.computeClicks((EventClick)null);
         } else {
            return 0;
         }
      }
   }

   @Override
   public int applyHitReduction(int n) {
      if (!this.shouldReduceHits()) {
         return n;
      } else {
         if (ClientManagers.moduleManager.debug.devToggleF != null && ClientManagers.moduleManager.debug.devToggleF.getValue() && ClientManagers.moduleManager.debug.isToggled()) {
            String str = "Reduce hits";
            ClientManagers.moduleManager.debug.sendChatMessage(str);
         }

         return 0;
      }
   }

   private boolean shouldReduceHits() {
      return this.reduceCPSIfYouCanNotHitATargett.getValue()
         && this.target != null
         && this.target.hurtTime == 0
         && System.currentTimeMillis() - this.target.lastDamageAttackTime > 75L
         && System.currentTimeMillis() - this.target.lastDamageAttackTime < 225L;
   }

   private boolean canAutoBlock() {
      if (this.onlyRightClickBlock.getValue() && !mc.gameSettings.keyBindUseItem.isKeyDown()) {
         return false;
      } else if (this.onlyWhileSwingInProgress.getValue() && !mc.thePlayer.isSwingInProgress) {
         return false;
      } else if (this.onlyWhileHurt.getValue() && mc.thePlayer.hurtTime == 0) {
         return false;
      } else {
         return !this.smartBlock.getValue()
            ? true
            : this.target != null
               && RotationUtil.getEntityLookAngleToPlayer(this.target) < 100.0F
               && this.target.getHeldItem() != null
               && (this.target.getHeldItem().getItem() instanceof ItemSword || this.target.getHeldItem().getItem() instanceof ItemTool);
      }
   }

   private void blockSword() {
      this.blockSwordWithEvent((EventClick)null);
   }

   private void blockSwordWithEvent(EventClick event) {
      if (this.canAutoBlock()
         && mc.thePlayer != null
         && mc.thePlayer.inventory != null
         && mc.thePlayer.inventory.getCurrentItem() != null
         && mc.thePlayer.inventory.getCurrentItem().getItem() != null
         && mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword) {
         if (this.interactAutoBlock.getValue()) {
            if (event == null) {
               mc.rightClickMouse();
            } else {
               event.setRightMouseDown(true);
            }
         } else if (event != null) {
            event.setRightMouseDown(true);
            event.setRightClickAction(() -> mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem()));
         } else {
            mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
         }
      }
   }

   private void unblockSword() {
      if (mc.thePlayer.isUsingItem()) {
         mc.playerController.onStoppedUsingItem(mc.thePlayer);
      }
   }

   private void performDirectAttacks(int n) {
      this.performAttacks(n, (EventClick)null);
   }

   private boolean canAttackTarget() {
      if (ClientManagers.moduleManager.autoRod.isToggled() && ClientManagers.moduleManager.autoRod.combat.getValue() && !ClientManagers.moduleManager.gommeMode.isToggled()) {
         mc.playerController.syncCurrentPlayItem();
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(mc.playerController.currentPlayerItem + 36).getStack();
         if (stack == null || !(stack.getItem() instanceof ItemFishingRod)) {
            if (!mc.playerController.timeHelper.reached(90L)) {
               this.decayPreHitEndurance();
               return false;
            }
         }
      }

      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityPlayer) {
         Entity entityHit = mc.objectMouseOver.entityHit;
         if (ClientManagers.moduleManager.teams.isTeammate(entityHit)) {
            return false;
         }

         Entity entityHit2 = mc.objectMouseOver.entityHit;
         if (ClientManagers.moduleManager.midClick.isFriend(entityHit2) && !ClientManagers.moduleManager.midClick.autoUnfriendTriggered) {
            return false;
         }
      }

      if (this.noLoopHit.getValue() && mc.remainingTicksInFrame != 0) {
         return false;
      } else if (!(mc.thePlayer.getDistanceToEntity(this.target) < 6.0F) && this.currentRange > 5.0) {
         return false;
      } else if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
         return true;
      } else {
         if (!this.preHit.getValue()) {
            if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
               return false;
            }
         } else {
            if ((double)mc.thePlayer.getDistanceToEntityBox(this.target) > this.preHitRangeTracker.getCurrentThreshold()) {
               this.decayPreHitEndurance();
               return false;
            }

            AxisAlignedBB expandedEntityBox = RotationUtil.getExpandedEntityBox(this.target);
            Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
            double normalizedPitchInBox = HitboxAngleUtil.getNormalizedPitchInBox(expandedEntityBox, positionEyes, (double)mc.thePlayer.rotationPitch);
            double normalizedYawInBox = HitboxAngleUtil.getNormalizedYawInBox(expandedEntityBox, positionEyes, (double)mc.thePlayer.rotationYaw);
            if (normalizedPitchInBox < 0.0 || normalizedPitchInBox > 1.0 || normalizedYawInBox < 0.0 || normalizedYawInBox > 1.0) {
               float rotationPitch2 = mc.thePlayer.rotationPitch;
               float rotationYaw2 = mc.thePlayer.rotationYaw;
               float[] floats = RotationUtil.getAngleDiffToEntityNearestPoint(this.target, rotationYaw2, rotationPitch2);
               if ((double)Math.abs(floats[0]) > this.preHitFovYawTracker.getCurrentThreshold() || (double)Math.abs(floats[1]) > this.preHitFovPitchTracker.getCurrentThreshold()) {
                  return false;
               }
            }

            int lastIntValue = this.prediction.getLastIntValue();
            if (!this.checkSmartPreHit(3.0, lastIntValue)) {
               return false;
            }
         }

         return true;
      }
   }

   private void decayPreHitEndurance() {
      this.preHitEnduranceUsed = Math.max(this.preHitEnduranceUsed - this.enduranceReduction.nextInt(true), 0);
      if (this.preHitEnduranceUsed == 0) {
         this.preHitEnduranceExhausted = false;
      }
   }

   private boolean checkSmartPreHit(double d, int n) {
      if (!this.smartPreHit.getValue()) {
         return true;
      } else if (this.preHitEnduranceUsed <= this.endurance.getLastIntValue() && !this.preHitEnduranceExhausted) {
         if (mc.ticksLookingAtEntity == 1) {
            this.loseDelay.nextValue();
            this.prediction.nextValue();
         }

         if ((mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) && mc.ticksNotLookingAtEntity > this.loseDelay.getLastIntValue()) {
            if (this.preHitEnduranceUsed > this.endurance.getLastIntValue() / 2) {
               return false;
            } else {
               MovingObjectPosition hit = RayTraceUtil.rayTrace(
                  1.0F, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, 10.0, 6.0, 1.0
               );
               if (hit != null && hit.entityHit instanceof EntityLivingBase) {
                  EntityLivingBase entityHit = (EntityLivingBase)hit.entityHit;
                  int ticksExisted = mc.thePlayer.ticksExisted;

                  for (int i = 1; i < n; i++) {
                     EntityLivingBase entityHit2 = (EntityLivingBase)hit.entityHit;
                     int n2 = ticksExisted + i;
                     AxisAlignedBB box = RotationUtil.createEntityBoxAt(entityHit, TrajectoryPredictionUtil.getClampedPosition(TrajectoryPredictionUtil.predictTargetPositions(mc.thePlayer, entityHit2, ticksExisted, n2), 99999));
                     double[] doubles = PlayerUtil.predictPlayerPosition(i - 1, mc.thePlayer);
                     Vec3 vec = new Vec3(doubles[0], doubles[1] + (double)mc.thePlayer.getEyeHeight(), doubles[2]);
                     if (vec.distanceTo(RotationUtil.clampPointToBox(vec, box)) < d) {
                        return true;
                     }
                  }

                  return false;
               } else {
                  return false;
               }
            }
         } else {
            return true;
         }
      } else {
         if (!this.preHitEnduranceExhausted) {
            this.endurance.nextValue();
         }

         this.preHitEnduranceExhausted = true;
         return false;
      }
   }

   private void performAttacks(int n, EventClick event) {
      if (this.activeAttackMode.equals("Multi")) {
         if (n > 0) {
            List list = this.findTargetsInRange(this.preAimRangeTracker.getCurrentThreshold());
            if (!list.isEmpty()) {
               mc.thePlayer.swingItem();
            }

            for (int i = 0; i < list.size(); i++) {
               Entity entity = (Entity)list.get(i);
               if ((double)i < this.maxTargets.getValue() && !ProtectedPlayerCheck.isProtectedPlayer(entity)) {
                  if (!ClientManagers.moduleManager.antiBot.isBot(entity) && (double)mc.thePlayer.getDistanceToEntityBox(entity) <= this.currentRange) {
                     mc.playerController.attackEntity(mc.thePlayer, entity);
                  }
               }
            }
         }
      } else {
         boolean flag;
         if (this.missClick.getValue()) {
if (this.shouldMissClick()) {
flag = true;
} else {
flag = false;
}
} else {
flag = false;
}

         boolean flag2 = flag;
         if (this.coolDown.getValue()) {
            while (n > 0) {
               if (mc.thePlayer.getCurrentEquippedItem() != null
                  && (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword || mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemTool)) {
                  int attackCooldownTicks = mc.thePlayer.getCurrentEquippedItem().getItem().attackCooldownTicks;
                  if (mc.thePlayer.ticksSinceLastSwing >= attackCooldownTicks) {
                     this.dispatchAttackClick(event, flag2);
                  }
               } else {
                  this.dispatchAttackClick(event, flag2);
               }

               n--;
            }
         } else {
            if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
               if (this.doubleClickFirstHit.getValue()) {
if (!(!this.firstHitReady && !this.doubleClickFirstHitTimer.reached(1500L))) {
if (!this.clickMode.getSelectedValue().equals("Smart")) {
                     this.dispatchAttackClick(event, flag2);
                     this.firstHitReady = false;
                  }
}
}

               this.doubleClickFirstHitTimer.reset();
            }

            while (n > 0) {
               this.dispatchAttackClick(event, flag2);
               n--;
            }
         }
      }

      this.clickCycleTimer.reset();
   }

   public void dispatchAttackClick(EventClick event, boolean flag) {
      if (!flag) {
         this.lastAttackTimer.reset();
      }

      if (!flag && mc.objectMouseOver != null && event != null) {
         boolean flag2 = ClientManagers.moduleManager.criticals.isToggled()
            || mc.objectMouseOver.entityHit instanceof EntityLivingBase
               && mc.thePlayer.fallDistance > 0.0F
               && !mc.thePlayer.onGround
               && !mc.thePlayer.isOnLadder()
               && !mc.thePlayer.isInWater()
               && !mc.thePlayer.isPotionActive(Potion.blindness)
               && mc.thePlayer.ridingEntity == null;
         if (!this.critLanded && flag2) {
            event.setCriticalHit(true);
         }
      }

      if (event == null) {
         this.executeLeftClick(flag);
         if (!flag) {
            EventClick.clicksThisTick++;
         }
      } else {
         event.addLeftClicks((byte)1);
         event.setLeftClickAction(() -> this.runScheduledLeftClick(flag));
      }
   }

   private boolean isBreakingBlock() {
      return this.breakBlocks.getValue() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK;
   }

   private void executeLeftClick(boolean flag) {
      if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
         if (ProtectedPlayerCheck.isProtectedPlayer(mc.objectMouseOver.entityHit)) {
            return;
         }

         Entity entityHit = mc.objectMouseOver.entityHit;
         if (ClientManagers.moduleManager.antiBot.isBot(entityHit)) {
            return;
         }
      }

      if (!flag && mc.objectMouseOver != null) {
         boolean flag2 = ClientManagers.moduleManager.criticals.isToggled()
            || mc.objectMouseOver.entityHit instanceof EntityLivingBase
               && mc.thePlayer.fallDistance > 0.0F
               && !mc.thePlayer.onGround
               && !mc.thePlayer.isOnLadder()
               && !mc.thePlayer.isInWater()
               && !mc.thePlayer.isPotionActive(Potion.blindness)
               && mc.thePlayer.ridingEntity == null;
         if (!this.critLanded && flag2) {
            this.critLanded = true;
         }

         this.pendingLongClickRelease = true;
         LeftClickListener.onLeftClick();
      }

      if (ClientManagers.moduleManager.fixes.isToggled()) {
         String fixMode = ClientManagers.moduleManager.fixes.hitDelayFixMode.getSelectedValue();
         boolean applyFix = (fixMode.equals("OnlyAtTargetLite") && mc.leftClickCounter < 9)
            || (fixMode.equals("OnlyAtTarget") && ClientManagers.moduleManager.fixes.shouldApplyHitDelayFix(mc.leftClickCounter < 9));
         if (applyFix && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            mc.leftClickCounter = 0;
         }
      }

      if (mc.leftClickCounter > 0
         && ClientManagers.moduleManager.debug.lostHitDebug.getValue()
         && ClientManagers.moduleManager.debug.isToggled()
         && mc.objectMouseOver != null
         && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
         String str = "Lost Hit";
         ClientManagers.moduleManager.debug.sendChatMessage(str);
      }

      if (mc.leftClickCounter <= 0) {
         if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_9) && !this.noSwing.getValue()) {
            mc.thePlayer.swingItem();
         }

         if (mc.objectMouseOver == null) {
            if (mc.playerController.isNotCreative()) {
               mc.leftClickCounter = (byte)10;
            }
         } else {
            if (flag) {
               if (ViaAugustus.targetProtocolVersion.newerThan(ProtocolVersion.v1_9) && !this.noSwing.getValue()) {
                  mc.thePlayer.swingItem();
               }

               return;
            }

            switch (mc.objectMouseOver.typeOfHit) {
               case ENTITY:
                  mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
                  if (this.rangeMode.getSelectedValue().equals("Grim") && !this.grimRangeActive) {
                     this.grimAttackPending = true;
                  }

                  if (this.fixBeta.getValue() && this.betaFixHitCount > 19 && !this.noSwing.getValue()) {
                     mc.thePlayer.swingItem();
                     this.betaFixHitCount = 0;
                  }

                  this.betaFixHitCount++;
                  break;
               case BLOCK:
                  BlockPos blockPos = mc.objectMouseOver.getBlockPos();
                  if (mc.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.air) {
                     mc.playerController.clickBlock(blockPos, mc.objectMouseOver.sideHit);
                     break;
                  }
               case MISS:
               default:
                  if (mc.playerController.isNotCreative()) {
                     mc.leftClickCounter = (byte)10;
                  }

                  mc.thePlayer.resetTicksSinceLastSwing();
                  if (ClientManagers.moduleManager.fixes.isToggled() && ClientManagers.moduleManager.fixes.hitDelayFixMode.getSelectedValue().equals("Default")) {
                     mc.leftClickCounter = (byte)0;
                  }
            }

            if (ViaAugustus.targetProtocolVersion.newerThan(ProtocolVersion.v1_9) && !this.noSwing.getValue()) {
               mc.thePlayer.swingItem();
            }
         }
      }
   }

   private void updateRayTraceTarget(float f) {
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null && mc.theWorld != null) {
         mc.pointedEntity = (Entity)null;
         double blockReachDistance = (double)mc.playerController.getBlockReachDistance();
         blockReachDistance = Math.max(blockReachDistance, this.currentRange);
         if (this.throughWallsMode.getSelectedValue().equals("NoneFullBlocks")) {
            FullBlockRaytraceFilter.INSTANCE.enable();
         }

         Minecraft mc2 = mc;
         mc2.objectMouseOver = renderViewEntity.rayTrace(blockReachDistance, f);
         FullBlockRaytraceFilter.INSTANCE.disable();
         double d = blockReachDistance;
         Vec3 positionEyes = renderViewEntity.getPositionEyes(f);
         boolean flag = false;
         if (blockReachDistance > 3.0) {
            flag = true;
         }

         if (mc.objectMouseOver != null) {
            d = mc.objectMouseOver.hitVec.distanceTo(positionEyes);
            if (this.throughWallsMode.getSelectedValue().equals("AllBlocks")) {
               d = Math.max(d, this.throughWallsRange.getValue());
            }
         }

         if (ClientManagers.moduleManager.fixes.isIntaveFixEnabled()) {
            blockReachDistance = 6.0;
         }

         blockReachDistance = Math.max(blockReachDistance, this.currentRange);
         Vec3 look = renderViewEntity.getLook(f);
         Vec3 vec = positionEyes.addVector(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance);
         mc.entityRenderer.pointedEntity = null;
         Vec3 vec2 = null;
         float f2 = 1.0F;
         List list = mc.theWorld
            .getEntitiesInAABBexcluding(
               renderViewEntity,
               renderViewEntity.getEntityBoundingBox()
                  .addCoord(look.xCoord * blockReachDistance, look.yCoord * blockReachDistance, look.zCoord * blockReachDistance)
                  .expand((double)f2, (double)f2, (double)f2),
               Predicates.and(EntitySelectors.NOT_SPECTATING, new EntityCollidablePredicate(mc.entityRenderer))
            );
         double d2 = d;
         if (this.activeAttackMode.equals("Packet")) {
            float collisionBorderSize = this.target.getCollisionBorderSize();
            AxisAlignedBB entityBoundingBox = this.target.getEntityBoundingBox();
            if (ClientManagers.moduleManager.hitBox.isToggled()) {
               EntityLivingBase target2 = this.target;
               entityBoundingBox = ClientManagers.moduleManager.hitBox.getExpandedBoundingBox(target2, collisionBorderSize);
            } else {
               entityBoundingBox = entityBoundingBox.expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            }

            MovingObjectPosition hit2 = entityBoundingBox.calculateIntercept(positionEyes, vec);
            if (entityBoundingBox.isVecInside(positionEyes)) {
               if (d >= 0.0) {
                  mc.entityRenderer.pointedEntity = this.target;
                  vec2 = hit2 == null ? positionEyes : hit2.hitVec;
                  d2 = 0.0;
               }
            } else if (hit2 != null) {
               double distance = positionEyes.distanceTo(hit2.hitVec);
               if (distance < d || d == 0.0) {
                  if (this.target != renderViewEntity.ridingEntity || false) {
                     mc.entityRenderer.pointedEntity = this.target;
                     vec2 = hit2.hitVec;
                     d2 = distance;
                  } else if (d == 0.0) {
                     mc.entityRenderer.pointedEntity = this.target;
                     vec2 = hit2.hitVec;
                  }
               }
            }
         } else {
            for (int i = 0; i < list.size(); i++) {
               Entity entity = (Entity)list.get(i);
               float collisionBorderSize2 = entity.getCollisionBorderSize();
               AxisAlignedBB entityBoundingBox2 = entity.getEntityBoundingBox();
               if (ClientManagers.moduleManager.hitBox.isToggled()) {
                  entityBoundingBox2 = ClientManagers.moduleManager.hitBox.getExpandedBoundingBox(entity, collisionBorderSize2);
               } else {
                  entityBoundingBox2 = entityBoundingBox2.expand((double)collisionBorderSize2, (double)collisionBorderSize2, (double)collisionBorderSize2);
               }

               MovingObjectPosition hit3 = entityBoundingBox2.calculateIntercept(positionEyes, vec);
               if (entityBoundingBox2.isVecInside(positionEyes)) {
                  if (d2 >= 0.0) {
                     mc.entityRenderer.pointedEntity = entity;
                     vec2 = hit3 == null ? positionEyes : hit3.hitVec;
                     d2 = 0.0;
                  }
               } else if (hit3 != null) {
                  double d4 = positionEyes.distanceTo(hit3.hitVec);
                  if (d4 < d2 || d2 == 0.0) {
                     if (entity != renderViewEntity.ridingEntity || false) {
                        mc.entityRenderer.pointedEntity = entity;
                        vec2 = hit3.hitVec;
                        d2 = d4;
                     } else if (d2 == 0.0) {
                        mc.entityRenderer.pointedEntity = entity;
                        vec2 = hit3.hitVec;
                     }
                  }
               }
            }
         }

         if (mc.entityRenderer.pointedEntity != null && flag && positionEyes.distanceTo(vec2) > this.currentRange) {
            mc.entityRenderer.pointedEntity = null;
            mc2 = mc;
            mc2.objectMouseOver = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec2, (EnumFacing)null, new BlockPos(vec2));
         }

         if (mc.entityRenderer.pointedEntity != null && (d2 < d || mc.objectMouseOver == null)) {
            mc2 = mc;
            mc2.objectMouseOver = new MovingObjectPosition(mc.entityRenderer.pointedEntity, vec2);
            if (mc.entityRenderer.pointedEntity instanceof EntityLivingBase || mc.entityRenderer.pointedEntity instanceof EntityItemFrame) {
               Entity pointedEntity = mc.entityRenderer.pointedEntity;
               mc.pointedEntity = pointedEntity;
            }
         }
      }
   }

   private boolean canOperate() {
      return (this.inInv.getValue() || mc.isNotInGui())
         && (!ClientManagers.moduleManager.blink.isToggled() || !ClientManagers.moduleManager.blink.disableKillAura.getValue())
         && (!ClientManagers.moduleManager.fly.isToggled() || !ClientManagers.moduleManager.fly.disableAura.getValue());
   }

   public boolean isEntityReachableThroughWalls(Entity entity) {
      return !mc.thePlayer.canEntityBeSeen(entity) && !this.canAimThroughWalls() && !this.smartAim.getValue()
         ? false
         : mc.thePlayer.canEntityBeSeen(entity) || !this.canAimThroughWalls() || !((double)mc.thePlayer.getDistanceToEntityBox(entity) > this.aimThroughWallsRangeTracker.getCurrentThreshold());
   }

   private boolean canAimThroughWalls() {
      return this.aimThroughWalls.getValue() || this.throughWallsMode.getSelectedValue().equals("AllBlocks");
   }

   private boolean hasRotationControl() {
      return this.target != null && ClientManagers.rotationController.getCurrentPriority() >= this.getRotationPriority();
   }

   private boolean isTrailPointExpired(double d) {
      return Math.abs(d - (double)System.currentTimeMillis()) > ClientManagers.moduleManager.line.auraLineTime.getValue();
   }

   public void onOutgoingCombatPacket(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C0APacketAnimation && this.actionToTrackInterval.getSelectedValue().equals("Swings")
         || packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK && this.actionToTrackInterval.getSelectedValue().equals("Attacks")) {
         this.intervalActionCount++;
      }

      if (this.intave13.getValue()) {
         if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook || packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
            float rotationYaw = mc.thePlayer.rotationYaw;
            float rotationPitch = mc.thePlayer.rotationPitch;
            float yaw = this.getEffectiveRotation().yaw;
            float pitch = this.getEffectiveRotation().pitch;
            double d = (double)Math.abs(yaw - rotationYaw);
            double d2 = (double)Math.abs(pitch - rotationPitch);
            double d3 = Math.abs(d) + Math.abs(d2);
            long time = this.lastSwingPacketTimer.getTime();
            if (d3 > 8.0 && time < 4000L) {
               this.intaveRotationFlags++;
               if (d3 > 30.0 && time < 250L) {
                  this.intaveRotationFlags++;
                  if (d3 > 60.0) {
                     this.intaveRotationFlags++;
                  }
               }
            } else if (this.intaveRotationFlags > 0) {
               if (!(d3 < 3.0) && time <= 5000L) {
                  this.intaveRotationFlags--;
               } else {
                  this.intaveRotationFlags = 0;
               }
            }
         }

         if (packet instanceof C0APacketAnimation) {
            this.lastSwingPacketTimer.reset();
         }
      }
   }

   public Rotation getEffectiveRotation() {
      return this.noRotation.getValue() ? this.currentRotation : ClientManagers.rotationController.getPreviousRotation();
   }

   public void renderRotationLineTrail(ShaderRenderPass shaderRenderPass) {
      RenderUtil.begin3DRender();
      Vec3 lookCustom2 = mc.thePlayer.getLookCustom2(1.0F, this.currentRotation.yaw, this.currentRotation.pitch);
      Vec3 vec = mc.thePlayer.getPositionEyes(1.0F).addVector(lookCustom2.xCoord * 3.0, lookCustom2.yCoord * 3.0, lookCustom2.zCoord * 3.0);
      float xCoord = (float)vec.xCoord;
      float yCoord = (float)vec.yCoord;
      float zCoord = (float)vec.zCoord;
      this.rotationTrailPoints.add(new double[]{(double)xCoord, (double)yCoord, (double)zCoord, (double)System.currentTimeMillis()});

      for (int i = 0; i < this.rotationTrailPoints.size(); i++) {
         if (this.isTrailPointExpired(((double[])this.rotationTrailPoints.get(i))[3])) {
            this.rotationTrailPoints.remove(i);
            i--;
         }
      }

      Line line = ClientManagers.moduleManager.line;
      RenderUtil.setGlColor(ClientManagers.moduleManager.line.blurShadowSettings.resolvePassColor(shaderRenderPass, line.color2.getColorInt((byte)0)));
      GL11.glLineWidth((float)line.auraLineWidth.getValue());
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      double[] doubles = new double[]{-2.0, -1.0, -1.0};
      double[] doubles2 = new double[]{-2.0, -1.0, -1.0};

      for (double[] doubles3 : (Iterable<double[]>)(this.rotationTrailPoints)) {
         if (!Arrays.equals(doubles, doubles2) && !Arrays.equals(doubles, doubles3)) {
            worldRenderer.pos(
                  (double)((float)doubles3[0]) - mc.getRenderManager().getRenderPosX(),
                  (double)((float)doubles3[1]) - mc.getRenderManager().getRenderPosY(),
                  (double)((float)doubles3[2]) - mc.getRenderManager().getRenderPosZ()
               )
               .endVertex();
         }

         doubles = doubles3;
      }

      instance.draw();
      RenderUtil.end3DRender();
   }

   public void renderTargetDot(ShaderRenderPass shaderRenderPass, double d) {
      RenderUtil.begin3DRender();
      if (this.dot.getValue() && this.target != null && this.lastObjectMouseOver != null && this.lastObjectMouseOver.hitVec != null && this.previousHitVec != null) {
         Vec3 vec = this.previousHitVec
            .lerp(this.lastObjectMouseOver.hitVec, d)
            .addVector(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
         float f = (float)this.dotSize.getValue() / 10.0F;
         RenderUtil.setGlColor(this.dotShadowSettings.resolvePassColor(shaderRenderPass, this.dotColor.getColorInt((byte)0)));
         RenderUtil.drawFilledBox(
            vec.xCoord - (double)f,
            vec.yCoord - (double)f,
            vec.zCoord - (double)f,
            vec.xCoord + (double)f,
            vec.yCoord + (double)f,
            vec.zCoord + (double)f
         );
      }

      RenderUtil.end3DRender();
   }

   private void resetClickPattern() {
      AbstractClickPatternMode abstractSubMode = (AbstractClickPatternMode)this.clickMode.getAbstractSubMode();
      if (abstractSubMode instanceof DeviationClickPatternMode) {
         ((DeviationClickPatternMode)abstractSubMode).resetNextClickTime();
      } else if (abstractSubMode instanceof RecordedClickPatternMode) {
         ((RecordedClickPatternMode)abstractSubMode).resetTiming();
      }
   }

   private void clearTargets() {
      this.target = null;
      this.previousTarget = null;
      this.targetSelection.setCurrentTarget((Entity)null);
      this.lastTickTargets.clear();
   }

   private boolean supportsHoldClicking() {
      return ((AbstractClickPatternMode)this.clickMode.getAbstractSubMode()).simulatesMousePress();
   }

   private boolean isRespawnDelayActive(RandomizedRangeSettings randomizedRangeSettings) {
      if (mc.thePlayer.ticksExisted - 5 >= randomizedRangeSettings.getLastIntValue() && this.ticksSinceTeleport >= randomizedRangeSettings.getLastIntValue()) {
         return false;
      } else {
         this.ticksSinceTeleport++;
         return true;
      }
   }

   private void rerollRespawnDelays() {
      this.delayClicksAfterRespawn.nextValue();
      this.delayRotationsAfterRespawn.nextValue();
   }

   private int getRotationPriority() {
      return 2;
   }

   private boolean isInterpolationEnabled() {
      return this.interpolation.getValue();
   }

   @Override
   public EntityLivingBase getTarget() {
      return this.target;
   }

   @Override
   public void setHoldingAttack(boolean holdingAttack) {
      this.holdingLeftClick = holdingAttack;
   }

   @Override
   public boolean hasLandedCrit() {
      return this.critLanded;
   }

   @Override
   public boolean isCritFixEnabled() {
      return this.critFix.getValue();
   }

   private void runScheduledLeftClick(boolean flag) {
      this.executeLeftClick(flag);
   }

   private void renderTargetDotPass(float f, ShaderRenderPass shaderRenderPass) {
      this.renderTargetDot(shaderRenderPass, (double)f);
   }

   private boolean isDelayClicksAfterRespawnVisible() {
      return !this.inInv.getValue();
   }

   private boolean isDelayRotationsAfterRespawnVisible() {
      return !this.inInv.getValue();
   }

   private boolean isDelayClicksAfterGuiVisible() {
      return !this.inInv.getValue();
   }

   private boolean isDelayRotationsAfterGuiVisible() {
      return !this.inInv.getValue();
   }

   private boolean isIntave13Visible() {
      return !this.noRotation.getValue();
   }

   private boolean isSilentMoveFixVisible() {
      return this.moveFix.getValue() && this.moveFix.isVisible();
   }

   private boolean isMoveFixVisible() {
      return !this.noRotation.getValue();
   }

   private boolean isDoubleClickFirstHitVisible() {
      return !this.clickMode.getSelectedValue().equals("Smart");
   }

   private boolean isPreHitEnduranceVisible() {
      return this.smartPreHit.getValue() && this.smartPreHit.isVisible();
   }

   private boolean isPreHitLoseDelayVisible() {
      return this.smartPreHit.getValue() && this.smartPreHit.isVisible();
   }

   private boolean isPreHitPredictionVisible() {
      return this.smartPreHit.getValue() && this.smartPreHit.isVisible();
   }

   private boolean isUnblockAacVisible() {
      return this.blockMode.getSelectedValue().equals("AAC") || this.blockMode.getSelectedValue().equals("AACOld");
   }

   private boolean isOnlyWhileHurtVisible() {
      return !this.blockMode.getSelectedValue().equals("None");
   }

   private boolean isInteractAutoBlockVisible() {
      return !this.blockMode.getSelectedValue().equals("None");
   }

   private boolean isSmartBlockVisible() {
      return !this.blockMode.getSelectedValue().equals("None");
   }

   private boolean isMaxTargetsVisible() {
      return this.attackMode.getSelectedValue().equals("Multi");
   }

   private boolean isAimThroughWallsRangeVisible() {
      return this.aimThroughWalls.isVisible() && this.aimThroughWalls.getValue();
   }

   private boolean isThroughWallsRangeVisible() {
      return this.throughWallsMode.getSelectedValue().equals("AllBlocks");
   }

   private boolean isAiSettingsGroupVisible() {
      return this.randomizeMode.getSelectedValue().equals("AI");
   }

   private boolean isTurboPlayerResponseVisible() {
      return this.randomizeMode.getSelectedValue().equals("Turbo");
   }

   private boolean isTurboAimSpeedVisible() {
      return this.randomizeMode.getSelectedValue().equals("Turbo");
   }

   private boolean isTrashcodeBypassVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isNoYawOutOfReachVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isMaxAimpointAngleDiffVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isMaxOrthogonalMovementVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isAdditionalInaccuracyVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isRotationPitchOffsetSpeedVisible() {
      if (this.randomizeMode.getSelectedValue().equals("Noise2")) {
         if (this.rotationPitchOffset.getMaxValue(true) != this.rotationPitchOffset.getMinValue(true)) {
            return true;
         }
      }

      return false;
   }

   private boolean isRotationYawOffsetSpeedVisible() {
      if (this.randomizeMode.getSelectedValue().equals("Noise2")) {
         if (this.rotationYawOffset.getMaxValue(true) != this.rotationYawOffset.getMinValue(true)) {
            return true;
         }
      }

      return false;
   }

   private boolean isRotationPitchOffsetVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isRotationYawOffsetVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isRotationOffsetGroupVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isSuperCorrectionDelayVisible() {
      if (this.randomizeMode.getSelectedValue().equals("Noise2")) {
         if (this.supercorrectionProbability.getValue(true) > 0.0) {
            return true;
         }
      }

      return false;
   }

   private boolean isSuperCorrectionProbabilityVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isSuperCorrectionGroupVisible() {
      if (this.randomizeMode.getSelectedValue().equals("Noise2")) {
         if (this.reactionTime.getMaxValue() > 0.0) {
            return true;
         }
      }

      return false;
   }

   private boolean isPredictionInaccuracySmoothnessVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isPredictionInaccuracyVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isAttentionVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isLookaheadTimeVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isReactionTimeVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isDecelerationPostVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isDecelerationPreVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isNoise2AimSpeedVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise2");
   }

   private boolean isNoiseTestVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise");
   }

   private boolean isAimProbabilityWhenNotNeededVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise");
   }

   private boolean isAdditionalAimSpeedIfNeededVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise");
   }

   private boolean isAdditionalMinAimSpeedVisible() {
      return this.randomizeMode.getSelectedValue().equals("Noise");
   }

   private boolean isYRelDistVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isXRelDistVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isMaxYAbsDistVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isMaxXAbsDistVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isMinAbsDistVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isPitchOutliersAddVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced") && this.pitchOutliers.getValue();
   }

   private boolean isPitchOutliersVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isSmartRotateVisible() {
      return this.randomizeMode.getSelectedValue().equals("Advanced");
   }

   private boolean isNearestHitVecHorizontalVisible() {
      return this.rotationMode.getSelectedValue().equalsIgnoreCase("NearestHitVec");
   }

   private boolean isNearestHitVecVerticalVisible() {
      return this.rotationMode.getSelectedValue().equalsIgnoreCase("NearestHitVec");
   }

   private boolean isBestHitVecVerticalVisible() {
      return this.rotationMode.getSelectedValue().equalsIgnoreCase("BestHitVec");
   }

   private boolean isMinDistanceCombinedVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None") && this.minDistanceToBestVecCalculation.getSelectedValue().equalsIgnoreCase("Combined");
   }

   private boolean isMinDistancePitchVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None") && this.minDistanceToBestVecCalculation.getSelectedValue().equalsIgnoreCase("Separate");
   }

   private boolean isMinDistanceYawVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None") && this.minDistanceToBestVecCalculation.getSelectedValue().equalsIgnoreCase("Separate");
   }

   private boolean isMinDistanceCalculationVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None");
   }

   private boolean isMinFixRotationAngleVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None");
   }

   private boolean isMaxFixRotationReachVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None");
   }

   private boolean isFixRotationChanceVisible() {
      return !this.fixRotationMode.getSelectedValue().equals("None");
   }

   private boolean isPitchAccelerationVisible() {
      return !this.isInterpolationEnabled()
         && !this.randomizeMode.getSelectedValue().contains("Noise")
         && !this.randomizeMode.getSelectedValue().equals("Turbo")
         && !this.randomizeMode.getSelectedValue().equals("AI");
   }

   private boolean isYawAccelerationVisible() {
      return !this.isInterpolationEnabled()
         && !this.randomizeMode.getSelectedValue().contains("Noise")
         && !this.randomizeMode.getSelectedValue().equals("Turbo")
         && !this.randomizeMode.getSelectedValue().equals("AI");
   }

   private boolean isYawSpeedVisible() {
      return !this.randomizeMode.getSelectedValue().contains("Noise") && !this.randomizeMode.getSelectedValue().equals("Turbo") && !this.randomizeMode.getSelectedValue().equals("AI");
   }

   private boolean isHurtTimeToHitWithReachVisible() {
      return this.rangeMode.getSelectedValue().equals("IntervalRange");
   }

   private boolean isMinReachToIntervalHitVisible() {
      return this.rangeMode.getSelectedValue().equals("IntervalRange");
   }

   private boolean isIntervalVisible() {
      return this.rangeMode.getSelectedValue().equals("IntervalRange");
   }

   private boolean isActionToTrackIntervalVisible() {
      return this.rangeMode.getSelectedValue().equals("IntervalRange");
   }

   private boolean isHazeMaxVisible() {
      return this.rangeMode.getSelectedValue().equals("HazeRange");
   }

}
