package net.minecraft.client.renderer;

import java.util.BitSet;
import java.util.List;
import net.augustus.modules.render.XRay;
import net.optifine.model.ListQuadsOverlay;
import net.augustus.modules.render.helper.XRayRenderMode;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.optifine.BetterSnow;
import net.optifine.CustomColors;
import net.optifine.config.Config;
import net.optifine.model.BlockModelCustomizer;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.shaders.Shaders;

public class BlockModelRenderer {
   private static float aoLightValueOpaque = 0.2F;
   private static boolean separateAoLightValue = false;
   private static final EnumWorldBlockLayer[] OVERLAY_LAYERS = new EnumWorldBlockLayer[]{
      EnumWorldBlockLayer.CUTOUT, EnumWorldBlockLayer.CUTOUT_MIPPED, EnumWorldBlockLayer.TRANSLUCENT
   };

   private void renderQuadsSmooth(IBlockAccess world, IBlockState state, BlockPos pos, WorldRenderer worldRenderer, List list, RenderEnv renderEnv) {
      Block block = state.getBlock();
      float[] quadBounds = renderEnv.getQuadBounds();
      BitSet boundsFlags = renderEnv.getBoundsFlags();
      BlockModelRenderer.AmbientOcclusionFace aoFace = renderEnv.getAoFace();
      double x = (double)pos.getX();
      double y = (double)pos.getY();
      double z = (double)pos.getZ();
      Block.EnumOffsetType offsetType = block.getOffsetType();
      if (offsetType != Block.EnumOffsetType.NONE) {
         long positionRandom = MathHelper.getPositionRandom(pos);
         x += ((double)((float)(positionRandom >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
         z += ((double)((float)(positionRandom >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
         if (offsetType == Block.EnumOffsetType.XYZ) {
            y += ((double)((float)(positionRandom >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
         }
      }

      for (int i = 0; i < list.size(); i++) {
         BakedQuad bakedQuad = (BakedQuad)list.get(i);
         if (bakedQuad.getSprite() != null) {
            this.fillQuadBounds(block, bakedQuad.getVertexData(), bakedQuad.getFace(), quadBounds, boundsFlags);
            aoFace.updateVertexBrightness(world, block, pos, bakedQuad.getFace(), quadBounds, boundsFlags);
            if (bakedQuad.getSprite().isEmissive) {
               aoFace.setMaxBlockLight();
            }

            if (worldRenderer.isMultiTexture()) {
               worldRenderer.addVertexData(bakedQuad.getVertexDataSingle());
            } else {
               worldRenderer.addVertexData(bakedQuad.getVertexData());
            }

            worldRenderer.putSprite(bakedQuad.getSprite());
            worldRenderer.putBrightness4(
               BlockModelRenderer.AmbientOcclusionFace.accessVertexBrightness(aoFace)[0],
               BlockModelRenderer.AmbientOcclusionFace.accessVertexBrightness(aoFace)[1],
               BlockModelRenderer.AmbientOcclusionFace.accessVertexBrightness(aoFace)[2],
               BlockModelRenderer.AmbientOcclusionFace.accessVertexBrightness(aoFace)[3]
            );
            int colorMultiplier = CustomColors.getColorMultiplier(bakedQuad, state, world, pos, renderEnv);
            if (!bakedQuad.hasTintIndex() && colorMultiplier == -1) {
               if (separateAoLightValue) {
                  worldRenderer.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0], 4);
                  worldRenderer.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1], 3);
                  worldRenderer.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2], 2);
                  worldRenderer.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3], 1);
               } else {
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0],
                     4
                  );
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1],
                     3
                  );
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2],
                     2
                  );
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3],
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3],
                     1
                  );
               }
            } else {
               int n;
               if (colorMultiplier != -1) {
                  n = colorMultiplier;
               } else {
                  n = block.colorMultiplier(world, pos, bakedQuad.getTintIndex());
               }

               if (EntityRenderer.anaglyphEnable) {
                  n = TextureUtil.anaglyphColor(n);
               }

               float f = (float)(n >> 16 & 0xFF) / 255.0F;
               float f2 = (float)(n >> 8 & 0xFF) / 255.0F;
               float f3 = (float)(n & 0xFF) / 255.0F;
               if (separateAoLightValue) {
                  worldRenderer.putColorMultiplierRgba(f, f2, f3, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0], 4);
                  worldRenderer.putColorMultiplierRgba(f, f2, f3, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1], 3);
                  worldRenderer.putColorMultiplierRgba(f, f2, f3, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2], 2);
                  worldRenderer.putColorMultiplierRgba(f, f2, f3, BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3], 1);
               } else {
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0] * f,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0] * f2,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[0] * f3,
                     4
                  );
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1] * f,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1] * f2,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[1] * f3,
                     3
                  );
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2] * f,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2] * f2,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[2] * f3,
                     2
                  );
                  worldRenderer.putColorMultiplier(
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3] * f,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3] * f2,
                     BlockModelRenderer.AmbientOcclusionFace.accessVertexColorMultiplier(aoFace)[3] * f3,
                     1
                  );
               }
            }

            worldRenderer.putPosition(x, y, z);
         }
      }
   }

   private void fillQuadBounds(Block block, int[] ints, EnumFacing facing, float[] floats, BitSet bitSet) {
      float f = 32.0F;
      float f2 = 32.0F;
      float f3 = 32.0F;
      float f4 = -32.0F;
      float f5 = -32.0F;
      float f6 = -32.0F;
      int n = ints.length / 4;

      for (int i = 0; i < 4; i++) {
         float f7 = Float.intBitsToFloat(ints[i * n]);
         float f8 = Float.intBitsToFloat(ints[i * n + 1]);
         float f9 = Float.intBitsToFloat(ints[i * n + 2]);
         f = Math.min(f, f7);
         f2 = Math.min(f2, f8);
         f3 = Math.min(f3, f9);
         f4 = Math.max(f4, f7);
         f5 = Math.max(f5, f8);
         f6 = Math.max(f6, f9);
      }

      if (floats != null) {
         floats[EnumFacing.WEST.getIndex()] = f;
         floats[EnumFacing.EAST.getIndex()] = f4;
         floats[EnumFacing.DOWN.getIndex()] = f2;
         floats[EnumFacing.UP.getIndex()] = f5;
         floats[EnumFacing.NORTH.getIndex()] = f3;
         floats[EnumFacing.SOUTH.getIndex()] = f6;
         int length = EnumFacing.VALUES.length;
         floats[EnumFacing.WEST.getIndex() + length] = 1.0F - f;
         floats[EnumFacing.EAST.getIndex() + length] = 1.0F - f4;
         floats[EnumFacing.DOWN.getIndex() + length] = 1.0F - f2;
         floats[EnumFacing.UP.getIndex() + length] = 1.0F - f5;
         floats[EnumFacing.NORTH.getIndex() + length] = 1.0F - f3;
         floats[EnumFacing.SOUTH.getIndex() + length] = 1.0F - f6;
      }

      float f10 = 1.0E-4F;
      float f11 = 0.9999F;
      switch (facing) {
         case DOWN:
            bitSet.set(1, f >= 1.0E-4F || f3 >= 1.0E-4F || f4 <= 0.9999F || f6 <= 0.9999F);
            bitSet.set(0, (f2 < 1.0E-4F || block.isFullCube()) && f2 == f5);
            break;
         case UP:
            bitSet.set(1, f >= 1.0E-4F || f3 >= 1.0E-4F || f4 <= 0.9999F || f6 <= 0.9999F);
            bitSet.set(0, (f5 > 0.9999F || block.isFullCube()) && f2 == f5);
            break;
         case NORTH:
            bitSet.set(1, f >= 1.0E-4F || f2 >= 1.0E-4F || f4 <= 0.9999F || f5 <= 0.9999F);
            bitSet.set(0, (f3 < 1.0E-4F || block.isFullCube()) && f3 == f6);
            break;
         case SOUTH:
            bitSet.set(1, f >= 1.0E-4F || f2 >= 1.0E-4F || f4 <= 0.9999F || f5 <= 0.9999F);
            bitSet.set(0, (f6 > 0.9999F || block.isFullCube()) && f3 == f6);
            break;
         case WEST:
            bitSet.set(1, f2 >= 1.0E-4F || f3 >= 1.0E-4F || f5 <= 0.9999F || f6 <= 0.9999F);
            bitSet.set(0, (f < 1.0E-4F || block.isFullCube()) && f == f4);
            break;
         case EAST:
            bitSet.set(1, f2 >= 1.0E-4F || f3 >= 1.0E-4F || f5 <= 0.9999F || f6 <= 0.9999F);
            bitSet.set(0, (f4 > 0.9999F || block.isFullCube()) && f == f4);
      }
   }

   public boolean renderModelAmbientOcclusion(IBlockAccess world, IBakedModel bakedModel, Block block, BlockPos pos, WorldRenderer worldRenderer, boolean flag) {
      return this.renderModelSmooth(world, bakedModel, world.getBlockState(pos), pos, worldRenderer, flag);
   }

   private void renderQuadsFlat(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, int n, boolean flag, WorldRenderer worldRenderer, List list, RenderEnv renderEnv) {
      Block block = state.getBlock();
      BitSet boundsFlags = renderEnv.getBoundsFlags();
      double x = (double)pos.getX();
      double y = (double)pos.getY();
      double z = (double)pos.getZ();
      Block.EnumOffsetType offsetType = block.getOffsetType();
      if (offsetType != Block.EnumOffsetType.NONE) {
         long l = (long)(pos.getX() * 3129871) ^ (long)pos.getZ() * 116129781L;
         l = l * l * 42317861L + l * 11L;
         x += ((double)((float)(l >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
         z += ((double)((float)(l >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
         if (offsetType == Block.EnumOffsetType.XYZ) {
            y += ((double)((float)(l >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
         }
      }

      for (BakedQuad bakedQuad : (Iterable<BakedQuad>)(list)) {
         if (bakedQuad.getSprite() != null) {
            if (flag) {
               this.fillQuadBounds(block, bakedQuad.getVertexData(), bakedQuad.getFace(), (float[])null, boundsFlags);
               n = boundsFlags.get(0) ? block.getMixedBrightnessForBlock(world, pos.offset(bakedQuad.getFace())) : block.getMixedBrightnessForBlock(world, pos);
            }

            if (bakedQuad.getSprite().isEmissive) {
               n |= 240;
            }

            if (worldRenderer.isMultiTexture()) {
               worldRenderer.addVertexData(bakedQuad.getVertexDataSingle());
            } else {
               worldRenderer.addVertexData(bakedQuad.getVertexData());
            }

            worldRenderer.putSprite(bakedQuad.getSprite());
            worldRenderer.putBrightness4(n, n, n, n);
            int colorMultiplier = CustomColors.getColorMultiplier(bakedQuad, state, world, pos, renderEnv);
            if (bakedQuad.hasTintIndex() || colorMultiplier != -1) {
               int n2;
               if (colorMultiplier != -1) {
                  n2 = colorMultiplier;
               } else {
                  n2 = block.colorMultiplier(world, pos, bakedQuad.getTintIndex());
               }

               if (EntityRenderer.anaglyphEnable) {
                  n2 = TextureUtil.anaglyphColor(n2);
               }

               float f = (float)(n2 >> 16 & 0xFF) / 255.0F;
               float f2 = (float)(n2 >> 8 & 0xFF) / 255.0F;
               float f3 = (float)(n2 & 0xFF) / 255.0F;
               worldRenderer.putColorMultiplier(f, f2, f3, 4);
               worldRenderer.putColorMultiplier(f, f2, f3, 3);
               worldRenderer.putColorMultiplier(f, f2, f3, 2);
               worldRenderer.putColorMultiplier(f, f2, f3, 1);
            }

            worldRenderer.putPosition(x, y, z);
         }
      }
   }

   private void renderModelBrightnessColorQuads(float f, float f2, float f3, float f4, List list) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();

      for (BakedQuad bakedQuad : (Iterable<BakedQuad>)(list)) {
         worldRenderer.begin(7, DefaultVertexFormats.ITEM);
         worldRenderer.addVertexData(bakedQuad.getVertexData());
         worldRenderer.putSprite(bakedQuad.getSprite());
         if (bakedQuad.hasTintIndex()) {
            worldRenderer.putColorRGB_F4(f2 * f, f3 * f, f4 * f);
         } else {
            worldRenderer.putColorRGB_F4(f, f, f);
         }

         Vec3i directionVec = bakedQuad.getFace().getDirectionVec();
         worldRenderer.putNormal((float)directionVec.getX(), (float)directionVec.getY(), (float)directionVec.getZ());
         instance.draw();
      }
   }

   public boolean renderModel(IBlockAccess world, IBakedModel bakedModel, IBlockState state, BlockPos pos, WorldRenderer worldRenderer, boolean flag) {
      if (worldRenderer == null) {
         return false;
      } else {
         boolean flag2 = Minecraft.isAmbientOcclusionEnabled() && state.getBlock().getLightValue() == 0 && bakedModel.isAmbientOcclusion();

         try {
            if (Config.isShaderPackLoaded()) {
               SVertexBuilder.pushEntity(state, pos, world, worldRenderer);
            }

            RenderEnv renderEnv = worldRenderer.getRenderEnv(state, pos);
            bakedModel = BlockModelCustomizer.getRenderModel(bakedModel, state, renderEnv);
            boolean flag3 = flag2 ? this.renderModelSmooth(world, bakedModel, state, pos, worldRenderer, flag) : this.renderModelFlat(world, bakedModel, state, pos, worldRenderer, flag);
            if (flag3) {
               this.renderOverlayModels(world, bakedModel, state, pos, worldRenderer, flag, 0L, renderEnv, flag2);
            }

            if (Config.isShaderPackLoaded()) {
               SVertexBuilder.popEntity(worldRenderer);
            }

            return flag3;
         } catch (Throwable e) {
            CrashReport crashReport = CrashReport.makeCrashReport(e, "Tesselating block model");
            CrashReportCategory crashReportCategory = crashReport.makeCategory("Block model being tesselated");
            CrashReportCategory.addBlockInfo(crashReportCategory, pos, state);
            crashReportCategory.addCrashSection("Using AO", flag2);
            throw new ReportedException(crashReport);
         }
      }
   }

   public boolean renderModel(IBlockAccess world, IBakedModel bakedModel, IBlockState state, BlockPos pos, WorldRenderer worldRenderer) {
      state.getBlock().setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return this.renderModel(world, bakedModel, state, pos, worldRenderer, true);
   }

   public void renderModelBrightnessColor(IBakedModel bakedModel, float f, float f2, float f3, float f4) {
      for (EnumFacing facing : EnumFacing.VALUES) {
         this.renderModelBrightnessColorQuads(f, f2, f3, f4, bakedModel.getFaceQuads(facing));
      }

      this.renderModelBrightnessColorQuads(f, f2, f3, f4, bakedModel.getGeneralQuads());
   }

   public static float fixAoLightValue(float f) {
      return f == 0.2F ? aoLightValueOpaque : f;
   }

   private boolean renderModelSmooth(IBlockAccess world, IBakedModel bakedModel, IBlockState state, BlockPos pos, WorldRenderer worldRenderer, boolean flag) {
      boolean flag2 = false;
      Block block = state.getBlock();
      boolean flag3 = false;
      if (XRay.enabled) {
         XRayRenderMode blockRenderMode = XRay.getBlockRenderMode(block, state, pos);
         if (blockRenderMode == XRayRenderMode.HIDE) {
            return false;
         }

         flag3 = blockRenderMode == XRayRenderMode.FORCE_VISIBLE;
      }

      RenderEnv renderEnv = worldRenderer.getRenderEnv(state, pos);
      EnumWorldBlockLayer blockLayer = worldRenderer.getBlockLayer();

      for (EnumFacing facing : EnumFacing.VALUES) {
         List faceQuads = bakedModel.getFaceQuads(facing);
         if (!faceQuads.isEmpty()
            && (
               flag3
                  || !flag
                  || block.shouldSideBeRendered(
                     world, pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ(), facing
                  )
            )) {
            faceQuads = BlockModelCustomizer.getRenderQuads(faceQuads, world, state, pos, facing, blockLayer, 0L, renderEnv);
            this.renderQuadsSmooth(world, state, pos, worldRenderer, faceQuads, renderEnv);
            flag2 = true;
         }
      }

      List generalQuads = bakedModel.getGeneralQuads();
      if (!generalQuads.isEmpty()) {
         generalQuads = BlockModelCustomizer.getRenderQuads(generalQuads, world, state, pos, (EnumFacing)null, blockLayer, 0L, renderEnv);
         this.renderQuadsSmooth(world, state, pos, worldRenderer, generalQuads, renderEnv);
         flag2 = true;
      }

      return flag2;
   }

   public boolean renderModelFlat(IBlockAccess world, IBakedModel bakedModel, IBlockState state, BlockPos pos, WorldRenderer worldRenderer, boolean flag) {
      boolean flag2 = false;
      Block block = state.getBlock();
      boolean flag3 = false;
      if (XRay.enabled) {
         XRayRenderMode blockRenderMode = XRay.getBlockRenderMode(block, state, pos);
         if (blockRenderMode == XRayRenderMode.HIDE) {
            return false;
         }

         flag3 = blockRenderMode == XRayRenderMode.FORCE_VISIBLE;
      }

      RenderEnv renderEnv = worldRenderer.getRenderEnv(state, pos);
      EnumWorldBlockLayer blockLayer = worldRenderer.getBlockLayer();

      for (EnumFacing facing : EnumFacing.VALUES) {
         List faceQuads = bakedModel.getFaceQuads(facing);
         if (!faceQuads.isEmpty()) {
            BlockPos pos2 = pos.offset(facing);
            if (flag3
               || !flag
               || block.shouldSideBeRendered(world, pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ(), facing)) {
               int n = block.getMixedBrightnessForBlock(world, pos2);
               faceQuads = BlockModelCustomizer.getRenderQuads(faceQuads, world, state, pos, facing, blockLayer, 0L, renderEnv);
               this.renderQuadsFlat(world, state, pos, facing, n, false, worldRenderer, faceQuads, renderEnv);
               flag2 = true;
            }
         }
      }

      List generalQuads = bakedModel.getGeneralQuads();
      if (!generalQuads.isEmpty()) {
         generalQuads = BlockModelCustomizer.getRenderQuads(generalQuads, world, state, pos, (EnumFacing)null, blockLayer, 0L, renderEnv);
         this.renderQuadsFlat(world, state, pos, (EnumFacing)null, -1, true, worldRenderer, generalQuads, renderEnv);
         flag2 = true;
      }

      return flag2;
   }

   public boolean renderModelStandard(IBlockAccess world, IBakedModel bakedModel, Block block, BlockPos pos, WorldRenderer worldRenderer, boolean flag) {
      return this.renderModelFlat(world, bakedModel, world.getBlockState(pos), pos, worldRenderer, flag);
   }

   private void renderOverlayModels(
      IBlockAccess world, IBakedModel bakedModel, IBlockState state, BlockPos pos, WorldRenderer worldRenderer, boolean flag, long l, RenderEnv renderEnv, boolean flag2
   ) {
      if (renderEnv.isOverlaysRendered()) {
         for (EnumWorldBlockLayer enumWorldBlockLayer : OVERLAY_LAYERS) {
            ListQuadsOverlay listQuadsOverlay = renderEnv.getListQuadsOverlay(enumWorldBlockLayer);
            if (listQuadsOverlay.size() > 0) {
               RegionRenderCacheBuilder regionRenderCacheBuilder = renderEnv.getRegionRenderCacheBuilder();
               if (regionRenderCacheBuilder != null) {
                  WorldRenderer worldRendererByLayer = regionRenderCacheBuilder.getWorldRendererByLayer(enumWorldBlockLayer);
                  if (!worldRendererByLayer.isDrawing()) {
                     worldRendererByLayer.begin(7, DefaultVertexFormats.BLOCK);
                     worldRendererByLayer.setTranslation(worldRenderer.getXOffset(), worldRenderer.getYOffset(), worldRenderer.getZOffset());
                  }

                  for (int i = 0; i < listQuadsOverlay.size(); i++) {
                     BakedQuad quad = listQuadsOverlay.getQuad(i);
                     List listQuadsSingle = listQuadsOverlay.getListQuadsSingle(quad);
                     IBlockState blockState = listQuadsOverlay.getBlockState(i);
                     if (quad.getQuadEmissive() != null) {
                        listQuadsOverlay.addQuad(quad.getQuadEmissive(), blockState);
                     }

                     renderEnv.reset(blockState, pos);
                     if (flag2) {
                        this.renderQuadsSmooth(world, blockState, pos, worldRendererByLayer, listQuadsSingle, renderEnv);
                     } else {
                        int n = blockState.getBlock().getMixedBrightnessForBlock(world, pos.offset(quad.getFace()));
                        this.renderQuadsFlat(world, blockState, pos, quad.getFace(), n, false, worldRendererByLayer, listQuadsSingle, renderEnv);
                     }
                  }
               }

               listQuadsOverlay.clear();
            }
         }
      }

      if (Config.isBetterSnow() && !renderEnv.isBreakingAnimation() && BetterSnow.shouldRender(world, state, pos)) {
         this.renderModel(world, BetterSnow.getModelSnowLayer(), BetterSnow.getStateSnowLayer(), pos, worldRenderer, flag);
      }
   }

   public void renderModelBrightness(IBakedModel bakedModel, IBlockState state, float f, boolean flag) {
      Block block = state.getBlock();
      block.setBlockBoundsForItemRender();
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      int renderColor = block.getRenderColor(block.getStateForEntityRender(state));
      if (EntityRenderer.anaglyphEnable) {
         renderColor = TextureUtil.anaglyphColor(renderColor);
      }

      float f2 = (float)(renderColor >> 16 & 0xFF) / 255.0F;
      float f3 = (float)(renderColor >> 8 & 0xFF) / 255.0F;
      float f4 = (float)(renderColor & 0xFF) / 255.0F;
      if (!flag) {
         GlStateManager.color(f, f, f, 1.0F);
      }

      this.renderModelBrightnessColor(bakedModel, f, f2, f3, f4);
   }

   public static void updateAoLightValue() {
      aoLightValueOpaque = 1.0F - Config.getAmbientOcclusionLevel() * 0.8F;
      separateAoLightValue = Config.isShaderPackLoaded() && Shaders.isSeparateAo();
   }

public enum Orientation {
      DOWN(EnumFacing.DOWN, false),
      UP(EnumFacing.UP, false),
      NORTH(EnumFacing.NORTH, false),
      SOUTH(EnumFacing.SOUTH, false),
      WEST(EnumFacing.WEST, false),
      EAST(EnumFacing.EAST, false),
      FLIP_DOWN(EnumFacing.DOWN, true),
      FLIP_UP(EnumFacing.UP, true),
      FLIP_NORTH(EnumFacing.NORTH, true),
      FLIP_SOUTH(EnumFacing.SOUTH, true),
      FLIP_WEST(EnumFacing.WEST, true),
      FLIP_EAST(EnumFacing.EAST, true);

   public final int shape;
   private Orientation(EnumFacing facing, boolean flag) {
         this.shape = facing.getIndex() + (flag ? EnumFacing.getValues().length : 0);
      }
   }

   public enum EnumNeighborInfo {
      DOWN(new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.5F, false, new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0]),
      UP(new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH}, 1.0F, false, new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0]),
      NORTH(new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST}, 0.8F, true, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_WEST
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_EAST
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST
         }),
      SOUTH(new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP}, 0.8F, true, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.WEST
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.WEST
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.EAST
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.EAST
         }),
      WEST(new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.6F, true, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.SOUTH
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.NORTH
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.NORTH
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.SOUTH
         }),
      EAST(new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.6F, true, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.SOUTH
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.NORTH
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.NORTH
         }, new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.SOUTH
         });

   public final EnumFacing[] corners;
      public final float shadeWeight;
      public final boolean doNonCubicWeight;
      public final BlockModelRenderer.Orientation[] vert0Weights;
      public final BlockModelRenderer.Orientation[] vert1Weights;
      public final BlockModelRenderer.Orientation[] vert2Weights;
      public final BlockModelRenderer.Orientation[] vert3Weights;
      private static final EnumNeighborInfo[] VALUES = new EnumNeighborInfo[6];
   static {
         VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
         VALUES[EnumFacing.UP.getIndex()] = UP;
         VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
         VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
         VALUES[EnumFacing.WEST.getIndex()] = WEST;
         VALUES[EnumFacing.EAST.getIndex()] = EAST;
      }

      private EnumNeighborInfo(
         EnumFacing[] facings,
         float f,
         boolean flag,
         BlockModelRenderer.Orientation[] orientations,
         BlockModelRenderer.Orientation[] orientations2,
         BlockModelRenderer.Orientation[] orientations3,
         BlockModelRenderer.Orientation[] orientations4
      ) {
         this.corners = facings;
         this.shadeWeight = f;
         this.doNonCubicWeight = flag;
         this.vert0Weights = orientations;
         this.vert1Weights = orientations2;
         this.vert2Weights = orientations3;
         this.vert3Weights = orientations4;
      }

      public static EnumNeighborInfo getNeighbourInfo(EnumFacing facing) {
         return VALUES[facing.getIndex()];
      }
   }

   public enum VertexTranslations {
      DOWN(0, 1, 2, 3),
      UP(2, 3, 0, 1),
      NORTH(3, 0, 1, 2),
      SOUTH(0, 1, 2, 3),
      WEST(3, 0, 1, 2),
      EAST(1, 2, 3, 0);

   private final int vert0;
      private final int vert1;
      private final int vert2;
      private final int vert3;
      private static final VertexTranslations[] VALUES = new VertexTranslations[6];
   public static VertexTranslations getVertexTranslations(EnumFacing facing) {
         return VALUES[facing.getIndex()];
      }

      public static int getVert1(VertexTranslations vertexTranslations) {
         return vertexTranslations.vert1;
      }

      public static int getVert2(VertexTranslations vertexTranslations) {
         return vertexTranslations.vert2;
      }

      static {
         VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
         VALUES[EnumFacing.UP.getIndex()] = UP;
         VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
         VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
         VALUES[EnumFacing.WEST.getIndex()] = WEST;
         VALUES[EnumFacing.EAST.getIndex()] = EAST;
      }

      public static int getVert0(VertexTranslations vertexTranslations) {
         return vertexTranslations.vert0;
      }

      public static int getVert3(VertexTranslations vertexTranslations) {
         return vertexTranslations.vert3;
      }

      private VertexTranslations(int n, int n2, int n3, int n4) {
         this.vert0 = n;
         this.vert1 = n2;
         this.vert2 = n3;
         this.vert3 = n4;
      }
   }

   public static class AmbientOcclusionFace {
      private final float[] vertexColorMultiplier = new float[4];
      private final int[] vertexBrightness = new int[4];

      public void updateVertexBrightness(IBlockAccess world, Block block, BlockPos pos, EnumFacing facing, float[] floats, BitSet bitSet) {
         BlockPos pos2 = bitSet.get(0) ? pos.offset(facing) : pos;
         BlockModelRenderer.EnumNeighborInfo neighbourInfo = BlockModelRenderer.EnumNeighborInfo.getNeighbourInfo(facing);
         BlockPos pos3 = pos2.offset(neighbourInfo.corners[0]);
         BlockPos pos4 = pos2.offset(neighbourInfo.corners[1]);
         BlockPos pos5 = pos2.offset(neighbourInfo.corners[2]);
         BlockPos pos6 = pos2.offset(neighbourInfo.corners[3]);
         int n = block.getMixedBrightnessForBlock(world, pos3);
         int n2 = block.getMixedBrightnessForBlock(world, pos4);
         int n3 = block.getMixedBrightnessForBlock(world, pos5);
         int n4 = block.getMixedBrightnessForBlock(world, pos6);
         float f = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos3).getBlock().getAmbientOcclusionLightValue());
         float f2 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos4).getBlock().getAmbientOcclusionLightValue());
         float f3 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos5).getBlock().getAmbientOcclusionLightValue());
         float f4 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos6).getBlock().getAmbientOcclusionLightValue());
         boolean isTranslucent = world.getBlockState(pos3.offset(facing)).getBlock().isTranslucent();
         boolean isTranslucent2 = world.getBlockState(pos4.offset(facing)).getBlock().isTranslucent();
         boolean isTranslucent3 = world.getBlockState(pos5.offset(facing)).getBlock().isTranslucent();
         boolean isTranslucent4 = world.getBlockState(pos6.offset(facing)).getBlock().isTranslucent();
         float f5;
         int n5;
         if (!isTranslucent3 && !isTranslucent) {
            f5 = f;
            n5 = n;
         } else {
            BlockPos pos7 = pos3.offset(neighbourInfo.corners[2]);
            f5 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos7).getBlock().getAmbientOcclusionLightValue());
            n5 = block.getMixedBrightnessForBlock(world, pos7);
         }

         float f6;
         int n6;
         if (!isTranslucent4 && !isTranslucent) {
            f6 = f;
            n6 = n;
         } else {
            BlockPos pos8 = pos3.offset(neighbourInfo.corners[3]);
            f6 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos8).getBlock().getAmbientOcclusionLightValue());
            n6 = block.getMixedBrightnessForBlock(world, pos8);
         }

         float f7;
         int n7;
         if (!isTranslucent3 && !isTranslucent2) {
            f7 = f2;
            n7 = n2;
         } else {
            BlockPos pos9 = pos4.offset(neighbourInfo.corners[2]);
            f7 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos9).getBlock().getAmbientOcclusionLightValue());
            n7 = block.getMixedBrightnessForBlock(world, pos9);
         }

         float f8;
         int n8;
         if (!isTranslucent4 && !isTranslucent2) {
            f8 = f2;
            n8 = n2;
         } else {
            BlockPos pos10 = pos4.offset(neighbourInfo.corners[3]);
            f8 = BlockModelRenderer.fixAoLightValue(world.getBlockState(pos10).getBlock().getAmbientOcclusionLightValue());
            n8 = block.getMixedBrightnessForBlock(world, pos10);
         }

         int n9 = block.getMixedBrightnessForBlock(world, pos);
         if (bitSet.get(0) || !world.getBlockState(pos.offset(facing)).getBlock().isOpaqueCube()) {
            n9 = block.getMixedBrightnessForBlock(world, pos.offset(facing));
         }

         float f9 = bitSet.get(0)
            ? world.getBlockState(pos2).getBlock().getAmbientOcclusionLightValue()
            : world.getBlockState(pos).getBlock().getAmbientOcclusionLightValue();
         f9 = BlockModelRenderer.fixAoLightValue(f9);
         BlockModelRenderer.VertexTranslations vertexTranslations = BlockModelRenderer.VertexTranslations.getVertexTranslations(facing);
         if (bitSet.get(1) && neighbourInfo.doNonCubicWeight) {
            float f10 = (f4 + f + f6 + f9) * 0.25F;
            float f11 = (f3 + f + f5 + f9) * 0.25F;
            float f12 = (f3 + f2 + f7 + f9) * 0.25F;
            float f13 = (f4 + f2 + f8 + f9) * 0.25F;
            float f14 = floats[neighbourInfo.vert0Weights[0].shape] * floats[neighbourInfo.vert0Weights[1].shape];
            float f15 = floats[neighbourInfo.vert0Weights[2].shape] * floats[neighbourInfo.vert0Weights[3].shape];
            float f16 = floats[neighbourInfo.vert0Weights[4].shape] * floats[neighbourInfo.vert0Weights[5].shape];
            float f17 = floats[neighbourInfo.vert0Weights[6].shape] * floats[neighbourInfo.vert0Weights[7].shape];
            float f18 = floats[neighbourInfo.vert1Weights[0].shape] * floats[neighbourInfo.vert1Weights[1].shape];
            float f19 = floats[neighbourInfo.vert1Weights[2].shape] * floats[neighbourInfo.vert1Weights[3].shape];
            float f20 = floats[neighbourInfo.vert1Weights[4].shape] * floats[neighbourInfo.vert1Weights[5].shape];
            float f21 = floats[neighbourInfo.vert1Weights[6].shape] * floats[neighbourInfo.vert1Weights[7].shape];
            float f22 = floats[neighbourInfo.vert2Weights[0].shape] * floats[neighbourInfo.vert2Weights[1].shape];
            float f23 = floats[neighbourInfo.vert2Weights[2].shape] * floats[neighbourInfo.vert2Weights[3].shape];
            float f24 = floats[neighbourInfo.vert2Weights[4].shape] * floats[neighbourInfo.vert2Weights[5].shape];
            float f25 = floats[neighbourInfo.vert2Weights[6].shape] * floats[neighbourInfo.vert2Weights[7].shape];
            float f26 = floats[neighbourInfo.vert3Weights[0].shape] * floats[neighbourInfo.vert3Weights[1].shape];
            float f27 = floats[neighbourInfo.vert3Weights[2].shape] * floats[neighbourInfo.vert3Weights[3].shape];
            float f28 = floats[neighbourInfo.vert3Weights[4].shape] * floats[neighbourInfo.vert3Weights[5].shape];
            float f29 = floats[neighbourInfo.vert3Weights[6].shape] * floats[neighbourInfo.vert3Weights[7].shape];
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert0(vertexTranslations)] = f10 * f14 + f11 * f15 + f12 * f16 + f13 * f17;
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert1(vertexTranslations)] = f10 * f18 + f11 * f19 + f12 * f20 + f13 * f21;
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert2(vertexTranslations)] = f10 * f22 + f11 * f23 + f12 * f24 + f13 * f25;
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert3(vertexTranslations)] = f10 * f26 + f11 * f27 + f12 * f28 + f13 * f29;
            int aoBrightness = this.getAoBrightness(n4, n, n6, n9);
            int aoBrightness2 = this.getAoBrightness(n3, n, n5, n9);
            int aoBrightness3 = this.getAoBrightness(n3, n2, n7, n9);
            int aoBrightness4 = this.getAoBrightness(n4, n2, n8, n9);
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert0(vertexTranslations)] = this.getVertexBrightness(
               aoBrightness, aoBrightness2, aoBrightness3, aoBrightness4, f14, f15, f16, f17
            );
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert1(vertexTranslations)] = this.getVertexBrightness(
               aoBrightness, aoBrightness2, aoBrightness3, aoBrightness4, f18, f19, f20, f21
            );
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert2(vertexTranslations)] = this.getVertexBrightness(
               aoBrightness, aoBrightness2, aoBrightness3, aoBrightness4, f22, f23, f24, f25
            );
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert3(vertexTranslations)] = this.getVertexBrightness(
               aoBrightness, aoBrightness2, aoBrightness3, aoBrightness4, f26, f27, f28, f29
            );
         } else {
            float f30 = (f4 + f + f6 + f9) * 0.25F;
            float f31 = (f3 + f + f5 + f9) * 0.25F;
            float f32 = (f3 + f2 + f7 + f9) * 0.25F;
            float f33 = (f4 + f2 + f8 + f9) * 0.25F;
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert0(vertexTranslations)] = this.getAoBrightness(n4, n, n6, n9);
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert1(vertexTranslations)] = this.getAoBrightness(n3, n, n5, n9);
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert2(vertexTranslations)] = this.getAoBrightness(n3, n2, n7, n9);
            this.vertexBrightness[BlockModelRenderer.VertexTranslations.getVert3(vertexTranslations)] = this.getAoBrightness(n4, n2, n8, n9);
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert0(vertexTranslations)] = f30;
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert1(vertexTranslations)] = f31;
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert2(vertexTranslations)] = f32;
            this.vertexColorMultiplier[BlockModelRenderer.VertexTranslations.getVert3(vertexTranslations)] = f33;
         }
      }

      public void setMaxBlockLight() {
         short s = 240;
         this.vertexBrightness[0] = this.vertexBrightness[0] | s;
         this.vertexBrightness[1] = this.vertexBrightness[1] | s;
         this.vertexBrightness[2] = this.vertexBrightness[2] | s;
         this.vertexBrightness[3] = this.vertexBrightness[3] | s;
         this.vertexColorMultiplier[0] = 1.0F;
         this.vertexColorMultiplier[1] = 1.0F;
         this.vertexColorMultiplier[2] = 1.0F;
         this.vertexColorMultiplier[3] = 1.0F;
      }

      private int getAoBrightness(int n, int n2, int n3, int n4) {
         if (n == 0) {
            n = n4;
         }

         if (n2 == 0) {
            n2 = n4;
         }

         if (n3 == 0) {
            n3 = n4;
         }

         return n + n2 + n3 + n4 >> 2 & 16711935;
      }

      public AmbientOcclusionFace(BlockModelRenderer blockModelRenderer) {
      }

      public static int[] accessVertexBrightness(AmbientOcclusionFace ambientOcclusionFace) {
         return ambientOcclusionFace.vertexBrightness;
      }

      public static float[] accessVertexColorMultiplier(AmbientOcclusionFace ambientOcclusionFace) {
         return ambientOcclusionFace.vertexColorMultiplier;
      }

      public AmbientOcclusionFace() {
         this((BlockModelRenderer)null);
      }

      private int getVertexBrightness(int n, int n2, int n3, int n4, float f, float f2, float f3, float f4) {
         return ((int)(
               (float)(n >> 16 & 0xFF) * f + (float)(n2 >> 16 & 0xFF) * f2 + (float)(n3 >> 16 & 0xFF) * f3 + (float)(n4 >> 16 & 0xFF) * f4
            )
            & 0xFF) << 16 | ((int)((float)(n & 0xFF) * f + (float)(n2 & 0xFF) * f2 + (float)(n3 & 0xFF) * f3 + (float)(n4 & 0xFF) * f4) & 0xFF);
      }
   }
}
