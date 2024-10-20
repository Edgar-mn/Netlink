from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options


# Path to your Chrome profile and ChromeDriver
profile_path = r"C:\Users\user\AppData\Local\Google\Chrome\User Data"  # Update your username
profile_name = "Default"
chromedriver_path = r"C:\Users\user\Downloads\chromedriver-win64\chromedriver-win64\chromedriver.exe"  # Update this to the path where chromedriver.exe is located
# Set up Chrome options
options = webdriver.ChromeOptions()
options.add_argument(f"--user-data-dir={profile_path}")
options.add_argument(f"--profile-directory={profile_name}")
options.add_experimental_option("detach", True)

# Set up the Service with the path to chromedriver.exe
service = Service(executable_path=chromedriver_path)

# Initialize the WebDriver with the specified service and options
driver = webdriver.Chrome(service=service, options=options)

# Open a website to test
driver.get("https://www.youtube.com")
driver.maximize_window()