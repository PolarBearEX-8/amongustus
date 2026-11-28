package javazoom.jl.player;


import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
public abstract class AudioDeviceBase implements AudioDevice {
   private boolean open = false;
   private Decoder decoder = null;

   public void flushImpl() {
   }

   public void setOpen(boolean flag) {
      this.open = flag;
   }

   public void closeImpl() {
   }

   @Override
   public void write(short[] sArr, int n, int n2) throws JavaLayerException {
      if (this.isOpen()) {
         this.writeImpl(sArr, n, n2);
      }
   }

   @Override
   public synchronized void open(Decoder decoder2) throws JavaLayerException {
      if (!this.isOpen()) {
         this.decoder = decoder2;
         this.openImpl();
         this.setOpen(true);
      }
   }

   public Decoder getDecoder() {
      return this.decoder;
   }

   @Override
   public void flush() {
      if (this.isOpen()) {
         this.flushImpl();
      }
   }

   @Override
   public synchronized void close() {
      if (this.isOpen()) {
         this.closeImpl();
         this.setOpen(false);
         this.decoder = null;
      }
   }

   @Override
   public synchronized boolean isOpen() {
      return this.open;
   }

   public void openImpl() throws JavaLayerException {
   }

   public void writeImpl(short[] sArr, int n, int n2) throws JavaLayerException {
   }
}
