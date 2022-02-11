package telran.io.controller;

import java.util.ArrayList;

import telran.io.service.DirectoryFilesCopy;
import telran.io.service.DirectoryFilesCopyImpl;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class DirectoryFilesCopyAppl { 

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		DirectoryFilesCopy dfc = new DirectoryFilesCopyImpl();
		ArrayList<Item> items = DirectoryFilesCopyActions.getItems(dfc);
		
		Menu menu = new Menu("File Operations", items);
		
		menu.perform(io); 
	}
 
}
