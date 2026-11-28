package net.minecraft.client.renderer;

import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;

public class BlockRendererDispatcher implements IResourceManagerReloadListener {
   private BlockModelShapes blockModelShapes;
   private final GameSettings gameSettings;
   private final BlockModelRenderer blockModelRenderer = new BlockModelRenderer();
   private final ChestRenderer chestRenderer = new ChestRenderer();
   private final BlockFluidRenderer fluidRenderer = new BlockFluidRenderer();

   public IBakedModel getModelFromBlockState(IBlockState state, IBlockAccess world, BlockPos pos) {
      Block block = state.getBlock();
      if (world.getWorldType() != WorldType.DEBUG_WORLD) {
         try {
            state = block.getActualState(state, world, pos.getX(), pos.getY(), pos.getZ());
         } catch (Exception e) {
         }
      }

      IBakedModel modelForState = this.blockModelShapes.getModelForState(state);
      if (pos != null && this.gameSettings.allowBlockAlternatives && modelForState instanceof WeightedBakedModel) {
         modelForState = ((WeightedBakedModel)modelForState).getAlternativeModel(MathHelper.getPositionRandom(pos));
      }

      return modelForState;
   }

   public void renderBlockBrightness(IBlockState state, float f) {
      int renderType = state.getBlock().getRenderType();
      if (renderType != -1) {
         switch (renderType) {
            case 1:
            default:
               break;
            case 2:
               this.chestRenderer.renderChestBrightness(state.getBlock(), f);
               break;
            case 3:
               IBakedModel bakedModel = this.getBakedModel(state, (BlockPos)null);
               this.blockModelRenderer.renderModelBrightness(bakedModel, state, f, true);
         }
      }
   }

   @Override
   public void onResourceManagerReload(IResourceManager resourceManager) {
      this.fluidRenderer.initAtlasSprites();
   }

   private IBakedModel getBakedModel(IBlockState state, BlockPos pos) {
      IBakedModel modelForState = this.blockModelShapes.getModelForState(state);
      if (pos != null && this.gameSettings.allowBlockAlternatives && modelForState instanceof WeightedBakedModel) {
         modelForState = ((WeightedBakedModel)modelForState).getAlternativeModel(MathHelper.getPositionRandom(pos));
      }

      return modelForState;
   }

   public BlockModelShapes getBlockModelShapes() {
      return this.blockModelShapes;
   }

   public boolean renderBlock(IBlockState state, BlockPos pos, IBlockAccess world, WorldRenderer worldRenderer) {
      try {
         int renderType = state.getBlock().getRenderType();
         if (renderType == -1) {
            return false;
         } else {
            switch (renderType) {
               case 1:
                  return this.fluidRenderer.renderFluid(world, state, pos, worldRenderer);
               case 2:
                  return false;
               case 3:
                  IBakedModel modelFromBlockState = this.getModelFromBlockState(state, world, pos);
                  return this.blockModelRenderer.renderModel(world, modelFromBlockState, state, pos, worldRenderer);
               default:
                  return false;
            }
         }
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Tesselating block in world");
         CrashReportCategory.addBlockInfo(crashReport.makeCategory("Block being tesselated"), pos, state.getBlock(), state.getBlock().getMetaFromState(state));
         throw new ReportedException(crashReport);
      }
   }

   public BlockModelRenderer getBlockModelRenderer() {
      return this.blockModelRenderer;
   }

   public boolean isRenderTypeChest(Block block, int n) {
      if (block == null) {
         return false;
      } else {
         int renderType = block.getRenderType();
         return renderType == 3 ? false : renderType == 2;
      }
   }

   public BlockRendererDispatcher(BlockModelShapes blockModelShapes2, GameSettings gameSettings2) {
      this.blockModelShapes = blockModelShapes2;
      this.gameSettings = gameSettings2;
   }

   public void renderBlockDamage(IBlockState state, BlockPos pos, TextureAtlasSprite textureAtlasSprite, IBlockAccess world) {
      Block block = state.getBlock();
      if (block.getRenderType() == 3) {
         state = block.getActualState(state, world, pos.getX(), pos.getY(), pos.getZ());
         IBakedModel modelForState = this.blockModelShapes.getModelForState(state);
         IBakedModel bakedModel = new SimpleBakedModel.Builder(modelForState, textureAtlasSprite).makeBakedModel();
         this.blockModelRenderer.renderModel(world, bakedModel, state, pos, Tessellator.getInstance().getWorldRenderer());
      }
   }
}
