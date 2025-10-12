package serverpackage;
import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            ServerSocket serveur = new ServerSocket(1234);
            System.out.println("Serveur prêt, en attente d’un client...");

            Socket socket = serveur.accept();
            System.out.println("Client connecté.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Lecture des éléments
            String op1 = in.readLine();
            String operateur = in.readLine();
            String op2 = in.readLine();

            double resultat = 0;
            double a = Double.parseDouble(op1);
            double b = Double.parseDouble(op2);

            switch (operateur) {
                case "+": resultat = a + b; break;
                case "-": resultat = a - b; break;
                case "*": resultat = a * b; break;
                case "/": 
                    if (b == 0) {
                        out.println("Erreur : division par zéro !");
                        socket.close();
                        serveur.close();
                        return;
                    }
                    resultat = a / b; 
                    break;
                default:
                    out.println("Erreur : opérateur inconnu !");
                    socket.close();
                    serveur.close();
                    return;
            }

            out.println("Résultat = " + resultat);
            socket.close();
            serveur.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
