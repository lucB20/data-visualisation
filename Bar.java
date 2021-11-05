import java.util.Arrays;
public class Bar implements Comparable<Bar> {
    private String Name;
    private String Category;
    private int Value;


    // Créé une nouvelle barre.
    public Bar(String name, int value, String category){
        this.Category= category;
        this.Value= value;
        this.Name= name;

        
    }
  
    // Renvoie le nom de la barre.
    public String getName(){
        return this.Name;
    }
  
    // Renvoie la valeur de la barre.
    public int getValue(){
        return this.Value;
    }
  
    // Renvoie la catégorie de la barre.
    public String getCategory(){
        return this.Category;
    }
    /**
     * affiche les proriétées des barres
     * @param tBars
     */
    public static void display(Bar[] tBars){
        for (int i=0;i<tBars.length;i++){
            System.out.print(tBars[i].getName()+" ");
            System.out.print(tBars[i].getValue()+" ");
            System.out.println(tBars[i].getCategory());
        }
    }  
    // Compare deux barres selon leur valeur.
    public int compareTo(Bar that){
        if(that.getValue()==this.getValue()){
            return 0;
        }
        if(that.getValue()<this.getValue()){
            return 1;
        }
        return -1;
    }
    

    // Exemple d'utillisation.
    public static void main(String[] args){
        Bar[] tBars = new Bar[13];
  
        tBars[0] = new Bar("adidas",3791,"Sporting Goods");
        tBars[1] = new Bar("Amazon",4528,"Retail");
        tBars[2] = new Bar("American Express",16122,"Financial Services");
        tBars[3] = new Bar("AOL",4531,"Media");
        tBars[4] = new Bar("Apple",6594,"Technology");
        tBars[5] = new Bar("AT&T",25548,"Telecommunications");
        tBars[6] = new Bar("Bacardi",3187,"Alcohol");
        tBars[7] = new Bar("Barbie",2315,"Toys & Games");
        tBars[8] = new Bar("BMW",12969,"Automotive");
        tBars[9] = new Bar("BP",3066,"Energy");
        tBars[10] = new Bar("Budweiser",10684,"Alcohol");
        tBars[11] = new Bar("Burger King",2701,"Restaurants");
        tBars[12] = new Bar("Chanel",4141,"Luxury");
        
        Arrays.sort(tBars);
        Bar.display(tBars);
    }
  
  }