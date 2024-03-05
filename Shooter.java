import java.util.List;
import java.util.Scanner;

public class Shooter extends Prototype implements Ranger{
    private int magic;
    private int maxMagic;
    private Elements element;
    Scanner input = new Scanner(System.in);


    public Shooter(){    
        super("", Shooter.n.nextInt(100,300), Statuses.ALIVE, Races.ELF, Shooter.n.nextInt(1,100), Genders.MALE, Skills.POISONING, Weapons.CROSSBOW, 3);
        this.maxMagic = Shooter.n.nextInt(100, 300);
        this.magic = maxMagic;
        this.element = Elements.POISON;
    }

    public String getInfo() {
        return String.format("%s Magic: %d / %d Element: %s",super.getInfo(), this.magic, this.maxMagic, this.element);
    }
    public void rest(){
        if(this.status==Statuses.ALIVE){
            super.rest();

            if(this.magic<this.maxMagic){
                this.magic = r + this.magic;
                if(this.magic>this.maxMagic){
                    this.magic=this.maxMagic;
                }
                System.out.println(String.format("\n%s rests and restores %d magic \n%s's magic: %d / %d arrows: %d",this.name, r,this.name, this.magic, this.maxMagic, this.inventory.items.get("arrow")));
            }else {
                System.out.println(String.format("\n%s doesn't need rest", this.name));
            }
        }
    }     
    public void skillAttack(Prototype target){
        super.skillAttack(target);
            for(int i=0,j=3;i<j;i++,j--){
                System.out.println(String.format("Usages left: %d",j-1));
                if(this.magic<damage){ 
                    System.out.println("Out of mana.");
                } else{
                    target.getDamage(damage);
                    this.magic-=damage;
                    System.out.println(String.format("%s's mana: %d / %d",this.name, this.magic, this.maxMagic));
                }
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
        Prototype enemy = this.findNearestEnemy(b);
        System.out.print("\n");
        int p =(int)input.nextInt();
        switch(p){
        case 1:
            this.shoot(enemy);
            break;
        case 2:
            this.rest();
            break;
        case 3:
            this.inventory.showInventory();
            break;
        case 4:
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