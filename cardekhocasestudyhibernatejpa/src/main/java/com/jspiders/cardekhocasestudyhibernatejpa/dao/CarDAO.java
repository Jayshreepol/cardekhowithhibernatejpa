package com.jspiders.cardekhocasestudyhibernatejpa.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.cardekhocasestudyhibernatejpa.dto.CarDTO;

public class CarDAO {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	static Scanner scanner = new Scanner(System.in);

	public static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("car");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	public static void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static List<CarDTO> findAll() {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car");
		List<CarDTO> car = query.getResultList();
		return car;
	}

	@SuppressWarnings("unused")
	public static void viewAllCars() {
		entityTransaction.begin();
		List<CarDTO> car = findAll();
		for (CarDTO carDTO : car) {
			System.out.println(carDTO);
		}
		entityTransaction.commit();
	}

	public static void addCar() {
		System.out.println("How many cars you want to add?");
		int count = scanner.nextInt();
		for (int i = 1; i <= count; i++) {
			scanner.nextLine();
			System.out.println("Enter the car name");
			String name = scanner.nextLine();
			System.out.println("Enter the car Fueltype");
			String fueltype = scanner.nextLine();
			System.out.println("Enter the car colour");
			String colour = scanner.nextLine();
			System.out.println("Enter the car price");
			double price = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter the car brand");
			String brand = scanner.nextLine();
			System.out.println("Enter the car model");
			String model = scanner.nextLine();
			CarDTO car = new CarDTO();
			car.setCar_name(name);
			car.setFuelType(fueltype);
			car.setColour(colour);
			car.setPrice(price);
			car.setBrand(brand);
			car.setModel(model);
			entityTransaction.begin();
			entityManager.persist(car);
			entityTransaction.commit();
			System.out.println(count + " cars get added in data.");
		}
	}

	public static void removeCar() {
		viewAllCars();
		System.out.println("Enter the id of car which you want to delete");
		int id = scanner.nextInt();
		entityTransaction.begin();
		CarDTO car = entityManager.find(CarDTO.class, id);
		entityManager.remove(car);
		entityTransaction.commit();
		System.out.println("The car with id " + id + " is removed");
	}

	public static void editCar() {
		viewAllCars();
		System.out.println("Enter the id of car");
		int id = scanner.nextInt();
		System.out.println("Enter the price you want to update");
		double price = scanner.nextDouble();
		entityTransaction.begin();
		CarDTO car = entityManager.find(CarDTO.class, id);
		car.setPrice(price);
		entityTransaction.commit();
		System.out.println("Price is updated for id " + id + ".");
	}

	public static void searchCarByName() {
		System.out.println("Enter the car name");
		String name = scanner.nextLine();
		entityTransaction.begin();
		List<CarDTO> car = searchNamefindAll(name);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<CarDTO> searchNamefindAll(String name) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE car_name = ?1");
		query.setParameter(1,name);
		List car = query.getResultList();
		return car;
	}

	public static void searchCarByBrand() {
		System.out.println("Enter the car brand");
		String brand = scanner.nextLine();
		entityTransaction.begin();
		List<CarDTO> car = searchBrandfindAll(brand);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();

	}

	private static List<CarDTO> searchBrandfindAll(String brand) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE brand = ?1");
		query.setParameter(1,brand);
		List car = query.getResultList();
		return car;
	}

	public static void searchCarByfuelType() {
		System.out.println("Enter the car fueltype");
		String  fuelType = scanner.nextLine();
		entityTransaction.begin();
		List<CarDTO> car = searchFuelTypefindAll(fuelType);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();  

	}

	private static List<CarDTO> searchFuelTypefindAll(String fuelType) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE fuelType = ?1");
		query.setParameter(1,fuelType);
		List car = query.getResultList();
		return car;
	}

	public static void searchCarByPrice() {
		System.out.println("Enter the car price");
		double price  = scanner.nextDouble();
		entityTransaction.begin();
		List<CarDTO> car = searchBrandfindAll(price);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();

	}

	private static List<CarDTO> searchBrandfindAll(double price) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE price = ?1");
		query.setParameter(1,price);
		List car = query.getResultList();
		return car;
	}

	public static void searchCarById() {
		System.out.println("Enter the car ID");
		int id = scanner.nextInt();
		entityTransaction.begin();
		List<CarDTO> car = searchIdfindAll(id);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();

	}

	private static List<CarDTO> searchIdfindAll(int id) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE carid = ?1");
		query.setParameter(1,id);
		List car = query.getResultList();
		return car;
	}

	public static void searchCarByColour() {
		System.out.println("Enter the car colour");
		String colour = scanner.nextLine();
		entityTransaction.begin();
		List<CarDTO> car = searchColourfindAll(colour);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();

	}

	private static List<CarDTO> searchColourfindAll(String colour) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE colour = ?1");
		query.setParameter(1,colour);
		List car = query.getResultList();
		return car;
	}
	public static void searchCarByModel() {
		System.out.println("Enter the car model");
		String model = scanner.nextLine();
		entityTransaction.begin();
		List<CarDTO> car = searchModelfindAll(model);
		for (CarDTO cars : car) {
			System.out.println(car);
		}
		entityTransaction.commit();

	}

	private static List<CarDTO> searchModelfindAll(String model) {
		Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE model = ?1");
		query.setParameter(1,model);
		List car = query.getResultList();
		return car;
	}


}
