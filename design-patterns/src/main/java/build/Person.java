package build;

/**
 * build模式 用于构建复杂的对象
 * 对象里面的属性可能有很多
 * 但是你不需要所有的属性 只需要对一些属性进行赋值
 * 不需要的不用设置值
 * build就很方便
 */
public class Person {
    String name;
    int age;
    Locate locate;
    double weight;
    double height;

    //只允许用build来创建对象
    private Person() {
    }

    static class PersonBuilder {
        Person person = new Person();

        PersonBuilder buildName(String name) {
            person.name = name;
            return this;
        }

        PersonBuilder buildAge(int age) {
            person.age = age;
            return this;
        }

        PersonBuilder buildLocate(Locate locate) {
            person.locate = locate;
            return this;
        }

        PersonBuilder buildWeight(double weight) {
            person.weight = weight;
            return this;
        }

        PersonBuilder buildHeight(double height) {
            person.height = height;
            return this;
        }

        Person build() {
            return person;
        }
    }


    static class Locate {
        int x;
        int y;
    }

    public static void main(String[] args) {
        Person person = new PersonBuilder()
                .buildAge(18)
                .buildHeight(12)
                .buildLocate(new Locate())
                .buildName("age")
                .buildWeight(18)
                .build();
    }
}
