
import java.util.List;
import java.util.Scanner;

public class Warlock extends Prototype implements Mage{
    protected int mana;
    protected int maxMana;
    protected Elements element;
    Scanner input = new Scanner(System.in);
    
    public Warlock(){    
        super("", Warlock.n.nextInt(100,300), Statuses.ALIVE,  Races.HUMAN, Warlock.n.nextInt(1,100), Genders.MALE, Skills.EXPLOSION, Weapons.CROSSBOW,1);
        this.maxMana = Warlock.n.nextInt(100, 300);
        this.mana = maxMana;
        this.element = Elements.AIR;
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
                System.out.println(String.format("\n%s rests and restores %d mana \n%s's mana: %d / %d",this.name, r,this.name,this.mana,this.maxMana));
            }else {System.out.println(String.format("\n%s doesn't need rest", this.name));
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

    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        if(!(this instanceof Healer)){
            Prototype enemy = this.findNearestEnemy(b);
            System.out.print("\n");
            System.out.print("Your choice:");
            int p =(int)input.nextInt();
            switch(p){
            case 1:
                ((Mage)this).skillAttack(enemy);
                break;
            case 2:
                this.rest();
                break;
            case 3:
                this.inventory.showInventory();
                break;
            case 4:
                ((Mage)this).resurrect(a);
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
            case 6:
                super.buy();
            }
        }
    }  
    public void resurrect(List<Prototype>a){
        super.resurrect(a);
        for(Prototype prototype:a){
            if(prototype.status==Statuses.DEAD){
                System.out.println(String.format("\n%s attempts to resurrect %s",this.name,prototype.name));
                prototype.status = Statuses.ALIVE;
                prototype.hp = 25;
                this.mana-=10;
                System.out.println(String.format("%s comes back to life and restoores %d",prototype.name,prototype.hp));
                break;
            } else{
                System.out.println("No one to resurrect");
            }
            break;
        }
    }
    
    @Override
    public String toString() {
        return getInfo();
    
    }
}
