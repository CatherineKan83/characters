
import java.util.List;
import java.util.Scanner;

public class Shooter extends Prototype implements Ranger{
    private int magic;
    private int maxMagic;
    private Elements element;
    Scanner input = new Scanner(System.in);


    public Shooter(){    
        super("", Shooter.n.nextInt(100,300), Statuses.ALIVE, Conditions.NORMAL, Races.ELF, Shooter.n.nextInt(1,100), Genders.MALE, Skills.POISONING, Weapons.CROSSBOW, 3);
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
                System.out.println(String.format("\n%s rests and restores %d magic \n%s's magic: %d / %d ",this.name, r,this.name, this.magic, this.maxMagic));
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
                    case "energy potion":
                        System.out.print("Player doesn't use attribute of this type");
                        break;
                    case "Magic potion":
                        this.magic+=20;
                        this.inventory.items.replace("Magic potion", this.inventory.items.get("Magic potion"),this.inventory.items.get("Magic potion")-1);
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