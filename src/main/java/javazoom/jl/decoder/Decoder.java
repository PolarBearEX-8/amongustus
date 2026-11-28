package javazoom.jl.decoder;

public class Decoder implements DecoderErrors {
   private static final DecoderParams DEFAULT_PARAMS = new DecoderParams();
   private Obuffer output;
   private SynthesisFilter filter1;
   private SynthesisFilter filter2;
   private LayerIIIDecoder l3decoder;
   private LayerIIDecoder l2decoder;
   private LayerIDecoder l1decoder;
   private int outputFrequency;
   private int outputChannels;
   private Equalizer equalizer = new Equalizer();
   private DecoderParams params;
   private boolean initialized;

   public Decoder() {
      this(null);
   }

   public void setOutput(Obuffer obuffer) {
      this.output = obuffer;
   }

   public DecoderException newDecoderException(int n, Throwable t) {
      return new DecoderException(n, t);
   }

   public int getOutputBlockSize() {
      return 2304;
   }

   public static DecoderParams getDefaultParams() {
      return (DecoderParams)DEFAULT_PARAMS.clone();
   }

   private void initialize(Header header) throws DecoderException {
      float f = 32700.0F;
      int hMode = header.getHMode();
      header.getHLayer();
      int n = hMode == 3 ? 1 : 2;
      if (this.output == null) {
         this.output = new SampleBuffer(header.frequency(), n);
      }

      float[] bandFactors = this.equalizer.getBandFactors();
      this.filter1 = new SynthesisFilter(0, f, bandFactors);
      if (n == 2) {
         this.filter2 = new SynthesisFilter(1, f, bandFactors);
      }

      this.outputChannels = n;
      this.outputFrequency = header.frequency();
      this.initialized = true;
   }

   public int getOutputFrequency() {
      return this.outputFrequency;
   }

   public Decoder(DecoderParams decoderParams) {
      if (decoderParams == null) {
         decoderParams = DEFAULT_PARAMS;
      }

      this.params = decoderParams;
      Equalizer equalizer2 = this.params.getEqualizer();
      if (equalizer2 != null) {
         this.equalizer.setFrom(equalizer2);
      }
   }

   public void setEqualizer(Equalizer equalizer2) {
      if (equalizer2 == null) {
         equalizer2 = Equalizer.PASS_THRU_EQ;
      }

      this.equalizer.setFrom(equalizer2);
      float[] bandFactors = this.equalizer.getBandFactors();
      if (this.filter1 != null) {
         this.filter1.setEQ(bandFactors);
      }

      if (this.filter2 != null) {
         this.filter2.setEQ(bandFactors);
      }
   }

   public int getOutputChannels() {
      return this.outputChannels;
   }

   public Obuffer decodeFrame(Header header, Bitstream bitstream) throws DecoderException {
      if (!this.initialized) {
         this.initialize(header);
      }

      int hLayer = header.getHLayer();
      this.output.clear_buffer();
      this.retrieveDecoder(header, bitstream, hLayer).decodeFrame();
      this.output.write_buffer(1);
      return this.output;
   }

   public FrameDecoder retrieveDecoder(Header header, Bitstream bitstream, int n) throws DecoderException {
      Object obj = null;
      switch (n) {
         case 1:
            if (this.l1decoder == null) {
               this.l1decoder = new LayerIDecoder();
               this.l1decoder.create(bitstream, header, this.filter1, this.filter2, this.output, 0);
            }

            obj = this.l1decoder;
            break;
         case 2:
            if (this.l2decoder == null) {
               this.l2decoder = new LayerIIDecoder();
               this.l2decoder.create(bitstream, header, this.filter1, this.filter2, this.output, 0);
            }

            obj = this.l2decoder;
            break;
         case 3:
            if (this.l3decoder == null) {
               this.l3decoder = new LayerIIIDecoder(bitstream, header, this.filter1, this.filter2, this.output, 0);
            }

            obj = this.l3decoder;
      }

      if (obj == null) {
         throw this.newDecoderException(513, null);
      } else {
         return (FrameDecoder)obj;
      }
   }

   public DecoderException newDecoderException(int n) {
      return new DecoderException(n, null);
   }
}
