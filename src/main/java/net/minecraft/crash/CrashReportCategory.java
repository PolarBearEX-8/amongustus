package net.minecraft.crash;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class CrashReportCategory {
   private final CrashReport crashReport;
   private final String name;
   private final List children = Lists.newArrayList();
   private StackTraceElement[] stackTrace = new StackTraceElement[0];

   public void appendToStringBuilder(StringBuilder sb) {
      sb.append("-- ").append(this.name).append(" --\n");
      sb.append("Details:");

      for (CrashReportCategory.Entry entry : (Iterable<CrashReportCategory.Entry>)(this.children)) {
         sb.append("\n\t");
         sb.append(entry.getKey());
         sb.append(": ");
         sb.append(entry.getValue());
      }

      if (this.stackTrace != null && this.stackTrace.length > 0) {
         sb.append("\nStacktrace:");

         for (StackTraceElement stackTraceElement : this.stackTrace) {
            sb.append("\n\tat ");
            sb.append(stackTraceElement.toString());
         }
      }
   }

   public CrashReportCategory(CrashReport crashReport2, String str) {
      this.crashReport = crashReport2;
      this.name = str;
   }

   public StackTraceElement[] getStackTrace() {
      return this.stackTrace;
   }

   public void trimStackTraceEntriesFromBottom(int n) {
      StackTraceElement[] stackTraceElements = new StackTraceElement[this.stackTrace.length - n];
      System.arraycopy(this.stackTrace, 0, stackTraceElements, 0, stackTraceElements.length);
      this.stackTrace = stackTraceElements;
   }

   public boolean firstTwoElementsOfStackTraceMatch(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
      if (this.stackTrace.length != 0 && stackTraceElement != null) {
         StackTraceElement stackTraceElement3 = this.stackTrace[0];
         if (stackTraceElement3.isNativeMethod() == stackTraceElement.isNativeMethod()
            && stackTraceElement3.getClassName().equals(stackTraceElement.getClassName())
            && stackTraceElement3.getFileName().equals(stackTraceElement.getFileName())
            && stackTraceElement3.getMethodName().equals(stackTraceElement.getMethodName())) {
            if (stackTraceElement2 != null != this.stackTrace.length > 1) {
               return false;
            } else if (stackTraceElement2 != null && !this.stackTrace[1].equals(stackTraceElement2)) {
               return false;
            } else {
               this.stackTrace[0] = stackTraceElement;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void addCrashSectionThrowable(String str, Throwable t) {
      this.addCrashSection(str, t);
   }

   public void addCrashSectionCallable(String str, Callable callable) {
      try {
         this.addCrashSection(str, callable.call());
      } catch (Throwable e) {
         this.addCrashSectionThrowable(str, e);
      }
   }

   public static void addBlockInfo(CrashReportCategory crashReportCategory, BlockPos pos, Block block, int n) {
      int idFromBlock = Block.getIdFromBlock(block);
      crashReportCategory.addCrashSectionCallable("Block type", new CrashReportCategory.ValueCallable(idFromBlock, block));
      crashReportCategory.addCrashSectionCallable("Block data value", new CrashReportCategory.ValueCallable2(n));
      crashReportCategory.addCrashSectionCallable("Block location", new CrashReportCategory.ValueCallable3(pos));
   }

   public void addCrashSection(String str, Object obj) {
      this.children.add(new CrashReportCategory.Entry(str, obj));
   }

   public static String getCoordinateInfo(double d, double d2, double d3) {
      return String.format("%.2f,%.2f,%.2f - %s", d, d2, d3, getCoordinateInfo(new BlockPos(d, d2, d3)));
   }

   public static void addBlockInfo(CrashReportCategory crashReportCategory, BlockPos pos, IBlockState state) {
      crashReportCategory.addCrashSectionCallable("Block", new CrashReportCategory.ValueCallable4(state));
      crashReportCategory.addCrashSectionCallable("Block location", new CrashReportCategory.ValueCallable5(pos));
   }

   public int getPrunedStackTrace(int n) {
      StackTraceElement[] stackTrace2 = Thread.currentThread().getStackTrace();
      if (stackTrace2.length <= 0) {
         return 0;
      } else {
         this.stackTrace = new StackTraceElement[stackTrace2.length - 3 - n];
         System.arraycopy(stackTrace2, 3 + n, this.stackTrace, 0, this.stackTrace.length);
         return this.stackTrace.length;
      }
   }

   public static String getCoordinateInfo(BlockPos pos) {
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      StringBuilder sb = new StringBuilder();

      try {
         sb.append(String.format("World: (%d,%d,%d)", x, y, z));
      } catch (Throwable e) {
         sb.append("(Error finding world loc)");
      }

      sb.append(", ");

      try {
         int n = x >> 4;
         int n2 = z >> 4;
         int n3 = x & 15;
         int n4 = y >> 4;
         int n5 = z & 15;
         int n6 = n << 4;
         int n7 = n2 << 4;
         int n8 = (n + 1 << 4) - 1;
         int n9 = (n2 + 1 << 4) - 1;
         sb.append(
            String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", n3, n4, n5, n, n2, n6, n7, n8, n9)
         );
      } catch (Throwable e2) {
         sb.append("(Error finding chunk loc)");
      }

      sb.append(", ");

      try {
         int n10 = x >> 9;
         int n11 = z >> 9;
         int n12 = n10 << 5;
         int n13 = n11 << 5;
         int n14 = (n10 + 1 << 5) - 1;
         int n15 = (n11 + 1 << 5) - 1;
         int n16 = n10 << 9;
         int n17 = n11 << 9;
         int n18 = (n10 + 1 << 9) - 1;
         int n19 = (n11 + 1 << 9) - 1;
         sb.append(
            String.format(
               "Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)",
               n10,
               n11,
               n12,
               n13,
               n14,
               n15,
               n16,
               n17,
               n18,
               n19
            )
         );
      } catch (Throwable e3) {
         sb.append("(Error finding world loc)");
      }

      return sb.toString();
   }


   public static final class ValueCallable implements Callable {
      public final int blockId;
      public final Block block;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable(int n, Block block2) {
         this.blockId = n;
         this.block = block2;
      }

      public String callAsString() throws Exception {
         try {
            return String.format("ID #%d (%s // %s)", this.blockId, this.block.getUnlocalizedName(), this.block.getClass().getCanonicalName());
         } catch (Throwable e) {
            return "ID #" + this.blockId;
         }
      }
   }

   public static final class ValueCallable2 implements Callable {
      public final int blockData;

      public String callAsString() throws Exception {
         if (this.blockData < 0) {
            return "Unknown? (Got " + this.blockData + ")";
         } else {
            String str = String.format("%4s", Integer.toBinaryString(this.blockData)).replace(" ", "0");
            return String.format("%1$d / 0x%1$X / 0b%2$s", this.blockData, str);
         }
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable2(int n) {
         this.blockData = n;
      }
   }

   public static final class ValueCallable3 implements Callable {
      public final BlockPos blockPos;

      public ValueCallable3(BlockPos pos) {
         this.blockPos = pos;
      }

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo(this.blockPos);
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static final class ValueCallable4 implements Callable {
      public final IBlockState blockState;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public ValueCallable4(IBlockState state) {
         this.blockState = state;
      }

      public String callAsString() throws Exception {
         return this.blockState.toString();
      }
   }

   public static final class ValueCallable5 implements Callable {
      public final BlockPos blockPos;

      public String callAsString() throws Exception {
         return CrashReportCategory.getCoordinateInfo(this.blockPos);
      }

      public ValueCallable5(BlockPos pos) {
         this.blockPos = pos;
      }

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }
   }

   public static class Entry {
      private final String key;
      private final String value;

      public Entry(String str, Object obj) {
         this.key = str;
         if (obj == null) {
            this.value = "~~NULL~~";
         } else if (obj instanceof Throwable) {
            Throwable t = (Throwable)obj;
            this.value = "~~ERROR~~ " + t.getClass().getSimpleName() + ": " + t.getMessage();
         } else {
            this.value = obj.toString();
         }
      }

      public String getKey() {
         return this.key;
      }

      public String getValue() {
         return this.value;
      }
   }
}
