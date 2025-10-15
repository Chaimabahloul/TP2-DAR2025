package ClientPackage;
import java.io.*;
import java.net.*;
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Serveur en attente de connexion...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connecté !");
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        String operation;
        while ((operation = br.readLine()) != null) {
            System.out.println("Opération reçue : " + operation);
            String[] parts = operation.split(" ");
            if (parts.length != 3) {
                pw.println("Erreur : syntaxe incorrecte");
                continue;
            }

            try {
                int op1 = Integer.parseInt(parts[0]);
                String operator = parts[1];
                int op2 = Integer.parseInt(parts[2]);
                int result = 0;

               
                switch (operator) {
                    case "+": result = op1 + op2; break;
                    case "-": result = op1 - op2; break;
                    case "*": result = op1 * op2; break;
                    case "/": 
                        if (op2 != 0) result = op1 / op2;
                        else pw.println("Erreur : division par zéro"); 
                        continue;
                    default: pw.println("Erreur : opérateur inconnu"); continue;
                }
                pw.println(result);
            } catch (NumberFormatException e) {
                pw.println("Erreur : opérandes non valides");
            }}
        br.close();
        pw.close();
        socket.close();
        serverSocket.close();
    }}

