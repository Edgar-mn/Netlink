# -*- coding: utf-8 -*-
"""
Created on 17/10/2024
test4
@author: Edgar
"""
from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys
from PIL import Image
import pyautogui

def main():
    driver = webdriver.Chrome()
    driver.get('https://paymentpreval-sandbox.netlink-testlabs.com/')
    time.sleep(2)
    submit = driver.find_element("xpath", '//*[@id="qsLoginBtn"]')
    submit=submit.click()
    time.sleep(2)
    email = driver.find_element("xpath", '//*[@id="username"]')
    email.send_keys('monitor@netlink-testlabs.com')
    password = driver.find_element("xpath", '//*[@id="password"]')
    password.send_keys('Monitor@123')
    time.sleep(2)
    submit = driver.find_element("xpath", '/html/body/div[1]/main/section/div/div/div/form/div[2]/button')
    submit.click()
    time.sleep(2)
    submit = driver.find_element("xpath", '//*[@id="page"]/div[2]/table/tbody/tr[1]/td[1]/a/span')
    submit.click()
    time.sleep(2)
    inputElement = driver.find_element("xpath", '//*[@id="correlationIdentifier"]')
    inputElement.send_keys('test')
    time.sleep(2)
    driver.find_element("xpath", '//*[@id="swhqbebb"]').click()
    time.sleep(2)
    inputElement = driver.find_element("xpath", '//*[@id="creditorAccount"]')
    inputElement.send_keys('7892368367')
    time.sleep(2)
    inputElement = driver.find_element("xpath", '//*[@id="creditorName"]')
    inputElement.send_keys('Sandeep')
    inputElement = driver.find_element("xpath", '//*[@id="creditorAgent"]')
    inputElement.send_keys('RZIPZHCQ')
    driver.find_element("xpath", '//*[@id="page"]/div/div[3]/table/tbody/tr[7]/td[2]/label[1]').click()
    inputElement = driver.find_element("xpath", '//*[@id="uetr"]')
    inputElement.send_keys('dd3027ea-9460-480c-8ef4-0aed819a5ce8')
    submit = driver.find_element("xpath", '//*[@id="page"]/div/div[3]/table/tbody/tr[9]/td[1]/input')
    submit.click()
    image = pyautogui.screenshot()
    image1 = pyautogui.screenshot("image1.png")
    time.sleep(10)
if __name__ == '__main__':
    main()
