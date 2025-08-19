# Selenium WebDriver Quick Notes

## 1. Selenium Locators (Fastest to Slowest)
1. **id**
2. **name**
3. **className**
4. **tagName**
5. **linkText**
6. **partialLinkText**
7. **cssSelector**
8. **xpath**

- Use `id` whenever possible for best speed and reliability.
- `cssSelector` is generally faster than `xpath` and more robust across browsers.
- `xpath` is the most flexible but can be slow and brittle.

## 2. To verify if an image is displayed
```java
WebElement img = driver.findElement(By.id("logo"));
if (img.isDisplayed()) {
    System.out.println("Image is visible");
}
```
- The `isDisplayed()` method checks if a web element is visible on the page.

## 3. Difference: findElement vs findElements
- `findElement(By locator)`
  - Returns the **first** matching WebElement.
  - Throws `NoSuchElementException` if not found.
- `findElements(By locator)`
  - Returns a **List** of all matching WebElements (empty list if none found).
  - Useful for counting elements, e.g., total links:

```java
List<WebElement> links = driver.findElements(By.tagName("a"));
System.out.println("Total links: " + links.size());
```

## 4. For `<a>` tag, use LinkText and partialLinkText
- `By.linkText("Exact Link Text")` — matches anchor tags with exact visible text.
- `By.partialLinkText("Partial Text")` — matches anchor tags containing the given substring.

```java
WebElement link1 = driver.findElement(By.linkText("Home"));
WebElement link2 = driver.findElement(By.partialLinkText("Sam"));
```

## 5. Types of Waits (with Syntax)

### Wait Methods & Synchronization

**Synchronization** is crucial in Selenium to handle timing issues between your script and the web page loading.

**Common Exceptions:**
- `NoSuchElementException`: Element is not present on the page (Synchronization issue)
- `ElementNotFoundException`: Locator is incorrect

### Thread.sleep(time in ms)
```java
Thread.sleep(5000); // waits for 5 seconds
```

**Advantages:**
1) Simple to implement and understand

**Disadvantages:**
1) Causes test failures if elements take longer to load than expected
2) Always waits for the full duration, wasting time and slowing down test execution
3) Using multiple sleep statements significantly increases overall test runtime

### 1. Implicit Wait
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

**Advantages:**
1) Set once and applies to all element searches automatically
2) Waits only as long as needed - stops immediately when element is found
3) Works globally for all findElement operations
4) Simple one-line configuration

**Disadvantages:**
1) Still causes test failures if the timeout duration is insufficient for slow-loading elements

### 2. Explicit Wait
Works based on time and condition.

**Declaration & Use:**
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someId")));
```

**Advantages:**
1) Waits for specific conditions to be met, making tests more reliable
2) Can wait for complex conditions like element visibility, clickability, or text presence
3) Intelligently waits until the condition is satisfied before proceeding
4) More robust and flexible than implicit waits

**Disadvantages:**
1) Requires separate wait statements for each element that needs explicit waiting

**Common Explicit Wait Conditions:**
- `alertIsPresent()`
- `elementSelectionStateToBe()`
- `elementToBeClickable()`
- `elementToBeSelected()`
- `frameToBeAvailableAndSwitchToIt()`
- `presenceOfAllElementsLocatedBy()`
- `presenceOfElementLocated()`
- `textToBePresentInElement()`
- `textToBePresentInElementLocated()`
- `textToBePresentInElementValue()`
- `titleIs()`
- `titleContains()`
- `visibilityOf()`
- `visibilityOfAllElements()`
- `visibilityOfAllElementsLocatedBy()`
- `visibilityOfElementLocated()`

### 3. Fluent Wait
```java
Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofSeconds(2))
    .ignoring(NoSuchElementException.class);

WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
    public WebElement apply(WebDriver driver) {
        return driver.findElement(By.id("foo"));
    }
});
```

### Page Load Timeout
```java
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
```
Sets maximum time to wait for a page to load completely.
## 6. Handling Dynamic Attributes in XPath
If an element's attribute (like `id`) changes dynamically (e.g., a button displays 'start' and then 'stop'), you can use XPath with logical operators or functions:

```java
//button[@name='start' or @name='stop']
// Or using wildcard for any tag:
//*[@name='start' or @name='stop']

// Using contains(): matches if name contains 'st'
//button[contains(@name,'st')]

// Using starts-with(): matches if name starts with 'st'
//*[starts-with(@name,'st')]
```
## 7. Locate Element by Class and Text Value (XPath with contains and text comparison)
Suppose you want to locate a `<span>` element with a class containing 'something' and its text value is greater than 100 (e.g., text is 124.00):

```java
//span[contains(@class,'something') and number(text()) > 100]
```

- `contains(@class,'something')` matches any span whose class attribute includes 'something'.
- `number(text()) > 100` ensures the text content is a number greater than 100.

This XPath will select:
```html
<span class="price something">124.00</span>
```
but not
```html
<span class="price something">99.99</span>
```
## 8. getWindowHandle vs getWindowHandles

**Practice Website:** `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`

- `getWindowHandle()` returns the handle (ID) of the current window as a String.
- `getWindowHandles()` returns a Set of Strings for all open windows/tabs.

### Example: Compare Title and Close a Particular Window
```java
Set<String> handles = driver.getWindowHandles();
for (String handle : handles) {
    driver.switchTo().window(handle);
    if (driver.getTitle().equals("Target Window Title")) {
        driver.close(); // Close the window with matching title
        break; // Optional: stop after closing
    }
}
```

### Practical Example: Closing Specific Browser Windows
```java
package day28;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClosingSpecificBrowserWindow {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        
        // Click link that opens new window
        driver.findElement(By.xpath("//a[normalize-space()='OrangeHRM, Inc']")).click();
        
        // Get all window handles
        Set<String> windowIDs = driver.getWindowHandles();

        for (String winid : windowIDs) {
            String title = driver.switchTo().window(winid).getTitle();
            // Alternative: String url = driver.switchTo().window(winid).getCurrentUrl();
            
            System.out.println(title);
            
            // Close specific windows based on title
            if (title.equals("Human Resources Management Software | OrangeHRM") || 
                title.equals("some other title")) {
                driver.close();
            }
        }
    }
}
```

**Key Points:**
- Use `getWindowHandles()` to get all open windows/tabs
- Switch to each window using `switchTo().window(handle)`
- Get window title or URL to identify the specific window
- Use `close()` to close the current window
- Use multiple conditions (`||`) to close windows with different titles
## 9. Conditional Methods: isDisplayed(), isEnabled(), isSelected()

**Practice Website:** `https://demo.nopcommerce.com/register`

These methods help verify the state of web elements:

- `isDisplayed()`: Checks if an element is visible on the page.
- `isEnabled()`: Checks if an element is enabled (can be interacted with).
- `isSelected()`: Checks if an element (checkbox/radio) is selected.

### Example Usage
```java
WebDriver driver = new ChromeDriver();
driver.get("https://demo.nopcommerce.com/register");
driver.manage().window().maximize();

// isDisplayed()
WebElement logo = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
System.out.println("Display status of logo: " + logo.isDisplayed()); // true

boolean status = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
System.out.println("Display status: " + status);

// isEnabled()
boolean enabledStatus = driver.findElement(By.xpath("//input[@id='FirstName']")).isEnabled();
System.out.println("Enable status: " + enabledStatus); // true

// isSelected()
WebElement male_rd = driver.findElement(By.xpath("//input[@id='gender-male']"));
WebElement female_rd = driver.findElement(By.xpath("//input[@id='gender-female']"));

System.out.println("Before selection...............");
System.out.println(male_rd.isSelected()); // false
System.out.println(female_rd.isSelected()); // false

System.out.println("After selecting male...");
male_rd.click(); // select male radio button
System.out.println(male_rd.isSelected()); // true
System.out.println(female_rd.isSelected()); // false

System.out.println("After selecting female...");
female_rd.click(); // select female radio button
System.out.println(male_rd.isSelected()); // false
System.out.println(female_rd.isSelected()); // true

boolean newsletterStatus = driver.findElement(By.xpath("//input[@id='Newsletter']")).isSelected();
System.out.println("Newsletter checkbox status: " + newsletterStatus); // true
```

Use these methods to validate element states in your Selenium tests.

## 10. driver.get() vs driver.navigate().to()

### driver.get()
- Accepts only **String** parameter (URL as text)
- Simple method for navigating to a webpage

```java
driver.get("https://www.google.com");
```

### driver.navigate().to()
- Accepts both **String** and **URL object** (java.net.URL)
- More flexible navigation method

```java
// Using String
driver.navigate().to("https://www.google.com");

// Using URL object
import java.net.URL;
URL url = new URL("https://www.google.com");
driver.navigate().to(url);
```

**Key Difference:** `navigate().to()` provides more flexibility by accepting URL objects, while `get()` only works with string URLs.

### Additional Navigation Methods
```java
// Navigate back to previous page (browser back button)
driver.navigate().back();

// Navigate forward to next page (browser forward button)  
driver.navigate().forward();

// Refresh/reload the current page (F5 or Ctrl+R)
driver.navigate().refresh();
```

These methods simulate browser navigation actions and are useful for testing multi-page workflows.

## 11. Handling Checkboxes in Selenium

**Practice Website:** `https://itera-qa.azurewebsites.net/home/automation`

- **Select a specific checkbox:**
```java
driver.findElement(By.xpath("//input[@id='monday']")).click();
```

- **Count total checkboxes:**
```java
List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
System.out.println("Total number of checkboxes: " + checkboxes.size());
```

- **Select all checkboxes:**
```java
for(WebElement chkbox : checkboxes) {
    chkbox.click();
}
```

- **Select last N checkboxes:**
```java
for(int i = checkboxes.size() - N; i < checkboxes.size(); i++) {
    checkboxes.get(i).click();
}
```

- **Select first N checkboxes:**
```java
for(int i = 0; i < N; i++) {
    checkboxes.get(i).click();
}
```

- **Uncheck all selected checkboxes:**
```java
for(WebElement chkbox : checkboxes) {
    if(chkbox.isSelected()) {
        chkbox.click();
    }
}
```

**Concepts:**
- Use `findElements` to get all checkboxes as a list
- Use loops to select/unselect based on index or condition
- Use `isSelected()` to check if a checkbox is checked
- Use concise snippets to retain the concept, not full code

## 12. Handling Dropdowns in Selenium

### Dropdown with `<select>` tag
**Practice Website:** `https://phppot.com/demo/jquery-dependent-dropdown-list-countries-and-states/`

- **Create Select object:**
```java
WebElement drpCountryEle = driver.findElement(By.xpath("//select[@id='country-list']"));
Select drpCountry = new Select(drpCountryEle);
```

- **Select options:**
```java
drpCountry.selectByVisibleText("USA");
drpCountry.selectByValue("4");  // if value attribute exists
drpCountry.selectByIndex(3);    // by index position
```

- **Get all options:**
```java
List<WebElement> options = drpCountry.getOptions();
System.out.println("Total options: " + options.size());
```

- **Print all options:**
```java
for(WebElement op : options) {
    System.out.println(op.getText());
}
```

### Dropdown without `<select>` tag (Custom dropdowns)
**Practice Website:** `https://www.jquery-az.com/boots/demo.php?ex=63.0_2`

- **Click to open dropdown:**
```java
driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click();
```

- **Get all options:**
```java
List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class,multiselect)]//label"));
```

- **Select specific options:**
```java
for(WebElement option : options) {
    String text = option.getText();
    if(text.equals("Java") || text.equals("Python")) {
        option.click();
    }
}
```

**Key Differences:**
- Select tag: Use `Select` class methods
- Custom dropdowns: Use `findElements` and click manually
- Custom dropdowns require opening first, then selecting options

## 13. Handling Alerts in Selenium

**Practice Website:** `https://the-internet.herokuapp.com/javascript_alerts`

- **Normal alert with OK button:**
```java
driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click();
driver.switchTo().alert().accept();
```

- **Confirmation alert (OK & Cancel):**
```java
driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();
driver.switchTo().alert().accept();   // click OK
driver.switchTo().alert().dismiss();  // click Cancel
```

- **Prompt alert (Input box):**
```java
driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click();
Alert myalert = driver.switchTo().alert();
System.out.println("Alert text: " + myalert.getText());
myalert.sendKeys("John");
myalert.accept();
```

- **Handle alert with explicit wait:**
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
Alert myalert = wait.until(ExpectedConditions.alertIsPresent());
myalert.accept();
```

## 14. Handling Frames/iFrames

**Practice Website:** `https://ui.vision/demo/webtest/frames/`

- **Switch to frame by WebElement:**
```java
WebElement frame1 = driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
driver.switchTo().frame(frame1);
driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Welcome");
```

- **Switch back to main content:**
```java
driver.switchTo().defaultContent();
```

- **Switch to inner frame (nested frame):**
```java
driver.switchTo().frame(0);  // by index
```

- **Other ways to switch frames:**
```java
driver.switchTo().frame("frameName");    // by name
driver.switchTo().frame("frameId");      // by id
driver.switchTo().frame(1);              // by index
```

## 15. Handling Authentication Popups

**Practice Website:** `http://the-internet.herokuapp.com/basic_auth`

- **Handle basic authentication popup:**
```java
// Syntax: http://username:password@website-url
driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
```

**Concepts:**
- Alerts: Use `switchTo().alert()` for all alert operations
- Frames: Always `switchTo().defaultContent()` to go back to main page
- Authentication: Pass credentials directly in URL