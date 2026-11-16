package client.via;

import com.viaversion.vialoader.impl.viaversion.VLInjector;

public class ViaPipelineInjector extends VLInjector {
   public String getDecoderName() {
      return "via-decoder";
   }

   public String getEncoderName() {
      return "via-encoder";
   }
}
