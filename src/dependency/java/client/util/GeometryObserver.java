package client.util;

import java.util.Arrays;
import java.util.regex.Pattern;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.commons.lang3.StringEscapeUtils;

public class GeometryObserver {
   public String[] stringArray = null;
   public String labelText = null;
   public boolean enabled = false;
   public int count = 0;
   public String labelText2 = null;
   public int count2 = 0;
   public static final int count3 = 0;
   public static final int count4 = 1;
   public static final int count5 = 2;
   public static final int count6 = 3;
   public static final int count7 = 4;
   public static final String labelText3 = "pattern:";
   public static final String labelText4 = "ipattern:";
   public static final String labelText5 = "regex:";
   public static final String labelText6 = "iregex:";
   public static final int count8 = 0;
   public static final int count9 = 1;
   public static final String labelText7 = "#";
   public static final Pattern pattern = Pattern.compile("^#[0-9a-f]{6}+$");

   private boolean matchesLISTManagerByLISTManager(LISTManager lISTManager) {
      if (lISTManager instanceof NBTTagCompound) {
         NBTTagCompound tagCompound = (NBTTagCompound)lISTManager;

         for (String text : (Iterable<String>)(Iterable<?>)tagCompound.keySet()) {
            LISTManager lISTManager2 = tagCompound.get(text);
            if (this.matchesLISTManager(lISTManager2)) {
               return true;
            }
         }
      }

      if (lISTManager instanceof NBTTagList) {
         NBTTagList nBTTagList = (NBTTagList)lISTManager;
         int intItem = nBTTagList.getInt();

         for (int index = 0; index < intItem; index++) {
            LISTManager lISTManager3 = nBTTagList.resolveLISTManager(index);
            if (this.matchesLISTManager(lISTManager3)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean matches(String text, String text2) {
      return text.matches(text2);
   }

   public boolean matchesNBTTagCompound(NBTTagCompound tagCompound) {
      return this.enabled ? !this.matchesNBTTagCompoundByNBTTagCompound(tagCompound) : this.matchesNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   public boolean matchesLISTManager(LISTManager lISTManager) {
      if (lISTManager == null) {
         return false;
      } else {
         String text = resolveText(lISTManager, this.count2);
         return this.matchesText(text);
      }
   }

   private boolean matchesText(String text, String text2) {
      return SplineCollector.matchesText(text, text2, '*', '?');
   }

   public boolean matchesText(String text) {
      if (text == null) {
         return false;
      } else {
         switch (this.count) {
            case 0:
               return text.equals(this.labelText2);
            case 1:
               return this.matchesText(text, this.labelText2);
            case 2:
               return this.matchesText(text.toLowerCase(), this.labelText2);
            case 3:
               return this.matches(text, this.labelText2);
            case 4:
               return this.matches(text.toLowerCase(), this.labelText2);
            default:
               throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.count);
         }
      }
   }

   public GeometryObserver(String text, String text2) {
      String[] textArray = BuildManager.resolveTextArray(text, ".");
      this.stringArray = Arrays.copyOfRange(textArray, 0, textArray.length - 1);
      this.labelText = textArray[textArray.length - 1];
      if (text2.startsWith("!")) {
         this.enabled = true;
         text2 = text2.substring(1);
      }

      if (text2.startsWith("pattern:")) {
         this.count = 1;
         text2 = text2.substring("pattern:".length());
      } else if (text2.startsWith("ipattern:")) {
         this.count = 2;
         text2 = text2.substring("ipattern:".length()).toLowerCase();
      } else if (text2.startsWith("regex:")) {
         this.count = 3;
         text2 = text2.substring("regex:".length());
      } else if (text2.startsWith("iregex:")) {
         this.count = 4;
         text2 = text2.substring("iregex:".length()).toLowerCase();
      } else {
         this.count = 0;
      }

      text2 = StringEscapeUtils.unescapeJava(text2);
      if (this.count == 0 && pattern.matcher(text2).matches()) {
         this.count2 = 1;
      }

      this.labelText2 = text2;
   }

   private static LISTManager resolveLISTManager(LISTManager lISTManager, String text) {
      if (lISTManager instanceof NBTTagCompound) {
         return ((NBTTagCompound)lISTManager).get(text);
      } else if (lISTManager instanceof NBTTagList) {
         NBTTagList nBTTagList = (NBTTagList)lISTManager;
         if (text.equals("count")) {
            return new InterpolationMatcher(nBTTagList.getInt());
         } else {
            int count10 = BuildManager.computeCount(text, -1);
            return count10 >= 0 && count10 < nBTTagList.getInt() ? nBTTagList.resolveLISTManager(count10) : null;
         }
      } else {
         return null;
      }
   }

   @Override
   public String toString() {
      StringBuffer builder = new StringBuffer();

      for (int index = 0; index < this.stringArray.length; index++) {
         String text = this.stringArray[index];
         if (index > 0) {
            builder.append(".");
         }

         builder.append(text);
      }

      if (builder.length() > 0) {
         builder.append(".");
      }

      builder.append(this.labelText);
      builder.append(" = ");
      builder.append(this.labelText2);
      return builder.toString();
   }

   public boolean matchesNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound == null) {
         return false;
      } else {
         Object entry = tagCompound;

         for (int index = 0; index < this.stringArray.length; index++) {
            String text = this.stringArray[index];
            entry = resolveLISTManager((LISTManager)entry, text);
            if (entry == null) {
               return false;
            }
         }

         if (this.labelText.equals("*")) {
            return this.matchesLISTManagerByLISTManager((LISTManager)entry);
         } else {
            LISTManager lISTManager = resolveLISTManager((LISTManager)entry, this.labelText);
            return lISTManager == null ? false : this.matchesLISTManager(lISTManager);
         }
      }
   }

   private static String resolveText(LISTManager lISTManager, int count10) {
      if (lISTManager == null) {
         return null;
      } else if (lISTManager instanceof HandlerStringEntry) {
         return ((HandlerStringEntry)lISTManager).getLabelText();
      } else if (lISTManager instanceof InterpolationMatcher) {
         InterpolationMatcher interpolationMatcher = (InterpolationMatcher)lISTManager;
         return count10 == 1 ? "#" + SplineCollector.resolveText(Integer.toHexString(interpolationMatcher.getCount()), 6, '0') : Integer.toString(interpolationMatcher.getCount());
      } else if (lISTManager instanceof AuxiliaryClientManagerRegistry) {
         return Byte.toString(((AuxiliaryClientManagerRegistry)lISTManager).getByteFlag());
      } else if (lISTManager instanceof ClientManagerController) {
         return Short.toString(((ClientManagerController)lISTManager).getShortCode());
      } else if (lISTManager instanceof ExtendedClientManagerHandler) {
         return Long.toString(((ExtendedClientManagerHandler)lISTManager).floor());
      } else if (lISTManager instanceof InternalClientManagerRegistry) {
         return Float.toString(((InternalClientManagerRegistry)lISTManager).getFactor());
      } else {
         return lISTManager instanceof PrimaryDoubleEntry ? Double.toString(((PrimaryDoubleEntry)lISTManager).getValue()) : lISTManager.toString();
      }
   }
}
