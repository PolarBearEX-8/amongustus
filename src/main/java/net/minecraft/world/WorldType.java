package net.minecraft.world;

public class WorldType {
   public static final WorldType[] worldTypes = new WorldType[16];
   public static final WorldType DEFAULT = new WorldType(0, "default", 1).setVersioned();
   public static final WorldType FLAT = new WorldType(1, "flat");
   public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
   public static final WorldType AMPLIFIED = new WorldType(3, "amplified").setNotificationData();
   public static final WorldType CUSTOMIZED = new WorldType(4, "customized");
   public static final WorldType DEBUG_WORLD = new WorldType(5, "debug_all_block_states");
   public static final WorldType DEFAULT_1_1 = new WorldType(8, "default_1_1", 0).setCanBeCreated(false);
   private final int worldTypeId;
   private final String worldType;
   private final int generatorVersion;
   private boolean canBeCreated;
   private boolean versioned;
   private boolean hasNotificationData;

   private WorldType(int n, String str, int n2) {
      this.worldType = str;
      this.generatorVersion = n2;
      this.canBeCreated = true;
      this.worldTypeId = n;
      worldTypes[n] = this;
   }

   private WorldType setVersioned() {
      this.versioned = true;
      return this;
   }

   public String getWorldTypeName() {
      return this.worldType;
   }

   public String getTranslateName() {
      return "generator." + this.worldType;
   }

   private WorldType setCanBeCreated(boolean flag) {
      this.canBeCreated = flag;
      return this;
   }

   public String getTranslatedInfo() {
      return this.getTranslateName() + ".info";
   }

   private WorldType setNotificationData() {
      this.hasNotificationData = true;
      return this;
   }

   public int getWorldTypeID() {
      return this.worldTypeId;
   }

   public boolean showWorldInfoNotice() {
      return this.hasNotificationData;
   }

   public boolean getCanBeCreated() {
      return this.canBeCreated;
   }

   private WorldType(int n, String str) {
      this(n, str, 0);
   }

   public int getGeneratorVersion() {
      return this.generatorVersion;
   }

   public WorldType getWorldTypeForGeneratorVersion(int n) {
      return this == DEFAULT && n == 0 ? DEFAULT_1_1 : this;
   }

   public static WorldType parseWorldType(String str) {
      for (int i = 0; i < worldTypes.length; i++) {
         if (worldTypes[i] != null && worldTypes[i].worldType.equalsIgnoreCase(str)) {
            return worldTypes[i];
         }
      }

      return null;
   }

   public boolean isVersioned() {
      return this.versioned;
   }
}
