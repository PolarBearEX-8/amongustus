package cc.baritone.utils;

public class StringPair extends CORERECOMMENDEDManager {
   public StringEntry stringEntry;

   @Override
   public String getText() {
      return this.stringEntry.getTextAsText();
   }

   public StringPair(StringEntry stringEntry2) {
      super(stringEntry2.labelText);
   }
}
