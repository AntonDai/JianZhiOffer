/**
 * @description: 单元素的枚举实现单例模式
 * @author: Daniel
 * @create: 2019-02-14 20:21
 **/
public enum Singleton3 {
    // 定义一个枚举元素，代表Singleton的一个实例
    INSTACE;

    private String objName;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public static void main(String[] args){
        // 单例测试
        Singleton3 firstSingleton = Singleton3.INSTACE;
        firstSingleton.setObjName("first");
        System.out.println(firstSingleton.getObjName()); // first

        Singleton3 secondSingleton = Singleton3.INSTACE;
        secondSingleton.setObjName("second");
        System.out.println(firstSingleton.getObjName()); // second
        System.out.println(secondSingleton.getObjName()); // second

        // 反射获取实例测试
        Singleton3[] enumConstants = Singleton3.class.getEnumConstants();
        for(Singleton3 enumConstant : enumConstants)
            System.out.println(enumConstant.getObjName());
    }
}
