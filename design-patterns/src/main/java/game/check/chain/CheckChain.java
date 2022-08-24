package game.check.chain;

import game.check.ImpactCheck;
import game.face.GameObject;
import util.ProjectCache;

import java.util.LinkedList;
import java.util.List;

public class CheckChain implements ImpactCheck<GameObject> {

    private List<ImpactCheck<GameObject>> list = new LinkedList<>();

    {
        String classNames = ProjectCache.getValue("chain");
        String[] split = classNames.split(",");
        for (String name : split) {
            try {
                Class<?> aClass = Class.forName(name);
                list.add((ImpactCheck<GameObject>) aClass.newInstance());
            } catch (Exception e) {
                System.out.println("----------------");
            }
        }
    }

    @Override
    public boolean isCollide(GameObject shape1, GameObject shape2) {
        for (ImpactCheck<GameObject> gameObjectImpactCheck : list) {
            boolean collide = gameObjectImpactCheck.isCollide(shape1, shape2);
            if (collide) return collide;
        }
        return false;
    }
}
