package client.util;

import java.io.IOException;
import java.security.PublicKey;

public class TertiaryPacketAdapter implements Packet {
   public String labelText;
   public PublicKey publicKey;
   public byte[] byteFlagArray;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(20);
      this.publicKey = SharedLoggerEntry.resolvePublicKey(packet.getByteArray());
      this.byteFlagArray = packet.getByteArray();
   }

   public void applyINetHandlerExtension(INetHandlerExtension iNetHandlerExtension) {
      iNetHandlerExtension.applyTertiaryPacketAdapter(this);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyINetHandlerExtension((INetHandlerExtension)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.applyByteArray(this.publicKey.getEncoded());
      packet.applyByteArray(this.byteFlagArray);
   }

   public PublicKey getPublicKey() {
      return this.publicKey;
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public TertiaryPacketAdapter() {
   }

   public TertiaryPacketAdapter(String text, PublicKey publicKey2, byte[] byteValueArray) {
      this.labelText = text;
      this.publicKey = publicKey2;
      this.byteFlagArray = byteValueArray;
   }
}
