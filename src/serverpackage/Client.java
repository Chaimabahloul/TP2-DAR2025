package serverpackage;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connecté au serveur !");

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);
        String operation;

        // Boucle pour envoyer plusieurs opérations
        while (true) {
            System.out.println("Entrez l'opération (Ex: 55 * 25) ou 'exit' pour quitter :");
            operation = sc.nextLine();

            if (operation.equalsIgnoreCase("exit")) break;

            // Envoyer opération au serveur
            pw.println(operation);

            // Lire résultat du serveur
            String resultat = br.readLine();
            System.out.println("Résultat reçu : " + resultat);
        }

        // Fermeture des flux et socket
        sc.close();
        br.close();
        pw.close();
        socket.close();
    }
}

