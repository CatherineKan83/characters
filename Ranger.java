
import java.util.List;

public interface Ranger{
    Prototype findNearestEnemy(List<Prototype>a);
    void shoot(Prototype target);
    void skillAttack(Prototype target);

}
