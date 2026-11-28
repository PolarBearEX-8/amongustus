package net.optifine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.init.Blocks;

public class ListQuadsOverlay {
   private List listQuads = new ArrayList();
   private List listBlockStates = new ArrayList();
   private List listQuadsSingle = Arrays.asList();

   public void addQuad(BakedQuad bakedQuad, IBlockState state) {
      if (bakedQuad != null) {
         this.listQuads.add(bakedQuad);
         this.listBlockStates.add(state);
      }
   }

   public BakedQuad getQuad(int n) {
      return (BakedQuad)this.listQuads.get(n);
   }

   public List getListQuadsSingle(BakedQuad bakedQuad) {
      this.listQuadsSingle.set(0, bakedQuad);
      return this.listQuadsSingle;
   }

   public int size() {
      return this.listQuads.size();
   }

   public IBlockState getBlockState(int n) {
      return n >= 0 && n < this.listBlockStates.size() ? (IBlockState)this.listBlockStates.get(n) : Blocks.air.getDefaultState();
   }

   public void clear() {
      this.listQuads.clear();
      this.listBlockStates.clear();
   }
}
