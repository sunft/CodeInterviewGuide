package com.sunft.test;

public interface ICar {
	void move();
}

//ConcreteComponent 具体构件角色(真实对象)
class Car implements ICar {
	@Override
	public void move() {
		System.out.println("陆地上跑！");
	}
}

//Decorator装饰角色
class SuperCar implements ICar {
	//抽象对象的引用
	protected ICar car;

	public SuperCar(ICar car) {
		super();
		this.car = car;
	}

	@Override
	public void move() {
		car.move();
	}
}

//ConcreteDecorator具体装饰角色
class FlyCar extends SuperCar {

	public FlyCar(ICar car) {
		super(car);
	}

	public void fly(){
		System.out.println("天上飞！");
	}

	@Override
	public void move() {
		super.move();
		fly();
	}

}


class Client {
	public static void main(String[] args) {
		Car car  = new Car();
		car.move();

		FlyCar flycar = new FlyCar(car);
		flycar.move();

	}
}
