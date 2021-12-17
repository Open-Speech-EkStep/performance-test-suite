const SocketStatus = require('./socket-status');
const {connectSocket, disconnect} = require('./socket-client');
const fs = require("fs");
let end = false;
var lang = 'en'
var transcriptJson
const responseNumber = new Map();
var fail = 0
var pass = 0

var startTime
var endTime

const startStreaming = (socket) => {
    const WaveFile = require('wavefile').WaveFile;
    var fs = require('fs');
    let wav = new WaveFile(fs.readFileSync("/Users/pankaj/gitAll/performance-test-suite/cutafew_converted.wav"));

    wav.toSampleRate(16000);
    let wavBuffer = wav.toBuffer();
    console.log(wavBuffer.length)

    var i,j, wavBuff, chunk = 30000, k=1;

    for (i = 0,j = wavBuffer.length; i < j; i += chunk) {
        console.log("value of i:", i, k)
        setTimeout(function(i){
            var temp = wavBuffer.slice(i, i + chunk);
            // console.log('emiting...',i, (i+chunk))
            socket.emit('mic_data', temp, lang, true, false);
            socket.emit('mic_data', null, lang, false, false);
        }, 1000 * k,i);
        k++;
    }
   setTimeout(function () {
       if(i != 0 && i != chunk) {
           i = i - chunk
           wavBuff = wavBuffer.slice(i, wavBuffer.length);
           //console.log("value remains:", i, wavBuffer.length)
           socket.emit('mic_data', wavBuff, lang, true, false);
       }
       socket.emit('mic_data', null, lang, false, false);
       socket.emit('mic_data', null, lang, false, true);
       end  = true;

   },1000 * k);
};

function sleep(delay) {
    var start = new Date().getTime();
    while (new Date().getTime() < start + delay);
}
const onConnectSuccess = (action, userid, socket) => {
    if (action === SocketStatus.CONNECTED) {
        console.log(userid);
        startTime = new Date().getTime();
        startStreaming(socket);

    } else if (action === SocketStatus.TERMINATED) {
        // Socket is closed and punctuation can be done after it.
        console.log("Connection closed")
    } else {
        //unexpected failures action on connect.
        console.log("Action", action, id);
    }
};

const onResponse = (data, socket, id) => {
    //response data;

    if(responseNumber.has(id) == false){
        responseNumber.set(id,1)
    } else {
        responseNumber.set(id, responseNumber.get(id) + 1)
    }
    respo_num = responseNumber.get(id)
    respo_index = "respo_"+respo_num
    console.log("Response:", id," ", respo_num," ", data);
    if(transcriptJson[0]["respo_"+respo_num] == data){
        pass = pass + 1
    } else {
        fail = fail + 1
    }
    // disconnect(socket);
}

const ended = (id) => {
    endTime = new Date().getTime();
    var time = endTime - startTime;
    console.log('Execution time: ' + time);
    result = id + "," + time + "," + pass + "," + fail + "," + startTime + "," + endTime + "\n"
    fs.appendFileSync("/Users/pankaj/performaceResult/result_default.csv",result)
}

const simulateUsers = (numberOfUsers) => {
    var fs = require('fs');
    var jsonFile = fs.readFileSync("/Users/pankaj/gitAll/performance-test-suite/audio_transcript.json")
    transcriptJson = JSON.parse(jsonFile)


    for (i = 0; i < numberOfUsers; i++)
    {
        console.log("language ",lang)

        connectSocket('https://meity-dev-asr.ulcacontrib.org', lang, onConnectSuccess, onResponse, ended)
        //startStreaming(socket);
    }
}
simulateUsers(20)