package client.util;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Controller10 {
   @SerializedName("artists")
   public List list;
   @SerializedName("album")
   public Controller14 controller14;
   @SerializedName("name")
   public String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   public void setList(List list2) {
      this.list = list2;
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public Controller14 getController14() {
      return this.controller14;
   }

   public void setController14(Controller14 controller) {
      this.controller14 = controller;
   }

   public List getList() {
      return this.list;
   }
}
