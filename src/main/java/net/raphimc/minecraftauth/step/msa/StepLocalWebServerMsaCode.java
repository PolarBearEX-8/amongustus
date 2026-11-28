package net.raphimc.minecraftauth.step.msa;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.utils.URLWrapper;
import net.raphimc.minecraftauth.responsehandler.exception.MsaRequestException;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.msa.MsaCodeStep.MsaCode;
import net.raphimc.minecraftauth.step.msa.StepLocalWebServer.LocalWebServer;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepLocalWebServerMsaCode extends MsaCodeStep<LocalWebServer> {
   private final int timeout;

   public StepLocalWebServerMsaCode(AbstractStep<?, LocalWebServer> prevStep, int timeout) {
      super(prevStep);
      this.timeout = timeout;
   }

   protected MsaCode execute(ILogger logger, HttpClient httpClient, LocalWebServer localWebServer) throws Exception {
      try {
         logger.info(this, "Waiting for MSA login via local webserver...");
         CompletableFuture<MsaCode> msaCodeFuture = new CompletableFuture<>();
         HttpServer httpServer = HttpServer.create(new InetSocketAddress(localWebServer.getPort()), 0);
         httpServer.createContext("/", httpExchange -> {
            try {
               Map<String, String> parameters = new URLWrapper(httpExchange.getRequestURI()).wrapQuery().getQueries();
               if (parameters.containsKey("error") && parameters.containsKey("error_description")) {
                  HttpResponse fakeResponse = new HttpResponse(null, 500, new byte[0], Collections.emptyMap());
                  throw new MsaRequestException(fakeResponse, parameters.get("error"), parameters.get("error_description"));
               }

               if (!parameters.containsKey("code")) {
                  throw new IllegalStateException("Could not extract MSA Code from response url");
               }

               byte[] response = "You have been logged in! You can now close this window.".getBytes(StandardCharsets.UTF_8);
               httpExchange.sendResponseHeaders(200, (long)response.length);
               httpExchange.getResponseBody().write(response);
               httpExchange.close();
               msaCodeFuture.complete(new MsaCode(parameters.get("code")));
            } catch (Throwable e) {
               byte[] response = ("Login failed. Error message: " + e.getMessage()).getBytes(StandardCharsets.UTF_8);
               httpExchange.sendResponseHeaders(500, (long)response.length);
               httpExchange.getResponseBody().write(response);
               httpExchange.close();
               msaCodeFuture.completeExceptionally(e);
            }
         });
         httpServer.start();

         try {
            MsaCode msaCode = msaCodeFuture.get((long)this.timeout, TimeUnit.MILLISECONDS);
            msaCode.customRedirectUri = localWebServer.getCustomRedirectUri();
            httpServer.stop(0);
            logger.info(this, "Got MSA Code");
            return msaCode;
         } catch (TimeoutException e2) {
            httpServer.stop(0);
            throw new TimeoutException("MSA login timed out");
         } catch (ExecutionException e3) {
            httpServer.stop(0);
            if (e3.getCause() != null) {
               throw e3.getCause();
            } else {
               throw e3;
            }
         }
      } catch (Throwable e4) {
         throw StepLocalWebServerMsaCode.<RuntimeException>sneakyThrow(e4);
      }
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }
}
