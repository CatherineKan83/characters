import java.util.List;
import java.util.Scanner;

public class Monk extends Prototype implements Martial{
    private int mana;
    private int maxMana;
    private Elements element;
    Scanner input = new Scanner(System.in);
    
    public Monk(){    
        super("", Monk.n.nextInt(100,200), Statuses.ALIVE, Races.HUMAN, Monk.n.nextInt(1,100), Genders.FEMALE, Skills.LEVITATION, Weapons.STAFF,2);
        this.maxMana = Monk.n.nextInt(100, 350);
        this.mana = maxMana;
        this.element = Elements.WATER;
    }

    public String getInfo() {
        return String.format("%s Mana: %d /%d Element: %s",super.getInfo(), this.mana, this.maxMana, this.element);
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
    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        Prototype enemy = this.findNearestEnemy(b);
        System.out.print("\n");
        System.out.print("Your choice:");
        int p =(int)input.nextInt();
        switch(p){
        case 1:
            this.flyingKick(enemy);
            break;
        case 2:
            this.rest();
            break;
        case 3:
            this.inventory.showInventory();
            break;
        case 4:
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
        }
    } 
    public void flyingKick(Prototype target){
        super.flyingKick(target);
    }
    
    @Override
    public String toString() {
        return getInfo();
    }
    
}
