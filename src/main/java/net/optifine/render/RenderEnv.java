package net.optifine.render;

import net.minecraft.client.renderer.BlockModelRenderer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import net.optifine.model.ListQuadsOverlay;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.BlockPosM;
import net.optifine.config.Config;

public class RenderEnv {
   private IBlockState blockState;
   private BlockPos blockPos;
   private int blockId = -1;
   private int metadata = -1;
   private int breakingAnimation = -1;
   private int smartLeaves = -1;
   private float[] quadBounds = new float[EnumFacing.VALUES.length * 2];
   private BitSet boundsFlags = new BitSet(3);
   private BlockModelRenderer.AmbientOcclusionFace aoFace = new BlockModelRenderer.AmbientOcclusionFace();
   private BlockPosM colorizerBlockPosM = null;
   private boolean[] borderFlags = null;
   private boolean[] borderFlags2 = null;
   private boolean[] borderFlags3 = null;
   private EnumFacing[] borderDirections = null;
   private List listQuadsCustomizer = new ArrayList();
   private List listQuadsCtmMultipass = new ArrayList();
   private BakedQuad[] arrayQuadsCtm1 = new BakedQuad[1];
   private BakedQuad[] arrayQuadsCtm2 = new BakedQuad[2];
   private BakedQuad[] arrayQuadsCtm3 = new BakedQuad[3];
   private BakedQuad[] arrayQuadsCtm4 = new BakedQuad[4];
   private RegionRenderCacheBuilder regionRenderCacheBuilder = null;
   private ListQuadsOverlay[] listsQuadsOverlay = new ListQuadsOverlay[EnumWorldBlockLayer.values().length];
   private boolean overlaysRendered = false;
   private static final int STATE_UNKNOWN = -1;
   private static final int STATE_FALSE = 0;
   private static final int STATE_TRUE = 1;

   public ListQuadsOverlay getListQuadsOverlay(EnumWorldBlockLayer enumWorldBlockLayer) {
      ListQuadsOverlay listQuadsOverlay = this.listsQuadsOverlay[enumWorldBlockLayer.ordinal()];
      if (listQuadsOverlay == null) {
         listQuadsOverlay = new ListQuadsOverlay();
         this.listsQuadsOverlay[enumWorldBlockLayer.ordinal()] = listQuadsOverlay;
      }

      return listQuadsOverlay;
   }

   public BitSet getBoundsFlags() {
      return this.boundsFlags;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad bakedQuad, BakedQuad bakedQuad2, BakedQuad bakedQuad3, BakedQuad bakedQuad4) {
      this.arrayQuadsCtm4[0] = bakedQuad;
      this.arrayQuadsCtm4[1] = bakedQuad2;
      this.arrayQuadsCtm4[2] = bakedQuad3;
      this.arrayQuadsCtm4[3] = bakedQuad4;
      return this.arrayQuadsCtm4;
   }

   public float[] getQuadBounds() {
      return this.quadBounds;
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad bakedQuad, BakedQuad bakedQuad2) {
      this.arrayQuadsCtm2[0] = bakedQuad;
      this.arrayQuadsCtm2[1] = bakedQuad2;
      return this.arrayQuadsCtm2;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad bakedQuad, BakedQuad bakedQuad2, BakedQuad bakedQuad3) {
      this.arrayQuadsCtm3[0] = bakedQuad;
      this.arrayQuadsCtm3[1] = bakedQuad2;
      this.arrayQuadsCtm3[2] = bakedQuad3;
      return this.arrayQuadsCtm3;
   }

   public boolean isBreakingAnimation() {
      return this.breakingAnimation == 1;
   }

   public boolean[] getBorderFlags() {
      if (this.borderFlags == null) {
         this.borderFlags = new boolean[4];
      }

      return this.borderFlags;
   }

   public boolean isBreakingAnimation2(List list) {
      if (this.breakingAnimation == -1 && list.size() > 0) {
         if (list.get(0) instanceof BreakingFour) {
            this.breakingAnimation = 1;
         } else {
            this.breakingAnimation = 0;
         }
      }

      return this.breakingAnimation == 1;
   }

   public List getListQuadsCtmMultipass(BakedQuad[] bakedQuads) {
      this.listQuadsCtmMultipass.clear();
      if (bakedQuads != null) {
         for (int i = 0; i < bakedQuads.length; i++) {
            BakedQuad bakedQuad = bakedQuads[i];
            this.listQuadsCtmMultipass.add(bakedQuad);
         }
      }

      return this.listQuadsCtmMultipass;
   }

   public void setOverlaysRendered(boolean flag) {
      this.overlaysRendered = flag;
   }

   public EnumFacing[] getBorderDirections(EnumFacing facing, EnumFacing facing2, EnumFacing facing3, EnumFacing facing4) {
      EnumFacing[] borderDirections2 = this.getBorderDirections();
      borderDirections2[0] = facing;
      borderDirections2[1] = facing2;
      borderDirections2[2] = facing3;
      borderDirections2[3] = facing4;
      return borderDirections2;
   }

   public BlockPosM getColorizerBlockPosM() {
      if (this.colorizerBlockPosM == null) {
         this.colorizerBlockPosM = new BlockPosM(0, 0, 0);
      }

      return this.colorizerBlockPosM;
   }

   public boolean[] getBorderFlags2() {
      if (this.borderFlags2 == null) {
         this.borderFlags2 = new boolean[4];
      }

      return this.borderFlags2;
   }

   public BlockModelRenderer.AmbientOcclusionFace getAoFace() {
      return this.aoFace;
   }

   public boolean isSmartLeaves() {
      if (this.smartLeaves == -1) {
         if (Config.isTreesSmart() && this.blockState.getBlock() instanceof BlockLeaves) {
            this.smartLeaves = 1;
         } else {
            this.smartLeaves = 0;
         }
      }

      return this.smartLeaves == 1;
   }

   public List getListQuadsCustomizer() {
      return this.listQuadsCustomizer;
   }

   public RegionRenderCacheBuilder getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public EnumFacing[] getBorderDirections() {
      if (this.borderDirections == null) {
         this.borderDirections = new EnumFacing[4];
      }

      return this.borderDirections;
   }

   public RenderEnv(IBlockState state, BlockPos pos) {
      this.blockState = state;
      this.blockPos = pos;
   }

   public boolean[] getBorderFlags3() {
      if (this.borderFlags3 == null) {
         this.borderFlags3 = new boolean[4];
      }

      return this.borderFlags3;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad bakedQuad) {
      this.arrayQuadsCtm1[0] = bakedQuad;
      return this.arrayQuadsCtm1;
   }

   public boolean isBreakingAnimation(BakedQuad bakedQuad) {
      if (this.breakingAnimation < 0) {
         if (bakedQuad instanceof BreakingFour) {
            this.breakingAnimation = 1;
         } else {
            this.breakingAnimation = 0;
         }
      }

      return this.breakingAnimation == 1;
   }

   public void setRegionRenderCacheBuilder(RegionRenderCacheBuilder regionRenderCacheBuilder2) {
      this.regionRenderCacheBuilder = regionRenderCacheBuilder2;
   }

   public boolean isOverlaysRendered() {
      return this.overlaysRendered;
   }

   public int getMetadata() {
      if (this.metadata < 0) {
         if (this.blockState instanceof BlockStateBase) {
            this.metadata = ((BlockStateBase)this.blockState).getMetadata();
         } else {
            this.metadata = this.blockState.getBlock().getMetaFromState(this.blockState);
         }
      }

      return this.metadata;
   }

   public int getBlockId() {
      if (this.blockId < 0) {
         if (this.blockState instanceof BlockStateBase) {
            this.blockId = ((BlockStateBase)this.blockState).getBlockId();
         } else {
            this.blockId = Block.getIdFromBlock(this.blockState.getBlock());
         }
      }

      return this.blockId;
   }

   public void reset(IBlockState state, BlockPos pos) {
      if (this.blockState != state || this.blockPos != pos) {
         this.blockState = state;
         this.blockPos = pos;
         this.blockId = -1;
         this.metadata = -1;
         this.breakingAnimation = -1;
         this.smartLeaves = -1;
         this.boundsFlags.clear();
      }
   }
}
