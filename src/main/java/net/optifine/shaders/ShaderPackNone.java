package net.optifine.shaders;

import java.io.InputStream;

public class ShaderPackNone implements IShaderPack {
   @Override
   public void close() {
   }

   @Override
   public String getName() {
      return "OFF";
   }

   @Override
   public boolean hasDirectory(String str) {
      return false;
   }

   @Override
   public InputStream getResourceAsStream(String str) {
      return null;
   }
}
