package client.util;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class LoggerEntryBridge extends CommonLoggerEntry {
   private String resolveText(SocketAddress socketAddress) {
      String text = socketAddress.toString();
      if (text.contains("/")) {
         text = text.substring(text.indexOf(47) + 1);
      }

      if (text.contains(":")) {
         text = text.substring(0, text.indexOf(58));
      }

      return text;
   }

   public boolean matchesSocketAddress(SocketAddress socketAddress) {
      String text = this.resolveText(socketAddress);
      return this.matchesTarget(text);
   }

   public AuxiliarySimpleDateFormatEntryAdapter resolveAuxiliarySimpleDateFormatEnt(SocketAddress socketAddress) {
      String text = this.resolveText(socketAddress);
      return (AuxiliarySimpleDateFormatEntryAdapter)this.resolveTargetStack(text);
   }

   public LoggerEntryBridge(File file) {
      super(file);
   }

   @Override
   public TargetStack resolveTargetStack(JsonObject jsonObject) {
      return new AuxiliarySimpleDateFormatEntryAdapter(jsonObject);
   }
}
