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
    }
    public void step(List<Prototype>a,List<Prototype>b){
        super.step(a,b);
        Prototype enemy = this.findNearestEnemy(b);
        System.out.print("\n");
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
    
}