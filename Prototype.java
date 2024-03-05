import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Prototype{
    protected static int number;
    protected static Random n;
    protected static int count;
     
    protected String name;
    protected int maxHp;
    protected int hp;
    protected Statuses status;
    protected Races race;
    protected int age;
    protected Genders gender;
    protected Skills skill;
    protected Weapons weapon; 
    protected int damage;
    protected int r;
    protected int x;
    protected int y;
    protected int initiative;
    
    protected enum Statuses{ALIVE,DEAD}
    protected enum Races {HUMAN,ELF,DWARF,LIZARD,UNDEAD,DEMON,BEAST}
    protected enum Genders {FEMALE,MALE}
    protected enum Elements {EARTH,AIR,FIRE,WATER,DARKNESS,POISON,THUNDER,LIGHT}
    protected enum Skills {SUMMONING,EXPLOSION,POISONING,MINDCONTROL,TANK,LEVITATION,FIREBREATH,HEADSHOT,STUN,INJURY,BLINDING}
    protected enum Weapons {DAGGER,SWORD,AXE,HAMMER,FLAIL,SPEAR,BRAID,WHIP,BOW,WAND,STAFF,CROSSBOW}
    
    Coordinates coordinates = new Coordinates() {
    };
    SetName setName = new SetName();
    Items inventory = new Items();
    Scanner input = new Scanner(System.in);
    static{
        Prototype.number = 0;
        Prototype.n = new Random();
    }
    
    public Prototype(String name, int hp, Statuses status, Races race, int age, Genders gender, Skills skill, Weapons weapon,int initiative){
        this.name = setName.setName();
        this.hp = hp;
        this.maxHp = hp;
        this.status = status;
        this.race = race;
        this.age = age;
        this.gender = gender;
        this.skill = skill;
        this.weapon = weapon;
        this.initiative = initiative;
    }
    
    public String getInfo(){
        return String.format("%s Class: %s HP: %d / %d Coordinates:{ %d:%d }", this.name, this.getClass().getName(),this.hp, this.maxHp,this.coordinates.getX(),this.coordinates.getY());
    }
    public int getHp(){
        return this.hp;
    }
    public int getInitiative(){
        return this.initiative;
    }
    public void getDamage(int damage) {
        if(this.status==Statuses.ALIVE){
            if(this.hp>=0){
                this.hp -= damage;
                System.out.println(String.format("%s -%d hp",this.name,damage));
                if(this.hp<0){
                    this.hp=0;
                    die();
                }
            }            
        }else{
            System.out.println(String.format("%s is already dead.",this.name));
        }
    }
    public void attack(Prototype target){
        if(target.status==Statuses.ALIVE){
            int damage = Prototype.n.nextInt(10,30);
            System.out.println(String.format("\n%s attacks %s",this.name, target.name));
            target.getDamage(damage);
        } else{System.out.println(String.format("%s skips move",this.name));}
    }
    public void flyingKick(Prototype target){
        int dis = (int)Coordinates.getDistance(this.coordinates,target.coordinates);
        if(this.coordinates.getY()>target.coordinates.getY()){
            int damage = Prototype.n.nextInt(10,25)+dis;
            System.out.println(String.format("\n%s attacks %s",this.name, target.name));
            target.getDamage(damage);
        }else if(this.coordinates.getY()<target.coordinates.getY()){
            int damage = Prototype.n.nextInt(10,20)-dis;
            if(damage<0){
                damage=0;
                System.out.println(String.format("\n%s failes to attack %s",this.name, target.name));
            }
            System.out.println(String.format("\n%s attacks %s",this.name, target.name));
            target.getDamage(damage);
        }else if(this.coordinates.getY()==target.coordinates.getY()){
            damage = Prototype.n.nextInt(10,25);
            System.out.println(String.format("\n%s attacks %s",this.name, target.name));
            target.getDamage(damage);
        }
    }
    public void shoot(Prototype target){
        if(target.status==Statuses.ALIVE){
            int dis = (int)Coordinates.getDistance(this.coordinates,target.coordinates);
            if(this.inventory.items.containsKey("arrow")){
                if(this.coordinates.getY()>target.coordinates.getY()){
                    int damage = Prototype.n.nextInt(10,30)+dis;
                    System.out.println(String.format("\n%s attacks %s",this.name, target.name));
                    target.getDamage(damage);
                    this.inventory.items.replace("arrow", this.inventory.items.get("arrow"),this.inventory.items.get("arrow")-1);
                }else if(this.coordinates.getY()<target.coordinates.getY()){
                    int damage = Prototype.n.nextInt(10,30)-dis;
                    if(damage<0){
                        damage=0;
                        System.out.println(String.format("\n%s failes to attack %s",this.name, target.name));
                    }
                    System.out.println(String.format("\n%s attacks %s",this.name, target.name));
                    target.getDamage(damage);
                    this.inventory.items.replace("arrow", this.inventory.items.get("arrow"),this.inventory.items.get("arrow")-1);
                }else if(this.coordinates.getY()==target.coordinates.getY()){
                    damage = Prototype.n.nextInt(10,30);
                    System.out.println(String.format("\n%s attacks %s",this.name, target.name));
                    target.getDamage(damage);
                    this.inventory.items.replace("arrow", this.inventory.items.get("arrow"),this.inventory.items.get("arrow")-1);
                }
            }else {
                System.out.println(String.format("\n%s attempts to shoot %s down, but he's out of arrows",this.name,target.name));
            }
        }
    }
    public void skillAttack(Prototype target){
        if(target.status==Statuses.ALIVE){
            if(this.skill==Skills.FIREBREATH){
                damage = Prototype.n.nextInt(20,35);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.FIREBREATH,target.name));
            }else if(this.skill==Skills.EXPLOSION){
                damage = Prototype.n.nextInt(25,37);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.EXPLOSION,target.name));
            }else if(this.skill==Skills.HEADSHOT){
                damage = Prototype.n.nextInt(15,30);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.HEADSHOT,target.name));
            }else if(this.skill==Skills.INJURY){
                damage = Prototype.n.nextInt(10,35);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.INJURY,target.name));
            }else if(this.skill==Skills.LEVITATION){
                damage = Prototype.n.nextInt(15,36);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.LEVITATION,target.name));
            }else if(this.skill==Skills.MINDCONTROL){
                damage = Prototype.n.nextInt(10,30);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.MINDCONTROL,target.name));
            }else if(this.skill==Skills.POISONING){
                damage = Prototype.n.nextInt(5,15);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.POISONING,target.name));
            }else if(this.skill==Skills.STUN){
                damage = Prototype.n.nextInt(20,40);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.STUN,target.name));
            }else if(this.skill==Skills.SUMMONING){
                damage = Prototype.n.nextInt(15,30);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.SUMMONING,target.name));
            }else if(this.skill==Skills.TANK){
                damage = Prototype.n.nextInt(30,40);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.TANK,target.name));
            }else if(this.skill==Skills.BLINDING){
                damage = Prototype.n.nextInt(30,40);
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.BLINDING,target.name));
            }
        } else {System.out.println(String.format("%s skips move",this.name));}
    }
    public void die() {
        this.status=Statuses.DEAD;
        System.out.println(String.format("%s is dead",this.name));
    }
    public void stealMoney(Prototype target){
        if(target.status == Statuses.DEAD){
            this.inventory.money += + target.inventory.money;
            System.out.println(String.format("\n%s steals  %d gold from %s\n%s : %d gold",this.name, target.inventory.money, target.name,this.name, this.inventory.money));
        } else {
            int t = Prototype.n.nextInt(1,15);
            this.inventory.money +=t;
            target.inventory.money -=t;
            System.out.println(String.format("\n%s steals  %d gold from %s\n%s : %d gold",this.name, t, target.name,this.name, this.inventory.money));
        } 
    }
    public void heal(List<Prototype>a){
        int hp = Prototype.n.nextInt(10,35);
        for(Prototype prototype:a){
            if(prototype.status==Statuses.ALIVE){
                if(prototype.hp<prototype.maxHp){
                    prototype.hp = prototype.hp+hp;
                    if(prototype.hp>prototype.maxHp){
                        prototype.hp=prototype.maxHp;
                    }
                        System.out.println(String.format("\n%s heals up and restores %d HP",prototype.name, hp));
                } else {
                    System.out.println(String.format("\n%s doesn't need healing", prototype.name));
                }
            } else {System.out.println(String.format("%s is dead and cannot be healed",prototype.name));}
        break;
        }
    }   
    
    public void resurrect(List<Prototype>a){
        for(Prototype prototype:a){
            if(prototype.status==Statuses.DEAD){
                System.out.println(String.format("\n%s attempts to resurrect %s",this.name,prototype.name));
                prototype.status = Statuses.ALIVE;
                prototype.hp = 50;
                System.out.println(String.format("%s comes back to life and restoores %d",prototype.name,prototype.hp));
                break;
            } else{
                System.out.println("No one to resurrect");
            }
            break;
        }
    }
    public void rest() {
        r=Prototype.n.nextInt(10,35);
    }

    public Prototype findNearestEnemy(List<Prototype>a){
        double min = Coordinates.getDistance(this.coordinates,a.get(0).coordinates);
        Prototype enemy= a.get(0);
        for(Prototype b:a){
            if(b.status==Statuses.ALIVE){
                double dis = Coordinates.getDistance(this.coordinates,b.coordinates);
                if(dis<min){
                    dis = min;
                    enemy = b;
                }
            }
        }
        if(enemy.status==Statuses.ALIVE){
            System.out.print(String.format("\n%s is nearest enemy to %s",enemy.name,this.name));
        } else{
            System.out.print("The nearest enemy is dead. Move around and search for enemies.");
        }
        return enemy;
    }
    
    public void step(List<Prototype>a,List<Prototype>b){
        if(this.status==Statuses.ALIVE){
            if (this instanceof Warrior) {
                System.out.println(String.format("\n%s's move. Choose action:\n1->attack closest enemy\n2->move closer to enemy\n3->rest\n4->check inventory\n5->move",this.name));
            } else if (this instanceof Mage && !(this instanceof Healer)) {
                System.out.println(String.format("\n%s's move. Choose action:\n1->use skill attack on closest enemy\n2->rest\n3->check inventory\n4->resurrect your dead ally\n5->move",this.name));                
            } else if (this instanceof Healer) {
                System.out.println(String.format("\n%s's move. Choose action:\n1->use skill attack on closest enemy\n2->rest\n3->check inventory\n4->heal your ally\n5->move",this.name));                
            } else if (this instanceof Ranger) {
                System.out.println(String.format("\n%s's move. Choose action:\n1->attack closest enemy\n2->rest\n3->check inventory\n4->move",this.name));                
            }else if (this instanceof Martial) {
                System.out.println(String.format("\n%s's move. Choose action:\n1->use skill attack on closest enemy\n2->rest\n3->check inventory\n4->move",this.name));                
            }else if (this instanceof Wanderer) {
                System.out.println(String.format("\n%s's move. Choose action:\n1->attack closest enemy\n2->move closer to enemy\n3->rest\n4->check inventory\n5->move",this.name));
            }  
        }
    }


    public static void round(List<Prototype>a,List<Prototype>b){
        int dead=0;
        boolean win= false;
        Prototype.queue(a);
        Prototype.queue(b);
        View.view();
        while(win!=true){
            for (Prototype prototype : a) {
                if(prototype.status==Statuses.ALIVE){
                    prototype.step(a,b);
                    View.view();
                    for (Prototype enemy : b) {
                        if(enemy.status==Statuses.DEAD){
                            dead+=1;
                            if(dead==b.size()){
                                System.out.println("Team 1 wins!");
                                win=true;
                            }
                        }
                    }
                    dead = 0;
                }
            }
            for (Prototype prototype : b) {
                if(prototype.status==Statuses.ALIVE){
                    prototype.step(b,a);
                    View.view();
                    for (Prototype enemy : a) {
                        if(enemy.status==Statuses.DEAD){
                            dead+=1;
                            if(dead==a.size()){
                                System.out.println("Team 2 wins!");
                                win = true;
                            }
                        }
                    }
                    dead = 0;
                }
            }
        }
    }

    public static List<Prototype> queue(List<Prototype>team){
        for(int j =0;j<team.size();j++){
            for(int i =0;i<team.size()-1;i++){
                if(team.get(i).initiative<team.get(i+1).initiative){
                    var temp = team.get(i+1);
                    team.set(i+1, team.get(i));
                    team.set(i,temp);
                }
            }   
        }
        return team;
    }

}