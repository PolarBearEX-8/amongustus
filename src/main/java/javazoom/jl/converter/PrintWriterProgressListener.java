package javazoom.jl.converter;

import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.Obuffer;
import java.io.PrintWriter;

public class PrintWriterProgressListener implements ConverterProgressListener {
   public static final int NO_DETAIL = 0;
   public static final int EXPERT_DETAIL = 1;
   public static final int VERBOSE_DETAIL = 2;
   public static final int DEBUG_DETAIL = 7;
   public static final int MAX_DETAIL = 10;
   private PrintWriter pw;
   private int detailLevel;

   public boolean isDetail(int n) {
      return this.detailLevel >= n;
   }

   @Override
   public void converterUpdate(int n, int n2, int n3) {
      if (this.isDetail(2)) {
         switch (n) {
            case 2:
               if (n3 == 0) {
                  n3 = 1;
               }

               this.pw.println();
               this.pw.println("Converted " + n3 + " frames in " + n2 + " ms (" + n2 / n3 + " ms per frame.)");
         }
      }
   }

   @Override
   public void decodedFrame(int n, Header header, Obuffer obuffer) {
      if (this.isDetail(10)) {
         String str = header.toString();
         this.pw.println("Decoded frame " + n + ": " + str);
         this.pw.println("Output: " + obuffer);
      } else if (this.isDetail(2)) {
         if (n == 0) {
            this.pw.print("Converting.");
            this.pw.flush();
         }

         if (n % 10 == 0) {
            this.pw.print('.');
            this.pw.flush();
         }
      }
   }

   @Override
   public boolean converterException(Throwable t) {
      if (this.detailLevel > 0) {
         t.printStackTrace(this.pw);
         this.pw.flush();
      }

      return false;
   }

   public PrintWriterProgressListener(PrintWriter writer, int n) {
      this.pw = writer;
      this.detailLevel = n;
   }

   public static PrintWriterProgressListener newStdOut(int n) {
      return new PrintWriterProgressListener(new PrintWriter(System.out, true), n);
   }

   @Override
   public void readFrame(int n, Header header) {
      if (n == 0 && this.isDetail(2)) {
         String str = header.toString();
         this.pw.println("File is a " + str);
      } else if (this.isDetail(10)) {
         String str2 = header.toString();
         this.pw.println("Read frame " + n + ": " + str2);
      }
   }

   @Override
   public void parsedFrame(int n, Header header) {
      if (n == 0 && this.isDetail(2)) {
         String str = header.toString();
         this.pw.println("File is a " + str);
      } else if (this.isDetail(10)) {
         String str2 = header.toString();
         this.pw.println("Prased frame " + n + ": " + str2);
      }
   }
}
