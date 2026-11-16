package client.util;

import client.config.ClientNetworkConfig;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.function.Supplier;

public class BezierEngine extends BezierBridge {
   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public Supplier getSupplier() {
      return ClientNetworkConfig::new;
   }

   @Override
   public String getString() {
      return "Proxies_New";
   }

   @Override
   public void applySupplier(Supplier supplier) {
      ClientNetworkConfig clientNetworkConfig = (ClientNetworkConfig)supplier.get();
      ColorEntry.copyOnWriteArrayList.add(clientNetworkConfig);
      ColorEntry.concurrentHashMap.put(clientNetworkConfig, new InterpolationEngine(clientNetworkConfig));
   }

   @Override
   public String getText() {
      return "settings";
   }

   @Override
   public void applyJsonObject(JsonObject jsonObject) {
      ColorEntry.copyOnWriteArrayList.clear();
      ColorEntry.concurrentHashMap.clear();
      super.applyJsonObject(jsonObject);
      AltDoubleEntry altDoubleEntry = ColorEntry.altDoubleEntry;
      int size = ColorEntry.copyOnWriteArrayList.size();
      altDoubleEntry.setCount4(size);
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonObject jsonObject = (JsonObject)jsonElement;
      this.applyJsonObject(jsonObject);
   }

   public BezierEngine() {
      super(ColorEntry.copyOnWriteArrayList);
   }
}
