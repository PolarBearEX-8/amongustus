package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.InvulnerableManager;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S39PacketPlayerAbilities implements Packet {
   public boolean enabled4;
   public boolean enabled;
   public boolean enabled2;
   public boolean enabled3;
   public float factor;
   public float factor2;

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public float getFactor2() {
      return this.factor2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      byte byteValue = packet.readByte();
      this.setEnabled4((byteValue & 1) > 0);
      this.setEnabled((byteValue & 2) > 0);
      this.setEnabled2((byteValue & 4) > 0);
      this.setEnabled3((byteValue & 8) > 0);
      this.setFactor(packet.readFloat());
      this.setFactor2(packet.readFloat());
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public float getFactor() {
      return this.factor;
   }

   public void setEnabled2(boolean enabled5) {
      this.enabled2 = enabled5;
   }

   public void setEnabled(boolean enabled5) {
      this.enabled = enabled5;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS39PacketPlayerAbilities(this);
   }

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      byte byteValue = 0;
      if (this.isEnabled4()) {
         byteValue = (byte)(byteValue | 1);
      }

      if (this.isEnabled()) {
         byteValue = (byte)(byteValue | 2);
      }

      if (this.isEnabled2()) {
         byteValue = (byte)(byteValue | 4);
      }

      if (this.isEnabled3()) {
         byteValue = (byte)(byteValue | 8);
      }

      packet.writeByte(byteValue);
      packet.writeFloat(this.factor);
      packet.writeFloat(this.factor2);
   }

   public void setEnabled3(boolean enabled5) {
      this.enabled3 = enabled5;
   }

   public void setFactor2(float factor3) {
      this.factor2 = factor3;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void setEnabled4(boolean enabled5) {
      this.enabled4 = enabled5;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public S39PacketPlayerAbilities() {
   }

   public S39PacketPlayerAbilities(InvulnerableManager invulnerableManager) {
      this.setEnabled4(invulnerableManager.enabled);
      this.setEnabled(invulnerableManager.state);
      this.setEnabled2(invulnerableManager.state2);
      this.setEnabled3(invulnerableManager.enabled2);
      this.setFactor(invulnerableManager.getFactor());
      this.setFactor2(invulnerableManager.getFactor2());
   }
}
