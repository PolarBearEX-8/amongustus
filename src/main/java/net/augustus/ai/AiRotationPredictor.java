package net.augustus.ai;

import net.augustus.utils.rotation.Rotation;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;

public final class AiRotationPredictor {
   private String loadError;
   private final long[] inputShape;
   private final RotationFeatureScaler scaler;
   private final float maxPitchDelta;
   private final AiRotationFeatureState featureState;
   private final float maxYawDelta;
   private final FeatureFrameRingBuffer frameBuffer;
   private static final int FEATURE_COUNT = 28;
   private static final int SEQUENCE_LENGTH = 10;
   private final OnnxRotationModel model;

   public AiRotationPredictor(File file, File file2) {
       super();
      this.inputShape = new long[]{1L, 10L, 28L};
      this.frameBuffer = new FeatureFrameRingBuffer(10, 28);
      this.featureState = new AiRotationFeatureState();
      RotationFeatureScaler rotationFeatureScaler = null;

      try {
         rotationFeatureScaler = RotationFeatureScaler.fromFile(file2);
      } catch (IOException e) {
         this.loadError = "failed to load normalization: " + e.getMessage();
      }

      this.scaler = rotationFeatureScaler;
      if (rotationFeatureScaler != null) {
         this.maxYawDelta = Math.abs(rotationFeatureScaler.getTargetSpec((byte)0).hi);
         this.maxPitchDelta = Math.abs(rotationFeatureScaler.getTargetSpec((byte)1).hi);
      } else {
         this.maxYawDelta = 180.0F;
         this.maxPitchDelta = 90.0F;
      }

      this.model = new OnnxRotationModel(file);
      if (!this.model.isLoaded() && this.loadError == null) {
         this.loadError = "failed to load model: " + this.model.getLoadError();
      }

   }

   public boolean isReady() {
      return this.loadError == null && this.model != null && this.model.isLoaded() && this.scaler != null;
   }

   public Rotation predict(EntityPlayerSP player, EntityLivingBase entity) {
      if (!this.isReady()) {
         return null;
      } else {
         float[] floats = AiRotationFeatureExtractor.extractFeatures(player, entity, this.featureState);
         this.scaler.normalizeInPlace(floats);
         this.frameBuffer.push(floats);
         float[] floats2 = this.frameBuffer.toFlatArray();
         long[] inputShape2 = this.inputShape;
         float[] floats3 = this.model.runInference(floats2, inputShape2);
         if (floats3 != null && floats3.length == 2) {
            float f = floats3[0];
            float f2 = this.scaler.denormalizeTarget((byte)0, f);
            float f3 = floats3[1];
            float f4 = this.scaler.denormalizeTarget((byte)1, f3);
            if (f2 > this.maxYawDelta) {
               f2 = this.maxYawDelta;
            } else if (f2 < -this.maxYawDelta) {
               f2 = -this.maxYawDelta;
            }

            if (f4 > this.maxPitchDelta) {
               f4 = this.maxPitchDelta;
            } else if (f4 < -this.maxPitchDelta) {
               f4 = -this.maxPitchDelta;
            }

            return new Rotation(f2, f4);
         } else {
            return null;
         }
      }
   }

   public String getLoadError() {
      return this.loadError;
   }

   public void reset() {
      this.frameBuffer.clear();
      this.featureState.reset();
   }

   public void close() {
      if (this.model != null) {
         this.model.close();
      }
   }

   public void recordRotationDelta(float f, float f2) {
      this.featureState.setLastRotationDelta(f, f2);
   }

   public void syncTarget(EntityLivingBase entity) {
      if (this.featureState.isInitialized()) {
         this.featureState.syncTarget(entity);
      }
   }

}
