package client.util;

import java.util.Objects;

public class PrimaryStringEntry {
   public String labelText;
   public Boolean memberRef;

   public PrimaryStringEntry(String text, Boolean flag) {
      this.labelText = text;
      this.memberRef = flag;
   }

   @Override
   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof PrimaryStringEntry)) {
         return false;
      } else {
         PrimaryStringEntry primaryStringEntry = (PrimaryStringEntry)other;
         return primaryStringEntry.memberRef == this.memberRef && primaryStringEntry.labelText.equals(this.labelText);
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.labelText, this.memberRef);
   }
}
