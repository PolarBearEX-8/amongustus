package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelBlock {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final Gson SERIALIZER = new GsonBuilder()
      .registerTypeAdapter(ModelBlock.class, new ModelBlock.Deserializer())
      .registerTypeAdapter(BlockPart.class, new BlockPart.Deserializer())
      .registerTypeAdapter(BlockPartFace.class, new BlockPartFace.Deserializer())
      .registerTypeAdapter(BlockFaceUV.class, new BlockFaceUV.Deserializer())
      .registerTypeAdapter(ItemTransformVec3f.class, new ItemTransformVec3f.Deserializer())
      .registerTypeAdapter(ItemCameraTransforms.class, new ItemCameraTransforms.Deserializer())
      .create();
   private final List elements;
   private final boolean gui3d;
   private final boolean ambientOcclusion;
   private ItemCameraTransforms cameraTransforms;
   public String name = "";
   public final Map textures;
   public ModelBlock parent;
   public ResourceLocation parentLocation;

   private String resolveTextureName(String str, ModelBlock.Bookkeep bookkeep) {
      if (this.startsWithHash(str)) {
         if (this == bookkeep.modelExt) {
            LOGGER.warn("Unable to resolve texture due to upward reference: " + str + " in " + this.name);
            return "missingno";
         } else {
            String str2 = (String)this.textures.get(str.substring(1));
            if (str2 == null && this.hasParent()) {
               str2 = this.parent.resolveTextureName(str, bookkeep);
            }

            bookkeep.modelExt = this;
            if (str2 != null && this.startsWithHash(str2)) {
               str2 = bookkeep.model.resolveTextureName(str2, bookkeep);
            }

            return str2 != null && !this.startsWithHash(str2) ? str2 : "missingno";
         }
      } else {
         return str;
      }
   }

   public boolean isTexturePresent(String str) {
      return !"missingno".equals(this.resolveTextureName(str));
   }

   public ResourceLocation getParentLocation() {
      return this.parentLocation;
   }

   public static void checkModelHierarchy(Map map) {
      for (ModelBlock modelBlock : (Iterable<ModelBlock>)(map.values())) {
         try {
            ModelBlock parent2 = modelBlock.parent;

            for (ModelBlock i = parent2.parent; parent2 != i; i = i.parent.parent) {
               parent2 = parent2.parent;
            }

            throw new ModelBlock.LoopException();
         } catch (NullPointerException e) {
         }
      }
   }

   public ModelBlock getRootModel() {
      return this.hasParent() ? this.parent.getRootModel() : this;
   }

   public static ModelBlock deserialize(String str) {
      return deserialize(new StringReader(str));
   }

   public static ModelBlock deserialize(Reader reader) {
      return (ModelBlock)SERIALIZER.fromJson(reader, ModelBlock.class);
   }

   private ModelBlock(ResourceLocation location, List list, Map map, boolean flag, boolean flag2, ItemCameraTransforms itemCameraTransforms) {
      this.elements = list;
      this.ambientOcclusion = flag;
      this.gui3d = flag2;
      this.textures = map;
      this.parentLocation = location;
      this.cameraTransforms = itemCameraTransforms;
   }

   public ModelBlock(List list, Map map, boolean flag, boolean flag2, ItemCameraTransforms itemCameraTransforms) {
      this((ResourceLocation)null, list, map, flag, flag2, itemCameraTransforms);
   }

   private ItemTransformVec3f getTransform(ItemCameraTransforms.TransformType transformType) {
      return this.parent != null && !this.cameraTransforms.hasCustomTransform(transformType) ? this.parent.getTransform(transformType) : this.cameraTransforms.getTransform(transformType);
   }

   private boolean hasParent() {
      return this.parent != null;
   }

   public ItemCameraTransforms getAllTransforms() {
      ItemTransformVec3f transform = this.getTransform(ItemCameraTransforms.TransformType.THIRD_PERSON);
      ItemTransformVec3f transform2 = this.getTransform(ItemCameraTransforms.TransformType.FIRST_PERSON);
      ItemTransformVec3f transform3 = this.getTransform(ItemCameraTransforms.TransformType.HEAD);
      ItemTransformVec3f transform4 = this.getTransform(ItemCameraTransforms.TransformType.GUI);
      ItemTransformVec3f transform5 = this.getTransform(ItemCameraTransforms.TransformType.GROUND);
      ItemTransformVec3f transform6 = this.getTransform(ItemCameraTransforms.TransformType.FIXED);
      return new ItemCameraTransforms(transform, transform2, transform3, transform4, transform5, transform6);
   }

   public boolean isGui3d() {
      return this.gui3d;
   }

   public boolean isResolved() {
      return this.parentLocation == null || this.parent != null && this.parent.isResolved();
   }

   public boolean isAmbientOcclusion() {
      return this.hasParent() ? this.parent.isAmbientOcclusion() : this.ambientOcclusion;
   }

   public void getParentFromMap(Map map) {
      if (this.parentLocation != null) {
         this.parent = (ModelBlock)map.get(this.parentLocation);
      }
   }

   public List getElements() {
      return this.hasParent() ? this.parent.getElements() : this.elements;
   }

   public ModelBlock(ResourceLocation location, Map map, boolean flag, boolean flag2, ItemCameraTransforms itemCameraTransforms) {
      this(location, Collections.emptyList(), map, flag, flag2, itemCameraTransforms);
   }

   public String resolveTextureName(String str) {
      if (!this.startsWithHash(str)) {
         str = '#' + str;
      }

      return this.resolveTextureName(str, new ModelBlock.Bookkeep(this, null));
   }

   private boolean startsWithHash(String str) {
      return str.charAt(0) == '#';
   }


   public static class InnerEntry {
   }

   public static final class Bookkeep {
      public final ModelBlock model;
      public ModelBlock modelExt;

      public Bookkeep(ModelBlock modelBlock, ModelBlock.InnerEntry obj) {
         this(modelBlock);
      }

      private Bookkeep(ModelBlock modelBlock) {
         this.model = modelBlock;
      }
   }

   public static class Deserializer implements JsonDeserializer {
      private Map getTextures(JsonObject json) {
         HashMap map = Maps.newHashMap();
         if (json.has("textures")) {

            for (Entry entry : json.getAsJsonObject("textures").entrySet()) {
               map.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
            }
         }

         return map;
      }

      public boolean getAmbientOcclusionEnabled(JsonObject json) {
         return JsonUtils.getBoolean(json, "ambientocclusion", true);
      }

      public List getModelElements(JsonDeserializationContext context, JsonObject json) {
         ArrayList list = Lists.newArrayList();
         if (json.has("elements")) {
            for (JsonElement elem : JsonUtils.getJsonArray(json, "elements")) {
               list.add((BlockPart)context.deserialize(elem, BlockPart.class));
            }
         }

         return list;
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeModelBlock(elem, type, context);
      }

      public ModelBlock deserializeModelBlock(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         JsonObject asJsonObject = elem.getAsJsonObject();
         List modelElements = this.getModelElements(context, asJsonObject);
         String parent = this.getParent(asJsonObject);
         boolean isEmpty = StringUtils.isEmpty(parent);
         boolean isEmpty2 = modelElements.isEmpty();
         if (isEmpty2 && isEmpty) {
            throw new JsonParseException("BlockModel requires either elements or parent, found neither");
         } else if (!isEmpty && !isEmpty2) {
            throw new JsonParseException("BlockModel requires either elements or parent, found both");
         } else {
            Map textures = this.getTextures(asJsonObject);
            boolean flag = this.getAmbientOcclusionEnabled(asJsonObject);
            ItemCameraTransforms itemCameraTransforms = ItemCameraTransforms.DEFAULT;
            if (asJsonObject.has("display")) {
               itemCameraTransforms = (ItemCameraTransforms)context.deserialize(JsonUtils.getJsonObject(asJsonObject, "display"), ItemCameraTransforms.class);
            }

            return isEmpty2 ? new ModelBlock(new ResourceLocation(parent), textures, flag, true, itemCameraTransforms) : new ModelBlock(modelElements, textures, flag, true, itemCameraTransforms);
         }
      }

      private String getParent(JsonObject json) {
         return JsonUtils.getString(json, "parent", "");
      }
   }

   public static class LoopException extends RuntimeException {
   }
}
