package net.augustus.modules.render.blockmarker;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.render.BlockMarker;
import net.augustus.modules.render.ClickGui;
import net.minecraft.block.Block;

public class MarkedBlockGroup {
   private final String blockName;
   private final Block block;
   private final List coords;
   public final BlockMarker blockMarker;

   public MarkedBlockGroup(BlockMarker blockMarker2, String str, Block block2) {
      this.blockMarker = blockMarker2;
      this.coords = new ArrayList();
      this.blockName = str;
      this.block = block2;
   }

   public void serializeToJson(JsonObject json) {
      JsonArray array = new JsonArray();

      for (MarkedBlockCoord markedBlockCoord : (Iterable<MarkedBlockCoord>)(this.coords)) {
         MarkedBlockCoord.writeToJsonArray(markedBlockCoord, array);
      }

      json.add(this.blockName, array);
   }

   public void deserializeFromJson(JsonArray array) {

      for (JsonElement elem : array) {
         MarkedBlockCoord markedBlockCoord = new MarkedBlockCoord(this.blockMarker);
         MarkedBlockCoord.loadFromJsonArray(markedBlockCoord, elem.getAsJsonArray());
         this.coords.add(markedBlockCoord);
      }
   }

   public static Block getBlock(MarkedBlockGroup markedBlockGroup) {
      return markedBlockGroup.block;
   }

   public static List getCoords(MarkedBlockGroup markedBlockGroup) {
      return markedBlockGroup.coords;
   }

   public static String getBlockName(MarkedBlockGroup markedBlockGroup) {
      return markedBlockGroup.blockName;
   }
}
