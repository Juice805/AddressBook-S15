package addressbook;

import static java.lang.System.*;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

public class AddressBook implements ContactList {

	private Contact cur, top;
	private int size = 0;

	/**
	 * TODO
	 * 
	 * @return The current Contact. Returns null when the list is empty.
	 */
	@Override
	public Contact getCurrent() {
		if (size <= 0)
			return null;
		else
			return cur;
	}

	/**
	 * TODO Makes the first contact the current contact.
	 * 
	 * @return The current contact (after the change).
	 */
	@Override
	public Contact goFirst() {
		cur = top;

		return cur;
	}

	/**
	 * TODO Makes the last contact the current contact.
	 * 
	 * @return The current contact (after the change).
	 */
	@Override
	public Contact goLast() {
		cur = top;
		while (cur.next != null) {
			cur = cur.next;
		}

		return cur;
	}

	/**
	 * TODO Makes the next contact the current contact. This method
	 * "wraps around", so goLast(), then goNext() would be equivalent to
	 * goFirst().
	 * 
	 * @return The current contact (after the change).
	 */
	@Override
	public Contact goNext() {
		// TODO Auto-generated method stub

		if (cur.next != null) {
			cur = cur.next;
		} else
			cur = top;

		return cur;
	}

	/**
	 * TODO Makes the previous contact the current contact. This method
	 * "wraps around", so goFirst(), then goPrevious() would be equivalent to
	 * goLast().
	 * 
	 * @return The current contact (after the change).
	 */
	@Override
	public Contact goPrevious() {

		if (cur.prev != null) {
			cur = cur.prev;
		} else
			goLast();

		return cur;
	}

	/**
	 * TODO
	 * 
	 * @return The current # of Contacts in the list.
	 */
	@Override
	public int getCount() {

		return size;
	}

	/**
	 * TODO Clears the current contact list and replaces it with contacts in
	 * filename. Contacts are stored in the same order as in the file. After
	 * loading the current contact is set to the first contact.
	 * 
	 * @param filename
	 *            Filename/path to the XML contacts file.
	 * @throws Exception
	 */
	@Override
	public void loadFile(String filename) throws Exception {
		//delete current addressbook
		cur = top;
		top = null;
		while (cur != null) {
			delete();
		}

		//load xml
		File addressBkXml = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(addressBkXml);

		doc.getDocumentElement().normalize();

		NodeList contactList = doc.getElementsByTagName("contact");

		Contact newContact;
		String lastName;
		String firstName;
		String phone;
		String email;

		for (int i = 0; i < contactList.getLength(); i++) {

			Element eElement = (Element) contactList.item(i);

			lastName = eElement.getElementsByTagName("last").item(0).getTextContent();
			firstName = eElement.getElementsByTagName("first").item(0).getTextContent();
			phone = eElement.getElementsByTagName("phone").item(0).getTextContent();
			email = eElement.getElementsByTagName("email").item(0).getTextContent();

			newContact = new Contact(lastName, firstName, phone, email);
			insert(newContact);
			//newContact = null;

			if (i == 0) {
				top = cur; //sets the top to the first item
			}
		}
		cur = top;
	}

	/**
	 * TODO Saves the contact list in XML format to the specified file.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	@Override
	public void saveFile(String filename) throws Exception {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		//create the list
		Document doc = docBuilder.newDocument();
		Element root = doc.createElement("contacts");
		doc.appendChild(root);

		cur = top;
		while (cur != null) {
			Element contact = doc.createElement("contact");
			root.appendChild(contact);

			Element lastName = doc.createElement("last");
			lastName.appendChild(doc.createTextNode(cur.getLastName()));
			contact.appendChild(lastName);

			Element firstname = doc.createElement("first");
			firstname.appendChild(doc.createTextNode(cur.getFirstName()));
			contact.appendChild(firstname);

			Element phone = doc.createElement("phone");
			phone.appendChild(doc.createTextNode(cur.getPhoneNumber()));
			contact.appendChild(phone);

			Element email = doc.createElement("email");
			email.appendChild(doc.createTextNode(cur.getEmail()));
			contact.appendChild(email);
			cur = cur.next;
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));

		transformer.transform(source, result);

	}

	/**
	 * TODO Inserts contact after the current contact. Current is set to the
	 * newly added contact.
	 * 
	 * @param contact
	 */
	@Override
	public void insert(Contact contact) {
		if (top == null) { // if only in list
			top = contact;
		} else {
			contact.prev = cur;
			contact.next = cur.next;

			if (cur.next != null) { // if cur not at end of list
				cur.next.prev = contact; // set next contact to point at
											// inserted contact
			}

			cur.next = contact;

		}

		cur = contact;
		size++;
	}

	/**
	 * TODO Inserts contact at the beginning of the list. Current is set to the
	 * newly added contact (i.e. the first contact).
	 * 
	 * @param contact
	 */
	@Override
	public void insertBeforeFirst(Contact contact) {
		contact.prev = null;

		contact.next = top;
		if (top != null)
			top.prev = contact;

		top = contact;
		cur = contact;

		size++;
	}

	/**
	 * TODO Deletes the current contact. Current is then set to the next contact
	 * in the list, unless there is no next, in which case the previous contact
	 * becomes the current contact.
	 */
	@Override
	public void delete() {

		if (cur != null) { // if cur is on a contact

			if (cur.prev != null) { // make surrounding contact connections
				cur.prev.next = cur.next;
			} else {
				top = cur.next; //if its the first item in list, the next contact will now be first
			}

			if (cur.next != null) {
				cur.next.prev = cur.prev;
			}

			// delete the contact
			if (cur.next == cur.prev) { // if both ends equal eachother (aka
										// both null)
				cur = null; // remove pointer
			} else if (cur.next == null) { // if no next node
				goPrevious();
			} else
				goNext();

			size--;
		}

	}

	/**
	 * TODO Searches for the first contact whose last name is lastName. Ignore
	 * case. If found, it becomes the current contact. If not found, current is
	 * not changed.
	 * 
	 * @param lastName
	 * @return true if found, false if not.
	 */
	@Override
	public boolean goContact(String lastName) {
		Contact scanner = top;

		while (scanner != null && !scanner.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
			scanner = scanner.next;
		}

		if (scanner == null) { // if the contact not in list
			return false; // return false (unfound)
		} else {
			cur = scanner;
			return true;
		}
	}

	/**
	 * TODO Sorts the list of contacts based on their last names. NOTE: The
	 * current contact does not change as a result of the sort, though its
	 * previous and next contacts may.
	 */
	@Override
	public void sortOnLastName() {

		if (size <= 1) { //dont sort if only one or less
			return;
		}

		//setup
		Contact unsortedTop = top.next; //The top of the unsorted list
		Contact unsortedPtr;

		// separate the lists
		unsortedTop.prev = null;
		top.next = null;
		size = 1; //inserts will add back to size

		do {
			cur = top; //start at top

			while (unsortedTop.getLastName().toLowerCase().compareTo(cur.getLastName().toLowerCase()) > 0 && cur.next != null) {
				cur = cur.next; //scan down the sorted list to find contact location
			}

			while (unsortedTop.getLastName().toLowerCase().compareTo(cur.getLastName().toLowerCase()) == 0 && cur.next != null
					&& unsortedTop.getFirstName().toLowerCase().compareTo(cur.getFirstName().toLowerCase()) > 0) {
				cur = cur.next; //scan down the sorted list to find contact location
			}

			unsortedPtr = unsortedTop.next;

			if (unsortedTop.getLastName().toLowerCase().compareTo(cur.getLastName().toLowerCase()) > 0) { //if its after the item
				insert(unsortedTop);
			} else if (unsortedTop.getLastName().toLowerCase().compareTo(cur.getLastName().toLowerCase()) < 0) {
				if (cur.prev == null) { //if its before the first item
					insertBeforeFirst(unsortedTop);
				} else {
					cur = cur.prev;
					insert(unsortedTop);
				}
			} else { // if its the same, same sorting process for first names
				if (unsortedTop.getFirstName().toLowerCase().compareTo(cur.getFirstName().toLowerCase()) > 0) { //if its after the item
					insert(unsortedTop);
				} else if (unsortedTop.getFirstName().toLowerCase().compareTo(cur.getFirstName().toLowerCase()) < 0) {
					if (cur.prev == null) { //if its before the first item
						insertBeforeFirst(unsortedTop);
					} else {
						cur = cur.prev;
						insert(unsortedTop);
					}
				}
			}

			unsortedTop = unsortedPtr;
		} while (unsortedTop != null);

		//printList();
	}

	public void printList() {
		cur = top;
		while (cur.next != null) {
			out.println(cur.getLastName() + "\t" + cur.getFirstName());
			cur = cur.next;
		}
	}
}
