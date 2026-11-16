package client.render;

import client.event.BridgeListener;
import client.setting.CombatFilterProperty;
import client.util.AddManager;
import client.util.AndFSManager;
import client.util.FileNotFoundIOException;
import client.util.IntManager;
import client.util.IntManagerAdapter;
import client.util.MissingManager;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderManager {
   public static final Logger logger = LogManager.getLogger();
   public static final IntManagerAdapter intManagerAdapter = new IntManagerAdapter();
   public static ShaderManager shaderManager = null;
   public static int count = -1;
   public static boolean enabled = true;
   public final Map map2 = Maps.newHashMap();
   public final List list3 = Lists.newArrayList();
   public final List list6 = Lists.newArrayList();
   public final List list4 = Lists.newArrayList();
   public final List list5 = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public final int count2;
   public final String labelText;
   public final boolean enabled3;
   public boolean enabled2;
   public final AddManager addManager;
   public final List list2;
   public final List list;
   public final ShadersProgramManager shadersProgramManager;
   public final ShadersProgramManager shadersProgramManager2;

   public void renderElement4() {
      this.enabled2 = true;
   }

   public void renderElement2() {
      this.enabled2 = false;
      shaderManager = this;
      this.addManager.updateState();
      if (this.count2 != count) {
         OpenGlHelper.applyIndex8(this.count2);
         count = this.count2;
      }

      if (this.enabled3) {
         GlStateManager.enableLighting();
      } else {
         GlStateManager.disableAlpha();
      }

      for (int index = 0; index < this.list6.size(); index++) {
         if (this.map2.get(this.list3.get(index)) != null) {
            GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit + index);
            GlStateManager.disableTexture2D();
            Object entry = this.map2.get(this.list3.get(index));
            int count3 = -1;
            if (entry instanceof Framebuffer) {
               count3 = ((Framebuffer)entry).indexOffset;
            } else if (entry instanceof BridgeListener) {
               count3 = ((BridgeListener)entry).getCount();
            } else if (entry instanceof Integer) {
               count3 = (Integer)entry;
            }

            if (count3 != -1) {
               GlStateManager.setActiveTexture(count3);
               OpenGlHelper.applyIndexByIndex(OpenGlHelper.computeCount(this.count2, (CharSequence)this.list3.get(index)), index);
            }
         }
      }

      Iterator iterator = this.list4.iterator();

      while (iterator.hasNext()) {
         ((IntManager)iterator.next()).updateState5();
      }
   }

   public void renderElement() {
      OpenGlHelper.applyIndex8(0);
      count = -1;
      shaderManager = null;
      enabled = true;

      for (int index = 0; index < this.list6.size(); index++) {
         if (this.map2.get(this.list3.get(index)) != null) {
            GlStateManager.enableState5(OpenGlHelper.lightmapTexUnit + index);
            GlStateManager.setActiveTexture(0);
         }
      }
   }

   private void renderElement5() {
      int count3 = 0;

      for (int index = 0; count3 < this.list3.size(); index++) {
         String text = (String)this.list3.get(count3);
         int count4 = OpenGlHelper.computeCount(this.count2, text);
         if (count4 == -1) {
            logger.warn("Shader " + this.labelText + "could not find sampler named " + text + " in the specified shader program.");
            this.map2.remove(text);
            this.list3.remove(index);
            index--;
         } else {
            this.list6.add(count4);
         }

         count3++;
      }

      for (IntManager intManager : (Iterable<IntManager>)(this.list4)) {
         String labelText2 = intManager.getLabelText();
         int count5 = OpenGlHelper.computeCount(this.count2, labelText2);
         if (count5 == -1) {
            logger.warn("Could not find uniform named " + labelText2 + " in the specified shader program.");
         } else {
            this.list5.add(count5);
            intManager.setCount(count5);
            this.map.put(labelText2, intManager);
         }
      }
   }

   public void applyText(String text, Object entry) {
      if (this.map2.containsKey(text)) {
         this.map2.remove(text);
      }

      this.map2.put(text, entry);
      this.renderElement4();
   }

   public ShadersProgramManager getShadersProgramManager2() {
      return this.shadersProgramManager2;
   }

   public IntManager resolveIntManagerByText(String text) {
      return (IntManager)(this.map.containsKey(text) ? (IntManager)this.map.get(text) : intManagerAdapter);
   }

   public int getCount2() {
      return this.count2;
   }

   public ShadersProgramManager getShadersProgramManager() {
      return this.shadersProgramManager;
   }

   private void applyJsonElementByJsonElement(JsonElement jsonElement) throws FileNotFoundIOException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "sampler");
      String text = MissingManager.resolveText(jsonObject, "name");
      if (!MissingManager.matchesJsonObject4(jsonObject, "file")) {
         this.map2.put(text, null);
         this.list3.add(text);
      } else {
         this.list3.add(text);
      }
   }

   public IntManager resolveIntManager(String text) {
      return this.map.containsKey(text) ? (IntManager)this.map.get(text) : null;
   }

   private void applyJsonElement(JsonElement jsonElement) throws FileNotFoundIOException {
      JsonObject jsonObject = MissingManager.resolveJsonObject(jsonElement, "uniform");
      String text = MissingManager.resolveText(jsonObject, "name");
      int count3 = IntManager.computeCount(MissingManager.resolveText(jsonObject, "type"));
      int count4 = MissingManager.computeCount(jsonObject, "count");
      float[] factorArray = new float[Math.max(count4, 16)];
      JsonArray jsonArray = MissingManager.resolveJsonArray(jsonObject, "values");
      if (jsonArray.size() != count4 && jsonArray.size() > 1) {
         throw new FileNotFoundIOException("Invalid amount of values specified (expected " + count4 + ", found " + jsonArray.size() + ")");
      } else {
         int count5 = 0;

         for (JsonElement jsonElement2 : jsonArray) {
            try {
               factorArray[count5] = MissingManager.computeFactor(jsonElement2, "value");
            } catch (Exception exception) {
               FileNotFoundIOException fileNotFoundIOException = FileNotFoundIOException.resolveFileNotFoundIOException(exception);
               fileNotFoundIOException.applyText("values[" + count5 + "]");
               throw fileNotFoundIOException;
            }

            count5++;
         }

         if (count4 > 1 && jsonArray.size() == 1) {
            while (count5 < count4) {
               factorArray[count5] = factorArray[0];
               count5++;
            }
         }

         int count6 = count4 > 1 && count4 <= 4 && count3 < 8 ? count4 - 1 : 0;
         IntManager intManager = new IntManager(text, count3 + count6, count4, this);
         if (count3 <= 3) {
            intManager.applyIndex((int)factorArray[0], (int)factorArray[1], (int)factorArray[2], (int)factorArray[3]);
         } else if (count3 <= 7) {
            intManager.applyFloat(factorArray[0], factorArray[1], factorArray[2], factorArray[3]);
         } else {
            intManager.applyFloatArray(factorArray);
         }

         this.list4.add(intManager);
      }
   }

   public void renderElement3() {
      AndFSManager.getAndFSManager().applyShaderManagerByShaderManager(this);
   }

   public ShaderManager(IResourceManager iResourceManager, String text) throws FileNotFoundIOException, IOException {
      JsonParser jsonParser = new JsonParser();
      ResourceLocation location = new ResourceLocation("shaders/program/" + text + ".json");
      this.labelText = text;
      InputStream inputStream = null;

      try {
         inputStream = iResourceManager.resolveIResourceByResourceLocation(location).getInputStream();
         JsonObject asJsonObject = jsonParser.parse(IOUtils.toString(inputStream, Charsets.UTF_8)).getAsJsonObject();
         String text2 = MissingManager.resolveText(asJsonObject, "vertex");
         String text3 = MissingManager.resolveText(asJsonObject, "fragment");
         JsonArray jsonArray = MissingManager.resolveJsonArray(asJsonObject, "samplers", (JsonArray)null);
         if (jsonArray != null) {
            int count3 = 0;

            for (JsonElement jsonElement : jsonArray) {
               try {
                  this.applyJsonElementByJsonElement(jsonElement);
               } catch (Exception exception) {
                  FileNotFoundIOException fileNotFoundIOException = FileNotFoundIOException.resolveFileNotFoundIOException(exception);
                  fileNotFoundIOException.applyText("samplers[" + count3 + "]");
                  throw fileNotFoundIOException;
               }

               count3++;
            }
         }

         JsonArray jsonArray2 = MissingManager.resolveJsonArray(asJsonObject, "attributes", (JsonArray)null);
         if (jsonArray2 != null) {
            int count4 = 0;
            this.list2 = Lists.newArrayListWithCapacity(jsonArray2.size());
            this.list = Lists.newArrayListWithCapacity(jsonArray2.size());

            for (JsonElement jsonElement2 : jsonArray2) {
               try {
                  this.list.add(MissingManager.resolveText(jsonElement2, "attribute"));
               } catch (Exception exception2) {
                  FileNotFoundIOException fileNotFoundIOException2 = FileNotFoundIOException.resolveFileNotFoundIOException(exception2);
                  fileNotFoundIOException2.applyText("attributes[" + count4 + "]");
                  throw fileNotFoundIOException2;
               }

               count4++;
            }
         } else {
            this.list2 = null;
            this.list = null;
         }

         JsonArray jsonArray3 = MissingManager.resolveJsonArray(asJsonObject, "uniforms", (JsonArray)null);
         if (jsonArray3 != null) {
            int count5 = 0;

            for (JsonElement jsonElement3 : jsonArray3) {
               try {
                  this.applyJsonElement(jsonElement3);
               } catch (Exception exception3) {
                  FileNotFoundIOException fileNotFoundIOException3 = FileNotFoundIOException.resolveFileNotFoundIOException(exception3);
                  fileNotFoundIOException3.applyText("uniforms[" + count5 + "]");
                  throw fileNotFoundIOException3;
               }

               count5++;
            }
         }

         this.addManager = AddManager.resolveAddManager(MissingManager.resolveJsonObject(asJsonObject, "blend", (JsonObject)null));
         this.enabled3 = MissingManager.matchesJsonObject(asJsonObject, "cull", true);
         this.shadersProgramManager = ShadersProgramManager.resolveShadersProgramManager(iResourceManager, CombatFilterProperty.VERTEX, text2);
         this.shadersProgramManager2 = ShadersProgramManager.resolveShadersProgramManager(iResourceManager, CombatFilterProperty.FRAGMENT, text3);
         this.count2 = AndFSManager.getAndFSManager().getCount();
         AndFSManager.getAndFSManager().applyShaderManager(this);
         this.renderElement5();
         if (this.list != null) {
            for (String text4 : (Iterable<String>)(this.list)) {
               int count6 = OpenGlHelper.computeCountByCharSequence(this.count2, text4);
               this.list2.add(count6);
            }
         }
      } catch (Exception exception4) {
         FileNotFoundIOException fileNotFoundIOException4 = FileNotFoundIOException.resolveFileNotFoundIOException(exception4);
         fileNotFoundIOException4.applyTextByText(location.getText1());
         throw fileNotFoundIOException4;
      } finally {
         IOUtils.closeQuietly(inputStream);
      }

      this.renderElement4();
   }
}
