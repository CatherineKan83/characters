
import java.util.List;
import java.util.Scanner;


public class Sniper extends Prototype implements Ranger{
    private int mana;
    private int maxMana;
    private Elements element;
    Scanner input = new Scanner(System.in);

    public Sniper(){    
        super("", Sniper.n.nextInt(100,300), Statuses.ALIVE,Conditions.NORMAL, Races.UNDEAD, Sniper.n.nextInt(1,100), Genders.FEMALE, Skills.HEADSHOT, Weapons.CROSSBOW,3);
        this.maxMana = Sniper.n.nextInt(100, 300);
        this.mana = maxMana;
        this.element = Elements.THUNDER;
    }
    public String getInfo() {
        return String.format("%s Mana: %d / %d Element: %s",super.getInfo(), this.mana, this.maxMana, this.element);
    }
    public void rest(){
        if(this.status==Statuses.ALIVE){
            super.rest();
            if(this.mana<this.maxMana){
                this.mana = r + this.mana;
                if(this.mana>this.maxMana){
                    this.mana=this.maxMana;
                }
                System.out.println(String.format("\n%s rests and restores %d mana \n%s's mana: %d / %d ",this.name, r,this.name,this.mana,this.maxMana, this.inventory));
            } else {
                System.out.println(String.format("\n%s doesn't need rest", this.name));
            }
        }
    }
    public void skillAttack(Prototype target){
        super.skillAttack(target);
        if(this.mana<damage){ 
            System.out.println("Out of mana.");
        } else{
            target.getDamage(damage);
            this.mana-=damage;
            System.out.println(String.format("%s's mana: %d / %d",this.name, this.mana, this.maxMana));
        }
    }
    public Prototype findNearestEnemy(List<Prototype>a){
        return super.findNearestEnemy(a);
    }
    public void shoot(Prototype target){
        super.shoot(target);
    }
    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        if(this.condition==Conditions.NORMAL){
            Prototype enemy = this.findNearestEnemy(b);
            System.out.print("\n");
            System.out.print("Your choice:");
            int p =(int)input.nextInt();
            switch(p){
            case 1:
                this.shoot(enemy);
                break;
            case 2:
                this.skillAttack(enemy);
                break;
            case 3:
                this.rest();
                break;
            case 4:
                this.inventory.showInventory();
                break;
            case 5:
                System.out.println("Choose direction:\n1->up\n2->down\n3->left\n4->right\n5->upper left\n6->upper right\n7->lower left\n8->lower right\n");
                System.out.print("Your choice:");
                p =(int)input.nextInt();
                switch(p){
                    case 1:
                        this.coordinates.move("UP");
                        break;
                    case 2:
                        this.coordinates.move("DOWN");
                        break;
                    case 3:
                        this.coordinates.move("LEFT");
                        break;
                    case 4:
                        this.coordinates.move("RIGHT");
                        break;
                    case 5:
                        this.coordinates.move("UPPERLEFT");
                        break;
                    case 6:
                        this.coordinates.move("UPPERRIGHT");
                        break;
                    case 7:
                        this.coordinates.move("LOWERLEFT");
                        break;
                    case 8:
                        this.coordinates.move("LOWERRIGHT");
                        break;
                    default:
                        System.out.println("Input error");
                    }
                    break;
            case 6:
                super.buy();
                break;
            case 7:
                String l =super.useItem(); 
                switch (l) {
                    case "Health potion":
                        this.hp+=20;
                        this.inventory.items.replace("Health potion", this.inventory.items.get("Health potion"),this.inventory.items.get("Health potion")-1);
                        break;
                    case "Mana potion":
                        this.mana+=20;
                        this.inventory.items.replace("Mana potion", this.inventory.items.get("Mana potion"),this.inventory.items.get("Mana potion")-1);
                        break;
                    case "Strength potion":
                        System.out.print("Player doesn't use attribute of this type");
                        break;
                    case "Energy potion":
                        System.out.print("Player doesn't use attribute of this type");
                        break;
                    case "Magic potion":
                        System.out.print("Player doesn't use attribute of this type");
                        break;
                    case "Arrow":
                        this.shoot(enemy);
                        break;
                    default:
                        System.out.println("Input error");
                        break;
                }
                break;
            case 8:
                super.giveItem(a);
                break;
            }
        } else {this.condition=Conditions.NORMAL;}
    } 
    @Override
    public String toString() {
        return getInfo();
    }
    
}
