package client.util;

public class FovEngine {
   public String labelText;
   public boolean enabled;
   public BoxesManager boxesManager;
   public RotationChecker rotationChecker;

   public RotationChecker getRotationChecker() {
      return this.rotationChecker;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public FovEngine(String text, boolean flag, BoxesManager boxesManager2, RotationChecker rotationChecker2) {
      this.labelText = text;
      this.enabled = flag;
      this.boxesManager = boxesManager2;
      this.rotationChecker = rotationChecker2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public BoxesManager getBoxesManager() {
      return this.boxesManager;
   }
}
