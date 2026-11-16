package client.util;

import client.setting.OptionManager;

public class GeometryBuffer extends OptionManager {
   public GeometryBuffer(String text) {
      super(text, (String)null, (String)null, new String[0], (String)null, (String)null);
   }

   @Override
   public String getText() {
      return PreEndHandManager.get("screen." + this.getLabelText() + ".comment", (String)null);
   }

   @Override
   public String getTextAsText() {
      return PreEndHandManager.get("screen." + this.getLabelText(), this.getLabelText());
   }
}
