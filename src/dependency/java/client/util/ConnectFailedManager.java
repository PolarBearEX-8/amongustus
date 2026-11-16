package client.util;

import client.gui.CustomGuiScreenAdapter;
import client.setting.CombatKeybindProperty;
import client.setting.CombatOffsetProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectFailedManager implements INetHandlerExtension {
   public static final Logger logger = LogManager.getLogger();
   public final Minecraft mc;
   public final GuiScreen guiScreen;
   public final NetworkManager networkManager;
   public GameProfile gameProfile;

   @Override
   public void applySecondaryPacketAdapter(SecondaryPacketAdapter packet) {
      GuiConnecting.add(CombatOffsetProperty.SUCCESS);
      this.gameProfile = packet.getGameProfile();
      this.networkManager.applyCombatKeybindProperty(CombatKeybindProperty.PLAY);
      this.networkManager.applyINetHandler(new NetHandlerPlayClient(this.mc, this.guiScreen, this.networkManager, this.gameProfile));
   }

   @Override
   public void applyTertiaryPacketAdapter(TertiaryPacketAdapter packet) {
      SecretKey secretKey = SharedLoggerEntry.getSecretKey();
      String labelText = packet.getLabelText();
      PublicKey publicKey = packet.getPublicKey();
      labelText = new BigInteger(SharedLoggerEntry.resolveByteArray(labelText, publicKey, secretKey)).toString(16);
      if (this.mc.getServerData() != null && this.mc.getServerData().isEnabled2()) {
         try {
            GuiConnecting.add(CombatOffsetProperty.VERIFYING_SESSION);
            this.getMinecraftSessionService().joinServer(this.mc.getSession().getGameProfile(), this.mc.getSession().getText2(), labelText);
         } catch (AuthenticationException authenticationException) {
            logger.warn("Couldn't connect to auth servers but will continue to join LAN");
         }
      } else {
         try {
            GuiConnecting.add(CombatOffsetProperty.VERIFYING_SESSION);
            this.getMinecraftSessionService().joinServer(this.mc.getSession().getGameProfile(), this.mc.getSession().getText2(), labelText);
         } catch (AuthenticationUnavailableException authenticationUnavailableException) {
            this.networkManager
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter(
                     "disconnect.loginFailedInfo",
                     new Object[]{new BaseManagerStyleIChatComponentAdapter("disconnect.loginFailedInfo.serversUnavailable", new Object[0])}
                  )
               );
            return;
         } catch (InvalidCredentialsException invalidCredentialsException) {
            this.networkManager
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter(
                     "disconnect.loginFailedInfo",
                     new Object[]{new BaseManagerStyleIChatComponentAdapter("disconnect.loginFailedInfo.invalidSession", new Object[0])}
                  )
               );
            return;
         } catch (AuthenticationException authenticationException2) {
            this.networkManager.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("disconnect.loginFailedInfo", new Object[]{authenticationException2.getMessage()}));
            return;
         } catch (NullPointerException nullPointerException) {
            this.networkManager
               .applyIChatComponent(
                  new ChatComponentText(
                     "§c§lLogin failed!\n\n§cPlease login to a §6§lpremium account §cin the §6§laugustus menu"
                  )
               );
            return;
         }
      }

      this.networkManager.applyPacket(new CustomPacketAdapter(secretKey, publicKey, packet.getByteFlagArray()), new SmoothSorter(this, secretKey), new GenericFutureListener[0]);
   }

   @Override
   public void applyStandardPacketAdapter(StandardPacketAdapter packet) {
      if (!this.networkManager.isActive()) {
         GuiConnecting.add(CombatOffsetProperty.ENABLING_COMPRESSION);
         this.networkManager.applyIndex(packet.getCount());
      }
   }

   @Override
   public void applySpecialPacketAdapter(SpecialPacketAdapter packet) {
      this.networkManager.applyIChatComponent(packet.getIChatComponent());
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      Minecraft mc2 = this.mc;
      CustomGuiScreenAdapter customGuiScreenAdapter = new CustomGuiScreenAdapter(this.guiScreen, "connect.failed", iChatComponent);
      mc2.displayGuiScreen(customGuiScreenAdapter);
   }

   private MinecraftSessionService getMinecraftSessionService() {
      return this.mc.getMinecraftSessionService();
   }

   public static NetworkManager resolveNetworkManager(ConnectFailedManager connectFailedManager) {
      return connectFailedManager.networkManager;
   }

   public ConnectFailedManager(NetworkManager networkManager2, Minecraft mc2, GuiScreen screen) {
      this.networkManager = networkManager2;
      this.mc = mc2;
      this.guiScreen = screen;
   }
}
