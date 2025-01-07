package Day02;

public class App {
    public static void main(String[] args) {
        // Tipe data object merupakan tipe data tertinggi sehingga mau di-assign dengan
        // value tipe data apapun tidak akan error
        Object variableObject = 1;
        System.out.println(variableObject);
        variableObject = "Satu";
        System.out.println(variableObject);

        Vehicle motor = new Vehicle();
        // motor.name = "Motor";
        // motor.brand = "Kawasaki";
        // motor.model = "ZX 25R";
        // motor.engineType = "4 Cylinder";
        // motor.engineCapacity = 250;
        // motor.wheels = 2;

        motor.setName("Motor");
        motor.setBrand("Kawasaki");
        motor.setModel("ZX 25R");
        motor.setEngineType("4 Cylinder");
        motor.setEngineCapacity(250);
        motor.setWheels(250);

        motor.start();
        motor.accelerate(10);
        motor.accelerate(20);
        motor.stop();
        motor.turnOff();

        // Car
        Car myCar = new Car();
        myCar.setBrand("Mazda");
        myCar.rev();

        Motorbike myBike = new Motorbike();
        myBike.rev();
    }
}

// PascalCase => digunakan untuk penamaan nama Class
// camelCase => digunakan untuk penamaan variable dan function
// snake_case => digunakan untuk penamaan field/column pada table sebuah
// database
// UPPER_SNAKE_CASE => digunakan untuk penamaan nilai konstanta