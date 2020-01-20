import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class clientTCP {
    private static int port;
    public static void main(String[] args) throws Exception{

        String adresse = "localhost";
        port= 8784;
        Afficheur afficheur = new Afficheur();
        ObjectInputStream readSocket;
        UserChoice objectReceived;
        UserChoice objectSent = new UserChoice();


        int choice;
        String line;


        Socket socket= new Socket(adresse, port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        Reader reader = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(reader);

        Scanner sc = new Scanner(System.in);

        readSocket = new ObjectInputStream(socket.getInputStream());

        while (true) {
            afficheur.print();
            choice = sc.nextInt();
            if(choice == 3) {
                System.out.println("Merci, au revoir");
                System.exit(0);
            }
            if (choice != 1 && choice != 2) {
                System.out.println("Je n'ai pas compris votre commande, veuillez rééssayer");
            }
            else {
                System.out.println("Entrez une phrase");
                line = keyboard.readLine();
                objectSent.setLine(line);
                objectSent.setChoice(choice);
                oos.writeObject(objectSent);
                objectReceived = (UserChoice) readSocket.readObject();
                System.out.println(objectReceived.getVal());
            }
        }
    }


}

