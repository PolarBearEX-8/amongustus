package javazoom.jl.converter;

public class RiffChunkHeader {
   public int ckID;
   public int ckSize;
   public final RiffFile riffFile;

   public RiffChunkHeader(RiffFile riffFile2) {
      this.riffFile = riffFile2;
      this.ckID = 0;
      this.ckSize = 0;
   }
}
