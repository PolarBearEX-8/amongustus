package net.augustus.ai;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtSession.Result;
import ai.onnxruntime.OrtSession.SessionOptions;
import ai.onnxruntime.OrtSession.SessionOptions.OptLevel;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.util.Collections;

public final class OnnxRotationModel {
   private String loadError;
   private static final String DEFAULT_MODEL_PATH = null;
   private OrtEnvironment environment;
   private OrtSession session;
   private final String modelPath;

   public String getModelPath() {
      return this.modelPath;
   }

   public boolean isLoaded() {
      return this.session != null;
   }

   private static float[] unwrapFloatArray(Object obj) {
      Object obj2 = obj;

      while (obj2 instanceof Object[]) {
         Object[] array = (Object[])obj2;
         if (array.length == 0) {
            return new float[0];
         }

         obj2 = array[0];
      }

      if (obj2 instanceof float[]) {
         return (float[])obj2;
      } else {
         throw new IllegalStateException("Unexpected ONNX output type: " + (obj2 == null ? "null" : obj2.getClass().getName()));
      }
   }

   public String getLoadError() {
      return this.loadError;
   }

   public void close() {
      if (this.session != null) {
         try {
            this.session.close();
         } catch (OrtException e) {
         }

         this.session = null;
      }
   }

   public float[] runInference(float[] floats, long[] longs) {
      if (this.session == null) {
         return null;
      } else {
         OnnxTensor onnxTensor = null;
         Result result = null;

         Object obj;
         try {
            onnxTensor = OnnxTensor.createTensor(this.environment, FloatBuffer.wrap(floats), longs);
            result = this.session.run(Collections.singletonMap("features", onnxTensor));
            return unwrapFloatArray(result.get(0).getValue());
         } catch (OrtException e) {
            System.err.println("[AiModel] Inference error: " + e.getMessage());
            obj = null;
         } finally {
            if (result != null) {
               result.close();
            }

            if (onnxTensor != null) {
               onnxTensor.close();
            }
         }

         return (float[])obj;
      }
   }

   public OnnxRotationModel(File file) {
      this.modelPath = file.getAbsolutePath();

      try {
         byte[] bytes = Files.readAllBytes(file.toPath());
         this.environment = OrtEnvironment.getEnvironment();
         SessionOptions sessionOptions = new SessionOptions();
         sessionOptions.setIntraOpNumThreads(1);
         sessionOptions.setOptimizationLevel(OptLevel.ALL_OPT);
         this.session = this.environment.createSession(bytes, sessionOptions);
      } catch (UnsatisfiedLinkError e) {
         this.loadError = "ONNX Runtime native library failed to load: " + e.getMessage();
      } catch (OrtException e2) {
         this.loadError = "OrtException while creating session: " + e2.getMessage();
      } catch (IOException e3) {
         this.loadError = "IO error reading model file: " + e3.getMessage();
      } catch (Exception e4) {
         this.loadError = "Unexpected error loading model: " + e4.getClass().getSimpleName() + ": " + e4.getMessage();
      }
   }
}
