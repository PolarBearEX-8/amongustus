package net.augustus.modules.render.blockmarker;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import net.augustus.modules.render.BlockMarker;
import net.augustus.modules.render.ClickGui;
import net.minecraft.block.Block;

public class BlockMarkerProfile {
   private final String name;
   private final List blockGroups;
   public final BlockMarker blockMarker;

   public BlockMarkerProfile(BlockMarker blockMarker2, String str) {
      this.blockMarker = blockMarker2;
      this.blockGroups = new ArrayList();
      this.name = str;
   }

   private void serializeToJson(JsonObject json) {
      json.addProperty("Name", this.name);
      JsonObject json2 = new JsonObject();

      for (MarkedBlockGroup markedBlockGroup : (Iterable<MarkedBlockGroup>)(this.blockGroups)) {
         markedBlockGroup.serializeToJson(json2);
      }

      json.add("Blocks", json2);
   }

   private void deserializeFromJson(JsonObject json) {

      for (Entry entry : json.entrySet()) {
         String key = (String)entry.getKey();
         JsonElement value = (JsonElement)entry.getValue();
         Block blockFromName = Block.getBlockFromName(key);
         if (blockFromName != null) {
            MarkedBlockGroup markedBlockGroup = new MarkedBlockGroup(this.blockMarker, key, blockFromName);
            markedBlockGroup.deserializeFromJson((JsonArray)value);
            this.blockGroups.add(markedBlockGroup);
         } else {
            String str = "Failed to load block: " + key;
            this.blockMarker.sendChatMessage(str);
         }
      }
   }

   public static List getBlockGroups(BlockMarkerProfile blockMarkerProfile) {
      return blockMarkerProfile.blockGroups;
   }

   public static void serializeProfile(BlockMarkerProfile blockMarkerProfile, JsonObject json) {
      blockMarkerProfile.serializeToJson(json);
   }

   public static String getName(BlockMarkerProfile blockMarkerProfile) {
      return blockMarkerProfile.name;
   }

   public static void deserializeProfile(BlockMarkerProfile blockMarkerProfile, JsonObject json) {
      blockMarkerProfile.deserializeFromJson(json);
   }

}
