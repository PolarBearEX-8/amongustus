package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.augustus.events.listeners.WorldTimeListener;
import net.augustus.events.EventWorldTime;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

public class WorldInfo {
   public static final EnumDifficulty DEFAULT_DIFFICULTY = EnumDifficulty.NORMAL;
   private long randomSeed;
   private WorldType terrainType = WorldType.DEFAULT;
   private String generatorOptions = "";
   private int spawnX;
   private int spawnY;
   private int spawnZ;
   private long totalTime;
   private long worldTime;
   private long lastTimePlayed;
   private long sizeOnDisk;
   private NBTTagCompound playerTag;
   private int dimension;
   private String levelName;
   private int saveVersion;
   private int cleanWeatherTime;
   private boolean raining;
   private int rainTime;
   private boolean thundering;
   private int thunderTime;
   private WorldSettings.GameType theGameType;
   private boolean mapFeaturesEnabled;
   private boolean hardcore;
   private boolean allowCommands;
   private boolean initialized;
   private EnumDifficulty difficulty;
   private boolean difficultyLocked;
   private double borderCenterX = 0.0;
   private double borderCenterZ = 0.0;
   private double borderSize = 6.0E7;
   private long borderSizeLerpTime = 0L;
   private double borderSizeLerpTarget = 0.0;
   private double borderSafeZone = 5.0;
   private double borderDamagePerBlock = 0.2;
   private int borderWarningDistance = 5;
   private int borderWarningTime = 15;
   private GameRules theGameRules = new GameRules();

   public void setHardcore(boolean flag) {
      this.hardcore = flag;
   }

   public void setAllowCommands(boolean flag) {
      this.allowCommands = flag;
   }

   public boolean isThundering() {
      return this.thundering;
   }

   public String getGeneratorOptions() {
      return this.generatorOptions;
   }

   public WorldInfo(NBTTagCompound tag) {
      this.randomSeed = tag.getLong("RandomSeed");
      if (tag.hasKey("generatorName", 8)) {
         this.terrainType = WorldType.parseWorldType(tag.getString("generatorName"));
         if (this.terrainType == null) {
            this.terrainType = WorldType.DEFAULT;
         } else if (this.terrainType.isVersioned()) {
            int n = 0;
            if (tag.hasKey("generatorVersion", 99)) {
               n = tag.getInteger("generatorVersion");
            }

            this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(n);
         }

         if (tag.hasKey("generatorOptions", 8)) {
            this.generatorOptions = tag.getString("generatorOptions");
         }
      }

      this.theGameType = WorldSettings.GameType.getByID(tag.getInteger("GameType"));
      if (tag.hasKey("MapFeatures", 99)) {
         this.mapFeaturesEnabled = tag.getBoolean("MapFeatures");
      } else {
         this.mapFeaturesEnabled = true;
      }

      this.spawnX = tag.getInteger("SpawnX");
      this.spawnY = tag.getInteger("SpawnY");
      this.spawnZ = tag.getInteger("SpawnZ");
      this.totalTime = tag.getLong("Time");
      if (tag.hasKey("DayTime", 99)) {
         this.worldTime = tag.getLong("DayTime");
      } else {
         this.worldTime = this.totalTime;
      }

      this.lastTimePlayed = tag.getLong("LastPlayed");
      this.sizeOnDisk = tag.getLong("SizeOnDisk");
      this.levelName = tag.getString("LevelName");
      this.saveVersion = tag.getInteger("version");
      this.cleanWeatherTime = tag.getInteger("clearWeatherTime");
      this.rainTime = tag.getInteger("rainTime");
      this.raining = tag.getBoolean("raining");
      this.thunderTime = tag.getInteger("thunderTime");
      this.thundering = tag.getBoolean("thundering");
      this.hardcore = tag.getBoolean("hardcore");
      if (tag.hasKey("initialized", 99)) {
         this.initialized = tag.getBoolean("initialized");
      } else {
         this.initialized = true;
      }

      if (tag.hasKey("allowCommands", 99)) {
         this.allowCommands = tag.getBoolean("allowCommands");
      } else {
         this.allowCommands = this.theGameType == WorldSettings.GameType.CREATIVE;
      }

      if (tag.hasKey("Player", 10)) {
         this.playerTag = tag.getCompoundTag("Player");
         this.dimension = this.playerTag.getInteger("Dimension");
      }

      if (tag.hasKey("GameRules", 10)) {
         this.theGameRules.readFromNBT(tag.getCompoundTag("GameRules"));
      }

      if (tag.hasKey("Difficulty", 99)) {
         this.difficulty = EnumDifficulty.getDifficultyEnum(tag.getByte("Difficulty"));
      }

      if (tag.hasKey("DifficultyLocked", 1)) {
         this.difficultyLocked = tag.getBoolean("DifficultyLocked");
      }

      if (tag.hasKey("BorderCenterX", 99)) {
         this.borderCenterX = tag.getDouble("BorderCenterX");
      }

      if (tag.hasKey("BorderCenterZ", 99)) {
         this.borderCenterZ = tag.getDouble("BorderCenterZ");
      }

      if (tag.hasKey("BorderSize", 99)) {
         this.borderSize = tag.getDouble("BorderSize");
      }

      if (tag.hasKey("BorderSizeLerpTime", 99)) {
         this.borderSizeLerpTime = tag.getLong("BorderSizeLerpTime");
      }

      if (tag.hasKey("BorderSizeLerpTarget", 99)) {
         this.borderSizeLerpTarget = tag.getDouble("BorderSizeLerpTarget");
      }

      if (tag.hasKey("BorderSafeZone", 99)) {
         this.borderSafeZone = tag.getDouble("BorderSafeZone");
      }

      if (tag.hasKey("BorderDamagePerBlock", 99)) {
         this.borderDamagePerBlock = tag.getDouble("BorderDamagePerBlock");
      }

      if (tag.hasKey("BorderWarningBlocks", 99)) {
         this.borderWarningDistance = tag.getInteger("BorderWarningBlocks");
      }

      if (tag.hasKey("BorderWarningTime", 99)) {
         this.borderWarningTime = tag.getInteger("BorderWarningTime");
      }
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   public int getSpawnZ() {
      return this.spawnZ;
   }

   public static WorldType accessTerrainType(WorldInfo worldInfo) {
      return worldInfo.terrainType;
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void populateFromWorldSettings(WorldSettings worldSettings) {
      this.randomSeed = worldSettings.getSeed();
      this.theGameType = worldSettings.getGameType();
      this.mapFeaturesEnabled = worldSettings.isMapFeaturesEnabled();
      this.hardcore = worldSettings.getHardcoreEnabled();
      this.terrainType = worldSettings.getTerrainType();
      this.generatorOptions = worldSettings.getWorldName();
      this.allowCommands = worldSettings.areCommandsAllowed();
   }

   public void setDifficultyLocked(boolean flag) {
      this.difficultyLocked = flag;
   }

   public void setBorderSafeZone(double d) {
      this.borderSafeZone = d;
   }

   public void setMapFeaturesEnabled(boolean flag) {
      this.mapFeaturesEnabled = flag;
   }

   public static boolean accessMapFeaturesEnabled(WorldInfo worldInfo) {
      return worldInfo.mapFeaturesEnabled;
   }

   public WorldType getTerrainType() {
      return this.terrainType;
   }

   public void setBorderSize(double d) {
      this.borderSize = d;
   }

   public static String accessGeneratorOptions(WorldInfo worldInfo) {
      return worldInfo.generatorOptions;
   }

   public void setWorldName(String str) {
      this.levelName = str;
   }

   public static int accessSaveVersion(WorldInfo worldInfo) {
      return worldInfo.saveVersion;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public WorldInfo(WorldInfo worldInfo) {
      this.randomSeed = worldInfo.randomSeed;
      this.terrainType = worldInfo.terrainType;
      this.generatorOptions = worldInfo.generatorOptions;
      this.theGameType = worldInfo.theGameType;
      this.mapFeaturesEnabled = worldInfo.mapFeaturesEnabled;
      this.spawnX = worldInfo.spawnX;
      this.spawnY = worldInfo.spawnY;
      this.spawnZ = worldInfo.spawnZ;
      this.totalTime = worldInfo.totalTime;
      this.worldTime = worldInfo.worldTime;
      this.lastTimePlayed = worldInfo.lastTimePlayed;
      this.sizeOnDisk = worldInfo.sizeOnDisk;
      this.playerTag = worldInfo.playerTag;
      this.dimension = worldInfo.dimension;
      this.levelName = worldInfo.levelName;
      this.saveVersion = worldInfo.saveVersion;
      this.rainTime = worldInfo.rainTime;
      this.raining = worldInfo.raining;
      this.thunderTime = worldInfo.thunderTime;
      this.thundering = worldInfo.thundering;
      this.hardcore = worldInfo.hardcore;
      this.allowCommands = worldInfo.allowCommands;
      this.initialized = worldInfo.initialized;
      this.theGameRules = worldInfo.theGameRules;
      this.difficulty = worldInfo.difficulty;
      this.difficultyLocked = worldInfo.difficultyLocked;
      this.borderCenterX = worldInfo.borderCenterX;
      this.borderCenterZ = worldInfo.borderCenterZ;
      this.borderSize = worldInfo.borderSize;
      this.borderSizeLerpTime = worldInfo.borderSizeLerpTime;
      this.borderSizeLerpTarget = worldInfo.borderSizeLerpTarget;
      this.borderSafeZone = worldInfo.borderSafeZone;
      this.borderDamagePerBlock = worldInfo.borderDamagePerBlock;
      this.borderWarningTime = worldInfo.borderWarningTime;
      this.borderWarningDistance = worldInfo.borderWarningDistance;
   }

   public long getSeed() {
      return this.randomSeed;
   }

   public static long accessTotalTime(WorldInfo worldInfo) {
      return worldInfo.totalTime;
   }

   public void setBorderLerpTarget(double d) {
      this.borderSizeLerpTarget = d;
   }

   public boolean areCommandsAllowed() {
      return this.allowCommands;
   }

   public static int accessDimension(WorldInfo worldInfo) {
      return worldInfo.dimension;
   }

   public void setThunderTime(int n) {
      this.thunderTime = n;
   }

   public NBTTagCompound cloneNBTCompound(NBTTagCompound tag) {
      NBTTagCompound tag2 = new NBTTagCompound();
      this.updateTagCompound(tag2, tag);
      return tag2;
   }

   public void setSaveVersion(int n) {
      this.saveVersion = n;
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }

   public void setCleanWeatherTime(int n) {
      this.cleanWeatherTime = n;
   }

   public void setBorderDamagePerBlock(double d) {
      this.borderDamagePerBlock = d;
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public int getSaveVersion() {
      return this.saveVersion;
   }

   public static int accessRainTime(WorldInfo worldInfo) {
      return worldInfo.rainTime;
   }

   public static boolean accessHardcore(WorldInfo worldInfo) {
      return worldInfo.hardcore;
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tag = new NBTTagCompound();
      this.updateTagCompound(tag, this.playerTag);
      return tag;
   }

   public void setRaining(boolean flag) {
      this.raining = flag;
   }

   public double getBorderDamagePerBlock() {
      return this.borderDamagePerBlock;
   }

   public void setBorderLerpTime(long l) {
      this.borderSizeLerpTime = l;
   }

   public int getSpawnY() {
      return this.spawnY;
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public long getWorldTotalTime() {
      return this.totalTime;
   }

   public int getSpawnX() {
      return this.spawnX;
   }

   public WorldSettings.GameType getGameType() {
      return this.theGameType;
   }

   public void setSpawnZ(int n) {
      this.spawnZ = n;
   }

   public void setSpawn(BlockPos pos) {
      this.spawnX = pos.getX();
      this.spawnY = pos.getY();
      this.spawnZ = pos.getZ();
   }

   public static boolean accessThundering(WorldInfo worldInfo) {
      return worldInfo.thundering;
   }

   public double getBorderLerpTarget() {
      return this.borderSizeLerpTarget;
   }

   public long getBorderLerpTime() {
      return this.borderSizeLerpTime;
   }

   public WorldInfo(WorldSettings worldSettings, String str) {
      this.populateFromWorldSettings(worldSettings);
      this.levelName = str;
      this.difficulty = DEFAULT_DIFFICULTY;
      this.initialized = false;
   }

   public int getBorderWarningDistance() {
      return this.borderWarningDistance;
   }

   public static int accessSpawnY(WorldInfo worldInfo) {
      return worldInfo.spawnY;
   }

   public double getBorderSize() {
      return this.borderSize;
   }

   public void setThundering(boolean flag) {
      this.thundering = flag;
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   public void setDifficulty(EnumDifficulty enumDifficulty) {
      this.difficulty = enumDifficulty;
   }

   public static WorldSettings.GameType accessTheGameType(WorldInfo worldInfo) {
      return worldInfo.theGameType;
   }

   public String getWorldName() {
      return this.levelName;
   }

   public double getBorderSafeZone() {
      return this.borderSafeZone;
   }

   public void setWorldTotalTime(long l) {
      this.totalTime = l;
   }

   public GameRules getGameRulesInstance() {
      return this.theGameRules;
   }

   public NBTTagCompound getPlayerNBTTagCompound() {
      return this.playerTag;
   }

   public int getRainTime() {
      return this.rainTime;
   }

   public void setTerrainType(WorldType worldType) {
      this.terrainType = worldType;
   }

   public static boolean accessAllowCommands(WorldInfo worldInfo) {
      return worldInfo.allowCommands;
   }

   public void getBorderCenterX(double d) {
      this.borderCenterX = d;
   }

   public void setGameType(WorldSettings.GameType gameType) {
      this.theGameType = gameType;
   }

   public void setBorderWarningTime(int n) {
      this.borderWarningTime = n;
   }

   public static int accessSpawnZ(WorldInfo worldInfo) {
      return worldInfo.spawnZ;
   }

   public static int accessThunderTime(WorldInfo worldInfo) {
      return worldInfo.thunderTime;
   }

   public static boolean accessRaining(WorldInfo worldInfo) {
      return worldInfo.raining;
   }

   public void getBorderCenterZ(double d) {
      this.borderCenterZ = d;
   }

   public int getCleanWeatherTime() {
      return this.cleanWeatherTime;
   }

   public void setWorldTime(long l) {
      this.worldTime = WorldTimeListener.postWorldTime(l).getWorldTime();
   }

   public double getBorderCenterX() {
      return this.borderCenterX;
   }

   public int getThunderTime() {
      return this.thunderTime;
   }

   public void setRainTime(int n) {
      this.rainTime = n;
   }

   public void addToCrashReport(CrashReportCategory crashReportCategory) {
      crashReportCategory.addCrashSectionCallable("Level seed", new WorldInfo.ValueCallable(this));
      crashReportCategory.addCrashSectionCallable("Level generator", new WorldInfo.ValueCallable2(this));
      crashReportCategory.addCrashSectionCallable("Level generator options", new WorldInfo.ValueCallable3(this));
      crashReportCategory.addCrashSectionCallable("Level spawn location", new WorldInfo.ValueCallable4(this));
      crashReportCategory.addCrashSectionCallable("Level time", new WorldInfo.ValueCallable5(this));
      crashReportCategory.addCrashSectionCallable("Level dimension", new WorldInfo.ValueCallable6(this));
      crashReportCategory.addCrashSectionCallable("Level storage version", new WorldInfo.ValueCallable7(this));
      crashReportCategory.addCrashSectionCallable("Level weather", new WorldInfo.ValueCallable8(this));
      crashReportCategory.addCrashSectionCallable("Level game mode", new WorldInfo.ValueCallable9(this));
   }

   public static int accessSpawnX(WorldInfo worldInfo) {
      return worldInfo.spawnX;
   }

   public double getBorderCenterZ() {
      return this.borderCenterZ;
   }

   public void setSpawnX(int n) {
      this.spawnX = n;
   }

   public void setServerInitialized(boolean flag) {
      this.initialized = flag;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public int getBorderWarningTime() {
      return this.borderWarningTime;
   }

   public static long accessWorldTime(WorldInfo worldInfo) {
      return worldInfo.worldTime;
   }

   public boolean isRaining() {
      return this.raining;
   }

   public void setSpawnY(int n) {
      this.spawnY = n;
   }

   public WorldInfo() {
   }

   private void updateTagCompound(NBTTagCompound tag, NBTTagCompound tag2) {
      tag.setLong("RandomSeed", this.randomSeed);
      tag.setString("generatorName", this.terrainType.getWorldTypeName());
      tag.setInteger("generatorVersion", this.terrainType.getGeneratorVersion());
      tag.setString("generatorOptions", this.generatorOptions);
      tag.setInteger("GameType", this.theGameType.getID());
      tag.setBoolean("MapFeatures", this.mapFeaturesEnabled);
      tag.setInteger("SpawnX", this.spawnX);
      tag.setInteger("SpawnY", this.spawnY);
      tag.setInteger("SpawnZ", this.spawnZ);
      tag.setLong("Time", this.totalTime);
      tag.setLong("DayTime", this.worldTime);
      tag.setLong("SizeOnDisk", this.sizeOnDisk);
      tag.setLong("LastPlayed", MinecraftServer.getCurrentTimeMillis());
      tag.setString("LevelName", this.levelName);
      tag.setInteger("version", this.saveVersion);
      tag.setInteger("clearWeatherTime", this.cleanWeatherTime);
      tag.setInteger("rainTime", this.rainTime);
      tag.setBoolean("raining", this.raining);
      tag.setInteger("thunderTime", this.thunderTime);
      tag.setBoolean("thundering", this.thundering);
      tag.setBoolean("hardcore", this.hardcore);
      tag.setBoolean("allowCommands", this.allowCommands);
      tag.setBoolean("initialized", this.initialized);
      tag.setDouble("BorderCenterX", this.borderCenterX);
      tag.setDouble("BorderCenterZ", this.borderCenterZ);
      tag.setDouble("BorderSize", this.borderSize);
      tag.setLong("BorderSizeLerpTime", this.borderSizeLerpTime);
      tag.setDouble("BorderSafeZone", this.borderSafeZone);
      tag.setDouble("BorderDamagePerBlock", this.borderDamagePerBlock);
      tag.setDouble("BorderSizeLerpTarget", this.borderSizeLerpTarget);
      tag.setDouble("BorderWarningBlocks", (double)this.borderWarningDistance);
      tag.setDouble("BorderWarningTime", (double)this.borderWarningTime);
      if (this.difficulty != null) {
         tag.setByte("Difficulty", (byte)this.difficulty.getDifficultyId());
      }

      tag.setBoolean("DifficultyLocked", this.difficultyLocked);
      tag.setTag("GameRules", this.theGameRules.writeToNBT());
      if (tag2 != null) {
         tag.setTag("Player", tag2);
      }
   }

   public void setBorderWarningDistance(int n) {
      this.borderWarningDistance = n;
   }


   public static class ValueCallable implements Callable {
      public final WorldInfo worldInfo;

      public String callAsString() throws Exception {
         return String.valueOf(this.worldInfo.getSeed());
      }

      public ValueCallable(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable2 implements Callable {
      public final WorldInfo worldInfo;

      public String callAsString() throws Exception {
         return String.format(
            "ID %02d - %s, ver %d. Features enabled: %b",
            WorldInfo.accessTerrainType(this.worldInfo).getWorldTypeID(),
            WorldInfo.accessTerrainType(this.worldInfo).getWorldTypeName(),
            WorldInfo.accessTerrainType(this.worldInfo).getGeneratorVersion(),
            WorldInfo.accessMapFeaturesEnabled(this.worldInfo)
         );
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable2(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }
   }

   public static class ValueCallable3 implements Callable {
      public final WorldInfo worldInfo;

      public String callAsString() throws Exception {
         return WorldInfo.accessGeneratorOptions(this.worldInfo);
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable3(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }
   }

   public static class ValueCallable4 implements Callable {
      public final WorldInfo worldInfo;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable4(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo((double)WorldInfo.accessSpawnX(this.worldInfo), (double)WorldInfo.accessSpawnY(this.worldInfo), (double)WorldInfo.accessSpawnZ(this.worldInfo));
      }
   }

   public static class ValueCallable5 implements Callable {
      public final WorldInfo worldInfo;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable5(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }

      public String callAsString() throws Exception {
         return String.format("%d game time, %d day time", WorldInfo.accessTotalTime(this.worldInfo), WorldInfo.accessWorldTime(this.worldInfo));
      }
   }

   public static class ValueCallable6 implements Callable {
      public final WorldInfo worldInfo;

      public String callAsString() throws Exception {
         return String.valueOf(WorldInfo.accessDimension(this.worldInfo));
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable6(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }
   }

   public static class ValueCallable7 implements Callable {
      public final WorldInfo worldInfo;

      public ValueCallable7(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }

      public String callAsString() throws Exception {
         String str = "Unknown?";

         try {
            switch (WorldInfo.accessSaveVersion(this.worldInfo)) {
               case 19132:
                  str = "McRegion";
                  break;
               case 19133:
                  str = "Anvil";
            }
         } catch (Throwable e) {
         }

         return String.format("0x%05X - %s", WorldInfo.accessSaveVersion(this.worldInfo), str);
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable8 implements Callable {
      public final WorldInfo worldInfo;

      public String callAsString() throws Exception {
         return String.format(
            "Rain time: %d (now: %b), thunder time: %d (now: %b)",
            WorldInfo.accessRainTime(this.worldInfo),
            WorldInfo.accessRaining(this.worldInfo),
            WorldInfo.accessThunderTime(this.worldInfo),
            WorldInfo.accessThundering(this.worldInfo)
         );
      }

      public ValueCallable8(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class ValueCallable9 implements Callable {
      public final WorldInfo worldInfo;

      public String callAsString() throws Exception {
         return String.format(
            "Game mode: %s (ID %d). Hardcore: %b. Cheats: %b",
            WorldInfo.accessTheGameType(this.worldInfo).getName(),
            WorldInfo.accessTheGameType(this.worldInfo).getID(),
            WorldInfo.accessHardcore(this.worldInfo),
            WorldInfo.accessAllowCommands(this.worldInfo)
         );
      }

      public ValueCallable9(WorldInfo worldInfo2) {
         this.worldInfo = worldInfo2;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }
}
