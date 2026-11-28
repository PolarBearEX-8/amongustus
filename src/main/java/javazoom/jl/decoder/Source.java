package javazoom.jl.decoder;

import java.io.IOException;

public interface Source {
   long LENGTH_UNKNOWN = -1L;

   int read(byte[] bytes, int n, int n2) throws IOException;

   boolean willReadBlock();

   boolean isSeekable();

   long length();

   long tell();

   long seek(long l);
}
