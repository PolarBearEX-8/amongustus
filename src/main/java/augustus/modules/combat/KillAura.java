package augustus.modules.combat;

import augustus.custom.RuntimeBridge;
import augustus.modes.combat.AuraRenderMode;
import augustus.modules.render.Line;
import client.Client;
import client.ClientGlobals;
import client.config.ClickPatternManager;
import client.config.ClickPatternProfile;
import client.event.ClickListener;
import client.event.EventChat;
import client.event.EventChatCallback;
import client.event.EventClick;
import client.event.EventListener;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSilentMove;
import client.event.EventWorldLoad;
import client.event.MotionListener;
import client.event.MotionUpdateListener;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.RenderTickListener;
import client.event.RotationListener;
import client.event.SilentMoveListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.gui.ClickGuiScreen;
import client.module.Category;
import client.module.Module;
import client.render.AttackRangeRenderer;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.ActionSetting;
import client.setting.AutoClickerComboBreakerType;
import client.setting.BooleanSetting;
import client.setting.BooleanSettingProperty;
import client.setting.ColorSetting;
import client.setting.KillAuraHurtTime;
import client.setting.KillAuraTargetEntity;
import client.setting.KillAuraWeaponSlot;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.setting.ShaderOptions;
import client.setting.TargetFilterSetting;
import client.util.AnimationTimer;
import client.util.AttackTargetDistance;
import client.util.AttackTargetHitCheck;
import client.util.AttackTimeTracker;
import client.util.BacktrackRecord;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.ClickHelper;
import client.util.ClickHistoryBuffer;
import client.util.CombatRayTrace;
import client.util.CombatUtil;
import client.util.ConsumerTask;
import client.util.DefaultVertexFormats;
import client.util.EntitySelectPredicates;
import client.util.LivingEntityFilter;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.Pair;
import client.util.PlayerSimulationData;
import client.util.PlayerUtil;
import client.util.PredictedHitPoint;
import client.util.RayTraceUtil;
import client.util.RayTraceUtils;
import client.util.RunnableTask;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import client.util.TrajectoryPredictor;
import client.util.UseEntityAction;
import client.via.ViaLoadingBase;
import com.google.common.base.Predicates;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.awt.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import net.minecraft.block.BlockCache;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemCallback;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class KillAura
extends Module
implements WorldLoadListener,
RotationListener,
EventChatCallback,
ClickListener,
ItemCallback,
TickListener,
MoveListener,
MotionUpdateListener,
RenderListener,
RenderTickListener,
PacketListener,
SilentMoveListener,
MotionListener,
MoveEntityListener {
    public SimulatedPlayer simulatedPlayer;
    public SettingGroup settingRange2;
    public RangeSetting settingPreAimRange;
    public NumberSetting settingRange;
    public ModeSetting settingSimple;
    public NumberSetting settingHazeAdd;
    public NumberSetting settingHazeMax;
    public ModeSetting settingAttacks;
    public NumberSetting settingInterval;
    public NumberSetting settingRangeAtInterval;
    public NumberSetting settingMinReachToIntervalHit;
    public NumberSetting settingHurtTimeToHitWithReach;
    public ActionSetting settingExtendedRange;
    public NumberSetting settingChanceOfExtendedRange;
    public SettingGroup settingRotations;
    public NumberSetting settingYawFov;
    public NumberSetting settingPitchFov;
    public RangeSetting settingYawSpeed;
    public RangeSetting settingPitchSpeed;
    public RangeSetting settingYawAcceleration;
    public RangeSetting settingPitchAcceleration;
    public BooleanSetting settingBetterAcceleration;
    public SettingGroup settingFixRotations;
    public ModeSetting settingNone4;
    public NumberSetting settingFixRotationChance;
    public RangeSetting settingMaxFixRotationReach;
    public NumberSetting settingMinFixRotationAngle;
    public ModeSetting settingNone;
    public NumberSetting settingMinDistanceToBestVecYaw;
    public NumberSetting settingMinDistanceToBestVecPitch;
    public NumberSetting settingMinDistanceToBestVec;
    public ModeSetting settingNone2;
    public ModeSetting settingBestHitVec;
    public NumberSetting settingHitBoxPercentageVertical2;
    public NumberSetting settingHitBoxPercentageHorizontal2;
    public NumberSetting settingHitBoxPercentageVertical;
    public NumberSetting settingHitBoxPercentageHorizontal;
    public SettingGroup settingRandomize;
    public ModeSetting settingSecureRandom;
    public ModeSetting settingOnlyRotation;
    public NumberSetting settingRandomStrength;
    public BooleanSetting settingReducedRotate;
    public NumberSetting settingReduceRotationPercentage;
    public BooleanSetting settingSmartRotate;
    public BooleanSetting settingPitchOutliers;
    public RangeSetting settingPitchOutliersAdd;
    public RangeSetting settingPitchOutliersDelay;
    public ActionSetting settingRadius;
    public NumberSetting settingBestHitXMinDist;
    public NumberSetting settingBestHitYMInDist;
    public NumberSetting settingMinAbsDist;
    public NumberSetting settingMaxXAbsDist;
    public NumberSetting settingMaxYAbsDist;
    public ActionSetting settingXRelDist;
    public ActionSetting settingYRelDist;
    public NumberSetting settingMaxRotationAngle;
    public NumberSetting settingAdditionalMinAimspeed;
    public NumberSetting settingAdditionalDynamicSpeed;
    public NumberSetting settingAdditionalAimspeedIfNeeded;
    public NumberSetting settingProbabilityOfAimingEvenWhenNotNeeded;
    public BooleanSetting settingTest;
    public NumberSetting settingAimSpeed;
    public NumberSetting settingDecelerationfactorPre;
    public NumberSetting settingDecelerationfactorPost;
    public ActionSetting settingReactionTime;
    public ActionSetting settingLookaheadTime;
    public NumberSetting settingAttention;
    public ActionSetting settingPredictionInaccuracy;
    public NumberSetting settingPredictionInaccuracySmoothness;
    public SettingGroup settingSuperCorrection;
    public NumberSetting settingSuperCorrectionProbability;
    public RangeSetting settingSuperCorrectionDelay;
    public BooleanSetting settingAdditionalAccuracy;
    public SettingGroup settingRotationOffset;
    public ActionSetting settingRotationYawOffset;
    public ActionSetting settingRotationPitchOffset;
    public NumberSetting settingRotationYawOffsetSpeed;
    public NumberSetting settingRotationPitchOffsetSpeed;
    public NumberSetting settingAdditionalInaccuracy;
    public ActionSetting settingOrthogonalScale;
    public NumberSetting settingMaxOrthogonalMovement;
    public NumberSetting settingMaxAimpointAnglediff;
    public RangeSetting settingBestvecPitchinterpolation;
    public ActionSetting settingRotationOnTarget;
    public BooleanSetting settingNoYawAdjustmentIfTargetOutOfReach;
    public BooleanSetting settingNoPitchAdjustmentIfTargetOutOfReach;
    public BooleanSetting settingApplyRecordedMouseJitterMovementIfAvailable;
    public BooleanSetting settingUltimateTrashcodeBypass;
    public BooleanSetting settingAdaptCurveIfNeeded;
    public RangeSetting settingAimSpeed2;
    public RangeSetting settingAcceleration;
    public NumberSetting settingBetterAcceleration2;
    public NumberSetting settingScale;
    public ActionSetting settingTargetResponse;
    public ActionSetting settingPlayerResponse;
    public BooleanSetting settingSmartAim;
    public BooleanSetting settingSmartAimEntityCheck;
    public BooleanSetting settingAdvancedRots;
    public BooleanSetting settingTimerRangeV2Fix;
    public BooleanSetting settingInterpolation;
    public BooleanSetting settingLockView;
    public BooleanSetting settingStopRotationInsideTarget;
    public BooleanSetting settingAimThroughWalls;
    public RangeSetting settingAimThroughWallsRange;
    public BooleanSetting settingThroughWalls;
    public NumberSetting settingThroughWallsRange;
    public BooleanSetting settingNoRotation;
    public SettingGroup settingClicking;
    public SettingGroup settingClickSettings;
    public ModeSetting settingAdvanced;
    public NumberSetting settingCPSLimiter;
    public BooleanSetting settingCPSLimiterIntave;
    public ActionSetting settingMinDelay;
    public RangeSetting settingCPS2;
    public RangeSetting settingCPSRecalculateDelay;
    public ActionSetting settingClickDutyCycle;
    public BooleanSetting settingDoubleClicks;
    public BooleanSetting settingExtraDoubleClickRandomize;
    public ActionSetting settingMinCPS;
    public BooleanSetting settingDoubleClick;
    public NumberSetting settingAirMultiplier2;
    public ModeSetting settingClickPattern;
    public ModeSetting settingTimeMillis;
    public NumberSetting settingMultiplier;
    public NumberSetting settingAirMultiplier;
    public ActionSetting settingCPS;
    public ActionSetting settingMinDelay2;
    public BooleanSetting settingSpikes;
    public NumberSetting settingSpikeDelay;
    public ActionSetting settingSpikeDuration;
    public NumberSetting settingSpikeMulti;
    public BooleanSetting settingDrops;
    public NumberSetting settingDropDelay;
    public ActionSetting settingDropDuration;
    public NumberSetting settingDropMulti;
    public BooleanSetting settingReduceInAir;
    public ActionSetting settingReduceMulti;
    public ActionSetting settingReduceRecalculateDelay;
    public BooleanSetting settingReduceHitsOnWall;
    public RangeSetting settingReduceRecalculateDelayOnWall;
    public RangeSetting settingReduceMultiplierOnWall;
    public MultiSelectSetting settingHurttimesToHit;
    public ModeSetting settingMode;
    public ModeSetting settingClickMode;
    public ModeSetting settingDoubleClickMode;
    public SettingGroup settingCPSSettings;
    public SettingGroup settingInAir;
    public RangeSetting settingCPSInAir;
    public RangeSetting settingCPSInAirRecalculateTickDelay;
    public RangeSetting settingDCPSInAir;
    public RangeSetting settingDCPSInAirRecalculateTickDelay;
    public SettingGroup settingOnWall;
    public RangeSetting settingCPSOnWall;
    public RangeSetting settingCPSOnWallRecalculateTickDelay;
    public RangeSetting settingDCPSOnWall;
    public RangeSetting settingDCPSOnWallRecalculateTickDelay;
    public SettingGroup settingOnFirstHit;
    public RangeSetting settingCPSFirstHit;
    public RangeSetting settingCPSFirstHitRecalculateTickDelay;
    public RangeSetting settingDCPSFirstHit;
    public RangeSetting settingDCPSFirstHitRecalculateTickDelay;
    public SettingGroup settingOnTarget;
    public RangeSetting settingCPS3;
    public RangeSetting settingCPSRecalculateTickDelay;
    public RangeSetting settingDCPS;
    public RangeSetting settingDCPSRecalculateTickDelay;
    public SettingGroup settingInteractions;
    public ModeSetting settingLegit;
    public NumberSetting settingMaxTargets;
    public ModeSetting settingNone3;
    public BooleanSetting settingSmartBlock;
    public BooleanSetting settingInteractAutoBlock;
    public BooleanSetting settingOnlyRightClickBlock;
    public BooleanSetting settingOnlyWhileSwingInProgress;
    public BooleanSetting settingOnlyWhileHurt;
    public NumberSetting settingUnblockAAC;
    public BooleanSetting settingPreHit;
    public BooleanSetting settingSmartPreHit;
    public RangeSetting settingPrediction;
    public RangeSetting settingLoseDelay;
    public RangeSetting settingEndurance;
    public RangeSetting settingEnduranceReduction;
    public RangeSetting settingPreHitRange;
    public RangeSetting settingPreHitFovYaw;
    public RangeSetting settingPreHitFovPitch;
    public BooleanSetting settingMissClick;
    public NumberSetting settingMissClickPercentage;
    public BooleanSetting settingPerfectHit;
    public BooleanSetting settingLongClick;
    public BooleanSetting settingCritFix;
    public BooleanSetting settingCritFixPacketCheck;
    public BooleanSetting settingNoLoopHit;
    public BooleanSetting settingFixBeta;
    public BooleanSetting settingBreakBlocks;
    public BooleanSetting settingDoubleClickFirstHit;
    public BooleanSetting settingReduceCPSIfYouCanNotHitATarget;
    public BooleanSetting settingNoSwing;
    public BooleanSetting settingSlowDown;
    public BooleanSetting settingCoolDown;
    public SettingGroup settingMovement;
    public BooleanSetting settingJumpFix;
    public BooleanSetting settingMoveFix;
    public ModeSetting settingDefault;
    public BooleanSetting settingSilentMoveFixIntave;
    public SettingGroup settingTargets2;
    public MultiSelectSetting settingTargets;
    public ModeSetting settingUltimateSwitch;
    public BooleanSetting settingSwitchOnlyToTargetsInRange;
    public NumberSetting settingTargetDelay;
    public RangeSetting settingTargetLoseDelay;
    public RangeSetting settingMaxDeathTime;
    public SettingGroup settingMiscellaneous;
    public BooleanSetting settingCloseGuisIfTargetArrives;
    public BooleanSetting settingInInv;
    public MultiSelectSetting settingActiveSlots;
    public TargetFilterSetting auxiliarySettingAdapter;
    public BooleanSetting settingIntave13;
    public RangeSetting settingDelayRotationsAfterClosingGuiScreen;
    public RangeSetting settingDelayClicksAfterClosingGuiScreen;
    public RangeSetting settingDelayRotationsAfterRespawn;
    public RangeSetting settingDelayClicksAfterRespawn;
    public SettingGroup settingVisuals;
    public BooleanSetting settingDot;
    public NumberSetting settingDotSize;
    public ColorSetting settingDotColor;
    public ShaderOptions shaderOptions;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public TimeHelper timer4;
    public TimeHelper timer5;
    public TimeHelper timer6;
    public ClickPatternProfile clickPatternProfile;
    public ArrayList itemList3;
    public double reachDistance;
    public EntityLivingBase target;
    public Vec3 positionVec;
    public long timestampMillis23;
    public long timestampMillis18;
    public long timestampMillis20;
    public double reachDistance4;
    public long timestampMillis1;
    public long timestampMillis21;
    public long timestampMillis30;
    public long timestampMillis2;
    public EntityLivingBase targetEntity2;
    public EntityLivingBase targetEntity3;
    public int tickDelay5;
    public CopyOnWriteArrayList copyOnWriteArrayList;
    public int tickDelay;
    public TimeHelper timer7;
    public TimeHelper timer8;
    public ClickHistoryBuffer auxiliaryArrayListEntry;
    public Rotation rotation;
    public int[] intValueArray;
    public long timestampMillis25;
    public int tickDelay12;
    public boolean activeState3;
    public MovingObjectPosition movingObjectPosition;
    public long timestampMillis26;
    public long timestampMillis;
    public long timestampMillis24;
    public boolean activeState19;
    public boolean activeState31;
    public int tickDelay16;
    public boolean activeState14;
    public List itemList4;
    public String name2;
    public boolean activeState32;
    public TimeHelper timer9;
    public TimeHelper timer10;
    public double reachDistance8;
    public long timestampMillis17;
    public boolean activeState;
    public int tickDelay10;
    public int tickDelay13;
    public boolean activeState28;
    public Vec3 positionVec2;
    public AnimationTimer[] animationTimerArray;
    public SmoothDamper[] smoothDamperArray;
    public RangeSetting[] rangeSettingArray;
    public AnimationTimer animationTimer;
    public AnimationTimer animationTimer2;
    public AnimationTimer animationTimer3;
    public AnimationTimer animationTimer4;
    public AnimationTimer animationTimer5;
    public AnimationTimer animationTimer6;
    public AnimationTimer animationTimer7;
    public AnimationTimer animationTimer8;
    public SmoothDamper smoothDamper;
    public SmoothDamper smoothDamper2;
    public AttackTimeTracker auxiliaryLongEntry;
    public AnimationTimer animationTimer9;
    public TimeHelper timer11;
    public AnimationTimer animationTimer10;
    public int tickDelay6;
    public int tickDelay7;
    public int tickDelay11;
    public boolean expanded2;
    public SmoothDamper smoothDamper3;
    public BacktrackRecord backtrackRecord;
    public int tickDelay15;
    public AttackTargetDistance doubleEntry27;
    public AttackTargetDistance doubleEntry;
    public AttackTargetDistance doubleEntry33;
    public AttackTargetDistance doubleEntry29;
    public AttackTargetDistance doubleEntry34;
    public boolean activeState22;
    public int[] intValueArray2;
    public double[] coordOffsetArray;
    public boolean visible2;
    public int tickDelay9;
    public boolean enabled2;

    public KillAura() {
        super("KillAura", "AutomatischerTöter", new Color(102, 38, 28, 255), Category.COMBAT);
        SimulatedPlayer simulatedPlayer = new SimulatedPlayer();
        this.simulatedPlayer = simulatedPlayer;
        SettingGroup settingGroup = new SettingGroup(this, "Range", true);
        this.settingRange2 = settingGroup;
        this.settingPreAimRange = new RangeSetting(this, "PreAimRange", 6.0, 6.0, 0.0, 15.0, 1, this.settingRange2).getRangeSetting2();
        this.settingRange = new NumberSetting(this, "Range", 3.0, 3.0, 6.0, 3, this.settingRange2);
        String[] textArray = new String[5];
        textArray[0] = "Simple";
        textArray[1] = "HazeRange";
        textArray[2] = "Grim";
        textArray[3] = "IntervalRange";
        textArray[4] = "Dual";
        this.settingSimple = new ModeSetting(this, "RangeMode", "Simple", textArray, this.settingRange2);
        NumberSetting numberSetting2 = new NumberSetting(this, "HazeAdd", 0.5, 0.0, 1.0, 2, this.settingRange2);
        BooleanSupplier booleanSupplier = () -> this.isHazeAddVisible();
        this.settingHazeAdd = numberSetting2.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting4 = new NumberSetting(this, "HazeMax", 4.5, 3.0, 6.0, 2, this.settingRange2);
        booleanSupplier = () -> this.isHazeMaxVisible();
        this.settingHazeMax = numberSetting4.getNumberSetting(booleanSupplier);
        String[] textArray2 = new String[2];
        textArray2[0] = "Attacks";
        textArray2[1] = "Swings";
        ModeSetting modeSetting2 = new ModeSetting(this, "ActionToTrackInterval", "Attacks", textArray2, this.settingRange2);
        booleanSupplier = () -> this.isActionToTrackIntervalVisible();
        this.settingAttacks = modeSetting2.getModeSetting(booleanSupplier);
        NumberSetting numberSetting6 = new NumberSetting(this, "Interval", 2.0, 1.0, 10.0, 0, this.settingRange2);
        booleanSupplier = () -> this.isIntervalVisible();
        this.settingInterval = numberSetting6.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting8 = new NumberSetting(this, "RangeAtInterval", 3.0, 3.0, 6.0, 3, this.settingRange2);
        booleanSupplier = () -> this.isRangeAtIntervalVisible();
        this.settingRangeAtInterval = numberSetting8.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting10 = new NumberSetting(this, "MinReachToIntervalHit", 4.0, 3.0, 6.0, 3, this.settingRange2);
        booleanSupplier = () -> this.isMinReachToIntervalHitVisible();
        this.settingMinReachToIntervalHit = numberSetting10.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting12 = new NumberSetting(this, "HurtTimeToHitWithReach", 2.0, 0.0, 9.0, 0, this.settingRange2);
        booleanSupplier = () -> this.isHurtTimeToHitWithReachVisible();
        this.settingHurtTimeToHitWithReach = numberSetting12.getNumberSetting(booleanSupplier);
        this.settingExtendedRange = new ActionSetting(this, "BaseRange", "ExtendedRange", 3.0, 3.7, 3.0, 6.0, 2, this.settingRange2).getActionSetting(() -> this.isBaseRangeVisible());
        NumberSetting numberSetting14 = new NumberSetting(this, "Chance of Extended Range", 50.0, 0.0, 100.0, 0, this.settingRange2);
        booleanSupplier = () -> this.isChanceOfExtendedRangeVisible();
        this.settingChanceOfExtendedRange = numberSetting14.getNumberSetting(booleanSupplier);
        this.settingRotations = new SettingGroup(this, "Rotations", true);
        this.settingYawFov = new NumberSetting(this, "YawFov", 180.0, 0.0, 180.0, 0, this.settingRotations);
        this.settingPitchFov = new NumberSetting(this, "PitchFov", 180.0, 0.0, 180.0, 0, this.settingRotations);
        RangeSetting rangeSetting22 = new RangeSetting(this, "YawSpeed", 30.0, 50.0, 1.0, 180.0, 0, this.settingRotations).getRangeSetting2();
        booleanSupplier = () -> this.isYawSpeedVisible();
        RangeSetting rangeSetting = rangeSetting22.getRangeSetting(15343, booleanSupplier);
        this.settingYawSpeed = rangeSetting;
        RangeSetting rangeSetting23 = new RangeSetting(this, "PitchSpeed", 30.0, 50.0, 1.0, 180.0, 0, this.settingRotations).getRangeSetting2();
        booleanSupplier = () -> this.isPitchSpeedVisible();
        this.settingPitchSpeed = rangeSetting23.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting24 = new RangeSetting(this, "YawAcceleration", 15.0, 20.0, 1.0, 180.0, 1, this.settingRotations).getRangeSetting2();
        booleanSupplier = () -> this.isYawAccelerationVisible();
        this.settingYawAcceleration = rangeSetting24.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting25 = new RangeSetting(this, "PitchAcceleration", 15.0, 20.0, 1.0, 180.0, 1, this.settingRotations)
        .getRangeSetting2();
        booleanSupplier = () -> this.isPitchAccelerationVisible();
        this.settingPitchAcceleration = rangeSetting25.getRangeSetting(15343, booleanSupplier);
        BooleanSetting booleanSetting = new BooleanSetting(this, "BetterAcceleration", false, this.settingRotations);
        booleanSupplier = () -> this.isBetterAccelerationVisible();
        this.settingBetterAcceleration = (BooleanSetting)booleanSetting.getSetting(booleanSupplier);
        this.settingFixRotations = (SettingGroup)new SettingGroup(this, "FixRotationGroup", true, this.settingRotations).getSetting("FixRotations");
        String[] textArray3 = new String[3];
        textArray3[0] = "None";
        textArray3[1] = "OnlyWhenNeed";
        textArray3[2] = "Always";
        this.settingNone4 = new ModeSetting(this, "FixRotationMode", "None", textArray3, this.settingFixRotations);
        NumberSetting numberSetting18 = new NumberSetting(this, "FixRotationChance", 100.0, 1.0, 100.0, 0, this.settingFixRotations);
        booleanSupplier = () -> this.isFixRotationChanceVisible();
        this.settingFixRotationChance = numberSetting18.getNumberSetting(booleanSupplier);
        RangeSetting rangeSetting26 = new RangeSetting(this, "MaxFixRotationReach", 2.96, 2.98, 2.5, 3.0, 3, this.settingFixRotations)
        .getRangeSetting2();
        booleanSupplier = () -> this.isMaxFixRotationReachVisible();
        this.settingMaxFixRotationReach = rangeSetting26.getRangeSetting(15343, booleanSupplier);
        NumberSetting numberSetting20 = new NumberSetting(this, "MinFixRotationAngle", 90.0, 0.0, 180.0, 0, this.settingFixRotations);
        booleanSupplier = () -> this.isMinFixRotationAngleVisible();
        this.settingMinFixRotationAngle = numberSetting20.getNumberSetting(booleanSupplier);
        String[] textArray4 = new String[3];
        textArray4[0] = "None";
        textArray4[1] = "Separate";
        textArray4[2] = "Combined";
        ModeSetting modeSetting5 = new ModeSetting(this, "MinDistanceToBestVecCalculation", "None", textArray4, this.settingFixRotations);
        booleanSupplier = () -> this.isMinDistanceToBestVecCalculationVisible();
        this.settingNone = modeSetting5.getModeSetting(booleanSupplier);
        NumberSetting numberSetting22 = new NumberSetting(this, "MinDistanceToBestVecYaw", 2.5, 0.0, 10.0, 2, this.settingFixRotations);
        booleanSupplier = () -> this.isMinDistanceToBestVecYawVisible();
        this.settingMinDistanceToBestVecYaw = numberSetting22.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting24 = new NumberSetting(this, "MinDistanceToBestVecPitch", 2.5, 0.0, 10.0, 2, this.settingFixRotations);
        booleanSupplier = () -> this.isMinDistanceToBestVecPitchVisible();
        this.settingMinDistanceToBestVecPitch = numberSetting24.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting26 = new NumberSetting(this, "MinDistanceToBestVec", 2.5, 0.0, 10.0, 2, this.settingFixRotations);
        booleanSupplier = () -> this.isMinDistanceToBestVecVisible();
        this.settingMinDistanceToBestVec = numberSetting26.getNumberSetting(booleanSupplier);
        String[] textArray5 = new String[3];
        textArray5[0] = "None";
        textArray5[1] = "Pattern1";
        textArray5[2] = "IntaveTest";
        this.settingNone2 = new ModeSetting(this, "Heuristics", "None", textArray5, this.settingRotations);
        String[] textArray6 = new String[3];
        textArray6[0] = "Head";
        textArray6[1] = "BestHitVec";
        textArray6[2] = "NearestHitVec";
        this.settingBestHitVec = new ModeSetting(this, "RotationMode", "BestHitVec", textArray6, this.settingRotations);
        NumberSetting numberSetting28 = new NumberSetting(this, "HitBoxPercentageVertical", 100.0, 1.0, 100.0, 0, this.settingRotations);
        booleanSupplier = () -> this.isHitBoxPercentageVerticalVisible();
        this.settingHitBoxPercentageVertical2 = numberSetting28.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting30 = new NumberSetting(this, "HitBoxPercentageHorizontal", 100.0, 1.0, 100.0, 0, this.settingRotations);
        booleanSupplier = () -> this.isHitBoxPercentageHorizontalVisible();
        this.settingHitBoxPercentageHorizontal2 = numberSetting30.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting33 = new NumberSetting(this, "HitBoxPercentageVertical1", 100.0, 1.0, 100.0, 0, this.settingRotations).getNumberSetting("HitBoxPercentageVertical");
        booleanSupplier = () -> this.isHitBoxPercentageVertical1Visible();
        this.settingHitBoxPercentageVertical = numberSetting33.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting36 = new NumberSetting(this, "HitBoxPercentageHorizontal1", 100.0, 1.0, 100.0, 0, this.settingRotations).getNumberSetting("HitBoxPercentageHorizontal");
        booleanSupplier = () -> this.isHitBoxPercentageHorizontal1Visible();
        this.settingHitBoxPercentageHorizontal = numberSetting36.getNumberSetting(booleanSupplier);
        this.settingRandomize = new SettingGroup(this, "Randomize", true, this.settingRotations);
        String[] textArray7 = new String[4];
        textArray7[0] = "Random";
        textArray7[1] = "SecureRandom";
        textArray7[2] = "Gaussian";
        textArray7[3] = "Intave";
        this.settingSecureRandom = new ModeSetting(this, "RandomType", "SecureRandom", textArray7, this.settingRandomize);
        Client.getClient();
        String[] textArray8 = new String[13];
        textArray8[0] = "None";
        textArray8[1] = "Basic";
        textArray8[2] = "Doubled";
        textArray8[3] = "OnlyRotation";
        textArray8[4] = "Hybrid";
        textArray8[5] = "PolarTest";
        textArray8[6] = "Circle";
        textArray8[7] = "Advanced";
        textArray8[8] = "Noise";
        textArray8[9] = "Turbo";
        textArray8[10] = "Noise2";
        textArray8[11] = "Noise3";
        textArray8[12] = "LegitTest";
        this.settingOnlyRotation = new ModeSetting(this, "Randomize", "OnlyRotation", textArray8, this.settingRandomize);
        NumberSetting numberSetting38 = new NumberSetting(this, "RandomStrength", 1.25, 0.01, 5.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isRandomStrengthVisible();
        this.settingRandomStrength = numberSetting38.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "ReducedRotate", false, this.settingRandomize);
        booleanSupplier = () -> this.isReducedRotateVisible();
        this.settingReducedRotate = (BooleanSetting)booleanSetting2.getSetting(booleanSupplier);
        NumberSetting numberSetting40 = new NumberSetting(this, "ReduceRotationPercentage", 100.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isReduceRotationPercentageVisible();
        this.settingReduceRotationPercentage = numberSetting40.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "SmartRotate", false, this.settingRandomize);
        booleanSupplier = () -> this.isSmartRotateVisible();
        this.settingSmartRotate = (BooleanSetting)booleanSetting3.getSetting(booleanSupplier);
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "PitchOutliers", false, this.settingRandomize);
        booleanSupplier = () -> this.isPitchOutliersVisible();
        this.settingPitchOutliers = (BooleanSetting)booleanSetting4.getSetting(booleanSupplier);
        RangeSetting rangeSetting7 = new RangeSetting(this, "pitchOutliersAdd", 4.0, 10.0, 0.0, 20.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isPitchOutliersAddVisible();
        this.settingPitchOutliersAdd = rangeSetting7.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting27 = new RangeSetting(this, "PitchOutliersDelay", 15.0, 20.0, 1.0, 40.0, 0, this.settingRandomize)
        .getRangeSetting2();
        booleanSupplier = () -> this.isPitchOutliersDelayVisible();
        this.settingPitchOutliersDelay = rangeSetting27.getRangeSetting(15343, booleanSupplier);
        this.settingRadius = new ActionSetting(this, "InnerRadius", "Radius", 4.0, 9.0, 0.0, 20.0, 2, this.settingRandomize).getActionSetting(() -> this.isInnerRadiusVisible());
        NumberSetting numberSetting42 = new NumberSetting(this, "BestHitXMinDist", 2.0, 0.0, 10.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isBestHitXMinDistVisible();
        this.settingBestHitXMinDist = numberSetting42.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting44 = new NumberSetting(this, "BestHitYMInDist", 2.0, 0.0, 10.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isBestHitYMInDistVisible();
        this.settingBestHitYMInDist = numberSetting44.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting46 = new NumberSetting(this, "MinAbsDist", 0.5, 0.0, 10.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isMinAbsDistVisible();
        this.settingMinAbsDist = numberSetting46.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting48 = new NumberSetting(this, "MaxXAbsDist", 5.0, 0.0, 20.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isMaxXAbsDistVisible();
        this.settingMaxXAbsDist = numberSetting48.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting50 = new NumberSetting(this, "MaxYAbsDist", 20.0, 0.0, 20.0, 2, this.settingRandomize);
        booleanSupplier = () -> this.isMaxYAbsDistVisible();
        this.settingMaxYAbsDist = numberSetting50.getNumberSetting(booleanSupplier);
        this.settingXRelDist = new ActionSetting(this, "XRelDist", 0.5, 10.0, 0.0, 20.0, 2, this.settingRandomize).getActionSetting(() -> this.isXRelDistVisible());
        this.settingYRelDist = new ActionSetting(this, "YRelDist", 0.5, 10.0, 0.0, 20.0, 2, this.settingRandomize).getActionSetting(() -> this.isYRelDistVisible());
        NumberSetting numberSetting52 = new NumberSetting(this, "MaxRotationAngle", 90.0, 0.0, 180.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isMaxRotationAngleVisible();
        this.settingMaxRotationAngle = numberSetting52.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting54 = new NumberSetting(this, "Additional min Aimspeed", 100.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isAdditionalMinAimspeedVisible();
        this.settingAdditionalMinAimspeed = numberSetting54.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting56 = new NumberSetting(this, "Additional dynamic Speed", 100.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isAdditionalDynamicSpeedVisible();
        this.settingAdditionalDynamicSpeed = numberSetting56.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting58 = new NumberSetting(this, "Additional Aimspeed if needed", 100.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isAdditionalAimspeedIfNeededVisible();
        this.settingAdditionalAimspeedIfNeeded = numberSetting58.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting60 = new NumberSetting(
        this, "Probability of aiming even when not needed", 100.0, 0.0, 100.0, 0, this.settingRandomize
        );
        booleanSupplier = () -> this.isProbabilityOfAimingEvenWhenNotNeededVisible();
        this.settingProbabilityOfAimingEvenWhenNotNeeded = numberSetting60.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting5 = new BooleanSetting(this, "Test", false, this.settingRandomize);
        booleanSupplier = () -> this.isTestVisible();
        this.settingTest = (BooleanSetting)booleanSetting5.getSetting(booleanSupplier);
        NumberSetting numberSetting62 = new NumberSetting(this, "AimSpeed", 75.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isAimSpeedVisible();
        this.settingAimSpeed = numberSetting62.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting64 = new NumberSetting(this, "Decelerationfactor Pre", 12.5, 0.0, 100.0, 1, this.settingRandomize);
        booleanSupplier = () -> this.isDecelerationfactorPreVisible();
        this.settingDecelerationfactorPre = numberSetting64.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting66 = new NumberSetting(this, "Decelerationfactor Post", 0.0, 0.0, 100.0, 1, this.settingRandomize);
        booleanSupplier = () -> this.isDecelerationfactorPostVisible();
        this.settingDecelerationfactorPost = numberSetting66.getNumberSetting(booleanSupplier);
        this.settingReactionTime = new ActionSetting(this, "Reaction Time", 2.0, 4.0, 0.0, 6.0, 0, this.settingRandomize).getActionSetting(" Reaction Time").getActionSetting(() -> this.isReactionTimeVisible());
        this.settingLookaheadTime = new ActionSetting(this, "Lookahead Time", 2.0, 3.0, 0.0, 10.0, 0, this.settingRandomize).getActionSetting(" Lookahead Time").getActionSetting(() -> this.isLookaheadTimeVisible());
        NumberSetting numberSetting68 = new NumberSetting(this, "Attention", 3.0, 2.0, 4.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isAttentionVisible();
        this.settingAttention = numberSetting68.getNumberSetting(booleanSupplier);
        this.settingPredictionInaccuracy = new ActionSetting(this, "Prediction Inaccuracy", 0.0, 0.0, -100.0, 100.0, 0, this.settingRandomize).getActionSetting(" Prediction Inaccuracy").getActionSetting(() -> this.isPredictionInaccuracyVisible());
        NumberSetting numberSetting70 = new NumberSetting(this, "Prediction Inaccuracy Smoothness", 0.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isPredictionInaccuracySmoothnessVisible();
        this.settingPredictionInaccuracySmoothness = numberSetting70.getNumberSetting(booleanSupplier);
        SettingGroup settingGroup5 = new SettingGroup(this, "SuperCorrection", this.settingRandomize);
        booleanSupplier = () -> this.isSuperCorrectionVisible();
        this.settingSuperCorrection = settingGroup5.getSettingGroup(booleanSupplier);
        NumberSetting numberSetting72 = new NumberSetting(this, "SuperCorrection Probability", 100.0, 0.0, 100.0, 0, this.settingSuperCorrection);
        booleanSupplier = () -> this.isSuperCorrectionProbabilityVisible();
        this.settingSuperCorrectionProbability = numberSetting72.getNumberSetting(booleanSupplier);
        RangeSetting rangeSetting11 = new RangeSetting(this, "SuperCorrection Delay", 0.0, 0.0, 0.0, 5.0, 0, this.settingSuperCorrection).getRangeSetting(" SuperCorrection Delay");
        booleanSupplier = () -> this.isSuperCorrectionDelayVisible();
        this.settingSuperCorrectionDelay = rangeSetting11.getRangeSetting(15343, booleanSupplier).getRangeSetting2();
        BooleanSetting booleanSetting6 = new BooleanSetting(this, "Additional Accuracy", false, this.settingSuperCorrection);
        booleanSupplier = () -> this.isAdditionalAccuracyVisible();
        this.settingAdditionalAccuracy = (BooleanSetting)booleanSetting6.getSetting(booleanSupplier);
        SettingGroup settingGroup7 = new SettingGroup(this, "Rotation Offset", this.settingRandomize);
        booleanSupplier = () -> this.isRotationOffsetVisible();
        this.settingRotationOffset = settingGroup7.getSettingGroup(booleanSupplier);
        this.settingRotationYawOffset = new ActionSetting(this, "Rotation Yaw Offset", 0.0, 0.0, -30.0, 30.0, 1, this.settingRotationOffset).getActionSetting(" Rotation Yaw Offset").getActionSetting(() -> this.isRotationYawOffsetVisible());
        this.settingRotationPitchOffset = new ActionSetting(this, "Rotation Pitch Offset", 0.0, 0.0, -30.0, 30.0, 1, this.settingRotationOffset).getActionSetting(" Rotation Pitch Offset").getActionSetting(() -> this.isRotationPitchOffsetVisible());
        NumberSetting numberSetting74 = new NumberSetting(this, "Rotation Yaw Offset Speed", 0.0, 0.0, 100.0, 1, this.settingRotationOffset);
        booleanSupplier = () -> this.isRotationYawOffsetSpeedVisible();
        this.settingRotationYawOffsetSpeed = numberSetting74.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting76 = new NumberSetting(this, "Rotation Pitch Offset Speed", 0.0, 0.0, 100.0, 1, this.settingRotationOffset);
        booleanSupplier = () -> this.isRotationPitchOffsetSpeedVisible();
        this.settingRotationPitchOffsetSpeed = numberSetting76.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting78 = new NumberSetting(this, "Additional inaccuracy", 0.0, 0.0, 100.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isAdditionalInaccuracyVisible();
        this.settingAdditionalInaccuracy = numberSetting78.getNumberSetting(booleanSupplier);
        this.settingOrthogonalScale = new ActionSetting(this, "Orthogonal Scale", 0.0, 30.0, 0.0, 100.0, 0, this.settingRandomize).getActionSetting(" Orthogonal Scale").getActionSetting(() -> this.isOrthogonalScaleVisible());
        NumberSetting numberSetting80 = new NumberSetting(this, "Max Orthogonal Movement", 10.0, 0.0, 90.0, 1, this.settingRandomize);
        booleanSupplier = () -> this.isMaxOrthogonalMovementVisible();
        this.settingMaxOrthogonalMovement = numberSetting80.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting82 = new NumberSetting(this, "Max Aimpoint Anglediff", 60.0, 1.0, 180.0, 0, this.settingRandomize);
        booleanSupplier = () -> this.isMaxAimpointAnglediffVisible();
        this.settingMaxAimpointAnglediff = numberSetting82.getNumberSetting(booleanSupplier);
        RangeSetting rangeSetting29 = new RangeSetting(this, "Bestvec Pitchinterpolation", 0.0, 0.0, 0.0, 100.0, 0, this.settingRandomize)
        .getRangeSetting2();
        booleanSupplier = () -> this.isBestvecPitchinterpolationVisible();
        this.settingBestvecPitchinterpolation = rangeSetting29.getRangeSetting(15343, booleanSupplier);
        this.settingRotationOnTarget = new ActionSetting(this, "Rotation on Target", 100.0, 100.0, 0.0, 100.0, 0, this.settingRandomize).getActionSetting(" Rotation on Target").getActionSetting(() -> this.isRotationOnTargetVisible());
        BooleanSetting booleanSetting7 = new BooleanSetting(
        this, "No yaw adjustment if target out of reach", true, this.settingRandomize
        );
        booleanSupplier = () -> this.isNoYawAdjustmentIfTargetOutOfReachVisible();
        this.settingNoYawAdjustmentIfTargetOutOfReach = (BooleanSetting)booleanSetting7.getSetting(booleanSupplier);
        BooleanSetting booleanSetting8 = new BooleanSetting(
        this, "No pitch adjustment if target out of reach", true, this.settingRandomize
        );
        booleanSupplier = () -> this.isNoPitchAdjustmentIfTargetOutOfReachVisible();
        this.settingNoPitchAdjustmentIfTargetOutOfReach = (BooleanSetting)booleanSetting8.getSetting(booleanSupplier);
        BooleanSetting booleanSetting9 = new BooleanSetting(
        this, "Apply recorded mouse jitter movement if available", true, this.settingRandomize
        );
        booleanSupplier = () -> this.isApplyRecordedMouseJitterMovementIfAvailableVisible();
        this.settingApplyRecordedMouseJitterMovementIfAvailable = (BooleanSetting)booleanSetting9.getSetting(booleanSupplier);
        BooleanSetting booleanSetting10 = new BooleanSetting(this, "Ultimate Trashcode Bypass", true, this.settingRandomize);
        booleanSupplier = () -> this.isUltimateTrashcodeBypassVisible();
        this.settingUltimateTrashcodeBypass = (BooleanSetting)booleanSetting10.getSetting(booleanSupplier);
        BooleanSetting booleanSetting11 = new BooleanSetting(this, "Adapt Curve if needed", false, this.settingRandomize);
        booleanSupplier = () -> this.isAdaptCurveIfNeededVisible();
        this.settingAdaptCurveIfNeeded = (BooleanSetting)booleanSetting11.getSetting(booleanSupplier);
        RangeSetting rangeSetting210 = new RangeSetting(this, "Noise3AimSpeed", 20.0, 30.0, 0.0, 180.0, 0, this.settingRandomize).getRangeSetting("AimSpeed").getRangeSetting2();
        booleanSupplier = () -> this.isNoise3AimSpeedVisible();
        this.settingAimSpeed2 = rangeSetting210.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting211 = new RangeSetting(this, "Noise3Acceleration", 2.0, 4.0, 2.0, 20.0, 2, this.settingRandomize).getRangeSetting("Acceleration").getRangeSetting2();
        booleanSupplier = () -> this.isNoise3AccelerationVisible();
        this.settingAcceleration = rangeSetting211.getRangeSetting(15343, booleanSupplier);
        NumberSetting numberSetting85 = new NumberSetting(this, "Noise33BetterAcceleration", 0.0, 0.0, 1.0, 2, this.settingRandomize).getNumberSetting("BetterAcceleration");
        booleanSupplier = () -> this.isNoise33BetterAccelerationVisible();
        this.settingBetterAcceleration2 = numberSetting85.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting88 = new NumberSetting(this, "Noise3Scale", 1.5, 1.0, 7.0, 2, this.settingRandomize).getNumberSetting("Scale");
        booleanSupplier = () -> this.isNoise3ScaleVisible();
        this.settingScale = numberSetting88.getNumberSetting(booleanSupplier);
        this.settingTargetResponse = new ActionSetting(this, "Noise3ResponseTarget", 3.0, 5.0, 1.0, 10.0, 0, this.settingRandomize).getActionSetting("TargetResponse").getActionSetting(() -> this.isNoise3ResponseTargetVisible());
        this.settingPlayerResponse = new ActionSetting(this, "Noise3PlayerResponse", 3.0, 5.0, 1.0, 10.0, 0, this.settingRandomize).getActionSetting("PlayerResponse").getActionSetting(() -> this.isNoise3PlayerResponseVisible());
        this.settingSmartAim = new BooleanSetting(this, "SmartAim", true, this.settingRotations);
        BooleanSetting booleanSetting13 = new BooleanSetting(this, "SmartAimEntityCheck", true, this.settingRotations);
        booleanSupplier = () -> this.settingSmartAim.isEnabled();
        this.settingSmartAimEntityCheck = (BooleanSetting)booleanSetting13.getSetting(booleanSupplier);
        this.settingAdvancedRots = new BooleanSetting(this, "AdvancedRots", true, this.settingRotations);
        this.settingTimerRangeV2Fix = new BooleanSetting(this, "TimerRangeV2Fix", false, this.settingRotations);
        this.settingInterpolation = new BooleanSetting(this, "Interpolation", false, this.settingRotations);
        this.settingLockView = new BooleanSetting(this, "LockView", false, this.settingRotations);
        this.settingStopRotationInsideTarget = new BooleanSetting(this, "StopRotationInsideTarget", false, this.settingRotations);
        this.settingAimThroughWalls = new BooleanSetting(this, "AimThroughWalls", false, this.settingRotations);
        RangeSetting rangeSetting212 = new RangeSetting(this, "AimThroughWallsRange", 6.0, 6.0, 0.0, 6.0, 3, this.settingRotations)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingAimThroughWalls.isEnabled();
        this.settingAimThroughWallsRange = rangeSetting212.getRangeSetting(15343, booleanSupplier);
        BooleanSetting booleanSetting20 = new BooleanSetting(this, "ThroughWalls", false, this.settingRotations);
        booleanSupplier = () -> this.settingAimThroughWalls.isEnabled();
        this.settingThroughWalls = (BooleanSetting)booleanSetting20.getSetting(booleanSupplier);
        NumberSetting numberSetting90 = new NumberSetting(this, "ThroughWallsRange", 3.0, 0.0, 3.0, 3, this.settingRotations);
        booleanSupplier = () -> this.isThroughWallsRangeVisible();
        this.settingThroughWallsRange = numberSetting90.getNumberSetting(booleanSupplier);
        this.settingNoRotation = new BooleanSetting(this, "NoRotation", false, this.settingRotations);
        this.settingClicking = new SettingGroup(this, "Clicking", true);
        this.settingClickSettings = new SettingGroup(this, "ClickSettings", true, this.settingClicking);
        String[] textArray9 = new String[6];
        textArray9[0] = "Basic";
        textArray9[1] = "Deviation";
        textArray9[2] = "Simple";
        textArray9[3] = "Advanced";
        textArray9[4] = "Recorded";
        textArray9[5] = "Smart";
        this.settingAdvanced = new ModeSetting(this, "ClickMode", "Advanced", textArray9, this.settingClickSettings);
        this.settingCPSLimiter = new NumberSetting(this, "CPSLimiter", 15.0, 1.0, 40.0, 0, this.settingClickSettings);
        this.settingCPSLimiterIntave = new BooleanSetting(this, "CPSLimiterIntave", true, this.settingClickSettings);
        ActionSetting actionSetting23 = new ActionSetting(this, "BasicDelay", 50.0, 100.0, 0.0, 400.0, 0, this.settingClickSettings);
        String text = "MinDelay";
        this.settingMinDelay = actionSetting23.getActionSetting(text, "MaxDelay").getActionSetting(() -> this.isBasicDelayVisible());
        RangeSetting rangeSetting19 = new RangeSetting(this, "SimpleCPS", 12.0, 16.0, 0.0, 40.0, 0, this.settingClickSettings).getRangeSetting("CPS");
        booleanSupplier = () -> this.isSimpleCPSVisible();
        this.settingCPS2 = rangeSetting19.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting21 = new RangeSetting(this, "SimpleCPSRecalculateDelay", 3.0, 8.0, 0.0, 20.0, 0, this.settingClickSettings)
        .getRangeSetting2().getRangeSetting("CPSRecalculateDelay");
        booleanSupplier = () -> this.isSimpleCPSRecalculateDelayVisible();
        this.settingCPSRecalculateDelay = rangeSetting21.getRangeSetting(15343, booleanSupplier);
        this.settingClickDutyCycle = new ActionSetting(this, "SimpleClickDutyCycle", 0.35, 0.65, 0.0, 1.0, 2, this.settingClickSettings).getActionSetting("ClickDutyCycle").getActionSetting(() -> this.isSimpleClickDutyCycleVisible());
        Setting setting15 = new BooleanSetting(this, "SimpleDoubleClicks", true, this.settingClickSettings).getSetting("DoubleClicks");
        booleanSupplier = () -> this.isSimpleDoubleClicksVisible();
        this.settingDoubleClicks = (BooleanSetting)(((BooleanSetting)setting15).getSetting(booleanSupplier));
        Setting setting17 = new BooleanSetting(this, "SimpleExtraDoubleClickRandomize", false, this.settingClickSettings).getSetting("ExtraDoubleClickRandomize");
        booleanSupplier = () -> this.isSimpleExtraDoubleClickRandomizeVisible();
        this.settingExtraDoubleClickRandomize = (BooleanSetting)(((BooleanSetting)setting17).getSetting(booleanSupplier));
        ActionSetting actionSetting27 = new ActionSetting(this, "DeviationCPS", 13.0, 20.0, 0.0, 40.0, 0, this.settingClickSettings);
        text = "MinCPS";
        this.settingMinCPS = actionSetting27.getActionSetting(text, "MaxCPS").getActionSetting(() -> this.isDeviationCPSVisible());
        Setting setting19 = new BooleanSetting(this, "DeviationDoubleClick", true, this.settingClickSettings).getSetting("DoubleClick");
        booleanSupplier = () -> this.isDeviationDoubleClickVisible();
        this.settingDoubleClick = (BooleanSetting)(((BooleanSetting)setting19).getSetting(booleanSupplier));
        NumberSetting numberSetting94 = new NumberSetting(this, "DeviationAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingClickSettings).getNumberSetting("AirMultiplier");
        booleanSupplier = () -> this.isDeviationAirMultiplierVisible();
        this.settingAirMultiplier2 = numberSetting94.getNumberSetting(booleanSupplier);
        String[] textArray10 = new String[0];
        ModeSetting modeSetting12 = new ModeSetting(this, "ClickPattern", "", textArray10, this.settingClickSettings);
        booleanSupplier = () -> this.isClickPatternVisible();
        this.settingClickPattern = modeSetting12.getModeSetting(booleanSupplier);
        String[] textArray11 = new String[2];
        textArray11[0] = "TimeMillis";
        textArray11[1] = "Ticks";
        ModeSetting modeSetting14 = new ModeSetting(this, "RecordedMode", "TimeMillis", textArray11, this.settingClickSettings);
        booleanSupplier = () -> this.isRecordedModeVisible();
        this.settingTimeMillis = modeSetting14.getModeSetting(booleanSupplier);
        NumberSetting numberSetting96 = new NumberSetting(this, "Multiplier", 3.0, 0.0, 5.0, 2, this.settingClickSettings);
        booleanSupplier = () -> this.isMultiplierVisible();
        this.settingMultiplier = numberSetting96.getNumberSetting(booleanSupplier);
        NumberSetting numberSetting99 = new NumberSetting(this, "RecordedAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingClickSettings).getNumberSetting("AirMultiplier");
        booleanSupplier = () -> this.isRecordedAirMultiplierVisible();
        this.settingAirMultiplier = numberSetting99.getNumberSetting(booleanSupplier);
        this.settingCPS = new ActionSetting(this, "CPS", 14.0, 20.0, 0.0, 40.0, 0, this.settingClickSettings).getActionSetting(() -> this.isCPSVisible());
        ActionSetting actionSetting30 = new ActionSetting(this, "SpeedChangeDelay", 800.0, 1500.0, 0.0, 4000.0, 0, this.settingClickSettings);
        text = "MinDelay";
        this.settingMinDelay2 = actionSetting30.getActionSetting(text, "MaxDelay").getActionSetting(() -> this.isSpeedChangeDelayVisible());
        BooleanSetting booleanSetting26 = new BooleanSetting(this, "Spikes", false, this.settingClickSettings);
        booleanSupplier = () -> this.isSpikesVisible();
        this.settingSpikes = (BooleanSetting)booleanSetting26.getSetting(booleanSupplier);
        NumberSetting numberSetting101 = new NumberSetting(this, "SpikeDelay", 15000.0, 0.0, 20000.0, 0, this.settingClickSettings);
        booleanSupplier = () -> this.isSpikeDelayVisible();
        this.settingSpikeDelay = numberSetting101.getNumberSetting(booleanSupplier);
        this.settingSpikeDuration = new ActionSetting(this, "SpikeDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingClickSettings).getActionSetting(() -> this.isSpikeDurationVisible());
        NumberSetting numberSetting103 = new NumberSetting(this, "SpikeMulti", 1.5, 1.0, 3.0, 2, this.settingClickSettings);
        booleanSupplier = () -> this.isSpikeMultiVisible();
        this.settingSpikeMulti = numberSetting103.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting27 = new BooleanSetting(this, "Drops", true, this.settingClickSettings);
        booleanSupplier = () -> this.isDropsVisible();
        this.settingDrops = (BooleanSetting)booleanSetting27.getSetting(booleanSupplier);
        NumberSetting numberSetting105 = new NumberSetting(this, "DropDelay", 7000.0, 0.0, 20000.0, 0, this.settingClickSettings);
        booleanSupplier = () -> this.isDropDelayVisible();
        this.settingDropDelay = numberSetting105.getNumberSetting(booleanSupplier);
        this.settingDropDuration = new ActionSetting(this, "DropDuration", 250.0, 500.0, 0.0, 2000.0, 0, this.settingClickSettings).getActionSetting(() -> this.isDropDurationVisible());
        NumberSetting numberSetting107 = new NumberSetting(this, "DropMulti", 0.5, 0.0, 1.0, 2, this.settingClickSettings);
        booleanSupplier = () -> this.isDropMultiVisible();
        this.settingDropMulti = numberSetting107.getNumberSetting(booleanSupplier);
        BooleanSetting booleanSetting28 = new BooleanSetting(this, "ReduceInAir", true, this.settingClickSettings);
        booleanSupplier = () -> this.isReduceInAirVisible();
        this.settingReduceInAir = (BooleanSetting)booleanSetting28.getSetting(booleanSupplier);
        this.settingReduceMulti = new ActionSetting(this, "ReduceMulti", 0.4, 0.4, 0.0, 1.0, 2, this.settingClickSettings).getActionSetting(() -> this.isReduceMultiVisible());
        this.settingReduceRecalculateDelay = new ActionSetting(this, "ReduceRecalculateDelay", 100.0, 150.0, 0.0, 1000.0, 0, this.settingClickSettings).getActionSetting(() -> this.isReduceRecalculateDelayVisible());
        BooleanSetting booleanSetting29 = new BooleanSetting(this, "ReduceHitsOnWall", false, this.settingClickSettings);
        booleanSupplier = () -> this.isReduceHitsOnWallVisible();
        this.settingReduceHitsOnWall = (BooleanSetting)booleanSetting29.getSetting(booleanSupplier);
        RangeSetting rangeSetting214 = new RangeSetting(this, "ReduceRecalculateDelayOnWall", 100.0, 200.0, 0.0, 1000.0, 0, this.settingClickSettings)
        .getRangeSetting2();
        booleanSupplier = () -> this.isReduceRecalculateDelayOnWallVisible();
        this.settingReduceRecalculateDelayOnWall = rangeSetting214.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting215 = new RangeSetting(this, "ReduceMultiplierOnWall", 0.4, 0.4, 0.0, 1.0, 2, this.settingClickSettings)
        .getRangeSetting2();
        booleanSupplier = () -> this.isReduceMultiplierOnWallVisible();
        this.settingReduceMultiplierOnWall = rangeSetting215.getRangeSetting(15343, booleanSupplier);
        KillAuraHurtTime[] killAuraHurtTimeArray = KillAuraHurtTime.values();
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting(this, "HurttimesToHit", killAuraHurtTimeArray, this.settingClickSettings);
        booleanSupplier = () -> this.isHurttimesToHitVisible();
        this.settingHurttimesToHit = multiSelectSetting.getMultiSelectSetting(booleanSupplier);
        String[] textArray12 = new String[2];
        textArray12[0] = "Recommended";
        textArray12[1] = "Overengineered";
        ModeSetting modeSetting17 = new ModeSetting(this, "SmartMode", "Recommended", textArray12, this.settingClickSettings).getModeSetting("Mode");
        booleanSupplier = () -> this.isSmartModeVisible();
        this.settingMode = modeSetting17.getModeSetting(booleanSupplier);
        String[] textArray13 = new String[3];
        textArray13[0] = "Combined";
        textArray13[1] = "Default";
        textArray13[2] = "Separate";
        ModeSetting modeSetting20 = new ModeSetting(this, "SmartClickMode", "Combined", textArray13, this.settingClickSettings).getModeSetting("ClickMode");
        booleanSupplier = () -> this.isSmartClickModeVisible();
        this.settingClickMode = modeSetting20.getModeSetting(booleanSupplier);
        String[] textArray14 = new String[3];
        textArray14[0] = "Combined";
        textArray14[1] = "Default";
        textArray14[2] = "Separate";
        ModeSetting modeSetting23 = new ModeSetting(this, "SmartDoubleClickModeS", "Default", textArray14, this.settingClickSettings).getModeSetting("DoubleClickMode");
        booleanSupplier = () -> this.isSmartDoubleClickModeSVisible();
        this.settingDoubleClickMode = modeSetting23.getModeSetting(booleanSupplier);
        SettingGroup settingGroup11 = new SettingGroup(this, "CPS Settings", this.settingClickSettings);
        booleanSupplier = () -> this.isCPSSettingsVisible();
        this.settingCPSSettings = settingGroup11.getSettingGroup(booleanSupplier);
        SettingGroup settingGroup13 = new SettingGroup(this, "In Air", true, this.settingCPSSettings);
        booleanSupplier = () -> this.isInAirVisible();
        this.settingInAir = settingGroup13.getSettingGroup(booleanSupplier);
        RangeSetting rangeSetting34 = new RangeSetting(this, "SmartCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.settingInAir).getRangeSetting("CPSInAir");
        booleanSupplier = () -> this.isSmartCPSInAirVisible();
        this.settingCPSInAir = rangeSetting34.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting216 = new RangeSetting(this, "SmartCPSInAirRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.settingInAir).getRangeSetting("CPSInAirRecalculateTickDelay").getRangeSetting2();
        booleanSupplier = () -> this.isSmartCPSInAirRecalculateTickDelayVisible();
        this.settingCPSInAirRecalculateTickDelay = rangeSetting216.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting39 = new RangeSetting(this, "SmartDCPSInAir", 2.0, 4.0, 0.0, 20.0, 0, this.settingInAir).getRangeSetting("DCPSInAir");
        booleanSupplier = () -> this.isSmartDCPSInAirVisible();
        this.settingDCPSInAir = rangeSetting39.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting217 = new RangeSetting(this, "DCPSInAirRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingInAir)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDCPSInAirRecalculateTickDelayVisible();
        this.settingDCPSInAirRecalculateTickDelay = rangeSetting217.getRangeSetting(15343, booleanSupplier);
        SettingGroup settingGroup15 = new SettingGroup(this, "On wall", true, this.settingCPSSettings);
        booleanSupplier = () -> this.isOnWallVisible();
        this.settingOnWall = settingGroup15.getSettingGroup(booleanSupplier);
        RangeSetting rangeSetting43 = new RangeSetting(this, "SmartCPSOnWall", 8.0, 12.0, 0.0, 20.0, 0, this.settingOnWall).getRangeSetting("CPSOnWall");
        booleanSupplier = () -> this.isSmartCPSOnWallVisible();
        this.settingCPSOnWall = rangeSetting43.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting218 = new RangeSetting(this, "SmartCPSOnWallRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.settingOnWall).getRangeSetting("CPSOnWallRecalculateTickDelay").getRangeSetting2();
        booleanSupplier = () -> this.isSmartCPSOnWallRecalculateTickDelayVisible();
        this.settingCPSOnWallRecalculateTickDelay = rangeSetting218.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting48 = new RangeSetting(this, "SmartDCPSOnWall", 0.0, 0.0, 0.0, 20.0, 0, this.settingOnWall).getRangeSetting("DCPSOnWall");
        booleanSupplier = () -> this.isSmartDCPSOnWallVisible();
        this.settingDCPSOnWall = rangeSetting48.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting219 = new RangeSetting(this, "DCPSOnWallRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnWall)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDCPSOnWallRecalculateTickDelayVisible();
        this.settingDCPSOnWallRecalculateTickDelay = rangeSetting219.getRangeSetting(15343, booleanSupplier);
        SettingGroup settingGroup17 = new SettingGroup(this, "On first Hit", true, this.settingCPSSettings);
        booleanSupplier = () -> this.isOnFirstHitVisible();
        this.settingOnFirstHit = settingGroup17.getSettingGroup(booleanSupplier);
        RangeSetting rangeSetting52 = new RangeSetting(this, "SmartCPSFirstHit", 20.0, 20.0, 0.0, 40.0, 0, this.settingOnFirstHit).getRangeSetting("CPSFirstHit");
        booleanSupplier = () -> this.isSmartCPSFirstHitVisible();
        this.settingCPSFirstHit = rangeSetting52.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting220 = new RangeSetting(
        this, "SmartCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.settingOnFirstHit
        ).getRangeSetting("CPSFirstHitRecalculateTickDelay").getRangeSetting2();
        booleanSupplier = () -> this.isSmartCPSFirstHitRecalculateTickDelayVisible();
        this.settingCPSFirstHitRecalculateTickDelay = rangeSetting220.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting57 = new RangeSetting(this, "SmartDCPSFirstHit", 4.0, 5.0, 0.0, 20.0, 0, this.settingOnFirstHit).getRangeSetting("DCPSFirstHit");
        booleanSupplier = () -> this.isSmartDCPSFirstHitVisible();
        this.settingDCPSFirstHit = rangeSetting57.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting221 = new RangeSetting(
        this, "SmartDCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnFirstHit
        ).getRangeSetting("DCPSFirstHitRecalculateTickDelay").getRangeSetting2();
        booleanSupplier = () -> this.isSmartDCPSFirstHitRecalculateTickDelayVisible();
        this.settingDCPSFirstHitRecalculateTickDelay = rangeSetting221.getRangeSetting(15343, booleanSupplier);
        SettingGroup settingGroup19 = new SettingGroup(this, "On Target", true, this.settingCPSSettings);
        booleanSupplier = () -> this.isOnTargetVisible();
        this.settingOnTarget = settingGroup19.getSettingGroup(booleanSupplier);
        RangeSetting rangeSetting62 = new RangeSetting(this, "SmartCPS", 17.0, 19.0, 1.0, 40.0, 0, this.settingOnTarget).getRangeSetting("CPS");
        booleanSupplier = () -> this.isSmartCPSVisible();
        this.settingCPS3 = rangeSetting62.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting222 = new RangeSetting(this, "CPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnTarget)
        .getRangeSetting2();
        booleanSupplier = () -> this.isCPSRecalculateTickDelayVisible();
        this.settingCPSRecalculateTickDelay = rangeSetting222.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting66 = new RangeSetting(this, "SmartDCPS", 4.0, 5.0, 0.0, 20.0, 0, this.settingOnTarget).getRangeSetting("DCPS");
        booleanSupplier = () -> this.isSmartDCPSVisible();
        this.settingDCPS = rangeSetting66.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting223 = new RangeSetting(this, "DCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnTarget)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDCPSRecalculateTickDelayVisible();
        this.settingDCPSRecalculateTickDelay = rangeSetting223.getRangeSetting(15343, booleanSupplier);
        this.settingInteractions = new SettingGroup(this, "Interactions", true, this.settingClicking);
        String[] textArray15 = new String[3];
        textArray15[0] = "Legit";
        textArray15[1] = "Packet";
        textArray15[2] = "Multi";
        this.settingLegit = new ModeSetting(this, "AttackMode", "Legit", textArray15, this.settingInteractions);
        NumberSetting numberSetting109 = new NumberSetting(this, "MaxTargets", 10.0, 1.0, 20.0, 0, this.settingInteractions);
        booleanSupplier = () -> this.isMaxTargetsVisible();
        this.settingMaxTargets = numberSetting109.getNumberSetting(booleanSupplier);
        String[] textArray16 = new String[15];
        textArray16[0] = "None";
        textArray16[1] = "Constant";
        textArray16[2] = "Constant2";
        textArray16[3] = "Pre";
        textArray16[4] = "Post";
        textArray16[5] = "UnblockOnHit";
        textArray16[6] = "UnblockOnHit2";
        textArray16[7] = "Spam";
        textArray16[8] = "SlowSpam";
        textArray16[9] = "OldHypixel";
        textArray16[10] = "AAC";
        textArray16[11] = "AACOld";
        textArray16[12] = "Legit";
        textArray16[13] = "Bug";
        textArray16[14] = "Test";
        this.settingNone3 = new ModeSetting(this, "BlockMode", "None", textArray16, this.settingInteractions);
        BooleanSetting booleanSetting30 = new BooleanSetting(this, "SmartBlock", true, this.settingInteractions);
        booleanSupplier = () -> this.isSmartBlockVisible();
        this.settingSmartBlock = (BooleanSetting)booleanSetting30.getSetting(booleanSupplier);
        BooleanSetting booleanSetting31 = new BooleanSetting(this, "InteractAutoBlock", true, this.settingInteractions);
        booleanSupplier = () -> this.isInteractAutoBlockVisible();
        this.settingInteractAutoBlock = (BooleanSetting)booleanSetting31.getSetting(booleanSupplier);
        BooleanSetting booleanSetting32 = new BooleanSetting(this, "OnlyRightClickBlock", false, this.settingInteractions);
        booleanSupplier = () -> this.isOnlyRightClickBlockVisible();
        this.settingOnlyRightClickBlock = (BooleanSetting)booleanSetting32.getSetting(booleanSupplier);
        BooleanSetting booleanSetting33 = new BooleanSetting(this, "OnlyWhileSwingInProgress", false, this.settingInteractions);
        booleanSupplier = () -> this.isOnlyWhileSwingInProgressVisible();
        this.settingOnlyWhileSwingInProgress = (BooleanSetting)booleanSetting33.getSetting(booleanSupplier);
        BooleanSetting booleanSetting34 = new BooleanSetting(this, "OnlyWhileHurt", false, this.settingInteractions);
        booleanSupplier = () -> this.isOnlyWhileHurtVisible();
        this.settingOnlyWhileHurt = (BooleanSetting)booleanSetting34.getSetting(booleanSupplier);
        NumberSetting numberSetting111 = new NumberSetting(this, "UnblockAAC", 4.0, 0.0, 10.0, 0, this.settingInteractions);
        booleanSupplier = () -> this.isUnblockAACVisible();
        this.settingUnblockAAC = numberSetting111.getNumberSetting(booleanSupplier);
        this.settingPreHit = new BooleanSetting(this, "PreHit", true, this.settingClicking);
        BooleanSetting booleanSetting36 = new BooleanSetting(this, "SmartPreHit", false, this.settingClicking);
        booleanSupplier = () -> this.settingPreHit.isEnabled();
        this.settingSmartPreHit = (BooleanSetting)booleanSetting36.getSetting(booleanSupplier);
        RangeSetting rangeSetting224 = new RangeSetting(this, "Prediction", 3.0, 5.0, 1.0, 10.0, 0, this.settingClicking).getRangeSetting2();
        booleanSupplier = () -> this.isPredictionVisible();
        this.settingPrediction = rangeSetting224.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting225 = new RangeSetting(this, "LoseDelay", 3.0, 5.0, 1.0, 10.0, 0, this.settingClicking).getRangeSetting2();
        booleanSupplier = () -> this.isLoseDelayVisible();
        this.settingLoseDelay = rangeSetting225.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting226 = new RangeSetting(this, "Endurance", 90.0, 110.0, 1.0, 400.0, 0, this.settingClicking).getRangeSetting2();
        booleanSupplier = () -> this.isEnduranceVisible();
        this.settingEndurance = rangeSetting226.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting227 = new RangeSetting(this, "EnduranceReduction", 5.0, 10.0, 1.0, 20.0, 0, this.settingClicking)
        .getRangeSetting2();
        booleanSupplier = () -> this.isEnduranceReductionVisible();
        this.settingEnduranceReduction = rangeSetting227.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting228 = new RangeSetting(this, "PreHitRange", 6.0, 6.0, 3.0, 8.0, 2, this.settingClicking).getRangeSetting2();
        booleanSupplier = () -> this.settingPreHit.isEnabled();
        this.settingPreHitRange = rangeSetting228.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting229 = new RangeSetting(this, "PreHitFovYaw", 50.0, 60.0, 0.0, 180.0, 0, this.settingClicking).getRangeSetting2();
        booleanSupplier = () -> this.settingPreHit.isEnabled();
        this.settingPreHitFovYaw = rangeSetting229.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting230 = new RangeSetting(this, "PreHitFovPitch", 50.0, 60.0, 0.0, 180.0, 0, this.settingClicking)
        .getRangeSetting2();
        booleanSupplier = () -> this.settingPreHit.isEnabled();
        this.settingPreHitFovPitch = rangeSetting230.getRangeSetting(15343, booleanSupplier);
        this.settingMissClick = new BooleanSetting(this, "MissClick", false, this.settingClicking);
        NumberSetting numberSetting113 = new NumberSetting(this, "MissClickPercentage", 5.0, 0.0, 100.0, 0, this.settingClicking);
        booleanSupplier = () -> this.settingMissClick.isEnabled();
        this.settingMissClickPercentage = numberSetting113.getNumberSetting(booleanSupplier);
        this.settingPerfectHit = new BooleanSetting(this, "PerfectHit", true, this.settingClicking);
        BooleanSetting booleanSetting39 = new BooleanSetting(this, "LongClick", false, this.settingClicking);
        booleanSupplier = () -> this.isLongClickVisible();
        this.settingLongClick = (BooleanSetting)booleanSetting39.getSetting(booleanSupplier);
        this.settingCritFix = new BooleanSetting(this, "CritFix", true, this.settingClicking);
        BooleanSetting booleanSetting41 = new BooleanSetting(this, "CritFixPacketCheck", true, this.settingClicking);
        booleanSupplier = () -> this.settingCritFix.isEnabled();
        this.settingCritFixPacketCheck = (BooleanSetting)booleanSetting41.getSetting(booleanSupplier);
        this.settingNoLoopHit = new BooleanSetting(this, "NoLoopHit", true, this.settingClicking);
        this.settingFixBeta = new BooleanSetting(this, "Fix (Beta)", false, this.settingClicking);
        this.settingBreakBlocks = new BooleanSetting(this, "BreakBlocks", false, this.settingClicking);
        BooleanSetting booleanSetting45 = new BooleanSetting(this, "DoubleClickFirstHit", true, this.settingClicking);
        booleanSupplier = () -> this.isDoubleClickFirstHitVisible();
        this.settingDoubleClickFirstHit = (BooleanSetting)booleanSetting45.getSetting(booleanSupplier);
        Setting setting34 = new BooleanSetting(this, "ReduceCPSIfYouCanNotHitATargett", false, this.settingClicking).getSetting("ReduceCPSIfYouCanNotHitATarget");
        booleanSupplier = () -> this.isReduceCPSIfYouCanNotHitATargettVisible();
        this.settingReduceCPSIfYouCanNotHitATarget = (BooleanSetting)(((BooleanSetting)setting34).getSetting(booleanSupplier));
        this.settingNoSwing = new BooleanSetting(this, "NoSwing", false, this.settingClicking);
        this.settingSlowDown = new BooleanSetting(this, "SlowDown", true, this.settingClicking);
        BooleanSetting booleanSetting49 = new BooleanSetting(this, "CoolDown", false, this.settingClicking);
        booleanSupplier = () -> this.isCoolDownVisible();
        this.settingCoolDown = (BooleanSetting)booleanSetting49.getSetting(booleanSupplier);
        this.settingMovement = new SettingGroup(this, "Movement", true);
        this.settingJumpFix = new BooleanSetting(this, "JumpFix", true, this.settingMovement);
        BooleanSetting booleanSetting51 = new BooleanSetting(this, "MoveFix", true, this.settingMovement);
        booleanSupplier = () -> this.isMoveFixVisible();
        this.settingMoveFix = (BooleanSetting)booleanSetting51.getSetting(booleanSupplier);
        String[] textArray17 = new String[3];
        textArray17[0] = "None";
        textArray17[1] = "Default";
        textArray17[2] = "Advanced";
        ModeSetting modeSetting27 = new ModeSetting(this, "SilentMoveFix", "Default", textArray17, this.settingMovement);
        booleanSupplier = () -> this.isSilentMoveFixVisible();
        this.settingDefault = modeSetting27.getModeSetting(booleanSupplier);
        BooleanSetting booleanSetting52 = new BooleanSetting(this, "SilentMoveFixIntave", true, this.settingMovement);
        booleanSupplier = () -> this.isSilentMoveFixIntaveVisible();
        this.settingSilentMoveFixIntave = (BooleanSetting)booleanSetting52.getSetting(booleanSupplier);
        this.settingTargets2 = new SettingGroup(this, "Targets", true);
        KillAuraTargetEntity[] killAuraTargetEntityArray = KillAuraTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", killAuraTargetEntityArray, this.settingTargets2);
        String[] textArray18 = new String[7];
        textArray18[0] = "Single";
        textArray18[1] = "FOV";
        textArray18[2] = "Health";
        textArray18[3] = "Distance";
        textArray18[4] = "Short";
        textArray18[5] = "ShortSwitch";
        textArray18[6] = "UltimateSwitch";
        this.settingUltimateSwitch = new ModeSetting(this, "TargetSort", "UltimateSwitch", textArray18, this.settingTargets2);
        BooleanSetting booleanSetting53 = new BooleanSetting(this, "SwitchOnlyToTargetsInRange", false, this.settingTargets2);
        booleanSupplier = () -> this.isSwitchOnlyToTargetsInRangeVisible();
        this.settingSwitchOnlyToTargetsInRange = (BooleanSetting)booleanSetting53.getSetting(booleanSupplier);
        this.settingTargetDelay = new NumberSetting(this, "TargetDelay", 0.0, 0.0, 1000.0, 0, this.settingTargets2);
        this.settingTargetLoseDelay = new RangeSetting(this, "TargetLoseDelay", 0.0, 0.0, 0.0, 1000.0, 0, this.settingTargets2)
        .getRangeSetting2();
        this.settingMaxDeathTime = new RangeSetting(this, "MaxDeathTime", 0.0, 0.0, 0.0, 30.0, 0, this.settingTargets2).getRangeSetting2();
        this.settingMiscellaneous = new SettingGroup(this, "Miscellaneous");
        this.settingCloseGuisIfTargetArrives = new BooleanSetting(this, "CloseGuisIfTargetArrives", false, this.settingMiscellaneous);
        this.settingInInv = new BooleanSetting(this, "InInv", false, this.settingMiscellaneous);
        KillAuraWeaponSlot[] killAuraWeaponSlotArray = KillAuraWeaponSlot.values();
        this.settingActiveSlots = new MultiSelectSetting(this, "ActiveSlots", killAuraWeaponSlotArray, this.settingMiscellaneous);
        this.auxiliarySettingAdapter = new TargetFilterSetting(this, this.settingMiscellaneous);
        BooleanSetting booleanSetting56 = new BooleanSetting(this, "Intave13", false, this.settingMiscellaneous);
        booleanSupplier = () -> this.isIntave13Visible();
        this.settingIntave13 = (BooleanSetting)booleanSetting56.getSetting(booleanSupplier);
        RangeSetting rangeSetting233 = new RangeSetting(this, "DelayRotationsAfterClosingGuiScreen", 3.0, 5.0, 1.0, 20.0, 0, this.settingMiscellaneous)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDelayRotationsAfterClosingGuiScreenVisible();
        this.settingDelayRotationsAfterClosingGuiScreen = rangeSetting233.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting234 = new RangeSetting(this, "DelayClicksAfterClosingGuiScreen", 3.0, 5.0, 1.0, 20.0, 0, this.settingMiscellaneous)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDelayClicksAfterClosingGuiScreenVisible();
        this.settingDelayClicksAfterClosingGuiScreen = rangeSetting234.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting235 = new RangeSetting(this, "DelayRotationsAfterRespawn", 4.0, 6.0, 1.0, 20.0, 0, this.settingMiscellaneous)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDelayRotationsAfterRespawnVisible();
        this.settingDelayRotationsAfterRespawn = rangeSetting235.getRangeSetting(15343, booleanSupplier);
        RangeSetting rangeSetting236 = new RangeSetting(this, "DelayClicksAfterRespawn", 4.0, 6.0, 1.0, 20.0, 0, this.settingMiscellaneous)
        .getRangeSetting2();
        booleanSupplier = () -> this.isDelayClicksAfterRespawnVisible();
        this.settingDelayClicksAfterRespawn = rangeSetting236.getRangeSetting(15343, booleanSupplier);
        this.settingVisuals = new SettingGroup(this, "Visuals");
        this.settingDot = new BooleanSetting(this, "Dot", false, this.settingVisuals);
        NumberSetting numberSetting116 = new NumberSetting(this, "DotSize", 0.3, 0.01, 1.0, 2, this.settingVisuals);
        booleanSupplier = () -> this.settingDot.isEnabled();
        this.settingDotSize = numberSetting116.getNumberSetting(booleanSupplier);
        Color color = new Color(41, 146, 222);
        ColorSetting colorSetting = new ColorSetting(this, "DotColor", color, this.settingVisuals);
        booleanSupplier = () -> this.settingDot.isEnabled();
        this.settingDotColor = (ColorSetting)colorSetting.getSetting(booleanSupplier);
        ShaderOptions shaderOptions2 = new ShaderOptions(this, this.settingVisuals);
        booleanSupplier = () -> this.settingDot.isEnabled();
        ShaderOptions shaderOptions5 = ((ShaderOptions)shaderOptions2.getSetting(booleanSupplier)).getShaderOptions5();
        Color black = Color.black;
        this.shaderOptions = shaderOptions5.getShaderOptions(black);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.timer4 = new TimeHelper();
        this.timer5 = new TimeHelper();
        this.timer6 = new TimeHelper();
        this.clickPatternProfile = (ClickPatternProfile)null;
        ArrayList arrayList = new ArrayList();
        this.itemList3 = arrayList;
        this.reachDistance = 3.0;
        this.target = (EntityLivingBase)null;
        this.timestampMillis23 = 100L;
        this.timestampMillis18 = 2000L;
        this.timestampMillis20 = 2000L;
        this.reachDistance4 = 1.0;
        this.timestampMillis1 = 10L;
        this.timestampMillis21 = 100L;
        this.targetEntity2 = (EntityLivingBase)null;
        this.targetEntity3 = (EntityLivingBase)null;
        this.tickDelay5 = 0;
        this.copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.tickDelay = 0;
        this.timer7 = new TimeHelper();
        this.timer8 = new TimeHelper();
        this.auxiliaryArrayListEntry = new ClickHistoryBuffer(60);
        Rotation rotation2 = new Rotation();
        this.rotation = rotation2;
        this.intValueArray = new int[2];
        this.timestampMillis25 = 0L;
        this.tickDelay12 = 0;
        this.activeState3 = false;
        this.movingObjectPosition = (MovingObjectPosition)null;
        this.timestampMillis26 = 0L;
        this.timestampMillis = 0L;
        this.timestampMillis24 = 0L;
        this.activeState19 = false;
        this.activeState31 = false;
        this.tickDelay16 = 0;
        this.activeState14 = false;
        this.itemList4 = new ArrayList();
        this.activeState32 = false;
        this.timer9 = new TimeHelper();
        this.timer10 = new TimeHelper();
        this.timestampMillis17 = 1L;
        this.activeState = false;
        this.tickDelay10 = 0;
        this.tickDelay13 = 0;
        this.activeState28 = false;
        this.animationTimerArray = new AnimationTimer[22];
        this.smoothDamperArray = new SmoothDamper[28];
        this.rangeSettingArray = new RangeSetting[44];
        AnimationTimer animationTimer11 = new AnimationTimer();
        this.animationTimer = animationTimer11;
        AnimationTimer animationTimer12 = new AnimationTimer();
        this.animationTimer2 = animationTimer12;
        this.animationTimer3 = new AnimationTimer();
        this.animationTimer4 = new AnimationTimer();
        this.animationTimer5 = new AnimationTimer();
        this.animationTimer6 = new AnimationTimer();
        this.animationTimer7 = new AnimationTimer();
        this.animationTimer8 = new AnimationTimer();
        SmoothDamper smoothDamper4 = new SmoothDamper(20, 0.0);
        this.smoothDamper = smoothDamper4;
        SmoothDamper smoothDamper5 = new SmoothDamper(20, 5.0);
        this.smoothDamper2 = smoothDamper5;
        AttackTimeTracker auxiliaryLongEntry2 = new AttackTimeTracker();
        this.auxiliaryLongEntry = auxiliaryLongEntry2;
        this.animationTimer9 = new AnimationTimer();
        this.timer11 = new TimeHelper();
        this.animationTimer10 = new AnimationTimer();
        this.tickDelay6 = 0;
        this.tickDelay7 = 0;
        this.tickDelay11 = 0;
        this.expanded2 = false;
        SmoothDamper smoothDamper6 = new SmoothDamper(100, 0.0);
        this.smoothDamper3 = smoothDamper6;
        BacktrackRecord backtrackRecord2 = new BacktrackRecord(23);
        this.backtrackRecord = backtrackRecord2;
        this.tickDelay15 = 0;
        this.doubleEntry27 = new AttackTargetDistance();
        this.doubleEntry = new AttackTargetDistance();
        this.doubleEntry33 = new AttackTargetDistance();
        this.doubleEntry29 = new AttackTargetDistance();
        this.doubleEntry34 = new AttackTargetDistance();
        this.activeState22 = false;
        this.intValueArray2 = new int[2];
        double[] coordArray = new double[2];
        this.coordOffsetArray = coordArray;
        this.visible2 = false;
        this.tickDelay9 = 0;
        this.enabled2 = false;
        this.settingClickPattern.getModeSetting(() -> this.updateKillAura2());
        this.processByte();
        simulatedPlayer = this.simulatedPlayer;
        BooleanSettingProperty booleanSettingEntryEntryEntry = new BooleanSettingProperty(this, this.settingRandomize);
        AttackRangeRenderer auxiliaryRenderListenerAdapter = new AttackRangeRenderer(booleanSettingEntryEntryEntry, () -> this.canAttack());
        simulatedPlayer.auxiliaryRenderListenerAdapter = auxiliaryRenderListenerAdapter;
    }

    @Override
    public void onEnable() {
        this.registerEvent(28, 18);
        this.registerEvent(2);
        this.registerEvent(7);
        this.registerEvent(6);
        this.registerEvent(40);
        this.registerEvent(21);
        this.registerEvent(18);
        this.registerEvent(32);
        this.registerEvent(29);
        this.registerEvent(26);
        this.registerEvent(19);
        this.registerEvent(34);
        this.registerEvent(20);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.copyOnWriteArrayList.clear();
        this.intValueArray[0] = this.intValueArray[1] = 0;
        this.intValueArray2[0] = this.intValueArray2[1] = 0;
        this.coordOffsetArray[0] = this.coordOffsetArray[1] = 0.0;
        this.updateKillAura3();
        this.updateKillAura7();
        this.simulatedPlayer.updateState();
        this.tickDelay9 = 0;
        this.enabled2 = false;
    }

    @Override
    public void onPreDisable() {
        this.itemList3 = ClickPatternManager.getArrayList();
        ArrayList itemList32 = this.itemList3;
        String[] textArray = ClickPatternManager.getTextArray(itemList32);
        this.settingClickPattern.applyStringArray2(textArray);
        String selected = this.settingClickPattern.getSelected();
        ArrayList itemList33 = this.itemList3;
        String text = ClickPatternManager.getText(selected, itemList33);
        this.settingClickPattern.setSelected(text);
        String selected2 = this.settingClickPattern.getSelected();
        ArrayList itemList34 = this.itemList3;
        this.clickPatternProfile = ClickPatternManager.getClickPatternProfile(selected2, itemList34);
        this.activeState3 = true;
        this.reachDistance = this.settingRange.getValue();
        this.intValueArray[0] = this.intValueArray[1] = 0;
        this.applyString4("0");
        this.target = null;
        RotationManager rotationManager = ClientGlobals.rotationManager;
        this.rotation = rotationManager.getRotation3();
        this.targetEntity2 = null;
        this.activeState22 = true;
        this.processChar();
        SimulatedPlayer settingAutomatischerTöter2 = this.simulatedPlayer;
        float value;
        if (!this.settingOnlyRotation.getSelected().equals("Advanced")) {
            value = 20.0F;
        } else {
            value = (float)this.settingRadius.getDouble6();
        }

        settingAutomatischerTöter2.processAction(value, 1000);
        this.name2 = this.settingLegit.getSelected();
        this.simulatedPlayer.updateState();
        this.doubleEntry.applyRangeSetting(this.settingPreAimRange);
        this.doubleEntry27.applyRangeSetting(this.settingPreHitRange);
        this.doubleEntry33.applyRangeSetting(this.settingAimThroughWallsRange);
        this.doubleEntry29.applyRangeSetting(this.settingPreHitFovYaw);
        this.doubleEntry34.applyRangeSetting(this.settingPreHitFovPitch);
        this.settingMaxDeathTime.recalculate();
    }

    @Override
    public void onRotation(EventRotation event) {
        if (this.checkCondition4()) {
            if (!BlockCache.canExecute()) {
                if (!ClientGlobals.moduleManager.targetStrafe.isToggled() || ClientGlobals.moduleManager.targetStrafe.targetEntity == null) {
                    if (!this.settingMoveFix.isEnabled()) {
                        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
                        event.setScaleFactor(scaleFactor1);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(EventClick event) {
        this.activeState28 = false;
        if ((this.checkCondition4() || this.settingNoRotation.isEnabled())
        && (!ClientGlobals.moduleManager.autoGapple.isToggled() || ClientGlobals.moduleManager.autoGapple.getKeyCode2() == -1)
        && this.canAttack8()) {
            boolean completed = false;
            if (this.target != null) {
                int int3 = minecraft.getInt3();
                if (int3 > this.settingDelayClicksAfterClosingGuiScreen.getIndexOffset2() && minecraft.currentScreen == null || this.settingInInv.isEnabled()) {
                    if (!this.checkRangeSetting(this.settingDelayClicksAfterRespawn) || this.settingInInv.isEnabled()) {
                        if (this.activeState22) {
                            this.processChar();
                            this.activeState22 = false;
                        }
                        this.onClick3(event);
                        completed = true;
                    }
                }
            }
            if (!completed) {
                this.processAction3();
                this.activeState22 = true;
                this.processChar();
                this.activeState3 = true;
            }


            String selected = this.settingSimple.getSelected();
            switch (selected) {
                case "HazeRange":
                    if (this.target != null && this.target.hurtTime == 10) {
                        double offset = this.reachDistance + this.settingHazeAdd.getValue();
                        this.reachDistance = Math.min(offset, this.settingHazeMax.getValue());
                        this.timestampMillis30 = System.currentTimeMillis();
                    }

                    if (this.target != this.targetEntity2 || this.targetEntity2 == null) {
                        this.reachDistance = this.settingRange.getValue();
                    }

                    if (Math.abs(System.currentTimeMillis() - this.timestampMillis30) > 1000L) {
                        this.reachDistance = this.settingRange.getValue();
                    }
                    break;
                case "IntervalRange":
                    this.reachDistance = this.settingRange.getValue();
                    double tickDelay102 = (double)this.tickDelay10;
                    if (tickDelay102 >= this.settingInterval.getValue() && this.target != null) {
                        double hurtTime = (double)this.target.hurtTime;
                        if (hurtTime <= this.settingHurtTimeToHitWithReach.getValue()) {
                            double distance = (double)minecraft.thePlayer.getDistanceToEntity(this.target);
                            if (distance > this.settingMinReachToIntervalHit.getValue() && minecraft.thePlayer.getDistanceSqToEntity(this.target) < 6.0F) {
                                this.reachDistance = this.settingRangeAtInterval.getValue();
                                this.tickDelay10 = 0;
                            }
                        }
                    }
                case "Grim":
                    break;
                case "Dual":
                    if (this.target != null) {
                        double offset2;
                        if (this.expanded2) {
                            offset2 = this.settingExtendedRange.getDouble6();
                        } else {
                            offset2 = this.settingExtendedRange.getDouble3();
                        }

                        this.reachDistance = offset2;
                        if (this.target.hurtTime == 9) {
                            int int12 = MathHelper.getInt12((int)this.settingChanceOfExtendedRange.getValue(), 0, 100);
                            this.expanded2 = this.smoothDamper3.checkCondition(int12);
                        }
                    }
                    break;
                default:
                    this.reachDistance = this.settingRange.getValue();
            }
        } else {
            this.activeState22 = true;
            this.processChar();
            this.processAction3();
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        if (packet.getPacket() instanceof C03PacketPlayer) {
            if (this.activeState14) {
                if (!BlockCache.canExecute()) {
                    packet.cancel();
                }
            }
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (this.checkCondition4()) {
            if (!BlockCache.canExecute()) {
                if (!ClientGlobals.moduleManager.targetStrafe.isToggled() || ClientGlobals.moduleManager.targetStrafe.targetEntity == null) {
                    if (!this.settingJumpFix.isEnabled()) {
                        float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
                        event.setScaleFactor(scaleFactor1);
                    }
                }
            }
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
        this.updateKillAura4();
        if (this.target != null) {
            if (this.canAttack8()) {
                if (!BlockCache.canExecute()) {
                    String selected = this.settingNone3.getSelected();
switch (selected) {
            case "Pre":
                this.processAction2();
        }
                }
            }
        }
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        boolean completed = false;
        if (this.settingLockView.isEnabled()
        && this.canAttack8()
        && this.target != null
        && ClientGlobals.rotationManager.checkModule(this)
        && !this.settingOnlyRotation.getSelected().equals("LegitTest")) {
            if (!this.settingOnlyRotation.getSelected().equals("Noise2")) {
                int index = (int)(System.currentTimeMillis() - this.timestampMillis2);
                double offset = (double)this.intValueArray[0] / (double)this.tickDelay12 * (double)index;
                double offset2 = (double)this.intValueArray[1] / (double)this.tickDelay12 * (double)index;
                this.coordOffsetArray[0] = this.coordOffsetArray[0] + offset;
                this.coordOffsetArray[1] = this.coordOffsetArray[1] + offset2;
                int index2 = (int)Math.round(this.coordOffsetArray[0] - (double)this.intValueArray2[0]);
                int index3 = (int)Math.round(this.coordOffsetArray[1] - (double)this.intValueArray2[1]);
                this.intValueArray2[0] = this.intValueArray2[0] + index2;
                this.intValueArray2[1] = this.intValueArray2[1] + index3;
                event.processAction(index2);
                event.processAction2(index3);
                event.setActive2(true);
                event.setActive(true);
                completed = true;
            }
            if (!completed) {
                Client.getClient().getClass();
            }
        }
        if (!completed) {
            this.intValueArray2[0] = this.intValueArray2[1] = 0;
            this.coordOffsetArray[0] = this.coordOffsetArray[1] = 0.0;
            this.intValueArray[0] = this.intValueArray[1] = 0;
        }


        this.timestampMillis2 = System.currentTimeMillis();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.updateKillAura3();
        this.updateKillAura9();
    }

    @Override
    public void onPacketPostSend(EventPacket packet) {
        if (this.settingCritFix.isEnabled()) {
            if (this.settingCritFixPacketCheck.isEnabled()) {
                Packet packet2 = packet.getPacket();
                if (packet2 instanceof S0BPacketAnimation && minecraft.theWorld != null) {
                    S0BPacketAnimation s0BPacketAnimation = (S0BPacketAnimation)packet2;
                    if (s0BPacketAnimation.getIndex2() != 4) {
                        return;
                    }

                    Entity entityByID = minecraft.theWorld.getEntityByID(s0BPacketAnimation.getIndex());
                    if (this.target != null && entityByID != null && entityByID.getEntityId() == this.target.getEntityId()) {
                        this.visible2 = true;
                    }
                }

                if (packet2 instanceof S08PacketPlayerPosLook) {
                    S08PacketPlayerPosLook s08PacketPlayerPosLook = (S08PacketPlayerPosLook)packet2;
                    if (new Vec3(s08PacketPlayerPosLook.getCoordinate(), s08PacketPlayerPosLook.getCoordinate1(), s08PacketPlayerPosLook.getCoordinate4())
                    .getDouble2(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ)
                    > 4.0) {
                        this.tickDelay15 = 0;
                        this.updateKillAura9();
                    }
                }
            }
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (this.checkCondition4()) {
            if (!BlockCache.canExecute()) {
                if (!ClientGlobals.moduleManager.targetStrafe.isToggled() || ClientGlobals.moduleManager.targetStrafe.targetEntity == null) {
                    if (this.settingMoveFix.isEnabled()) {
                        if (!this.settingDefault.getSelected().equals("None")) {
                            if (this.settingDefault.getSelected().equals("Advanced")) {
                                AuraRenderMode auraRenderMode = AuraRenderMode.ADVANCED;
                                event.setAuraRenderMode(auraRenderMode);
                            } else {
                                AuraRenderMode auraRenderMode2 = AuraRenderMode.DEFAULT;
                                event.setAuraRenderMode(auraRenderMode2);
                            }

                            if (this.settingSilentMoveFixIntave.isEnabled()) {
                                event.setState1(true);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void updateKillAura() {
        this.reachDistance = this.settingRange.getValue();
        this.name2 = this.settingLegit.getSelected();
    }

    @Override
    public void onPostMoveEntity(EventMoveEntity event) {
        if (this.activeState14) {
            if (!BlockCache.canExecute()) {
                event.setCoordOffset2(0.0);
                event.setCoordOffset1(0.0);
                event.setCoordOffset(0.0);
            }
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (ClientGlobals.moduleManager.line.isToggled()
        && ClientGlobals.moduleManager.line.settingActivateAuraLine.isEnabled()
        && !ClientGlobals.moduleManager.line.settingDisplayLegitimateRotations.isEnabled()) {
            boolean enabled = ClientGlobals.moduleManager.line.settingResetOnTeleportAuraLine2.canExecute();
            MovementHandler.processBoolean(enabled, ClientGlobals.moduleManager.line.settingResetOnTeleportAuraLine2.canExecute2());
            MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass2(renderPass));
        }

        if (this.target != null) {
            boolean enabled3 = this.shaderOptions.canExecute();
            ShaderOptions shaderOptions2 = this.shaderOptions;
            MovementHandler.processBoolean(enabled3, shaderOptions2.canExecute2());
            MovementHandler.applyRunnableTask(false, renderPass2 -> this.applyRenderPass(partialTicks, renderPass2));
        }

        if (SimulatedPlayer.positionVec9 != null) {
            RenderUtils.updateState();
            GL11.glPushMatrix();
            GL11.glLineWidth(3.0F);
            double offset = -minecraft.getRenderManager().getRenderPosX();
            double offset2 = -minecraft.getRenderManager().getRenderPosY();
            GL11.glTranslated(offset, offset2, -minecraft.getRenderManager().getRenderPosZ());
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtils.drawBoundingBox(AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec9, 0.05));
            GL11.glPopMatrix();
            RenderUtils.prepareScissor();
        }

        if (SimulatedPlayer.advancedDoubleEntry78 != null) {
            PlayerSimulationData advancedDoubleEntry78 = SimulatedPlayer.advancedDoubleEntry78;
            if (advancedDoubleEntry78.targetEntity == this.target && SimulatedPlayer.positionVec8 != null) {
                RenderUtils.updateState();
                GL11.glPushMatrix();
                GL11.glLineWidth(3.0F);
                double offset3 = -minecraft.getRenderManager().getRenderPosX();
                double offset4 = -minecraft.getRenderManager().getRenderPosY();
                GL11.glTranslated(offset3, offset4, -minecraft.getRenderManager().getRenderPosZ());
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                RenderUtils.drawBoundingBox(
                AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec8.getVec39(SimulatedPlayer.positionVec9, (double)partialTicks), 0.05)
                );
                GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 1.0F);
                RenderUtils.drawBoundingBox(
                AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec2.getVec39(SimulatedPlayer.positionVec6, (double)partialTicks), 0.05)
                );
                GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
                RenderUtils.drawBoundingBox(
                AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec.getVec39(SimulatedPlayer.positionVec4, (double)partialTicks), 0.05)
                );
                GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 1.0F);
                RenderUtils.drawBoundingBox(
                AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec3.getVec39(SimulatedPlayer.positionVec7, (double)partialTicks), 0.05)
                );
                GL11.glPopMatrix();
                RenderUtils.prepareScissor();
            }
        }

        if (this.target != null) {
            RenderUtils.updateState();
            GL11.glPushMatrix();
            GL11.glLineWidth(3.0F);
            double offset5 = -minecraft.getRenderManager().getRenderPosX();
            double offset6 = -minecraft.getRenderManager().getRenderPosY();
            GL11.glTranslated(offset5, offset6, -minecraft.getRenderManager().getRenderPosZ());
            int index = 0;

            while (true) {
                BacktrackRecord backtrackRecord2 = this.target.backtrackRecord2;
                if (index >= backtrackRecord2.getInt()) {
                    for (Vec3 vec : ((Iterable<Vec3>) this.target.backtrackRecord2.getItemList())) {
                        this.backtrackRecord.applyObject(vec);
                    }

                    if (SimulatedPlayer.positionVec3 != null) {
                        GlStateManager.color4f2(1.0F, 0.0F, 0.0F, 1.0F);
                        RenderUtils.drawBoundingBox(
                        AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec3.getVec39(SimulatedPlayer.positionVec7, (double)partialTicks), 0.05)
                        );
                    }

                    if (SimulatedPlayer.positionVec3 != null) {
                        GlStateManager.color4f2(0.0F, 1.0F, 0.0F, 1.0F);
                        RenderUtils.drawBoundingBox(
                        AxisAlignedBB.getAxisAlignedBB(SimulatedPlayer.positionVec2.getVec39(SimulatedPlayer.positionVec6, (double)partialTicks), 0.05)
                        );
                    }

                    GL11.glPopMatrix();
                    RenderUtils.prepareScissor();
                    break;
                }

                Vec3 object = (Vec3)this.target.backtrackRecord2.getObject(index);
                if (this.backtrackRecord.getItemList().size() > index) {
                    BacktrackRecord backtrackRecord3 = this.backtrackRecord;
                    object = object.getVec39((Vec3)backtrackRecord3.getObject(index), (double)partialTicks);
                }

                RenderUtils.drawBoundingBox(AxisAlignedBB.getAxisAlignedBB(object, 0.05));
                index++;
            }
        }
    }

    @Override
    public void onChat2(EventChat event) {
        if (!this.settingSlowDown.isEnabled()) {
            if (this.target != null) {
                event.processAction(1.0);
            }
        }
    }

    @Override
    public void onPostMotion() {
        this.tickDelay16++;
        if (this.canAttack8()) {
            if (this.target != null) {
                if (this.settingNone3.getSelected().equals("Bug")) {
                    if (minecraft.thePlayer.getItemStack() == null) {
                        return;
                    }

                    if (!(minecraft.thePlayer.getItemStack().getItem() instanceof ItemSword)) {
                        return;
                    }

                    if (this.target.hurtTime > 2) {
                        return;
                    }

                    if (!this.activeState31) {
                        return;
                    }

                    int index = SlotManager.indexOffset == 0 ? 1 : -1;
                    int index2 = SlotManager.indexOffset + index;
                    SlotManager.applyModule3(this, 1, index2);
                    this.activeState31 = false;
                    this.tickDelay16 = 0;
                }
            }
        }
    }

    @Override
    public void onPostRenderTick() {
        if (this.target == null) {
            this.updateKillAura7();
        }

        if (this.target != null) {
            if (ClientGlobals.rotationManager.getIndexOffset8() < this.getInt5() && !this.settingNoRotation.isEnabled()) {
                return;
            }

            float rotationYaw = minecraft.thePlayer.rotationYaw;
            float rotationPitch = minecraft.thePlayer.rotationPitch;
            if (this.settingNoRotation.isEnabled()) {
                minecraft.thePlayer.rotationYaw = this.rotation.yaw;
                minecraft.thePlayer.rotationPitch = this.rotation.pitch;
            }

            this.processFloat(1.0F);
            if (!this.settingNoRotation.isEnabled()) {
                return;
            }

            minecraft.thePlayer.rotationYaw = rotationYaw;
            minecraft.thePlayer.rotationPitch = rotationPitch;
        }
    }

    @Override
    public void onClientTick() {
        this.tickDelay12 = (int)Math.abs(this.timestampMillis25 - System.currentTimeMillis());
        this.timestampMillis25 = System.currentTimeMillis();
        this.intValueArray2[0] = this.intValueArray2[1] = 0;
        this.coordOffsetArray[0] = this.coordOffsetArray[1] = 0.0;
    }

    @Override
    public void onPreMotionUpdate() {
        if (this.movingObjectPosition != null) {
            this.positionVec2 = this.movingObjectPosition.hitVec;
        }

        this.movingObjectPosition = minecraft.objectMouseOver;
        if (this.target != null) {
            if (this.canAttack8()) {
                if (!BlockCache.canExecute()) {
                    String selected = this.settingNone3.getSelected();
                    selected.hashCode();
                    int state = -1;
                    if (selected.equals("Spam")) {
                        state = 0;
                    } else if (selected.equals("Post")) {
                        state = 1;
                    }

                    if (state == 0 || state == 1) {
                        this.processAction2();
                    }
                }
            }
        }
    }

    @Override
    public void onPreTick() {
        if (minecraft.currentScreen != null && minecraft.guiScreen == null) {
            this.settingDelayClicksAfterClosingGuiScreen.recalculate();
            this.settingDelayRotationsAfterClosingGuiScreen.recalculate();
        }

        this.doubleEntry27.applyEntityLivingBase2(this.target, this.targetEntity2, this.settingPreHitRange, 1680333436);
        this.doubleEntry.applyEntityLivingBase2(this.target, this.targetEntity2, this.settingPreAimRange, 1680333436);
        this.doubleEntry33.applyEntityLivingBase2(this.target, this.targetEntity2, this.settingAimThroughWallsRange, 1680333436);
        float[] factorArray = this.target == null
        ? new float[]{0.0F, 0.0F}
        : SimulatedPlayer.getScaleFactorArray(this.target, minecraft.thePlayer.rotationYaw, 263465940, minecraft.thePlayer.rotationPitch, 6342);
        this.doubleEntry29.applyEntityLivingBase(this.target, this.targetEntity2, this.settingPreHitFovYaw, (double)Math.abs(factorArray[0]));
        this.doubleEntry34.applyEntityLivingBase(this.target, this.targetEntity2, this.settingPreHitFovPitch, (double)Math.abs(factorArray[1]));
        this.intValueArray[0] = this.intValueArray[1] = 0;
        this.targetEntity2 = this.target;
        this.target = null;
        if (!this.canAttack8() && !this.settingCloseGuisIfTargetArrives.isEnabled()) {
            this.rotation = ClientGlobals.rotationManager.getRotation2();
        } else {
            List list = this.getList();
            Vec3 positionVec3 = this.positionVec;
            Pair pair = this.getPair(list);
            this.positionVec = (Vec3)pair.getSecond();
            EntityLivingBase livingEntity = (EntityLivingBase)pair.getFirst();
            this.applyString4("" + Math.min(list.isEmpty() ? 0 : list.size(), 9));
            if (this.target != null) {
                if (this.settingCloseGuisIfTargetArrives.isEnabled()
                && !this.canAttack8()
                && !(minecraft.currentScreen instanceof ClickGuiScreen)
                && this.target != null) {
                    minecraft.thePlayer.updateState5();
                    this.rotation = ClientGlobals.rotationManager.getRotation2();
                    this.target = null;
                    return;
                }

                EntityLivingBase target2 = this.target;
                if (this.settingSimple.getSelected().equals("Grim") && this.target != null) {
                    ArrayList arrayList = new ArrayList();

                    for (Object object : list) {
                        if (object instanceof EntityLivingBase && !this.itemList4.contains(object)) {
                            EntityLivingBase livingEntity2 = (EntityLivingBase)object;
                            if (!(livingEntity2.getDistanceToEntity(minecraft.thePlayer) > 3.0F)) {
                                arrayList.add(livingEntity2);
                            }
                        }
                    }

                    if (target2 != null && target2.hurtTime != 0 && this.activeState32 && minecraft.thePlayer.onGround) {
                        this.activeState32 = false;
                        this.updateKillAura6();
                    }

                    if (!arrayList.isEmpty()) {
                        this.updateKillAura7();
                    }
                }
            }

            this.itemList4.clear();
            this.itemList4.addAll(list);
            if (livingEntity != null) {
                this.timer11.reset();
                this.settingTargetLoseDelay.recalculate();
                this.settingMaxDeathTime.recalculate();
            }

            if (livingEntity == null
            && this.targetEntity3 != null
            && !this.timer11.hasReached((long)this.settingTargetLoseDelay.getIndexOffset2())
            && (double)minecraft.thePlayer.getDistanceToEntity(this.targetEntity3) < this.doubleEntry.getCoordinate()) {
                livingEntity = this.targetEntity3;
                this.target = livingEntity;
            }

            this.target = this.getEntityLivingBase(this.target);
            EntityLivingBase entityLivingBase = this.getEntityLivingBase(livingEntity);
            if (entityLivingBase != null
            && this.targetEntity2 != null
            && this.target != null
            && !this.target.getText().equals(this.targetEntity2.getText())
            && this.settingTargetDelay.getValue() != 0.0) {
                if (!this.timer
                .hasReached(
                (long)this.settingTargetDelay.getValue() + SettingContainer.settingProvider.getLong(0L, 60L)
                )) {
                    this.target = this.targetEntity2;
                    entityLivingBase = this.target;
                    this.positionVec = positionVec3;
                } else {
                    this.timer.reset();
                }
            }

            if ((this.targetEntity3 = entityLivingBase) != null && this.target != null) {
                if ((
                minecraft.getInt3() <= this.settingDelayRotationsAfterClosingGuiScreen.getIndexOffset2()
                || this.checkRangeSetting(this.settingDelayRotationsAfterRespawn)
                )
                && !this.settingInInv.isEnabled()) {
                    this.rotation = ClientGlobals.rotationManager.getRotation3();
                    if (!this.settingNoRotation.isEnabled()) {
                        ClientGlobals.rotationManager.applyRotation2(this.rotation, this.getInt5(), 1898960900, this);
                    }

                    return;
                }

                this.simulatedPlayer.coordOffset93 = this.settingAdditionalMinAimspeed.getValue();
                this.simulatedPlayer.coordOffset59 = this.settingAdditionalDynamicSpeed.getValue();
                this.simulatedPlayer.coordOffset4 = this.settingAdditionalAimspeedIfNeeded.getValue();
                this.simulatedPlayer.coordOffset26 = this.settingProbabilityOfAimingEvenWhenNotNeeded.getDouble2(true) / 100.0;
                this.simulatedPlayer.state57 = this.settingTest.isEnabled();
                this.simulatedPlayer.coordOffset81 = this.settingAimSpeed.getDouble2(true) / 100.0 * 8.0 + 2.0;
                this.simulatedPlayer.coordOffset23 = this.settingDecelerationfactorPre.getDouble2(true) / 100.0 * 4.0 + 0.5;
                this.simulatedPlayer.coordOffset2 = this.settingDecelerationfactorPost.getDouble2(true) / 100.0 * 4.0 + 1.0;
                this.simulatedPlayer.indexOffset86 = MathHelper.getInt12((int)this.settingReactionTime.getDouble3(), 0, 6);
                this.simulatedPlayer.indexOffset52 = MathHelper.getInt12((int)this.settingReactionTime.getDouble6(), 0, 6);
                this.simulatedPlayer.indexOffset80 = (int)this.settingAttention.getDouble2(true);
                this.simulatedPlayer.coordOffset18 = this.settingAdditionalInaccuracy.getDouble2(true) / 100.0 * 20.0;
                this.simulatedPlayer.indexOffset75 = this.settingSuperCorrectionDelay.getInt5(true);
                this.simulatedPlayer.coordOffset8 = this.settingSuperCorrectionProbability.getDouble2(true) / 100.0;
                this.simulatedPlayer.state70 = this.settingAdditionalAccuracy.isEnabled();
                this.simulatedPlayer.indexOffset = (int)this.settingLookaheadTime.getDouble5(true);
                this.simulatedPlayer.indexOffset48 = (int)this.settingLookaheadTime.getDouble4(true);
                this.simulatedPlayer.coordOffset53 = this.settingPredictionInaccuracy.getDouble5(true) / 100.0;
                this.simulatedPlayer.coordOffset30 = this.settingPredictionInaccuracy.getDouble4(true) / 100.0;
                this.simulatedPlayer.scaleFactor47 = (float)PlayerUtil.getDouble(
                0.016666666666666666, 0.05, 1.0 - this.settingPredictionInaccuracySmoothness.getValue() / 100.0
                );
                this.simulatedPlayer.coordOffset = this.settingBestvecPitchinterpolation.getDouble5(true) / 100.0;
                this.simulatedPlayer.coordOffset14 = this.settingRotationOnTarget.getDouble5(true) / 100.0;
                this.simulatedPlayer.coordOffset10 = this.settingRotationOnTarget.getDouble4(true) / 100.0;
                this.simulatedPlayer.coordOffset56 = this.settingMaxOrthogonalMovement.getDouble2(true);
                this.simulatedPlayer.coordOffset35 = this.settingOrthogonalScale.getDouble5(true) / 100.0;
                this.simulatedPlayer.coordOffset37 = this.settingOrthogonalScale.getDouble4(true) / 100.0;
                this.simulatedPlayer.coordOffset33 = this.settingMaxAimpointAnglediff.getDouble2(true);
                this.simulatedPlayer.coordOffset68 = this.settingRotationYawOffset.getDouble5(true);
                this.simulatedPlayer.coordOffset42 = this.settingRotationYawOffset.getDouble4(true);
                this.simulatedPlayer.coordOffset20 = this.settingRotationPitchOffset.getDouble5(true);
                this.simulatedPlayer.coordOffset90 = this.settingRotationPitchOffset.getDouble4(true);
                this.simulatedPlayer.coordOffset73 = this.settingRotationYawOffsetSpeed.getDouble2(true) / 100.0 * 0.2;
                this.simulatedPlayer.coordOffset9 = this.settingRotationPitchOffsetSpeed.getDouble2(true) / 100.0 * 0.2;
                this.simulatedPlayer.state55 = this.settingNoYawAdjustmentIfTargetOutOfReach.isEnabled();
                this.simulatedPlayer.state13 = this.settingNoPitchAdjustmentIfTargetOutOfReach.isEnabled();
                this.simulatedPlayer.state = this.settingApplyRecordedMouseJitterMovementIfAvailable.isEnabled();
                this.simulatedPlayer.state22 = this.settingUltimateTrashcodeBypass.isEnabled();
                this.simulatedPlayer.state11 = this.settingAdaptCurveIfNeeded.isEnabled();
                this.simulatedPlayer.indexOffset66 = (int)this.settingTargetResponse.getDouble5(true);
                this.simulatedPlayer.indexOffset85 = (int)this.settingTargetResponse.getDouble4(true);
                this.simulatedPlayer.indexOffset46 = (int)this.settingPlayerResponse.getDouble5(true);
                this.simulatedPlayer.indexOffset5 = (int)this.settingPlayerResponse.getDouble4(true);
                this.simulatedPlayer.scaleFactor32 = (float)((int)this.settingAcceleration.getDouble9());
                this.simulatedPlayer.scaleFactor15 = (float)((int)this.settingAcceleration.getDouble2());
                this.simulatedPlayer.scaleFactor79 = (float)((int)this.settingAimSpeed2.getDouble9());
                this.simulatedPlayer.scaleFactor77 = (float)((int)this.settingAimSpeed2.getDouble2());
                this.simulatedPlayer.coordOffset1 = this.settingScale.getDouble2(true);
                this.simulatedPlayer.coordOffset6 = this.settingBetterAcceleration2.getDouble2(true);
                if (entityLivingBase != this.target) {
                    RayTraceUtil.applyString("ERROR");
                }

                Rotation rotation2 = this.simulatedPlayer
                .getRotation(
                this.target,
                
                this.settingYawAcceleration,
                this.settingPitchAcceleration,
                this.settingYawSpeed,
                this.settingPitchSpeed,
                this.getFloatArray()[0],
                599338805,
                
                this.getFloatArray()[1],
                this.settingOnlyRotation.getSelected(),
                this.settingInterpolation.isEnabled(),
                this.settingSmartAim.isEnabled(),
                this.settingIntave13.isEnabled(),
                (float)this.settingRandomStrength.getValue(),
                this.positionVec,
                this.settingThroughWalls.isEnabled() && this.settingAimThroughWalls.isEnabled(),
                this.settingAdvancedRots.isEnabled(),
                this.settingNone2.getSelected(),
                this.settingBestHitVec.getSelected(),
                this.reachDistance,
                (int)this.settingHitBoxPercentageVertical2.getValue(),
                (int)this.settingHitBoxPercentageHorizontal2.getValue(),
                this.tickDelay5,
                this.settingSecureRandom.getSelected(),
                this.settingNone4.getSelected(),
                this.settingSmartRotate.isEnabled(),
                this.settingReducedRotate.isEnabled(),
                this.settingPitchOutliers.isEnabled(),
                this.settingPitchOutliersAdd,
                this.settingPitchOutliersDelay,
                this.settingReduceRotationPercentage.getValue(),
                (float)this.settingMaxXAbsDist.getValue(),
                (float)this.settingMaxYAbsDist.getValue(),
                (float)this.settingMinAbsDist.getValue(),
                (float)this.settingXRelDist.getDouble3(),
                (float)this.settingYRelDist.getDouble3(),
                (float)this.settingXRelDist.getDouble6(),
                (float)this.settingYRelDist.getDouble6(),
                (float)this.settingMaxRotationAngle.getValue(),
                (float)this.settingRadius.getDouble3(),
                (float)this.settingBestHitXMinDist.getValue(),
                (float)this.settingBestHitYMInDist.getValue(),
                this.settingBetterAcceleration.isEnabled(),
                (int)this.settingFixRotationChance.getDouble2(true),
                this.settingMaxFixRotationReach.recalculate(),
                this.settingMinFixRotationAngle.getValue(),
                this.settingMinDistanceToBestVecYaw.getValue(),
                this.settingMinDistanceToBestVecPitch.getValue(),
                this.settingMinDistanceToBestVec.getValue(),
                this.settingNone.getSelected(),
                this.settingStopRotationInsideTarget.isEnabled()
                );
                if (!this.settingNoRotation.isEnabled()) {
                    ClientGlobals.rotationManager.applyRotation2(rotation2, this.getInt5(), 1898960900, this);
                }

                this.intValueArray = SimulatedPlayer.getIntArray(
                rotation2.yaw, rotation2.pitch, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch
                );
                this.rotation = rotation2;
            } else {
                this.simulatedPlayer.updateState();
                this.rotation = ClientGlobals.rotationManager.getRotation2();
            }
        }
    }

    private boolean isNoise3ResponseTargetVisible() {
        return this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isReduceRotationPercentageVisible() {
        return !this.settingOnlyRotation.getSelected().equals("Advanced") ? false : this.settingReducedRotate.isEnabled();
    }

    private void processAction() {
        if (!this.settingMode.getSelected().equals("Recommended")) {
            while (true) {
            }
        }

        AnimationTimer animationTimer72 = this.animationTimer7;
        int indexOffset2 = this.settingCPSRecalculateTickDelay.getIndexOffset2();
        if (animationTimer72.checkCondition(indexOffset2)) {
            this.animationTimer7.processAction3();
            this.settingCPSRecalculateTickDelay.getDouble4();
            this.settingCPS3.getDouble4();
        }

        this.settingDCPS.getDouble4();
    }

    private boolean isDelayRotationsAfterClosingGuiScreenVisible() {
        return !this.settingInInv.isEnabled();
    }

    public boolean checkEntity(Entity entity, double offset) {
        if (!(entity instanceof EntityLivingBase)
        || !ChatUtil.checkEntity6(
        entity,
        KillAuraTargetEntity.PLAYER.isSelected(),
        KillAuraTargetEntity.MOB.isSelected(),
        KillAuraTargetEntity.ANIMAL.isSelected(),
        KillAuraTargetEntity.VILLAGER.isSelected(),
        
        KillAuraTargetEntity.ARMOR_STAND.isSelected(),
        KillAuraTargetEntity.INVISIBLE.isSelected(),
        true,
        true,
        true,
        KillAuraTargetEntity.DEAD.isSelected(),
        true,
        this.settingMaxDeathTime.getIndexOffset2()
        )) {
            return false;
        } else if (!minecraft.thePlayer.checkEntity4(entity) && !this.settingAimThroughWalls.isEnabled() && !this.settingSmartAim.isEnabled()) {
            return false;
        } else {
            if (!minecraft.thePlayer.checkEntity4(entity)
            && this.settingAimThroughWalls.isEnabled()
            && (double)minecraft.thePlayer.getDistanceToEntity(entity) > this.doubleEntry33.getCoordinate()) {
                return false;
            } else if (entity instanceof EntityPlayer
            && !ClientGlobals.moduleManager.midClick.enabled2
            && ClientGlobals.moduleManager.midClick.checkEntity(entity)) {
                return false;
            } else {
                float[] scaleFactorArray = SimulatedPlayer.getScaleFactorArray(
                entity,
                Client.getClient().getViaProtocolHelper().scaleFactor1,
                
                263465940,
                Client.getClient().getViaProtocolHelper().scaleFactor2,
                6342
                );
                if (entity instanceof EntityPlayerSP) {
                    return false;
                } else {
                    if ((double)minecraft.thePlayer.getDistanceToEntity(entity) >= offset) {
                        return false;
                    } else {
                        return (double)Math.abs(scaleFactorArray[0]) > this.settingYawFov.getValue()
                        ? false
                        : (double)Math.abs(scaleFactorArray[1]) <= this.settingPitchFov.getValue();
                    }
                }
            }
        }
    }

    public void onClick2(EventClick event, boolean enabled) {
        if (!enabled) {
            this.timer2.reset();
        }

        if (!enabled && minecraft.objectMouseOver != null && event != null) {
            boolean enabled3;
            if (ClientGlobals.moduleManager.criticals.isToggled()
            || (byte)((minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) ? 1 : 0) != 0
            && minecraft.thePlayer.fallDistance > 0.0F
            && !minecraft.thePlayer.onGround
            && !minecraft.thePlayer.canExecute9()
            && !minecraft.thePlayer.isInWater()
            && !minecraft.thePlayer.checkPotion(Potion.potion16)
            && minecraft.thePlayer.ridingEntity == null) {
                enabled3 = true;
            } else {
                enabled3 = false;
            }

            if (!this.visible2 && enabled3) {
                event.setState(true);
            }
        }

        if (event == null) {
            int state = (byte)(enabled ? 1 : 0);
            this.setActive4((state & 1) != 0);
            if (!enabled) {
                EventClick.indexOffset10++;
            }
        } else {
            event.updateInt(1);
            Runnable runnable = () -> this.setState5(enabled);
            event.setRunnable3(runnable);
        }
    }

    private boolean checkString(String text) {
        return text.equals("AAC") || text.equals("Legit") || text.equals("None") || text.equals("Test");
    }

    private static double getDistanceToTarget(Entity entity) {
        return (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
    }

    private void applyRenderPass(float partialTicks, RenderPass renderPass) {
        double offset = (double)partialTicks;
        this.applyRenderPass3(renderPass, offset);
    }

    private void applyList(List list) {
        String selected = this.settingUltimateSwitch.getSelected();
switch (selected) {
            case "FOV":
                list.sort(Comparator.comparingDouble(object -> this.getDistanceToTarget2((Entity)object)));
                return;
            case "Health":
                list.sort(Comparator.comparingDouble(object2 -> getDistanceToTarget3((Entity)object2)));
                return;
            case "Distance":
                list.sort(Comparator.comparingDouble(object3 -> getDistanceToTarget((Entity)object3)));
                return;
            case "Single":
                list.sort(Comparator.comparingDouble(object4 -> this.getDistanceToTarget4((Entity)object4)));
                return;
            case "UltimateSwitch":
                list.sort(Comparator.comparingDouble(object5 -> this.getDistanceToTarget5((Entity)object5)));
                return;
            case "Short":
                Comparator comparator = Comparator.comparingDouble(object6 -> this.getDistanceToTarget7((Entity)object6));
                list.sort(comparator);
                return;
            case "ShortSwitch":
                Comparator comparator2 = Comparator.comparingDouble(object7 -> this.getDistanceToTarget6((Entity)object7));
                list.sort(comparator2);
                return;
        }
    }

    private boolean checkCondition(int index) {
        return index > 0;
    }

    public double getDistanceToTarget2(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else {
            double posX = entity.posX;
            double posY = entity.posY;
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            Client client = Client.getClient();
            return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
        }
    }

    private void onClick3(EventClick event) {
        if (this.target == null || BlockCache.canExecute()) {
            this.processAction3();
            return;
        }

        boolean preCheck = this.checkCondition();
        int index = this.getInt7(preCheck);
        String selected3 = this.settingNone3.getSelected();
        ItemStack heldItem = minecraft.thePlayer.getHeldItem();
        if (!this.checkString(selected3) && this.canAttack() && heldItem != null && heldItem.getItem() instanceof ItemSword) {
            if (ClientGlobals.moduleManager.fixes.canExecute8()) {
                if (!selected3.equals("Bug")
                    || Math.abs(minecraft.thePlayer.inventoryPlayer.currentItem - SlotManager.indexOffset) != 1
                    || this.tickDelay16 >= 4) {
                    this.tickDelay++;
                    return;
                }
            }

            switch (selected3) {
                case "Constant":
                    if (this.checkCondition(index)) {
                        this.processAction(index);
                    }
                    this.processAction2();
                    break;
                case "Constant2":
                    if (this.checkCondition(index)) {
                        this.processAction(index);
                    }
                    if (!minecraft.thePlayer.isUsingItem()) {
                        this.processAction2();
                    }
                    break;
                case "UnblockOnHit":
                    if (this.checkCondition(index)) {
                        this.updateKillAura5();
                        this.processAction(index);
                    }
                    this.processAction2();
                    break;
                case "UnblockOnHit2":
                    if (this.checkCondition(index)) {
                        this.updateKillAura5();
                        this.processAction(index);
                    }
                    if (!minecraft.thePlayer.isUsingItem()) {
                        this.processAction2();
                    }
                    break;
                case "Spam":
                    this.updateKillAura5();
                    if (this.checkCondition(index)) {
                        this.processAction(index);
                    }
                    break;
                case "SlowSpam":
                    if (minecraft.thePlayer.ticksExisted % 2 == 0) {
                        this.updateKillAura5();
                    }
                    this.processAction2();
                    if (this.checkCondition(index)) {
                        this.processAction(index);
                    }
                    break;
                case "OldHypixel":
                    if (minecraft.thePlayer.hurtTime > 0) {
                        if (!minecraft.thePlayer.isUsingItem()) {
                            this.processAction2();
                        }
                        if (this.checkCondition(index)) {
                            this.processAction(index);
                        }
                    } else if (minecraft.thePlayer.isUsingItem()) {
                        this.updateKillAura5();
                    } else if (this.checkCondition(index)) {
                        this.processAction(index);
                    }
                    break;
                case "Post":
                case "Pre":
                    if (this.checkCondition(index)) {
                        this.processAction(index);
                    }
                    break;
                case "AACOld":
                    if (!this.canAttack()) {
                        this.updateKillAura5();
                        if (this.checkCondition(index)) {
                            this.processAction(index);
                        }
                    } else {
                        if ((this.target.hurtTime == 0 && this.tickDelay > 4)
                            || (double)this.target.maxHurtTime == this.settingUnblockAAC.getValue()) {
                            if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                                this.updateKillAura5();
                                this.processAction(index);
                                this.processAction2();
                                this.tickDelay = 0;
                                break;
                            }
                        }
                        if (!minecraft.thePlayer.isUsingItem()) {
                            this.processAction2();
                        }
                    }
                    break;
                case "Bug":
                    if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                        if (this.target.hurtTime <= 1 && !this.activeState31 && this.tickDelay16 > 1) {
                            minecraft.clickMouse();
                            if (this.settingInteractAutoBlock.isEnabled()) {
                                minecraft.rightClickMouse();
                            } else {
                                minecraft.playerController.sendUseItem(
                                    minecraft.thePlayer,
                                    minecraft.theWorld,
                                    minecraft.thePlayer.inventoryPlayer.getCurrentItem()
                                );
                            }
                        }
                    } else if (index > 0) {
                        this.processAction(index);
                    }
                    break;
                default:
                    break;
            }

            event.cancel();
            event.setState2(true);
            event.setState1(this.canAttack2());
            this.tickDelay++;
            return;
        }

        if (this.canAttack6()) {
            event.setState1(true);
            this.tickDelay++;
            return;
        }

        if (!selected3.equals("None")
            && heldItem != null
            && heldItem.getItem() instanceof ItemSword
            && !ClientGlobals.moduleManager.fixes.canExecute8()
            && this.canAttack()) {
            switch (selected3) {
                case "Legit":
                    if (minecraft.thePlayer.isUsingItem()) {
                        event.setState11(false);
                    } else {
                        this.onClick4(index, event);
                        this.onClick5(event);
                    }
                    break;
                case "AAC":
                    MovingObjectPosition movingObjectPosition2 = CombatRayTrace.getMovingObjectPosition(
                        1, ClientGlobals.rotationManager.getRotation2(), this.reachDistance
                    );
                    boolean unblockCheck = (this.target.hurtTime == 0 && this.tickDelay > 4)
                        || (double)this.target.maxHurtTime == this.settingUnblockAAC.getValue() + 1.0
                        || (double)this.target.maxHurtTime == this.settingUnblockAAC.getValue();
                    if (unblockCheck
                        && (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
                            || movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.ENTITY)) {
                        if (minecraft.thePlayer.isUsingItem()) {
                            event.setState11(false);
                        } else {
                            this.onClick4(index, event);
                            this.tickDelay = 0;
                            this.onClick5(event);
                        }
                    } else {
                        this.onClick5(event);
                    }
                    break;
                case "Test":
                    if (minecraft.thePlayer.isUsingItem()) {
                        event.setState11(false);
                    } else if (minecraft.thePlayer.hurtTime == 2) {
                        this.onClick4(index, event);
                        this.onClick5(event);
                    } else if (this.checkCondition(index)) {
                        this.onClick4(index, event);
                    }
                    break;
                default:
                    break;
            }
        } else if (!minecraft.thePlayer.isUsingItem()) {
            this.onClick4(index, event);
        }

        event.setState8(this.settingLongClick.isEnabled());
        if (this.canAttack5() && preCheck) {
            event.setState5(true);
            event.setState4(this.activeState19);
        }
        this.tickDelay++;
    }

    private Pair getPair(List list) {
        List list2 = ((List)(list.stream().filter(object -> this.checkEntity3((Entity)object)).collect(Collectors.toList())));
        this.positionVec = null;
        if (this.settingSmartAim.isEnabled() && (!this.settingThroughWalls.isEnabled() || !this.settingAimThroughWalls.isEnabled())) {
            Vec3 vec = this.getVec3(list2);
            if (vec != null) {
                return new Pair(vec, this.target);
            }

            ArrayList arrayList = new ArrayList(list);
            arrayList.removeAll(list2);
            Vec3 vec2 = this.getVec3(arrayList);
            if (vec2 != null) {
                return new Pair(vec2, null);
            }
        }

        if (this.settingAimThroughWalls.isEnabled() || !this.settingSmartAim.isEnabled()) {
            if (!list2.isEmpty()) {
                this.target = (EntityLivingBase)list2.get(0);
                return new Pair(null, this.target);
            }

            if (!list.isEmpty()) {
                this.target = (EntityLivingBase)list.get(0);
                return new Pair(null, null);
            }
        }

        this.target = null;
        return new Pair(null, null);
    }

    private void onClick4(int index, EventClick event) {
        int index2 = index;
        if (this.name2.equals("Multi")) {
            if (index <= 0) {
                this.timer3.reset();
                this.updateKillAura10();
                return;
            }

            minecraft.thePlayer.respawnPlayer();
            Object[] itemArray = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(object -> this.checkEntity2((Entity)object))
            .sorted(Comparator.comparingDouble(object2 -> this.getDistanceToTarget5((Entity)object2)))
            .toArray();
            int index3 = 0;
            int length = itemArray.length;
            int index4 = 0;

            while (index4 < length) {
                Object object3 = itemArray[index4];
                double offset = (double)index3;
                if (offset > this.settingMaxTargets.getValue()) {
                    break;
                }

                if (!(object3 instanceof EntityLivingBase)) {
                    index4++;
                } else {
                    Entity entity = (Entity)object3;
                    if (AttackTargetHitCheck.checkEntity(entity)) {
                        index4++;
                    } else if (!ClientGlobals.moduleManager.antiBot.checkEntity2(entity)) {
                        if ((double)minecraft.thePlayer.getDistanceToEntity(entity) <= this.reachDistance) {
                            minecraft.playerController.attackEntity(minecraft.thePlayer, entity);
                            index3++;
                            index4++;
                        } else {
                            index4++;
                        }
                    } else {
                        index4++;
                    }
                }
            }
        } else {
            boolean enabled;
            if (this.settingMissClick.isEnabled() && this.canAttack7()) {
                enabled = true;
            } else {
                enabled = false;
            }

            if (this.settingCoolDown.isEnabled()) {
                if (index > 0) {
                    Minecraft mc3 = minecraft;

                    while (true) {
                        if (mc3.thePlayer.getHeldItem() == null) {
                            this.onClick2(event, enabled);
                            index2--;
                        } else {
                            boolean completed = false;
                            int index5 = 0;
                            if (!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemSword)) {
                                if (!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemTool)) {
                                    this.onClick2(event, enabled);
                                    index2--;
                                    completed = true;
                                }
                                if (!completed) {
                                    index5 = minecraft.thePlayer.getHeldItem().getItem().indexOffset;
                                }
                            } else {
                                index5 = minecraft.thePlayer.getHeldItem().getItem().indexOffset;
                            }
                            if (!completed) {
                                if (minecraft.thePlayer.indexOffset12 >= index5) {
                                    this.onClick2(event, enabled);
                                }
                                index2--;
                            }

                        }

                        if (index2 <= 0) {
                            break;
                        }

                        mc3 = minecraft;
                    }
                }
            } else {
                if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                    if (this.settingDoubleClickFirstHit.isEnabled()
                    && (this.activeState3 || this.timer8.hasReached(1500L))
                    && !this.settingAdvanced.getSelected().equals("Smart")) {
                        this.onClick2(event, enabled);
                        this.activeState3 = false;
                    }

                    this.timer8.reset();
                }

                while (index2 > 0) {
                    this.onClick2(event, enabled);
                    index2--;
                }
            }
        }

        this.timer3.reset();
        this.updateKillAura10();
    }

    private static void updateKillAura8() {
        minecraft.playerController.sendUseItem(minecraft.thePlayer, minecraft.theWorld, minecraft.thePlayer.inventoryPlayer.getCurrentItem());
    }

    private void updateKillAura2() {
        String selected = this.settingClickPattern.getSelected();
        ArrayList itemList32 = this.itemList3;
        this.clickPatternProfile = ClickPatternManager.getClickPatternProfile(selected, itemList32);
    }

    private boolean isOrthogonalScaleVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private void updateKillAura9() {
        this.settingDelayClicksAfterRespawn.recalculate();
        this.settingDelayRotationsAfterRespawn.recalculate();
    }

    private void processByte() {
        for (int i = 0; i < this.animationTimerArray.length; i++) {
            this.animationTimerArray[i] = new AnimationTimer();
        }

        int index = 0;

        for (int i2 = 0; i2 < this.smoothDamperArray.length; i2 = ++index) {
            this.smoothDamperArray[index] = new SmoothDamper(20, (double)index * 1.2 % 20.0);
        }

        for (int i3 = 0; i3 < 11; i3++) {
            SettingGroup settingGroup = new SettingGroup(this, "HurtTime " + i3, this.settingCPSSettings)
            .getSettingGroup(() -> this.isHurtTimeVisible());
            this.rangeSettingArray[i3 * 4] = new RangeSetting(this, "SmartCPSHurt" + i3, 17.0, 19.0, 1.0, 40.0, 0, settingGroup)
            .getRangeSetting("CPS")
            .getRangeSetting(16772371, () -> this.isSmartCPSHurtVisible());
            this.rangeSettingArray[i3 * 4 + 1] = new RangeSetting(
            this, "Hurt" + i3 + "SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, settingGroup
            )
            .getRangeSetting("CPSRecalculateTickDelay")
            .getRangeSetting2()
            .getRangeSetting(16772371, () -> this.isHurtVisible());
            this.rangeSettingArray[i3 * 4 + 2] = new RangeSetting(this, "SmartDCPSHurt" + i3, 4.0, 5.0, 0.0, 20.0, 0, settingGroup)
            .getRangeSetting("DCPS")
            .getRangeSetting(16772371, () -> this.isSmartDCPSHurtVisible());
            this.rangeSettingArray[i3 * 4 + 3] = new RangeSetting(
            this, "DCPSRecalculateTickDelayHurt" + i3, 3.0, 8.0, 1.0, 40.0, 0, settingGroup
            )
            .getRangeSetting("DCPSRecalculateTickDelay")
            .getRangeSetting2()
            .getRangeSetting(16772371, () -> this.isDCPSRecalculateTickDelayHurtVisible());
        }
    }

    private boolean isAttentionVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private void updateKillAura10() {
        String selected = this.settingAdvanced.getSelected();
switch (selected) {
            case "Advanced":
                long timestampMillis232 = this.timestampMillis23;
                boolean reached = this.timer4.hasReached(timestampMillis232);
                if (reached) {
                    int cPS = (int)this.settingCPS.getDouble3();
                    int cPS2 = (int)this.settingCPS.getDouble6();
                    this.timestampMillis1 = (long)SettingContainer.settingProvider.getInt3(cPS, cPS2);

                    while (this.timestampMillis1 == this.timestampMillis21) {
                        double double3 = this.settingCPS.getDouble3();
                        if (double3 == this.settingCPS.getDouble6()) {
                            break;
                        }

                        double offset = this.settingCPS.getDouble3() + 1.0;
                        if (offset == this.settingCPS.getDouble6()) {
                            break;
                        }

                        int cPS3 = (int)this.settingCPS.getDouble3();
                        int cPS4 = (int)this.settingCPS.getDouble6();
                        this.timestampMillis1 = (long)SettingContainer.settingProvider.getInt3(cPS3, cPS4);
                    }

                    this.timestampMillis21 = this.timestampMillis1;
                    this.timer4.reset();
                    int minDelay = (int)this.settingMinDelay2.getDouble3();
                    int minDelay2 = (int)this.settingMinDelay2.getDouble6();
                    this.timestampMillis23 = (long)SettingContainer.settingProvider.getInt3(minDelay, minDelay2);
                }
                if (this.settingSpikes.isEnabled()) {
                    int spikeDelay = (int)this.settingSpikeDelay.getValue();
                    long timestampMillis182 = this.timestampMillis18;
                    if (this.timer6.hasReached(timestampMillis182)) {
                        double timestampMillis12 = (double)this.timestampMillis1;
                        this.timestampMillis21 = Math.round(timestampMillis12 * this.settingSpikeMulti.getValue());
                        TimeHelper timer62 = this.timer6;
                        long timestampMillis183 = this.timestampMillis18;
                        int spikeDuration = (int)this.settingSpikeDuration.getDouble3();
                        int spikeDuration2 = (int)this.settingSpikeDuration.getDouble6();
                        long time2 = timestampMillis183 + (long)SettingContainer.settingProvider.getInt3(spikeDuration, spikeDuration2);
                        if (timer62.hasReached(time2)) {
                            int index = Math.max(0, spikeDelay - 100);
                            int index2 = spikeDelay + 100;
                            this.timestampMillis18 = (long)SettingContainer.settingProvider.getInt3(index, index2);
                            this.timer6.reset();
                        }
                    }
                }
                if (!this.settingDrops.isEnabled()) {
                    return;
                } else {
                    int dropDelay = (int)this.settingDropDelay.getValue();
                    long timestampMillis202 = this.timestampMillis20;
                    if (this.timer5.hasReached(timestampMillis202)) {
                        double timestampMillis13 = (double)this.timestampMillis1;
                        this.timestampMillis21 = Math.round(timestampMillis13 * this.settingDropMulti.getValue());
                        TimeHelper timer52 = this.timer5;
                        long timestampMillis203 = this.timestampMillis20;
                        int dropDuration = (int)this.settingDropDuration.getDouble3();
                        int dropDuration2 = (int)this.settingDropDuration.getDouble6();
                        long time3 = timestampMillis203 + (long)SettingContainer.settingProvider.getInt3(dropDuration, dropDuration2);
                        if (timer52.hasReached(time3)) {
                            int index3 = Math.max(0, dropDelay - 100);
                            int index4 = dropDelay + 100;
                            this.timestampMillis20 = (long)SettingContainer.settingProvider.getInt3(index3, index4);
                            this.timer5.reset();
                        }
                    }

                    return;
                }
            case "Basic":
                if (this.settingMinDelay.getDouble3() == 0.0 && this.settingMinDelay.getDouble6() == 0.0) {
                    this.timestampMillis21 = 0L;
                    return;
                } else {
                    double double32 = this.settingMinDelay.getDouble3();
                    if (Math.abs(double32 - this.settingMinDelay.getDouble6()) > 0.0) {
                        int minDelay3 = (int)this.settingMinDelay.getDouble3();
                        int minDelay4 = (int)this.settingMinDelay.getDouble6();
                        this.timestampMillis21 = (long)SettingContainer.settingProvider.getInt3(minDelay3, minDelay4);
                        return;
                    }

                    this.timestampMillis21 = (long)this.settingMinDelay.getDouble3();
                    return;
                }
        }
    }

    private void updateKillAura4() {
        for (int i = 0; i < this.animationTimerArray.length; i++) {
            this.animationTimerArray[i].processAction();
        }

        this.animationTimer9.processAction();
        this.animationTimer3.processAction();
        this.animationTimer4.processAction();
        this.animationTimer10.processAction();
        this.animationTimer.processAction();
        this.animationTimer2.processAction();
        this.animationTimer5.processAction();
        this.animationTimer6.processAction();
        this.animationTimer7.processAction();
        this.animationTimer8.processAction();
    }

    private void onClick5(EventClick event) {
        if (this.canAttack9()) {
            if (minecraft.thePlayer == null) {
                return;
            }

            if (minecraft.thePlayer.inventoryPlayer == null) {
                return;
            }

            if (minecraft.thePlayer.inventoryPlayer.getCurrentItem() == null) {
                return;
            }

            if (minecraft.thePlayer.inventoryPlayer.getCurrentItem().getItem() == null) {
                return;
            }

            if (!(minecraft.thePlayer.inventoryPlayer.getCurrentItem().getItem() instanceof ItemSword)) {
                return;
            }

            if (this.settingInteractAutoBlock.isEnabled()) {
                if (event == null) {
                    minecraft.rightClickMouse();
                    return;
                }

                event.setState11(true);
                return;
            }

            if (event != null) {
                event.setState11(true);
                Runnable runnable = () -> updateKillAura8();
                event.setRunnable2(runnable);
                return;
            }

            minecraft.playerController.sendUseItem(minecraft.thePlayer, minecraft.theWorld, minecraft.thePlayer.inventoryPlayer.getCurrentItem());
        }
    }

    private void processAction2() {
        this.onClick5((EventClick)null);
    }

    private int getInt2(int index) {
        if (this.canAttack4()) {
            if (ClientGlobals.moduleManager.debug.booleanSetting12 == null) {
                return 0;
            } else {
                if (ClientGlobals.moduleManager.debug.booleanSetting12.isEnabled() && ClientGlobals.moduleManager.debug.isToggled()) {
                    RayTraceUtil.applyString("ReduceHits");
                }

                return 0;
            }
        } else {
            return index;
        }
    }

    private EntityLivingBase getEntityLivingBase(EntityLivingBase livingEntity) {
        int index = minecraft.thePlayer.inventoryPlayer.currentItem + 1;
        if (index == 1 && !KillAuraWeaponSlot.SLOT1.isSelected()) {
            return null;
        } else if (index == 2 && !KillAuraWeaponSlot.SLOT2.isSelected()) {
            return null;
        } else if (index == 3 && !KillAuraWeaponSlot.SLOT3.isSelected()) {
            return null;
        } else if (index == 4 && !KillAuraWeaponSlot.SLOT4.isSelected()) {
            return null;
        } else if (index == 5 && !KillAuraWeaponSlot.SLOT5.isSelected()) {
            return null;
        } else if (index == 6 && !KillAuraWeaponSlot.SLOT6.isSelected()) {
            return null;
        } else if (index == 7 && !KillAuraWeaponSlot.SLOT7.isSelected()) {
            return null;
        } else if (index == 8 && !KillAuraWeaponSlot.SLOT8.isSelected()) {
            return null;
        } else if (index == 9 && !KillAuraWeaponSlot.SLOT9.isSelected()) {
            return null;
        } else {
            return !this.auxiliarySettingAdapter.canExecute() ? null : livingEntity;
        }
    }

    private void processFloat(float value) {
        Entity renderViewEntity = minecraft.getRenderViewEntity();
        if (renderViewEntity != null && minecraft.theWorld != null) {
            minecraft.pointedEntity = (Entity)null;
            double offset = Math.max((double)minecraft.playerController.getBlockReachDistance(), this.reachDistance);
            MovingObjectPosition movingObjectPosition2 = renderViewEntity.getMovingObjectPosition2(offset, value);
            minecraft.objectMouseOver = movingObjectPosition2;
            double offset2 = offset;
            Vec3 vec2 = renderViewEntity.getVec35(value);
            boolean enabled = false;
            if (!(offset <= 3.0)) {
                enabled = true;
            }

            if (minecraft.objectMouseOver != null) {
                offset2 = minecraft.objectMouseOver.hitVec.getCoordinate4(vec2);
                if (this.settingThroughWalls.isEnabled() && this.settingAimThroughWalls.isEnabled()) {
                    offset2 = Math.max(offset2, this.settingThroughWallsRange.getValue());
                }
            }

            if (ClientGlobals.moduleManager.fixes.canExecute7()) {
                offset = 6.0;
            }

            double offset3 = Math.max(offset, this.reachDistance);
            Vec3 vec3 = renderViewEntity.getVec36(value);
            Vec3 vec4 = vec2.getVec312(vec3.xCoord * offset3, vec3.yCoord * offset3, vec3.zCoord * offset3);
            minecraft.entityRenderer.entity = null;
            Vec3 vec = null;
            float value2 = 1.0F;
            AxisAlignedBB axisAlignedBB = renderViewEntity.getBoundingBox2()
            .contract(vec3.xCoord * offset3, vec3.yCoord * offset3, vec3.zCoord * offset3)
            .offset((double)value2, (double)value2, (double)value2);
            List entitiesInAABBexcluding = minecraft.theWorld.getEntitiesInAABBexcluding(renderViewEntity, axisAlignedBB, Predicates.and(LivingEntityFilter.predicate4, new EntitySelectPredicates(minecraft.entityRenderer)));
            double offset4 = offset2;
            if (this.name2.equals("Packet")) {
                float height = this.target.getHeight();
                AxisAlignedBB boundingBox2 = this.target.getBoundingBox2();
                AxisAlignedBB boundingBox;
                if (ClientGlobals.moduleManager.hitBox.isToggled()) {
                    EntityLivingBase target2 = this.target;
                    boundingBox = ClientGlobals.moduleManager.hitBox.getAxisAlignedBB(target2, height);
                } else {
                    boundingBox = boundingBox2.offset((double)height, (double)height, (double)height);
                }

                MovingObjectPosition movingObjectPosition3 = boundingBox.calculateIntercept(vec2, vec4);
                if (boundingBox.isVecInside(vec2)) {
                    if (offset2 >= 0.0) {
                        minecraft.entityRenderer.entity = this.target;
                        vec = movingObjectPosition3 == null ? vec2 : movingObjectPosition3.hitVec;
                        offset4 = 0.0;
                    }
                } else if (movingObjectPosition3 != null) {
                    double coordinate4 = vec2.getCoordinate4(movingObjectPosition3.hitVec);
                    if (coordinate4 < offset2 || offset2 == 0.0) {
                        if (this.target != renderViewEntity.ridingEntity) {
                            minecraft.entityRenderer.entity = this.target;
                            vec = movingObjectPosition3.hitVec;
                            offset4 = coordinate4;
                        } else if (offset2 == 0.0) {
                            minecraft.entityRenderer.entity = this.target;
                            vec = movingObjectPosition3.hitVec;
                        }
                    }
                }
            } else {
                for (int i = 0; i < entitiesInAABBexcluding.size(); i++) {
                    Entity entity = (Entity)entitiesInAABBexcluding.get(i);
                    float height2 = entity.getHeight();
                    AxisAlignedBB boundingBox22 = entity.getBoundingBox2();
                    AxisAlignedBB boundingBox3;
                    if (ClientGlobals.moduleManager.hitBox.isToggled()) {
                        boundingBox3 = ClientGlobals.moduleManager.hitBox.getAxisAlignedBB(entity, height2);
                    } else {
                        boundingBox3 = boundingBox22.offset((double)height2, (double)height2, (double)height2);
                    }

                    MovingObjectPosition movingObjectPosition4 = boundingBox3.calculateIntercept(vec2, vec4);
                    if (boundingBox3.isVecInside(vec2)) {
                        if (offset4 >= 0.0) {
                            minecraft.entityRenderer.entity = entity;
                            vec = movingObjectPosition4 == null ? vec2 : movingObjectPosition4.hitVec;
                            offset4 = 0.0;
                        }
                    } else if (movingObjectPosition4 != null) {
                        double coordinate42 = vec2.getCoordinate4(movingObjectPosition4.hitVec);
                        if (coordinate42 < offset4 || offset4 == 0.0) {
                            if (entity == renderViewEntity.ridingEntity) {
                                if (offset4 == 0.0) {
                                    minecraft.entityRenderer.entity = entity;
                                    vec = movingObjectPosition4.hitVec;
                                }
                            } else {
                                minecraft.entityRenderer.entity = entity;
                                vec = movingObjectPosition4.hitVec;
                                offset4 = coordinate42;
                            }
                        }
                    }
                }
            }

            if (minecraft.entityRenderer.entity != null && enabled && vec2.getCoordinate4(vec) > this.reachDistance) {
                minecraft.entityRenderer.entity = null;
                MovingObjectPosition movingObjectPosition5 = new MovingObjectPosition(MovingObjectType.MISS, vec, (EnumFacing)null, new BlockPos(vec));
                minecraft.objectMouseOver = movingObjectPosition5;
            }

            if (minecraft.entityRenderer.entity == null) {
                return;
            }

            if (offset4 >= offset2 && minecraft.objectMouseOver != null) {
                return;
            }

            MovingObjectPosition movingObjectPosition6 = new MovingObjectPosition(minecraft.entityRenderer.entity, vec);
            minecraft.objectMouseOver = movingObjectPosition6;
            if (!(minecraft.entityRenderer.entity instanceof EntityLivingBase) && !(minecraft.entityRenderer.entity instanceof EntityItemframe)) {
                return;
            }

            Entity entity2 = minecraft.entityRenderer.entity;
            minecraft.pointedEntity = entity2;
        }
    }

    private void setActive4(boolean enabled) {
        boolean enabled3;
        if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
            if (AttackTargetHitCheck.checkEntity(minecraft.objectMouseOver.entityHit)) {
                return;
            }

            Entity entityHit = minecraft.objectMouseOver.entityHit;
            if (ClientGlobals.moduleManager.antiBot.checkEntity2(entityHit)) {
                return;
            }

            enabled3 = enabled;
        } else {
            enabled3 = enabled;
        }

        Minecraft mc = null;
        boolean completed4 = false;
        boolean completed3 = false;
        boolean enabled4 = false;
        boolean completed2 = false;
        boolean completed = false;
        boolean enabled5 = false;
        if (!enabled3 && minecraft.objectMouseOver != null) {
            boolean enabled6 = false;
            if (ClientGlobals.moduleManager.criticals.isToggled()) {
                enabled6 = true;
            } else {
                if (!(minecraft.objectMouseOver.entityHit instanceof EntityLivingBase)
                || ((Float.compare(minecraft.thePlayer.fallDistance, 0.0F) & 1) != 0 ? 1 : 0) <= 0) {
                    enabled6 = false;
                } else if (!minecraft.thePlayer.onGround
                && !minecraft.thePlayer.canExecute9()
                && !minecraft.thePlayer.isInWater()
                && !minecraft.thePlayer.checkPotion(Potion.potion16)
                && minecraft.thePlayer.ridingEntity == null) {
                    enabled6 = true;
                } else {
                    enabled6 = false;
                }
            }
            if (!this.visible2 && enabled6) {
                this.visible2 = true;
            }
            this.activeState = true;
            EventListener.getAbstractEventAdapter();
            if (!ClientGlobals.moduleManager.fixes.isToggled()) {
                completed = true;
            }
            if (!completed) {
                enabled5 = ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("OnlyAtTargetLite");
            }
        } else {
            if (!ClientGlobals.moduleManager.fixes.isToggled()) {
                int leftClickCounter = minecraft.leftClickCounter;
                if (leftClickCounter <= 0 || !ClientGlobals.moduleManager.debug.settingLostHitDebug.isEnabled()) {
                    if (minecraft.leftClickCounter > 0) {
                        return;
                    }
                    if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_9) && !this.settingNoSwing.isEnabled()) {
                        minecraft.thePlayer.respawnPlayer();
                    }
                    mc = minecraft;
                    completed4 = true;
                }
                if (!completed4) {
                    enabled4 = ClientGlobals.moduleManager.debug.isToggled();
                    completed2 = true;
                }
            }
            if (!completed4) {
                if (!completed2) {
                    enabled5 = ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("OnlyAtTargetLite");
                }
            }
        }
        if (!completed4) {
            if (!completed2) {
                if (!completed) {
                    if ((
                    enabled5 && minecraft.leftClickCounter < 9
                    || ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("OnlyAtTarget")
                    )
                    && minecraft.objectMouseOver != null
                    && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                        minecraft.leftClickCounter = 0;
                    }
                }
                if (minecraft.leftClickCounter <= 0 || !ClientGlobals.moduleManager.debug.settingLostHitDebug.isEnabled()) {
                    completed3 = true;
                }
                if (!completed3) {
                    enabled4 = ClientGlobals.moduleManager.debug.isToggled();
                }
            }
            if (!completed3) {
                if (enabled4 && minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                    RayTraceUtil.applyString("Lost Hit");
                }
            }
            int leftClickCounter2 = minecraft.leftClickCounter;
            if (leftClickCounter2 > 0) {
                return;
            }
            if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_9) && !this.settingNoSwing.isEnabled()) {
                minecraft.thePlayer.respawnPlayer();
            }
            mc = minecraft;
        }


        if (mc.objectMouseOver != null) {
            if (enabled) {
                if (ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_9) && !this.settingNoSwing.isEnabled()) {
                    minecraft.thePlayer.respawnPlayer();
                }
            } else {
                boolean enabled7;
                if ((minecraft.objectMouseOver.typeOfHit) == MovingObjectType.ENTITY) {
                    minecraft.playerController.attackEntity(minecraft.thePlayer, minecraft.objectMouseOver.entityHit);
                    if (this.settingSimple.getSelected().equals("Grim") && !this.activeState14) {
                        this.activeState32 = true;
                    }

                    if (this.settingFixBeta.isEnabled() && this.tickDelay13 > 19 && !this.settingNoSwing.isEnabled()) {
                        minecraft.thePlayer.respawnPlayer();
                        this.tickDelay13 = 0;
                    }

                    this.tickDelay13++;
                    enabled7 = ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_9);
                } else if ((minecraft.objectMouseOver.typeOfHit) == MovingObjectType.BLOCK) {
                    BlockPos blockPos = minecraft.objectMouseOver.getBlockPos();
                    if (minecraft.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
                        minecraft.playerController.onPlayerDamageBlock(blockPos, minecraft.objectMouseOver.sideHit);
                    } else {
                        if (minecraft.playerController.canExecute()) {
                            minecraft.leftClickCounter = 10;
                        }

                        minecraft.thePlayer.updateState6();
                        if (ClientGlobals.moduleManager.fixes.isToggled()
                        && ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("Default")) {
                            minecraft.leftClickCounter = 0;
                        }
                    }

                    enabled7 = ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_9);
                } else {
                    if (minecraft.playerController.canExecute()) {
                        minecraft.leftClickCounter = 10;
                    }

                    minecraft.thePlayer.updateState6();
                    if (ClientGlobals.moduleManager.fixes.isToggled()
                    && ClientGlobals.moduleManager.fixes.settingHitDelayFixMode.getSelected().equals("Default")) {
                        minecraft.leftClickCounter = 0;
                    }

                    enabled7 = ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_9);
                }

                if (enabled7) {
                    if (!this.settingNoSwing.isEnabled()) {
                        minecraft.thePlayer.respawnPlayer();
                    }
                }
            }
        } else if (minecraft.playerController.canExecute()) {
            minecraft.leftClickCounter = 10;
        }
    }

    private boolean isMaxAimpointAnglediffVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private static double getDistanceToTarget3(Entity entity) {
        return (double)((EntityLivingBase)entity).getFloat2();
    }

    private boolean checkEntity2(Entity entity) {
        double coordinate = this.doubleEntry.getCoordinate();
        return this.checkEntity(entity, coordinate);
    }

    public double getDistanceToTarget4(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000.0;
        } else if (this.targetEntity2 != null && entity.getEntityId() == this.targetEntity2.getEntityId()) {
            return -1.0;
        } else {
            double posX = entity.posX;
            double posY = entity.posY;
            double posZ = entity.posZ;
            float scaleFactor1 = Client.getClient().getViaProtocolHelper().scaleFactor1;
            Client client = Client.getClient();
            return (double)Math.abs(SimulatedPlayer.getFloatArray(posX, posY, posZ, scaleFactor1, client.getViaProtocolHelper().scaleFactor2)[0]);
        }
    }

    private boolean checkRangeSetting(RangeSetting rangeSetting) {
        if (minecraft.thePlayer.ticksExisted - 5 >= rangeSetting.getIndexOffset2() && this.tickDelay15 >= rangeSetting.getIndexOffset2()) {
            return false;
        } else {
            this.tickDelay15++;
            return true;
        }
    }

    private int getInt3(int index) {
        return this.settingMode.getSelected().equals("Recommended")
        ? this.settingDCPS.getIndexOffset2()
        : this.rangeSettingArray[index].getIndexOffset2();
    }

    public float[] getFloatArray() {
        return !this.settingNoRotation.isEnabled() ? ClientGlobals.rotationManager.getRotation3().getFloatArray() : this.rotation.getFloatArray();
    }

    private void processAction(int index) {
        this.onClick4(index, (EventClick)null);
    }

    private boolean checkCondition(double offset, int index2) {
        if (!this.settingSmartPreHit.isEnabled()) {
            return true;
        } else if (this.tickDelay9 <= this.settingEndurance.getIndexOffset2() && !this.enabled2) {
            if (minecraft.indexOffset == 1) {
                this.settingLoseDelay.recalculate();
                this.settingPrediction.recalculate();
            }

            if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                return true;
            } else if (minecraft.rightClickDelayTimer > this.settingLoseDelay.getIndexOffset2()) {
                if (this.tickDelay9 > this.settingEndurance.getIndexOffset2() / 2) {
                    return false;
                } else {
                    MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition2(
                    1.0F, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch, 10.0, 6.0, 1.0
                    );
                    if (movingObjectPosition2 != null && movingObjectPosition2.entityHit instanceof EntityLivingBase) {
                        EntityLivingBase livingEntity = (EntityLivingBase)movingObjectPosition2.entityHit;
                        int ticksExisted = minecraft.thePlayer.ticksExisted;

                        for (int i = 1; i < index2; i++) {
                            Vec3 trajectoryPoint = RuntimeBridge.getTrajectoryPoint(
                            RuntimeBridge.predictTargetPoints(minecraft.thePlayer, (EntityLivingBase)movingObjectPosition2.entityHit, ticksExisted, ticksExisted + i), 99999
                            );
                            AxisAlignedBB axisAlignedBB = SimulatedPlayer.getAxisAlignedBB(livingEntity, trajectoryPoint);
                            double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(i - 1, minecraft.thePlayer);
                            Vec3 vec2 = new Vec3(coordOffsetArray2[0], coordOffsetArray2[1] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray2[2]);
                            Vec3 vec = SimulatedPlayer.processAction(vec2, axisAlignedBB);
                            if (vec2.getCoordinate4(vec) < offset) {
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
            if (!this.enabled2) {
                this.settingEndurance.recalculate();
            }

            this.enabled2 = true;
            return false;
        }
    }

    private int getInt4() {
        if (this.target.hurtTime == 10) {
            this.visible2 = false;
        }

        boolean completed11 = false;
        boolean enabled3 = false;
        boolean completed10 = false;
        boolean completed9 = false;
        MovingObjectPosition hitResult = null;
        boolean completed8 = false;
        AnimationTimer animationTimer11 = null;
        boolean completed7 = false;
        int state = (byte)0;
        boolean completed6 = false;
        boolean completed5 = false;
        long time2 = 0L;
        long time3 = 0L;
        KillAura killAura = null;
        boolean completed4 = false;
        boolean completed3 = false;
        boolean completed2 = false;
        byte state2 = (byte)0;
        boolean completed = false;
        if (this.settingPerfectHit.isEnabled()) {
            if (minecraft.objectMouseOver != null
            && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
            && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase
            && !this.canAttack4()) {
                EntityLivingBase livingEntity = (EntityLivingBase)minecraft.objectMouseOver.entityHit;
                if (livingEntity.hurtTime == 0 || livingEntity.hurtTime == 1) {
                    return 1;
                }
            }

            if (this.settingIntave13.isEnabled()
            && ClientGlobals.moduleManager.timerRange.isToggled()
            && ClientGlobals.moduleManager.timerRange.tickDelay3 >= 4) {
                return 0;
            }
        } else {
            if (!this.settingIntave13.isEnabled()) {
                String selected = this.settingAdvanced.getSelected();
                byte state3 = -1;
                switch (selected.hashCode()) {
                    case -1818419758:
                        {
                            if (!selected.equals("Simple")) {
                                return 0;
                            }
                            state2 = 3;
                            { completed = true; break; }
                        }
                    case -734589744:
                        {
                            if (!selected.equals("Recorded")) {
                                return 0;
                            }
                            state2 = 2;
                            { completed = true; break; }
                        }
                    case -654193598:
                        {
                            if (!selected.equals("Advanced")) {
                                return 0;
                            }
                            { completed5 = true; break; }
                        }
                    case 63955982:
                        {
                            if (!selected.equals("Basic")) {
                                return 0;
                            }
                            { completed11 = true; break; }
                        }
                    case 79996329:
                        {
                            {
                                {
                                    {
                                        {
                                            {
                                                {
                                                    {
                                                        {
                                                            if (selected.equals("Smart")) {
                                                                state3 = 5;
                                                            }
                                                            switch (state3) {
                                                                case 0:
                                                                    {
                                                                        { completed11 = true; break; }
                                                                    }
                                                                case 1:
                                                                    {
                                                                        { completed5 = true; break; }
                                                                    }
                                                                case 2:
                                                                    {
                                                                        {
                                                                            {
                                                                                {
                                                                                    if (this.clickPatternProfile == null) {
                                                                                        return 0;
                                                                                    }
                                                                                    state = 0;
                                                                                    if (this.settingTimeMillis.getSelected().equals("Ticks")) {
                                                                                        if (this.timestampMillis24 < this.timestampMillis26) {
                                                                                            this.activeState19 = this.timestampMillis > this.timestampMillis24;
                                                                                            this.timestampMillis24 += 50L;
                                                                                            { completed6 = true; break; }
                                                                                        }
                                                                                        if (!completed6) {
                                                                                            state += 1;
                                                                                            Pair pair = this.getPair();
                                                                                            int second = (Integer)pair.getSecond();
                                                                                            this.timestampMillis = this.timestampMillis26;
                                                                                            this.timestampMillis = this.timestampMillis
                                                                                            + (long)((Integer)pair.getFirst()).intValue();
                                                                                            this.timestampMillis26 += (long)second;
                                                                                            { completed3 = true; break; }
                                                                                        }
                                                                                    }
                                                                                    if (!completed6) {
                                                                                        if (!completed3) {
                                                                                            time2 = System.currentTimeMillis();
                                                                                            if (time2 < this.timestampMillis26) {
                                                                                                killAura = this;
                                                                                                time3 = this.timestampMillis;
                                                                                                { completed4 = true; break; }
                                                                                            }
                                                                                            if (!completed4) {
                                                                                                state += 1;
                                                                                                Pair pair2 = this.getPair();
                                                                                                int second2 = (Integer)pair2.getSecond();
                                                                                                this.timestampMillis = this.timestampMillis26;
                                                                                                this.timestampMillis = this.timestampMillis
                                                                                                + (long)((Integer)pair2.getFirst()).intValue();
                                                                                                this.timestampMillis26 += (long)second2;
                                                                                                { completed2 = true; break; }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                case 3:
                                                                    {
                                                                        animationTimer11 = this.animationTimer9;
                                                                        { completed7 = true; break; }
                                                                    }
                                                                case 4:
                                                                    {
                                                                        { completed9 = true; break; }
                                                                    }
                                                                case 5:
                                                                    {
                                                                        hitResult = minecraft.objectMouseOver;
                                                                        { completed8 = true; break; }
                                                                    }
                                                                default:
                                                                    return 0;
                                                            }
                                                            if (completed11) break;
                                                            if (!completed11) {
                                                                if (completed9) break;
                                                                if (!completed9) {
                                                                    if (completed8) break;
                                                                    if (!completed8) {
                                                                        if (completed7) break;
                                                                        if (!completed7) {
                                                                            if (completed6) break;
                                                                            if (!completed6) {
                                                                                if (completed5) break;
                                                                                if (!completed5) {
                                                                                    if (completed4) break;
                                                                                    if (!completed4) {
                                                                                        if (completed3) break;
                                                                                        if (!completed3) {
                                                                                            if (completed2) break;
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
                                    }
                                }
                            }
                        }
                    case 2027787361:
                        {
                            if (!selected.equals("Deviation")) {
                                return 0;
                            }
                            { completed9 = true; break; }
                        }
                    default:
                        return 0;
                }
            }
            if (!completed11) {
                if (!completed9) {
                    if (!completed8) {
                        if (!completed7) {
                            if (!completed6) {
                                if (!completed5) {
                                    if (!completed4) {
                                        if (!completed3) {
                                            if (!completed2) {
                                                if (!completed) {
                                                    if (ClientGlobals.moduleManager.timerRange.isToggled()
                                                    && ClientGlobals.moduleManager.timerRange.tickDelay3 >= 4) {
                                                        return 0;
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
        if (!completed11) {
            if (!completed9) {
                if (!completed8) {
                    if (!completed7) {
                        if (!completed6) {
                            if (!completed5) {
                                if (!completed4) {
                                    if (!completed3) {
                                        if (!completed2) {
String selected2 = this.settingAdvanced.getSelected();
switch (selected2) {
            case "Basic":
                long timestampMillis212 = this.timestampMillis21;
                if (!this.timer2.hasReached(timestampMillis212) && !this.timer3.hasReached(1000L)
                                                    )
                                                    {
                                                        return 0;
                                                    }
                return 1;
            case "Advanced":
                {
                                                        enabled3 = this.settingReduceInAir.isEnabled();
                                                        { completed10 = true; break; }
                                                    }
            case "Recorded":
                {
                                                        if (this.clickPatternProfile == null) {
                                                            return 0;
                                                        }
                                                        state = 0;
                                                        if (this.settingTimeMillis.getSelected().equals("Ticks")) {
                                                            { completed3 = true; break; }
                                                        }
                                                        if (!completed3) {
                                                            time2 = System.currentTimeMillis();
                                                            break;
                                                        }
                                                    }
            case "Simple":
                {
                                                        animationTimer11 = this.animationTimer9;
                                                        { completed7 = true; break; }
                                                    }
            case "Deviation":
                float minCPS = (float)this.settingMinCPS.getDouble3();
                float minCPS2 = (float)this.settingMinCPS.getDouble6();
                float value = (minCPS + minCPS2) / 2.0F;
                float value2 = (minCPS2 - minCPS) / 6.0F;
                long time4 = System.currentTimeMillis();
                byte state4;
                for (state4 = 0; this.timestampMillis26 < time4; state4 += 1) {
                                                        double offset = (double)(
                                                        (int)(1000.0F / (float)((double)value + (double)value2 * new SecureRandom().nextGaussian()))
                                                        );
                                                        if (minecraft.objectMouseOver == null
                                                        || minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                                                            offset /= this.settingAirMultiplier2.getValue();
                                                        }

                                                        this.timestampMillis26 = this.timestampMillis26 + Math.min((long)offset, 1000L);
                                                    }
                if (!this.settingDoubleClick.isEnabled() && state4 > 1) {
                                                        state4 = 1;
                                                    }
                return this.getInt2(state4);
            case "Smart":
                {
                                                        hitResult = minecraft.objectMouseOver;
                                                        { completed8 = true; break; }
                                                    }
            default:
                return 0;
        }
                                        }
                                        if (!completed10) {
                                            if (!completed8) {
                                                if (!completed7) {
                                                    if (!completed3) {
                                                        while (time2 >= this.timestampMillis26) {
                                                            state += 1;
                                                            Pair pair3 = this.getPair();
                                                            int second3 = (Integer)pair3.getSecond();
                                                            this.timestampMillis = this.timestampMillis26;
                                                            this.timestampMillis = this.timestampMillis + (long)((Integer)pair3.getFirst()).intValue();
                                                            this.timestampMillis26 += (long)second3;
                                                        }
                                                        killAura = this;
                                                        time3 = this.timestampMillis;
                                                        completed4 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!completed10) {
                                        if (!completed8) {
                                            if (!completed7) {
                                                if (!completed4) {
                                                    while (this.timestampMillis24 >= this.timestampMillis26) {
                                                        state += 1;
                                                        Pair pair4 = this.getPair();
                                                        int second4 = (Integer)pair4.getSecond();
                                                        this.timestampMillis = this.timestampMillis26;
                                                        this.timestampMillis = this.timestampMillis + (long)((Integer)pair4.getFirst()).intValue();
                                                        this.timestampMillis26 += (long)second4;
                                                    }
                                                    this.activeState19 = this.timestampMillis > this.timestampMillis24;
                                                    this.timestampMillis24 += 50L;
                                                    completed6 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!completed10) {
                                    if (!completed8) {
                                        if (!completed7) {
                                            if (!completed6) {
                                                killAura.activeState19 = time3 > time2;
                                                completed6 = true;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!completed10) {
                                if (!completed8) {
                                    if (!completed7) {
                                        if (!completed6) {
                                            enabled3 = this.settingReduceInAir.isEnabled();
                                            completed10 = true;
                                        }
                                    }
                                }
                            }
                        }
                        if (!completed10) {
                            if (!completed8) {
                                if (!completed7) {
                                    this.clickPatternProfile.updateState();
                                    int int2 = this.getInt2(state);
                                    if (ClientGlobals.moduleManager.smartClicking.canExecute5() || int2 != state) {
                                        int2 = 0;
                                        this.activeState19 = false;
                                    }
                                    return int2;
                                }
                            }
                        }
                    }
                    if (!completed10) {
                        if (!completed8) {
                            int indexOffset2 = this.settingCPSRecalculateDelay.getIndexOffset2();
                            if (animationTimer11.checkCondition(indexOffset2)) {
                                this.settingCPSRecalculateDelay.recalculate();
                                this.settingCPS2.recalculate();
                                this.animationTimer9.processAction3();
                            }
                            AttackTimeTracker auxiliaryLongEntry2 = this.auxiliaryLongEntry;
                            int indexOffset22 = this.settingCPS2.getIndexOffset2();
                            double double3 = this.settingClickDutyCycle.getDouble3();
                            double double6 = this.settingClickDutyCycle.getDouble6();
                            boolean enabled4 = false;
                            if (this.settingDoubleClicks.isEnabled() && this.settingDoubleClicks.isVisible()) {
                                enabled4 = true;
                            } else {
                                enabled4 = false;
                            }
                            boolean enabled = this.settingExtraDoubleClickRandomize.isEnabled();
                            Pair pair5 = auxiliaryLongEntry2.getPair(indexOffset22, double3, double6, enabled4, enabled, 0.76);
                            int second5 = (Integer)pair5.getSecond();
                            this.activeState19 = (Boolean)pair5.getFirst();
                            int int22 = this.getInt2(second5);
                            if (ClientGlobals.moduleManager.smartClicking.canExecute5() || int22 != second5) {
                                int22 = 0;
                                this.activeState19 = false;
                            }
                            return int22;
                        }
                    }
                }
                if (!completed10) {
                    boolean enabled5 = false;
                    if (hitResult != null && hitResult.typeOfHit == MovingObjectType.ENTITY && hitResult.entityHit instanceof EntityLivingBase) {
                        enabled5 = true;
                    } else {
                        enabled5 = false;
                    }
                    this.processAction();
                    int index = 0;
                    if (enabled5) {
                        int indexOffset4 = this.target.hurtTime;
                        if (ClientGlobals.moduleManager.smartClicking.canExecute5()) {
                            index = 0;
                        } else if (indexOffset4 != this.tickDelay7) {
                            SmoothDamper smoothDamper22 = this.getSmoothDamper2(indexOffset4);
                            int index2 = indexOffset4 * 4;
                            int int6 = this.getInt6(index2);
                            int int23 = this.getInt2(int6);
                            index = smoothDamper22.getInt(int23);
                            if (index > 0) {
                                int index3 = indexOffset4 + 11;
                                SmoothDamper smoothDamper4 = this.getSmoothDamper(index3);
                                int index4 = indexOffset4 * 4 + 2;
                                int int3 = this.getInt3(index4);
                                int int24 = this.getInt2(int3);
                                index += smoothDamper4.getInt(int24);
                            }
                        } else {
                            AnimationTimer animationTimer52 = this.animationTimer5;
                            int indexOffset23 = this.settingCPSFirstHitRecalculateTickDelay.getIndexOffset2();
                            if (animationTimer52.checkCondition(indexOffset23)) {
                                this.animationTimer5.processAction3();
                                this.settingCPSFirstHitRecalculateTickDelay.getDouble4();
                                this.settingCPSFirstHit.getDouble4();
                            }

                            AnimationTimer animationTimer62 = this.animationTimer6;
                            int indexOffset24 = this.settingDCPSFirstHitRecalculateTickDelay.getIndexOffset2();
                            if (animationTimer62.checkCondition(indexOffset24)) {
                                this.animationTimer6.processAction3();
                                this.settingDCPSFirstHitRecalculateTickDelay.getDouble4();
                                this.settingDCPSFirstHit.getDouble4();
                            }

                            SmoothDamper smoothDamper23 = this.getSmoothDamper2(22);
                            int indexOffset25 = this.settingCPSFirstHit.getIndexOffset2();
                            int int25 = this.getInt2(indexOffset25);
                            index = smoothDamper23.getInt(int25);
                            if (index > 0) {
                                SmoothDamper smoothDamper5 = this.getSmoothDamper(23);
                                int index5;
                                if (this.settingMode.getSelected().equals("Recommended")) {
                                    index5 = this.settingDCPS.getIndexOffset2();
                                } else {
                                    index5 = this.settingDCPSFirstHit.getIndexOffset2();
                                }

                                int int26 = this.getInt2(index5);
                                index += smoothDamper5.getInt(int26);
                            }
                        }

                        this.tickDelay11 = index > 0 ? ++this.tickDelay11 : 0;
                    } else if (hitResult != null && hitResult.typeOfHit != MovingObjectType.MISS) {
                        AnimationTimer animationTimer32 = this.animationTimer3;
                        int indexOffset26 = this.settingCPSOnWallRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer32.checkCondition(indexOffset26)) {
                            this.animationTimer3.processAction3();
                            this.settingCPSOnWallRecalculateTickDelay.getDouble4();
                            this.settingCPSOnWall.getDouble4();
                        }

                        AnimationTimer animationTimer42 = this.animationTimer4;
                        int indexOffset27 = this.settingDCPSOnWallRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer42.checkCondition(indexOffset27)) {
                            this.animationTimer4.processAction3();
                            this.settingDCPSOnWallRecalculateTickDelay.getDouble4();
                            this.settingDCPSOnWall.getDouble4();
                        }

                        SmoothDamper smoothDamper24 = this.getSmoothDamper2(26);
                        int indexOffset28 = this.settingCPSOnWall.getIndexOffset2();
                        index = smoothDamper24.getInt(indexOffset28);
                        if (index > 0) {
                            SmoothDamper smoothDamper6 = this.getSmoothDamper(27);
                            int indexOffset29 = this.settingDCPSOnWall.getIndexOffset2();
                            index += smoothDamper6.getInt(indexOffset29);
                        }
                    } else {
                        AnimationTimer animationTimer12 = this.animationTimer;
                        int indexOffset210 = this.settingCPSInAirRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer12.checkCondition(indexOffset210)) {
                            this.animationTimer.processAction3();
                            this.settingCPSInAirRecalculateTickDelay.getDouble4();
                            this.settingCPSInAir.getDouble4();
                        }

                        AnimationTimer animationTimer22 = this.animationTimer2;
                        int indexOffset211 = this.settingDCPSInAirRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer22.checkCondition(indexOffset211)) {
                            this.animationTimer2.processAction3();
                            this.settingDCPSInAirRecalculateTickDelay.getDouble4();
                            this.settingDCPSInAir.getDouble4();
                        }

                        SmoothDamper smoothDamper25 = this.getSmoothDamper2(24);
                        int indexOffset212 = this.settingCPSInAir.getIndexOffset2();
                        index = smoothDamper25.getInt(indexOffset212);
                        if (index > 0) {
                            SmoothDamper smoothDamper7 = this.getSmoothDamper(25);
                            int indexOffset213 = this.settingDCPSInAir.getIndexOffset2();
                            index += smoothDamper7.getInt(indexOffset213);
                        }
                    }
                    if (this.target == null) {
                        return index;
                    }
                    this.tickDelay7 = this.target.hurtTime;
                    return index;
                }
            }
            if (!completed10) {
                float minCPS3 = (float)this.settingMinCPS.getDouble3();
                float minCPS4 = (float)this.settingMinCPS.getDouble6();
                float value3 = (minCPS3 + minCPS4) / 2.0F;
                float value4 = (minCPS4 - minCPS3) / 6.0F;
                long time5 = System.currentTimeMillis();
                int state5 = (byte)0;
                for (state5 = 0; this.timestampMillis26 < time5; state5 += 1) {
                    double offset2 = (double)((int)(1000.0F / (float)((double)value3 + (double)value4 * new SecureRandom().nextGaussian())));
                    if (minecraft.objectMouseOver == null || minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                        offset2 /= this.settingAirMultiplier2.getValue();
                    }

                    this.timestampMillis26 = this.timestampMillis26 + Math.min((long)offset2, 1000L);
                }
                if (!this.settingDoubleClick.isEnabled() && state5 > 1) {
                    state5 = 1;
                }
                return this.getInt2(state5);
            }
            if (enabled3 && minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.MISS) {
                long timestampMillis172 = this.timestampMillis17;
                if (this.timer9.hasReached(timestampMillis172)) {
                    double double32 = this.settingReduceRecalculateDelay.getDouble3();
                    double double62 = this.settingReduceRecalculateDelay.getDouble6();
                    this.timestampMillis17 = (long)SettingContainer.settingProvider.getInt(double32, double62);
                    double double33 = this.settingReduceMulti.getDouble3();
                    double double63 = this.settingReduceMulti.getDouble6();
                    this.reachDistance8 = SettingContainer.settingProvider.getDouble4(double33, double63);
                    this.timer9.reset();
                }

                this.timestampMillis21 = Math.round((double)this.timestampMillis1 * this.reachDistance8);
            }
            if (this.settingReduceHitsOnWall.isEnabled() && minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                TimeHelper timer102 = this.timer10;
                long time6 = (long)this.settingReduceRecalculateDelayOnWall.getIndexOffset2();
                if (timer102.hasReached(time6)) {
                    this.settingReduceRecalculateDelayOnWall.recalculate();
                    this.settingReduceMultiplierOnWall.recalculate();
                    this.timer10.reset();
                }

                double timestampMillis12 = (double)this.timestampMillis1;
                this.timestampMillis21 = Math.round(timestampMillis12 * this.settingReduceMultiplierOnWall.getDouble3());
            }
            if (!ClientGlobals.moduleManager.smartClicking.canExecute5()) {
                int index7 = this.target != null ? this.target.hurtTime : 11;

                for (KillAuraHurtTime killAuraHurtTime : KillAuraHurtTime.values()) {
                    if (KillAuraHurtTime.getIndex(killAuraHurtTime) == index7 && !KillAuraHurtTime.checkKillAuraHurtTime(killAuraHurtTime)) {
                        return 0;
                    }
                }

                int timestampMillis214 = (int)this.timestampMillis21;
                long time7 = (long)this.getInt2(timestampMillis214);
                int reachDistance42 = (int)this.reachDistance4;
                this.reachDistance4 = this.reachDistance4 + (double)Math.max(1L, time7) / 20.0;
                int reachDistance43 = (int)this.reachDistance4;
                if (this.reachDistance4 >= (double)Math.max(1L, time7)) {
                    this.reachDistance4 = 0.0;
                }

                return reachDistance43 - reachDistance42;
            }
            return 0;
        }


        long timestampMillis213 = this.timestampMillis21;
        return !this.timer2.hasReached(timestampMillis213) && !this.timer3.hasReached(1000L) ? 0 : 1;
    }

    public void onPacket2(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C0APacketAnimation && this.settingAttacks.getSelected().equals("Swings")
        || packet2 instanceof C02PacketUseEntity
        && ((C02PacketUseEntity)packet2).getUseEntityAction() == UseEntityAction.ATTACK
        && this.settingAttacks.getSelected().equals("Attacks")) {
            this.tickDelay10++;
        }

        if (this.settingIntave13.isEnabled()) {
            if (packet2 instanceof C06PacketPlayerPosLook || packet2 instanceof C05PacketPlayerLook) {
                float rotationYaw = minecraft.thePlayer.rotationYaw;
                float rotationPitch = minecraft.thePlayer.rotationPitch;
                float value = this.getFloatArray()[0];
                float value2 = this.getFloatArray()[1];
                double offset = (double)Math.abs(value - rotationYaw);
                double offset2 = (double)Math.abs(value2 - rotationPitch);
                double offset3 = Math.abs(offset) + Math.abs(offset2);
                long time2 = this.timer7.getTime();
                if (offset3 > 8.0 && time2 < 4000L) {
                    this.tickDelay5++;
                    if (!(offset3 <= 30.0) && time2 < 250L) {
                        this.tickDelay5++;
                        if (!(offset3 <= 60.0)) {
                            this.tickDelay5++;
                        }
                    }
                } else if (this.tickDelay5 > 0) {
                    if (!(offset3 < 3.0) && time2 <= 5000L) {
                        int index = this.tickDelay5 - 1;
                        this.tickDelay5 = index;
                    } else {
                        this.tickDelay5 = 0;
                    }
                }
            }

            if (packet2 instanceof C0APacketAnimation) {
                this.timer7.reset();
            }
        }
    }

    public void applyRenderPass2(RenderPass renderPass) {
        RenderUtils.updateState();
        Vec3 vec = minecraft.thePlayer.getVec33(1.0F, this.rotation.yaw, this.rotation.pitch);
        Vec3 vec2 = minecraft.thePlayer.getVec35(1.0F).getVec312(vec.xCoord * 3.0, vec.yCoord * 3.0, vec.zCoord * 3.0);
        float x = (float)vec2.xCoord;
        float y = (float)vec2.yCoord;
        float z = (float)vec2.zCoord;
        this.copyOnWriteArrayList.add(new double[]{(double)x, (double)y, (double)z, (double)System.currentTimeMillis()});

        for (int i = 0; i < this.copyOnWriteArrayList.size(); i++) {
            double offset = ((double[])this.copyOnWriteArrayList.get(i))[3];
            if (this.checkCondition(offset)) {
                this.copyOnWriteArrayList.remove(i);
                i--;
            }
        }

        Line line = ClientGlobals.moduleManager.line;
        int int12 = line.settingAuraColor.getInt12(0);
        RenderUtils.setColor(ClientGlobals.moduleManager.line.settingResetOnTeleportAuraLine2.getIndex(renderPass, int12));
        GL11.glLineWidth((float)line.settingAuraLineWidth.getValue());
        Tessellator tessellator = Tessellator.getTessellator();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
        double[] coordArray = new double[]{-2.0, -1.0, -1.0};
        double[] coordArray2 = new double[]{-2.0, -1.0, -1.0};

        for (double[] coordArray3 : ((Iterable<double[]>) this.copyOnWriteArrayList)) {
            if (!Arrays.equals(coordArray, coordArray2) && !Arrays.equals(coordArray, coordArray3)) {
                double offset2 = (double)((float)coordArray3[0]);
                double offset3 = offset2 - minecraft.getRenderManager().getRenderPosX();
                double offset4 = (double)((float)coordArray3[1]);
                double offset5 = offset4 - minecraft.getRenderManager().getRenderPosY();
                double offset6 = (double)((float)coordArray3[2]);
                worldRenderer.getWorldRenderer(offset3, offset5, offset6 - minecraft.getRenderManager().getRenderPosZ()).updateState();
            }

            coordArray = coordArray3;
        }

        tessellator.updateState();
        RenderUtils.prepareScissor();
    }

    public double getDistanceToTarget5(Entity entity) {
        if (entity instanceof EntityLivingBase) {
            float value = this.getFloatArray()[0];
            float value2 = this.getFloatArray()[1];
            double reachDistance2 = this.reachDistance;
            if (RayTraceUtils.checkEntity(entity, 1.0F, value, value2, reachDistance2)) {
                double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
                return (double)(((EntityLivingBase)entity).hurtTime * 6) + distance;
            }
        }

        if (!(entity instanceof EntityLivingBase)) {
            return 10000.0;
        } else if (!this.settingSwitchOnlyToTargetsInRange.isEnabled()) {
            return 10000.0;
        } else if (this.targetEntity2 == null) {
            return 10000.0;
        } else {
            return entity.getEntityId() == this.targetEntity2.getEntityId() ? 1000.0 : 10000.0;
        }
    }

    private void processAction3() {
        this.tickDelay9 = Math.max(this.tickDelay9 - this.settingEnduranceReduction.getInt5(true), 0);
        if (this.tickDelay9 == 0) {
            this.enabled2 = false;
        }
    }

    private boolean isRotationYawOffsetSpeedVisible() {
        boolean enabled = this.settingOnlyRotation.getSelected().equals("Noise2");
        if (!enabled) {
            return enabled;
        } else {
            double double4 = this.settingRotationYawOffset.getDouble4(true);
            double double5 = this.settingRotationYawOffset.getDouble5(true);
            return double4 - double5 != 0.0;
        }
    }

    private int getInt5() {
        return 2;
    }

    private boolean checkEntity3(Entity entity) {
        double coordinate = this.doubleEntry.getCoordinate();
        return this.checkEntity(entity, coordinate);
    }

    private SmoothDamper getSmoothDamper(int index) {
        String selected = this.settingDoubleClickMode.getSelected();
switch (selected) {
            case "Combined":
                return this.smoothDamper2;
            case "Default":
                return index < 22 ? this.smoothDamper2 : this.smoothDamperArray[index];
            case "Separate":
                return this.smoothDamperArray[index];
            default:
                return this.smoothDamper2;
        }
    }

    public double getDistanceToTarget6(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000000.0;
        } else {
            float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
            float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
            float[] scaleFactorArray = SimulatedPlayer.getScaleFactorArray(entity, yaw, 263465940, pitch, 6342);
            float value = this.getFloatArray()[0];
            float value2 = this.getFloatArray()[1];
            double reachDistance2 = this.reachDistance;
            return !RayTraceUtils.checkEntity(entity, 1.0F, value, value2, reachDistance2)
            ? 11000.0 + Math.sqrt((double)(scaleFactorArray[0] * scaleFactorArray[0] + scaleFactorArray[1] * scaleFactorArray[1]))
            : (double)(((EntityLivingBase)entity).hurtTime * 1000) + Math.sqrt((double)(scaleFactorArray[0] * scaleFactorArray[0] + scaleFactorArray[1] * scaleFactorArray[1]));
        }
    }

    private List getList() {
        double offset = Math.max(this.settingPreHitRange.getDouble2(), this.settingPreAimRange.getDouble2());
        List list = ((List)(minecraft.theWorld.loadedEntityList.stream().filter(object -> this.checkEntity4(offset, (Entity)object)).collect(Collectors.toList())));
        this.applyList(list);
        return list;
    }

    private Vec3 getVec3(List list) {
        int index = 0;
        Vec3 vec = null;
        boolean completed = false;
        boolean enabled = false;
        int index2 = 0;
        List list2 = null;
        int state = (byte)0;
        if (0 < list.size()) {
            EntityLivingBase livingEntity = (EntityLivingBase)list.get(index2);
            int index3 = Float.compare(minecraft.thePlayer.getDistanceSqToEntity(livingEntity), 6.0F);
            while (true) {
                if (index3 <= 0) {
                    enabled = true;
                    break;
                }

                if (++index2 >= list.size()) {
                    break;
                }

                EntityLivingBase livingEntity2 = (EntityLivingBase)list.get(index2);
                index3 = Float.compare(minecraft.thePlayer.getDistanceSqToEntity(livingEntity2), 6.0F);
            }
            index = 0;
            while (true) {
                if (index >= list.size()) {
                    return null;
                }
                EntityLivingBase livingEntity3 = (EntityLivingBase)list.get(index);
                if (!(minecraft.thePlayer.getDistanceSqToEntity(livingEntity3) > 6.0F) || !enabled) {
                    SimulatedPlayer settingAutomatischerTöter2 = this.simulatedPlayer;
                    String selected = this.settingBestHitVec.getSelected();
                    int hitBoxPercentageVertical = (int)this.settingHitBoxPercentageVertical2.getValue();
                    int hitBoxPercentageHorizontal = (int)this.settingHitBoxPercentageHorizontal2.getValue();
                    int hitBoxPercentageVertical2 = (int)this.settingHitBoxPercentageVertical.getValue();
                    int hitBoxPercentageHorizontal2 = (int)this.settingHitBoxPercentageHorizontal.getValue();
                    Rotation rotation3 = ClientGlobals.rotationManager.getRotation3();
                    vec = settingAutomatischerTöter2.getVec3(livingEntity3, selected, hitBoxPercentageVertical, hitBoxPercentageHorizontal, hitBoxPercentageVertical2, hitBoxPercentageHorizontal2, rotation3);
                    double offset = vec.xCoord - minecraft.thePlayer.posX;
                    double offset2 = vec.yCoord - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                    double offset3 = vec.zCoord - minecraft.thePlayer.posZ;
                    Rotation rotation2 = new Rotation(
                    (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0),
                    (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI))
                    );
                    double double2 = this.settingPreAimRange.getDouble2();
                    double offset4 = Math.max(double2, this.settingPreHitRange.getDouble2()) + 3.0;
                    MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition(1.0F, rotation2, offset4, 0.0);
                    if (movingObjectPosition2 != null
                    && movingObjectPosition2.typeOfHit == MovingObjectType.ENTITY
                    && movingObjectPosition2.entityHit != null
                    && (movingObjectPosition2.entityHit.getEntityId() == livingEntity3.getEntityId() || !this.settingSmartAimEntityCheck.isEnabled())) {
                        { completed = true; break; }
                    }
                    if (!completed) {
                        float height = livingEntity3.getHeight();
                        AxisAlignedBB axisAlignedBB = livingEntity3.getBoundingBox2().offset((double)height, (double)height, (double)height);
                        double offset5 = livingEntity3.posX - minecraft.thePlayer.posX;
                        double offset6 = minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2();
                        PredictedHitPoint tertiaryDoubleEntry = new PredictedHitPoint(offset5, offset6, livingEntity3.posZ - minecraft.thePlayer.posZ);
                        list2 = PlayerUtil.getList(axisAlignedBB, tertiaryDoubleEntry);
                        boolean enabled3 = false;
                        state = 1;
                        for (PredictedHitPoint tertiaryDoubleEntry2 : (Iterable<PredictedHitPoint>) list2) {
                            double coordOffset = tertiaryDoubleEntry2.coordOffset;
                            double coordOffset1 = tertiaryDoubleEntry2.coordOffset1;
                            double coordOffset2 = tertiaryDoubleEntry2.coordOffset2;
                            tertiaryDoubleEntry2.booleanValueArray = this.getStateArray(coordOffset2, coordOffset1, coordOffset, livingEntity3);
                            if (tertiaryDoubleEntry2.booleanValueArray[0] || tertiaryDoubleEntry2.booleanValueArray[1]) {
                                enabled3 = true;
                            }

                            if (tertiaryDoubleEntry2.booleanValueArray[0]) {
                                state = 0;
                            }
                        }
                        if (enabled3 && (state == 0 || this.settingAimThroughWalls.isEnabled())) {
                            break;
                        }
                    }
                }
                if (!completed) {
                    index++;
                }
            }
        } else {
            index = 0;
            if (0 >= list.size()) {
                return null;
            }
            EntityLivingBase livingEntity4 = (EntityLivingBase)list.get(index);
            float value = 0.0F;
            if ((value = minecraft.thePlayer.getDistanceSqToEntity(livingEntity4) - 6.0F) == 0.0F) {
            } else if (value < 0.0F) {
            } else {
            }
            while (true) {
                SimulatedPlayer settingAutomatischerTöter3 = this.simulatedPlayer;
                String selected2 = this.settingBestHitVec.getSelected();
                int hitBoxPercentageVertical3 = (int)this.settingHitBoxPercentageVertical2.getValue();
                int hitBoxPercentageHorizontal3 = (int)this.settingHitBoxPercentageHorizontal2.getValue();
                int hitBoxPercentageVertical4 = (int)this.settingHitBoxPercentageVertical.getValue();
                int hitBoxPercentageHorizontal4 = (int)this.settingHitBoxPercentageHorizontal.getValue();
                Rotation rotation32 = ClientGlobals.rotationManager.getRotation3();
                vec = settingAutomatischerTöter3.getVec3(livingEntity4, selected2, hitBoxPercentageVertical3, hitBoxPercentageHorizontal3, hitBoxPercentageVertical4, hitBoxPercentageHorizontal4, rotation32);
                double offset7 = vec.xCoord - minecraft.thePlayer.posX;
                double offset8 = vec.yCoord - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
                double offset9 = vec.zCoord - minecraft.thePlayer.posZ;
                Rotation rotation4 = new Rotation(
                (float)(MathHelper.getDouble6(offset9, offset7) * 180.0 / Math.PI - 90.0),
                (float)(-(MathHelper.getDouble6(offset8, (double)MathHelper.sqrt_double(offset7 * offset7 + offset9 * offset9)) * 180.0 / Math.PI))
                );
                double double22 = this.settingPreAimRange.getDouble2();
                double offset10 = Math.max(double22, this.settingPreHitRange.getDouble2()) + 3.0;
                MovingObjectPosition movingObjectPosition3 = RayTraceUtils.getMovingObjectPosition(1.0F, rotation4, offset10, 0.0);
                if (movingObjectPosition3 != null
                && movingObjectPosition3.typeOfHit == MovingObjectType.ENTITY
                && movingObjectPosition3.entityHit != null
                && (movingObjectPosition3.entityHit.getEntityId() == livingEntity4.getEntityId() || !this.settingSmartAimEntityCheck.isEnabled())) {
                    { completed = true; break; }
                }
                if (!completed) {
                    float height2 = livingEntity4.getHeight();
                    AxisAlignedBB axisAlignedBB2 = livingEntity4.getBoundingBox2().offset((double)height2, (double)height2, (double)height2);
                    double offset11 = livingEntity4.posX - minecraft.thePlayer.posX;
                    double offset12 = minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2();
                    PredictedHitPoint tertiaryDoubleEntry3 = new PredictedHitPoint(offset11, offset12, livingEntity4.posZ - minecraft.thePlayer.posZ);
                    list2 = PlayerUtil.getList(axisAlignedBB2, tertiaryDoubleEntry3);
                    boolean enabled4 = false;
                    state = 1;
                    for (PredictedHitPoint tertiaryDoubleEntry4 : (Iterable<PredictedHitPoint>) list2) {
                        double coordOffset3 = tertiaryDoubleEntry4.coordOffset;
                        double coordOffset12 = tertiaryDoubleEntry4.coordOffset1;
                        double coordOffset22 = tertiaryDoubleEntry4.coordOffset2;
                        tertiaryDoubleEntry4.booleanValueArray = this.getStateArray(coordOffset22, coordOffset12, coordOffset3, livingEntity4);
                        if (tertiaryDoubleEntry4.booleanValueArray[0] || tertiaryDoubleEntry4.booleanValueArray[1]) {
                            enabled4 = true;
                        }

                        if (tertiaryDoubleEntry4.booleanValueArray[0]) {
                            state = 0;
                        }
                    }
                    if (enabled4 && (state == 0 || this.settingAimThroughWalls.isEnabled())) {
                        break;
                    }
                    if (++index >= list.size()) {
                        return null;
                    }
                    livingEntity4 = (EntityLivingBase)list.get(index);
                    float value2 = 0.0F;
                    if ((value2 = minecraft.thePlayer.getDistanceSqToEntity(livingEntity4) - 6.0F) == 0.0F) {
                    } else if (value2 < 0.0F) {
                    } else {
                    }
                }
            }
        }
        if (!completed) {
            PredictedHitPoint tertiaryDoubleEntry5 = PlayerUtil.getTertiaryDoubleEntry(list2, state);
            this.target = (EntityLivingBase)list.get(index);
            return new Vec3(tertiaryDoubleEntry5.coordOffset2, tertiaryDoubleEntry5.coordOffset1, tertiaryDoubleEntry5.coordOffset);
        }


        this.target = (EntityLivingBase)list.get(index);
        return new Vec3(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    private boolean checkEntity4(double offset, Entity entity) {
        return this.checkEntity(entity, offset);
    }

    public double getDistanceToTarget7(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return 1000000.0;
        } else {
            float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
            float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
            float[] scaleFactorArray = SimulatedPlayer.getScaleFactorArray(entity, yaw, 263465940, pitch, 6342);
            float value = this.getFloatArray()[0];
            float value2 = this.getFloatArray()[1];
            double reachDistance2 = this.reachDistance;
            return RayTraceUtils.checkEntity(entity, 1.0F, value, value2, reachDistance2)
            ? Math.sqrt((double)(scaleFactorArray[0] * scaleFactorArray[0] + scaleFactorArray[1] * scaleFactorArray[1]))
            : 11000.0 + Math.sqrt((double)(scaleFactorArray[0] * scaleFactorArray[0] + scaleFactorArray[1] * scaleFactorArray[1]));
        }
    }

    private boolean[] getStateArray(double x, double y, double z, EntityLivingBase livingEntity) {
        if (minecraft.thePlayer.checkCondition(x, y, z)) {
            double offset = x - minecraft.thePlayer.posX;
            double offset2 = y - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
            double offset3 = z - minecraft.thePlayer.posZ;
            Rotation rotation2 = new Rotation(
            (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0),
            (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI))
            );
            MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition(1.0F, rotation2, this.reachDistance, 0.0);
            if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit != MovingObjectType.MISS) {
                MovingObjectType typeOfHit = movingObjectPosition2.typeOfHit;
                MovingObjectType movingObjectTypeKind = MovingObjectType.ENTITY;
                if (typeOfHit != movingObjectTypeKind || movingObjectPosition2.entityHit.getEntityId() != livingEntity.getEntityId()) {
                    return new boolean[]{false, false};
                }
            }

            boolean[] conditionArray = new boolean[2];
            boolean enabled;
            if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.ENTITY) {
                enabled = true;
            } else {
                enabled = false;
            }

            conditionArray[0] = enabled;
            conditionArray[1] = true;
            return conditionArray;
        } else {
            return new boolean[]{false, false};
        }
    }

    private void updateKillAura5() {
        if (minecraft.thePlayer.isUsingItem()) {
            minecraft.playerController.processAction2(minecraft.thePlayer);
        }
    }

    private SmoothDamper getSmoothDamper2(int index) {
        String selected = this.settingClickMode.getSelected();
switch (selected) {
            case "Combined":
                return this.smoothDamper;
            case "Default":
                return index < 22 ? this.smoothDamper : this.smoothDamperArray[index];
            case "Separate":
                return this.smoothDamperArray[index];
            default:
                return this.smoothDamper;
        }
    }

    private int getInt6(int index) {
        return this.settingMode.getSelected().equals("Recommended")
        ? this.settingCPS3.getIndexOffset2()
        : this.rangeSettingArray[index].getIndexOffset2();
    }

    private Pair getPair() {
        int intEntry = this.clickPatternProfile.getInt();
        int intEntry2 = this.clickPatternProfile.getInt();
        if (minecraft.objectMouseOver == null || minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
            intEntry = (int)((double)intEntry / this.settingAirMultiplier.getValue());
            intEntry2 = (int)((double)intEntry2 / this.settingAirMultiplier.getValue());
        }

        int index = (int)Math.min((double)intEntry * (1.0 / this.settingMultiplier.getValue()), 1000.0);
        int index2 = (int)Math.min((double)intEntry2 * (1.0 / this.settingMultiplier.getValue()), 1000.0);
        return new Pair(index + index2, index);
    }

    public void applyRenderPass3(RenderPass renderPass, double offset) {
        RenderUtils.updateState();
        if (this.settingDot.isEnabled() && this.target != null && this.movingObjectPosition != null) {
            MovingObjectPosition movingObjectPosition2 = this.movingObjectPosition;
            if (movingObjectPosition2.hitVec != null && this.positionVec2 != null) {
                Vec3 vec = this.positionVec2.getVec39(this.movingObjectPosition.hitVec, offset);
                double offset2 = -minecraft.getRenderManager().getRenderPosX();
                double offset3 = -minecraft.getRenderManager().getRenderPosY();
                Vec3 vec2 = vec.getVec312(offset2, offset3, -minecraft.getRenderManager().getRenderPosZ());
                float value = (float)this.settingDotSize.getValue() / 10.0F;
                ShaderOptions shaderOptions2 = this.shaderOptions;
                int int12 = this.settingDotColor.getInt12(0);
                RenderUtils.setColor(shaderOptions2.getIndex(renderPass, int12));
                RenderUtils.processAction4(
                vec2.xCoord - (double)value,
                vec2.yCoord - (double)value,
                vec2.zCoord - (double)value,
                vec2.xCoord + (double)value,
                vec2.yCoord + (double)value,
                vec2.zCoord + (double)value
                );
            }
        }

        RenderUtils.prepareScissor();
    }

    private int getInt7(boolean enabled) {
        int index = 0;
        if (enabled) {
            index = this.getInt4();
        } else {
            this.activeState22 = true;
            this.processChar();
        }

        if (ClientGlobals.moduleManager.moveHelper.isToggled()
        && AutoClickerComboBreakerType.HIT_SELECTION.isSelected()
        && ClientGlobals.moduleManager.moveHelper.isExpanded2()) {
            index = 0;
        }

        if (index == 0
        && ClientGlobals.moduleManager.moveHelper.isToggled()
        && AutoClickerComboBreakerType.HIT_SELECTION.isSelected()
        && !ClientGlobals.moduleManager.moveHelper.isExpanded2()
        && ClientGlobals.moduleManager.moveHelper.isEnabled2()) {
            index = 1;
        }

        double offset = (double)index;
        ClickHelper clickHelper = EventClick.clickHelper;
        int index2 = (int)(offset + clickHelper.getDouble10());
        if (this.settingCPSLimiterIntave.isEnabled()) {
            if (this.auxiliaryArrayListEntry.canExecute()) {
                return -1337;
            }
        } else {
            double offset2 = (double)index2;
            if (offset2 > this.settingCPSLimiter.getValue()) {
                return -1337;
            }
        }

        if (index == 0 && this.settingCritFix.isEnabled() && minecraft.objectMouseOver != null) {
            boolean enabled3 = false;
            boolean completed = false;
            if (!ClientGlobals.moduleManager.criticals.isToggled()) {
                if (minecraft.thePlayer.fallDistance <= 0.0F
                || minecraft.thePlayer.onGround
                || minecraft.thePlayer.canExecute9()
                || minecraft.thePlayer.isInWater()) {
                    completed = true;
                }
                if (!completed) {
                    if (minecraft.thePlayer.checkPotion(Potion.potion16)
                    || minecraft.thePlayer.ridingEntity != null
                    || !(minecraft.objectMouseOver.entityHit instanceof EntityLivingBase)) {
                        completed = true;
                    }
                }
            }
            if (!completed) {
                enabled3 = true;
            } else {
                enabled3 = false;
            }

            if (!this.visible2 && enabled3) {
                return 1;
            }
        }

        return index;
    }

    private boolean checkCondition(double offset) {
        return Math.abs(offset - (double)System.currentTimeMillis()) > ClientGlobals.moduleManager.line.settingAuraLineTime.getValue();
    }

    private boolean isMoveFixVisible() {
        return !this.settingNoRotation.isEnabled();
    }

    private boolean isCPSRecalculateTickDelayVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart") ? false : this.settingMode.getSelected().equals("Recommended");
    }

    private boolean isReduceMultiVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingReduceInAir.isEnabled();
    }

    private boolean isRotationOffsetVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isBestvecPitchinterpolationVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isRotationPitchOffsetSpeedVisible() {
        boolean enabled = this.settingOnlyRotation.getSelected().equals("Noise2");
        if (!enabled) {
            return enabled;
        } else {
            double double4 = this.settingRotationPitchOffset.getDouble4(true);
            double double5 = this.settingRotationPitchOffset.getDouble5(true);
            return double4 - double5 != 0.0;
        }
    }

    private boolean isXRelDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isAdaptCurveIfNeededVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isReduceInAirVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private boolean isBestHitYMInDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isDeviationCPSVisible() {
        return this.settingAdvanced.getSelected().equals("Deviation");
    }

    private boolean isEnduranceReductionVisible() {
        return !this.settingSmartPreHit.isEnabled() ? false : this.settingSmartPreHit.isVisible();
    }

    private boolean isPitchOutliersVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isSmartDCPSVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart") ? false : this.settingMode.getSelected().equals("Recommended");
    }

    private boolean isApplyRecordedMouseJitterMovementIfAvailableVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isLoseDelayVisible() {
        return !this.settingSmartPreHit.isEnabled() ? false : this.settingSmartPreHit.isVisible();
    }

    private boolean isMaxOrthogonalMovementVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isEnduranceVisible() {
        return !this.settingSmartPreHit.isEnabled() ? false : this.settingSmartPreHit.isVisible();
    }

    private boolean isTestVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise");
    }

    private boolean isOnWallVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isSmartRotateVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isAdditionalMinAimspeedVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise");
    }

    private boolean isReduceCPSIfYouCanNotHitATargettVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced")
        || this.settingAdvanced.getSelected().equals("Smart")
        || this.settingAdvanced.getSelected().equals("Recorded")
        || this.settingAdvanced.getSelected().equals("Deviation");
    }

    private boolean isPitchOutliersDelayVisible() {
        return !this.settingOnlyRotation.getSelected().equals("Advanced") ? false : this.settingPitchOutliers.isEnabled();
    }

    private boolean isDeviationDoubleClickVisible() {
        return this.settingAdvanced.getSelected().equals("Deviation");
    }

    private boolean isAdditionalInaccuracyVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isAdditionalAimspeedIfNeededVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise");
    }

    private boolean isMinAbsDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isHurtTimeVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isSmartDCPSOnWallVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean canAttack9() {
        if (this.settingOnlyRightClickBlock.isEnabled() && !minecraft.gameSettings.keyBindUseItem.isKeyDown()) {
            return false;
        } else if (this.settingOnlyWhileSwingInProgress.isEnabled() && !minecraft.thePlayer.state2) {
            return false;
        } else if (this.settingOnlyWhileHurt.isEnabled() && minecraft.thePlayer.hurtTime == 0) {
            return false;
        } else if (this.settingSmartBlock.isEnabled()) {
            if (this.target == null) {
                return false;
            } else if (!this.target.state2) {
                return false;
            } else if (this.target.getItemStack() == null) {
                return false;
            } else {
                return this.target.getItemStack().getItem() instanceof ItemSword ? true : this.target.getItemStack().getItem() instanceof ItemTool;
            }
        } else {
            return true;
        }
    }

    private boolean isOnlyWhileHurtVisible() {
        return !this.settingNone3.getSelected().equals("None");
    }

    private boolean isSpikeMultiVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingSpikes.isEnabled();
    }

    private boolean isMinDistanceToBestVecPitchVisible() {
        return this.settingNone4.getSelected().equals("None")
        ? false
        : this.settingNone.getSelected().equalsIgnoreCase("Separate");
    }

    private boolean canAttack2() {
        if (this.activeState && this.settingLongClick.isEnabled()) {
            if (minecraft.currentScreen == null && minecraft.inGameHasFocus) {
                if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                    int tickDelay62 = this.tickDelay6;
                    if (this.animationTimer10.checkCondition(tickDelay62)) {
                        this.tickDelay6 = SettingContainer.settingProvider.getInt2(1, 3);
                        this.animationTimer10.processAction3();
                        this.activeState = false;
                        return true;
                    }

                    return minecraft.currentScreen != null ? false : minecraft.inGameHasFocus;
                }
            }

            this.animationTimer10.processAction3();
            this.activeState = false;
            return minecraft.currentScreen != null ? false : minecraft.inGameHasFocus;
        } else {
            this.animationTimer10.processAction3();
            return false;
        }
    }

    private boolean isUltimateTrashcodeBypassVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isPredictionInaccuracySmoothnessVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isMinDistanceToBestVecYawVisible() {
        return this.settingNone4.getSelected().equals("None")
        ? false
        : this.settingNone.getSelected().equalsIgnoreCase("Separate");
    }

    private boolean isRotationPitchOffsetVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isPitchOutliersAddVisible() {
        return !this.settingOnlyRotation.getSelected().equals("Advanced") ? false : this.settingPitchOutliers.isEnabled();
    }

    private boolean isNoise3PlayerResponseVisible() {
        return this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isDropMultiVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingDrops.isEnabled();
    }

    private boolean isCoolDownVisible() {
        return !this.settingLegit.getSelected().equals("Multi");
    }

    private boolean checkCondition() {
        if (ClientGlobals.moduleManager.autoRod.isToggled()
        && ClientGlobals.moduleManager.autoRod.settingCombat.isEnabled()
        && !ClientGlobals.moduleManager.gommeMode.isToggled()) {
            minecraft.playerController.updateController();
            ItemStack itemStack = minecraft.thePlayer.container.getSlot(minecraft.playerController.indexOffset + 36).getStack();
            if ((itemStack == null || !(itemStack.getItem() instanceof ItemFishingRod)) && !minecraft.playerController.timer.hasReached(90L)) {
                this.processAction3();
                return false;
            }
        }

        if (!this.settingPreHit.isEnabled()) {
            if (minecraft.objectMouseOver == null) {
                return false;
            }

            if (minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                return false;
            }
        } else {
            double distance = (double)minecraft.thePlayer.getDistanceToEntity(this.target);
            AttackTargetDistance doubleEntry272 = this.doubleEntry27;
            if (distance > doubleEntry272.getCoordinate()) {
                this.processAction3();
                return false;
            }

            AxisAlignedBB boundingBox = SimulatedPlayer.processAction(this.target);
            Vec3 vec = minecraft.thePlayer.getVec35(1.0F);
            double pitch = (double)minecraft.thePlayer.rotationPitch;
            double coordinate = TrajectoryPredictor.getCoordinate(boundingBox, vec, pitch);
            double yaw = (double)minecraft.thePlayer.rotationYaw;
            double coordinate2 = TrajectoryPredictor.getCoordinate(boundingBox, vec, yaw);
            if (coordinate < 0.0 || coordinate > 1.0 || coordinate2 < 0.0 || !(coordinate2 <= 1.0)) {
                float rotationPitch = minecraft.thePlayer.rotationPitch;
                float rotationYaw = minecraft.thePlayer.rotationYaw;
                float[] scaleFactorArray = SimulatedPlayer.getScaleFactorArray(this.target, rotationYaw, 263465940, rotationPitch, 6342);
                double offset = (double)Math.abs(scaleFactorArray[0]);
                AttackTargetDistance doubleEntry292 = this.doubleEntry29;
                if (offset > doubleEntry292.getCoordinate()) {
                    return false;
                }

                double offset2 = (double)Math.abs(scaleFactorArray[1]);
                AttackTargetDistance doubleEntry342 = this.doubleEntry34;
                if (offset2 > doubleEntry342.getCoordinate()) {
                    return false;
                }
            }

            int indexOffset2 = this.settingPrediction.getIndexOffset2();
            if (!this.checkCondition(3.0, indexOffset2)) {
                return false;
            }
        }

        if (minecraft.thePlayer.getDistanceSqToEntity(this.target) >= 6.0F && this.reachDistance > 5.0) {
            return false;
        } else {
            if (minecraft.objectMouseOver != null
            && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
            && minecraft.objectMouseOver.entityHit instanceof EntityPlayer) {
                Entity entityHit = minecraft.objectMouseOver.entityHit;
                if (ClientGlobals.moduleManager.teams.checkEntity2(entityHit)) {
                    return false;
                }

                Entity entityHit2 = minecraft.objectMouseOver.entityHit;
                if (ClientGlobals.moduleManager.midClick.checkEntity(entityHit2) && !ClientGlobals.moduleManager.midClick.enabled2) {
                    return false;
                }
            }

            return !this.settingNoLoopHit.isEnabled() || minecraft.indexOffset1 == 0;
        }
    }

    private boolean isHazeMaxVisible() {
        return this.settingSimple.getSelected().equals("HazeRange");
    }

    private boolean canAttack3() {
        return this.settingInterpolation.isEnabled();
    }

    private boolean isOnFirstHitVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isSmartClickModeVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isRotationYawOffsetVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isSimpleClickDutyCycleVisible() {
        return this.settingAdvanced.getSelected().equals("Simple");
    }

    private boolean isSmartDCPSInAirVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isSmartCPSFirstHitVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isAdditionalAccuracyVisible() {
        return !this.settingOnlyRotation.getSelected().equals("Noise2")
        ? false
        : this.settingSuperCorrectionProbability.getDouble2(true) > 0.0;
    }

    private boolean isSilentMoveFixIntaveVisible() {
        return !this.settingDefault.isVisible() ? false : !this.settingDefault.getSelected().equals("None");
    }

    private boolean isSuperCorrectionProbabilityVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isSilentMoveFixVisible() {
        return !this.settingMoveFix.isEnabled() ? false : this.settingMoveFix.isVisible();
    }

    private boolean isReactionTimeVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean checkCondition4() {
        return this.target != null && ClientGlobals.rotationManager.getIndexOffset8() >= this.getInt5();
    }

    private boolean isDropsVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private boolean isSpikeDelayVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingSpikes.isEnabled();
    }

    private boolean isSpikeDurationVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingSpikes.isEnabled();
    }

    private boolean isReducedRotateVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isAdditionalDynamicSpeedVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise");
    }

    private boolean isMaxRotationAngleVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isDecelerationfactorPreVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isBasicDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Basic");
    }

    private boolean isMaxYAbsDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isSimpleCPSRecalculateDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Simple");
    }

    private boolean isProbabilityOfAimingEvenWhenNotNeededVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise");
    }

    private boolean isSmartCPSInAirVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isRecordedModeVisible() {
        return this.settingAdvanced.getSelected().equals("Recorded");
    }

    private boolean isLookaheadTimeVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isMaxXAbsDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isLongClickVisible() {
        return !this.canAttack5();
    }

    private boolean isSmartCPSOnWallRecalculateTickDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isMultiplierVisible() {
        return this.settingAdvanced.getSelected().equals("Recorded");
    }

    private boolean isRotationOnTargetVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean canAttack4() {
        if (!this.settingReduceCPSIfYouCanNotHitATarget.isEnabled()) {
            return false;
        } else if (this.target == null) {
            return false;
        } else {
            EntityLivingBase target2 = this.target;
            if (target2.hurtTime != 0) {
                return false;
            } else {
                long time2;
                return System.currentTimeMillis() <= this.target.epochMillis - 75L
                ? false
                : ((time2 = System.currentTimeMillis() - this.target.epochMillis - 225L) == 0L ? 0 : (time2 < 0L ? -1 : 1)) < 0;
            }
        }
    }

    private boolean isNoYawAdjustmentIfTargetOutOfReachVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isSmartCPSFirstHitRecalculateTickDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isSimpleExtraDoubleClickRandomizeVisible() {
        if (!this.settingAdvanced.getSelected().equals("Simple")) {
            return false;
        } else {
            return !this.settingDoubleClicks.isVisible() ? false : this.settingDoubleClicks.isEnabled();
        }
    }

    private boolean isMaxTargetsVisible() {
        return this.settingLegit.getSelected().equals("Multi");
    }

    private boolean isMaxFixRotationReachVisible() {
        return !this.settingNone4.getSelected().equals("None");
    }

    private boolean isCPSSettingsVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isSuperCorrectionVisible() {
        return !this.settingOnlyRotation.getSelected().equals("Noise2")
        ? false
        : this.settingReactionTime.getDouble6() > 0.0;
    }

    private boolean isSuperCorrectionDelayVisible() {
        return !this.settingOnlyRotation.getSelected().equals("Noise2")
        ? false
        : this.settingSuperCorrectionProbability.getDouble2(true) > 0.0;
    }

    private boolean isCPSVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private boolean isNoise3ScaleVisible() {
        return this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isSmartDCPSFirstHitRecalculateTickDelayVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isDelayClicksAfterClosingGuiScreenVisible() {
        return !this.settingInInv.isEnabled();
    }

    private boolean isOnlyWhileSwingInProgressVisible() {
        return !this.settingNone3.getSelected().equals("None");
    }

    private boolean isIntervalVisible() {
        return this.settingSimple.getSelected().equals("IntervalRange");
    }

    private boolean isYawAccelerationVisible() {
        if (this.canAttack3()) {
            return false;
        } else {
            return this.settingOnlyRotation.getSelected().contains("Noise")
            ? false
            : !this.settingOnlyRotation.getSelected().equals("Turbo");
        }
    }

    private boolean isRangeAtIntervalVisible() {
        return this.settingSimple.getSelected().equals("IntervalRange");
    }

    private boolean isHitBoxPercentageVerticalVisible() {
        return this.settingBestHitVec.getSelected().equalsIgnoreCase("BestHitVec");
    }

    private boolean isThroughWallsRangeVisible() {
        return !this.settingThroughWalls.isEnabled() ? false : this.settingAimThroughWalls.isEnabled();
    }

    private boolean isYawSpeedVisible() {
        return this.settingOnlyRotation.getSelected().contains("Noise")
        ? false
        : !this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isMinFixRotationAngleVisible() {
        return !this.settingNone4.getSelected().equals("None");
    }

    private boolean canAttack5() {
        return this.settingAdvanced.getSelected().equals("Simple") || this.settingAdvanced.getSelected().equals("Recorded");
    }

    private boolean isActionToTrackIntervalVisible() {
        return this.settingSimple.getSelected().equals("IntervalRange");
    }

    private boolean isHitBoxPercentageHorizontalVisible() {
        return this.settingBestHitVec.getSelected().equalsIgnoreCase("BestHitVec");
    }

    private boolean isReduceRecalculateDelayVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingReduceInAir.isEnabled();
    }

    private boolean isDCPSOnWallRecalculateTickDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isDoubleClickFirstHitVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isIntave13Visible() {
        return !this.settingNoRotation.isEnabled();
    }

    private boolean isHurtTimeToHitWithReachVisible() {
        return this.settingSimple.getSelected().equals("IntervalRange");
    }

    private boolean isHurttimesToHitVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private boolean isHitBoxPercentageVertical1Visible() {
        return this.settingBestHitVec.getSelected().equalsIgnoreCase("NearestHitVec");
    }

    private boolean isNoise33BetterAccelerationVisible() {
        return this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isMinDistanceToBestVecCalculationVisible() {
        return !this.settingNone4.getSelected().equals("None");
    }

    private boolean isYRelDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isSmartModeVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isSmartDCPSHurtVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isSmartCPSHurtVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isMinReachToIntervalHitVisible() {
        return this.settingSimple.getSelected().equals("IntervalRange");
    }

    private boolean isSmartCPSVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart") ? false : this.settingMode.getSelected().equals("Recommended");
    }

    private boolean isSmartBlockVisible() {
        return !this.settingNone3.getSelected().equals("None");
    }

    private boolean isInteractAutoBlockVisible() {
        return !this.settingNone3.getSelected().equals("None");
    }

    private boolean isSimpleDoubleClicksVisible() {
        if (!this.settingAdvanced.getSelected().equals("Simple")) {
            return false;
        } else {
            return this.settingCPS2.getDouble2() > 16.0
            ? false
            : this.settingCPS2.getDouble9() >= 10.0;
        }
    }

    private boolean isClickPatternVisible() {
        return this.settingAdvanced.getSelected().equals("Recorded");
    }

    private boolean isAimSpeedVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isDecelerationfactorPostVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isHazeAddVisible() {
        return this.settingSimple.getSelected().equals("HazeRange");
    }

    private boolean isMinDistanceToBestVecVisible() {
        return this.settingNone4.getSelected().equals("None")
        ? false
        : this.settingNone.getSelected().equalsIgnoreCase("Combined");
    }

    private boolean isUnblockAACVisible() {
        return this.settingNone3.getSelected().equals("AAC") || this.settingNone3.getSelected().equals("AACOld");
    }

    private boolean isChanceOfExtendedRangeVisible() {
        return this.settingSimple.getSelected().equals("Dual");
    }

    private boolean isSmartCPSOnWallVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isDropDurationVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingDrops.isEnabled();
    }

    private boolean canAttack6() {
        if (this.settingBreakBlocks.isEnabled()) {
            if (minecraft.objectMouseOver == null) {
                return false;
            }

            if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                return true;
            }
        }

        return false;
    }

    private boolean canAttack() {
        return this.settingOnlyRotation.getSelected().equals("Noise3");
    }

    private boolean isNoise3AccelerationVisible() {
        return this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isDeviationAirMultiplierVisible() {
        return this.settingAdvanced.getSelected().equals("Deviation");
    }

    private boolean isPredictionVisible() {
        return !this.settingSmartPreHit.isEnabled() ? false : this.settingSmartPreHit.isVisible();
    }

    private boolean isSpikesVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private boolean isBestHitXMinDistVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isBaseRangeVisible() {
        return this.settingSimple.getSelected().equals("Dual");
    }

    private boolean isInAirVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isDropDelayVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingDrops.isEnabled();
    }

    private boolean isReduceRecalculateDelayOnWallVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingReduceHitsOnWall.isEnabled();
    }

    private boolean isSwitchOnlyToTargetsInRangeVisible() {
        return this.settingUltimateSwitch.getSelected().equals("UltimateSwitch");
    }

    private boolean isPredictionInaccuracyVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isDCPSRecalculateTickDelayHurtVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isDCPSInAirRecalculateTickDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean canAttack7() {
        return (double)SettingContainer.settingProvider.getInt2(0, 99) > 100.0 - this.settingMissClickPercentage.getValue();
    }

    private boolean isSimpleCPSVisible() {
        return this.settingAdvanced.getSelected().equals("Simple");
    }

    private boolean isSmartCPSInAirRecalculateTickDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isFixRotationChanceVisible() {
        return !this.settingNone4.getSelected().equals("None");
    }

    private boolean isSmartDoubleClickModeSVisible() {
        return this.settingAdvanced.getSelected().equals("Smart");
    }

    private boolean isRecordedAirMultiplierVisible() {
        return this.settingAdvanced.getSelected().equals("Recorded");
    }

    private boolean isHurtVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isRandomStrengthVisible() {
        return this.settingOnlyRotation.getSelected().equals("Basic")
        || this.settingOnlyRotation.getSelected().equals("Doubled")
        || this.settingOnlyRotation.getSelected().equals("OnlyRotation")
        || this.settingOnlyRotation.getSelected().equals("Hybrid");
    }

    private boolean isSpeedChangeDelayVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private void setState5(boolean enabled) {
        this.setActive4(enabled);
    }

    private boolean isInnerRadiusVisible() {
        return this.settingOnlyRotation.getSelected().equals("Advanced");
    }

    private boolean isOnTargetVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart") ? false : this.settingMode.getSelected().equals("Recommended");
    }

    private boolean isReduceMultiplierOnWallVisible() {
        return !this.settingAdvanced.getSelected().equals("Advanced") ? false : this.settingReduceHitsOnWall.isEnabled();
    }

    private boolean isReduceHitsOnWallVisible() {
        return this.settingAdvanced.getSelected().equals("Advanced");
    }

    private boolean isNoPitchAdjustmentIfTargetOutOfReachVisible() {
        return this.settingOnlyRotation.getSelected().equals("Noise2");
    }

    private boolean isNoise3AimSpeedVisible() {
        return this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private boolean isPitchAccelerationVisible() {
        if (this.canAttack3()) {
            return false;
        } else {
            return this.settingOnlyRotation.getSelected().contains("Noise")
            ? false
            : !this.settingOnlyRotation.getSelected().equals("Turbo");
        }
    }

    private boolean canAttack8() {
        if (!this.settingInInv.isEnabled() && !minecraft.isIntegratedServerRunning()) {
            return false;
        } else if (ClientGlobals.moduleManager.blink.isToggled() && ClientGlobals.moduleManager.blink.settingDisableKillAura.isEnabled()) {
            return false;
        } else {
            return !ClientGlobals.moduleManager.fly.isToggled() ? true : !ClientGlobals.moduleManager.fly.settingDisableAura.isEnabled();
        }
    }

    private void updateKillAura6() {
        this.activeState14 = true;
        this.reachDistance = 6.0;
        this.name2 = "Multi";
    }

    private boolean isOnlyRightClickBlockVisible() {
        return !this.settingNone3.getSelected().equals("None");
    }

    private boolean isHitBoxPercentageHorizontal1Visible() {
        return this.settingBestHitVec.getSelected().equalsIgnoreCase("NearestHitVec");
    }

    private boolean isSmartDCPSFirstHitVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart")
        ? false
        : this.settingMode.getSelected().equals("Overengineered");
    }

    private boolean isBetterAccelerationVisible() {
        if (this.canAttack3()) {
            return false;
        } else {
            return this.settingOnlyRotation.getSelected().contains("Noise")
            ? false
            : !this.settingOnlyRotation.getSelected().equals("Turbo");
        }
    }

    private void updateKillAura3() {
        this.target = null;
        this.targetEntity2 = null;
        this.itemList4.clear();
    }

    private boolean isDelayClicksAfterRespawnVisible() {
        return !this.settingInInv.isEnabled();
    }

    private boolean isDelayRotationsAfterRespawnVisible() {
        return !this.settingInInv.isEnabled();
    }

    private boolean isDCPSRecalculateTickDelayVisible() {
        return !this.settingAdvanced.getSelected().equals("Smart") ? false : this.settingMode.getSelected().equals("Recommended");
    }

    private boolean isPitchSpeedVisible() {
        return this.settingOnlyRotation.getSelected().contains("Noise")
        ? false
        : !this.settingOnlyRotation.getSelected().equals("Turbo");
    }

    private void updateKillAura7() {
        this.activeState14 = false;
        this.reachDistance = 3.0;
        this.name2 = this.settingLegit.getSelected();
    }

    private void processChar() {
        long time = System.currentTimeMillis();
        this.timestampMillis26 = time - (long)SettingContainer.settingProvider.getInt2(0, 45);
        this.timestampMillis24 = time;
    }
}
