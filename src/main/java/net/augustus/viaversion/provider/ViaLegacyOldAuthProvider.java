package net.augustus.viaversion.provider;

import com.viaversion.viaversion.api.connection.UserConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.raphimc.vialegacy.protocol.release.r1_2_4_5tor1_3_1_2.provider.OldAuthProvider;

public final class ViaLegacyOldAuthProvider extends OldAuthProvider {
   @Override
   public void sendAuthRequest(UserConnection userConnection, String str) throws Throwable {
      Session session = Minecraft.getMinecraft().session;
      Minecraft.getMinecraft().getSessionService().joinServer(session.getProfile(), session.getSessionID(), str);
   }
}
