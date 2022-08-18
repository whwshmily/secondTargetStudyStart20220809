package game.check;

//碰撞检测

public interface ImpactCheck<T> {

    //是否碰撞
    boolean isCollide(T shape1, T shape2);

}
