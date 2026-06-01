package lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lox {
    static boolean hadError = false;
    
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
        System.out.println("Usage: jlox [script]");
        System.exit(64); 
        } else if (args.length == 1) {
        runFile(args[0]);
        } else {
        runPrompt();
        }
    }

    // start Jlox from the cli and provide it a filepath
    // read filepath and execute it
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    //Interactive prompt "REPL"
    // Read a line of input, Evaluate it, Print the result, Loop and do it all over again
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null) break;
            run(line);
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        //For now, just print the tokens.
        for (Token token: tokens) {
            System.out.println(token);
        }
    }

    //Error Handling
    static void error(int line, String message){
        report(line, "", message);
    }

    private static void report(int line, String where, String message){
        String.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}


