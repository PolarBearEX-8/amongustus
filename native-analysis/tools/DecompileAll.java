// Ghidra headless post-script: decompile every discovered function without a timeout.
import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileOptions;
import ghidra.app.decompiler.DecompileResults;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DecompileAll extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: DecompileAll.java <output.c>");
        }

        DecompInterface decompiler = new DecompInterface();
        decompiler.setOptions(new DecompileOptions());
        decompiler.toggleCCode(true);
        decompiler.toggleSyntaxTree(false);
        if (!decompiler.openProgram(currentProgram)) {
            throw new IllegalStateException("Could not open program in decompiler");
        }

        int total = currentProgram.getFunctionManager().getFunctionCount();
        int done = 0;
        int failed = 0;
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(args[0])))) {
            out.println("/* Decompiled by Ghidra " + currentProgram.getName() + ". */");
            out.println("/* Functions: " + total + ". Per-function timeout: disabled. */");
            FunctionIterator functions = currentProgram.getFunctionManager().getFunctions(true);
            while (functions.hasNext() && !monitor.isCancelled()) {
                Function function = functions.next();
                out.println("\n/* Function " + function.getName() + " at " + function.getEntryPoint() + " */");
                DecompileResults result = decompiler.decompileFunction(function, 0, monitor);
                if (result.decompileCompleted() && result.getDecompiledFunction() != null) {
                    out.println(result.getDecompiledFunction().getC());
                } else {
                    failed++;
                    out.println("/* DECOMPILATION FAILED: " + result.getErrorMessage() + " */");
                }
                done++;
                out.flush();
                if (done % 100 == 0 || done == total) {
                    println("Decompiled " + done + "/" + total + " functions; failed=" + failed);
                }
            }
        } finally {
            decompiler.dispose();
        }
        println("COMPLETE: " + done + "/" + total + " functions; failed=" + failed);
    }
}
