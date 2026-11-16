package client.util;

import client.event.BridgeListener;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class PassManager {
   public Framebuffer framebuffer;
   public IResourceManager iResourceManager;
   public String labelText;
   public final List list2 = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public final List list = Lists.newArrayList();
   public Matrix4f matrix4f;
   public int count;
   public int count2;
   public float factor;
   public float factor2;

   public void applyText(String text, int count3, int count4) {
      Framebuffer framebuffer2 = new Framebuffer(count3, count4, true);
      framebuffer2.applyFloat(0.0F, 0.0F, 0.0F, 0.0F);
      this.map.put(text, framebuffer2);
      if (count3 == this.count && count4 == this.count2) {
         this.list.add(framebuffer2);
      }
   }

   public void applyIndex(int count3, int count4) {
      this.count = this.framebuffer.count4;
      this.count2 = this.framebuffer.count5;
      this.updateState2();
      Iterator iterator = this.list2.iterator();

      while (iterator.hasNext()) {
         ((DiffuseSamplerManager)iterator.next()).setMatrix4f(this.matrix4f);
      }

      Iterator iterator2 = this.list.iterator();

      while (iterator2.hasNext()) {
         ((Framebuffer)iterator2.next()).applyIndex4(count3, count4);
      }
   }

   public void updateState() {
      Iterator iterator = this.map.values().iterator();

      while (iterator.hasNext()) {
         ((Framebuffer)iterator.next()).updateState6();
      }

      Iterator iterator2 = this.list2.iterator();

      while (iterator2.hasNext()) {
         ((DiffuseSamplerManager)iterator2.next()).updateState();
      }

      this.list2.clear();
   }

   public final String getLabelText() {
      return this.labelText;
   }

   private void applyTextureManager(TextureManager textureManager, JsonElement jsonElement) throws FileNotFoundIOException, IOException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "pass");
      String text = MissingManager.resolveText(jsonObject, "name");
      String text2 = MissingManager.resolveText(jsonObject, "intarget");
      String text3 = MissingManager.resolveText(jsonObject, "outtarget");
      Framebuffer framebuffer2 = this.resolveFramebufferByText(text2);
      Framebuffer framebuffer3 = this.resolveFramebufferByText(text3);
      if (framebuffer2 == null) {
         throw new FileNotFoundIOException("Input target '" + text2 + "' does not exist");
      } else if (framebuffer3 == null) {
         throw new FileNotFoundIOException("Output target '" + text3 + "' does not exist");
      } else {
         DiffuseSamplerManager diffuseSamplerManager = this.resolveDiffuseSamplerManagerByText(text, framebuffer2, framebuffer3);
         JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, "auxtargets", (JsonArray)null);
         if (jsonArray != null) {
            int count3 = 0;

            for (JsonElement jsonElement2 : jsonArray) {
               try {
                  JsonObject jsonObject2 = MissingManager.resolveJsonObject(jsonElement2, "auxtarget");
                  String text4 = MissingManager.resolveText(jsonObject2, "name");
                  String text5 = MissingManager.resolveText(jsonObject2, "id");
                  Framebuffer framebuffer4 = this.resolveFramebufferByText(text5);
                  if (framebuffer4 == null) {
                     ResourceLocation location = new ResourceLocation("textures/effect/" + text5 + ".png");

                     try {
                        this.iResourceManager.resolveIResourceByResourceLocation(location);
                     } catch (FileNotFoundException fileNotFoundException) {
                        throw new FileNotFoundIOException("Render target or texture '" + text5 + "' does not exist");
                     }

                     textureManager.applyResourceLocation3(location);
                     BridgeListener bridgeListener = textureManager.get(location);
                     int count4 = MissingManager.computeCount(jsonObject2, "width");
                     int count5 = MissingManager.computeCount(jsonObject2, "height");
                     if (MissingManager.matchesJsonObject3(jsonObject2, "bilinear")) {
                        GL11.glTexParameteri(3553, 10241, 9729);
                        GL11.glTexParameteri(3553, 10240, 9729);
                     } else {
                        GL11.glTexParameteri(3553, 10241, 9728);
                        GL11.glTexParameteri(3553, 10240, 9728);
                     }

                     diffuseSamplerManager.applyText(text4, bridgeListener.getCount(), count4, count5);
                  } else {
                     diffuseSamplerManager.applyText(text4, framebuffer4, framebuffer4.count4, framebuffer4.count5);
                  }
               } catch (Exception exception) {
                  FileNotFoundIOException fileNotFoundIOException = FileNotFoundIOException.resolveFileNotFoundIOException(exception);
                  fileNotFoundIOException.applyText("auxtargets[" + count3 + "]");
                  throw fileNotFoundIOException;
               }

               count3++;
            }
         }

         JsonArray jsonArray2 = MissingManager.resolveJsonArray(jsonObject, "uniforms", (JsonArray)null);
         if (jsonArray2 != null) {
            int count6 = 0;

            for (JsonElement jsonElement3 : jsonArray2) {
               try {
                  this.applyJsonElement(jsonElement3);
               } catch (Exception exception2) {
                  FileNotFoundIOException fileNotFoundIOException2 = FileNotFoundIOException.resolveFileNotFoundIOException(exception2);
                  fileNotFoundIOException2.applyText("uniforms[" + count6 + "]");
                  throw fileNotFoundIOException2;
               }

               count6++;
            }
         }
      }
   }

   private void applyJsonElement(JsonElement jsonElement) throws FileNotFoundIOException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "uniform");
      String text = MissingManager.resolveText(jsonObject, "name");
      IntManager intManager = ((DiffuseSamplerManager)this.list2.get(this.list2.size() - 1)).getShaderManager().resolveIntManager(text);
      if (intManager == null) {
         throw new FileNotFoundIOException("Uniform '" + text + "' does not exist");
      } else {
         float[] factorArray = new float[4];
         int count3 = 0;

         for (JsonElement jsonElement2 : MissingManager.resolveJsonArray(jsonObject, "values")) {
            try {
               factorArray[count3] = MissingManager.computeFactor(jsonElement2, "value");
            } catch (Exception exception) {
               FileNotFoundIOException fileNotFoundIOException = FileNotFoundIOException.resolveFileNotFoundIOException(exception);
               fileNotFoundIOException.applyText("values[" + count3 + "]");
               throw fileNotFoundIOException;
            }

            count3++;
         }

         switch (count3) {
            case 0:
            default:
               break;
            case 1:
               intManager.applyFloat(factorArray[0]);
               break;
            case 2:
               intManager.applyFloat(factorArray[0], factorArray[1]);
               break;
            case 3:
               intManager.applyFloat(factorArray[0], factorArray[1], factorArray[2]);
               break;
            case 4:
               intManager.applyFloatByFloat(factorArray[0], factorArray[1], factorArray[2], factorArray[3]);
         }
      }
   }

   private void updateState2() {
      this.matrix4f = new Matrix4f();
      this.matrix4f.setIdentity();
      this.matrix4f.m00 = 2.0F / (float)this.framebuffer.count4;
      this.matrix4f.m11 = 2.0F / (float)(-this.framebuffer.count5);
      this.matrix4f.m22 = -0.0020001999F;
      this.matrix4f.m33 = 1.0F;
      this.matrix4f.m03 = -1.0F;
      this.matrix4f.m13 = 1.0F;
      this.matrix4f.m23 = -1.0001999F;
   }

   private Framebuffer resolveFramebufferByText(String text) {
      return text == null ? null : (text.equals("minecraft:main") ? this.framebuffer : (Framebuffer)this.map.get(text));
   }

   public Framebuffer get(String text) {
      return (Framebuffer)this.map.get(text);
   }

   public DiffuseSamplerManager resolveDiffuseSamplerManagerByText(String text, Framebuffer framebuffer2, Framebuffer framebuffer3) throws FileNotFoundIOException, IOException {
      DiffuseSamplerManager diffuseSamplerManager = new DiffuseSamplerManager(this.iResourceManager, text, framebuffer2, framebuffer3);
      this.list2.add(this.list2.size(), diffuseSamplerManager);
      return diffuseSamplerManager;
   }

   private void applyJsonElementByJsonElement(JsonElement jsonElement) throws FileNotFoundIOException {
      if (MissingManager.matchesJsonElement(jsonElement)) {
         this.applyText(jsonElement.getAsString(), this.count, this.count2);
      } else {
         JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "target");
         String text = MissingManager.resolveText(jsonObject, "name");
         int count3 = MissingManager.computeCount(jsonObject, "width", this.count);
         int count4 = MissingManager.computeCount(jsonObject, "height", this.count2);
         if (this.map.containsKey(text)) {
            throw new FileNotFoundIOException(text + " is already defined");
         }

         this.applyText(text, count3, count4);
      }
   }

   public void applyTextureManagerByTextureManager(TextureManager textureManager, ResourceLocation location) throws FileNotFoundIOException, IOException, JsonSyntaxException {
      JsonParser jsonParser = new JsonParser();
      InputStream inputStream = null;

      try {
         inputStream = this.iResourceManager.resolveIResourceByResourceLocation(location).getInputStream();
         JsonObject asJsonObject = jsonParser.parse(IOUtils.toString(inputStream, Charsets.UTF_8)).getAsJsonObject();
         if (MissingManager.matchesJsonObject2(asJsonObject, "targets")) {
            JsonArray asJsonArray = asJsonObject.getAsJsonArray("targets");
            int count3 = 0;

            for (JsonElement jsonElement : asJsonArray) {
               try {
                  this.applyJsonElementByJsonElement(jsonElement);
               } catch (Exception exception) {
                  FileNotFoundIOException fileNotFoundIOException = FileNotFoundIOException.resolveFileNotFoundIOException(exception);
                  fileNotFoundIOException.applyText("targets[" + count3 + "]");
                  throw fileNotFoundIOException;
               }

               count3++;
            }
         }

         if (MissingManager.matchesJsonObject2(asJsonObject, "passes")) {
            JsonArray asJsonArray2 = asJsonObject.getAsJsonArray("passes");
            int count4 = 0;

            for (JsonElement jsonElement2 : asJsonArray2) {
               try {
                  this.applyTextureManager(textureManager, jsonElement2);
               } catch (Exception exception2) {
                  FileNotFoundIOException fileNotFoundIOException2 = FileNotFoundIOException.resolveFileNotFoundIOException(exception2);
                  fileNotFoundIOException2.applyText("passes[" + count4 + "]");
                  throw fileNotFoundIOException2;
               }

               count4++;
            }
         }
      } catch (Exception exception3) {
         FileNotFoundIOException fileNotFoundIOException3 = FileNotFoundIOException.resolveFileNotFoundIOException(exception3);
         fileNotFoundIOException3.applyTextByText(location.getText1());
         throw fileNotFoundIOException3;
      } finally {
         IOUtils.closeQuietly(inputStream);
      }
   }

   public List getList2() {
      return this.list2;
   }

   public void applyFloat(float factor3) {
      if (factor3 < this.factor2) {
         this.factor = this.factor + (1.0F - this.factor2);
         this.factor += factor3;
      } else {
         this.factor = this.factor + (factor3 - this.factor2);
      }

      this.factor2 = factor3;

      while (this.factor > 20.0F) {
         this.factor -= 20.0F;
      }

      Iterator iterator = this.list2.iterator();

      while (iterator.hasNext()) {
         ((DiffuseSamplerManager)iterator.next()).applyFloat(this.factor / 20.0F);
      }
   }

   public PassManager(TextureManager textureManager, IResourceManager iResourceManager2, Framebuffer framebuffer2, ResourceLocation location) throws FileNotFoundIOException, IOException, JsonSyntaxException {
      this.iResourceManager = iResourceManager2;
      this.framebuffer = framebuffer2;
      this.factor = 0.0F;
      this.factor2 = 0.0F;
      this.count = framebuffer2.indexOffset2;
      this.count2 = framebuffer2.indexOffset1;
      this.labelText = location.toString();
      this.updateState2();
      this.applyTextureManagerByTextureManager(textureManager, location);
   }
}
