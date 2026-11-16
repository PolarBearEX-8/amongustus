package client.util;

public class EnabledManager {
   public boolean enabled;
   public int count;
   public int count3;
   public int count4;
   public int count2;

   public int getCount() {
      return this.count;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void applyFlag(boolean flag, int count5, int count6, int count7, int count8) {
      this.enabled = flag;
      this.count = count5;
      this.count3 = count6;
      this.count4 = count7;
      this.count2 = count8;
   }

   public void applyIndex(int count5, int count6) {
      this.count = count5;
      this.count3 = count6;
      this.count4 = count5;
      this.count2 = count6;
   }

   public boolean isActive() {
      return this.count != this.count4 || this.count3 != this.count2;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void updateState2() {
      this.enabled = true;
   }

   public int getCount3() {
      return this.count3;
   }

   public void updateState() {
      this.enabled = false;
   }

   public void applyIndex(int count5, int count6, int count7, int count8) {
      this.count = count5;
      this.count3 = count6;
      this.count4 = count7;
      this.count2 = count8;
   }

   public int getCount4() {
      return this.count4;
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyEnabledManager(EnabledManager enabledManager) {
      this.enabled = enabledManager.enabled;
      this.count = enabledManager.count;
      this.count3 = enabledManager.count3;
      this.count4 = enabledManager.count4;
      this.count2 = enabledManager.count2;
   }

   public EnabledManager(boolean flag) {
      this(flag, 1, 0);
   }

   public EnabledManager() {
      this(false, 1, 0);
   }

   public EnabledManager(boolean flag, int count5, int count6) {
      this(flag, count5, count6, count5, count6);
   }

   public EnabledManager(boolean flag, int count5, int count6, int count7, int count8) {
      this.enabled = flag;
      this.count = count5;
      this.count3 = count6;
      this.count4 = count7;
      this.count2 = count8;
   }

   @Override
   public String toString() {
      return "enabled: " + this.enabled + ", src: " + this.count + ", dst: " + this.count3 + ", srcAlpha: " + this.count4 + ", dstAlpha: " + this.count2;
   }
}
