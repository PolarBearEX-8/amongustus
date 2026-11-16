package client.util;

import com.viaversion.vialoader.impl.viaversion.VLLoader;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.platform.providers.ViaProviders;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import net.raphimc.vialegacy.protocol.release.r1_2_4_5tor1_3_1_2.provider.OldAuthProvider;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.provider.EncryptionProvider;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.provider.GameProfileFetcher;

public class ViaPlatformLoader extends VLLoader {
   public void load() {
      super.load();
      ViaProviders providers = Via.getManager().getProviders();
      providers.use(VersionProvider.class, new BaseVersionProviderAdapter());
      providers.use(EncryptionProvider.class, new EncryptionProviderAdapter());
      providers.use(GameProfileFetcher.class, new GameProfileFetcherAdapter());
      providers.use(OldAuthProvider.class, new OldAuthProviderHandler());
   }
}
