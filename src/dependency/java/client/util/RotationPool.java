package client.util;

public abstract class RotationPool {
   public int count;

   public boolean isActive2() {
      return true;
   }

   public int getCount() {
      return this.count;
   }

   public void updateState() {
   }

   public void updateState3() {
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public boolean isActiveAsFlag() {
      return this.isActive();
   }

   public void updateState2() {
   }

   public abstract boolean isActive();
}
