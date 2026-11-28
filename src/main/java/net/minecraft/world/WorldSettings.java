package net.minecraft.world;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.storage.WorldInfo;

public final class WorldSettings {
   private final long seed;
   private final WorldSettings.GameType theGameType;
   private final boolean mapFeaturesEnabled;
   private final boolean hardcoreEnabled;
   private final WorldType terrainType;
   private boolean commandsAllowed;
   private boolean bonusChestEnabled;
   private String worldName = "";

   public WorldSettings.GameType getGameType() {
      return this.theGameType;
   }

   public WorldSettings enableCommands() {
      this.commandsAllowed = true;
      return this;
   }

   public boolean isMapFeaturesEnabled() {
      return this.mapFeaturesEnabled;
   }

   public WorldSettings enableBonusChest() {
      this.bonusChestEnabled = true;
      return this;
   }

   public WorldSettings setWorldName(String str) {
      this.worldName = str;
      return this;
   }

   public boolean getHardcoreEnabled() {
      return this.hardcoreEnabled;
   }

   public boolean areCommandsAllowed() {
      return this.commandsAllowed;
   }

   public boolean isBonusChestEnabled() {
      return this.bonusChestEnabled;
   }

   public WorldType getTerrainType() {
      return this.terrainType;
   }

   public String getWorldName() {
      return this.worldName;
   }

   public static WorldSettings.GameType getGameTypeById(int n) {
      return WorldSettings.GameType.getByID(n);
   }

   public WorldSettings(long l, WorldSettings.GameType gameType, boolean flag, boolean flag2, WorldType worldType) {
      this.seed = l;
      this.theGameType = gameType;
      this.mapFeaturesEnabled = flag;
      this.hardcoreEnabled = flag2;
      this.terrainType = worldType;
   }

   public WorldSettings(WorldInfo worldInfo) {
      this(worldInfo.getSeed(), worldInfo.getGameType(), worldInfo.isMapFeaturesEnabled(), worldInfo.isHardcoreModeEnabled(), worldInfo.getTerrainType());
   }

   public long getSeed() {
      return this.seed;
   }


   public enum GameType {
      NOT_SET(-1, ""),
      SURVIVAL(0, "survival"),
      CREATIVE(1, "creative"),
      ADVENTURE(2, "adventure"),
      SPECTATOR(3, "spectator");

   public int id;
      public String name;
   public static GameType getByID(int n) {
         for (GameType gameType : values()) {
            if (gameType.id == n) {
               return gameType;
            }
         }

         return SURVIVAL;
      }

      public void configurePlayerCapabilities(PlayerCapabilities playerCapabilities) {
         if (this == CREATIVE) {
            playerCapabilities.allowFlying = true;
            playerCapabilities.isCreativeMode = true;
            playerCapabilities.disableDamage = true;
         } else if (this == SPECTATOR) {
            playerCapabilities.allowFlying = true;
            playerCapabilities.isCreativeMode = false;
            playerCapabilities.disableDamage = true;
            playerCapabilities.isFlying = true;
         } else {
            playerCapabilities.allowFlying = false;
            playerCapabilities.isCreativeMode = false;
            playerCapabilities.disableDamage = false;
            playerCapabilities.isFlying = false;
         }

         playerCapabilities.allowEdit = !this.isAdventure();
      }

      private GameType(int n, String str) {
         this.id = n;
         this.name = str;
      }

      public int getID() {
         return this.id;
      }

      public boolean isSurvivalOrAdventure() {
         return this == SURVIVAL || this == ADVENTURE;
      }

      public static GameType getByName(String str) {
         for (GameType gameType : values()) {
            if (gameType.name.equals(str)) {
               return gameType;
            }
         }

         return SURVIVAL;
      }

      public boolean isCreative() {
         return this == CREATIVE;
      }

      public boolean isAdventure() {
         return this == ADVENTURE || this == SPECTATOR;
      }

      public String getName() {
         return this.name;
      }
   }
}
