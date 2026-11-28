package net.augustus.settings;

import java.util.HashSet;
import java.util.Set;

public enum StringInputMode {
   NUMERIC("1234567890.-", true, false),
   DEFAULT("", false, false),
   HIDDEN("", false, true);

   public final boolean numeric;
public final boolean hidden;
public final Set allowedChars = new HashSet();

   private StringInputMode(String str, boolean flag, boolean flag2) {
      for (int i = 0; i < str.length(); i++) {
         this.allowedChars.add(str.charAt(i));
      }

      this.numeric = flag;
      this.hidden = flag2;
   }

   public boolean isCharAllowed(char ch) {
      return !this.allowedChars.isEmpty() ? this.allowedChars.contains(ch) : !Character.isISOControl(ch) && Character.isDefined(ch);
   }


}
