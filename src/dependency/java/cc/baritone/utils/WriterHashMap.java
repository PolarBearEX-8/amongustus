package cc.baritone.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WriterHashMap extends HashMap implements Callback, Map, WriterCallback {
   public static final long serialVersionUID = -0x6fc977743e77bbcL;

   public static void applyMap(Map map, Writer writer) throws IOException {
      if (map == null) {
         writer.write("null");
      } else {
         boolean flag = true;
         Iterator iterator = map.entrySet().iterator();
         writer.write(123);

         while (iterator.hasNext()) {
            if (flag) {
               flag = false;
            } else {
               writer.write(44);
            }

            Entry entry = (Entry)iterator.next();
            writer.write(34);
            writer.write(resolveText(String.valueOf(entry.getKey())));
            writer.write(34);
            writer.write(58);
            WriterHelper.applyTarget(entry.getValue(), writer);
         }

         writer.write(125);
      }
   }

   @Override
   public String toString() {
      return this.getText();
   }

   public WriterHashMap() {
   }

   public static String resolveText(String text) {
      return WriterHelper.resolveText(text);
   }

   @Override
   public void applyWriter(Writer writer) throws IOException {
      applyMap(this, writer);
   }

   public WriterHashMap(Map map) {
      super(map);
   }

   @Override
   public String getText() {
      return resolveText(this);
   }

   public static String resolveText(String text, Object entry) {
      StringBuffer builder = new StringBuffer();
      builder.append('"');
      if (text == null) {
         builder.append("null");
      } else {
         WriterHelper.applyText(text, builder);
      }

      builder.append('"').append(':');
      builder.append(WriterHelper.resolveText(entry));
      return builder.toString();
   }

   public static String resolveText(Map map) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyMap(map, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }
}
