package baritone.api;

import baritone.api.utils.SettingsUtil;
import java.lang.reflect.Type;
import baritone.api.utils.NotificationHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3i;

public final class Settings {
   public final Settings.Setting allowBreak = new Settings.Setting(this, "allowBreak", true, false);
   public final Settings.Setting allowBreakAnyway = new Settings.Setting(this, "allowBreakAnyway", new ArrayList(), false);
   public final Settings.Setting allowSprint = new Settings.Setting(this, "allowSprint", true, false);
   public final Settings.Setting allowPlace = new Settings.Setting(this, "allowPlace", true, false);
   public final Settings.Setting allowInventory = new Settings.Setting(this, "allowInventory", false, false);
   public final Settings.Setting ticksBetweenInventoryMoves = new Settings.Setting(this, "ticksBetweenInventoryMoves", 1, false);
   public final Settings.Setting inventoryMoveOnlyIfStationary = new Settings.Setting(this, "inventoryMoveOnlyIfStationary", false, false);
   public final Settings.Setting assumeExternalAutoTool = new Settings.Setting(this, "assumeExternalAutoTool", false, false);
   public final Settings.Setting autoTool = new Settings.Setting(this, "autoTool", true, false);
   public final Settings.Setting blockPlacementPenalty = new Settings.Setting(this, "blockPlacementPenalty", 20.0, false);
   public final Settings.Setting blockBreakAdditionalPenalty = new Settings.Setting(this, "blockBreakAdditionalPenalty", 2.0, false);
   public final Settings.Setting jumpPenalty = new Settings.Setting(this, "jumpPenalty", 2.0, false);
   public final Settings.Setting walkOnWaterOnePenalty = new Settings.Setting(this, "walkOnWaterOnePenalty", 3.0, false);
   public final Settings.Setting strictLiquidCheck = new Settings.Setting(this, "strictLiquidCheck", false, false);
   public final Settings.Setting allowWaterBucketFall = new Settings.Setting(this, "allowWaterBucketFall", true, false);
   public final Settings.Setting assumeWalkOnWater = new Settings.Setting(this, "assumeWalkOnWater", false, false);
   public final Settings.Setting assumeWalkOnLava = new Settings.Setting(this, "assumeWalkOnLava", false, false);
   public final Settings.Setting assumeStep = new Settings.Setting(this, "assumeStep", false, false);
   public final Settings.Setting assumeSafeWalk = new Settings.Setting(this, "assumeSafeWalk", false, false);
   public final Settings.Setting allowJumpAt256 = new Settings.Setting(this, "allowJumpAt256", false, false);
   public final Settings.Setting allowParkourAscend = new Settings.Setting(this, "allowParkourAscend", true, false);
   public final Settings.Setting allowDiagonalDescend = new Settings.Setting(this, "allowDiagonalDescend", false, false);
   public final Settings.Setting allowDiagonalAscend = new Settings.Setting(this, "allowDiagonalAscend", false, false);
   public final Settings.Setting allowDownward = new Settings.Setting(this, "allowDownward", true, false);
   public final Settings.Setting acceptableThrowawayItems = new Settings.Setting(
      this,
      "acceptableThrowawayItems",
      new ArrayList<>(
         Arrays.asList(
            Item.getItemFromBlock(Blocks.dirt),
            Item.getItemFromBlock(Blocks.cobblestone),
            Item.getItemFromBlock(Blocks.netherrack),
            Item.getItemFromBlock(Blocks.stone)
         )
      ),
      false
   );
   public final Settings.Setting blocksToAvoid = new Settings.Setting(this, "blocksToAvoid", new ArrayList(), false);
   public final Settings.Setting blocksToDisallowBreaking = new Settings.Setting(this, "blocksToDisallowBreaking", new ArrayList(), false);
   public final Settings.Setting blocksToAvoidBreaking = new Settings.Setting(
      this,
      "blocksToAvoidBreaking",
      new ArrayList<>(
         Arrays.asList(Blocks.crafting_table, Blocks.furnace, Blocks.lit_furnace, Blocks.chest, Blocks.trapped_chest, Blocks.standing_sign, Blocks.wall_sign)
      ),
      false
   );
   public final Settings.Setting avoidBreakingMultiplier = new Settings.Setting(this, "avoidBreakingMultiplier", 0.1, false);
   public final Settings.Setting buildIgnoreBlocks = new Settings.Setting(this, "buildIgnoreBlocks", new ArrayList(), false);
   public final Settings.Setting buildSkipBlocks = new Settings.Setting(this, "buildSkipBlocks", new ArrayList(), false);
   public final Settings.Setting buildValidSubstitutes = new Settings.Setting(this, "buildValidSubstitutes", new HashMap(), false);
   public final Settings.Setting buildSubstitutes = new Settings.Setting(this, "buildSubstitutes", new HashMap(), false);
   public final Settings.Setting okIfAir = new Settings.Setting(this, "okIfAir", new ArrayList(), false);
   public final Settings.Setting buildIgnoreExisting = new Settings.Setting(this, "buildIgnoreExisting", false, false);
   public final Settings.Setting buildIgnoreDirection = new Settings.Setting(this, "buildIgnoreDirection", false, false);
   public final Settings.Setting buildIgnoreProperties = new Settings.Setting(this, "buildIgnoreProperties", new ArrayList(), false);
   public final Settings.Setting avoidUpdatingFallingBlocks = new Settings.Setting(this, "avoidUpdatingFallingBlocks", true, false);
   public final Settings.Setting allowVines = new Settings.Setting(this, "allowVines", false, false);
   public final Settings.Setting allowWalkOnBottomSlab = new Settings.Setting(this, "allowWalkOnBottomSlab", true, false);
   public final Settings.Setting allowParkour = new Settings.Setting(this, "allowParkour", false, false);
   public final Settings.Setting allowParkourPlace = new Settings.Setting(this, "allowParkourPlace", false, false);
   public final Settings.Setting considerPotionEffects = new Settings.Setting(this, "considerPotionEffects", true, false);
   public final Settings.Setting sprintAscends = new Settings.Setting(this, "sprintAscends", true, false);
   public final Settings.Setting overshootTraverse = new Settings.Setting(this, "overshootTraverse", true, false);
   public final Settings.Setting pauseMiningForFallingBlocks = new Settings.Setting(this, "pauseMiningForFallingBlocks", true, false);
   public final Settings.Setting rightClickSpeed = new Settings.Setting(this, "rightClickSpeed", 4, false);
   public final Settings.Setting blockReachDistance = new Settings.Setting(this, "blockReachDistance", 4.5F, false);
   public final Settings.Setting randomLooking = new Settings.Setting(this, "randomLooking", 0.01, false);
   public final Settings.Setting costHeuristic = new Settings.Setting(this, "costHeuristic", 3.563, false);
   public final Settings.Setting pathingMaxChunkBorderFetch = new Settings.Setting(this, "pathingMaxChunkBorderFetch", 50, false);
   public final Settings.Setting backtrackCostFavoringCoefficient = new Settings.Setting(this, "backtrackCostFavoringCoefficient", 0.5, false);
   public final Settings.Setting avoidance = new Settings.Setting(this, "avoidance", false, false);
   public final Settings.Setting mobSpawnerAvoidanceCoefficient = new Settings.Setting(this, "mobSpawnerAvoidanceCoefficient", 2.0, false);
   public final Settings.Setting mobSpawnerAvoidanceRadius = new Settings.Setting(this, "mobSpawnerAvoidanceRadius", 16, false);
   public final Settings.Setting mobAvoidanceCoefficient = new Settings.Setting(this, "mobAvoidanceCoefficient", 1.5, false);
   public final Settings.Setting mobAvoidanceRadius = new Settings.Setting(this, "mobAvoidanceRadius", 8, false);
   public final Settings.Setting rightClickContainerOnArrival = new Settings.Setting(this, "rightClickContainerOnArrival", true, false);
   public final Settings.Setting enterPortal = new Settings.Setting(this, "enterPortal", true, false);
   public final Settings.Setting minimumImprovementRepropagation = new Settings.Setting(this, "minimumImprovementRepropagation", true, false);
   public final Settings.Setting cutoffAtLoadBoundary = new Settings.Setting(this, "cutoffAtLoadBoundary", false, false);
   public final Settings.Setting maxCostIncrease = new Settings.Setting(this, "maxCostIncrease", 10.0, false);
   public final Settings.Setting costVerificationLookahead = new Settings.Setting(this, "costVerificationLookahead", 5, false);
   public final Settings.Setting pathCutoffFactor = new Settings.Setting(this, "pathCutoffFactor", 0.9, false);
   public final Settings.Setting pathCutoffMinimumLength = new Settings.Setting(this, "pathCutoffMinimumLength", 30, false);
   public final Settings.Setting planningTickLookahead = new Settings.Setting(this, "planningTickLookahead", 150, false);
   public final Settings.Setting pathingMapDefaultSize = new Settings.Setting(this, "pathingMapDefaultSize", 1024, false);
   public final Settings.Setting pathingMapLoadFactor = new Settings.Setting(this, "pathingMapLoadFactor", 0.75F, false);
   public final Settings.Setting maxFallHeightNoWater = new Settings.Setting(this, "maxFallHeightNoWater", 3, false);
   public final Settings.Setting maxFallHeightBucket = new Settings.Setting(this, "maxFallHeightBucket", 20, false);
   public final Settings.Setting allowOvershootDiagonalDescend = new Settings.Setting(this, "allowOvershootDiagonalDescend", true, false);
   public final Settings.Setting simplifyUnloadedYCoord = new Settings.Setting(this, "simplifyUnloadedYCoord", true, false);
   public final Settings.Setting repackOnAnyBlockChange = new Settings.Setting(this, "repackOnAnyBlockChange", true, false);
   public final Settings.Setting movementTimeoutTicks = new Settings.Setting(this, "movementTimeoutTicks", 100, false);
   public final Settings.Setting primaryTimeoutMS = new Settings.Setting(this, "primaryTimeoutMS", 500L, false);
   public final Settings.Setting failureTimeoutMS = new Settings.Setting(this, "failureTimeoutMS", 2000L, false);
   public final Settings.Setting planAheadPrimaryTimeoutMS = new Settings.Setting(this, "planAheadPrimaryTimeoutMS", 4000L, false);
   public final Settings.Setting planAheadFailureTimeoutMS = new Settings.Setting(this, "planAheadFailureTimeoutMS", 5000L, false);
   public final Settings.Setting slowPath = new Settings.Setting(this, "slowPath", false, false);
   public final Settings.Setting slowPathTimeDelayMS = new Settings.Setting(this, "slowPathTimeDelayMS", 100L, false);
   public final Settings.Setting slowPathTimeoutMS = new Settings.Setting(this, "slowPathTimeoutMS", 40000L, false);
   public final Settings.Setting doBedWaypoints = new Settings.Setting(this, "doBedWaypoints", true, false);
   public final Settings.Setting doDeathWaypoints = new Settings.Setting(this, "doDeathWaypoints", true, false);
   public final Settings.Setting chunkCaching = new Settings.Setting(this, "chunkCaching", true, false);
   public final Settings.Setting pruneRegionsFromRAM = new Settings.Setting(this, "pruneRegionsFromRAM", true, false);
   public final Settings.Setting chunkPackerQueueMaxSize = new Settings.Setting(this, "chunkPackerQueueMaxSize", 2000, false);
   public final Settings.Setting backfill = new Settings.Setting(this, "backfill", false, false);
   public final Settings.Setting logAsToast = new Settings.Setting(this, "logAsToast", false, false);
   public final Settings.Setting toastTimer = new Settings.Setting(this, "toastTimer", 5000L, false);
   public final Settings.Setting chatDebug = new Settings.Setting(this, "chatDebug", false, false);
   public final Settings.Setting chatControl = new Settings.Setting(this, "chatControl", true, false);
   public final Settings.Setting chatControlAnyway = new Settings.Setting(this, "chatControlAnyway", false, false);
   public final Settings.Setting renderPath = new Settings.Setting(this, "renderPath", true, false);
   public final Settings.Setting renderPathAsLine = new Settings.Setting(this, "renderPathAsLine", false, false);
   public final Settings.Setting renderGoal = new Settings.Setting(this, "renderGoal", true, false);
   public final Settings.Setting renderGoalAnimated = new Settings.Setting(this, "renderGoalAnimated", true, false);
   public final Settings.Setting renderSelectionBoxes = new Settings.Setting(this, "renderSelectionBoxes", true, false);
   public final Settings.Setting renderGoalIgnoreDepth = new Settings.Setting(this, "renderGoalIgnoreDepth", true, false);
   public final Settings.Setting renderGoalXZBeacon = new Settings.Setting(this, "renderGoalXZBeacon", false, false);
   public final Settings.Setting renderSelectionBoxesIgnoreDepth = new Settings.Setting(this, "renderSelectionBoxesIgnoreDepth", true, false);
   public final Settings.Setting renderPathIgnoreDepth = new Settings.Setting(this, "renderPathIgnoreDepth", true, false);
   public final Settings.Setting pathRenderLineWidthPixels = new Settings.Setting(this, "pathRenderLineWidthPixels", 5.0F, false);
   public final Settings.Setting goalRenderLineWidthPixels = new Settings.Setting(this, "goalRenderLineWidthPixels", 3.0F, false);
   public final Settings.Setting fadePath = new Settings.Setting(this, "fadePath", false, false);
   public final Settings.Setting freeLook = new Settings.Setting(this, "freeLook", true, false);
   public final Settings.Setting blockFreeLook = new Settings.Setting(this, "blockFreeLook", false, false);
   public final Settings.Setting antiCheatCompatibility = new Settings.Setting(this, "antiCheatCompatibility", true, false);
   public final Settings.Setting pathThroughCachedOnly = new Settings.Setting(this, "pathThroughCachedOnly", false, false);
   public final Settings.Setting sprintInWater = new Settings.Setting(this, "sprintInWater", true, false);
   public final Settings.Setting blacklistClosestOnFailure = new Settings.Setting(this, "blacklistClosestOnFailure", true, false);
   public final Settings.Setting renderCachedChunks = new Settings.Setting(this, "renderCachedChunks", false, false);
   public final Settings.Setting cachedChunksOpacity = new Settings.Setting(this, "cachedChunksOpacity", 0.5F, false);
   public final Settings.Setting prefixControl = new Settings.Setting(this, "prefixControl", true, false);
   public final Settings.Setting prefix = new Settings.Setting(this, "prefix", "#", false);
   public final Settings.Setting shortBaritonePrefix = new Settings.Setting(this, "shortBaritonePrefix", false, false);
   public final Settings.Setting echoCommands = new Settings.Setting(this, "echoCommands", true, false);
   public final Settings.Setting censorCoordinates = new Settings.Setting(this, "censorCoordinates", false, false);
   public final Settings.Setting censorRanCommands = new Settings.Setting(this, "censorRanCommands", false, false);
   public final Settings.Setting itemSaver = new Settings.Setting(this, "itemSaver", false, false);
   public final Settings.Setting itemSaverThreshold = new Settings.Setting(this, "itemSaverThreshold", 10);
   public final Settings.Setting preferSilkTouch = new Settings.Setting(this, "preferSilkTouch", false);
   public final Settings.Setting walkWhileBreaking = new Settings.Setting(this, "walkWhileBreaking", true);
   public final Settings.Setting splicePath = new Settings.Setting(this, "splicePath", true);
   public final Settings.Setting maxPathHistoryLength = new Settings.Setting(this, "maxPathHistoryLength", 300);
   public final Settings.Setting pathHistoryCutoffAmount = new Settings.Setting(this, "pathHistoryCutoffAmount", 50);
   public final Settings.Setting mineGoalUpdateInterval = new Settings.Setting(this, "mineGoalUpdateInterval", 5);
   public final Settings.Setting maxCachedWorldScanCount = new Settings.Setting(this, "maxCachedWorldScanCount", 10);
   public final Settings.Setting minYLevelWhileMining = new Settings.Setting(this, "minYLevelWhileMining", 0);
   public final Settings.Setting maxYLevelWhileMining = new Settings.Setting(this, "maxYLevelWhileMining", 255);
   public final Settings.Setting allowOnlyExposedOres = new Settings.Setting(this, "allowOnlyExposedOres", false);
   public final Settings.Setting allowOnlyExposedOresDistance = new Settings.Setting(this, "allowOnlyExposedOresDistance", 1);
   public final Settings.Setting exploreForBlocks = new Settings.Setting(this, "exploreForBlocks", true);
   public final Settings.Setting worldExploringChunkOffset = new Settings.Setting(this, "worldExploringChunkOffset", 0);
   public final Settings.Setting exploreChunkSetMinimumSize = new Settings.Setting(this, "exploreChunkSetMinimumSize", 10);
   public final Settings.Setting exploreMaintainY = new Settings.Setting(this, "exploreMaintainY", 64);
   public final Settings.Setting replantCrops = new Settings.Setting(this, "replantCrops", true);
   public final Settings.Setting replantNetherWart = new Settings.Setting(this, "replantNetherWart", false);
   public final Settings.Setting extendCacheOnThreshold = new Settings.Setting(this, "extendCacheOnThreshold", false);
   public final Settings.Setting buildInLayers = new Settings.Setting(this, "buildInLayers", false);
   public final Settings.Setting layerOrder = new Settings.Setting(this, "layerOrder", false);
   public final Settings.Setting layerHeight = new Settings.Setting(this, "layerHeight", 1);
   public final Settings.Setting startAtLayer = new Settings.Setting(this, "startAtLayer", 0);
   public final Settings.Setting skipFailedLayers = new Settings.Setting(this, "skipFailedLayers", false);
   public final Settings.Setting buildOnlySelection = new Settings.Setting(this, "buildOnlySelection", false);
   public final Settings.Setting buildRepeat = new Settings.Setting(this, "buildRepeat", new Vec3i(0, 0, 0));
   public final Settings.Setting buildRepeatCount = new Settings.Setting(this, "buildRepeatCount", -1);
   public final Settings.Setting buildRepeatSneaky = new Settings.Setting(this, "buildRepeatSneaky", true);
   public final Settings.Setting breakFromAbove = new Settings.Setting(this, "breakFromAbove", false);
   public final Settings.Setting goalBreakFromAbove = new Settings.Setting(this, "goalBreakFromAbove", false);
   public final Settings.Setting mapArtMode = new Settings.Setting(this, "mapArtMode", false);
   public final Settings.Setting okIfWater = new Settings.Setting(this, "okIfWater", false);
   public final Settings.Setting incorrectSize = new Settings.Setting(this, "incorrectSize", 100);
   public final Settings.Setting breakCorrectBlockPenaltyMultiplier = new Settings.Setting(this, "breakCorrectBlockPenaltyMultiplier", 10.0);
   public final Settings.Setting schematicOrientationX = new Settings.Setting(this, "schematicOrientationX", false);
   public final Settings.Setting schematicOrientationY = new Settings.Setting(this, "schematicOrientationY", false);
   public final Settings.Setting schematicOrientationZ = new Settings.Setting(this, "schematicOrientationZ", false);
   public final Settings.Setting schematicFallbackExtension = new Settings.Setting(this, "schematicFallbackExtension", "schematic");
   public final Settings.Setting builderTickScanRadius = new Settings.Setting(this, "builderTickScanRadius", 5);
   public final Settings.Setting mineScanDroppedItems = new Settings.Setting(this, "mineScanDroppedItems", true);
   public final Settings.Setting mineDropLoiterDurationMSThanksLouca = new Settings.Setting(this, "mineDropLoiterDurationMSThanksLouca", 250L);
   public final Settings.Setting distanceTrim = new Settings.Setting(this, "distanceTrim", true);
   public final Settings.Setting cancelOnGoalInvalidation = new Settings.Setting(this, "cancelOnGoalInvalidation", true);
   public final Settings.Setting axisHeight = new Settings.Setting(this, "axisHeight", 120);
   public final Settings.Setting disconnectOnArrival = new Settings.Setting(this, "disconnectOnArrival", false);
   public final Settings.Setting legitMine = new Settings.Setting(this, "legitMine", false);
   public final Settings.Setting legitMineYLevel = new Settings.Setting(this, "legitMineYLevel", 11);
   public final Settings.Setting legitMineIncludeDiagonals = new Settings.Setting(this, "legitMineIncludeDiagonals", false);
   public final Settings.Setting forceInternalMining = new Settings.Setting(this, "forceInternalMining", true);
   public final Settings.Setting internalMiningAirException = new Settings.Setting(this, "internalMiningAirException", true);
   public final Settings.Setting followOffsetDistance = new Settings.Setting(this, "followOffsetDistance", 0.0);
   public final Settings.Setting followOffsetDirection = new Settings.Setting(this, "followOffsetDirection", 0.0F);
   public final Settings.Setting followRadius = new Settings.Setting(this, "followRadius", 3);
   public final Settings.Setting disableCompletionCheck = new Settings.Setting(this, "disableCompletionCheck", false);
   public final Settings.Setting cachedChunksExpirySeconds = new Settings.Setting(this, "cachedChunksExpirySeconds", -1L);
   @JavaOnly
   public final Settings.Setting logger = new Settings.Setting(
      this, "logger", (Consumer<IChatComponent>)component -> Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component), true
   );
   @JavaOnly
   public final Settings.Setting notifier = new Settings.Setting(this, "notifier", (BiConsumer<String, Boolean>)NotificationHelper::notify, true);
   public final Settings.Setting yLevelBoxSize = new Settings.Setting(this, "yLevelBoxSize", 15.0);
   public final Settings.Setting colorCurrentPath = new Settings.Setting(this, "colorCurrentPath", Color.RED);
   public final Settings.Setting colorNextPath = new Settings.Setting(this, "colorNextPath", Color.MAGENTA);
   public final Settings.Setting colorBlocksToBreak = new Settings.Setting(this, "colorBlocksToBreak", Color.RED);
   public final Settings.Setting colorBlocksToPlace = new Settings.Setting(this, "colorBlocksToPlace", Color.GREEN);
   public final Settings.Setting colorBlocksToWalkInto = new Settings.Setting(this, "colorBlocksToWalkInto", Color.MAGENTA);
   public final Settings.Setting colorBestPathSoFar = new Settings.Setting(this, "colorBestPathSoFar", Color.BLUE);
   public final Settings.Setting colorMostRecentConsidered = new Settings.Setting(this, "colorMostRecentConsidered", Color.CYAN);
   public final Settings.Setting colorGoalBox = new Settings.Setting(this, "colorGoalBox", Color.GREEN);
   public final Settings.Setting colorInvertedGoalBox = new Settings.Setting(this, "colorInvertedGoalBox", Color.RED);
   public final Settings.Setting colorSelection = new Settings.Setting(this, "colorSelection", Color.CYAN);
   public final Settings.Setting colorSelectionPos1 = new Settings.Setting(this, "colorSelectionPos1", Color.BLACK);
   public final Settings.Setting colorSelectionPos2 = new Settings.Setting(this, "colorSelectionPos2", Color.ORANGE);
   public final Settings.Setting selectionOpacity = new Settings.Setting(this, "selectionOpacity", 0.5F);
   public final Settings.Setting selectionLineWidth = new Settings.Setting(this, "selectionLineWidth", 2.0F);
   public final Settings.Setting renderSelection = new Settings.Setting(this, "renderSelection", true);
   public final Settings.Setting renderSelectionIgnoreDepth = new Settings.Setting(this, "renderSelectionIgnoreDepth", true);
   public final Settings.Setting renderSelectionCorners = new Settings.Setting(this, "renderSelectionCorners", true);
   public final Settings.Setting useSwordToMine = new Settings.Setting(this, "useSwordToMine", true);
   public final Settings.Setting desktopNotifications = new Settings.Setting(this, "desktopNotifications", false);
   public final Settings.Setting notificationOnPathComplete = new Settings.Setting(this, "notificationOnPathComplete", true);
   public final Settings.Setting notificationOnFarmFail = new Settings.Setting(this, "notificationOnFarmFail", true);
   public final Settings.Setting notificationOnBuildFinished = new Settings.Setting(this, "notificationOnBuildFinished", true);
   public final Settings.Setting notificationOnExploreFinished = new Settings.Setting(this, "notificationOnExploreFinished", true);
   public final Settings.Setting notificationOnMineFail = new Settings.Setting(this, "notificationOnMineFail", true);
   public final Map byLowerName;
   public final List<Settings.Setting> allSettings;
   public final Map settingTypes;

   public List<Settings.Setting> getAllValuesByType(Class clazz) {
      ArrayList list = new ArrayList();

      for (Settings.Setting setting : (Iterable<Settings.Setting>)(this.allSettings)) {
         if (setting.getValueClass().equals(clazz)) {
            list.add(setting);
         }
      }

      return list;
   }

   public Settings(SettingsRegistry settingsRegistry) {
      this.byLowerName = Collections.unmodifiableMap(settingsRegistry.byLowerName);
      this.allSettings = Collections.unmodifiableList(settingsRegistry.allSettings);
      this.settingTypes = Collections.unmodifiableMap(settingsRegistry.settingTypes);
   }


   public static final class Setting {
      public Object value;
      public final Object defaultValue;
      private String name;
      private boolean javaOnly;
      public final Settings settings;

      public String getName() {
         return this.name;
      }

      public Setting(Settings settings2, String str, Object obj, boolean flag) {
         this.settings = settings2;
         this.name = str;
         this.value = obj;
         this.defaultValue = obj;
         this.javaOnly = flag;
         this.register();
      }

      public Class getValueClass() {
         return this.value.getClass();
      }

      public void reset() {
         this.value = this.defaultValue;
      }

      public Setting(Settings settings2, String str, Object obj) {
         this.settings = settings2;
         this.name = str;
         this.value = obj;
         this.defaultValue = obj;
         this.javaOnly = false;
         this.register();
      }

      public boolean isJavaOnly() {
         return this.javaOnly;
      }

      private void register() {
         BaritoneAPI.getSettingsRegistry().allSettings.add(this);
         BaritoneAPI.getSettingsRegistry().byLowerName.put(this.name.toLowerCase(), this);
         BaritoneAPI.getSettingsRegistry().settingTypes.put(this, this.value.getClass());
      }

      @Override
      public String toString() {
         return SettingsUtil.settingToString(this);
      }

      public Type getType() {
         return (Type)this.settings.settingTypes.get(this);
      }
   }
}
