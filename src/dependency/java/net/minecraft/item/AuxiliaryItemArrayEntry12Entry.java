package net.minecraft.item;

import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.EnumFacing;

public class AuxiliaryItemArrayEntry12Entry {
   public RenderChunk renderChunk;
   public EnumFacing enumFacing;
   public int count;

   private void applyEnumFacing(EnumFacing enumFacing2, int count2) {
      this.enumFacing = enumFacing2;
      this.count = count2;
   }

   public boolean ordinal(EnumFacing enumFacing2) {
      return (this.count & 1 << enumFacing2.ordinal()) > 0;
   }

   public void ordinal(byte byteValue, EnumFacing enumFacing2) {
      this.count = this.count | byteValue | 1 << enumFacing2.ordinal();
   }

   public static void applyAuxiliaryItemArrayEntry12E(AuxiliaryItemArrayEntry12Entry auxiliaryItemArrayEntry12Entry, EnumFacing enumFacing2, int count2) {
      auxiliaryItemArrayEntry12Entry.applyEnumFacing(enumFacing2, count2);
   }

   public AuxiliaryItemArrayEntry12Entry(RenderChunk renderChunk2, EnumFacing enumFacing2, int count2) {
      this.renderChunk = renderChunk2;
      this.enumFacing = enumFacing2;
      this.count = count2;
   }
}
