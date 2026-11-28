package net.minecraft.client.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderGroup {
   public Framebuffer mainFramebuffer;
   private IResourceManager resourceManager;
   private String shaderGroupName;
   private final List listShaders = Lists.newArrayList();
   private final Map mapFramebuffers = Maps.newHashMap();
   private final List listFramebuffers = Lists.newArrayList();
   private Matrix4f projectionMatrix;
   private int mainFramebufferWidth;
   private int mainFramebufferHeight;
   private float time;
   private float lastStamp;

   public ShaderGroup(TextureManager textureManager, IResourceManager resourceManager2, Framebuffer framebuffer, ResourceLocation location) throws JsonException, IOException, JsonSyntaxException {
      this.resourceManager = resourceManager2;
      this.mainFramebuffer = framebuffer;
      this.time = 0.0F;
      this.lastStamp = 0.0F;
      this.mainFramebufferWidth = framebuffer.framebufferWidth;
      this.mainFramebufferHeight = framebuffer.framebufferHeight;
      this.shaderGroupName = location.toString();
      this.resetProjectionMatrix();
      this.parseGroup(textureManager, location);
   }

   private void parsePass(TextureManager textureManager, JsonElement elem) throws JsonException, IOException {
      JsonObject jsonObject = JsonUtils.getJsonObject(elem, "pass");
      String string = JsonUtils.getString(jsonObject, "name");
      String string2 = JsonUtils.getString(jsonObject, "intarget");
      String string3 = JsonUtils.getString(jsonObject, "outtarget");
      Framebuffer framebuffer = this.getFramebuffer(string2);
      Framebuffer framebuffer2 = this.getFramebuffer(string3);
      if (framebuffer == null) {
         throw new JsonException("Input target '" + string2 + "' does not exist");
      } else if (framebuffer2 == null) {
         throw new JsonException("Output target '" + string3 + "' does not exist");
      } else {
         Shader shader = this.addShader(string, framebuffer, framebuffer2);
         JsonArray jsonArray = JsonUtils.getJsonArray(jsonObject, "auxtargets", (JsonArray)null);
         if (jsonArray != null) {
            int n = 0;

            for (JsonElement elem2 : jsonArray) {
               try {
                  JsonObject jsonObject2 = JsonUtils.getJsonObject(elem2, "auxtarget");
                  String string4 = JsonUtils.getString(jsonObject2, "name");
                  String string5 = JsonUtils.getString(jsonObject2, "id");
                  Framebuffer framebuffer3 = this.getFramebuffer(string5);
                  if (framebuffer3 == null) {
                     ResourceLocation location = new ResourceLocation("textures/effect/" + string5 + ".png");

                     try {
                        this.resourceManager.getResource(location);
                     } catch (FileNotFoundException e) {
                        throw new JsonException("Render target or texture '" + string5 + "' does not exist");
                     }

                     textureManager.bindTexture(location);
                     ITextureObject texture = textureManager.getTexture(location);
                     int n2 = JsonUtils.getInt(jsonObject2, "width");
                     int n3 = JsonUtils.getInt(jsonObject2, "height");
                     if (JsonUtils.getBoolean(jsonObject2, "bilinear")) {
                        GL11.glTexParameteri(3553, 10241, 9729);
                        GL11.glTexParameteri(3553, 10240, 9729);
                     } else {
                        GL11.glTexParameteri(3553, 10241, 9728);
                        GL11.glTexParameteri(3553, 10240, 9728);
                     }

                     shader.addAuxFramebuffer(string4, texture.getGlTextureId(), n2, n3);
                  } else {
                     shader.addAuxFramebuffer(string4, framebuffer3, framebuffer3.framebufferTextureWidth, framebuffer3.framebufferTextureHeight);
                  }
               } catch (Exception e2) {
                  JsonException ex = JsonException.forException(e2);
                  ex.prependJsonKey("auxtargets[" + n + "]");
                  throw ex;
               }

               n++;
            }
         }

         JsonArray jsonArray2 = JsonUtils.getJsonArray(jsonObject, "uniforms", (JsonArray)null);
         if (jsonArray2 != null) {
            int n4 = 0;

            for (JsonElement elem3 : jsonArray2) {
               try {
                  this.initUniform(elem3);
               } catch (Exception e3) {
                  JsonException ex2 = JsonException.forException(e3);
                  ex2.prependJsonKey("uniforms[" + n4 + "]");
                  throw ex2;
               }

               n4++;
            }
         }
      }
   }

   private void initTarget(JsonElement elem) throws JsonException {
      if (JsonUtils.isString(elem)) {
         this.addFramebuffer(elem.getAsString(), this.mainFramebufferWidth, this.mainFramebufferHeight);
      } else {
         JsonObject jsonObject = JsonUtils.getJsonObject(elem, "target");
         String string = JsonUtils.getString(jsonObject, "name");
         int n = JsonUtils.getInt(jsonObject, "width", this.mainFramebufferWidth);
         int n2 = JsonUtils.getInt(jsonObject, "height", this.mainFramebufferHeight);
         if (this.mapFramebuffers.containsKey(string)) {
            throw new JsonException(string + " is already defined");
         }

         this.addFramebuffer(string, n, n2);
      }
   }

   public void createBindFramebuffers(int n, int n2) {
      this.mainFramebufferWidth = this.mainFramebuffer.framebufferTextureWidth;
      this.mainFramebufferHeight = this.mainFramebuffer.framebufferTextureHeight;
      this.resetProjectionMatrix();

      for (Shader shader : (Iterable<Shader>)(this.listShaders)) {
         shader.setProjectionMatrix(this.projectionMatrix);
      }

      for (Framebuffer framebuffer : (Iterable<Framebuffer>)(this.listFramebuffers)) {
         framebuffer.createBindFramebuffer(n, n2);
      }
   }

   public void addFramebuffer(String str, int n, int n2) {
      Framebuffer framebuffer = new Framebuffer(n, n2, true);
      framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.mapFramebuffers.put(str, framebuffer);
      if (n == this.mainFramebufferWidth && n2 == this.mainFramebufferHeight) {
         this.listFramebuffers.add(framebuffer);
      }
   }

   private Framebuffer getFramebuffer(String str) {
      return str == null ? null : (str.equals("minecraft:main") ? this.mainFramebuffer : (Framebuffer)this.mapFramebuffers.get(str));
   }

   public final String getShaderGroupName() {
      return this.shaderGroupName;
   }

   public void deleteShaderGroup() {
      for (Framebuffer framebuffer : (Iterable<Framebuffer>)(this.mapFramebuffers.values())) {
         framebuffer.deleteFramebuffer();
      }

      for (Shader shader : (Iterable<Shader>)(this.listShaders)) {
         shader.deleteShader();
      }

      this.listShaders.clear();
   }

   private void initUniform(JsonElement elem) throws JsonException {
      JsonObject jsonObject = JsonUtils.getJsonObject(elem, "uniform");
      String string = JsonUtils.getString(jsonObject, "name");
      ShaderUniform shaderUniform = ((Shader)this.listShaders.get(this.listShaders.size() - 1)).getShaderManager().getShaderUniform(string);
      if (shaderUniform == null) {
         throw new JsonException("Uniform '" + string + "' does not exist");
      } else {
         float[] floats = new float[4];
         int n = 0;

         for (JsonElement elem2 : JsonUtils.getJsonArray(jsonObject, "values")) {
            try {
               floats[n] = JsonUtils.getFloat(elem2, "value");
            } catch (Exception e) {
               JsonException ex = JsonException.forException(e);
               ex.prependJsonKey("values[" + n + "]");
               throw ex;
            }

            n++;
         }

         switch (n) {
            case 0:
            default:
               break;
            case 1:
               shaderUniform.set(floats[0]);
               break;
            case 2:
               shaderUniform.set(floats[0], floats[1]);
               break;
            case 3:
               shaderUniform.set(floats[0], floats[1], floats[2]);
               break;
            case 4:
               shaderUniform.set(floats[0], floats[1], floats[2], floats[3]);
         }
      }
   }

   public Framebuffer getFramebufferRaw(String str) {
      return (Framebuffer)this.mapFramebuffers.get(str);
   }

   public void loadShaderGroup(float f) {
      if (f < this.lastStamp) {
         this.time = this.time + (1.0F - this.lastStamp);
         this.time += f;
      } else {
         this.time = this.time + (f - this.lastStamp);
      }

      this.lastStamp = f;

      while (this.time > 20.0F) {
         this.time -= 20.0F;
      }

      for (Shader shader : (Iterable<Shader>)(this.listShaders)) {
         shader.loadShader(this.time / 20.0F);
      }
   }

   public List getListShaders() {
      return this.listShaders;
   }

   public void parseGroup(TextureManager textureManager, ResourceLocation location) throws JsonException, IOException, JsonSyntaxException {
      JsonParser jsonParser = new JsonParser();
      InputStream in = null;

      try {
         in = this.resourceManager.getResource(location).getInputStream();
         JsonObject asJsonObject = jsonParser.parse(IOUtils.toString(in, Charsets.UTF_8)).getAsJsonObject();
         if (JsonUtils.isJsonArray(asJsonObject, "targets")) {
            JsonArray asJsonArray = asJsonObject.getAsJsonArray("targets");
            int n = 0;

            for (JsonElement elem : asJsonArray) {
               try {
                  this.initTarget(elem);
               } catch (Exception e) {
                  JsonException ex = JsonException.forException(e);
                  ex.prependJsonKey("targets[" + n + "]");
                  throw ex;
               }

               n++;
            }
         }

         if (JsonUtils.isJsonArray(asJsonObject, "passes")) {
            JsonArray asJsonArray2 = asJsonObject.getAsJsonArray("passes");
            int n2 = 0;

            for (JsonElement elem2 : asJsonArray2) {
               try {
                  this.parsePass(textureManager, elem2);
               } catch (Exception e2) {
                  JsonException ex2 = JsonException.forException(e2);
                  ex2.prependJsonKey("passes[" + n2 + "]");
                  throw ex2;
               }

               n2++;
            }
         }
      } catch (Exception e3) {
         JsonException ex3 = JsonException.forException(e3);
         ex3.setFilenameAndFlush(location.getResourcePath());
         throw ex3;
      } finally {
         IOUtils.closeQuietly(in);
      }
   }

   private void resetProjectionMatrix() {
      this.projectionMatrix = new Matrix4f();
      this.projectionMatrix.setIdentity();
      this.projectionMatrix.m00 = 2.0F / (float)this.mainFramebuffer.framebufferTextureWidth;
      this.projectionMatrix.m11 = 2.0F / (float)(-this.mainFramebuffer.framebufferTextureHeight);
      this.projectionMatrix.m22 = -0.0020001999F;
      this.projectionMatrix.m33 = 1.0F;
      this.projectionMatrix.m03 = -1.0F;
      this.projectionMatrix.m13 = 1.0F;
      this.projectionMatrix.m23 = -1.0001999F;
   }

   public Shader addShader(String str, Framebuffer framebuffer, Framebuffer framebuffer2) throws JsonException, IOException {
      Shader shader = new Shader(this.resourceManager, str, framebuffer, framebuffer2);
      this.listShaders.add(this.listShaders.size(), shader);
      return shader;
   }
}
