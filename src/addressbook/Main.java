package addressbook;

import static java.lang.System.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AddressBook myBook = new AddressBook();
		myBook.insert(new Contact("Oroz", "zach", "", ""));
		myBook.insert(new Contact("Oroz", "jake", "", ""));
		myBook.insert(new Contact("Oroz", "gabe", "", ""));
		myBook.insert(new Contact("Oroz", "zach", "", ""));
		myBook.insert(new Contact("Oroz", "abel", "", ""));
		myBook.insert(new Contact("Oroz", "bro", "", ""));
		myBook.insert(new Contact("Oroz", "ski", "", ""));
		myBook.insert(new Contact("Oroz", "Justin", "", ""));

		myBook.sortOnLastName();

		out.println("Yup");

	}

}
