package game.check;

//碰撞检测

public interface ImpactCheck {

    //是否碰撞
    boolean isCollide(BaseShape shape1, BaseShape shape2);

}
