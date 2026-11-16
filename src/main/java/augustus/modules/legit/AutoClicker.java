package augustus.modules.legit;

import augustus.custom.RuntimeBridge;
import client.ClientGlobals;
import client.config.ClickPatternManager;
import client.config.ClickPatternProfile;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventSilentMove;
import client.event.EventWorldLoad;
import client.event.SilentMoveListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.AutoClickerComboBreakerType;
import client.setting.AutoClickerHurtTime;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.setting.TargetFilterSetting;
import client.util.AnimationTimer;
import client.util.AttackTimeTracker;
import client.util.BooleanSupplier;
import client.util.ClickHistoryBuffer;
import client.util.CombatTargetFilter;
import client.util.CombatUtil;
import client.util.ConsumerTask;
import client.util.MovingObjectType;
import client.util.Pair;
import client.util.RayTraceUtils;
import client.util.SimulatedPlayer;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class AutoClicker extends Module implements WorldLoadListener, ClickListener, SilentMoveListener, TickListener {
    public ModeSetting settingMode;
    public NumberSetting settingCPSLimiter;
    public BooleanSetting settingCPSLimiterIntave;
    public SettingGroup settingModeValues;
    public RangeSetting settingSimpleCPS;
    public RangeSetting settingSimpleCPSRecalculateDelay;
    public ActionSetting settingSimpleClickDutyCycle;
    public BooleanSetting settingSimpleDoubleClicks;
    public BooleanSetting settingSimpleExtraDoubleClickRandomize;
    public RangeSetting settingDeviationCPS;
    public BooleanSetting settingDeviationDoubleClick;
    public NumberSetting settingDeviationAirMultiplier;
    public RangeSetting settingDragCPSInAir;
    public RangeSetting settingDragCPSInAirRecalculateTickDelay;
    public RangeSetting settingMaxCPS;
    public RangeSetting settingMaxCPSRecalculateTickDelay;
    public NumberSetting settingMinStandardDeviation;
    public RangeSetting settingAdvancedCPS;
    public RangeSetting settingAdvancedCPSRecalculateDelay;
    public BooleanSetting settingSpikes;
    public NumberSetting settingSpikeDelay;
    public RangeSetting settingSpikeDuration;
    public NumberSetting settingSpikeMultiplier;
    public BooleanSetting settingDrops;
    public NumberSetting settingDropDelay;
    public RangeSetting settingDropDuration;
    public NumberSetting settingDropMultiplier;
    public BooleanSetting settingReduceHitsInAir;
    public RangeSetting settingReduceRecalculateDelay;
    public RangeSetting settingReduceMultiplier;
    public BooleanSetting settingReduceHitsOnWall;
    public RangeSetting settingReduceRecalculateDelayOnWall;
    public RangeSetting settingReduceMultiplierOnWall;
    public MultiSelectSetting settingHurttimesToHit;
    public ModeSetting settingClickPattern;
    public ModeSetting settingRecordedMode;
    public NumberSetting settingMultiplier;
    public NumberSetting settingRecordedAirMultiplier;
    public BooleanSetting settingPlayRecordedMouseMovements;
    public ModeSetting settingSmartMode;
    public ModeSetting settingSmartClickMode;
    public ModeSetting settingSmartDoubleClickModeS;
    public SettingGroup settingCPSSettings;
    public SettingGroup settingInAir;
    public RangeSetting settingSmartCPSInAir;
    public RangeSetting settingSmartCPSInAirRecalculateTickDelay;
    public RangeSetting settingSmartDCPSInAir;
    public RangeSetting settingDCPSInAirRecalculateTickDelay;
    public SettingGroup settingOnWall;
    public RangeSetting settingSmartCPSOnWall;
    public RangeSetting settingSmartCPSOnWallRecalculateTickDelay;
    public RangeSetting settingSmartDCPSOnWall;
    public RangeSetting settingDCPSOnWallRecalculateTickDelay;
    public SettingGroup settingOnFirstHit;
    public RangeSetting settingSmartCPSFirstHit;
    public RangeSetting settingSmartCPSFirstHitRecalculateTickDelay;
    public RangeSetting settingSmartDCPSFirstHit;
    public RangeSetting settingSmartDCPSFirstHitRecalculateTickDelay;
    public SettingGroup settingOnTarget;
    public RangeSetting settingSmartCPS;
    public RangeSetting settingCPSRecalculateTickDelay;
    public RangeSetting settingSmartDCPS;
    public RangeSetting settingDCPSRecalculateTickDelay;
    public ModeSetting settingTiming;
    public SettingGroup settingTimingValues;
    public RangeSetting settingPrediction;
    public RangeSetting settingLoseDelay;
    public RangeSetting settingEndurance;
    public RangeSetting settingEnduranceReduction;
    public RangeSetting settingDelayClicksAfterClosingGuiScreen;
    public BooleanSetting settingReduceCPSIfYouCanNotHitATargett;
    public BooleanSetting settingCritFix;
    public BooleanSetting settingOnlyOnTarget;
    public BooleanSetting settingPerfectHit;
    public BooleanSetting settingAttackFriends;
    public BooleanSetting settingLongClick;
    public BooleanSetting settingBreakBlocks;
    public NumberSetting settingBreakTickDelay;
    public BooleanSetting settingOnlyOnClick;
    public TargetFilterSetting auxiliarySettingAdapter;
    public long timestampMillis15;
    public long timestampMillis14;
    public long timestampMillis10;
    public boolean activeState;
    public int tickDelay1;
    public ClickPatternProfile clickPatternProfile;
    public ArrayList clickPatternProfiles;
    public EntityLivingBase targetEntity;
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
    public ClickHistoryBuffer auxiliaryArrayListEntry;
    public TimeHelper timer;
    public TimeHelper timer2;
    public TimeHelper timer3;
    public TimeHelper timer4;
    public TimeHelper timer5;
    public SmoothDamper smoothDamper3;
    public SmoothDamper smoothDamper4;
    public ArrayList itemList3;
    public AttackTimeTracker auxiliaryLongEntry;
    public AnimationTimer animationTimer9;
    public int tickDelay;
    public int tickDelay4;
    public int tickDelay3;
    public boolean activeState11;
    public boolean activeState13;
    public boolean visible2;
    public long timestampMillis9;
    public long timestampMillis6;
    public long timestampMillis12;
    public int tickDelay5;
    public long timestampMillis;
    public int[] intValueArray;
    public float[] scaleFactorArray;
    public double[] coordOffsetArray;
    public int[] intValueArray2;
    public boolean activeState7;
    public int tickDelay2;
    public boolean enabled2;
    public boolean activeState8;
    public boolean expanded2;
    public TimeHelper timer6;

    public AutoClicker() {
        super("AutoClicker", "SelbstständigerClicker", new Color(82, 186, 74), Category.LEGIT);
        String[] textArray = new String[6];
        textArray[0] = "Deviation";
        textArray[1] = "DragClick";
        textArray[2] = "Simple";
        textArray[3] = "Advanced";
        textArray[4] = "Recorded";
        textArray[5] = "Smart";
        this.settingMode = new ModeSetting(this, "Mode", "Smart", textArray);
        this.settingCPSLimiter = new NumberSetting(this, "CPSLimiter", 20.0, 5.0, 40.0, 0);
        this.settingCPSLimiterIntave = new BooleanSetting(this, "CPSLimiterIntave", false);
        SettingGroup settingGroup = new SettingGroup(this, "ModeValues", true);
        this.settingModeValues = settingGroup;
        RangeSetting rangeSetting = new RangeSetting(this, "SimpleCPS", 12.0, 16.0, 0.0, 40.0, 0, this.settingModeValues);
        String text = "CPS";
        this.settingSimpleCPS = rangeSetting.getRangeSetting(text).getRangeSetting(15343, () -> this.isSimpleCPSVisible());
        RangeSetting rangeSetting22 = new RangeSetting(this, "SimpleCPSRecalculateDelay", 3.0, 8.0, 0.0, 20.0, 0, this.settingModeValues)
        .getRangeSetting2();
        text = "CPSRecalculateDelay";
        this.settingSimpleCPSRecalculateDelay = rangeSetting22.getRangeSetting(text).getRangeSetting(15343, () -> this.isSimpleCPSRecalculateDelayVisible());
        ActionSetting actionSetting = new ActionSetting(this, "SimpleClickDutyCycle", 0.35, 0.65, 0.0, 1.0, 2, this.settingModeValues);
        text = "ClickDutyCycle";
        this.settingSimpleClickDutyCycle = actionSetting.getActionSetting(text).getActionSetting(() -> this.isSimpleClickDutyCycleVisible());
        BooleanSetting booleanSetting2 = new BooleanSetting(this, "SimpleDoubleClicks", true, this.settingModeValues);
        text = "DoubleClicks";
        Setting setting = booleanSetting2.getSetting(text);
        this.settingSimpleDoubleClicks = (BooleanSetting)(((BooleanSetting)setting).getSetting(() -> this.isSimpleDoubleClicksVisible()));
        BooleanSetting booleanSetting3 = new BooleanSetting(this, "SimpleExtraDoubleClickRandomize", false, this.settingModeValues);
        text = "ExtraDoubleClickRandomize";
        Setting setting3 = booleanSetting3.getSetting(text);
        this.settingSimpleExtraDoubleClickRandomize = (BooleanSetting)(((BooleanSetting)setting3).getSetting(() -> this.isSimpleExtraDoubleClickRandomizeVisible()));
        RangeSetting rangeSetting23 = new RangeSetting(this, "DeviationCPS", 13.0, 20.0, 0.0, 40.0, 0, this.settingModeValues).getRangeSetting2();
        text = "MaxCPS";
        this.settingDeviationCPS = rangeSetting23.getRangeSetting("MinCPS", text).getRangeSetting(15343, () -> this.isDeviationCPSVisible());
        BooleanSetting booleanSetting4 = new BooleanSetting(this, "DeviationDoubleClick", true, this.settingModeValues);
        text = "DoubleClick";
        Setting setting5 = booleanSetting4.getSetting(text);
        this.settingDeviationDoubleClick = (BooleanSetting)(((BooleanSetting)setting5).getSetting(() -> this.isDeviationDoubleClickVisible()));
        NumberSetting numberSetting2 = new NumberSetting(this, "DeviationAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingModeValues);
        text = "AirMultiplier";
        this.settingDeviationAirMultiplier = numberSetting2.getNumberSetting(text).getNumberSetting(() -> this.isDeviationAirMultiplierVisible());
        RangeSetting rangeSetting8 = new RangeSetting(this, "DragCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.settingModeValues);
        text = "CPSInAir";
        this.settingDragCPSInAir = rangeSetting8.getRangeSetting(text).getRangeSetting(15343, () -> this.isDragCPSInAirVisible());
        RangeSetting rangeSetting11 = new RangeSetting(this, "DragCPSInAirRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, this.settingModeValues);
        text = "CPSInAirRecalculateTickDelay";
        this.settingDragCPSInAirRecalculateTickDelay = rangeSetting11.getRangeSetting(text).getRangeSetting2().getRangeSetting(15343, () -> this.isDragCPSInAirRecalculateTickDelayVisible());
        this.settingMaxCPS = new RangeSetting(this, "MaxCPS", 18.0, 20.0, 1.0, 40.0, 0, this.settingModeValues).getRangeSetting(15343, () -> this.isMaxCPSVisible());
        this.settingMaxCPSRecalculateTickDelay = new RangeSetting(this, "MaxCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingModeValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isMaxCPSRecalculateTickDelayVisible());
        this.settingMinStandardDeviation = new NumberSetting(this, "MinStandardDeviation", 0.5, 0.0, 1.0, 2, this.settingModeValues).getNumberSetting(() -> this.isMinStandardDeviationVisible());
        RangeSetting rangeSetting26 = new RangeSetting(this, "AdvancedCPS", 20.0, 20.0, 0.0, 40.0, 0, this.settingModeValues).getRangeSetting2();
        text = "CPS";
        this.settingAdvancedCPS = rangeSetting26.getRangeSetting(text).getRangeSetting(15343, () -> this.isAdvancedCPSVisible());
        RangeSetting rangeSetting27 = new RangeSetting(this, "AdvancedCPSRecalculateDelay", 200.0, 600.0, 0.0, 4000.0, 0, this.settingModeValues)
        .getRangeSetting2();
        text = "CPSRecalculateDelay";
        this.settingAdvancedCPSRecalculateDelay = rangeSetting27.getRangeSetting(text).getRangeSetting(15343, () -> this.isAdvancedCPSRecalculateDelayVisible());
        this.settingSpikes = (BooleanSetting)new BooleanSetting(this, "Spikes", false, this.settingModeValues).getSetting(() -> this.isSpikesVisible());
        this.settingSpikeDelay = new NumberSetting(this, "SpikeDelay", 15000.0, 0.0, 20000.0, 0, this.settingModeValues).getNumberSetting(() -> this.isSpikeDelayVisible());
        this.settingSpikeDuration = new RangeSetting(this, "SpikeDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingModeValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isSpikeDurationVisible());
        this.settingSpikeMultiplier = new NumberSetting(this, "SpikeMultiplier", 1.5, 1.0, 3.0, 2, this.settingModeValues).getNumberSetting(() -> this.isSpikeMultiplierVisible());
        this.settingDrops = (BooleanSetting)new BooleanSetting(this, "Drops", false, this.settingModeValues).getSetting(() -> this.isDropsVisible());
        this.settingDropDelay = new NumberSetting(this, "DropDelay", 7000.0, 0.0, 20000.0, 0, this.settingModeValues).getNumberSetting(() -> this.isDropDelayVisible());
        this.settingDropDuration = new RangeSetting(this, "DropDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingModeValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isDropDurationVisible());
        this.settingDropMultiplier = new NumberSetting(this, "DropMultiplier", 0.5, 0.0, 1.0, 2, this.settingModeValues).getNumberSetting(() -> this.isDropMultiplierVisible());
        this.settingReduceHitsInAir = (BooleanSetting)new BooleanSetting(this, "ReduceHitsInAir", true, this.settingModeValues).getSetting(() -> this.isReduceHitsInAirVisible());
        this.settingReduceRecalculateDelay = new RangeSetting(this, "ReduceRecalculateDelay", 100.0, 200.0, 0.0, 1000.0, 0, this.settingModeValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isReduceRecalculateDelayVisible());
        this.settingReduceMultiplier = new RangeSetting(this, "ReduceMultiplier", 0.4, 0.4, 0.0, 1.0, 2, this.settingModeValues).getRangeSetting2().getRangeSetting(15343, () -> this.isReduceMultiplierVisible());
        this.settingReduceHitsOnWall = (BooleanSetting)new BooleanSetting(this, "ReduceHitsOnWall", false, this.settingModeValues).getSetting(() -> this.isReduceHitsOnWallVisible());
        this.settingReduceRecalculateDelayOnWall = new RangeSetting(this, "ReduceRecalculateDelayOnWall", 100.0, 200.0, 0.0, 1000.0, 0, this.settingModeValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isReduceRecalculateDelayOnWallVisible());
        this.settingReduceMultiplierOnWall = new RangeSetting(this, "ReduceMultiplierOnWall", 0.4, 0.4, 0.0, 1.0, 2, this.settingModeValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isReduceMultiplierOnWallVisible());
        AutoClickerHurtTime[] autoClickerHurtTimeArray = AutoClickerHurtTime.values();
        this.settingHurttimesToHit = new MultiSelectSetting(this, "HurttimesToHit", autoClickerHurtTimeArray, this.settingModeValues).getMultiSelectSetting(() -> this.isHurttimesToHitVisible());
        String[] textArray2 = new String[0];
        this.settingClickPattern = new ModeSetting(this, "ClickPattern", "", textArray2, this.settingModeValues).getModeSetting(() -> this.isClickPatternVisible());
        String[] textArray3 = new String[2];
        textArray3[0] = "TimeMillis";
        textArray3[1] = "Ticks";
        this.settingRecordedMode = new ModeSetting(this, "RecordedMode", "TimeMillis", textArray3, this.settingModeValues).getModeSetting(() -> this.isRecordedModeVisible());
        this.settingMultiplier = new NumberSetting(this, "Multiplier", 1.0, 0.0, 5.0, 2, this.settingModeValues).getNumberSetting(() -> this.isMultiplierVisible());
        NumberSetting numberSetting17 = new NumberSetting(this, "RecordedAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingModeValues);
        text = "AirMultiplier";
        this.settingRecordedAirMultiplier = numberSetting17.getNumberSetting(text).getNumberSetting(() -> this.isRecordedAirMultiplierVisible());
        this.settingPlayRecordedMouseMovements = (BooleanSetting)new BooleanSetting(this, "PlayRecordedMouseMovements", false, this.settingModeValues).getSetting(() -> this.isPlayRecordedMouseMovementsVisible());
        String[] textArray4 = new String[2];
        textArray4[0] = "Recommended";
        textArray4[1] = "Overengineered";
        ModeSetting modeSetting6 = new ModeSetting(this, "SmartMode", "Recommended", textArray4, this.settingModeValues);
        text = "Mode";
        this.settingSmartMode = modeSetting6.getModeSetting(text).getModeSetting(() -> this.isSmartModeVisible());
        String[] textArray5 = new String[3];
        textArray5[0] = "Combined";
        textArray5[1] = "Default";
        textArray5[2] = "Separate";
        ModeSetting modeSetting9 = new ModeSetting(this, "SmartClickMode", "Combined", textArray5, this.settingModeValues);
        text = "ClickMode";
        this.settingSmartClickMode = modeSetting9.getModeSetting(text).getModeSetting(() -> this.isSmartClickModeVisible());
        String[] textArray6 = new String[3];
        textArray6[0] = "Combined";
        textArray6[1] = "Default";
        textArray6[2] = "Separate";
        ModeSetting modeSetting12 = new ModeSetting(this, "SmartDoubleClickModeS", "Default", textArray6, this.settingModeValues);
        text = "DoubleClickMode";
        this.settingSmartDoubleClickModeS = modeSetting12.getModeSetting(text).getModeSetting(() -> this.isSmartDoubleClickModeSVisible());
        this.settingCPSSettings = new SettingGroup(this, "CPS Settings", true, this.settingModeValues).getSettingGroup(() -> this.isCPSSettingsVisible());
        this.settingInAir = new SettingGroup(this, "In Air", true, this.settingCPSSettings).getSettingGroup(() -> this.isInAirVisible());
        RangeSetting rangeSetting34 = new RangeSetting(this, "SmartCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.settingInAir);
        text = "CPSInAir";
        this.settingSmartCPSInAir = rangeSetting34.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartCPSInAirVisible());
        RangeSetting rangeSetting37 = new RangeSetting(this, "SmartCPSInAirRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.settingInAir);
        text = "CPSInAirRecalculateTickDelay";
        this.settingSmartCPSInAirRecalculateTickDelay = rangeSetting37.getRangeSetting(text).getRangeSetting2().getRangeSetting(15343, () -> this.isSmartCPSInAirRecalculateTickDelayVisible());
        RangeSetting rangeSetting39 = new RangeSetting(this, "SmartDCPSInAir", 0.0, 0.0, 0.0, 20.0, 0, this.settingInAir);
        text = "DoubleClicksPerSecondInAir";
        this.settingSmartDCPSInAir = rangeSetting39.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartDCPSInAirVisible());
        this.settingDCPSInAirRecalculateTickDelay = new RangeSetting(this, "DCPSInAirRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingInAir)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isDCPSInAirRecalculateTickDelayVisible());
        this.settingOnWall = new SettingGroup(this, "On wall", true, this.settingCPSSettings).getSettingGroup(() -> this.isOnWallVisible());
        RangeSetting rangeSetting43 = new RangeSetting(this, "SmartCPSOnWall", 8.0, 12.0, 0.0, 20.0, 0, this.settingOnWall);
        text = "CPSOnWall";
        this.settingSmartCPSOnWall = rangeSetting43.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartCPSOnWallVisible());
        RangeSetting rangeSetting46 = new RangeSetting(this, "SmartCPSOnWallRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.settingOnWall);
        text = "CPSOnWallRecalculateTickDelay";
        this.settingSmartCPSOnWallRecalculateTickDelay = rangeSetting46.getRangeSetting(text).getRangeSetting2().getRangeSetting(15343, () -> this.isSmartCPSOnWallRecalculateTickDelayVisible());
        RangeSetting rangeSetting48 = new RangeSetting(this, "SmartDCPSOnWall", 2.0, 4.0, 0.0, 20.0, 0, this.settingOnWall);
        text = "DCPSOnWall";
        this.settingSmartDCPSOnWall = rangeSetting48.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartDCPSOnWallVisible());
        this.settingDCPSOnWallRecalculateTickDelay = new RangeSetting(this, "DCPSOnWallRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnWall)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isDCPSOnWallRecalculateTickDelayVisible());
        this.settingOnFirstHit = new SettingGroup(this, "On first Hit", true, this.settingCPSSettings).getSettingGroup(() -> this.isOnFirstHitVisible());
        RangeSetting rangeSetting52 = new RangeSetting(this, "SmartCPSFirstHit", 20.0, 20.0, 0.0, 40.0, 0, this.settingOnFirstHit);
        text = "CPSFirstHit";
        this.settingSmartCPSFirstHit = rangeSetting52.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartCPSFirstHitVisible());
        RangeSetting rangeSetting55 = new RangeSetting(
        this, "SmartCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 0.0, 60.0, 0, this.settingOnFirstHit
        );
        text = "CPSFirstHitRecalculateTickDelay";
        this.settingSmartCPSFirstHitRecalculateTickDelay = rangeSetting55.getRangeSetting(text).getRangeSetting2().getRangeSetting(15343, () -> this.isSmartCPSFirstHitRecalculateTickDelayVisible());
        RangeSetting rangeSetting57 = new RangeSetting(this, "SmartDCPSFirstHit", 4.0, 5.0, 0.0, 20.0, 0, this.settingOnFirstHit);
        text = "DoubleClicksPerSecondFirstHit";
        this.settingSmartDCPSFirstHit = rangeSetting57.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartDCPSFirstHitVisible());
        RangeSetting rangeSetting60 = new RangeSetting(
        this, "SmartDCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnFirstHit
        );
        text = "DCPSFirstHitRecalculateTickDelay";
        this.settingSmartDCPSFirstHitRecalculateTickDelay = rangeSetting60.getRangeSetting(text).getRangeSetting2().getRangeSetting(15343, () -> this.isSmartDCPSFirstHitRecalculateTickDelayVisible());
        this.settingOnTarget = new SettingGroup(this, "On Target", true, this.settingCPSSettings).getSettingGroup(() -> this.isOnTargetVisible());
        RangeSetting rangeSetting62 = new RangeSetting(this, "SmartCPS", 17.0, 19.0, 1.0, 40.0, 0, this.settingOnTarget);
        text = "CPS";
        this.settingSmartCPS = rangeSetting62.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartCPSVisible());
        this.settingCPSRecalculateTickDelay = new RangeSetting(this, "CPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnTarget)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isCPSRecalculateTickDelayVisible());
        RangeSetting rangeSetting66 = new RangeSetting(this, "SmartDCPS", 4.0, 5.0, 0.0, 20.0, 0, this.settingOnTarget);
        text = "DoubleClicksPerSecond";
        this.settingSmartDCPS = rangeSetting66.getRangeSetting(text).getRangeSetting(15343, () -> this.isSmartDCPSVisible());
        this.settingDCPSRecalculateTickDelay = new RangeSetting(this, "DCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnTarget)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isDCPSRecalculateTickDelayVisible());
        String[] textArray7 = new String[2];
        textArray7[0] = "Always";
        textArray7[1] = "Other";
        this.settingTiming = new ModeSetting(this, "Timing", "Always", textArray7);
        this.settingTimingValues = new SettingGroup(this, "TimingValues", true).getSettingGroup(() -> this.isTimingValuesVisible());
        this.settingPrediction = new RangeSetting(this, "Prediction", 3.0, 5.0, 1.0, 10.0, 0, this.settingTimingValues).getRangeSetting2().getRangeSetting(15343, () -> this.isPredictionVisible());
        this.settingLoseDelay = new RangeSetting(this, "LoseDelay", 3.0, 5.0, 1.0, 10.0, 0, this.settingTimingValues).getRangeSetting2().getRangeSetting(15343, () -> this.isLoseDelayVisible());
        this.settingEndurance = new RangeSetting(this, "Endurance", 90.0, 110.0, 1.0, 400.0, 0, this.settingTimingValues).getRangeSetting2().getRangeSetting(15343, () -> this.isEnduranceVisible());
        this.settingEnduranceReduction = new RangeSetting(this, "EnduranceReduction", 5.0, 10.0, 1.0, 20.0, 0, this.settingTimingValues)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isEnduranceReductionVisible());
        this.settingDelayClicksAfterClosingGuiScreen = new RangeSetting(this, "DelayClicksAfterClosingGuiScreen", 3.0, 5.0, 1.0, 20.0, 0)
        .getRangeSetting2();
        BooleanSetting booleanSetting10 = new BooleanSetting(this, "ReduceCPSIfYouCanNotHitATargett", false);
        text = "ReduceCPSIfYouCanNotHitATarget";
        this.settingReduceCPSIfYouCanNotHitATargett = (BooleanSetting)booleanSetting10.getSetting(text);
        this.settingCritFix = new BooleanSetting(this, "CritFix", true);
        this.settingOnlyOnTarget = new BooleanSetting(this, "OnlyOnTarget", false);
        this.settingPerfectHit = new BooleanSetting(this, "PerfectHit", false);
        this.settingAttackFriends = new BooleanSetting(this, "AttackFriends", false);
        this.settingLongClick = (BooleanSetting)new BooleanSetting(this, "LongClick", false).getSetting(() -> this.isDelayClicksAfterClosingGuiScreenVisible());
        this.settingBreakBlocks = new BooleanSetting(this, "BreakBlocks", false);
        this.settingBreakTickDelay = new NumberSetting(this, "BreakTickDelay", 5.0, 0.0, 20.0, 0).getNumberSetting(() -> this.settingBreakBlocks.isEnabled());
        this.settingOnlyOnClick = new BooleanSetting(this, "OnlyOnClick", true);
        this.auxiliarySettingAdapter = new TargetFilterSetting(this);
        this.timestampMillis15 = 0L;
        this.timestampMillis14 = 0L;
        this.timestampMillis10 = 0L;
        this.activeState = false;
        this.tickDelay1 = 0;
        this.clickPatternProfile = (ClickPatternProfile)null;
        this.clickPatternProfiles = new ArrayList();
        this.targetEntity = (EntityLivingBase)null;
        this.animationTimerArray = new AnimationTimer[22];
        this.smoothDamperArray = new SmoothDamper[28];
        this.rangeSettingArray = new RangeSetting[44];
        AnimationTimer animationTimer10 = new AnimationTimer();
        this.animationTimer = animationTimer10;
        AnimationTimer animationTimer11 = new AnimationTimer();
        this.animationTimer2 = animationTimer11;
        this.animationTimer3 = new AnimationTimer();
        this.animationTimer4 = new AnimationTimer();
        this.animationTimer5 = new AnimationTimer();
        this.animationTimer6 = new AnimationTimer();
        this.animationTimer7 = new AnimationTimer();
        this.animationTimer8 = new AnimationTimer();
        SmoothDamper smoothDamper5 = new SmoothDamper(20, 0.0);
        this.smoothDamper = smoothDamper5;
        SmoothDamper smoothDamper6 = new SmoothDamper(20, 5.0);
        this.smoothDamper2 = smoothDamper6;
        this.auxiliaryArrayListEntry = new ClickHistoryBuffer(60);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.timer3 = new TimeHelper();
        this.timer4 = new TimeHelper();
        this.timer5 = new TimeHelper();
        SmoothDamper smoothDamper7 = new SmoothDamper(20, 0.0);
        this.smoothDamper3 = smoothDamper7;
        SmoothDamper smoothDamper8 = new SmoothDamper(20, 0.0);
        this.smoothDamper4 = smoothDamper8;
        this.itemList3 = new ArrayList();
        AttackTimeTracker auxiliaryLongEntry2 = new AttackTimeTracker();
        this.auxiliaryLongEntry = auxiliaryLongEntry2;
        this.animationTimer9 = new AnimationTimer();
        this.tickDelay = 0;
        this.tickDelay4 = 0;
        this.tickDelay3 = 0;
        this.activeState11 = false;
        this.activeState13 = false;
        this.visible2 = false;
        this.timestampMillis9 = 2000L;
        this.timestampMillis6 = 2000L;
        this.timestampMillis12 = 0L;
        this.tickDelay5 = 0;
        this.timestampMillis = 0L;
        this.intValueArray = new int[2];
        this.scaleFactorArray = new float[2];
        this.coordOffsetArray = new double[2];
        this.intValueArray2 = new int[2];
        this.activeState7 = false;
        this.tickDelay2 = 0;
        this.enabled2 = false;
        this.activeState8 = false;
        this.expanded2 = false;
        this.timer6 = new TimeHelper();
        this.settingClickPattern.getModeSetting(() -> this.updateState());
        this.updateState3();
    }

    @Override
    public void onEnable() {
        this.registerEvent(7);
        this.registerEvent(17);
        this.registerEvent(19);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
        this.tickDelay2 = 0;
        this.enabled2 = false;
    }

    @Override
    public void onPreDisable() {
        this.clickPatternProfiles = ClickPatternManager.getArrayList();
        ArrayList settings22 = this.clickPatternProfiles;
        String[] textArray = ClickPatternManager.getTextArray(settings22);
        this.settingClickPattern.applyStringArray2(textArray);
        String selected = this.settingClickPattern.getSelected();
        ArrayList settings23 = this.clickPatternProfiles;
        String text = ClickPatternManager.getText(selected, settings23);
        this.settingClickPattern.setSelected(text);
        String selected2 = this.settingClickPattern.getSelected();
        ArrayList settings24 = this.clickPatternProfiles;
        this.clickPatternProfile = ClickPatternManager.getClickPatternProfile(selected2, settings24);
        this.timestampMillis15 = System.currentTimeMillis();
        this.tickDelay3 = 0;
        this.activeState11 = false;
        this.activeState13 = true;
        this.timestampMillis = System.currentTimeMillis();
        this.timestampMillis12 = System.currentTimeMillis();
        this.settingDelayClicksAfterClosingGuiScreen.recalculate();
        this.activeState7 = true;
    }

    @Override
    public void onClick(EventClick event) {
        this.targetEntity = this.getEntityLivingBase();
        this.visible2 = minecraft.gameSettings.keyBindAttack.isKeyDown();
        this.activeState7 = true;
        long time = System.currentTimeMillis();
        this.timestampMillis15 = time - (long)SettingContainer.settingProvider.getInt2(0, 45);
        this.timestampMillis10 = time;
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        this.timestampMillis = System.currentTimeMillis();
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onPostTick() {
        this.intValueArray[0] = this.intValueArray[1] = 0;
        this.tickDelay5 = (int)Math.abs(this.timestampMillis12 - System.currentTimeMillis());
        this.timestampMillis12 = System.currentTimeMillis();
        this.intValueArray2[0] = this.intValueArray2[1] = 0;
        this.coordOffsetArray[0] = this.coordOffsetArray[1] = 0.0;
    }

    private boolean isSmartCPSHurtVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isCPSRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isReduceRecalculateDelayVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean checkCondition(double offset, int index2) {
        if (!this.settingTiming.getSelected().equals("Other")) {
            return true;
        } else {
            int tickDelay22 = this.tickDelay2;
            int indexOffset2 = this.settingEndurance.getIndexOffset2();
            if (tickDelay22 <= indexOffset2 && !this.enabled2) {
                if (minecraft.indexOffset == 1) {
                    this.settingLoseDelay.recalculate();
                    this.settingPrediction.recalculate();
                }

                if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                    return true;
                } else {
                    if (minecraft.rightClickDelayTimer > this.settingLoseDelay.getIndexOffset2()) {
                        if (this.tickDelay2 <= this.settingEndurance.getIndexOffset2() / 2) {
                            MovingObjectPosition movingObjectPosition2 = RayTraceUtils.getMovingObjectPosition2(
                            1.0F, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch, 10.0, 6.0, 1.0
                            );
                            if (movingObjectPosition2 != null && movingObjectPosition2.entityHit instanceof EntityLivingBase) {
                                EntityLivingBase livingEntity = (EntityLivingBase)movingObjectPosition2.entityHit;

                                for (int i = 1; i < index2; i++) {
                                    EntityLivingBase livingEntity2 = (EntityLivingBase)movingObjectPosition2.entityHit;
                                    Vec3 vec2 = CombatTargetFilter.getVec3(livingEntity2, i);
                                    AxisAlignedBB axisAlignedBB = SimulatedPlayer.getAxisAlignedBB(livingEntity, vec2);
                                    int index = i - 1;
                                    double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(index, minecraft.thePlayer);
                                    double offset2 = coordOffsetArray2[0];
                                    double offset3 = coordOffsetArray2[1];
                                    Vec3 vec3 = new Vec3(offset2, offset3 + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray2[2]);
                                    Vec3 vec = SimulatedPlayer.processAction(vec3, axisAlignedBB);
                                    if (vec3.getCoordinate4(vec) < offset) {
                                        return true;
                                    }
                                }

                                return false;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                if (!this.enabled2) {
                    this.settingEndurance.recalculate();
                }

                this.enabled2 = true;
                return false;
            }
        }
    }

    private void updateState3() {
        int index3 = 0;

        while (index3 < this.animationTimerArray.length) {
            this.animationTimerArray[index3] = new AnimationTimer();
            index3++;
        }

        int index4 = 0;

        for (int i = 0; i < this.smoothDamperArray.length; i = ++index4) {
            this.smoothDamperArray[index4] = new SmoothDamper(20, (double)index4 * 1.2 % 20.0);
        }

        int index = 0;

        for (int i2 = 0; i2 < 11; i2 = ++index) {
            SettingGroup settingGroup = new SettingGroup(this, "HurtTime " + index, this.settingCPSSettings)
            .getSettingGroup(() -> this.isHurtTimeVisible());
            this.rangeSettingArray[index * 4] = new RangeSetting(this, "SmartCPSHurt" + index, 17.0, 19.0, 1.0, 40.0, 0, settingGroup)
            .getRangeSetting("CPS")
            .getRangeSetting(15343, () -> this.isSmartCPSHurtVisible());
            this.rangeSettingArray[index * 4 + 1] = new RangeSetting(
            this, "Hurt" + index + "SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, settingGroup
            )
            .getRangeSetting("CPSRecalculateTickDelay")
            .getRangeSetting2()
            .getRangeSetting(15343, () -> this.isHurtVisible());
            this.rangeSettingArray[index * 4 + 2] = new RangeSetting(this, "SmartDCPSHurt" + index, 4.0, 5.0, 0.0, 20.0, 0, settingGroup)
            .getRangeSetting("DoubleClicksPerSecond")
            .getRangeSetting(15343, () -> this.isSmartDCPSHurtVisible());
            this.rangeSettingArray[index * 4 + 3] = new RangeSetting(
            this, "DCPSRecalculateTickDelayHurt" + index, 3.0, 8.0, 1.0, 40.0, 0, settingGroup
            )
            .getRangeSetting("DoubleClicksPerSecondRecalculateTickDelay")
            .getRangeSetting2()
            .getRangeSetting(15343, () -> this.isDCPSRecalculateTickDelayHurtVisible());
        }
    }

    private SmoothDamper getSmoothDamper(int index) {
        int state = (byte)0;
        String selected = this.settingSmartClickMode.getSelected();
        state = -1;
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

    private int getInt2(int index) {
        return (this.checkCondition() ? 1 : 0);
    }

    private boolean isSimpleClickDutyCycleVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private SmoothDamper getSmoothDamper2(int index) {
        String selected = this.settingSmartDoubleClickModeS.getSelected();
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

    private void updateState() {
        String selected = this.settingClickPattern.getSelected();
        ArrayList settings22 = this.clickPatternProfiles;
        this.clickPatternProfile = ClickPatternManager.getClickPatternProfile(selected, settings22);
    }

    private boolean isSmartDCPSVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private void onClick2(EventClick event, int index) {
        if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
            this.tickDelay3 = 0;
        }

        if (this.canExecute6() && index > 0) {
            this.activeState11 = true;
            event.setState(true);
        }

        minecraft.gameSettings.keyBindAttack.setPressTime(0);
        event.updateInt(index);
    }

    private Pair getPair() {
        int intEntry = this.clickPatternProfile.getInt();
        this.clickPatternProfile.getInt();
        int state = ((byte)(this.canExecute10() ? 1 : 0));
        return new Pair(intEntry + state, intEntry);
    }

    private int getInt3(int index) {
        return this.rangeSettingArray[index].getIndexOffset2();
    }

    private void processAction() {
        this.settingSmartMode.getSelected().equals("Recommended");
    }

    private int getInt4() {
        String selected = this.settingMode.getSelected();
switch (selected) {
            case "DragClick":
                MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
                int index2 = 0;
                if (this.itemList3.size() < 5) {
                    index2 = 1;
                } else {
                    AnimationTimer animationTimer72 = this.animationTimer7;
                    int indexOffset2 = this.settingMaxCPSRecalculateTickDelay.getIndexOffset2();
                    if (animationTimer72.checkCondition(indexOffset2)) {
                        this.animationTimer7.processAction3();
                        this.settingMaxCPSRecalculateTickDelay.getDouble4();
                        this.settingMaxCPS.getDouble4();
                    }

                    ArrayList itemList32 = this.itemList3;
                    double value = this.settingMinStandardDeviation.getValue();
                    double indexOffset218 = (double)this.settingMaxCPS.getIndexOffset2();
                    index2 = RuntimeBridge.calculateClickDelay(itemList32, 21, value, indexOffset218);
                }
                if (ClientGlobals.moduleManager.smartClicking.canExecute5()) {
                    index2 = 0;
                }
                {
                    boolean completed3 = false;
                    if (objectMouseOver != null) {
                        MovingObjectType typeOfHit = objectMouseOver.typeOfHit;
                        MovingObjectType movingObjectTypeKind = MovingObjectType.ENTITY;
                        if (typeOfHit == movingObjectTypeKind) {
                            if (index2 == 0 && this.settingCritFix.isEnabled() && minecraft.objectMouseOver != null) {
                                boolean enabled4 = false;
                                boolean completed2 = false;
                                boolean completed = false;
                                if (!ClientGlobals.moduleManager.criticals.isToggled()) {
                                    float fallDistance = minecraft.thePlayer.fallDistance;
                                    if ((!Float.isNaN(fallDistance) && !Float.isNaN(0.0F) ? (fallDistance > 0.0F ? 1 : (fallDistance == 0.0F ? 0 : -1)) : -1) > 0
                                    && !minecraft.thePlayer.onGround
                                    && !minecraft.thePlayer.canExecute9()
                                    && !minecraft.thePlayer.isInWater()) {
                                        Potion potion16 = Potion.potion16;
                                        if (!minecraft.thePlayer.checkPotion(potion16)
                                        && minecraft.thePlayer.ridingEntity == null
                                        && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
                                            completed = true;
                                        }
                                    }
                                    if (!completed) {
                                        enabled4 = false;
                                        completed2 = true;
                                    }
                                }
                                if (!completed2) {
                                    enabled4 = true;
                                }


                                if (!this.activeState11 && enabled4) {
                                    index2 = 1;
                                }
                            }
                            completed3 = true;
                        }
                    }
                    if (!completed3) {
                        AnimationTimer animationTimer10 = this.animationTimer;
                        int indexOffset22 = this.settingDragCPSInAirRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer10.checkCondition(indexOffset22)) {
                            this.animationTimer.processAction3();
                            this.settingDragCPSInAirRecalculateTickDelay.getDouble4();
                            this.settingDragCPSInAir.getDouble4();
                        }
                        SmoothDamper smoothDamper42 = this.smoothDamper4;
                        int indexOffset23 = this.settingDragCPSInAir.getIndexOffset2();
                        index2 = smoothDamper42.getInt(indexOffset23);
                    }

                }
                ArrayList itemList33 = this.itemList3;
                Integer integer = index2;
                itemList33.add(integer);
                while (this.itemList3.size() > 21) {
                    this.itemList3.remove(0);
                }
                return index2;
            case "Advanced":
                TimeHelper timer7 = this.timer;
                long time2 = (long)this.settingAdvancedCPSRecalculateDelay.getIndexOffset2();
                if (timer7.hasReached(time2)) {
                    this.settingAdvancedCPS.getDouble4();
                    this.settingAdvancedCPSRecalculateDelay.recalculate();
                    this.timer.reset();
                }
                int indexOffset24 = this.settingAdvancedCPS.getIndexOffset2();
                if (this.settingSpikes.isEnabled()) {
                    int spikeDelay = (int)this.settingSpikeDelay.getValue();
                    TimeHelper timer32 = this.timer3;
                    long timestampMillis92 = this.timestampMillis9;
                    if (timer32.hasReached(timestampMillis92)) {
                        double indexOffset219 = (double)this.settingAdvancedCPS.getIndexOffset2();
                        double value2 = this.settingSpikeMultiplier.getValue();
                        indexOffset24 = (int)Math.round(indexOffset219 * value2);
                        TimeHelper timer33 = this.timer3;
                        long timestampMillis93 = this.timestampMillis9;
                        int spikeDuration = (int)this.settingSpikeDuration.getDouble9();
                        int spikeDuration2 = (int)this.settingSpikeDuration.getDouble2();
                        long time3 = (long)SettingContainer.settingProvider.getInt3(spikeDuration, spikeDuration2);
                        long time4 = timestampMillis93 + time3;
                        if (timer33.hasReached(time4)) {
                            int index3 = spikeDelay - 100;
                            int index4 = Math.max(0, index3);
                            int index5 = spikeDelay + 100;
                            long time5 = (long)SettingContainer.settingProvider.getInt3(index4, index5);
                            this.timestampMillis9 = time5;
                            this.timer3.reset();
                        }
                    }
                }
                if (this.settingDrops.isEnabled()) {
                    int dropDelay = (int)this.settingDropDelay.getValue();
                    TimeHelper timer22 = this.timer2;
                    long timestampMillis62 = this.timestampMillis6;
                    if (timer22.hasReached(timestampMillis62)) {
                        double indexOffset220 = (double)this.settingAdvancedCPS.getIndexOffset2();
                        double value3 = this.settingDropMultiplier.getValue();
                        indexOffset24 = (int)Math.round(indexOffset220 * value3);
                        TimeHelper timer23 = this.timer2;
                        long timestampMillis63 = this.timestampMillis6;
                        int dropDuration = (int)this.settingDropDuration.getDouble9();
                        int dropDuration2 = (int)this.settingDropDuration.getDouble2();
                        long time6 = (long)SettingContainer.settingProvider.getInt3(dropDuration, dropDuration2);
                        long time7 = timestampMillis63 + time6;
                        if (timer23.hasReached(time7)) {
                            int index6 = dropDelay - 100;
                            int index7 = Math.max(0, index6);
                            int index8 = dropDelay + 100;
                            long time8 = (long)SettingContainer.settingProvider.getInt3(index7, index8);
                            this.timestampMillis6 = time8;
                            this.timer2.reset();
                        }
                    }
                }
                if (this.settingReduceHitsInAir.isEnabled() && minecraft.objectMouseOver != null) {
                    MovingObjectType typeOfHit2 = minecraft.objectMouseOver.typeOfHit;
                    MovingObjectType movingObjectTypeKind2 = MovingObjectType.MISS;
                    if (typeOfHit2 == movingObjectTypeKind2) {
                        TimeHelper timer42 = this.timer4;
                        long time9 = (long)this.settingReduceRecalculateDelay.getIndexOffset2();
                        if (timer42.hasReached(time9)) {
                            this.settingReduceRecalculateDelay.recalculate();
                            this.settingReduceMultiplier.recalculate();
                            this.timer4.reset();
                        }

                        double indexOffset221 = (double)this.settingAdvancedCPS.getIndexOffset2();
                        double double3 = this.settingReduceMultiplier.getDouble3();
                        indexOffset24 = (int)Math.round(indexOffset221 * double3);
                    }
                }
                if (this.settingReduceHitsOnWall.isEnabled() && minecraft.objectMouseOver != null) {
                    MovingObjectType typeOfHit3 = minecraft.objectMouseOver.typeOfHit;
                    MovingObjectType movingObjectTypeKind3 = MovingObjectType.BLOCK;
                    if (typeOfHit3 == movingObjectTypeKind3) {
                        TimeHelper timer52 = this.timer5;
                        long time10 = (long)this.settingReduceRecalculateDelayOnWall.getIndexOffset2();
                        if (timer52.hasReached(time10)) {
                            this.settingReduceRecalculateDelayOnWall.recalculate();
                            this.settingReduceMultiplierOnWall.recalculate();
                            this.timer5.reset();
                        }

                        double indexOffset222 = (double)this.settingAdvancedCPS.getIndexOffset2();
                        double double32 = this.settingReduceMultiplierOnWall.getDouble3();
                        indexOffset24 = (int)Math.round(indexOffset222 * double32);
                    }
                }
                if (ClientGlobals.moduleManager.smartClicking.canExecute5()) {
                    return 0;
                } else {
                    int index9;
                    if (this.targetEntity != null) {
                        index9 = this.targetEntity.hurtTime;
                    } else {
                        index9 = 11;
                    }

                    AutoClickerHurtTime[] autoClickerHurtTimeArray = AutoClickerHurtTime.values();
                    int length = Array.getLength(autoClickerHurtTimeArray);

                    for (int i = 0; i < length; i++) {
                        Object object = autoClickerHurtTimeArray[i];
                        if (AutoClickerHurtTime.getIndex((AutoClickerHurtTime)object) == index9
                        && !AutoClickerHurtTime.checkAutoClickerHurtTime((AutoClickerHurtTime)object)) {
                            return 0;
                        }
                    }

                    int intEntry = this.smoothDamper3.getInt(indexOffset24);
                    return this.getInt2(intEntry);
                }
            case "Deviation":
                float deviationCPS = (float)this.settingDeviationCPS.getDouble9();
                float deviationCPS2 = (float)this.settingDeviationCPS.getDouble2();
                float value5 = (deviationCPS + deviationCPS2) / 2.0F;
                float value6 = (deviationCPS2 - deviationCPS) / 6.0F;
                long time11 = System.currentTimeMillis();
                int index = 0;
                while (true) {
                    long timestampMillis152 = this.timestampMillis15;
                    if ((timestampMillis152 > time11 ? 1 : (timestampMillis152 == time11 ? 0 : -1)) >= 0) {
                        boolean enabled = this.settingDeviationDoubleClick.isEnabled();
                        if (!enabled && index > 1) {
                            index = 1;
                        }

                        return this.getInt2(index);
                    }

                    double offset;
                    double offset2 = (double)value5;
                    double offset3 = (double)value6;
                    double offset4 = new SecureRandom().nextGaussian();
                    double offset5 = offset3 * offset4;
                    float value7 = (float)(offset2 + offset5);
                    int index10 = (int)(1000.0F / value7);
                    offset = (double)index10;
                    if (!((minecraft.objectMouseOver != null) && ((minecraft.objectMouseOver.typeOfHit) == (MovingObjectType.ENTITY)))) {
                        double value4 = this.settingDeviationAirMultiplier.getValue();
                        offset /= value4;
                    }

                    long timestampMillis153 = this.timestampMillis15;
                    long time12 = Math.min((long)offset, 1000L);
                    long time13 = timestampMillis153 + time12;
                    this.timestampMillis15 = time13;
                    index++;
                }
            case "Recorded":
                if (this.clickPatternProfile == null) {
                    return 0;
                }
                byte state;
                if (this.settingRecordedMode.getSelected().equals("Ticks")) {
                    state = 0;

                    while (true) {
                        long timestampMillis102 = this.timestampMillis10;
                        long timestampMillis154 = this.timestampMillis15;
                        if ((timestampMillis102 > timestampMillis154 ? 1 : (timestampMillis102 == timestampMillis154 ? 0 : -1)) < 0) {
                            long timestampMillis142 = this.timestampMillis14;
                            long timestampMillis103 = this.timestampMillis10;
                            boolean enabled5;
                            if ((timestampMillis142 > timestampMillis103 ? 1 : (timestampMillis142 == timestampMillis103 ? 0 : -1)) > 0) {
                                enabled5 = true;
                            } else {
                                enabled5 = false;
                            }

                            this.activeState = enabled5 & true;
                            long time14 = this.timestampMillis10 + 50L;
                            this.timestampMillis10 = time14;
                            break;
                        }

                        state += 1;
                        Pair pair = this.getPair();
                        int second = (Integer)pair.getSecond();
                        this.timestampMillis14 = this.timestampMillis15;
                        long timestampMillis143 = this.timestampMillis14;
                        long time15 = (long)((Integer)pair.getFirst()).intValue();
                        this.timestampMillis14 = timestampMillis143 + time15;
                        long timestampMillis156 = this.timestampMillis15;
                        long time17 = (long)second;
                        this.timestampMillis15 = timestampMillis156 + time17;
                    }
                } else {
                    long time19 = System.currentTimeMillis();
                    state = 0;

                    while (true) {
                        long timestampMillis157 = this.timestampMillis15;
                        if ((time19 > timestampMillis157 ? 1 : (time19 == timestampMillis157 ? 0 : -1)) < 0) {
                            long timestampMillis144 = this.timestampMillis14;
                            boolean enabled6;
                            if ((timestampMillis144 > time19 ? 1 : (timestampMillis144 == time19 ? 0 : -1)) > 0) {
                                enabled6 = true;
                            } else {
                                enabled6 = false;
                            }

                            this.activeState = enabled6 & true;
                            break;
                        }

                        state += 1;
                        Pair pair2 = this.getPair();
                        int second2 = (Integer)pair2.getSecond();
                        this.timestampMillis14 = this.timestampMillis15;
                        long timestampMillis145 = this.timestampMillis14;
                        long time20 = (long)((Integer)pair2.getFirst()).intValue();
                        this.timestampMillis14 = timestampMillis145 + time20;
                        long timestampMillis159 = this.timestampMillis15;
                        long time22 = (long)second2;
                        this.timestampMillis15 = timestampMillis159 + time22;
                    }
                }
                this.clickPatternProfile.updateState();
                float[] floatArray = this.clickPatternProfile.getFloatArray();
                float value8 = floatArray[0];
                float value9 = floatArray[1];
                float value10 = this.scaleFactorArray[0];
                float value11 = this.scaleFactorArray[1];
                this.intValueArray = SimulatedPlayer.getIntArray(value8, value9, value10, value11);
                float[] scaleFactorArray2 = this.scaleFactorArray;
                float value12 = floatArray[0];
                scaleFactorArray2[0] = value12;
                float[] scaleFactorArray3 = this.scaleFactorArray;
                float value13 = floatArray[1];
                scaleFactorArray3[1] = value13;
                int int2 = this.getInt2(state);
                if (ClientGlobals.moduleManager.smartClicking.canExecute5() || int2 != state) {
                    this.activeState = false;
                    int2 = 0;
                }
                return int2;
            case "Simple":
                AnimationTimer animationTimer92 = this.animationTimer9;
                int indexOffset25 = this.settingSimpleCPSRecalculateDelay.getIndexOffset2();
                if (animationTimer92.checkCondition(indexOffset25)) {
                    this.settingSimpleCPSRecalculateDelay.recalculate();
                    this.settingSimpleCPS.recalculate();
                    this.animationTimer9.processAction3();
                }
                AttackTimeTracker auxiliaryLongEntry2 = this.auxiliaryLongEntry;
                int indexOffset26 = this.settingSimpleCPS.getIndexOffset2();
                double double33 = this.settingSimpleClickDutyCycle.getDouble3();
                double double6 = this.settingSimpleClickDutyCycle.getDouble6();
                boolean enabled7;
                if (this.settingSimpleDoubleClicks.isEnabled() && this.settingSimpleDoubleClicks.isVisible()) {
                    enabled7 = true;
                } else {
                    enabled7 = false;
                }
                boolean enabled3 = this.settingSimpleExtraDoubleClickRandomize.isEnabled();
                Pair pair3 = auxiliaryLongEntry2.getPair(indexOffset26, double33, double6, enabled7 & true, enabled3 & true, 0.76);
                int second3 = (Integer)pair3.getSecond();
                this.activeState = (Boolean)pair3.getFirst();
                int int22 = this.getInt2(second3);
                if (ClientGlobals.moduleManager.smartClicking.canExecute5() || int22 != second3) {
                    this.activeState = false;
                    int22 = 0;
                }
                return int22;
            case "Smart":
                MovingObjectPosition hitResult = null;
                boolean enabled8 = false;
                {
                    boolean completed = false;
                    hitResult = minecraft.objectMouseOver;
                    if (hitResult != null) {
                        MovingObjectType typeOfHit5 = hitResult.typeOfHit;
                        MovingObjectType movingObjectTypeKind5 = MovingObjectType.ENTITY;
                        if (typeOfHit5 == movingObjectTypeKind5 && hitResult.entityHit instanceof EntityLivingBase) {
                            enabled8 = true;
                            completed = true;
                        }
                    }
                    if (!completed) {
                        enabled8 = false;
                    }

                }
                this.processAction();
                int index11 = 0;
                if (enabled8) {
                    int indexOffset4 = this.targetEntity.hurtTime;
                    if (ClientGlobals.moduleManager.smartClicking.canExecute5()) {
                        index11 = 0;
                    } else {
                        int tickDelay42 = this.tickDelay4;
                        if (indexOffset4 != tickDelay42) {
                            SmoothDamper smoothDamper5 = this.getSmoothDamper(indexOffset4);
                            int index12 = indexOffset4 * 4;
                            int int6 = this.getInt6(index12);
                            int int23 = this.getInt2(int6);
                            index11 = smoothDamper5.getInt(int23);
                            if (index11 > 0) {
                                int index13 = indexOffset4 + 11;
                                SmoothDamper smoothDamper22 = this.getSmoothDamper2(index13);
                                int index14 = indexOffset4 * 4 + 2;
                                int int3 = this.getInt3(index14);
                                int int24 = this.getInt2(int3);
                                int intEntry2 = smoothDamper22.getInt(int24);
                                index11 += intEntry2;
                            }
                        } else {
                            AnimationTimer animationTimer52 = this.animationTimer5;
                            int indexOffset27 = this.settingSmartCPSFirstHitRecalculateTickDelay.getIndexOffset2();
                            if (animationTimer52.checkCondition(indexOffset27)) {
                                this.animationTimer5.processAction3();
                                this.settingSmartCPSFirstHitRecalculateTickDelay.getDouble4();
                                this.settingSmartCPSFirstHit.getDouble4();
                            }

                            AnimationTimer animationTimer62 = this.animationTimer6;
                            int indexOffset28 = this.settingSmartDCPSFirstHitRecalculateTickDelay.getIndexOffset2();
                            if (animationTimer62.checkCondition(indexOffset28)) {
                                this.animationTimer6.processAction3();
                                this.settingSmartDCPSFirstHitRecalculateTickDelay.getDouble4();
                                this.settingSmartDCPSFirstHit.getDouble4();
                            }

                            SmoothDamper smoothDamper6 = this.getSmoothDamper(22);
                            int indexOffset29 = this.settingSmartCPSFirstHit.getIndexOffset2();
                            int int25 = this.getInt2(indexOffset29);
                            index11 = smoothDamper6.getInt(int25);
                            if (index11 > 0) {
                                SmoothDamper smoothDamper23 = this.getSmoothDamper2(23);
                                int index15;
                                if (this.settingSmartMode.getSelected().equals("Recommended")) {
                                    index15 = this.settingSmartDCPS.getIndexOffset2();
                                } else {
                                    index15 = this.settingSmartDCPSFirstHit.getIndexOffset2();
                                }

                                int int26 = this.getInt2(index15);
                                int intEntry3 = smoothDamper23.getInt(int26);
                                index11 += intEntry3;
                            }
                        }
                    }

                    int index16;
                    if (index11 > 0) {
                        index16 = this.tickDelay + 1;
                        this.tickDelay = index16;
                    } else {
                        index16 = 0;
                    }

                    this.tickDelay = index16;
                } else {
                    boolean completed = false;
                    if (hitResult != null) {
                        MovingObjectType typeOfHit6 = hitResult.typeOfHit;
                        MovingObjectType movingObjectTypeKind6 = MovingObjectType.MISS;
                        if (typeOfHit6 != movingObjectTypeKind6) {
                            AnimationTimer animationTimer32 = this.animationTimer3;
                            int indexOffset210 = this.settingSmartCPSOnWallRecalculateTickDelay.getIndexOffset2();
                            if (animationTimer32.checkCondition(indexOffset210)) {
                                this.animationTimer3.processAction3();
                                this.settingSmartCPSOnWallRecalculateTickDelay.getDouble4();
                                this.settingSmartCPSOnWall.getDouble4();
                            }
                            AnimationTimer animationTimer42 = this.animationTimer4;
                            int indexOffset211 = this.settingDCPSOnWallRecalculateTickDelay.getIndexOffset2();
                            if (animationTimer42.checkCondition(indexOffset211)) {
                                this.animationTimer4.processAction3();
                                this.settingDCPSOnWallRecalculateTickDelay.getDouble4();
                                this.settingSmartDCPSOnWall.getDouble4();
                            }
                            SmoothDamper smoothDamper7 = this.getSmoothDamper(26);
                            int indexOffset212 = this.settingSmartCPSOnWall.getIndexOffset2();
                            index11 = smoothDamper7.getInt(indexOffset212);
                            if (index11 > 0) {
                                SmoothDamper smoothDamper24 = this.getSmoothDamper2(27);
                                int indexOffset213 = this.settingSmartDCPSOnWall.getIndexOffset2();
                                int intEntry4 = smoothDamper24.getInt(indexOffset213);
                                index11 += intEntry4;
                            }
                            completed = true;
                        }
                    }
                    if (!completed) {
                        AnimationTimer animationTimer11 = this.animationTimer;
                        int indexOffset214 = this.settingSmartCPSInAirRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer11.checkCondition(indexOffset214)) {
                            this.animationTimer.processAction3();
                            this.settingSmartCPSInAirRecalculateTickDelay.getDouble4();
                            this.settingSmartCPSInAir.getDouble4();
                        }
                        AnimationTimer animationTimer22 = this.animationTimer2;
                        int indexOffset215 = this.settingDCPSInAirRecalculateTickDelay.getIndexOffset2();
                        if (animationTimer22.checkCondition(indexOffset215)) {
                            this.animationTimer2.processAction3();
                            this.settingDCPSInAirRecalculateTickDelay.getDouble4();
                            this.settingSmartDCPSInAir.getDouble4();
                        }
                        SmoothDamper smoothDamper8 = this.getSmoothDamper(24);
                        int indexOffset216 = this.settingSmartCPSInAir.getIndexOffset2();
                        index11 = smoothDamper8.getInt(indexOffset216);
                        if (index11 > 0) {
                            SmoothDamper smoothDamper25 = this.getSmoothDamper2(25);
                            int indexOffset217 = this.settingSmartDCPSInAir.getIndexOffset2();
                            int intEntry5 = smoothDamper25.getInt(indexOffset217);
                            index11 += intEntry5;
                        }
                    }

                }
                if (this.targetEntity != null) {
                    this.tickDelay4 = this.targetEntity.hurtTime;
                }
                return index11;
            default:
                return 0;
        }
    }

    private boolean isPlayRecordedMouseMovementsVisible() {
        return this.settingMode.getSelected().equals("Recorded");
    }

    private int getInt5() {
        if (this.targetEntity != null && this.targetEntity.hurtTime == 10) {
            this.activeState11 = false;
        }

        int clicks = this.getInt4();
        if (this.activeState13) {
            if (clicks <= 0 && !this.settingOnlyOnClick.isEnabled()) {
                clicks = 1;
            }

            this.activeState13 = false;
        }

        if (this.settingPerfectHit.isEnabled()
            && minecraft.objectMouseOver != null
            && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
            && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase
            && !this.checkCondition()) {
            EntityLivingBase entityHit = (EntityLivingBase)minecraft.objectMouseOver.entityHit;
            if (entityHit.hurtTime == 0 || entityHit.hurtTime == 1) {
                clicks = 1;
            }
        }

        if (ClientGlobals.moduleManager.moveHelper.isToggled()
            && AutoClickerComboBreakerType.HIT_SELECTION.isSelected()
            && ClientGlobals.moduleManager.moveHelper.isExpanded2()) {
            clicks = 0;
        } else if (clicks == 0
            && ClientGlobals.moduleManager.moveHelper.isToggled()
            && AutoClickerComboBreakerType.HIT_SELECTION.isSelected()
            && !ClientGlobals.moduleManager.moveHelper.isExpanded2()
            && ClientGlobals.moduleManager.moveHelper.isEnabled2()) {
            clicks = 1;
        } else if (clicks == 0 && this.settingCritFix.isEnabled() && !this.activeState11 && this.canExecute6()) {
            clicks = 1;
        } else if (this.settingOnlyOnTarget.isEnabled() && (minecraft.objectMouseOver == null || minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY)) {
            clicks = 0;
        }

        if (this.canExecute5()) {
            clicks = 0;
        }

        int projectedCps = (int)((double)clicks + EventClick.clickHelper.getDouble10());
        if (this.settingCPSLimiterIntave.isEnabled() ? this.auxiliaryArrayListEntry.canExecute() : (double)projectedCps > this.settingCPSLimiter.getValue()) {
            clicks = 0;
        }

        return clicks;
    }

    private void processShort() {
        for (int i = 0; i < this.animationTimerArray.length; i++) {
            this.animationTimerArray[i].processAction();
        }

        this.animationTimer.processAction();
        this.animationTimer2.processAction();
        this.animationTimer3.processAction();
        this.animationTimer4.processAction();
        this.animationTimer5.processAction();
        this.animationTimer6.processAction();
        this.animationTimer7.processAction();
        this.animationTimer8.processAction();
        this.animationTimer9.processAction();
    }

    private int getInt6(int index) {
        return this.rangeSettingArray[index].getIndexOffset2();
    }

    private EntityLivingBase getEntityLivingBase() {
        if (ClientGlobals.moduleManager.aimBot.isToggled() && ClientGlobals.moduleManager.aimBot.target != null) {
            return ClientGlobals.moduleManager.aimBot.target;
        } else if (minecraft.objectMouseOver == null) {
            return null;
        } else if (minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
            return null;
        } else {
            return minecraft.objectMouseOver.entityHit instanceof EntityLivingBase ? (EntityLivingBase)minecraft.objectMouseOver.entityHit : null;
        }
    }

    private void updateState2() {
        this.tickDelay2 = Math.max(this.tickDelay2 - this.settingEnduranceReduction.getInt5(true), 0);
        this.enabled2 = false;
    }

    private boolean isSmartCPSVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isMultiplierVisible() {
        return this.settingMode.getSelected().equals("Recorded");
    }

    private boolean isSmartCPSFirstHitVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isRecordedModeVisible() {
        return this.settingMode.getSelected().equals("Recorded");
    }

    private boolean checkCondition() {
        return ((this.targetEntity.hurtTime) != 0);
    }

    private boolean isSmartCPSInAirRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isSpikeDelayVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean canExecute5() {
        return minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
        ? ClientGlobals.moduleManager.midClick.checkEntity(minecraft.objectMouseOver.entityHit)
        : false;
    }

    private boolean isTimingValuesVisible() {
        return this.settingTiming.getSelected().equals("Always");
    }

    private boolean isDCPSRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isReduceRecalculateDelayOnWallVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isDragCPSInAirVisible() {
        return this.settingMode.getSelected().equals("DragClick");
    }

    private boolean isSmartModeVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isDragCPSInAirRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("DragClick");
    }

    private boolean isAdvancedCPSRecalculateDelayVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isSmartDCPSInAirVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isOnWallVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isOnFirstHitVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isDeviationCPSVisible() {
        return this.settingMode.getSelected().equals("Deviation");
    }

    private boolean isSmartCPSOnWallVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isSmartDCPSOnWallVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isDropsVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isDeviationAirMultiplierVisible() {
        return this.settingMode.getSelected().equals("Deviation");
    }

    private boolean isDCPSRecalculateTickDelayHurtVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isEnduranceVisible() {
        return this.settingTiming.getSelected().equals("Other");
    }

    private boolean isSmartCPSFirstHitRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isReduceMultiplierOnWallVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean canExecute6() {
        return minecraft.objectMouseOver == null ? false : ClientGlobals.moduleManager.criticals.isToggled();
    }

    private boolean isSmartDCPSHurtVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isSimpleCPSRecalculateDelayVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean canExecute7() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean isDCPSOnWallRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isEnduranceReductionVisible() {
        return this.settingTiming.getSelected().equals("Other");
    }

    private boolean isHurtVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isInAirVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isReduceHitsOnWallVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isMaxCPSVisible() {
        return this.settingMode.getSelected().equals("DragClick");
    }

    private boolean isSmartClickModeVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isOnTargetVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isSimpleCPSVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean isRecordedAirMultiplierVisible() {
        return this.settingMode.getSelected().equals("Recorded");
    }

    private boolean isSimpleDoubleClicksVisible() {
        return (this.settingMode.getSelected().equals("Simple") ? 1 : 0) >= 0;
    }

    private boolean canExecute8() {
        if (!this.auxiliarySettingAdapter.canExecute()) {
            return false;
        } else if (this.settingOnlyOnClick.isEnabled()
        && !minecraft.gameSettings.keyBindAttack.isKeyDown()
        && !minecraft.gameSettings.keyBindAttack.isPressed()) {
            return false;
        } else if (!minecraft.isIntegratedServerRunning()) {
            return false;
        } else {
            int int3 = minecraft.getInt3();
            int indexOffset2 = this.settingDelayClicksAfterClosingGuiScreen.getIndexOffset2();
            return int3 >= indexOffset2;
        }
    }

    private boolean isHurttimesToHitVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isMaxCPSRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("DragClick");
    }

    private boolean isDropMultiplierVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isSmartCPSInAirVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isSpikesVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isSmartDCPSFirstHitRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isSmartDoubleClickModeSVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isClickPatternVisible() {
        return this.settingMode.getSelected().equals("Recorded");
    }

    private boolean isReduceHitsInAirVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean canExecute9() {
        return this.settingBreakBlocks.isEnabled() && minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
        ? (double)this.tickDelay3 != this.settingBreakTickDelay.getValue()
        : false;
    }

    private boolean isHurtTimeVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isDropDurationVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isReduceMultiplierVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean canExecute10() {
        return minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY;
    }

    private boolean isCPSSettingsVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isPredictionVisible() {
        return this.settingTiming.getSelected().equals("Other");
    }

    private boolean isLoseDelayVisible() {
        return this.settingTiming.getSelected().equals("Other");
    }

    private boolean isSimpleExtraDoubleClickRandomizeVisible() {
        return this.settingMode.getSelected().equals("Simple");
    }

    private boolean isDCPSInAirRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isDelayClicksAfterClosingGuiScreenVisible() {
        return this.canExecute7();
    }

    private boolean isSpikeDurationVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isSpikeMultiplierVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isSmartDCPSFirstHitVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isDropDelayVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }

    private boolean isDeviationDoubleClickVisible() {
        return this.settingMode.getSelected().equals("Deviation");
    }

    private boolean isMinStandardDeviationVisible() {
        return this.settingMode.getSelected().equals("DragClick");
    }

    private boolean isSmartCPSOnWallRecalculateTickDelayVisible() {
        return this.settingMode.getSelected().equals("Smart");
    }

    private boolean isAdvancedCPSVisible() {
        return this.settingMode.getSelected().equals("Advanced");
    }
}
