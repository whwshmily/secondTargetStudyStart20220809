package game.strategy.bulletStrategy;

import game.bullet.BaseBullet;

import java.util.List;

//产生子弹的策略
public interface BulletStrategy<T> {

    List<BaseBullet> produceBullet(T condition);
}
