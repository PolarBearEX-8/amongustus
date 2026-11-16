package client.util;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Controller14 {
   @SerializedName("images")
   public List list;

   public List getList() {
      return this.list;
   }

   public void setList(List list2) {
      this.list = list2;
   }
}
