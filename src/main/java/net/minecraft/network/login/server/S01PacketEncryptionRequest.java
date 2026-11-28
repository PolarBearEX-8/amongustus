package net.minecraft.network.login.server;

import java.io.IOException;
import java.security.PublicKey;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.CryptManager;

public class S01PacketEncryptionRequest implements Packet {
   private String hashedServerId;
   private PublicKey publicKey;
   private byte[] verifyToken;

   public String getServerId() {
      return this.hashedServerId;
   }

   public PublicKey getPublicKey() {
      return this.publicKey;
   }

   public byte[] getVerifyToken() {
      return this.verifyToken;
   }

   public S01PacketEncryptionRequest(String str, PublicKey publicKey2, byte[] bytes) {
      this.hashedServerId = str;
      this.publicKey = publicKey2;
      this.verifyToken = bytes;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerLoginClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.hashedServerId);
      buf.writeByteArray(this.publicKey.getEncoded());
      buf.writeByteArray(this.verifyToken);
   }

   public void processPacket(INetHandlerLoginClient handler) {
      handler.handleEncryptionRequest(this);
   }

   public S01PacketEncryptionRequest() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.hashedServerId = buf.readStringFromBuffer(20);
      this.publicKey = CryptManager.decodePublicKey(buf.readByteArray());
      this.verifyToken = buf.readByteArray();
   }
}
