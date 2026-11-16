package cc.baritone.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Arrays;

public class RelativeToFileController {
   public final String labelText;
   public final String labelText2;
   public final String[] stringArray;
   public final ReleaseURLController releaseURLController;

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else if (!(other instanceof RelativeToFileController)) {
         return false;
      } else {
         RelativeToFileController relativeToFileController = (RelativeToFileController)other;
         if (!this.getReleaseURLController().equals(relativeToFileController.getReleaseURLController())) {
            return false;
         } else {
            if (this.labelText2 == null) {
               if (relativeToFileController.labelText2 != null) {
                  return false;
               }
            } else if (!this.labelText2.equals(relativeToFileController.labelText2)) {
               return false;
            }

            if (this.labelText == null) {
               if (relativeToFileController.labelText != null) {
                  return false;
               }
            } else if (!this.labelText.equals(relativeToFileController.labelText)) {
               return false;
            }

            return true;
         }
      }
   }

   public RelativeToFileController(ReleaseURLController releaseURLController2, JsonObject jsonObject) {
      this.releaseURLController = releaseURLController2;
      this.labelText = jsonObject.get("RelativeTo").getAsString();
      this.labelText2 = jsonObject.get("File").getAsString();
      JsonArray asJsonArray = jsonObject.get("Hash").getAsJsonArray();
      this.stringArray = new String[asJsonArray.size()];

      for (int index = 0; index < this.stringArray.length; index++) {
         this.stringArray[index] = asJsonArray.get(index).getAsString();
      }
   }

   private ReleaseURLController getReleaseURLController() {
      return this.releaseURLController;
   }

   @Override
   public String toString() {
      return "HashData [relativeTo=" + this.labelText + ", file=" + this.labelText2 + ", validHashes=" + Arrays.toString((Object[])this.stringArray) + "]";
   }

   @Override
   public int hashCode() {
      int count = 31 + this.getReleaseURLController().hashCode();
      int count2 = 31 * count + (this.labelText2 == null ? 0 : this.labelText2.hashCode());
      return 31 * count2 + (this.labelText == null ? 0 : this.labelText.hashCode());
   }
}
