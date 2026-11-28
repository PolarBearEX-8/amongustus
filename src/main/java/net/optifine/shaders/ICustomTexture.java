package net.optifine.shaders;

public interface ICustomTexture {
   int getTextureId();

   void deleteTexture();

   int getTarget();

   int getTextureUnit();
}
