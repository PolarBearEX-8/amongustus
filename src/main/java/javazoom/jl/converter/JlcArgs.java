package javazoom.jl.converter;


public class JlcArgs {
   public int which_c;
   public int output_mode;
   public boolean use_own_scalefactor;
   public float scalefactor;
   public String output_filename;
   public String filename;
   public boolean verbose_mode;
   public int verbose_level = 3;

   public JlcArgs() {
      this.which_c = 0;
      this.use_own_scalefactor = false;
      this.scalefactor = 32768.0F;
      this.verbose_mode = false;
   }

   public boolean maiden() {
      System.out.println("JavaLayer Converter :");
      System.out.println("  -v[x]         verbose mode. ");
      System.out.println("                default = 2");
      System.out.println("  -p name    output as a PCM wave file");
      System.out.println();
      System.out.println("  More info on http://www.javazoom.net");
      return false;
   }

   public boolean processArgs(String[] strings) {
      this.filename = null;
      int length = strings.length;
      this.verbose_mode = false;
      this.output_mode = 0;
      this.output_filename = "";
      if (length >= 2 && !strings[1].equals("-h")) {
         for (int i = 1; i < length; i++) {
            if (strings[i].charAt(0) == '-') {
               if (strings[i].startsWith("-v")) {
                  this.verbose_mode = true;
                  if (strings[i].length() > 2) {
                     try {
                        this.verbose_level = Integer.parseInt(strings[i].substring(2));
                     } catch (NumberFormatException e) {
                        System.err.println("Invalid verbose level. Using default.");
                     }
                  }

                  System.out.println("Verbose Activated (level " + this.verbose_level + ")");
               } else {
                  if (!strings[i].equals("-p")) {
                     return this.maiden();
                  }

                  if (++i == length) {
                     System.out.println("Please specify an output filename after the -p option!");
                     System.exit(1);
                  }

                  this.output_filename = strings[i];
               }
            } else {
               this.filename = strings[i];
               System.out.println("FileName = " + strings[i]);
               if (this.filename == null) {
                  return this.maiden();
               }
            }
         }

         return this.filename == null ? this.maiden() : true;
      } else {
         return this.maiden();
      }
   }
}
