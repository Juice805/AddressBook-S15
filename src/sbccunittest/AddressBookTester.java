package sbccunittest;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;
import org.junit.*;

import addressbook.*;

/**
 * This class unit-tests the CS145 Address Book.
 * 
 * @author sstrenn 4/14/2013
 * 
 */
public class AddressBookTester {

	ContactList actualContacts; // The student's contact list.

	public String[] lastNames = { "SMITH", "JOHNSON", "WILLIAMS", "JONES", "BROWN", "DAVIS", "MILLER", "WILSON",
			"MOORE", "TAYLOR", "ANDERSON", "THOMAS", "JACKSON", "WHITE", "HARRIS", "MARTIN", "THOMPSON", "GARCIA",
			"MARTINEZ", "ROBINSON", "CLARK", "RODRIGUEZ", "LEWIS", "LEE", "WALKER", "HALL", "ALLEN", "YOUNG",
			"HERNANDEZ", "KING", "WRIGHT", "LOPEZ", "HILL", "SCOTT", "GREEN", "ADAMS", "BAKER", "GONZALEZ", "NELSON",
			"CARTER", "MITCHELL", "PEREZ", "ROBERTS", "TURNER", "PHILLIPS", "CAMPBELL", "PARKER", "EVANS", "EDWARDS",
			"COLLINS", "STEWART", "SANCHEZ", "MORRIS", "ROGERS", "REED", "COOK", "MORGAN", "BELL", "MURPHY", "BAILEY",
			"RIVERA", "COOPER", "RICHARDSON", "COX", "HOWARD", "WARD", "TORRES", "PETERSON", "GRAY", "RAMIREZ",
			"JAMES", "WATSON", "BROOKS", "KELLY", "SANDERS", "PRICE", "BENNETT", "WOOD", "BARNES", "ROSS", "HENDERSON",
			"COLEMAN", "JENKINS", "PERRY", "POWELL", "LONG", "PATTERSON", "HUGHES", "FLORES", "WASHINGTON", "BUTLER",
			"SIMMONS", "FOSTER", "GONZALES", "BRYANT", "ALEXANDER", "RUSSELL", "GRIFFIN", "DIAZ", "HAYES" };

	public String[] firstNames = { "AUNDREA", "ROSALIA", "SACHIKO", "MARCO", "ASLEY", "EMMALINE", "WINTER", "HORACE",
			"BRIAN", "ILUMINADA", "THOMASINA", "LOVELLA", "ELFRIEDA", "CLARE", "CATHIE", "MASON", "KARIMA", "DARIA",
			"ELWOOD", "HILARIA", "TERRILYN", "LEONIDA", "LATONYA", "GIA", "MOZELL", "YOLANDE", "MIKAELA", "TUYET",
			"TWANDA", "MARQUITA", "BRYANNA", "DALE", "SHAN", "MARILU", "NELLIE", "TITUS", "BILLIE", "YUK", "HORACE",
			"TERESE", "JULIANE", "LEANNA", "TIANA", "ADOLFO", "SHARAN", "LOWELL", "NATHANIEL", "LEONORE", "SIMONE",
			"DORRIS", "AUBREY", "HANNAH", "EMERY", "BRUNA", "LOYD", "WILBER", "HEIDE", "MANIE", "KARY", "BIANCA",
			"CARROL", "PHYLIS", "PEGGIE", "ROSEMARY", "EDDIE", "DONA", "TOMMY", "LEESA", "TYRA", "JOHANNA", "KATHY",
			"JOANA", "NELLY", "STACIA", "JOEY", "PHILLIS", "CONNIE", "MELODY", "MONSERRATE", "ARMINDA", "MAYNARD",
			"AMI", "ELDA", "VIOLA", "TATIANA", "ELMO", "RICHELLE", "JO", "KEILA", "MANA", "JOYA", "SHERRYL", "TAYNA",
			"NEOMA", "MONNIE", "ABDUL", "LUCY", "LORRAINE", "LATRINA", "NOMA" };

	public String[] domains = { "google.com", "yahoo.com", "msn.com", "live.com", "youtube.com", "wikipedia.org",
			"microsoft.com", "myspace.com", "facebook.com", "ebay.com", "aol.com", "amazon.com", "about.com",
			"craigslist.org", "blogspot.com", "ask.com", "answers.com", "mapquest.com", "photobucket.com", "go.com",
			"adobe.com", "windows.com", "walmart.com", "usatoday.com", "wordpress.com", "paypal.com", "cnn.com",
			"reference.com", "imdb.com", "flickr.com", "att.com", "blogger.com", "target.com", "comcast.net",
			"yellowpages.com", "apple.com", "bizrate.com", "ehow.com", "geocities.com", "webmd.com", "weather.com",
			"bankofamerica.com", "nextag.com", "careerbuilder.com", "whitepages.com", "irs.gov", "zynga.com",
			"bestbuy.com", "download.com", "merriamwebster.com", "chase.com", "classmates.com", "comcast.com",
			"shopzilla.com", "verizonwireless.com", "hp.com", "nytimes.com", "usps.com", "hulu.com", "wellsfargo.com",
			"reunion.com", "reuters.com", "ups.com", "metacafe.com", "netflix.com", "huffingtonpost.com",
			"capitalone.com", "icq.com", "digg.com", "overstock.com", "ezinearticles.com", "jcpenney.com",
			"monster.com", "tripod.com", "symantec.com", "nbc.com", "verizon.com", "pronto.com", "vzw.com", "dell.com",
			"simplyhired.com", "smarter.com", "cnet.com", "areaconnect.com", "linkedin.com", "nih.gov", "typepad.com",
			"ticketmaster.com", "expedia.com", "wikimedia.org", "ign.com", "time.com", "evite.com", "scribd.com",
			"pogo.com", "allrecipes.com", "gamevance.com", "gamespot.com", "sears.com", "citysearch.com" };

	public String[] fieldNames = { "lastName", "firstName", "email", "phoneNumber" };

	public static int totalScore = 0;

	public static int extraCredit = 0;


	@BeforeClass
	public static void beforeTesting() {
		totalScore = 0;
		extraCredit = 0;
	}


	@AfterClass
	public static void afterTesting() {
		System.out.println("Estimated score (assuming no late penalties, etc.) = " + totalScore);
		System.out.println("Estimated extra credit (assuming on time submission) = " + extraCredit);
	}


	@Before
	public void setUp() throws Exception {
		actualContacts = new AddressBook();
	}


	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testInsertOne() {
		actualContacts.insertBeforeFirst(new Contact("al", "af", "555-1111", "al@x.com"));
		assertEquals(1, actualContacts.getCount());
		assertEquals("al", actualContacts.getCurrent().getLastName());
		assertEquals("af", actualContacts.getCurrent().getFirstName());

		totalScore += 10;
	}


	@Test
	public void testInsert() {
		int numContacts = (int) (Math.random() * 10) + 2;
		ArrayList<Contact> expectedContacts = new ArrayList<Contact>();
		addContactsRandomly(expectedContacts, numContacts, actualContacts);

		verifyContacts(expectedContacts, actualContacts);

		totalScore += 10;
	}


	@Test
	public void testInsertAndDelete() {
		actualContacts.insertBeforeFirst(new Contact("al", "af", "555-1111", "al@x.com"));
		actualContacts.insert(new Contact("bl", "bf", "555-2222", "bl@x.com"));
		actualContacts.insertBeforeFirst(new Contact("cl", "cf", "555-3333", "cl@x.com"));
		actualContacts.insert(new Contact("dl", "df", "555-4444", "dl@x.com"));

		assertEquals(4, actualContacts.getCount());

		actualContacts.goFirst();
		assertEquals("cl", actualContacts.getCurrent().getLastName());

		actualContacts.delete();
		actualContacts.delete();
		actualContacts.delete();
		assertEquals("bl", actualContacts.getCurrent().getLastName());

		actualContacts.delete();
		assertEquals(0, actualContacts.getCount());

		actualContacts.delete();
		actualContacts.delete();
		actualContacts.delete();

		assertEquals(0, actualContacts.getCount());
		totalScore += 10;
	}


	@Test
	/**
	 * Checking for NPE's.
	 */
	public void testIteratePastEnds() {

		int numContacts = (int) (Math.random() * 4) + 2;
		ArrayList<Contact> expectedContacts = new ArrayList<Contact>();
		addContactsRandomly(expectedContacts, numContacts, actualContacts);

		assertEquals(expectedContacts.size(), actualContacts.getCount());

		actualContacts.goFirst();
		actualContacts.goPrevious();
		verifyContact(expectedContacts.get(expectedContacts.size() - 1), actualContacts.getCurrent());
		actualContacts.goPrevious();
		actualContacts.goLast();
		actualContacts.goNext();
		verifyContact(expectedContacts.get(0), actualContacts.getCurrent());
		actualContacts.goNext();
		actualContacts.goLast();

		verifyContact(expectedContacts.get(expectedContacts.size() - 1), actualContacts.getCurrent());
		totalScore += 5;
	}


	@Test
	public void testLoadAndSave() {
		try {
			// Save out a random # of randomly generated contacts
			int numContacts = (int) (Math.random() * 10) + 2;
			ArrayList<Contact> expectedContacts = new ArrayList<Contact>();
			addContactsRandomly(expectedContacts, numContacts, actualContacts);
			String xml = toXml(expectedContacts);
			String inFilename = "test_load_and_save_in.xml";
			FileUtils.writeStringToFile(new File(inFilename), xml);

			// Load and verify
			actualContacts.loadFile(inFilename);
			verifyContacts(expectedContacts, actualContacts);

			// Delete a contact and save the list to a file.
			expectedContacts.remove(0);
			actualContacts.delete();
			String outFilename = "test_load_and_save_out.xml";
			actualContacts.saveFile(outFilename);

			// Verify that the file has the correct # of contacts
			String s = FileUtils.readFileToString(new File(outFilename));
			String[] parts = s.split("<contact>");
			int numberOfContactsInFile = parts.length - 1;
			assertEquals(expectedContacts.size(), numberOfContactsInFile);

			// Verify that file can be loaded and had the correct # of contacts
			actualContacts.loadFile(outFilename);
			assertEquals(expectedContacts.size(), actualContacts.getCount());
			verifyContacts(expectedContacts, actualContacts);

			totalScore += 10;
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}


	@Test
	public void testSearch() {

		int numContacts = (int) (Math.random() * 10) + 10;
		ArrayList<Contact> expectedContacts = new ArrayList<Contact>();
		addContactsRandomly(expectedContacts, numContacts, actualContacts);

		int randNdx = (int) (Math.random() * numContacts);
		Contact c = expectedContacts.get(randNdx);

		// Find a last name that isn't in the list
		String unknownLast = null;
		for (String last : lastNames) {
			unknownLast = last;
			boolean inList = false;
			for (Contact cx : expectedContacts) {
				if (cx.getLastName().equals(last)) {
					inList = true;
					break;
				}
			}

			if (inList == false)
				break;
		}

		// Try randomly searching for known and unknown people
		for (int ndx = 0; ndx < 50; ndx++) {
			if (Math.random() > 0.5) {
				assertEquals(true, actualContacts.goContact(c.getLastName()));
				assertEquals(c.getLastName(), actualContacts.getCurrent().getLastName());
			} else {
				String lastBefore = actualContacts.getCurrent().getLastName();
				assertEquals(false, actualContacts.goContact(unknownLast));
				assertEquals(lastBefore, actualContacts.getCurrent().getLastName());
			}
		}

		totalScore += 5;
	}


	@Test
	public void testSort() {

		int numContacts = (int) (Math.random() * 50) + 50;
		ArrayList<Contact> expectedContacts = new ArrayList<Contact>();
		addContactsRandomly(expectedContacts, numContacts, actualContacts);

		assertEquals(expectedContacts.size(), actualContacts.getCount());

		actualContacts.sortOnLastName();
		actualContacts.goFirst();

		boolean sortOrderValid = true;
		int count = actualContacts.getCount();
		Contact previousContact = actualContacts.getCurrent();
		actualContacts.goNext();

		for (int ndx = 1; ndx < count; ndx++) {
			String currentLastName = actualContacts.getCurrent().getLastName();
			String previousLastName = previousContact.getLastName();
			int result = currentLastName.compareTo(previousLastName);
			if (result < 0) {
				sortOrderValid = false;
				break;
			}
			previousContact = actualContacts.getCurrent();
			actualContacts.goNext();
		}

		if (!sortOrderValid)
			fail("Sort order not valid");

		extraCredit += 5;
	}


	public ArrayList<Contact> buildContactList(int count) {
		ArrayList<Contact> contactList = new ArrayList<Contact>();

		for (int ndx = 0; ndx < count; ndx++) {
			contactList.add(buildContact());
		}

		return contactList;
	}


	private Contact buildContact() {
		String lastName = lastNames[(int) (Math.random() * lastNames.length)];
		String phoneNumber = "800" + String.format("%07d", (int) (9999999 * Math.random() + 1));
		Contact contact = new Contact(lastName, firstNames[(int) (Math.random() * firstNames.length)], phoneNumber,
				lastName + domains[(int) (Math.random() * domains.length)]);
		return contact;
	}


	private String toXml(ArrayList<Contact> contacts) {
		StringBuilder sb = new StringBuilder(8192);
		sb.append("<contacts>\n");
		for (Contact c : contacts) {
			sb.append("\t<contact>\n");
			sb.append("\t\t<last>" + c.getLastName() + "</last>");
			sb.append("\t\t<first>" + c.getFirstName() + "</first>");
			sb.append("\t\t<phone>" + c.getPhoneNumber() + "</phone>");
			sb.append("\t\t<email>" + c.getEmail() + "</email>");
			sb.append("\t</contact>\n");
		}
		sb.append("</contacts>");
		return sb.toString();
	}


	private void addContactsRandomly(ArrayList<Contact> expectedContacts, int numContacts, ContactList actualContacts) {
		Contact c;

		int position;

		for (int ndx = 0; ndx < numContacts; ndx++) {
			c = buildContact();
			position = (int) (Math.random() * expectedContacts.size());
			expectedContacts.add(position, c);

			actualContacts.goFirst();
			for (int p = 1; p < position; p++)
				actualContacts.goNext();
			if (position == 0)
				actualContacts.insertBeforeFirst(c);
			else
				actualContacts.insert(c);
		}
	}


	private void verifyContacts(ArrayList<Contact> expectedContacts, ContactList actualContacts) {
		actualContacts.goFirst();
		for (int ndx = 0; ndx < expectedContacts.size(); ndx++) {
			Contact expectedContact = expectedContacts.get(ndx);
			Contact actualContact = actualContacts.getCurrent();
			verifyContact(expectedContact, actualContact);
			actualContacts.goNext();
		}

	}


	private void verifyContact(Contact expectedContact, Contact actualContact) {
		assertEquals(expectedContact.getLastName(), actualContact.getLastName());
		assertEquals(expectedContact.getFirstName(), actualContact.getFirstName());
		assertEquals(expectedContact.getEmail(), actualContact.getEmail());
		assertEquals(expectedContact.getPhoneNumber(), actualContact.getPhoneNumber());
	}

}
