package net.minecraft.client.renderer.vertex;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertexFormat {
   private static final Logger LOGGER = LogManager.getLogger();
   private final List elements = Lists.newArrayList();
   private final IntArrayList offsets = new IntArrayList();
   private int nextOffset = 0;
   private int colorOffset = -1;
   private IntArrayList uvOffsetsById = new IntArrayList();
   private int normalOffset = -1;

   public int getIntegerSize() {
      return this.nextOffset / 4;
   }

   public int getElementCount() {
      return this.elements.size();
   }

   public int getNextOffset() {
      return this.nextOffset;
   }

   public VertexFormat addElement(VertexFormatElement vertexFormatElement) {
      if (vertexFormatElement.isPositionElement() && this.hasPosition()) {
         LOGGER.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
         return this;
      } else {
         this.elements.add(vertexFormatElement);
         this.offsets.add(this.nextOffset);
         switch (vertexFormatElement.getUsage()) {
            case NORMAL:
               this.normalOffset = this.nextOffset;
               break;
            case COLOR:
               this.colorOffset = this.nextOffset;
               break;
            case UV:
               this.uvOffsetsById.add(vertexFormatElement.getIndex(), this.nextOffset);
         }

         this.nextOffset = this.nextOffset + vertexFormatElement.getSize();
         return this;
      }
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         VertexFormat vertexFormat = (VertexFormat)obj;
         return this.nextOffset == vertexFormat.nextOffset && this.elements.equals(vertexFormat.elements) && this.offsets.equals(vertexFormat.offsets);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder("format: " + this.elements.size() + " elements: ");

      for (int i = 0; i < this.elements.size(); i++) {
         sb.append(((VertexFormatElement)this.elements.get(i)).toString());
         if (i != this.elements.size() - 1) {
            sb.append(" ");
         }
      }

      return sb.toString();
   }

   public boolean hasColor() {
      return this.colorOffset >= 0;
   }

   private boolean hasPosition() {
      int n = 0;

      for (int i = this.elements.size(); n < i; n++) {
         if (((VertexFormatElement)this.elements.get(n)).isPositionElement()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int hashCode() {
      int n = this.elements.hashCode();
      n = 31 * n + this.offsets.hashCode();
      return 31 * n + this.nextOffset;
   }

   public VertexFormat(VertexFormat vertexFormat) {
      this();

      for (int i = 0; i < vertexFormat.getElementCount(); i++) {
         this.addElement(vertexFormat.getElement(i));
      }

      this.nextOffset = vertexFormat.getNextOffset();
   }

   public void clear() {
      this.elements.clear();
      this.offsets.clear();
      this.colorOffset = -1;
      this.uvOffsetsById.clear();
      this.normalOffset = -1;
      this.nextOffset = 0;
   }

   public int getNormalOffset() {
      return this.normalOffset;
   }

   public VertexFormatElement getElement(int n) {
      return (VertexFormatElement)this.elements.get(n);
   }

   public int getColorOffset() {
      return this.colorOffset;
   }

   public boolean hasUvOffset(int n) {
      return this.uvOffsetsById.size() - 1 >= n;
   }

   public int getUvOffsetById(int n) {
      return this.uvOffsetsById.getInt(n);
   }

   public int getOffset(int n) {
      return this.offsets.getInt(n);
   }

   public boolean hasNormal() {
      return this.normalOffset >= 0;
   }

   public VertexFormat() {
   }

   public List getElements() {
      return this.elements;
   }

}
