import java.util.List;
import java.util.Scanner;

public class Spearman extends Prototype implements Warrior{
    protected int strength;
    protected int maxStrength;
    protected Elements element;
    Scanner input = new Scanner(System.in);
    
    public Spearman(){    
        super("", Spearman.n.nextInt(100,300),Statuses.ALIVE, Races.BEAST, Spearman.n.nextInt(1,100), Genders.MALE, Skills.TANK, Weapons.SPEAR,2);
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
            System.out.println("Out of mana.");
        } else{
            target.getDamage(damage);
            this.strength-=damage;
            System.out.println(String.format("%s's mana: %d / %d",this.name, this.strength, this.maxStrength));
        }
    }
    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        Prototype enemy = this.findNearestEnemy(b);
        int dis=(int)Coordinates.getDistance(this.coordinates, enemy.coordinates);
        if(dis>=2){
            System.out.print(", but out of reach");
        }
        System.out.print("\n");
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
                p =(int)input.nextInt();
                if(p==1){
                    this.coordinates.move("UP");
                } else if(p==2){
                    this.coordinates.move("DOWN");
                } else if(p==3){
                    this.coordinates.move("LEFT");
                } else if(p==4){
                    this.coordinates.move("RIGHT");
                } else if(p==5){
                    this.coordinates.move("UPPERLEFT");
                } else if(p==6){
                    this.coordinates.move("UPPERRIGHT");
                } else if(p==7){
                    this.coordinates.move("LOWERLEFT");
                } else if(p==8){
                    this.coordinates.move("LOWERRIGHT");
                }
                break;
            }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
}
