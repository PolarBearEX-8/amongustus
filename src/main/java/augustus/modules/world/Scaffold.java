package augustus.modules.world;

import augustus.anticheat.checks.Intave14TimeBlockPlaceCheck;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventMoveInput;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSneak;
import client.event.EventSprint;
import client.event.EventStateUpdate;
import client.event.EventStep;
import client.event.EventStrafe;
import client.event.MotionListener;
import client.event.MotionUpdateListener;
import client.event.MoveEntityListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.RenderTickListener;
import client.event.RotationListener;
import client.event.SneakListener;
import client.event.SprintListener;
import client.event.StepListener;
import client.event.StrafeListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.gui.ClickGuiScreen;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.setting.ShaderOptions;
import client.util.AnimationTimer;
import client.util.BooleanCondition;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatRayTrace;
import client.util.CombatUtil;
import client.util.EagleSneakHelper;
import client.util.HitBoxUtils;
import client.util.MinecraftTimer;
import client.util.MotionVectorSnapshot;
import client.util.MouseUtils;
import client.util.MoveUtil;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.Pair;
import client.util.PlaceTarget;
import client.util.PlayerUtil;
import client.util.RandomUtil;
import client.util.RandomizationGenerator;
import client.util.RayTraceHit;
import client.util.RunnableTask;
import client.util.SimulatedPlayer;
import client.util.SimulationBoundingBox;
import client.util.SlotManager;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCache;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Scaffold
extends Module
implements RotationListener,
MoveInputListener,
ClickListener,
TickListener,
StrafeListener,
MoveEntityListener,
MoveListener,
MotionUpdateListener,
RenderListener,
RenderTickListener,
PacketListener,
SprintListener,
SneakListener,
MotionListener,
StepListener,
UpdateStateListener {
    public SettingGroup settingRotations;
    public SettingGroup settingRotationSpeed;
    public BooleanSetting settingModeNew;
    public NumberSetting settingAimSpeed;
    public RangeSetting settingYawSpeed;
    public RangeSetting settingPitchSpeed;
    public RangeSetting settingYawAcceleration;
    public RangeSetting settingPitchAcceleration;
    public BooleanSetting settingCustomBlockClutchRotationSpeeds;
    public SettingGroup settingBlockClutchRotationSpeed;
    public BooleanSetting settingBlockClutchModeNew;
    public NumberSetting settingBlockClutchAimSpeed;
    public RangeSetting settingBlockClutchYawSpeed;
    public RangeSetting settingBlockClutchPitchSpeed;
    public RangeSetting settingBlockClutchYawAcceleration;
    public RangeSetting settingBlockClutchPitchAcceleration;
    public RangeSetting settingKeepRotationsAfterModuleDisable;
    public SettingGroup settingRotationRandomization;
    public BooleanSetting settingRandomizePitch;
    public NumberSetting settingRandomizePitchChanceWhileYawRotation;
    public NumberSetting settingRandomizePitchChanceOnBlock;
    public NumberSetting settingRandomizePitchChanceInAir;
    public BooleanSetting settingOtherPitchRandomization;
    public RangeSetting settingRandomizePitchVal;
    public RangeSetting settingRandomizePitchInterval;
    public BooleanSetting settingRandomizeYaw;
    public NumberSetting settingRandomizeYawChanceWhilePitchRotation;
    public NumberSetting settingRandomizeYawChanceOnBlock;
    public NumberSetting settingRandomizeYawChanceOnAir;
    public BooleanSetting settingOtherYawRandomization;
    public RangeSetting settingRandomizeYawVal;
    public RangeSetting settingRandomizeYawInterval;
    public SettingGroup settingStaticPitchSettings;
    public BooleanSetting settingStaticPitchBool;
    public BooleanSetting settingStaticPitchAtJump;
    public BooleanSetting settingStaticPitchRandomizeOnlyAtStart;
    public BooleanSetting settingIgnoreStaticPitchForBlockClutches;
    public RangeSetting settingStaticPitch;
    public BooleanSetting settingStaticPitchBoolDiagonal;
    public BooleanSetting settingStaticPitchAtJumpDiagonal;
    public BooleanSetting settingStaticPitchRandomizeOnlyAtStartDiagonal;
    public BooleanSetting settingIgnoreStaticPitchForBlockClutchesDiagonal;
    public RangeSetting settingStaticPitchDiagonal;
    public BooleanSetting settingReduceYawRotation;
    public BooleanSetting settingStrictYaw;
    public RangeSetting settingStrictYawIncorrectFactor;
    public RangeSetting settingStartDelayAfterDirectionChange;
    public BooleanSetting settingIgnoreStartDelayAfterDirectionChangeAtBlockClutch;
    public BooleanSetting settingRotateWithMovement;
    public BooleanSetting settingRotateWithMovementOnlyOrthogonal;
    public BooleanSetting settingImprove;
    public BooleanSetting settingImproveOnlyAtClutch;
    public BooleanSetting settingExtraBlockSearch;
    public BooleanSetting settingExtraBlockSearchOnlyAtClutch;
    public SettingGroup settingStrafeSettings;
    public BooleanSetting settingStrafe;
    public RangeSetting settingStrafeDegree;
    public RangeSetting settingStrafeWidth;
    public BooleanSetting settingCorrectRotationWhileBuilding;
    public BooleanSetting settingCorrectStrafeWithKeyBinds;
    public BooleanSetting settingCorrectStrafeWithKeyBindsCage;
    public RangeSetting settingCorrectStrafeWithKeyBindsWidth;
    public RangeSetting settingCorrectStrafeWithKeyBindsDuration;
    public RangeSetting settingCorrectStrafeWithKeyBindsMiddle;
    public ActionSetting settingCorrectStrafeWithKeyBindsMiddleExclusion;
    public RangeSetting settingCorrectStrafeWithKeyBindsDelayToRecalculateMiddle;
    public BooleanSetting settingStartAtEdge;
    public RangeSetting settingStartAtEdgeDelay;
    public BooleanSetting settingIgnoreStartAtEdgeAtBlockClutch;
    public BooleanSetting settingEdgeRotateCheck;
    public BooleanSetting settingEdgePlaceCheck;
    public BooleanSetting settingFlick;
    public BooleanSetting settingOtherFlick;
    public BooleanSetting settingOtherRotation;
    public BooleanSetting settingRecycleRotation;
    public BooleanSetting settingIntave;
    public BooleanSetting settingIntaveSafe;
    public BooleanSetting settingIntaveFast;
    public ModeSetting settingPitchSelection;
    public NumberSetting settingMaxPitchCorrection;
    public BooleanSetting settingIgnoreMaxPitchCorrectionIfBlockClutch;
    public BooleanSetting settingExpand;
    public NumberSetting settingExpandLength;
    public SettingGroup settingClicking;
    public BooleanSetting settingNoSwing;
    public BooleanSetting settingServerSideNoSwing;
    public RangeSetting settingCPS;
    public ModeSetting settingDCPSMode;
    public RangeSetting settingDCPS;
    public BooleanSetting settingForcePlaceIfYouWouldFallDown;
    public BooleanSetting settingAlwaysPlaceWhenPossible;
    public BooleanSetting settingClickPause;
    public BooleanSetting settingDelayClick;
    public BooleanSetting settingOneClick;
    public NumberSetting settingMaxYawRotationDeltaToPlaceBlocks;
    public NumberSetting settingMaxPitchRotationDeltaToPlaceBlocks;
    public BooleanSetting settingRemoveRotationDeltaLimitAtBlockClutch;
    public SettingGroup settingMovement;
    public BooleanSetting settingRemoveSneakStop;
    public SettingGroup settingSneaking;
    public BooleanSetting settingSneak;
    public BooleanSetting settingTimeJumpForOneStackForward;
    public BooleanSetting settingOneStackForward;
    public ActionSetting settingMinOffGroundSneakTickForward;
    public ActionSetting settingMaxOffGroundSneakTickForward;
    public BooleanSetting settingOneStackDiagonal;
    public ActionSetting settingMinOffGroundSneakTickDiagonal;
    public ActionSetting settingMaxOffGroundSneakTickDiagonal;
    public RangeSetting settingSneakTicks;
    public ActionSetting settingStartSneakDistToBlock;
    public ActionSetting settingSneakBlocksForward;
    public ActionSetting settingSneakBlocksDiagonal;
    public NumberSetting settingSneakSlowDown;
    public BooleanSetting settingEarlySneakCalculation;
    public ModeSetting settingADHSMode;
    public ModeSetting settingMoveFix;
    public BooleanSetting settingMoveFixIntave;
    public ModeSetting settingMode;
    public BooleanSetting settingJumpSprint;
    public BooleanSetting settingHover;
    public BooleanSetting settingStopAtStart;
    public BooleanSetting settingSafeWalkNoBlocks;
    public BooleanSetting settingSneakNoBlocks;
    public BooleanSetting settingStopNoBlocks;
    public BooleanSetting settingReduceVelocity;
    public BooleanSetting settingIgnoreSpeedMotion;
    public BooleanSetting settingSneakAtRotation;
    public BooleanSetting settingStopAtRotation;
    public SettingGroup settingGodBridging;
    public BooleanSetting settingGodBridge;
    public BooleanSetting settingDisableGodbridgeSafetyFeature;
    public RangeSetting settingResetGodBridgeAfterXJumps;
    public RangeSetting settingJumpAfterXBlocks;
    public RangeSetting settingJumpDelayAfterPlace;
    public RangeSetting settingStartDelay;
    public SettingGroup settingMoonWalking;
    public BooleanSetting settingMoonWalk;
    public RangeSetting settingMoonWalkWideness;
    public ActionSetting settingMoonWalkStartDistToBlock;
    public RangeSetting settingMoonWalkMiddle;
    public RangeSetting settingDelayToRecalculateMiddle;
    public SettingGroup settingSameYGroup;
    public BooleanSetting settingSameY;
    public BooleanSetting settingAutoJump;
    public BooleanSetting settingIntaveFast2;
    public BooleanSetting settingTellyBridge;
    public SettingGroup settingSafeWalkGroup;
    public BooleanSetting settingSafeWalk;
    public BooleanSetting settingSafeWalkAir;
    public BooleanSetting settingSafeWalkOnlyDiagonal;
    public SettingGroup settingJumpGroup;
    public BooleanSetting settingJump;
    public BooleanSetting settingJumpOnlyWhileSpacePressed;
    public RangeSetting settingJumpAfterXBlocksForward;
    public RangeSetting settingJumpAfterXBlocksDiagonal;
    public SettingGroup settingTimeGroup;
    public NumberSetting settingTimer;
    public BooleanSetting settingTimerDrop;
    public ActionSetting settingTimerSpeed;
    public SettingGroup settingMotionMultiplier;
    public RangeSetting settingMotionMultiplierAirForward;
    public RangeSetting settingMotionMultiplierAirStrafe;
    public RangeSetting settingMotionMultiplierGroundForward;
    public RangeSetting settingMotionMultiplierGroundStrafe;
    public SettingGroup settingMiscellaneous;
    public ModeSetting settingTowerMode;
    public BooleanSetting settingTowerWhileMoving;
    public NumberSetting settingConstantMotion;
    public NumberSetting settingJumpMotion;
    public ModeSetting settingSilentMode;
    public BooleanSetting settingUseBiggestStackSilent;
    public RangeSetting settingSilentStartDelay;
    public RangeSetting settingSilentSwitchDelay;
    public RangeSetting settingStackCountToStartSwitchProcess;
    public RangeSetting settingBackSwitchTickDelay;
    public BooleanSetting settingDynamicBackSwitchTickDelay;
    public SettingGroup settingVisuals;
    public BooleanSetting settingThirdPersonMode;
    public BooleanSetting settingHideSneakAnimation;
    public BooleanSetting settingBlockCount;
    public NumberSetting settingX;
    public NumberSetting settingY;
    public NumberSetting settingScale;
    public BooleanSetting settingBackGround;
    public ColorSetting settingBackGroundColor;
    public NumberSetting settingRoundFactor;
    public ShaderOptions settingBlockCount2;
    public BooleanSetting settingESP;
    public ColorSetting settingColor;
    public ShaderOptions settingESP2;
    public BlockPos blockPos;
    public PlaceTarget placeTarget;
    public MovingObjectPosition movingObjectPosition;
    public SimulatedPlayer simulatedPlayer;
    public RandomizationGenerator randomEntry;
    public MotionVectorSnapshot secondaryDoubleEntry;
    public SimulationBoundingBox customDoubleEntry;
    public Rotation rotation;
    public int tickDelay8;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public int tickDelay42;
    public int tickDelay15;
    public int tickDelay25;
    public double reachDistance22;
    public double reachDistance23;
    public int tickDelay27;
    public boolean visible2;
    public boolean activeState19;
    public int tickDelay17;
    public int tickDelay;
    public Intave14TimeBlockPlaceCheck intaveBlockPlaceCheck;
    public float motionMultiplier26;
    public int tickDelay16;
    public EagleSneakHelper internalManager;
    public int tickDelay37;
    public int keyCode2;
    public double reachDistance21;
    public float motionMultiplier;
    public int tickDelay28;
    public int tickDelay29;
    public int tickDelay46;
    public int tickDelay44;
    public double reachDistance31;
    public boolean activeState24;
    public int tickDelay51;
    public int tickDelay47;
    public int tickDelay48;
    public int tickDelay35;
    public int tickDelay43;
    public double reachDistance33;
    public double reachDistance40;
    public boolean activeState4;
    public boolean activeState32;
    public boolean activeState41;
    public int tickDelay14;
    public boolean activeState12;
    public boolean activeState36;
    public AnimationTimer animationTimer;
    public AnimationTimer animationTimer2;
    public boolean activeState2;
    public Vec3 positionVec;
    public int tickDelay10;
    public boolean activeState13;
    public boolean enabled2;
    public AnimationTimer animationTimer3;
    public AnimationTimer animationTimer4;
    public BlockPos blockPos2;
    public int tickDelay30;
    public int tickDelay34;
    public boolean activeState3;
    public AnimationTimer animationTimer5;
    public boolean activeState7;
    public int tickDelay39;
    public HashMap entryMap;
    public int tickDelay5;
    public int tickDelay50;
    public double reachDistance;
    public AnimationTimer animationTimer6;
    public AnimationTimer animationTimer7;
    public boolean activeState;
    public boolean expanded2;
    public boolean activeState20;
    public boolean activeState45;
    public int tickDelay18;
    public int tickDelay9;
    public AnimationTimer animationTimer8;
    public boolean activeState11;
    public int tickDelay38;
    public int tickDelay49;
    public int tickDelay6;
    public boolean activeState1;
    public double reachDistance52;

    public Scaffold() {
        super("Scaffold", "Gerüst", new Color(182, 194, 207), Category.WORLD);
        this.settingRotations = new SettingGroup(this, "Rotations", true);
        this.settingRotationSpeed = new SettingGroup(this, "RotationSpeed", true, this.settingRotations);
        BooleanSetting booleanSetting = new BooleanSetting(this, "ModeNew", false, this.settingRotationSpeed);
        BooleanSupplier booleanSupplier = () -> this.isModeNewVisible();
        Setting setting = booleanSetting.getSetting(booleanSupplier);
        this.settingModeNew = (BooleanSetting)setting;
        NumberSetting numberSetting = new NumberSetting(this, "AimSpeed", 50.0, 0.0, 100.0, 0, this.settingRotationSpeed);
        booleanSupplier = () -> this.isAimSpeedVisible();
        this.settingAimSpeed = numberSetting.getNumberSetting(booleanSupplier);
        RangeSetting rangeSetting2 = new RangeSetting(this, "YawSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.settingRotationSpeed).getRangeSetting2();
        booleanSupplier = () -> this.isYawSpeedVisible();
        RangeSetting rangeSetting = rangeSetting2.getRangeSetting(15343, booleanSupplier);
        this.settingYawSpeed = rangeSetting;
        RangeSetting rangeSetting22 = new RangeSetting(this, "PitchSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.settingRotationSpeed).getRangeSetting2();
        booleanSupplier = () -> this.isPitchSpeedVisible();
        this.settingPitchSpeed = rangeSetting22.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting23 = new RangeSetting(this, "YawAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.settingRotationSpeed).getRangeSetting2();
        booleanSupplier = () -> this.isYawAccelerationVisible();
        this.settingYawAcceleration = rangeSetting23.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting24 = new RangeSetting(this, "PitchAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.settingRotationSpeed)
        .getRangeSetting2();
        booleanSupplier = () -> this.isPitchAccelerationVisible();
        this.settingPitchAcceleration = rangeSetting24.getRangeSetting(15343, booleanSupplier);
        this.settingCustomBlockClutchRotationSpeeds = new BooleanSetting(this, "CustomBlockClutchRotationSpeeds", false, this.settingRotationSpeed);
        SettingGroup settingGroup3 = new SettingGroup(this, "BlockClutchRotationSpeed", true, this.settingRotationSpeed);
        booleanSupplier = () -> this.settingCustomBlockClutchRotationSpeeds.isEnabled();
        this.settingBlockClutchRotationSpeed = settingGroup3.getSettingGroup(booleanSupplier);
        Setting setting2 = new BooleanSetting(this, "BlockClutchModeNew", false, this.settingBlockClutchRotationSpeed).getSetting("ModeNew");
        booleanSupplier = () -> this.isBlockClutchModeNewVisible();
        this.settingBlockClutchModeNew = (BooleanSetting)(((BooleanSetting)setting2).getSetting(booleanSupplier));
        NumberSetting numberSetting4 = new NumberSetting(this, "BlockClutchAimSpeed", 50.0, 0.0, 100.0, 0, this.settingBlockClutchRotationSpeed).getNumberSetting("AimSpeed");
        booleanSupplier = () -> this.isBlockClutchAimSpeedVisible();
        this.settingBlockClutchAimSpeed = numberSetting4.getNumberSetting(booleanSupplier);
        RangeSetting rangeSetting6 = new RangeSetting(this, "BlockClutchYawSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.settingBlockClutchRotationSpeed)
        .getRangeSetting2().getRangeSetting("YawSpeed");
        booleanSupplier = () -> this.isBlockClutchYawSpeedVisible();
        this.settingBlockClutchYawSpeed = rangeSetting6.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting8 = new RangeSetting(this, "BlockClutchPitchSpeed", 30.0, 50.0, 0.0, 180.0, 1, this.settingBlockClutchRotationSpeed)
        .getRangeSetting2().getRangeSetting("PitchSpeed");
        booleanSupplier = () -> this.isBlockClutchPitchSpeedVisible();
        this.settingBlockClutchPitchSpeed = rangeSetting8.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting27 = new RangeSetting(
        this, "BlockClutchYawAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.settingBlockClutchRotationSpeed
        ).getRangeSetting("YawAcceleration").getRangeSetting2();
        booleanSupplier = () -> this.isBlockClutchYawAccelerationVisible();
        this.settingBlockClutchYawAcceleration = rangeSetting27.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting28 = new RangeSetting(
        this, "BlockClutchPitchAcceleration", 7.0, 15.0, 1.0, 180.0, 1, this.settingBlockClutchRotationSpeed
        ).getRangeSetting("PitchAcceleration").getRangeSetting2();
        booleanSupplier = () -> this.isBlockClutchPitchAccelerationVisible();
        this.settingBlockClutchPitchAcceleration = rangeSetting28.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting29 = new RangeSetting(this, "KeepRotationsAfterModuleDisable", 3.0, 5.0, 0.0, 20.0, 0, this.settingRotationSpeed)
        .getRangeSetting2();
        booleanSupplier = () -> this.isMoveFixDefault();
        this.settingKeepRotationsAfterModuleDisable = rangeSetting29.getRangeSetting(15343, booleanSupplier);
        this.settingRotationRandomization = new SettingGroup(this, "RotationRandomization", true, this.settingRotations);
        this.settingRandomizePitch = new BooleanSetting(this, "RandomizePitch", false, this.settingRotationRandomization);
        NumberSetting numberSetting6 = new NumberSetting(
        this, "RandomizePitchChanceWhileYawRotation", 100.0, 0.0, 100.0, 0, this.settingRotationRandomization
        );
        booleanSupplier = () -> this.isRandomizePitchChanceWhileYawRotationVisible();
        this.settingRandomizePitchChanceWhileYawRotation = numberSetting6.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting8 = new NumberSetting(this, "RandomizePitchChanceOnBlock", 100.0, 0.0, 100.0, 0, this.settingRotationRandomization);
        booleanSupplier = () -> this.isRandomizePitchChanceOnBlockVisible();
        this.settingRandomizePitchChanceOnBlock = numberSetting8.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting10 = new NumberSetting(this, "RandomizePitchChanceInAir", 100.0, 0.0, 100.0, 0, this.settingRotationRandomization);
        booleanSupplier = () -> this.isRandomizePitchChanceInAirVisible();
        this.settingRandomizePitchChanceInAir = numberSetting10.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "OtherPitchRandomization", false, this.settingRotationRandomization);
        booleanSupplier = () -> this.isOtherPitchRandomizationVisible();
        this.settingOtherPitchRandomization = (BooleanSetting)booleanSetting5.getSetting(booleanSupplier);
        RangeSetting rangeSetting210 = new RangeSetting(this, "RandomizePitchVal", -1.0, 1.0, -10.0, 10.0, 2, this.settingRotationRandomization)
        .getRangeSetting2();
        booleanSupplier = () -> this.isRandomizePitchValVisible();
        this.settingRandomizePitchVal = rangeSetting210.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting16 = new RangeSetting(this, "RandomizePitchInterval", 2.0, 4.0, 0.0, 20.0, 0, this.settingRotationRandomization);
        booleanSupplier = () -> this.isRandomizePitchIntervalVisible();
        this.settingRandomizePitchInterval = rangeSetting16.getRangeSetting(15343, booleanSupplier);
        this.settingRandomizeYaw = new BooleanSetting(this, "RandomizeYaw", false, this.settingRotationRandomization);
        NumberSetting numberSetting12 = new NumberSetting(
        this, "RandomizeYawChanceWhilePitchRotation", 100.0, 0.0, 100.0, 0, this.settingRotationRandomization
        );
        booleanSupplier = () -> this.isRandomizeYawChanceWhilePitchRotationVisible();
        this.settingRandomizeYawChanceWhilePitchRotation = numberSetting12.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting14 = new NumberSetting(this, "RandomizeYawChanceOnBlock", 100.0, 0.0, 100.0, 0, this.settingRotationRandomization);
        booleanSupplier = () -> this.isRandomizeYawChanceOnBlockVisible();
        this.settingRandomizeYawChanceOnBlock = numberSetting14.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting16 = new NumberSetting(this, "RandomizeYawChanceOnAir", 100.0, 0.0, 100.0, 0, this.settingRotationRandomization);
        booleanSupplier = () -> this.isRandomizeYawChanceOnAirVisible();
        this.settingRandomizeYawChanceOnAir = numberSetting16.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting7 = new BooleanSetting(this, "OtherYawRandomization", false, this.settingRotationRandomization);
        booleanSupplier = () -> this.isOtherYawRandomizationVisible();
        this.settingOtherYawRandomization = (BooleanSetting)booleanSetting7.getSetting(booleanSupplier);
        RangeSetting rangeSetting211 = new RangeSetting(this, "RandomizeYawVal", -1.0, 1.0, -10.0, 10.0, 2, this.settingRotationRandomization).getRangeSetting2();
        booleanSupplier = () -> this.isRandomizeYawValVisible();
        this.settingRandomizeYawVal = rangeSetting211.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting19 = new RangeSetting(this, "RandomizeYawInterval", 2.0, 4.0, 0.0, 20.0, 0, this.settingRotationRandomization);
        booleanSupplier = () -> this.isRandomizeYawIntervalVisible();
        this.settingRandomizeYawInterval = rangeSetting19.getRangeSetting(15343, booleanSupplier);
        this.settingStaticPitchSettings = new SettingGroup(this, "StaticPitchSettings", true, this.settingRotations);
        this.settingStaticPitchBool = (BooleanSetting)new BooleanSetting(this, "StaticPitchBool", false, this.settingStaticPitchSettings).getSetting("StaticPitch");
        BooleanSetting booleanSetting9 = new BooleanSetting(this, "StaticPitchAtJump", false, this.settingStaticPitchSettings);
        booleanSupplier = () -> this.settingStaticPitchBool.isEnabled();
        this.settingStaticPitchAtJump = (BooleanSetting)booleanSetting9.getSetting(booleanSupplier);
        BooleanSetting booleanSetting10 = new BooleanSetting(
        this, "StaticPitchRandomizeOnlyAtStart", false, this.settingStaticPitchSettings
        );
        booleanSupplier = () -> this.settingStaticPitchBool.isEnabled();
        this.settingStaticPitchRandomizeOnlyAtStart = (BooleanSetting)booleanSetting10.getSetting(booleanSupplier);
        BooleanSetting booleanSetting11 = new BooleanSetting(
        this, "IgnoreStaticPitchForBlockClutches", false, this.settingStaticPitchSettings
        );
        booleanSupplier = () -> this.settingStaticPitchBool.isEnabled();
        this.settingIgnoreStaticPitchForBlockClutches = (BooleanSetting)booleanSetting11.getSetting(booleanSupplier);
        RangeSetting rangeSetting21 = new RangeSetting(this, "StaticPitch", 80.0, 80.0, 0.0, 90.0, 2, this.settingStaticPitchSettings).getRangeSetting2().getRangeSetting("MinStaticPitchForward", "MaxStaticPitchForward");
        booleanSupplier = () -> this.settingStaticPitchBool.isEnabled();
        this.settingStaticPitch = rangeSetting21.getRangeSetting(15343, booleanSupplier);
        this.settingStaticPitchBoolDiagonal = (BooleanSetting)new BooleanSetting(this, "StaticPitchBoolDiagonal", false, this.settingStaticPitchSettings).getSetting("StaticPitchDiagonal");
        BooleanSetting booleanSetting13 = new BooleanSetting(this, "StaticPitchAtJumpDiagonal", false, this.settingStaticPitchSettings);
        booleanSupplier = () -> this.settingStaticPitchBoolDiagonal.isEnabled();
        this.settingStaticPitchAtJumpDiagonal = (BooleanSetting)booleanSetting13.getSetting(booleanSupplier);
        BooleanSetting booleanSetting14 = new BooleanSetting(
        this, "StaticPitchRandomizeOnlyAtStartDiagonal", false, this.settingStaticPitchSettings
        );
        booleanSupplier = () -> this.settingStaticPitchBoolDiagonal.isEnabled();
        this.settingStaticPitchRandomizeOnlyAtStartDiagonal = (BooleanSetting)booleanSetting14.getSetting(booleanSupplier);
        BooleanSetting booleanSetting15 = new BooleanSetting(
        this, "IgnoreStaticPitchForBlockClutchesDiagonal", false, this.settingStaticPitchSettings
        );
        booleanSupplier = () -> this.settingStaticPitchBoolDiagonal.isEnabled();
        this.settingIgnoreStaticPitchForBlockClutchesDiagonal = (BooleanSetting)booleanSetting15.getSetting(booleanSupplier);
        RangeSetting rangeSetting213 = new RangeSetting(this, "StaticPitchDiagonal", 80.0, 80.0, 0.0, 90.0, 2, this.settingStaticPitchSettings)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingStaticPitchBoolDiagonal.isEnabled();
        this.settingStaticPitchDiagonal = rangeSetting213.getRangeSetting(15343, booleanSupplier);
        this.settingReduceYawRotation = new BooleanSetting(this, "ReduceYawRotation", false, this.settingRotations);
        this.settingStrictYaw = new BooleanSetting(this, "StrictYaw", false, this.settingRotations);
        RangeSetting rangeSetting214 = new RangeSetting(this, "StrictYawIncorrectFactor", 1.5, 3.0, 0.0, 6.0, 2, this.settingRotations)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingStrictYaw.isEnabled();
        this.settingStrictYawIncorrectFactor = rangeSetting214.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting215 = new RangeSetting(this, "StartDelayAfterDirectionChange", 0.0, 0.0, 0.0, 20.0, 0, this.settingRotations)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingStrictYaw.isEnabled();
        this.settingStartDelayAfterDirectionChange = rangeSetting215.getRangeSetting(15343, booleanSupplier);
        BooleanSetting booleanSetting18 = new BooleanSetting(
        this, "IgnoreStartDelayAfterDirectionChangeAtBlockClutch", false, this.settingRotations
        );
        booleanSupplier = () -> this.isIgnoreStartDelayAfterDirectionChangeAtBlockClutchVisible();
        this.settingIgnoreStartDelayAfterDirectionChangeAtBlockClutch = (BooleanSetting)booleanSetting18.getSetting(booleanSupplier);
        this.settingRotateWithMovement = new BooleanSetting(this, "RotateWithMovement", true, this.settingRotations);
        BooleanSetting booleanSetting20 = new BooleanSetting(this, "RotateWithMovementOnlyOrthogonal", false, this.settingRotations);
        booleanSupplier = () -> this.settingRotateWithMovement.isEnabled();
        this.settingRotateWithMovementOnlyOrthogonal = (BooleanSetting)booleanSetting20.getSetting(booleanSupplier);
        this.settingImprove = new BooleanSetting(this, "Improve", true, this.settingRotations);
        BooleanSetting booleanSetting22 = new BooleanSetting(this, "ImproveOnlyAtClutch", false, this.settingRotations);
        booleanSupplier = () -> this.isImproveOnlyAtClutchVisible();
        this.settingImproveOnlyAtClutch = (BooleanSetting)booleanSetting22.getSetting(booleanSupplier);
        this.settingExtraBlockSearch = new BooleanSetting(this, "ExtraBlockSearch", true, this.settingRotations);
        BooleanSetting booleanSetting24 = new BooleanSetting(this, "ExtraBlockSearchOnlyAtClutch", false, this.settingRotations);
        booleanSupplier = () -> this.settingExtraBlockSearch.isEnabled();
        this.settingExtraBlockSearchOnlyAtClutch = (BooleanSetting)booleanSetting24.getSetting(booleanSupplier);
        this.settingStrafeSettings = new SettingGroup(this, "StrafeSettings", true, this.settingRotations);
        this.settingStrafe = new BooleanSetting(this, "Strafe", false, this.settingStrafeSettings);
        RangeSetting rangeSetting216 = new RangeSetting(this, "StrafeDegree", 40.0, 45.0, 1.0, 50.0, 2, this.settingStrafeSettings).getRangeSetting2();
        booleanSupplier = () -> this.settingStrafe.isEnabled();
        this.settingStrafeDegree = rangeSetting216.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting217 = new RangeSetting(this, "StrafeWidth", 0.35, 0.39, 0.0, 0.4, 2, this.settingStrafeSettings).getRangeSetting2();
        booleanSupplier = () -> this.isStrafeWidthVisible();
        this.settingStrafeWidth = rangeSetting217.getRangeSetting(15343, booleanSupplier);
        BooleanSetting booleanSetting26 = new BooleanSetting(this, "CorrectRotationWhileBuilding", true, this.settingStrafeSettings);
        booleanSupplier = () -> this.settingStrafe.isEnabled();
        this.settingCorrectRotationWhileBuilding = (BooleanSetting)booleanSetting26.getSetting(booleanSupplier);
        BooleanSetting booleanSetting27 = new BooleanSetting(this, "CorrectStrafeWithKeyBinds", true, this.settingStrafeSettings);
        booleanSupplier = () -> this.settingStrafe.isEnabled();
        this.settingCorrectStrafeWithKeyBinds = (BooleanSetting)booleanSetting27.getSetting(booleanSupplier);
        BooleanSetting booleanSetting28 = new BooleanSetting(this, "CorrectStrafeWithKeyBindsCage", false, this.settingStrafeSettings);
        booleanSupplier = () -> this.isCorrectStrafeWithKeyBindsCageVisible();
        this.settingCorrectStrafeWithKeyBindsCage = (BooleanSetting)booleanSetting28.getSetting(booleanSupplier);
        RangeSetting rangeSetting218 = new RangeSetting(
        this, "CorrectStrafeWithKeyBindsWidth", -0.05, 0.05, -0.3, 0.3, 2, this.settingStrafeSettings
        )
        .getRangeSetting2()
        .getRangeSetting2();
        booleanSupplier = () -> this.isCorrectStrafeWithKeyBindsWidthVisible();
        this.settingCorrectStrafeWithKeyBindsWidth = rangeSetting218.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting37 = new RangeSetting(
        this, "CorrectStrafeWithKeyBindsDuration", 1.0, 3.0, 1.0, 5.0, 0, this.settingStrafeSettings
        );
        booleanSupplier = () -> this.isCorrectStrafeWithKeyBindsDurationVisible();
        this.settingCorrectStrafeWithKeyBindsDuration = rangeSetting37.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting219 = new RangeSetting(this, "CorrectStrafeWithKeyBindsMiddle", 0.5, 0.5, 0.2, 0.8, 2, this.settingStrafeSettings)
        .getRangeSetting2();
        booleanSupplier = () -> this.isCorrectStrafeWithKeyBindsMiddleVisible();
        this.settingCorrectStrafeWithKeyBindsMiddle = rangeSetting219.getRangeSetting(15343, booleanSupplier);
        this.settingCorrectStrafeWithKeyBindsMiddleExclusion = new ActionSetting(
        this, "CorrectStrafeWithKeyBindsMiddleExclusion", 0.0, 0.0, 0.2, 0.8, 2, this.settingStrafeSettings
        ).getActionSetting(
        () -> this.isCorrectStrafeWithKeyBindsMiddleExclusionVisible()
        );
        RangeSetting rangeSetting40 = new RangeSetting(this, "CorrectStrafeWithKeyBindsDelayToRecalculateMiddle", 15.0, 30.0, 0.0, 40.0, 0, this.settingStrafeSettings)
        .getRangeSetting2().getRangeSetting("DelayToRecalculateMiddle");
        booleanSupplier = () -> this.executeAction();
        this.settingCorrectStrafeWithKeyBindsDelayToRecalculateMiddle = rangeSetting40.getRangeSetting(15343, booleanSupplier);
        this.settingStartAtEdge = new BooleanSetting(this, "StartAtEdge", false, this.settingRotations);
        RangeSetting rangeSetting221 = new RangeSetting(this, "StartAtEdgeDelay", 2.0, 4.0, 0.0, 10.0, 0, this.settingRotations).getRangeSetting2();
        booleanSupplier = () -> this.settingStartAtEdge.isEnabled();
        this.settingStartAtEdgeDelay = rangeSetting221.getRangeSetting(15343, booleanSupplier);
        BooleanSetting booleanSetting30 = new BooleanSetting(this, "IgnoreStartAtEdgeAtBlockClutch", false, this.settingRotations);
        booleanSupplier = () -> this.isIgnoreStartAtEdgeAtBlockClutchVisible();
        this.settingIgnoreStartAtEdgeAtBlockClutch = (BooleanSetting)booleanSetting30.getSetting(booleanSupplier);
        this.settingEdgeRotateCheck = new BooleanSetting(this, "EdgeRotateCheck", false, this.settingRotations);
        BooleanSetting booleanSetting32 = new BooleanSetting(this, "EdgePlaceCheck", false, this.settingRotations);
        booleanSupplier = () -> this.settingEdgeRotateCheck.isEnabled();
        this.settingEdgePlaceCheck = (BooleanSetting)booleanSetting32.getSetting(booleanSupplier);
        this.settingFlick = new BooleanSetting(this, "Flick", false, this.settingRotations);
        BooleanSetting booleanSetting34 = new BooleanSetting(this, "OtherFlick", false, this.settingRotations);
        booleanSupplier = () -> this.settingFlick.isEnabled();
        this.settingOtherFlick = (BooleanSetting)booleanSetting34.getSetting(booleanSupplier);
        this.settingOtherRotation = new BooleanSetting(this, "OtherRotation", true, this.settingRotations);
        this.settingRecycleRotation = new BooleanSetting(this, "RecycleRotation", false, this.settingRotations);
        this.settingIntave = new BooleanSetting(this, "Intave", true, this.settingRotations);
        BooleanSetting booleanSetting38 = new BooleanSetting(this, "IntaveSafe", true, this.settingRotations);
        booleanSupplier = () -> this.settingIntave.isEnabled();
        this.settingIntaveSafe = (BooleanSetting)booleanSetting38.getSetting(booleanSupplier);
        BooleanSetting booleanSetting39 = new BooleanSetting(this, "IntaveFast", false, this.settingRotations);
        booleanSupplier = () -> this.settingIntave.isEnabled();
        this.settingIntaveFast = (BooleanSetting)booleanSetting39.getSetting(booleanSupplier);
        String[] textArray = new String[6];
        textArray[0] = "Nearest";
        textArray[1] = "Furthest";
        textArray[2] = "Highest";
        textArray[3] = "Lowest";
        textArray[4] = "Mid";
        textArray[5] = "Random";
        this.settingPitchSelection = new ModeSetting(this, "PitchSelection", "Nearest", textArray, this.settingRotations);
        NumberSetting numberSetting18 = new NumberSetting(this, "MaxPitchCorrection", 180.0, 0.0, 180.0, 2, this.settingRotations);
        booleanSupplier = () -> this.isMaxPitchCorrectionVisible();
        this.settingMaxPitchCorrection = numberSetting18.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting40 = new BooleanSetting(this, "IgnoreMaxPitchCorrectionIfBlockClutch", false, this.settingRotations);
        booleanSupplier = () -> this.isIgnoreMaxPitchCorrectionIfBlockClutchVisible();
        this.settingIgnoreMaxPitchCorrectionIfBlockClutch = (BooleanSetting)booleanSetting40.getSetting(booleanSupplier);
        this.settingExpand = new BooleanSetting(this, "Expand", false, this.settingRotations);
        NumberSetting numberSetting20 = new NumberSetting(this, "ExpandLength", 1.0, 1.0, 10.0, 1, this.settingRotations);
        booleanSupplier = () -> this.settingExpand.isEnabled();
        this.settingExpandLength = numberSetting20.getNumberSetting(booleanSupplier);
        this.settingClicking = new SettingGroup(this, "Clicking", true);
        this.settingNoSwing = new BooleanSetting(this, "NoSwing", false, this.settingClicking);
        BooleanSetting booleanSetting43 = new BooleanSetting(this, "ServerSideNoSwing", false, this.settingClicking);
        booleanSupplier = () -> this.settingNoSwing.isEnabled();
        this.settingServerSideNoSwing = (BooleanSetting)booleanSetting43.getSetting(booleanSupplier);
        this.settingCPS = new RangeSetting(this, "CPS", 10.0, 20.0, 0.0, 40.0, 0, this.settingClicking).getRangeSetting2();
        String[] textArray2 = new String[2];
        textArray2[0] = "Internal";
        textArray2[1] = "External";
        this.settingDCPSMode = new ModeSetting(this, "DCPSMode", "External", textArray2, this.settingClicking);
        RangeSetting rangeSetting223 = new RangeSetting(this, "DCPS", 2.0, 4.0, 0.0, 20.0, 0, this.settingClicking).getRangeSetting2();
        booleanSupplier = () -> this.isDCPSModeExternal();
        this.settingDCPS = rangeSetting223.getRangeSetting(15343, booleanSupplier);
        this.settingForcePlaceIfYouWouldFallDown = new BooleanSetting(this, "ForcePlaceIfYouWouldFallDown", true, this.settingClicking);
        this.settingAlwaysPlaceWhenPossible = new BooleanSetting(this, "AlwaysPlaceWhenPossible", true, this.settingClicking);
        this.settingClickPause = new BooleanSetting(this, "ClickPause", false, this.settingClicking);
        this.settingDelayClick = new BooleanSetting(this, "DelayClick", false, this.settingClicking);
        this.settingOneClick = new BooleanSetting(this, "OneClick", false, this.settingClicking);
        this.settingMaxYawRotationDeltaToPlaceBlocks = new NumberSetting(this, "MaxYawRotationDeltaToPlaceBlocks", 180.0, 0.0, 180.0, 2, this.settingClicking);
        this.settingMaxPitchRotationDeltaToPlaceBlocks = new NumberSetting(this, "MaxPitchRotationDeltaToPlaceBlocks", 180.0, 0.0, 180.0, 2, this.settingClicking);
        BooleanSetting booleanSetting49 = new BooleanSetting(this, "RemoveRotationDeltaLimitAtBlockClutch", false, this.settingClicking);
        booleanSupplier = () -> this.isRemoveRotationDeltaLimitAtBlockClutchVisible();
        this.settingRemoveRotationDeltaLimitAtBlockClutch = (BooleanSetting)booleanSetting49.getSetting(booleanSupplier);
        this.settingMovement = new SettingGroup(this, "Movement", true);
        this.settingRemoveSneakStop = new BooleanSetting(this, "RemoveSneakStop", false, this.settingMovement);
        this.settingSneaking = new SettingGroup(this, "Sneaking", true, this.settingMovement);
        this.settingSneak = new BooleanSetting(this, "Sneak", false, this.settingSneaking);
        BooleanSetting booleanSetting52 = new BooleanSetting(this, "TimeJumpForOneStackForward", false, this.settingSneaking);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingTimeJumpForOneStackForward = (BooleanSetting)booleanSetting52.getSetting(booleanSupplier);
        BooleanSetting booleanSetting53 = new BooleanSetting(this, "OneStackForward", false, this.settingSneaking);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingOneStackForward = (BooleanSetting)booleanSetting53.getSetting(booleanSupplier);
        this.settingMinOffGroundSneakTickForward = new ActionSetting(this, "MinOffGroundSneakTickForward", 1.0, 2.0, 0.0, 12.0, 0, this.settingSneaking).getActionSetting(() -> this.isMinOffGroundSneakTickForwardVisible());
        this.settingMaxOffGroundSneakTickForward = new ActionSetting(this, "MaxOffGroundSneakTickForward", 10.0, 11.0, 0.0, 12.0, 0, this.settingSneaking).getActionSetting(() -> this.isMaxOffGroundSneakTickForwardVisible());
        BooleanSetting booleanSetting54 = new BooleanSetting(this, "OneStackDiagonal", false, this.settingSneaking);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingOneStackDiagonal = (BooleanSetting)booleanSetting54.getSetting(booleanSupplier);
        this.settingMinOffGroundSneakTickDiagonal = new ActionSetting(this, "MinOffGroundSneakTickDiagonal", 1.0, 2.0, 0.0, 12.0, 0, this.settingSneaking).getActionSetting(() -> this.isMinOffGroundSneakTickDiagonalVisible());
        this.settingMaxOffGroundSneakTickDiagonal = new ActionSetting(this, "MaxOffGroundSneakTickDiagonal", 10.0, 11.0, 0.0, 12.0, 0, this.settingSneaking).getActionSetting(() -> this.isMaxOffGroundSneakTickDiagonalVisible());
        RangeSetting rangeSetting45 = new RangeSetting(this, "SneakTicks", 1.0, 2.0, 0.0, 10.0, 0, this.settingSneaking).getRangeSetting(3.0, 3.0, 1.0, 30.0, 8.0, 15.0);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingSneakTicks = rangeSetting45.getRangeSetting(15343, booleanSupplier);
        this.settingStartSneakDistToBlock = new ActionSetting(this, "StartSneakDistToBlock", 0.05, 0.2, -0.2, 0.5, 2, this.settingSneaking).getActionSetting(() -> this.settingSneak.isEnabled());
        this.settingSneakBlocksForward = new ActionSetting(this, "SneakBlocksForward", 1.0, 2.0, 0.0, 15.0, 0, this.settingSneaking).getActionSetting(() -> this.settingSneak.isEnabled());
        this.settingSneakBlocksDiagonal = new ActionSetting(this, "SneakBlocksDiagonal", 1.0, 2.0, 0.0, 15.0, 0, this.settingSneaking).getActionSetting(() -> this.settingSneak.isEnabled());
        NumberSetting numberSetting24 = new NumberSetting(this, "SneakSlowDown", 0.3, 0.3, 1.0, 2, this.settingSneaking);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingSneakSlowDown = numberSetting24.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting55 = new BooleanSetting(this, "EarlySneakCalculation", true, this.settingSneaking);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingEarlySneakCalculation = (BooleanSetting)booleanSetting55.getSetting(booleanSupplier);
        String[] textArray3 = new String[3];
        textArray3[0] = "None";
        textArray3[1] = "ADS";
        textArray3[2] = "ADHS";
        this.settingADHSMode = new ModeSetting(this, "ADHSMode", "None", textArray3, this.settingMovement);
        String[] textArray4 = new String[3];
        textArray4[0] = "None";
        textArray4[1] = "Default";
        textArray4[2] = "Advanced";
        this.settingMoveFix = new ModeSetting(this, "MoveFix", "Default", textArray4, this.settingMovement);
        BooleanSetting booleanSetting56 = new BooleanSetting(this, "MoveFixIntave", true, this.settingMovement);
        booleanSupplier = () -> this.isMoveFixIntaveVisible();
        this.settingMoveFixIntave = (BooleanSetting)booleanSetting56.getSetting(booleanSupplier);
        String[] textArray5 = new String[4];
        textArray5[0] = "None";
        textArray5[1] = "Legit";
        textArray5[2] = "AllDirection";
        textArray5[3] = "AllDirectionMotion";
        this.settingMode = new ModeSetting(this, "Mode", "None", textArray5, this.settingMovement).getModeSetting("SprintMode");
        this.settingJumpSprint = new BooleanSetting(this, "JumpSprint", false, this.settingMovement);
        this.settingHover = new BooleanSetting(this, "Hover", false, this.settingMovement);
        this.settingStopAtStart = new BooleanSetting(this, "StopAtStart", false, this.settingMovement);
        this.settingSafeWalkNoBlocks = new BooleanSetting(this, "SafeWalkNoBlocks", true, this.settingMovement);
        this.settingSneakNoBlocks = new BooleanSetting(this, "SneakNoBlocks", true, this.settingMovement);
        this.settingStopNoBlocks = new BooleanSetting(this, "StopNoBlocks", false, this.settingMovement);
        this.settingReduceVelocity = new BooleanSetting(this, "ReduceVelocity", false, this.settingMovement);
        this.settingIgnoreSpeedMotion = new BooleanSetting(this, "IgnoreSpeedMotion", false, this.settingMovement);
        this.settingSneakAtRotation = new BooleanSetting(this, "SneakAtRotation", false, this.settingMovement);
        this.settingStopAtRotation = new BooleanSetting(this, "StopAtRotation", false, this.settingMovement);
        this.settingGodBridging = new SettingGroup(this, "GodBridging", true, this.settingMovement);
        this.settingGodBridge = new BooleanSetting(this, "GodBridge", false, this.settingGodBridging);
        this.settingDisableGodbridgeSafetyFeature = new BooleanSetting(this, "DisableGodbridgeSafetyFeature", false, this.settingGodBridging);
        RangeSetting rangeSetting47 = new RangeSetting(this, "ResetGodBridgeAfterXJumps", 7.0, 8.0, 0.0, 12.0, 0, this.settingGodBridging);
        booleanSupplier = () -> this.settingGodBridge.isEnabled();
        this.settingResetGodBridgeAfterXJumps = rangeSetting47.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting224 = new RangeSetting(this, "JumpAfterXBlocks", 8.0, 8.0, 0.0, 9.0, 0, this.settingGodBridging).getRangeSetting2();
        booleanSupplier = () -> this.settingGodBridge.isEnabled();
        this.settingJumpAfterXBlocks = rangeSetting224.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting50 = new RangeSetting(this, "JumpDelayAfterPlace", 0.0, 0.0, 0.0, 1.0, 0, this.settingGodBridging);
        booleanSupplier = () -> this.settingGodBridge.isEnabled();
        this.settingJumpDelayAfterPlace = rangeSetting50.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting225 = new RangeSetting(this, "StartDelay", 10.0, 13.0, 8.0, 20.0, 0, this.settingGodBridging).getRangeSetting2();
        booleanSupplier = () -> this.settingGodBridge.isEnabled();
        this.settingStartDelay = rangeSetting225.getRangeSetting(15343, booleanSupplier);
        this.settingMoonWalking = new SettingGroup(this, "MoonWalking", true, this.settingMovement);
        this.settingMoonWalk = new BooleanSetting(this, "MoonWalk", true, this.settingMoonWalking);
        RangeSetting rangeSetting226 = new RangeSetting(this, "MoonWalkWideness", 0.05, 0.1, 0.0, 0.3, 2, this.settingMoonWalking).getRangeSetting2();
        booleanSupplier = () -> this.isMoonWalkWidenessVisible();
        this.settingMoonWalkWideness = rangeSetting226.getRangeSetting(15343, booleanSupplier);
        this.settingMoonWalkStartDistToBlock = new ActionSetting(this, "MoonWalkStartDistToBlock", 0.05, 0.2, -0.2, 0.5, 2, this.settingMoonWalking).getActionSetting(() -> this.isMoonWalkStartDistToBlockVisible());
        RangeSetting rangeSetting54 = new RangeSetting(this, "MoonWalkMiddle", 0.5, 0.5, 0.2, 0.8, 2, this.settingMoonWalking);
        booleanSupplier = () -> this.isMoonWalkMiddleVisible();
        this.settingMoonWalkMiddle = rangeSetting54.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting56 = new RangeSetting(this, "DelayToRecalculateMiddle", 15.0, 30.0, 0.0, 40.0, 0, this.settingMoonWalking);
        booleanSupplier = () -> this.isDelayToRecalculateMiddleVisible();
        this.settingDelayToRecalculateMiddle = rangeSetting56.getRangeSetting(15343, booleanSupplier);
        this.settingSameYGroup = new SettingGroup(this, "SameYGroup", true, this.settingMovement);
        this.settingSameY = new BooleanSetting(this, "SameY", false, this.settingSameYGroup);
        BooleanSetting booleanSetting71 = new BooleanSetting(this, "AutoJump", false, this.settingSameYGroup);
        booleanSupplier = () -> this.settingSameY.isEnabled();
        this.settingAutoJump = (BooleanSetting)booleanSetting71.getSetting(booleanSupplier);
        BooleanSetting booleanSetting72 = new BooleanSetting(this, "IntaveFast2", true, this.settingSameYGroup);
        booleanSupplier = () -> this.isIntaveFast2Visible();
        this.settingIntaveFast2 = (BooleanSetting)booleanSetting72.getSetting(booleanSupplier);
        BooleanSetting booleanSetting73 = new BooleanSetting(this, "TellyBridge", false, this.settingSameYGroup);
        booleanSupplier = () -> this.settingSameY.isEnabled();
        this.settingTellyBridge = (BooleanSetting)booleanSetting73.getSetting(booleanSupplier);
        this.settingSafeWalkGroup = new SettingGroup(this, "SafeWalkGroup", true, this.settingMovement);
        this.settingSafeWalk = new BooleanSetting(this, "SafeWalk", false, this.settingSafeWalkGroup);
        BooleanSetting booleanSetting75 = new BooleanSetting(this, "SafeWalkAir", false, this.settingSafeWalkGroup);
        booleanSupplier = () -> this.settingSafeWalk.isEnabled();
        this.settingSafeWalkAir = (BooleanSetting)booleanSetting75.getSetting(booleanSupplier);
        BooleanSetting booleanSetting76 = new BooleanSetting(this, "SafeWalkOnlyDiagonal", false, this.settingSafeWalkGroup);
        booleanSupplier = () -> this.settingSafeWalk.isEnabled();
        this.settingSafeWalkOnlyDiagonal = (BooleanSetting)booleanSetting76.getSetting(booleanSupplier);
        this.settingJumpGroup = new SettingGroup(this, "JumpGroup", true, this.settingMovement);
        this.settingJump = new BooleanSetting(this, "Jump", false, this.settingJumpGroup);
        BooleanSetting booleanSetting78 = new BooleanSetting(this, "JumpOnlyWhileSpacePressed", false, this.settingJumpGroup);
        booleanSupplier = () -> this.settingJump.isEnabled();
        this.settingJumpOnlyWhileSpacePressed = (BooleanSetting)booleanSetting78.getSetting(booleanSupplier);
        RangeSetting rangeSetting227 = new RangeSetting(this, "JumpAfterXBlocksForward", 6.0, 6.0, 0.0, 10.0, 0, this.settingJumpGroup)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingJump.isEnabled();
        this.settingJumpAfterXBlocksForward = rangeSetting227.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting228 = new RangeSetting(this, "JumpAfterXBlocksDiagonal", 6.0, 6.0, 0.0, 10.0, 0, this.settingJumpGroup)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingJump.isEnabled();
        this.settingJumpAfterXBlocksDiagonal = rangeSetting228.getRangeSetting(15343, booleanSupplier);
        this.settingTimeGroup = new SettingGroup(this, "TimeGroup", true, this.settingMovement);
        this.settingTimer = new NumberSetting(this, "Timer", 1.0, 0.1, 4.0, 2, this.settingTimeGroup);
        this.settingTimerDrop = new BooleanSetting(this, "TimerDrop", false, this.settingTimeGroup);
        this.settingTimerSpeed = new ActionSetting(this, "TimerSpeed", 0.1, 50.0, 0.0, 50.0, 2, this.settingTimeGroup).getActionSetting(() -> this.isTimerSpeedVisible());
        this.settingMotionMultiplier = new SettingGroup(this, "MotionMultiplier", true, this.settingMovement);
        this.settingMotionMultiplierAirForward = new RangeSetting(this, "MotionMultiplierAirForward", 1.0, 1.0, 0.1, 2.0, 2, this.settingMotionMultiplier);
        this.settingMotionMultiplierAirStrafe = new RangeSetting(this, "MotionMultiplierAirStrafe", 1.0, 1.0, 0.1, 2.0, 2, this.settingMotionMultiplier);
        this.settingMotionMultiplierGroundForward = new RangeSetting(this, "MotionMultiplierGroundForward", 1.0, 1.0, 0.1, 2.0, 2, this.settingMotionMultiplier);
        this.settingMotionMultiplierGroundStrafe = new RangeSetting(this, "MotionMultiplierGroundStrafe", 1.0, 1.0, 0.1, 2.0, 2, this.settingMotionMultiplier);
        this.settingMiscellaneous = new SettingGroup(this, "Miscellaneous", true);
        String[] textArray6 = new String[8];
        textArray6[0] = "Legit";
        textArray6[1] = "Intave1";
        textArray6[2] = "Intave2";
        textArray6[3] = "NCP";
        textArray6[4] = "NCPFast";
        textArray6[5] = "Verus";
        textArray6[6] = "ConstantMotion";
        textArray6[7] = "Jump";
        this.settingTowerMode = new ModeSetting(this, "TowerMode", "Legit", textArray6, this.settingMiscellaneous);
        BooleanSetting booleanSetting80 = new BooleanSetting(this, "TowerWhileMoving", false, this.settingMiscellaneous);
        booleanSupplier = () -> this.isTowerWhileMovingVisible();
        this.settingTowerWhileMoving = (BooleanSetting)booleanSetting80.getSetting(booleanSupplier);
        NumberSetting numberSetting27 = new NumberSetting(this, "ConstantMotion", 0.3, 0.01, 0.4, 2, this.settingMiscellaneous);
        booleanSupplier = () -> this.isTowerModeConstantMotion();
        this.settingConstantMotion = numberSetting27.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting29 = new NumberSetting(this, "JumpMotion", 0.5, 0.37, 1.0, 2, this.settingMiscellaneous);
        booleanSupplier = () -> this.isTowerModeJump();
        this.settingJumpMotion = numberSetting29.getNumberSetting(booleanSupplier);
        String[] textArray7 = new String[4];
        textArray7[0] = "None";
        textArray7[1] = "Legit";
        textArray7[2] = "Default";
        textArray7[3] = "Exploit";
        this.settingSilentMode = new ModeSetting(this, "SilentMode", "Default", textArray7, this.settingMiscellaneous);
        BooleanSetting booleanSetting81 = new BooleanSetting(this, "UseBiggestStackSilent", false, this.settingMiscellaneous);
        booleanSupplier = () -> this.isUseBiggestStackSilentVisible();
        this.settingUseBiggestStackSilent = (BooleanSetting)booleanSetting81.getSetting(booleanSupplier);
        RangeSetting rangeSetting64 = new RangeSetting(this, "SilentStartDelay", 0.0, 0.0, 0.0, 10.0, 0, this.settingMiscellaneous);
        booleanSupplier = () -> this.isSilentStartDelayVisible();
        this.settingSilentStartDelay = rangeSetting64.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting66 = new RangeSetting(this, "SilentSwitchDelay", 0.0, 0.0, 0.0, 10.0, 0, this.settingMiscellaneous);
        booleanSupplier = () -> this.isSilentSwitchDelayVisible();
        this.settingSilentSwitchDelay = rangeSetting66.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting68 = new RangeSetting(this, "StackCountToStartSwitchProcess", 0.0, 0.0, 0.0, 5.0, 0, this.settingMiscellaneous);
        booleanSupplier = () -> this.isStackCountToStartSwitchProcessVisible();
        this.settingStackCountToStartSwitchProcess = rangeSetting68.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting229 = new RangeSetting(this, "BackSwitchTickDelay", 5.0, 10.0, 0.0, 40.0, 0, this.settingMiscellaneous)
        .getRangeSetting2();
        booleanSupplier = () -> this.isSilentModeDefault();
        this.settingBackSwitchTickDelay = rangeSetting229.getRangeSetting(15343, booleanSupplier);
        BooleanSetting booleanSetting82 = new BooleanSetting(this, "DynamicBackSwitchTickDelay", true, this.settingMiscellaneous);
        booleanSupplier = () -> this.isDynamicBackSwitchTickDelayVisible();
        this.settingDynamicBackSwitchTickDelay = (BooleanSetting)booleanSetting82.getSetting(booleanSupplier);
        this.settingVisuals = new SettingGroup(this, "Visuals", true);
        this.settingThirdPersonMode = new BooleanSetting(this, "ThirdPersonMode", false, this.settingVisuals);
        BooleanSetting booleanSetting84 = new BooleanSetting(this, "HideSneakAnimation", false, this.settingVisuals);
        booleanSupplier = () -> this.settingSneak.isEnabled();
        this.settingHideSneakAnimation = (BooleanSetting)booleanSetting84.getSetting(booleanSupplier);
        this.settingBlockCount = new BooleanSetting(this, "BlockCount", true, this.settingVisuals);
        NumberSetting numberSetting31 = new NumberSetting(this, "X", 50.0, 0.0, 100.0, 2, this.settingVisuals);
        booleanSupplier = () -> this.settingBlockCount.isEnabled();
        this.settingX = numberSetting31.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting33 = new NumberSetting(this, "Y", 53.0, 0.0, 100.0, 2, this.settingVisuals);
        booleanSupplier = () -> this.settingBlockCount.isEnabled();
        this.settingY = numberSetting33.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting35 = new NumberSetting(this, "Scale", 2.0, 1.0, 4.0, 1, this.settingVisuals);
        booleanSupplier = () -> this.settingBlockCount.isEnabled();
        this.settingScale = numberSetting35.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting86 = new BooleanSetting(this, "BackGround", true, this.settingVisuals);
        booleanSupplier = () -> this.settingBlockCount.isEnabled();
        this.settingBackGround = (BooleanSetting)booleanSetting86.getSetting(booleanSupplier);
        Color color = new Color(0, 0, 0, 100);
        ColorSetting colorSetting = new ColorSetting(this, "BackGroundColor", color, this.settingVisuals);
        booleanSupplier = () -> this.settingBackGround.isEnabled();
        this.settingBackGroundColor = (ColorSetting)colorSetting.getSetting(booleanSupplier);
        NumberSetting numberSetting37 = new NumberSetting(this, "RoundFactor", 5.0, 0.0, 11.0, 0, this.settingVisuals);
        booleanSupplier = () -> this.isRoundFactorVisible();
        this.settingRoundFactor = numberSetting37.getNumberSetting(booleanSupplier);
        ShaderOptions shaderOptions = new ShaderOptions(this, "BlockCount", this.settingVisuals);
        Color black = Color.black;
        ShaderOptions shaderOptions2 = shaderOptions.getShaderOptions(black);
        booleanSupplier = () -> this.isBlockCountVisible();
        this.settingBlockCount2 = (ShaderOptions)shaderOptions2.getSetting(booleanSupplier);
        this.settingESP = new BooleanSetting(this, "ESP", true, this.settingVisuals);
        Color color2 = new Color(41, 146, 222, 100);
        ColorSetting colorSetting2 = new ColorSetting(this, "Color", color2, this.settingVisuals);
        booleanSupplier = () -> this.settingESP.isEnabled();
        this.settingColor = (ColorSetting)colorSetting2.getSetting(booleanSupplier);
        ShaderOptions shaderOptions3 = new ShaderOptions(this, "ESP", this.settingVisuals);
        Color black2 = Color.black;
        ShaderOptions shaderOptions4 = shaderOptions3.getShaderOptions(black2);
        booleanSupplier = () -> this.settingESP.isEnabled();
        this.settingESP2 = (ShaderOptions)shaderOptions4.getSetting(booleanSupplier);
        this.blockPos = (BlockPos)null;
        this.placeTarget = (PlaceTarget)null;
        this.movingObjectPosition = (MovingObjectPosition)null;
        SimulatedPlayer simulatedPlayer2 = new SimulatedPlayer();
        this.simulatedPlayer = simulatedPlayer2;
        RandomizationGenerator randomEntry2 = new RandomizationGenerator();
        this.randomEntry = randomEntry2;
        this.secondaryDoubleEntry = (MotionVectorSnapshot)null;
        this.customDoubleEntry = (SimulationBoundingBox)null;
        this.rotation = (Rotation)null;
        this.tickDelay8 = 0;
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.tickDelay42 = 0;
        this.tickDelay15 = 0;
        this.tickDelay25 = 0;
        this.reachDistance22 = 0.0;
        this.tickDelay27 = 0;
        this.visible2 = false;
        this.activeState19 = false;
        this.tickDelay17 = 0;
        this.tickDelay = 0;
        this.intaveBlockPlaceCheck = new Intave14TimeBlockPlaceCheck("Intave14SusBlockPlacementTime", null, false);
        this.motionMultiplier26 = 45.0F;
        this.tickDelay16 = 0;
        EagleSneakHelper internalManager2 = new EagleSneakHelper(this.settingCPS, this.settingDCPSMode, this.settingDCPS, this.settingAlwaysPlaceWhenPossible, this.settingOneClick, this.settingDelayClick);
        this.internalManager = internalManager2;
        this.tickDelay37 = 1;
        this.keyCode2 = 1;
        this.reachDistance21 = 0.0;
        this.tickDelay28 = 0;
        this.tickDelay29 = 10;
        this.tickDelay46 = 0;
        this.tickDelay44 = 10;
        this.reachDistance31 = 0.0;
        this.activeState24 = false;
        this.tickDelay51 = 0;
        this.tickDelay47 = 0;
        this.tickDelay48 = 0;
        this.tickDelay35 = 0;
        this.tickDelay43 = 0;
        this.reachDistance33 = 0.0;
        this.reachDistance40 = 0.0;
        this.activeState4 = false;
        this.activeState32 = false;
        this.activeState41 = false;
        this.tickDelay14 = 0;
        this.activeState12 = false;
        this.activeState36 = false;
        AnimationTimer animationTimer9 = new AnimationTimer();
        this.animationTimer = animationTimer9;
        this.animationTimer2 = new AnimationTimer();
        this.activeState2 = false;
        this.positionVec = (Vec3)null;
        this.tickDelay10 = 0;
        this.activeState13 = false;
        this.enabled2 = false;
        this.animationTimer3 = new AnimationTimer();
        this.animationTimer4 = new AnimationTimer();
        this.tickDelay30 = 1;
        this.tickDelay34 = 0;
        this.activeState3 = false;
        this.animationTimer5 = new AnimationTimer();
        this.activeState7 = false;
        this.tickDelay39 = -1;
        this.entryMap = new HashMap();
        this.tickDelay5 = 0;
        this.tickDelay50 = 0;
        this.reachDistance = 0.0;
        this.animationTimer6 = new AnimationTimer();
        this.animationTimer7 = new AnimationTimer();
        this.activeState = false;
        this.expanded2 = false;
        this.tickDelay9 = 0;
        this.animationTimer8 = new AnimationTimer();
        this.activeState11 = false;
        this.tickDelay38 = 0;
        this.tickDelay49 = 0;
        this.activeState1 = false;
        this.reachDistance52 = 0.0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(36, 20);
        this.registerEvent(7);
        this.registerEvent(40);
        this.registerEvent(24);
        this.registerEvent(20);
        this.registerEvent(21, 5);
        this.registerEvent(18);
        this.registerEvent(32);
        this.registerEvent(29);
        this.registerEvent(26);
        this.registerEvent(33);
        this.registerEvent(39);
        this.registerEvent(34);
        this.registerEvent(0);
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
        minecraft.getTimer().scaleFactor2 = 1.0F;
        this.tickDelay39 = -1;
        this.entryMap.clear();
        this.customDoubleEntry = null;
        this.secondaryDoubleEntry = null;
        this.tickDelay8 = 0;
        if (this.settingThirdPersonMode.isEnabled()) {
            if (minecraft.gameSettings != null) {
                minecraft.gameSettings.indexOffset64 = this.tickDelay38;
            }
        }
    }

    @Override
    public void onPreDisable() {
        this.blockPos = null;
        this.tickDelay = 0;
        this.tickDelay51 = 0;
        this.activeState24 = true;
        this.tickDelay47 = 0;
        this.activeState32 = false;
        this.tickDelay48 = 0;
        this.activeState41 = false;
        this.tickDelay17 = 0;
        minecraft.getTimer().scaleFactor2 = 1.0F;
        this.timer3.reset();
        this.tickDelay15 = 1000;
        this.tickDelay25 = 1000;
        this.tickDelay27 = 0;
        float value;
        if (this.motionMultiplier26 > 0.0F) {
            value = (float)this.settingStrafeDegree.recalculate();
        } else {
            value = (float)(-this.settingStrafeDegree.recalculate());
        }

        this.motionMultiplier26 = value;
        this.settingStartDelay.recalculate();
        this.settingJumpDelayAfterPlace.recalculate();
        this.settingResetGodBridgeAfterXJumps.recalculate();
        this.settingJumpAfterXBlocks.recalculate();
        this.tickDelay43 = SettingContainer.settingProvider.getInt2(5, 7);
        this.settingJumpAfterXBlocks.recalculate();
        this.settingSneakTicks.recalculate();
        byte state = (byte)(this.settingJumpOnlyWhileSpacePressed.isEnabled() ? 10000 : 0);
        this.tickDelay14 = state;
        this.tickDelay10 = 0;
        this.activeState13 = false;
        this.enabled2 = false;
        this.settingStrafeDegree.recalculate();
        this.blockPos2 = null;
        this.activeState7 = true;
        this.settingSilentStartDelay.recalculate();
        this.tickDelay5 = SettingContainer.settingProvider.getInt2(145, 255);
        this.tickDelay9 = SettingContainer.settingProvider.getInt2(1, 3);
        this.tickDelay6 = SettingContainer.settingProvider.getInt2(1, 3);
        this.reachDistance = this.getDouble5();
        this.settingStaticPitch.recalculate();
        this.settingStaticPitchDiagonal.recalculate();
        this.settingBackSwitchTickDelay.recalculate();
        this.expanded2 = false;
        this.activeState20 = true;
        this.activeState45 = false;
        this.tickDelay18 = 0;
        this.movingObjectPosition = null;
        this.settingStartAtEdgeDelay.recalculate();
        this.activeState11 = true;
        this.activeState = false;
        this.tickDelay38 = minecraft.gameSettings.indexOffset64;
        this.activeState1 = false;
    }

    @Override
    public void onMoveInput(EventMoveInput event) {
        boolean enabled;
        if (this.settingJumpSprint.isEnabled() && MoveUtil.canExecute()) {
            enabled = true;
        } else {
            enabled = false;
        }

        event.setActive(enabled);
    }

    @Override
    public void onSneak(EventSneak event) {
        if (this.settingNoSwing.isEnabled()) {
            event.setActive(true);
        }
    }

    @Override
    public void onSprint(EventSprint event) {
        if (!this.activeState20) {
            boolean completed = false;
            if ((this.settingRemoveSneakStop.isEnabled() || this.shouldPlaceBlock9()) && this.settingSneakNoBlocks.isEnabled()) {
                ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
                if (itemStack5 == null || !BlockCache.checkItemStack(itemStack5)) {
                    double offset = minecraft.thePlayer.posY - 0.5;
                    if (!minecraft.theWorld.getBlockState(new BlockPos(minecraft.thePlayer.posX, offset, minecraft.thePlayer.posZ)).getBlock().isState18()) {
                        completed = true;
                    }
                }
                if (!completed) {
                    event.setActive2(true);
                }
            }


            boolean completed2 = false;
            if (this.settingSafeWalk.isEnabled()) {
                if (!this.settingSafeWalkAir.isEnabled()) {
                    if (!minecraft.thePlayer.onGround) {
                        completed2 = true;
                    }
                }
                if (!completed2) {
                    if (!this.settingSafeWalkOnlyDiagonal.isEnabled()
                    || !SimulatedPlayer.checkCondition(ClientGlobals.rotationManager.getRotation3().yaw)) {
                        event.setActive(true);
                        event.setActive2(false);
                    }
                }
            }


            if (this.settingSafeWalkNoBlocks.isEnabled()) {
                ItemStack itemStack52 = minecraft.thePlayer.getHeldItem();
                if (!BlockCache.checkItemStack(itemStack52) && this.getIndex() < 0 && !this.activeState7) {
                    event.setActive(true);
                    event.setActive2(false);
                }
            }

            if (this.settingSneakNoBlocks.isEnabled()) {
                ItemStack itemStack53 = minecraft.thePlayer.getHeldItem();
                if (BlockCache.checkItemStack(itemStack53)) {
                    return;
                }

                if (this.getIndex() >= 0) {
                    return;
                }

                if (!this.activeState7) {
                    event.setActive2(false);
                }
            }
        }
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (!this.activeState20) {
            if (this.settingSneak.isEnabled()) {
                if (this.settingHideSneakAnimation.isEnabled()) {
                    event.processAction3(1.62F);
                }
            }
        }
    }

    @Override
    public void onRotation(EventRotation event) {
        if (!this.activeState20) {
            if (this.settingMoveFix.getSelected().equals("None")) {
                event.setScaleFactor(MoveUtil.getFloat2());
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if (this.settingIntave.isEnabled()) {
            if (this.settingIntaveSafe.isEnabled()) {
                if (this.activeState20) {
                    this.intaveBlockPlaceCheck.handlePacket(packet.getPacket());
                }
            }
        }
    }

    @Override
    public void onPostSprint(EventSprint event) {
        this.activeState32 = event.canExecute();
    }

    @Override
    public void onClick(EventClick event) {
        this.animationTimer3.processAction();
        this.animationTimer4.processAction();
        this.animationTimer7.processAction();
        if (!this.activeState20) {
            event.cancel();
            event.setState2(true);
            this.reachDistance52 = 0.0;
            RotationManager rotationManager = ClientGlobals.rotationManager;
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            double offset = (double)Math.abs(rotationManager.getRotation2().yaw - rotationManager2.getRotation3().yaw);
            RotationManager rotationManager3 = ClientGlobals.rotationManager;
            RotationManager rotationManager4 = ClientGlobals.rotationManager;
            double offset2 = (double)Math.abs(rotationManager3.getRotation2().pitch - rotationManager4.getRotation3().pitch);
            this.animationTimer8.processAction();
            boolean enabled = offset2 > 2.0 || offset > 2.0;
            if (enabled) {
                this.animationTimer8.processAction3();
            }

            boolean enabled3 = false;
            boolean completed = false;
            if (!((!enabled) && ((this.animationTimer8).checkCondition((this.settingStartAtEdgeDelay.getIndexOffset2()))))) {
                if (this.settingStartAtEdge.isEnabled()
                && !this.activeState45
                && (!this.checkCondition16(true) || !this.settingIgnoreStartAtEdgeAtBlockClutch.isEnabled())) {
                    enabled3 = false;
                    completed = true;
                }
            }
            if (!completed) {
                enabled3 = true;
            }


            double offset3 = Math.abs(offset);
            if (!((!(offset3 > this.settingMaxYawRotationDeltaToPlaceBlocks.getDouble2(true))) && (!((Math.abs(offset2)) > (this.settingMaxPitchRotationDeltaToPlaceBlocks).getDouble2(true))))) {
                if (!this.settingRemoveRotationDeltaLimitAtBlockClutch.isEnabled() || !this.shouldPlaceBlock()) {
                    enabled3 = false;
                }
            }

            this.tickDelay49++;
            if (this.settingStrictYaw.isEnabled()
            && this.activeState
            && (!this.settingIgnoreStartDelayAfterDirectionChangeAtBlockClutch.isEnabled() || !this.shouldPlaceBlock())
            && this.activeState45) {
                if (enabled) {
                    this.animationTimer7.processAction3();
                }

                AnimationTimer animationTimer72 = this.animationTimer7;
                int indexOffset22 = this.settingStartDelayAfterDirectionChange.getIndexOffset2();
                if (!animationTimer72.checkCondition(indexOffset22)) {
                    enabled3 = false;
                    this.tickDelay49 = 0;
                } else {
                    this.tickDelay6 = SettingContainer.settingProvider.getInt2(1, 3);
                    this.settingStartDelayAfterDirectionChange.recalculate();
                    this.activeState = false;
                }
            }

            if (enabled3 && this.blockPos != null && this.shouldPlaceBlock12()) {
                if (minecraft.playerController.isState() && !ClientGlobals.moduleManager.fixes.canExecute5()) {
                    return;
                }

                ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
                if (!BlockCache.checkItemStack(itemStack5) && !this.settingSilentMode.getSelected().equals("Exploit")) {
                    return;
                }

                if (this.settingExpand.isEnabled() && !this.shouldPlaceBlock3()) {
                    Vec3 hitVec = this.placeTarget.getHitVec();
                    EnumFacing facing = this.placeTarget.getFacing();
                    PlaceTarget placeTarget2 = this.placeTarget;
                    MovingObjectPosition movingObjectPosition2 = new MovingObjectPosition(hitVec, facing, placeTarget2.getBlockPos());
                    if (this.settingClickPause.isEnabled() && !this.internalManager.canExecute()) {
                        return;
                    }

                    EagleSneakHelper internalManager2 = this.internalManager;
                    internalManager2.applyMovingObjectPosition3(movingObjectPosition2, event, () -> this.checkMovingObjectPosition(movingObjectPosition2));
                    return;
                }

                if (minecraft.objectMouseOver == null) {
                    return;
                }

                if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                    if (minecraft.theWorld.getBlockState(minecraft.objectMouseOver.getBlockPos()).getBlock().getMaterial() != Material.coreItemEntry
                    && (!this.settingGodBridge.isEnabled() || !this.activeState24 || this.activeState41)) {
                        BlockPos blockPos3 = minecraft.objectMouseOver.getBlockPos();
                        EnumFacing sideHit = minecraft.objectMouseOver.sideHit;
                        if (sideHit == EnumFacing.UP) {
                            ItemStack itemStack52 = minecraft.thePlayer.getHeldItem();
                            boolean enabled4 = CombatUtil.checkItemStack2(itemStack52);
                            if (this.settingSameY.isEnabled() && enabled4 && !this.activeState2 && !this.shouldPlaceBlock3()) {
                                return;
                            }

                            if (blockPos3.checkBlockPos2(this.blockPos)
                            && this.shouldPlaceBlock11()
                            && (!this.settingHover.isEnabled() || this.enabled2)) {
                                boolean enabled5 = false;
                                boolean completed2 = false;
                                if (!(ClientGlobals.rotationManager.getRotation3().pitch < 85.0F)
                                && this.settingIntave.isEnabled()
                                && !this.settingOtherRotation.isEnabled()) {
                                    ItemStack itemStack53 = minecraft.thePlayer.getHeldItem();
                                    if (CombatUtil.checkItemStack2(itemStack53)) {
                                        enabled5 = false;
                                        completed2 = true;
                                    }
                                }
                                if (!completed2) {
                                    enabled5 = true;
                                }


                                double offset4;
                                if (this.settingIntave.isEnabled() && this.settingIntaveSafe.isEnabled()) {
                                    Intave14TimeBlockPlaceCheck settingIntave14SusBlockPlacementTime2 = this.intaveBlockPlaceCheck;
                                    BlockPos blockPos4 = blockPos3.offset(sideHit);
                                    BlockPos blockPos5 = minecraft.objectMouseOver.getBlockPos();
                                    offset4 = settingIntave14SusBlockPlacementTime2.getCoordinate(blockPos4, blockPos5);
                                } else {
                                    offset4 = 0.0;
                                }

                                this.reachDistance52 = offset4;
                                EagleSneakHelper internalManager3 = this.internalManager;
                                MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
                                boolean enabled6 = enabled5;
                                internalManager3.applyMovingObjectPosition3(objectMouseOver, event, () -> ((Scaffold)this).checkCondition(enabled6));
                            }
                        } else {
                            if (!blockPos3.checkBlockPos2(this.blockPos)) {
                                if (!minecraft.theWorld
                                .getList2(
                                minecraft.thePlayer,
                                minecraft.thePlayer.getBoundingBox2().expand(0.0, -0.5, 0.0).offset(-0.001, 0.0, -0.001)
                                )
                                .isEmpty()
                                || !minecraft.thePlayer.onGround) {
                                    RotationManager rotationManager5 = ClientGlobals.rotationManager;
                                    RotationManager rotationManager6 = ClientGlobals.rotationManager;
                                    if (rotationManager5.getRotation2().pitch != rotationManager6.getRotation3().pitch) {
                                        return;
                                    }
                                }
                            }

                            if (this.shouldPlaceBlock11() && this.shouldPlaceBlock5()) {
                                double offset5;
                                if (this.settingIntave.isEnabled() && this.settingIntaveSafe.isEnabled()) {
                                    Intave14TimeBlockPlaceCheck settingIntave14SusBlockPlacementTime3 = this.intaveBlockPlaceCheck;
                                    BlockPos blockPos6 = minecraft.objectMouseOver.getBlockPos();
                                    BlockPos blockPos7 = blockPos6.offset(minecraft.objectMouseOver.sideHit);
                                    BlockPos blockPos8 = minecraft.objectMouseOver.getBlockPos();
                                    offset5 = settingIntave14SusBlockPlacementTime3.getCoordinate(blockPos7, blockPos8);
                                } else {
                                    offset5 = 0.0;
                                }

                                boolean enabled7 = false;
                                boolean completed3 = false;
                                this.reachDistance52 = offset5;
                                if (!(ClientGlobals.rotationManager.getRotation3().pitch < 85.0F)
                                && this.settingIntave.isEnabled()
                                && !this.settingOtherRotation.isEnabled()) {
                                    ItemStack itemStack54 = minecraft.thePlayer.getHeldItem();
                                    if (CombatUtil.checkItemStack2(itemStack54)) {
                                        enabled7 = false;
                                        completed3 = true;
                                    }
                                }
                                if (!completed3) {
                                    enabled7 = true;
                                }


                                EagleSneakHelper internalManager4 = this.internalManager;
                                MovingObjectPosition objectMouseOver2 = minecraft.objectMouseOver;
                                boolean enabled8 = enabled7;
                                BooleanCondition standardListener3 = () -> this.checkCondition2(enabled8);
                                boolean enabled9 = this.checkCondition();
                                internalManager4.applyMovingObjectPosition(objectMouseOver2, event, standardListener3, enabled9);
                            }
                        }
                    }
                }

                return;
            }
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (!this.activeState20) {
            if (this.settingMoveFix.getSelected().equals("None")) {
                event.setScaleFactor(MoveUtil.getFloat2());
            }

            if (this.settingIntave.isEnabled()) {
                if (this.settingIntaveSafe.isEnabled()) {
                    this.intaveBlockPlaceCheck.updateState();
                }
            }
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
        if (this.settingBlockCount.isEnabled()) {
            this.placeScaffoldBlock4();
        }

        RenderUtils.processAction();
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        if (!this.activeState20) {
            if (!ClientGlobals.moduleManager.timer.isToggled()
            || ClientGlobals.moduleManager.timer.settingTime.getValue() == 1.0) {
                if (this.settingTimerDrop.isEnabled()) {
                    if (!minecraft.gameSettings.keyBindJump.pressed) {
                        if (minecraft.thePlayer.ticksExisted % 3 == 0) {
                            minecraft.getTimer().scaleFactor2 = (float)MathHelper.getDouble7(this.settingTimerSpeed.getDouble3(), 0.1F, 100.0);
                        } else {
                            minecraft.getTimer().scaleFactor2 = (float)MathHelper.getDouble7(this.settingTimerSpeed.getDouble6(), 0.1F, 100.0);
                        }
                    }
                } else if (!minecraft.gameSettings.keyBindJump.pressed) {
                    minecraft.getTimer().scaleFactor2 = (float)this.settingTimer.getValue();
                }
            }

            if (this.settingHover.isEnabled()) {
                if (this.activeState13) {
                    if (minecraft.thePlayer.ticksExisted % 2 == 0) {
                        if (minecraft.thePlayer.onGround) {
                            if (event.getCoordOffset1() % 1.0 == 0.0) {
                                this.enabled2 = true;
                                double offset = Math.random();
                                double offset2 = offset * 1.0E-4;
                                double offset3 = event.getCoordOffset1() + offset2;
                                event.setCoordOffset1(offset3);
                                event.setState(false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onUpdateState(EventStateUpdate event) {
        if (this.settingThirdPersonMode.isEnabled()) {
            minecraft.gameSettings.indexOffset64 = 1;
        }

        EntityPlayerSP player = null;
        boolean completed6 = false;
        EntityPlayerSP player2 = null;
        boolean completed5 = false;
        if (!this.activeState20) {
            if (!this.shouldPlaceBlock12()) {
                return;
            }
            if (this.blockPos == null) {
                return;
            }
            if ((!this.settingTowerWhileMoving.isEnabled() || !minecraft.gameSettings.keyBindJump.isKeyDown())
            && !this.shouldPlaceBlock3()) {
                return;
            }
            ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
            if (BlockCache.checkItemStack(itemStack5)) {
                EntityPlayerSP player3 = null;
                boolean completed4 = false;
                int index = 0;
                double offset = 0.0;
                boolean completed3 = false;
                int index2 = 0;
                boolean completed2 = false;
                byte state = (byte)0;
                String selected = this.settingTowerMode.getSelected();
switch (selected) {
            case "Intave1":
                {
                                boolean onGround = minecraft.thePlayer.onGround;
                                if (onGround) {
                                    minecraft.thePlayer.motionY = 0.405;
                                }
                                if (this.tickDelay27 != 5) {
                                    return;
                                }
                                player3 = minecraft.thePlayer;
                                { completed4 = true; break; }
                            }
            case "Intave2":
                if (minecraft.thePlayer.onGround) {
                                minecraft.thePlayer.motionY = 0.405;
                            }
                if (this.tickDelay27 == 5) {
                                float float3 = SettingContainer.settingProvider.getFloat3(0.9908900590734863, 0.9909900590734863);
                                minecraft.thePlayer.motionY = (minecraft.thePlayer.motionY - 0.08) * (double)float3;
                            }
                return;
            case "NCP":
                {
                                if (!minecraft.thePlayer.onGround) {
                                    if (minecraft.thePlayer.posY > this.reachDistance23 + 1.0) {
                                        minecraft.thePlayer.motionY = -0.1;
                                    }

                                    return;
                                }
                                player2 = minecraft.thePlayer;
                                { completed5 = true; break; }
                            }
            case "Verus":
                if (minecraft.thePlayer.ticksExisted % 2 != 0) {
                                return;
                            }
                minecraft.thePlayer.closeScreen();
                return;
            case "ConstantMotion":
                {
                                player = minecraft.thePlayer;
                                { completed6 = true; break; }
                            }
            case "Jump":
                if (minecraft.thePlayer.onGround) {
                                minecraft.thePlayer.motionY = this.settingJumpMotion.getValue();
                            }
                return;
            case "NCPFast":
                {
                                index2 = this.tickDelay27;
                                { completed2 = true; break; }
                            }
            case "Watchdog":
                {
                                if (!minecraft.gameSettings.keyBindForward.pressed) {
                                    MoveUtil.processFloat(0.0F);
                                    this.tickDelay10 = 0;
                                    return;
                                }
                                this.tickDelay10++;
                                if (minecraft.thePlayer.onGround) {
                                    this.tickDelay10 = 0;
                                }
                                minecraft.thePlayer.motionY = 0.41965;
                                MoveUtil.processFloat2(0.241F, false);
                                offset = minecraft.thePlayer.posY;
                                index = this.tickDelay10;
                                { completed3 = true; break; }
                            }
            default:
                return;
        }
                if (!completed6) {
                    if (!completed5) {
                        if (!completed4) {
                            if (!completed3) {
                                if (!completed2) {
                                    switch (state) {
                                        case 0:
                                            {
                                                boolean onGround2 = minecraft.thePlayer.onGround;
                                                if (onGround2) {
                                                    minecraft.thePlayer.motionY = 0.405;
                                                }
                                                if (this.tickDelay27 != 5) {
                                                    return;
                                                }
                                                player3 = minecraft.thePlayer;
                                                { completed4 = true; break; }
                                            }
                                        case 1:
                                            if (minecraft.thePlayer.onGround) {
                                                minecraft.thePlayer.motionY = 0.405;
                                            }

                                            if (this.tickDelay27 == 5) {
                                                float float32 = SettingContainer.settingProvider.getFloat3(0.9908900590734863, 0.9909900590734863);
                                                minecraft.thePlayer.motionY = (minecraft.thePlayer.motionY - 0.08) * (double)float32;
                                            }

                                            return;
                                        case 2:
                                            {
                                                if (!minecraft.thePlayer.onGround) {
                                                    if (minecraft.thePlayer.posY > this.reachDistance23 + 1.0) {
                                                        minecraft.thePlayer.motionY = -0.1;
                                                    }

                                                    return;
                                                }
                                                player2 = minecraft.thePlayer;
                                                { completed5 = true; break; }
                                            }
                                        case 3:
                                            if (minecraft.thePlayer.ticksExisted % 2 != 0) {
                                                return;
                                            }

                                            minecraft.thePlayer.closeScreen();
                                            return;
                                        case 4:
                                            {
                                                player = minecraft.thePlayer;
                                                { completed6 = true; break; }
                                            }
                                        case 5:
                                            if (minecraft.thePlayer.onGround) {
                                                minecraft.thePlayer.motionY = this.settingJumpMotion.getValue();
                                            }

                                            return;
                                        case 6:
                                            index2 = this.tickDelay27;
                                            break;
                                        case 7:
                                            {
                                                if (!minecraft.gameSettings.keyBindForward.pressed) {
                                                    MoveUtil.processFloat(0.0F);
                                                    this.tickDelay10 = 0;
                                                    return;
                                                }
                                                this.tickDelay10++;
                                                if (minecraft.thePlayer.onGround) {
                                                    this.tickDelay10 = 0;
                                                }
                                                minecraft.thePlayer.motionY = 0.41965;
                                                MoveUtil.processFloat2(0.241F, false);
                                                offset = minecraft.thePlayer.posY;
                                                index = this.tickDelay10;
                                                { completed3 = true; break; }
                                            }
                                        default:
                                            return;
                                    }
                                }
                                if (!completed6) {
                                    if (!completed5) {
                                        if (!completed4) {
                                            if (!completed3) {
                                                if (index2 <= 1) {
                                                    this.tickDelay27 = 0;
                                                    if (minecraft.thePlayer.ticksExisted % 3 == 0) {
                                                        minecraft.thePlayer.motionY = 0.4196;
                                                        return;
                                                    }
                                                }
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!completed6) {
                                if (!completed5) {
                                    if (!completed4) {
                                        if (index == 1) {
                                            minecraft.thePlayer.motionY = 0.33;
                                            return;
                                        }
                                        if (this.tickDelay10 == 2) {
                                            minecraft.thePlayer.motionY = 1.0 - offset % 1.0;
                                            return;
                                        }
                                        if (this.tickDelay10 >= 3) {
                                            this.tickDelay10 = 0;
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                        if (!completed6) {
                            if (!completed5) {
                                player3.motionY = (minecraft.thePlayer.motionY - 0.08) * 0.98F;
                            }
                        }
                    }
                }
            }
        }
        if (!completed6) {
            if (!completed5) {
                return;
            }
            player2.closeScreen();
            this.reachDistance23 = minecraft.thePlayer.posY;
            return;
        }


        player.motionY = this.settingConstantMotion.getValue();
    }

    @Override
    public void onStep(EventStep event) {
        if (!this.activeState20) {
            if (this.settingIgnoreSpeedMotion.isEnabled()) {
                event.processAction(MathHelper.clamp_float(event.getScaleFactor(), -0.13000001F, 0.13000001F));
            }
        }
    }

    @Override
    public void onPreMoveInput(EventMoveInput event) {
        if (!this.activeState20) {
            boolean enabled;
            if (this.settingMode.getSelected().equals("AllDirectionMotion") && MoveUtil.canExecute()) {
                enabled = true;
            } else {
                enabled = false;
            }

            event.setActive4(enabled);
        }
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (!this.activeState20) {
            double offset;
            if (minecraft.thePlayer.onGround) {
                double offset2;
                if (SimulatedPlayer.checkCondition(ClientGlobals.rotationManager.getRotation3().yaw)) {
                    offset2 = this.settingMotionMultiplierGroundForward.recalculate();
                } else {
                    offset2 = this.settingMotionMultiplierGroundStrafe.recalculate();
                }

                offset = offset2;
            } else if (SimulatedPlayer.checkCondition(ClientGlobals.rotationManager.getRotation3().yaw)) {
                offset = this.settingMotionMultiplierAirForward.recalculate();
            } else {
                offset = this.settingMotionMultiplierAirStrafe.recalculate();
            }

            double offset3 = event.getCoordOffset2() * offset;
            event.setCoordOffset2(offset3);
            double offset4 = event.getCoordOffset() * offset;
            event.setCoordOffset(offset4);
        }
    }

    @Override
    public void onMove(EventMove event) {
        this.animationTimer6.processAction();
        boolean pressed = minecraft.gameSettings.keyBindJump.pressed;
        if (!this.activeState20) {
            boolean enabled;
            if (this.settingStartAtEdge.isEnabled()) {
                enabled = this.activeState45;
            } else {
                enabled = this.settingStrictYaw.isEnabled();
            }

            boolean enabled3;
            if (enabled && (this.settingIgnoreStartDelayAfterDirectionChangeAtBlockClutch.isEnabled() ? 1 : 0) > 0) {
                enabled3 = this.activeState;
            } else {
                enabled3 = this.activeState45;
            }

            boolean enabled4;
            if (enabled3 && !this.activeState13) {
                enabled4 = minecraft.thePlayer.onGround;
            } else {
                enabled4 = this.settingReduceVelocity.isEnabled();
            }

            int index;
            if (enabled4) {
                index = minecraft.thePlayer.hurtTime;
            } else {
                float scaleFactor7 = event.getScaleFactor7();
                index = !Float.isNaN(scaleFactor7) && !Float.isNaN(0.0F) ? (scaleFactor7 > 0.0F ? 1 : (scaleFactor7 == 0.0F ? 0 : -1)) : -1;
            }

            if (index == 0) {
                this.settingGodBridge.isEnabled();
            }

            boolean enabled5;
            if (this.blockPos != null
            && this.settingTowerWhileMoving.isEnabled()
            && !this.settingTowerMode.getSelected().equals("Intave1")
            && !this.settingTowerMode.getSelected().equals("Intave2")) {
                enabled5 = this.settingTowerMode.getSelected().equals("Legit");
            } else {
                enabled5 = this.settingSneakNoBlocks.isEnabled();
            }

            boolean enabled6 = false;
            boolean completed = false;
            if (enabled5) {
                ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
                if (!BlockCache.checkItemStack(itemStack5) && this.getIndex() != 0 && minecraft.thePlayer.onGround) {
                    double posX = minecraft.thePlayer.posX;
                    double offset = minecraft.thePlayer.posY - 0.5;
                    double posZ = minecraft.thePlayer.posZ;
                    BlockPos blockPos3 = new BlockPos(posX, offset, posZ);
                    enabled6 = minecraft.theWorld.getBlockState(blockPos3).getBlock().isState18();
                    completed = true;
                }
            }
            if (!completed) {
                enabled6 = this.settingStopNoBlocks.isEnabled();
            }


            boolean enabled7 = false;
            boolean completed2 = false;
            if (enabled6) {
                ItemStack itemStack52 = minecraft.thePlayer.getHeldItem();
                if (!BlockCache.checkItemStack(itemStack52)) {
                    if (this.getIndex() < 0) {
                        enabled7 = this.activeState7;
                    } else {
                        enabled7 = this.settingSilentMode.getSelected().equals("None");
                    }
                    completed2 = true;
                }
            }
            if (!completed2) {
                enabled7 = this.settingSneak.isEnabled();
            }


            boolean enabled8;
            if (!enabled7) {
                enabled8 = SimulatedPlayer.checkCondition(this.getFloat8());
            } else {
                enabled8 = this.settingMoveFixIntave.isEnabled();
            }

            boolean enabled9;
            if (enabled8) {
                enabled9 = this.settingMoveFix.getSelected().equals("None");
            } else {
                enabled9 = this.settingSameY.isEnabled();
            }

            boolean enabled10;
            if (enabled9 && this.settingAutoJump.isEnabled()) {
                enabled10 = MoveUtil.canExecute2();
            } else {
                enabled10 = this.settingSameY.isEnabled();
            }

            boolean enabled11;
            if (enabled10 && this.settingIntave.isEnabled() && this.settingIntaveFast2.isEnabled()) {
                enabled11 = this.activeState2;
            } else {
                enabled11 = this.settingStopAtStart.isEnabled();
            }

            boolean enabled12;
            if (enabled11) {
                TimeHelper timer32 = this.timer3;
                long time2 = (long)this.tickDelay5;
                enabled12 = timer32.hasReached(time2);
            } else {
                enabled12 = this.settingJump.isEnabled();
            }

            boolean enabled13 = false;
            boolean completed3 = false;
            if (enabled12) {
                if (this.settingJumpOnlyWhileSpacePressed.isEnabled() && MoveUtil.canExecute()) {
                    event.setState(false);
                    pressed = false;
                }
                int index2 = 0;
                if (SimulatedPlayer.checkCondition(this.getFloat2())) {
                    index2 = this.settingJumpAfterXBlocksForward.getIndexOffset2();
                } else {
                    index2 = this.settingJumpAfterXBlocksDiagonal.getIndexOffset2();
                }
                if (this.tickDelay14 >= index2) {
                    enabled13 = this.settingJumpOnlyWhileSpacePressed.isEnabled();
                    completed3 = true;
                }
            }
            if (!completed3) {
                enabled13 = this.settingSneak.isEnabled();
            }


            int index3 = 0;
            boolean completed4 = false;
            if (!enabled13) {
                Potion potion2 = Potion.potion2;
                if (minecraft.thePlayer.checkPotion(potion2)) {
                    index3 = minecraft.thePlayer.ticksExisted % 2;
                    completed4 = true;
                }
            }
            if (!completed4) {
                index3 = (this.settingMoonWalk.isEnabled() ? 1 : 0);
            }


            int index4 = 0;
            if (index3 != 0
            && this.blockPos != null
            && !pressed
            && MoveUtil.canExecute()
            && SimulatedPlayer.checkCondition(ClientGlobals.rotationManager.getRotation3().yaw)) {
                float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
                EnumFacing enumFacing2 = minecraft.thePlayer.getEnumFacing2(yaw);
                AxisAlignedBB axisAlignedBB6 = minecraft.thePlayer.getBoundingBox2().expand(0.0, -1.0, 0.0);
                Iterator iterator = minecraft.theWorld.getList2(minecraft.thePlayer, axisAlignedBB6).iterator();
                index4 = 0;

                while (iterator.hasNext()) {
                    double offset2 = 0.0;
                    AxisAlignedBB boundingBox = null;
                    AxisAlignedBB boundingBox2 = null;
                    boolean completed5 = false;
                    boundingBox = (AxisAlignedBB)iterator.next();
                    boundingBox2 = minecraft.thePlayer.getBoundingBox2().offset(-0.3, 0.0, -0.3).expand(0.0, -1.0, 0.0);
                    EnumFacing enumFacing = EnumFacing.EAST;
                    if (enumFacing2 != enumFacing) {
                        EnumFacing enumFacing3 = EnumFacing.WEST;
                        if (enumFacing2 != enumFacing3) {
                            offset2 = 0.0;
                            completed5 = true;
                        }
                    }
                    if (!completed5) {
                        offset2 = this.reachDistance21;
                    }


                    double offset3 = 0.0;
                    double offset4 = 0.0;
                    boolean completed6 = false;
                    EnumFacing enumFacing4 = EnumFacing.NORTH;
                    offset3 = 0.0;
                    if (enumFacing2 != enumFacing4) {
                        EnumFacing enumFacing5 = EnumFacing.SOUTH;
                        if (enumFacing2 != enumFacing5) {
                            offset4 = 0.0;
                            completed6 = true;
                        }
                    }
                    if (!completed6) {
                        offset4 = this.reachDistance21;
                    }


                    AxisAlignedBB axisAlignedBB = boundingBox.offset(offset2, offset3, offset4);
                    if (boundingBox2.intersectsWith(axisAlignedBB)) {
                        index4 = 1;
                    }
                }

                this.settingMoonWalkMiddle.getDouble3();
                this.settingMoonWalkWideness.getDouble3();
            } else {
                boolean completed8 = false;
                if (this.settingStrafe.isEnabled()
                && SimulatedPlayer.checkCondition(this.getFloat8())
                && this.blockPos != null
                && this.settingCorrectStrafeWithKeyBinds.isEnabled()) {
                    double double3 = this.settingCorrectStrafeWithKeyBindsWidth.getDouble3();
                    float motionMultiplier262 = this.motionMultiplier26;
                    this.settingCorrectStrafeWithKeyBindsCage.isEnabled();
                    float value = Math.abs(this.motionMultiplier26);
                    float value2 = 0.0F;
                    if ((!Float.isNaN(value) && !Float.isNaN(45.0F) ? (value > 45.0F ? 1 : (value == 45.0F ? 0 : -1)) : -1) > 0) {
                        value2 = -motionMultiplier262;
                    } else {
                        value2 = Math.abs(this.motionMultiplier26);
                    }
                    int index5 = 0;
                    boolean completed7 = false;
                    double offset5 = (double)value2;
                    double offset6 = this.reachDistance - double3;
                    float value3 = this.getFloat2() - 180.0F;
                    EnumFacing enumFacing22 = minecraft.thePlayer.getEnumFacing2(value3);
                    if ((enumFacing22) == EnumFacing.EAST) {
                        double posZ2 = minecraft.thePlayer.posZ;
                        double offset7 = (double)this.blockPos.getZ() + offset5;
                        int index6;
                        if ((!Double.isNaN(posZ2) && !Double.isNaN(offset7) ? (posZ2 > offset7 ? 1 : (posZ2 == offset7 ? 0 : -1)) : -1) > 0) {
                            index6 = !Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : -1;
                        } else {
                            double posZ3 = minecraft.thePlayer.posZ;
                            double offset8 = (double)this.blockPos.getZ() + offset6;
                            index6 = !Double.isNaN(posZ3) && !Double.isNaN(offset8) ? (posZ3 > offset8 ? 1 : (posZ3 == offset8 ? 0 : -1)) : 1;
                        }

                        if (index6 < 0 && (!Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : 1) != 0) {
                            this.animationTimer3.processAction3();
                        }
                    } else if ((enumFacing22) == EnumFacing.WEST) {
                        double posZ4 = minecraft.thePlayer.posZ;
                        double offset9 = (double)this.blockPos.getZ() + offset5;
                        int index7 = 0;
                        if ((!Double.isNaN(posZ4) && !Double.isNaN(offset9) ? (posZ4 > offset9 ? 1 : (posZ4 == offset9 ? 0 : -1)) : -1) > 0) {
                            index7 = !Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : 1;
                        } else {
                            double posZ5 = minecraft.thePlayer.posZ;
                            double offset10 = (double)this.blockPos.getZ() + offset6;
                            index7 = !Double.isNaN(posZ5) && !Double.isNaN(offset10) ? (posZ5 > offset10 ? 1 : (posZ5 == offset10 ? 0 : -1)) : 1;
                        }
                        if (index7 < 0) {
                            index5 = !Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : -1;
                            completed7 = true;
                        }
                    } else if ((enumFacing22) == EnumFacing.SOUTH) {
                        double posX2 = minecraft.thePlayer.posX;
                        double offset11 = (double)this.blockPos.getX() + offset5;
                        int index8;
                        if ((!Double.isNaN(posX2) && !Double.isNaN(offset11) ? (posX2 > offset11 ? 1 : (posX2 == offset11 ? 0 : -1)) : -1) > 0) {
                            index8 = !Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : 1;
                        } else {
                            double posX3 = minecraft.thePlayer.posX;
                            double offset12 = (double)this.blockPos.getX() + offset6;
                            index8 = !Double.isNaN(posX3) && !Double.isNaN(offset12) ? (posX3 > offset12 ? 1 : (posX3 == offset12 ? 0 : -1)) : 1;
                        }

                        if (index8 < 0 && (!Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : -1) != 0) {
                            this.animationTimer3.processAction3();
                        }
                    } else if ((enumFacing22) == EnumFacing.NORTH) {
                        double posX4 = minecraft.thePlayer.posX;
                        double offset13 = (double)this.blockPos.getX() + offset5;
                        int index9;
                        if ((!Double.isNaN(posX4) && !Double.isNaN(offset13) ? (posX4 > offset13 ? 1 : (posX4 == offset13 ? 0 : -1)) : -1) > 0) {
                            index9 = !Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : -1;
                        } else {
                            double posX5 = minecraft.thePlayer.posX;
                            double offset14 = (double)this.blockPos.getX() + offset6;
                            index9 = !Double.isNaN(posX5) && !Double.isNaN(offset14) ? (posX5 > offset14 ? 1 : (posX5 == offset14 ? 0 : -1)) : 1;
                        }

                        if (index9 < 0 && (!Float.isNaN(motionMultiplier262) && !Float.isNaN(0.0F) ? (motionMultiplier262 > 0.0F ? 1 : (motionMultiplier262 == 0.0F ? 0 : -1)) : 1) != 0) {
                            this.animationTimer3.processAction3();
                        }
                    }
                    if (!completed7) {
                        AnimationTimer animationTimer32 = this.animationTimer3;
                        int indexOffset2 = this.settingCorrectStrafeWithKeyBindsDuration.getIndexOffset2();
                        index5 = (animationTimer32.checkCondition(indexOffset2) ? 1 : 0);
                    }
                    if (index5 != 0) {
                        this.activeState3 = false;
                        this.settingCorrectStrafeWithKeyBindsWidth.recalculate();
                        this.settingCorrectStrafeWithKeyBindsDuration.recalculate();
                        AnimationTimer animationTimer42 = this.animationTimer4;
                        int indexOffset22 = this.settingCorrectStrafeWithKeyBindsDelayToRecalculateMiddle.getIndexOffset2();
                        index4 = (animationTimer42.checkCondition(indexOffset22) ? 1 : 0);
                        completed8 = true;
                    }
                }
                if (!completed8) {
                    double reachDistance522 = this.reachDistance52;
                    index4 = !Double.isNaN(reachDistance522) && !Double.isNaN(0.0) ? (reachDistance522 > 0.0 ? 1 : (reachDistance522 == 0.0 ? 0 : -1)) : -1;
                }

            }

            if (index4 != 0 && this.settingIntaveSafe.isEnabled()) {
                Intave14TimeBlockPlaceCheck settingIntave14SusBlockPlacementTime2 = this.intaveBlockPlaceCheck;
                EventMove event2 = event;
                settingIntave14SusBlockPlacementTime2.onMove(event2);
            }

            if (this.settingGodBridge.isEnabled()) {
                EventMove event3 = event;
                this.onMove3(event3);
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.settingESP.isEnabled()) {
            RenderUtils.updateState();
            int int12 = this.settingColor.getInt12(0);
            if (this.blockPos != null) {
                GL11.glAlphaFunc(516, 0.0F);
                MovementHandler.processBoolean(this.settingESP2.canExecute(), this.settingESP2.canExecute2());
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(int12, 0.0F, renderPass));
            }

            RenderUtils.prepareScissor();
        }
    }

    @Override
    public void onPostMoveInput(EventMoveInput event) {
        boolean enabled = !this.settingMode.getSelected().equals("None");
        event.setState(enabled);
        boolean enabled3;
        if (this.settingMode.getSelected().equals("AllDirection") && MoveUtil.canExecute()) {
            enabled3 = true;
        } else {
            enabled3 = false;
        }

        event.setActive5(enabled3);
    }

    @Override
    public void onPostMotion() {
        this.animationTimer5.processAction();
        if (this.settingSilentMode.getSelected().equals("None")) {
            this.tickDelay39 = 0;
        } else {
            this.placeScaffoldBlock7();
            int index;
            if (this.settingSilentMode.getSelected().equals("Exploit")) {
                index = this.getInt5();
            } else {
                index = this.getIndex();
            }

            if (index < 9 && index >= 0) {
                int index2 = 1;
                if (this.settingSilentMode.getSelected().equals("Default")) {
                    index2 = this.settingBackSwitchTickDelay.getIndexOffset2() + 1;
                    if (this.settingDynamicBackSwitchTickDelay.isEnabled() && ChatUtil.getEntityLivingBase2((EntityLivingBase)null) != null) {
                        index2 = Math.max(1, index2 / 2);
                    }
                }

                boolean enabled = !this.settingSilentMode.getSelected().equals("Legit");
                SlotManager.applyModule2(this, -1, index, index2, enabled);
            }
        }
    }

    @Override
    public void onPostRenderTick() {
        if (!this.activeState20) {
            if (this.movingObjectPosition != null) {
                minecraft.objectMouseOver = this.movingObjectPosition;
                this.movingObjectPosition = null;
            }
        }
    }

    @Override
    public void onPostUpdateState() {
        if (!this.activeState20) {
            if (this.settingSameY.isEnabled()) {
                if (this.settingIntaveFast2.isEnabled()) {
                    if (this.settingIntave.isEnabled()) {
                        if (!this.activeState2) {
                            if (MoveUtil.canExecute()) {
                                if (this.tickDelay16 > 0) {
                                    if (minecraft.thePlayer.hurtTime <= 0) {
                                        if (!minecraft.thePlayer.isRiding()) {
                                            if (this.timer3.hasReached(400L)) {
                                                if (minecraft.thePlayer.onGround) {
                                                    minecraft.thePlayer.closeScreen();
                                                } else {
                                                    if (minecraft.thePlayer.motionY > 0.0) {
                                                        MoveUtil.processFloat2(0.28F, false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        if (minecraft.thePlayer.onGround) {
            this.activeState2 = minecraft.gameSettings.keyBindUseItem.pressed;
        }

        if (this.settingEarlySneakCalculation.isEnabled()) {
            this.activeState19 = false;
            this.tickDelay16 = this.getInt3();
            List collidingBoundingBoxes = minecraft.theWorld.getCollidingBoundingBoxes(minecraft.thePlayer, minecraft.thePlayer.getBoundingBox2().expand(0.0, -1.0, 0.0));
            boolean enabled = Math.abs(minecraft.thePlayer.motionX) > 0.02;
            boolean enabled3 = Math.abs(minecraft.thePlayer.motionZ) > 0.02;

            for (AxisAlignedBB boundingBox : (Iterable<AxisAlignedBB>) collidingBoundingBoxes) {
                AxisAlignedBB boundingBox2 = minecraft.thePlayer.getBoundingBox2();
                double offset = -Math.abs(minecraft.thePlayer.getBoundingBox2().minX - minecraft.thePlayer.getBoundingBox2().maxX) / 2.0;
                if (boundingBox2.offset(offset, 0.0, -Math.abs(minecraft.thePlayer.getBoundingBox2().minZ - minecraft.thePlayer.getBoundingBox2().maxZ) / 2.0)
                .expand(0.0, -1.0, 0.0)
                .intersectsWith(boundingBox.offset(enabled ? this.reachDistance22 : 0.0, 0.0, enabled3 ? this.reachDistance22 : 0.0))) {
                    this.activeState19 = true;
                    break;
                }
            }
        }

        if (minecraft.thePlayer.onGround) {
            this.tickDelay27 = 0;
        } else {
            this.tickDelay27++;
        }

        if (this.shouldPlaceBlock12()) {
            this.placeTarget = this.getPlaceTarget();
            BlockPos blockPos3 = this.blockPos;
            if (this.placeTarget != null) {
                PlaceTarget placeTarget2 = this.placeTarget;
                this.blockPos = placeTarget2.getBlockPos();
            } else {
                this.blockPos = null;
            }

            if (!((this.settingStartAtEdge.isEnabled()) && (!(minecraft).theWorld.getIBlockState2((minecraft).thePlayer.posX, (minecraft.thePlayer.posY - 0.5), (minecraft).thePlayer.posZ).getBlock().getMaterial().canExecute()
            && !this.checkCondition16(true)))) {
                this.activeState20 = false;
            }

            if (this.blockPos2 == null) {
                this.blockPos2 = this.blockPos;
            }

            if (this.blockPos != null && blockPos3 != null && !this.blockPos.checkBlockPos2(blockPos3)) {
                this.blockPos2 = blockPos3;
            }

            this.movingObjectPosition = null;
            if (!this.activeState20) {
                if (this.settingExpand.isEnabled()) {
                    this.placeScaffoldBlock5();
                } else {
                    this.placeScaffoldBlock6();
                }
            }
        }

        this.animationTimer.processAction();
        this.animationTimer2.processAction();
    }

    public float getFloat(boolean enabled) {
        double offset = Math.random() * 100.0;
        double offset2;
        if (enabled) {
            offset2 = this.settingRandomizePitchChanceWhileYawRotation.getValue();
        } else {
            offset2 = this.getDouble3();
        }

        if (offset > offset2) {
            return 0.0F;
        } else {
            float value;
            if (this.settingOtherYawRandomization.isEnabled()) {
                double offset3 = (double)minecraft.thePlayer.ticksExisted * 0.75;
                double offset4 = -0.2;
                value = (float)Math.sin(offset3 + (double)SettingContainer.settingProvider.getFloat3(offset4, 0.2) + 1000.0) * 0.75F;
            } else {
                double offset5 = -this.settingRandomizePitchVal.getDouble3();
                double offset6 = this.settingRandomizePitchVal.recalculate();
                value = SettingContainer.settingProvider.getFloat3(offset5, offset6);
            }

            AnimationTimer animationTimer22 = this.animationTimer2;
            int indexOffset2 = this.settingRandomizePitchInterval.getIndexOffset2();
            if (!animationTimer22.checkCondition(indexOffset2)) {
                return value;
            } else {
                this.animationTimer2.processAction3();
                this.settingRandomizePitchInterval.recalculate();
                this.settingRandomizePitchVal.recalculate();
                this.activeState36 = false;
                return value;
            }
        }
    }

    private float getFloat2() {
        float float6 = this.getFloat6(Client.getClient().getViaProtocolHelper().scaleFactor1);
        float float9 = this.getFloat9(float6, true);
        if (this.shouldPlaceBlock7() && MoveUtil.canExecute2()) {
            float yaw = SimulatedPlayer.getRotation2(
            minecraft.thePlayer.posX + minecraft.thePlayer.motionX,
            minecraft.thePlayer.posY,
            
            minecraft.thePlayer.posZ + minecraft.thePlayer.motionZ,
            ClientGlobals.rotationManager.getRotation3(),
            180.0F,
            180.0F,
            false
            )
            .yaw;
            if (Math.abs(
            HitBoxUtils.getDouble(
            (double)MathHelper.sqrt_float(yaw), (double)MathHelper.sqrt_float(float9)
            )
            ) > 12.0
            && !this.settingMoonWalk.isEnabled()
            && minecraft.thePlayer.hurtResistantTime != 0
            || minecraft.thePlayer.hurtTime != 0) {
                float9 = yaw;
            }
        }

        if (this.settingAutoJump.isEnabled() && this.settingSameY.isEnabled() && this.settingTellyBridge.isEnabled()
        ? !(minecraft.thePlayer.motionY >= 0.0) && !minecraft.thePlayer.onGround && MoveUtil.canExecute2()
        : !this.settingFlick.isEnabled() || (this.settingOtherFlick.isEnabled() ? !minecraft.thePlayer.onGround : this.shouldPlaceBlock2())) {
            if ((
            minecraft.thePlayer.indexOffset41 == 1
            || CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer)[9] == 1.0
            )
            && this.settingOtherFlick.isEnabled()
            && this.settingFlick.isEnabled()) {
                float9 -= 160.0F;
            } else {
                float9 -= 180.0F;
            }
        }

        if (this.settingReduceYawRotation.isEnabled() && MoveUtil.canExecute() && this.timer3.hasReached(500L)) {
            if (Math.abs(this.motionMultiplier - float9) < 25.0F) {
                return this.motionMultiplier;
            } else {
                this.motionMultiplier = float9;
                return float9;
            }
        } else {
            this.motionMultiplier = float9;
            return float9;
        }
    }

    private static double getCoordinate(PlaceTarget placeTarget2) {
        double offset = (double)placeTarget2.getBlockPos().getX() + 0.5;
        double offset2 = (double)placeTarget2.getBlockPos().getY() + 0.5;
        return minecraft.thePlayer.getDouble4(offset, offset2, (double)placeTarget2.getBlockPos().getZ() + 0.5);
    }

    private void applyRenderPass(int index2, float partialTicks, RenderPass renderPass) {
        BlockPos blockPos3 = this.blockPos;
        int index = RandomUtil.getIndex(renderPass, index2);
        RenderUtils.applyBlockPos(blockPos3, this.settingESP2.getIndex(renderPass, index), 1.0F, partialTicks);
    }

    private RangeSetting getRangeSetting() {
        return this.shouldPlaceBlock() && this.settingCustomBlockClutchRotationSpeeds.isEnabled()
        ? this.settingBlockClutchPitchAcceleration
        : this.settingPitchAcceleration;
    }

    private void placeScaffoldBlock7() {
        int index2 = -1;
        int index3 = -1;

        for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack != null && itemStack.getItem() instanceof ItemBlock && BlockCache.checkItemStack(itemStack)) {
                int index = i - 36;
                boolean enabled;
                if (this.entryMap.containsKey(index)) {
                    enabled = itemStack.animationsToGo > (Integer)this.entryMap.get(index);
                } else {
                    enabled = itemStack.animationsToGo > this.settingStackCountToStartSwitchProcess.getIndexOffset2();
                }

                if (enabled) {
                    if (!this.settingUseBiggestStackSilent.isEnabled()) {
                        index3 = index;
                        break;
                    }

                    if (itemStack.animationsToGo > index2) {
                        index2 = itemStack.animationsToGo;
                        index3 = index;
                    }
                }
            }
        }

        if (this.settingSilentMode.getSelected().equals("Exploit")) {
            this.tickDelay39 = index3;
            this.activeState7 = false;
        } else if (this.tickDelay39 == index3) {
            this.animationTimer5.processAction3();
        } else if (index3 < 0) {
            this.tickDelay39 = index3;
        } else {
            int index4 = this.activeState7 ? this.settingSilentStartDelay.getIndexOffset2() : this.settingSilentSwitchDelay.getIndexOffset2();
            if (this.animationTimer5.checkCondition(index4 + 1)) {
                this.entryMap.put(index3, this.settingStackCountToStartSwitchProcess.getIndexOffset2());
                this.tickDelay39 = index3;
                this.activeState7 = false;
                this.settingSilentSwitchDelay.recalculate();
                this.settingStackCountToStartSwitchProcess.recalculate();
            }
        }
    }

    private void onMove2(EventMove event) {
        if (event.getScaleFactor7() != 0.0F) {
            int index3 = event.getScaleFactor7() > 0.0F ? 1 : -1;
            this.tickDelay37 = index3;
        } else if (event.getScaleFactor2() != 0.0F) {
            int index4 = event.getScaleFactor2() > 0.0F ? -1 : 1;
            this.tickDelay37 = index4;
        }

        if (this.tickDelay50 == 2) {
            this.settingMoonWalkWideness.recalculate();
            AnimationTimer animationTimer62 = this.animationTimer6;
            int indexOffset2 = this.settingDelayToRecalculateMiddle.getIndexOffset2();
            if (animationTimer62.checkCondition(indexOffset2)) {
                this.settingMoonWalkMiddle.recalculate();
                this.settingDelayToRecalculateMiddle.recalculate();
                this.animationTimer6.processAction3();
            }
        }

        this.tickDelay50 = 1;
    }

    private boolean checkCondition16(boolean enabled) {
        if (minecraft.thePlayer.onGround) {
            return false;
        } else if (minecraft.thePlayer.fallDistance > 3.0F) {
            return true;
        } else if (minecraft.thePlayer.hurtTime != 0) {
            return true;
        } else {
            return minecraft.thePlayer.posY >= minecraft.thePlayer.coordOffset9 + (enabled ? 0.5 : 0.9)
            ? false
            : minecraft.thePlayer.motionY < 0.0;
        }
    }

    private void applyMovingObjectPosition(float value, float value2, MovingObjectPosition hitResult, boolean enabled) {
        float value3 = value;
        SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
        Rotation rotation2 = new Rotation(value, value2);
        Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
        simulatedPlayer2.processAction(rotation2, rotation3);
        this.movingObjectPosition = hitResult;
        if (this.settingRandomizeYaw.isEnabled() & true && this.settingRandomizeYawChanceWhilePitchRotation.getValue() > 0.0) {
            RotationManager rotationManager = ClientGlobals.rotationManager;
            if (value2 != rotationManager.getRotation3().pitch && !enabled) {
                RotationManager rotationManager2 = ClientGlobals.rotationManager;
                if (value == rotationManager2.getRotation3().yaw) {
                    this.activeState12 = true;
                    float value4 = this.getFloat4(true) + value;
                    float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
                    value3 = SimulatedPlayer.getFloat2(value4, yaw);
                    if (this.movingObjectPosition != null) {
                        this.movingObjectPosition = CombatRayTrace.getMovingObjectPosition(1.0F, value3, value2);
                    }
                }
            }
        }

        float yaw2 = ClientGlobals.rotationManager.getRotation3().yaw;
        int index;
        if ((value3 != yaw2 || this.getDouble3() > 0.0)
        && this.settingRandomizePitch.isEnabled() & true) {
            RotationManager rotationManager3 = ClientGlobals.rotationManager;
            index = Float.compare(value2, rotationManager3.getRotation3().pitch);
        } else {
            index = (this.settingGodBridge.isEnabled() ? 1 : 0);
        }

        if (!(((index & 1) == 0) && (value2 == (ClientGlobals.rotationManager).getRotation3().pitch))) {
            this.placeScaffoldBlock3();
        }

        RotationManager rotationManager5 = ClientGlobals.rotationManager;
        float[] factorArray = new float[]{value3, value2};
        int int2 = this.getInt2();
        rotationManager5.applyFloatArray2(factorArray, -1, this, int2);
    }

    private double getCoordinate(RayTraceHit movingObjectPositionEntry) {
        return 180.0 - this.getCoordinate2(movingObjectPositionEntry);
    }

    private void placeScaffoldBlock2() {
        if (!this.settingNoSwing.isEnabled() || !this.settingServerSideNoSwing.isEnabled()) {
            minecraft.thePlayer.respawnPlayer();
        }
    }

    private float getFloat3() {
        return this.shouldPlaceBlock() && this.settingCustomBlockClutchRotationSpeeds.isEnabled()
        ? (float)this.settingBlockClutchYawSpeed.recalculate()
        : (float)this.settingYawSpeed.recalculate();
    }

    private Pair getPair(RayTraceHit movingObjectPositionEntry, float value) {
        if (this.settingMaxPitchCorrection.getValue() == 180.0) {
            return new Pair(movingObjectPositionEntry.scaleFactor, movingObjectPositionEntry.movingObjectPosition);
        } else {
            MovingObjectPosition movingObjectPosition2 = movingObjectPositionEntry.movingObjectPosition;
            float value2;
            if (this.settingIgnoreMaxPitchCorrectionIfBlockClutch.isEnabled() && this.shouldPlaceBlock()) {
                value2 = 180.0F;
            } else {
                value2 = (float)this.settingMaxPitchCorrection.getValue();
            }

            float scaleFactor = movingObjectPositionEntry.scaleFactor;
            float value3 = ClientGlobals.rotationManager.getRotation3().pitch - value2;
            RotationManager rotationManager = ClientGlobals.rotationManager;
            float floatEntry = MathHelper.clamp_float(scaleFactor, value3, rotationManager.getRotation3().pitch + value2);
            if (floatEntry == movingObjectPositionEntry.scaleFactor) {
                return new Pair(floatEntry, movingObjectPosition2);
            } else {
                RotationManager rotationManager2 = ClientGlobals.rotationManager;
                float float3 = SimulatedPlayer.getFloat3(floatEntry, rotationManager2.getRotation3().pitch);
                MovingObjectPosition movingObjectPosition3 = CombatRayTrace.getMovingObjectPosition(1.0F, value, float3);
                return new Pair(float3, movingObjectPosition3);
            }
        }
    }

    private float getFloat4(boolean enabled) {
        double offset = Math.random() * 100.0;
        double offset2;
        if (enabled) {
            offset2 = this.settingRandomizeYawChanceWhilePitchRotation.getValue();
        } else {
            offset2 = this.getDouble2();
        }

        if (offset > offset2) {
            return 0.0F;
        } else {
            float value;
            if (this.settingOtherYawRandomization.isEnabled()) {
                double offset3 = (double)minecraft.thePlayer.ticksExisted * 0.75;
                double offset4 = -0.2;
                value = (float)Math.sin(offset3 + (double)SettingContainer.settingProvider.getFloat3(offset4, 0.2)) * 0.75F;
            } else {
                double offset5 = -this.settingRandomizeYawVal.getDouble3();
                double offset6 = this.settingRandomizeYawVal.recalculate();
                value = SettingContainer.settingProvider.getFloat3(offset5, offset6);
            }

            AnimationTimer animationTimer9 = this.animationTimer;
            int indexOffset2 = this.settingRandomizeYawInterval.getIndexOffset2();
            if (!animationTimer9.checkCondition(indexOffset2)) {
                return value;
            } else {
                this.animationTimer.processAction3();
                this.settingRandomizeYawInterval.recalculate();
                this.settingRandomizeYawVal.recalculate();
                this.activeState12 = false;
                return value;
            }
        }
    }

    private double getCoordinate(float[] factorArray) {
        return (double)Math.abs(factorArray[0] - ClientGlobals.rotationManager.getRotation3().pitch);
    }

    private boolean checkCondition3(float value) {
        return !this.settingStrafe.isEnabled() ? false : SimulatedPlayer.checkCondition(value) && this.blockPos != null;
    }

    private float getFloat5() {
        return this.shouldPlaceBlock() && this.settingCustomBlockClutchRotationSpeeds.isEnabled()
        ? (float)this.settingBlockClutchPitchSpeed.recalculate()
        : (float)this.settingPitchSpeed.recalculate();
    }

    private static double getCoordinate2(PlaceTarget placeTarget2) {
        double offset = (double)placeTarget2.getBlockPos().getX() + 0.5;
        double offset2 = (double)placeTarget2.getBlockPos().getY() + 0.5;
        return minecraft.thePlayer.getDouble4(offset, offset2, (double)placeTarget2.getBlockPos().getZ() + 0.5);
    }

    private double getCoordinate(float value, RayTraceHit movingObjectPositionEntry) {
        return this.getCoordinate(movingObjectPositionEntry, value);
    }

    private boolean isRandomizePitchChanceOnBlockVisible() {
        return !this.settingRandomizePitch.isEnabled() ? false : this.settingRandomizePitch.isVisible();
    }

    private boolean isOtherYawRandomizationVisible() {
        return !this.settingRandomizeYaw.isEnabled() ? false : this.settingRandomizeYaw.isVisible();
    }

    private boolean executeAction() {
        return !this.settingCorrectStrafeWithKeyBinds.isEnabled() ? false : this.settingCorrectStrafeWithKeyBinds.isVisible();
    }

    private boolean checkCondition(boolean enabled) {
        boolean enabled3 = true;
        if (this.reachDistance52 == 0.0 && enabled) {
            int index = -1;
            if (this.settingSilentMode.getSelected().equals("Exploit")) {
                int index2 = this.getIndex() + 36;
                ItemStack itemStack = minecraft.thePlayer.container.getSlot(index2).getStack();
                if (!CombatUtil.checkItemStack2(itemStack)) {
                    return false;
                }

                index = this.getInt4();
            }

            ItemStack itemStack4 = minecraft.thePlayer.inventoryPlayer.getCurrentItem();
            int index3 = itemStack4 != null ? itemStack4.animationsToGo : 0;
            PlayerControllerMP playerController = minecraft.playerController;
            BlockPos blockPos3 = minecraft.objectMouseOver.getBlockPos();
            if (playerController.onPlayerRightClick(minecraft.thePlayer, minecraft.theWorld, itemStack4, blockPos3, minecraft.objectMouseOver.sideHit, minecraft.objectMouseOver.hitVec)) {
                this.activeState45 = true;
                this.tickDelay25++;
                this.tickDelay14++;
                this.timer.reset();
                this.timer2.reset();
                enabled3 = false;
                double double3 = this.settingMoonWalkStartDistToBlock.getDouble3();
                double double6 = this.settingMoonWalkStartDistToBlock.getDouble6();
                this.reachDistance21 = -SettingContainer.settingProvider.getDouble4(double3, double6);
                this.placeScaffoldBlock2();
            }

            if (itemStack4 == null) {
                this.processAction3(index);
                return false;
            } else {
                if (itemStack4.animationsToGo == 0) {
                    minecraft.thePlayer.inventoryPlayer.mainInventory[minecraft.thePlayer.inventoryPlayer.currentItem] = null;
                } else if ((itemStack4.animationsToGo != index3 || minecraft.playerController.processAction2()) && !this.settingNoSwing.isEnabled()) {
                    minecraft.entityRenderer.itemRenderer.updateState();
                }

                if (enabled3) {
                    this.timer.reset();
                    if (minecraft.playerController.sendUseItem(minecraft.thePlayer, minecraft.theWorld, itemStack4)) {
                        minecraft.entityRenderer.itemRenderer.updateState2();
                    }
                }

                this.processAction3(index);
                return false;
            }
        } else {
            return false;
        }
    }

    private void applyRenderPass2(int x, int x2, int y, int z, RenderPass renderPass) {
        float value = (float)(x - x2 - 2);
        float value2 = (float)(y - 2);
        float value3 = (float)(x + x2 + 2);
        float value4 = (float)(y + 15 + 2);
        float roundFactor = (float)this.settingRoundFactor.getValue();
        int index = RandomUtil.getIndex(renderPass, z);
        RenderUtils.processFloat9(value, value2, value3, value4, roundFactor, 1, this.settingBlockCount2.getIndex(renderPass, index));
    }

    private double getDouble(float value) {
        RotationManager rotationManager = ClientGlobals.rotationManager;
        return (double)Math.abs(value - rotationManager.getRotation3().pitch);
    }

    private float getFloat6(float value) {
        float value2 = value;
        if (!this.settingRotateWithMovement.isEnabled()) {
            return value;
        } else if (minecraft.gameSettings.keyBindBack.pressed) {
            value2 = value + 180.0F;
            if (this.settingRotateWithMovementOnlyOrthogonal.isEnabled()) {
                return value2;
            } else if (minecraft.gameSettings.keyBindLeft.pressed) {
                return value2 + 45.0F;
            } else {
                return !minecraft.gameSettings.keyBindRight.pressed ? value2 : value2 - 45.0F;
            }
        } else if (minecraft.gameSettings.keyBindForward.pressed) {
            if (this.settingRotateWithMovementOnlyOrthogonal.isEnabled()) {
                return value;
            } else if (minecraft.gameSettings.keyBindLeft.pressed) {
                return value - 45.0F;
            } else {
                return !minecraft.gameSettings.keyBindRight.pressed ? value : value + 45.0F;
            }
        } else if (minecraft.gameSettings.keyBindRight.pressed) {
            return value + 90.0F;
        } else {
            if (minecraft.gameSettings.keyBindLeft.pressed) {
                value2 = value - 90.0F;
            }

            return value2;
        }
    }

    private float getFloat7(float value, int index, boolean enabled) {
        if (!enabled) {
            return value;
        } else if (this.settingStrictYawIncorrectFactor.getDouble2() <= 0.0) {
            return value;
        } else {
            if (index != this.tickDelay34) {
                this.tickDelay34 = index;
                this.settingStrictYawIncorrectFactor.recalculate();
                byte state = (byte)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
                this.tickDelay30 = state;
                this.activeState = true;
                this.animationTimer7.processAction3();
            }

            double tickDelay302 = (double)this.tickDelay30;
            return value + (float)(tickDelay302 * this.settingStrictYawIncorrectFactor.getDouble3());
        }
    }

    private void placeScaffoldBlock3() {
        if (!this.activeState24) {
            this.settingResetGodBridgeAfterXJumps.recalculate();
            this.settingStartDelay.recalculate();
            this.tickDelay43 = SettingContainer.settingProvider.getInt2(5, 7);
        }

        this.activeState24 = true;
        this.tickDelay47 = 0;
        this.tickDelay48 = 0;
        this.tickDelay35 = 0;
        this.tickDelay51 = 0;
    }

    private void onMove3(EventMove event) {
        if (minecraft.gameSettings.keyBindJump.pressed) {
            this.activeState41 = true;
            this.placeScaffoldBlock3();
        } else if (this.activeState41) {
            if (minecraft.thePlayer.indexOffset67 > 2) {
                this.activeState41 = false;
            }
        } else {
            boolean activeState42 = this.activeState4;
            Client client = Client.getClient();
            this.activeState4 = SimulatedPlayer.checkCondition(client.getViaProtocolHelper().scaleFactor1);
            if (activeState42 != this.activeState4) {
                this.placeScaffoldBlock3();
            }

            if (!SimulatedPlayer.checkCondition(Client.getClient().getViaProtocolHelper().scaleFactor1)) {
                boolean completed2 = false;
                if (this.activeState24 && !this.settingDisableGodbridgeSafetyFeature.isEnabled()) {
                    boolean completed = false;
                    event.setState4(true);
                    if (event.getScaleFactor7() > 0.0F && this.activeState32) {
                        if (this.reachDistance33 == minecraft.thePlayer.posX) {
                            if (this.reachDistance40 == minecraft.thePlayer.posZ) {
                                completed = true;
                            }
                        }
                    }
                    if (!completed) {
                        if (this.tickDelay47 <= 3) {
                            completed2 = true;
                        }
                    }
                    if (!completed2) {
                        event.setScaleFactor7(0.0F);
                        event.setScaleFactor2(0.0F);
                        this.tickDelay47++;
                        double tickDelay475 = (double)this.tickDelay47;
                        if (tickDelay475 > this.settingStartDelay.recalculate() - 4.0) {
                            double tickDelay476 = (double)this.tickDelay47;
                            if (tickDelay476 < this.settingStartDelay.recalculate() - 4.0 + 5.0) {
                                event.setScaleFactor2(1.0F);
                            } else {
                                double tickDelay477 = (double)this.tickDelay47;
                                if (tickDelay477 < this.settingStartDelay.recalculate() - 4.0 + 7.0) {
                                    float value = -1.0F;
                                    event.setScaleFactor7(value);
                                    this.reachDistance33 = minecraft.thePlayer.posX;
                                    this.reachDistance40 = minecraft.thePlayer.posZ;
                                    return;
                                }

                                this.activeState24 = false;
                            }
                        }
                    }
                }


                this.reachDistance33 = minecraft.thePlayer.posX;
                this.reachDistance40 = minecraft.thePlayer.posZ;
                return;
            }

            int tickDelay512 = this.tickDelay51;
            if (tickDelay512 >= this.settingJumpAfterXBlocks.getIndexOffset2()) {
                if (this.tickDelay35 >= this.settingJumpDelayAfterPlace.getIndexOffset2()) {
                    this.tickDelay35 = 0;
                    this.tickDelay48++;
                    this.settingJumpAfterXBlocks.recalculate();
                    this.settingJumpDelayAfterPlace.recalculate();
                    if (this.tickDelay48 >= this.settingResetGodBridgeAfterXJumps.getIndexOffset2()) {
                        this.placeScaffoldBlock3();
                    } else {
                        event.setState(true);
                        this.tickDelay51 = 0;
                    }
                }

                this.tickDelay35++;
                if (!this.activeState24) {
                    event.setScaleFactor2(0.0F);
                    return;
                }

                this.tickDelay51 = 0;
                if (event.getScaleFactor7() <= 0.0F || this.checkEventMove(event) || !this.activeState32 && this.tickDelay47 <= 3) {
                    this.placeScaffoldBlock3();
                    event.setState4(true);
                    return;
                }

                int tickDelay472 = this.tickDelay47;
                int indexOffset2 = this.settingStartDelay.getIndexOffset2();
                if (tickDelay472 >= indexOffset2) {
                    this.activeState24 = false;
                    return;
                }

                this.tickDelay47++;
                event.setScaleFactor7(0.0F);
            } else {
                this.tickDelay35++;
                if (!this.activeState24) {
                    event.setScaleFactor2(0.0F);
                    return;
                }

                this.tickDelay51 = 0;
                if (event.getScaleFactor7() <= 0.0F || this.checkEventMove(event) || !this.activeState32 && this.tickDelay47 <= 3) {
                    this.placeScaffoldBlock3();
                    event.setState4(true);
                    return;
                }

                int tickDelay473 = this.tickDelay47;
                int indexOffset22 = this.settingStartDelay.getIndexOffset2();
                if (tickDelay473 >= indexOffset22) {
                    this.activeState24 = false;
                    return;
                }

                this.tickDelay47++;
                event.setScaleFactor7(0.0F);
            }

            event.setScaleFactor2(0.0F);
            int tickDelay474 = this.tickDelay47;
            int tickDelay432 = this.tickDelay43;
            if (tickDelay474 < tickDelay432) {
                event.setState4(true);
            }

            return;
        }
    }

    private boolean checkEventMove(EventMove event) {
        boolean enabled = false;
        EnumFacing enumFacing = null;
        double offset = 0.0;
        boolean completed = false;
        enabled = false;
        enumFacing = minecraft.thePlayer.getEnumFacing2(Client.getClient().getViaProtocolHelper().scaleFactor1);
        if (enumFacing != EnumFacing.NORTH) {
            EnumFacing enumFacing2 = EnumFacing.SOUTH;
            if (enumFacing != enumFacing2) {
                offset = minecraft.thePlayer.posZ;
                completed = true;
            }
        }
        if (!completed) {
            offset = minecraft.thePlayer.posX;
        }


        double offset2;
        if (offset < 0.0) {
            offset2 = 1.0 + offset % 1.0;
        } else {
            offset2 = offset % 1.0;
        }

        boolean enabled3 = false;
        boolean completed2 = false;
        if (enumFacing != EnumFacing.NORTH) {
            EnumFacing enumFacing3 = EnumFacing.EAST;
            if (enumFacing != enumFacing3) {
                enabled3 = false;
                completed2 = true;
            }
        }
        if (!completed2) {
            enabled3 = true;
        }


        if (offset2 < 0.5) {
            if (offset2 < 0.2) {
                EventMove event2;
                float value;
                if (enabled3) {
                    event2 = event;
                    value = -1.0F;
                } else {
                    event2 = event;
                    value = 1.0F;
                }

                event2.setScaleFactor2(value);
                return true;
            } else if (offset2 <= 0.3) {
                return enabled;
            } else {
                EventMove event3;
                float value2;
                if (enabled3) {
                    event3 = event;
                    value2 = 1.0F;
                } else {
                    event3 = event;
                    value2 = -1.0F;
                }

                event3.setScaleFactor2(value2);
                return true;
            }
        } else if (offset2 < 0.7) {
            EventMove event4;
            float value3;
            if (enabled3) {
                event4 = event;
                value3 = -1.0F;
            } else {
                event4 = event;
                value3 = 1.0F;
            }

            event4.setScaleFactor2(value3);
            return true;
        } else {
            if (offset2 > 0.8) {
                EventMove event5;
                float value4;
                if (enabled3) {
                    event5 = event;
                    value4 = 1.0F;
                } else {
                    event5 = event;
                    value4 = -1.0F;
                }

                event5.setScaleFactor2(value4);
                enabled = true;
            }

            return enabled;
        }
    }

    private float getFloat8() {
        return this.getFloat6(Client.getClient().getViaProtocolHelper().scaleFactor1);
    }

    private double getDouble2() {
        BlockPos blockPos3 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 0.5, minecraft.thePlayer.posZ);
        return minecraft.theWorld.isAirBlock(blockPos3)
        ? this.settingRandomizeYawChanceOnAir.getValue()
        : this.settingRandomizeYawChanceOnBlock.getValue();
    }

    public PlaceTarget getPlaceTarget() {
        BooleanSetting setting;
        if (Math.abs(minecraft.thePlayer.motionZ) < 1.0E-6) {
            if (Math.abs(minecraft.thePlayer.motionX) < 1.0E-6) {
                for (int i = 1; i < 4; i++) {
                    BlockPos blockPos3 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - (double)i, minecraft.thePlayer.posZ);
                    if (BlockCache.checkBlockPos(blockPos3)) {
                        return new PlaceTarget(
                        blockPos3, EnumFacing.UP, new Vec3((double)blockPos3.getX() + 0.5, (double)(blockPos3.getY() + 1), (double)blockPos3.getZ() + 0.5)
                        );
                    }
                }
            }

            setting = this.settingAutoJump;
        } else {
            setting = this.settingAutoJump;
        }

        float value;
        if (setting.isEnabled() && this.settingSameY.isEnabled() && this.settingTellyBridge.isEnabled()
        ? !(minecraft.thePlayer.motionY >= 0.0) && !minecraft.thePlayer.onGround && MoveUtil.canExecute2()
        : !this.settingFlick.isEnabled() || this.blockPos != null && this.shouldPlaceBlock2()) {
            value = 0.0F;
        } else {
            value = -180.0F;
        }

        Vec3 vec = minecraft.thePlayer
        .getVec34(Client.getClient().getViaProtocolHelper().scaleFactor2, Client.getClient().getViaProtocolHelper().scaleFactor1 - 180.0F + value);
        double offset = (
        minecraft.thePlayer.lastTickPosX - minecraft.thePlayer.posX == 0.0 ? vec.xCoord : minecraft.thePlayer.lastTickPosX - minecraft.thePlayer.posX
        )
        * 2.0;
        double offset2 = (
        minecraft.thePlayer.lastTickPosZ - minecraft.thePlayer.posZ == 0.0 ? vec.zCoord : minecraft.thePlayer.lastTickPosZ - minecraft.thePlayer.posZ
        )
        * 2.0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        float value2 = this.settingSameY.isEnabled() && !this.activeState2 ? (float)(minecraft.thePlayer.posY - minecraft.thePlayer.coordOffset9) : 0.0F;
        float value3 = this.settingSameY.isEnabled() && !this.activeState2 ? 4.5F : 1.0F;

        for (float i2 = 0.5F + value2; i2 < 3.0F + value2; i2++) {
            double offset3 = minecraft.thePlayer.posX + offset;
            double offset4 = minecraft.thePlayer.posY - (double)i2;
            double offset5 = minecraft.thePlayer.posZ + offset2;
            double offset6 = offset3 - minecraft.thePlayer.posX;
            double offset7 = offset5 - minecraft.thePlayer.posZ;
            float value4 = (float)(MathHelper.getDouble6(offset7, offset6) * 180.0 / Math.PI - 90.0);
            float value5 = (float)(-(MathHelper.getDouble6(0.0, (double)MathHelper.sqrt_double(offset6 * offset6 + offset7 * offset7)) * 180.0 / Math.PI));
            float float9 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value4, 180.0F);
            float float92 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, value5, 180.0F);
            Vec3 vec2 = minecraft.thePlayer.getVec34(float92, float9);
            MovingObjectPosition movingObjectPosition7 = minecraft.theWorld
            .getMovingObjectPosition7(
            offset3,
            offset4,
            offset5,
            offset3 + vec2.xCoord * (double)value3,
            offset4 + vec2.yCoord * (double)value3,
            offset5 + vec2.zCoord * (double)value3,
            false,
            false,
            true
            );
            if (movingObjectPosition7 != null
            && movingObjectPosition7.typeOfHit == MovingObjectType.BLOCK
            && movingObjectPosition7.getBlockPos() != null
            && BlockCache.checkBlockPos(movingObjectPosition7.getBlockPos())
            && !arrayList2.contains(movingObjectPosition7.getBlockPos())
            && !BlockCache.checkBlockPos2(movingObjectPosition7.getBlockPos().up())) {
                arrayList.add(new PlaceTarget(movingObjectPosition7.getBlockPos(), movingObjectPosition7.sideHit, movingObjectPosition7.hitVec));
                arrayList2.add(movingObjectPosition7.getBlockPos());
            }
        }

        arrayList.sort(Comparator.comparingDouble(object -> getCoordinate2((PlaceTarget)object)));
        if (!arrayList.isEmpty()) {
            return (PlaceTarget)arrayList.get(0);
        } else {
            for (float i3 = 0.5F + value2; i3 < 4.0F + value2; i3++) {
                double offset8 = minecraft.thePlayer.posX + offset;
                double offset9 = minecraft.thePlayer.posY - (double)i3;
                double offset10 = minecraft.thePlayer.posZ + offset2;
                double offset11 = offset8 - minecraft.thePlayer.posX;
                double offset12 = offset10 - minecraft.thePlayer.posZ;
                float value6 = (float)(MathHelper.getDouble6(offset12, offset11) * 180.0 / Math.PI - 90.0);
                float value7 = (float)(-(MathHelper.getDouble6(0.0, (double)MathHelper.sqrt_double(offset11 * offset11 + offset12 * offset12)) * 180.0 / Math.PI));
                float float93 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value6, 180.0F);
                float float94 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, value7, 180.0F);

                for (float i4 = 0.0F; i4 <= 360.0F; i4++) {
                    Vec3 vec3 = minecraft.thePlayer.getVec34(float94, float93 + i4);
                    MovingObjectPosition movingObjectPosition4 = minecraft.theWorld
                    .getMovingObjectPosition4(
                    offset8, offset9, offset10, offset8 + vec3.xCoord * 4.5, offset9 + vec3.yCoord * 4.5, offset10 + vec3.zCoord * 4.5, false, false, true
                    );
                    if (movingObjectPosition4 != null
                    && movingObjectPosition4.typeOfHit == MovingObjectType.BLOCK
                    && movingObjectPosition4.getBlockPos() != null
                    && BlockCache.checkBlockPos(movingObjectPosition4.getBlockPos())
                    && !arrayList2.contains(movingObjectPosition4.getBlockPos())
                    && !BlockCache.checkBlockPos2(movingObjectPosition4.getBlockPos().up())) {
                        arrayList.add(new PlaceTarget(movingObjectPosition4.getBlockPos(), movingObjectPosition4.sideHit, movingObjectPosition4.hitVec));
                        arrayList2.add(movingObjectPosition4.getBlockPos());
                    }
                }
            }

            arrayList.sort(Comparator.comparingDouble(object2 -> getCoordinate((PlaceTarget)object2)));
            if (!arrayList.isEmpty()) {
                return (PlaceTarget)arrayList.get(0);
            } else {
                for (int i5 = 1; i5 < 4; i5++) {
                    BlockPos blockPos4 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - (double)i5, minecraft.thePlayer.posZ);
                    if (BlockCache.checkBlockPos(blockPos4)) {
                        return new PlaceTarget(
                        blockPos4, EnumFacing.UP, new Vec3((double)blockPos4.getX() + 0.5, (double)(blockPos4.getY() + 1), (double)blockPos4.getZ() + 0.5)
                        );
                    }
                }

                System.out.println("Null");
                return null;
            }
        }
    }

    private boolean checkCondition2(boolean enabled) {
        boolean enabled3 = true;
        if (this.reachDistance52 == 0.0 && enabled) {
            int index = -1;
            if (this.settingSilentMode.getSelected().equals("Exploit")) {
                int index2 = this.getIndex() + 36;
                ItemStack itemStack = minecraft.thePlayer.container.getSlot(index2).getStack();
                if (!CombatUtil.checkItemStack2(itemStack)) {
                    return false;
                }

                index = this.getInt4();
            }

            ItemStack itemStack4 = minecraft.thePlayer.inventoryPlayer.getCurrentItem();
            int index3 = itemStack4 != null ? itemStack4.animationsToGo : 0;
            PlayerControllerMP playerController = minecraft.playerController;
            BlockPos blockPos3 = minecraft.objectMouseOver.getBlockPos();
            if (playerController.onPlayerRightClick(minecraft.thePlayer, minecraft.theWorld, itemStack4, blockPos3, minecraft.objectMouseOver.sideHit, minecraft.objectMouseOver.hitVec)) {
                this.activeState45 = true;
                this.tickDelay25++;
                this.tickDelay51++;
                this.tickDelay14++;
                this.tickDelay35 = 0;
                this.timer.reset();
                this.timer2.reset();
                enabled3 = false;
                double double3 = this.settingMoonWalkStartDistToBlock.getDouble3();
                double double6 = this.settingMoonWalkStartDistToBlock.getDouble6();
                this.reachDistance21 = -SettingContainer.settingProvider.getDouble4(double3, double6);
                this.placeScaffoldBlock2();
            }

            if (itemStack4 == null) {
                this.processAction3(index);
                return false;
            } else {
                if (itemStack4.animationsToGo == 0) {
                    minecraft.thePlayer.inventoryPlayer.mainInventory[minecraft.thePlayer.inventoryPlayer.currentItem] = null;
                } else if ((itemStack4.animationsToGo != index3 || minecraft.playerController.processAction2()) && !this.settingNoSwing.isEnabled()) {
                    minecraft.entityRenderer.itemRenderer.updateState();
                }

                if (enabled3) {
                    this.timer.reset();
                    if (minecraft.playerController.sendUseItem(minecraft.thePlayer, minecraft.theWorld, itemStack4)) {
                        minecraft.entityRenderer.itemRenderer.updateState2();
                    }
                }

                this.processAction3(index);
                return !enabled3;
            }
        } else {
            return false;
        }
    }

    private boolean isRandomizePitchIntervalVisible() {
        if (this.settingOtherPitchRandomization.isEnabled()) {
            return false;
        } else {
            return !this.settingRandomizePitch.isEnabled() ? false : this.settingRandomizePitch.isVisible();
        }
    }

    private void processAction3(int index) {
        if (this.settingSilentMode.getSelected().equals("Exploit")) {
            if (index >= 0) {
                minecraft.thePlayer.inventoryPlayer.currentItem = index;
            }
        }
    }

    private BlockPos getBlockPos() {
        ArrayList arrayList = new ArrayList();
        float value = 15.0F;

        while (true) {
            byte state;
            if (!this.settingIntave.isEnabled() && !this.settingOtherRotation.isEnabled()) {
                state = 90;
            } else {
                state = 85;
            }

            if (!(value < (float)state)) {
                float[] factorArray = new float[2];
                if (arrayList.isEmpty()) {
                    return null;
                }

                arrayList.sort(Comparator.comparingDouble(object -> this.getDouble(((Number)object).floatValue())));
                if (!arrayList.isEmpty()) {
                    factorArray[1] = (Float)arrayList.get(0);
                    RotationManager rotationManager = ClientGlobals.rotationManager;
                    factorArray[0] = rotationManager.getRotation3().yaw;
                }

                MovingObjectPosition movingObjectPosition2 = minecraft.thePlayer.getMovingObjectPosition(4.5, 2.0F, factorArray[0], factorArray[1]);
                EnumFacing sideHit = movingObjectPosition2.sideHit;
                BlockPos blockPos3 = movingObjectPosition2.getBlockPos();
                if (sideHit == EnumFacing.EAST) {
                    return blockPos3.add(1, 0, 0);
                }

                EnumFacing enumFacing = EnumFacing.WEST;
                if (sideHit == enumFacing) {
                    return blockPos3.add(-1, 0, 0);
                }

                EnumFacing enumFacing2 = EnumFacing.NORTH;
                if (sideHit == enumFacing2) {
                    return blockPos3.add(0, 0, -1);
                }

                EnumFacing enumFacing3 = EnumFacing.SOUTH;
                return sideHit == enumFacing3 ? blockPos3.add(0, 0, 1) : null;
            }

            float[] factorArray2 = new float[2];
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            factorArray2[0] = rotationManager2.getRotation3().yaw;
            factorArray2[1] = value;
            RotationManager rotationManager3 = ClientGlobals.rotationManager;
            MovingObjectPosition movingObjectPosition3 = minecraft.thePlayer.getMovingObjectPosition(4.5, 2.0F, rotationManager3.getRotation3().yaw, factorArray2[1]);
            if (movingObjectPosition3.typeOfHit == MovingObjectType.BLOCK) {
                BlockPos blockPos4 = movingObjectPosition3.getBlockPos();
                if (BlockCache.checkBlockPos(blockPos4) && movingObjectPosition3.sideHit != EnumFacing.DOWN) {
                    EnumFacing sideHit2 = movingObjectPosition3.sideHit;
                    EnumFacing enumFacing4 = EnumFacing.UP;
                    if (sideHit2 != enumFacing4 && (movingObjectPosition3.hitVec.yCoord % 1.0 < 0.65 && movingObjectPosition3.hitVec.yCoord % 1.0 > 0.35 || !this.settingIntave.isEnabled())) {
                        arrayList.add(factorArray2[1]);
                    }
                }
            }

            value += 0.05F;
        }
    }

    private void placeScaffoldBlock4() {
        if (this.tickDelay16 != 0) {
            if (this.getIndex() >= 0) {
                ScaledResolution scaledResolution = new ScaledResolution(minecraft);
                GL11.glPushMatrix();
                GL11.glScaled(1.0 / (double)scaledResolution.getScaleFactor(), 1.0 / (double)scaledResolution.getScaleFactor(), 1.0);
                double value = this.settingScale.getValue();
                GL11.glScaled(value, this.settingScale.getValue(), 1.0);
                double displayWidth = (double)minecraft.displayWidth;
                double offset = displayWidth / this.settingScale.getValue();
                double displayHeight = (double)minecraft.displayHeight;
                double offset2 = displayHeight / this.settingScale.getValue();
                int int6 = MathHelper.truncateDoubleToInt(offset);
                int int62 = MathHelper.truncateDoubleToInt(offset2);
                String text = this.tickDelay16 + "";
                int index = minecraft.fontRendererObj.getIndex(text);
                int index2 = (20 + index) / 2;
                int index3 = index2 + 2;
                double offset3 = (double)(int6 - (index2 + 2) * 2);
                int index4 = index3 + (int)(offset3 * (this.settingX.getValue() / 100.0));
                double offset4 = (double)(int62 - 19);
                int index5 = 2 + (int)(offset4 * (this.settingY.getValue() / 100.0));
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                if (this.settingBackGround.isEnabled()) {
                    int int12 = this.settingBackGroundColor.getInt12(0);
                    boolean enabled = this.settingBlockCount2.canExecute();
                    boolean enabled3 = this.settingBlockCount2.canExecute2();
                    MovementHandler.processBoolean(enabled, enabled3);
                    MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass2(index4, index2, index5, int12, renderPass));
                }

                RenderHelper.enableGUIStandardItemLighting();
                RenderUtils.applyItemStack(minecraft.thePlayer.container.getSlot(this.getIndex() + 36).getStack(), index4 - index2, index5, false);
                RenderHelper.disableStandardItemLighting();
                minecraft.fontRendererObj.getIndex(text, index4 - index2 + 20, index5 + 4, -1);
                GL11.glPopMatrix();
            }
        }
    }

    private RangeSetting getRangeSetting2() {
        return this.shouldPlaceBlock() && this.settingCustomBlockClutchRotationSpeeds.isEnabled()
        ? this.settingBlockClutchYawAcceleration
        : this.settingYawAcceleration;
    }

    private int getInt2() {
        return this.settingMoveFix.getSelected().equals("None") ? 0 : this.settingKeepRotationsAfterModuleDisable.getIndexOffset2();
    }

    private Pair getPair() {
        float value = 0.0F;
        byte state = (byte)0;
        boolean completed3 = false;
        byte state2 = (byte)0;
        boolean completed2 = false;
        boolean completed = false;
        value = this.getFloat2();
        boolean enabled = this.checkCondition3(value);
        if (this.settingAutoJump.isEnabled() && this.settingSameY.isEnabled()) {
            if (this.settingTellyBridge.isEnabled()) {
                state2 = (byte)(minecraft.thePlayer.motionY < 0.0 && !minecraft.thePlayer.onGround && MoveUtil.canExecute2() ? 1 : 0);
                completed2 = true;
            }
            if (!completed2) {
                if (!this.settingFlick.isEnabled()) {
                    completed = true;
                }
                if (!completed) {
                    state2 = ((byte)(this.settingOtherFlick.isEnabled() ? 1 : 0));
                }
            }
        } else {
            if (!this.settingFlick.isEnabled()) {
                state = (byte)1;
                completed3 = true;
            }
            if (!completed3) {
                state2 = ((byte)(this.settingOtherFlick.isEnabled() ? 1 : 0));
            }
        }
        if (!completed3) {
            if (!completed2) {
                if (!completed) {
                    if (state2 != 0) {
                        if (minecraft.thePlayer.onGround) {
                            completed2 = true;
                        }
                    } else {
                        state2 = ((byte)(this.shouldPlaceBlock2() ? 1 : 0));
                        if (state2 == 0) {
                            completed2 = true;
                        }
                    }
                }
                if (!completed2) {
                    state2 = 1;
                }
            }
            state = (byte)state2;
        }


        if (state != 0 && this.blockPos != null) {
            float motionMultiplier262 = this.motionMultiplier26;
            double offset;
            double offset2;
            if (this.activeState11) {
                offset2 = 0.5;
                offset = 0.5;
            } else if ((double)Math.abs(this.motionMultiplier26) <= 22.5) {
                double double7 = MathHelper.getDouble7(this.settingStrafeWidth.recalculate(), 0.0, 0.5);
                offset2 = 0.5 + double7;
                offset = 0.5 - double7;
            } else {
                double double72 = MathHelper.getDouble7(
                PlayerUtil.getDouble3(
                PlayerUtil.getCoordinate(minecraft.thePlayer.getVec34(80.0F, 0.0F), minecraft.thePlayer.getVec34(80.0F, this.motionMultiplier26)),
                90.0,
                1.6
                )
                - 0.05,
                0.0,
                0.5
                );
                offset2 = 0.5 + double72;
                offset = 0.5 - double72;
            }

            int index;
            if (!this.activeState11 && !this.settingCorrectRotationWhileBuilding.isEnabled()) {
                this.activeState11 = false;
                index = Double.compare((double)Math.abs(this.motionMultiplier26), 22.5);
            } else {
                if ((minecraft.thePlayer.getEnumFacing2(value - 180.0F)) == EnumFacing.EAST) {
                    if (minecraft.thePlayer.posZ > (double)this.blockPos.getZ() + offset2 && motionMultiplier262 > 0.0F) {
                        this.motionMultiplier26 = -((float)Math.max(1.0, this.settingStrafeDegree.recalculate()));
                    } else {
                        if (minecraft.thePlayer.posZ < (double)this.blockPos.getZ() + offset
                        && motionMultiplier262 < 0.0F) {
                            this.motionMultiplier26 = (float)Math.max(1.0, this.settingStrafeDegree.recalculate());
                        }
                    }

                    this.activeState11 = false;
                    index = Double.compare((double)Math.abs(this.motionMultiplier26), 22.5);
                } else if ((minecraft.thePlayer.getEnumFacing2(value - 180.0F)) == EnumFacing.WEST) {
                    if (minecraft.thePlayer.posZ > (double)this.blockPos.getZ() + offset2 && motionMultiplier262 < 0.0F) {
                        this.motionMultiplier26 = (float)Math.max(1.0, this.settingStrafeDegree.recalculate());
                    } else {
                        if (minecraft.thePlayer.posZ < (double)this.blockPos.getZ() + offset
                        && motionMultiplier262 > 0.0F) {
                            this.motionMultiplier26 = -((float)Math.max(1.0, this.settingStrafeDegree.recalculate()));
                        }
                    }

                    this.activeState11 = false;
                    index = Double.compare((double)Math.abs(this.motionMultiplier26), 22.5);
                } else if ((minecraft.thePlayer.getEnumFacing2(value - 180.0F)) == EnumFacing.SOUTH) {
                    if (minecraft.thePlayer.posX > (double)this.blockPos.getX() + offset2 && motionMultiplier262 < 0.0F) {
                        this.motionMultiplier26 = (float)Math.max(1.0, this.settingStrafeDegree.recalculate());
                    } else {
                        if (minecraft.thePlayer.posX < (double)this.blockPos.getX() + offset
                        && motionMultiplier262 > 0.0F) {
                            this.motionMultiplier26 = -((float)Math.max(1.0, this.settingStrafeDegree.recalculate()));
                        }
                    }

                    this.activeState11 = false;
                    index = Double.compare((double)Math.abs(this.motionMultiplier26), 22.5);
                } else if ((minecraft.thePlayer.getEnumFacing2(value - 180.0F)) == EnumFacing.NORTH) {
                    if (minecraft.thePlayer.posX > (double)this.blockPos.getX() + offset2 && motionMultiplier262 > 0.0F) {
                        this.motionMultiplier26 = -((float)Math.max(1.0, this.settingStrafeDegree.recalculate()));
                    } else {
                        if (minecraft.thePlayer.posX < (double)this.blockPos.getX() + offset
                        && motionMultiplier262 < 0.0F) {
                            this.motionMultiplier26 = (float)Math.max(1.0, this.settingStrafeDegree.recalculate());
                        }
                    }

                    this.activeState11 = false;
                    index = Double.compare((double)Math.abs(this.motionMultiplier26), 22.5);
                } else {
                    this.activeState11 = false;
                    index = Double.compare((double)Math.abs(this.motionMultiplier26), 22.5);
                }
            }

            if (index > 0) {
                value -= this.motionMultiplier26;
            } else {
                value += this.motionMultiplier26;
            }
        }

        if (this.settingFlick.isEnabled() && this.settingOtherFlick.isEnabled()) {
            value += minecraft.thePlayer.ticksExisted % 2 == 0 ? 1.0F : 0.0F;
        }

        if (this.settingRandomizeYaw.isEnabled()
        && (this.getDouble2() > 0.0 || this.activeState12)) {
            value += this.getFloat4(this.activeState12);
        }

        float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
        if (pitch > 75.0F) {
            this.activeState1 = false;
        }

        if (!MoveUtil.canExecute() || !this.timer3.hasReached(200L) || !this.activeState1) {
            pitch = 80.34F;
        }

        if (this.settingGodBridge.isEnabled() && !this.activeState24) {
            value = ClientGlobals.rotationManager.getRotation3().yaw;
        }

        if (this.blockPos == null) {
            return new Pair(new Rotation(value, pitch), true);
        } else {
            Block block = minecraft.theWorld.getBlockState(this.blockPos).getBlock();
            if (this.shouldPlaceBlock7() && (!this.settingFlick.isEnabled() || this.shouldPlaceBlock2())) {
                float float9 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, value, 180.0F);
                double offset3 = (double)this.blockPos.getX() + block.getCoordOffset();
                double offset4 = (double)this.blockPos.getX() + block.getCoordOffset9();
                double offset5 = (double)this.blockPos.getY() + block.getCoordOffset6();
                double offset6 = (double)this.blockPos.getZ() + block.getCoordOffset2();
                double offset7 = (double)this.blockPos.getZ() + block.getCoordOffset15();
                ArrayList arrayList = new ArrayList();
                arrayList.add(
                SimulatedPlayer.getRotation2(offset3, offset5, offset6, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false).yaw
                );
                arrayList.add(
                SimulatedPlayer.getRotation2(offset3, offset5, offset7, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false).yaw
                );
                arrayList.add(
                SimulatedPlayer.getRotation2(offset4, offset5, offset6, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false).yaw
                );
                arrayList.add(
                SimulatedPlayer.getRotation2(offset4, offset5, offset7, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false).yaw
                );
                Collections.sort(arrayList);
                value = MathHelper.clamp_float(float9, (Float)arrayList.get(0) + 0.5F, (Float)arrayList.get(arrayList.size() - 1) - 0.5F);
                this.motionMultiplier = value - this.motionMultiplier26;
            }

            if (this.shouldPlaceBlock3()) {
                Vec3 vec = minecraft.thePlayer.getVec35(1.0F);
                double double73 = MathHelper.getDouble7(
                vec.xCoord, (double)this.blockPos.getX() + block.getCoordOffset(), (double)this.blockPos.getX() + block.getCoordOffset6()
                );
                double double74 = MathHelper.getDouble7(
                vec.yCoord,
                (double)this.blockPos.getY() + block.getCoordOffset11(),
                (double)this.blockPos.getY() + block.getCoordOffset6() - 0.02
                );
                double double75 = MathHelper.getDouble7(
                vec.zCoord, (double)this.blockPos.getZ() + block.getCoordOffset2(), (double)this.blockPos.getZ() + block.getCoordOffset15()
                );
                Rotation rotation2 = SimulatedPlayer.getRotation2(
                double73, double74, double75, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false
                );
                if (new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 1.0, minecraft.thePlayer.posZ).checkBlockPos(this.blockPos)) {
                    rotation2.yaw = value;
                }

                Rotation rotation5 = SimulatedPlayer.getRotation5(rotation2, ClientGlobals.rotationManager.getRotation3());
                return new Pair(rotation5, true);
            } else if (SimulatedPlayer.checkCondition(Client.getClient().getViaProtocolHelper().scaleFactor1)) {
                if (this.settingStaticPitchBool.isEnabled()) {
                    if (minecraft.gameSettings.keyBindJump.pressed && !this.settingStaticPitchAtJump.isEnabled()) {
                        return this.settingGodBridge.isEnabled()
                        ? new Pair(
                        new Rotation(
                        value,
                        SimulatedPlayer.checkCondition(Client.getClient().getViaProtocolHelper().scaleFactor1) ? 75.83F : 76.5F
                        ),
                        true
                        )
                        : new Pair(new Rotation(value, pitch), false);
                    }

                    if (!this.shouldPlaceBlock6()) {
                        if (!this.settingStaticPitchRandomizeOnlyAtStart.isEnabled()) {
                            this.settingStaticPitch.recalculate();
                        }

                        float staticPitch = (float)this.settingStaticPitch.getDouble3();
                        return new Pair(new Rotation(value, staticPitch), true);
                    }
                }

                return this.settingGodBridge.isEnabled()
                ? new Pair(
                new Rotation(
                value, SimulatedPlayer.checkCondition(Client.getClient().getViaProtocolHelper().scaleFactor1) ? 75.83F : 76.5F
                ),
                true
                )
                : new Pair(new Rotation(value, pitch), false);
            } else {
                if (this.settingStaticPitchBoolDiagonal.isEnabled()) {
                    if (minecraft.gameSettings.keyBindJump.pressed && !this.settingStaticPitchAtJumpDiagonal.isEnabled()) {
                        return this.settingGodBridge.isEnabled()
                        ? new Pair(
                        new Rotation(
                        value,
                        SimulatedPlayer.checkCondition(Client.getClient().getViaProtocolHelper().scaleFactor1) ? 75.83F : 76.5F
                        ),
                        true
                        )
                        : new Pair(new Rotation(value, pitch), false);
                    }

                    if (!this.shouldPlaceBlock10()) {
                        if (!this.settingStaticPitchRandomizeOnlyAtStartDiagonal.isEnabled()) {
                            this.settingStaticPitchDiagonal.recalculate();
                        }

                        float staticPitchDiagonal = (float)this.settingStaticPitchDiagonal.getDouble3();
                        return new Pair(new Rotation(value, staticPitchDiagonal), true);
                    }
                }

                return this.settingGodBridge.isEnabled()
                ? new Pair(
                new Rotation(
                value, SimulatedPlayer.checkCondition(Client.getClient().getViaProtocolHelper().scaleFactor1) ? 75.83F : 76.5F
                ),
                true
                )
                : new Pair(new Rotation(value, pitch), false);
            }
        }
    }

    private void onMove4(EventMove event) {
        if (event.getScaleFactor7() != 0.0F) {
            int index = event.getScaleFactor7() > 0.0F ? 1 : -1;
            this.keyCode2 = index;
        } else {
            if (event.getScaleFactor2() != 0.0F) {
                int index2 = event.getScaleFactor2() > 0.0F ? -1 : 1;
                this.keyCode2 = index2;
            }
        }
    }

    private double getDouble3() {
        BlockPos blockPos3 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 0.5, minecraft.thePlayer.posZ);
        return minecraft.theWorld.isAirBlock(blockPos3)
        ? this.settingRandomizePitchChanceInAir.getValue()
        : this.settingRandomizePitchChanceOnBlock.getValue();
    }

    private int getIndex() {
        return this.tickDelay39;
    }

    private double getCoordinate(RayTraceHit movingObjectPositionEntry, float value) {
        MovingObjectPosition movingObjectPosition2 = CombatRayTrace.getMovingObjectPosition(1.0F, value, movingObjectPositionEntry.scaleFactor);
        if (movingObjectPosition2 == null) {
            return 100.0;
        } else if (movingObjectPosition2.getBlockPos() == null) {
            return 100.0;
        } else if (movingObjectPosition2.hitVec == null) {
            return 100.0;
        } else {
            double offset = movingObjectPosition2.hitVec.yCoord % 1.0;
            return Math.abs(0.5 - offset);
        }
    }

    private double getDouble4() {
        return this.shouldPlaceBlock() && this.settingCustomBlockClutchRotationSpeeds.isEnabled()
        ? this.settingBlockClutchAimSpeed.getValue()
        : this.settingAimSpeed.getValue();
    }

    private void applyMovingObjectPosition2(float value, float value2, MovingObjectPosition hitResult) {
        this.applyMovingObjectPosition(value, value2, hitResult, false);
    }

    private void onMove5(EventMove event) {
        if (event.getScaleFactor7() != 0.0F) {
            int index = event.getScaleFactor7() > 0.0F ? -1 : 1;
            this.keyCode2 = index;
        } else {
            if (event.getScaleFactor2() != 0.0F) {
                int index2 = event.getScaleFactor2() > 0.0F ? 1 : -1;
                this.keyCode2 = index2;
            }
        }
    }

    public static void placeScaffoldBlock() {
    }

    private boolean shouldPlaceBlock() {
        return this.checkCondition16(false);
    }

    private boolean checkCondition2(float value) {
        if (this.checkCondition17()) {
            return true;
        } else if (!this.settingEdgeRotateCheck.isEnabled() || this.blockPos == null) {
            int int15 = MathHelper.ceiling_double_int(minecraft.thePlayer.posX);
            int int152 = MathHelper.ceiling_double_int(minecraft.thePlayer.posY - 0.5);
            return minecraft.theWorld.checkCondition(int15, int152, MathHelper.ceiling_double_int(minecraft.thePlayer.posZ));
        } else {
            return this.shouldPlaceBlock4() || this.checkCondition(value);
        }
    }

    private boolean checkMovingObjectPosition(MovingObjectPosition hitResult) {
        boolean enabled = true;
        int index = -1;
        if (this.settingSilentMode.getSelected().equals("Exploit")) {
            int index2 = this.getIndex() + 36;
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(index2).getStack();
            BlockPos blockPos3 = this.placeTarget.getBlockPos();
            EnumFacing facing = this.placeTarget.getFacing();
            Vec3 hitVec = this.placeTarget.getHitVec();
            if (!CombatUtil.checkItemStack(itemStack, blockPos3, facing, hitVec)) {
                return false;
            }

            index = this.getInt4();
        }

        if (minecraft.playerController
        .onPlayerRightClick(minecraft.thePlayer, minecraft.theWorld, minecraft.thePlayer.getItemStack(), hitResult.getBlockPos(), hitResult.sideHit, hitResult.hitVec)) {
            this.activeState45 = true;
            this.tickDelay25++;
            this.tickDelay51++;
            this.tickDelay14++;
            this.tickDelay35 = 0;
            this.timer.reset();
            this.timer2.reset();
            enabled = false;
            double double3 = this.settingMoonWalkStartDistToBlock.getDouble3();
            double double6 = this.settingMoonWalkStartDistToBlock.getDouble6();
            this.reachDistance21 = -SettingContainer.settingProvider.getDouble4(double3, double6);
            this.placeScaffoldBlock2();
        }

        if (minecraft.thePlayer.getHeldItem() != null) {
            ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
            if (itemStack5.animationsToGo == 0) {
                minecraft.thePlayer.inventoryPlayer.mainInventory[minecraft.thePlayer.inventoryPlayer.currentItem] = null;
            }
        }

        if (enabled) {
            this.timer.reset();
            if (minecraft.thePlayer.getHeldItem() != null) {
                if (minecraft.playerController.sendUseItem(minecraft.thePlayer, minecraft.theWorld, minecraft.thePlayer.getItemStack())) {
                    minecraft.entityRenderer.itemRenderer.updateState2();
                }
            }
        }

        this.processAction3(index);
        return false;
    }

    private void placeScaffoldBlock5() {
        float float3 = this.getFloat3();
        float float5 = this.getFloat5();
        float floatEntry = this.simulatedPlayer
        .getFloat(float3, ClientGlobals.rotationManager.getRotation3().yaw, Client.getClient().getViaProtocolHelper().scaleFactor1);
        if (this.shouldPlaceBlock3() && this.blockPos != null) {
            Block block = minecraft.theWorld.getBlockState(this.blockPos).getBlock();
            Vec3 vec = minecraft.thePlayer.getVec35(1.0F);
            double double7 = MathHelper.getDouble7(
            vec.xCoord, (double)this.blockPos.getX() + block.getCoordOffset(), (double)this.blockPos.getX() + block.getCoordOffset6()
            );
            double double72 = MathHelper.getDouble7(
            vec.yCoord, (double)this.blockPos.getY() + block.getCoordOffset11(), (double)this.blockPos.getY() + block.getCoordOffset6() - 0.02
            );
            double double73 = MathHelper.getDouble7(
            vec.zCoord, (double)this.blockPos.getZ() + block.getCoordOffset2(), (double)this.blockPos.getZ() + block.getCoordOffset15()
            );
            Rotation rotation2 = SimulatedPlayer.getRotation2(double7, double72, double73, ClientGlobals.rotationManager.getRotation3(), float3, float5, true);
            if (new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - 1.0, minecraft.thePlayer.posZ).checkBlockPos(this.blockPos)) {
                rotation2.yaw = floatEntry;
            }

            Rotation rotation5 = SimulatedPlayer.getRotation5(rotation2, ClientGlobals.rotationManager.getRotation3());
            ClientGlobals.rotationManager.applyRotation4(rotation5, -1, this, this.getInt2());
        } else {
            double value = this.settingExpandLength.getValue();
            double offset = Math.sqrt(minecraft.thePlayer.motionX * minecraft.thePlayer.motionX + minecraft.thePlayer.motionZ * minecraft.thePlayer.motionZ);
            double offset2 = value / offset;
            if (minecraft.thePlayer.onGround) {
                this.reachDistance31 = 0.0;
            }

            if (!minecraft.thePlayer.onGround && (!this.settingSameY.isEnabled() || MouseUtils.checkCondition(1))) {
                this.reachDistance31 = 1.0;
                if (CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer)[9] == 1.0) {
                    return;
                }

                double offset3 = 0.0;
                double offset4 = 0.0;

                for (int i = 0; i < 10; i++) {
                    double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(i, minecraft.thePlayer);
                    double offset5 = coordOffsetArray[1] - minecraft.thePlayer.coordOffset9;
                    if (offset5 > 1.0 && offset5 < 2.0) {
                        offset3 = coordOffsetArray[0];
                        offset4 = coordOffsetArray[2];
                    }
                }

                BlockPos blockPos3 = new BlockPos(offset3, minecraft.thePlayer.coordOffset9 - 0.1, offset4);
                if (offset3 != 0.0
                && offset4 != 0.0
                && minecraft.theWorld.getBlockState(blockPos3).getBlock().isState18()
                && !minecraft.theWorld.getBlockState(blockPos3.up()).getBlock().isState18()) {
                    this.placeTarget = new PlaceTarget(blockPos3, EnumFacing.UP, new Vec3(offset3, minecraft.thePlayer.coordOffset9, offset4));
                    Rotation rotation22 = SimulatedPlayer.getRotation2(
                    this.placeTarget.getHitVec().xCoord,
                    this.placeTarget.getHitVec().yCoord,
                    
                    this.placeTarget.getHitVec().zCoord,
                    ClientGlobals.rotationManager.getRotation3(),
                    float3,
                    float5,
                    false
                    );
                    float floatEntry2 = this.simulatedPlayer.getFloat(float3, ClientGlobals.rotationManager.getRotation3().yaw, rotation22.yaw);
                    float float11 = this.simulatedPlayer
                    .getFloat11(float5, ClientGlobals.rotationManager.getRotation3().pitch, rotation22.pitch);
                    ClientGlobals.rotationManager.applyRotation4(new Rotation(floatEntry2, float11), -1, this, this.getInt2());
                    return;
                }
            }

            for (float i2 = 0.5F; (double)i2 < Math.min(20.0, offset2); i2 = (float)((double)i2 + 0.25)) {
                double offset6 = minecraft.thePlayer.posX + MathHelper.getDouble7(minecraft.thePlayer.motionX * (double)i2, (double)(-i2), (double)i2);
                double offset7 = minecraft.thePlayer.coordOffset9 - 0.5 + this.reachDistance31;
                double offset8 = minecraft.thePlayer.posZ + MathHelper.getDouble7(minecraft.thePlayer.motionZ * (double)i2, (double)(-i2), (double)i2);
                MovingObjectPosition movingObjectPosition4 = minecraft.theWorld
                .getMovingObjectPosition4(offset6, offset7, offset8, minecraft.thePlayer.posX, offset7, minecraft.thePlayer.posZ, false, false, true);
                if (MoveUtil.canExecute()
                && movingObjectPosition4 != null
                && movingObjectPosition4.typeOfHit == MovingObjectType.BLOCK
                && !movingObjectPosition4.getBlockPos().checkBlockPos2(new BlockPos(offset6, offset7, offset8))) {
                    this.placeTarget = new PlaceTarget(movingObjectPosition4.getBlockPos(), movingObjectPosition4.sideHit, movingObjectPosition4.hitVec);
                    this.blockPos = this.placeTarget.getBlockPos();
                    Rotation rotation23 = SimulatedPlayer.getRotation2(
                    this.placeTarget.getHitVec().xCoord,
                    this.placeTarget.getHitVec().yCoord,
                    
                    this.placeTarget.getHitVec().zCoord,
                    ClientGlobals.rotationManager.getRotation3(),
                    float3,
                    float5,
                    false
                    );
                    float floatEntry3 = this.simulatedPlayer.getFloat(float3, ClientGlobals.rotationManager.getRotation3().yaw, rotation23.yaw);
                    float float112 = this.simulatedPlayer
                    .getFloat11(float5, ClientGlobals.rotationManager.getRotation3().pitch, rotation23.pitch);
                    ClientGlobals.rotationManager.applyRotation4(new Rotation(floatEntry3, float112), -1, this, this.getInt2());
                    return;
                }
            }

            double offset9 = minecraft.thePlayer.posX + MathHelper.getDouble7(minecraft.thePlayer.motionX * offset2, -offset2, offset2);
            double offset10 = minecraft.thePlayer.coordOffset9 - 0.5;
            double posZ = minecraft.thePlayer.posZ;
            double double74 = MathHelper.getDouble7(minecraft.thePlayer.motionZ * offset2, -offset2, offset2);
            double offset11 = posZ + double74;
            MovingObjectPosition movingObjectPosition42 = minecraft.theWorld
            .getMovingObjectPosition4(offset9, offset10, offset11, minecraft.thePlayer.posX, offset10, minecraft.thePlayer.posZ, false, false, true);
            if (MoveUtil.canExecute() && movingObjectPosition42 != null && movingObjectPosition42.typeOfHit == MovingObjectType.BLOCK) {
                this.placeTarget = new PlaceTarget(movingObjectPosition42.getBlockPos(), movingObjectPosition42.sideHit, movingObjectPosition42.hitVec);
                this.blockPos = this.placeTarget.getBlockPos();
                Rotation rotation24 = SimulatedPlayer.getRotation2(
                this.placeTarget.getHitVec().xCoord,
                this.placeTarget.getHitVec().yCoord,
                
                this.placeTarget.getHitVec().zCoord,
                ClientGlobals.rotationManager.getRotation3(),
                float3,
                float5,
                false
                );
                float floatEntry4 = this.simulatedPlayer.getFloat(float3, ClientGlobals.rotationManager.getRotation3().yaw, rotation24.yaw);
                float float113 = this.simulatedPlayer.getFloat11(float5, ClientGlobals.rotationManager.getRotation3().pitch, rotation24.pitch);
                ClientGlobals.rotationManager.applyRotation4(new Rotation(floatEntry4, float113), -1, this, this.getInt2());
            } else {
                ClientGlobals.rotationManager
                .applyRotation4(ClientGlobals.rotationManager.getRotation3(), -1, this, this.getInt2());
            }
        }
    }

    public boolean checkCondition(float value) {
        double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer, value);
        BlockPos blockPos3 = this.getBlockPos();
        if (blockPos3 == null) {
            return false;
        } else {
            double offset = (double)blockPos3.getX() + 1.285;
            double offset2 = (double)blockPos3.getX() - 0.285;
            double offset3 = (double)blockPos3.getZ() + 1.285;
            double offset4 = (double)blockPos3.getZ() - 0.285;
            return !(coordOffsetArray[0] <= offset) || !(coordOffsetArray[0] >= offset2) || !(coordOffsetArray[2] <= offset3) || !(coordOffsetArray[2] >= offset4);
        }
    }

    private double getCoordinate2(RayTraceHit movingObjectPositionEntry) {
        RotationManager rotationManager = ClientGlobals.rotationManager;
        return (double)Math.abs(movingObjectPositionEntry.scaleFactor - rotationManager.getRotation3().pitch);
    }

    public double getDouble5() {
        double offset = this.settingCorrectStrafeWithKeyBindsMiddle.recalculate();

        while (true) {
            if (!(offset > this.settingCorrectStrafeWithKeyBindsMiddleExclusion.getDouble3())) {
                break;
            }

            if (!(offset < this.settingCorrectStrafeWithKeyBindsMiddleExclusion.getDouble6())) {
                break;
            }

            double double9 = this.settingCorrectStrafeWithKeyBindsMiddle.getDouble9();
            if (!(double9 < this.settingCorrectStrafeWithKeyBindsMiddleExclusion.getDouble3())) {
                double double2 = this.settingCorrectStrafeWithKeyBindsMiddle.getDouble2();
                if (!(double2 > this.settingCorrectStrafeWithKeyBindsMiddleExclusion.getDouble6())) {
                    break;
                }
            }

            offset = this.settingCorrectStrafeWithKeyBindsMiddle.recalculate();
        }

        return offset;
    }

    private void onMove6(EventMove event) {
        if (event.getScaleFactor7() != 0.0F) {
            int index = event.getScaleFactor7() > 0.0F ? -1 : 1;
            this.tickDelay37 = index;
        } else if (event.getScaleFactor2() != 0.0F) {
            int index2 = event.getScaleFactor2() > 0.0F ? 1 : -1;
            this.tickDelay37 = index2;
        }

        if (this.tickDelay50 == 1) {
            this.settingMoonWalkWideness.recalculate();
            AnimationTimer animationTimer62 = this.animationTimer6;
            int indexOffset2 = this.settingDelayToRecalculateMiddle.getIndexOffset2();
            if (animationTimer62.checkCondition(indexOffset2)) {
                this.settingMoonWalkMiddle.recalculate();
                this.settingDelayToRecalculateMiddle.recalculate();
                this.animationTimer6.processAction3();
            }
        }

        this.tickDelay50 = 2;
    }

    private void placeScaffoldBlock6() {
        Pair pair = this.getPair();
        Rotation rotation2 = (Rotation)pair.getSecond();
        Rotation rotation3;
        if (this.shouldPlaceBlock13()) {
            this.randomEntry.coordOffset = this.getDouble4() / 100.0 * 16.0 + 2.0;
            this.randomEntry.state = true;
            Rotation rotation32 = ClientGlobals.rotationManager.getRotation3();
            Rotation rotation4 = this.rotation;
            if (!rotation32.checkRotation(rotation4) || this.customDoubleEntry == null || this.secondaryDoubleEntry == null) {
                SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
                Rotation rotation33 = ClientGlobals.rotationManager.getRotation3();
                this.customDoubleEntry = simulatedPlayer2.getCustomDoubleEntry(rotation33);
                SimulatedPlayer simulatedPlayer3 = this.simulatedPlayer;
                Rotation rotation34 = ClientGlobals.rotationManager.getRotation3();
                this.secondaryDoubleEntry = simulatedPlayer3.getSecondaryDoubleEntry(rotation34, rotation2, 3, 0.3, 10.0, false);
                this.tickDelay8 = 0;
            }

            if (this.tickDelay8 % 3 == 0) {
                SimulatedPlayer simulatedPlayer4 = this.simulatedPlayer;
                Rotation rotation35 = ClientGlobals.rotationManager.getRotation3();
                this.secondaryDoubleEntry = simulatedPlayer4.getSecondaryDoubleEntry(rotation35, rotation2, 3, 0.3, 10.0, false);
            }

            MotionVectorSnapshot secondaryDoubleEntry2 = this.secondaryDoubleEntry;
            SimulationBoundingBox customDoubleEntry2 = this.customDoubleEntry;
            RandomizationGenerator randomEntry2 = this.randomEntry;
            this.customDoubleEntry = this.simulatedPlayer.getCustomDoubleEntry(randomEntry2, customDoubleEntry2, secondaryDoubleEntry2, 3, 0.0);
            this.tickDelay8++;
            RotationManager rotationManager = ClientGlobals.rotationManager;
            SimulationBoundingBox customDoubleEntry3 = this.customDoubleEntry;
            float float9 = SimulatedPlayer.getFloat9(rotationManager.getRotation3().yaw, (float)customDoubleEntry3.getCoordinate2(), 180.0F);
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            SimulationBoundingBox customDoubleEntry4 = this.customDoubleEntry;
            float float92 = SimulatedPlayer.getFloat9(rotationManager2.getRotation3().pitch, (float)customDoubleEntry4.getCoordinate(), 180.0F);
            RotationManager rotationManager3 = ClientGlobals.rotationManager;
            RotationManager rotationManager4 = ClientGlobals.rotationManager;
            rotation3 = SimulatedPlayer.getRotation8(float9, float92, rotationManager3.getRotation3().yaw, rotationManager4.getRotation3().pitch);
            this.rotation = new Rotation(rotation3);
        } else {
            float float3 = this.getFloat3();
            float float5 = this.getFloat5();
            SimulatedPlayer simulatedPlayer6 = this.simulatedPlayer;
            float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
            float yaw2 = rotation2.yaw;
            RangeSetting rangeSetting2 = this.getRangeSetting2();
            RotationManager rotationManager5 = ClientGlobals.rotationManager;
            RotationManager rotationManager6 = ClientGlobals.rotationManager;
            float value = rotationManager5.getRotation3().yaw - rotationManager6.getRotation().yaw;
            float scaleFactor = simulatedPlayer6.getScaleFactor(float3, yaw, yaw2, rangeSetting2, value);
            SimulatedPlayer simulatedPlayer7 = this.simulatedPlayer;
            float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
            RotationManager rotationManager7 = ClientGlobals.rotationManager;
            float float32 = SimulatedPlayer.getFloat3(rotation2.pitch, rotationManager7.getRotation3().pitch);
            RangeSetting rangeSetting = this.getRangeSetting();
            RotationManager rotationManager8 = ClientGlobals.rotationManager;
            RotationManager rotationManager9 = ClientGlobals.rotationManager;
            float value2 = rotationManager8.getRotation3().pitch - rotationManager9.getRotation().pitch;
            rotation3 = new Rotation(scaleFactor, simulatedPlayer7.computeClampedTurnAngle(float5, pitch, float32, rangeSetting, value2));
        }

        if ((Boolean)pair.getFirst()) {
            float pitch2 = rotation3.pitch;
            float yaw3 = rotation3.yaw;
            this.applyMovingObjectPosition2(yaw3, pitch2, (MovingObjectPosition)null);
        } else {
            float yaw4 = rotation3.yaw;
            float pitch3 = rotation3.pitch;
            if (this.blockPos == null) {
                this.applyMovingObjectPosition2(yaw4, pitch3, (MovingObjectPosition)null);
            } else {
                if (this.shouldPlaceBlock3()) {
                    this.applyMovingObjectPosition2(yaw4, pitch3, (MovingObjectPosition)null);
                } else {
                    if (this.settingGodBridge.isEnabled()) {
                        this.applyMovingObjectPosition2(yaw4, pitch3, (MovingObjectPosition)null);
                        return;
                    }

                    if (this.settingRecycleRotation.isEnabled()) {
                        MovingObjectPosition hitResult = null;
                        boolean enabled = false;
                        boolean completed = false;
                        hitResult = CombatRayTrace.getMovingObjectPosition(1.0F, yaw4, pitch3);
                        if (!this.settingIntave.isEnabled() && this.settingTowerWhileMoving.isEnabled() && hitResult.getBlockPos() != null) {
                            if (!minecraft.thePlayer.onGround
                            && !(minecraft.thePlayer.posY <= (double)hitResult.getBlockPos().getY() - 2.0)
                            && minecraft.thePlayer.posY < (double)hitResult.getBlockPos().getY() - 2.5) {
                                enabled = true;
                                completed = true;
                            }
                        }
                        if (!completed) {
                            enabled = false;
                        }


                        if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
                            BlockPos blockPos3 = hitResult.getBlockPos();
                            if (BlockCache.checkBlockPos(blockPos3)
                            && hitResult.sideHit != EnumFacing.DOWN
                            && (enabled ? hitResult.sideHit == EnumFacing.UP : hitResult.sideHit != EnumFacing.UP)
                            && this.blockPos.checkBlockPos2(hitResult.getBlockPos())) {
                                this.applyMovingObjectPosition2(yaw4, pitch3, hitResult);
                                return;
                            }
                        }
                    }

                    if (this.checkCondition2(yaw4) && this.blockPos != null) {
                        float value3 = yaw4;
                        if (this.settingRandomizeYaw.isEnabled()
                        && this.settingRandomizeYawChanceWhilePitchRotation.getValue() > 0.0) {
                            RotationManager rotationManager10 = ClientGlobals.rotationManager;
                            if (yaw4 == rotationManager10.getRotation3().yaw) {
                                this.activeState12 = true;
                                float value4 = this.getFloat4(true) + yaw4;
                                RotationManager rotationManager11 = ClientGlobals.rotationManager;
                                yaw4 = SimulatedPlayer.getFloat2(value4, rotationManager11.getRotation3().yaw);
                            }
                        }

                        ArrayList arrayList = new ArrayList();
                        float value5 = 0.0F;
                        float value6 = 15.0F;

                        while (true) {
                            byte state;
                            if (!this.settingIntave.isEnabled() && !this.settingOtherRotation.isEnabled()) {
                                state = 90;
                            } else {
                                state = 85;
                            }

                            if (!(value6 < (float)state)) {
                                RayTraceHit movingObjectPositionEntry = this.getMovingObjectPositionEntry(arrayList, yaw4);
                                if (movingObjectPositionEntry != null) {
                                    Pair pair2 = this.getPair(movingObjectPositionEntry, yaw4);
                                    float second = (Float)pair2.getSecond();
                                    MovingObjectPosition hitResult2 = (MovingObjectPosition)pair2.getFirst();
                                    this.applyMovingObjectPosition(yaw4, second, hitResult2, true);
                                    return;
                                }

                                if (this.settingExtraBlockSearch.isEnabled()
                                && (!this.settingExtraBlockSearchOnlyAtClutch.isEnabled() || this.shouldPlaceBlock())) {
                                    if (minecraft.theWorld
                                    .getList2(
                                    minecraft.thePlayer,
                                    minecraft.thePlayer.getBoundingBox2().expand(0.0, -0.5, 0.0).offset(-0.001, 0.0, -0.001)
                                    )
                                    .isEmpty()
                                    && minecraft.thePlayer.onGround) {
                                        int index2 = Math.min(30, (int)this.settingYawSpeed.getDouble2());
                                        double offset = minecraft.thePlayer.posX + minecraft.thePlayer.motionX;
                                        double offset2 = minecraft.thePlayer.posY - 0.5;
                                        BlockPos blockPos4 = new BlockPos(offset, offset2, minecraft.thePlayer.posZ + minecraft.thePlayer.motionZ);
                                        int index3 = index2 * 2;
                                        ArrayList arrayList2 = new ArrayList();
                                        byte state2 = 0;
                                        float value7 = ClientGlobals.rotationManager.getRotation3().yaw + (float)index2;
                                        float value8 = ClientGlobals.rotationManager.getRotation3().yaw - (float)index2;

                                        for (int i = 0; index3 > 0; index3--) {
                                            if (state2 == 0) {
                                                i += 1;
                                                float value9 = yaw4 + (float)i;
                                                float value10 = yaw4 - (float)i;
                                                if (value9 > value7) {
                                                    state2 = 1;
                                                }

                                                if (value10 < value8) {
                                                    state2 = -1;
                                                }

                                                arrayList2.add(value9);
                                                arrayList2.add(value10);
                                                index3 -= 2;
                                            } else if (state2 > 0) {
                                                i += 1;
                                                index3--;
                                                arrayList2.add(yaw4 - (float)i);
                                            } else {
                                                i += 1;
                                                index3--;
                                                arrayList2.add(yaw4 + (float)i);
                                            }
                                        }

                                        for (float value11 : (Iterable<Float>) arrayList2) {
                                            arrayList.clear();
                                            float value12 = 0.0F;
                                            RotationManager rotationManager12 = ClientGlobals.rotationManager;
                                            float float2 = SimulatedPlayer.getFloat2(value11, rotationManager12.getRotation3().yaw);
                                            float value13 = 15.0F;

                                            while (true) {
                                                byte state3;
                                                if (!this.settingIntave.isEnabled() && !this.settingOtherRotation.isEnabled()) {
                                                    state3 = 90;
                                                } else {
                                                    state3 = 85;
                                                }

                                                if (!(value13 < (float)state3)) {
                                                    RayTraceHit movingObjectPositionEntry2 = this.getMovingObjectPositionEntry(arrayList, float2);
                                                    if (movingObjectPositionEntry2 != null) {
                                                        Pair pair3 = this.getPair(movingObjectPositionEntry2, float2);
                                                        float second2 = (Float)pair3.getSecond();
                                                        MovingObjectPosition hitResult3 = (MovingObjectPosition)pair3.getFirst();
                                                        this.applyMovingObjectPosition(float2, second2, hitResult3, true);
                                                        return;
                                                    }
                                                    break;
                                                }

                                                RotationManager rotationManager13 = ClientGlobals.rotationManager;
                                                float float33 = SimulatedPlayer.getFloat3(value13, rotationManager13.getRotation3().pitch);
                                                if (float33 != value12) {
                                                    MovingObjectPosition movingObjectPosition2 = CombatRayTrace.getMovingObjectPosition(1.0F, float2, float33);
                                                    if (movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK) {
                                                        BlockPos blockPos5 = movingObjectPosition2.getBlockPos();
                                                        if (BlockCache.checkBlockPos(blockPos5)
                                                        && movingObjectPosition2.sideHit != EnumFacing.DOWN
                                                        && movingObjectPosition2.sideHit != EnumFacing.UP
                                                        && (
                                                        movingObjectPosition2.getBlockPos().offset(movingObjectPosition2.sideHit).checkBlockPos(blockPos4)
                                                        || movingObjectPosition2.getBlockPos().checkBlockPos2(this.blockPos)
                                                        )
                                                        && (
                                                        movingObjectPosition2.hitVec.yCoord % 1.0 < 0.88
                                                        && movingObjectPosition2.hitVec.yCoord % 1.0 > 0.18
                                                        || !this.settingIntave.isEnabled()
                                                        )) {
                                                            arrayList.add(new RayTraceHit(movingObjectPosition2, float33));
                                                        }
                                                    }
                                                }

                                                value12 = float33;
                                                value13 += 0.04F;
                                            }
                                        }
                                    }
                                }

                                yaw4 = value3;
                                break;
                            }

                            RotationManager rotationManager14 = ClientGlobals.rotationManager;
                            float float34 = SimulatedPlayer.getFloat3(value6, rotationManager14.getRotation3().pitch);
                            if (float34 != value5) {
                                MovingObjectPosition hitResult4 = null;
                                boolean enabled3 = false;
                                boolean completed2 = false;
                                hitResult4 = CombatRayTrace.getMovingObjectPosition(1.0F, yaw4, float34);
                                if (!this.settingIntave.isEnabled() && this.settingTowerWhileMoving.isEnabled() && hitResult4.getBlockPos() != null) {
                                    if (!minecraft.thePlayer.onGround
                                    && !(minecraft.thePlayer.posY <= (double)hitResult4.getBlockPos().getY() - 2.0)
                                    && minecraft.thePlayer.posY < (double)hitResult4.getBlockPos().getY() - 2.5) {
                                        enabled3 = true;
                                        completed2 = true;
                                    }
                                }
                                if (!completed2) {
                                    enabled3 = false;
                                }


                                if (hitResult4.typeOfHit == MovingObjectType.BLOCK) {
                                    BlockPos blockPos6 = hitResult4.getBlockPos();
                                    if (BlockCache.checkBlockPos(blockPos6)
                                    && hitResult4.sideHit != EnumFacing.DOWN
                                    && (enabled3 ? hitResult4.sideHit == EnumFacing.UP : hitResult4.sideHit != EnumFacing.UP)
                                    && this.blockPos.checkBlockPos2(hitResult4.getBlockPos())
                                    && (
                                    hitResult4.hitVec.yCoord % 1.0 < 0.88
                                    && hitResult4.hitVec.yCoord % 1.0 > 0.18
                                    || !this.settingIntave.isEnabled()
                                    )) {
                                        arrayList.add(new RayTraceHit(hitResult4, float34));
                                    }
                                }
                            }

                            value5 = float34;
                            value6 += 0.04F;
                        }
                    }
                }

                this.applyMovingObjectPosition2(yaw4, pitch3, (MovingObjectPosition)null);
            }
        }
    }

    private float getFloat9(float value, boolean enabled) {
        if (!this.settingStrictYaw.isEnabled()) {
            return value;
        } else {
            float float4 = MathHelper.sqrt_float(value);
            if (float4 > -25.0F && float4 < 25.0F) {
                return this.getFloat7(0.0F, 0, enabled);
            } else if (float4 > -25.0F && float4 < 70.0F) {
                return this.getFloat7(45.0F, 1, enabled);
            } else if (float4 > 70.0F && float4 < 115.0F) {
                return this.getFloat7(90.0F, 2, enabled);
            } else if (float4 > 115.0F && float4 < 160.0F) {
                return this.getFloat7(135.0F, 3, enabled);
            } else if (float4 <= 160.0F) {
                if (float4 < -160.0F) {
                    return this.getFloat7(180.0F, 4, enabled);
                } else if (float4 > -160.0F && float4 < -115.0F) {
                    return this.getFloat7(-135.0F, 5, enabled);
                } else if (float4 > -115.0F && float4 < -70.0F) {
                    return this.getFloat7(-90.0F, 6, enabled);
                } else {
                    return float4 > -70.0F && float4 < -25.0F ? this.getFloat7(-45.0F, 7, enabled) : value;
                }
            } else {
                return this.getFloat7(180.0F, 4, enabled);
            }
        }
    }

    private RayTraceHit getMovingObjectPositionEntry(ArrayList arrayList, float value) {
        if (arrayList.isEmpty()) {
            return null;
        } else {
            String selected = this.settingPitchSelection.getSelected();
            selected.hashCode();
            byte state = -1;
            if (selected.equals("Nearest")) {
                state = 0;
            } else if (selected.equals("Furthest")) {
                state = 1;
            } else if (selected.equals("Highest")) {
                state = 2;
            } else if (selected.equals("Lowest")) {
                state = 3;
            } else if (selected.equals("Mid")) {
                state = 4;
            } else if (selected.equals("Random")) {
                state = 5;
            }

            switch (state) {
                case 0:
                    arrayList.sort(Comparator.comparingDouble(object -> this.getCoordinate2((RayTraceHit)object)));
                    return (RayTraceHit)arrayList.get(0);
                case 1:
                    arrayList.sort(Comparator.comparingDouble(object2 -> this.getCoordinate((RayTraceHit)object2)));
                    return (RayTraceHit)arrayList.get(0);
                case 2:
                case 3:
                    RayTraceHit movingObjectPositionEntry = null;

                    for (RayTraceHit movingObjectPositionEntry2 : (Iterable<RayTraceHit>) arrayList) {
                        if (movingObjectPositionEntry2 != null && (movingObjectPositionEntry == null || (state == 2 ? movingObjectPositionEntry2.scaleFactor > movingObjectPositionEntry.scaleFactor : movingObjectPositionEntry2.scaleFactor < movingObjectPositionEntry.scaleFactor))) {
                            movingObjectPositionEntry = movingObjectPositionEntry2;
                        }
                    }

                    return movingObjectPositionEntry;
                case 4:
                    arrayList.sort(Comparator.comparingDouble(object3 -> this.getCoordinate(value, (RayTraceHit)object3)));
                    return (RayTraceHit)arrayList.get(0);
                case 5:
                    int int2 = SettingContainer.settingProvider.getInt2(0, arrayList.size() - 1);
                    return (RayTraceHit)arrayList.get(int2);
                default:
                    return null;
            }
        }
    }

    private boolean isRandomizePitchChanceWhileYawRotationVisible() {
        return !this.settingRandomizePitch.isEnabled() ? false : this.settingRandomizePitch.isVisible();
    }

    private boolean checkCondition() {
        return minecraft.thePlayer.onGround && this.settingForcePlaceIfYouWouldFallDown.isEnabled()
        ? CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer)[9] != 1.0
        : false;
    }

    private boolean isRemoveRotationDeltaLimitAtBlockClutchVisible() {
        return this.settingMaxYawRotationDeltaToPlaceBlocks.getValue() < 180.0
        ? true
        : this.settingMaxPitchRotationDeltaToPlaceBlocks.getValue() < 180.0;
    }

    private boolean isIgnoreStartAtEdgeAtBlockClutchVisible() {
        return !this.settingStartAtEdge.isEnabled()
        ? false
        : this.settingStartAtEdgeDelay.getDouble2() > 0.0;
    }

    private boolean isIgnoreMaxPitchCorrectionIfBlockClutchVisible() {
        return !this.checkCondition18();
    }

    private boolean isPitchAccelerationVisible() {
        return !this.settingModeNew.isVisible() || !this.settingModeNew.isEnabled();
    }

    private boolean shouldPlaceBlock2() {
        return this.checkCondition2(ClientGlobals.rotationManager.getRotation3().yaw);
    }

    private boolean isSilentSwitchDelayVisible() {
        return this.settingSilentMode.getSelected().equals("Default") || this.settingSilentMode.getSelected().equals("Legit");
    }

    private boolean isBlockClutchModeNewVisible() {
        return !this.settingExpand.isEnabled();
    }

    private boolean isRoundFactorVisible() {
        return !this.settingBlockCount.isEnabled() ? false : this.settingBackGround.isEnabled();
    }

    private boolean isSilentStartDelayVisible() {
        return this.settingSilentMode.getSelected().equals("Default") || this.settingSilentMode.getSelected().equals("Legit");
    }

    private boolean shouldPlaceBlock3() {
        if (!minecraft.gameSettings.keyBindJump.isKeyDown()) {
            return false;
        } else {
            return Math.abs(minecraft.thePlayer.motionZ) >= 0.01
            ? false
            : Math.abs(minecraft.thePlayer.motionX) < 0.01;
        }
    }

    public boolean shouldPlaceBlock4() {
        if (this.blockPos == null) {
            return false;
        } else {
            double offset = 1.282;
            double offset2 = 0.282;
            double posX = minecraft.thePlayer.posX;
            double posZ = minecraft.thePlayer.posZ;
            double offset3 = (double)this.blockPos.getX() + offset;
            double offset4 = (double)this.blockPos.getX() - offset2;
            double offset5 = (double)this.blockPos.getZ() + offset;
            double offset6 = (double)this.blockPos.getZ() - offset2;
            return !(posX <= offset3) || !(posX >= offset4) || !(posZ <= offset5) || !(posZ >= offset6);
        }
    }

    private boolean isBlockClutchPitchAccelerationVisible() {
        return !this.settingBlockClutchModeNew.isVisible() || !this.settingBlockClutchModeNew.isEnabled();
    }

    public boolean shouldPlaceBlock5() {
        return this.shouldPlaceBlock2() && this.settingEdgeRotateCheck.isEnabled() && this.settingEdgePlaceCheck.isEnabled()
        ? true
        : !this.settingEdgeRotateCheck.isEnabled() || !this.settingEdgePlaceCheck.isEnabled();
    }

    private boolean shouldPlaceBlock6() {
        return this.settingIgnoreStaticPitchForBlockClutches.isEnabled() && this.settingStaticPitchBool.isEnabled()
        ? this.shouldPlaceBlock()
        : false;
    }

    private boolean isBlockClutchPitchSpeedVisible() {
        return !this.settingBlockClutchModeNew.isVisible() || !this.settingBlockClutchModeNew.isEnabled();
    }

    private boolean isRandomizeYawValVisible() {
        if (this.settingOtherYawRandomization.isEnabled()) {
            return false;
        } else {
            return !this.settingRandomizeYaw.isEnabled() ? false : this.settingRandomizeYaw.isVisible();
        }
    }

    private boolean checkCondition17() {
        if (minecraft.thePlayer.onGround) {
            return false;
        } else if (!this.settingTowerWhileMoving.isEnabled()) {
            return false;
        } else {
            return !MoveUtil.canExecute()
            ? false
            : minecraft.thePlayer.posY - minecraft.thePlayer.coordOffset9 > 1.0;
        }
    }

    private boolean isRandomizeYawIntervalVisible() {
        if (this.settingOtherYawRandomization.isEnabled()) {
            return false;
        } else {
            return !this.settingRandomizeYaw.isEnabled() ? false : this.settingRandomizeYaw.isVisible();
        }
    }

    private boolean isRandomizeYawChanceWhilePitchRotationVisible() {
        return !this.settingRandomizeYaw.isEnabled() ? false : this.settingRandomizeYaw.isVisible();
    }

    private boolean shouldPlaceBlock7() {
        if (!this.settingImprove.isEnabled()) {
            return false;
        } else {
            return !this.settingImproveOnlyAtClutch.isEnabled() ? true : this.shouldPlaceBlock8();
        }
    }

    private boolean isMinOffGroundSneakTickDiagonalVisible() {
        return !this.settingSneak.isEnabled() ? false : this.settingOneStackDiagonal.isEnabled();
    }

    private boolean isBlockCountVisible() {
        return !this.settingBlockCount.isEnabled() ? false : this.settingBackGround.isEnabled();
    }

    private boolean isMaxOffGroundSneakTickForwardVisible() {
        return !this.settingSneak.isEnabled() ? false : this.settingOneStackForward.isEnabled();
    }

    private boolean isIntaveFast2Visible() {
        return !this.settingSameY.isEnabled() ? false : this.settingIntave.isEnabled();
    }

    private int getInt3() {
        int index2 = 0;

        for (int i = 0; i < 9; i++) {
            ItemStack itemStack = minecraft.thePlayer.inventoryPlayer.processAction3(i);
            if (itemStack != null && itemStack.getItem() instanceof ItemBlock && BlockCache.checkItemStack(itemStack)) {
                index2 += itemStack.animationsToGo;
            }
        }

        return index2;
    }

    private int getInt4() {
        if (!this.settingSilentMode.getSelected().equals("Exploit")) {
            return -1;
        } else {
            int index = this.getIndex();
            int indexOffset = minecraft.thePlayer.inventoryPlayer.currentItem;
            if (index >= 0) {
                minecraft.thePlayer.inventoryPlayer.currentItem = index;
            }

            return indexOffset;
        }
    }

    private int getInt5() {
        for (int i = 36; i < minecraft.thePlayer.container.itemList2.size(); i++) {
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(i).getStack();
            if (itemStack == null) {
                return i - 36;
            }

            if (!(itemStack.getItem() instanceof ItemBlock) || !BlockCache.checkItemStack(itemStack)) {
                return i - 36;
            }
        }

        return -1;
    }

    private boolean isBlockClutchYawSpeedVisible() {
        return !this.settingBlockClutchModeNew.isVisible() || !this.settingBlockClutchModeNew.isEnabled();
    }

    private boolean isCorrectStrafeWithKeyBindsMiddleVisible() {
        return !this.settingCorrectStrafeWithKeyBinds.isEnabled() ? false : this.settingCorrectStrafeWithKeyBinds.isVisible();
    }

    private boolean isMoveFixIntaveVisible() {
        return !this.settingMoveFix.getSelected().equalsIgnoreCase("None");
    }

    private boolean isMoveFixDefault() {
        return this.settingMoveFix.getSelected().equals("Default");
    }

    private boolean isRandomizeYawChanceOnAirVisible() {
        return !this.settingRandomizeYaw.isEnabled() ? false : this.settingRandomizeYaw.isVisible();
    }

    private boolean isRandomizeYawChanceOnBlockVisible() {
        return !this.settingRandomizeYaw.isEnabled() ? false : this.settingRandomizeYaw.isVisible();
    }

    private boolean isMoonWalkStartDistToBlockVisible() {
        return !this.settingMoonWalk.isEnabled() ? false : this.settingMoonWalk.isVisible();
    }

    private boolean isCorrectStrafeWithKeyBindsMiddleExclusionVisible() {
        return !this.settingCorrectStrafeWithKeyBinds.isEnabled() ? false : this.settingCorrectStrafeWithKeyBinds.isVisible();
    }

    private boolean isCorrectStrafeWithKeyBindsCageVisible() {
        return !this.settingCorrectStrafeWithKeyBinds.isEnabled() ? false : this.settingCorrectStrafeWithKeyBinds.isVisible();
    }

    private boolean isStackCountToStartSwitchProcessVisible() {
        return this.settingSilentMode.getSelected().equals("Default") || this.settingSilentMode.getSelected().equals("Legit");
    }

    private boolean isTowerModeConstantMotion() {
        return this.settingTowerMode.getSelected().equals("ConstantMotion");
    }

    private boolean isMaxOffGroundSneakTickDiagonalVisible() {
        return !this.settingSneak.isEnabled() ? false : this.settingOneStackDiagonal.isEnabled();
    }

    private boolean isTowerWhileMovingVisible() {
        return !this.settingTowerMode.getSelected().equals("Legit");
    }

    private boolean shouldPlaceBlock8() {
        return this.settingImproveOnlyAtClutch.isEnabled() && this.settingImprove.isEnabled() ? this.shouldPlaceBlock() : false;
    }

    private boolean shouldPlaceBlock9() {
        float float4 = MathHelper.sqrt_float(ClientGlobals.rotationManager.getRotation3().yaw);
        if (!this.settingIntave.isEnabled()) {
            return false;
        } else if (!this.settingIntaveFast.isEnabled()) {
            return false;
        } else if (!MoveUtil.canExecute()) {
            return false;
        } else if (this.shouldPlaceBlock3()) {
            return false;
        } else {
            if ((
            float4 <= -2.0F
            || float4 >= 2.0F
            )
            && (
            float4 <= 88.0F
            || float4 >= 92.0F
            )
            && float4 >= -178.0F
            && float4 <= 178.0F) {
                if (float4 >= -88.0F) {
                    return false;
                }

                if (float4 <= -92.0F) {
                    return false;
                }
            }

            return !minecraft.thePlayer.onGround ? false : !minecraft.gameSettings.keyBindJump.pressed;
        }
    }

    private boolean isYawAccelerationVisible() {
        return !this.settingModeNew.isVisible() || !this.settingModeNew.isEnabled();
    }

    private boolean isPitchSpeedVisible() {
        return !this.settingModeNew.isVisible() || !this.settingModeNew.isEnabled();
    }

    private boolean isCorrectStrafeWithKeyBindsDurationVisible() {
        return !this.settingCorrectStrafeWithKeyBinds.isEnabled() ? false : this.settingCorrectStrafeWithKeyBinds.isVisible();
    }

    private boolean shouldPlaceBlock10() {
        return this.settingIgnoreStaticPitchForBlockClutchesDiagonal.isEnabled() && this.settingStaticPitchBoolDiagonal.isEnabled()
        ? this.shouldPlaceBlock()
        : false;
    }

    private boolean isOtherPitchRandomizationVisible() {
        return !this.settingRandomizePitch.isEnabled() ? false : this.settingRandomizePitch.isVisible();
    }

    private boolean isYawSpeedVisible() {
        return !this.settingModeNew.isVisible() || !this.settingModeNew.isEnabled();
    }

    private boolean shouldPlaceBlock11() {
        return !this.settingClickPause.isEnabled() || this.internalManager.canExecute();
    }

    private boolean isAimSpeedVisible() {
        return !this.settingModeNew.isEnabled() ? false : this.settingModeNew.isVisible();
    }

    private boolean isBlockClutchAimSpeedVisible() {
        return !this.settingBlockClutchModeNew.isEnabled() ? false : this.settingBlockClutchModeNew.isVisible();
    }

    private boolean isStrafeWidthVisible() {
        return !this.settingStrafe.isEnabled()
        ? false
        : this.settingStrafeDegree.getDouble9() <= 25.0;
    }

    private boolean isMoonWalkMiddleVisible() {
        return !this.settingMoonWalk.isEnabled() ? false : this.settingMoonWalk.isVisible();
    }

    private boolean isMinOffGroundSneakTickForwardVisible() {
        return !this.settingSneak.isEnabled() ? false : this.settingOneStackForward.isEnabled();
    }

    private boolean isRandomizePitchValVisible() {
        if (this.settingOtherPitchRandomization.isEnabled()) {
            return false;
        } else {
            return !this.settingRandomizePitch.isEnabled() ? false : this.settingRandomizePitch.isVisible();
        }
    }

    private boolean isTimerSpeedVisible() {
        return !this.settingTimerDrop.isVisible() ? false : this.settingTimerDrop.isEnabled();
    }

    private boolean isBlockClutchYawAccelerationVisible() {
        return !this.settingBlockClutchModeNew.isVisible() || !this.settingBlockClutchModeNew.isEnabled();
    }

    private boolean shouldPlaceBlock12() {
        return minecraft.isIntegratedServerRunning() || minecraft.currentScreen instanceof ClickGuiScreen;
    }

    private boolean isModeNewVisible() {
        return !this.settingExpand.isEnabled();
    }

    private boolean checkCondition18() {
        return this.settingExpand.isEnabled();
    }

    private boolean isRandomizePitchChanceInAirVisible() {
        return !this.settingRandomizePitch.isEnabled() ? false : this.settingRandomizePitch.isVisible();
    }

    private boolean shouldPlaceBlock13() {
        return this.shouldPlaceBlock() && this.settingCustomBlockClutchRotationSpeeds.isEnabled()
        ? this.settingBlockClutchModeNew.isEnabled()
        : this.settingModeNew.isEnabled();
    }

    private boolean isMoonWalkWidenessVisible() {
        return !this.settingMoonWalk.isEnabled() ? false : this.settingMoonWalk.isVisible();
    }

    private boolean isDelayToRecalculateMiddleVisible() {
        return !this.settingMoonWalk.isEnabled() ? false : this.settingMoonWalk.isVisible();
    }

    private boolean isImproveOnlyAtClutchVisible() {
        return this.settingImprove.isEnabled();
    }

    private boolean isDCPSModeExternal() {
        return this.settingDCPSMode.getSelected().equals("External");
    }

    private boolean isMaxPitchCorrectionVisible() {
        return !this.checkCondition18();
    }

    private boolean isTowerModeJump() {
        return this.settingTowerMode.getSelected().equals("Jump");
    }

    private boolean isCorrectStrafeWithKeyBindsWidthVisible() {
        return !this.settingCorrectStrafeWithKeyBinds.isEnabled() ? false : this.settingCorrectStrafeWithKeyBinds.isVisible();
    }

    private boolean isSilentModeDefault() {
        return this.settingSilentMode.getSelected().equals("Default");
    }

    private boolean isUseBiggestStackSilentVisible() {
        return !this.settingSilentMode.getSelected().equals("None");
    }

    private boolean isDynamicBackSwitchTickDelayVisible() {
        return !this.settingSilentMode.getSelected().equals("Default")
        ? false
        : this.settingBackSwitchTickDelay.getDouble9() > 0.0;
    }

    private boolean isIgnoreStartDelayAfterDirectionChangeAtBlockClutchVisible() {
        return !this.settingStrictYaw.isEnabled()
        ? false
        : this.settingStartDelayAfterDirectionChange.getDouble2() > 0.0;
    }
}
