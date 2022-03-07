# Performance Test Suite

Repository for performance testing includes Jmeter ` .jmx ` files for all types of load testing as well as code for streaming client.

## Streaming Client

### Pre-requisites

- Install node on your MAC OS machine, run this command : `brew install node@16`
- Verify the installation using this command : `node --version`

### Getting Started

- After node is installed , you can put the audio(.wav) file on which you want to run the test inside the streaming folder.
- As of now we are using `audiosample.wav` as the audio file.
- The configuration variables like streaming_server_url, audio_file_path, timeout are available in the `streaming/config.js`. Please do configure as per your requirements.
- Once you are ready with the configurations, go to location using the command : `cd ./streaming/src/`.
- To do a sample run, execute the following command :

  ```sh
  lang=en node application.js <absolute path to audio .wav file>
  ```

**Note:** `lang` param is used to define the audio language for which you want to run the test suite.

In logs you should be able to see input audio split into `5 chunks` (as per the current audio file and config) and its respective responses. Verify that the `Execution time` given in logs is equal to the audio duration.

### Configuration settings

- If you wish to change the audio file, you need to change the chunk size and timeout in config file (`streaming/config.js`).
- Logical explanation is explained in the config file.

## How to run Performance test suite for Streaming Client with Jmeter

### Jmeter Pre-requisites

- Install `java - 8+` on your machine
- Refer doc `https://www.java.com/en/download/help/mac_install.html`
- Install `jre and Jdk both (8+)`
- Install jmeter from ` https://jmeter.apache.org/download_jmeter.cgi `

### How to Run with Streaming with jmeter

- `jmeter` is available as an executable file in `bin` folder of the installed / downloaded location.
- Open jmeter GUI and open file `jmeter/Streaming_Client_ASR.jmx`
- If you wish to change the users , ramp up time, test duration etc you can do that in thread group `Streaming Client -en`.
- You need to provide the command in the OS Sampler, `English -10users` and you need to provide the correct directory path of `application.js` as per the folder structure on your machine.
- Once the setup is done, you can run it by clicking on the run button.
- You can see the report in the aggregate Report after the test.

### How to Run other suites using Jmeter

- `Open Jmeter` on your machine
- For ASR Batch API - use `ASR.jmx`.
- For TTS API - use `TTS.jmx`.
- You can configure the URLs , Total Test time, ramp up time in Jmeter on `Thread Group` of all languages.
- You can enable and disable the languages by `right click --> Enable/disable`.
- Once you are ready you can click on the run button to execute the test.
- You can see the report in the aggregate Report after the test.