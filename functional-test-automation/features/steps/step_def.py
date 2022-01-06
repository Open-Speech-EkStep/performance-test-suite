from behave import *
import features.vars.connection as vars
import utils.file_utils as fileUtils
from clients.inference_api_client import InferenceAPIClient

inferenceAPIClient = InferenceAPIClient()

@given('initialize api urls')
def initialization(context):
    inferenceAPIClient.set_base_url(vars.inference_url)
    print('Something is happening' + " || "+ vars.inference_url)



@when('"{audio_uri}" audio is converted to "{output_text_lang}"')
def step_impl(context, audio_uri, output_text_lang):
    payload = fileUtils.read_json_file("features/test_data/payloads/inference_api_payload.json")
    payload["config"]["language"]["value"] = output_text_lang
    payload["audio"][0]["audioUri"] = audio_uri
    print(payload)
    response = inferenceAPIClient.convert_audio(payload, output_text_lang)
    print("\tINFO: hiting api ", response)
    print(response["output"])
    context.actualText = response["output"][0]["source"].strip()




@then('verify "{output_result}"')
def step_impl(context, output_result):
    expectedText = fileUtils.read_file("features" + output_result).strip()

    assert context.actualText == expectedText