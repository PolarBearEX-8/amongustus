package client.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontRegistry {
   public static CustomFont fontFaceAdapter7;
   public static CustomFont fontFaceAdapter3;
   public static FontFace fontFace;
   public static CustomFont fontFaceAdapter8;
   public static FontFace fontFace11;
   public static FontFace fontFace4;
   public static FontFace fontFace3;
   public static FontFace fontFace9;
   public static CustomFont fontFaceAdapter5;
   public static FontFace fontFace7;
   public static CustomFont fontFaceAdapter4;
   public static CustomFont fontFaceAdapter10;
   public static CustomFont fontFaceAdapter13;
   public static CustomFont fontFaceAdapter9;
   public static CustomFont fontFaceAdapter2;
   public static FontFace fontFace6;
   public static FontFace fontFace8;
   public static FontFace fontFace2;
   public static CustomFont fontFaceAdapter;
   public static FontFace fontFace10;
   public static CustomFont fontFaceAdapter12;
   public static CustomFont fontFaceAdapter11;
   public static FontFace fontFace5;
   public static CustomFont fontFaceAdapter14;
   public static CustomFont fontFaceAdapter1;
   public static FontFace fontFace12;
   public static CustomFont fontFaceAdapter6;
   public static FontFace fontFace13;

   public static Font resolveFont(String text) throws IOException, FontFormatException {
      InputStream resourceAsStream = FontRegistry.class.getClassLoader().getResourceAsStream("assets/augustus/fonts/" + text);
      Font font = Font.createFont(0, resourceAsStream);
      resourceAsStream.close();
      return font;
   }

   public static void updateState() {
      long value = 0x698369264b39L;
      value = 0x5ba4ecf6d927L ^ value;
      fontFace = new FontFace("Esp", resolveFont("esp.ttf", 44));
      fontFace12 = new FontFace("Esp", resolveFont("esp.ttf", 16));
      fontFaceAdapter12 = new CustomFont("verdanaArrayList", "verdana.ttf", 32, 1);
      fontFaceAdapter14 = new CustomFont("robotoArrayList", "roboto.ttf", 32, 1);
      fontFaceAdapter11 = new CustomFont("arialArrayList", "arial.ttf", 32, 1);
      fontFaceAdapter7 = new CustomFont("comfortaaArrayList", "comfortaa.ttf", 32, 1);
      fontFaceAdapter10 = new CustomFont("espArrayList", "esp.ttf", 32, 1);
      fontFaceAdapter8 = new CustomFont("goldBilArrayList", "goldbill-xsextrabold.ttf", 32, 1);
      fontFaceAdapter6 = new CustomFont("jelloArrayList", "jello.ttf", 32, 1);
      fontFaceAdapter1 = new CustomFont("productSansArrayList", "productsans.ttf", 32, 1);
      fontFaceAdapter13 = new CustomFont("museosansarraylist", "museosans.ttf", 32, 1);
      fontFaceAdapter4 = new CustomFont("borelarraylist", "borel.ttf", 32, 1);
      fontFaceAdapter2 = new CustomFont("rubikarraylist", "rubik.ttf", 32, 1);
      fontFaceAdapter9 = new CustomFont("rubikboldarraylist", "rubik-bold.ttf", 32, 1);
      fontFaceAdapter5 = new CustomFont("tahomaarraylist", "tahoma.ttf", 32, 1);
      fontFaceAdapter = new CustomFont("tahomaboldarraylist", "tahoma-bold.ttf", 32, 1);
      fontFaceAdapter3 = new CustomFont("rubikboldhud", "rubik-bold.ttf", 32, 1);
      fontFace13 = new FontFace("Esp", resolveFont("esp.ttf", 60));
      fontFace2 = new FontFace("Consolas30", resolveFont("consolas.ttf", 30), 1);
      fontFace4 = new FontFace("Consolas301", resolveFont("consolas.ttf", 30), 1);
      fontFace6 = new FontFace("Consolas301", resolveFont("consolas.ttf", 30), 1);
      fontFace5 = new FontFace("Consolas301", resolveFont("consolas.ttf", 30), 1);
      fontFace3 = new FontFace("Consolas301", resolveFont("consolas.ttf", 30), 1);
      fontFace7 = new FontFace("Consolas301", resolveFont("consolas.ttf", 30), 1);
      fontFace9 = new FontFace("Consolas301", resolveFont("consolas.ttf", 30), 1);
      fontFace11 = new FontFace("Consolas32", resolveFont("consolas.ttf", 32), 1);
      fontFace10 = new FontFace("Esp", resolveFont("esp.ttf", 32), 1);
      fontFace8 = new FontFace("RubikBold32", resolveFont("rubik-bold.ttf", 32), 1);
   }

   public static Font resolveFont(Font font, int count) {
      try {
         font = font.deriveFont(0, (float)count);
      } catch (Exception exception) {
         exception.printStackTrace();
         System.err.println("Error loading font");
         font = new Font("default", 0, 10);
      }

      return font;
   }

   public static Font resolveFont(String text, int count) {
      long value = 0x330366fb4518L;
      value = 0x5ba4ecf6d927L ^ value;

      try {
         return resolveFont(resolveFont(text), count);
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }
   }
}
