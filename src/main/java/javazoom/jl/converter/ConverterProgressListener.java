package javazoom.jl.converter;


import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.Obuffer;
public interface ConverterProgressListener {
   int UPDATE_FRAME_COUNT = 1;
   int UPDATE_CONVERT_COMPLETE = 2;

   void parsedFrame(int n, Header header);

   void decodedFrame(int n, Header header, Obuffer obuffer);

   boolean converterException(Throwable t);

   void converterUpdate(int n, int n2, int n3);

   void readFrame(int n, Header header);
}
