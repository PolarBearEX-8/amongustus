package client.util;

import client.Client;
import client.ClientGlobals;
import client.gui.CustomGuiScreenAdapter;
import client.setting.CombatKeybindProperty;
import client.setting.CombatOffsetProperty;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.network.NetworkManager;

public class CheatingThread extends Thread {
   public final String labelText;
   public final int count;
   public final GuiConnecting guiConnecting;

   public CheatingThread(GuiConnecting guiConnecting2, String text, String text2, int count2) {
      super(text);
      this.guiConnecting = guiConnecting2;
      this.labelText = text2;
      this.count = count2;
   }

   @Override
   public void run() {
      GuiConnecting.add(CombatOffsetProperty.STARTING_NETTY_CONNECTION);
      InetAddress inetAddress = null;

      try {
         if (GuiConnecting.matchesGuiConnecting(this.guiConnecting)) {
            return;
         }

         GuiConnecting.add(CombatOffsetProperty.RESOLVING_IP);
         inetAddress = InetAddress.getByName(this.labelText);
         ClientGlobals.moduleManager.timerRange.timerDelayMs = 0L;
         GuiConnecting.resolveNetworkManager(
            this.guiConnecting,
            NetworkManager.resolveNetworkManager(inetAddress, this.count, GuiConnecting.resolveMinecraftByGuiConnecting(this.guiConnecting).gameSettings.isActive())
         );
         GuiConnecting.add(CombatOffsetProperty.CONNECTING);
         GuiConnecting.resolveNetworkManager(this.guiConnecting)
            .applyINetHandler(
               new ConnectFailedManager(
                  GuiConnecting.resolveNetworkManager(this.guiConnecting),
                  GuiConnecting.resolveMinecraft9(this.guiConnecting),
                  GuiConnecting.resolveGuiScreen(this.guiConnecting)
               )
            );
         GuiConnecting.add(CombatOffsetProperty.SENDING_LOGIN_PACKETS);
         if (ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_17)) {
            GuiConnecting.resolveNetworkManager(this.guiConnecting)
               .handlePacket6(new BasePacketAdapter(47, inetAddress.getHostName(), this.count, CombatKeybindProperty.LOGIN));
         } else {
            GuiConnecting.resolveNetworkManager(this.guiConnecting)
               .handlePacket6(new BasePacketAdapter(47, this.labelText, this.count, CombatKeybindProperty.LOGIN));
         }

         GuiConnecting.resolveNetworkManager(this.guiConnecting)
            .handlePacket6(new ExtraPacketHandler(GuiConnecting.resolveMinecraft7(this.guiConnecting).getSession().getGameProfile()));
         String text = Minecraft.getMinecraft().session.getText1();
         String text2 = this.labelText + (this.count == 25565 ? "" : ":" + this.count);
         MsnManager.applyText(text, text2);
         DiscordRpcWorker threadEntry = Client.getClient().getThreadEntry();
         String text3 = "Cheating";
         String text4 = "Maybe cheating on ReifenHD.net";
         threadEntry.applyString(text4, text3);
         GuiConnecting.add(CombatOffsetProperty.WAITING_FOR_RESPONSE);
      } catch (UnknownHostException unknownHostException) {
         if (GuiConnecting.matchesGuiConnecting(this.guiConnecting)) {
            return;
         }

         GuiConnecting.getLogger2().error("Couldn't connect to server", unknownHostException);
         CustomGuiScreenAdapter customGuiScreenAdapter = new CustomGuiScreenAdapter(
            GuiConnecting.resolveGuiScreen(this.guiConnecting),
            "connect.failed",
            new BaseManagerStyleIChatComponentAdapter("disconnect.genericReason", new Object[]{"Unknown host"})
         );
         GuiConnecting.resolveMinecraft5(this.guiConnecting).displayGuiScreen(customGuiScreenAdapter);
      } catch (Exception exception) {
         if (GuiConnecting.matchesGuiConnecting(this.guiConnecting)) {
            return;
         }

         GuiConnecting.getLogger2().error("Couldn't connect to server", exception);
         String text5 = exception.toString();
         if (inetAddress != null) {
            String text6 = inetAddress + ":" + this.count;
            text5 = text5.replaceAll(text6, "");
         }

         CustomGuiScreenAdapter customGuiScreenAdapter2 = new CustomGuiScreenAdapter(
            GuiConnecting.resolveGuiScreen(this.guiConnecting),
            "connect.failed",
            new BaseManagerStyleIChatComponentAdapter("disconnect.genericReason", new Object[]{text5})
         );
         GuiConnecting.resolveMinecraft3(this.guiConnecting).displayGuiScreen(customGuiScreenAdapter2);
      }
   }
}
