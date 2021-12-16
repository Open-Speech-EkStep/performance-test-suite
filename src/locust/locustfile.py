from locust import HttpUser, TaskSet, task, between
import json
import time

class UserBehavior(TaskSet):
    @task
    def create_post(self):
        headers = {'content-type': 'application/json','Accept-Encoding':'gzip'}
        body = json.dump({"audio": [{"audioUri": "https://storage.googleapis.com/test_public_bucket/download.wav"}]})
        self.client.post('/en', data = body, headers=headers)
        # headers=headers,
        # name = "Create a new post")
#
class WebsiteUser(HttpUser):
    tasks = [UserBehavior]
    wait_time = between(5, 10)
#         response = self.client.post("/post/endpoint", data=json.dumps(payload), headers=headers)