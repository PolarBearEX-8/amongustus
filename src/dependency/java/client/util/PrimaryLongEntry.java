package client.util;

import java.util.Set;
import net.minecraft.util.EnumFacing;

public class PrimaryLongEntry {
   public static final int count = EnumFacing.getEntries().length;
   public long timestamp;

   public void applySet(Set set) {
      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable<?>)set) {
         for (EnumFacing enumFacing2 : (Iterable<EnumFacing>)(Iterable<?>)set) {
            this.applyEnumFacing(enumFacing, enumFacing2, true);
         }
      }
   }

   public void setEnabled(boolean enabled) {
      if (enabled) {
         this.timestamp = -1L;
      } else {
         this.timestamp = 0L;
      }
   }

   private void applyIndex(int count2, boolean flag) {
      if (flag) {
         this.applyIndex(count2);
      } else {
         this.applyIndexByIndex(count2);
      }
   }

   private boolean checkIndex(int count2) {
      return (this.timestamp & (long)(1 << count2)) != 0L;
   }

   public void applyEnumFacing(EnumFacing enumFacing, EnumFacing enumFacing2, boolean flag) {
      this.applyIndex(enumFacing.ordinal() + enumFacing2.ordinal() * count, flag);
      this.applyIndex(enumFacing2.ordinal() + enumFacing.ordinal() * count, flag);
   }

   private void applyIndex(int count2) {
      this.timestamp |= (long)(1 << count2);
   }

   public boolean matchesEnumFacing(EnumFacing enumFacing, EnumFacing enumFacing2) {
      return this.checkIndex(enumFacing.ordinal() + enumFacing2.ordinal() * count);
   }

   private void applyIndexByIndex(int count2) {
      this.timestamp &= (long)(~(1 << count2));
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(' ');

      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         builder.append(' ').append(enumFacing.toString().toUpperCase().charAt(0));
      }

      builder.append('\n');

      for (EnumFacing enumFacing2 : EnumFacing.getEntries()) {
         builder.append(enumFacing2.toString().toUpperCase().charAt(0));

         for (EnumFacing enumFacing3 : EnumFacing.getEntries()) {
            if (enumFacing2 == enumFacing3) {
               builder.append("  ");
            } else {
               boolean flag = this.matchesEnumFacing(enumFacing2, enumFacing3);
               builder.append(' ').append((char)(flag ? 'Y' : 'n'));
            }
         }

         builder.append('\n');
      }

      return builder.toString();
   }
}
