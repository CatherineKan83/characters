

public class Knight extends Spearman {
    public Knight(){
        super();
        this.element = Elements.FIRE;
        this.skill = Skills.STUN;
    }
    @Override
    public void skillAttack(Prototype target){
        super.skillAttack(target);
        // if(this.strength<damage){ 
        //     System.out.println("Out of energy.");
        // } else{
        //     target.getDamage(damage);
        //     this.strength-=damage;
            target.condition = Conditions.STUNNED;
            System.out.println(String.format("%s is stunned",target.name));
        // }
    }
}
