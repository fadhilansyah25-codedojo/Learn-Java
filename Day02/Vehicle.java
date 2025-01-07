package Day02;

public class Vehicle {
    private String name;
    private int wheels;
    private int speed;
    private int engineCapacity;
    private String engineType;
    private String brand;
    private String model;
    private boolean isStarted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public void start() {
        this.isStarted = true;
    }

    public void accelerate(int velocity) {
        if (!isStarted) {
            System.out.println("You haven't started the vehicle!");
        } else {
            speed += velocity;
            System.out.println("Current speed: " + this.speed);
        }
    }

    public void brake() {
        this.speed -= 10;
        System.out.println("Current speed: " + this.speed);
    }

    public void stop() {
        this.speed = 0;
    }

    public void turnOff() {
        if (this.speed != 0) {
            System.out.println("You should stop the vehicle first!");
        } else {
            this.isStarted = false;
        }
    }

    public void rev() {
        System.out.println("this vehicle goes brumm!!!");
    }
}