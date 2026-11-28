package net.minecraft.client.renderer.chunk;

import java.util.Set;
import net.minecraft.util.EnumFacing;

public class SetVisibility {
   private static final int COUNT_FACES = EnumFacing.getValues().length;
   private long bits;

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(' ');

      for (EnumFacing facing : EnumFacing.getValues()) {
         sb.append(' ').append(facing.toString().toUpperCase().charAt(0));
      }

      sb.append('\n');

      for (EnumFacing facing2 : EnumFacing.getValues()) {
         sb.append(facing2.toString().toUpperCase().charAt(0));

         for (EnumFacing facing3 : EnumFacing.getValues()) {
            if (facing2 == facing3) {
               sb.append("  ");
            } else {
               boolean isVisible = this.isVisible(facing2, facing3);
               sb.append(' ').append((char)(isVisible ? 'Y' : 'n'));
            }
         }

         sb.append('\n');
      }

      return sb.toString();
   }

   public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
      return this.getBit(facing.ordinal() + facing2.ordinal() * COUNT_FACES);
   }

   private boolean getBit(int n) {
      return (this.bits & (long)(1 << n)) != 0L;
   }

   public void setVisible(EnumFacing facing, EnumFacing facing2, boolean flag) {
      this.setBit(facing.ordinal() + facing2.ordinal() * COUNT_FACES, flag);
      this.setBit(facing2.ordinal() + facing.ordinal() * COUNT_FACES, flag);
   }

   private void setBit(int n, boolean flag) {
      if (flag) {
         this.setBit(n);
      } else {
         this.clearBit(n);
      }
   }

   private void setBit(int n) {
      this.bits |= (long)(1 << n);
   }

   public void setAllVisible(boolean flag) {
      if (flag) {
         this.bits = -1L;
      } else {
         this.bits = 0L;
      }
   }

   private void clearBit(int n) {
      this.bits &= (long)(~(1 << n));
   }

   public void setManyVisible(Set set) {
      for (EnumFacing facing : (Iterable<EnumFacing>)(set)) {
         for (EnumFacing facing2 : (Iterable<EnumFacing>)(set)) {
            this.setVisible(facing, facing2, true);
         }
      }
   }
}
