import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileOptions;
import ghidra.app.decompiler.DecompileResults;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.listing.Function;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DecompileRetry extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length != 3) throw new IllegalArgumentException("Usage: DecompileRetry.java <output.c> <max-instructions> <address[,address...]>");
        int maxInstructions = Integer.parseInt(args[1]);
        String[] wanted = args[2].split(",");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(args[0], true)))) {
            for (String address : wanted) {
                long offset = Long.parseUnsignedLong(address.replaceFirst("^0x", ""), 16);
                Function function = null;
                for (Function f : currentProgram.getFunctionManager().getFunctions(true)) {
                    if (f.getEntryPoint().getOffset() == offset) { function = f; break; }
                }
                if (function == null) { println("No function at " + address); continue; }
                out.println("\n/* Retry " + function.getName() + " at " + function.getEntryPoint() + " (max instructions=" + maxInstructions + ") */");
                out.flush();
                DecompInterface decompiler = new DecompInterface();
                try {
                    DecompileOptions options = new DecompileOptions();
                    options.setMaxInstructions(maxInstructions);
                    options.setMaxPayloadMBytes(1024);
                    decompiler.setOptions(options);
                    decompiler.toggleCCode(true);
                    decompiler.toggleSyntaxTree(false);
                    if (!decompiler.openProgram(currentProgram)) throw new IllegalStateException("Could not open program");
                    DecompileResults result = decompiler.decompileFunction(function, 0, monitor);
                    if (result.decompileCompleted() && result.getDecompiledFunction() != null) out.println(result.getDecompiledFunction().getC());
                    else out.println("/* DECOMPILATION FAILED: " + result.getErrorMessage() + " */");
                    println("Retry " + address + ": " + (result.decompileCompleted() ? "success" : result.getErrorMessage()));
                } catch (Exception e) {
                    out.println("/* DECOMPILATION FAILED: " + e + " */");
                    println("Retry " + address + " exception: " + e);
                } finally {
                    decompiler.dispose();
                    out.flush();
                }
            }
        }
    }
}
