package tree;

import lombok.AllArgsConstructor;
import po.Employee;

import java.util.List;

public class MaxHappyTree {

    public static int maxHappy(Employee boss) {
        HappyInfoBo process = process(boss);
        return Math.max(process.yes, process.no);
    }

    private static HappyInfoBo process(Employee employee) {
        if (employee == null) {
            return new HappyInfoBo(0, 0);
        }
        //当前去
        int yes = employee.getHappy();
        //不去
        int no = 0;

        /**
         * 去的快乐最大值  和他的下属都不去的快乐值的和
         * 不去的快乐值 是他的下属去 和不去取最大值  然后总和
         */

        List<Employee> subordinate = employee.getSubordinate();
        for (Employee e : subordinate) {
            HappyInfoBo process = process(e);
            yes += process.no;
            no += Math.max(process.no, process.yes);
        }
        return new HappyInfoBo(yes, no);
    }


    @AllArgsConstructor
    private static class HappyInfoBo {
        int yes;
        int no;
    }

}
