package serverpackage;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            System.out.print("Entrez une opération (ex: 55 * 25) : ");
            String input = sc.nextLine().trim();

            // ✅ Validation syntaxe
            if (!input.matches("\\d+\\s*[+\\-*/]\\s*\\d+")) {
                System.out.println(" Erreur : format invalide !");
                sc.close();
                socket.close();
                return;
            }

            // Séparer les éléments
            String[] elements = input.split("\\s*([+\\-*/])\\s*");
            String op1 = elements[0];
            String op2 = elements[1];
            String operateur = input.replaceAll("[0-9\\s]", ""); // extrait le symbole

            // Envoi au serveur
            out.println(op1);
            out.println(operateur);
            out.println(op2);

            // Lecture du résultat
            String resultat = in.readLine();
            System.out.println("le resultat " + resultat);

            // ✅ Fermeture des ressources
            sc.close();
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

       
        


	}


