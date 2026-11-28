package net.augustus.viaversion.provider;

import net.augustus.viaversion.ViaAugustus;
import com.viaversion.viaversion.api.connection.UserConnection;
import net.minecraft.network.NetworkManager;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.provider.EncryptionProvider;

public final class ViaLegacyEncryptionProvider extends EncryptionProvider {
   @Override
   public void enableDecryption(UserConnection userConnection) {
      ((NetworkManager)userConnection.getChannel().attr(ViaAugustus.CONNECTION_ATTRIBUTE_KEY).get()).enableViaLegacyDecryption();
   }
}
