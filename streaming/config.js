var p = process.argv[2];

const configr = {
"chunksize" : 60000,  // chunk size decides the number of chunks you want your audio to break into . In this case it will create a chunk of 60000 bytes
"timeout" : 1800 ,  // This helps you to configure your test to real time streaning scenario, For eg: you have 5 chunks and the audio length is 9 sec, so to ensure the timelines of 9sec/5 chunks = 1.8 sec . 
// this application will send one chunk and then wait for 1.8 sec and then repeat 5 times for 9 sec of audio

"audio_path" : p,

//"audio_path" : '/Users/amulya.ahuja/Downloads/performance_CDAC/performance-test-suite/streaming/cutafew_converted.wav',
// "transcript_json_path" : '/Users/amulya.ahuja/Downloads/performance_CDAC/performance-test-suite/streaming/audio_transcript.json',
"url" : 'https://meity-dev-asr.ulcacontrib.org'
}

module.exports = {configr};