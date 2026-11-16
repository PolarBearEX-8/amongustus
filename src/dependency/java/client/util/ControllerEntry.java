package client.util;

import com.google.gson.annotations.SerializedName;

public class ControllerEntry {
   @SerializedName("item")
   public Controller10 controller10;

   public void setController10(Controller10 controller) {
      this.controller10 = controller;
   }

   public Controller10 getController10() {
      return this.controller10;
   }
}
