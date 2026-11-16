package client.util;

import com.google.gson.annotations.SerializedName;

public class Controller2 {
   @SerializedName("name")
   public String labelText;

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public String getLabelText() {
      return this.labelText;
   }
}
