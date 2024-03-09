
import java.util.List;
import java.util.Scanner;

public class Bandit extends Prototype implements Wanderer{ 
    private int energy;
    private int maxEnergy;
    private Elements element;
    Scanner input = new Scanner(System.in);
    
    
    public Bandit(){    
        super("", Bandit.n.nextInt(100,250), Statuses.ALIVE, Races.HUMAN, Bandit.n.nextInt(1,100), Genders.FEMALE, Skills.BLINDING, Weapons.BOW,0);
        this.maxEnergy = Bandit.n.nextInt(50, 200);
        this.energy = maxEnergy;
        this.element = Elements.LIGHT;
    }

    public String getInfo() {
        return String.format("%s Energy: %d / %d Element: %s",super.getInfo(), this.energy, this.maxEnergy, this.element);
    }
    public void rest(){
        if(this.status==Statuses.ALIVE){
            super.rest();
            if(this.energy<this.maxEnergy){
                this.energy = r + this.energy;
                if(this.energy>this.maxEnergy){
                    this.energy=this.maxEnergy;
                }
                System.out.println(String.format("\n%s rests and restores %d energy \n%s's energy: %d / %d",this.name, r,this.name, this.energy,this.maxEnergy));
            } else {
                System.out.println(String.format("\n%s doesn't need rest", this.name));
            }
        }
    }
    public void skillAttack(Prototype target){
        super.skillAttack(target);
        if(this.energy<damage){ 
            System.out.println("Out of mana.");
        } else{
            target.getDamage(damage);
            this.energy-=damage;
            System.out.println(String.format("%s's mana: %d / %d",this.name, this.energy, this.maxEnergy));
        }
    }
    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        Prototype enemy = this.findNearestEnemy(b);
        int dis=(int)Coordinates.getDistance(this.coordinates, enemy.coordinates);
        if(dis>=2){
            System.out.print(", but out of reach\n");
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
                    break;

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
                    System.out.print("Player doesn't use attribute of this type");
                    break;
                case "Strength potion":
                    System.out.print("Player doesn't use attribute of this type");
                    break;
                case "Energy potion":
                    this.energy+=20;
                    this.inventory.items.replace("Energy potion", this.inventory.items.get("Energy potion"),this.inventory.items.get("Energy potion")-1);
                    break;
                case "Magic potion":
                    System.out.print("Player doesn't use attribute of this type");
                    break;
                case "Arrow":
                    super.giveArrow(a);
                default:
                    System.out.println("Input error");
                    break;
            }
            break;
            
            case 8:
                super.giveItem(a);
                break;

            }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}
