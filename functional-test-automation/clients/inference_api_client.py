import requests
import json
import time
from requests import Response
import features.vars.connection as vars
from simplejson.errors import JSONDecodeError

from utils.Singleton import Singleton


class InferenceAPIClient(Singleton):
    def __init__(self):
        self.token = None
        self.base_url = ""

    def set_base_url(self, url):
        self.base_url = url

    def convert_audio(self, payload):
        headers = {'content-type': 'application/json', 'charset': 'utf-8'}
        return requests.post(self.base_url, data=json.dumps(payload), headers=headers).json()

