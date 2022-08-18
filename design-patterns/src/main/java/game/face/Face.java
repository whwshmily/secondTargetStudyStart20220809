package game.face;

import game.check.ImpactCheck;
import game.enumerate.FieldActionEnum;
import game.strategy.bulletStrategy.BulletStrategy;

import java.awt.*;

/**
 * 门面模式
 * frame 中坦克 子弹 强 之间的关系全由它处理
 */
public interface Face<T> {
    //画出游戏的元素
    void paintElement(Graphics g);

    /**
     * 开始画游戏中元素前 检测元素的状态
     *
     * @param elements 游戏中的元素
     */
    void prevCheckElementStatus(ImpactCheck<T> impactCheck);

    //执行元素的特有的属性 type 用来具体属性 可以用枚举值 执行这些动作可能用到的策略
    void executeElementSpecialField(FieldActionEnum type, BulletStrategy<T> strategy);

    boolean isGameOver();

    int getAlCreateComputerTankCounts();

    int getElementCounts(Class<? extends T> clz);

    T getElement(Class<? extends T> clz);

    //检测游戏时候结束 保证游戏的正常运转
    void gameDetection();

    void init();
}
