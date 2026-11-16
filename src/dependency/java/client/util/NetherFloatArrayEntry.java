package client.util;

import net.minecraft.item.DiamondFragment;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class NetherFloatArrayEntry extends SecondaryFloatArrayEntry {
   @Override
   public void updateState() {
      this.invalidBiomeIdManager = new DiamondFragment(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager38, 0.0F);
      this.enabled2 = true;
      this.enabled = true;
      this.count = -1;
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return false;
   }

   @Override
   public String getText() {
      return "_nether";
   }

   @Override
   public IChunkProvider getIChunkProvider() {
      return new ExtendedWorldEntry(this.world, this.world.getRandomSeedManager().isEnabled7(), this.world.getTimestamp());
   }

   @Override
   public void updateState2() {
      float factor = 0.1F;

      for (int index = 0; index <= 15; index++) {
         float factor2 = 1.0F - (float)index / 15.0F;
         this.factorArray[index] = (1.0F - factor2) / (factor2 * 3.0F + 1.0F) * (1.0F - factor) + factor;
      }
   }

   @Override
   public boolean isActive2() {
      return false;
   }

   @Override
   public WorldInfo getWorldInfo() {
      return new ListEntryAdapter(this);
   }

   @Override
   public Vec3 resolveVec3(float factor, float factor2) {
      return new Vec3(0.2F, 0.03F, 0.03F);
   }

   @Override
   public float computeFactor(long value, float factor) {
      return 0.5F;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public String getTextAsText() {
      return "Nether";
   }

   @Override
   public boolean checkIndexByIndex(int count, int count2) {
      return true;
   }
}
