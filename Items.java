import java.util.HashMap;

public class Items {
    protected HashMap<String,Integer> items = new HashMap<>();
    protected String item;
    protected int money;
    protected int capacity;
    protected int maxCapacity=10;
    public void addItem(String item){
        if(capacity<=maxCapacity){
            items.put(item,20);
            capacity+=1;
        }
    }
    public void throwItemAway(String item){
        if(items.containsKey(item)){
            items.remove(item);
            capacity-=1;
        }
    }
    public void showInventory(){
        System.out.println(String.format("\nInventory: %s\nCapacity %d / %d\n%dgold",items,capacity,maxCapacity,money));
    }
}
