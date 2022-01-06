@smoke
Feature: API speech to text conversion test
  @smoke1
  Scenario Outline: API is able to convert speech to text
    Given initialize api urls
    When "<audio_uri>" audio is converted to "<output_text_lang>"
    Then verify "<output_result>"
    Examples:
      | audio_uri                                                          | output_text_lang    | output_result             |
#      | https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav       | hi                  | /test_data/hindi/cutafew.txt   |
      | https://storage.googleapis.com/test_public_bucket/download.wav      | en                  | /test_data/english/cutafew.txt                |
#      | https://www2.engr.arizona.edu/~429rns/audiofiles/cutafew.wav       | gu                  | /test_data/gujarati/cutafew.txt                |