
import java.util.List;
import java.util.Scanner;

public class Spearman extends Prototype implements Warrior{
    protected int strength;
    protected int maxStrength;
    protected Elements element;
    Scanner input = new Scanner(System.in);
    
    public Spearman(){    
        super("", Spearman.n.nextInt(100,300),Statuses.ALIVE, Conditions.NORMAL, Races.BEAST, Spearman.n.nextInt(1,100), Genders.MALE, Skills.TANK, Weapons.SPEAR,2);
        this.maxStrength = Spearman.n.nextInt(100, 300);
        this.strength = maxStrength;
        this.element = Elements.EARTH;
    }

    public String getInfo() {
        return String.format("%s Strength: %d / %d Element: %s",super.getInfo(), this.strength, this.maxStrength, this.element);
    }
    public void rest(){
        if(this.status==Statuses.ALIVE){
            super.rest();
            if(this.strength<this.maxStrength){
                this.strength = r + this.strength;
                if(this.strength>this.maxStrength){
                    this.strength=this.maxStrength;
                }
                System.out.println(String.format("\n%s rests and restores %d strength \n %s's strength: %d / %d",this.name, r,this.name,this.strength,this.maxStrength));
            } else {System.out.println(String.format("\n%s doesn't need rest", this.name));
            }
        }
    }
    public void attack(Prototype target){
        super.attack(target);
    };
    public void skillAttack(Prototype target){
        super.skillAttack(target);
        if(this.strength<damage){ 
            System.out.println("Out of strength.");
        } else{
            target.getDamage(damage);
            this.strength-=damage;
            System.out.println(String.format("%s's strength: %d / %d",this.name, this.strength, this.maxStrength));
        }
    }
    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        if(this.condition==Conditions.NORMAL){
            Prototype enemy = this.findNearestEnemy(b);
            int dis=(int)Coordinates.getDistance(this.coordinates, enemy.coordinates);
            if(dis>=2){
                System.out.print(", but out of reach");
            }
            System.out.print("\n");
            System.out.print("Your choice:");
            int p =(int)input.nextInt();
            switch(p){
                case 1:
                if(dis<2){
                    this.attack(enemy);
                } else {
                    System.out.println("Your enemy is too far away");
                }
                break;
                case 2:
                if(dis<2){
                    this.skillAttack(enemy);
                } else {
                    System.out.println("Your enemy is too far away");
                }
                break;
                case 3:
                if(dis>=2){
                    if(this.coordinates.getX()>enemy.coordinates.getX()&&this.coordinates.getY()==enemy.coordinates.getY()){
                        this.coordinates.move("UP");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()<enemy.coordinates.getX()&&this.coordinates.getY()==enemy.coordinates.getY()){
                        this.coordinates.move("DOWN");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()==enemy.coordinates.getX()&&this.coordinates.getY()>enemy.coordinates.getY()){
                        this.coordinates.move("LEFT");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()==enemy.coordinates.getX()&&this.coordinates.getY()<enemy.coordinates.getY()){
                        this.coordinates.move("RIGHT");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()>enemy.coordinates.getX()&&this.coordinates.getY()>enemy.coordinates.getY()){
                        this.coordinates.move("UPPERLEFT");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()<enemy.coordinates.getX()&&this.coordinates.getY()<enemy.coordinates.getY()){
                        this.coordinates.move("LOWERRIGHT");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()>enemy.coordinates.getX()&&this.coordinates.getY()<enemy.coordinates.getY()){
                        this.coordinates.move("UPPERRIGHT");
                        System.out.print(", moving closer to enemy\n");
                    }else if(this.coordinates.getX()<enemy.coordinates.getX()&&this.coordinates.getY()>enemy.coordinates.getY()){
                        this.coordinates.move("LOWERLEFT");
                        System.out.print(", moving closer to enemy\n");
                    }else {
                        System.out.println("Your enemy is close enough to attack");
                    }
                }
                break;
                case 4:
                    this.rest();
                    break;
                case 5:
                    this.inventory.showInventory();
                    break;
                case 6:
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
                            break;
                        }
                        break;
                case 7:
                    super.buy();
                    break;
                case 8:
                    String l =super.useItem(); 
                    switch (l) {
                        case "Health potion":
                            this.hp+=20;
                            this.inventory.items.replace("Health potion", this.inventory.items.get("Health potion"),this.inventory.items.get("Health potion")-1);
                            break;
                        case "Mana potion":
                            System.out.print("Player doesn't use attribute of this type");
                            break;
                        case "Strength potion":
                            this.strength+=20;
                            this.inventory.items.replace("Strength potion", this.inventory.items.get("Strength potion"),this.inventory.items.get("Strength potion")-1);
                            break;
                        case "Energy potion":
                            System.out.print("Player doesn't use attribute of this type");
                            break;
                        case "Magic potion":
                            System.out.print("Player doesn't use attribute of this type");
                            break;
                        case "Arrow":
                            super.giveArrow(a);
                            break;
                        default:
                            System.out.println("Input error");
                            break;
                    }
                    break;
                case 9:
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
