
import java.util.Scanner;
public class SetName {
    Scanner input= new Scanner(System.in);
    public String setName(){
        System.out.print("Input player's name:");
        String name = input.nextLine();
        return name;
    }
}
