package client.render;

import client.setting.CombatFilterProperty;
import client.util.FileNotFoundIOException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.BufferUtils;

public class ShadersProgramManager {
   public final CombatFilterProperty combatFilterProperty;
   public final String labelText;
   public int count;
   public int count2 = 0;

   public static byte[] resolveByteArray(BufferedInputStream bufferedInputStream) throws IOException {
      byte[] byteValueArray;
      try {
         byteValueArray = IOUtils.toByteArray(bufferedInputStream);
      } finally {
         bufferedInputStream.close();
      }

      return byteValueArray;
   }

   public static ShadersProgramManager resolveShadersProgramManager(IResourceManager iResourceManager, CombatFilterProperty combatFilterProperty2, String text) throws IOException {
      ShadersProgramManager shadersProgramManager = (ShadersProgramManager)combatFilterProperty2.getMap().get(text);
      if (shadersProgramManager == null) {
         ResourceLocation location = new ResourceLocation("shaders/program/" + text + combatFilterProperty2.getLabelText2());
         byte[] byteValueArray = resolveByteArray(new BufferedInputStream(iResourceManager.resolveIResourceByResourceLocation(location).getInputStream()));
         ByteBuffer buffer = BufferUtils.createByteBuffer(byteValueArray.length);
         buffer.put(byteValueArray);
         ((Buffer)buffer).position(0);
         int count3 = OpenGlHelper.computeCount(combatFilterProperty2.getCount());
         OpenGlHelper.applyByteBuffer(count3, buffer);
         OpenGlHelper.applyIndex2(count3);
         if (OpenGlHelper.computeCountByIndex(count3, OpenGlHelper.count34) == 0) {
            String text2 = StringUtils.trim(OpenGlHelper.resolveTextByIndex(count3, 32768));
            FileNotFoundIOException fileNotFoundIOException = new FileNotFoundIOException("Couldn't compile " + combatFilterProperty2.getLabelText() + " program: " + text2);
            fileNotFoundIOException.applyTextByText(location.getText1());
            throw fileNotFoundIOException;
         }

         shadersProgramManager = new ShadersProgramManager(combatFilterProperty2, count3, text);
         combatFilterProperty2.getMap().put(text, shadersProgramManager);
      }

      return shadersProgramManager;
   }

   public void applyShaderManagerByShaderManager(ShaderManager shaderManager) {
      this.count2++;
      OpenGlHelper.applyIndex4(shaderManager.getCount2(), this.count);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void applyShaderManager(ShaderManager shaderManager) {
      this.count2--;
      if (this.count2 <= 0) {
         OpenGlHelper.applyIndex5(this.count);
         this.combatFilterProperty.getMap().remove(this.labelText);
      }
   }

   private ShadersProgramManager(CombatFilterProperty combatFilterProperty2, int count3, String text) {
      this.combatFilterProperty = combatFilterProperty2;
      this.count = count3;
      this.labelText = text;
   }
}
