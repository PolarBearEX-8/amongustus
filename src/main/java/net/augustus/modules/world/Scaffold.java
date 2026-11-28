package net.augustus.modules.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.modes.anticheat.intave14.scaffold.Intave14TimeBlockPlaceCheck;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.listeners.SprintListener;
import net.augustus.events.EventMove;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.modules.world.scaffold.BlockPlacementData;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.utils.input.MouseInputUtil;

import net.augustus.settings.RangeSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.events.EventSafeWalk;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.SafeWalkListener;
import net.augustus.utils.TuplePair;
import net.augustus.utils.rotation.RotationKinematicState;
import net.augustus.utils.rotation.RotationController;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.rotation.BezierCurve2D;
import net.augustus.events.listeners.AIMoveSpeedListener;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.events.EventSwing;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.render.RenderUtil;
import net.augustus.utils.TickCounter;
import net.augustus.events.EventAIMoveSpeed;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.Augustus;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.clickgui.ClickGuiScreen;
import net.augustus.events.EventUpdate;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.world.scaffold.ScaffoldRaycastResult;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.events.EventSprint;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.events.listeners.SwingListener;
import net.augustus.utils.rotation.BezierRotationStepper;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Scaffold
   extends Module
   implements MoveListener,
   SprintListener,
   ClickListener,
   TickListener,
   OrientCameraListener,
   MoveEntityListener,
   MoveInputListener,
   MotionListener,
   RenderListener,
   RayCastListener,
   PacketListener,
   SafeWalkListener,
   SwingListener,
   SilentSlotListener,
   AIMoveSpeedListener,
   UpdateListener {
   private final SettingGroup rotations;
   private final SettingGroup rotationSpeed;
   private final BooleanValue modeNew;
   private final DoubleValue aimSpeed;
   private final RandomizedRangeSettings yawSpeed;
   private final RandomizedRangeSettings pitchSpeed;
   private final RandomizedRangeSettings yawAcceleration;
   private final RandomizedRangeSettings pitchAcceleration;
   private final BooleanValue customBlockClutchRotationSpeeds;
   private final SettingGroup blockClutchRotationSpeed;
   private final BooleanValue blockClutchModeNew;
   private final DoubleValue blockClutchAimSpeed;
   private final RandomizedRangeSettings blockClutchYawSpeed;
   private final RandomizedRangeSettings blockClutchPitchSpeed;
   private final RandomizedRangeSettings blockClutchYawAcceleration;
   private final RandomizedRangeSettings blockClutchPitchAcceleration;
   private final RandomizedRangeSettings keepRotationsAfterModuleDisable;
   private final SettingGroup rotationRandomization;
   private final BooleanValue randomizePitch;
   private final DoubleValue randomizePitchChanceWhileYawRotation;
   private final DoubleValue randomizePitchChanceOnBlock;
   private final DoubleValue randomizePitchChanceInAir;
   private final BooleanValue otherPitchRandomization;
   private final RandomizedRangeSettings randomizePitchVal;
   private final RandomizedRangeSettings randomizePitchInterval;
   private final BooleanValue randomizeYaw;
   private final DoubleValue randomizeYawChanceWhilePitchRotation;
   private final DoubleValue randomizeYawChanceOnBlock;
   private final DoubleValue randomizeYawChanceOnAir;
   private final BooleanValue otherYawRandomization;
   private final RandomizedRangeSettings randomizeYawVal;
   private final RandomizedRangeSettings randomizeYawInterval;
   private final SettingGroup staticPitchSettings;
   private final BooleanValue staticPitchBool;
   private final BooleanValue staticPitchAtJump;
   private final BooleanValue staticPitchRandomizeOnlyAtStart;
   private final BooleanValue ignoreStaticPitchForBlockClutches;
   private final RandomizedRangeSettings staticPitch;
   private final BooleanValue staticPitchBoolDiagonal;
   private final BooleanValue staticPitchAtJumpDiagonal;
   private final BooleanValue staticPitchRandomizeOnlyAtStartDiagonal;
   private final BooleanValue ignoreStaticPitchForBlockClutchesDiagonal;
   private final RandomizedRangeSettings staticPitchDiagonal;
   private final BooleanValue minimalMouseMovement;
   private final BooleanValue reduceYawRotation;
   private final BooleanValue strictYaw;
   private final RandomizedRangeSettings strictYawIncorrectFactor;
   private final RandomizedRangeSettings startDelayAfterDirectionChange;
   private final BooleanValue ignoreStartDelayAfterDirectionChangeAtBlockClutch;
   private final BooleanValue changeDirectionOnlyIfBlockUnderYou;
   private final BooleanValue rotateWithMovement;
   public final BooleanValue rotateWithMovementOnlyOrthogonal;
   public final BooleanValue improve;
   public final BooleanValue improveOnlyAtClutch;
   public final BooleanValue extraBlockSearch;
   public final BooleanValue extraBlockSearchOnlyAtClutch;
   public final SettingGroup strafeSettings;
   public final BooleanValue strafe;
   public final BooleanValue strafeOnlyWhileJump;
   public final RandomizedRangeSettings strafeDegree;
   public final RandomizedRangeSettings strafeWidth;
   public final BooleanValue correctRotationWhileBuilding;
   public final BooleanValue correctStrafeWithKeyBinds;
   private final BooleanValue correctStrafeWithKeyBindsCage;
   private final RandomizedRangeSettings correctStrafeWithKeyBindsWidth;
   private final RandomizedRangeSettings correctStrafeWithKeyBindsDuration;
   public final RandomizedRangeSettings correctStrafeWithKeyBindsMiddle;
   public final RangeSetting correctStrafeWithKeyBindsMiddleExclusion;
   public final RandomizedRangeSettings correctStrafeWithKeyBindsDelayToRecalculateMiddle;
   public final BooleanValue startAtEdge;
   public final RandomizedRangeSettings startAtEdgeDelay;
   public final BooleanValue ignoreStartAtEdgeAtBlockClutch;
   public final BooleanValue edgeRotateCheck;
   public final BooleanValue edgePlaceCheck;
   private final StringValue flickMode;
   private final BooleanValue flickUserStrafeSettingForForwardLooking;
   public final BooleanValue otherRotation;
   public final BooleanValue recycleRotation;
   public final BooleanValue intave;
   public final BooleanValue intaveSafe;
   public final BooleanValue intaveFast;
   public final StringValue pitchSelection;
   public final DoubleValue maxPitchCorrection;
   public final DoubleValue maxPitchCorrectionAtBlockClutch;
   public final BooleanValue expand;
   public final DoubleValue expandLength;
   public final SettingGroup clicking;
   public final BooleanValue noSwing;
   public final BooleanValue serverSideNoSwing;
   public final RandomizedRangeSettings cps;
   private final StringValue dcpsMode;
   public final RandomizedRangeSettings dcps;
   public final BooleanValue forcePlaceIfYouWouldFallDown;
   public final BooleanValue alwaysPlaceWhenPossible;
   private final BooleanValue clickPause;
   private final BooleanValue delayClick;
   public final BooleanValue oneClick;
   private final BooleanValue stopClickingAtSneaking;
   public final DoubleValue maxYawRotationDeltaToPlaceBlocks;
   public final DoubleValue maxPitchRotationDeltaToPlaceBlocks;
   public final DoubleValue maxYawRotationDeltaToPlaceBlocksAtBlockClutch;
   public final DoubleValue maxPitchRotationDeltaToPlaceBlocksAtBlockClutch;
   public final SettingGroup movement;
   public final BooleanValue removeSneakStop;
   public final SettingGroup sneaking;
   public final BooleanValue sneak;
   public final BooleanValue timeJumpForOneStackForward;
   public final BooleanValue oneStackForward;
   public final RangeSetting minOffGroundSneakTickForward;
   public final RangeSetting maxOffGroundSneakTickForward;
   public final BooleanValue oneStackDiagonal;
   public final RangeSetting minOffGroundSneakTickDiagonal;
   public final RangeSetting maxOffGroundSneakTickDiagonal;
   public final RandomizedRangeSettings sneakTicks;
   public final RangeSetting startSneakDistToBlock;
   public final RangeSetting sneakBlocksForward;
   public final RangeSetting sneakBlocksDiagonal;
   public final DoubleValue sneakSlowDown;
   public final BooleanValue earlySneakCalculation;
   public final BooleanValue earlySneakCalculationOnlyAtSpeedEffect;
   public final StringValue adhsMode;
   public StringValue moveFix;
   public final BooleanValue moveFixIntave;
   public final StringValue mode;
   public final BooleanValue jumpSprint;
   public final BooleanValue hover;
   public final BooleanValue stopAtStart;
   public final BooleanValue safeWalkNoBlocks;
   public final BooleanValue sneakNoBlocks;
   public final BooleanValue stopNoBlocks;
   public final BooleanValue reduceVelocity;
   public final BooleanValue ignoreSpeedMotion;
   private final BooleanValue sneakAtRotation;
   private final BooleanValue stopAtRotation;
   public final SettingGroup godBridging;
   public final BooleanValue godBridge;
   public final BooleanValue disableGodbridgeSafetyFeature;
   public final RandomizedRangeSettings resetGodBridgeAfterXJumps;
   public final RandomizedRangeSettings jumpAfterXBlocks;
   public final RandomizedRangeSettings jumpDelayAfterPlace;
   public final RandomizedRangeSettings startDelay;
   public final SettingGroup moonWalking;
   public final BooleanValue moonWalk;
   public final BooleanValue onlyOnGround;
   public final RandomizedRangeSettings moonWalkWideness;
   public final RangeSetting moonWalkStartDistToBlock;
   public final RandomizedRangeSettings moonWalkMiddle;
   public final RandomizedRangeSettings delayToRecalculateMiddle;
   public final RandomizedRangeSettings postPlacementStrafeTicks;
   public final RandomizedRangeSettings minMoonwalkTicks;
   public final RandomizedRangeSettings directionChangeDelay;
   public final SettingGroup sameYBlockGroup;
   public final BooleanValue sameYBlock;
   public final BooleanValue stopMovementAfterJump;
   public final SettingGroup sameYGroup;
   public final BooleanValue sameY;
   public final BooleanValue autoJump;
   public final BooleanValue intaveFast2;
   public final BooleanValue tellyBridge;
   public final SettingGroup safeWalkGroup;
   public final BooleanValue safeWalk;
   public final BooleanValue safeWalkAir;
   public final BooleanValue safeWalkOnlyDiagonal;
   public final SettingGroup jumpGroup;
   public final BooleanValue jump;
   public final BooleanValue jumpOnlyWhileSpacePressed;
   public final RandomizedRangeSettings jumpAfterXBlocksForward;
   public final RandomizedRangeSettings jumpAfterXBlocksDiagonal;
   public final SettingGroup timeGroup;
   public final DoubleValue timer;
   public final BooleanValue timerDrop;
   public final RangeSetting timerSpeed;
   public final SettingGroup motionMultiplier;
   public final RandomizedRangeSettings motionMultiplierAirForward;
   public final RandomizedRangeSettings motionMultiplierAirStrafe;
   public final RandomizedRangeSettings motionMultiplierGroundForward;
   public final RandomizedRangeSettings motionMultiplierGroundStrafe;
   public final SettingGroup miscellaneous;
   public final StringValue towerMode;
   public final BooleanValue towerWhileMoving;
   public final DoubleValue constantMotion;
   public final DoubleValue jumpMotion;
   public final StringValue silentMode;
   public final BooleanValue useBiggestStackSilent;
   public final RandomizedRangeSettings silentStartDelay;
   public final RandomizedRangeSettings silentSwitchDelay;
   public final RandomizedRangeSettings stackCountToStartSwitchProcess;
   public final RandomizedRangeSettings backSwitchTickDelay;
   public final BooleanValue dynamicBackSwitchTickDelay;
   public final SettingGroup visuals;
   private final BooleanValue thirdPersonMode;
   public final BooleanValue hideSneakAnimation;
   public final BooleanValue blockCount;
   private final DoubleValue x;
   private final DoubleValue y;
   private final DoubleValue scale;
   private final BooleanValue backGround;
   private final ColorSetting backgroundColor;
   private final DoubleValue roundFactor;
   public final BlurShadowSettings blockCount2;
   public final BooleanValue esp;
   public final ColorSetting espColor;
   public final BlurShadowSettings esp2;
   private BlockPos lastPlacedBlockPos;
   private BlockPos targetBlockPos;
   private int tellyPredictedBlockDistance;
   private BlockPlacementData blockPlacementData;
   private MovingObjectPosition overrideRayTrace;
   private final RotationUtil rotationUtil;
   private final BezierRotationStepper bezierRotationStepper;
   private BezierCurve2D bezierCurve;
   private RotationKinematicState rotationKinematicState;
   private Rotation lastBezierRotation;
   private int bezierTickCounter;
   private final TimeHelper lastRightClickTimer;
   private final TimeHelper lastPlaceTimer;
   private final TimeHelper enableTimer;
   private int sneakHoldTicks;
   private int blocksSinceLastSneak;
   private int placedBlocksSinceSneak;
   private double sneakEdgeExpandDist;
   private double ncpTowerStartY;
   private int offGroundTicks;
   private boolean wasSneakingLastTick;
   private boolean isOverSupportBlock;
   private int sneakIntervalBlocksForward;
   private int sneakIntervalBlocksDiagonal;
   private final Intave14TimeBlockPlaceCheck intave14TimeBlockPlaceCheck;
   private float strafeYawOffset;
   private int hotbarBlockCount;
   private final BridgeClickController bridgeClickController;
   private int moonWalkStrafeDir;
   private int keyBindStrafeCorrectionDir;
   private double moonWalkEdgeExpandDist;
   private float lastTargetYaw;
   private int ticksSinceLastPlace;
   private int moonWalkTicks;
   private int moonWalkDirChangeTicks;
   private int lastAppliedStrafeInput;
   private boolean wasOverMoonWalkBlock;
   private int minOffGroundSneakForward;
   private int maxOffGroundSneakForward;
   private int minOffGroundSneakDiagonal;
   private int maxOffGroundSneakDiagonal;
   private double expandAirYOffset;
   private boolean godBridgeResetting;
   private int godBridgePlacedBlocks;
   private int godBridgeSetupTicks;
   private int godBridgeJumpCount;
   private int ticksSinceGodBridgePlace;
   private int godBridgeSneakSetupTicks;
   private double lastGodBridgePosX;
   private double lastGodBridgePosZ;
   private boolean wasCardinalDirection;
   private boolean safeWalkEdgeDetected;
   private boolean godBridgeJumpHeld;
   private int blocksPlacedSinceJump;
   private boolean yawRandomizationTriggered;
   private boolean pitchRandomizationTriggered;
   private final TickCounter yawRandomizeCounter;
   private final TickCounter pitchRandomizeCounter;
   private boolean rightClickHeldOnGround;
   private Vec3 velocityAnchorPos;
   private int watchdogTowerTick;
   private boolean hoverJumpStarted;
   private boolean hoverOffsetActive;
   private final TickCounter strafeCorrectionCounter;
   private final TickCounter strafeMiddleRecalcCounter;
   private BlockPos previousTargetBlockPos;
   private int strictYawErrorSign;
   private int lastStrictYawSector;
   private boolean strafeCorrectionActive;
   private final TickCounter silentSwitchCounter;
   private boolean firstSilentSwitchPending;
   private int selectedBlockSlot;
   private final HashMap slotMinStackThresholds;
   private int stopAtStartDelayMs;
   private int lastMoonWalkSide;
   private double strafeCorrectionMiddle;
   private final TickCounter moonWalkMiddleRecalcCounter;
   private final TickCounter directionChangeDelayCounter;
   private boolean directionChangePending;
   private boolean reducingVelocity;
   private boolean waitingForStartEdge;
   private boolean hasPlacedFirstBlock;
   private int ticksSinceFirstPlace;
   private int startEdgeSneakTicks;
   private final TickCounter startAtEdgeDelayCounter;
   private boolean initialStrafeDirectionPending;
   private int savedThirdPersonView;
   private int ticksSinceDirectionChange;
   private int postDirectionChangeSneakTicks;
   private boolean keepInitialPitch;
   private float cachedStrictRotationYaw;
   private float cachedStrictMoveYaw;
   private boolean sneakingOnGroundForEdge;
   private boolean placedBlockSinceEnable;
   private float minimalMouseYawOffset;
   private float minimalMousePitchOffset;
   private TimeHelper minimalMouseMoveTimer;
   private TickCounter minimalMouseMoveCounter;
   private int minimalMouseMoveIntervalMs;
   private int minimalMouseMoveDurationTicks;
   private boolean sameYBlockJumpActive;
   private double sameYBlockBaseY;
   private boolean sameYBlockResetPending;
   private boolean sameYBlockTopPlaced;
   private boolean stopMovementUntilLand;
   private double intaveBlockPlacePenalty;

   public Scaffold() {
      super("Scaffold", "Gerüst", new Color(182, 194, 207), Categorys.WORLD);
      this.rotations = new SettingGroup(this, "Rotations", true);
      this.rotationSpeed = new SettingGroup(this, "RotationSpeed", true, this.rotations);
      this.modeNew = (BooleanValue)new BooleanValue(this, "ModeNew", false, this.rotationSpeed).withVisibility(() -> this.isModeNewVisible());
      this.aimSpeed = new DoubleValue(this, "AimSpeed", 50.0, 0.0, 100.0, 0, this.rotationSpeed).visibleWhen(() -> this.isAimSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "YawSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.rotationSpeed)).withoutNotInARow();
      SettingVisibilityCondition condition3 = () -> {
         return !this.modeNew.isVisible() || !this.modeNew.getValue();
      };
      this.yawSpeed = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(condition3);
      this.pitchSpeed = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "PitchSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.rotationSpeed)).withoutNotInARow().withVisibility(() -> this.isPitchSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings3 = (new RandomizedRangeSettings(this, "YawAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.rotationSpeed)).withoutNotInARow();
      SettingVisibilityCondition condition5 = () -> {
         return !this.modeNew.isVisible() || !this.modeNew.getValue();
      };
      this.yawAcceleration = (RandomizedRangeSettings)randomizedRangeSettings3.withVisibility(condition5);
      this.pitchAcceleration = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "PitchAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.rotationSpeed)).withoutNotInARow().withVisibility(() -> this.isPitchAccelerationVisible());
      this.customBlockClutchRotationSpeeds = new BooleanValue(this, "CustomBlockClutchRotationSpeeds", false, this.rotationSpeed);
      SettingGroup blockClutchRotationSpeed2 = new SettingGroup(this, "BlockClutchRotationSpeed", true, this.rotationSpeed);
      BooleanValue booleanValue2 = this.customBlockClutchRotationSpeeds;
      BooleanValue booleanValue3 = booleanValue2;
      this.blockClutchRotationSpeed = blockClutchRotationSpeed2.visibleWhen(() -> booleanValue3.getValue());
      this.blockClutchModeNew = (BooleanValue)((BooleanValue)new BooleanValue(this, "BlockClutchModeNew", false, this.blockClutchRotationSpeed).withDisplayName("ModeNew")).withVisibility(() -> this.isBlockClutchModeNewVisible());
      this.blockClutchAimSpeed = new DoubleValue(this, "BlockClutchAimSpeed", 50.0, 0.0, 100.0, 0, this.blockClutchRotationSpeed).named("AimSpeed").visibleWhen(() -> this.isBlockClutchAimSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings6 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BlockClutchYawSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.blockClutchRotationSpeed)).withoutNotInARow().withDisplayNamePrefix("YawSpeed");
      SettingVisibilityCondition condition10 = () -> {
         return !this.blockClutchModeNew.isVisible() || !this.blockClutchModeNew.getValue();
      };
      this.blockClutchYawSpeed = (RandomizedRangeSettings)randomizedRangeSettings6.withVisibility(condition10);
      this.blockClutchPitchSpeed = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BlockClutchPitchSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.blockClutchRotationSpeed)).withoutNotInARow().withDisplayNamePrefix("PitchSpeed")).withVisibility(() -> this.isBlockClutchPitchSpeedVisible());
      RandomizedRangeSettings randomizedRangeSettings10 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "BlockClutchYawAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.blockClutchRotationSpeed).withDisplayNamePrefix("YawAcceleration")).withoutNotInARow();
      SettingVisibilityCondition condition12 = () -> {
         return !this.blockClutchModeNew.isVisible() || !this.blockClutchModeNew.getValue();
      };
      this.blockClutchYawAcceleration = (RandomizedRangeSettings)randomizedRangeSettings10.withVisibility(condition12);
      RandomizedRangeSettings randomizedRangeSettings12 = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "BlockClutchPitchAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.blockClutchRotationSpeed).withDisplayNamePrefix("PitchAcceleration")).withoutNotInARow();
      SettingVisibilityCondition condition13 = () -> {
         return !this.blockClutchModeNew.isVisible() || !this.blockClutchModeNew.getValue();
      };
      this.blockClutchPitchAcceleration = (RandomizedRangeSettings)randomizedRangeSettings12.withVisibility(condition13);
      RandomizedRangeSettings randomizedRangeSettings13 = (new RandomizedRangeSettings(this, "KeepRotationsAfterModuleDisable", 3.0, 5.0, 0.0, 20.0, 0, this.rotationSpeed)).withoutNotInARow();
      SettingVisibilityCondition condition14 = () -> {
         return this.moveFix.getSelectedValue().equals("Default");
      };
      this.keepRotationsAfterModuleDisable = (RandomizedRangeSettings)randomizedRangeSettings13.withVisibility(condition14);
      this.rotationRandomization = new SettingGroup(this, "RotationRandomization", true, this.rotations);
      this.randomizePitch = new BooleanValue(this, "RandomizePitch", false, this.rotationRandomization);
      this.randomizePitchChanceWhileYawRotation = new DoubleValue(this, "RandomizePitchChanceWhileYawRotation", 100.0, 0.0, 100.0, 0, this.rotationRandomization).visibleWhen(() -> this.isRandomizePitchChanceWhileYawVisible());
      this.randomizePitchChanceOnBlock = new DoubleValue(this, "RandomizePitchChanceOnBlock", 100.0, 0.0, 100.0, 0, this.rotationRandomization).visibleWhen(() -> this.isRandomizePitchChanceOnBlockVisible());
      this.randomizePitchChanceInAir = new DoubleValue(this, "RandomizePitchChanceInAir", 100.0, 0.0, 100.0, 0, this.rotationRandomization).visibleWhen(() -> this.isRandomizePitchChanceInAirVisible());
      BooleanValue booleanValue6 = new BooleanValue(this, "OtherPitchRandomization", false, this.rotationRandomization);
      SettingVisibilityCondition condition18 = () -> {
         return this.randomizePitch.getValue() && this.randomizePitch.isVisible();
      };
      this.otherPitchRandomization = (BooleanValue)booleanValue6.withVisibility(condition18);
      RandomizedRangeSettings randomizedRangeSettings14 = (new RandomizedRangeSettings(this, "RandomizePitchVal", -1.0, 1.0, -10.0, 10.0, 2, this.rotationRandomization)).withoutNotInARow();
      SettingVisibilityCondition condition19 = () -> {
         return !this.otherPitchRandomization.getValue() && this.randomizePitch.getValue() && this.randomizePitch.isVisible();
      };
      this.randomizePitchVal = (RandomizedRangeSettings)randomizedRangeSettings14.withVisibility(condition19);
      this.randomizePitchInterval = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "RandomizePitchInterval", 2.0, 4.0, 0.0, 20.0, 0, this.rotationRandomization).withVisibility(() -> this.isRandomizePitchIntervalVisible());
      this.randomizeYaw = new BooleanValue(this, "RandomizeYaw", false, this.rotationRandomization);
      this.randomizeYawChanceWhilePitchRotation = new DoubleValue(this, "RandomizeYawChanceWhilePitchRotation", 100.0, 0.0, 100.0, 0, this.rotationRandomization).visibleWhen(() -> this.isRandomizeYawChanceWhilePitchVisible());
      this.randomizeYawChanceOnBlock = new DoubleValue(this, "RandomizeYawChanceOnBlock", 100.0, 0.0, 100.0, 0, this.rotationRandomization).visibleWhen(() -> this.isRandomizeYawChanceOnBlockVisible());
      DoubleValue randomizeYawChanceOnAir2 = new DoubleValue(this, "RandomizeYawChanceOnAir", 100.0, 0.0, 100.0, 0, this.rotationRandomization);
      SettingVisibilityCondition condition23 = () -> {
         return this.randomizeYaw.getValue() && this.randomizeYaw.isVisible();
      };
      this.randomizeYawChanceOnAir = randomizeYawChanceOnAir2.visibleWhen(condition23);
      this.otherYawRandomization = (BooleanValue)new BooleanValue(this, "OtherYawRandomization", false, this.rotationRandomization).withVisibility(() -> this.isOtherYawRandomizationVisible());
      this.randomizeYawVal = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "RandomizeYawVal", -1.0, 1.0, -10.0, 10.0, 2, this.rotationRandomization)).withoutNotInARow().withVisibility(() -> this.isRandomizeYawValVisible());
      this.randomizeYawInterval = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "RandomizeYawInterval", 2.0, 4.0, 0.0, 20.0, 0, this.rotationRandomization).withVisibility(() -> this.isRandomizeYawIntervalVisible());
      this.staticPitchSettings = new SettingGroup(this, "StaticPitchSettings", true, this.rotations);
      this.staticPitchBool = (BooleanValue)new BooleanValue(this, "StaticPitchBool", false, this.staticPitchSettings).withDisplayName("StaticPitch");
      BooleanValue booleanValue9 = new BooleanValue(this, "StaticPitchAtJump", false, this.staticPitchSettings);
      booleanValue2 = this.staticPitchBool;
      BooleanValue booleanValue10 = booleanValue2;
      this.staticPitchAtJump = (BooleanValue)booleanValue9.withVisibility(() -> booleanValue10.getValue());
      BooleanValue booleanValue11 = new BooleanValue(this, "StaticPitchRandomizeOnlyAtStart", false, this.staticPitchSettings);
      booleanValue2 = this.staticPitchBool;
      BooleanValue booleanValue12 = booleanValue2;
      this.staticPitchRandomizeOnlyAtStart = (BooleanValue)booleanValue11.withVisibility(() -> booleanValue12.getValue());
      BooleanValue booleanValue13 = new BooleanValue(this, "IgnoreStaticPitchForBlockClutches", false, this.staticPitchSettings);
      booleanValue2 = this.staticPitchBool;
      BooleanValue booleanValue14 = booleanValue2;
      this.ignoreStaticPitchForBlockClutches = (BooleanValue)booleanValue13.withVisibility(() -> booleanValue14.getValue());
      RandomizedRangeSettings randomizedRangeSettings18 = (new RandomizedRangeSettings(this, "StaticPitch", 80.0, 80.0, 0.0, 90.0, 2, this.staticPitchSettings)).withoutNotInARow();
      String str8 = "MaxStaticPitchForward";
      RandomizedRangeSettings randomizedRangeSettings19 = randomizedRangeSettings18.withRangeNames("MinStaticPitchForward", str8);
      booleanValue2 = this.staticPitchBool;
      BooleanValue booleanValue15 = booleanValue2;
      this.staticPitch = (RandomizedRangeSettings)randomizedRangeSettings19.withVisibility(() -> booleanValue15.getValue());
      this.staticPitchBoolDiagonal = (BooleanValue)new BooleanValue(this, "StaticPitchBoolDiagonal", false, this.staticPitchSettings).withDisplayName("StaticPitchDiagonal");
      BooleanValue booleanValue17 = new BooleanValue(this, "StaticPitchAtJumpDiagonal", false, this.staticPitchSettings);
      booleanValue2 = this.staticPitchBoolDiagonal;
      BooleanValue booleanValue18 = booleanValue2;
      this.staticPitchAtJumpDiagonal = (BooleanValue)booleanValue17.withVisibility(() -> booleanValue18.getValue());
      BooleanValue booleanValue19 = new BooleanValue(this, "StaticPitchRandomizeOnlyAtStartDiagonal", false, this.staticPitchSettings);
      booleanValue2 = this.staticPitchBoolDiagonal;
      BooleanValue booleanValue20 = booleanValue2;
      this.staticPitchRandomizeOnlyAtStartDiagonal = (BooleanValue)booleanValue19.withVisibility(() -> booleanValue20.getValue());
      BooleanValue booleanValue21 = new BooleanValue(this, "IgnoreStaticPitchForBlockClutchesDiagonal", false, this.staticPitchSettings);
      booleanValue2 = this.staticPitchBoolDiagonal;
      BooleanValue booleanValue22 = booleanValue2;
      this.ignoreStaticPitchForBlockClutchesDiagonal = (BooleanValue)booleanValue21.withVisibility(() -> booleanValue22.getValue());
      RandomizedRangeSettings randomizedRangeSettings20 = (new RandomizedRangeSettings(this, "StaticPitchDiagonal", 80.0, 80.0, 0.0, 90.0, 2, this.staticPitchSettings)).withoutNotInARow();
      booleanValue2 = this.staticPitchBoolDiagonal;
      BooleanValue booleanValue23 = booleanValue2;
      this.staticPitchDiagonal = (RandomizedRangeSettings)randomizedRangeSettings20.withVisibility(() -> booleanValue23.getValue());
      BooleanValue booleanValue24 = new BooleanValue(this, "MinimalMouseMovement", false, this.staticPitchSettings);
      SettingVisibilityCondition condition35 = () -> {
         return this.staticPitchBool.getValue() || this.staticPitchBoolDiagonal.getValue();
      };
      this.minimalMouseMovement = (BooleanValue)booleanValue24.withVisibility(condition35);
      this.reduceYawRotation = new BooleanValue(this, "ReduceYawRotation", false, this.rotations);
      this.strictYaw = new BooleanValue(this, "StrictYaw", false, this.rotations);
      RandomizedRangeSettings randomizedRangeSettings21 = (new RandomizedRangeSettings(this, "StrictYawIncorrectFactor", 1.5, 3.0, 0.0, 6.0, 2, this.rotations)).withoutNotInARow();
      booleanValue2 = this.strictYaw;
      BooleanValue booleanValue25 = booleanValue2;
      this.strictYawIncorrectFactor = (RandomizedRangeSettings)randomizedRangeSettings21.withVisibility(() -> booleanValue25.getValue());
      RandomizedRangeSettings randomizedRangeSettings22 = (new RandomizedRangeSettings(this, "StartDelayAfterDirectionChange", 0.0, 0.0, 0.0, 20.0, 0, this.rotations)).withoutNotInARow();
      booleanValue2 = this.strictYaw;
      BooleanValue booleanValue26 = booleanValue2;
      this.startDelayAfterDirectionChange = (RandomizedRangeSettings)randomizedRangeSettings22.withVisibility(() -> booleanValue26.getValue());
      this.ignoreStartDelayAfterDirectionChangeAtBlockClutch = (BooleanValue)new BooleanValue(this, "IgnoreStartDelayAfterDirectionChangeAtBlockClutch", false, this.rotations).withVisibility(() -> this.isIgnoreDirChangeDelayClutchVisible());
      BooleanValue booleanValue28 = new BooleanValue(this, "ChangeDirectionOnlyIfBlockUnderYou", false, this.rotations);
      booleanValue2 = this.strictYaw;
      BooleanValue booleanValue29 = booleanValue2;
      this.changeDirectionOnlyIfBlockUnderYou = (BooleanValue)booleanValue28.withVisibility(() -> booleanValue29.getValue());
      this.rotateWithMovement = new BooleanValue(this, "RotateWithMovement", true, this.rotations);
      booleanValue2 = new BooleanValue(this, "RotateWithMovementOnlyOrthogonal", false, this.rotations);
      BooleanValue rotateWithMovement2 = this.rotateWithMovement;
      this.rotateWithMovementOnlyOrthogonal = (BooleanValue)booleanValue2.withVisibility(() -> rotateWithMovement2.getValue());
      this.improve = new BooleanValue(this, "Improve", true, this.rotations);
      booleanValue2 = new BooleanValue(this, "ImproveOnlyAtClutch", false, this.rotations);
      this.improveOnlyAtClutch = (BooleanValue)booleanValue2.withVisibility(() -> this.isImproveOnlyAtClutchVisible());
      this.extraBlockSearch = new BooleanValue(this, "ExtraBlockSearch", true, this.rotations);
      booleanValue2 = new BooleanValue(this, "ExtraBlockSearchOnlyAtClutch", false, this.rotations);
      BooleanValue extraBlockSearch2 = this.extraBlockSearch;
      this.extraBlockSearchOnlyAtClutch = (BooleanValue)booleanValue2.withVisibility(() -> extraBlockSearch2.getValue());
      this.strafeSettings = new SettingGroup(this, "StrafeSettings", true, this.rotations);
      this.strafe = new BooleanValue(this, "Strafe", false, this.strafeSettings);
      booleanValue2 = new BooleanValue(this, "StrafeOnlyWhileJump", false, this.strafeSettings);
      this.strafeOnlyWhileJump = (BooleanValue)booleanValue2.withVisibility(() -> this.isStrafeOnlyWhileJumpVisible());
      RandomizedRangeSettings randomizedRangeSettings23 = (new RandomizedRangeSettings(this, "StrafeDegree", 40.0, 45.0, 1.0, 50.0, 2, this.strafeSettings)).withoutNotInARow();
      booleanValue2 = this.strafe;
      BooleanValue booleanValue32 = booleanValue2;
      this.strafeDegree = (RandomizedRangeSettings)randomizedRangeSettings23.withVisibility(() -> booleanValue32.getValue());
      this.strafeWidth = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "StrafeWidth", 0.35, 0.39, 0.0, 0.4, 2, this.strafeSettings)).withoutNotInARow().withVisibility(() -> this.isStrafeWidthVisible());
      BooleanValue booleanValue33 = new BooleanValue(this, "CorrectRotationWhileBuilding", true, this.strafeSettings);
      booleanValue2 = this.strafe;
      BooleanValue booleanValue34 = booleanValue2;
      this.correctRotationWhileBuilding = (BooleanValue)booleanValue33.withVisibility(() -> booleanValue34.getValue());
      BooleanValue booleanValue35 = new BooleanValue(this, "CorrectStrafeWithKeyBinds", true, this.strafeSettings);
      booleanValue2 = this.strafe;
      BooleanValue booleanValue36 = booleanValue2;
      this.correctStrafeWithKeyBinds = (BooleanValue)booleanValue35.withVisibility(() -> booleanValue36.getValue());
      this.correctStrafeWithKeyBindsCage = (BooleanValue)new BooleanValue(this, "CorrectStrafeWithKeyBindsCage", false, this.strafeSettings).withVisibility(() -> this.isStrafeKeyBindCageVisible());
      this.correctStrafeWithKeyBindsWidth = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CorrectStrafeWithKeyBindsWidth", -0.05, 0.05, -0.3, 0.3, 2, this.strafeSettings))
         .withoutNotInARow()
         .withoutNotInARow().withVisibility(() -> this.isStrafeKeyBindWidthVisible());
      RandomizedRangeSettings randomizedRangeSettings26 = new RandomizedRangeSettings(this, "CorrectStrafeWithKeyBindsDuration", 1.0, 3.0, 1.0, 5.0, 0, this.strafeSettings);
      SettingVisibilityCondition condition50 = () -> {
         return this.correctStrafeWithKeyBinds.getValue() && this.correctStrafeWithKeyBinds.isVisible();
      };
      this.correctStrafeWithKeyBindsDuration = (RandomizedRangeSettings)randomizedRangeSettings26.withVisibility(condition50);
      this.correctStrafeWithKeyBindsMiddle = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CorrectStrafeWithKeyBindsMiddle", 0.5, 0.5, 0.2, 0.8, 2, this.strafeSettings)).withoutNotInARow().withVisibility(() -> this.isStrafeKeyBindMiddleVisible());
      this.correctStrafeWithKeyBindsMiddleExclusion = new RangeSetting(this, "CorrectStrafeWithKeyBindsMiddleExclusion", 0.0, 0.0, 0.2, 0.8, 2, this.strafeSettings).visibleWhen(() -> this.isStrafeKeyBindMiddleExclusionVisible());
      RandomizedRangeSettings randomizedRangeSettings29 = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "CorrectStrafeWithKeyBindsDelayToRecalculateMiddle", 15.0, 30.0, 0.0, 40.0, 0, this.strafeSettings))
         .withoutNotInARow().withDisplayNamePrefix("DelayToRecalculateMiddle");
      this.correctStrafeWithKeyBindsDelayToRecalculateMiddle = (RandomizedRangeSettings)randomizedRangeSettings29.withVisibility(() -> this.isStrafeKeyBindRecalcDelayVisible());
      this.startAtEdge = new BooleanValue(this, "StartAtEdge", false, this.rotations);
      RandomizedRangeSettings randomizedRangeSettings30 = (new RandomizedRangeSettings(this, "StartAtEdgeDelay", 2.0, 4.0, 0.0, 10.0, 0, this.rotations)).withoutNotInARow();
      booleanValue2 = this.startAtEdge;
      BooleanValue booleanValue38 = booleanValue2;
      this.startAtEdgeDelay = (RandomizedRangeSettings)randomizedRangeSettings30.withVisibility(() -> booleanValue38.getValue());
      this.ignoreStartAtEdgeAtBlockClutch = (BooleanValue)new BooleanValue(this, "IgnoreStartAtEdgeAtBlockClutch", false, this.rotations).withVisibility(() -> this.isIgnoreStartAtEdgeClutchVisible());
      this.edgeRotateCheck = new BooleanValue(this, "EdgeRotateCheck", false, this.rotations);
      BooleanValue booleanValue40 = new BooleanValue(this, "EdgePlaceCheck", false, this.rotations);
      booleanValue2 = this.edgeRotateCheck;
      BooleanValue booleanValue41 = booleanValue2;
      this.edgePlaceCheck = (BooleanValue)booleanValue40.withVisibility(() -> booleanValue41.getValue());
      this.flickMode = new StringValue(this, "FlickMode", "None", new String[]{"None", "Simple", "Jump"}, this.rotations);
      this.flickUserStrafeSettingForForwardLooking = (BooleanValue)new BooleanValue(this, "FlickUserStrafeSettingForForwardLooking", false, this.rotations).withVisibility(() -> this.isFlickForwardStrafeVisible());
      this.otherRotation = new BooleanValue(this, "OtherRotation", true, this.rotations);
      this.recycleRotation = new BooleanValue(this, "RecycleRotation", false, this.rotations);
      this.intave = new BooleanValue(this, "Intave", true, this.rotations);
      BooleanValue booleanValue43 = new BooleanValue(this, "IntaveSafe", true, this.rotations);
      booleanValue2 = this.intave;
      BooleanValue booleanValue44 = booleanValue2;
      this.intaveSafe = (BooleanValue)booleanValue43.withVisibility(() -> booleanValue44.getValue());
      BooleanValue booleanValue45 = new BooleanValue(this, "IntaveFast", false, this.rotations);
      booleanValue2 = this.intave;
      BooleanValue booleanValue46 = booleanValue2;
      this.intaveFast = (BooleanValue)booleanValue45.withVisibility(() -> booleanValue46.getValue());
      this.pitchSelection = new StringValue(
         this, "PitchSelection", "Nearest", new String[]{"Nearest", "Furthest", "Highest", "Lowest", "Mid", "Random"}, this.rotations
      );
      this.maxPitchCorrection = new DoubleValue(this, "MaxPitchCorrection", 180.0, 0.0, 180.0, 2, this.rotations).visibleWhen(() -> this.isMaxPitchCorrectionVisible());
      this.maxPitchCorrectionAtBlockClutch = new DoubleValue(this, "MaxPitchCorrectionAtBlockClutch", 180.0, 0.0, 180.0, 2, this.rotations).visibleWhen(() -> this.isMaxPitchCorrectionClutchVisible());
      this.expand = new BooleanValue(this, "Expand", false, this.rotations);
      DoubleValue expandLengthSetting = new DoubleValue(this, "ExpandLength", 1.0, 1.0, 10.0, 1, this.rotations);
      booleanValue2 = this.expand;
      BooleanValue booleanValue47 = booleanValue2;
      this.expandLength = expandLengthSetting.visibleWhen(() -> booleanValue47.getValue());
      this.clicking = new SettingGroup(this, "Clicking", true);
      this.noSwing = new BooleanValue(this, "NoSwing", false, this.clicking);
      BooleanValue booleanValue48 = new BooleanValue(this, "ServerSideNoSwing", false, this.clicking);
      booleanValue2 = this.noSwing;
      BooleanValue booleanValue49 = booleanValue2;
      this.serverSideNoSwing = (BooleanValue)booleanValue48.withVisibility(() -> booleanValue49.getValue());
      this.cps = (new RandomizedRangeSettings(this, "CPS", 10.0, 20.0, 0.0, 40.0, 0, this.clicking)).withoutNotInARow();
      this.dcpsMode = new StringValue(this, "DCPSMode", "External", new String[]{"Internal", "External"}, this.clicking);
      RandomizedRangeSettings randomizedRangeSettings31 = (new RandomizedRangeSettings(this, "DCPS", 2.0, 4.0, 0.0, 20.0, 0, this.clicking)).withoutNotInARow();
      SettingVisibilityCondition condition64 = () -> {
         return this.dcpsMode.getSelectedValue().equals("External");
      };
      this.dcps = (RandomizedRangeSettings)randomizedRangeSettings31.withVisibility(condition64);
      this.forcePlaceIfYouWouldFallDown = new BooleanValue(this, "ForcePlaceIfYouWouldFallDown", true, this.clicking);
      this.alwaysPlaceWhenPossible = new BooleanValue(this, "AlwaysPlaceWhenPossible", true, this.clicking);
      this.clickPause = new BooleanValue(this, "ClickPause", false, this.clicking);
      this.delayClick = new BooleanValue(this, "DelayClick", false, this.clicking);
      this.oneClick = new BooleanValue(this, "OneClick", false, this.clicking);
      this.stopClickingAtSneaking = (BooleanValue)new BooleanValue(this, "StopClickingAtSneaking", false, this.clicking).withDisplayName("StopClickingAtSneakingOnGround");
      this.maxYawRotationDeltaToPlaceBlocks = new DoubleValue(this, "MaxYawRotationDeltaToPlaceBlocks", 180.0, 0.0, 180.0, 2, this.clicking);
      this.maxPitchRotationDeltaToPlaceBlocks = new DoubleValue(this, "MaxPitchRotationDeltaToPlaceBlocks", 180.0, 0.0, 180.0, 2, this.clicking);
      this.maxYawRotationDeltaToPlaceBlocksAtBlockClutch = new DoubleValue(this, "MaxYawRotationDeltaToPlaceBlocksAtBlockClutch", 180.0, 0.0, 180.0, 2, this.clicking);
      this.maxPitchRotationDeltaToPlaceBlocksAtBlockClutch = new DoubleValue(this, "MaxPitchRotationDeltaToPlaceBlocksAtBlockClutch", 180.0, 0.0, 180.0, 2, this.clicking);
      this.movement = new SettingGroup(this, "Movement", true);
      this.removeSneakStop = new BooleanValue(this, "RemoveSneakStop", false, this.movement);
      this.sneaking = new SettingGroup(this, "Sneaking", true, this.movement);
      this.sneak = new BooleanValue(this, "Sneak", false, this.sneaking);
      BooleanValue booleanValue51 = new BooleanValue(this, "TimeJumpForOneStackForward", false, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue52 = booleanValue2;
      this.timeJumpForOneStackForward = (BooleanValue)booleanValue51.withVisibility(() -> booleanValue52.getValue());
      BooleanValue booleanValue53 = new BooleanValue(this, "OneStackForward", false, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue54 = booleanValue2;
      this.oneStackForward = (BooleanValue)booleanValue53.withVisibility(() -> booleanValue54.getValue());
      this.minOffGroundSneakTickForward = new RangeSetting(this, "MinOffGroundSneakTickForward", 1.0, 2.0, 0.0, 12.0, 0, this.sneaking).visibleWhen(() -> this.isMinOffGroundSneakForwardVisible());
      this.maxOffGroundSneakTickForward = new RangeSetting(this, "MaxOffGroundSneakTickForward", 10.0, 11.0, 0.0, 12.0, 0, this.sneaking).visibleWhen(() -> this.isMaxOffGroundSneakForwardVisible());
      BooleanValue booleanValue55 = new BooleanValue(this, "OneStackDiagonal", false, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue56 = booleanValue2;
      this.oneStackDiagonal = (BooleanValue)booleanValue55.withVisibility(() -> booleanValue56.getValue());
      this.minOffGroundSneakTickDiagonal = new RangeSetting(this, "MinOffGroundSneakTickDiagonal", 1.0, 2.0, 0.0, 12.0, 0, this.sneaking).visibleWhen(() -> this.isMinOffGroundSneakDiagonalVisible());
      this.maxOffGroundSneakTickDiagonal = new RangeSetting(this, "MaxOffGroundSneakTickDiagonal", 10.0, 11.0, 0.0, 12.0, 0, this.sneaking).visibleWhen(() -> this.isMaxOffGroundSneakDiagonalVisible());
      RandomizedRangeSettings randomizedRangeSettings32 = new RandomizedRangeSettings(this, "SneakTicks", 1.0, 2.0, 0.0, 10.0, 0, this.sneaking);
      double d = 15.0;
      double d2 = 8.0;
      double d3 = 30.0;
      double d4 = 1.0;
      double d5 = 3.0;
      RandomizedRangeSettings randomizedRangeSettings33 = randomizedRangeSettings32.withOutliers(3.0, d5, d4, d3, d2, d);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue57 = booleanValue2;
      this.sneakTicks = (RandomizedRangeSettings)randomizedRangeSettings33.withVisibility(() -> booleanValue57.getValue());
      RangeSetting rangeSetting6 = new RangeSetting(this, "StartSneakDistToBlock", 0.05, 0.2, -0.2, 0.5, 2, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue58 = booleanValue2;
      this.startSneakDistToBlock = rangeSetting6.visibleWhen(() -> booleanValue58.getValue());
      RangeSetting rangeSetting7 = new RangeSetting(this, "SneakBlocksForward", 1.0, 2.0, 0.0, 15.0, 0, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue59 = booleanValue2;
      this.sneakBlocksForward = rangeSetting7.visibleWhen(() -> booleanValue59.getValue());
      RangeSetting rangeSetting8 = new RangeSetting(this, "SneakBlocksDiagonal", 1.0, 2.0, 0.0, 15.0, 0, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue60 = booleanValue2;
      this.sneakBlocksDiagonal = rangeSetting8.visibleWhen(() -> booleanValue60.getValue());
      DoubleValue sneakSlowDownSetting = new DoubleValue(this, "SneakSlowDown", 0.3, 0.3, 1.0, 2, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue61 = booleanValue2;
      this.sneakSlowDown = sneakSlowDownSetting.visibleWhen(() -> booleanValue61.getValue());
      BooleanValue booleanValue62 = new BooleanValue(this, "EarlySneakCalculation", true, this.sneaking);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue63 = booleanValue2;
      this.earlySneakCalculation = (BooleanValue)booleanValue62.withVisibility(() -> booleanValue63.getValue());
      this.earlySneakCalculationOnlyAtSpeedEffect = (BooleanValue)new BooleanValue(this, "EarlySneakCalculationOnlyAtSpeedEffect", true, this.sneaking).withVisibility(() -> this.isEarlySneakSpeedOnlyVisible());
      this.adhsMode = new StringValue(this, "ADHSMode", "None", new String[]{"None", "ADS", "ADHS"}, this.movement);
      this.moveFix = new StringValue(this, "MoveFix", "Default", new String[]{"None", "Default", "Advanced"}, this.movement);
      this.moveFixIntave = (BooleanValue)new BooleanValue(this, "MoveFixIntave", true, this.movement).withVisibility(() -> this.isMoveFixIntaveVisible());
      this.mode = (StringValue)new StringValue(
         this, "Mode", "None", new String[]{"None", "Legit", "AllDirection", "AllDirectionMotion"}, this.movement
      ).withDisplayName("SprintMode");
      this.jumpSprint = new BooleanValue(this, "JumpSprint", false, this.movement);
      this.hover = new BooleanValue(this, "Hover", false, this.movement);
      this.stopAtStart = new BooleanValue(this, "StopAtStart", false, this.movement);
      this.safeWalkNoBlocks = new BooleanValue(this, "SafeWalkNoBlocks", true, this.movement);
      this.sneakNoBlocks = new BooleanValue(this, "SneakNoBlocks", true, this.movement);
      this.stopNoBlocks = new BooleanValue(this, "StopNoBlocks", false, this.movement);
      this.reduceVelocity = new BooleanValue(this, "ReduceVelocity", false, this.movement);
      this.ignoreSpeedMotion = new BooleanValue(this, "IgnoreSpeedMotion", false, this.movement);
      this.sneakAtRotation = new BooleanValue(this, "SneakAtRotation", false, this.movement);
      this.stopAtRotation = new BooleanValue(this, "StopAtRotation", false, this.movement);
      this.godBridging = new SettingGroup(this, "GodBridging", true, this.movement);
      this.godBridge = new BooleanValue(this, "GodBridge", false, this.godBridging);
      this.disableGodbridgeSafetyFeature = new BooleanValue(this, "DisableGodbridgeSafetyFeature", false, this.godBridging);
      RandomizedRangeSettings randomizedRangeSettings34 = new RandomizedRangeSettings(this, "ResetGodBridgeAfterXJumps", 7.0, 8.0, 0.0, 12.0, 0, this.godBridging);
      booleanValue2 = this.godBridge;
      BooleanValue booleanValue66 = booleanValue2;
      this.resetGodBridgeAfterXJumps = (RandomizedRangeSettings)randomizedRangeSettings34.withVisibility(() -> booleanValue66.getValue());
      RandomizedRangeSettings randomizedRangeSettings35 = (new RandomizedRangeSettings(this, "JumpAfterXBlocks", 8.0, 8.0, 0.0, 9.0, 0, this.godBridging)).withoutNotInARow();
      booleanValue2 = this.godBridge;
      BooleanValue booleanValue67 = booleanValue2;
      this.jumpAfterXBlocks = (RandomizedRangeSettings)randomizedRangeSettings35.withVisibility(() -> booleanValue67.getValue());
      RandomizedRangeSettings randomizedRangeSettings36 = new RandomizedRangeSettings(this, "JumpDelayAfterPlace", 0.0, 0.0, 0.0, 1.0, 0, this.godBridging);
      booleanValue2 = this.godBridge;
      BooleanValue booleanValue68 = booleanValue2;
      this.jumpDelayAfterPlace = (RandomizedRangeSettings)randomizedRangeSettings36.withVisibility(() -> booleanValue68.getValue());
      RandomizedRangeSettings randomizedRangeSettings37 = (new RandomizedRangeSettings(this, "StartDelay", 10.0, 13.0, 8.0, 20.0, 0, this.godBridging)).withoutNotInARow();
      booleanValue2 = this.godBridge;
      BooleanValue booleanValue69 = booleanValue2;
      this.startDelay = (RandomizedRangeSettings)randomizedRangeSettings37.withVisibility(() -> booleanValue69.getValue());
      this.moonWalking = new SettingGroup(this, "MoonWalking", true, this.movement);
      this.moonWalk = new BooleanValue(this, "MoonWalk", true, this.moonWalking);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false, this.moonWalking);
      RandomizedRangeSettings randomizedRangeSettings38 = (new RandomizedRangeSettings(this, "MoonWalkWideness", 0.05, 0.1, 0.0, 0.3, 2, this.moonWalking)).withoutNotInARow();
      SettingVisibilityCondition condition84 = () -> {
         return this.moonWalk.getValue() && this.moonWalk.isVisible();
      };
      this.moonWalkWideness = (RandomizedRangeSettings)randomizedRangeSettings38.withVisibility(condition84);
      RangeSetting rangeSetting9 = new RangeSetting(this, "MoonWalkStartDistToBlock", 0.05, 0.2, -0.2, 0.5, 2, this.moonWalking);
      SettingVisibilityCondition condition85 = () -> {
         return this.moonWalk.getValue() && this.moonWalk.isVisible();
      };
      this.moonWalkStartDistToBlock = rangeSetting9.visibleWhen(condition85);
      this.moonWalkMiddle = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "MoonWalkMiddle", 0.5, 0.5, 0.2, 0.8, 2, this.moonWalking).withVisibility(() -> this.isMoonWalkMiddleVisible());
      this.delayToRecalculateMiddle = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "DelayToRecalculateMiddle", 15.0, 30.0, 0.0, 40.0, 0, this.moonWalking).withVisibility(() -> this.isDelayToRecalculateMiddleVisible());
      RandomizedRangeSettings randomizedRangeSettings41 = (new RandomizedRangeSettings(this, "PostPlacementStrafeTicks", 0.0, 0.0, 0.0, 8.0, 0, this.moonWalking)).withMaxInARow();
      double d7 = 15.0;
      double d8 = 0.0;
      double d9 = 8.0;
      double d10 = 0.0;
      double d11 = 8.0;
      this.postPlacementStrafeTicks = (RandomizedRangeSettings)randomizedRangeSettings41.withOutliers(5.0, d11, d10, d9, d8, d7, false).withVisibility(() -> this.isPostPlacementStrafeTicksVisible());
      RandomizedRangeSettings randomizedRangeSettings43 = (new RandomizedRangeSettings(this, "MinMoonwalkTicks", 0.0, 0.0, 0.0, 10.0, 0, this.moonWalking)).withMaxInARow();
      double d13 = 15.0;
      double d14 = 0.0;
      double d15 = 10.0;
      double d16 = 0.0;
      double d17 = 12.0;
      RandomizedRangeSettings randomizedRangeSettings44 = randomizedRangeSettings43.withOutliers(8.0, d17, d16, d15, d14, d13, false);
      SettingVisibilityCondition condition89 = () -> {
         return this.moonWalk.getValue() && this.moonWalk.isVisible();
      };
      this.minMoonwalkTicks = (RandomizedRangeSettings)randomizedRangeSettings44.withVisibility(condition89);
      this.directionChangeDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DirectionChangeDelay", 0.0, 0.0, 0.0, 3.0, 0, this.moonWalking)).withMaxInARow().withVisibility(() -> this.isDirectionChangeDelayVisible());
      this.sameYBlockGroup = new SettingGroup(this, "SameYBlockGroup", true, this.movement);
      this.sameYBlock = new BooleanValue(this, "SameYBlock", false, this.sameYBlockGroup);
      BooleanValue booleanValue70 = new BooleanValue(this, "StopMovementAfterJump", true, this.sameYBlockGroup);
      booleanValue2 = this.sameYBlock;
      BooleanValue booleanValue71 = booleanValue2;
      this.stopMovementAfterJump = (BooleanValue)booleanValue70.withVisibility(() -> booleanValue71.getValue());
      this.sameYGroup = new SettingGroup(this, "SameYGroup", true, this.movement);
      this.sameY = new BooleanValue(this, "SameY", false, this.sameYGroup);
      BooleanValue booleanValue72 = new BooleanValue(this, "AutoJump", false, this.sameYGroup);
      booleanValue2 = this.sameY;
      BooleanValue booleanValue73 = booleanValue2;
      this.autoJump = (BooleanValue)booleanValue72.withVisibility(() -> booleanValue73.getValue());
      this.intaveFast2 = (BooleanValue)new BooleanValue(this, "IntaveFast2", true, this.sameYGroup).withVisibility(() -> this.isIntaveFast2Visible());
      this.tellyBridge = (BooleanValue)new BooleanValue(this, "TellyBridge", false, this.sameYGroup).withVisibility(() -> this.isTellyBridgeVisible());
      this.safeWalkGroup = new SettingGroup(this, "SafeWalkGroup", true, this.movement);
      this.safeWalk = new BooleanValue(this, "SafeWalk", false, this.safeWalkGroup);
      BooleanValue booleanValue76 = new BooleanValue(this, "SafeWalkAir", false, this.safeWalkGroup);
      booleanValue2 = this.safeWalk;
      BooleanValue booleanValue77 = booleanValue2;
      this.safeWalkAir = (BooleanValue)booleanValue76.withVisibility(() -> booleanValue77.getValue());
      BooleanValue booleanValue78 = new BooleanValue(this, "SafeWalkOnlyDiagonal", false, this.safeWalkGroup);
      booleanValue2 = this.safeWalk;
      BooleanValue booleanValue79 = booleanValue2;
      this.safeWalkOnlyDiagonal = (BooleanValue)booleanValue78.withVisibility(() -> booleanValue79.getValue());
      this.jumpGroup = new SettingGroup(this, "JumpGroup", true, this.movement);
      this.jump = new BooleanValue(this, "Jump", false, this.jumpGroup);
      BooleanValue booleanValue80 = new BooleanValue(this, "JumpOnlyWhileSpacePressed", false, this.jumpGroup);
      booleanValue2 = this.jump;
      BooleanValue booleanValue81 = booleanValue2;
      this.jumpOnlyWhileSpacePressed = (BooleanValue)booleanValue80.withVisibility(() -> booleanValue81.getValue());
      RandomizedRangeSettings randomizedRangeSettings46 = (new RandomizedRangeSettings(this, "JumpAfterXBlocksForward", 6.0, 6.0, 0.0, 10.0, 0, this.jumpGroup)).withoutNotInARow();
      booleanValue2 = this.jump;
      BooleanValue booleanValue82 = booleanValue2;
      this.jumpAfterXBlocksForward = (RandomizedRangeSettings)randomizedRangeSettings46.withVisibility(() -> booleanValue82.getValue());
      RandomizedRangeSettings randomizedRangeSettings47 = (new RandomizedRangeSettings(this, "JumpAfterXBlocksDiagonal", 6.0, 6.0, 0.0, 10.0, 0, this.jumpGroup)).withoutNotInARow();
      booleanValue2 = this.jump;
      BooleanValue booleanValue83 = booleanValue2;
      this.jumpAfterXBlocksDiagonal = (RandomizedRangeSettings)randomizedRangeSettings47.withVisibility(() -> booleanValue83.getValue());
      this.timeGroup = new SettingGroup(this, "TimeGroup", true, this.movement);
      this.timer = new DoubleValue(this, "Timer", 1.0, 0.1, 4.0, 2, this.timeGroup);
      this.timerDrop = new BooleanValue(this, "TimerDrop", false, this.timeGroup);
      this.timerSpeed = new RangeSetting(this, "TimerSpeed", 0.1, 50.0, 0.0, 50.0, 2, this.timeGroup).visibleWhen(() -> this.isTimerSpeedVisible());
      this.motionMultiplier = new SettingGroup(this, "MotionMultiplier", true, this.movement);
      this.motionMultiplierAirForward = new RandomizedRangeSettings(this, "MotionMultiplierAirForward", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplier);
      this.motionMultiplierAirStrafe = new RandomizedRangeSettings(this, "MotionMultiplierAirStrafe", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplier);
      this.motionMultiplierGroundForward = new RandomizedRangeSettings(this, "MotionMultiplierGroundForward", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplier);
      this.motionMultiplierGroundStrafe = new RandomizedRangeSettings(this, "MotionMultiplierGroundStrafe", 1.0, 1.0, 0.1, 2.0, 2, this.motionMultiplier);
      this.miscellaneous = new SettingGroup(this, "Miscellaneous", true);
      this.towerMode = new StringValue(
         this, "TowerMode", "Legit", new String[]{"Legit", "Intave1", "Intave2", "NCP", "NCPFast", "Verus", "ConstantMotion", "Jump"}, this.miscellaneous
      );
      this.towerWhileMoving = (BooleanValue)new BooleanValue(this, "TowerWhileMoving", false, this.miscellaneous).withVisibility(() -> this.isTowerWhileMovingVisible());
      this.constantMotion = new DoubleValue(this, "ConstantMotion", 0.3, 0.01, 0.4, 2, this.miscellaneous).visibleWhen(() -> this.isConstantMotionVisible());
      DoubleValue jumpMotionSetting = new DoubleValue(this, "JumpMotion", 0.5, 0.37, 1.0, 2, this.miscellaneous);
      SettingVisibilityCondition condition103 = () -> {
         return this.towerMode.getSelectedValue().equals("Jump");
      };
      this.jumpMotion = jumpMotionSetting.visibleWhen(condition103);
      this.silentMode = new StringValue(this, "SilentMode", "Default", new String[]{"None", "Legit", "Default", "Exploit"}, this.miscellaneous);
      this.useBiggestStackSilent = (BooleanValue)new BooleanValue(this, "UseBiggestStackSilent", false, this.miscellaneous).withVisibility(() -> this.isUseBiggestStackSilentVisible());
      this.silentStartDelay = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "SilentStartDelay", 0.0, 0.0, 0.0, 10.0, 0, this.miscellaneous).withVisibility(() -> this.isSilentStartDelayVisible());
      this.silentSwitchDelay = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "SilentSwitchDelay", 0.0, 0.0, 0.0, 10.0, 0, this.miscellaneous).withVisibility(() -> this.isSilentSwitchDelayVisible());
      this.stackCountToStartSwitchProcess = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "StackCountToStartSwitchProcess", 0.0, 0.0, 0.0, 5.0, 0, this.miscellaneous).withVisibility(() -> this.isStackCountToStartSwitchVisible());
      this.backSwitchTickDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "BackSwitchTickDelay", 5.0, 10.0, 0.0, 40.0, 0, this.miscellaneous)).withoutNotInARow().withVisibility(() -> this.isBackSwitchTickDelayVisible());
      this.dynamicBackSwitchTickDelay = (BooleanValue)new BooleanValue(this, "DynamicBackSwitchTickDelay", true, this.miscellaneous).withVisibility(() -> this.isDynamicBackSwitchTickDelayVisible());
      this.visuals = (SettingGroup)(new SettingGroup(this, "Visuals", true)).markVisualOnly();
      this.thirdPersonMode = (BooleanValue)(new BooleanValue(this, "ThirdPersonMode", false, this.visuals)).markVisualOnly();
      BooleanValue booleanValue87 = new BooleanValue(this, "HideSneakAnimation", false, this.visuals);
      booleanValue2 = this.sneak;
      BooleanValue booleanValue88 = booleanValue2;
      this.hideSneakAnimation = (BooleanValue)((BooleanValue)booleanValue87.withVisibility(() -> booleanValue88.getValue())).markVisualOnly();
      this.blockCount = (BooleanValue)(new BooleanValue(this, "BlockCount", true, this.visuals)).markVisualOnly();
      DoubleValue doubleSetting = new DoubleValue(this, "X", 50.0, 0.0, 100.0, 2, this.visuals);
      booleanValue2 = this.blockCount;
      BooleanValue booleanValue89 = booleanValue2;
      this.x = (DoubleValue)doubleSetting.visibleWhen(() -> booleanValue89.getValue()).markVisualOnly();
      DoubleValue doubleSetting2 = new DoubleValue(this, "Y", 53.0, 0.0, 100.0, 2, this.visuals);
      booleanValue2 = this.blockCount;
      BooleanValue booleanValue90 = booleanValue2;
      this.y = (DoubleValue)doubleSetting2.visibleWhen(() -> booleanValue90.getValue()).markVisualOnly();
      DoubleValue scaleSetting = new DoubleValue(this, "Scale", 2.0, 1.0, 4.0, 1, this.visuals);
      booleanValue2 = this.blockCount;
      BooleanValue booleanValue91 = booleanValue2;
      this.scale = (DoubleValue)scaleSetting.visibleWhen(() -> booleanValue91.getValue()).markVisualOnly();
      BooleanValue booleanValue92 = new BooleanValue(this, "BackGround", true, this.visuals);
      booleanValue2 = this.blockCount;
      BooleanValue booleanValue93 = booleanValue2;
      this.backGround = (BooleanValue)((BooleanValue)booleanValue92.withVisibility(() -> booleanValue93.getValue())).markVisualOnly();
      Color color2 = new Color(0, 0, 0, 100);
      SettingGroup visuals2 = this.visuals;
      Color color3 = color2;
      String str14 = "BackGroundColor";
      ColorSetting colorSetting = new ColorSetting(this, str14, color3, visuals2);
      booleanValue2 = this.backGround;
      BooleanValue booleanValue94 = booleanValue2;
      this.backgroundColor = (ColorSetting)((ColorSetting)colorSetting.withVisibility(() -> booleanValue94.getValue())).markVisualOnly();
      this.roundFactor = (DoubleValue)new DoubleValue(this, "RoundFactor", 5.0, 0.0, 11.0, 0, this.visuals).visibleWhen(() -> this.isRoundFactorVisible()).markVisualOnly();
      this.blockCount2 = (BlurShadowSettings)((BlurShadowSettings)new BlurShadowSettings(this, "BlockCount", this.visuals).withCustomShadowColor(Color.black).withVisibility(() -> this.isBlockCountShadowVisible())).markAllVisualOnly();
      this.esp = (BooleanValue)(new BooleanValue(this, "ESP", true, this.visuals)).markVisualOnly();
      color2 = new Color(41, 146, 222, 100);
      visuals2 = this.visuals;
      color3 = color2;
      str14 = "Color";
      ColorSetting colorSetting2 = new ColorSetting(this, str14, color3, visuals2);
      booleanValue2 = this.esp;
      BooleanValue booleanValue95 = booleanValue2;
      this.espColor = (ColorSetting)((ColorSetting)colorSetting2.withVisibility(() -> booleanValue95.getValue())).markVisualOnly();
      BlurShadowSettings blurShadowSettings4 = new BlurShadowSettings(this, "ESP", this.visuals).withCustomShadowColor(Color.black);
      booleanValue2 = this.esp;
      BooleanValue booleanValue96 = booleanValue2;
      this.esp2 = (BlurShadowSettings)((BlurShadowSettings)blurShadowSettings4.withVisibility(() -> booleanValue96.getValue())).markAllVisualOnly();
      this.lastPlacedBlockPos = null;
      this.targetBlockPos = null;
      this.tellyPredictedBlockDistance = 0;
      this.blockPlacementData = null;
      this.overrideRayTrace = null;
      this.rotationUtil = new RotationUtil();
      this.bezierRotationStepper = new BezierRotationStepper();
      this.bezierCurve = null;
      this.rotationKinematicState = null;
      this.lastBezierRotation = null;
      this.bezierTickCounter = 0;
      this.lastRightClickTimer = new TimeHelper();
      this.lastPlaceTimer = new TimeHelper();
      this.enableTimer = new TimeHelper();
      this.sneakHoldTicks = 0;
      this.blocksSinceLastSneak = 0;
      this.placedBlocksSinceSneak = 0;
      this.sneakEdgeExpandDist = 0.0;
      this.offGroundTicks = 0;
      this.wasSneakingLastTick = false;
      this.isOverSupportBlock = false;
      this.sneakIntervalBlocksForward = 0;
      this.sneakIntervalBlocksDiagonal = 0;
      this.intave14TimeBlockPlaceCheck = new Intave14TimeBlockPlaceCheck("Intave14SusBlockPlacementTime", null, false);
      this.strafeYawOffset = 45.0F;
      this.hotbarBlockCount = 0;
      this.bridgeClickController = new BridgeClickController(this.cps, this.dcpsMode, this.dcps, this.alwaysPlaceWhenPossible, this.oneClick, this.delayClick);
      this.moonWalkStrafeDir = 1;
      this.keyBindStrafeCorrectionDir = 1;
      this.moonWalkEdgeExpandDist = 0.0;
      this.ticksSinceLastPlace = 0;
      this.moonWalkTicks = 0;
      this.moonWalkDirChangeTicks = 0;
      this.lastAppliedStrafeInput = 0;
      this.wasOverMoonWalkBlock = false;
      this.minOffGroundSneakForward = 0;
      this.maxOffGroundSneakForward = 10;
      this.minOffGroundSneakDiagonal = 0;
      this.maxOffGroundSneakDiagonal = 10;
      this.expandAirYOffset = 0.0;
      this.godBridgeResetting = false;
      this.godBridgePlacedBlocks = 0;
      this.godBridgeSetupTicks = 0;
      this.godBridgeJumpCount = 0;
      this.ticksSinceGodBridgePlace = 0;
      this.godBridgeSneakSetupTicks = 0;
      this.lastGodBridgePosX = 0.0;
      this.lastGodBridgePosZ = 0.0;
      this.wasCardinalDirection = false;
      this.safeWalkEdgeDetected = false;
      this.godBridgeJumpHeld = false;
      this.blocksPlacedSinceJump = 0;
      this.yawRandomizationTriggered = false;
      this.pitchRandomizationTriggered = false;
      this.yawRandomizeCounter = new TickCounter();
      this.pitchRandomizeCounter = new TickCounter();
      this.rightClickHeldOnGround = false;
      this.velocityAnchorPos = null;
      this.watchdogTowerTick = 0;
      this.hoverJumpStarted = false;
      this.hoverOffsetActive = false;
      this.strafeCorrectionCounter = new TickCounter();
      this.strafeMiddleRecalcCounter = new TickCounter();
      this.strictYawErrorSign = 1;
      this.lastStrictYawSector = 0;
      this.strafeCorrectionActive = false;
      this.silentSwitchCounter = new TickCounter();
      this.firstSilentSwitchPending = false;
      this.selectedBlockSlot = -1;
      this.slotMinStackThresholds = new HashMap();
      this.stopAtStartDelayMs = 0;
      this.lastMoonWalkSide = 0;
      this.strafeCorrectionMiddle = 0.0;
      this.moonWalkMiddleRecalcCounter = new TickCounter();
      this.directionChangeDelayCounter = new TickCounter();
      this.directionChangePending = false;
      this.reducingVelocity = false;
      this.startEdgeSneakTicks = 0;
      this.startAtEdgeDelayCounter = new TickCounter();
      this.initialStrafeDirectionPending = false;
      this.savedThirdPersonView = 0;
      this.ticksSinceDirectionChange = 0;
      this.keepInitialPitch = false;
      this.sneakingOnGroundForEdge = false;
      this.placedBlockSinceEnable = false;
      this.minimalMouseMoveTimer = new TimeHelper();
      this.minimalMouseMoveCounter = new TickCounter();
      this.minimalMouseMoveIntervalMs = 0;
      this.minimalMouseMoveDurationTicks = 0;
      this.sameYBlockJumpActive = false;
      this.sameYBlockBaseY = 0.0;
      this.sameYBlockResetPending = false;
      this.sameYBlockTopPlaced = false;
      this.stopMovementUntilLand = false;
      this.intaveBlockPlacePenalty = 0.0;
   }

   public static void initScaffold() {
   }

   @Override
   public void onEnable() {
      this.targetBlockPos = null;
      this.tellyPredictedBlockDistance = 0;
      this.lastPlacedBlockPos = null;
      this.minimalMouseYawOffset = 0.0F;
      this.minimalMousePitchOffset = 0.0F;
      this.sneakIntervalBlocksDiagonal = 0;
      this.godBridgePlacedBlocks = 0;
      this.placedBlockSinceEnable = false;
      this.blocksSinceLastSneak = 0;
      this.godBridgeResetting = true;
      this.godBridgeSetupTicks = 0;
      this.safeWalkEdgeDetected = false;
      this.godBridgeJumpCount = 0;
      this.godBridgeJumpHeld = false;
      this.sneakIntervalBlocksForward = 0;
      mc.getTimer().timerSpeed = 1.0F;
      this.enableTimer.reset();
      this.blocksSinceLastSneak = 1000;
      this.placedBlocksSinceSneak = 1000;
      this.offGroundTicks = 0;
      this.strafeYawOffset = this.strafeYawOffset > 0.0F ? (float)this.strafeDegree.nextValue() : (float)(-this.strafeDegree.nextValue());
      this.startDelay.nextValue();
      this.jumpDelayAfterPlace.nextValue();
      this.resetGodBridgeAfterXJumps.nextValue();
      this.jumpAfterXBlocks.nextValue();
      this.godBridgeSneakSetupTicks = DefaultRandomGenerator.instance.nextInt((byte)5, (byte)7);
      this.jumpAfterXBlocks.nextValue();
      this.sneakTicks.nextValue();
      this.blocksPlacedSinceJump = this.jumpOnlyWhileSpacePressed.getValue() ? 10000 : 0;
      this.watchdogTowerTick = 0;
      this.hoverJumpStarted = false;
      this.hoverOffsetActive = false;
      this.strafeDegree.nextValue();
      this.previousTargetBlockPos = null;
      this.firstSilentSwitchPending = true;
      this.silentStartDelay.nextValue();
      this.stopAtStartDelayMs = DefaultRandomGenerator.instance.nextInt((short)145, (short)255);
      this.startEdgeSneakTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)3);
      this.postDirectionChangeSneakTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)3);
      this.strafeCorrectionMiddle = this.rollStrafeCorrectionMiddle();
      this.staticPitch.nextValue();
      this.staticPitchDiagonal.nextValue();
      this.backSwitchTickDelay.nextValue();
      this.reducingVelocity = false;
      this.waitingForStartEdge = true;
      this.hasPlacedFirstBlock = false;
      this.ticksSinceFirstPlace = 0;
      this.overrideRayTrace = null;
      this.startAtEdgeDelay.nextValue();
      this.initialStrafeDirectionPending = true;
      this.directionChangePending = false;
      this.savedThirdPersonView = mc.gameSettings.thirdPersonView;
      this.keepInitialPitch = false;
      this.lastStrictYawSector = -1;
      this.sameYBlockTopPlaced = false;
      this.sameYBlockResetPending = false;
      this.sameYBlockJumpActive = false;
      if (mc.thePlayer != null) {
         this.sameYBlockBaseY = mc.thePlayer.lastGroundY;
      }

      this.stopMovementUntilLand = false;
      this.keepRotationsAfterModuleDisable.nextValue();
   }

   @Override
   public void onDisable() {
      mc.getTimer().timerSpeed = 1.0F;
      this.selectedBlockSlot = -1;
      this.slotMinStackThresholds.clear();
      this.rotationKinematicState = null;
      this.bezierCurve = null;
      this.bezierTickCounter = 0;
      if (this.thirdPersonMode.getValue() && mc.gameSettings != null) {
         mc.gameSettings.thirdPersonView = this.savedThirdPersonView;
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(36, 20);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(24);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(21, 5);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(29);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(33);
      this.toggleEventSubscription(39);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(0);
      this.toggleEventSubscription(43);
   }

   @Override
   public void onPreUpdate(EventUpdate event) {
      if (this.thirdPersonMode.getValue()) {
         mc.gameSettings.thirdPersonView = 1;
      }

      if (!this.waitingForStartEdge) {
         if (this.canRunScaffoldInCurrentScreen()
            && this.targetBlockPos != null
            && (this.towerWhileMoving.getValue() && mc.gameSettings.keyBindJump.isKeyDown() || this.isStationaryTowering())
            && BlockUtil.isValidScaffoldBlock(mc.thePlayer.getCurrentEquippedItem())) {
            switch (this.towerMode.getSelectedValue()) {
               case "Intave1":
                  if (mc.thePlayer.onGround) {
                     mc.thePlayer.motionY = 0.405;
                  }

                  if (this.offGroundTicks == 5) {
                     mc.thePlayer.motionY = (mc.thePlayer.motionY - 0.08) * 0.98F;
                  }
                  break;
               case "Intave2":
                  if (mc.thePlayer.onGround) {
                     mc.thePlayer.motionY = 0.405;
                  }

                  if (this.offGroundTicks == 5) {
                     double d = 0.9909900590734863;
                     double d2 = 0.9908900590734863;
                     float f = DefaultRandomGenerator.instance.nextFloat(d2, d);
                     mc.thePlayer.motionY = (mc.thePlayer.motionY - 0.08) * (double)f;
                  }
                  break;
               case "NCP":
                  if (mc.thePlayer.onGround) {
                     mc.thePlayer.jump();
                     this.ncpTowerStartY = mc.thePlayer.posY;
                  } else if (mc.thePlayer.posY > this.ncpTowerStartY + 1.0) {
                     mc.thePlayer.motionY = -0.1;
                  }
                  break;
               case "Verus":
                  if (mc.thePlayer.ticksExisted % 2 == 0) {
                     mc.thePlayer.jump();
                  }
                  break;
               case "ConstantMotion":
                  mc.thePlayer.motionY = this.constantMotion.getValue();
                  break;
               case "Jump":
                  if (mc.thePlayer.onGround) {
                     mc.thePlayer.motionY = this.jumpMotion.getValue();
                  }
                  break;
               case "NCPFast":
                  if (this.offGroundTicks <= 1) {
                     this.offGroundTicks = 0;
                     if (mc.thePlayer.ticksExisted % 3 == 0) {
                        mc.thePlayer.motionY = 0.4196;
                     }
                  }
                  break;
               case "Watchdog":
                  if (!mc.gameSettings.keyBindForward.isKeyDown()) {
                     MoveUtil.setSpeed(0.0F);
                     this.watchdogTowerTick = 0;
                  } else {
                     this.watchdogTowerTick++;
                     if (mc.thePlayer.onGround) {
                        this.watchdogTowerTick = 0;
                     }

                     mc.thePlayer.motionY = 0.41965;
                     MoveUtil.setSpeed(0.241F, false);
                     double posY = mc.thePlayer.posY;
                     if (this.watchdogTowerTick == 1) {
                        mc.thePlayer.motionY = 0.33;
                     } else if (this.watchdogTowerTick == 2) {
                        mc.thePlayer.motionY = 1.0 - posY % 1.0;
                     } else if (this.watchdogTowerTick >= 3) {
                        this.watchdogTowerTick = 0;
                     }
                  }
            }
         }

         if (this.sameYBlock.getValue()) {
            if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
               this.sameYBlockResetPending = true;
               this.sameYBlockTopPlaced = false;
               this.sameYBlockJumpActive = false;
            }

            if (this.sameYBlockResetPending && mc.thePlayer.onGround) {
               this.sameYBlockBaseY = mc.thePlayer.posY;
               this.sameYBlockResetPending = false;
            }
         } else {
            this.sameYBlockTopPlaced = false;
            this.sameYBlockJumpActive = false;
         }
      }
   }

   @Override
   public void onEarlyTick() {
      if (mc.thePlayer.onGround) {
         this.rightClickHeldOnGround = mc.gameSettings.keyBindUseItem.isKeyDown();
      }

      if (this.isEarlySneakCalculationActive()) {
         this.isOverSupportBlock = false;
         this.hotbarBlockCount = this.countHotbarBlocks();
         List list = mc.theWorld.getCollidingBoundingBoxesCapped(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -1.0, 0.0));
         boolean flag = Math.abs(mc.thePlayer.motionX) > 0.02;
         boolean flag2 = Math.abs(mc.thePlayer.motionZ) > 0.02;

         for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(list)) {
            if (mc.thePlayer
               .getEntityBoundingBox()
               .expand(
                  -Math.abs(mc.thePlayer.getEntityBoundingBox().minX - mc.thePlayer.getEntityBoundingBox().maxX) / 2.0,
                  0.0,
                  -Math.abs(mc.thePlayer.getEntityBoundingBox().minZ - mc.thePlayer.getEntityBoundingBox().maxZ) / 2.0
               )
               .offset(0.0, -1.0, 0.0)
               .intersectsWith(box.expand(flag ? this.sneakEdgeExpandDist : 0.0, 0.0, flag2 ? this.sneakEdgeExpandDist : 0.0))) {
               this.isOverSupportBlock = true;
               break;
            }
         }
      }

      if (mc.thePlayer.onGround) {
         this.offGroundTicks = 0;
      } else {
         this.offGroundTicks++;
      }

      if (this.canRunScaffoldInCurrentScreen()) {
         this.blockPlacementData = this.findBlockPlacementData();
         BlockPos targetBlockPos2 = this.targetBlockPos;
         if (this.blockPlacementData != null) {
            this.targetBlockPos = this.blockPlacementData.getBlockPos();
         } else {
            this.targetBlockPos = null;
         }

         if (this.startAtEdge.getValue() && !mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().getMaterial().isReplaceable()) {
if (this.checkBlockClutchState(true)) {
this.waitingForStartEdge = false;
}
} else {
this.waitingForStartEdge = false;
}

         if (this.previousTargetBlockPos == null) {
            this.previousTargetBlockPos = this.targetBlockPos;
         }

         if (this.targetBlockPos != null && targetBlockPos2 != null && !this.targetBlockPos.equalsPos(targetBlockPos2)) {
            this.previousTargetBlockPos = targetBlockPos2;
         }

         this.overrideRayTrace = null;
         if (!this.waitingForStartEdge) {
            if (this.expand.getValue()) {
               this.updateExpandRotation();
            } else {
               this.updateStandardRotation();
            }
         } else {
            this.sameYBlockBaseY = mc.thePlayer.lastGroundY;
         }
      }

      this.yawRandomizeCounter.increment();
      this.pitchRandomizeCounter.increment();
   }

   private void swingHandIfAllowed() {
      if (!this.noSwing.getValue() || !this.serverSideNoSwing.getValue()) {
         mc.thePlayer.swingItem();
      }
   }

   private int switchToExploitSlot() {
      if (!this.silentMode.getSelectedValue().equals("Exploit")) {
         return -1;
      } else {
         int selectedBlockSlot2 = this.getSelectedBlockSlot();
         int currentItem = mc.thePlayer.inventory.currentItem;
         if (selectedBlockSlot2 >= 0) {
            mc.thePlayer.inventory.currentItem = selectedBlockSlot2;
         }

         return currentItem;
      }
   }

   private void restoreExploitSlot(int n) {
      if (this.silentMode.getSelectedValue().equals("Exploit")) {
         if (n >= 0) {
            mc.thePlayer.inventory.currentItem = n;
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.strafeCorrectionCounter.increment();
      this.strafeMiddleRecalcCounter.increment();
      this.directionChangeDelayCounter.increment();
      if (!this.waitingForStartEdge) {
         event.cancel();
         event.setBlockAllClicks(true);
         this.intaveBlockPlacePenalty = 0.0;
         double d = (double)Math.abs(ClientManagers.rotationController.getCurrentRotation().yaw - ClientManagers.rotationController.getPreviousRotation().yaw);
         double d2 = (double)Math.abs(ClientManagers.rotationController.getCurrentRotation().pitch - ClientManagers.rotationController.getPreviousRotation().pitch);
         this.startAtEdgeDelayCounter.increment();
         boolean flag2 = d2 > 2.0 || d > 2.0;
         if (flag2) {
            this.startAtEdgeDelayCounter.reset();
         }

         boolean flag3;
         if (!(!flag2 && this.startAtEdgeDelayCounter.hasReached(this.startAtEdgeDelay.getLastIntValue()))) {
if (this.startAtEdge.getValue() && !this.hasPlacedFirstBlock && (!this.checkBlockClutchState(true) || !this.ignoreStartAtEdgeAtBlockClutch.getValue())) {
flag3 = false;
} else {
flag3 = true;
}
} else {
flag3 = true;
}

         boolean flag5;
         flag5 = flag3;
         double d3 = Math.abs(d);
         double d4 = Math.abs(d2);
         if (this.isBlockClutching()) {
            if (d3 > this.maxYawRotationDeltaToPlaceBlocksAtBlockClutch.getValue(true)
               || d4 > this.maxPitchRotationDeltaToPlaceBlocksAtBlockClutch.getValue(true)) {
               flag5 = false;
            }
         } else if (d3 > this.maxYawRotationDeltaToPlaceBlocks.getValue(true)
            || d4 > this.maxPitchRotationDeltaToPlaceBlocks.getValue(true)) {
            flag5 = false;
         }

         if (this.stopClickingAtSneaking.getValue() && this.sneakingOnGroundForEdge && this.placedBlockSinceEnable) {
            flag5 = false;
         }

         this.ticksSinceDirectionChange++;
         if (this.strictYaw.getValue() && this.directionChangePending && (!this.ignoreStartDelayAfterDirectionChangeAtBlockClutch.getValue() || !this.isBlockClutching()) && this.hasPlacedFirstBlock) {
            if (flag2) {
               this.directionChangeDelayCounter.reset();
            }

            if (!this.directionChangeDelayCounter.hasReached(this.startDelayAfterDirectionChange.getLastIntValue())) {
               flag5 = false;
               this.ticksSinceDirectionChange = 0;
            } else {
               this.postDirectionChangeSneakTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)3);
               this.startDelayAfterDirectionChange.nextValue();
               this.directionChangePending = false;
            }
         }

         if (flag5 && this.targetBlockPos != null && this.canRunScaffoldInCurrentScreen()
               && (!mc.playerController.isHittingBlock() || ClientManagers.moduleManager.fixes.isPlaceWhileBreakEnabled())
               && (BlockUtil.isValidScaffoldBlock(mc.thePlayer.getCurrentEquippedItem()) || this.silentMode.getSelectedValue().equals("Exploit"))) {
               if (this.expand.getValue() && !this.isStationaryTowering()) {
                  MovingObjectPosition hit = new MovingObjectPosition(this.blockPlacementData.getHitVec(), this.blockPlacementData.getFacing(), this.blockPlacementData.getBlockPos());
                  if (this.clickPause.getValue() && !this.bridgeClickController.shouldClickAtEdge()) {
                     return;
                  }

                  BridgeClickController bridgeClickController2 = this.bridgeClickController;
                  ScaffoldPlaceCondition scaffoldPlaceCondition = () -> {
                     boolean flag10 = true;
                     int n = -1;
                     if (this.silentMode.getSelectedValue().equals("Exploit")) {
                        int n2 = this.getSelectedBlockSlot() + 36;
                        ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(n2).getStack();
                        BlockPos blockPos = this.blockPlacementData.getBlockPos();
                        Vec3 hitVec = this.blockPlacementData.getHitVec();
                        if (!PlayerUtil.canPlaceBlockAt(stack, blockPos, this.blockPlacementData.getFacing(), hitVec)) {
                           return false;
                        }

                        n = this.switchToExploitSlot();
                     }

                     if (mc.playerController
                        .onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem(), hit.getBlockPos(), hit.sideHit, hit.hitVec)) {
                        this.hasPlacedFirstBlock = true;
                        this.placedBlocksSinceSneak++;
                        this.godBridgePlacedBlocks++;
                        this.blocksPlacedSinceJump++;
                        this.ticksSinceGodBridgePlace = 0;
                        this.lastRightClickTimer.reset();
                        this.lastPlaceTimer.reset();
                        flag10 = false;
                        this.moonWalkEdgeExpandDist = -DefaultRandomGenerator.instance.nextDouble(this.moonWalkStartDistToBlock.getMinValue(), this.moonWalkStartDistToBlock.getMaxValue());
                        this.ticksSinceLastPlace = 0;
                        this.postPlacementStrafeTicks.nextValue();
                        this.placedBlockSinceEnable = true;
                        this.lastPlacedBlockPos = hit.getBlockPos().offset(hit.sideHit);
                        this.swingHandIfAllowed();
                     }

                     if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().stackSize == 0) {
                        mc.thePlayer.inventory.mainInventory[mc.thePlayer.inventory.currentItem] = null;
                     }

                     if (flag10) {
                        this.lastRightClickTimer.reset();
                        if (mc.thePlayer.getCurrentEquippedItem() != null) {
                           if (mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem())) {
                              mc.entityRenderer.itemRenderer.resetEquippedProgress2();
                           }
                        }
                     }

                     this.restoreExploitSlot(n);
                     return false;
                  };
                  bridgeClickController2.handleBlockClick(hit, event, scaffoldPlaceCondition);
                  return;
               }

               if (mc.objectMouseOver == null) {
                  return;
               }

               if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                  && mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock().getMaterial() != Material.air
                  && (!this.godBridge.getValue() || !this.godBridgeResetting || this.godBridgeJumpHeld)) {
                  BlockPos blockPos2 = mc.objectMouseOver.getBlockPos();
                  EnumFacing sideHit = mc.objectMouseOver.sideHit;
                  if (sideHit == EnumFacing.UP) {
                     boolean flag11 = PlayerUtil.canPlaceHeldItemAtMouseOver(mc.thePlayer.getCurrentEquippedItem());
                     if ((!this.sameY.getValue() || !flag11 || this.rightClickHeldOnGround || this.isStationaryTowering())
                        && blockPos2.equalsPos(this.targetBlockPos)
                        && this.canClickWithPause()
                        && (!this.hover.getValue() || this.hoverOffsetActive)
                        && !this.sameYBlockTopPlaced) {
                        if (!(ClientManagers.rotationController.getPreviousRotation().pitch < 85.0F) && this.intave.getValue() && !this.otherRotation.getValue()) {
if (PlayerUtil.canPlaceHeldItemAtMouseOver(mc.thePlayer.getCurrentEquippedItem())) {
flag3 = false;
} else {
flag3 = true;
}
} else {
flag3 = true;
}

                        boolean flag12 = flag3;
                        double d7;
                        if (this.intave.getValue() && this.intaveSafe.getValue()) {
                           d7 = this.intave14TimeBlockPlaceCheck.computePlaceTimeDeficit(blockPos2.offset(sideHit), mc.objectMouseOver.getBlockPos());
                        } else {
                           d7 = 0.0;
                        }

                        this.intaveBlockPlacePenalty = d7;
                        this.bridgeClickController.handleBlockClick(mc.objectMouseOver, event, () -> this.placeTopBlock(flag12));
                     }
                  } else if ((
                        blockPos2.equalsPos(this.targetBlockPos)
                           || (
                                 mc.theWorld
                                          .getCollidingBoundingBoxes(
                                             mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(-0.001, 0.0, -0.001)
                                          )
                                          .isEmpty()
                                       && mc.thePlayer.onGround
                                    || ClientManagers.rotationController.getCurrentRotation().pitch == ClientManagers.rotationController.getPreviousRotation().pitch
                              )
                              && !this.sameYBlockJumpActive
                     )
                     && this.canClickWithPause()
                     && this.canPlaceAtEdge()) {
                     double d8;
                     if (this.intave.getValue() && this.intaveSafe.getValue()) {
                        d8 = this.intave14TimeBlockPlaceCheck.computePlaceTimeDeficit(mc.objectMouseOver.getBlockPos().offset(mc.objectMouseOver.sideHit), mc.objectMouseOver.getBlockPos());
                     } else {
                        d8 = 0.0;
                     }

                     this.intaveBlockPlacePenalty = d8;
if (!(ClientManagers.rotationController.getPreviousRotation().pitch < 85.0F) && this.intave.getValue() && !this.otherRotation.getValue()) {
if (PlayerUtil.canPlaceHeldItemAtMouseOver(mc.thePlayer.getCurrentEquippedItem())) {
flag3 = false;
} else {
flag3 = true;
}
} else {
flag3 = true;
}

                     boolean flag13 = flag3;
                     this.bridgeClickController.handleBlockClick(mc.objectMouseOver, event, () -> this.placeSideBlock(flag13), this.wouldFallNextTick());
                  }
               }

               return;
            }
      }
   }

   private boolean canClickWithPause() {
      return !this.clickPause.getValue() || this.bridgeClickController.shouldClickAtEdge();
   }

   public boolean canPlaceAtEdge() {
      return this.isAtBlockEdge() && this.edgeRotateCheck.getValue() && this.edgePlaceCheck.getValue() || !this.edgeRotateCheck.getValue() || !this.edgePlaceCheck.getValue();
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (this.intave.getValue() && this.intaveSafe.getValue() && this.waitingForStartEdge) {
         this.intave14TimeBlockPlaceCheck.onClientPacket(event.getPacket());
      }
   }

   @Override
   public void onPostJumpMoveInput(EventMoveInput event) {
      if (this.canRunScaffoldInCurrentScreen()) {
         if (!this.waitingForStartEdge) {
            if (MoveUtil.isMoving()) {
               if (this.isTellyBridgeEnabled() && this.blocksSinceLastSneak > 0 && !mc.thePlayer.isSprinting()) {
                  event.setJump(false);
               }
            }

            if (this.sameY.getValue()
               && this.autoJump.getValue()
               && (MoveUtil.hasHorizontalMotion() || MoveUtil.isMoving())
               && (this.hotbarBlockCount > 5 || mc.playerController.isInCreativeMode())
               && mc.thePlayer.onGround) {
               if (!mc.thePlayer.isSprinting()) {
                  if (this.isTellyBridgeEnabled()) {
                     return;
                  }
               }

               event.setJump(true);
               if (this.isTellyBridgeEnabled() && mc.thePlayer.onGround) {
                  double[] doubles = PlayerUtil.predictPlayerPosition(9, mc.thePlayer);
                  double d = doubles[0];
                  double d3 = doubles[2];
                  this.tellyPredictedBlockDistance = this.countVoxelStepsToTarget(this.blockPlacementData, d, d3);
               }
            }
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      this.sneakingOnGroundForEdge = false;
      this.moonWalkMiddleRecalcCounter.increment();
      boolean isKeyDown = mc.gameSettings.keyBindJump.isKeyDown();
      if (this.canRunScaffoldInCurrentScreen()) {
         if (!this.waitingForStartEdge) {
            if (this.startAtEdge.getValue() && (!this.hasPlacedFirstBlock || this.ticksSinceFirstPlace < this.startEdgeSneakTicks)) {
               event.setSneak(true);
            }

            if (this.strictYaw.getValue() && (!this.ignoreStartDelayAfterDirectionChangeAtBlockClutch.getValue() || !this.isBlockClutching())) {
               if (this.startDelayAfterDirectionChange.getMaxValue() > 0.0 && (this.directionChangePending || this.ticksSinceDirectionChange <= this.postDirectionChangeSneakTicks)) {
                  event.setSneak(true);
               }
            }

            if (this.hasPlacedFirstBlock) {
               this.ticksSinceFirstPlace++;
            }

            if (!this.isEarlySneakCalculationActive()) {
               this.isOverSupportBlock = false;
               this.hotbarBlockCount = this.countHotbarBlocks();
               List collidingBoundingBoxes = mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -1.0, 0.0));
               boolean flag3 = Math.abs(mc.thePlayer.motionX) > 0.01;
               boolean flag4 = Math.abs(mc.thePlayer.motionZ) > 0.01;

               for (AxisAlignedBB box : (Iterable<AxisAlignedBB>)(collidingBoundingBoxes)) {
                  if (mc.thePlayer
                     .getEntityBoundingBox()
                     .expand(
                        Math.abs(mc.thePlayer.motionX) < 0.002
                           ? 0.0
                           : -Math.abs(mc.thePlayer.getEntityBoundingBox().minX - mc.thePlayer.getEntityBoundingBox().maxX) / 2.0,
                        0.0,
                        Math.abs(mc.thePlayer.motionZ) < 0.002
                           ? 0.0
                           : -Math.abs(mc.thePlayer.getEntityBoundingBox().minZ - mc.thePlayer.getEntityBoundingBox().maxZ) / 2.0
                     )
                     .offset(0.0, -1.0, 0.0)
                     .intersectsWith(box.expand(flag3 ? this.sneakEdgeExpandDist : 0.0, 0.0, flag4 ? this.sneakEdgeExpandDist : 0.0))) {
                     this.isOverSupportBlock = true;
                     break;
                  }
               }
            }

            if ((double)Math.abs(ClientManagers.rotationController.getPreviousRotation().yaw - ClientManagers.rotationController.getLastTickRotation().yaw) > 1.5
               || (double)Math.abs(ClientManagers.rotationController.getCurrentRotation().yaw - ClientManagers.rotationController.getPreviousRotation().yaw) > 1.5) {
               if (this.sneakAtRotation.getValue()) {
                  event.setSneak(true);
               }

               if (this.stopAtRotation.getValue()) {
                  event.setMoveForward(0.0F);
                  event.setMoveStrafe(0.0F);
               }
            }

            if (this.hover.getValue() && !this.hoverJumpStarted && mc.thePlayer.onGround) {
               event.setJump(true);
               this.hoverJumpStarted = true;
            }

            if (this.reduceVelocity.getValue()) {
               if (mc.thePlayer.hurtTime == 9) {
                  this.reducingVelocity = true;
               } else if (mc.thePlayer.hurtTime == 0) {
                  this.reducingVelocity = false;
               }

               if (this.reducingVelocity) {
                  double posX = mc.thePlayer.posX;
                  double posZ = mc.thePlayer.posZ;
                  if (mc.thePlayer.hurtTime == 9) {
                     this.velocityAnchorPos = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
                     posX += mc.thePlayer.motionX;
                     posZ += mc.thePlayer.motionZ;
                  }

                  if (this.velocityAnchorPos != null) {
                     double d = this.velocityAnchorPos.xCoord - posX;
                     double d2 = this.velocityAnchorPos.zCoord - posZ;
                     if (Math.sqrt(d * d + d2 * d2) < 0.15) {
                        this.reducingVelocity = false;
                     }

                     event.setYaw((float)(MathHelper.atan2(d2, d) * 180.0 / Math.PI - 90.0));
                  }
               }
            }

            if (event.getMoveForward() == 0.0F && event.getMoveStrafe() == 0.0F && this.godBridge.getValue() && !this.godBridgeResetting && mc.thePlayer.onGround) {
               this.resetGodBridgeState();
            }

            if (this.targetBlockPos != null
               && (this.towerWhileMoving.getValue() && isKeyDown || this.isStationaryTowering())
               && !this.towerMode.getSelectedValue().equals("Intave1")
               && !this.towerMode.getSelectedValue().equals("Intave2")
               && !this.towerMode.getSelectedValue().equals("Legit")) {
               event.setJump(false);
            }

            if (this.sneakNoBlocks.getValue()) {
               if (!BlockUtil.isValidScaffoldBlock(mc.thePlayer.getCurrentEquippedItem())
                  && (this.getSelectedBlockSlot() < 0 && !this.firstSilentSwitchPending || this.silentMode.getSelectedValue().equals("None"))
                  && mc.thePlayer.onGround
                  && !mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ)).getBlock().isFullBlock()) {
                  event.setSneak(true);
               }
            }

            if (this.stopNoBlocks.getValue()) {
               if (!BlockUtil.isValidScaffoldBlock(mc.thePlayer.getCurrentEquippedItem()) && (this.getSelectedBlockSlot() < 0 && !this.firstSilentSwitchPending || this.silentMode.getSelectedValue().equals("None"))) {
                  event.setMoveForward(0.0F);
                  event.setMoveStrafe(0.0F);
               }
            }

            if (this.sneak.getValue()) {
               event.setSneakForwardMultiplier(this.sneakSlowDown.getValue());
               event.setSneakStrafeMultiplier(this.sneakSlowDown.getValue());
            }

            if (this.isIntaveFastSneakActive()) {
               event.setSneak(true);
               event.setSneakForwardMultiplier(1.0);
               event.setSneakStrafeMultiplier(0.0);
            }

            if (this.moveFix.getSelectedValue().equals("Default")) {
               event.setMoveFixMode(MoveFixMode.DEFAULT);
            } else if (this.moveFix.getSelectedValue().equals("Advanced")) {
               event.setMoveFixMode(MoveFixMode.ADVANCED);
            }

            if (!this.moveFix.getSelectedValue().equals("None") && RotationUtil.isNearCardinalYaw(this.getMovementYaw())) {
               float f6 = this.applyStrictYaw(Augustus.getInstance().getYawPitchHelper().realYaw, false);
               this.cachedStrictMoveYaw = f6;
               event.setYaw(f6);
            }

            if (this.moveFixIntave.getValue() && !this.moveFix.getSelectedValue().equals("None")) {
               event.setPreventInstantReverse(true);
            }

            if (this.sameY.getValue() && this.intave.getValue() && this.intaveFast2.getValue() && this.rightClickHeldOnGround) {
               event.setJump(true);
            }

            if (this.stopAtStart.getValue()) {
               long stopAtStartDelayMs2 = (long)this.stopAtStartDelayMs;
               if (!this.enableTimer.reached(stopAtStartDelayMs2)) {
                  event.setMoveForward(0.0F);
               }
            }

            if (this.jump.getValue()) {
               if (this.jumpOnlyWhileSpacePressed.getValue() && MoveUtil.isMoving()) {
                  event.setJump(false);
                  isKeyDown = false;
               }

               int n = RotationUtil.isNearCardinalYaw(this.computeBaseBridgeYaw()) ? this.jumpAfterXBlocksForward.getLastIntValue() : this.jumpAfterXBlocksDiagonal.getLastIntValue();
               if (this.blocksPlacedSinceJump >= n && (!this.jumpOnlyWhileSpacePressed.getValue() || mc.gameSettings.keyBindJump.isKeyDown())) {
                  this.blocksPlacedSinceJump = 0;
                  this.jumpAfterXBlocksForward.nextValue();
                  this.jumpAfterXBlocksDiagonal.nextValue();
                  event.setJump(true);
               }
            }

            if (this.sneak.getValue()) {
               boolean isNearCardinalYaw = RotationUtil.isNearCardinalYaw(this.computeBaseBridgeYaw());
               if (this.timeJumpForOneStackForward.getValue() && isKeyDown && isNearCardinalYaw && MoveUtil.isMoving() && !this.isStationaryTowering()) {
                  isKeyDown = !mc.thePlayer.onGround;
                  if ((mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().getMaterial().isReplaceable() || !this.hasPlacedFirstBlock)
                     && mc.thePlayer.onGround) {
                     event.setJump(true);
                     event.setSneak(true);
                     isKeyDown = true;
                  } else {
                     event.setJump(false);
                  }
               }

               boolean flag18 = isNearCardinalYaw && this.isStrafeActive()
                  ? RotationUtil.isNearCardinalYaw(ClientManagers.rotationController.getPreviousRotation().yaw - this.strafeYawOffset)
                  : RotationUtil.isNearCardinalYaw(ClientManagers.rotationController.getPreviousRotation().yaw);
               if ((isKeyDown && !this.sameY.getValue() || this.sameY.getValue() && this.rightClickHeldOnGround) && !this.isStationaryTowering() && this.hotbarBlockCount > 0) {
                  if (this.offGroundTicks == 0) {
                     if (this.oneStackDiagonal.getValue()) {
                        RandomNumberGenerator instance = DefaultRandomGenerator.instance;
                        double minValue = this.minOffGroundSneakTickDiagonal.getMinValue();
                        double maxValue = this.minOffGroundSneakTickDiagonal.getMaxValue();
                        this.minOffGroundSneakDiagonal = instance.nextInt(minValue, maxValue);
                        RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
                        minValue = this.maxOffGroundSneakTickDiagonal.getMinValue();
                        maxValue = this.maxOffGroundSneakTickDiagonal.getMaxValue();
                        this.maxOffGroundSneakDiagonal = instance2.nextInt(minValue, maxValue);
                     }

                     if (this.oneStackForward.getValue()) {
                        RandomNumberGenerator instance3 = DefaultRandomGenerator.instance;
                        double minValue2 = this.minOffGroundSneakTickForward.getMinValue();
                        double maxValue2 = this.minOffGroundSneakTickForward.getMaxValue();
                        this.minOffGroundSneakForward = instance3.nextInt(minValue2, maxValue2);
                        RandomNumberGenerator instance4 = DefaultRandomGenerator.instance;
                        minValue2 = this.maxOffGroundSneakTickForward.getMinValue();
                        maxValue2 = this.maxOffGroundSneakTickForward.getMaxValue();
                        this.maxOffGroundSneakForward = instance4.nextInt(minValue2, maxValue2);
                     }
                  }

                  if (this.oneStackDiagonal.getValue() && !flag18 && this.offGroundTicks >= this.minOffGroundSneakDiagonal && this.offGroundTicks < this.maxOffGroundSneakDiagonal) {
                     event.setSneak(true);
                  }

                  if (this.oneStackForward.getValue() && flag18 && this.offGroundTicks >= this.minOffGroundSneakForward && this.offGroundTicks < this.maxOffGroundSneakForward) {
                     event.setSneak(true);
                  }
               }

               if (!isKeyDown
                  && (!this.sameYBlockJumpActive || !this.sameYBlock.getValue())
                  && (this.sneakHoldTicks < this.sneakTicks.getLastIntValue() - 1 || !this.isOverSupportBlock && (flag18 ? this.blocksSinceLastSneak >= this.sneakIntervalBlocksForward : this.blocksSinceLastSneak >= this.sneakIntervalBlocksDiagonal))) {
                  event.setSneak(true);
                  this.sneakingOnGroundForEdge = mc.thePlayer.onGround;
               }

               this.blocksSinceLastSneak = this.placedBlocksSinceSneak;
               this.sneakHoldTicks++;
               if (event.isSneak() && !this.wasSneakingLastTick) {
                  this.sneakHoldTicks = 0;
                  this.blocksSinceLastSneak = 0;
                  this.placedBlocksSinceSneak = 0;
                  this.sneakTicks.nextValue();
                  RandomNumberGenerator instance5 = DefaultRandomGenerator.instance;
                  double minValue3 = this.sneakBlocksForward.getMinValue();
                  double maxValue3 = this.sneakBlocksForward.getMaxValue();
                  this.sneakIntervalBlocksForward = instance5.nextInt(minValue3, maxValue3);
                  RandomNumberGenerator instance6 = DefaultRandomGenerator.instance;
                  minValue3 = this.sneakBlocksDiagonal.getMinValue();
                  maxValue3 = this.sneakBlocksDiagonal.getMaxValue();
                  this.sneakIntervalBlocksDiagonal = instance6.nextInt(minValue3, maxValue3);
               }

               if (!event.isSneak() && this.wasSneakingLastTick) {
                  this.sneakEdgeExpandDist = -DefaultRandomGenerator.instance.nextDouble(this.startSneakDistToBlock.getMinValue(), this.startSneakDistToBlock.getMaxValue());
               }

               this.wasSneakingLastTick = event.isSneak();
            }

            if (!this.adhsMode.getSelectedValue().equals("None")) {
               if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
                  for (PotionEffect potionEffect : (Iterable<PotionEffect>)(mc.thePlayer.getActivePotionEffects())) {
                     if (potionEffect.getPotionID() == Potion.moveSpeed.id && mc.thePlayer.ticksExisted % (3 + potionEffect.getAmplifier()) != 0) {
                        event.setSneak(true);
                        break;
                     }
                  }
               } else if (this.adhsMode.getSelectedValue().equals("ADS")) {
                  if (mc.thePlayer.ticksExisted % 3 == 0 || mc.thePlayer.ticksExisted % 4 == 0) {
                     event.setSneak(true);
                  }
               } else if (this.adhsMode.getSelectedValue().equals("ADHS") && mc.thePlayer.ticksExisted % 2 == 0) {
                  event.setSneak(true);
               }
            }

            if (this.moonWalk.getValue()
               && (!this.onlyOnGround.getValue() || mc.thePlayer.onGround)
               && this.targetBlockPos != null
               && !isKeyDown
               && MoveUtil.isMoving()
               && RotationUtil.isNearCardinalYaw(ClientManagers.rotationController.getPreviousRotation().yaw)
               && (!this.sneakingOnGroundForEdge || !this.stopClickingAtSneaking.getValue())) {
               EnumFacing horizontalFacing = mc.thePlayer.getHorizontalFacing(ClientManagers.rotationController.getPreviousRotation().yaw);
               boolean flag25 = false;

               for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -1.0, 0.0)))) {
                  if (mc.thePlayer
                     .getEntityBoundingBox()
                     .expand(-0.3, 0.0, -0.3)
                     .offset(0.0, -1.0, 0.0)
                     .intersectsWith(
                        box2.expand(
                           horizontalFacing != EnumFacing.EAST && horizontalFacing != EnumFacing.WEST ? 0.0 : this.moonWalkEdgeExpandDist,
                           0.0,
                           horizontalFacing != EnumFacing.NORTH && horizontalFacing != EnumFacing.SOUTH ? 0.0 : this.moonWalkEdgeExpandDist
                        )
                     )) {
                     flag25 = true;
                     break;
                  }
               }

               if (!flag25 && this.wasOverMoonWalkBlock) {
                  this.moonWalkTicks = 0;
                  this.minMoonwalkTicks.nextValue();
               }

               this.wasOverMoonWalkBlock = flag25;
               this.ticksSinceLastPlace++;
               this.moonWalkTicks++;
               double lastValue = this.moonWalkMiddle.getLastValue();
               double lastValue2 = this.moonWalkWideness.getLastValue();
               if (!flag25 || this.ticksSinceLastPlace <= this.postPlacementStrafeTicks.getLastIntValue() || this.moonWalkTicks <= this.minMoonwalkTicks.getLastIntValue()) {
                  switch (horizontalFacing) {
                     case EAST:
                        if ((double)this.targetBlockPos.getZ() + lastValue - lastValue2 > mc.thePlayer.posZ) {
                           this.setMoonWalkLeftStrafe(event);
                        } else if ((double)this.targetBlockPos.getZ() + lastValue + lastValue2 < mc.thePlayer.posZ) {
                           this.setMoonWalkRightStrafe(event);
                        }
                        break;
                     case WEST:
                        if ((double)this.targetBlockPos.getZ() + lastValue + lastValue2 < mc.thePlayer.posZ) {
                           this.setMoonWalkLeftStrafe(event);
                        } else if ((double)this.targetBlockPos.getZ() + lastValue - lastValue2 > mc.thePlayer.posZ) {
                           this.setMoonWalkRightStrafe(event);
                        }
                        break;
                     case SOUTH:
                        if ((double)this.targetBlockPos.getX() + lastValue + lastValue2 < mc.thePlayer.posX) {
                           this.setMoonWalkLeftStrafe(event);
                        } else if ((double)this.targetBlockPos.getX() + lastValue - lastValue2 > mc.thePlayer.posX) {
                           this.setMoonWalkRightStrafe(event);
                        }
                        break;
                     case NORTH:
                        if ((double)this.targetBlockPos.getX() + lastValue - lastValue2 > mc.thePlayer.posX) {
                           this.setMoonWalkLeftStrafe(event);
                        } else if ((double)this.targetBlockPos.getX() + lastValue + lastValue2 < mc.thePlayer.posX) {
                           this.setMoonWalkRightStrafe(event);
                        }
                  }

                  this.applyMoonWalkStrafe(event);
               }
            }

            if (this.isStrafeActive() && RotationUtil.isNearCardinalYaw(this.getMovementYaw()) && this.targetBlockPos != null && this.correctStrafeWithKeyBinds.getValue()) {
               double lastValue3 = this.correctStrafeWithKeyBindsWidth.getLastValue();
               float strafeYawOffset2 = this.strafeYawOffset;
               boolean value3 = this.correctStrafeWithKeyBindsCage.getValue();
               double d19;
               double d20;
               if (Math.abs(this.strafeYawOffset) > 45.0F) {
                  d19 = this.strafeCorrectionMiddle + lastValue3;
                  d20 = this.strafeCorrectionMiddle - lastValue3;
                  strafeYawOffset2 = -strafeYawOffset2;
               } else if ((double)Math.abs(this.strafeYawOffset) >= 22.5) {
                  if (value3) {
                     d19 = this.strafeCorrectionMiddle + lastValue3;
                     d20 = this.strafeCorrectionMiddle - lastValue3;
                  } else {
                     d19 = this.strafeCorrectionMiddle - lastValue3;
                     d20 = this.strafeCorrectionMiddle + lastValue3;
                  }
               } else {
                  d19 = this.strafeCorrectionMiddle + lastValue3;
                  d20 = this.strafeCorrectionMiddle - lastValue3;
               }

               switch (mc.thePlayer.getHorizontalFacing(this.computeBaseBridgeYaw() - 180.0F)) {
                  case EAST:
                     if (!(mc.thePlayer.posZ > (double)this.targetBlockPos.getZ() + d19) || !(strafeYawOffset2 > 0.0F) && !value3) {
                        if (mc.thePlayer.posZ < (double)this.targetBlockPos.getZ() + d20 && (strafeYawOffset2 < 0.0F || value3)) {
                           this.strafeCorrectionCounter.reset();
                           this.setKeyBindCorrectionRight(event);
                        }
                     } else {
                        this.strafeCorrectionCounter.reset();
                        this.setKeyBindCorrectionLeft(event);
                     }
                     break;
                  case WEST:
                     if (!(mc.thePlayer.posZ > (double)this.targetBlockPos.getZ() + d19) || !(strafeYawOffset2 < 0.0F) && !value3) {
                        if (mc.thePlayer.posZ < (double)this.targetBlockPos.getZ() + d20 && (strafeYawOffset2 > 0.0F || value3)) {
                           this.strafeCorrectionCounter.reset();
                           this.setKeyBindCorrectionLeft(event);
                        }
                        break;
                     }

                     this.strafeCorrectionCounter.reset();
                     this.setKeyBindCorrectionRight(event);
                     break;
                  case SOUTH:
                     if (!(mc.thePlayer.posX > (double)this.targetBlockPos.getX() + d19) || !(strafeYawOffset2 < 0.0F) && !value3) {
                        if (mc.thePlayer.posX < (double)this.targetBlockPos.getX() + d20 && (strafeYawOffset2 > 0.0F || value3)) {
                           this.strafeCorrectionCounter.reset();
                           this.setKeyBindCorrectionLeft(event);
                        }
                        break;
                     }

                     this.strafeCorrectionCounter.reset();
                     this.setKeyBindCorrectionRight(event);
                     break;
                  case NORTH:
                     if (!(mc.thePlayer.posX > (double)this.targetBlockPos.getX() + d19) || !(strafeYawOffset2 > 0.0F) && !value3) {
                        if (mc.thePlayer.posX < (double)this.targetBlockPos.getX() + d20 && (strafeYawOffset2 < 0.0F || value3)) {
                           this.strafeCorrectionCounter.reset();
                           this.setKeyBindCorrectionRight(event);
                        }
                     } else {
                        this.strafeCorrectionCounter.reset();
                        this.setKeyBindCorrectionLeft(event);
                     }
               }

               TickCounter tickCounter = this.strafeCorrectionCounter;
               if (!tickCounter.hasReached(this.correctStrafeWithKeyBindsDuration.getLastIntValue())) {
                  if (event.getMoveForward() != 0.0F) {
                     event.setMoveStrafe((float)this.keyBindStrafeCorrectionDir);
                  } else if (event.getMoveStrafe() != 0.0F) {
                     event.setMoveForward((float)this.keyBindStrafeCorrectionDir);
                  }

                  this.strafeCorrectionActive = true;
               } else if (this.strafeCorrectionActive) {
                  this.strafeCorrectionActive = false;
                  this.correctStrafeWithKeyBindsWidth.nextValue();
                  this.correctStrafeWithKeyBindsDuration.nextValue();
                  tickCounter = this.strafeMiddleRecalcCounter;
                  if (tickCounter.hasReached(this.correctStrafeWithKeyBindsDelayToRecalculateMiddle.getLastIntValue())) {
                     this.correctStrafeWithKeyBindsDelayToRecalculateMiddle.nextValue();
                     this.strafeMiddleRecalcCounter.reset();
                     this.strafeCorrectionMiddle = this.rollStrafeCorrectionMiddle();
                  }
               }
            }

            if (this.sameYBlock.getValue()) {
               if (this.sameYBlockJumpActive && mc.thePlayer.onGround && mc.thePlayer.posY == this.sameYBlockBaseY) {
                  this.sameYBlockJumpActive = false;
               }

               if (this.stopMovementUntilLand && this.stopMovementAfterJump.getValue()) {
                  if (!mc.thePlayer.onGround) {
                     event.setMoveForward(0.0F);
                     event.setMoveStrafe(0.0F);
                  } else {
                     this.stopMovementUntilLand = false;
                  }
               }

               if (event.isJump()) {
                  this.sameYBlockJumpActive = true;
                  this.stopMovementUntilLand = true;
                  this.sameYBlockTopPlaced = false;
               }
            }

            if (this.intaveBlockPlacePenalty > 0.0) {
               if (mc.thePlayer.onGround) {
                  event.setSneak(true);
               }

               event.setMoveForward(this.intaveBlockPlacePenalty > 22.5 ? -1.0F : 0.0F);
            }

            if (this.intave.getValue() && this.intaveSafe.getValue()) {
               this.intave14TimeBlockPlaceCheck.onMoveInput(event);
            }

            if (this.godBridge.getValue()) {
               this.handleGodBridgeInput(event);
            }

            this.lastAppliedStrafeInput = event.getMoveForward() != 0.0F ? (int)event.getMoveStrafe() : (int)event.getMoveForward();
         }
      }
   }

   private void handleGodBridgeInput(EventMoveInput event) {
      if (mc.gameSettings.keyBindJump.isKeyDown()) {
         this.godBridgeJumpHeld = true;
         this.resetGodBridgeState();
      } else if (this.godBridgeJumpHeld) {
         if (mc.thePlayer.onGroundTicks > 2) {
            this.godBridgeJumpHeld = false;
         }
      } else {
         boolean wasCardinalDirection2 = this.wasCardinalDirection;
         this.wasCardinalDirection = RotationUtil.isNearCardinalYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
         if (wasCardinalDirection2 != this.wasCardinalDirection) {
            this.resetGodBridgeState();
         }

         if (!RotationUtil.isNearCardinalYaw(Augustus.getInstance().getYawPitchHelper().realYaw)) {
            if (this.godBridgeResetting && !this.disableGodbridgeSafetyFeature.getValue()) {
               event.setSneak(true);
               if (event.getMoveForward() > 0.0F && this.safeWalkEdgeDetected && this.lastGodBridgePosX == mc.thePlayer.posX && this.lastGodBridgePosZ == mc.thePlayer.posZ || this.godBridgeSetupTicks > 3) {
                  event.setMoveForward(0.0F);
                  event.setMoveStrafe(0.0F);
                  this.godBridgeSetupTicks++;
                  if ((double)this.godBridgeSetupTicks > this.startDelay.nextValue() - 4.0) {
                     if ((double)this.godBridgeSetupTicks < this.startDelay.nextValue() - 4.0 + 5.0) {
                        event.setMoveStrafe(1.0F);
                     } else if ((double)this.godBridgeSetupTicks < this.startDelay.nextValue() - 4.0 + 7.0) {
                        event.setMoveForward(-1.0F);
                     } else {
                        this.godBridgeResetting = false;
                     }
                  }
               }
            }

            this.lastGodBridgePosX = mc.thePlayer.posX;
            this.lastGodBridgePosZ = mc.thePlayer.posZ;
         } else {
            if (this.godBridgePlacedBlocks >= this.jumpAfterXBlocks.getLastIntValue() && this.ticksSinceGodBridgePlace >= this.jumpDelayAfterPlace.getLastIntValue()) {
               this.ticksSinceGodBridgePlace = 0;
               this.godBridgeJumpCount++;
               this.jumpAfterXBlocks.nextValue();
               this.jumpDelayAfterPlace.nextValue();
               if (this.godBridgeJumpCount >= this.resetGodBridgeAfterXJumps.getLastIntValue()) {
                  this.resetGodBridgeState();
               } else {
                  event.setJump(true);
                  this.godBridgePlacedBlocks = 0;
               }
            }

            this.ticksSinceGodBridgePlace++;
            if (!this.godBridgeResetting) {
               event.setMoveStrafe(0.0F);
            } else {
               this.godBridgePlacedBlocks = 0;
               if (!(event.getMoveForward() <= 0.0F) && !this.alignGodBridgePosition(event) && (this.safeWalkEdgeDetected || this.godBridgeSetupTicks > 3)) {
                  if (this.godBridgeSetupTicks >= this.startDelay.getLastIntValue()) {
                     this.godBridgeResetting = false;
                  } else {
                     this.godBridgeSetupTicks++;
                     event.setMoveForward(0.0F);
                     event.setMoveStrafe(0.0F);
                     if (this.godBridgeSetupTicks < this.godBridgeSneakSetupTicks) {
                        event.setSneak(true);
                     }
                  }
               } else {
                  this.resetGodBridgeState();
                  event.setSneak(true);
               }
            }
         }
      }
   }

   public double rollStrafeCorrectionMiddle() {

      double d;
      for (d = this.correctStrafeWithKeyBindsMiddle.nextValue(); d > this.correctStrafeWithKeyBindsMiddleExclusion.getMinValue(); d = this.correctStrafeWithKeyBindsMiddle.nextValue()) {
         if (!(d < this.correctStrafeWithKeyBindsMiddleExclusion.getMaxValue())) {
            break;
         }

         if (!(this.correctStrafeWithKeyBindsMiddle.getMinValue() < this.correctStrafeWithKeyBindsMiddleExclusion.getMinValue())) {
            if (!(this.correctStrafeWithKeyBindsMiddle.getMaxValue() > this.correctStrafeWithKeyBindsMiddleExclusion.getMaxValue())) {
               break;
            }
         }
      }

      return d;
   }

   @Override
   public void onOrientCamera(EventOrientCamera event) {
      if (!this.waitingForStartEdge) {
         if (this.sneak.getValue() && this.hideSneakAnimation.getValue()) {
            event.setEyeHeight(1.62F);
         }
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (!this.waitingForStartEdge) {
         if (this.moveFix.getSelectedValue().equals("None")) {
            event.setYaw(MoveUtil.getMovementYaw());
         }
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (!this.waitingForStartEdge) {
         if (this.moveFix.getSelectedValue().equals("None")) {
            event.setYaw(MoveUtil.getMovementYaw());
         }

         if (this.intave.getValue() && this.intaveSafe.getValue()) {
            this.intave14TimeBlockPlaceCheck.onMoveUpdate();
         }
      }
   }

   @Override
   public void onRayCast() {
      if (!this.waitingForStartEdge) {
         if (this.overrideRayTrace != null) {
            MovingObjectPosition overrideRayTrace2 = this.overrideRayTrace;
            mc.objectMouseOver = overrideRayTrace2;
            this.overrideRayTrace = null;
         }
      }
   }

   @Override
   public void onPreSafeWalk(EventSafeWalk event) {
      if (!this.waitingForStartEdge) {
         if (!(!this.removeSneakStop.getValue() && !this.isIntaveFastSneakActive())) {
if (this.sneakNoBlocks.getValue()) {
               ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
               if (currentEquippedItem != null && BlockUtil.isValidScaffoldBlock(currentEquippedItem)
                  || mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ)).getBlock().isFullBlock()) {
                  event.setDisableSneakSafeWalk(true);
               }
            }
}

         if (this.safeWalk.getValue()
            && (this.safeWalkAir.getValue() || mc.thePlayer.onGround)
            && (!this.safeWalkOnlyDiagonal.getValue() || !RotationUtil.isNearCardinalYaw(ClientManagers.rotationController.getPreviousRotation().yaw))) {
            event.setSafeWalk(true);
            event.setDisableSneakSafeWalk(false);
         }

         if (this.safeWalkNoBlocks.getValue()) {
            if (!BlockUtil.isValidScaffoldBlock(mc.thePlayer.getCurrentEquippedItem()) && this.getSelectedBlockSlot() < 0 && !this.firstSilentSwitchPending) {
               event.setSafeWalk(true);
               event.setDisableSneakSafeWalk(false);
            }
         }

         if (this.sneakNoBlocks.getValue()) {
            if (!BlockUtil.isValidScaffoldBlock(mc.thePlayer.getCurrentEquippedItem()) && this.getSelectedBlockSlot() < 0 && !this.firstSilentSwitchPending) {
               event.setDisableSneakSafeWalk(false);
            }
         }
      }
   }

   @Override
   public void onSwing(EventSwing event) {
      if (this.noSwing.getValue()) {
         event.setCancelClientSwing(true);
      }
   }

   @Override
   public void onSprint(EventSprint event) {
      event.setSprintKeyDown(!this.mode.getSelectedValue().equals("None"));
      event.setForceSprint(this.mode.getSelectedValue().equals("AllDirection") && MoveUtil.isMoving());
   }

   @Override
   public void onJumpSprint(EventSprint event) {
      event.setOmniJumpSprint(this.jumpSprint.getValue() && MoveUtil.isMoving());
   }

   @Override
   public void onMotionSprint(EventSprint event) {
      if (!this.waitingForStartEdge) {
         event.setMotionSprint(this.mode.getSelectedValue().equals("AllDirectionMotion") && MoveUtil.isMoving());
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (!this.waitingForStartEdge) {
         if (!ClientManagers.moduleManager.timer.isToggled() || ClientManagers.moduleManager.timer.time.getValue() == 1.0) {
            if (this.timerDrop.getValue()) {
               if (!mc.gameSettings.keyBindJump.isKeyDown()) {
                  if (mc.thePlayer.ticksExisted % 3 == 0) {
                     mc.getTimer().timerSpeed = (float)MathHelper.clamp_double(this.timerSpeed.getMinValue(), 0.1F, 100.0);
                  } else {
                     mc.getTimer().timerSpeed = (float)MathHelper.clamp_double(this.timerSpeed.getMaxValue(), 0.1F, 100.0);
                  }
               }
            } else if (!mc.gameSettings.keyBindJump.isKeyDown()) {
                  mc.getTimer().timerSpeed = (float)this.timer.getValue();
               }
         }

         if (this.hover.getValue() && this.hoverJumpStarted && mc.thePlayer.ticksExisted % 2 == 0 && mc.thePlayer.onGround && event.getY() % 1.0 == 0.0) {
            this.hoverOffsetActive = true;
            double d = Math.random() * 1.0E-4;
            event.setY(event.getY() + d);
            event.setOnGround(false);
         }
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (!this.waitingForStartEdge) {
         double d;
         if (mc.thePlayer.onGround) {
            d = RotationUtil.isNearCardinalYaw(ClientManagers.rotationController.getPreviousRotation().yaw) ? this.motionMultiplierGroundForward.nextValue() : this.motionMultiplierGroundStrafe.nextValue();
         } else {
            d = RotationUtil.isNearCardinalYaw(ClientManagers.rotationController.getPreviousRotation().yaw) ? this.motionMultiplierAirForward.nextValue() : this.motionMultiplierAirStrafe.nextValue();
         }

         event.setX(event.getX() * d);
         event.setZ(event.getZ() * d);
      }
   }

   @Override
   public void onPreUpdate() {
      if (!this.waitingForStartEdge) {
         if (this.sameY.getValue()
            && this.intaveFast2.getValue()
            && this.intave.getValue()
            && !this.rightClickHeldOnGround
            && MoveUtil.isMoving()
            && this.hotbarBlockCount > 0
            && mc.thePlayer.hurtTime <= 0
            && !mc.thePlayer.isSprinting()) {
            if (this.enableTimer.reached(400L)) {
               if (mc.thePlayer.onGround) {
                  mc.thePlayer.jump();
               } else if (mc.thePlayer.motionY > 0.0) {
                  MoveUtil.setSpeed(0.28F, false);
               }
            }
         }
      }
   }

   private boolean alignGodBridgePosition(EventMoveInput event) {
      boolean flag = false;
      EnumFacing horizontalFacing = mc.thePlayer.getHorizontalFacing(Augustus.getInstance().getYawPitchHelper().realYaw);
      double d = horizontalFacing != EnumFacing.NORTH && horizontalFacing != EnumFacing.SOUTH ? mc.thePlayer.posZ : mc.thePlayer.posX;
      double d2 = d < 0.0 ? 1.0 + d % 1.0 : d % 1.0;
      boolean flag2 = horizontalFacing == EnumFacing.NORTH || horizontalFacing == EnumFacing.EAST;
      if (d2 < 0.5) {
         if (d2 < 0.2) {
            event.setMoveStrafe(flag2 ? -1.0F : 1.0F);
            flag = true;
         } else if (d2 > 0.3) {
            event.setMoveStrafe(flag2 ? 1.0F : -1.0F);
            flag = true;
         }
      } else if (d2 < 0.7) {
         event.setMoveStrafe(flag2 ? -1.0F : 1.0F);
         flag = true;
      } else if (d2 > 0.8) {
         event.setMoveStrafe(flag2 ? 1.0F : -1.0F);
         flag = true;
      }

      return flag;
   }

   private void resetGodBridgeState() {
      if (!this.godBridgeResetting) {
         this.resetGodBridgeAfterXJumps.nextValue();
         this.startDelay.nextValue();
         this.godBridgeSneakSetupTicks = DefaultRandomGenerator.instance.nextInt((byte)5, (byte)7);
      }

      this.godBridgeResetting = true;
      this.godBridgeSetupTicks = 0;
      this.godBridgeJumpCount = 0;
      this.ticksSinceGodBridgePlace = 0;
      this.godBridgePlacedBlocks = 0;
   }

   private float applyStrictYaw(float f, boolean flag) {
      if (!this.strictYaw.getValue()) {
         return f;
      } else if (this.lastStrictYawSector < 0
         || !this.hasPlacedFirstBlock
         || !this.changeDirectionOnlyIfBlockUnderYou.getValue()
         || mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).getBlock().getMaterial() != Material.air) {
         float f2 = MathHelper.wrapAngleTo180_float(f);
         if (f2 > -25.0F && f2 < 25.0F) {
            return this.applyStrictYawError(0.0F, (byte)0, flag);
         } else if (f2 > -25.0F && f2 < 70.0F) {
            return this.applyStrictYawError(45.0F, (byte)1, flag);
         } else if (f2 > 70.0F && f2 < 115.0F) {
            return this.applyStrictYawError(90.0F, (byte)2, flag);
         } else if (f2 > 115.0F && f2 < 160.0F) {
            return this.applyStrictYawError(135.0F, (byte)3, flag);
         } else if (f2 > 160.0F || f2 < -160.0F) {
            return this.applyStrictYawError(180.0F, (byte)4, flag);
         } else if (f2 > -160.0F && f2 < -115.0F) {
            return this.applyStrictYawError(-135.0F, (byte)5, flag);
         } else if (f2 > -115.0F && f2 < -70.0F) {
            return this.applyStrictYawError(-90.0F, (byte)6, flag);
         } else if (f2 > -70.0F && f2 < -25.0F) {
            return this.applyStrictYawError(-45.0F, (byte)7, flag);
         } else {
            return f;
         }
      } else {
         return flag ? this.cachedStrictRotationYaw : this.cachedStrictMoveYaw;
      }
   }

   private float applyStrictYawError(float f, int n, boolean flag) {
      if (flag) {
         if (!(this.strictYawIncorrectFactor.getMaxValue() <= 0.0)) {
            if (n != this.lastStrictYawSector) {
               this.lastStrictYawSector = n;
               this.strictYawIncorrectFactor.nextValue();
               this.strictYawErrorSign = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
               this.directionChangePending = true;
               this.directionChangeDelayCounter.reset();
            }

            return f + (float)((double)this.strictYawErrorSign * this.strictYawIncorrectFactor.getLastValue());
         }
      }

      return f;
   }

   private float applyMovementYawOffset(float f) {
      if (this.rotateWithMovement.getValue()) {
         if (mc.gameSettings.keyBindBack.isKeyDown()) {
            f += 180.0F;
            if (!this.rotateWithMovementOnlyOrthogonal.getValue()) {
               if (mc.gameSettings.keyBindLeft.isKeyDown()) {
                  f += 45.0F;
               } else if (mc.gameSettings.keyBindRight.isKeyDown()) {
                  f -= 45.0F;
               }
            }
         } else if (mc.gameSettings.keyBindForward.isKeyDown()) {
            if (!this.rotateWithMovementOnlyOrthogonal.getValue()) {
               if (mc.gameSettings.keyBindLeft.isKeyDown()) {
                  f -= 45.0F;
               } else if (mc.gameSettings.keyBindRight.isKeyDown()) {
                  f += 45.0F;
               }
            }
         } else if (mc.gameSettings.keyBindRight.isKeyDown()) {
            f += 90.0F;
         } else if (mc.gameSettings.keyBindLeft.isKeyDown()) {
            f -= 90.0F;
         }
      }

      return f;
   }

   private float getMovementYaw() {
      return this.applyMovementYawOffset(Augustus.getInstance().getYawPitchHelper().realYaw);
   }

   private float computeBaseBridgeYaw() {
      float f = this.applyMovementYawOffset(Augustus.getInstance().getYawPitchHelper().realYaw);
      f = this.applyStrictYaw(f, true);
      if (this.isTellyBridgeEnabled() && !mc.thePlayer.onGround) {
         f = this.cachedStrictRotationYaw;
      } else {
         this.cachedStrictRotationYaw = f;
      }

      if (this.shouldImproveRotation() && MoveUtil.hasHorizontalMotion()) {
         double d = mc.thePlayer.posX + mc.thePlayer.motionX;
         double posY = mc.thePlayer.posY;
         double d2 = mc.thePlayer.posZ + mc.thePlayer.motionZ;
         Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
         float f2 = 180.0F;
         float yaw = RotationUtil.getRotationsToPos(d, posY, d2, previousRotation, 180.0F, f2, false).yaw;
         if (Math.abs(EntityBoundsUtil.getShortestSignedAngleDelta((double)MathHelper.wrapAngleTo180_float(yaw), (double)MathHelper.wrapAngleTo180_float(f))) > 12.0
               && !this.moonWalk.getValue()
               && mc.thePlayer.hurtResistantTime != 0
            || mc.thePlayer.hurtTime != 0) {
            f = yaw;
         }
      }

      if (this.isTellyBridgeEnabled()) {
         if (this.shouldRotateBackwardInTelly()) {
            f -= 180.0F;
         }
      } else if (this.flickMode.getSelectedValue().equals("Jump")) {
         if (!mc.thePlayer.onGround) {
            if (mc.thePlayer.airTicks != 1 && PlayerUtil.predictPlayerPosition(0, mc.thePlayer)[9] != 1.0) {
               f -= 180.0F;
            } else {
               f -= 160.0F;
            }
         }
      } else if (this.flickMode.getSelectedValue().equals("Simple")) {
         if (this.isAtBlockEdge()) {
            f -= 180.0F;
         }
      } else {
         f -= 180.0F;
      }

      if (this.reduceYawRotation.getValue() && MoveUtil.isMoving()) {
         if (this.enableTimer.reached(500L)) {
            if (Math.abs(this.lastTargetYaw - f) < 25.0F) {
               f = this.lastTargetYaw;
            } else {
               this.lastTargetYaw = f;
            }

            return f;
         }
      }

      this.lastTargetYaw = f;
      return f;
   }

   private boolean shouldImproveRotation() {
      return this.improve.getValue() && (!this.improveOnlyAtClutch.getValue() || this.isImproveClutchConditionMet());
   }

   private void updateExpandRotation() {
      float f = this.rollActiveYawSpeed();
      float f2 = this.rollActivePitchSpeed();
      float f4 = this.rotationUtil.stepYawWithGcd(f, ClientManagers.rotationController.getPreviousRotation().yaw, Augustus.getInstance().getYawPitchHelper().realYaw);
      if (this.isStationaryTowering() && this.targetBlockPos != null) {
         Block block = mc.theWorld.getBlockState(this.targetBlockPos).getBlock();
         Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
         Rotation rotationsToPos = RotationUtil.getRotationsToPos(MathHelper.clamp_double(
            positionEyes.xCoord, (double)this.targetBlockPos.getX() + block.getBlockBoundsMinX(), (double)this.targetBlockPos.getX() + block.getBlockBoundsMaxY()
         ), MathHelper.clamp_double(
            positionEyes.yCoord, (double)this.targetBlockPos.getY() + block.getBlockBoundsMinY(), (double)this.targetBlockPos.getY() + block.getBlockBoundsMaxY() - 0.02
         ), MathHelper.clamp_double(
            positionEyes.zCoord, (double)this.targetBlockPos.getZ() + block.getBlockBoundsMinZ(), (double)this.targetBlockPos.getZ() + block.getBlockBoundsMaxZ()
         ), ClientManagers.rotationController.getPreviousRotation(), f, f2, true);
         if (new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).equalsXZ(this.targetBlockPos)) {
            rotationsToPos.yaw = f4;
         }

         rotationsToPos = RotationUtil.applyGcdFix(rotationsToPos, ClientManagers.rotationController.getPreviousRotation());
         ClientManagers.rotationController.requestRotation(rotationsToPos, (byte)-1, this, this.getRotationPriority());
      } else {
         double value = this.expandLength.getValue();
         double d4 = Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
         value /= d4;
         if (mc.thePlayer.onGround) {
            this.expandAirYOffset = 0.0;
         }

         if (!mc.thePlayer.onGround && (!this.sameY.getValue() || MouseInputUtil.isButtonDown(1))) {
            this.expandAirYOffset = 1.0;
            if (PlayerUtil.predictPlayerPosition(0, mc.thePlayer)[9] == 1.0) {
               return;
            }

            double d5 = 0.0;
            double d6 = 0.0;

            for (int i = 0; i < 10; i++) {
               double[] doubles = PlayerUtil.predictPlayerPosition(i, mc.thePlayer);
               double d7 = doubles[1] - mc.thePlayer.lastGroundY;
               if (d7 > 1.0 && d7 < 2.0) {
                  d5 = doubles[0];
                  d6 = doubles[2];
               }
            }

            BlockPos pos = new BlockPos(d5, mc.thePlayer.lastGroundY - 0.1, d6);
            if (d5 != 0.0
               && d6 != 0.0
               && mc.theWorld.getBlockState(pos).getBlock().isFullBlock()
               && !mc.theWorld.getBlockState(pos.up()).getBlock().isFullBlock()) {
               this.blockPlacementData = new BlockPlacementData(pos, EnumFacing.UP, new Vec3(d5, mc.thePlayer.lastGroundY, d6));
               Rotation rotationsToPos2 = RotationUtil.getRotationsToPos(this.blockPlacementData.getHitVec().xCoord, this.blockPlacementData.getHitVec().yCoord, this.blockPlacementData.getHitVec().zCoord, ClientManagers.rotationController.getPreviousRotation(), f, f2, false);
               RotationUtil rotationUtil3 = this.rotationUtil;
               float yaw2 = rotationsToPos2.yaw;
               f4 = rotationUtil3.stepYawWithGcd(f, ClientManagers.rotationController.getPreviousRotation().yaw, yaw2);
               RotationUtil rotationUtil4 = this.rotationUtil;
               float pitch = rotationsToPos2.pitch;
               float f5 = rotationUtil4.updatePitchWithIntaveSin(f2, ClientManagers.rotationController.getPreviousRotation().pitch, pitch);
               ClientManagers.rotationController.requestRotation(new Rotation(f4, f5), (byte)-1, this, this.getRotationPriority());
               return;
            }
         }

         for (float j = 0.5F; (double)j < Math.min(20.0, value); j = (float)((double)j + 0.25)) {
            double d8 = mc.thePlayer.posX + MathHelper.clamp_double(mc.thePlayer.motionX * (double)j, (double)(-j), (double)j);
            double d9 = mc.thePlayer.lastGroundY - 0.5 + this.expandAirYOffset;
            double d10 = mc.thePlayer.posZ + MathHelper.clamp_double(mc.thePlayer.motionZ * (double)j, (double)(-j), (double)j);
            MovingObjectPosition hit = mc.theWorld.rayTraceBlocks(d8, d9, d10, mc.thePlayer.posX, d9, mc.thePlayer.posZ, false, false, true);
            if (MoveUtil.isMoving()
               && hit != null
               && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
               && !hit.getBlockPos().equalsPos(new BlockPos(d8, d9, d10))) {
               this.blockPlacementData = new BlockPlacementData(hit.getBlockPos(), hit.sideHit, hit.hitVec);
               this.targetBlockPos = this.blockPlacementData.getBlockPos();
               Rotation rotationsToPos3 = RotationUtil.getRotationsToPos(this.blockPlacementData.getHitVec().xCoord, this.blockPlacementData.getHitVec().yCoord, this.blockPlacementData.getHitVec().zCoord, ClientManagers.rotationController.getPreviousRotation(), f, f2, false);
               RotationUtil rotationUtil5 = this.rotationUtil;
               float yaw4 = rotationsToPos3.yaw;
               f4 = rotationUtil5.stepYawWithGcd(f, ClientManagers.rotationController.getPreviousRotation().yaw, yaw4);
               RotationUtil rotationUtil6 = this.rotationUtil;
               float pitch3 = rotationsToPos3.pitch;
               float f6 = rotationUtil6.updatePitchWithIntaveSin(f2, ClientManagers.rotationController.getPreviousRotation().pitch, pitch3);
               ClientManagers.rotationController.requestRotation(new Rotation(f4, f6), (byte)-1, this, this.getRotationPriority());
               return;
            }
         }

         double d11 = mc.thePlayer.posX + MathHelper.clamp_double(mc.thePlayer.motionX * value, -value, value);
         double d12 = mc.thePlayer.lastGroundY - 0.5;
         double d13 = mc.thePlayer.posZ + MathHelper.clamp_double(mc.thePlayer.motionZ * value, -value, value);
         MovingObjectPosition hit2 = mc.theWorld.rayTraceBlocks(d11, d12, d13, mc.thePlayer.posX, d12, mc.thePlayer.posZ, false, false, true);
         if (MoveUtil.isMoving() && hit2 != null && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.blockPlacementData = new BlockPlacementData(hit2.getBlockPos(), hit2.sideHit, hit2.hitVec);
            this.targetBlockPos = this.blockPlacementData.getBlockPos();
            Rotation rotationsToPos4 = RotationUtil.getRotationsToPos(this.blockPlacementData.getHitVec().xCoord, this.blockPlacementData.getHitVec().yCoord, this.blockPlacementData.getHitVec().zCoord, ClientManagers.rotationController.getPreviousRotation(), f, f2, false);
            RotationUtil rotationUtil7 = this.rotationUtil;
            float yaw6 = rotationsToPos4.yaw;
            f4 = rotationUtil7.stepYawWithGcd(f, ClientManagers.rotationController.getPreviousRotation().yaw, yaw6);
            RotationUtil rotationUtil8 = this.rotationUtil;
            float pitch5 = rotationsToPos4.pitch;
            float f7 = rotationUtil8.updatePitchWithIntaveSin(f2, ClientManagers.rotationController.getPreviousRotation().pitch, pitch5);
            ClientManagers.rotationController.requestRotation(new Rotation(f4, f7), (byte)-1, this, this.getRotationPriority());
         } else {
            ClientManagers.rotationController.requestRotation(ClientManagers.rotationController.getPreviousRotation(), (byte)-1, this, this.getRotationPriority());
         }
      }
   }

   private TuplePair computeTargetRotation() {
      float f = this.computeBaseBridgeYaw();
      boolean shouldApplyStrafeYaw = this.shouldApplyStrafeYaw(f);
      String selectedValue = this.flickMode.getSelectedValue();
      boolean flag2;
      if (this.isTellyBridgeEnabled()) {
         flag2 = this.shouldRotateBackwardInTelly();
      } else if (!selectedValue.equals("None")) {
         if (selectedValue.equals("Jump")) {
            flag2 = !mc.thePlayer.onGround;
         } else {
            flag2 = this.flickUserStrafeSettingForForwardLooking.getValue() || this.isAtBlockEdge();
         }
      } else {
         flag2 = true;
      }

      boolean flag3 = flag2;
      if (shouldApplyStrafeYaw && flag3 && this.targetBlockPos != null) {
         float strafeYawOffset2 = this.strafeYawOffset;
         double d;
         double d2;
         if (this.initialStrafeDirectionPending) {
            d = 0.5;
            d2 = 0.5;
         } else if ((double)Math.abs(this.strafeYawOffset) <= 22.5) {
            double d3 = MathHelper.clamp_double(this.strafeWidth.nextValue(), 0.0, 0.5);
            d = 0.5 + d3;
            d2 = 0.5 - d3;
         } else {
            double d4 = MathHelper.clamp_double(MathStatisticsUtil.lawOfSinesSide(MathStatisticsUtil.angleBetweenDegrees(mc.thePlayer.getVectorForRotation(80.0F, 0.0F), mc.thePlayer.getVectorForRotation(80.0F, this.strafeYawOffset)), 90.0, 1.6) - 0.05, 0.0, 0.5);
            d = 0.5 + d4;
            d2 = 0.5 - d4;
         }

         if (this.initialStrafeDirectionPending || this.correctRotationWhileBuilding.getValue()) {
if (!(selectedValue.equals("Simple") && !this.isAtBlockEdge())) {
switch (mc.thePlayer.getHorizontalFacing(f - 180.0F)) {
               case EAST:
                  if (mc.thePlayer.posZ > (double)this.targetBlockPos.getZ() + d && strafeYawOffset2 > 0.0F) {
                     this.strafeYawOffset = -((float)Math.max(1.0, this.strafeDegree.nextValue()));
                  } else if (mc.thePlayer.posZ < (double)this.targetBlockPos.getZ() + d2 && strafeYawOffset2 < 0.0F) {
                     this.strafeYawOffset = (float)Math.max(1.0, this.strafeDegree.nextValue());
                  }
                  break;
               case WEST:
                  if (mc.thePlayer.posZ > (double)this.targetBlockPos.getZ() + d && strafeYawOffset2 < 0.0F) {
                     this.strafeYawOffset = (float)Math.max(1.0, this.strafeDegree.nextValue());
                  } else if (mc.thePlayer.posZ < (double)this.targetBlockPos.getZ() + d2 && strafeYawOffset2 > 0.0F) {
                     this.strafeYawOffset = -((float)Math.max(1.0, this.strafeDegree.nextValue()));
                  }
                  break;
               case SOUTH:
                  if (mc.thePlayer.posX > (double)this.targetBlockPos.getX() + d && strafeYawOffset2 < 0.0F) {
                     this.strafeYawOffset = (float)Math.max(1.0, this.strafeDegree.nextValue());
                  } else if (mc.thePlayer.posX < (double)this.targetBlockPos.getX() + d2 && strafeYawOffset2 > 0.0F) {
                     this.strafeYawOffset = -((float)Math.max(1.0, this.strafeDegree.nextValue()));
                  }
                  break;
               case NORTH:
                  if (mc.thePlayer.posX > (double)this.targetBlockPos.getX() + d && strafeYawOffset2 > 0.0F) {
                     this.strafeYawOffset = -((float)Math.max(1.0, this.strafeDegree.nextValue()));
                  } else if (mc.thePlayer.posX < (double)this.targetBlockPos.getX() + d2 && strafeYawOffset2 < 0.0F) {
                     this.strafeYawOffset = (float)Math.max(1.0, this.strafeDegree.nextValue());
                  }
            }
}
}

         this.initialStrafeDirectionPending = false;
         boolean flag4 = (double)Math.abs(this.strafeYawOffset) > 22.5;
         if (selectedValue.equals("Simple") && this.flickUserStrafeSettingForForwardLooking.getValue()) {
            if (!this.isAtBlockEdge()) {
               flag4 = !flag4;
            }
         }

         if (flag4) {
            f -= this.strafeYawOffset;
         } else {
            f += this.strafeYawOffset;
         }
      }

      if (selectedValue.equals("Jump")) {
         f += (float)(mc.thePlayer.ticksExisted % 2 == 0 ? 1 : 0);
      }

      if (this.randomizeYaw.getValue() && (this.getYawRandomizeChance() > 0.0 || this.yawRandomizationTriggered)) {
         boolean flag5 = this.yawRandomizationTriggered;
         f += this.computeYawRandomOffset(flag5);
      }

      float pitch = ClientManagers.rotationController.getPreviousRotation().pitch;
      if (pitch > 75.0F) {
         this.keepInitialPitch = false;
      }

      if (MoveUtil.isMoving()) {
if (!(this.enableTimer.reached(200L) && this.keepInitialPitch)) {
pitch = 80.34F;
}
} else {
pitch = 80.34F;
}

      if (this.godBridge.getValue() && !this.godBridgeResetting) {
         f = ClientManagers.rotationController.getPreviousRotation().yaw;
      }

      if (this.targetBlockPos == null) {
         return new TuplePair(new Rotation(f, pitch), true);
      } else {
         Block block;
         block = mc.theWorld.getBlockState(this.targetBlockPos).getBlock();
         if (this.shouldImproveRotation()) {
if (!(!selectedValue.equals("None") && !this.isAtBlockEdge())) {
if (!this.isTellyBridgeEnabled() || this.shouldRotateBackwardInTelly()) {
               float f2 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f, 180.0F);
               double d5 = (double)this.targetBlockPos.getX() + block.getBlockBoundsMinX();
               double d6 = (double)this.targetBlockPos.getX() + block.getBlockBoundsMaxX();
               double d7 = (double)this.targetBlockPos.getY() + block.getBlockBoundsMaxY();
               double d8 = (double)this.targetBlockPos.getZ() + block.getBlockBoundsMinZ();
               double d9 = (double)this.targetBlockPos.getZ() + block.getBlockBoundsMaxZ();
               ArrayList list = new ArrayList();
               Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f3 = 180.0F;
               list.add(RotationUtil.getRotationsToPos(d5, d7, d8, previousRotation, 180.0F, f3, false).yaw);
               previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f5 = 180.0F;
               list.add(RotationUtil.getRotationsToPos(d5, d7, d9, previousRotation, 180.0F, f5, false).yaw);
               previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f7 = 180.0F;
               list.add(RotationUtil.getRotationsToPos(d6, d7, d8, previousRotation, 180.0F, f7, false).yaw);
               previousRotation = ClientManagers.rotationController.getPreviousRotation();
               float f9 = 180.0F;
               list.add(RotationUtil.getRotationsToPos(d6, d7, d9, previousRotation, 180.0F, f9, false).yaw);
               Collections.sort(list);
               f = MathHelper.clamp_float(f2, (Float)list.get(0) + 0.5F, (Float)list.get(list.size() - 1) - 0.5F);
               this.lastTargetYaw = shouldApplyStrafeYaw ? f - this.strafeYawOffset : f;
            }
}
}

         if (this.isStationaryTowering()) {
            Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
            double d10 = MathHelper.clamp_double(
               positionEyes.xCoord, (double)this.targetBlockPos.getX() + block.getBlockBoundsMinX(), (double)this.targetBlockPos.getX() + block.getBlockBoundsMaxY()
            );
            double d11 = MathHelper.clamp_double(
               positionEyes.yCoord, (double)this.targetBlockPos.getY() + block.getBlockBoundsMinY(), (double)this.targetBlockPos.getY() + block.getBlockBoundsMaxY() - 0.02
            );
            double d12 = MathHelper.clamp_double(
               positionEyes.zCoord, (double)this.targetBlockPos.getZ() + block.getBlockBoundsMinZ(), (double)this.targetBlockPos.getZ() + block.getBlockBoundsMaxZ()
            );
            Rotation previousRotation2 = ClientManagers.rotationController.getPreviousRotation();
            float f11 = 180.0F;
            Rotation rotationsToPos = RotationUtil.getRotationsToPos(d10, d11, d12, previousRotation2, 180.0F, f11, false);
            if (new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ).equalsXZ(this.targetBlockPos)) {
               rotationsToPos.yaw = f;
            }

            rotationsToPos = RotationUtil.applyGcdFix(rotationsToPos, ClientManagers.rotationController.getPreviousRotation());
            return new TuplePair(rotationsToPos, true);
         } else {
            if (RotationUtil.isNearCardinalYaw(f)) {
               if (this.staticPitchBool.getValue() && (!mc.gameSettings.keyBindJump.isKeyDown() || this.staticPitchAtJump.getValue()) && !this.shouldIgnoreStaticPitchForClutch()) {
                  if (!this.staticPitchRandomizeOnlyAtStart.getValue()) {
                     this.staticPitch.nextValue();
                  }

                  float lastValue = (float)this.staticPitch.getLastValue();
                  Rotation rotation = new Rotation(f, lastValue);
                  return new TuplePair(this.applyMinimalMouseMovement(rotation), true);
               }
            } else if (this.staticPitchBoolDiagonal.getValue() && (!mc.gameSettings.keyBindJump.isKeyDown() || this.staticPitchAtJumpDiagonal.getValue()) && !this.shouldIgnoreDiagonalStaticPitchForClutch()) {
               if (!this.staticPitchRandomizeOnlyAtStartDiagonal.getValue()) {
                  this.staticPitchDiagonal.nextValue();
               }

               float lastValue2 = (float)this.staticPitchDiagonal.getLastValue();
               Rotation rotation2 = new Rotation(f, lastValue2);
               return new TuplePair(this.applyMinimalMouseMovement(rotation2), true);
            }

            return this.godBridge.getValue()
               ? new TuplePair(new Rotation(f, RotationUtil.isNearCardinalYaw(Augustus.getInstance().getYawPitchHelper().realYaw) ? 75.83F : 76.5F), true)
               : new TuplePair(new Rotation(f, pitch), false);
         }
      }
   }

   private Rotation applyMinimalMouseMovement(Rotation rotation) {
      if (this.minimalMouseMovement.getValue()) {
         long l = (long)this.minimalMouseMoveIntervalMs;
         if (this.minimalMouseMoveTimer.reached(l) && mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().getMaterial() != Material.air
            )
          {
            this.minimalMouseMoveCounter.increment();
            int n = this.minimalMouseMoveDurationTicks;
            if (this.minimalMouseMoveCounter.hasReached(n)) {
               this.minimalMouseMoveIntervalMs = DefaultRandomGenerator.instance.nextInt((short)750, (short)1500);
               this.minimalMouseMoveDurationTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)2);
               this.minimalMouseMoveCounter.reset();
               this.minimalMouseMoveTimer.reset();
            }

            double d = 0.2;
            double d2 = -0.2;
            float f = DefaultRandomGenerator.instance.nextFloat(d2, d);
            double d3 = 0.2;
            double d4 = -0.2;
            float f2 = DefaultRandomGenerator.instance.nextFloat(d4, d3);
            if (this.minimalMouseYawOffset + f > 5.0F || this.minimalMouseYawOffset + f < 5.0F) {
               f *= -1.0F;
            }

            Rotation rotation2 = RotationUtil.applyGcdFix(rotation.yaw + this.minimalMouseYawOffset + f, rotation.pitch + this.minimalMousePitchOffset + f2, ClientManagers.rotationController.getPreviousRotation());
            boolean isNearCardinalYaw = RotationUtil.isNearCardinalYaw(this.getMovementYaw());
            double d5;
            if (isNearCardinalYaw) {
               d5 = this.staticPitchDiagonal.getMinValue();
            } else {
               d5 = this.staticPitch.getMaxValue();
            }

            float f3 = (float)d5;
            if (isNearCardinalYaw) {
               d5 = this.staticPitchDiagonal.getMaxValue();
            } else {
               d5 = this.staticPitch.getMaxValue();
            }

            float f4 = (float)d5;
            this.minimalMouseYawOffset += f;
            if (rotation2.pitch >= f4) {
               this.minimalMousePitchOffset -= f2;
               rotation2 = RotationUtil.applyGcdFix(rotation.yaw + this.minimalMouseYawOffset, MathHelper.clamp_float(rotation.pitch + this.minimalMousePitchOffset, f3, f4), ClientManagers.rotationController.getPreviousRotation());
            } else if (rotation2.pitch <= f3) {
               this.minimalMousePitchOffset -= f2;
               rotation2 = RotationUtil.applyGcdFix(rotation.yaw + this.minimalMouseYawOffset, MathHelper.clamp_float(rotation.pitch + this.minimalMousePitchOffset, f3, f4), ClientManagers.rotationController.getPreviousRotation());
            } else {
               this.minimalMousePitchOffset += f2;
            }

            rotation = rotation2;
         } else {
            rotation.yaw = rotation.yaw + this.minimalMouseYawOffset;
            rotation.pitch = rotation.pitch + this.minimalMousePitchOffset;
         }
      } else {
         this.minimalMousePitchOffset = 0.0F;
         this.minimalMouseYawOffset = 0.0F;
      }

      return rotation;
   }

   private void updateStandardRotation() {
      TuplePair tuplePair = this.computeTargetRotation();
      Rotation first = (Rotation)tuplePair.getFirst();
      Rotation rotation;
      if (this.isBezierRotationModeActive()) {
         this.bezierRotationStepper.aimSpeed = this.getActiveAimSpeed() / 100.0 * 16.0 + 2.0;
         this.bezierRotationStepper.adaptCurveIfNeeded = true;
         byte b = 3;
         if (!ClientManagers.rotationController.getPreviousRotation().equalsRotation(this.lastBezierRotation) || this.rotationKinematicState == null || this.bezierCurve == null) {
            this.rotationKinematicState = this.rotationUtil.createKinematicState(ClientManagers.rotationController.getPreviousRotation());
            RotationUtil rotationUtil3 = this.rotationUtil;
            Rotation previousRotation3 = ClientManagers.rotationController.getPreviousRotation();
            double d = 10.0;
            this.bezierCurve = rotationUtil3.createBezierCurve(previousRotation3, first, b, 0.3, d, false);
            this.bezierTickCounter = 0;
         }

         if (this.bezierTickCounter % b == 0) {
            RotationUtil rotationUtil4 = this.rotationUtil;
            Rotation previousRotation4 = ClientManagers.rotationController.getPreviousRotation();
            double d3 = 10.0;
            this.bezierCurve = rotationUtil4.createBezierCurve(previousRotation4, first, b, 0.3, d3, false);
         }

         double d5 = 0.0;
         BezierCurve2D bezierCurve2 = this.bezierCurve;
         RotationKinematicState rotationKinematicState2 = this.rotationKinematicState;
         BezierRotationStepper bezierRotationStepper2 = this.bezierRotationStepper;
         this.rotationKinematicState = this.rotationUtil.stepAndAdvanceBezierCurve(bezierRotationStepper2, rotationKinematicState2, bezierCurve2, b, d5);
         this.bezierTickCounter++;
         rotation = RotationUtil.applyGcdFix(
            RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, (float)this.rotationKinematicState.getYaw(), 180.0F),
            RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, (float)this.rotationKinematicState.getPitch(), 180.0F),
            ClientManagers.rotationController.getPreviousRotation().yaw,
            ClientManagers.rotationController.getPreviousRotation().pitch
         );
         this.lastBezierRotation = new Rotation(rotation);
      } else {
         float f = this.rollActiveYawSpeed();
         float f2 = this.rollActivePitchSpeed();
         RotationUtil rotationUtil5 = this.rotationUtil;
         float yaw = ClientManagers.rotationController.getPreviousRotation().yaw;
         float yaw2 = first.yaw;
         float f6 = rotationUtil5.stepYawAccelerated(f, yaw, yaw2, this.getActiveYawAcceleration(), ClientManagers.rotationController.getPreviousRotation().yaw - ClientManagers.rotationController.getLastTickRotation().yaw);
         RotationUtil rotationUtil6 = this.rotationUtil;
         yaw2 = ClientManagers.rotationController.getPreviousRotation().pitch;
         float f7 = RotationUtil.applyPitchGcd(first.pitch, ClientManagers.rotationController.getPreviousRotation().pitch);
         RandomizedRangeSettings randomizedRangeSettings2 = this.getActivePitchAcceleration();
         float f8 = ClientManagers.rotationController.getPreviousRotation().pitch - ClientManagers.rotationController.getLastTickRotation().pitch;
         float f10 = yaw2;
         rotation = new Rotation(f6, rotationUtil6.stepPitchAccelerated(f2, f10, f7, randomizedRangeSettings2, f8));
      }

      if ((Boolean)tuplePair.getSecond()) {
         float pitch = rotation.pitch;
         this.applyScaffoldRotation(rotation.yaw, pitch, (MovingObjectPosition)null, false);
      } else {
         float yaw4 = rotation.yaw;
         float pitch2 = rotation.pitch;
         if (this.targetBlockPos == null) {
            this.applyScaffoldRotation(yaw4, pitch2, (MovingObjectPosition)null);
         } else {
            if (this.isStationaryTowering()) {
               this.applyScaffoldRotation(yaw4, pitch2, (MovingObjectPosition)null);
            } else {
               if (this.godBridge.getValue()) {
                  this.applyScaffoldRotation(yaw4, pitch2, (MovingObjectPosition)null);
                  return;
               }

               if (this.recycleRotation.getValue()) {
                  MovingObjectPosition hit = RayCastUtil.rayCast(1.0F, yaw4, pitch2);
                  boolean flag4 = !this.intave.getValue()
                     && this.towerWhileMoving.getValue()
                     && hit.getBlockPos() != null
                     && !mc.thePlayer.onGround
                     && mc.thePlayer.posY - (double)hit.getBlockPos().getY() > 2.0
                     && mc.thePlayer.posY - (double)hit.getBlockPos().getY() < 2.5;
                  if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                     && BlockUtil.isSolidBlock(hit.getBlockPos())
                     && hit.sideHit != EnumFacing.DOWN
                     && (flag4 ? hit.sideHit == EnumFacing.UP : hit.sideHit != EnumFacing.UP)
                     && this.targetBlockPos.equalsPos(hit.getBlockPos())) {
                     this.applyScaffoldRotation(yaw4, pitch2, hit);
                     return;
                  }
               }

               if (this.isAtBlockEdgeForYaw(yaw4) && this.targetBlockPos != null) {
                  float f11 = yaw4;
                  if (this.randomizeYaw.getValue() && this.randomizeYawChanceWhilePitchRotation.getValue() > 0.0 && yaw4 == ClientManagers.rotationController.getPreviousRotation().yaw) {
                     this.yawRandomizationTriggered = true;
                     yaw4 = RotationUtil.applyYawGcd(this.computeYawRandomOffset(true) + yaw4, ClientManagers.rotationController.getPreviousRotation().yaw);
                  }

                  ArrayList list = new ArrayList();
                  float f13 = 0.0F;

                  for (float i = 15.0F; i < (float)(!this.intave.getValue() && !this.otherRotation.getValue() ? 90 : 85); i += 0.04F) {
                     float f14 = RotationUtil.applyPitchGcd(i, ClientManagers.rotationController.getPreviousRotation().pitch);
                     if (f14 != f13) {
                        MovingObjectPosition hit2 = RayCastUtil.rayCast(1.0F, yaw4, f14);
                        if (hit2 != null) {
                           boolean flag6 = !this.intave.getValue()
                              && this.towerWhileMoving.getValue()
                              && hit2.getBlockPos() != null
                              && !mc.thePlayer.onGround
                              && mc.thePlayer.posY - (double)hit2.getBlockPos().getY() > 2.0
                              && mc.thePlayer.posY - (double)hit2.getBlockPos().getY() < 2.5;
                           if (hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                              && BlockUtil.isSolidBlock(hit2.getBlockPos())
                              && hit2.sideHit != EnumFacing.DOWN
                              && (flag6 ? hit2.sideHit == EnumFacing.UP : hit2.sideHit != EnumFacing.UP)
                              && this.targetBlockPos.equalsPos(hit2.getBlockPos())
                              && (hit2.hitVec.yCoord % 1.0 < 0.88 && hit2.hitVec.yCoord % 1.0 > 0.18 || !this.intave.getValue())) {
                              list.add(new ScaffoldRaycastResult(hit2, f14));
                           }
                        }
                     }

                     f13 = f14;
                  }

                  ScaffoldRaycastResult scaffoldRaycastResult = this.selectBestPitchCandidate(list, yaw4);
                  if (scaffoldRaycastResult != null) {
                     TuplePair tuplePair2 = this.clampPitchCorrection(scaffoldRaycastResult, yaw4);
                     float first2 = (Float)tuplePair2.getFirst();
                     this.applyScaffoldRotation(yaw4, first2, (MovingObjectPosition)tuplePair2.getSecond(), true);
                     return;
                  }

                  if (this.extraBlockSearch.getValue()
                     && (!this.extraBlockSearchOnlyAtClutch.getValue() || this.isBlockClutching())
                     && mc.theWorld
                        .getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(-0.001, 0.0, -0.001))
                        .isEmpty()
                     && mc.thePlayer.onGround) {
                     int n = Math.min(30, (int)this.yawSpeed.getMaxValue());
                     BlockPos pos = new BlockPos(mc.thePlayer.posX + mc.thePlayer.motionX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ + mc.thePlayer.motionZ);
                     int n2 = n * 2;
                     ArrayList list2 = new ArrayList();
                     byte b2 = 0;
                     float f16 = ClientManagers.rotationController.getPreviousRotation().yaw + (float)n;
                     float f17 = ClientManagers.rotationController.getPreviousRotation().yaw - (float)n;

                     for (int j = 0; n2 > 0; n2--) {
                        if (b2 == 0) {
                           float f18 = yaw4 + (float)(++j);
                           float f19 = yaw4 - (float)j;
                           if (f18 > f16) {
                              b2 = 1;
                           }

                           if (f19 < f17) {
                              b2 = -1;
                           }

                           list2.add(f18);
                           list2.add(f19);
                           n2 -= 2;
                        } else if (b2 > 0) {
                           j++;
                           n2--;
                           list2.add(yaw4 - (float)j);
                        } else {
                           j++;
                           n2--;
                           list2.add(yaw4 + (float)j);
                        }
                     }

                     for (float f20 : (Iterable<Float>)(list2)) {
                        list.clear();
                        f13 = 0.0F;
                        float f21 = RotationUtil.applyYawGcd(f20, ClientManagers.rotationController.getPreviousRotation().yaw);

                        for (float k = 15.0F; k < (float)(!this.intave.getValue() && !this.otherRotation.getValue() ? 90 : 85); k += 0.04F) {
                           float f22 = RotationUtil.applyPitchGcd(k, ClientManagers.rotationController.getPreviousRotation().pitch);
                           if (f22 != f13) {
                              MovingObjectPosition hit4 = RayCastUtil.rayCast(1.0F, f21, f22);
                              if (hit4.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                                 && BlockUtil.isSolidBlock(hit4.getBlockPos())
                                 && hit4.sideHit != EnumFacing.DOWN
                                 && hit4.sideHit != EnumFacing.UP
                                 && (hit4.getBlockPos().offset(hit4.sideHit).equalsXZ(pos) && !this.sameYBlockJumpActive || hit4.getBlockPos().equalsPos(this.targetBlockPos))
                                 && (hit4.hitVec.yCoord % 1.0 < 0.88 && hit4.hitVec.yCoord % 1.0 > 0.18 || !this.intave.getValue())) {
                                 list.add(new ScaffoldRaycastResult(hit4, f22));
                              }
                           }

                           f13 = f22;
                        }

                        scaffoldRaycastResult = this.selectBestPitchCandidate(list, f21);
                        if (scaffoldRaycastResult != null) {
                           TuplePair tuplePair3 = this.clampPitchCorrection(scaffoldRaycastResult, f21);
                           float first3 = (Float)tuplePair3.getFirst();
                           this.applyScaffoldRotation(f21, first3, (MovingObjectPosition)tuplePair3.getSecond(), true);
                           return;
                        }
                     }
                  }

                  yaw4 = f11;
               }
            }

            this.applyScaffoldRotation(yaw4, pitch2, (MovingObjectPosition)null);
         }
      }
   }

   private TuplePair clampPitchCorrection(ScaffoldRaycastResult scaffoldRaycastResult, float f) {
      if (this.maxPitchCorrection.getValue() == 180.0) {
         return new TuplePair(scaffoldRaycastResult.pitch, scaffoldRaycastResult.hitResult);
      } else {
         MovingObjectPosition hitResult = scaffoldRaycastResult.hitResult;
         float f2 = this.isBlockClutching() ? (float)this.maxPitchCorrectionAtBlockClutch.getValue() : (float)this.maxPitchCorrection.getValue();
         float f3 = MathHelper.clamp_float(scaffoldRaycastResult.pitch, ClientManagers.rotationController.getPreviousRotation().pitch - f2, ClientManagers.rotationController.getPreviousRotation().pitch + f2);
         if (f3 != scaffoldRaycastResult.pitch) {
            f3 = RotationUtil.applyPitchGcd(f3, ClientManagers.rotationController.getPreviousRotation().pitch);
            hitResult = RayCastUtil.rayCast(1.0F, f, f3);
         }

         return new TuplePair(f3, hitResult);
      }
   }

   private boolean isBlockClutching() {
      return this.checkBlockClutchState(false);
   }

   private boolean checkBlockClutchState(boolean flag) {
      if (mc.thePlayer.onGround) {
         return false;
      } else if (mc.thePlayer.fallDistance > 3.0F) {
         return true;
      } else {
         return mc.thePlayer.hurtTime != 0 ? true : mc.thePlayer.posY < mc.thePlayer.lastGroundY + (flag ? 0.5 : 0.9) && mc.thePlayer.motionY < 0.0;
      }
   }

   private boolean isImproveClutchConditionMet() {
      return this.improveOnlyAtClutch.getValue() && this.improve.getValue() ? this.isBlockClutching() : false;
   }

   private boolean shouldIgnoreStaticPitchForClutch() {
      return this.ignoreStaticPitchForBlockClutches.getValue() && this.staticPitchBool.getValue() ? this.isBlockClutching() : false;
   }

   private boolean shouldIgnoreDiagonalStaticPitchForClutch() {
      return this.ignoreStaticPitchForBlockClutchesDiagonal.getValue() && this.staticPitchBoolDiagonal.getValue() ? this.isBlockClutching() : false;
   }

   private ScaffoldRaycastResult selectBestPitchCandidate(ArrayList list, float f) {
      if (list.isEmpty()) {
         return null;
      } else {
         switch (this.pitchSelection.getSelectedValue()) {
            case "Nearest":
               list.sort(Comparator.comparingDouble(obj -> this.getPitchDeltaScore((ScaffoldRaycastResult)obj)));
               return (ScaffoldRaycastResult)list.get(0);
            case "Furthest":
               list.sort(Comparator.comparingDouble(obj2 -> this.scoreFurthestPitchCandidate((ScaffoldRaycastResult)obj2)));
               return (ScaffoldRaycastResult)list.get(0);
            case "Highest":
               ScaffoldRaycastResult scaffoldRaycastResult = null;

               for (ScaffoldRaycastResult scaffoldRaycastResult2 : (Iterable<ScaffoldRaycastResult>)(list)) {
                  if (scaffoldRaycastResult2 != null && (scaffoldRaycastResult == null || scaffoldRaycastResult2.pitch > scaffoldRaycastResult.pitch)) {
                     scaffoldRaycastResult = scaffoldRaycastResult2;
                  }
               }

               return scaffoldRaycastResult;
            case "Lowest":
               ScaffoldRaycastResult scaffoldRaycastResult3 = null;

               for (ScaffoldRaycastResult scaffoldRaycastResult4 : (Iterable<ScaffoldRaycastResult>)(list)) {
                  if (scaffoldRaycastResult4 != null && (scaffoldRaycastResult3 == null || scaffoldRaycastResult4.pitch < scaffoldRaycastResult3.pitch)) {
                     scaffoldRaycastResult3 = scaffoldRaycastResult4;
                  }
               }

               return scaffoldRaycastResult3;
            case "Mid":
               list.sort(Comparator.comparingDouble(obj3 -> this.scoreMidPitchCandidate(f, (ScaffoldRaycastResult)obj3)));
               return (ScaffoldRaycastResult)list.get(0);
            case "Random":
               return (ScaffoldRaycastResult)list.get(DefaultRandomGenerator.instance.nextInt((byte)0, list.size() - 1));
            default:
               return null;
         }
      }
   }

   private float computeYawRandomOffset(boolean flag) {
      if (Math.random() * 100.0 > (flag ? this.randomizeYawChanceWhilePitchRotation.getValue() : this.getYawRandomizeChance())) {
         return 0.0F;
      } else {
         float f;
         if (this.otherYawRandomization.getValue()) {
            double d = (double)mc.thePlayer.ticksExisted * 0.75;
            double d2 = 0.2;
            double d3 = -0.2;
            f = (float)Math.sin(d + (double)DefaultRandomGenerator.instance.nextFloat(d3, d2)) * 0.75F;
         } else {
            f = DefaultRandomGenerator.instance.nextFloat(-this.randomizeYawVal.getLastValue(), this.randomizeYawVal.nextValue());
         }

         if (this.yawRandomizeCounter.hasReached(this.randomizeYawInterval.getLastIntValue())) {
            this.yawRandomizeCounter.reset();
            this.randomizeYawInterval.nextValue();
            this.randomizeYawVal.nextValue();
            this.yawRandomizationTriggered = false;
         }

         return f;
      }
   }

   public float computePitchRandomOffset(boolean flag) {
      if (Math.random() * 100.0 > (flag ? this.randomizePitchChanceWhileYawRotation.getValue() : this.getPitchRandomizeChance())) {
         return 0.0F;
      } else {
         float f;
         if (this.otherYawRandomization.getValue()) {
            double d = (double)mc.thePlayer.ticksExisted * 0.75;
            double d2 = 0.2;
            double d3 = -0.2;
            f = (float)Math.sin(d + (double)DefaultRandomGenerator.instance.nextFloat(d3, d2) + 1000.0) * 0.75F;
         } else {
            f = DefaultRandomGenerator.instance.nextFloat(-this.randomizePitchVal.getLastValue(), this.randomizePitchVal.nextValue());
         }

         if (this.pitchRandomizeCounter.hasReached(this.randomizePitchInterval.getLastIntValue())) {
            this.pitchRandomizeCounter.reset();
            this.randomizePitchInterval.nextValue();
            this.randomizePitchVal.nextValue();
            this.pitchRandomizationTriggered = false;
         }

         return f;
      }
   }

   private double getYawRandomizeChance() {
      BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ);
      return mc.theWorld.isAirBlock(pos) ? this.randomizeYawChanceOnAir.getValue() : this.randomizeYawChanceOnBlock.getValue();
   }

   private double getPitchRandomizeChance() {
      BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ);
      return mc.theWorld.isAirBlock(pos) ? this.randomizePitchChanceInAir.getValue() : this.randomizePitchChanceOnBlock.getValue();
   }

   private void applyScaffoldRotation(float f, float f2, MovingObjectPosition hit) {
      this.applyScaffoldRotation(f, f2, hit, false);
   }

   private void applyScaffoldRotation(float f, float f2, MovingObjectPosition hit, boolean flag) {
      this.applyScaffoldRotation(f, f2, hit, flag, false);
   }

   private void applyScaffoldRotation(float f, float f2, MovingObjectPosition hit, boolean flag, boolean flag2) {
      this.rotationUtil.updateLastRotationSpeeds(new Rotation(f, f2), ClientManagers.rotationController.getPreviousRotation());
      this.overrideRayTrace = hit;
      if (this.randomizeYaw.getValue() && this.randomizeYawChanceWhilePitchRotation.getValue() > 0.0 && f2 != ClientManagers.rotationController.getPreviousRotation().pitch && !flag && f == ClientManagers.rotationController.getPreviousRotation().yaw) {
         this.yawRandomizationTriggered = true;
         f = RotationUtil.applyYawGcd(this.computeYawRandomOffset(true) + f, ClientManagers.rotationController.getPreviousRotation().yaw);
         if (this.overrideRayTrace != null) {
            this.overrideRayTrace = RayCastUtil.rayCast(1.0F, f, f2);
         }
      }

      if ((f != ClientManagers.rotationController.getPreviousRotation().yaw || this.getPitchRandomizeChance() > 0.0) && this.randomizePitch.getValue() && f2 == ClientManagers.rotationController.getPreviousRotation().pitch) {
         this.pitchRandomizationTriggered = true;
         f2 = RotationUtil.applyPitchGcd(this.computePitchRandomOffset(f != ClientManagers.rotationController.getPreviousRotation().yaw) + f2, ClientManagers.rotationController.getPreviousRotation().pitch);
         if (f == ClientManagers.rotationController.getPreviousRotation().yaw == this.randomizeYaw.getValue() && this.randomizeYawChanceWhilePitchRotation.getValue() > 0.0 && f2 != ClientManagers.rotationController.getPreviousRotation().pitch) {
            this.yawRandomizationTriggered = true;
            f = RotationUtil.applyYawGcd(this.computeYawRandomOffset(true) + f, ClientManagers.rotationController.getPreviousRotation().yaw);
         }

         if (this.overrideRayTrace != null) {
            this.overrideRayTrace = RayCastUtil.rayCast(1.0F, f, f2);
         }
      }

      if (this.godBridge.getValue() && (f != ClientManagers.rotationController.getPreviousRotation().yaw || f2 != ClientManagers.rotationController.getPreviousRotation().pitch)) {
         this.resetGodBridgeState();
      }

      ClientManagers.rotationController.requestRotation(new float[]{f, f2}, (byte)-1, this, this.getRotationPriority());
   }

   private int getRotationPriority() {
      return 0;
   }

   private boolean shouldApplyStrafeYaw(float f) {
      return this.isStrafeActive() && RotationUtil.isNearCardinalYaw(f) && this.targetBlockPos != null;
   }

   private double getPitchDeltaFromNull(float[] floats) {
      return (double)Math.abs(floats[0] - ((Rotation)null).pitch);
   }

   private double getPitchDeltaScore(ScaffoldRaycastResult scaffoldRaycastResult) {
      return (double)Math.abs(scaffoldRaycastResult.pitch - ClientManagers.rotationController.getPreviousRotation().pitch);
   }

   private double getHitCenterOffset(ScaffoldRaycastResult scaffoldRaycastResult, float f) {
      MovingObjectPosition hit = RayCastUtil.rayCast(1.0F, f, scaffoldRaycastResult.pitch);
      if (hit != null && hit.getBlockPos() != null && hit.hitVec != null) {
         double d = hit.hitVec.yCoord % 1.0;
         return Math.abs(0.5 - d);
      } else {
         return 100.0;
      }
   }

   private boolean isAtBlockEdge() {
      return this.isAtBlockEdgeForYaw(ClientManagers.rotationController.getPreviousRotation().yaw);
   }

   private boolean isAtBlockEdgeForYaw(float f) {
      if (this.isToweringWhileMoving()) {
         return true;
      } else if (this.edgeRotateCheck.getValue() && this.targetBlockPos != null) {
         if (!this.isOutsideTargetBlockBounds()) {
            if (!this.isPredictedOutsideNextBlock(f)) {
               return false;
            }
         }

         return true;
      } else {
         return mc.theWorld
            .isAirBlock(MathHelper.floor_double(mc.thePlayer.posX), MathHelper.floor_double(mc.thePlayer.posY - 0.5), MathHelper.floor_double(mc.thePlayer.posZ));
      }
   }

   private boolean isToweringWhileMoving() {
      return !mc.thePlayer.onGround && this.towerWhileMoving.getValue() && MoveUtil.isMoving() && mc.thePlayer.posY - mc.thePlayer.lastGroundY > 1.0;
   }

   private void setMoonWalkLeftStrafe(EventMoveInput event) {
      if (event.getMoveForward() != 0.0F) {
         this.moonWalkStrafeDir = event.getMoveForward() > 0.0F ? 1 : -1;
      } else if (event.getMoveStrafe() != 0.0F) {
         this.moonWalkStrafeDir = event.getMoveStrafe() > 0.0F ? -1 : 1;
      }

      if (this.lastMoonWalkSide == 2) {
         this.moonWalkWideness.nextValue();
         if (this.moonWalkMiddleRecalcCounter.hasReached(this.delayToRecalculateMiddle.getLastIntValue())) {
            this.moonWalkMiddle.nextValue();
            this.delayToRecalculateMiddle.nextValue();
            this.moonWalkMiddleRecalcCounter.reset();
         }
      }

      this.lastMoonWalkSide = 1;
   }

   private void setMoonWalkRightStrafe(EventMoveInput event) {
      if (event.getMoveForward() != 0.0F) {
         this.moonWalkStrafeDir = event.getMoveForward() > 0.0F ? -1 : 1;
      } else if (event.getMoveStrafe() != 0.0F) {
         this.moonWalkStrafeDir = event.getMoveStrafe() > 0.0F ? 1 : -1;
      }

      if (this.lastMoonWalkSide == 1) {
         this.moonWalkWideness.nextValue();
         if (this.moonWalkMiddleRecalcCounter.hasReached(this.delayToRecalculateMiddle.getLastIntValue())) {
            this.moonWalkMiddle.nextValue();
            this.delayToRecalculateMiddle.nextValue();
            this.moonWalkMiddleRecalcCounter.reset();
         }
      }

      this.lastMoonWalkSide = 2;
   }

   private void setKeyBindCorrectionLeft(EventMoveInput event) {
      if (event.getMoveForward() != 0.0F) {
         this.keyBindStrafeCorrectionDir = event.getMoveForward() > 0.0F ? 1 : -1;
      } else if (event.getMoveStrafe() != 0.0F) {
         this.keyBindStrafeCorrectionDir = event.getMoveStrafe() > 0.0F ? -1 : 1;
      }
   }

   private void setKeyBindCorrectionRight(EventMoveInput event) {
      if (event.getMoveForward() != 0.0F) {
         this.keyBindStrafeCorrectionDir = event.getMoveForward() > 0.0F ? -1 : 1;
      } else if (event.getMoveStrafe() != 0.0F) {
         this.keyBindStrafeCorrectionDir = event.getMoveStrafe() > 0.0F ? 1 : -1;
      }
   }

   private void applyMoonWalkStrafe(EventMoveInput event) {
      int moonWalkStrafeDir2 = this.moonWalkStrafeDir;
      this.moonWalkDirChangeTicks++;
      if (this.lastAppliedStrafeInput != 0 && moonWalkStrafeDir2 != 0 && this.lastAppliedStrafeInput != moonWalkStrafeDir2) {
         this.moonWalkDirChangeTicks = 0;
         this.directionChangeDelay.nextValue();
         if (this.directionChangeDelay.getLastIntValue() > 0) {
            if (mc.theWorld.getBlockState(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ).getBlock().getMaterial() == Material.air && mc.thePlayer.onGround) {
               this.moonWalkDirChangeTicks = 100;
            }
         }
      }

      if (this.moonWalkDirChangeTicks >= this.directionChangeDelay.getLastIntValue()) {
         if (event.getMoveForward() != 0.0F) {
            event.setMoveStrafe((float)moonWalkStrafeDir2);
         } else if (event.getMoveStrafe() != 0.0F) {
            event.setMoveForward((float)moonWalkStrafeDir2);
         }
      }
   }

   public boolean isPredictedOutsideNextBlock(float f) {
      double[] doubles = PlayerUtil.predictPlayerPosition(0, mc.thePlayer, f);
      BlockPos pos = this.findNextRaytracedBlockPos();
      if (pos == null) {
         return false;
      } else {
         double d = (double)pos.getX() + 1.285;
         double d2 = (double)pos.getX() - 0.285;
         double d3 = (double)pos.getZ() + 1.285;
         double d4 = (double)pos.getZ() - 0.285;
         return doubles[0] > d || doubles[0] < d2 || doubles[2] > d3 || doubles[2] < d4;
      }
   }

   private BlockPos findNextRaytracedBlockPos() {
      ArrayList list = new ArrayList();

      for (float i = 15.0F; i < (float)(!this.intave.getValue() && !this.otherRotation.getValue() ? 90 : 85); i += 0.05F) {
         float[] floats = new float[]{ClientManagers.rotationController.getPreviousRotation().yaw, i};
         MovingObjectPosition hit = mc.thePlayer.rayTraceCustom(4.5, 2.0F, ClientManagers.rotationController.getPreviousRotation().yaw, floats[1]);
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
            && BlockUtil.isSolidBlock(hit.getBlockPos())
            && hit.sideHit != EnumFacing.DOWN
            && hit.sideHit != EnumFacing.UP
            && (hit.hitVec.yCoord % 1.0 < 0.65 && hit.hitVec.yCoord % 1.0 > 0.35 || !this.intave.getValue())) {
            list.add(floats[1]);
         }
      }

      float[] floats2 = new float[2];
      if (!list.isEmpty()) {
         list.sort(Comparator.comparingDouble(obj -> (double)Math.abs(((Number)obj).floatValue() - ClientManagers.rotationController.getPreviousRotation().pitch)));
         if (!list.isEmpty()) {
            floats2[1] = (Float)list.get(0);
            floats2[0] = ClientManagers.rotationController.getPreviousRotation().yaw;
         }

         MovingObjectPosition hit2 = mc.thePlayer.rayTraceCustom(4.5, 2.0F, floats2[0], floats2[1]);
         EnumFacing sideHit = hit2.sideHit;
         BlockPos blockPos = hit2.getBlockPos();
         if (sideHit == EnumFacing.EAST) {
            return blockPos.add(1, 0, 0);
         }

         if (sideHit == EnumFacing.WEST) {
            return blockPos.add(-1, 0, 0);
         }

         if (sideHit == EnumFacing.NORTH) {
            return blockPos.add(0, 0, -1);
         }

         if (sideHit == EnumFacing.SOUTH) {
            return blockPos.add(0, 0, 1);
         }
      }

      return null;
   }

   private boolean isTellyBridgeEnabled() {
      return this.autoJump.getValue() && this.sameY.getValue() && this.tellyBridge.getValue();
   }

   private boolean shouldRotateBackwardInTelly() {
      return !(!mc.thePlayer.onGround && mc.thePlayer.airTicks < 1
         || mc.thePlayer.onGround && PlayerUtil.predictPlayerPosition(0, mc.thePlayer)[9] == 1.0 && MoveUtil.hasHorizontalMotion());
   }

   private int countVoxelStepsToTarget(BlockPlacementData blockPlacementData2, double d, double d2) {
      if (blockPlacementData2 != null && blockPlacementData2.getBlockPos() != null) {
         BlockPos blockPos = blockPlacementData2.getBlockPos();
         Vec3 hitVec = blockPlacementData2.getHitVec();
         double d3 = (double)blockPos.getY() + 0.5;
         double d4 = d - ((double)blockPos.getX() + hitVec.xCoord);
         double d5 = 0.0;
         double d6 = d2 - ((double)blockPos.getZ() + hitVec.zCoord);
         float f = (float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0);
         float f2 = (float)(-(MathHelper.atan2(0.0, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI));
         float f3 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f, 180.0F);
         float f4 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, f2, 180.0F);
         Vec3 vectorForRotation = mc.thePlayer.getVectorForRotation(f4, f3);
         double xCoord = vectorForRotation.xCoord;
         double yCoord = vectorForRotation.yCoord;
         double zCoord = vectorForRotation.zCoord;
         int n = MathHelper.floor_double(d);
         int n2 = MathHelper.floor_double(d3);
         int n3 = MathHelper.floor_double(d2);
         int x2 = blockPos.getX();
         int y2 = blockPos.getY();
         int z = blockPos.getZ();
         int n4 = Integer.signum((int)Math.round(xCoord * 1000.0));
         int n5 = Integer.signum((int)Math.round(yCoord * 1000.0));
         int n6 = Integer.signum((int)Math.round(zCoord * 1000.0));
         double d7 = n4 == 0 ? Double.POSITIVE_INFINITY : Math.abs(1.0 / xCoord);
         double d8 = n5 == 0 ? Double.POSITIVE_INFINITY : Math.abs(1.0 / yCoord);
         double d9 = n6 == 0 ? Double.POSITIVE_INFINITY : Math.abs(1.0 / zCoord);
         double d10 = d - (double)n;
         double d11 = d3 - (double)n2;
         double d12 = d2 - (double)n3;
         double d13 = n4 > 0 ? (1.0 - d10) * d7 : d10 * d7;
         double d14 = n5 > 0 ? (1.0 - d11) * d8 : d11 * d8;
         double d15 = n6 > 0 ? (1.0 - d12) * d9 : d12 * d9;
         if (n4 == 0) {
            d13 = Double.POSITIVE_INFINITY;
         }

         if (n5 == 0) {
            d14 = Double.POSITIVE_INFINITY;
         }

         if (n6 == 0) {
            d15 = Double.POSITIVE_INFINITY;
         }

         int n7 = 0;
         double d16 = 4.5;

         for (double i = 0.0; i <= d16 && (n != x2 || n2 != y2 || n3 != z); n7++) {
            if (d13 < d14 && d13 < d15) {
               i = d13;
               if (d13 > d16) {
                  break;
               }

               n += n4;
               d13 += d7;
            } else if (d14 < d15) {
               i = d14;
               if (d14 > d16) {
                  break;
               }

               n2 += n5;
               d14 += d8;
            } else {
               i = d15;
               if (d15 > d16) {
                  break;
               }

               n3 += n6;
               d15 += d9;
            }
         }

         return n7;
      } else {
         return 0;
      }
   }

   public BlockPlacementData findBlockPlacementData() {
      if (Math.abs(mc.thePlayer.motionZ) < 1.0E-6 && Math.abs(mc.thePlayer.motionX) < 1.0E-6) {
         for (int i = 1; i < 4; i++) {
            BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - (double)i, mc.thePlayer.posZ);
            if (BlockUtil.isSolidBlock(pos)) {
               return new BlockPlacementData(pos, EnumFacing.UP, new Vec3((double)pos.getX() + 0.5, (double)(pos.getY() + 1), (double)pos.getZ() + 0.5));
            }
         }
      }

      boolean lookForward;
      if (this.isTellyBridgeEnabled()) {
         lookForward = !this.shouldRotateBackwardInTelly();
      } else if (!this.flickMode.getSelectedValue().equals("None")) {
         lookForward = this.targetBlockPos == null || !this.isAtBlockEdge();
      } else {
         lookForward = false;
      }
      float f = lookForward ? -180.0F : 0.0F;

      float f2 = f;
      Vec3 vectorForRotation = mc.thePlayer.getVectorForRotation(Augustus.getInstance().getYawPitchHelper().realPitch, Augustus.getInstance().getYawPitchHelper().realYaw - 180.0F + f2);
      double d = (mc.thePlayer.lastTickPosX - mc.thePlayer.posX == 0.0 ? vectorForRotation.xCoord : mc.thePlayer.lastTickPosX - mc.thePlayer.posX) * 2.0;
      double d2 = (mc.thePlayer.lastTickPosZ - mc.thePlayer.posZ == 0.0 ? vectorForRotation.zCoord : mc.thePlayer.lastTickPosZ - mc.thePlayer.posZ) * 2.0;
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      float f3 = this.sameY.getValue() && !this.rightClickHeldOnGround ? (float)(mc.thePlayer.posY - mc.thePlayer.lastGroundY) : 0.0F;
      float f4 = this.sameY.getValue() && !this.rightClickHeldOnGround ? 4.5F : 1.0F;
      double[] doubles = new double[]{1.5, 0.5, 2.5, 3.5};

      for (double d3 : doubles) {
         double d4 = mc.thePlayer.posX + d;
         double d5 = mc.thePlayer.posY - (d3 + (double)f3);
         double d6 = mc.thePlayer.posZ + d2;
         double d7 = d4 - mc.thePlayer.posX;
         double d8 = 0.0;
         double d9 = d6 - mc.thePlayer.posZ;
         float f5 = (float)(MathHelper.atan2(d9, d7) * 180.0 / Math.PI - 90.0);
         float f6 = (float)(-(MathHelper.atan2(d8, (double)MathHelper.sqrt_double(d7 * d7 + d9 * d9)) * 180.0 / Math.PI));
         float f7 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f5, 180.0F);
         float f8 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, f6, 180.0F);

         for (float j = -45.0F; j <= 45.0F; j++) {
            Vec3 vectorForRotation2 = mc.thePlayer.getVectorForRotation(f8, f7 + j);
            MovingObjectPosition hit = mc.theWorld
               .rayTraceBlocks(
                  d4,
                  d5,
                  d6,
                  d4 + vectorForRotation2.xCoord * (double)f4,
                  d5 + vectorForRotation2.yCoord * (double)f4,
                  d6 + vectorForRotation2.zCoord * (double)f4,
                  false,
                  false,
                  true
               );
            if (hit != null
               && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
               && hit.getBlockPos() != null
               && BlockUtil.isSolidBlock(hit.getBlockPos())
               && !list2.contains(hit.getBlockPos())
               && (!BlockUtil.isWalkableBlock(hit.getBlockPos().up()) || this.sameYBlockJumpActive)) {
               BlockPos blockPos = hit.getBlockPos();
               BlockPos blockPos2 = hit.getBlockPos();
               EnumFacing sideHit = hit.sideHit;
               Vec3 hitVec = hit.hitVec;
               double d10 = mc.thePlayer.getDistance((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5)
                  + (double)(d3 == 1.5 && this.sameYBlock.getValue() ? -10 : 0)
                  + (double)j / 20.0
                  - (hit.getBlockPos().equals(this.lastPlacedBlockPos) ? 1.5 : 0.0);
               list.add(new BlockPlacementData(blockPos2, sideHit, hitVec, d10));
               list2.add(hit.getBlockPos());
            }
         }
      }

      list.sort(Comparator.comparingDouble(BlockPlacementData::getScore));
      if (!list.isEmpty()) {
         return (BlockPlacementData)list.get(0);
      } else {
         for (double d11 : doubles) {
            double d12 = mc.thePlayer.posX + d;
            double d13 = mc.thePlayer.posY - (d11 + (double)f3);
            double d14 = mc.thePlayer.posZ + d2;
            double d15 = d12 - mc.thePlayer.posX;
            double d16 = 0.0;
            double d17 = d14 - mc.thePlayer.posZ;
            float f9 = (float)(MathHelper.atan2(d17, d15) * 180.0 / Math.PI - 90.0);
            float f10 = (float)(-(MathHelper.atan2(d16, (double)MathHelper.sqrt_double(d15 * d15 + d17 * d17)) * 180.0 / Math.PI));
            float f11 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f9, 180.0F);
            float f12 = RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().pitch, f10, 180.0F);

            for (float k = 0.0F; k <= 360.0F; k++) {
               Vec3 vectorForRotation3 = mc.thePlayer.getVectorForRotation(f12, f11 + k);
               MovingObjectPosition hit2 = mc.theWorld
                  .rayTraceBlocks(d12, d13, d14, d12 + vectorForRotation3.xCoord * 4.5, d13 + vectorForRotation3.yCoord * 4.5, d14 + vectorForRotation3.zCoord * 4.5, false, false, true);
               if (hit2 != null
                  && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                  && hit2.getBlockPos() != null
                  && BlockUtil.isSolidBlock(hit2.getBlockPos())
                  && !list2.contains(hit2.getBlockPos())
                  && (!BlockUtil.isWalkableBlock(hit2.getBlockPos().up()) || this.sameYBlockJumpActive)) {
                  BlockPos blockPos3 = hit2.getBlockPos();
                  BlockPos blockPos4 = hit2.getBlockPos();
                  EnumFacing sideHit2 = hit2.sideHit;
                  Vec3 hitVec2 = hit2.hitVec;
                  double d18 = mc.thePlayer.getDistance((double)blockPos3.getX() + 0.5, (double)blockPos3.getY() + 0.5, (double)blockPos3.getZ() + 0.5)
                     + (double)(d11 == 1.5 && this.sameYBlock.getValue() ? -10 : 0);
                  list.add(new BlockPlacementData(blockPos4, sideHit2, hitVec2, d18));
                  list2.add(hit2.getBlockPos());
               }
            }
         }

         list.sort(Comparator.comparingDouble(BlockPlacementData::getScore));
         if (!list.isEmpty()) {
            return (BlockPlacementData)list.get(0);
         } else {
            for (int m = 1; m < 4; m++) {
               BlockPos pos4 = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - (double)m, mc.thePlayer.posZ);
               if (BlockUtil.isSolidBlock(pos4)) {
                  return new BlockPlacementData(pos4, EnumFacing.UP, new Vec3((double)pos4.getX() + 0.5, (double)(pos4.getY() + 1), (double)pos4.getZ() + 0.5));
               }
            }

            System.out.println("Null");
            return null;
         }
      }
   }

   private boolean canRunScaffoldInCurrentScreen() {
      boolean flag = mc.isNotInGui() || mc.currentScreen instanceof ClickGuiScreen;
      return !ClientManagers.moduleManager.guiMove.isToggled() ? flag : ClientManagers.moduleManager.guiMove.canMoveInGui() || flag;
   }

   private boolean isStationaryTowering() {
      return mc.gameSettings.keyBindJump.isKeyDown() && Math.abs(mc.thePlayer.motionZ) < 0.01 && Math.abs(mc.thePlayer.motionX) < 0.01;
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.esp.getValue()) {
         RenderUtil.begin3DRender();
         int colorInt = this.espColor.getColorInt((byte)0);
         float f2 = 0.0F;
         if (this.targetBlockPos != null) {
            GlStateManager.alphaFunc(516, 0.0F);
            BlurShadowManager.enableOneShotPass(this.esp2.isBlurActive(), this.esp2.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> {
               RenderUtil.drawBlockEspBox(this.targetBlockPos, this.esp2.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, colorInt)), 1.0F, f2);
            });
         }

         RenderUtil.end3DRender();
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.blockCount.getValue()) {
         this.renderBlockCountHud();
      }

      RenderUtil.resetGlColorState();
   }

   private void renderBlockCountHud() {
      if (this.hotbarBlockCount != 0 && this.getSelectedBlockSlot() >= 0) {
         ScaledResolution sr = new ScaledResolution(mc);
         GL11.glPushMatrix();
         GL11.glScaled(1.0 / (double)sr.getScaleFactor(), 1.0 / (double)sr.getScaleFactor(), 1.0);
         GL11.glScaled(this.scale.getValue(), this.scale.getValue(), 1.0);
         double d = (double)mc.displayWidth / this.scale.getValue();
         double d2 = (double)mc.displayHeight / this.scale.getValue();
         int n = MathHelper.ceiling_double_int(d);
         int n2 = MathHelper.ceiling_double_int(d2);
         String str = this.hotbarBlockCount + "";
         int n3 = (20 + mc.fontRendererObj.getStringWidth(str)) / 2;
         int n4 = n3 + 2 + (int)((double)(n - (n3 + 2) * 2) * (this.x.getValue() / 100.0));
         int n5 = 2 + (int)((double)(n2 - 19) * (this.y.getValue() / 100.0));
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (this.backGround.getValue()) {
            int colorInt = this.backgroundColor.getColorInt((byte)0);
            BlurShadowManager.enableOneShotPass(this.blockCount2.isBlurActive(), this.blockCount2.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderBlockCountBackground(n4, n3, n5, colorInt, shaderRenderPass));
         }

         RenderHelper.enableGUIStandardItemLighting();
         RenderUtil.renderItemIntoGuiElevated(mc.thePlayer.inventoryContainer.getSlot(this.getSelectedBlockSlot() + 36).getStack(), n4 - n3, n5, false);
         RenderHelper.disableStandardItemLighting();
         mc.fontRendererObj.drawString(str, n4 - n3 + 20, n5 + 4, -1);
         GL11.glPopMatrix();
      }
   }

   private int countHotbarBlocks() {
      int n = 0;

      for (int i = 0; i < 9; i++) {
         ItemStack stackInSlot = mc.thePlayer.inventory.getStackInSlot(i);
         if (stackInSlot != null && stackInSlot.getItem() instanceof ItemBlock && BlockUtil.isValidScaffoldBlock(stackInSlot)) {
            n += stackInSlot.stackSize;
         }
      }

      return n;
   }

   private int getSelectedBlockSlot() {
      return this.selectedBlockSlot;
   }

   private void updateSelectedBlockSlot() {
      int n = -1;
      int n2 = -1;

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null
            && stack.getItem() instanceof ItemBlock
            && BlockUtil.isValidScaffoldBlock(stack)
            && (this.slotMinStackThresholds.containsKey(i - 36) ? stack.stackSize > (Integer)this.slotMinStackThresholds.get(i - 36) : stack.stackSize > this.stackCountToStartSwitchProcess.getLastIntValue())) {
            if (!this.useBiggestStackSilent.getValue()) {
               n2 = i - 36;
               break;
            }

            if (stack.stackSize > n) {
               n = stack.stackSize;
               n2 = i - 36;
            }
         }
      }

      if (this.silentMode.getSelectedValue().equals("Exploit")) {
         this.selectedBlockSlot = n2;
         this.firstSilentSwitchPending = false;
      } else if (this.selectedBlockSlot == n2) {
            this.silentSwitchCounter.reset();
         } else if (n2 < 0) {
            this.selectedBlockSlot = n2;
         } else if (this.silentSwitchCounter.hasReached((this.firstSilentSwitchPending ? this.silentStartDelay.getLastIntValue() : this.silentSwitchDelay.getLastIntValue()) + 1)) {
               this.slotMinStackThresholds.put(n2, this.stackCountToStartSwitchProcess.getLastIntValue());
               this.selectedBlockSlot = n2;
               this.firstSilentSwitchPending = false;
               this.silentSwitchDelay.nextValue();
               this.stackCountToStartSwitchProcess.nextValue();
            }
   }

   private int findNonBlockHotbarSlot() {

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack == null || !(stack.getItem() instanceof ItemBlock) || !BlockUtil.isValidScaffoldBlock(stack)) {
            return i - 36;
         }
      }

      return -1;
   }

   @Override
   public void onPostSilentSlot() {
      this.silentSwitchCounter.increment();
      if (!this.silentMode.getSelectedValue().equals("None")) {
         this.updateSelectedBlockSlot();
         int n;
         if (this.silentMode.getSelectedValue().equals("Exploit")) {
            n = this.findNonBlockHotbarSlot();
         } else {
            n = this.getSelectedBlockSlot();
         }

         int n2 = n;
         if (n2 >= 0 && n2 < 9) {
            int n3 = 1;
            if (this.silentMode.getSelectedValue().equals("Default")) {
               n3 = this.backSwitchTickDelay.getLastIntValue() + 1;
               if (this.dynamicBackSwitchTickDelay.getValue() && CombatTargetUtil.getActiveTargetOrDefault((EntityLivingBase)null) != null) {
                  n3 = Math.max(1, n3 / 2);
               }
            }

            SilentSlotManager.scheduleSlotSwitch(this, (byte)-1, n2, n3, !this.silentMode.getSelectedValue().equals("Legit"));
         }
      } else {
         this.selectedBlockSlot = 0;
      }
   }

   @Override
   public void onAIMoveSpeed(EventAIMoveSpeed event) {
      if (!this.waitingForStartEdge) {
         if (this.ignoreSpeedMotion.getValue()) {
            event.setMoveSpeed(MathHelper.clamp_float(event.getMoveSpeed(), -0.13000001F, 0.13000001F));
         }
      }
   }

   @Override
   public void onPostSafeWalk(EventSafeWalk event) {
      this.safeWalkEdgeDetected = event.isClippedAtEdge();
   }

   private boolean isIntaveFastSneakActive() {
      float f = MathHelper.wrapAngleTo180_float(ClientManagers.rotationController.getPreviousRotation().yaw);
      return this.intave.getValue()
         && this.intaveFast.getValue()
         && MoveUtil.isMoving()
         && !this.isStationaryTowering()
         && (f > -2.0F && f < 2.0F || f > 88.0F && f < 92.0F || f < -178.0F || f > 178.0F || f < -88.0F && f > -92.0F)
         && mc.thePlayer.onGround
         && !mc.gameSettings.keyBindJump.isKeyDown();
   }

   private boolean wouldFallNextTick() {
      if (mc.thePlayer.onGround && this.forcePlaceIfYouWouldFallDown.getValue()) {
         return PlayerUtil.predictPlayerPosition(0, mc.thePlayer)[9] != 1.0;
      } else {
         return false;
      }
   }

   public boolean isOutsideTargetBlockBounds() {
      if (this.targetBlockPos == null) {
         return false;
      } else {
         double d = 1.282;
         double d2 = 0.282;
         double posX = mc.thePlayer.posX;
         double posZ = mc.thePlayer.posZ;
         double d3 = (double)this.targetBlockPos.getX() + d;
         double d4 = (double)this.targetBlockPos.getX() - d2;
         double d5 = (double)this.targetBlockPos.getZ() + d;
         double d6 = (double)this.targetBlockPos.getZ() - d2;
         return posX > d3 || posX < d4 || posZ > d5 || posZ < d6;
      }
   }

   private boolean isStrafeActive() {
      return this.strafe.getValue() && (mc.gameSettings.keyBindJump.isKeyDown() || !this.strafeOnlyWhileJump.getValue());
   }

   private boolean isBezierRotationModeActive() {
      return this.isBlockClutching() && this.customBlockClutchRotationSpeeds.getValue() ? this.blockClutchModeNew.getValue() : this.modeNew.getValue();
   }

   private double getActiveAimSpeed() {
      return this.isBlockClutching() && this.customBlockClutchRotationSpeeds.getValue() ? this.blockClutchAimSpeed.getValue() : this.aimSpeed.getValue();
   }

   private float rollActiveYawSpeed() {
      return (float)(this.isBlockClutching() && this.customBlockClutchRotationSpeeds.getValue() ? this.blockClutchYawSpeed.nextValue() : this.yawSpeed.nextValue());
   }

   private float rollActivePitchSpeed() {
      return (float)(this.isBlockClutching() && this.customBlockClutchRotationSpeeds.getValue() ? this.blockClutchPitchSpeed.nextValue() : this.pitchSpeed.nextValue());
   }

   private RandomizedRangeSettings getActiveYawAcceleration() {
      return this.isBlockClutching() && this.customBlockClutchRotationSpeeds.getValue() ? this.blockClutchYawAcceleration : this.yawAcceleration;
   }

   private RandomizedRangeSettings getActivePitchAcceleration() {
      return this.isBlockClutching() && this.customBlockClutchRotationSpeeds.getValue() ? this.blockClutchPitchAcceleration : this.pitchAcceleration;
   }

   private boolean isExpandEnabled() {
      return this.expand.getValue();
   }

   private boolean isEarlySneakCalculationActive() {
      return mc.thePlayer != null && this.earlySneakCalculation.getValue() && (mc.thePlayer.isPotionActive(Potion.moveSpeed) || !this.earlySneakCalculationOnlyAtSpeedEffect.getValue());
   }

   private void renderBlockCountBackground(int n, int n2, int n3, int n4, ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawRoundedRect((float)(n - n2 - 2), (float)(n3 - 2), (float)(n + n2 + 2), (float)(n3 + 15 + 2), (float)this.roundFactor.getValue(), 1, this.blockCount2.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n4)));
   }

   private double scoreMidPitchCandidate(float f, ScaffoldRaycastResult scaffoldRaycastResult) {
      return this.getHitCenterOffset(scaffoldRaycastResult, f);
   }

   private double scoreFurthestPitchCandidate(ScaffoldRaycastResult scaffoldRaycastResult) {
      return 180.0 - this.getPitchDeltaScore(scaffoldRaycastResult);
   }

   private boolean placeSideBlock(boolean flag) {
      boolean flag2 = true;
      if (this.intaveBlockPlacePenalty == 0.0 && flag) {
         int n = -1;
         if (this.silentMode.getSelectedValue().equals("Exploit")) {
            int n2 = this.getSelectedBlockSlot() + 36;
            if (!PlayerUtil.canPlaceHeldItemAtMouseOver(mc.thePlayer.inventoryContainer.getSlot(n2).getStack())) {
               return false;
            }

            n = this.switchToExploitSlot();
         }

         ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();
         int n3 = currentItem != null ? currentItem.stackSize : 0;
         if (mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, currentItem, mc.objectMouseOver.getBlockPos(), mc.objectMouseOver.sideHit, mc.objectMouseOver.hitVec)) {
            this.hasPlacedFirstBlock = true;
            this.placedBlocksSinceSneak++;
            this.godBridgePlacedBlocks++;
            this.blocksPlacedSinceJump++;
            this.ticksSinceGodBridgePlace = 0;
            this.lastRightClickTimer.reset();
            this.lastPlaceTimer.reset();
            flag2 = false;
            this.moonWalkEdgeExpandDist = -DefaultRandomGenerator.instance.nextDouble(this.moonWalkStartDistToBlock.getMinValue(), this.moonWalkStartDistToBlock.getMaxValue());
            this.ticksSinceLastPlace = 0;
            this.postPlacementStrafeTicks.nextValue();
            this.placedBlockSinceEnable = true;
            this.lastPlacedBlockPos = mc.objectMouseOver.getBlockPos().offset(mc.objectMouseOver.sideHit);
            this.swingHandIfAllowed();
         }

         if (currentItem == null) {
            this.restoreExploitSlot(n);
            return false;
         } else {
            if (currentItem.stackSize == 0) {
               mc.thePlayer.inventory.mainInventory[mc.thePlayer.inventory.currentItem] = null;
            } else if ((currentItem.stackSize != n3 || mc.playerController.isInCreativeMode()) && !this.noSwing.getValue()) {
               mc.entityRenderer.itemRenderer.resetEquippedProgress();
            }

            if (flag2) {
               this.lastRightClickTimer.reset();
               if (currentItem != null && mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, currentItem)) {
                  mc.entityRenderer.itemRenderer.resetEquippedProgress2();
               }
            }

            this.restoreExploitSlot(n);
            return !flag2;
         }
      } else {
         return false;
      }
   }

   private boolean placeTopBlock(boolean flag) {
      boolean flag2 = true;
      if (this.intaveBlockPlacePenalty == 0.0 && flag) {
         int n = -1;
         if (this.silentMode.getSelectedValue().equals("Exploit")) {
            int n2 = this.getSelectedBlockSlot() + 36;
            if (!PlayerUtil.canPlaceHeldItemAtMouseOver(mc.thePlayer.inventoryContainer.getSlot(n2).getStack())) {
               return false;
            }

            n = this.switchToExploitSlot();
         }

         ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();
         int n3 = currentItem != null ? currentItem.stackSize : 0;
         if (mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, currentItem, mc.objectMouseOver.getBlockPos(), mc.objectMouseOver.sideHit, mc.objectMouseOver.hitVec)) {
            if (this.sameYBlockJumpActive) {
               this.sameYBlockTopPlaced = true;
            }

            this.hasPlacedFirstBlock = true;
            this.placedBlocksSinceSneak++;
            this.blocksPlacedSinceJump++;
            this.lastRightClickTimer.reset();
            this.lastPlaceTimer.reset();
            flag2 = false;
            this.moonWalkEdgeExpandDist = -DefaultRandomGenerator.instance.nextDouble(this.moonWalkStartDistToBlock.getMinValue(), this.moonWalkStartDistToBlock.getMaxValue());
            this.ticksSinceLastPlace = 0;
            this.postPlacementStrafeTicks.nextValue();
            this.placedBlockSinceEnable = true;
            this.lastPlacedBlockPos = mc.objectMouseOver.getBlockPos().offset(mc.objectMouseOver.sideHit);
            this.swingHandIfAllowed();
         }

         if (currentItem == null) {
            this.restoreExploitSlot(n);
            return false;
         } else {
            if (currentItem.stackSize == 0) {
               mc.thePlayer.inventory.mainInventory[mc.thePlayer.inventory.currentItem] = null;
            } else if ((currentItem.stackSize != n3 || mc.playerController.isInCreativeMode()) && !this.noSwing.getValue()) {
               mc.entityRenderer.itemRenderer.resetEquippedProgress();
            }

            if (flag2) {
               this.lastRightClickTimer.reset();
               if (currentItem != null && mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, currentItem)) {
                  mc.entityRenderer.itemRenderer.resetEquippedProgress2();
               }
            }

            this.restoreExploitSlot(n);
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean isBlockCountShadowVisible() {
      return this.blockCount.getValue() && this.backGround.getValue();
   }

   private boolean isRoundFactorVisible() {
      return this.blockCount.getValue() && this.backGround.getValue();
   }

   private boolean isDynamicBackSwitchTickDelayVisible() {
      return this.silentMode.getSelectedValue().equals("Default") && this.backSwitchTickDelay.getMinValue() > 0.0;
   }

   private boolean isBackSwitchTickDelayVisible() {
      return this.silentMode.getSelectedValue().equals("Default");
   }

   private boolean isStackCountToStartSwitchVisible() {
      return this.silentMode.getSelectedValue().equals("Default") || this.silentMode.getSelectedValue().equals("Legit");
   }

   private boolean isSilentSwitchDelayVisible() {
      return this.silentMode.getSelectedValue().equals("Default") || this.silentMode.getSelectedValue().equals("Legit");
   }

   private boolean isSilentStartDelayVisible() {
      return this.silentMode.getSelectedValue().equals("Default") || this.silentMode.getSelectedValue().equals("Legit");
   }

   private boolean isUseBiggestStackSilentVisible() {
      return !this.silentMode.getSelectedValue().equals("None");
   }

   private boolean isConstantMotionVisible() {
      return this.towerMode.getSelectedValue().equals("ConstantMotion");
   }

   private boolean isTowerWhileMovingVisible() {
      return !this.towerMode.getSelectedValue().equals("Legit");
   }

   private boolean isTimerSpeedVisible() {
      return this.timerDrop.isVisible() && this.timerDrop.getValue();
   }

   private boolean isTellyBridgeVisible() {
      return this.sameY.getValue() && this.autoJump.getValue();
   }

   private boolean isIntaveFast2Visible() {
      return this.sameY.getValue() && this.intave.getValue();
   }

   private boolean isDirectionChangeDelayVisible() {
      return this.moonWalk.getValue() && this.moonWalk.isVisible();
   }

   private boolean isPostPlacementStrafeTicksVisible() {
      return this.moonWalk.getValue() && this.moonWalk.isVisible();
   }

   private boolean isDelayToRecalculateMiddleVisible() {
      return this.moonWalk.getValue() && this.moonWalk.isVisible();
   }

   private boolean isMoonWalkMiddleVisible() {
      return this.moonWalk.getValue() && this.moonWalk.isVisible();
   }

   private boolean isMoveFixIntaveVisible() {
      return !this.moveFix.getSelectedValue().equalsIgnoreCase("None");
   }

   private boolean isEarlySneakSpeedOnlyVisible() {
      return this.sneak.getValue() && this.earlySneakCalculation.getValue();
   }

   private boolean isMaxOffGroundSneakDiagonalVisible() {
      return this.sneak.getValue() && this.oneStackDiagonal.getValue();
   }

   private boolean isMinOffGroundSneakDiagonalVisible() {
      return this.sneak.getValue() && this.oneStackDiagonal.getValue();
   }

   private boolean isMaxOffGroundSneakForwardVisible() {
      return this.sneak.getValue() && this.oneStackForward.getValue();
   }

   private boolean isMinOffGroundSneakForwardVisible() {
      return this.sneak.getValue() && this.oneStackForward.getValue();
   }

   private boolean isMaxPitchCorrectionClutchVisible() {
      return !this.isExpandEnabled();
   }

   private boolean isMaxPitchCorrectionVisible() {
      return !this.isExpandEnabled();
   }

   private boolean isFlickForwardStrafeVisible() {
      return this.flickMode.getSelectedValue().equals("Simple");
   }

   private boolean isIgnoreStartAtEdgeClutchVisible() {
      if (this.startAtEdge.getValue()) {
         if (this.startAtEdgeDelay.getMaxValue() > 0.0) {
            return true;
         }
      }

      return false;
   }

   private boolean isStrafeKeyBindRecalcDelayVisible() {
      return this.correctStrafeWithKeyBinds.getValue() && this.correctStrafeWithKeyBinds.isVisible();
   }

   private boolean isStrafeKeyBindMiddleExclusionVisible() {
      return this.correctStrafeWithKeyBinds.getValue() && this.correctStrafeWithKeyBinds.isVisible();
   }

   private boolean isStrafeKeyBindMiddleVisible() {
      return this.correctStrafeWithKeyBinds.getValue() && this.correctStrafeWithKeyBinds.isVisible();
   }

   private boolean isStrafeKeyBindWidthVisible() {
      return this.correctStrafeWithKeyBinds.getValue() && this.correctStrafeWithKeyBinds.isVisible();
   }

   private boolean isStrafeKeyBindCageVisible() {
      return this.correctStrafeWithKeyBinds.getValue() && this.correctStrafeWithKeyBinds.isVisible();
   }

   private boolean isStrafeWidthVisible() {
      return this.strafe.getValue() && this.strafeDegree.getMinValue() <= 25.0;
   }

   private boolean isStrafeOnlyWhileJumpVisible() {
      return this.strafe.getValue();
   }

   private boolean isImproveOnlyAtClutchVisible() {
      return this.improve.getValue();
   }

   private boolean isIgnoreDirChangeDelayClutchVisible() {
      if (this.strictYaw.getValue()) {
         if (this.startDelayAfterDirectionChange.getMaxValue() > 0.0) {
            return true;
         }
      }

      return false;
   }

   private boolean isRandomizeYawIntervalVisible() {
      return !this.otherYawRandomization.getValue() && this.randomizeYaw.getValue() && this.randomizeYaw.isVisible();
   }

   private boolean isRandomizeYawValVisible() {
      return !this.otherYawRandomization.getValue() && this.randomizeYaw.getValue() && this.randomizeYaw.isVisible();
   }

   private boolean isOtherYawRandomizationVisible() {
      return this.randomizeYaw.getValue() && this.randomizeYaw.isVisible();
   }

   private boolean isRandomizeYawChanceOnBlockVisible() {
      return this.randomizeYaw.getValue() && this.randomizeYaw.isVisible();
   }

   private boolean isRandomizeYawChanceWhilePitchVisible() {
      return this.randomizeYaw.getValue() && this.randomizeYaw.isVisible();
   }

   private boolean isRandomizePitchIntervalVisible() {
      return !this.otherPitchRandomization.getValue() && this.randomizePitch.getValue() && this.randomizePitch.isVisible();
   }

   private boolean isRandomizePitchChanceInAirVisible() {
      return this.randomizePitch.getValue() && this.randomizePitch.isVisible();
   }

   private boolean isRandomizePitchChanceOnBlockVisible() {
      return this.randomizePitch.getValue() && this.randomizePitch.isVisible();
   }

   private boolean isRandomizePitchChanceWhileYawVisible() {
      return this.randomizePitch.getValue() && this.randomizePitch.isVisible();
   }

   private boolean isBlockClutchPitchSpeedVisible() {
      return !this.blockClutchModeNew.isVisible() || !this.blockClutchModeNew.getValue();
   }

   private boolean isBlockClutchAimSpeedVisible() {
      return this.blockClutchModeNew.getValue() && this.blockClutchModeNew.isVisible();
   }

   private boolean isBlockClutchModeNewVisible() {
      return !this.expand.getValue();
   }

   private boolean isPitchAccelerationVisible() {
      return !this.modeNew.isVisible() || !this.modeNew.getValue();
   }

   private boolean isPitchSpeedVisible() {
      return !this.modeNew.isVisible() || !this.modeNew.getValue();
   }

   private boolean isAimSpeedVisible() {
      return this.modeNew.getValue() && this.modeNew.isVisible();
   }

   private boolean isModeNewVisible() {
      return !this.expand.getValue();
   }


}
