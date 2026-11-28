package net.minecraft.client.multiplayer;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.augustus.Augustus;
import net.augustus.discord.DiscordRpcManager;
import net.augustus.ui.account.AccountAuthenticator;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentTranslation;
import net.augustus.utils.render.ShaderRenderPass;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import net.augustus.ClientManagers;
import net.augustus.ui.multiplayer.ConnectionStatus;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiConnecting extends GuiScreen {
   private static final List CONNECTION_STATUSES = new CopyOnWriteArrayList();
   private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();
   private NetworkManager networkManager;
   private boolean cancel;
   private final GuiScreen previousGuiScreen;
   public static String lastServerIp;
   public static String lastRawServerIp = "";
   public static int lastServerPort;

   public static Minecraft getMcForLoginHandler(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   public GuiConnecting(GuiScreen screen, Minecraft mc2, String str, int n) {
      this.mc = mc2;
      this.previousGuiScreen = screen;
      mc2.loadWorld((WorldClient)null);
      this.connect(str, n);
   }

   @Override
   public void updateScreen() {
      if (this.networkManager != null) {
         if (this.networkManager.isChannelOpen()) {
            this.networkManager.processReceivedPackets();
         } else {
            this.networkManager.checkDisconnected();
         }
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      int n = (ConnectionStatus.values().length + 1) * this.fontRendererObj.FONT_HEIGHT + 2;
      int n2 = this.height / 4 + 110 - n;
      BlurShadowManager.enableOneShotPass(true, false);
      BlurShadowManager.submitRenderTask(
         false,
         shaderRenderPass -> {
            if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
               RenderUtil.drawRoundedRect(
                  (float)this.width / 2.0F - 100.0F,
                  (float)n2,
                  (float)this.width / 2.0F + 100.0F,
                  (float)(n2 + n),
                  (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
                  1,
                  Integer.MIN_VALUE
               );
               GlStateManager.enableBlend();
            } else {
               RenderUtil.drawRect(this.width / 2 - 100, n2, this.width / 2 + 100, n2 + n, Integer.MIN_VALUE);
            }
         }
      );
      int n3 = 0;

      for (ConnectionStatus connectionStatus : (Iterable<ConnectionStatus>)(CONNECTION_STATUSES)) {
         this.drawString(
            this.fontRendererObj,
            EnumChatFormatting.RED + connectionStatus.getMessage(),
            this.width / 2 - 100 + 1 + 3,
            n2 + n3 * this.fontRendererObj.FONT_HEIGHT + 2 + 3,
            -1
         );
         n3++;
      }

      String str;
      switch ((int)(Minecraft.getSystemTime() / 300L % 4L)) {
         case 0:
         case 2:
         default:
            str = "_";
            break;
         case 1:
         case 3:
            str = "";
      }

      if (!CONNECTION_STATUSES.contains(ConnectionStatus.SUCCESS)) {
         this.drawString(this.fontRendererObj, str, this.width / 2 - 100 + 1 + 3, n2 + n3 * this.fontRendererObj.FONT_HEIGHT + 2 + 3, -1);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public static Minecraft getMcForConnect(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   public static List getConnectionStatuses() {
      return CONNECTION_STATUSES;
   }

   public static Logger accessLogger() {
      return logger;
   }

   private void connect(String str, int n) {
      lastServerIp = str;
      lastServerPort = n;
      logger.info("Connecting to " + str + ", " + n);
      new GuiConnecting.WorkerTask(this, "Server Connector #" + CONNECTION_ID.incrementAndGet(), str, n).start();
   }

   public static boolean accessCancel(GuiConnecting guiConnecting) {
      return guiConnecting.cancel;
   }

   public static Minecraft getMcForUnknownHost(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
   }

   public static NetworkManager assignNetworkManager(GuiConnecting guiConnecting, NetworkManager networkManager2) {
      return guiConnecting.networkManager = networkManager2;
   }

   public static GuiScreen accessPreviousGuiScreen(GuiConnecting guiConnecting) {
      return guiConnecting.previousGuiScreen;
   }

   public GuiConnecting(GuiScreen screen, Minecraft mc2, ServerData serverData) {
      this.mc = mc2;
      this.previousGuiScreen = screen;
      addConnectionStatus(ConnectionStatus.RESOLVING_SRV);
      lastRawServerIp = serverData.serverIP;
      ServerAddress serverAddress = ServerAddress.fromString(serverData.serverIP);
      mc2.loadWorld((WorldClient)null);
      mc2.setServerData(serverData);
      this.connect(serverAddress.getIP(), serverAddress.getPort());
   }

   public static NetworkManager accessNetworkManager(GuiConnecting guiConnecting) {
      return guiConnecting.networkManager;
   }

   public static Minecraft getMcForError(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   public static void addConnectionStatus(ConnectionStatus connectionStatus) {
      CONNECTION_STATUSES.add(connectionStatus);
   }

   public static Minecraft getMcForException(GuiConnecting guiConnecting) {
      return guiConnecting.mc;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 0) {
         this.cancel = true;
         if (this.networkManager != null) {
            this.networkManager.closeChannel(new ChatComponentText("Aborted"));
         }

         GuiScreen previousGuiScreen2 = this.previousGuiScreen;
         this.mc.displayGuiScreen(previousGuiScreen2);
      }
   }



   public static class WorkerTask extends Thread {
      public final String ip;
      public final int port;
      public final GuiConnecting guiConnecting;

      @Override
      public void run() {
         GuiConnecting.getConnectionStatuses().clear();
         GuiConnecting.addConnectionStatus(ConnectionStatus.STARTING_NETTY_CONNECTION);
         InetAddress inetAddress = null;

         try {
            if (GuiConnecting.accessCancel(this.guiConnecting)) {
               return;
            }

            GuiConnecting.addConnectionStatus(ConnectionStatus.RESOLVING_IP);
            inetAddress = InetAddress.getByName(this.ip);
            ClientManagers.moduleManager.timerRange.timerBalanceMs = 0L;
            GuiConnecting.assignNetworkManager(this.guiConnecting, NetworkManager.createNetworkManagerAndConnect(inetAddress, this.port, GuiConnecting.getMcForConnect(this.guiConnecting).gameSettings.isUsingNativeTransport()));
            GuiConnecting.addConnectionStatus(ConnectionStatus.CONNECTING);
            GuiConnecting.accessNetworkManager(this.guiConnecting)
               .setNetHandler(new NetHandlerLoginClient(GuiConnecting.accessNetworkManager(this.guiConnecting), GuiConnecting.getMcForLoginHandler(this.guiConnecting), GuiConnecting.accessPreviousGuiScreen(this.guiConnecting)));
            GuiConnecting.addConnectionStatus(ConnectionStatus.SENDING_LOGIN_PACKETS);
            if (ViaAugustus.targetProtocolVersion.newerThan(ProtocolVersion.v1_17)) {
               GuiConnecting.accessNetworkManager(this.guiConnecting).sendPacket(new C00Handshake(47, inetAddress.getHostName(), this.port, EnumConnectionState.LOGIN));
            } else {
               GuiConnecting.accessNetworkManager(this.guiConnecting).sendPacket(new C00Handshake(47, this.ip, this.port, EnumConnectionState.LOGIN));
            }

            GuiConnecting.accessNetworkManager(this.guiConnecting).sendPacket(new C00PacketLoginStart(GuiConnecting.getMcForUnknownHost(this.guiConnecting).getSession().getProfile()));
            AccountAuthenticator.recordServerConnection(Minecraft.getMinecraft().session.getUsername(), this.ip + (this.port == 25565 ? "" : ":" + this.port));
            DiscordRpcManager discordRpcManager = Augustus.getInstance().getDiscordRpcManager();
            String str2 = "Cheating";
            discordRpcManager.updatePresence("Maybe cheating on ReifenHD.net", str2);
            GuiConnecting.addConnectionStatus(ConnectionStatus.WAITING_FOR_RESPONSE);
         } catch (UnknownHostException e) {
            if (GuiConnecting.accessCancel(this.guiConnecting)) {
               return;
            }

            GuiConnecting.accessLogger().error("Couldn't connect to server", e);
            GuiDisconnected guiDisconnected = new GuiDisconnected(
               GuiConnecting.accessPreviousGuiScreen(this.guiConnecting), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Unknown host"})
            );
            GuiConnecting.getMcForException(this.guiConnecting).displayGuiScreen(guiDisconnected);
         } catch (Exception e2) {
            if (GuiConnecting.accessCancel(this.guiConnecting)) {
               return;
            }

            GuiConnecting.accessLogger().error("Couldn't connect to server", e2);
            String str4 = e2.toString();
            if (inetAddress != null) {
               str4 = str4.replaceAll(inetAddress + ":" + this.port, "");
            }

            GuiDisconnected guiDisconnected2 = new GuiDisconnected(
               GuiConnecting.accessPreviousGuiScreen(this.guiConnecting), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{str4})
            );
            GuiConnecting.getMcForError(this.guiConnecting).displayGuiScreen(guiDisconnected2);
         }
      }

      public WorkerTask(GuiConnecting guiConnecting2, String str, String str2, int n) {
         super(str);
         this.guiConnecting = guiConnecting2;
         this.ip = str2;
         this.port = n;
      }

   }
}
