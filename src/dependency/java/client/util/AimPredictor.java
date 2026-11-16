package client.util;

import com.viaversion.vialoader.netty.VLLegacyPipeline;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public class AimPredictor extends VLLegacyPipeline {
   public String packetEncoderName() {
      return "encoder";
   }

   public String lengthSplitterName() {
      return "splitter";
   }

   public AimPredictor(UserConnection userConnection, ProtocolVersion protocolVersion) {
      super(userConnection, protocolVersion);
   }

   public String compressName() {
      return "compress";
   }

   public String packetDecoderName() {
      return "decoder";
   }

   public String lengthPrependerName() {
      return "prepender";
   }

   public String decompressName() {
      return "decompress";
   }
}
