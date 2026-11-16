package augustus.modules.misc;


import client.setting.HURTTIMEEnum;
import client.setting.Setting;
import client.util.CombatRandomizer;
import client.util.RayTraceUtil;
import java.security.SecureRandom;
import client.ClientGlobals;
import client.config.ClickPatternManager;
import client.config.ClickPatternProfile;
import client.event.ClickListener;
import client.event.EventClick;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.ActionSetting;
import client.setting.AutoClickerHurtTime;
import client.setting.BooleanSetting;
import client.setting.HURTTIMEEnum;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.ClickHistoryBuffer;
import client.util.CombatRandomizer;
import client.util.CombatRayTrace;
import client.util.ConsumerTask;
import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import client.util.RayTraceUtil;
import client.util.RayTraceUtils;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;

public class OldAutoClicker extends Module implements ClickListener {
   public final ModeSetting settingSmart;
   public final NumberSetting settingCPSLimiter;
   public final BooleanSetting settingCPSLimiterIntave;
   public final SettingGroup settingModeValues;
   public final ActionSetting actionSetting;
   public final ActionSetting actionSetting2;
   public final BooleanSetting settingDoubleClick;
   public final NumberSetting settingAirMultiplier;
   public final ActionSetting actionSetting3;
   public final ActionSetting actionSetting4;
   public final BooleanSetting settingSpikes;
   public final NumberSetting settingSpikeDelay;
   public final ActionSetting actionSetting5;
   public final NumberSetting settingSpikeMulti;
   public final BooleanSetting settingDrops;
   public final NumberSetting settingDropDelay;
   public final ActionSetting actionSetting6;
   public final NumberSetting settingDropMulti;
   public final BooleanSetting settingReduceInAir;
   public final ActionSetting actionSetting7;
   public final ActionSetting actionSetting8;
   public final MultiSelectSetting settingHurttimesToHit;
   public final BooleanSetting settingSmartClicking;
   public final RangeSetting settingSmartClickingHurttimeEnd;
   public final ModeSetting settingClickPattern;
   public final ModeSetting settingTicks;
   public final NumberSetting settingMultiplier;
   public final NumberSetting settingAirMultiplier2;
   public final RangeSetting settingCPSInAir;
   public final RangeSetting settingCPSInAirRecalculateTickDelay;
   public final RangeSetting settingMaxCPS;
   public final RangeSetting settingMaxCPSRecalculateTickDelay;
   public final NumberSetting settingMinStandardDeviation;
   public final RangeSetting settingForceHitsHurtTime;
   public final ModeSetting settingMode;
   public final ModeSetting settingDoubleClickMode;
   public final SettingGroup settingInAir;
   public final RangeSetting settingCPSInAir2;
   public final RangeSetting settingCPSInAirRecalculateTickDelay2;
   public final RangeSetting settingDCPSInAir;
   public final RangeSetting settingDCPSInAirRecalculateTickDelay;
   public final SettingGroup settingOnFirstHit;
   public final RangeSetting settingCPSFirstHit;
   public final RangeSetting settingCPSFirstHitRecalculateTickDelay;
   public final RangeSetting settingDCPSFirstHit;
   public final RangeSetting settingDCPSFirstHitRecalculateTickDelay;
   public final SettingGroup settingOnTarget;
   public final RangeSetting settingCPS;
   public final RangeSetting settingCPSRecalculateTickDelay;
   public final RangeSetting settingDCPS;
   public final RangeSetting settingDCPSRecalculateTickDelay;
   public final SettingGroup settingHurtTime0;
   public final RangeSetting settingCPS2;
   public final RangeSetting settingCPSRecalculateTickDelay2;
   public final RangeSetting settingDCPS2;
   public final RangeSetting settingDCPSRecalculateTickDelay2;
   public final SettingGroup settingHurtTime1;
   public final RangeSetting settingCPS3;
   public final RangeSetting settingCPSRecalculateTickDelay3;
   public final RangeSetting settingDCPS3;
   public final RangeSetting settingDCPSRecalculateTickDelay3;
   public final SettingGroup settingHurtTime2;
   public final RangeSetting settingCPS4;
   public final RangeSetting settingCPSRecalculateTickDelay4;
   public final RangeSetting settingDCPS4;
   public final RangeSetting settingDCPSRecalculateTickDelay4;
   public final SettingGroup settingHurtTime3;
   public final RangeSetting settingCPS5;
   public final RangeSetting settingCPSRecalculateTickDelay5;
   public final RangeSetting settingDCPS5;
   public final RangeSetting settingDCPSRecalculateTickDelay5;
   public final SettingGroup settingHurtTime4;
   public final RangeSetting settingCPS6;
   public final RangeSetting settingCPSRecalculateTickDelay6;
   public final RangeSetting settingDCPS6;
   public final RangeSetting settingDCPSRecalculateTickDelay6;
   public final SettingGroup settingHurtTime5;
   public final RangeSetting settingCPS7;
   public final RangeSetting settingCPSRecalculateTickDelay7;
   public final RangeSetting settingDCPS7;
   public final RangeSetting settingDCPSRecalculateTickDelay7;
   public final SettingGroup settingHurtTime6;
   public final RangeSetting settingCPS8;
   public final RangeSetting settingCPSRecalculateTickDelay8;
   public final RangeSetting settingDCPS8;
   public final RangeSetting settingDCPSRecalculateTickDelay8;
   public final SettingGroup settingHurtTime7;
   public final RangeSetting settingCPS9;
   public final RangeSetting settingCPSRecalculateTickDelay9;
   public final RangeSetting settingDCPS9;
   public final RangeSetting settingDCPSRecalculateTickDelay9;
   public final SettingGroup settingHurtTime8;
   public final RangeSetting settingCPS10;
   public final RangeSetting settingCPSRecalculateTickDelay10;
   public final RangeSetting settingDCPS10;
   public final RangeSetting settingDCPSRecalculateTickDelay10;
   public final SettingGroup settingHurtTime9;
   public final RangeSetting settingCPS11;
   public final RangeSetting settingCPSRecalculateTickDelay11;
   public final RangeSetting settingDCPS11;
   public final RangeSetting settingDCPSRecalculateTickDelay11;
   public final SettingGroup settingHurtTime10;
   public final RangeSetting settingCPS12;
   public final RangeSetting settingCPSRecalculateTickDelay12;
   public final RangeSetting settingDCPS12;
   public final RangeSetting settingDCPSRecalculateTickDelay12;
   public final RangeSetting settingForceHitsHurtTimeStart;
   public final RangeSetting settingForceHitsHurtTimeEnd;
   public final BooleanSetting settingIntaveFix;
   public final BooleanSetting settingOnlyOnTarget;
   public final BooleanSetting settingPerfectHit;
   public final BooleanSetting settingDoubleClickFirstHit;
   public final BooleanSetting settingReduceCPSIfYouCanNotHitATarget;
   public final BooleanSetting settingSetReduceCPSToInAirCPS;
   public final BooleanSetting settingCritFix;
   public final BooleanSetting settingLongClick;
   public final BooleanSetting settingAttackFriends;
   public final BooleanSetting settingBreakBlocks;
   public final NumberSetting settingBreakTickDelay;
   public final BooleanSetting settingAutoBlock;
   public final BooleanSetting settingOnlyOnRightClick;
   public final BooleanSetting settingOnlyOnClick;
   public final TimeHelper timer;
   public final TimeHelper timer2;
   public final TimeHelper timer3;
   public final TimeHelper timer4;
   public final TimeHelper timer5;
   public final TimeHelper timer6;
   public final ClickHistoryBuffer clickHistoryBuffer;
   public final ArrayList arrayList;
   public EntityLivingBase entityLivingBase;
   public EntityLivingBase entityLivingBase2;
   public boolean enabled3;
   public boolean enabled4;
   public int count6;
   public long timestamp;
   public long timestamp2;
   public long timestamp3;
   public long timestamp4;
   public double value2;
   public long timestamp5;
   public int count7;
   public long timestamp6;
   public long timestamp7;
   public long timestamp8;
   public double value3;
   public long timestamp9;
   public boolean enabled5;
   public boolean enabled6;
   public boolean enabled7;
   public ClickPatternProfile clickPatternProfile;
   public ArrayList arrayList2;
   public final ArrayList arrayList3;
   public double value4;
   public final HashMap hashMap;
   public final AnimationTimer animationTimer;
   public final AnimationTimer animationTimer2;
   public final AnimationTimer animationTimer3;
   public final AnimationTimer animationTimer4;
   public final AnimationTimer animationTimer5;
   public final AnimationTimer animationTimer6;
   public final AnimationTimer animationTimer7;
   public final AnimationTimer animationTimer8;
   public final AnimationTimer animationTimer9;
   public final AnimationTimer animationTimer10;
   public final AnimationTimer animationTimer11;
   public final AnimationTimer animationTimer12;
   public final AnimationTimer animationTimer13;
   public final AnimationTimer animationTimer14;
   public final AnimationTimer animationTimer15;
   public final AnimationTimer animationTimer16;
   public final AnimationTimer animationTimer17;
   public final AnimationTimer animationTimer18;
   public final AnimationTimer animationTimer19;
   public final AnimationTimer animationTimer20;
   public final AnimationTimer animationTimer21;
   public final AnimationTimer animationTimer22;
   public final AnimationTimer animationTimer23;
   public final AnimationTimer animationTimer24;
   public final AnimationTimer animationTimer25;
   public final AnimationTimer animationTimer26;
   public final AnimationTimer animationTimer27;
   public final AnimationTimer animationTimer28;
   public final TimeHelper timer7;
   public final SmoothDamper smoothDamper;
   public final SmoothDamper smoothDamper2;
   public int count8;
   public int count9;

   public OldAutoClicker() {
        super("OldAutoClicker", "AlterSelbstständigerClicker", new Color(82, 186, 74), Category.LEGIT);
      
      this.settingSmart = new ModeSetting(this, "Mode", "Advanced", new String[]{"Basic", "Deviation", "Advanced", "DragClick", "Recorded", "Smart"});
      this.settingCPSLimiter = new NumberSetting(this, "CPSLimiter", 20.0, 1.0, 40.0, 0);
      this.settingCPSLimiterIntave = new BooleanSetting(this, "CPSLimiterIntave", false);
      this.settingModeValues = new SettingGroup(this, "ModeValues", true);
      ActionSetting setting = new ActionSetting(this, "Delay", 40.0, 100.0, 0.0, 400.0, 0, this.settingModeValues);
      BooleanSupplier booleanSupplier = this::isActive3;
      this.actionSetting = setting.getActionSetting(booleanSupplier);
      ActionSetting setting2 = new ActionSetting(this, "DeviationCPS", 13.0, 20.0, 0.0, 40.0, 0, this.settingModeValues);
      String text = "MaxCPS";
      String text2 = "MinCPS";
      ActionSetting actionSetting9 = setting2.getActionSetting(text2, text);
      booleanSupplier = this::isActive79;
      this.actionSetting2 = actionSetting9.getActionSetting(booleanSupplier);
      BooleanSetting setting3 = new BooleanSetting(this, "DeviationDoubleClick", true, this.settingModeValues);
      String text3 = "DoubleClick";
      BooleanSetting setting4 = (BooleanSetting)setting3.getSetting(text3);
      booleanSupplier = this::isActive55;
      this.settingDoubleClick = (BooleanSetting)setting4.getSetting(booleanSupplier);
      NumberSetting setting5 = new NumberSetting(this, "DeviationAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingModeValues);
      String text4 = "AirMultiplier";
      NumberSetting numberSetting = setting5.getNumberSetting(text4);
      booleanSupplier = this::isActive42;
      this.settingAirMultiplier = numberSetting.getNumberSetting(booleanSupplier);
      ActionSetting setting6 = new ActionSetting(this, "CPS", 20.0, 20.0, 0.0, 40.0, 0, this.settingModeValues);
      booleanSupplier = this::isActive24;
      this.actionSetting3 = setting6.getActionSetting(booleanSupplier);
      ActionSetting setting7 = new ActionSetting(this, "SpeedChangeDelay", 200.0, 600.0, 0.0, 4000.0, 0, this.settingModeValues);
      String text5 = "MaxDelay";
      text2 = "MinDelay";
      ActionSetting actionSetting10 = setting7.getActionSetting(text2, text5);
      booleanSupplier = this::isActive61;
      this.actionSetting4 = actionSetting10.getActionSetting(booleanSupplier);
      BooleanSetting setting8 = new BooleanSetting(this, "Spikes", false, this.settingModeValues);
      booleanSupplier = this::isActive5;
      this.settingSpikes = (BooleanSetting)setting8.getSetting(booleanSupplier);
      NumberSetting setting9 = new NumberSetting(this, "SpikeDelay", 15000.0, 0.0, 20000.0, 0, this.settingModeValues);
      booleanSupplier = this::isActiveAsFlag;
      this.settingSpikeDelay = setting9.getNumberSetting(booleanSupplier);
      ActionSetting setting10 = new ActionSetting(this, "SpikeDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingModeValues);
      booleanSupplier = this::isActive100;
      this.actionSetting5 = setting10.getActionSetting(booleanSupplier);
      NumberSetting setting11 = new NumberSetting(this, "SpikeMulti", 1.5, 1.0, 3.0, 2, this.settingModeValues);
      booleanSupplier = this::isActive62;
      this.settingSpikeMulti = setting11.getNumberSetting(booleanSupplier);
      BooleanSetting setting12 = new BooleanSetting(this, "Drops", false, this.settingModeValues);
      booleanSupplier = this::isActive82;
      this.settingDrops = (BooleanSetting)setting12.getSetting(booleanSupplier);
      NumberSetting setting13 = new NumberSetting(this, "DropDelay", 7000.0, 0.0, 20000.0, 0, this.settingModeValues);
      booleanSupplier = this::isActive36;
      this.settingDropDelay = setting13.getNumberSetting(booleanSupplier);
      ActionSetting setting14 = new ActionSetting(this, "DropDuration", 500.0, 1000.0, 0.0, 2000.0, 0, this.settingModeValues);
      booleanSupplier = this::isActive70;
      this.actionSetting6 = setting14.getActionSetting(booleanSupplier);
      NumberSetting setting15 = new NumberSetting(this, "DropMulti", 0.5, 0.0, 1.0, 2, this.settingModeValues);
      booleanSupplier = this::isActive47;
      this.settingDropMulti = setting15.getNumberSetting(booleanSupplier);
      BooleanSetting setting16 = new BooleanSetting(this, "ReduceInAir", true, this.settingModeValues);
      booleanSupplier = this::isActive35;
      this.settingReduceInAir = (BooleanSetting)setting16.getSetting(booleanSupplier);
      ActionSetting setting17 = new ActionSetting(this, "ReduceMulti", 0.4, 0.4, 0.0, 1.0, 2, this.settingModeValues);
      booleanSupplier = this::isActive28;
      this.actionSetting7 = setting17.getActionSetting(booleanSupplier);
      ActionSetting setting18 = new ActionSetting(this, "ReduceRecalculateDelay", 100.0, 200.0, 0.0, 1000.0, 0, this.settingModeValues);
      booleanSupplier = this::isActive83;
      this.actionSetting8 = setting18.getActionSetting(booleanSupplier);
      MultiSelectSetting setting19 = new MultiSelectSetting(this, "HurttimesToHit", AutoClickerHurtTime.values(), this.settingModeValues);
      booleanSupplier = this::isActive74;
      this.settingHurttimesToHit = setting19.getMultiSelectSetting(booleanSupplier);
      BooleanSetting setting20 = new BooleanSetting(this, "SmartClicking", true, this.settingModeValues);
      booleanSupplier = this::isActive92;
      this.settingSmartClicking = (BooleanSetting)setting20.getSetting(booleanSupplier);
      RangeSetting setting21 = new RangeSetting(this, "SmartClickingHurttimeEnd", 2.0, 3.0, 0.0, 9.0, 0, this.settingModeValues);
      booleanSupplier = this.settingSmartClicking::isVisible;
      this.settingSmartClickingHurttimeEnd = setting21.getRangeSetting(15343, booleanSupplier);
      ModeSetting setting22 = new ModeSetting(this, "ClickPattern", "", new String[0], this.settingModeValues);
      booleanSupplier = this::isActive68;
      this.settingClickPattern = setting22.getModeSetting(booleanSupplier);
      ModeSetting setting23 = new ModeSetting(this, "RecordedMode", "TimeMillis", new String[]{"TimeMillis", "Ticks"}, this.settingModeValues);
      booleanSupplier = this::isActive9;
      this.settingTicks = setting23.getModeSetting(booleanSupplier);
      NumberSetting setting24 = new NumberSetting(this, "Multiplier", 1.0, 0.0, 5.0, 2, this.settingModeValues);
      booleanSupplier = this::isActive14;
      this.settingMultiplier = setting24.getNumberSetting(booleanSupplier);
      NumberSetting setting25 = new NumberSetting(this, "RecordedAirMultiplier", 0.4, 0.0, 1.0, 2, this.settingModeValues);
      String text6 = "AirMultiplier";
      NumberSetting numberSetting2 = setting25.getNumberSetting(text6);
      booleanSupplier = this::isActive44;
      this.settingAirMultiplier2 = numberSetting2.getNumberSetting(booleanSupplier);
      RangeSetting setting26 = new RangeSetting(this, "DragCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.settingModeValues);
      String text7 = "CPSInAir";
      RangeSetting rangeSetting = setting26.getRangeSetting(text7);
      booleanSupplier = this::isActive46;
      this.settingCPSInAir = rangeSetting.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting27 = new RangeSetting(this, "DragCPSInAirRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, this.settingModeValues);
      String text8 = "CPSInAirRecalculateTickDelay";
      RangeSetting rangeSetting2 = setting27.getRangeSetting(text8).getRangeSetting2();
      booleanSupplier = this::isActive10;
      this.settingCPSInAirRecalculateTickDelay = rangeSetting2.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting28 = new RangeSetting(this, "MaxCPS", 18.0, 20.0, 1.0, 40.0, 0, this.settingModeValues);
      booleanSupplier = this::isActive45;
      this.settingMaxCPS = setting28.getRangeSetting(15343, booleanSupplier);
      RangeSetting rangeSetting3 = (new RangeSetting(this, "MaxCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingModeValues)).getRangeSetting2();
      booleanSupplier = this::isActive102;
      this.settingMaxCPSRecalculateTickDelay = rangeSetting3.getRangeSetting(15343, booleanSupplier);
      NumberSetting setting29 = new NumberSetting(this, "MinStandardDeviation", 0.5, 0.0, 1.0, 2, this.settingModeValues);
      booleanSupplier = this::isActive105;
      this.settingMinStandardDeviation = setting29.getNumberSetting(booleanSupplier);
      RangeSetting setting30 = new RangeSetting(this, "DragForceHitsHurtTime", 2.0, 3.0, 0.0, 9.0, 0, this.settingModeValues);
      String text9 = "ForceHitsHurtTime";
      RangeSetting rangeSetting4 = setting30.getRangeSetting(text9);
      booleanSupplier = this::isActive26;
      this.settingForceHitsHurtTime = rangeSetting4.getRangeSetting(15343, booleanSupplier);
      ModeSetting setting31 = new ModeSetting(this, "SmartMode", "Recommended", new String[]{"Recommended", "Overengineered"}, this.settingModeValues);
      String text10 = "Mode";
      ModeSetting modeSetting = setting31.getModeSetting(text10);
      booleanSupplier = this::isActive59;
      this.settingMode = modeSetting.getModeSetting(booleanSupplier);
      ModeSetting setting32 = new ModeSetting(this, "DoubleClickMode", "Default", new String[]{"Default", "Separate"}, this.settingModeValues);
      String text11 = "DoubleClickMode";
      ModeSetting modeSetting2 = setting32.getModeSetting(text11);
      booleanSupplier = this::isActive39;
      this.settingDoubleClickMode = modeSetting2.getModeSetting(booleanSupplier);
      SettingGroup settingGroup = new SettingGroup(this, "In Air", true, this.settingModeValues);
      BooleanSupplier booleanSupplier2 = this::isActive106;
      booleanSupplier = booleanSupplier2;
      this.settingInAir = settingGroup.getSettingGroup(booleanSupplier);
      RangeSetting setting33 = new RangeSetting(this, "SmartCPSInAir", 4.0, 8.0, 0.0, 20.0, 0, this.settingInAir);
      String text12 = "CPSInAir";
      RangeSetting rangeSetting5 = setting33.getRangeSetting(text12);
      booleanSupplier = this::isActive77;
      this.settingCPSInAir2 = rangeSetting5.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting34 = new RangeSetting(this, "SmartCPSInAirRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, this.settingInAir);
      String text13 = "CPSInAirRecalculateTickDelay";
      RangeSetting rangeSetting6 = setting34.getRangeSetting(text13).getRangeSetting2();
      booleanSupplier = this::isActive86;
      this.settingCPSInAirRecalculateTickDelay2 = rangeSetting6.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting35 = new RangeSetting(this, "SmartDCPSInAir", 0.0, 0.0, 0.0, 20.0, 0, this.settingInAir);
      String text14 = "DCPSInAir";
      RangeSetting rangeSetting7 = setting35.getRangeSetting(text14);
      booleanSupplier = this::isActive17;
      this.settingDCPSInAir = rangeSetting7.getRangeSetting(15343, booleanSupplier);
      RangeSetting rangeSetting8 = (new RangeSetting(this, "DCPSInAirRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingInAir)).getRangeSetting2();
      booleanSupplier = this::isActive52;
      this.settingDCPSInAirRecalculateTickDelay = rangeSetting8.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup2 = new SettingGroup(this, "On first Hit", true, this.settingModeValues);
      booleanSupplier2 = this::isActive;
      booleanSupplier = booleanSupplier2;
      this.settingOnFirstHit = settingGroup2.getSettingGroup(booleanSupplier);
      RangeSetting setting36 = new RangeSetting(this, "SmartCPSFirstHit", 20.0, 20.0, 0.0, 40.0, 0, this.settingOnFirstHit);
      String text15 = "CPSFirstHit";
      RangeSetting rangeSetting9 = setting36.getRangeSetting(text15);
      booleanSupplier = this::isActive95;
      this.settingCPSFirstHit = rangeSetting9.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting37 = new RangeSetting(this, "SmartCPSFirstHitRecalculateTickDelay", 10.0, 30.0, 0.0, 60.0, 0, this.settingOnFirstHit);
      String text16 = "CPSFirstHitRecalculateTickDelay";
      RangeSetting rangeSetting10 = setting37.getRangeSetting(text16).getRangeSetting2();
      booleanSupplier = this::isActive63;
      this.settingCPSFirstHitRecalculateTickDelay = rangeSetting10.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting38 = new RangeSetting(this, "SmartDCPSFirstHit", 4.0, 5.0, 0.0, 20.0, 0, this.settingOnFirstHit);
      String text17 = "DCPSFirstHit";
      RangeSetting rangeSetting11 = setting38.getRangeSetting(text17);
      booleanSupplier = this::isActive19;
      this.settingDCPSFirstHit = rangeSetting11.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting39 = new RangeSetting(this, "SmartDCPSFirstHitRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnFirstHit);
      String text18 = "DCPSFirstHitRecalculateTickDelay";
      RangeSetting rangeSetting12 = setting39.getRangeSetting(text18).getRangeSetting2();
      booleanSupplier = this::isActive71;
      this.settingDCPSFirstHitRecalculateTickDelay = rangeSetting12.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup3 = new SettingGroup(this, "On Target", true, this.settingModeValues);
      booleanSupplier2 = this::isActive103;
      booleanSupplier = booleanSupplier2;
      this.settingOnTarget = settingGroup3.getSettingGroup(booleanSupplier);
      RangeSetting setting40 = new RangeSetting(this, "SmartCPS", 17.0, 19.0, 1.0, 40.0, 0, this.settingOnTarget);
      String text19 = "CPS";
      RangeSetting rangeSetting13 = setting40.getRangeSetting(text19);
      booleanSupplier = this::isActive13;
      this.settingCPS = rangeSetting13.getRangeSetting(15343, booleanSupplier);
      RangeSetting rangeSetting14 = (new RangeSetting(this, "CPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnTarget)).getRangeSetting2();
      booleanSupplier = this::isActive53;
      this.settingCPSRecalculateTickDelay = rangeSetting14.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting41 = new RangeSetting(this, "SmartDCPS", 4.0, 5.0, 0.0, 20.0, 0, this.settingOnTarget);
      String text20 = "DCPS";
      RangeSetting rangeSetting15 = setting41.getRangeSetting(text20);
      booleanSupplier = this::isActive51;
      this.settingDCPS = rangeSetting15.getRangeSetting(15343, booleanSupplier);
      RangeSetting rangeSetting16 = (new RangeSetting(this, "DCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingOnTarget)).getRangeSetting2();
      booleanSupplier = this::isActive107;
      this.settingDCPSRecalculateTickDelay = rangeSetting16.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup4 = new SettingGroup(this, "HurtTime 0", this.settingModeValues);
      booleanSupplier2 = this::isActive11;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime0 = settingGroup4.getSettingGroup(booleanSupplier);
      RangeSetting setting42 = new RangeSetting(this, "SmartCPSHurt0", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime0);
      String text21 = "CPS";
      RangeSetting rangeSetting17 = setting42.getRangeSetting(text21);
      booleanSupplier = this::isActive12;
      this.settingCPS2 = rangeSetting17.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting43 = new RangeSetting(this, "Hurt0SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime0);
      String text22 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting18 = setting43.getRangeSetting(text22).getRangeSetting2();
      booleanSupplier = this::isActive33;
      this.settingCPSRecalculateTickDelay2 = rangeSetting18.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting44 = new RangeSetting(this, "SmartDCPSHurt0", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime0);
      String text23 = "DCPS";
      RangeSetting rangeSetting19 = setting44.getRangeSetting(text23);
      booleanSupplier = this::isActive67;
      this.settingDCPS2 = rangeSetting19.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting45 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt0", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime0);
      String text24 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting20 = setting45.getRangeSetting(text24).getRangeSetting2();
      booleanSupplier = this::isActive108;
      this.settingDCPSRecalculateTickDelay2 = rangeSetting20.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup5 = new SettingGroup(this, "HurtTime 1", this.settingModeValues);
      booleanSupplier2 = this::isActive84;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime1 = settingGroup5.getSettingGroup(booleanSupplier);
      RangeSetting setting46 = new RangeSetting(this, "SmartCPSHurt1", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime1);
      String text25 = "CPS";
      RangeSetting rangeSetting21 = setting46.getRangeSetting(text25);
      booleanSupplier = this::isActive78;
      this.settingCPS3 = rangeSetting21.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting47 = new RangeSetting(this, "Hurt1SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime1);
      String text26 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting22 = setting47.getRangeSetting(text26).getRangeSetting2();
      booleanSupplier = this::isActive22;
      this.settingCPSRecalculateTickDelay3 = rangeSetting22.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting48 = new RangeSetting(this, "SmartDCPSHurt1", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime1);
      String text27 = "DCPS";
      RangeSetting rangeSetting23 = setting48.getRangeSetting(text27);
      booleanSupplier = this::isActive72;
      this.settingDCPS3 = rangeSetting23.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting49 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt1", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime1);
      String text28 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting24 = setting49.getRangeSetting(text28).getRangeSetting2();
      booleanSupplier = this::isActive69;
      this.settingDCPSRecalculateTickDelay3 = rangeSetting24.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup6 = new SettingGroup(this, "HurtTime 2", this.settingModeValues);
      booleanSupplier2 = this::isActive20;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime2 = settingGroup6.getSettingGroup(booleanSupplier);
      RangeSetting setting50 = new RangeSetting(this, "SmartCPSHurt2", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime2);
      String text29 = "CPS";
      RangeSetting rangeSetting25 = setting50.getRangeSetting(text29);
      booleanSupplier = this::isActive65;
      this.settingCPS4 = rangeSetting25.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting51 = new RangeSetting(this, "Hurt2SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime2);
      String text30 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting26 = setting51.getRangeSetting(text30).getRangeSetting2();
      booleanSupplier = this::isActive38;
      this.settingCPSRecalculateTickDelay4 = rangeSetting26.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting52 = new RangeSetting(this, "SmartDCPSHurt2", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime2);
      String text31 = "DCPS";
      RangeSetting rangeSetting27 = setting52.getRangeSetting(text31);
      booleanSupplier = this::isActive58;
      this.settingDCPS4 = rangeSetting27.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting53 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt2", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime2);
      String text32 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting28 = setting53.getRangeSetting(text32).getRangeSetting2();
      booleanSupplier = this::isActive32;
      this.settingDCPSRecalculateTickDelay4 = rangeSetting28.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup7 = new SettingGroup(this, "HurtTime 3", this.settingModeValues);
      booleanSupplier2 = this::isActive21;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime3 = settingGroup7.getSettingGroup(booleanSupplier);
      RangeSetting setting54 = new RangeSetting(this, "SmartCPSHurt3", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime3);
      String text33 = "CPS";
      RangeSetting rangeSetting29 = setting54.getRangeSetting(text33);
      booleanSupplier = this::isActive54;
      this.settingCPS5 = rangeSetting29.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting55 = new RangeSetting(this, "Hurt3SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime3);
      String text34 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting30 = setting55.getRangeSetting(text34).getRangeSetting2();
      booleanSupplier = this::isActive29;
      this.settingCPSRecalculateTickDelay5 = rangeSetting30.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting56 = new RangeSetting(this, "SmartDCPSHurt3", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime3);
      String text35 = "DCPS";
      RangeSetting rangeSetting31 = setting56.getRangeSetting(text35);
      booleanSupplier = this::isActive101;
      this.settingDCPS5 = rangeSetting31.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting57 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt3", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime3);
      String text36 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting32 = setting57.getRangeSetting(text36).getRangeSetting2();
      booleanSupplier = this::isActive4;
      this.settingDCPSRecalculateTickDelay5 = rangeSetting32.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup8 = new SettingGroup(this, "HurtTime 4", this.settingModeValues);
      booleanSupplier2 = this::isActive88;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime4 = settingGroup8.getSettingGroup(booleanSupplier);
      RangeSetting setting58 = new RangeSetting(this, "SmartCPSHurt4", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime4);
      String text37 = "CPS";
      RangeSetting rangeSetting33 = setting58.getRangeSetting(text37);
      booleanSupplier = this::isActive66;
      this.settingCPS6 = rangeSetting33.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting59 = new RangeSetting(this, "Hurt4SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime4);
      String text38 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting34 = setting59.getRangeSetting(text38).getRangeSetting2();
      booleanSupplier = this::isActive93;
      this.settingCPSRecalculateTickDelay6 = rangeSetting34.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting60 = new RangeSetting(this, "SmartDCPSHurt4", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime4);
      String text39 = "DCPS";
      RangeSetting rangeSetting35 = setting60.getRangeSetting(text39);
      booleanSupplier = this::isActive7;
      this.settingDCPS6 = rangeSetting35.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting61 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt4", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime4);
      String text40 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting36 = setting61.getRangeSetting(text40).getRangeSetting2();
      booleanSupplier = this::isActive6;
      this.settingDCPSRecalculateTickDelay6 = rangeSetting36.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup9 = new SettingGroup(this, "HurtTime 5", this.settingModeValues);
      booleanSupplier2 = this::isActive99;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime5 = settingGroup9.getSettingGroup(booleanSupplier);
      RangeSetting setting62 = new RangeSetting(this, "SmartCPSHurt5", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime5);
      String text41 = "CPS";
      RangeSetting rangeSetting37 = setting62.getRangeSetting(text41);
      booleanSupplier = this::isActive48;
      this.settingCPS7 = rangeSetting37.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting63 = new RangeSetting(this, "Hurt5SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime5);
      String text42 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting38 = setting63.getRangeSetting(text42).getRangeSetting2();
      booleanSupplier = this::isActive27;
      this.settingCPSRecalculateTickDelay7 = rangeSetting38.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting64 = new RangeSetting(this, "SmartDCPSHurt5", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime5);
      String text43 = "DCPS";
      RangeSetting rangeSetting39 = setting64.getRangeSetting(text43);
      booleanSupplier = this::isActive40;
      this.settingDCPS7 = rangeSetting39.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting65 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt5", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime5);
      String text44 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting40 = setting65.getRangeSetting(text44).getRangeSetting2();
      booleanSupplier = this::isActive43;
      this.settingDCPSRecalculateTickDelay7 = rangeSetting40.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup10 = new SettingGroup(this, "HurtTime 6", this.settingModeValues);
      booleanSupplier2 = this::isActive97;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime6 = settingGroup10.getSettingGroup(booleanSupplier);
      RangeSetting setting66 = new RangeSetting(this, "SmartCPSHurt6", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime6);
      String text45 = "CPS";
      RangeSetting rangeSetting41 = setting66.getRangeSetting(text45);
      booleanSupplier = this::isActive98;
      this.settingCPS8 = rangeSetting41.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting67 = new RangeSetting(this, "Hurt6SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime6);
      String text46 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting42 = setting67.getRangeSetting(text46).getRangeSetting2();
      booleanSupplier = this::isActive110;
      this.settingCPSRecalculateTickDelay8 = rangeSetting42.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting68 = new RangeSetting(this, "SmartDCPSHurt6", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime6);
      String text47 = "DCPS";
      RangeSetting rangeSetting43 = setting68.getRangeSetting(text47);
      booleanSupplier = this::isActive57;
      this.settingDCPS8 = rangeSetting43.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting69 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt6", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime6);
      String text48 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting44 = setting69.getRangeSetting(text48).getRangeSetting2();
      booleanSupplier = this::isActive23;
      this.settingDCPSRecalculateTickDelay8 = rangeSetting44.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup11 = new SettingGroup(this, "HurtTime 7", this.settingModeValues);
      booleanSupplier2 = this::isActive2;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime7 = settingGroup11.getSettingGroup(booleanSupplier);
      RangeSetting setting70 = new RangeSetting(this, "SmartCPSHurt7", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime7);
      String text49 = "CPS";
      RangeSetting rangeSetting45 = setting70.getRangeSetting(text49);
      booleanSupplier = this::isActive49;
      this.settingCPS9 = rangeSetting45.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting71 = new RangeSetting(this, "Hurt7SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime7);
      String text50 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting46 = setting71.getRangeSetting(text50).getRangeSetting2();
      booleanSupplier = this::isActive16;
      this.settingCPSRecalculateTickDelay9 = rangeSetting46.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting72 = new RangeSetting(this, "SmartDCPSHurt7", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime7);
      String text51 = "DCPS";
      RangeSetting rangeSetting47 = setting72.getRangeSetting(text51);
      booleanSupplier = this::isActive73;
      this.settingDCPS9 = rangeSetting47.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting73 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt7", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime7);
      String text52 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting48 = setting73.getRangeSetting(text52).getRangeSetting2();
      booleanSupplier = this::isActive85;
      this.settingDCPSRecalculateTickDelay9 = rangeSetting48.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup12 = new SettingGroup(this, "HurtTime 8", this.settingModeValues);
      booleanSupplier2 = this::isActive104;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime8 = settingGroup12.getSettingGroup(booleanSupplier);
      RangeSetting setting74 = new RangeSetting(this, "SmartCPSHurt8", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime8);
      String text53 = "CPS";
      RangeSetting rangeSetting49 = setting74.getRangeSetting(text53);
      booleanSupplier = this::isActive109;
      this.settingCPS10 = rangeSetting49.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting75 = new RangeSetting(this, "Hurt8SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime8);
      String text54 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting50 = setting75.getRangeSetting(text54).getRangeSetting2();
      booleanSupplier = this::isActive81;
      this.settingCPSRecalculateTickDelay10 = rangeSetting50.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting76 = new RangeSetting(this, "SmartDCPSHurt8", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime8);
      String text55 = "DCPS";
      RangeSetting rangeSetting51 = setting76.getRangeSetting(text55);
      booleanSupplier = this::isActive89;
      this.settingDCPS10 = rangeSetting51.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting77 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt8", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime8);
      String text56 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting52 = setting77.getRangeSetting(text56).getRangeSetting2();
      booleanSupplier = this::isActive87;
      this.settingDCPSRecalculateTickDelay10 = rangeSetting52.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup13 = new SettingGroup(this, "HurtTime 9", this.settingModeValues);
      booleanSupplier2 = this::isActive80;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime9 = settingGroup13.getSettingGroup(booleanSupplier);
      RangeSetting setting78 = new RangeSetting(this, "SmartCPSHurt9", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime9);
      String text57 = "CPS";
      RangeSetting rangeSetting53 = setting78.getRangeSetting(text57);
      booleanSupplier = this::isActive41;
      this.settingCPS11 = rangeSetting53.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting79 = new RangeSetting(this, "Hurt9SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime9);
      String text58 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting54 = setting79.getRangeSetting(text58).getRangeSetting2();
      booleanSupplier = this::isActive94;
      this.settingCPSRecalculateTickDelay11 = rangeSetting54.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting80 = new RangeSetting(this, "SmartDCPSHurt9", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime9);
      String text59 = "DCPS";
      RangeSetting rangeSetting55 = setting80.getRangeSetting(text59);
      booleanSupplier = this::isActive90;
      this.settingDCPS11 = rangeSetting55.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting81 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt9", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime9);
      String text60 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting56 = setting81.getRangeSetting(text60).getRangeSetting2();
      booleanSupplier = this::isActive56;
      this.settingDCPSRecalculateTickDelay11 = rangeSetting56.getRangeSetting(15343, booleanSupplier);
      SettingGroup settingGroup14 = new SettingGroup(this, "HurtTime 10", this.settingModeValues);
      booleanSupplier2 = this::isActive34;
      booleanSupplier = booleanSupplier2;
      this.settingHurtTime10 = settingGroup14.getSettingGroup(booleanSupplier);
      RangeSetting setting82 = new RangeSetting(this, "SmartCPSHurt10", 17.0, 19.0, 1.0, 40.0, 0, this.settingHurtTime10);
      String text61 = "CPS";
      RangeSetting rangeSetting57 = setting82.getRangeSetting(text61);
      booleanSupplier = this::isActive30;
      this.settingCPS12 = rangeSetting57.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting83 = new RangeSetting(this, "Hurt10SmartCPSRecalculateTickDelay", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime10);
      String text62 = "CPSRecalculateTickDelay";
      RangeSetting rangeSetting58 = setting83.getRangeSetting(text62).getRangeSetting2();
      booleanSupplier = this::isActive96;
      this.settingCPSRecalculateTickDelay12 = rangeSetting58.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting84 = new RangeSetting(this, "SmartDCPSHurt10", 4.0, 5.0, 0.0, 20.0, 0, this.settingHurtTime10);
      String text63 = "DCPS";
      RangeSetting rangeSetting59 = setting84.getRangeSetting(text63);
      booleanSupplier = this::isActive8;
      this.settingDCPS12 = rangeSetting59.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting85 = new RangeSetting(this, "DCPSRecalculateTickDelayHurt10", 3.0, 8.0, 1.0, 40.0, 0, this.settingHurtTime10);
      String text64 = "DCPSRecalculateTickDelay";
      RangeSetting rangeSetting60 = setting85.getRangeSetting(text64).getRangeSetting2();
      booleanSupplier = this::isActive25;
      this.settingDCPSRecalculateTickDelay12 = rangeSetting60.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting86 = new RangeSetting(this, "SmartForceHitsHurtTime", 2.0, 3.0, 0.0, 9.0, 0, this.settingModeValues);
      String text65 = "ForceHitsHurtTimeStart";
      RangeSetting rangeSetting61 = setting86.getRangeSetting(text65);
      booleanSupplier = this::isActive37;
      this.settingForceHitsHurtTimeStart = rangeSetting61.getRangeSetting(15343, booleanSupplier);
      RangeSetting setting87 = new RangeSetting(this, "SmartForceHitsHurtTimeEnd", 10.0, 10.0, 0.0, 10.0, 0, this.settingModeValues);
      String text66 = "ForceHitsHurtTimeEnd";
      RangeSetting rangeSetting62 = setting87.getRangeSetting(text66);
      booleanSupplier = this::isActive76;
      this.settingForceHitsHurtTimeEnd = rangeSetting62.getRangeSetting(15343, booleanSupplier);
      BooleanSetting setting88 = new BooleanSetting(this, "SmartIntaveFix", false, this.settingModeValues);
      String text67 = "IntaveFix";
      BooleanSetting setting89 = (BooleanSetting)setting88.getSetting(text67);
      booleanSupplier = this::isActive15;
      this.settingIntaveFix = (BooleanSetting)setting89.getSetting(booleanSupplier);
      this.settingOnlyOnTarget = new BooleanSetting(this, "OnlyOnTarget", false);
      BooleanSetting setting90 = new BooleanSetting(this, "PerfectHit", false);
      booleanSupplier = this::isActive60;
      this.settingPerfectHit = (BooleanSetting)setting90.getSetting(booleanSupplier);
      BooleanSetting setting91 = new BooleanSetting(this, "DoubleClickFirstHit", true);
      booleanSupplier = this::isActive75;
      this.settingDoubleClickFirstHit = (BooleanSetting)setting91.getSetting(booleanSupplier);
      BooleanSetting setting92 = new BooleanSetting(this, "ReduceCPSIfYouCanNotHitATargett", false);
      String text68 = "ReduceCPSIfYouCanNotHitATarget";
      BooleanSetting setting93 = (BooleanSetting)setting92.getSetting(text68);
      booleanSupplier = this::isActive50;
      this.settingReduceCPSIfYouCanNotHitATarget = (BooleanSetting)setting93.getSetting(booleanSupplier);
      BooleanSetting setting94 = new BooleanSetting(this, "SetReduceCPSToInAirCPS", false);
      booleanSupplier = this::isActive18;
      this.settingSetReduceCPSToInAirCPS = (BooleanSetting)setting94.getSetting(booleanSupplier);
      this.settingCritFix = new BooleanSetting(this, "CritFix", true);
      this.settingLongClick = new BooleanSetting(this, "LongClick", false);
      this.settingAttackFriends = new BooleanSetting(this, "AttackFriends", false);
      this.settingBreakBlocks = new BooleanSetting(this, "BreakBlocks", false);
      NumberSetting setting95 = new NumberSetting(this, "BreakTickDelay", 5.0, 0.0, 20.0, 0);
      booleanSupplier = this.settingBreakBlocks::isEnabled;
      this.settingBreakTickDelay = setting95.getNumberSetting(booleanSupplier);
      this.settingAutoBlock = new BooleanSetting(this, "AutoBlock", false);
      BooleanSetting setting96 = new BooleanSetting(this, "OnlyOnRightClick", true);
      booleanSupplier = this.settingAutoBlock::isEnabled;
      this.settingOnlyOnRightClick = (BooleanSetting)setting96.getSetting(booleanSupplier);
      this.settingOnlyOnClick = new BooleanSetting(this, "OnlyOnClick", true);
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.timer3 = new TimeHelper();
      this.timer4 = new TimeHelper();
      this.timer5 = new TimeHelper();
      this.timer6 = new TimeHelper();
      this.clickHistoryBuffer = new ClickHistoryBuffer(60);
      this.arrayList = new ArrayList();
      this.entityLivingBase = null;
      this.entityLivingBase2 = null;
      this.enabled3 = false;
      this.enabled4 = false;
      this.count6 = 0;
      this.timestamp = 100L;
      this.timestamp2 = 100L;
      this.timestamp3 = 2000L;
      this.timestamp4 = 2000L;
      this.value2 = 1.0;
      this.timestamp5 = 10L;
      this.count7 = 0;
      this.timestamp6 = 0L;
      this.timestamp7 = 0L;
      this.timestamp8 = 0L;
      this.timestamp9 = 1L;
      this.enabled5 = false;
      this.enabled6 = false;
      this.enabled7 = false;
      this.clickPatternProfile = null;
      this.arrayList2 = new ArrayList();
      this.arrayList3 = new ArrayList();
      this.value4 = 1.0;
      this.hashMap = new HashMap();
      this.animationTimer = new AnimationTimer();
      this.animationTimer2 = new AnimationTimer();
      this.animationTimer3 = new AnimationTimer();
      this.animationTimer4 = new AnimationTimer();
      this.animationTimer5 = new AnimationTimer();
      this.animationTimer6 = new AnimationTimer();
      this.animationTimer7 = new AnimationTimer();
      this.animationTimer8 = new AnimationTimer();
      this.animationTimer9 = new AnimationTimer();
      this.animationTimer10 = new AnimationTimer();
      this.animationTimer11 = new AnimationTimer();
      this.animationTimer12 = new AnimationTimer();
      this.animationTimer13 = new AnimationTimer();
      this.animationTimer14 = new AnimationTimer();
      this.animationTimer15 = new AnimationTimer();
      this.animationTimer16 = new AnimationTimer();
      this.animationTimer17 = new AnimationTimer();
      this.animationTimer18 = new AnimationTimer();
      this.animationTimer19 = new AnimationTimer();
      this.animationTimer20 = new AnimationTimer();
      this.animationTimer21 = new AnimationTimer();
      this.animationTimer22 = new AnimationTimer();
      this.animationTimer23 = new AnimationTimer();
      this.animationTimer24 = new AnimationTimer();
      this.animationTimer25 = new AnimationTimer();
      this.animationTimer26 = new AnimationTimer();
      this.animationTimer27 = new AnimationTimer();
      this.animationTimer28 = new AnimationTimer();
      this.timer7 = new TimeHelper();
      this.smoothDamper = new SmoothDamper(20, 0.0);
      this.smoothDamper2 = new SmoothDamper(20, 0.0);
      this.count8 = 0;
      this.count9 = 0;
      ModeSetting settingClickPattern2 = this.settingClickPattern;
      ConsumerTask consumerTask = this::updateState3;
      settingClickPattern2.getModeSetting(consumerTask);
      this.updateState2();
   }

   @Override
   public void onPreDisable() {
      byte byteValue = 0;
      int count = 306858775;
      short shortValue = 9708;
      long value = (long)byteValue << 48 | (long)count << 32 >>> 16 | (long)shortValue << 48 >>> 48;
      this.enabled3 = true;
      this.timestamp = 0L;
      this.enabled6 = true;
      this.arrayList2 = ClickPatternManager.getArrayList();
      ModeSetting settingClickPattern2 = this.settingClickPattern;
      String[] textArray = ClickPatternManager.getTextArray(this.arrayList2);
      settingClickPattern2.applyStringArray2(textArray);
      ModeSetting settingClickPattern3 = this.settingClickPattern;
      String selected = this.settingClickPattern.getSelected();
      ArrayList arrayList4 = this.arrayList2;
      String text = ClickPatternManager.getText(selected, arrayList4);
      settingClickPattern3.setSelected(text);
      String selected2 = this.settingClickPattern.getSelected();
      ArrayList arrayList5 = this.arrayList2;
      this.clickPatternProfile = ClickPatternManager.getClickPatternProfile(selected2, arrayList5);
   }

   @Override
   public void onDisable() {
      this.entityLivingBase = null;
      this.entityLivingBase2 = null;
   }

   @Override
   public void onEnable() {
      this.registerEvent(7);
   }

   @Override
   public void onClick(EventClick event) {
      this.timestamp8++;
      if (((Module)ClientGlobals.moduleManager.aimBot).isToggled() && ClientGlobals.moduleManager.aimBot.target != null) {
         this.entityLivingBase = ClientGlobals.moduleManager.aimBot.target;
      } else if (MinecraftAccessor.minecraft.objectMouseOver != null
         && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
         && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
         this.entityLivingBase = (EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit;
      }

      if (this.settingOnlyOnClick.isEnabled()
         && (MinecraftAccessor.minecraft.gameSettings.keyBindAttack.isKeyDown() || MinecraftAccessor.minecraft.gameSettings.keyBindAttack.getPressTime() > 0)
         && !this.enabled7) {
         this.enabled6 = true;
      }

      this.enabled7 = MinecraftAccessor.minecraft.gameSettings.keyBindAttack.isKeyDown()
         || MinecraftAccessor.minecraft.gameSettings.keyBindAttack.getPressTime() > 0;
      if ((
            MinecraftAccessor.minecraft.gameSettings.keyBindAttack.isKeyDown()
               || MinecraftAccessor.minecraft.gameSettings.keyBindAttack.getPressTime() > 0
               || !this.settingOnlyOnClick.isEnabled()
         )
         && MinecraftAccessor.minecraft.isIntegratedServerRunning()) {
         Rotation rotation = ClientGlobals.rotationManager.getRotation2();
         double offset = (((Module)ClientGlobals.moduleManager.range).isToggled() ? ClientGlobals.moduleManager.range.getCoordinate() : 3.0) + 0.5;
         double offset2 = 0.0;
         double offset3 = offset;
         Rotation rotation2 = rotation;
         float factor = 1.0F;
         MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(factor, rotation2, offset3, offset2);
         if (!this.isActive91()
            && MinecraftAccessor.minecraft.objectMouseOver != null
            && this.settingAutoBlock.isEnabled()
            && MinecraftAccessor.minecraft.thePlayer.getHeldItem() != null
            && MinecraftAccessor.minecraft.thePlayer.getHeldItem().getItem() instanceof ItemSword
            && (
               MinecraftAccessor.minecraft.gameSettings.keyBindUseItem.isKeyDown()
                  || !this.settingOnlyOnRightClick.isEnabled() && movingObjectPosition != null && movingObjectPosition.entityHit instanceof EntityLivingBase
            )
            && this.entityLivingBase != null
            && !ClientGlobals.moduleManager.fixes.canExecute8()) {
            MovingObjectPosition movingObjectPosition2 = CombatRayTrace.getMovingObjectPosition(
               1,
               ClientGlobals.rotationManager.getRotation2(),
               ((Module)ClientGlobals.moduleManager.range).isToggled() ? ClientGlobals.moduleManager.range.getCoordinate() : 3.0
            );
            if ((MinecraftAccessor.minecraft.gameSettings.keyBindUseItem.isKeyDown() || !this.settingOnlyOnRightClick.isEnabled())
               && (this.entityLivingBase.hurtTime == 0 && this.count6 > 4 || this.entityLivingBase.maxHurtTime == 2 || this.entityLivingBase.maxHurtTime == 1)
               && (
                  MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
                     || movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.ENTITY
               )) {
               if (MinecraftAccessor.minecraft.thePlayer.isUsingItem()) {
                  event.setState11(false);
               } else {
                  this.onClick2(event);
                  this.count6 = 0;
                  event.updateInt2(1);
               }
            } else {
               event.setState11(true);
            }
         } else if (!MinecraftAccessor.minecraft.thePlayer.isUsingItem() && !this.isActive91()) {
            int count = this.getCount();
            if (count > 0) {
               if (this.settingOnlyOnTarget.isEnabled()) {
                  if (MinecraftAccessor.minecraft.objectMouseOver != null && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                     while (count > 0) {
                        this.onClick2(event);
                        count--;
                     }

                     this.timer.reset();
                     this.updateState();
                  }
               } else {
                  while (count > 0) {
                     this.onClick2(event);
                     count--;
                  }

                  this.timer.reset();
                  this.updateState();
               }
            }
         }

         if (this.isActive91()) {
            event.setState1(true);
         } else {
            boolean enabled = this.settingLongClick.isEnabled();
            event.setState8(enabled);
         }
      } else {
         this.timestamp = 0L;
         this.value2 = 0.0;
         this.timestamp5 = 20L;
         this.enabled3 = true;
         long timestamp10 = System.currentTimeMillis();
         this.timestamp6 = timestamp10;
         this.timestamp7 = timestamp10;
      }

      this.count6++;
      this.arrayList.add(this.count7);
      this.count7 = 0;
      this.animationTimer.processAction();
      this.animationTimer2.processAction();
      this.animationTimer3.processAction();
      this.animationTimer4.processAction();
      this.animationTimer5.processAction();
      this.animationTimer6.processAction();
      this.animationTimer7.processAction();
      this.animationTimer8.processAction();
      this.animationTimer9.processAction();
      this.animationTimer10.processAction();
      this.animationTimer11.processAction();
      this.animationTimer12.processAction();
      this.animationTimer13.processAction();
      this.animationTimer14.processAction();
      this.animationTimer15.processAction();
      this.animationTimer16.processAction();
      this.animationTimer17.processAction();
      this.animationTimer18.processAction();
      this.animationTimer19.processAction();
      this.animationTimer20.processAction();
      this.animationTimer21.processAction();
      this.animationTimer22.processAction();
      this.animationTimer23.processAction();
      this.animationTimer24.processAction();
      this.animationTimer25.processAction();
      this.animationTimer26.processAction();
      this.animationTimer27.processAction();
      this.animationTimer28.processAction();
   }

   private boolean isActive91() {
      return this.settingBreakBlocks.isEnabled()
         && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK
         && (double)this.timestamp8 > this.settingBreakTickDelay.getValue();
   }

   private void onClick2(EventClick event) {
      if (((Module)ClientGlobals.moduleManager.autoRod).isToggled() && ClientGlobals.moduleManager.autoRod.settingCombat.isEnabled()) {
         MinecraftAccessor.minecraft.playerController.updateController();
         ItemStack stack = MinecraftAccessor.minecraft.thePlayer.container.getSlot(MinecraftAccessor.minecraft.playerController.indexOffset + 36).getStack();
         if (stack != null && stack.getItem() instanceof ItemFishingRod || MinecraftAccessor.minecraft.playerController.timer.hasReached(90L)) {
            this.onClickByEventClick(event);
         }
      } else {
         this.onClickByEventClick(event);
      }
   }

   private boolean isActive64() {
      if (MinecraftAccessor.minecraft.objectMouseOver != null && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
         Entity entityHit = MinecraftAccessor.minecraft.objectMouseOver.entityHit;
         if (ClientGlobals.moduleManager.midClick.checkEntity(entityHit) && !this.settingAttackFriends.isEnabled()) {
            return true;
         }
      }

      return false;
   }

   private void onClickByEventClick(EventClick event) {
      boolean flag = ((Module)ClientGlobals.moduleManager.criticals).isToggled()
         || MinecraftAccessor.minecraft.thePlayer.fallDistance > 0.0F
            && !MinecraftAccessor.minecraft.thePlayer.onGround
            && !MinecraftAccessor.minecraft.thePlayer.canExecute9()
            && !MinecraftAccessor.minecraft.thePlayer.isInWater()
            && !MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion16)
            && MinecraftAccessor.minecraft.thePlayer.ridingEntity == null
            && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
      if (!this.enabled4 && flag) {
         this.enabled4 = true;
      }

      if (!this.isActive64()) {
         this.count7++;
         event.updateInt(1);
         event.setState1(true);
         this.enabled5 = true;
      }

      if (MinecraftAccessor.minecraft.objectMouseOver != null && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
         this.timestamp8 = 0L;
         if (this.settingDoubleClickFirstHit.isEnabled() && (this.enabled3 || this.timer2.hasReached(1500L)) && this.settingDoubleClickFirstHit.isVisible()) {
            this.enabled3 = false;
            if (!this.isActive64()) {
               event.updateInt(1);
               event.setState1(true);
               this.enabled5 = true;
            }
         }

         this.timer2.reset();
      }
   }

   private int getCount() {
      int countAsIndex = this.getCountAsIndex();
      if (this.enabled6) {
         if (countAsIndex <= 0) {
            countAsIndex = 1;
         }

         this.enabled6 = false;
      }

      while (this.arrayList.size() > 19) {
         this.arrayList.remove(0);
      }

      int count = countAsIndex;

      for (Object count2_o : this.arrayList) {
         int count2 = (Integer)count2_o;
         count += count2;
      }

      if (this.settingCPSLimiterIntave.isEnabled() ? this.clickHistoryBuffer.canExecute() : (double)count > this.settingCPSLimiter.getValue()) {
         countAsIndex = 0;
      }

      if (countAsIndex == 0
         && this.settingCritFix.isEnabled()
         && !this.settingSmart.getSelected().equals("DragClick")
         && !this.settingSmart.getSelected().equals("Smart")) {
         boolean flag = ((Module)ClientGlobals.moduleManager.criticals).isToggled()
            || MinecraftAccessor.minecraft.thePlayer.fallDistance > 0.0F
               && !MinecraftAccessor.minecraft.thePlayer.onGround
               && !MinecraftAccessor.minecraft.thePlayer.canExecute9()
               && !MinecraftAccessor.minecraft.thePlayer.isInWater()
               && !MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion16)
               && MinecraftAccessor.minecraft.thePlayer.ridingEntity == null
               && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
         if (!this.enabled4 && flag) {
            return 1;
         }
      }

      return countAsIndex;
   }

   private int getCountAsIndex() {
      long value = 0xf9bc310c098L;
      long keySalt = 0x36f37882e74eL ^ value;
      if (this.entityLivingBase != null && this.entityLivingBase.hurtTime == 10) {
         this.enabled4 = false;
      }

      if (this.settingPerfectHit.isEnabled()
         && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
         && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase
         && !this.settingSmart.getSelected().equals("DragClick")
         && !this.isActive31()) {
         EntityLivingBase entityHit = (EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit;
         if (entityHit.hurtTime == 0 || entityHit.hurtTime == 1) {
            return 1;
         }
      }

      String selected = this.settingSmart.getSelected();
      switch (selected) {
         case "Basic":
            long timestamp10 = this.timestamp;
            return this.timer.hasReached(timestamp10) ? 1 : 0;
         case "Deviation":
            float doubleItem = (float)this.actionSetting2.getDouble3();
            float doubleItem2 = (float)this.actionSetting2.getDouble6();
            float factor = (doubleItem + doubleItem2) / 2.0F;
            float factor2 = (doubleItem2 - doubleItem) / 6.0F;
            long timestamp11 = System.currentTimeMillis();

            int count;
            for (count = 0; this.timestamp6 < timestamp11; count++) {
               double offset = (double)((int)(1000.0F / (float)((double)factor + (double)factor2 * new SecureRandom().nextGaussian())));
               if (MinecraftAccessor.minecraft.objectMouseOver == null || MinecraftAccessor.minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                  offset /= this.settingAirMultiplier.getValue();
               }

               this.timestamp6 = this.timestamp6 + Math.min((long)offset, 1000L);
            }

            if (!this.settingDoubleClick.isEnabled() && count > 1) {
               count = 1;
            }

            return count;
         case "Recorded":
            if (this.clickPatternProfile == null) {
               return 0;
            } else {
               if (this.settingTicks.getSelected().equals("Ticks")) {
                  this.timestamp7 += 50L;
                  int count2 = 0;

                  while (this.timestamp6 < this.timestamp7) {
                     int intItem = this.clickPatternProfile.getInt();
                     count2 += intItem;
                     int count3 = 50;
                     if (MinecraftAccessor.minecraft.objectMouseOver == null
                        || MinecraftAccessor.minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                        count3 = (int)((double)count3 / this.settingAirMultiplier2.getValue());
                     }

                     this.timestamp6 = this.timestamp6 + Math.min((long)((double)count3 * (1.0 / this.settingMultiplier.getValue())), 1000L);
                  }

                  return count2;
               }

               long timestamp12 = System.currentTimeMillis();
               int clickCount = 0;

               while (this.timestamp6 < timestamp12) {
                  clickCount++;
                  int intItem2 = this.clickPatternProfile.getInt();
                  if (MinecraftAccessor.minecraft.objectMouseOver == null || MinecraftAccessor.minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                     intItem2 = (int)((double)intItem2 / this.settingAirMultiplier2.getValue());
                  }

                  this.timestamp6 = this.timestamp6 + Math.min((long)((double)intItem2 * (1.0 / this.settingMultiplier.getValue())), 1000L);
               }

               return clickCount;
            }
         case "Advanced":
            if (this.timestamp == 0L) {
               this.timestamp = this.timestamp5;
            }

            if (this.settingReduceInAir.isEnabled() && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
               long timestamp13 = this.timestamp9;
               if (this.timer6.hasReached(timestamp13)) {
                  SettingProvider settingProvider = SettingContainer.settingProvider;
                  double doubleItem3 = this.actionSetting8.getDouble6();
                  double doubleItem4 = this.actionSetting8.getDouble3();
                  this.timestamp9 = (long)settingProvider.getInt(doubleItem4, doubleItem3);
                  settingProvider = SettingContainer.settingProvider;
                  double doubleItem5 = this.actionSetting7.getDouble6();
                  double doubleItem6 = this.actionSetting7.getDouble3();
                  this.value3 = settingProvider.getDouble4(doubleItem6, doubleItem5);
               }

               this.timestamp = Math.round((double)this.timestamp5 * this.value3);
            }

            if (this.settingSmartClicking.isEnabled()
               && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
               && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase
               && (double)((EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit).hurtTime
                  > this.settingSmartClickingHurttimeEnd.recalculate()) {
               EntityLivingBase entityHit2 = (EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit;
               float yaw = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
               if (CombatRandomizer.matchesEntity(entityHit2, yaw)) {
                  return 0;
               }
            }

            if (MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
               int hurtTime = ((EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit).hurtTime;
               HURTTIMEEnum[] values = HURTTIMEEnum.values();
               int length = values.length;

               for (byte byteValue2 = 0; byteValue2 < length; byteValue2 += 1) {
                  HURTTIMEEnum hURTTIMEEnum = values[byteValue2];
                  if (HURTTIMEEnum.computeCount(hURTTIMEEnum) == hurtTime && !HURTTIMEEnum.matchesHURTTIMEEnum(hURTTIMEEnum)) {
                     return 0;
                  }
               }
            }

            long timestamp14 = this.timestamp;
            if (this.isActive31()) {
               if (this.settingSetReduceCPSToInAirCPS.isEnabled()) {
                  Math.round((double)this.timestamp5 * this.value3);
               } else {
                  keySalt = (long)SettingContainer.settingProvider.getInt2(4, 7);
               }

               timestamp14 = 0L;
               if (ClientGlobals.moduleManager.debug.booleanSetting12 != null && ClientGlobals.moduleManager.debug.booleanSetting12.isEnabled()) {
                  RayTraceUtil.applyString("ReduceHits");
               }
            }

            int count4 = (int)timestamp14;
            return this.smoothDamper2.getInt(count4);
         case "DragClick":
            MovingObjectPosition objectMouseOver = MinecraftAccessor.minecraft.objectMouseOver;
            int count5;
            if (this.arrayList3.size() < 5) {
               count5 = 1;
            } else {
               AnimationTimer animationTimer29 = this.animationTimer4;
               int indexOffset = this.settingMaxCPSRecalculateTickDelay.getIndexOffset2();
               if (animationTimer29.checkCondition(indexOffset)) {
                  this.animationTimer4.processAction3();
                  this.settingMaxCPSRecalculateTickDelay.getDouble4();
                  this.settingMaxCPS.getDouble4();
               }

               ArrayList arrayList4 = this.arrayList3;
               double value5 = this.settingMinStandardDeviation.getValue();
               double indexOffset2 = (double)this.settingMaxCPS.getIndexOffset2();
               double offset2 = value5;
               byte byteValue3 = 21;
               count5 = CombatRandomizer.selectWeightedIndex(arrayList4, byteValue3, offset2, indexOffset2);
            }

            if (objectMouseOver != null
               && objectMouseOver.typeOfHit == MovingObjectType.ENTITY
               && objectMouseOver.entityHit instanceof EntityLivingBase
               && (double)((EntityLivingBase)objectMouseOver.entityHit).hurtTime > this.settingForceHitsHurtTime.recalculate()) {
               EntityLivingBase entityHit3 = (EntityLivingBase)objectMouseOver.entityHit;
               float yaw2 = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
               if (CombatRandomizer.matchesEntity(entityHit3, yaw2)) {
                  count5 = 0;
               }
            }

            if (objectMouseOver == null || objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
               AnimationTimer animationTimer30 = this.animationTimer;
               int indexOffset3 = this.settingCPSInAirRecalculateTickDelay.getIndexOffset2();
               if (animationTimer30.checkCondition(indexOffset3)) {
                  this.animationTimer.processAction3();
                  this.settingCPSInAirRecalculateTickDelay.getDouble4();
                  this.settingCPSInAir.getDouble4();
               }

               SmoothDamper smoothDamper3 = this.smoothDamper2;
               int indexOffset4 = this.settingCPSInAir.getIndexOffset2();
               count5 = smoothDamper3.getInt(indexOffset4);
            } else if (count5 == 0 && this.settingCritFix.isEnabled()) {
               boolean flag = ((Module)ClientGlobals.moduleManager.criticals).isToggled()
                  || MinecraftAccessor.minecraft.thePlayer.fallDistance > 0.0F
                     && !MinecraftAccessor.minecraft.thePlayer.onGround
                     && !MinecraftAccessor.minecraft.thePlayer.canExecute9()
                     && !MinecraftAccessor.minecraft.thePlayer.isInWater()
                     && !MinecraftAccessor.minecraft.thePlayer.checkPotion(Potion.potion16)
                     && MinecraftAccessor.minecraft.thePlayer.ridingEntity == null
                     && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
               if (!this.enabled4 && flag) {
                  count5 = 1;
               }
            }

            this.arrayList3.add(count5);

            while (this.arrayList3.size() > 21) {
               this.arrayList3.remove(0);
            }

            return count5;
         case "Smart":
            EntityLivingBase entityLivingBase3 = this.getEntityLivingBase();
            if (entityLivingBase3 != null) {
               this.entityLivingBase2 = entityLivingBase3;
               this.timer7.reset();
            }

            if (this.timer7.hasReached(3000L)) {
               this.entityLivingBase2 = null;
            }

            MovingObjectPosition objectMouseOver2 = MinecraftAccessor.minecraft.objectMouseOver;
            boolean flag2 = objectMouseOver2 != null && objectMouseOver2.typeOfHit == MovingObjectType.ENTITY && objectMouseOver2.entityHit instanceof EntityLivingBase;
            this.updateState4();
            AnimationTimer animationTimer31 = this.animationTimer5;
            int indexOffset5 = this.settingDCPSRecalculateTickDelay.getIndexOffset2();
            if (animationTimer31.checkCondition(indexOffset5)) {
               this.animationTimer5.processAction3();
               this.settingDCPSRecalculateTickDelay.getDouble4();
               this.settingDCPS.getDouble4();
            }

            int count10;
            if (flag2) {
               int hurtTime2 = this.entityLivingBase2.hurtTime;
               boolean flag3 = (double)((EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit).hurtTime
                     > this.settingForceHitsHurtTimeStart.recalculate()
                  && (double)((EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit).hurtTime
                     <= this.settingForceHitsHurtTimeEnd.recalculate();
               float yaw3 = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
               boolean flag4 = CombatRandomizer.matchesEntity(objectMouseOver2.entityHit, yaw3);
               if (flag3 && flag4) {
                  if (this.count9 <= 0 && this.settingIntaveFix.isEnabled()) {
                     count10 = 1;
                  } else {
                     count10 = 0;
                  }
               } else if (hurtTime2 != this.count8) {
                  double offset3 = (Double)this.hashMap.get(hurtTime2);
                  int count11 = this.computeCount(hurtTime2);
                  if (this.isActive31()) {
                     count11 = 0;
                     if (ClientGlobals.moduleManager.debug.booleanSetting12 != null && ClientGlobals.moduleManager.debug.booleanSetting12.isEnabled()) {
                        RayTraceUtil.applyString("ReduceHits");
                     }
                  }

                  int count12 = (int)offset3;
                  double offset4 = offset3 + (double)Math.max(0, count11) / 20.0;
                  int count13 = (int)offset4;
                  if (offset4 >= (double)Math.max(1, count11)) {
                     offset4 = 0.0;
                  }

                  this.hashMap.remove(hurtTime2);
                  this.hashMap.put(hurtTime2, offset4);
                  count10 = count13 - count12;
                  if (count10 > 0) {
                     int count14 = hurtTime2 + 10;
                     int count15 = this.computeCount(count14);
                     int count16 = hurtTime2 + 10;
                     double offset5 = this.computeValue(count16);
                     int count17 = (int)offset5;
                     double offset6 = offset5 + (double)Math.max(0, count15) / 20.0;
                     int count18 = (int)offset6;
                     if (offset6 >= (double)Math.max(1, count15)) {
                        offset6 = 0.0;
                     }

                     count10 += count18 - count17;
                     int count19 = hurtTime2 + 10;
                     this.applyDouble(offset6, count19);
                  }
               } else {
                  AnimationTimer animationTimer32 = this.animationTimer3;
                  int indexOffset6 = this.settingCPSFirstHitRecalculateTickDelay.getIndexOffset2();
                  if (animationTimer32.checkCondition(indexOffset6)) {
                     this.animationTimer3.processAction3();
                     this.settingCPSFirstHitRecalculateTickDelay.getDouble4();
                     this.settingCPSFirstHit.getDouble4();
                  }

                  int indexOffset7 = this.settingCPSFirstHit.getIndexOffset2();
                  if (this.isActive31()) {
                     AnimationTimer animationTimer33 = this.animationTimer;
                     int indexOffset8 = this.settingCPSInAirRecalculateTickDelay2.getIndexOffset2();
                     if (animationTimer33.checkCondition(indexOffset8)) {
                        this.animationTimer.processAction3();
                        this.settingCPSInAirRecalculateTickDelay2.getDouble4();
                        this.settingCPSInAir2.getDouble4();
                     }

                     if (this.settingSetReduceCPSToInAirCPS.isEnabled()) {
                        this.settingCPSInAir2.getIndexOffset2();
                     } else {
                        SettingContainer.settingProvider.getInt2(4, 7);
                     }

                     indexOffset7 = 0;
                     if (ClientGlobals.moduleManager.debug.booleanSetting12 != null && ClientGlobals.moduleManager.debug.booleanSetting12.isEnabled()) {
                        RayTraceUtil.applyString("ReduceHits");
                     }
                  }

                  double offset7 = (Double)this.hashMap.get(22);
                  int count20 = (int)offset7;
                  double offset8 = offset7 + (double)Math.max(0, indexOffset7) / 20.0;
                  int count21 = (int)offset8;
                  if (offset8 >= (double)Math.max(1, indexOffset7)) {
                     offset8 = 0.0;
                  }

                  this.hashMap.remove(22);
                  this.hashMap.put(22, offset8);
                  count10 = count21 - count20;
                  if (count10 > 0) {
                     double offset9 = this.computeValue(23);
                     int indexOffset9 = this.settingDCPS.getIndexOffset2();
                     int count22 = (int)offset9;
                     double offset10 = offset9 + (double)Math.max(0, indexOffset9) / 20.0;
                     int count23 = (int)offset10;
                     if (offset10 >= (double)Math.max(1, indexOffset9)) {
                        offset10 = 0.0;
                     }

                     count10 += count23 - count22;
                     this.applyDouble(offset10, 23);
                  }
               }

               this.count9 = count10 > 0 ? ++this.count9 : 0;
            } else {
               AnimationTimer animationTimer34 = this.animationTimer;
               int indexOffset10 = this.settingCPSInAirRecalculateTickDelay2.getIndexOffset2();
               if (animationTimer34.checkCondition(indexOffset10)) {
                  this.animationTimer.processAction3();
                  this.settingCPSInAirRecalculateTickDelay2.getDouble4();
                  this.settingCPSInAir2.getDouble4();
               }

               int value6 = (int)this.value2;
               this.value2 = this.value2 + (double)Math.max(0, this.settingCPSInAir2.getIndexOffset2()) / 20.0;
               int value7 = (int)this.value2;
               if (this.value2 >= (double)Math.max(1, this.settingCPSInAir2.getIndexOffset2())) {
                  this.value2 = 0.0;
               }

               count10 = value7 - value6;
               if (count10 > 0) {
                  AnimationTimer animationTimer35 = this.animationTimer2;
                  int indexOffset11 = this.settingDCPSInAirRecalculateTickDelay.getIndexOffset2();
                  if (animationTimer35.checkCondition(indexOffset11)) {
                     this.animationTimer2.processAction3();
                     this.settingDCPSInAirRecalculateTickDelay.getDouble4();
                     this.settingDCPSInAir.getDouble4();
                  }

                  SmoothDamper smoothDamper4 = this.smoothDamper;
                  int indexOffset12 = this.settingDCPSInAir.getIndexOffset2();
                  count10 += smoothDamper4.getInt(indexOffset12);
               }
            }

            if (this.entityLivingBase2 != null) {
               this.count8 = this.entityLivingBase2.hurtTime;
            }

            return count10;
         default:
            return 0;
      }
   }

   private double computeValue(int count) {
      return this.settingDoubleClickMode.getSelected().equals("Default") ? this.value4 : (Double)this.hashMap.get(count);
   }

   private void applyDouble(double offset, int count) {
      if (this.settingDoubleClickMode.getSelected().equals("Default")) {
         this.value4 = offset;
      } else {
         this.hashMap.remove(count);
         this.hashMap.put(count, offset);
      }
   }

   private EntityLivingBase getEntityLivingBase() {
      EntityLivingBase livingEntity = null;
      if (MinecraftAccessor.minecraft.objectMouseOver != null
         && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
         && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
         livingEntity = (EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit;
      }

      return ChatUtil.getEntityLivingBase2(livingEntity);
   }

   private void updateState() {
      String selected = this.settingSmart.getSelected();
      switch (selected) {
         case "Basic":
            SettingProvider settingProvider = SettingContainer.settingProvider;
            double doubleItem = this.actionSetting.getDouble6();
            double doubleItem2 = this.actionSetting.getDouble3();
            this.timestamp = (long)settingProvider.computeValue(doubleItem2, doubleItem);
            break;
         case "Advanced":
            long timestamp10 = this.timestamp2;
            if (this.timer3.hasReached(timestamp10)) {
               SettingProvider settingProvider2 = SettingContainer.settingProvider;
               int doubleItem3 = (int)this.actionSetting3.getDouble6();
               int doubleItem4 = (int)this.actionSetting3.getDouble3();
               this.timestamp5 = (long)settingProvider2.getInt3(doubleItem4, doubleItem3);

               while (this.timestamp5 == this.timestamp && this.actionSetting3.getDouble3() != this.actionSetting3.getDouble6()) {
                  settingProvider2 = SettingContainer.settingProvider;
                  int doubleItem5 = (int)this.actionSetting3.getDouble6();
                  int doubleItem6 = (int)this.actionSetting3.getDouble3();
                  this.timestamp5 = (long)settingProvider2.getInt3(doubleItem6, doubleItem5);
               }

               this.timestamp = this.timestamp5;
               this.timer3.reset();
               settingProvider2 = SettingContainer.settingProvider;
               int doubleItem7 = (int)this.actionSetting4.getDouble6();
               int doubleItem8 = (int)this.actionSetting4.getDouble3();
               this.timestamp2 = (long)settingProvider2.getInt3(doubleItem8, doubleItem7);
            }

            if (this.settingSpikes.isEnabled()) {
               int value5 = (int)this.settingSpikeDelay.getValue();
               long timestamp11 = this.timestamp3;
               if (this.timer5.hasReached(timestamp11)) {
                  this.timestamp = Math.round((double)this.timestamp5 * this.settingSpikeMulti.getValue());
                  TimeHelper timer8 = this.timer5;
                  long timestamp12 = this.timestamp3;
                  SettingProvider settingProvider3 = SettingContainer.settingProvider;
                  int doubleItem9 = (int)this.actionSetting5.getDouble6();
                  int doubleItem10 = (int)this.actionSetting5.getDouble3();
                  long value6 = timestamp12 + (long)settingProvider3.getInt3(doubleItem10, doubleItem9);
                  if (timer8.hasReached(value6)) {
                     SettingProvider settingProvider4 = SettingContainer.settingProvider;
                     int count = Math.max(0, value5 - 100);
                     int count2 = value5 + 100;
                     int count3 = count;
                     this.timestamp3 = (long)settingProvider4.getInt3(count3, count2);
                     this.timer5.reset();
                  }
               }
            }

            if (this.settingDrops.isEnabled()) {
               int value7 = (int)this.settingDropDelay.getValue();
               long timestamp13 = this.timestamp4;
               if (this.timer4.hasReached(timestamp13)) {
                  this.timestamp = Math.round((double)this.timestamp5 * this.settingDropMulti.getValue());
                  TimeHelper timer9 = this.timer4;
                  long timestamp14 = this.timestamp4;
                  SettingProvider settingProvider5 = SettingContainer.settingProvider;
                  int doubleItem11 = (int)this.actionSetting6.getDouble6();
                  int doubleItem12 = (int)this.actionSetting6.getDouble3();
                  long value8 = timestamp14 + (long)settingProvider5.getInt3(doubleItem12, doubleItem11);
                  if (timer9.hasReached(value8)) {
                     SettingProvider settingProvider6 = SettingContainer.settingProvider;
                     int count4 = Math.max(0, value7 - 100);
                     int count5 = value7 + 100;
                     int count10 = count4;
                     this.timestamp4 = (long)settingProvider6.getInt3(count10, count5);
                     this.timer4.reset();
                  }
               }
            }
      }
   }

   private void updateState4() {
      byte byteValue = 0;
      if (this.settingMode.getSelected().equals("Recommended")) {
         AnimationTimer animationTimer29 = this.animationTimer4;
         int indexOffset = this.settingCPSRecalculateTickDelay.getIndexOffset2();
         if (animationTimer29.checkCondition(indexOffset)) {
            this.animationTimer4.processAction3();
            this.settingCPSRecalculateTickDelay.getDouble4();
            this.settingCPS.getDouble4();
         }
      } else {
         AnimationTimer animationTimer30 = this.animationTimer6;
         int indexOffset2 = this.settingCPSRecalculateTickDelay2.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset2)) {
            this.animationTimer6.processAction3();
            this.settingCPSRecalculateTickDelay2.getDouble4();
            this.settingCPS2.getDouble4();
         }

         animationTimer30 = this.animationTimer7;
         int indexOffset3 = this.settingCPSRecalculateTickDelay3.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset3)) {
            this.animationTimer7.processAction3();
            this.settingCPSRecalculateTickDelay3.getDouble4();
            this.settingCPS3.getDouble4();
         }

         animationTimer30 = this.animationTimer8;
         int indexOffset4 = this.settingCPSRecalculateTickDelay4.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset4)) {
            this.animationTimer8.processAction3();
            this.settingCPSRecalculateTickDelay4.getDouble4();
            this.settingCPS4.getDouble4();
         }

         animationTimer30 = this.animationTimer9;
         int indexOffset5 = this.settingCPSRecalculateTickDelay5.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset5)) {
            this.animationTimer9.processAction3();
            this.settingCPSRecalculateTickDelay5.getDouble4();
            this.settingCPS5.getDouble4();
         }

         animationTimer30 = this.animationTimer10;
         int indexOffset6 = this.settingCPSRecalculateTickDelay6.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset6)) {
            this.animationTimer10.processAction3();
            this.settingCPSRecalculateTickDelay6.getDouble4();
            this.settingCPS6.getDouble4();
         }

         animationTimer30 = this.animationTimer11;
         int indexOffset7 = this.settingCPSRecalculateTickDelay7.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset7)) {
            this.animationTimer11.processAction3();
            this.settingCPSRecalculateTickDelay7.getDouble4();
            this.settingCPS7.getDouble4();
         }

         animationTimer30 = this.animationTimer12;
         int indexOffset8 = this.settingCPSRecalculateTickDelay8.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset8)) {
            this.animationTimer12.processAction3();
            this.settingCPSRecalculateTickDelay8.getDouble4();
            this.settingCPS8.getDouble4();
         }

         animationTimer30 = this.animationTimer13;
         int indexOffset9 = this.settingCPSRecalculateTickDelay9.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset9)) {
            this.animationTimer13.processAction3();
            this.settingCPSRecalculateTickDelay9.getDouble4();
            this.settingCPS9.getDouble4();
         }

         animationTimer30 = this.animationTimer14;
         int indexOffset10 = this.settingCPSRecalculateTickDelay10.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset10)) {
            this.animationTimer14.processAction3();
            this.settingCPSRecalculateTickDelay10.getDouble4();
            this.settingCPS10.getDouble4();
         }

         animationTimer30 = this.animationTimer15;
         int indexOffset11 = this.settingCPSRecalculateTickDelay11.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset11)) {
            this.animationTimer15.processAction3();
            this.settingCPSRecalculateTickDelay11.getDouble4();
            this.settingCPS11.getDouble4();
         }

         animationTimer30 = this.animationTimer16;
         int indexOffset12 = this.settingCPSRecalculateTickDelay12.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset12)) {
            this.animationTimer16.processAction3();
            this.settingCPSRecalculateTickDelay12.getDouble4();
            this.settingCPS12.getDouble4();
         }

         animationTimer30 = this.animationTimer17;
         int indexOffset13 = this.settingDCPSRecalculateTickDelay2.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset13)) {
            this.animationTimer17.processAction3();
            this.settingCPSRecalculateTickDelay2.getDouble4();
            this.settingDCPS2.getDouble4();
         }

         animationTimer30 = this.animationTimer18;
         int indexOffset14 = this.settingDCPSRecalculateTickDelay3.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset14)) {
            this.animationTimer18.processAction3();
            this.settingDCPSRecalculateTickDelay3.getDouble4();
            this.settingDCPS3.getDouble4();
         }

         animationTimer30 = this.animationTimer19;
         int indexOffset15 = this.settingDCPSRecalculateTickDelay4.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset15)) {
            this.animationTimer19.processAction3();
            this.settingDCPSRecalculateTickDelay4.getDouble4();
            this.settingDCPS4.getDouble4();
         }

         animationTimer30 = this.animationTimer20;
         int indexOffset16 = this.settingDCPSRecalculateTickDelay5.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset16)) {
            this.animationTimer20.processAction3();
            this.settingDCPSRecalculateTickDelay5.getDouble4();
            this.settingDCPS5.getDouble4();
         }

         animationTimer30 = this.animationTimer21;
         int indexOffset17 = this.settingDCPSRecalculateTickDelay6.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset17)) {
            this.animationTimer21.processAction3();
            this.settingDCPSRecalculateTickDelay6.getDouble4();
            this.settingDCPS6.getDouble4();
         }

         animationTimer30 = this.animationTimer22;
         int indexOffset18 = this.settingDCPSRecalculateTickDelay7.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset18)) {
            this.animationTimer22.processAction3();
            this.settingDCPSRecalculateTickDelay7.getDouble4();
            this.settingDCPS7.getDouble4();
         }

         animationTimer30 = this.animationTimer23;
         int indexOffset19 = this.settingDCPSRecalculateTickDelay8.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset19)) {
            this.animationTimer23.processAction3();
            this.settingDCPSRecalculateTickDelay8.getDouble4();
            this.settingDCPS8.getDouble4();
         }

         animationTimer30 = this.animationTimer24;
         int indexOffset20 = this.settingDCPSRecalculateTickDelay9.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset20)) {
            this.animationTimer24.processAction3();
            this.settingDCPSRecalculateTickDelay9.getDouble4();
            this.settingDCPS9.getDouble4();
         }

         animationTimer30 = this.animationTimer25;
         int indexOffset21 = this.settingDCPSRecalculateTickDelay10.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset21)) {
            this.animationTimer25.processAction3();
            this.settingDCPSRecalculateTickDelay10.getDouble4();
            this.settingDCPS10.getDouble4();
         }

         animationTimer30 = this.animationTimer26;
         int indexOffset22 = this.settingDCPSRecalculateTickDelay11.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset22)) {
            this.animationTimer26.processAction3();
            this.settingDCPSRecalculateTickDelay11.getDouble4();
            this.settingDCPS11.getDouble4();
         }

         animationTimer30 = this.animationTimer27;
         int indexOffset23 = this.settingDCPSRecalculateTickDelay12.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset23)) {
            this.animationTimer27.processAction3();
            this.settingDCPSRecalculateTickDelay12.getDouble4();
            this.settingDCPS12.getDouble4();
         }

         animationTimer30 = this.animationTimer28;
         int indexOffset24 = this.settingDCPSFirstHitRecalculateTickDelay.getIndexOffset2();
         if (animationTimer30.checkCondition(indexOffset24)) {
            this.animationTimer28.processAction3();
            this.settingDCPSFirstHitRecalculateTickDelay.getDouble4();
            this.settingDCPSFirstHit.getDouble4();
         }
      }
   }

   private int computeCount(int count) {
      int count2 = count < 11 ? this.settingCPS.getIndexOffset2() : this.settingDCPS.getIndexOffset2();
      if (!this.settingMode.getSelected().equals("Recommended")) {
         switch (count) {
            case 0:
               count2 = this.settingCPS2.getIndexOffset2();
               break;
            case 1:
               count2 = this.settingCPS3.getIndexOffset2();
               break;
            case 2:
               count2 = this.settingCPS4.getIndexOffset2();
               break;
            case 3:
               count2 = this.settingCPS5.getIndexOffset2();
               break;
            case 4:
               count2 = this.settingCPS6.getIndexOffset2();
               break;
            case 5:
               count2 = this.settingCPS7.getIndexOffset2();
               break;
            case 6:
               count2 = this.settingCPS8.getIndexOffset2();
               break;
            case 7:
               count2 = this.settingCPS9.getIndexOffset2();
               break;
            case 8:
               count2 = this.settingCPS10.getIndexOffset2();
               break;
            case 9:
               count2 = this.settingCPS11.getIndexOffset2();
               break;
            case 10:
               count2 = this.settingCPS12.getIndexOffset2();
               break;
            case 11:
               count2 = this.settingDCPS2.getIndexOffset2();
               break;
            case 12:
               count2 = this.settingDCPS3.getIndexOffset2();
               break;
            case 13:
               count2 = this.settingDCPS4.getIndexOffset2();
               break;
            case 14:
               count2 = this.settingDCPS5.getIndexOffset2();
               break;
            case 15:
               count2 = this.settingDCPS6.getIndexOffset2();
               break;
            case 16:
               count2 = this.settingDCPS7.getIndexOffset2();
               break;
            case 17:
               count2 = this.settingDCPS8.getIndexOffset2();
               break;
            case 18:
               count2 = this.settingDCPS9.getIndexOffset2();
               break;
            case 19:
               count2 = this.settingDCPS10.getIndexOffset2();
               break;
            case 20:
               count2 = this.settingDCPS11.getIndexOffset2();
               break;
            case 21:
               count2 = this.settingDCPS12.getIndexOffset2();
         }
      }

      return count2;
   }

   private void updateState2() {
      this.hashMap.clear();

      for (int index = 0; index < 24; index++) {
         this.hashMap.put(index, (double)index * 1.2 % 20.0);
      }
   }

   private boolean isActive31() {
      return this.settingReduceCPSIfYouCanNotHitATarget.isEnabled()
         && this.entityLivingBase != null
         && this.entityLivingBase.hurtTime == 0
         && System.currentTimeMillis() - this.entityLivingBase.epochMillis > 75L
         && System.currentTimeMillis() - this.entityLivingBase.epochMillis < 225L;
   }

   private void updateState3() {
      String selected = this.settingClickPattern.getSelected();
      ArrayList arrayList4 = this.arrayList2;
      this.clickPatternProfile = ClickPatternManager.getClickPatternProfile(selected, arrayList4);
   }

   private boolean isActive18() {
      return this.settingReduceCPSIfYouCanNotHitATarget.isVisible() && this.settingReduceCPSIfYouCanNotHitATarget.isEnabled();
   }

   private boolean isActive50() {
      return this.settingSmart.getSelected().equals("Advanced") || this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive75() {
      return !this.settingSmart.getSelected().equals("DragClick") && !this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive60() {
      return !this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive15() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive76() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive37() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive25() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive8() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive96() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive30() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive34() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive56() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive90() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive94() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive41() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive80() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive87() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive89() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive81() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive109() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive104() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive85() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive73() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive16() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive49() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive2() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive23() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive57() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive110() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive98() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive97() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive43() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive40() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive27() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive48() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive99() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive6() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive7() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive93() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive66() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive88() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive4() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive101() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive29() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Recommended");
   }

   private boolean isActive54() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive21() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive32() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive58() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive38() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive65() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive20() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive69() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive72() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive22() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive78() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive84() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive108() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive67() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive33() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive12() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive11() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive107() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Recommended");
   }

   private boolean isActive51() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Recommended");
   }

   private boolean isActive53() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Recommended");
   }

   private boolean isActive13() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Recommended");
   }

   private boolean isActive103() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Recommended");
   }

   private boolean isActive71() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive19() {
      return this.settingSmart.getSelected().equals("Smart") && this.settingMode.getSelected().equals("Overengineered");
   }

   private boolean isActive63() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive95() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive52() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive17() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive86() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive77() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive106() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive39() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive59() {
      return this.settingSmart.getSelected().equals("Smart");
   }

   private boolean isActive26() {
      return this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive105() {
      return this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive102() {
      return this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive45() {
      return this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive10() {
      return this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive46() {
      return this.settingSmart.getSelected().equals("DragClick");
   }

   private boolean isActive44() {
      return this.settingSmart.getSelected().equals("Recorded");
   }

   private boolean isActive14() {
      return this.settingSmart.getSelected().equals("Recorded");
   }

   private boolean isActive9() {
      return this.settingSmart.getSelected().equals("Recorded");
   }

   private boolean isActive68() {
      return this.settingSmart.getSelected().equals("Recorded");
   }

   private boolean isActive92() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive74() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive83() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingReduceInAir.isEnabled();
   }

   private boolean isActive28() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingReduceInAir.isEnabled();
   }

   private boolean isActive35() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive47() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingDrops.isEnabled();
   }

   private boolean isActive70() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingDrops.isEnabled();
   }

   private boolean isActive36() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingDrops.isEnabled();
   }

   private boolean isActive82() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive62() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingSpikes.isEnabled();
   }

   private boolean isActive100() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingSpikes.isEnabled();
   }

   private boolean isActiveAsFlag() {
      return this.settingSmart.getSelected().equals("Advanced") && this.settingSpikes.isEnabled();
   }

   private boolean isActive5() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive61() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive24() {
      return this.settingSmart.getSelected().equals("Advanced");
   }

   private boolean isActive42() {
      return this.settingSmart.getSelected().equals("Deviation");
   }

   private boolean isActive55() {
      return this.settingSmart.getSelected().equals("Deviation");
   }

   private boolean isActive79() {
      return this.settingSmart.getSelected().equals("Deviation");
   }

   private boolean isActive3() {
      return this.settingSmart.getSelected().equals("Basic");
   }
}
