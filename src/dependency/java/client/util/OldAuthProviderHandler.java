package client.util;

import com.viaversion.viaversion.api.connection.UserConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.raphimc.vialegacy.protocol.release.r1_2_4_5tor1_3_1_2.provider.OldAuthProvider;

public class OldAuthProviderHandler extends OldAuthProvider {
   public void sendAuthRequest(UserConnection userConnection, String text) throws Throwable {
      Session session = Minecraft.getMinecraft().session;
      Minecraft.getMinecraft().getMinecraftSessionService().joinServer(session.getGameProfile(), session.getTextAsText(), text);
   }
}
