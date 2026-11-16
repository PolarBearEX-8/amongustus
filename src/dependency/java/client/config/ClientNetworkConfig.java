package client.config;

import client.setting.CombatSpeedProperty;
import client.util.AuxiliaryStringArrayEntry;
import client.util.InternalListenerCoordinator;
import client.util.ListenerTracker;
import client.util.SmoothPicker;
import io.netty.channel.Channel;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.net.InetSocketAddress;

public class ClientNetworkConfig extends ListenerTracker {
   public final SmoothPicker smoothPicker2;
   public final SmoothPicker smoothPicker3;
   public final SmoothPicker smoothPicker4;
   public final InternalListenerCoordinator internalListenerCoordinator2;
   public final InternalListenerCoordinator internalListenerCoordinator;
   public InetSocketAddress inetSocketAddress;
   public final SmoothPicker smoothPicker;
   public final AuxiliaryStringArrayEntry auxiliaryStringArrayEntry = new AuxiliaryStringArrayEntry(this, "Marked", false);

   public String getText2() {
      return (String)this.smoothPicker3.getValue();
   }

   public boolean isActiveAsFlag() {
      return (Boolean)this.auxiliaryStringArrayEntry.getValue();
   }

   public void setEnabled(boolean enabled) {
      AuxiliaryStringArrayEntry auxiliaryStringArrayEntry2 = this.auxiliaryStringArrayEntry;
      Boolean flag = enabled;
      auxiliaryStringArrayEntry2.applyObject2(flag);
   }

   public String getString3() {
      return (String)this.smoothPicker2.getValue();
   }

   private boolean isActive() {
      return this.getText() != null && !this.getText().isEmpty();
   }

   public int getInt() {
      return (Integer)this.internalListenerCoordinator.getValue();
   }

   public String getTextAsText() {
      return (String)this.smoothPicker.getValue();
   }

   public InetSocketAddress getInetSocketAddress() {
      return this.inetSocketAddress;
   }

   public void applyChannel(Channel channel) {
      if (this.inetSocketAddress != null) {
         String textAsText = this.getTextAsText();
         switch (textAsText) {
            case "Socks 4":
               channel.pipeline()
                  .addFirst(
                     "socks4-proxy-handler",
                     this.isActive() ? new Socks4ProxyHandler(this.inetSocketAddress, this.getText()) : new Socks4ProxyHandler(this.inetSocketAddress)
                  );
               break;
            case "Socks 5":
               channel.pipeline()
                  .addFirst(
                     "socks5-proxy-handler",
                     this.isActive()
                        ? new Socks5ProxyHandler(this.inetSocketAddress, this.getText(), this.getText2())
                        : new Socks5ProxyHandler(this.inetSocketAddress)
                  );
         }
      }
   }

   public void applyIndex(int count) {
      InternalListenerCoordinator internalListenerCoordinator3 = this.internalListenerCoordinator2;
      Integer count2 = count;
      internalListenerCoordinator3.applyObject2(count2);
   }

   public int getCount() {
      return (Integer)this.internalListenerCoordinator2.getValue();
   }

   public boolean matchesClientNetworkConfig(ClientNetworkConfig clientNetworkConfig) {
      return clientNetworkConfig != null
         && (
            clientNetworkConfig.getString3() == null && this.getString3() == null
               || clientNetworkConfig.getString3() != null && this.getString3() != null && clientNetworkConfig.getString3().equals(this.getString3())
         )
         && clientNetworkConfig.getInt() == this.getInt()
         && (clientNetworkConfig.getText() == null && this.getText() == null || clientNetworkConfig.getText() != null && this.getText() != null && clientNetworkConfig.getText().equals(this.getText()))
         && (
            clientNetworkConfig.getText2() == null && this.getText2() == null || clientNetworkConfig.getText2() != null && this.getText2() != null && clientNetworkConfig.getText2().equals(this.getText2())
         )
         && (
            clientNetworkConfig.getTextAsText() == null && this.getTextAsText() == null
               || clientNetworkConfig.getTextAsText() != null && this.getTextAsText() != null && clientNetworkConfig.getTextAsText().equals(this.getTextAsText())
         );
   }

   public String getText() {
      return (String)this.smoothPicker4.getValue();
   }

   public ClientNetworkConfig() {
      super(null);
      this.smoothPicker2 = new SmoothPicker(this, "IP", "");
      this.internalListenerCoordinator = new InternalListenerCoordinator(this, "Port", 0);
      this.internalListenerCoordinator2 = new InternalListenerCoordinator(this, "Ping", 0);
      this.smoothPicker4 = new SmoothPicker(this, "Username", "");
      this.smoothPicker3 = new SmoothPicker(this, "Password", "");
      this.smoothPicker = new SmoothPicker(this, "ProxyType", "");
      this.inetSocketAddress = null;
   }

   private ClientNetworkConfig(String text, int count, String text2, String text3) {
      super(text + ":" + count);
      this.smoothPicker2 = new SmoothPicker(this, "IP", "");
      this.internalListenerCoordinator = new InternalListenerCoordinator(this, "Port", 0);
      this.internalListenerCoordinator2 = new InternalListenerCoordinator(this, "Ping", 0);
      this.smoothPicker4 = new SmoothPicker(this, "Username", "");
      this.smoothPicker3 = new SmoothPicker(this, "Password", "");
      this.smoothPicker = new SmoothPicker(this, "ProxyType", "");
      this.inetSocketAddress = null;
      this.smoothPicker2.applyObject2(text);
      InternalListenerCoordinator internalListenerCoordinator3 = this.internalListenerCoordinator;
      Integer count2 = count;
      internalListenerCoordinator3.applyObject2(count2);
      this.smoothPicker4.applyObject2(text2);
      this.smoothPicker3.applyObject2(text3);

      try {
         this.inetSocketAddress = new InetSocketAddress(this.getString3(), this.getInt());
      } catch (Exception exception) {
      }
   }

   public ClientNetworkConfig(ClientNetworkConfig clientNetworkConfig) {
      this(clientNetworkConfig.getString3(), clientNetworkConfig.getInt(), clientNetworkConfig.getText(), clientNetworkConfig.getText2());
      int count = clientNetworkConfig.getCount();
      this.applyIndex(count);
      SmoothPicker smoothPicker5 = this.smoothPicker;
      String textAsText = clientNetworkConfig.getTextAsText();
      smoothPicker5.applyObject2(textAsText);
   }

   public ClientNetworkConfig(String text, int count, String text2, String text3, CombatSpeedProperty combatSpeedProperty) {
      this(text, count, text2, text3);
      SmoothPicker smoothPicker5 = this.smoothPicker;
      String labelText = combatSpeedProperty.getLabelText();
      smoothPicker5.applyObject2(labelText);
   }

   @Override
   public boolean equals(Object other) {
      if (other instanceof ClientNetworkConfig) {
         ClientNetworkConfig clientNetworkConfig = (ClientNetworkConfig)other;
         return this.matchesClientNetworkConfig(clientNetworkConfig);
      } else {
         return super.equals(other);
      }
   }
}
