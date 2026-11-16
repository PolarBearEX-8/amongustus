package client.util;

import java.util.HashMap;
import java.util.Map;

public class InternalMapHolder {
   public boolean enabled;
   public Map map = new HashMap();
   public String labelText;

   public InternalMapHolder() {
      this.enabled = false;
      this.labelText = null;
   }
}
