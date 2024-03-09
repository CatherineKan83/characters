
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Druid extends Warlock implements Healer{
    public Druid(){
        super();
        this.element = Elements.LIGHT;
        this.skill = Skills.MINDCONTROL;
    }
    Scanner input = new Scanner(System.in);

    @Override
    public void heal(List<Prototype>a){
        super.heal(a);
        HashMap<Integer,Prototype>l = new HashMap<>();
        int c =0;
        boolean check = false;
        for(Prototype prototype:a){
            if(prototype.status==Statuses.ALIVE){
                l.put(c+=1,prototype);
            }
        }
        for (Integer key : l.keySet()) {
            String value = l.get(key).name;
            System.out.println(key + "->" + value);
        }
        if(this.mana>=hp){
            while(check==false){
                int j = input.nextInt();
                if(l.containsKey(j)){
                    if(l.get(j).hp<l.get(j).maxHp){
                        l.get(j).hp = l.get(j).hp+hp;
                        if(l.get(j).hp>l.get(j).maxHp){
                            l.get(j).hp=l.get(j).maxHp;
                            this.mana-=hp;
                        }
                        System.out.println(String.format("\n%s heals up and restores %d HP",l.get(j).name, hp));
                    } else {
                        System.out.println(String.format("\n%s doesn't need healing", l.get(j).name));
                    }
                    check=true;
                } else{
                    System.out.println("Input error");
                }
            }
        } else{
            System.out.print("Not enough mana");
            this.mana+=1;
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
            ((Mage)this).skillAttack(enemy);
            break;
        case 2:
            this.rest();
            break;
        case 3:
            this.inventory.showInventory();
            break;
        case 4:
            this.heal(a);
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
                this.mana+=20;
                this.inventory.items.replace("Mana potion", this.inventory.items.get("Mana potion"),this.inventory.items.get("Mana potion")-1);
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
                super.giveArrow(a);
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
    } 
    
}
