package game.face;

import game.check.ImpactCheck;
import game.enumerate.FieldActionEnum;
import game.enumerate.TeamGroup;
import game.strategy.bulletStrategy.BulletStrategy;
import game.tank.impl.ComputerTank;
import game.tank.impl.PlayerTank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultFace implements Face<GameObject> {

    private volatile int createdComputedTankCounts = 0;

    private boolean isGameOver = false;

    private List<GameObject> elements = new ArrayList<>();

    @Override
    public void paintElement(Graphics g) {
        for (int i = 0; i < elements.size(); i++) {
            try {
                GameObject gameObject = elements.get(i);
                if(gameObject.isDie && gameObject.getClass() != PlayerTank.class){
                    continue;
                }
                gameObject.paintElement(g);
            } catch (Exception e) {
                ////防止数组越界错误
            }
        }

    }

    @Override
    public void prevCheckElementStatus(ImpactCheck<GameObject> impactCheck) {
        for (int i = 0; i < elements.size(); i++) {
            try {
                elements.get(i).statusCheck(elements, impactCheck);
            } catch (Exception e) {
                //防止数组越界错误
            }
        }
    }

    @Override
    public void executeElementSpecialField(FieldActionEnum type, BulletStrategy<GameObject> strategy) {
        for (int i = 0; i < elements.size(); i++) {
            try {
                elements.get(i).executeElementSpecialField(elements, type, strategy);
            } catch (Exception e) {
                //防止数组越界错误
            }
        }
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    @Override
    public int getAlCreateComputerTankCounts() {
        return this.createdComputedTankCounts;
    }

    @Override
    public int getElementCounts(Class<? extends GameObject> clz) {
        int count = 0;
        for (int i = 0; i < elements.size();i++) {
            if(elements.get(i).getClass() == clz){
                count++;
            }
        }
        return count;
    }

    @Override
    public GameObject getElement(Class<? extends GameObject> clz) {
        for (int i = 0; i < elements.size();i++) {
            if(elements.get(i).getClass() == clz){
               return elements.get(i);
            }
        }
        return null;
    }

    @Override
    public void gameDetection() {
        this.removeDieGameObj();
        GameObject element = getElement(PlayerTank.class);
        if (element.isDie() && element.getLiveNum() > 0) {
            element.setLiveNum(element.getLiveNum() - 1);
            element.resurrection();
            element.setDie(false);
        } else if (element.isDie() && element.getLiveNum() == 0) {
            this.isGameOver = true;
        }

        //此处可以改成策略模式 小于几个的时候生成tank数量不一样
        if (createdComputedTankCounts == 20) {
            return;
        }
        int elementCounts = getElementCounts(ComputerTank.class);
        if (elementCounts < 6) {
            createdComputedTankCounts++;

            elements.add(new ComputerTank(TeamGroup.COMPUTER,elements));
        }
    }

    private void removeDieGameObj(){
        for (int i = 0; i < elements.size(); i++) {
            GameObject gameObject = elements.get(i);
            if(gameObject.isDie && gameObject.getClass() != PlayerTank.class){
                elements.remove(elements.get(i));
            }
        }
    }

    @Override
    public void init() {
        elements.add(new PlayerTank(TeamGroup.PLAYER));
    }
}
