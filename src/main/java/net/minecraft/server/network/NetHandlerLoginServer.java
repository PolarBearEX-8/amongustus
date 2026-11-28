package net.minecraft.server.network;

import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import java.math.BigInteger;
import net.minecraft.util.CryptManager;
import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import io.netty.util.concurrent.GenericFutureListener;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginServer implements INetHandlerLoginServer, ITickable {
   private static final AtomicInteger AUTHENTICATOR_THREAD_ID = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();
   private static final Random RANDOM = new Random();
   private final byte[] verifyToken = new byte[4];
   private final MinecraftServer server;
   public final NetworkManager networkManager;
   private NetHandlerLoginServer.LoginState currentLoginState = NetHandlerLoginServer.LoginState.HELLO;
   private int connectionTimer;
   private GameProfile loginGameProfile;
   private String serverId = "";
   private SecretKey secretKey;
   private EntityPlayerMP player;

   @Override
   public void update() {
      if (this.currentLoginState == NetHandlerLoginServer.LoginState.READY_TO_ACCEPT) {
         this.tryAcceptPlayer();
      } else if (this.currentLoginState == NetHandlerLoginServer.LoginState.DELAY_ACCEPT) {
         if (this.server.getConfigurationManager().getPlayerByUUID(this.loginGameProfile.getId()) == null) {
            this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
            this.server.getConfigurationManager().initializeConnectionToPlayer(this.networkManager, this.player);
            this.player = null;
         }
      }

      if (this.connectionTimer++ == 600) {
         this.closeConnection("Took too long to log in");
      }
   }

   public String getConnectionInfo() {
      return this.loginGameProfile != null
         ? this.loginGameProfile + " (" + this.networkManager.getRemoteAddress().toString() + ")"
         : String.valueOf(this.networkManager.getRemoteAddress());
   }

   public GameProfile getOfflineProfile(GameProfile profile) {
      UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + profile.getName()).getBytes(Charsets.UTF_8));
      return new GameProfile(uuid, profile.getName());
   }

   public static GameProfile assignLoginGameProfile(NetHandlerLoginServer netHandlerLoginServer, GameProfile profile) {
      return netHandlerLoginServer.loginGameProfile = profile;
   }

   public static SecretKey accessSecretKey(NetHandlerLoginServer netHandlerLoginServer) {
      return netHandlerLoginServer.secretKey;
   }

   public void tryAcceptPlayer() {
      if (!this.loginGameProfile.isComplete()) {
         this.loginGameProfile = this.getOfflineProfile(this.loginGameProfile);
      }

      String str = this.server.getConfigurationManager().allowUserToConnect(this.networkManager.getRemoteAddress(), this.loginGameProfile);
      if (str != null) {
         this.closeConnection(str);
      } else {
         this.currentLoginState = NetHandlerLoginServer.LoginState.ACCEPTED;
         if (this.server.getNetworkCompressionTreshold() >= 0 && !this.networkManager.isLocalChannel()) {
            this.networkManager
               .sendPacket(
                  new S03PacketEnableCompression(this.server.getNetworkCompressionTreshold()), new NetHandlerLoginServer.DefaultChannelFutureListener(this), new GenericFutureListener[0]
               );
         }

         this.networkManager.sendPacket(new S02PacketLoginSuccess(this.loginGameProfile));
         if (this.server.getConfigurationManager().getPlayerByUUID(this.loginGameProfile.getId()) != null) {
            this.currentLoginState = NetHandlerLoginServer.LoginState.DELAY_ACCEPT;
            this.player = this.server.getConfigurationManager().createPlayerForUser(this.loginGameProfile);
         } else {
            this.server
               .getConfigurationManager()
               .initializeConnectionToPlayer(this.networkManager, this.server.getConfigurationManager().createPlayerForUser(this.loginGameProfile));
         }
      }
   }

   public static NetHandlerLoginServer.LoginState assignCurrentLoginState(NetHandlerLoginServer netHandlerLoginServer, NetHandlerLoginServer.LoginState loginState) {
      return netHandlerLoginServer.currentLoginState = loginState;
   }

   public static Logger accessLogger() {
      return logger;
   }

   @Override
   public void processEncryptionResponse(C01PacketEncryptionResponse packet) {
      Validate.validState(this.currentLoginState == NetHandlerLoginServer.LoginState.KEY, "Unexpected key packet", new Object[0]);
      PrivateKey privateKey = this.server.getKeyPair().getPrivate();
      if (!Arrays.equals(this.verifyToken, packet.getVerifyToken(privateKey))) {
         throw new IllegalStateException("Invalid nonce!");
      } else {
         this.secretKey = packet.getSecretKey(privateKey);
         this.currentLoginState = NetHandlerLoginServer.LoginState.AUTHENTICATING;
         this.networkManager.enableEncryption(this.secretKey);
         new NetHandlerLoginServer.WorkerTask(this, "User Authenticator #" + AUTHENTICATOR_THREAD_ID.incrementAndGet()).start();
      }
   }

   @Override
   public void onDisconnect(IChatComponent component) {
      logger.info(this.getConnectionInfo() + " lost connection: " + component.getUnformattedText());
   }

   public NetHandlerLoginServer(MinecraftServer minecraftServer, NetworkManager networkManager2) {
      this.server = minecraftServer;
      this.networkManager = networkManager2;
      RANDOM.nextBytes(this.verifyToken);
   }

   @Override
   public void processLoginStart(C00PacketLoginStart packet) {
      Validate.validState(this.currentLoginState == NetHandlerLoginServer.LoginState.HELLO, "Unexpected hello packet", new Object[0]);
      this.loginGameProfile = packet.getProfile();
      if (this.server.isServerInOnlineMode() && !this.networkManager.isLocalChannel()) {
         this.currentLoginState = NetHandlerLoginServer.LoginState.KEY;
         this.networkManager.sendPacket(new S01PacketEncryptionRequest(this.serverId, this.server.getKeyPair().getPublic(), this.verifyToken));
      } else {
         this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
      }
   }

   public static String accessServerId(NetHandlerLoginServer netHandlerLoginServer) {
      return netHandlerLoginServer.serverId;
   }

   public void closeConnection(String str) {
      try {
         logger.info("Disconnecting " + this.getConnectionInfo() + ": " + str);
         ChatComponentText chatComponentText = new ChatComponentText(str);
         this.networkManager.sendPacket(new S00PacketDisconnect(chatComponentText));
         this.networkManager.closeChannel(chatComponentText);
      } catch (Exception e) {
         logger.error("Error whilst disconnecting player", e);
      }
   }

   public static MinecraftServer accessServer(NetHandlerLoginServer netHandlerLoginServer) {
      return netHandlerLoginServer.server;
   }

   public static GameProfile accessLoginGameProfile(NetHandlerLoginServer netHandlerLoginServer) {
      return netHandlerLoginServer.loginGameProfile;
   }


   public static class DefaultChannelFutureListener implements ChannelFutureListener {
      public final NetHandlerLoginServer loginServer;

      public void operationComplete(ChannelFuture channelFuture) throws Exception {
         this.loginServer.networkManager.setCompressionTreshold(NetHandlerLoginServer.accessServer(this.loginServer).getNetworkCompressionTreshold());
      }

      public DefaultChannelFutureListener(NetHandlerLoginServer netHandlerLoginServer) {
         this.loginServer = netHandlerLoginServer;
      }

   }

   public static class WorkerTask extends Thread {
      public final NetHandlerLoginServer loginServer;

      public WorkerTask(NetHandlerLoginServer netHandlerLoginServer, String str) {
         super(str);
         this.loginServer = netHandlerLoginServer;
      }

      @Override
      public void run() {
         GameProfile profile = NetHandlerLoginServer.accessLoginGameProfile(this.loginServer);

         try {
            String str = new BigInteger(
                  CryptManager.getServerIdHash(
                     NetHandlerLoginServer.accessServerId(this.loginServer),
                     NetHandlerLoginServer.accessServer(this.loginServer).getKeyPair().getPublic(),
                     NetHandlerLoginServer.accessSecretKey(this.loginServer)
                  )
               )
               .toString(16);
            NetHandlerLoginServer.assignLoginGameProfile(
               this.loginServer,
               NetHandlerLoginServer.accessServer(this.loginServer).getMinecraftSessionService().hasJoinedServer(new GameProfile((UUID)null, profile.getName()), str)
            );
            if (NetHandlerLoginServer.accessLoginGameProfile(this.loginServer) != null) {
               NetHandlerLoginServer.accessLogger()
                  .info("UUID of player " + NetHandlerLoginServer.accessLoginGameProfile(this.loginServer).getName() + " is " + NetHandlerLoginServer.accessLoginGameProfile(this.loginServer).getId());
               NetHandlerLoginServer.assignCurrentLoginState(this.loginServer, NetHandlerLoginServer.LoginState.READY_TO_ACCEPT);
            } else if (NetHandlerLoginServer.accessServer(this.loginServer).isSinglePlayer()) {
               NetHandlerLoginServer.accessLogger().warn("Failed to verify username but will let them in anyway!");
               NetHandlerLoginServer.assignLoginGameProfile(this.loginServer, this.loginServer.getOfflineProfile(profile));
               NetHandlerLoginServer.assignCurrentLoginState(this.loginServer, NetHandlerLoginServer.LoginState.READY_TO_ACCEPT);
            } else {
               this.loginServer.closeConnection("Failed to verify username!");
               NetHandlerLoginServer.accessLogger()
                  .error("Username '" + NetHandlerLoginServer.accessLoginGameProfile(this.loginServer).getName() + "' tried to join with an invalid session");
            }
         } catch (AuthenticationUnavailableException e) {
            if (NetHandlerLoginServer.accessServer(this.loginServer).isSinglePlayer()) {
               NetHandlerLoginServer.accessLogger().warn("Authentication servers are down but will let them in anyway!");
               NetHandlerLoginServer.assignLoginGameProfile(this.loginServer, this.loginServer.getOfflineProfile(profile));
               NetHandlerLoginServer.assignCurrentLoginState(this.loginServer, NetHandlerLoginServer.LoginState.READY_TO_ACCEPT);
            } else {
               this.loginServer.closeConnection("Authentication servers are down. Please try again later, sorry!");
               NetHandlerLoginServer.accessLogger().error("Couldn't verify username because servers are unavailable");
            }
         }
      }
   }

   public enum LoginState {
      HELLO,
      KEY,
      AUTHENTICATING,
      READY_TO_ACCEPT,
      DELAY_ACCEPT,
      ACCEPTED;
   }
}
