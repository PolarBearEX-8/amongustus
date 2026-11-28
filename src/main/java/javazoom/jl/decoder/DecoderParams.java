package javazoom.jl.decoder;


public class DecoderParams implements Cloneable {
   private OutputChannels outputChannels;
   private Equalizer equalizer;

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException e) {
         throw new InternalError(this + ": " + e);
      }
   }

   public void setOutputChannels(OutputChannels outputChannels2) {
      if (outputChannels2 == null) {
         throw new NullPointerException("out");
      } else {
         this.outputChannels = outputChannels2;
      }
   }

   public DecoderParams() {
      this.outputChannels = OutputChannels.BOTH;
      this.equalizer = new Equalizer();
   }

   public Equalizer getEqualizer() {
      return this.equalizer;
   }

   public OutputChannels getOutputChannels() {
      return this.outputChannels;
   }
}
