package net.minecraft.client.renderer.vertex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertexFormatElement {
   private static final Logger LOGGER = LogManager.getLogger();
   private final VertexFormatElement.EnumType type;
   private final VertexFormatElement.EnumUsage usage;
   private int index;
   private int elementCount;

   public VertexFormatElement(int n, VertexFormatElement.EnumType enumType, VertexFormatElement.EnumUsage enumUsage, int n2) {
      if (!this.isFirstOrUV(n, enumUsage)) {
         LOGGER.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
         this.usage = VertexFormatElement.EnumUsage.UV;
      } else {
         this.usage = enumUsage;
      }

      this.type = enumType;
      this.index = n;
      this.elementCount = n2;
   }

   @Override
   public String toString() {
      return this.elementCount + "," + this.usage.getDisplayName() + "," + this.type.getDisplayName();
   }

   public final int getSize() {
      return this.type.getSize() * this.elementCount;
   }

   public final int getElementCount() {
      return this.elementCount;
   }

   private final boolean isFirstOrUV(int n, VertexFormatElement.EnumUsage enumUsage) {
      return n == 0 || enumUsage == VertexFormatElement.EnumUsage.UV;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         VertexFormatElement vertexFormatElement = (VertexFormatElement)obj;
         return this.elementCount != vertexFormatElement.elementCount
            ? false
            : (this.index != vertexFormatElement.index ? false : (this.type != vertexFormatElement.type ? false : this.usage == vertexFormatElement.usage));
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int n = this.type.hashCode();
      n = 31 * n + this.usage.hashCode();
      n = 31 * n + this.index;
      return 31 * n + this.elementCount;
   }

   public final boolean isPositionElement() {
      return this.usage == VertexFormatElement.EnumUsage.POSITION;
   }

   public final int getIndex() {
      return this.index;
   }

   public final VertexFormatElement.EnumUsage getUsage() {
      return this.usage;
   }

   public final VertexFormatElement.EnumType getType() {
      return this.type;
   }


   public enum EnumType {
      FLOAT(4, "Float", 5126),
      UBYTE(1, "Unsigned Byte", 5121),
      BYTE(1, "Byte", 5120),
      USHORT(2, "Unsigned Short", 5123),
      SHORT(2, "Short", 5122),
      UINT(4, "Unsigned Int", 5125),
      INT(4, "Int", 5124);

   private final int size;
      private final String displayName;
      private final int glConstant;
   private EnumType(int n, String str, int n2) {
         this.size = n;
         this.displayName = str;
         this.glConstant = n2;
      }

      public int getSize() {
         return this.size;
      }

      public int getGlConstant() {
         return this.glConstant;
      }

      public String getDisplayName() {
         return this.displayName;
      }
   }

   public enum EnumUsage {
      POSITION("Position"),
      NORMAL("Normal"),
      COLOR("Vertex Color"),
      UV("UV"),
      MATRIX("Bone Matrix"),
      BLEND_WEIGHT("Blend Weight"),
      PADDING("Padding");

   private final String displayName;
   private EnumUsage(String str) {
         this.displayName = str;
      }

      public String getDisplayName() {
         return this.displayName;
      }
   }
}
