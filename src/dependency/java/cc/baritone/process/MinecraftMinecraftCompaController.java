package cc.baritone.process;

import cc.baritone.utils.Controller2;
import cc.baritone.utils.RelativeToFileController;
import cc.baritone.utils.ReleaseURLController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;

public class MinecraftMinecraftCompaController {
   public final String labelText;
   public final String[] stringArray;
   public final String labelText2;
   public final String labelText3;
   public final RelativeToFileController[] relativeToFileControllerArray;
   public final ReleaseURLController releaseURLController;

   private MinecraftMinecraftCompaController(ReleaseURLController releaseURLController2, JsonObject jsonObject) {
      this.releaseURLController = releaseURLController2;
      this.labelText = jsonObject.get("Minecraft").getAsString();
      JsonArray asJsonArray = jsonObject.get("MinecraftCompatible").getAsJsonArray();
      this.stringArray = new String[asJsonArray.size()];

      for (int index = 0; index < asJsonArray.size(); index++) {
         this.stringArray[index] = asJsonArray.get(index).getAsString();
      }

      this.labelText2 = jsonObject.get("Loader").getAsString();
      JsonElement jsonElement = jsonObject.get("Post");
      if (jsonElement.isJsonNull()) {
         this.labelText3 = null;
      } else {
         this.labelText3 = jsonElement.getAsString();
      }

      JsonArray asJsonArray2 = jsonObject.get("Hashes").getAsJsonArray();
      this.relativeToFileControllerArray = new RelativeToFileController[asJsonArray2.size()];

      for (int index2 = 0; index2 < asJsonArray2.size(); index2++) {
         this.relativeToFileControllerArray[index2] = new RelativeToFileController(releaseURLController2, asJsonArray2.get(index2).getAsJsonObject());
      }
   }

   public MinecraftMinecraftCompaController(ReleaseURLController releaseURLController2, JsonObject jsonObject, Controller2 controller) {
      this(releaseURLController2, jsonObject);
   }

   @Override
   public String toString() {
      return "HiddenInfo [mainMinecraftVersion="
         + this.labelText
         + ", supportedMinecraftVersions="
         + Arrays.toString((Object[])this.stringArray)
         + ", loader="
         + this.labelText2
         + ", post="
         + this.labelText3
         + ", hashes="
         + Arrays.toString((Object[])this.relativeToFileControllerArray)
         + "]";
   }
}
