package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(3000);
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContatcsPageLable(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contatcs Lable is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContatcsTest(){
		contactsPage.selectContactsByName("abc dddd");
	}
	
	@Test(priority=3)
	public void selectMultipleContatcsTest(){
		contactsPage.selectContactsByName("abc dddd");
		contactsPage.selectContactsByName("Amit Kulkarni");
		contactsPage.selectContactsByName("Andy Alam");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData", enabled=false)
	public void validateCreateNewContact(String title, String firstname, String lastName, String company){
		homePage.clickOnNewContact();
		//contactsPage.createNewContact("Mr.", "Aayan", "Raj", "Google");
		contactsPage.createNewContact(title, firstname, lastName, company);
	}
	
	@AfterMethod()
	public void tearDown(){
		driver.quit();
	}
}