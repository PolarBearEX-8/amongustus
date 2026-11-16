package cc.baritone.utils;

public final class HeuristicInspector extends Service12Impl {
   public final String labelText;

   public HeuristicInspector(String text) {
      this.labelText = text;
   }

   public final String getLabelText() {
      return this.labelText;
   }
}
