package cc.baritone.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

public class WriterHelper {
   public static void applyTarget(Object entry, Writer writer) throws IOException {
      if (entry == null) {
         writer.write("null");
      } else if (entry instanceof String) {
         writer.write(34);
         writer.write(resolveText((String)entry));
         writer.write(34);
      } else if (entry instanceof Double) {
         if (!((Double)entry).isInfinite() && !((Double)entry).isNaN()) {
            writer.write(entry.toString());
         } else {
            writer.write("null");
         }
      } else if (!(entry instanceof Float)) {
         if (entry instanceof Number) {
            writer.write(entry.toString());
         } else if (entry instanceof Boolean) {
            writer.write(entry.toString());
         } else if (entry instanceof WriterCallback) {
            ((WriterCallback)entry).applyWriter(writer);
         } else if (entry instanceof Callback) {
            writer.write(((Callback)entry).getText());
         } else if (entry instanceof Map) {
            WriterHashMap.applyMap((Map)entry, writer);
         } else if (entry instanceof Collection) {
            WriterArrayList.applyCollection((Collection)entry, writer);
         } else if (entry instanceof byte[]) {
            WriterArrayList.applyByteArray((byte[])entry, writer);
         } else if (entry instanceof short[]) {
            WriterArrayList.applyShortArray((short[])entry, writer);
         } else if (entry instanceof int[]) {
            WriterArrayList.applyIndexArray((int[])entry, writer);
         } else if (entry instanceof long[]) {
            WriterArrayList.applyTimeArray((long[])entry, writer);
         } else if (entry instanceof float[]) {
            WriterArrayList.applyFloatArray((float[])entry, writer);
         } else if (entry instanceof double[]) {
            WriterArrayList.applyDoubleArray((double[])entry, writer);
         } else if (entry instanceof boolean[]) {
            WriterArrayList.applyFlagArray((boolean[])entry, writer);
         } else if (entry instanceof char[]) {
            WriterArrayList.applyCharArray((char[])entry, writer);
         } else if (entry instanceof Object[]) {
            WriterArrayList.applyTargetArray((Object[])entry, writer);
         } else {
            writer.write(entry.toString());
         }
      } else {
         if (!((Float)entry).isInfinite() && !((Float)entry).isNaN()) {
            writer.write(entry.toString());
         } else {
            writer.write("null");
         }
      }
   }

   public static Object resolveTarget(Reader reader) throws IOException, UnexpectedCharacterException {
      return new LinkedListController().resolveTarget(reader);
   }

   /** @deprecated */
   public static Object resolveTarget(String text) {
      return resolveTargetByReader(new StringReader(text));
   }

   /** @deprecated */
   public static Object resolveTargetByReader(Reader reader) {
      try {
         return new LinkedListController().resolveTarget(reader);
      } catch (Exception exception) {
         return null;
      }
   }

   public static Object resolveTargetByText(String text) throws UnexpectedCharacterException {
      return new LinkedListController().resolveTarget(text);
   }

   public static void applyText(String text, StringBuffer builder) {
      int length = text.length();

      for (int index = 0; index < length; index++) {
         char character = text.charAt(index);
         switch (character) {
            case '\b':
               builder.append("\\b");
               continue;
            case '\t':
               builder.append("\\t");
               continue;
            case '\n':
               builder.append("\\n");
               continue;
            case '\f':
               builder.append("\\f");
               continue;
            case '\r':
               builder.append("\\r");
               continue;
            case '"':
               builder.append("\\\"");
               continue;
            case '/':
               builder.append("\\/");
               continue;
            case '\\':
               builder.append("\\\\");
               continue;
         }

         if (character >= 0 && character <= 31 || character >= 127 && character <= 159 || character >= 8192 && character <= 8447) {
            String text2 = Integer.toHexString(character);
            builder.append("\\u");

            for (int index2 = 0; index2 < 4 - text2.length(); index2++) {
               builder.append('0');
            }

            builder.append(text2.toUpperCase());
         } else {
            builder.append(character);
         }
      }
   }

   public static String resolveText(Object entry) {
      StringWriter stringWriter = new StringWriter();

      try {
         applyTarget(entry, stringWriter);
         return stringWriter.toString();
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }
   }

   public static String resolveText(String text) {
      if (text == null) {
         return null;
      } else {
         StringBuffer builder = new StringBuffer();
         applyText(text, builder);
         return builder.toString();
      }
   }
}
