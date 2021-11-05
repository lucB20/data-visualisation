import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
public class AnimatedDiagBar {
    public static void main(String[] args) {
        String[] data; // liste des propriétée d'une barre 
        int nbValeurs;// nombre de valeurs pour une date
        int nBars;// nombre de barres a afficher
        File file = null;//fichier d'entrer
        Scanner input = null;//scanner du fichier
        //creation du scanner du ficher d'entrée
        //on vérifie qu'il y a assez d'argument
        if (args.length>0){
            //si oui on crée la variable contenant le fichier
            file = new File(args[0]);
            //on regarde si il est possible de crée un scanner avec le ficher
            try{
              input = new Scanner(file);
              //sinon on retourne un erreur disant que le fichier n'est pas bon
            }catch(FileNotFoundException bad){
                throw new IllegalArgumentException("bad file");
            }
        //si non on retourn on erreur disant que le fichier n'est pas définit
        }else throw new IllegalArgumentException("file is not set");
        //on recupere les valeurs des lengendes du diagrame
        String titre=input.nextLine();
        String axeX=input.nextLine();
        String source =input.nextLine();
        //on crée un nouveau diagrame
        DiagBar diag = new DiagBar(titre, axeX, source);
        //on redimentionne le canevas et on active les transition fluides
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        input.nextLine();
        // creation de chaque frame du diagrame
        while(input.hasNext()){
            //on récupere le nombre de valeurs pour la date actuelle
            nbValeurs=input.nextInt();
            input.nextLine();
            //on crée une liste contenant toute les barres pour la date
            Bar[] allBarres = new Bar[nbValeurs];
            //récupère les valeur séparée sous forme de tableau
            data=input.nextLine().split(",");
            //on récupère la date
            diag.setCaption(data[0]);
            //on crée toutes les barres
            for(int i=0;i<nbValeurs;i++){
                allBarres[i]=new Bar(data[1],Integer.parseInt(data[3]),data[4]);
                data=input.nextLine().split(",");
            }
            //on regarde si le nombre de valeurs a afficher a été donner par l'utilisateur
            if (args.length>1){
                //si oui on vérifie que cette valeur soit inférieur au nombre de barres disponible
                if(Integer.parseInt(args[1])<=nbValeurs){
                    nBars=Integer.parseInt(args[1]);
                }else{
                    //si non on met le nombres de valeirs au maximum disponible
                    nBars=nbValeurs;
                }
                //si non on retourne une erreur
            }else throw new IllegalArgumentException("bars number not set");
            // regarde si le tri a été définit
            if(args.length>2){
                // on regarde si le l'utilisateur veut trier et si la sytaxe et bonne
                switch(args[2]){
                    case "sort":
                        Arrays.sort(allBarres,Collections.reverseOrder());
                        break;
                    case "noSort":
                        break;
                    default:
                        throw new IllegalArgumentException("bad sort option ");
                }
            }else throw new IllegalArgumentException("no sort options");
            // on ajoute les barres au diagramme
            for(int i=0;i<nBars;i++){
                diag.add(allBarres[i].getName(), allBarres[i].getValue(), allBarres[i].getCategory());
            }
            // on dessine
            diag.draw();
            //on affiche
            StdDraw.show();
            //on temporise
            StdDraw.pause(50);
            //on réinitialise
            diag.reset();
        }
    }
    
}
