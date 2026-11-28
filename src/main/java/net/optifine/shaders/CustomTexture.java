package net.optifine.shaders;

import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;

public class CustomTexture implements ICustomTexture {
   private int textureUnit = -1;
   private String path = null;
   private ITextureObject texture = null;

   @Override
   public int getTarget() {
      return 3553;
   }

   @Override
   public void deleteTexture() {
      TextureUtil.deleteTexture(this.texture.getGlTextureId());
   }

   @Override
   public String toString() {
      return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.getTextureId();
   }

   @Override
   public int getTextureUnit() {
      return this.textureUnit;
   }

   public String getPath() {
      return this.path;
   }

   @Override
   public int getTextureId() {
      return this.texture.getGlTextureId();
   }

   public CustomTexture(int n, String str, ITextureObject textureObject) {
      this.textureUnit = n;
      this.path = str;
      this.texture = textureObject;
   }

   public ITextureObject getTexture() {
      return this.texture;
   }
}
