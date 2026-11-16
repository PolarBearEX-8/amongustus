package cc;

import cc.baritone.utils.AuxiliaryMapEntry;
import cc.baritone.utils.AuxiliaryStringEntry;
import cc.baritone.utils.BreadthFirstCalculator;
import cc.baritone.utils.OsascriptManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3i;

public final class Settings {
   public final AuxiliaryStringEntry allowBreak = new AuxiliaryStringEntry(this, "allowBreak", true, false);
   public final AuxiliaryStringEntry allowBreakAnyway = new AuxiliaryStringEntry(this, "allowBreakAnyway", new ArrayList(), false);
   public final AuxiliaryStringEntry allowSprint = new AuxiliaryStringEntry(this, "allowSprint", true, false);
   public final AuxiliaryStringEntry allowPlace = new AuxiliaryStringEntry(this, "allowPlace", true, false);
   public final AuxiliaryStringEntry allowInventory = new AuxiliaryStringEntry(this, "allowInventory", false, false);
   public final AuxiliaryStringEntry ticksBetweenInventoryMoves = new AuxiliaryStringEntry(this, "ticksBetweenInventoryMoves", 1, false);
   public final AuxiliaryStringEntry inventoryMoveOnlyIfStationary = new AuxiliaryStringEntry(this, "inventoryMoveOnlyIfStationary", false, false);
   public final AuxiliaryStringEntry assumeExternalAutoTool = new AuxiliaryStringEntry(this, "assumeExternalAutoTool", false, false);
   public final AuxiliaryStringEntry autoTool = new AuxiliaryStringEntry(this, "autoTool", true, false);
   public final AuxiliaryStringEntry blockPlacementPenalty = new AuxiliaryStringEntry(this, "blockPlacementPenalty", 20.0, false);
   public final AuxiliaryStringEntry blockBreakAdditionalPenalty = new AuxiliaryStringEntry(this, "blockBreakAdditionalPenalty", 2.0, false);
   public final AuxiliaryStringEntry jumpPenalty = new AuxiliaryStringEntry(this, "jumpPenalty", 2.0, false);
   public final AuxiliaryStringEntry walkOnWaterOnePenalty = new AuxiliaryStringEntry(this, "walkOnWaterOnePenalty", 3.0, false);
   public final AuxiliaryStringEntry strictLiquidCheck = new AuxiliaryStringEntry(this, "strictLiquidCheck", false, false);
   public final AuxiliaryStringEntry allowWaterBucketFall = new AuxiliaryStringEntry(this, "allowWaterBucketFall", true, false);
   public final AuxiliaryStringEntry assumeWalkOnWater = new AuxiliaryStringEntry(this, "assumeWalkOnWater", false, false);
   public final AuxiliaryStringEntry assumeWalkOnLava = new AuxiliaryStringEntry(this, "assumeWalkOnLava", false, false);
   public final AuxiliaryStringEntry assumeStep = new AuxiliaryStringEntry(this, "assumeStep", false, false);
   public final AuxiliaryStringEntry assumeSafeWalk = new AuxiliaryStringEntry(this, "assumeSafeWalk", false, false);
   public final AuxiliaryStringEntry allowJumpAt256 = new AuxiliaryStringEntry(this, "allowJumpAt256", false, false);
   public final AuxiliaryStringEntry allowParkourAscend = new AuxiliaryStringEntry(this, "allowParkourAscend", true, false);
   public final AuxiliaryStringEntry allowDiagonalDescend = new AuxiliaryStringEntry(this, "allowDiagonalDescend", false, false);
   public final AuxiliaryStringEntry allowDiagonalAscend = new AuxiliaryStringEntry(this, "allowDiagonalAscend", false, false);
   public final AuxiliaryStringEntry allowDownward = new AuxiliaryStringEntry(this, "allowDownward", true, false);
   public final AuxiliaryStringEntry acceptableThrowawayItems = new AuxiliaryStringEntry(
      this,
      "acceptableThrowawayItems",
      new ArrayList<>(
         Arrays.asList(
            Item.processAction2(Blocks.block115), Item.processAction2(Blocks.block65), Item.processAction2(Blocks.block96), Item.processAction2(Blocks.stone)
         )
      ),
      false
   );
   public final AuxiliaryStringEntry blocksToAvoid = new AuxiliaryStringEntry(this, "blocksToAvoid", new ArrayList(), false);
   public final AuxiliaryStringEntry blocksToDisallowBreaking = new AuxiliaryStringEntry(this, "blocksToDisallowBreaking", new ArrayList(), false);
   public final AuxiliaryStringEntry blocksToAvoidBreaking = new AuxiliaryStringEntry(
      this,
      "blocksToAvoidBreaking",
      new ArrayList<>(Arrays.asList(Blocks.block46, Blocks.block39, Blocks.block88, Blocks.blockTrappedChest, Blocks.block128, Blocks.block37, Blocks.block108)),
      false
   );
   public final AuxiliaryStringEntry avoidBreakingMultiplier = new AuxiliaryStringEntry(this, "avoidBreakingMultiplier", 0.1, false);
   public final AuxiliaryStringEntry buildIgnoreBlocks = new AuxiliaryStringEntry(this, "buildIgnoreBlocks", new ArrayList(), false);
   public final AuxiliaryStringEntry buildSkipBlocks = new AuxiliaryStringEntry(this, "buildSkipBlocks", new ArrayList(), false);
   public final AuxiliaryStringEntry buildValidSubstitutes = new AuxiliaryStringEntry(this, "buildValidSubstitutes", new HashMap(), false);
   public final AuxiliaryStringEntry buildSubstitutes = new AuxiliaryStringEntry(this, "buildSubstitutes", new HashMap(), false);
   public final AuxiliaryStringEntry okIfAir = new AuxiliaryStringEntry(this, "okIfAir", new ArrayList(), false);
   public final AuxiliaryStringEntry buildIgnoreExisting = new AuxiliaryStringEntry(this, "buildIgnoreExisting", false, false);
   public final AuxiliaryStringEntry buildIgnoreDirection = new AuxiliaryStringEntry(this, "buildIgnoreDirection", false, false);
   public final AuxiliaryStringEntry buildIgnoreProperties = new AuxiliaryStringEntry(this, "buildIgnoreProperties", new ArrayList(), false);
   public final AuxiliaryStringEntry avoidUpdatingFallingBlocks = new AuxiliaryStringEntry(this, "avoidUpdatingFallingBlocks", true, false);
   public final AuxiliaryStringEntry allowVines = new AuxiliaryStringEntry(this, "allowVines", false, false);
   public final AuxiliaryStringEntry allowWalkOnBottomSlab = new AuxiliaryStringEntry(this, "allowWalkOnBottomSlab", true, false);
   public final AuxiliaryStringEntry allowParkour = new AuxiliaryStringEntry(this, "allowParkour", false, false);
   public final AuxiliaryStringEntry allowParkourPlace = new AuxiliaryStringEntry(this, "allowParkourPlace", false, false);
   public final AuxiliaryStringEntry considerPotionEffects = new AuxiliaryStringEntry(this, "considerPotionEffects", true, false);
   public final AuxiliaryStringEntry sprintAscends = new AuxiliaryStringEntry(this, "sprintAscends", true, false);
   public final AuxiliaryStringEntry overshootTraverse = new AuxiliaryStringEntry(this, "overshootTraverse", true, false);
   public final AuxiliaryStringEntry pauseMiningForFallingBlocks = new AuxiliaryStringEntry(this, "pauseMiningForFallingBlocks", true, false);
   public final AuxiliaryStringEntry rightClickSpeed = new AuxiliaryStringEntry(this, "rightClickSpeed", 4, false);
   public final AuxiliaryStringEntry blockReachDistance = new AuxiliaryStringEntry(this, "blockReachDistance", 4.5F, false);
   public final AuxiliaryStringEntry randomLooking = new AuxiliaryStringEntry(this, "randomLooking", 0.01, false);
   public final AuxiliaryStringEntry costHeuristic = new AuxiliaryStringEntry(this, "costHeuristic", 3.563, false);
   public final AuxiliaryStringEntry pathingMaxChunkBorderFetch = new AuxiliaryStringEntry(this, "pathingMaxChunkBorderFetch", 50, false);
   public final AuxiliaryStringEntry backtrackCostFavoringCoefficient = new AuxiliaryStringEntry(this, "backtrackCostFavoringCoefficient", 0.5, false);
   public final AuxiliaryStringEntry avoidance = new AuxiliaryStringEntry(this, "avoidance", false, false);
   public final AuxiliaryStringEntry mobSpawnerAvoidanceCoefficient = new AuxiliaryStringEntry(this, "mobSpawnerAvoidanceCoefficient", 2.0, false);
   public final AuxiliaryStringEntry mobSpawnerAvoidanceRadius = new AuxiliaryStringEntry(this, "mobSpawnerAvoidanceRadius", 16, false);
   public final AuxiliaryStringEntry mobAvoidanceCoefficient = new AuxiliaryStringEntry(this, "mobAvoidanceCoefficient", 1.5, false);
   public final AuxiliaryStringEntry mobAvoidanceRadius = new AuxiliaryStringEntry(this, "mobAvoidanceRadius", 8, false);
   public final AuxiliaryStringEntry rightClickContainerOnArrival = new AuxiliaryStringEntry(this, "rightClickContainerOnArrival", true, false);
   public final AuxiliaryStringEntry enterPortal = new AuxiliaryStringEntry(this, "enterPortal", true, false);
   public final AuxiliaryStringEntry minimumImprovementRepropagation = new AuxiliaryStringEntry(this, "minimumImprovementRepropagation", true, false);
   public final AuxiliaryStringEntry cutoffAtLoadBoundary = new AuxiliaryStringEntry(this, "cutoffAtLoadBoundary", false, false);
   public final AuxiliaryStringEntry maxCostIncrease = new AuxiliaryStringEntry(this, "maxCostIncrease", 10.0, false);
   public final AuxiliaryStringEntry costVerificationLookahead = new AuxiliaryStringEntry(this, "costVerificationLookahead", 5, false);
   public final AuxiliaryStringEntry pathCutoffFactor = new AuxiliaryStringEntry(this, "pathCutoffFactor", 0.9, false);
   public final AuxiliaryStringEntry pathCutoffMinimumLength = new AuxiliaryStringEntry(this, "pathCutoffMinimumLength", 30, false);
   public final AuxiliaryStringEntry planningTickLookahead = new AuxiliaryStringEntry(this, "planningTickLookahead", 150, false);
   public final AuxiliaryStringEntry pathingMapDefaultSize = new AuxiliaryStringEntry(this, "pathingMapDefaultSize", 1024, false);
   public final AuxiliaryStringEntry pathingMapLoadFactor = new AuxiliaryStringEntry(this, "pathingMapLoadFactor", 0.75F, false);
   public final AuxiliaryStringEntry maxFallHeightNoWater = new AuxiliaryStringEntry(this, "maxFallHeightNoWater", 3, false);
   public final AuxiliaryStringEntry maxFallHeightBucket = new AuxiliaryStringEntry(this, "maxFallHeightBucket", 20, false);
   public final AuxiliaryStringEntry allowOvershootDiagonalDescend = new AuxiliaryStringEntry(this, "allowOvershootDiagonalDescend", true, false);
   public final AuxiliaryStringEntry simplifyUnloadedYCoord = new AuxiliaryStringEntry(this, "simplifyUnloadedYCoord", true, false);
   public final AuxiliaryStringEntry repackOnAnyBlockChange = new AuxiliaryStringEntry(this, "repackOnAnyBlockChange", true, false);
   public final AuxiliaryStringEntry movementTimeoutTicks = new AuxiliaryStringEntry(this, "movementTimeoutTicks", 100, false);
   public final AuxiliaryStringEntry primaryTimeoutMS = new AuxiliaryStringEntry(this, "primaryTimeoutMS", 500L, false);
   public final AuxiliaryStringEntry failureTimeoutMS = new AuxiliaryStringEntry(this, "failureTimeoutMS", 2000L, false);
   public final AuxiliaryStringEntry planAheadPrimaryTimeoutMS = new AuxiliaryStringEntry(this, "planAheadPrimaryTimeoutMS", 4000L, false);
   public final AuxiliaryStringEntry planAheadFailureTimeoutMS = new AuxiliaryStringEntry(this, "planAheadFailureTimeoutMS", 5000L, false);
   public final AuxiliaryStringEntry slowPath = new AuxiliaryStringEntry(this, "slowPath", false, false);
   public final AuxiliaryStringEntry slowPathTimeDelayMS = new AuxiliaryStringEntry(this, "slowPathTimeDelayMS", 100L, false);
   public final AuxiliaryStringEntry slowPathTimeoutMS = new AuxiliaryStringEntry(this, "slowPathTimeoutMS", 40000L, false);
   public final AuxiliaryStringEntry doBedWaypoints = new AuxiliaryStringEntry(this, "doBedWaypoints", true, false);
   public final AuxiliaryStringEntry doDeathWaypoints = new AuxiliaryStringEntry(this, "doDeathWaypoints", true, false);
   public final AuxiliaryStringEntry chunkCaching = new AuxiliaryStringEntry(this, "chunkCaching", true, false);
   public final AuxiliaryStringEntry pruneRegionsFromRAM = new AuxiliaryStringEntry(this, "pruneRegionsFromRAM", true, false);
   public final AuxiliaryStringEntry chunkPackerQueueMaxSize = new AuxiliaryStringEntry(this, "chunkPackerQueueMaxSize", 2000, false);
   public final AuxiliaryStringEntry backfill = new AuxiliaryStringEntry(this, "backfill", false, false);
   public final AuxiliaryStringEntry logAsToast = new AuxiliaryStringEntry(this, "logAsToast", false, false);
   public final AuxiliaryStringEntry toastTimer = new AuxiliaryStringEntry(this, "toastTimer", 5000L, false);
   public final AuxiliaryStringEntry chatDebug = new AuxiliaryStringEntry(this, "chatDebug", false, false);
   public final AuxiliaryStringEntry chatControl = new AuxiliaryStringEntry(this, "chatControl", true, false);
   public final AuxiliaryStringEntry chatControlAnyway = new AuxiliaryStringEntry(this, "chatControlAnyway", false, false);
   public final AuxiliaryStringEntry renderPath = new AuxiliaryStringEntry(this, "renderPath", true, false);
   public final AuxiliaryStringEntry renderPathAsLine = new AuxiliaryStringEntry(this, "renderPathAsLine", false, false);
   public final AuxiliaryStringEntry renderGoal = new AuxiliaryStringEntry(this, "renderGoal", true, false);
   public final AuxiliaryStringEntry renderGoalAnimated = new AuxiliaryStringEntry(this, "renderGoalAnimated", true, false);
   public final AuxiliaryStringEntry renderSelectionBoxes = new AuxiliaryStringEntry(this, "renderSelectionBoxes", true, false);
   public final AuxiliaryStringEntry renderGoalIgnoreDepth = new AuxiliaryStringEntry(this, "renderGoalIgnoreDepth", true, false);
   public final AuxiliaryStringEntry renderGoalXZBeacon = new AuxiliaryStringEntry(this, "renderGoalXZBeacon", false, false);
   public final AuxiliaryStringEntry renderSelectionBoxesIgnoreDepth = new AuxiliaryStringEntry(this, "renderSelectionBoxesIgnoreDepth", true, false);
   public final AuxiliaryStringEntry renderPathIgnoreDepth = new AuxiliaryStringEntry(this, "renderPathIgnoreDepth", true, false);
   public final AuxiliaryStringEntry pathRenderLineWidthPixels = new AuxiliaryStringEntry(this, "pathRenderLineWidthPixels", 5.0F, false);
   public final AuxiliaryStringEntry goalRenderLineWidthPixels = new AuxiliaryStringEntry(this, "goalRenderLineWidthPixels", 3.0F, false);
   public final AuxiliaryStringEntry fadePath = new AuxiliaryStringEntry(this, "fadePath", false, false);
   public final AuxiliaryStringEntry freeLook = new AuxiliaryStringEntry(this, "freeLook", true, false);
   public final AuxiliaryStringEntry blockFreeLook = new AuxiliaryStringEntry(this, "blockFreeLook", false, false);
   public final AuxiliaryStringEntry antiCheatCompatibility = new AuxiliaryStringEntry(this, "antiCheatCompatibility", true, false);
   public final AuxiliaryStringEntry pathThroughCachedOnly = new AuxiliaryStringEntry(this, "pathThroughCachedOnly", false, false);
   public final AuxiliaryStringEntry sprintInWater = new AuxiliaryStringEntry(this, "sprintInWater", true, false);
   public final AuxiliaryStringEntry blacklistClosestOnFailure = new AuxiliaryStringEntry(this, "blacklistClosestOnFailure", true, false);
   public final AuxiliaryStringEntry renderCachedChunks = new AuxiliaryStringEntry(this, "renderCachedChunks", false, false);
   public final AuxiliaryStringEntry cachedChunksOpacity = new AuxiliaryStringEntry(this, "cachedChunksOpacity", 0.5F, false);
   public final AuxiliaryStringEntry prefixControl = new AuxiliaryStringEntry(this, "prefixControl", true, false);
   public final AuxiliaryStringEntry prefix = new AuxiliaryStringEntry(this, "prefix", "#", false);
   public final AuxiliaryStringEntry shortBaritonePrefix = new AuxiliaryStringEntry(this, "shortBaritonePrefix", false, false);
   public final AuxiliaryStringEntry echoCommands = new AuxiliaryStringEntry(this, "echoCommands", true, false);
   public final AuxiliaryStringEntry censorCoordinates = new AuxiliaryStringEntry(this, "censorCoordinates", false, false);
   public final AuxiliaryStringEntry censorRanCommands = new AuxiliaryStringEntry(this, "censorRanCommands", false, false);
   public final AuxiliaryStringEntry itemSaver = new AuxiliaryStringEntry(this, "itemSaver", false, false);
   public final AuxiliaryStringEntry itemSaverThreshold = new AuxiliaryStringEntry(this, "itemSaverThreshold", 10);
   public final AuxiliaryStringEntry preferSilkTouch = new AuxiliaryStringEntry(this, "preferSilkTouch", false);
   public final AuxiliaryStringEntry walkWhileBreaking = new AuxiliaryStringEntry(this, "walkWhileBreaking", true);
   public final AuxiliaryStringEntry splicePath = new AuxiliaryStringEntry(this, "splicePath", true);
   public final AuxiliaryStringEntry maxPathHistoryLength = new AuxiliaryStringEntry(this, "maxPathHistoryLength", 300);
   public final AuxiliaryStringEntry pathHistoryCutoffAmount = new AuxiliaryStringEntry(this, "pathHistoryCutoffAmount", 50);
   public final AuxiliaryStringEntry mineGoalUpdateInterval = new AuxiliaryStringEntry(this, "mineGoalUpdateInterval", 5);
   public final AuxiliaryStringEntry maxCachedWorldScanCount = new AuxiliaryStringEntry(this, "maxCachedWorldScanCount", 10);
   public final AuxiliaryStringEntry minYLevelWhileMining = new AuxiliaryStringEntry(this, "minYLevelWhileMining", 0);
   public final AuxiliaryStringEntry maxYLevelWhileMining = new AuxiliaryStringEntry(this, "maxYLevelWhileMining", 255);
   public final AuxiliaryStringEntry allowOnlyExposedOres = new AuxiliaryStringEntry(this, "allowOnlyExposedOres", false);
   public final AuxiliaryStringEntry allowOnlyExposedOresDistance = new AuxiliaryStringEntry(this, "allowOnlyExposedOresDistance", 1);
   public final AuxiliaryStringEntry exploreForBlocks = new AuxiliaryStringEntry(this, "exploreForBlocks", true);
   public final AuxiliaryStringEntry worldExploringChunkOffset = new AuxiliaryStringEntry(this, "worldExploringChunkOffset", 0);
   public final AuxiliaryStringEntry exploreChunkSetMinimumSize = new AuxiliaryStringEntry(this, "exploreChunkSetMinimumSize", 10);
   public final AuxiliaryStringEntry exploreMaintainY = new AuxiliaryStringEntry(this, "exploreMaintainY", 64);
   public final AuxiliaryStringEntry replantCrops = new AuxiliaryStringEntry(this, "replantCrops", true);
   public final AuxiliaryStringEntry replantNetherWart = new AuxiliaryStringEntry(this, "replantNetherWart", false);
   public final AuxiliaryStringEntry extendCacheOnThreshold = new AuxiliaryStringEntry(this, "extendCacheOnThreshold", false);
   public final AuxiliaryStringEntry buildInLayers = new AuxiliaryStringEntry(this, "buildInLayers", false);
   public final AuxiliaryStringEntry layerOrder = new AuxiliaryStringEntry(this, "layerOrder", false);
   public final AuxiliaryStringEntry layerHeight = new AuxiliaryStringEntry(this, "layerHeight", 1);
   public final AuxiliaryStringEntry startAtLayer = new AuxiliaryStringEntry(this, "startAtLayer", 0);
   public final AuxiliaryStringEntry skipFailedLayers = new AuxiliaryStringEntry(this, "skipFailedLayers", false);
   public final AuxiliaryStringEntry buildOnlySelection = new AuxiliaryStringEntry(this, "buildOnlySelection", false);
   public final AuxiliaryStringEntry buildRepeat = new AuxiliaryStringEntry(this, "buildRepeat", new Vec3i(0, 0, 0));
   public final AuxiliaryStringEntry buildRepeatCount = new AuxiliaryStringEntry(this, "buildRepeatCount", -1);
   public final AuxiliaryStringEntry buildRepeatSneaky = new AuxiliaryStringEntry(this, "buildRepeatSneaky", true);
   public final AuxiliaryStringEntry breakFromAbove = new AuxiliaryStringEntry(this, "breakFromAbove", false);
   public final AuxiliaryStringEntry goalBreakFromAbove = new AuxiliaryStringEntry(this, "goalBreakFromAbove", false);
   public final AuxiliaryStringEntry mapArtMode = new AuxiliaryStringEntry(this, "mapArtMode", false);
   public final AuxiliaryStringEntry okIfWater = new AuxiliaryStringEntry(this, "okIfWater", false);
   public final AuxiliaryStringEntry incorrectSize = new AuxiliaryStringEntry(this, "incorrectSize", 100);
   public final AuxiliaryStringEntry breakCorrectBlockPenaltyMultiplier = new AuxiliaryStringEntry(this, "breakCorrectBlockPenaltyMultiplier", 10.0);
   public final AuxiliaryStringEntry schematicOrientationX = new AuxiliaryStringEntry(this, "schematicOrientationX", false);
   public final AuxiliaryStringEntry schematicOrientationY = new AuxiliaryStringEntry(this, "schematicOrientationY", false);
   public final AuxiliaryStringEntry schematicOrientationZ = new AuxiliaryStringEntry(this, "schematicOrientationZ", false);
   public final AuxiliaryStringEntry schematicFallbackExtension = new AuxiliaryStringEntry(this, "schematicFallbackExtension", "schematic");
   public final AuxiliaryStringEntry builderTickScanRadius = new AuxiliaryStringEntry(this, "builderTickScanRadius", 5);
   public final AuxiliaryStringEntry mineScanDroppedItems = new AuxiliaryStringEntry(this, "mineScanDroppedItems", true);
   public final AuxiliaryStringEntry mineDropLoiterDurationMSThanksLouca = new AuxiliaryStringEntry(this, "mineDropLoiterDurationMSThanksLouca", 250L);
   public final AuxiliaryStringEntry distanceTrim = new AuxiliaryStringEntry(this, "distanceTrim", true);
   public final AuxiliaryStringEntry cancelOnGoalInvalidation = new AuxiliaryStringEntry(this, "cancelOnGoalInvalidation", true);
   public final AuxiliaryStringEntry axisHeight = new AuxiliaryStringEntry(this, "axisHeight", 120);
   public final AuxiliaryStringEntry disconnectOnArrival = new AuxiliaryStringEntry(this, "disconnectOnArrival", false);
   public final AuxiliaryStringEntry legitMine = new AuxiliaryStringEntry(this, "legitMine", false);
   public final AuxiliaryStringEntry legitMineYLevel = new AuxiliaryStringEntry(this, "legitMineYLevel", 11);
   public final AuxiliaryStringEntry legitMineIncludeDiagonals = new AuxiliaryStringEntry(this, "legitMineIncludeDiagonals", false);
   public final AuxiliaryStringEntry forceInternalMining = new AuxiliaryStringEntry(this, "forceInternalMining", true);
   public final AuxiliaryStringEntry internalMiningAirException = new AuxiliaryStringEntry(this, "internalMiningAirException", true);
   public final AuxiliaryStringEntry followOffsetDistance = new AuxiliaryStringEntry(this, "followOffsetDistance", 0.0);
   public final AuxiliaryStringEntry followOffsetDirection = new AuxiliaryStringEntry(this, "followOffsetDirection", 0.0F);
   public final AuxiliaryStringEntry followRadius = new AuxiliaryStringEntry(this, "followRadius", 3);
   public final AuxiliaryStringEntry disableCompletionCheck = new AuxiliaryStringEntry(this, "disableCompletionCheck", false);
   public final AuxiliaryStringEntry cachedChunksExpirySeconds = new AuxiliaryStringEntry(this, "cachedChunksExpirySeconds", -1L);
   @BreadthFirstCalculator
   public final AuxiliaryStringEntry logger = new AuxiliaryStringEntry(this, "logger", (Consumer<IChatComponent>)Settings::lambda$new$0, true);
   @BreadthFirstCalculator
   public final AuxiliaryStringEntry notifier = new AuxiliaryStringEntry(this, "notifier", (BiConsumer<String, Boolean>)OsascriptManager::applyText, true);
   public final AuxiliaryStringEntry yLevelBoxSize = new AuxiliaryStringEntry(this, "yLevelBoxSize", 15.0);
   public final AuxiliaryStringEntry colorCurrentPath = new AuxiliaryStringEntry(this, "colorCurrentPath", Color.RED);
   public final AuxiliaryStringEntry colorNextPath = new AuxiliaryStringEntry(this, "colorNextPath", Color.MAGENTA);
   public final AuxiliaryStringEntry colorBlocksToBreak = new AuxiliaryStringEntry(this, "colorBlocksToBreak", Color.RED);
   public final AuxiliaryStringEntry colorBlocksToPlace = new AuxiliaryStringEntry(this, "colorBlocksToPlace", Color.GREEN);
   public final AuxiliaryStringEntry colorBlocksToWalkInto = new AuxiliaryStringEntry(this, "colorBlocksToWalkInto", Color.MAGENTA);
   public final AuxiliaryStringEntry colorBestPathSoFar = new AuxiliaryStringEntry(this, "colorBestPathSoFar", Color.BLUE);
   public final AuxiliaryStringEntry colorMostRecentConsidered = new AuxiliaryStringEntry(this, "colorMostRecentConsidered", Color.CYAN);
   public final AuxiliaryStringEntry colorGoalBox = new AuxiliaryStringEntry(this, "colorGoalBox", Color.GREEN);
   public final AuxiliaryStringEntry colorInvertedGoalBox = new AuxiliaryStringEntry(this, "colorInvertedGoalBox", Color.RED);
   public final AuxiliaryStringEntry colorSelection = new AuxiliaryStringEntry(this, "colorSelection", Color.CYAN);
   public final AuxiliaryStringEntry colorSelectionPos1 = new AuxiliaryStringEntry(this, "colorSelectionPos1", Color.BLACK);
   public final AuxiliaryStringEntry colorSelectionPos2 = new AuxiliaryStringEntry(this, "colorSelectionPos2", Color.ORANGE);
   public final AuxiliaryStringEntry selectionOpacity = new AuxiliaryStringEntry(this, "selectionOpacity", 0.5F);
   public final AuxiliaryStringEntry selectionLineWidth = new AuxiliaryStringEntry(this, "selectionLineWidth", 2.0F);
   public final AuxiliaryStringEntry renderSelection = new AuxiliaryStringEntry(this, "renderSelection", true);
   public final AuxiliaryStringEntry renderSelectionIgnoreDepth = new AuxiliaryStringEntry(this, "renderSelectionIgnoreDepth", true);
   public final AuxiliaryStringEntry renderSelectionCorners = new AuxiliaryStringEntry(this, "renderSelectionCorners", true);
   public final AuxiliaryStringEntry useSwordToMine = new AuxiliaryStringEntry(this, "useSwordToMine", true);
   public final AuxiliaryStringEntry desktopNotifications = new AuxiliaryStringEntry(this, "desktopNotifications", false);
   public final AuxiliaryStringEntry notificationOnPathComplete = new AuxiliaryStringEntry(this, "notificationOnPathComplete", true);
   public final AuxiliaryStringEntry notificationOnFarmFail = new AuxiliaryStringEntry(this, "notificationOnFarmFail", true);
   public final AuxiliaryStringEntry notificationOnBuildFinished = new AuxiliaryStringEntry(this, "notificationOnBuildFinished", true);
   public final AuxiliaryStringEntry notificationOnExploreFinished = new AuxiliaryStringEntry(this, "notificationOnExploreFinished", true);
   public final AuxiliaryStringEntry notificationOnMineFail = new AuxiliaryStringEntry(this, "notificationOnMineFail", true);
   public final Map byLowerName;
   public final List allSettings;
   public final Map settingTypes;

   private static void lambda$new$0(IChatComponent iChatComponent) {
      Minecraft.getMinecraft().ingameGUI.getInternalLoggerEntry().applyIChatComponent(iChatComponent);
   }

   public Settings(AuxiliaryMapEntry auxiliaryMapEntry) {
      this.byLowerName = Collections.unmodifiableMap(auxiliaryMapEntry.map);
      this.allSettings = Collections.unmodifiableList(auxiliaryMapEntry.list);
      this.settingTypes = Collections.unmodifiableMap(auxiliaryMapEntry.map2);
   }

   public List getAllValuesByType(Class clazz) {
      ArrayList list = new ArrayList();

      for (AuxiliaryStringEntry auxiliaryStringEntry : (Iterable<AuxiliaryStringEntry>)(Iterable)this.allSettings) {
         if (auxiliaryStringEntry.getClassAsClass().equals(clazz)) {
            list.add(auxiliaryStringEntry);
         }
      }

      return list;
   }
}
