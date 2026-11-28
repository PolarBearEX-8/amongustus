package net.optifine.shaders;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class SVertexAttrib {
   public int index;
   public int count;
   public VertexFormatElement.EnumType type;
   public int offset;

   public SVertexAttrib(int n, int n2, VertexFormatElement.EnumType enumType) {
      this.index = n;
      this.count = n2;
      this.type = enumType;
   }
}
