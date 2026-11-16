package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.InvulnerableManager;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C13PacketPlayerAbilities implements Packet {
   public boolean enabled3;
   public boolean enabled4;
   public boolean enabled2;
   public boolean enabled;
   public float factor2;
   public float factor;

   public void setActive(boolean active) {
      this.enabled2 = active;
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC13PacketPlayerAbilities(this);
   }

   public void setActive2(boolean active) {
      this.enabled4 = active;
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      byte byteValue = packet.readByte();
      this.setEnabled3((byteValue & 1) > 0);
      this.setActive2((byteValue & 2) > 0);
      this.setActive((byteValue & 4) > 0);
      this.setEnabled((byteValue & 8) > 0);
      this.setFactor2(packet.readFloat());
      this.setFactor(packet.readFloat());
   }

   public void setEnabled(boolean enabled5) {
      this.enabled = enabled5;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void setEnabled3(boolean enabled5) {
      this.enabled3 = enabled5;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      byte byteValue = 0;
      if (this.isEnabled3()) {
         byteValue = (byte)(byteValue | 1);
      }

      if (this.isEnabled4()) {
         byteValue = (byte)(byteValue | 2);
      }

      if (this.isEnabled2()) {
         byteValue = (byte)(byteValue | 4);
      }

      if (this.isEnabled()) {
         byteValue = (byte)(byteValue | 8);
      }

      packet.writeByte(byteValue);
      packet.writeFloat(this.factor2);
      packet.writeFloat(this.factor);
   }

   public void setFactor2(float factor3) {
      this.factor2 = factor3;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   public C13PacketPlayerAbilities() {
   }

   public C13PacketPlayerAbilities(InvulnerableManager invulnerableManager) {
      this.setEnabled3(invulnerableManager.enabled);
      this.setActive2(invulnerableManager.state);
      this.setActive(invulnerableManager.state2);
      this.setEnabled(invulnerableManager.enabled2);
      this.setFactor2(invulnerableManager.getFactor());
      this.setFactor(invulnerableManager.getFactor2());
   }
}
