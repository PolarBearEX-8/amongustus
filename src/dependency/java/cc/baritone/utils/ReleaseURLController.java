package cc.baritone.utils;

import cc.baritone.process.MinecraftMinecraftCompaController;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReleaseURLController {
   public static final Pattern pattern = Pattern.compile("^\\[\\]\\(# '(.+?)'\\)");
   public static final JsonParser jsonParser = new JsonParser();
   public final JsonObject jsonObject;
   public final String labelText2;
   public final String labelText;
   public final String labelText3;
   public final String labelText4;
   public final boolean enabled;
   public final String labelText5;
   public final String labelText6;
   public final MinecraftMinecraftCompaController minecraftMinecraftCompaController;

   @Override
   public String toString() {
      return "Release [URL="
         + this.labelText2
         + ", tag="
         + this.labelText
         + ", title="
         + this.labelText3
         + ", date="
         + this.labelText4
         + ", prerelease="
         + this.enabled
         + ", markdownBody="
         + this.labelText5
         + ", textOnlyBody="
         + this.labelText6
         + ", hiddenInfo="
         + this.minecraftMinecraftCompaController
         + "]";
   }

   public ReleaseURLController(JsonObject jsonObject2) {
      this.jsonObject = jsonObject2;
      this.labelText5 = jsonObject2.get("body").getAsString();
      Matcher matcher = pattern.matcher(this.labelText5);
      if (matcher.find()) {
         String text = this.labelText5.substring(matcher.start(1), matcher.end(1));
         JsonObject asJsonObject = jsonParser.parse(text).getAsJsonObject();
         this.minecraftMinecraftCompaController = new MinecraftMinecraftCompaController(this, asJsonObject, null);
      } else {
         this.minecraftMinecraftCompaController = null;
      }

      this.labelText2 = jsonObject2.get("html_url").getAsString();
      this.labelText6 = jsonObject2.get("body_text").getAsString();
      this.labelText = jsonObject2.get("tag_name").getAsString();
      this.labelText3 = jsonObject2.get("name").getAsString();
      this.labelText4 = jsonObject2.get("published_at").getAsString();
      this.enabled = jsonObject2.get("prerelease").getAsBoolean();
   }
}
