from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service

# Create an instance of ChromeOptions
options = Options()

# Specify the path to the Chrome profile directory
options.add_argument(r"--user-data-dir=C:\Users\user\AppData\Local\Google\Chrome\User Data")
options.add_argument(r"--profile-directory=Default")
# Specify the path to your ChromeDriver executable
service = Service(r"C:\Users\user\Downloads\chromedriver-win64\chromedriver-win64\chromedriver.exe")

# Create the WebDriver instance using the Service object
driver = webdriver.Chrome(service=service, options=options)
# Specify the path to your ChromeDriver executable
#driver = webdriver.Chrome(executable_path=r"C:\Users\user\Downloads\chromedriver-win64\chromedriver-win64\chromedriver.exe", options=options)

# Open a webpage using the specified profile
driver.get("https://www.hdvox.com")

# Perform any additional actions with the driver
# ...

# Close the browser
driver.quit()