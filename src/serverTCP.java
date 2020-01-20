import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class serverTCP {
    private static int port;

    public static void main(String [] args) throws Exception {
        ObjectInputStream reader;
        ObjectOutputStream sortie;
        UserChoice objectReceived;
        UserChoice objectSent = new UserChoice();
        Socket soc;
        String line;
        port = 8784;

        ServerSocket s = new ServerSocket(port);

        while (true) {
            soc = s.accept();
            reader = new ObjectInputStream(soc.getInputStream());
            sortie = new ObjectOutputStream(soc.getOutputStream());

            while (true) {
                try {
                    objectReceived = (UserChoice) reader.readObject();
                    System.out.println(objectReceived.getChoice());
                    if (objectReceived.getChoice() == 1) {
                        objectSent.setVal(countVoyelles(objectReceived.getLine()));
                    } else {
                        objectSent.setVal(countWord(objectReceived.getLine()));
                    }
                    sortie.writeObject(objectSent);
                }
                catch (Exception e){
                    break;
                }
            }
        }
    }

    private static int countWord(String phrase) {
        String[] words = phrase.split(" ");
        return words.length;
    }

    private static int countVoyelles(String phrase) {
        String voyelles = "aeiouy";
        int cpt = 0;
        for(int i=0; i< phrase.length(); i++) {
            if (voyelles.contains(phrase.substring(i,i+1))){
                cpt+=1;
            }
        }
        return cpt;
    }
}
