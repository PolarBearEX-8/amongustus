package org.json.simple;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

public class JSONValue {

   /** @deprecated */
   public static Object parse(String str) {
      return parse2(new StringReader(str));
   }

   public static Object parseWithException2(Reader reader) throws IOException, ParseException {
      return new JSONParser().parse(reader);
   }

   /** @deprecated */
   public static Object parse2(Reader reader) {
      try {
         return new JSONParser().parse(reader);
      } catch (Exception e) {
         return null;
      }
   }

   public static void writeJSONString(Object obj, Writer writer) throws IOException {
      if (obj == null) {
         writer.write("null");
      } else if (obj instanceof String) {
         writer.write(34);
         writer.write(escape((String)obj));
         writer.write(34);
      } else if (obj instanceof Double) {
         if (!((Double)obj).isInfinite() && !((Double)obj).isNaN()) {
            writer.write(obj.toString());
         } else {
            writer.write("null");
         }
      } else if (!(obj instanceof Float)) {
         if (obj instanceof Number) {
            writer.write(obj.toString());
         } else if (obj instanceof Boolean) {
            writer.write(obj.toString());
         } else if (obj instanceof JSONStreamAware) {
            ((JSONStreamAware)obj).writeJSONString(writer);
         } else if (obj instanceof JSONAware) {
            writer.write(((JSONAware)obj).toJSONString());
         } else if (obj instanceof Map) {
            JSONObject.writeJSONString((Map)obj, writer);
         } else if (obj instanceof Collection) {
            JSONArray.writeJSONString9((Collection)obj, writer);
         } else if (obj instanceof byte[]) {
            JSONArray.writeJSONString10((byte[])obj, writer);
         } else if (obj instanceof short[]) {
            JSONArray.writeJSONString8((short[])obj, writer);
         } else if (obj instanceof int[]) {
            JSONArray.writeJSONString4((int[])obj, writer);
         } else if (obj instanceof long[]) {
            JSONArray.writeJSONString5((long[])obj, writer);
         } else if (obj instanceof float[]) {
            JSONArray.writeJSONString2((float[])obj, writer);
         } else if (obj instanceof double[]) {
            JSONArray.writeJSONString3((double[])obj, writer);
         } else if (obj instanceof boolean[]) {
            JSONArray.writeJSONString((boolean[])obj, writer);
         } else if (obj instanceof char[]) {
            JSONArray.writeJSONString7((char[])obj, writer);
         } else if (obj instanceof Object[]) {
            JSONArray.writeJSONString6((Object[])obj, writer);
         } else {
            writer.write(obj.toString());
         }
      } else {
         if (!((Float)obj).isInfinite() && !((Float)obj).isNaN()) {
            writer.write(obj.toString());
         } else {
            writer.write("null");
         }
      }
   }

   public static String toJSONString(Object obj) {
      StringWriter stringWriter = new StringWriter();

      try {
         writeJSONString(obj, stringWriter);
         return stringWriter.toString();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static Object parseWithException(String str) throws ParseException {
      return new JSONParser().parse2(str);
   }

   public static void escape(String str, StringBuffer sb) {
      int length = str.length();

      for (int i = 0; i < length; i++) {
         char ch = str.charAt(i);
         switch (ch) {
            case '\b':
               sb.append("\\b");
               continue;
            case '\t':
               sb.append("\\t");
               continue;
            case '\n':
               sb.append("\\n");
               continue;
            case '\f':
               sb.append("\\f");
               continue;
            case '\r':
               sb.append("\\r");
               continue;
            case '"':
               sb.append("\\\"");
               continue;
            case '/':
               sb.append("\\/");
               continue;
            case '\\':
               sb.append("\\\\");
               continue;
         }

         if (ch >= 0 && ch <= 31 || ch >= 127 && ch <= 159 || ch >= 8192 && ch <= 8447) {
            String str2 = Integer.toHexString(ch);
            sb.append("\\u");

            for (int j = 0; j < 4 - str2.length(); j++) {
               sb.append('0');
            }

            sb.append(str2.toUpperCase());
         } else {
            sb.append(ch);
         }
      }
   }

   public static String escape(String str) {
      if (str == null) {
         return null;
      } else {
         StringBuffer sb = new StringBuffer();
         escape(str, sb);
         return sb.toString();
      }
   }
}
