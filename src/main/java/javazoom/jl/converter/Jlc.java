package javazoom.jl.converter;

import javazoom.jl.decoder.JavaLayerException;
import java.io.PrintWriter;

public class Jlc {

   public static void main(String[] args) {
      System.currentTimeMillis();
      String[] strings = new String[args.length + 1];
      strings[0] = "jlc";

      for (int i = 0; i < args.length; i++) {
         strings[i + 1] = args[i];
      }

      JlcArgs jlcArgs = new JlcArgs();
      if (!jlcArgs.processArgs(strings)) {
         System.exit(1);
      }

      Converter converter = new Converter();
      int n2 = jlcArgs.verbose_mode ? jlcArgs.verbose_level : 0;
      PrintWriterProgressListener listener = new PrintWriterProgressListener(new PrintWriter(System.out, true), n2);

      try {
         converter.convert(jlcArgs.filename, jlcArgs.output_filename, listener);
      } catch (JavaLayerException e) {
         System.err.println("Convertion failure: " + e);
      }

      System.exit(0);
   }
}
