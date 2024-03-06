import java.util.List;
import java.util.Scanner;

public class Villager extends Prototype implements Wanderer{
    private int energy;
    private int maxEnergy;
    Scanner input = new Scanner(System.in);

    public Villager(){    
        super("", Villager.n.nextInt(100,200),Statuses.ALIVE,Races.HUMAN, Villager.n.nextInt(1,100), Genders.MALE, Skills.STUN, Weapons.HAMMER,0);
        
        this.maxEnergy = Villager.n.nextInt(50, 150);
        this.energy = maxEnergy;
    }

    public String getInfo() {
        return String.format("%s Strength: %d / %d",super.getInfo(), this.energy, this.maxEnergy);
    }
    public void rest(){
        if(this.status==Statuses.ALIVE){
            super.rest();
            if(this.energy<this.maxEnergy){
                this.energy = r + this.energy;
                if(this.energy>this.maxEnergy){
                    this.energy=this.maxEnergy;
                }
                System.out.println(String.format("\n%s rests and restores %d strength \n%s's strength: %d / %d",
                this.name, r,this.name,this.energy,this.maxEnergy));
            } else {System.out.println(String.format("\n%s doesn't need rest", this.name));
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
            case 6:
                super.giveArrow(a);
                break;
            }
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
    
}
