package cc.baritone.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OutputBuffersController {
   public synchronized void applyInputStream(InputStream inputStream, String text, ThrowableService throwableService, ChannelCodeControlleController channelCodeControlleController) throws AuxiliaryExceptionAdapter {
      InputStream entry = inputStream;
      Object entry2 = throwableService;
      if (throwableService == null) {
         entry2 = FileReadThrowableService.resolveFileReadThrowableService(0);
      }

      try {
         if (!(entry instanceof BufferedInputStream)) {
            entry = new BufferedInputStream(entry);
         }

         int count = -1;
         if (entry.markSupported()) {
            entry.mark(-1);
            count = this.computeCount(entry);
            entry.reset();
         }

         ((ThrowableService)entry2).applyIndex(1, count, 0);
         FileNameExtraCustom fileNameExtraCustom = null;
         BreadthFirstPlanner breadthFirstPlanner = new BreadthFirstPlanner(channelCodeControlleController);
         DepthFirstPipeline depthFirstPipeline = new DepthFirstPipeline((InputStream)entry);
         if (count == -1) {
            count = Integer.MAX_VALUE;
         }

         int count2 = 0;
         long timestamp = System.currentTimeMillis();

         try {
            for (; count2 < count; count2++) {
               try {
                  AStarService aStarServiceAsAStarService = depthFirstPipeline.getAStarServiceAsAStarService();
                  if (aStarServiceAsAStarService == null) {
                     break;
                  }

                  ((ThrowableService)entry2).applyAStarServiceByAStarService(count2, aStarServiceAsAStarService);
                  if (fileNameExtraCustom == null) {
                     int count3 = aStarServiceAsAStarService.getCount17() == 3 ? 1 : 2;
                     int count4 = aStarServiceAsAStarService.getCount3();
                     fileNameExtraCustom = new FileNameExtraCustom(count3, count4, text);
                     breadthFirstPlanner.setExtraCustom(fileNameExtraCustom);
                  }

                  if (breadthFirstPlanner.resolveExtraCustom(aStarServiceAsAStarService, depthFirstPipeline) != fileNameExtraCustom) {
                     throw new InternalError("Output buffers are different.");
                  }

                  ((ThrowableService)entry2).applyAStarService(count2, aStarServiceAsAStarService, fileNameExtraCustom);
                  depthFirstPipeline.updateState2();
               } catch (Exception exception) {
                  if (!((ThrowableService)entry2).matchesThrowable(exception)) {
                     throw new AuxiliaryExceptionAdapter(exception.getLocalizedMessage(), exception);
                  }
               }
            }
         } finally {
            if (fileNameExtraCustom != null) {
               fileNameExtraCustom.updateState3();
            }
         }

         int count5 = (int)(System.currentTimeMillis() - timestamp);
         ((ThrowableService)entry2).applyIndex(2, count5, count2);
      } catch (IOException ioException) {
         throw new AuxiliaryExceptionAdapter(ioException.getLocalizedMessage(), ioException);
      }
   }

   public synchronized void applyText(String text, String text2, ThrowableService throwableService) throws AuxiliaryExceptionAdapter {
      this.applyText(text, text2, throwableService, null);
   }

   public InputStream resolveInputStream(String text) throws IOException {
      File file = new File(text);
      FileInputStream inputStream = new FileInputStream(file);
      return new BufferedInputStream(inputStream);
   }

   public synchronized void applyText(String text, String text2) throws AuxiliaryExceptionAdapter {
      this.applyText(text, text2, null, null);
   }

   public void applyText(String text, String text2, ThrowableService throwableService, ChannelCodeControlleController channelCodeControlleController) throws AuxiliaryExceptionAdapter {
      String text3 = text2;
      if (text2.length() == 0) {
         text3 = null;
      }

      try {
         InputStream inputStream = this.resolveInputStream(text);
         this.applyInputStream(inputStream, text3, throwableService, channelCodeControlleController);
         inputStream.close();
      } catch (IOException ioException) {
         throw new AuxiliaryExceptionAdapter(ioException.getLocalizedMessage(), ioException);
      }
   }

   public int computeCount(InputStream inputStream) {
      return -1;
   }
}
