package net.augustus.modules.render.blockmarker;

import com.google.gson.JsonArray;
import net.augustus.modules.render.BlockMarker;
import net.augustus.modules.render.ClickGui;

public class MarkedBlockCoord {
   private int x;
   private int y;
   private int z;
   public final BlockMarker blockMarker;

   public MarkedBlockCoord(BlockMarker blockMarker2) {
      this.blockMarker = blockMarker2;
   }

   public MarkedBlockCoord(BlockMarker blockMarker2, int n, int n2, int n3) {
      this.blockMarker = blockMarker2;
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   private void appendToJsonArray(JsonArray array) {
      JsonArray array2 = new JsonArray();
      array2.add(this.x);
      array2.add(this.y);
      array2.add(this.z);
      array.add(array2);
   }

   private void readFromJsonArray(JsonArray array) {
      this.x = array.get(0).getAsInt();
      this.y = array.get(1).getAsInt();
      this.z = array.get(2).getAsInt();
   }

   private boolean equalsCoord(MarkedBlockCoord markedBlockCoord) {
      return this.x == markedBlockCoord.x && this.y == markedBlockCoord.y && this.z == markedBlockCoord.z;
   }

   public static int getX(MarkedBlockCoord markedBlockCoord) {
      return markedBlockCoord.x;
   }

   public static int getY(MarkedBlockCoord markedBlockCoord) {
      return markedBlockCoord.y;
   }

   public static int getZ(MarkedBlockCoord markedBlockCoord) {
      return markedBlockCoord.z;
   }

   public static boolean matchesCoord(MarkedBlockCoord markedBlockCoord, MarkedBlockCoord markedBlockCoord2) {
      return markedBlockCoord.equalsCoord(markedBlockCoord2);
   }

   public static void writeToJsonArray(MarkedBlockCoord markedBlockCoord, JsonArray array) {
      markedBlockCoord.appendToJsonArray(array);
   }

   public static void loadFromJsonArray(MarkedBlockCoord markedBlockCoord, JsonArray array) {
      markedBlockCoord.readFromJsonArray(array);
   }
}
