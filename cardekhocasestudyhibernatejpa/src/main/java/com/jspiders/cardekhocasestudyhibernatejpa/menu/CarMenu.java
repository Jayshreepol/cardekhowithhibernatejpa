package com.jspiders.cardekhocasestudyhibernatejpa.menu;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityTransaction;

import com.jspiders.cardekhocasestudyhibernatejpa.dao.CarDAO;
import com.jspiders.cardekhocasestudyhibernatejpa.dto.CarDTO;

public class CarMenu {
	private static CarDAO car = new CarDAO();
	static boolean loop = true;

	public static void main(String[] args) {
		CarDAO.openConnection();
		while (loop) {
			mainMenu();
		}
		CarDAO.closeConnection();
	}

	private static void mainMenu() {
		System.out.println("========Menu=======\n" + "1.View All Cars\n" + "2.Search Car\n" + "3.Add Car\n"
				+ "4.Remove Car\n" + "5.Edit Car\n" + "6.Exit");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			CarDAO.viewAllCars();
			break;
		case 2:
			System.out.println("====Search for car====\n" + "1.Search car by name\n" + "2.Search car by Brand\n"
					+ "3.search car by FuelType\n" + "4.search car by price\n" + "5.Search car by id \n"
					+ "6.Search car by colour\n" + "7.Search car by model\n" + "8.Go back to Menu\n");
			int ch1 = scanner.nextInt();
			switch (ch1) {
			case 1:
				CarDAO.searchCarByName();
				break;
			case 2:
				CarDAO.searchCarByBrand();
				break;
			case 3:

				CarDAO.searchCarByfuelType();
				break;
			case 4:
				CarDAO.searchCarByPrice();
				break;
			case 5:
				CarDAO.searchCarById();
				break;
			case 6:
				CarDAO.searchCarByColour();
				break;
			case 7:
				CarDAO.searchCarByPrice();
				break;
			case 8:
				mainMenu();
				break;
			default:
				System.out.println("Invalid choice!!");
				break;
			}
			break;
		case 3:
			CarDAO.addCar();
			break;
		case 4:
			CarDAO.removeCar();
			break;
		case 5:
			CarDAO.editCar();
			break;
		case 6:
			System.out.println("Thank you!!");
			loop = false;
			break;
		default:
			System.out.println("Invalid choice!!Please try again");
			break;
		}
	}
}
