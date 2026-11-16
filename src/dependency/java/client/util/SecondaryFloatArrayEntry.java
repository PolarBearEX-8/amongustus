package client.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.DiamondFragment;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public abstract class SecondaryFloatArrayEntry {
   public static final float[] factorArray3 = new float[]{1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
   public World world;
   public InfoManager infoManager;
   public String labelText;
   public InvalidBiomeIdManager invalidBiomeIdManager;
   public boolean enabled2;
   public boolean enabled;
   public final float[] factorArray = new float[16];
   public int count;
   public final float[] factorArray2 = new float[4];

   public void updateState() {
      InfoManager infoManager2 = this.world.getRandomSeedManager().getInfoManager();
      if (infoManager2 == InfoManager.infoManager2) {
         VillageManager villageManager = VillageManager.resolveVillageManager(this.world.getRandomSeedManager().getLabelText2());
         this.invalidBiomeIdManager = new DiamondFragment(
            BiomeIDIsOutOfBoundsManager.resolveBiomeIDIsOutOfBoundsManager(villageManager.getCount(), BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager10), 0.5F
         );
      } else if (infoManager2 == InfoManager.infoManager4) {
         this.invalidBiomeIdManager = new DiamondFragment(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35, 0.0F);
      } else {
         this.invalidBiomeIdManager = new InvalidBiomeIdManager(this.world);
      }
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public boolean checkIndex(int count2, int count3) {
      return this.world.resolveBlock(new BlockPos(count2, 0, count3)) == Blocks.blockGrass;
   }

   public abstract String getText();

   public double getValue() {
      return this.infoManager == InfoManager.infoManager2 ? 1.0 : 0.03125;
   }

   public boolean isActive() {
      return true;
   }

   public IChunkProvider getIChunkProvider() {
      return (IChunkProvider)(this.infoManager == InfoManager.infoManager2
         ? new BezierDeserializer(this.world, this.world.getTimestamp(), this.world.getRandomSeedManager().isEnabled7(), this.labelText)
         : (
            this.infoManager == InfoManager.infoManager4
               ? new AngleProvider(this.world)
               : (
                  this.infoManager == InfoManager.infoManager3
                     ? new ExtendedRandomEntry(this.world, this.world.getTimestamp(), this.world.getRandomSeedManager().isEnabled7(), this.labelText)
                     : new ExtendedRandomEntry(this.world, this.world.getTimestamp(), this.world.getRandomSeedManager().isEnabled7(), this.labelText)
               )
         ));
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public final void applyWorldByWorld(World world2) {
      this.world = world2;
      this.infoManager = world2.getRandomSeedManager().getInfoManager();
      this.labelText = world2.getRandomSeedManager().getLabelText2();
      this.updateState();
      this.updateState2();
   }

   public float getFactor() {
      return 128.0F;
   }

   public float[] getFactorArray() {
      return this.factorArray;
   }

   public void updateState2() {
      float factor = 0.0F;

      for (int index = 0; index <= 15; index++) {
         float factor2 = 1.0F - (float)index / 15.0F;
         this.factorArray[index] = (1.0F - factor2) / (factor2 * 3.0F + 1.0F) * (1.0F - factor) + factor;
      }
   }

   public static SecondaryFloatArrayEntry resolveSecondaryFloatArrayEntry(int count2) {
      return (SecondaryFloatArrayEntry)(count2 == -1 ? new NetherFloatArrayEntry() : (count2 == 0 ? new OverworldManager() : (count2 == 1 ? new EndManager() : null)));
   }

   public int getCount() {
      return this.infoManager == InfoManager.infoManager2 ? 4 : this.world.getIndexOffset1() + 1;
   }

   public boolean isActive2() {
      return true;
   }

   public float[] resolveFloatArray(float factor, float factor2) {
      float factor3 = 0.4F;
      float factor4 = MathHelper.cos(factor * (float) Math.PI * 2.0F) - 0.0F;
      float factor5 = -0.0F;
      if (factor4 >= factor5 - factor3 && factor4 <= factor5 + factor3) {
         float factor6 = (factor4 - factor5) / factor3 * 0.5F + 0.5F;
         float factor7 = 1.0F - (1.0F - MathHelper.abs(factor6 * (float) Math.PI)) * 0.99F;
         float factor8 = factor7 * factor7;
         this.factorArray2[0] = factor6 * 0.3F + 0.7F;
         this.factorArray2[1] = factor6 * factor6 * 0.7F + 0.2F;
         this.factorArray2[2] = factor6 * factor6 * 0.0F + 0.2F;
         this.factorArray2[3] = factor8;
         return this.factorArray2;
      } else {
         return null;
      }
   }

   public Vec3 resolveVec3(float factor, float factor2) {
      float factor3 = MathHelper.clamp_float(MathHelper.cos(factor * (float) Math.PI * 2.0F) * 2.0F + 0.5F, 0.0F, 1.0F);
      float factor4 = 0.7529412F;
      float factor5 = 0.84705883F;
      float factor6 = 1.0F;
      float factor7 = factor4 * (factor3 * 0.94F + 0.06F);
      float factor8 = factor5 * (factor3 * 0.94F + 0.06F);
      float factor9 = factor6 * (factor3 * 0.91F + 0.09F);
      return new Vec3((double)factor7, (double)factor8, (double)factor9);
   }

   public int computeCount(long value) {
      return (int)(value / 24000L % 8L + 8L) % 8;
   }

   public WorldInfo getWorldInfo() {
      return new WorldInfo();
   }

   public float computeFactor(long value, float factor) {
      float factor2 = ((float)((int)(value % 24000L)) + factor) / 24000.0F - 0.25F;
      if (factor2 < 0.0F) {
         factor2++;
      }

      if (factor2 > 1.0F) {
         factor2--;
      }

      float factor3 = 1.0F - (float)((Math.cos((double)factor2 * Math.PI) + 1.0) / 2.0);
      return factor3 + (factor3 - factor3) / 3.0F;
   }

   public boolean isActiveAsFlag() {
      return true;
   }

   public abstract String getTextAsText();

   public boolean checkIndexByIndex(int count2, int count3) {
      return false;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public InvalidBiomeIdManager getInvalidBiomeIdManager() {
      return this.invalidBiomeIdManager;
   }

   public BlockPos getBlockPos() {
      return null;
   }
}
