package net.minecraft.nbt;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.Stack;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonToNBT {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Pattern INT_ARRAY_MATCHER = Pattern.compile("\\[[-+\\d|,\\s]+\\]");

   public static NBTTagCompound getTagFromJson(String str) throws NBTException {
      str = str.trim();
      if (!str.startsWith("{")) {
         throw new NBTException("Invalid tag encountered, expected '{' as first char.");
      } else if (getTypeFromTag(str) != 1) {
         throw new NBTException("Encountered multiple top tags, only one expected");
      } else {
         return (NBTTagCompound)nameValueToNBT("tag", str).parse();
      }
   }

   private static String nextNameValuePair(String str, int n) throws NBTException {
      Stack stack = new Stack();
      int n2 = n + 1;
      boolean flag = false;
      boolean flag2 = false;
      boolean flag3 = false;

      for (int i = 0; n2 < str.length(); n2++) {
         char ch = str.charAt(n2);
         if (ch == '"') {
            if (isCharEscaped(str, n2)) {
               if (!flag) {
                  throw new NBTException("Illegal use of \\\": " + str);
               }
            } else {
               flag = !flag;
               if (flag && !flag3) {
                  flag2 = true;
               }

               if (!flag) {
                  i = n2;
               }
            }
         } else if (!flag) {
            if (ch != '{' && ch != '[') {
               if (ch == '}' && (stack.isEmpty() || (Character)stack.pop() != '{')) {
                  throw new NBTException("Unbalanced curly brackets {}: " + str);
               }

               if (ch == ']' && (stack.isEmpty() || (Character)stack.pop() != '[')) {
                  throw new NBTException("Unbalanced square brackets []: " + str);
               }

               if (ch == ',' && stack.isEmpty()) {
                  return str.substring(0, n2);
               }
            } else {
               stack.push(ch);
            }
         }

         if (!Character.isWhitespace(ch)) {
            if (!flag && flag2 && i != n2) {
               return str.substring(0, i + 1);
            }

            flag3 = true;
         }
      }

      return str.substring(0, n2);
   }

   private static boolean isCharEscaped(String str, int n) {
      return n > 0 && str.charAt(n - 1) == '\\' && !isCharEscaped(str, n - 1);
   }

   private static JsonToNBT.Any parseTag(String str, boolean flag) throws NBTException {
      String str2 = topName(str, flag);
      String str3 = topValue(str, flag);
      return joinStrToNBT(new String[]{str2, str3});
   }

   private static int locateChar(String str, char ch) {
      int n = 0;

      for (boolean i = true; n < str.length(); n++) {
         char ch2 = str.charAt(n);
         if (ch2 == '"') {
            if (!isCharEscaped(str, n)) {
               i = !i;
            }
         } else if (i) {
            if (ch2 == ch) {
               return n;
            }

            if (ch2 == '{' || ch2 == '[') {
               return -1;
            }
         }
      }

      return -1;
   }

   public static JsonToNBT.Any joinStrToNBT(String[] strings) throws NBTException {
      return nameValueToNBT(strings[0], strings[1]);
   }

   private static String topName(String str, boolean flag) throws NBTException {
      if (flag) {
         str = str.trim();
         if (str.startsWith("{") || str.startsWith("[")) {
            return "";
         }
      }

      int n = locateChar(str, ':');
      if (n != -1) {
         return str.substring(0, n).trim();
      } else if (flag) {
         return "";
      } else {
         throw new NBTException("Unable to locate name/value separator for string: " + str);
      }
   }

   public static int getTypeFromTag(String str) throws NBTException {
      int n = 0;
      boolean flag = false;
      Stack stack = new Stack();

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (ch == '"') {
            if (isCharEscaped(str, i)) {
               if (!flag) {
                  throw new NBTException("Illegal use of \\\": " + str);
               }
            } else {
               flag = !flag;
            }
         } else if (!flag) {
            if (ch != '{' && ch != '[') {
               if (ch == '}' && (stack.isEmpty() || (Character)stack.pop() != '{')) {
                  throw new NBTException("Unbalanced curly brackets {}: " + str);
               }

               if (ch == ']' && (stack.isEmpty() || (Character)stack.pop() != '[')) {
                  throw new NBTException("Unbalanced square brackets []: " + str);
               }
            } else {
               if (stack.isEmpty()) {
                  n++;
               }

               stack.push(ch);
            }
         }
      }

      if (flag) {
         throw new NBTException("Unbalanced quotation: " + str);
      } else if (!stack.isEmpty()) {
         throw new NBTException("Unbalanced brackets: " + str);
      } else {
         if (n == 0 && !str.isEmpty()) {
            n = 1;
         }

         return n;
      }
   }

   private static String nextElement(String str, boolean flag) throws NBTException {
      int n = locateChar(str, ':');
      int n2 = locateChar(str, ',');
      if (flag) {
         if (n == -1) {
            throw new NBTException("Unable to locate name/value separator for string: " + str);
         }

         if (n2 != -1 && n2 < n) {
            throw new NBTException("Name error at: " + str);
         }
      } else if (n == -1 || n > n2) {
         n = -1;
      }

      return nextNameValuePair(str, n);
   }

   public static JsonToNBT.Any nameValueToNBT(String str, String str2) throws NBTException {
      str2 = str2.trim();
      if (str2.startsWith("{")) {
         str2 = str2.substring(1, str2.length() - 1);
         JsonToNBT.Compound compound = new JsonToNBT.Compound(str);

         while (str2.length() > 0) {
            String str3 = nextElement(str2, true);
            if (str3.length() > 0) {
               compound.tagList.add(parseTag(str3, false));
            }

            if (str2.length() < str3.length() + 1) {
               break;
            }

            char ch = str2.charAt(str3.length());
            if (ch != ',' && ch != '{' && ch != '}' && ch != '[' && ch != ']') {
               throw new NBTException("Unexpected token '" + ch + "' at: " + str2.substring(str3.length()));
            }

            str2 = str2.substring(str3.length() + 1);
         }

         return compound;
      } else if (str2.startsWith("[") && !INT_ARRAY_MATCHER.matcher(str2).matches()) {
         str2 = str2.substring(1, str2.length() - 1);
         JsonToNBT.List list = new JsonToNBT.List(str);

         while (str2.length() > 0) {
            String str4 = nextElement(str2, false);
            if (str4.length() > 0) {
               list.tagList.add(parseTag(str4, true));
            }

            if (str2.length() < str4.length() + 1) {
               break;
            }

            char ch2 = str2.charAt(str4.length());
            if (ch2 != ',' && ch2 != '{' && ch2 != '}' && ch2 != '[' && ch2 != ']') {
               throw new NBTException("Unexpected token '" + ch2 + "' at: " + str2.substring(str4.length()));
            }

            str2 = str2.substring(str4.length() + 1);
         }

         return list;
      } else {
         return new JsonToNBT.Primitive(str, str2);
      }
   }

   private static String topValue(String str, boolean flag) throws NBTException {
      if (flag) {
         str = str.trim();
         if (str.startsWith("{") || str.startsWith("[")) {
            return str;
         }
      }

      int n = locateChar(str, ':');
      if (n != -1) {
         return str.substring(n + 1).trim();
      } else if (flag) {
         return str;
      } else {
         throw new NBTException("Unable to locate name/value separator for string: " + str);
      }
   }


   public static abstract class Any {
      public String json;

      public abstract NBTBase parse() throws NBTException;
   }

   public static class List extends JsonToNBT.Any {
      public java.util.List tagList = Lists.newArrayList();

      @Override
      public NBTBase parse() throws NBTException {
         NBTTagList tagList2 = new NBTTagList();

         for (JsonToNBT.Any any : (Iterable<JsonToNBT.Any>)(this.tagList)) {
            tagList2.appendTag(any.parse());
         }

         return tagList2;
      }

      public List(String str) {
         this.json = str;
      }
   }

   public static class Compound extends JsonToNBT.Any {
      public java.util.List tagList = Lists.newArrayList();

      @Override
      public NBTBase parse() throws NBTException {
         NBTTagCompound tag = new NBTTagCompound();

         for (JsonToNBT.Any any : (Iterable<JsonToNBT.Any>)(this.tagList)) {
            tag.setTag(any.json, any.parse());
         }

         return tag;
      }

      public Compound(String str) {
         this.json = str;
      }
   }

   public static class Primitive extends JsonToNBT.Any {
      private static final Pattern DOUBLE = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");
      private static final Pattern FLOAT = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
      private static final Pattern BYTE = Pattern.compile("[-+]?[0-9]+[b|B]");
      private static final Pattern LONG = Pattern.compile("[-+]?[0-9]+[l|L]");
      private static final Pattern SHORT = Pattern.compile("[-+]?[0-9]+[s|S]");
      private static final Pattern INTEGER = Pattern.compile("[-+]?[0-9]+");
      private static final Pattern DOUBLE_UNTYPED = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
      private static final Splitter SPLITTER = Splitter.on(',').omitEmptyStrings();
      public String jsonValue;

      @Override
      public NBTBase parse() throws NBTException {
         try {
            if (DOUBLE.matcher(this.jsonValue).matches()) {
               return new NBTTagDouble(Double.parseDouble(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
            }

            if (FLOAT.matcher(this.jsonValue).matches()) {
               return new NBTTagFloat(Float.parseFloat(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
            }

            if (BYTE.matcher(this.jsonValue).matches()) {
               return new NBTTagByte(Byte.parseByte(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
            }

            if (LONG.matcher(this.jsonValue).matches()) {
               return new NBTTagLong(Long.parseLong(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
            }

            if (SHORT.matcher(this.jsonValue).matches()) {
               return new NBTTagShort(Short.parseShort(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
            }

            if (INTEGER.matcher(this.jsonValue).matches()) {
               return new NBTTagInt(Integer.parseInt(this.jsonValue));
            }

            if (DOUBLE_UNTYPED.matcher(this.jsonValue).matches()) {
               return new NBTTagDouble(Double.parseDouble(this.jsonValue));
            }

            if (this.jsonValue.equalsIgnoreCase("true") || this.jsonValue.equalsIgnoreCase("false")) {
               return new NBTTagByte((byte)(Boolean.parseBoolean(this.jsonValue) ? 1 : 0));
            }
         } catch (NumberFormatException e) {
            this.jsonValue = this.jsonValue.replaceAll("\\\\\"", "\"");
            return new NBTTagString(this.jsonValue);
         }

         if (this.jsonValue.startsWith("[") && this.jsonValue.endsWith("]")) {
            String str = this.jsonValue.substring(1, this.jsonValue.length() - 1);
            String[] strings = (String[])Iterables.toArray(SPLITTER.split(str), String.class);

            try {
               int[] ints = new int[strings.length];

               for (int i = 0; i < strings.length; i++) {
                  ints[i] = Integer.parseInt(strings[i].trim());
               }

               return new NBTTagIntArray(ints);
            } catch (NumberFormatException e2) {
               return new NBTTagString(this.jsonValue);
            }
         } else {
            if (this.jsonValue.startsWith("\"") && this.jsonValue.endsWith("\"")) {
               this.jsonValue = this.jsonValue.substring(1, this.jsonValue.length() - 1);
            }

            this.jsonValue = this.jsonValue.replaceAll("\\\\\"", "\"");
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < this.jsonValue.length(); j++) {
               if (j < this.jsonValue.length() - 1 && this.jsonValue.charAt(j) == '\\' && this.jsonValue.charAt(j + 1) == '\\') {
                  sb.append('\\');
                  j++;
               } else {
                  sb.append(this.jsonValue.charAt(j));
               }
            }

            return new NBTTagString(sb.toString());
         }
      }

      public Primitive(String str, String str2) {
         this.json = str;
         this.jsonValue = str2;
      }
   }
}
