package javazoom.jl.player;


import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
public interface AudioDevice {
   void flush();

   boolean isOpen();

   void close();

   int getPosition();

   void write(short[] sArr, int n, int n2) throws JavaLayerException;

   void open(Decoder decoder) throws JavaLayerException;
}
