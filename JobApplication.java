

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class JobApplication {
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hp\\eclipse-workspace\\NumadicTest\\src\\test\\resources\\executables\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Navigate to the job page
		driver.get("https://jobs.numadic.com/jobs/Careers");
		driver.manage().window().maximize();

		// Verify header text
		WebElement header = driver.findElement(By.xpath("//h2[normalize-space()='JOIN OUR CREW']"));
		if (header != null) {
			System.out.println("Header text is present");
		} else {
			System.out.println("Header text is not present");
		}

		// Select Engineering from the filter by dropdown
		WebElement filterDropdown = driver.findElement(By.xpath("//lyte-icon[@class='dropdown']"));
		filterDropdown.click();
		WebElement engineeringOption = driver.findElement(By.xpath("//lyte-drop-item[@id='Lyte_Drop_Item_4']"));
		engineeringOption.click();
		System.out.println("Clicking  engineeringOption ");
		
		

		// Select QA Engineer from the job options
		WebElement qaEngineerOption = driver.findElement(By.xpath("//a[normalize-space()='QA Engineer']"));
		qaEngineerOption.click();
		System.out.println("Clicking  qaEngineerOption ");
		

		// Verify page title
		String expectedPageTitle = "Numadic Iot Pvt. Ltd. - QA Engineer in";
		String actualPageTitle = driver.getTitle();
		if (actualPageTitle.startsWith(expectedPageTitle)) {
			System.out.println("Page title is correct");
		} else {
			System.out.println("Page title is incorrect");
		}

		// Verify 'I'm interested' button and click
		WebElement interestedButton = driver
				.findElement(By.xpath("//lyte-button[@id='detail-page-applybtn']//button[@type='button']"));
		if (interestedButton != null) {
			System.out.println("Button is present");
			interestedButton.click();
		} else {
			System.out.println("Button is not present");
		}

		// Verify validation for all fields
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\hp\\eclipse-workspace\\NumadicTest\\src\\test\\resources\\excel\\Test.xlsx");
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Fill First Name
		
		WebElement titel = driver.findElement(By.xpath("(//lyte-icon[contains(@class,'dropdown')])[1]"));
		titel.click();
		WebElement mr = driver.findElement(By.xpath("//lyte-drop-item[@id='Lyte_Drop_Item_1']"));
		mr.click();
		
		WebElement firstName = driver.findElement(By.xpath("(//input[contains(@type,'text')])[1]"));
		firstName.sendKeys(sheet.getRow(0).getCell(0).getStringCellValue());
		System.out.println("Fristname done");

		// Fill Last Name
		WebElement lastName = driver.findElement(
				By.xpath("//crux-text-component[@id='rec-form_53264000000003151']//input[contains(@type,'text')]"));
		lastName.sendKeys(sheet.getRow(0).getCell(1).getStringCellValue());
		System.out.println("lastname done");

		// Fill Email
		WebElement email = driver.findElement(By.xpath("(//input[contains(@type,'text')])[3]"));
		email.sendKeys(sheet.getRow(0).getCell(2).getStringCellValue());
		System.out.println("Email done");

		// Fill Mobile
		WebElement mobile = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
		mobile.sendKeys(String.valueOf((long) sheet.getRow(0).getCell(3).getNumericCellValue()));

		// Fill Street
		WebElement street = driver.findElement(By.xpath("(//input[contains(@type,'text')])[5]"));
//		street.sendKeys(sheet.getRow(0).getCell(4).getStringCellValue());
		street.sendKeys("72D Regal");


		WebElement city = driver.findElement(By.xpath("(//input[@id='inputId'])[1]"));
		city.sendKeys("Noida");
		Actions action = new Actions(driver);
		action.moveToElement(city).perform();


		// Fill State/Province
		WebElement stateProvince =
		 driver.findElement(By.xpath("(//input[@type='text'])[7]"));
		// stateProvince.sendKeys(sheet.getRow(0).getCell(6).getStringCellValue());
		stateProvince.sendKeys("Uttar Pradesh");
		action.moveToElement(stateProvince).perform();

		// Fill Zip/Postal Code
		WebElement zipPostalCode = driver.findElement(By.xpath("(//input[@type='text'])[8]"));
		zipPostalCode.sendKeys("201301");

		// Fill Country
		WebElement country = driver.findElement(By.xpath("(//input[contains(@type,'text')])[9]"));
		country.sendKeys("India");
		
		
		// Fill currentEmployer
		WebElement currentEmployer = driver.findElement(By.xpath("(//input[contains(@type,'text')])[10]"));
		currentEmployer.sendKeys(sheet.getRow(0).getCell(4).getStringCellValue());

	

		
		
		// Fill currentCTC
		WebElement currentCTC = driver.findElement(By.xpath("(//input[@type='text'])[12]"));
		currentCTC.sendKeys(String.valueOf((long) sheet.getRow(0).getCell(5).getNumericCellValue()));
		// Fill expectetCTC
		WebElement expectetCTC = driver.findElement(By.xpath("(//input[contains(@type,'text')])[13]"));
		expectetCTC.sendKeys(String.valueOf((long) sheet.getRow(0).getCell(6).getNumericCellValue()));
		// Fill reasonforchange
		WebElement reasonforchange = driver.findElement(By.xpath("(//input[@type='text'])[15]"));
		reasonforchange.sendKeys(sheet.getRow(0).getCell(7).getStringCellValue());
		// Fill whyJoinNumadic
		WebElement whyJoinNumadic = driver.findElement(By.xpath("(//input[contains(@type,'text')])[16]"));
		whyJoinNumadic.sendKeys(sheet.getRow(0).getCell(8).getStringCellValue());
		// Fill LinkedIn URL
		WebElement LinkedIn  = driver.findElement(By.xpath("(//input[contains(@type,'text')])[17]"));
		LinkedIn.sendKeys(sheet.getRow(0).getCell(10).getStringCellValue());

		
		// Educational Details
		WebElement educationalDetails = driver.findElement(By.xpath("(//a[@class='tabular-group-add'][normalize-space()='Add'])[1]"));
		educationalDetails.click();
		WebElement institute = driver.findElement(By.xpath("//lyte-input[@id='53264000000201044_1_tab']//input[contains(@type,'text')]"));
		institute.sendKeys("NIET");
		WebElement department = driver.findElement(By.xpath("(//input[contains(@type,'text')])[18]"));
		department.sendKeys("Computer Science");
		WebElement degree = driver.findElement(By.xpath("(//input[contains(@type,'text')])[19]"));
		degree.sendKeys("B-Tech");

		

		
		// Experience Details
		WebElement experienceDetails = driver.findElement(By.xpath("//div[@class='crc-form-row Experience_Details crc-section-53264000000002559_53264000000201078 crc-form-sec ']//div[@class='wbf-doublewrapper wbf-tabular-true wbf-attachment-']//a[1]"));
		experienceDetails.click();
		WebElement experienceTitle = driver.findElement(By.xpath("//lyte-input[@id='53264000000201080_1_tab']//input[contains(@type,'text')]"));
		experienceTitle.sendKeys("Senior QA Engineer");
		WebElement company = driver.findElement(By.xpath("//lyte-input[@id='53264000000201082_1_tab']//input[contains(@type,'text')]"));
		company.sendKeys("Avis E Solutions");
		WebElement summary = driver.findElement(By.xpath("//textarea[contains(@autocomplete,'off')]"));
		summary.sendKeys("Senior QA Engineer");
		WebElement check = driver.findElement(By.xpath("//lyte-checkbox[@id='53264000000201088_1_tab']//span[@class='lyteCheckBoxDefault']"));
	    check.click();

		
		//Upload resume
		WebElement uploadField = driver.findElement(By.xpath("(//lyte-file-message[contains(@class,'lyteFileUpdMsgWrap')])[2]"));
		uploadField.click();
	    uploadField.sendKeys("C:\\Users\\hp\\eclipse-workspace\\NumadicTest\\src\\test\\resources\\excel\\Saksham_Chaudhary.pdf");

	    //click submit button
	    WebElement submit = driver.findElement(By.xpath("//lyte-yield[normalize-space()='Submit Application']"));
	    submit.click();
	      

   
        // Capture screenshot
	    File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File("C:\\Users\\hp\\eclipse-workspace\\NumadicTest\\src\\test\\resources\\excel\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //Test Cases
     // Negative test case 1: Empty value
        driver.findElement(By.xpath("(//input[contains(@type,'text')])[1]")).clear();
        String errorMessage = driver.findElement(By.xpath("//span[normalize-space()='First Name cannot be empty.']")).getText();
        if (errorMessage.equals("First Name cannot be empty")) {
            System.out.println("Test case 1: Empty value - Passed");
        } else {
            System.out.println("Test case 1: Empty value - Failed");
        }

    // Negative test case 2: Special characters
       driver.findElement(By.xpath("//span[normalize-space()='First Name cannot be empty.']")).sendKeys("!@#$%^&*");
       driver.findElement(By.id("submit-button")).click();
       errorMessage = driver.findElement(By.id("error-message")).getText();
       if (errorMessage.equals("Special characters are not allowed")) {
           System.out.println("Test case 2: Special characters - Passed");
       } else {
           System.out.println("Test case 2: Special characters - Failed");
       }
		

		driver.quit();
	}
}
