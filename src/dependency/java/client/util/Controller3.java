package client.util;

import com.google.gson.annotations.SerializedName;

public class Controller3 {
   @SerializedName("url")
   public String labelText;
   @SerializedName("height")
   public int count;

   public int getCount() {
      return this.count;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public String getLabelText() {
      return this.labelText;
   }
}
