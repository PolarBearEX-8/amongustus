package net.augustus.viaversion.platform;

import net.augustus.viaversion.provider.ViaAugustusVersionProvider;
import net.augustus.viaversion.provider.ViaLegacyEncryptionProvider;
import net.augustus.viaversion.provider.ViaLegacyGameProfileFetcher;
import net.augustus.viaversion.provider.ViaLegacyOldAuthProvider;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.platform.ViaPlatformLoader;
import com.viaversion.viaversion.api.platform.providers.ViaProviders;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import net.raphimc.vialegacy.protocol.release.r1_2_4_5tor1_3_1_2.provider.OldAuthProvider;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.provider.EncryptionProvider;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.provider.GameProfileFetcher;

public final class ViaAugustusPlatformLoader implements ViaPlatformLoader {
   public void unload() {
   }

   public void load() {
      ViaProviders providers = Via.getManager().getProviders();
      providers.use(VersionProvider.class, new ViaAugustusVersionProvider());
      providers.use(EncryptionProvider.class, new ViaLegacyEncryptionProvider());
      providers.use(GameProfileFetcher.class, new ViaLegacyGameProfileFetcher());
      providers.use(OldAuthProvider.class, new ViaLegacyOldAuthProvider());
   }
}
