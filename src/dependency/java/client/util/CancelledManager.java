package client.util;

import client.setting.CombatKeybindProperty;
import client.via.ViaLoadingBase;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelledManager {
   public static final Splitter splitter = Splitter.on('\u0000').limit(6);
   public static final Logger logger = LogManager.getLogger();
   public final List list = Collections.synchronizedList(Lists.newArrayList());

   public void updateState2() {
      synchronized (this.list) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            NetworkManager networkManager = (NetworkManager)iterator.next();
            if (networkManager.isOpen()) {
               iterator.remove();
               networkManager.applyIChatComponent(new ChatComponentText("Cancelled"));
            }
         }
      }
   }

   public void updateState() {
      synchronized (this.list) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            NetworkManager networkManager = (NetworkManager)iterator.next();
            if (networkManager.isOpen()) {
               networkManager.updateState2();
            } else {
               iterator.remove();
               networkManager.updateState3();
            }
         }
      }
   }

   public static Logger getLogger() {
      return logger;
   }

   public void applyServerData(ServerData serverData) throws UnknownHostException {
      JavaNamingFactoryInitialManager javaNamingFactoryInitialManager = JavaNamingFactoryInitialManager.resolveJavaNamingFactoryInitialMana(serverData.text);
      InetAddress byName = InetAddress.getByName(javaNamingFactoryInitialManager.toASCII());
      NetworkManager networkManager = NetworkManager.resolveNetworkManager(byName, javaNamingFactoryInitialManager.getCount(), false);
      this.list.add(networkManager);
      serverData.labelText = "Pinging...";
      serverData.timestamp = -1L;
      serverData.labelText6 = null;
      networkManager.applyINetHandler(new SmoothProvider(this, networkManager, serverData));

      try {
         if (ViaLoadingBase.protocolVersion.newerThan(ProtocolVersion.v1_17)) {
            networkManager.handlePacket6(new BasePacketAdapter(47, byName.getHostName(), javaNamingFactoryInitialManager.getCount(), CombatKeybindProperty.STATUS));
         } else {
            networkManager.handlePacket6(new BasePacketAdapter(47, javaNamingFactoryInitialManager.toASCII(), javaNamingFactoryInitialManager.getCount(), CombatKeybindProperty.STATUS));
         }

         networkManager.handlePacket6(new CommonPacketAdapter());
      } catch (Throwable throwable) {
         logger.error(throwable);
      }
   }
}
