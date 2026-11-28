package net.minecraft.client.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderManager {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ShaderDefault defaultShaderUniform = new ShaderDefault();
   private static ShaderManager staticShaderManager = null;
   private static int currentProgram = -1;
   private static boolean lastCull = true;
   private final Map shaderSamplers = Maps.newHashMap();
   private final List samplerNames = Lists.newArrayList();
   private final List shaderSamplerLocations = Lists.newArrayList();
   private final List shaderUniforms = Lists.newArrayList();
   private final List shaderUniformLocations = Lists.newArrayList();
   private final Map mappedShaderUniforms = Maps.newHashMap();
   private final int program;
   private final String programFilename;
   private final boolean useFaceCulling;
   private boolean isDirty;
   private final JsonBlendingMode jSONBlendingMode;
   private final List attribLocations;
   private final List attributes;
   private final ShaderLoader vertexShaderLoader;
   private final ShaderLoader fragmentShaderLoader;

   public ShaderManager(IResourceManager resourceManager, String str) throws JsonException, IOException {
      JsonParser jsonParser = new JsonParser();
      ResourceLocation location = new ResourceLocation("shaders/program/" + str + ".json");
      this.programFilename = str;
      InputStream in = null;

      try {
         in = resourceManager.getResource(location).getInputStream();
         JsonObject asJsonObject = jsonParser.parse(IOUtils.toString(in, Charsets.UTF_8)).getAsJsonObject();
         String string = JsonUtils.getString(asJsonObject, "vertex");
         String string2 = JsonUtils.getString(asJsonObject, "fragment");
         JsonArray jsonArray = JsonUtils.getJsonArray(asJsonObject, "samplers", (JsonArray)null);
         if (jsonArray != null) {
            int n = 0;

            for (JsonElement elem : jsonArray) {
               try {
                  this.parseSampler(elem);
               } catch (Exception e) {
                  JsonException ex = JsonException.forException(e);
                  ex.prependJsonKey("samplers[" + n + "]");
                  throw ex;
               }

               n++;
            }
         }

         JsonArray jsonArray2 = JsonUtils.getJsonArray(asJsonObject, "attributes", (JsonArray)null);
         if (jsonArray2 != null) {
            int n2 = 0;
            this.attribLocations = Lists.newArrayListWithCapacity(jsonArray2.size());
            this.attributes = Lists.newArrayListWithCapacity(jsonArray2.size());

            for (JsonElement elem2 : jsonArray2) {
               try {
                  this.attributes.add(JsonUtils.getString(elem2, "attribute"));
               } catch (Exception e2) {
                  JsonException ex2 = JsonException.forException(e2);
                  ex2.prependJsonKey("attributes[" + n2 + "]");
                  throw ex2;
               }

               n2++;
            }
         } else {
            this.attribLocations = null;
            this.attributes = null;
         }

         JsonArray jsonArray3 = JsonUtils.getJsonArray(asJsonObject, "uniforms", (JsonArray)null);
         if (jsonArray3 != null) {
            int n3 = 0;

            for (JsonElement elem3 : jsonArray3) {
               try {
                  this.parseUniform(elem3);
               } catch (Exception e3) {
                  JsonException ex3 = JsonException.forException(e3);
                  ex3.prependJsonKey("uniforms[" + n3 + "]");
                  throw ex3;
               }

               n3++;
            }
         }

         this.jSONBlendingMode = JsonBlendingMode.parseBlendNode(JsonUtils.getJsonObject(asJsonObject, "blend", (JsonObject)null));
         this.useFaceCulling = JsonUtils.getBoolean(asJsonObject, "cull", true);
         this.vertexShaderLoader = ShaderLoader.loadShader(resourceManager, ShaderLoader.ShaderType.VERTEX, string);
         this.fragmentShaderLoader = ShaderLoader.loadShader(resourceManager, ShaderLoader.ShaderType.FRAGMENT, string2);
         this.program = ShaderLinkHelper.getStaticShaderLinkHelper().createProgram();
         ShaderLinkHelper.getStaticShaderLinkHelper().linkProgram(this);
         this.setupUniforms();
         if (this.attributes != null) {
            for (String str2 : (Iterable<String>)(this.attributes)) {
               int n4 = OpenGlHelper.glGetAttribLocation(this.program, str2);
               this.attribLocations.add(n4);
            }
         }
      } catch (Exception e4) {
         JsonException ex4 = JsonException.forException(e4);
         ex4.setFilenameAndFlush(location.getResourcePath());
         throw ex4;
      } finally {
         IOUtils.closeQuietly(in);
      }

      this.markDirty();
   }

   public void markDirty() {
      this.isDirty = true;
   }

   public void deleteShader() {
      ShaderLinkHelper.getStaticShaderLinkHelper().deleteShader(this);
   }

   public int getProgram() {
      return this.program;
   }

   public void endShader() {
      OpenGlHelper.glUseProgram(0);
      currentProgram = -1;
      staticShaderManager = null;
      lastCull = true;

      for (int i = 0; i < this.shaderSamplerLocations.size(); i++) {
         if (this.shaderSamplers.get(this.samplerNames.get(i)) != null) {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + i);
            GlStateManager.bindTexture(0);
         }
      }
   }

   public void useShader() {
      this.isDirty = false;
      staticShaderManager = this;
      this.jSONBlendingMode.apply();
      if (this.program != currentProgram) {
         OpenGlHelper.glUseProgram(this.program);
         currentProgram = this.program;
      }

      if (this.useFaceCulling) {
         GlStateManager.enableCull();
      } else {
         GlStateManager.disableCull();
      }

      for (int i = 0; i < this.shaderSamplerLocations.size(); i++) {
         if (this.shaderSamplers.get(this.samplerNames.get(i)) != null) {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + i);
            GlStateManager.enableTexture2D();
            Object obj = this.shaderSamplers.get(this.samplerNames.get(i));
            int n = -1;
            if (obj instanceof Framebuffer) {
               n = ((Framebuffer)obj).framebufferTexture;
            } else if (obj instanceof ITextureObject) {
               n = ((ITextureObject)obj).getGlTextureId();
            } else if (obj instanceof Integer) {
               n = (Integer)obj;
            }

            if (n != -1) {
               GlStateManager.bindTexture(n);
               OpenGlHelper.glUniform1i(OpenGlHelper.glGetUniformLocation(this.program, (CharSequence)this.samplerNames.get(i)), i);
            }
         }
      }

      for (ShaderUniform shaderUniform : (Iterable<ShaderUniform>)(this.shaderUniforms)) {
         shaderUniform.upload();
      }
   }

   public ShaderUniform getShaderUniformOrDefault(String str) {
      return (ShaderUniform)(this.mappedShaderUniforms.containsKey(str) ? (ShaderUniform)this.mappedShaderUniforms.get(str) : defaultShaderUniform);
   }

   private void parseUniform(JsonElement elem) throws JsonException {
      JsonObject jsonObject = JsonUtils.getJsonObject(elem, "uniform");
      String string = JsonUtils.getString(jsonObject, "name");
      int n = ShaderUniform.parseType(JsonUtils.getString(jsonObject, "type"));
      int n2 = JsonUtils.getInt(jsonObject, "count");
      float[] floats = new float[Math.max(n2, 16)];
      JsonArray jsonArray = JsonUtils.getJsonArray(jsonObject, "values");
      if (jsonArray.size() != n2 && jsonArray.size() > 1) {
         throw new JsonException("Invalid amount of values specified (expected " + n2 + ", found " + jsonArray.size() + ")");
      } else {
         int n3 = 0;

         for (JsonElement elem2 : jsonArray) {
            try {
               floats[n3] = JsonUtils.getFloat(elem2, "value");
            } catch (Exception e) {
               JsonException ex = JsonException.forException(e);
               ex.prependJsonKey("values[" + n3 + "]");
               throw ex;
            }

            n3++;
         }

         if (n2 > 1 && jsonArray.size() == 1) {
            while (n3 < n2) {
               floats[n3] = floats[0];
               n3++;
            }
         }

         int n4 = n2 > 1 && n2 <= 4 && n < 8 ? n2 - 1 : 0;
         ShaderUniform shaderUniform = new ShaderUniform(string, n + n4, n2, this);
         if (n <= 3) {
            shaderUniform.set((int)floats[0], (int)floats[1], (int)floats[2], (int)floats[3]);
         } else if (n <= 7) {
            shaderUniform.setSafe(floats[0], floats[1], floats[2], floats[3]);
         } else {
            shaderUniform.set(floats);
         }

         this.shaderUniforms.add(shaderUniform);
      }
   }

   public ShaderLoader getFragmentShaderLoader() {
      return this.fragmentShaderLoader;
   }

   public void addSamplerTexture(String str, Object obj) {
      if (this.shaderSamplers.containsKey(str)) {
         this.shaderSamplers.remove(str);
      }

      this.shaderSamplers.put(str, obj);
      this.markDirty();
   }

   private void setupUniforms() {
      int n = 0;

      for (int i = 0; n < this.samplerNames.size(); i++) {
         String str = (String)this.samplerNames.get(n);
         int n2 = OpenGlHelper.glGetUniformLocation(this.program, str);
         if (n2 == -1) {
            LOGGER.warn("Shader " + this.programFilename + "could not find sampler named " + str + " in the specified shader program.");
            this.shaderSamplers.remove(str);
            this.samplerNames.remove(i);
            i--;
         } else {
            this.shaderSamplerLocations.add(n2);
         }

         n++;
      }

      for (ShaderUniform shaderUniform : (Iterable<ShaderUniform>)(this.shaderUniforms)) {
         String shaderName = shaderUniform.getShaderName();
         int n3 = OpenGlHelper.glGetUniformLocation(this.program, shaderName);
         if (n3 == -1) {
            LOGGER.warn("Could not find uniform named " + shaderName + " in the specified shader program.");
         } else {
            this.shaderUniformLocations.add(n3);
            shaderUniform.setUniformLocation(n3);
            this.mappedShaderUniforms.put(shaderName, shaderUniform);
         }
      }
   }

   public ShaderUniform getShaderUniform(String str) {
      return this.mappedShaderUniforms.containsKey(str) ? (ShaderUniform)this.mappedShaderUniforms.get(str) : null;
   }

   public ShaderLoader getVertexShaderLoader() {
      return this.vertexShaderLoader;
   }

   private void parseSampler(JsonElement elem) throws JsonException {
      JsonObject jsonObject = JsonUtils.getJsonObject(elem, "sampler");
      String string = JsonUtils.getString(jsonObject, "name");
      if (!JsonUtils.isString(jsonObject, "file")) {
         this.shaderSamplers.put(string, null);
         this.samplerNames.add(string);
      } else {
         this.samplerNames.add(string);
      }
   }
}
