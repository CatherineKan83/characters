
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public abstract class Prototype{
    protected static int number;
    protected static Random n;
    protected static int count;
    protected static HashMap<String,Integer> shopitems = new HashMap<>();
     
    protected String name;
    protected int maxHp;
    protected int hp;
    protected Statuses status;
    protected Conditions condition;
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
    protected boolean check;
    
    protected enum Statuses{ALIVE,DEAD}
    protected enum Conditions{NORMAL,STUNNED,BLINDED}
    protected enum Races {HUMAN,ELF,DWARF,LIZARD,UNDEAD,DEMON,BEAST}
    protected enum Genders {FEMALE,MALE}
    protected enum Elements {EARTH,AIR,FIRE,WATER,DARKNESS,POISON,THUNDER,LIGHT}
    protected enum Skills {SUMMONING,EXPLOSION,POISONING,MINDCONTROL,TANK,LEVITATION,FIREBREATH,HEADSHOT,STUN,INJURY,BLINDING}
    protected enum Weapons {DAGGER,SWORD,AXE,HAMMER,FLAIL,SPEAR,BRAID,WHIP,BOW,WAND,STAFF,CROSSBOW}
    
    protected Coordinates coordinates = new Coordinates() {
    };
    SetName setName = new SetName();
    protected Items inventory = new Items();
    Scanner input = new Scanner(System.in);    
    static{
        Prototype.number = 0;
        Prototype.n = new Random();
    }
    
    public Prototype(String name, int hp, Statuses status, Conditions condition, Races race, int age, Genders gender, Skills skill, Weapons weapon,int initiative){
        this.name = setName.setName();
        this.hp = hp;
        this.maxHp = hp;
        this.status = status;
        this.condition = condition;
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
            if(this.inventory.items.containsKey("arrow") && this.inventory.items.get("arrow")>0){
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
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.FIREBREATH,target.name));
            }else if(this.skill==Skills.EXPLOSION){
                damage = Prototype.n.nextInt(25,37);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.EXPLOSION,target.name));
            }else if(this.skill==Skills.HEADSHOT){
                damage = Prototype.n.nextInt(15,30);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.HEADSHOT,target.name));
            }else if(this.skill==Skills.INJURY){
                damage = Prototype.n.nextInt(10,35);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.INJURY,target.name));
            }else if(this.skill==Skills.LEVITATION){
                damage = Prototype.n.nextInt(15,36);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.LEVITATION,target.name));
            }else if(this.skill==Skills.MINDCONTROL){
                damage = Prototype.n.nextInt(10,30);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.MINDCONTROL,target.name));
            }else if(this.skill==Skills.POISONING){
                damage = Prototype.n.nextInt(5,15);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.POISONING,target.name));
            }else if(this.skill==Skills.STUN){
                damage = Prototype.n.nextInt(20,40);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.STUN,target.name));
            }else if(this.skill==Skills.SUMMONING){
                damage = Prototype.n.nextInt(15,30);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.SUMMONING,target.name));
            }else if(this.skill==Skills.TANK){
                damage = Prototype.n.nextInt(30,40);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.TANK,target.name));
            }else if(this.skill==Skills.BLINDING){
                damage = Prototype.n.nextInt(30,40);
                if(this.condition==Conditions.BLINDED){
                    damage-=10;
                    this.condition=Conditions.NORMAL;
                }
                System.out.println(String.format("\n%s uses %s on %s",this.name, Skills.BLINDING,target.name));
            }
        }
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
        hp = Prototype.n.nextInt(5,20);
    }   
    
    public void resurrect(List<Prototype>a){

    }
    public void giveArrow(List<Prototype>a){
        HashMap <Integer, Prototype>l =new HashMap<>(); 
        int i =1;
        check = false;
        for(Prototype prototype: a){
            if(prototype instanceof Ranger){
                l.put(i,prototype);
                i++;
            }
        }
        if(l.isEmpty()){
            System.out.println("No one to give it to");
        } else{
            for (Integer key : l.keySet()) {
                String value = l.get(key).name;
                System.out.println(key + "->" + value);
            }
            System.out.print("Your choice:");
            while(check==false){
                int c = input.nextInt();
                if(l.containsKey(c)){
                    l.get(c).inventory.items.replace("arrow", l.get(c).inventory.items.get("arrow"),l.get(c).inventory.items.get("arrow")+1);
                    check=true;
                } else{
                    System.out.println("Input error");
                }
            }
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
    public static HashMap<String,Integer> setItems(){
        shopitems.put("health potion",20);
        shopitems.put("mana potion",20);
        shopitems.put("energy potion",20);
        shopitems.put("strength potion",20);
        shopitems.put("magic potion",20);
        shopitems.put("arrow",5);
        return shopitems;
    }
    public void buy(){
        check = false;
        int count =0;
        HashMap<Integer,String>list = new HashMap<>();
        for (String key : shopitems.keySet()) {
            count+=1;
            int value = shopitems.get(key);
            System.out.println(count + "->" + key + " cost:" + value);
            list.put(count,key);
        }
        while(check==false){
            int c = input.nextInt();
            if(list.containsKey(c)){
                if(this.inventory.items.containsKey(list.get(c))){
                    this.inventory.items.replace(list.get(c), this.inventory.items.get(list.get(c)),this.inventory.items.get(list.get(c))+1);
                    this.inventory.money-=shopitems.get(list.get(c));
                    check=true;
                }else{
                    this.inventory.addItem(list.get(c), 1);;
                    this.inventory.money-=shopitems.get(list.get(c));
                    check=true;
                }
            } else{
                System.out.println("Input error");
            }
        }
    }
    public String useItem(){
        count=0;
        HashMap<Integer, String> list = new HashMap<>();
        for (String key : this.inventory.items.keySet()) {
            count+=1;
            int quantity = this.inventory.items.get(key);
            System.out.println(count + "->" + key + " --- " + quantity + " left");
            list.put(count,key);
        }
        int c = input.nextInt();
        System.out.print(list.get(c));
        return list.get(c);
    }
    public void giveItem(List<Prototype>a){
        HashMap <Integer, Prototype>l =new HashMap<>(); 
        int i =1;
        check = false;
        count=0;
        HashMap<Integer, String> list = new HashMap<>();
        for (String key : this.inventory.items.keySet()) {
            count+=1;
            int quantity = this.inventory.items.get(key);
            System.out.println(count + "->" + key + " --- " + quantity + " left");
            list.put(count,key);
        }
        int c = input.nextInt();
        for(Prototype prototype: a){
            if(prototype!=this){
                l.put(i,prototype);
                i++;
            }
        }
        if(l.isEmpty()){
            System.out.println("No one to give item to");
        } else{
            for (Integer key : l.keySet()) {
                String value = l.get(key).name;
                System.out.println(key + "->" + value);
            }
            System.out.print("Your choice:");
            while(check==false){
                int t = input.nextInt();
                if(l.containsKey(c)){
                    if(this.inventory.items.containsKey(list.get(c)))
                    l.get(t).inventory.items.replace(list.get(c), l.get(t).inventory.items.get(list.get(c)),l.get(t).inventory.items.get(list.get(c))+1);
                    this.inventory.items.replace(list.get(c), this.inventory.items.get(list.get(c)),this.inventory.items.get(list.get(c))-1);
                    if(this.inventory.items.get(list.get(c))==0){
                        this.inventory.throwItemAway(list.get(c));
                    }
                    check=true;
                } else{
                    System.out.println("Input error");
                }
            }
        }
    }
    public void step(List<Prototype>a,List<Prototype>b){
        if(this.status==Statuses.ALIVE){
            if(this.condition!=Conditions.STUNNED){
                if (this instanceof Warrior) {
                    System.out.println(String.format("\n%s's move. Choose action:\n1->attack closest enemy\n2->use skill attack on closest enemy\n3->move closer to enemy\n4->rest\n5->check inventory\n6->move\n7->buy\n8->use item\n9->give item",this.name));
                } else if (this instanceof Mage && !(this instanceof Healer)) {
                    System.out.println(String.format("\n%s's move. Choose action:\n1->use skill attack on closest enemy\n2->rest\n3->check inventory\n4->resurrect your dead ally\n5->move\n6->buy\n7->use item\n8->give item",this.name));                
                } else if (this instanceof Healer) {
                    System.out.println(String.format("\n%s's move. Choose action:\n1->use skill attack on closest enemy\n2->rest\n3->check inventory\n4->heal your ally\n5->move\n6->buy\n7->use item\n8->give item",this.name));                
                } else if (this instanceof Ranger) {
                    System.out.println(String.format("\n%s's move. Choose action:\n1->attack closest enemy\n2->use skill attack on closest enemy\n3->rest\n4->check inventory\n5->move\n6->buy\n7->use item\n8->give item",this.name));                
                }else if (this instanceof Martial) {
                    System.out.println(String.format("\n%s's move. Choose action:\n1->use flying kick attack on closest enemy\n2->use skill attack on closest enemy\n3->rest\n4->check inventory\n5->move\n6->buy\n7->use item\n8->give item",this.name));                
                }else if (this instanceof Wanderer) {
                    System.out.println(String.format("\n%s's move. Choose action:\n1->attack closest enemy\n2->use skill attack on closest enemy\n3->move closer to enemy\n4->rest\n5->check inventory\n6->move\n7->buy\n8->use item\n9->give item",this.name));
                }
            }else{
                System.out.println(String.format("\n%s skips the move because he is %s", this.name,this.condition));
            }
        }
    }


    public static void round(List<Prototype>a,List<Prototype>b){
        count=1;
        int dead=0;
        boolean win= false;
        Prototype.queue(a);
        Prototype.queue(b);
        while(win!=true){
            System.out.println(String.format("\nROUND %d", count));
            for (Prototype prototype : a) {
                if(prototype.status==Statuses.ALIVE){
                    View.view();
                    prototype.step(a,b);
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
                    View.view();
                    prototype.step(b,a);
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
            count+=1;
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