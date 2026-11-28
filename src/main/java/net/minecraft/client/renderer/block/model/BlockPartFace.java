package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.EnumFacing;

public class BlockPartFace {
   public static final EnumFacing FACING_DEFAULT = null;
   public final EnumFacing cullFace;
   public final int tintIndex;
   public final String texture;
   public final BlockFaceUV blockFaceUV;

   public BlockPartFace(EnumFacing facing, int n, String str, BlockFaceUV blockFaceUV2) {
      this.cullFace = facing;
      this.tintIndex = n;
      this.texture = str;
      this.blockFaceUV = blockFaceUV2;
   }


   public static class Deserializer implements JsonDeserializer {
      public int parseTintIndex(JsonObject json) {
         return JsonUtils.getInt(json, "tintindex", -1);
      }

      private String parseTexture(JsonObject json) {
         return JsonUtils.getString(json, "texture");
      }

      private EnumFacing parseCullFace(JsonObject json) {
         return EnumFacing.byName(JsonUtils.getString(json, "cullface", ""));
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeBlockPartFace(elem, type, context);
      }

      public BlockPartFace deserializeBlockPartFace(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject asJsonObject = elem.getAsJsonObject();
         EnumFacing facing = this.parseCullFace(asJsonObject);
         int n = this.parseTintIndex(asJsonObject);
         String str = this.parseTexture(asJsonObject);
         BlockFaceUV blockFaceUV = (BlockFaceUV)context.deserialize(asJsonObject, BlockFaceUV.class);
         return new BlockPartFace(facing, n, str, blockFaceUV);
      }
   }
}
