package javazoom.jl.decoder;

public final class Header {
   public static final int[][] frequencies = new int[][]{{22050, 24000, 16000, 1}, {44100, 48000, 32000, 1}, {11025, 12000, 8000, 1}};
   public static final int MPEG2_LSF = 0;
   public static final int MPEG25_LSF = 2;
   public static final int MPEG1 = 1;
   public static final int STEREO = 0;
   public static final int JOINT_STEREO = 1;
   public static final int DUAL_CHANNEL = 2;
   public static final int SINGLE_CHANNEL = 3;
   public static final int FOURTYFOUR_POINT_ONE = 0;
   public static final int FOURTYEIGHT = 1;
   public static final int THIRTYTWO = 2;
   private int h_layer;
   private int h_protection_bit;
   private int h_bitrate_index;
   private int h_padding_bit;
   private int h_mode_extension;
   private int h_version;
   private int h_mode;
   private int h_sample_frequency;
   private int h_number_of_subbands;
   private int h_intensity_stereo_bound;
   private boolean h_copyright;
   private boolean h_original;
   private double[] h_vbr_time_per_frame = new double[]{-1.0, 384.0, 1152.0, 1152.0};
   private boolean h_vbr;
   private int h_vbr_frames;
   private int h_vbr_scale;
   private int h_vbr_bytes;
   private byte[] h_vbr_toc;
   private byte syncmode = Bitstream.INITIAL_SYNC;
   private Crc16 crc;
   public short checksum;
   public int framesize;
   public int nSlots;
   private int _headerstring = -1;
   public static final int[][][] bitrates = new int[][][]{
      {
            {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}
      },
      {
            {0, 32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0},
            {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0},
            {0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0}
      },
      {
            {0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0},
            {0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}
      }
   };
   public static final String[][][] bitrate_str = new String[][][]{
      {
            {
                  "free format",
                  "32 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "176 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            }
      },
      {
            {
                  "free format",
                  "32 kbit/s",
                  "64 kbit/s",
                  "96 kbit/s",
                  "128 kbit/s",
                  "160 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "288 kbit/s",
                  "320 kbit/s",
                  "352 kbit/s",
                  "384 kbit/s",
                  "416 kbit/s",
                  "448 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "32 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "160 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "320 kbit/s",
                  "384 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "160 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "320 kbit/s",
                  "forbidden"
            }
      },
      {
            {
                  "free format",
                  "32 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "176 kbit/s",
                  "192 kbit/s",
                  "224 kbit/s",
                  "256 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            },
            {
                  "free format",
                  "8 kbit/s",
                  "16 kbit/s",
                  "24 kbit/s",
                  "32 kbit/s",
                  "40 kbit/s",
                  "48 kbit/s",
                  "56 kbit/s",
                  "64 kbit/s",
                  "80 kbit/s",
                  "96 kbit/s",
                  "112 kbit/s",
                  "128 kbit/s",
                  "144 kbit/s",
                  "160 kbit/s",
                  "forbidden"
            }
      }
   };

   public String bitrate_string() {
      return this.h_vbr ? this.bitrate() / 1000 + " kb/s" : bitrate_str[this.h_version][this.h_layer - 1][this.h_bitrate_index];
   }

   public boolean checksums() {
      return this.h_protection_bit == 0;
   }

   public boolean isHOriginal() {
      return this.h_original;
   }

   public int getHVbrScale() {
      return this.h_vbr_scale;
   }

   public int getHModeExtension() {
      return this.h_mode_extension;
   }

   public String layer_string() {
      switch (this.h_layer) {
         case 1:
            return "I";
         case 2:
            return "II";
         case 3:
            return "III";
         default:
            return null;
      }
   }

   public byte[] getHVbrToc() {
      return this.h_vbr_toc;
   }

   public String version_string() {
      switch (this.h_version) {
         case 0:
            return "MPEG-2 LSF";
         case 1:
            return "MPEG-1";
         case 2:
            return "MPEG-2.5 LSF";
         default:
            return null;
      }
   }

   public int min_number_of_frames(int n) {
      if (this.h_vbr) {
         return this.h_vbr_frames;
      } else {
         return this.framesize + 5 - this.h_padding_bit == 0 ? 0 : n / (this.framesize + 5 - this.h_padding_bit);
      }
   }

   public boolean isHVbr() {
      return this.h_vbr;
   }

   public int bitrate_instant() {
      return bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
   }

   public String mode_string() {
      switch (this.h_mode) {
         case 0:
            return "Stereo";
         case 1:
            return "Joint stereo";
         case 2:
            return "Dual channel";
         case 3:
            return "Single channel";
         default:
            return null;
      }
   }

   public String sample_frequency_string() {
      switch (this.h_sample_frequency) {
         case 0:
            if (this.h_version == 1) {
               return "44.1 kHz";
            } else {
               if (this.h_version == 0) {
                  return "22.05 kHz";
               }

               return "11.025 kHz";
            }
         case 1:
            if (this.h_version == 1) {
               return "48 kHz";
            } else {
               if (this.h_version == 0) {
                  return "24 kHz";
               }

               return "12 kHz";
            }
         case 2:
            if (this.h_version == 1) {
               return "32 kHz";
            } else {
               if (this.h_version == 0) {
                  return "16 kHz";
               }

               return "8 kHz";
            }
         default:
            return null;
      }
   }

   public int getHLayer() {
      return this.h_layer;
   }

   public int getNSlots() {
      return this.nSlots;
   }

   public boolean checksum_ok() {
      return this.checksum == this.crc.checksum();
   }

   public void read_header(Bitstream bitstream, Crc16[] crc16s) throws BitstreamException {
      boolean flag = false;

      int n;
      do {
         n = bitstream.syncHeader(this.syncmode);
         this._headerstring = n;
         if (this.syncmode == Bitstream.INITIAL_SYNC) {
            this.h_version = n >>> 19 & 1;
            if ((n >>> 20 & 1) == 0) {
               if (this.h_version != 0) {
                  throw bitstream.newBitstreamException(256);
               }

               this.h_version = 2;
            }

            if ((this.h_sample_frequency = n >>> 10 & 3) == 3) {
               throw bitstream.newBitstreamException(256);
            }
         }

         this.h_layer = 4 - (n >>> 17) & 3;
         this.h_protection_bit = n >>> 16 & 1;
         this.h_bitrate_index = n >>> 12 & 15;
         this.h_padding_bit = n >>> 9 & 1;
         this.h_mode = n >>> 6 & 3;
         this.h_mode_extension = n >>> 4 & 3;
         if (this.h_mode == 1) {
            this.h_intensity_stereo_bound = (this.h_mode_extension << 2) + 4;
         } else {
            this.h_intensity_stereo_bound = 0;
         }

         if ((n >>> 3 & 1) == 1) {
            this.h_copyright = true;
         }

         if ((n >>> 2 & 1) == 1) {
            this.h_original = true;
         }

         if (this.h_layer == 1) {
            this.h_number_of_subbands = 32;
         } else {
            int hBitrateIndex = this.h_bitrate_index;
            if (this.h_mode != 3) {
               if (hBitrateIndex == 4) {
                  hBitrateIndex = 1;
               } else {
                  hBitrateIndex -= 4;
               }
            }

            if (hBitrateIndex != 1 && hBitrateIndex != 2) {
               if (this.h_sample_frequency != 1 && (hBitrateIndex < 3 || hBitrateIndex > 5)) {
                  this.h_number_of_subbands = 30;
               } else {
                  this.h_number_of_subbands = 27;
               }
            } else if (this.h_sample_frequency == 2) {
               this.h_number_of_subbands = 12;
            } else {
               this.h_number_of_subbands = 8;
            }
         }

         if (this.h_intensity_stereo_bound > this.h_number_of_subbands) {
            this.h_intensity_stereo_bound = this.h_number_of_subbands;
         }

         this.calculate_framesize();
         int n2 = bitstream.read_frame_data(this.framesize);
         if (this.framesize >= 0 && n2 != this.framesize) {
            throw bitstream.newBitstreamException(261);
         }

         if (bitstream.isSyncCurrentPosition(this.syncmode)) {
            if (this.syncmode == Bitstream.INITIAL_SYNC) {
               this.syncmode = Bitstream.STRICT_SYNC;
               bitstream.set_syncword(n & -521024);
            }

            flag = true;
         } else {
            bitstream.unreadFrame();
         }
      } while (!flag);

      bitstream.parse_frame();
      if (this.h_protection_bit == 0) {
         this.checksum = (short)bitstream.get_bits(16);
         if (this.crc == null) {
            this.crc = new Crc16();
         }

         this.crc.add_bits(n, 16);
         crc16s[0] = this.crc;
      } else {
         crc16s[0] = null;
      }

      if (this.h_sample_frequency == 0) {
      }
   }

   public boolean padding() {
      return this.h_padding_bit != 0;
   }

   public int getHNumberOfSubbands() {
      return this.h_number_of_subbands;
   }

   public void parseVBR(byte[] bytes) throws BitstreamException {
      String str = "Xing";
      byte[] bytes2 = new byte[4];
      byte b = 0;
      if (this.h_version == 1) {
         if (this.h_mode == 3) {
            b = 17;
         } else {
            b = 32;
         }
      } else if (this.h_mode == 3) {
         b = 9;
      } else {
         b = 17;
      }

      try {
         System.arraycopy(bytes, b, bytes2, 0, 4);
         if (str.equals(new String(bytes2))) {
            this.h_vbr = true;
            this.h_vbr_frames = -1;
            this.h_vbr_bytes = -1;
            this.h_vbr_scale = -1;
            this.h_vbr_toc = new byte[100];
            int n = 4;
            byte[] bytes3 = new byte[4];
            System.arraycopy(bytes, b + n, bytes3, 0, bytes3.length);
            n += bytes3.length;
            if ((bytes3[3] & 1) != 0) {
               System.arraycopy(bytes, b + n, bytes2, 0, bytes2.length);
               this.h_vbr_frames = bytes2[0] << 24 & 0xFF000000 | bytes2[1] << 16 & 0xFF0000 | bytes2[2] << 8 & 0xFF00 | bytes2[3] & 255;
               n += 4;
            }

            if ((bytes3[3] & 2) != 0) {
               System.arraycopy(bytes, b + n, bytes2, 0, bytes2.length);
               this.h_vbr_bytes = bytes2[0] << 24 & 0xFF000000 | bytes2[1] << 16 & 0xFF0000 | bytes2[2] << 8 & 0xFF00 | bytes2[3] & 255;
               n += 4;
            }

            if ((bytes3[3] & 4) != 0) {
               System.arraycopy(bytes, b + n, this.h_vbr_toc, 0, this.h_vbr_toc.length);
               n += this.h_vbr_toc.length;
            }

            if ((bytes3[3] & 8) != 0) {
               System.arraycopy(bytes, b + n, bytes2, 0, bytes2.length);
               this.h_vbr_scale = bytes2[0] << 24 & 0xFF000000 | bytes2[1] << 16 & 0xFF0000 | bytes2[2] << 8 & 0xFF00 | bytes2[3] & 255;
               n += 4;
            }
         }
      } catch (ArrayIndexOutOfBoundsException e) {
         throw new BitstreamException("XingVBRHeader Corrupted", e);
      }

      String str2 = "VBRI";
      b = 32;

      try {
         System.arraycopy(bytes, b, bytes2, 0, 4);
         if (str2.equals(new String(bytes2))) {
            this.h_vbr = true;
            this.h_vbr_frames = -1;
            this.h_vbr_bytes = -1;
            this.h_vbr_scale = -1;
            this.h_vbr_toc = new byte[100];
            int n2 = 10;
            System.arraycopy(bytes, b + n2, bytes2, 0, bytes2.length);
            this.h_vbr_bytes = bytes2[0] << 24 & 0xFF000000 | bytes2[1] << 16 & 0xFF0000 | bytes2[2] << 8 & 0xFF00 | bytes2[3] & 255;
            n2 += 4;
            System.arraycopy(bytes, b + n2, bytes2, 0, bytes2.length);
            this.h_vbr_frames = bytes2[0] << 24 & 0xFF000000 | bytes2[1] << 16 & 0xFF0000 | bytes2[2] << 8 & 0xFF00 | bytes2[3] & 255;
            n2 += 4;
         }
      } catch (ArrayIndexOutOfBoundsException e2) {
         throw new BitstreamException("VBRIVBRHeader Corrupted", e2);
      }
   }

   public boolean isHCopyright() {
      return this.h_copyright;
   }

   public int max_number_of_frames(int n) {
      if (this.h_vbr) {
         return this.h_vbr_frames;
      } else {
         return this.framesize + 4 - this.h_padding_bit == 0 ? 0 : n / (this.framesize + 4 - this.h_padding_bit);
      }
   }

   public int getHBitrateIndex() {
      return this.h_bitrate_index;
   }

   public int getHMode() {
      return this.h_mode;
   }

   public int getHIntensityStereoBound() {
      return this.h_intensity_stereo_bound;
   }

   public int frequency() {
      return frequencies[this.h_version][this.h_sample_frequency];
   }

   public float total_ms(int n) {
      return (float)this.max_number_of_frames(n) * this.ms_per_frame();
   }

   public int bitrate() {
      return this.h_vbr ? (int)((float)(this.h_vbr_bytes * 8) / (this.ms_per_frame() * (float)this.h_vbr_frames)) * 1000 : bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer(200);
      sb.append("Layer ");
      sb.append(this.layer_string());
      sb.append(" frame ");
      sb.append(this.mode_string());
      sb.append(' ');
      sb.append(this.version_string());
      if (!this.checksums()) {
         sb.append(" no");
      }

      sb.append(" checksums");
      sb.append(' ');
      sb.append(this.sample_frequency_string());
      sb.append(',');
      sb.append(' ');
      sb.append(this.bitrate_string());
      return sb.toString();
   }

   public int getHSampleFrequency() {
      return this.h_sample_frequency;
   }

   public float ms_per_frame() {
      if (!this.h_vbr) {
         return (new float[][]{{8.707483F, 8.0F, 12.0F}, {26.12245F, 24.0F, 36.0F}, {26.12245F, 24.0F, 36.0F}})[this.h_layer - 1][this.h_sample_frequency];
      } else {
         double d = this.h_vbr_time_per_frame[this.getHLayer()] / (double)this.frequency();
         if (this.h_version == 0 || this.h_version == 2) {
            d /= 2.0;
         }

         return (float)(d * 1000.0);
      }
   }

   public int getHeaderstring() {
      return this._headerstring;
   }

   public int calculate_framesize() {
      if (this.h_layer == 1) {
         this.framesize = 12 * bitrates[this.h_version][0][this.h_bitrate_index] / frequencies[this.h_version][this.h_sample_frequency];
         if (this.h_padding_bit != 0) {
            this.framesize++;
         }

         this.framesize <<= 2;
         this.nSlots = 0;
      } else {
         this.framesize = 144 * bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index] / frequencies[this.h_version][this.h_sample_frequency];
         if (this.h_version == 0 || this.h_version == 2) {
            this.framesize >>= 1;
         }

         if (this.h_padding_bit != 0) {
            this.framesize++;
         }

         if (this.h_layer == 3) {
            if (this.h_version == 1) {
               this.nSlots = this.framesize - (this.h_mode == 3 ? 17 : 32) - (this.h_protection_bit != 0 ? 0 : 2) - 4;
            } else {
               this.nSlots = this.framesize - (this.h_mode == 3 ? 9 : 17) - (this.h_protection_bit != 0 ? 0 : 2) - 4;
            }
         } else {
            this.nSlots = 0;
         }
      }

      this.framesize -= 4;
      return this.framesize;
   }

   public int getHVersion() {
      return this.h_version;
   }
}
