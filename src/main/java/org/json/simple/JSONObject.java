package org.json.simple;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class JSONObject extends HashMap implements JSONAware, JSONStreamAware, Map {
   private static final long serialVersionUID = -503443796854799292L;

   @Override
   public String toString() {
      return this.toJSONString();
   }

   public static String toString(String str, Object obj) {
      StringBuffer sb = new StringBuffer();
      sb.append('"');
      if (str == null) {
         sb.append("null");
      } else {
         JSONValue.escape(str, sb);
      }

      sb.append('"').append(':');
      sb.append(JSONValue.toJSONString(obj));
      return sb.toString();
   }

   public static String escape(String str) {
      return JSONValue.escape(str);
   }

   @Override
   public void writeJSONString(Writer writer) throws IOException {
      writeJSONString(this, writer);
   }

   public JSONObject() {
   }

   public static String toJSONString(Map map) {
      StringWriter stringWriter = new StringWriter();

      try {
         writeJSONString(map, stringWriter);
         return stringWriter.toString();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public String toJSONString() {
      return toJSONString(this);
   }

   public JSONObject(Map map) {
      super(map);
   }

   public static void writeJSONString(Map map, Writer writer) throws IOException {
      if (map == null) {
         writer.write("null");
      } else {
         boolean flag = true;
         Iterator it = map.entrySet().iterator();
         writer.write(123);

         while (it.hasNext()) {
            if (flag) {
               flag = false;
            } else {
               writer.write(44);
            }

            Entry entry = (Entry)it.next();
            writer.write(34);
            writer.write(escape(String.valueOf(entry.getKey())));
            writer.write(34);
            writer.write(58);
            JSONValue.writeJSONString(entry.getValue(), writer);
         }

         writer.write(125);
      }
   }
}
