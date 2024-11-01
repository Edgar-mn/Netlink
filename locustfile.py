from locust import HttpUser, task, between
import matplotlib.pyplot as plt


class WebsiteUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def index(self):
        self.client.get("/")

    @task
    def about(self):
        self.client.get("/swift-alliance-connect-virtual-acv/")
        

if __name__ == "__main__":
    WebsiteUser().run()
    
    

print("Content-type: text/html\n\n")
print("<html><body>")
print("<h1>Hello, World!</h1>")
print("</body></html>")