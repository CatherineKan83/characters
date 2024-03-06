import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main{
    public static ArrayList<Prototype> all = new ArrayList<>();
    public static List<Prototype> team1 = new ArrayList<>();
    public static List<Prototype> team2 = new ArrayList<>();
    public static int teamcount;
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.print("Input the number of players in one team:");
        teamcount= input.nextInt();
        int x1 = 1;
        int x2 = 1;

        for(int i=0;i<teamcount;i++){
            int val = Prototype.n.nextInt(8);
            switch(val){
                case 0:
                System.out.println("\nAdding bandits");
                team1.add(new Bandit());
                team2.add(new Bandit());
                break;
                case 1:
                System.out.println("\nAdding druids");
                team1.add(new Druid());
                team2.add(new Druid());
                break;
                case 2:
                System.out.println("\nAdding knights");
                team1.add(new Knight());
                team2.add(new Knight());
                break;
                case 3:
                System.out.println("\nAdding monks");
                team1.add(new Monk());
                team2.add(new Monk());
                break;
                case 4:
                System.out.println("\nAdding shooters");
                team1.add(new Shooter());
                team2.add(new Shooter());
                break;
                case 5:
                System.out.println("\nAdding snipers");
                team1.add(new Sniper());
                team2.add(new Sniper());
                break;
                case 6:
                System.out.println("\nAdding spearmans");
                team1.add(new Spearman());
                team2.add(new Spearman());
                break;
                case 7:
                System.out.println("\nAdding villagers");
                team1.add(new Villager());
                team2.add(new Villager());
                break;
                case 8:
                System.out.println("\nAdding warlocks");
                team1.add(new Warlock());
                team2.add(new Warlock());
                break;
            }
            team1.get(i).coordinates.setCoordinates(x1,1);
            team2.get(i).coordinates.setCoordinates(x2,10);
            x1+=1;
            x2+=1;
    
            System.out.println(String.format("\n|New player %s %s joined team 1|" + team1.get(i).getInfo(),team1.get(i).getClass().getSimpleName(),team1.get(i).name));
            System.out.println(String.format("\n|New player %s %s joined team 2|" + team2.get(i).getInfo(),team2.get(i).getClass().getSimpleName(),team2.get(i).name));
            if(team1.get(i) instanceof Ranger){
                team1.get(i).inventory.addItem("arrow",10);
            }
            if(team2.get(i) instanceof Ranger){
                team2.get(i).inventory.addItem("arrow",10);
            }
            team1.get(i).inventory.addItem("Health potion",2);
            team1.get(i).inventory.money=100;
            team2.get(i).inventory.addItem("Health potion",2);
            team2.get(i).inventory.money=100;
            
        }
        all.addAll(team1);
        all.addAll(team2);
        Prototype.queue(all);
        Prototype.round(team1,team2);        
    }
    

}