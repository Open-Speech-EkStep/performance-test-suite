# performance-test-suite
Repository for performance testing includes Jmeter `.jmx ` files for all types of load testing as well as code for streaming client. <br>

## Streaming Client :
<br>
<br>

### Pre Requisites :
 - Install node on your machine <br>
    run this command : `brew install node@16` <br>
    verify using this command ; `node --version` <br>

### Streaming Client Performance Run steps :
- After node is installed , you can put the audio(.wav) file on which you want to run the test inside the streaming folder <br>
- As of now we are using `audiosample.wav` as the audio file <br>
- All the configs like url , audio file path , timeout are available in the streaming/config.js <br>

- Once you are ready with the configs and other things, go to location `./streaming/src`  <br>
- to try the sample run :  <br> <br>


`lang=en node application.js /Users/amulya.ahuja/Downloads/performance_CDAC/performance-test-suite/streaming/audiosample.wav  `  <br>
  lang param is used to define the language in which you want to run the test suite. <br>

In logs you should see `5 chunks` (as per the current audio file and config) and response of 5 chunks. Verify the `Execution time ` should be around the length of audio <br>

#  IMP NOTE :
    - IF you wish to change the audio file, you need to change the chunk size and timeout in config file.
    - Logical explanation is explained in the config file.


### How to run Streaming with Jmeter .
 ### Pre Requisites :
 - Install `java - 8+` on your machine <br>
 - Refer doc `https://www.java.com/en/download/help/mac_install.html`
 - Install `jre and Jdk both (8+)`
 - Install jmeter from ` https://jmeter.apache.org/download_jmeter.cgi `

### How to Run with Streaming with jmeter :<br>
- jmeter is available as an executable file  in bin folder of the installed / downloaded location <br>
- open jmeter GUI and open file `/jmeter/Streaming_Client_ASR.jmx`  <br>
- If you wish to change the users , ramp up time, test duration etc you can do that in thread group `Streaming Client -en` <br>
- You need to provide the command in the OS Sampler, `English -10users` and you need to provide the correct directory path of `application.js` as per the folder structure on your machine <br>
- Once the setup is done , you can run it by clicking on the run button. <br>
- You can see the report in the aggregate Report after the test. <br>
    


## How to Run other suites using Jmeter :
- `Open Jmeter` on your machine
- For ASR Batch API - use `ASR.jmx` <br>
- For TTS API - use `TTS.jmx` <br>
- You can configure the URLs , Total Test time, ramp up time in Jmeter on `Thread Group` of all language. <br>
- You can enable and disbale the languages by `right click --> Enable/disable` <br>
- Once you are ready you can click on the run button to execute the test. <br>
- You can see the report in the aggregate Report after the test. <br>
