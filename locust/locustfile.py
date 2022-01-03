from locust import HttpUser, TaskSet, task, between
import json
import time
from json import JSONDecodeError
class UserBehavior(TaskSet):
    @task
    def create_post(self):
        headers = {'content-type': 'application/json'}
        body1 = {"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]}
        body = json.dumps({"audio": [{"audioUri": "https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav"}]})
        with self.client.post('/asr/v1/recognize/en', data = body, headers = headers,catch_response=True) as response:
            try:
                if ((response.json()["status"] == "SUCCESS") and (response.status_code == 200)):
                    response.success()
                else:
                    response.failure("Response code:" + str(response.status_code) +" And Response:" +str(response.json()))
            except JSONDecodeError:
                response.failure("Response could not be decoded as JSON " + "Response code:" + str(response.status_code))
            except KeyError:
                response.failure("Response did not contain expected key 'status' " + "Response code:" + str(response.status_code))

        # headers=headers,
        # name = "Create a new post")
#
class WebsiteUser(HttpUser):
    tasks = [UserBehavior]
    # wait_time = between(5, 10)
#         response = self.client.post("/post/endpoint", data=json.dumps(payload), headers=headers)