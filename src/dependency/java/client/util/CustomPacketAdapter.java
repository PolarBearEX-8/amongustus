package client.util;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;

public class CustomPacketAdapter implements Packet {
   public byte[] byteFlagArray2 = new byte[0];
   public byte[] byteFlagArray = new byte[0];

   public SecretKey resolveSecretKey(PrivateKey privateKey) {
      return SharedLoggerEntry.resolveSecretKey(privateKey, this.byteFlagArray2);
   }

   public void applyInternalINetHandlerExtensi(InternalINetHandlerExtension internalINetHandlerExtension) {
      internalINetHandlerExtension.applyCustomPacketAdapter(this);
   }

   public byte[] resolveByteArray(PrivateKey privateKey) {
      return privateKey == null ? this.byteFlagArray : SharedLoggerEntry.resolveByteArrayByKey(privateKey, this.byteFlagArray);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.byteFlagArray2 = packet.getByteArray();
      this.byteFlagArray = packet.getByteArray();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyInternalINetHandlerExtensi((InternalINetHandlerExtension)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyByteArray(this.byteFlagArray2);
      packet.applyByteArray(this.byteFlagArray);
   }

   public CustomPacketAdapter(SecretKey secretKey, PublicKey publicKey, byte[] byteValueArray) {
      this.byteFlagArray2 = SharedLoggerEntry.resolveByteArray(publicKey, secretKey.getEncoded());
      this.byteFlagArray = SharedLoggerEntry.resolveByteArray(publicKey, byteValueArray);
   }

   public CustomPacketAdapter() {
   }
}
