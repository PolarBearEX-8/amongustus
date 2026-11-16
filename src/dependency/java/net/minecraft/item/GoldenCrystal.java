package net.minecraft.item;

import client.render.WorldRenderer;
import client.setting.GameSettings;
import client.util.CommonMapEntry;
import client.util.DefaultClientManager;
import client.util.IBlockState;
import client.util.InfoManager;
import client.util.InternalListenerDispatcher;
import client.util.MissingParticleManager;
import client.util.SecondaryRuntimeExceptionAdapter;
import client.util.ServiceStringEntry;
import client.util.SharedFloatEntry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class GoldenCrystal implements IResourceManagerReloadListener {
   public CommonMapEntry commonMapEntry;
   public final GameSettings gameSettings;
   public final SharedFloatEntry sharedFloatEntry = new SharedFloatEntry();
   public final DefaultClientManager defaultClientManager = new DefaultClientManager();
   public final ExtendedItemArrayEntry extendedItemArrayEntry = new ExtendedItemArrayEntry();

   public void applyIBlockState(IBlockState blockState, BlockPos blockPos, ServiceStringEntry serviceStringEntry, IBlockAccess iBlockAccess) {
      Block block = blockState.getBlock();
      if (block.processAction13() == 3) {
         IBlockState blockState2 = block.processAction(blockState, iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         IBakedModel iBakedModel = this.commonMapEntry.resolveIBakedModel(blockState2);
         IBakedModel iBakedModel2 = new MissingParticleManager(iBakedModel, serviceStringEntry).getIBakedModel();
         this.sharedFloatEntry.matchesIBlockAccess(iBlockAccess, iBakedModel2, blockState2, blockPos, Tessellator.getTessellator().getWorldRenderer());
      }
   }

   public SharedFloatEntry getSharedFloatEntry() {
      return this.sharedFloatEntry;
   }

   public IBakedModel resolveIBakedModel(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      IBlockState blockState2 = blockState;
      Block block = blockState.getBlock();
      if (iBlockAccess.getInfoManager() != InfoManager.infoManager4) {
         try {
            blockState2 = block.processAction(blockState2, iBlockAccess, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         } catch (Exception exception) {
         }
      }

      IBakedModel iBakedModel = this.commonMapEntry.resolveIBakedModel(blockState2);
      if (blockPos != null && this.gameSettings.state123 && iBakedModel instanceof InternalListenerDispatcher) {
         iBakedModel = ((InternalListenerDispatcher)iBakedModel).resolveIBakedModel(MathHelper.processAction(blockPos));
      }

      return iBakedModel;
   }

   public boolean matchesBlock(Block block, int count) {
      if (block == null) {
         return false;
      } else {
         int count2 = block.processAction13();
         return count2 == 3 ? false : count2 == 2;
      }
   }

   public CommonMapEntry getCommonMapEntry() {
      return this.commonMapEntry;
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      this.extendedItemArrayEntry.updateState();
   }

   public void applyIBlockState(IBlockState blockState, float factor) {
      int count = blockState.getBlock().processAction13();
      if (count != -1) {
         switch (count) {
            case 1:
            default:
               break;
            case 2:
               this.defaultClientManager.applyBlock(blockState.getBlock(), factor);
               break;
            case 3:
               IBakedModel iBakedModel = this.resolveIBakedModel(blockState, (BlockPos)null);
               this.sharedFloatEntry.applyIBakedModel(iBakedModel, blockState, factor, true);
         }
      }
   }

   public boolean matchesIBlockState(IBlockState blockState, BlockPos blockPos, IBlockAccess iBlockAccess, WorldRenderer worldRenderer) {
      try {
         int count = blockState.getBlock().processAction13();
         if (count == -1) {
            return false;
         } else {
            switch (count) {
               case 1:
                  return this.extendedItemArrayEntry.matchesIBlockAccess(iBlockAccess, blockState, blockPos, worldRenderer);
               case 2:
                  return false;
               case 3:
                  IBakedModel iBakedModel = this.resolveIBakedModel(blockState, iBlockAccess, blockPos);
                  return this.sharedFloatEntry.matchesIBlockAccess(iBlockAccess, iBakedModel, blockState, blockPos, worldRenderer);
               default:
                  return false;
            }
         }
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Tesselating block in world");
         CrashReportCategory.applyCrashReportCategory(
            crashReport.resolveCrashReportCategory("Block being tesselated"), blockPos, blockState.getBlock(), blockState.getBlock().getIndex(blockState)
         );
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   private IBakedModel resolveIBakedModel(IBlockState blockState, BlockPos blockPos) {
      IBakedModel iBakedModel = this.commonMapEntry.resolveIBakedModel(blockState);
      if (blockPos != null && this.gameSettings.state123 && iBakedModel instanceof InternalListenerDispatcher) {
         iBakedModel = ((InternalListenerDispatcher)iBakedModel).resolveIBakedModel(MathHelper.processAction(blockPos));
      }

      return iBakedModel;
   }

   public GoldenCrystal(CommonMapEntry commonMapEntry2, GameSettings gameSettings2) {
      this.commonMapEntry = commonMapEntry2;
      this.gameSettings = gameSettings2;
   }
}
