package client.util;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileNotFoundIOException extends IOException {
   public final List list = Lists.newArrayList();
   public final String labelText;

   public void applyText(String text) {
      RotationTransformer.applyRotationTransformer((RotationTransformer)this.list.get(0), text);
   }

   public static FileNotFoundIOException resolveFileNotFoundIOException(Exception exception) {
      if (exception instanceof FileNotFoundIOException) {
         return (FileNotFoundIOException)exception;
      } else {
         String message = exception.getMessage();
         if (exception instanceof FileNotFoundException) {
            message = "File not found";
         }

         return new FileNotFoundIOException(message, exception);
      }
   }

   public void applyTextByText(String text) {
      RotationTransformer.resolveText((RotationTransformer)this.list.get(0), text);
      this.list.add(0, new RotationTransformer(null));
   }

   public FileNotFoundIOException(String text) {
      this.list.add(new RotationTransformer(null));
      this.labelText = text;
   }

   public FileNotFoundIOException(String text, Throwable throwable) {
      super(throwable);
      this.list.add(new RotationTransformer(null));
      this.labelText = text;
   }

   @Override
   public String getMessage() {
      return "Invalid " + ((RotationTransformer)this.list.get(this.list.size() - 1)).toString() + ": " + this.labelText;
   }
}
