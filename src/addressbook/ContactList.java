package addressbook;

public interface ContactList {

	/**
	 * 
	 * @return The current Contact. Returns null when the list is empty.
	 */
	public Contact getCurrent();


	/**
	 * Makes the first contact the current contact.
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goFirst();


	/**
	 * Makes the last contact the current contact.
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goLast();


	/**
	 * Makes the next contact the current contact. This method "wraps around", so goLast(), then goNext() would be equivalent to goFirst().
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goNext();


	/**
	 * Makes the previous contact the current contact. This method "wraps around", so goFirst(), then goPrevious() would be equivalent to
	 * goLast().
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goPrevious();


	/**
	 * 
	 * @return The current # of Contacts in the list.
	 */
	public int getCount();


	/**
	 * Clears the current contact list and replaces it with contacts in filename. Contacts are stored in the same order as in the file.
	 * After loading the current contact is set to the first contact.
	 * 
	 * @param filename
	 *            Filename/path to the XML contacts file.
	 * @throws Exception
	 */
	public void loadFile(String filename) throws Exception;


	/**
	 * Saves the contact list in XML format to the specified file.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void saveFile(String filename) throws Exception;


	/**
	 * Inserts contact after the current contact. Current is set to the newly added contact.
	 * 
	 * @param contact
	 */
	public void insert(Contact contact);


	/**
	 * Inserts contact at the beginning of the list. Current is set to the newly added contact (i.e. the first contact).
	 * 
	 * @param contact
	 */
	public void insertBeforeFirst(Contact contact);


	/**
	 * Deletes the current contact. Current is then set to the next contact in the list, unless there is no next, in which case the previous
	 * contact becomes the current contact.
	 */
	public void delete();


	/**
	 * Searches for the first contact whose last name is lastName. Ignore case. If found, it becomes the current contact. If not found,
	 * current is not changed.
	 * 
	 * @param lastName
	 * @return true if found, false if not.
	 */
	public boolean goContact(String lastName);


	/**
	 * Sorts the list of contacts based on their last names. NOTE: The current contact does not change as a result of the sort, though its
	 * previous and next contacts may.
	 */
	public void sortOnLastName();

}
