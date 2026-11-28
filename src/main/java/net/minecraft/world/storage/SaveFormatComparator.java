package net.minecraft.world.storage;

import net.minecraft.world.WorldSettings;

public class SaveFormatComparator implements Comparable {
   private final String fileName;
   private final String displayName;
   private final long lastTimePlayed;
   private final long sizeOnDisk;
   private final boolean requiresConversion;
   private final WorldSettings.GameType theEnumGameType;
   private final boolean hardcore;
   private final boolean cheatsEnabled;

   public int compareTo(SaveFormatComparator other) {
      return this.lastTimePlayed < other.lastTimePlayed ? 1 : (this.lastTimePlayed > other.lastTimePlayed ? -1 : this.fileName.compareTo(other.fileName));
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public SaveFormatComparator(String str, String str2, long l, long l2, WorldSettings.GameType gameType, boolean flag, boolean flag2, boolean flag3) {
      this.fileName = str;
      this.displayName = str2;
      this.lastTimePlayed = l;
      this.sizeOnDisk = l2;
      this.theEnumGameType = gameType;
      this.requiresConversion = flag;
      this.hardcore = flag2;
      this.cheatsEnabled = flag3;
   }

   public boolean requiresConversion() {
      return this.requiresConversion;
   }

   public boolean getCheatsEnabled() {
      return this.cheatsEnabled;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public WorldSettings.GameType getEnumGameType() {
      return this.theEnumGameType;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public String getFileName() {
      return this.fileName;
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo((SaveFormatComparator)other);
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }
}
