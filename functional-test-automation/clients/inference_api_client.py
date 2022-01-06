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

    def convert_audio(self, payload, lang):
        headers = {'content-type': 'application/json', 'charset': 'utf-8'}
        print(self.base_url)
        return requests.post(self.base_url + "/" + lang, data=json.dumps(payload), headers=headers).json()

