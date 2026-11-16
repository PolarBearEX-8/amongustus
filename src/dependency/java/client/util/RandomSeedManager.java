package client.util;

import client.event.EventTimeUpdate;
import client.event.EventTimeUpdateCallback;
import client.setting.GameSettingsPeacefulType;
import client.setting.WorldSettingsGameType;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class RandomSeedManager {
   public static final GameSettingsPeacefulType gameSettingsPeacefulType = GameSettingsPeacefulType.NORMAL;
   public long timestamp5;
   public InfoManager infoManager = InfoManager.infoManager7;
   public String labelText2 = "";
   public int count5;
   public int count4;
   public int count2;
   public long timestamp2;
   public long timestamp;
   public long timestamp4;
   public long timestamp3;
   public NBTTagCompound nBTTagCompound;
   public int count9;
   public String labelText;
   public int count7;
   public int count;
   public boolean enabled4;
   public int count6;
   public boolean enabled6;
   public int count10;
   public WorldSettingsGameType worldSettingsGameType;
   public boolean enabled7;
   public boolean enabled2;
   public boolean enabled3;
   public boolean enabled5;
   public GameSettingsPeacefulType gameSettingsPeacefulType2;
   public boolean enabled;
   public double value = 0.0;
   public double value6 = 0.0;
   public double value5 = 6.0E7;
   public long timestamp6 = 0L;
   public double value3 = 0.0;
   public double value2 = 5.0;
   public double value4 = 0.2;
   public int count8 = 5;
   public int count3 = 15;
   public DoFireTickManager doFireTickManager = new DoFireTickManager();

   public static int computeCount3(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count10;
   }

   public static boolean matchesRandomSeedManagerByRandomSeedManage(RandomSeedManager randomSeedManager) {
      return randomSeedManager.enabled4;
   }

   public int getCount2() {
      return this.count2;
   }

   public boolean isEnabled7() {
      return this.enabled7;
   }

   public void setCount10(int count11) {
      this.count10 = count11;
   }

   public void setEnabled4(boolean enabled8) {
      this.enabled4 = enabled8;
   }

   public long getTimestamp4() {
      return this.timestamp4;
   }

   public NBTTagCompound getNBTTagCompound() {
      return this.nBTTagCompound;
   }

   public InfoManager getInfoManager() {
      return this.infoManager;
   }

   public void setValue2(double value7) {
      this.value2 = value7;
   }

   public int getCount8() {
      return this.count8;
   }

   public DoFireTickManager getDoFireTickManager() {
      return this.doFireTickManager;
   }

   public double getValue4() {
      return this.value4;
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public void setCount(int count11) {
      this.count = count11;
   }

   public void setCount4(int count11) {
      this.count4 = count11;
   }

   public static int computeCount(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count5;
   }

   public static boolean matchesRandomSeedManager(RandomSeedManager randomSeedManager) {
      return randomSeedManager.enabled2;
   }

   public void applyAdvancedLongEntry(AdvancedLongEntry advancedLongEntry) {
      this.timestamp5 = advancedLongEntry.getTimestamp();
      this.worldSettingsGameType = advancedLongEntry.getWorldSettingsGameType();
      this.enabled7 = advancedLongEntry.isEnabled2();
      this.enabled2 = advancedLongEntry.isEnabled();
      this.infoManager = advancedLongEntry.getInfoManager();
      this.labelText2 = advancedLongEntry.getLabelText();
      this.enabled3 = advancedLongEntry.isEnabled3();
   }

   public void setEnabled7(boolean enabled8) {
      this.enabled7 = enabled8;
   }

   public int getCount5() {
      return this.count5;
   }

   public void setTimestamp6(long timestamp7) {
      this.timestamp6 = timestamp7;
   }

   public double getValue6() {
      return this.value6;
   }

   public void setWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType2) {
      this.worldSettingsGameType = worldSettingsGameType2;
   }

   public void setCount5(int count11) {
      this.count5 = count11;
   }

   public void applyBlockPos(BlockPos blockPos) {
      this.count5 = blockPos.getX();
      this.count4 = blockPos.getY();
      this.count2 = blockPos.getZ();
   }

   public void setValue6(double value7) {
      this.value6 = value7;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setEnabled2(boolean enabled8) {
      this.enabled2 = enabled8;
   }

   private void applyNBTTagCompound(NBTTagCompound tagCompound, NBTTagCompound tagCompound2) {
      tagCompound.put("RandomSeed", this.timestamp5);
      tagCompound.put("generatorName", this.infoManager.getText());
      tagCompound.put("generatorVersion", this.infoManager.getCount());
      tagCompound.put("generatorOptions", this.labelText2);
      tagCompound.put("GameType", this.worldSettingsGameType.getIndex());
      tagCompound.applyText("MapFeatures", this.enabled7);
      tagCompound.put("SpawnX", this.count5);
      tagCompound.put("SpawnY", this.count4);
      tagCompound.put("SpawnZ", this.count2);
      tagCompound.put("Time", this.timestamp2);
      tagCompound.put("DayTime", this.timestamp);
      tagCompound.put("SizeOnDisk", this.timestamp3);
      tagCompound.put("LastPlayed", VanillaRunnable.currentTimeMillis());
      tagCompound.put("LevelName", this.labelText);
      tagCompound.put("version", this.count7);
      tagCompound.put("clearWeatherTime", this.count);
      tagCompound.put("rainTime", this.count6);
      tagCompound.applyText("raining", this.enabled4);
      tagCompound.put("thunderTime", this.count10);
      tagCompound.applyText("thundering", this.enabled6);
      tagCompound.applyText("hardcore", this.enabled2);
      tagCompound.applyText("allowCommands", this.enabled3);
      tagCompound.applyText("initialized", this.enabled5);
      tagCompound.put("BorderCenterX", this.value);
      tagCompound.put("BorderCenterZ", this.value6);
      tagCompound.put("BorderSize", this.value5);
      tagCompound.put("BorderSizeLerpTime", this.timestamp6);
      tagCompound.put("BorderSafeZone", this.value2);
      tagCompound.put("BorderDamagePerBlock", this.value4);
      tagCompound.put("BorderSizeLerpTarget", this.value3);
      tagCompound.put("BorderWarningBlocks", (double)this.count8);
      tagCompound.put("BorderWarningTime", (double)this.count3);
      if (this.gameSettingsPeacefulType2 != null) {
         tagCompound.put("Difficulty", (byte)this.gameSettingsPeacefulType2.getIndex());
      }

      tagCompound.applyText("DifficultyLocked", this.enabled);
      tagCompound.applyText("GameRules", this.doFireTickManager.getNBTTagCompound());
      if (tagCompound2 != null) {
         tagCompound.applyText("Player", tagCompound2);
      }
   }

   public static InfoManager resolveInfoManager(RandomSeedManager randomSeedManager) {
      return randomSeedManager.infoManager;
   }

   public void applyTime(long value7) {
      EventTimeUpdate eventTimeUpdate = EventTimeUpdateCallback.getEventTimeUpdate();
      this.timestamp = eventTimeUpdate.getTimestamp();
   }

   public int getCount10() {
      return this.count10;
   }

   public boolean isEnabled6() {
      return this.enabled6;
   }

   public static int computeCountByRandomSeedManage(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count4;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public void setLabelText(String labelText3) {
      this.labelText = labelText3;
   }

   public double getValue3() {
      return this.value3;
   }

   public void setValue3(double value7) {
      this.value3 = value7;
   }

   public void setCount6(int count11) {
      this.count6 = count11;
   }

   public void setGameSettingsPeacefulType2(GameSettingsPeacefulType gameSettingsPeacefulType3) {
      this.gameSettingsPeacefulType2 = gameSettingsPeacefulType3;
   }

   public void setTimestamp2(long timestamp7) {
      this.timestamp2 = timestamp7;
   }

   public void setCount3(int count11) {
      this.count3 = count11;
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public int getCount6() {
      return this.count6;
   }

   public static int computeCount5(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count6;
   }

   public NBTTagCompound getNBTTagCompoundAsNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound, this.nBTTagCompound);
      return tagCompound;
   }

   public static boolean matchesRandomSeedManager3(RandomSeedManager randomSeedManager) {
      return randomSeedManager.enabled3;
   }

   public double getValue5() {
      return this.value5;
   }

   public void applyCrashReportCategory(CrashReportCategory crashReportCategory) {
      crashReportCategory.applyText("Level seed", new SplineMatcher(this));
      crashReportCategory.applyText("Level generator", new DampenerPicker(this));
      crashReportCategory.applyText("Level generator options", new SplineWriter(this));
      crashReportCategory.applyText("Level spawn location", new TargetMonitor(this));
      crashReportCategory.applyText("Level time", new AimPool(this));
      crashReportCategory.applyText("Level dimension", new AimWatcher(this));
      crashReportCategory.applyText("Level storage version", new TargetQueue(this));
      crashReportCategory.applyText("Level weather", new RotationSynchronizer(this));
      crashReportCategory.applyText("Level game mode", new SmoothAdapter(this));
   }

   public void setValue(double value7) {
      this.value = value7;
   }

   public GameSettingsPeacefulType getGameSettingsPeacefulType2() {
      return this.gameSettingsPeacefulType2;
   }

   public static boolean matchesRandomSeedManager4(RandomSeedManager randomSeedManager) {
      return randomSeedManager.enabled6;
   }

   public void setCount7(int count11) {
      this.count7 = count11;
   }

   public static int computeCount6(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count7;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static long computeTimestampByRandomSeedManage(RandomSeedManager randomSeedManager) {
      return randomSeedManager.timestamp;
   }

   public int getCount4() {
      return this.count4;
   }

   public long getTimestamp3() {
      return this.timestamp3;
   }

   public void setEnabled6(boolean enabled8) {
      this.enabled6 = enabled8;
   }

   public static WorldSettingsGameType resolveWorldSettingsGameType(RandomSeedManager randomSeedManager) {
      return randomSeedManager.worldSettingsGameType;
   }

   public static long computeTimestamp(RandomSeedManager randomSeedManager) {
      return randomSeedManager.timestamp2;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public int getCount3() {
      return this.count3;
   }

   public void setCount8(int count11) {
      this.count8 = count11;
   }

   public long getTimestamp6() {
      return this.timestamp6;
   }

   public static int computeCount2(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count9;
   }

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public void setEnabled3(boolean enabled8) {
      this.enabled3 = enabled8;
   }

   public void setEnabled5(boolean enabled8) {
      this.enabled5 = enabled8;
   }

   public static boolean matchesRandomSeedManager2(RandomSeedManager randomSeedManager) {
      return randomSeedManager.enabled7;
   }

   public int getCount() {
      return this.count;
   }

   public void setCount2(int count11) {
      this.count2 = count11;
   }

   public void setValue5(double value7) {
      this.value5 = value7;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public double getValue() {
      return this.value;
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   public static int computeCount4(RandomSeedManager randomSeedManager) {
      return randomSeedManager.count2;
   }

   public void setEnabled(boolean enabled8) {
      this.enabled = enabled8;
   }

   public int getCount7() {
      return this.count7;
   }

   public static String resolveText(RandomSeedManager randomSeedManager) {
      return randomSeedManager.labelText2;
   }

   public void setInfoManager(InfoManager infoManager2) {
      this.infoManager = infoManager2;
   }

   public double getValue2() {
      return this.value2;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public void setValue4(double value7) {
      this.value4 = value7;
   }

   public NBTTagCompound resolveNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound2, tagCompound);
      return tagCompound2;
   }

   public long getTimestamp5() {
      return this.timestamp5;
   }

   public RandomSeedManager(AdvancedLongEntry advancedLongEntry, String text) {
      this.applyAdvancedLongEntry(advancedLongEntry);
      this.labelText = text;
      this.gameSettingsPeacefulType2 = gameSettingsPeacefulType;
      this.enabled5 = false;
   }

   public RandomSeedManager() {
   }

   public RandomSeedManager(RandomSeedManager randomSeedManager) {
      this.timestamp5 = randomSeedManager.timestamp5;
      this.infoManager = randomSeedManager.infoManager;
      this.labelText2 = randomSeedManager.labelText2;
      this.worldSettingsGameType = randomSeedManager.worldSettingsGameType;
      this.enabled7 = randomSeedManager.enabled7;
      this.count5 = randomSeedManager.count5;
      this.count4 = randomSeedManager.count4;
      this.count2 = randomSeedManager.count2;
      this.timestamp2 = randomSeedManager.timestamp2;
      this.timestamp = randomSeedManager.timestamp;
      this.timestamp4 = randomSeedManager.timestamp4;
      this.timestamp3 = randomSeedManager.timestamp3;
      this.nBTTagCompound = randomSeedManager.nBTTagCompound;
      this.count9 = randomSeedManager.count9;
      this.labelText = randomSeedManager.labelText;
      this.count7 = randomSeedManager.count7;
      this.count6 = randomSeedManager.count6;
      this.enabled4 = randomSeedManager.enabled4;
      this.count10 = randomSeedManager.count10;
      this.enabled6 = randomSeedManager.enabled6;
      this.enabled2 = randomSeedManager.enabled2;
      this.enabled3 = randomSeedManager.enabled3;
      this.enabled5 = randomSeedManager.enabled5;
      this.doFireTickManager = randomSeedManager.doFireTickManager;
      this.gameSettingsPeacefulType2 = randomSeedManager.gameSettingsPeacefulType2;
      this.enabled = randomSeedManager.enabled;
      this.value = randomSeedManager.value;
      this.value6 = randomSeedManager.value6;
      this.value5 = randomSeedManager.value5;
      this.timestamp6 = randomSeedManager.timestamp6;
      this.value3 = randomSeedManager.value3;
      this.value2 = randomSeedManager.value2;
      this.value4 = randomSeedManager.value4;
      this.count3 = randomSeedManager.count3;
      this.count8 = randomSeedManager.count8;
   }

   public RandomSeedManager(NBTTagCompound tagCompound) {
      this.timestamp5 = tagCompound.computeTimestamp("RandomSeed");
      if (tagCompound.matchesText("generatorName", 8)) {
         String text = tagCompound.resolveText("generatorName");
         this.infoManager = InfoManager.resolveInfoManager(text);
         if (this.infoManager == null) {
            this.infoManager = InfoManager.infoManager7;
         } else if (this.infoManager.isEnabled2()) {
            int count11 = 0;
            if (tagCompound.matchesText("generatorVersion", 99)) {
               count11 = tagCompound.computeCount("generatorVersion");
            }

            this.infoManager = this.infoManager.resolveInfoManager(count11);
         }

         if (tagCompound.matchesText("generatorOptions", 8)) {
            this.labelText2 = tagCompound.resolveText("generatorOptions");
         }
      }

      this.worldSettingsGameType = WorldSettingsGameType.resolveWorldSettingsGameType(tagCompound.computeCount("GameType"));
      if (tagCompound.matchesText("MapFeatures", 99)) {
         this.enabled7 = tagCompound.matchesText("MapFeatures");
      } else {
         this.enabled7 = true;
      }

      this.count5 = tagCompound.computeCount("SpawnX");
      this.count4 = tagCompound.computeCount("SpawnY");
      this.count2 = tagCompound.computeCount("SpawnZ");
      this.timestamp2 = tagCompound.computeTimestamp("Time");
      if (tagCompound.matchesText("DayTime", 99)) {
         this.timestamp = tagCompound.computeTimestamp("DayTime");
      } else {
         this.timestamp = this.timestamp2;
      }

      this.timestamp4 = tagCompound.computeTimestamp("LastPlayed");
      this.timestamp3 = tagCompound.computeTimestamp("SizeOnDisk");
      this.labelText = tagCompound.resolveText("LevelName");
      this.count7 = tagCompound.computeCount("version");
      this.count = tagCompound.computeCount("clearWeatherTime");
      this.count6 = tagCompound.computeCount("rainTime");
      this.enabled4 = tagCompound.matchesText("raining");
      this.count10 = tagCompound.computeCount("thunderTime");
      this.enabled6 = tagCompound.matchesText("thundering");
      this.enabled2 = tagCompound.matchesText("hardcore");
      if (tagCompound.matchesText("initialized", 99)) {
         this.enabled5 = tagCompound.matchesText("initialized");
      } else {
         this.enabled5 = true;
      }

      if (tagCompound.matchesText("allowCommands", 99)) {
         this.enabled3 = tagCompound.matchesText("allowCommands");
      } else {
         this.enabled3 = this.worldSettingsGameType == WorldSettingsGameType.CREATIVE;
      }

      if (tagCompound.matchesText("Player", 10)) {
         this.nBTTagCompound = tagCompound.getNBTTagCompound("Player");
         this.count9 = this.nBTTagCompound.computeCount("Dimension");
      }

      if (tagCompound.matchesText("GameRules", 10)) {
         this.doFireTickManager.applyNBTTagCompound(tagCompound.getNBTTagCompound("GameRules"));
      }

      if (tagCompound.matchesText("Difficulty", 99)) {
         this.gameSettingsPeacefulType2 = GameSettingsPeacefulType.resolveGameSettingsPeacefulType(tagCompound.computeByteCode("Difficulty"));
      }

      if (tagCompound.matchesText("DifficultyLocked", 1)) {
         this.enabled = tagCompound.matchesText("DifficultyLocked");
      }

      if (tagCompound.matchesText("BorderCenterX", 99)) {
         this.value = tagCompound.computeValue("BorderCenterX");
      }

      if (tagCompound.matchesText("BorderCenterZ", 99)) {
         this.value6 = tagCompound.computeValue("BorderCenterZ");
      }

      if (tagCompound.matchesText("BorderSize", 99)) {
         this.value5 = tagCompound.computeValue("BorderSize");
      }

      if (tagCompound.matchesText("BorderSizeLerpTime", 99)) {
         this.timestamp6 = tagCompound.computeTimestamp("BorderSizeLerpTime");
      }

      if (tagCompound.matchesText("BorderSizeLerpTarget", 99)) {
         this.value3 = tagCompound.computeValue("BorderSizeLerpTarget");
      }

      if (tagCompound.matchesText("BorderSafeZone", 99)) {
         this.value2 = tagCompound.computeValue("BorderSafeZone");
      }

      if (tagCompound.matchesText("BorderDamagePerBlock", 99)) {
         this.value4 = tagCompound.computeValue("BorderDamagePerBlock");
      }

      if (tagCompound.matchesText("BorderWarningBlocks", 99)) {
         this.count8 = tagCompound.computeCount("BorderWarningBlocks");
      }

      if (tagCompound.matchesText("BorderWarningTime", 99)) {
         this.count3 = tagCompound.computeCount("BorderWarningTime");
      }
   }
}
