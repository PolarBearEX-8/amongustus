package net.minecraft.network.login.client;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.util.CryptManager;

public class C01PacketEncryptionResponse implements Packet {
   public byte[] secretKeyEncrypted = new byte[0];
   public byte[] verifyTokenEncrypted = new byte[0];

   public SecretKey getSecretKey(PrivateKey privateKey) {
      return CryptManager.decryptSharedKey(privateKey, this.secretKeyEncrypted);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByteArray(this.secretKeyEncrypted);
      buf.writeByteArray(this.verifyTokenEncrypted);
   }

   public C01PacketEncryptionResponse(SecretKey secretKey, PublicKey publicKey, byte[] bytes) {
      this.secretKeyEncrypted = CryptManager.encryptData(publicKey, secretKey.getEncoded());
      this.verifyTokenEncrypted = CryptManager.encryptData(publicKey, bytes);
   }

   public byte[] getVerifyToken(PrivateKey privateKey) {
      return privateKey == null ? this.verifyTokenEncrypted : CryptManager.decryptData(privateKey, this.verifyTokenEncrypted);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.secretKeyEncrypted = buf.readByteArray();
      this.verifyTokenEncrypted = buf.readByteArray();
   }

   public void processPacket(INetHandlerLoginServer handler) {
      handler.processEncryptionResponse(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerLoginServer)handler);
   }

   public C01PacketEncryptionResponse() {
   }
}
