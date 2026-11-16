package client.util;

import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.connection.UserConnection;
import net.minecraft.network.NetworkManager;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.provider.EncryptionProvider;

public class EncryptionProviderAdapter extends EncryptionProvider {
   public void enableDecryption(UserConnection userConnection) {
      NetworkManager networkManager = (NetworkManager)userConnection.getChannel().attr(ViaLoadingBase.attributeKey).get();
      if (networkManager != null) {
         networkManager.updateState5();
      }
   }
}
