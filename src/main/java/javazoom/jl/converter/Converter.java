package javazoom.jl.converter;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.DecoderParams;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Obuffer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Converter {
   public int countFrames(InputStream in) {
      return -1;
   }

   public InputStream openInput(String str) throws IOException {
      File file = new File(str);
      FileInputStream fileInputStream = new FileInputStream(file);
      return new BufferedInputStream(fileInputStream);
   }

   public void convert2(String str, String str2, ConverterProgressListener listener, DecoderParams decoderParams) throws JavaLayerException {
      if (str2.length() == 0) {
         str2 = null;
      }

      try {
         InputStream in = this.openInput(str);
         this.convert(in, str2, listener, decoderParams);
         in.close();
      } catch (IOException e) {
         throw new JavaLayerException(e.getLocalizedMessage(), e);
      }
   }

   public synchronized void convert(String str, String str2) throws JavaLayerException {
      this.convert2(str, str2, null, null);
   }

   public synchronized void convert(String str, String str2, ConverterProgressListener listener) throws JavaLayerException {
      this.convert2(str, str2, listener, null);
   }

   public synchronized void convert(InputStream in, String str, ConverterProgressListener listener, DecoderParams decoderParams) throws JavaLayerException {
      if (listener == null) {
         listener = PrintWriterProgressListener.newStdOut(0);
      }

      try {
         if (!(in instanceof BufferedInputStream)) {
            in = new BufferedInputStream((InputStream)in);
         }

         int n = -1;
         if (in.markSupported()) {
            in.mark(-1);
            n = this.countFrames((InputStream)in);
            in.reset();
         }

         ((ConverterProgressListener)listener).converterUpdate(1, n, 0);
         WaveFileObuffer waveFileObuffer = null;
         Decoder decoder = new Decoder(decoderParams);
         Bitstream bitstream = new Bitstream((InputStream)in);
         if (n == -1) {
            n = Integer.MAX_VALUE;
         }

         int n2 = 0;
         long l = System.currentTimeMillis();

         try {
            for (; n2 < n; n2++) {
               try {
                  Header header = bitstream.readFrame();
                  if (header == null) {
                     break;
                  }

                  ((ConverterProgressListener)listener).readFrame(n2, header);
                  if (waveFileObuffer == null) {
                     int n3 = header.getHMode() == 3 ? 1 : 2;
                     int n4 = header.frequency();
                     waveFileObuffer = new WaveFileObuffer(n3, n4, str);
                     decoder.setOutput(waveFileObuffer);
                  }

                  if (decoder.decodeFrame(header, bitstream) != waveFileObuffer) {
                     throw new InternalError("Output buffers are different.");
                  }

                  ((ConverterProgressListener)listener).decodedFrame(n2, header, waveFileObuffer);
                  bitstream.closeFrame();
               } catch (Exception e) {
                  if (!((ConverterProgressListener)listener).converterException(e)) {
                     throw new JavaLayerException(e.getLocalizedMessage(), e);
                  }
               }
            }
         } finally {
            if (waveFileObuffer != null) {
               waveFileObuffer.close();
            }
         }

         int n5 = (int)(System.currentTimeMillis() - l);
         ((ConverterProgressListener)listener).converterUpdate(2, n5, n2);
      } catch (IOException e2) {
         throw new JavaLayerException(e2.getLocalizedMessage(), e2);
      }
   }
}
