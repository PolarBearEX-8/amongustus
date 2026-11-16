package client.util;

import net.minecraft.nbt.NBTTagCompound;

public class InvulnerableManager {
   public boolean enabled;
   public boolean state;
   public boolean state2;
   public boolean enabled2;
   public boolean state1 = true;
   public float factor = 0.05F;
   public float factor2 = 0.1F;

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      tagCompound2.applyText("invulnerable", this.enabled);
      tagCompound2.applyText("flying", this.state);
      tagCompound2.applyText("mayfly", this.state2);
      tagCompound2.applyText("instabuild", this.enabled2);
      tagCompound2.applyText("mayBuild", this.state1);
      tagCompound2.put("flySpeed", this.factor);
      tagCompound2.put("walkSpeed", this.factor2);
      tagCompound.applyText("abilities", tagCompound2);
   }

   public float getFactor() {
      return this.factor;
   }

   public void setFactor(float factor3) {
      this.factor = factor3;
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound.matchesText("abilities", 10)) {
         NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("abilities");
         this.enabled = nBTTagCompound.matchesText("invulnerable");
         this.state = nBTTagCompound.matchesText("flying");
         this.state2 = nBTTagCompound.matchesText("mayfly");
         this.enabled2 = nBTTagCompound.matchesText("instabuild");
         if (nBTTagCompound.matchesText("flySpeed", 99)) {
            this.factor = nBTTagCompound.computeFactor("flySpeed");
            this.factor2 = nBTTagCompound.computeFactor("walkSpeed");
         }

         if (nBTTagCompound.matchesText("mayBuild", 1)) {
            this.state1 = nBTTagCompound.matchesText("mayBuild");
         }
      }
   }

   public void setFactor2(float factor3) {
      this.factor2 = factor3;
   }

   public float getFactor2() {
      return this.factor2;
   }
}
