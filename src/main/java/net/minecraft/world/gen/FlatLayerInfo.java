package net.minecraft.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class FlatLayerInfo {
   private final int version;
   private IBlockState layerMaterial;
   private int layerCount = 1;
   private int layerMinimumY;

   public int getLayerCount() {
      return this.layerCount;
   }

   private int getFillBlockMeta() {
      return this.layerMaterial.getBlock().getMetaFromState(this.layerMaterial);
   }

   public FlatLayerInfo(int n, int n2, Block block) {
      this.version = n;
      this.layerCount = n2;
      this.layerMaterial = block.getDefaultState();
   }

   public FlatLayerInfo(int n, int n2, Block block, int n3) {
      this(n, n2, block);
      this.layerMaterial = block.getStateFromMeta(n3);
   }

   @Override
   public String toString() {
      String str;
      if (this.version >= 3) {
         ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(this.getLayerMaterialBlock());
         str = nameForObject == null ? "null" : nameForObject.toString();
         if (this.layerCount > 1) {
            str = this.layerCount + "*" + str;
         }
      } else {
         str = Integer.toString(Block.getIdFromBlock(this.getLayerMaterialBlock()));
         if (this.layerCount > 1) {
            str = this.layerCount + "x" + str;
         }
      }

      int fillBlockMeta = this.getFillBlockMeta();
      if (fillBlockMeta > 0) {
         str = str + ":" + fillBlockMeta;
      }

      return str;
   }

   public FlatLayerInfo(int n, Block block) {
      this(3, n, block);
   }

   public IBlockState getLayerMaterial() {
      return this.layerMaterial;
   }

   private Block getLayerMaterialBlock() {
      return this.layerMaterial.getBlock();
   }

   public int getMinY() {
      return this.layerMinimumY;
   }

   public void setMinY(int n) {
      this.layerMinimumY = n;
   }
}
