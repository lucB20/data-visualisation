import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiagBar {

    private final String title;               // bar chart title
    private final String xAxisLabel;          // x-axis label
    private final String dataSource;          // data source
    private String caption;                   // caption
    private ArrayList<String> names;          // list of bar names
    private ArrayList<Integer> values;        // list of bar values
    private ArrayList<Color> colors;          // list of bar colors
    //création des listes permetant de définir une couleur pour chaque categorie
    private ArrayList<String> categorys;      // list of bar categorys
    private ArrayList<Color> colorsChoice;    // liste of used colors

    /**
    * Créé un nouveau diagramme à barre.
    *
    * @param title le titre
    * @param xAxisLabel la légende de l'axe horizontal
    * @param dataSource l'origine des données
    */
    public DiagBar(String title, String xAxisLabel, String dataSource) {
        if (title == null) throw new IllegalArgumentException("name is null");
        if (xAxisLabel == null) throw new IllegalArgumentException("x-axis label is null");
        if (dataSource == null) throw new IllegalArgumentException("data source is null");
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
        //innitialisation des listes permetant de définir une couleur pour chaque categorie 
        colorsChoice = new ArrayList<Color>();
        categorys = new ArrayList<String>();
        reset();
    }

    /**
     * Permet de réinitialiser un DiagBar
    */
    public void reset() {
        //intitialisation de liste nom values et colors qui change a chaque itération
        names = new ArrayList<String>();
        values = new ArrayList<Integer>();
        colors = new ArrayList<Color>();
        //réinitialisation du canevas
        StdDraw.clear();
    }

    /**
     * Sets the caption of this bar chart.
     * The caption is drawn in the lower-right part of the window.
     *
     * @param caption the caption
     */
    public void setCaption(String caption) {
        // on intialise la variable capitation avec la chaine de caractères donné 
        this.caption=caption;
    }
    /**
        * Ajoute une barre au diagramme.
        * La longueur de la barre est proportionnelle à sa valeur.
        * Les barre sont tracées de haut en bas dans l'ordre où elle sont ajoutées.
        * Toutes les barres d'une même catégorie sont de la même couleur.
        *
        * @param name     le nom de la barre
        * @param value    la valeur de la barre
        * @param category la catégorie de la barre  
        */
    public void add(String name, int value, String category) {
        // on initialise les variable permetant de définir des couleur aléatoire
        int rgb=255;
        // permet de rendre les couleur plus claires mais moins vives
        int claire=50;
        // on ajoute le nom et la valeur de la barre
        names.add(name);
        values.add(value);
        // on regarde si la couleur de la catégorie a déja été définit
        if(categorys.contains(category)){
            // si oui on ajoute la couleur atribué a la categorie
            colors.add(colorsChoice.get(categorys.indexOf(category)));
        }else{
            // si non on ajoute la categorie a la liste de catégorei déja définit
            categorys.add(category);
            //on crée une nouvelle couleur
            colorsChoice.add(new Color(claire+(int)(Math.random()*(rgb-claire)),claire+(int)(Math.random()*(rgb-claire)),claire+(int)(Math.random()*(rgb-claire))));
            // on ajoute le couleur a la barre
            colors.add(colorsChoice.get(categorys.size()-1));
        }
        
        
    }
    /**
     * permet de desiner le diagrame 
     */
    public void draw() {
        int nbbarres=values.size();// nombre de bars a dessiner 
        double pas=1;// pas des graduations
        double max = 0;// valeur des barres maximum
        double longeurs;//longueur des barres
        double tailleBarre=0.88/(nbbarres*1.3);//largeur d'une barre
        double[] posctr= new double[2];//position du centre des barres
        Font noms = new Font("Arial", Font.BOLD,(int)(tailleBarre*300)); // police pour les nom et les valeurs
        Font title = new Font("Arial", Font.BOLD, 35); // police du titre
        Font caption = new Font("Arial", Font.BOLD, 35);// police des dates
        Font legende = new Font("Arial", Font.PLAIN, 13);// police de la légende
        // récuperation la valeur maximum
        for(int i=0;i<nbbarres;i++){
            if (values.get(i)>max){
                max=values.get(i);
            }
        }
        // dessin du titre
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(title);
        StdDraw.text(0.5,0.97,this.title);
        //dessin de la date
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(caption);
        StdDraw.text(0.88,0.15,this.caption);
        //dessin de la légende
        StdDraw.setFont(legende);
        StdDraw.text(0.88,0.1,this.dataSource);
        StdDraw.text(0.07,0.94,this.xAxisLabel);
        //choix du pas pour que le nombre de graduations soit toujours suffisant
        while (max/pas>5){
            if(Double.toString(pas).charAt(0)=='1')
                pas*=2.5;
            else
                pas*=2;
        }
        //dessin des graduation intremédiaires
        for (int i=0;i<=max;i+=pas){
            //choix de l'intervale de dessin en fonction du pas des graduation
            longeurs=((i/max+0.015/2)*0.9);
            // dessin des graduations et de leur valeurs
            StdDraw.line(longeurs,0.88-(1.3*nbbarres-0.8)*(tailleBarre)-tailleBarre*0.5,longeurs,0.89);
            StdDraw.text(longeurs,0.910,Integer.toString((int)i));
        }
        //dessin des barres
        for (int i=0; i<nbbarres; i++){
            // récupération de la couleur de la barre i
            StdDraw.setPenColor(colors.get(i));
            // calcul de sa longeur
            longeurs=values.get(i)/max*0.9;
            //calcul des coordonée x et y du centre de la barre
            posctr[0]=(longeurs-0.015)*0.5+0.015;
            posctr[1]=0.88-(i*(tailleBarre+tailleBarre*0.3)+(tailleBarre/2));
            //dessin de la barre, de son nom et de sa valeur
            StdDraw.filledRectangle(posctr[0],posctr[1],longeurs*0.5,tailleBarre*0.5);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setFont(noms);
            StdDraw.textRight(posctr[0]+longeurs*0.5-0.005,posctr[1],names.get(i));
            StdDraw.textLeft(posctr[0]+longeurs*0.5+0.005,posctr[1],Integer.toString(values.get(i)));
        }
    }

    // Exemple pour mise au point
    public static void main(String[] args) {
        // création du diagramme
        String title = "Famous brands";
        String xAxis = "stock value $(million)";
        String source = "Source: Interbrand website";
        DiagBar diag = new DiagBar(title, xAxis, source);
        diag.setCaption("2000-01-01");


        // ajout des barres suivantes au diagramme
        diag.add("adidas",3791,"Sporting Goods");
        diag.add("Amazon",4528,"Retail");
        diag.add("American Express",16122,"Financial Services");
        diag.add("AOL",4531,"Media");
        diag.add("Apple",6594,"Technology");
        diag.add("AT&T",25548,"Telecommunications");
        diag.add("Bacardi",3187,"Alcohol");
        diag.add("Barbie",2315,"Toys & Games");
        diag.add("BMW",12969,"Automotive");
        diag.add("BP",3066,"Energy");
        diag.add("Budweiser",10684,"Alcohol");
        diag.add("Burger King",2701,"Restaurants");
        diag.add("Chanel",4141,"Luxury");

        // rendu du diagramme
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        diag.draw();
        StdDraw.show();
    }

}