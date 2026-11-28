package net.minecraft.client.network;

import io.netty.util.concurrent.Future;
import java.net.PasswordAuthentication;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.net.Authenticator;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.augustus.utils.network.ProxyEntry;
import net.augustus.ui.multiplayer.ConnectionStatus;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginClient implements INetHandlerLoginClient {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Minecraft mc;
   private final GuiScreen previousGuiScreen;
   private final NetworkManager networkManager;
   private GameProfile gameProfile;

   public void handleEncryptionRequest(S01PacketEncryptionRequest packet) {
      SecretKey secretKey = CryptManager.createNewSharedKey();
      String serverId = packet.getServerId();
      PublicKey publicKey = packet.getPublicKey();
      String str = new BigInteger(CryptManager.getServerIdHash(serverId, publicKey, secretKey)).toString(16);
      if (this.mc.getCurrentServerData() != null && this.mc.getCurrentServerData().isLanServer()) {
         try {
            GuiConnecting.addConnectionStatus(ConnectionStatus.VERIFYING_SESSION);
            this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), str);
         } catch (AuthenticationException e) {
            LOGGER.warn("Couldn't connect to auth servers but will continue to join LAN");
         }
      } else {
         try {
            GuiConnecting.addConnectionStatus(ConnectionStatus.VERIFYING_SESSION);
            this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), str);
         } catch (AuthenticationUnavailableException e2) {
            this.networkManager
               .closeChannel(
                  new ChatComponentText("§c§lLogin failed!\n\n§cAuthentication servers are down.\n§7You will need to wait until they are back up to login.")
               );
            return;
         } catch (InvalidCredentialsException e3) {
            this.networkManager
               .closeChannel(new ChatComponentText("§c§lLogin failed!\n\n§cYour session is no longer valid.\n§7Please relogin in the §6§laugustus menu"));
            return;
         } catch (AuthenticationException e4) {
            this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[]{e4.getMessage()}));
            return;
         } catch (NullPointerException e5) {
            this.networkManager.closeChannel(new ChatComponentText("§c§lLogin failed!\n\n§cPlease login to a §6§lpremium account §cin the §6§laugustus menu"));
            return;
         }
      }

      this.networkManager
         .sendPacket(new C01PacketEncryptionResponse(secretKey, publicKey, packet.getVerifyToken()), new NetHandlerLoginClient.DefaultGenericFutureListener(this, secretKey), new GenericFutureListener[0]);
   }

   private MinecraftSessionService getSessionService() {
      ProxyEntry proxy = Augustus.getInstance().getProxy();
      if (proxy != null) {
         String username = proxy.getUsername();
         String password = proxy.getPassword();
         if (username != null && !username.isEmpty()) {
            Authenticator.setDefault(new NetHandlerLoginClient.DefaultAuthenticator(this, username, password));
         }

         MinecraftSessionService sessionService = proxy.getSessionService();
         if (sessionService != null) {
            return sessionService;
         }
      }

      return this.mc.getSessionService();
   }

   public void onDisconnect(IChatComponent component) {
      this.mc.displayGuiScreen(new GuiDisconnected(this.previousGuiScreen, "connect.failed", component));
   }

   public NetHandlerLoginClient(NetworkManager networkManager2, Minecraft mc2, GuiScreen screen) {
      this.networkManager = networkManager2;
      this.mc = mc2;
      this.previousGuiScreen = screen;
   }

   public void handleEnableCompression(S03PacketEnableCompression packet) {
      if (!this.networkManager.isLocalChannel()) {
         GuiConnecting.addConnectionStatus(ConnectionStatus.ENABLING_COMPRESSION);
         this.networkManager.setCompressionTreshold(packet.getCompressionTreshold());
      }
   }

   public void handleLoginSuccess(S02PacketLoginSuccess packet) {
      GuiConnecting.addConnectionStatus(ConnectionStatus.SUCCESS);
      this.gameProfile = packet.getProfile();
      this.networkManager.setConnectionState(EnumConnectionState.PLAY);
      this.networkManager.setNetHandler(new NetHandlerPlayClient(this.mc, this.previousGuiScreen, this.networkManager, this.gameProfile));
   }

   public void handleDisconnect(S00PacketDisconnect packet) {
      this.networkManager.closeChannel(packet.getReason());
   }

   public static NetworkManager accessNetworkManager(NetHandlerLoginClient netHandlerLoginClient) {
      return netHandlerLoginClient.networkManager;
   }



   public static class DefaultGenericFutureListener implements GenericFutureListener {
      public final SecretKey secretKey;
      public final NetHandlerLoginClient netHandlerLoginClient;

      public void operationComplete(Future future) throws Exception {
         GuiConnecting.addConnectionStatus(ConnectionStatus.ENCRYPTING);
         NetHandlerLoginClient.accessNetworkManager(this.netHandlerLoginClient).enableEncryption(this.secretKey);
      }

      public DefaultGenericFutureListener(NetHandlerLoginClient netHandlerLoginClient2, SecretKey secretKey2) {
         this.netHandlerLoginClient = netHandlerLoginClient2;
         this.secretKey = secretKey2;
      }
   }

   public static class DefaultAuthenticator extends Authenticator {
      public final String proxyUser;
      public final String proxyPassword;
      public final NetHandlerLoginClient netHandlerLoginClient;

      public DefaultAuthenticator(NetHandlerLoginClient netHandlerLoginClient2, String str, String str2) {
         this.netHandlerLoginClient = netHandlerLoginClient2;
         this.proxyUser = str;
         this.proxyPassword = str2;
      }

      @Override
      public PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(this.proxyUser, this.proxyPassword != null ? this.proxyPassword.toCharArray() : new char[0]);
      }
   }
}
