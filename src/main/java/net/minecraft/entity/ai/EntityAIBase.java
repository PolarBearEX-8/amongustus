package net.minecraft.entity.ai;

public abstract class EntityAIBase {
   private int mutexBits;

   public int getMutexBits() {
      return this.mutexBits;
   }

   public boolean isInterruptible() {
      return true;
   }

   public void setMutexBits(int n) {
      this.mutexBits = n;
   }

   public boolean continueExecuting() {
      return this.shouldExecute();
   }

   public void resetTask() {
   }

   public void updateTask() {
   }

   public abstract boolean shouldExecute();

   public void startExecuting() {
   }
}
