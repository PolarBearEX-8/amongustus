package net.optifine.config;

import java.util.Arrays;
import java.util.regex.Pattern;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class NbtTagValue {
   private String[] parents = null;
   private String name = null;
   private boolean negative = false;
   private int type = 0;
   private String value = null;
   private int valueFormat = 0;
   private static final int TYPE_TEXT = 0;
   private static final int TYPE_PATTERN = 1;
   private static final int TYPE_IPATTERN = 2;
   private static final int TYPE_REGEX = 3;
   private static final int TYPE_IREGEX = 4;
   private static final String PREFIX_PATTERN = null;
   private static final String PREFIX_IPATTERN = null;
   private static final String PREFIX_REGEX = null;
   private static final String PREFIX_IREGEX = null;
   private static final int FORMAT_DEFAULT = 0;
   private static final int FORMAT_HEX_COLOR = 1;
   private static final String PREFIX_HEX_COLOR = "#";
   private static final Pattern PATTERN_HEX_COLOR = Pattern.compile("^#[0-9a-f]{6}+$");

   public boolean matchesCompound(NBTTagCompound tag) {
      if (tag == null) {
         return false;
      } else {
         Object obj = tag;

         for (int i = 0; i < this.parents.length; i++) {
            String str = this.parents[i];
            obj = getChildTag((NBTBase)obj, str);
            if (obj == null) {
               return false;
            }
         }

         if (this.name.equals("*")) {
            return this.matchesAnyChild((NBTBase)obj);
         } else {
            obj = getChildTag((NBTBase)obj, this.name);
            return obj == null ? false : this.matchesBase((NBTBase)obj);
         }
      }
   }

   public boolean matches(NBTTagCompound tag) {
      return this.negative ? !this.matchesCompound(tag) : this.matchesCompound(tag);
   }

   private boolean matchesRegex(String str, String str2) {
      return str.matches(str2);
   }

   private static String getNbtString(NBTBase nbt, int n) {
      if (nbt == null) {
         return null;
      } else if (nbt instanceof NBTTagString) {
         return ((NBTTagString)nbt).getString();
      } else if (nbt instanceof NBTTagInt) {
         NBTTagInt nbtTagInt = (NBTTagInt)nbt;
         return n == 1 ? "#" + StrUtils.fillLeft(Integer.toHexString(nbtTagInt.getInt()), 6, '0') : Integer.toString(nbtTagInt.getInt());
      } else if (nbt instanceof NBTTagByte) {
         return Byte.toString(((NBTTagByte)nbt).getByte());
      } else if (nbt instanceof NBTTagShort) {
         return Short.toString(((NBTTagShort)nbt).getShort());
      } else if (nbt instanceof NBTTagLong) {
         return Long.toString(((NBTTagLong)nbt).getLong());
      } else if (nbt instanceof NBTTagFloat) {
         return Float.toString(((NBTTagFloat)nbt).getFloat());
      } else if (nbt instanceof NBTTagDouble) {
         return Double.toString(((NBTTagDouble)nbt).getDouble());
      } else {
         return nbt.toString();
      }
   }

   public boolean matchesBase(NBTBase nbt) {
      if (nbt == null) {
         return false;
      } else {
         return this.matchesValue(getNbtString(nbt, this.valueFormat));
      }
   }

   public NbtTagValue(String str, String str2) {
      String[] strings = Config.tokenize(str, ".");
      this.parents = Arrays.copyOfRange(strings, 0, strings.length - 1);
      this.name = strings[strings.length - 1];
      if (str2.startsWith("!")) {
         this.negative = true;
         str2 = str2.substring(1);
      }

      if (str2.startsWith("pattern:")) {
         this.type = 1;
         str2 = str2.substring("pattern:".length());
      } else if (str2.startsWith("ipattern:")) {
         this.type = 2;
         str2 = str2.substring("ipattern:".length()).toLowerCase();
      } else if (str2.startsWith("regex:")) {
         this.type = 3;
         str2 = str2.substring("regex:".length());
      } else if (str2.startsWith("iregex:")) {
         this.type = 4;
         str2 = str2.substring("iregex:".length()).toLowerCase();
      } else {
         this.type = 0;
      }

      str2 = StringEscapeUtils.unescapeJava(str2);
      if (this.type == 0 && PATTERN_HEX_COLOR.matcher(str2).matches()) {
         this.valueFormat = 1;
      }

      this.value = str2;
   }

   private static NBTBase getChildTag(NBTBase nbt, String str) {
      if (nbt instanceof NBTTagCompound) {
         return ((NBTTagCompound)nbt).getTag(str);
      } else if (nbt instanceof NBTTagList) {
         NBTTagList tagList = (NBTTagList)nbt;
         if (str.equals("count")) {
            return new NBTTagInt(tagList.tagCount());
         } else {
            int n = Config.parseInt(str, -1);
            return n >= 0 && n < tagList.tagCount() ? tagList.get(n) : null;
         }
      } else {
         return null;
      }
   }

   private boolean matchesPattern(String str, String str2) {
      return StrUtils.equalsMask(str, str2, '*', '?');
   }

   private boolean matchesAnyChild(NBTBase nbt) {
      if (nbt instanceof NBTTagCompound) {
         NBTTagCompound tag = (NBTTagCompound)nbt;

         for (String str : (Iterable<String>)(tag.getKeySet())) {
            if (this.matchesBase(tag.getTag(str))) {
               return true;
            }
         }
      }

      if (nbt instanceof NBTTagList) {
         NBTTagList tagList = (NBTTagList)nbt;
         int n = tagList.tagCount();

         for (int i = 0; i < n; i++) {
            if (this.matchesBase(tagList.get(i))) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean matchesValue(String str) {
      if (str == null) {
         return false;
      } else {
         switch (this.type) {
            case 0:
               return str.equals(this.value);
            case 1:
               return this.matchesPattern(str, this.value);
            case 2:
               return this.matchesPattern(str.toLowerCase(), this.value);
            case 3:
               return this.matchesRegex(str, this.value);
            case 4:
               return this.matchesRegex(str.toLowerCase(), this.value);
            default:
               throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
         }
      }
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer();

      for (int i = 0; i < this.parents.length; i++) {
         String str = this.parents[i];
         if (i > 0) {
            sb.append(".");
         }

         sb.append(str);
      }

      if (sb.length() > 0) {
         sb.append(".");
      }

      sb.append(this.name);
      sb.append(" = ");
      sb.append(this.value);
      return sb.toString();
   }
}
