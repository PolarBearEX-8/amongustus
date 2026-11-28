package net.raphimc.vialegacy.platform;

import com.viaversion.viaversion.api.configuration.Config;

public interface ViaLegacyConfig extends Config {
   boolean isDynamicOnground();

   boolean isIgnoreLong1_8ChannelNames();

   boolean isLegacySkullLoading();

   boolean isLegacySkinLoading();

   boolean isSoundEmulation();

   boolean isOldBiomes();

   boolean enableB1_7_3Sprinting();

   String getB1_7_3Motd();

   int getClassicChunkRange();

   boolean enableClassicFly();
}
