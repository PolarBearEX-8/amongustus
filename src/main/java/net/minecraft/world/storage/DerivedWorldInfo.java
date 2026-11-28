package net.minecraft.world.storage;

import net.minecraft.world.WorldSettings;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldType;

public class DerivedWorldInfo extends WorldInfo {
   private final WorldInfo theWorldInfo;

   public DerivedWorldInfo(WorldInfo worldInfo) {
      this.theWorldInfo = worldInfo;
   }

   @Override
   public int getSpawnX() {
      return this.theWorldInfo.getSpawnX();
   }

   @Override
   public long getWorldTotalTime() {
      return this.theWorldInfo.getWorldTotalTime();
   }

   @Override
   public void setAllowCommands(boolean flag) {
   }

   @Override
   public void setTerrainType(WorldType worldType) {
   }

   @Override
   public void setSpawnZ(int n) {
   }

   @Override
   public WorldSettings.GameType getGameType() {
      return this.theWorldInfo.getGameType();
   }

   @Override
   public void setDifficultyLocked(boolean flag) {
   }

   @Override
   public boolean isInitialized() {
      return this.theWorldInfo.isInitialized();
   }

   @Override
   public int getSpawnY() {
      return this.theWorldInfo.getSpawnY();
   }

   @Override
   public boolean isMapFeaturesEnabled() {
      return this.theWorldInfo.isMapFeaturesEnabled();
   }

   @Override
   public boolean areCommandsAllowed() {
      return this.theWorldInfo.areCommandsAllowed();
   }

   @Override
   public NBTTagCompound getNBTTagCompound() {
      return this.theWorldInfo.getNBTTagCompound();
   }

   @Override
   public void setRaining(boolean flag) {
   }

   @Override
   public void setWorldName(String str) {
   }

   @Override
   public long getLastTimePlayed() {
      return this.theWorldInfo.getLastTimePlayed();
   }

   @Override
   public boolean isRaining() {
      return this.theWorldInfo.isRaining();
   }

   @Override
   public void setSpawnX(int n) {
   }

   @Override
   public void setSaveVersion(int n) {
   }

   @Override
   public void setWorldTime(long l) {
   }

   @Override
   public void setWorldTotalTime(long l) {
   }

   @Override
   public void setThunderTime(int n) {
   }

   @Override
   public GameRules getGameRulesInstance() {
      return this.theWorldInfo.getGameRulesInstance();
   }

   @Override
   public void setSpawnY(int n) {
   }

   @Override
   public EnumDifficulty getDifficulty() {
      return this.theWorldInfo.getDifficulty();
   }

   @Override
   public void setServerInitialized(boolean flag) {
   }

   @Override
   public NBTTagCompound cloneNBTCompound(NBTTagCompound tag) {
      return this.theWorldInfo.cloneNBTCompound(tag);
   }

   @Override
   public NBTTagCompound getPlayerNBTTagCompound() {
      return this.theWorldInfo.getPlayerNBTTagCompound();
   }

   @Override
   public WorldType getTerrainType() {
      return this.theWorldInfo.getTerrainType();
   }

   @Override
   public boolean isDifficultyLocked() {
      return this.theWorldInfo.isDifficultyLocked();
   }

   @Override
   public long getSizeOnDisk() {
      return this.theWorldInfo.getSizeOnDisk();
   }

   @Override
   public int getRainTime() {
      return this.theWorldInfo.getRainTime();
   }

   @Override
   public boolean isHardcoreModeEnabled() {
      return this.theWorldInfo.isHardcoreModeEnabled();
   }

   @Override
   public int getSaveVersion() {
      return this.theWorldInfo.getSaveVersion();
   }

   @Override
   public long getSeed() {
      return this.theWorldInfo.getSeed();
   }

   @Override
   public void setSpawn(BlockPos pos) {
   }

   @Override
   public long getWorldTime() {
      return this.theWorldInfo.getWorldTime();
   }

   @Override
   public void setDifficulty(EnumDifficulty enumDifficulty) {
   }

   @Override
   public void setThundering(boolean flag) {
   }

   @Override
   public boolean isThundering() {
      return this.theWorldInfo.isThundering();
   }

   @Override
   public int getSpawnZ() {
      return this.theWorldInfo.getSpawnZ();
   }

   @Override
   public int getThunderTime() {
      return this.theWorldInfo.getThunderTime();
   }

   @Override
   public void setRainTime(int n) {
   }

   @Override
   public String getWorldName() {
      return this.theWorldInfo.getWorldName();
   }
}
