package net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.data;

import com.viaversion.viaversion.libs.fastutil.ints.IntIterator;
import com.viaversion.viaversion.libs.fastutil.ints.IntOpenHashSet;
import com.viaversion.viaversion.libs.fastutil.ints.IntSet;
import java.util.Arrays;

public enum ClassicProtocolExtension {
   CLICK_DISTANCE("ClickDistance"),
   CUSTOM_BLOCKS("CustomBlocks", 1),
   HELD_BLOCK("HeldBlock"),
   TEXT_HOT_KEY("TextHotKey"),
   EXT_PLAYER_LIST("ExtPlayerList"),
   ENV_COLORS("EnvColors"),
   SELECTION_CUBOID("SelectionCuboid"),
   BLOCK_PERMISSIONS("BlockPermissions", 1),
   CHANGE_MODEL("ChangeModel"),
   ENV_MAP_APPEARANCE("EnvMapAppearance"),
   ENV_WEATHER_TYPE("EnvWeatherType"),
   HACK_CONTROL("HackControl", 1),
   EMOTE_FIX("EmoteFix", 1),
   MESSAGE_TYPES("MessageTypes"),
   LONGER_MESSAGES("LongerMessages", 1),
   FULL_CP437("FullCP437", 1),
   BLOCK_DEFINITIONS("BlockDefinitions"),
   BLOCK_DEFINITIONS_EXT("BlockDefinitionsExt"),
   TEXT_COLORS("TextColors"),
   BULK_BLOCK_UPDATE("BulkBlockUpdate", 1),
   ENV_MAP_ASPECT("EnvMapAspect"),
   PLAYER_CLICK("PlayerClick"),
   ENTITY_PROPERTY("EntityProperty"),
   EXT_ENTITY_POSITIONS("ExtEntityPositions"),
   TWO_WAY_PING("TwoWayPing", 1),
   INVENTORY_ORDER("InventoryOrder"),
   INSTANT_MOTD("InstantMOTD", 1),
   EXTENDED_BLOCKS("ExtendedBlocks"),
   FAST_MAP("FastMap"),
   EXTENDED_TEXTURES("ExtendedTextures"),
   SET_HOTBAR("SetHotbar"),
   SET_SPAWNPOINT("SetSpawnpoint"),
   VELOCITY_CONTROL("VelocityControl"),
   CUSTOM_PARTICLES("CustomParticles"),
   CUSTOM_MODELS("CustomModels"),
   EXT_ENTITY_TELEPORT("ExtEntityTeleport");

   private final String name;
   private final IntSet supportedVersions;

   private ClassicProtocolExtension(String name, int... supportedVersions) {
      this.name = name;
      this.supportedVersions = new IntOpenHashSet();

      for (int supportedVersion : supportedVersions) {
         this.supportedVersions.add(supportedVersion);
      }
   }

   public static ClassicProtocolExtension byName(String name) {
      return Arrays.stream(values()).filter(e -> e.name.equals(name)).findFirst().orElse(null);
   }

   public static ClassicProtocolExtension byNameAndVersion(String name, int version) {
      ClassicProtocolExtension extension = byName(name);
      return extension != null && extension.supportsVersion(version) ? extension : null;
   }

   public boolean supportsVersion(int version) {
      return this.supportedVersions.contains(version);
   }

   public IntSet getSupportedVersions() {
      return this.supportedVersions;
   }

   public int getHighestSupportedVersion() {
      int highest = 0;
      IntIterator it = this.supportedVersions.iterator();

      while (it.hasNext()) {
         int supportedVersion = (Integer)it.next();
         if (supportedVersion > highest) {
            highest = supportedVersion;
         }
      }

      return highest;
   }

   public boolean isSupported() {
      return !this.supportedVersions.isEmpty();
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
