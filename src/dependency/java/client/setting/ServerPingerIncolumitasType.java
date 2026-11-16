package client.setting;

import client.util.AuxiliaryGsonCallback;
import client.util.DynamicStringEntry;
import client.util.HttpsApiIncolumitasComQSManager;
import net.minecraft.item.IronCrown;

public enum ServerPingerIncolumitasType implements AuxiliaryGsonCallback {
    INCOLUMITAS("Incolumitas.com (BEST)", new HttpsApiIncolumitasComQSManager()),
    IP_API("ip-api.com", new DynamicStringEntry());

   public final String labelText;
   public final AuxiliaryGsonCallback auxiliaryGsonCallback;

   @Override
   public IronCrown resolveIronCrown(String text) throws Exception {
      return this.auxiliaryGsonCallback.resolveIronCrown(text);
   }

   public String getText() {
      return this.labelText;
   }

   private ServerPingerIncolumitasType(String text, AuxiliaryGsonCallback auxiliaryGsonCallback2) {
      this.labelText = text;
      this.auxiliaryGsonCallback = auxiliaryGsonCallback2;
   }


}
