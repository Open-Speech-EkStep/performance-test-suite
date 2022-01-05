const SocketStatus = require('./socket-status');
let {Socket_Client} = require('./socket-client');
const fs = require("fs");
var lang = 'en'
var transcriptJson

class UserProcessor {
    emitNumber;
    failCounter;
    passCounter;
    startTime;
    endTime;
    startTimeBeforeEmit;
    endTimeAfterResponse;
    latency = new Map();
    socket_id;
    socket_client;


    constructor(socket_client) {
        this.socket_client = socket_client;
        this.emitNumber = new Map();
        this.failCounter = 0;
        this.passCounter = 0;
    }

    onConnectSuccess = (action, id) => {
        if (action === SocketStatus.CONNECTED) {
            console.log("this.startTime:")
            console.log(this.startTime)
            this.startTime = new Date().getTime();
            this.socket_id = id;
            this.socket_client.startStreaming(this.beforeEmit);

        } else if (action === SocketStatus.TERMINATED) {
            console.log("Connection closed")
        } else {
            console.log("Action", action, id);
        }
    }
    beforeEmit = () => {
        this.startTimeBeforeEmit = new Date().getTime();
    }
    onResponse = (data) => {
        this.endTimeAfterResponse = new Date().getTime();
        if (this.emitNumber.has(this.socket_id) == false) {
            this.emitNumber.set(this.socket_id, 1)
        } else {
            this.emitNumber.set(this.socket_id, this.emitNumber.get(this.socket_id) + 1)
        }
        let _latency = this.endTimeAfterResponse - this.startTimeBeforeEmit;
        console.log("startTimeBeforeEmit:" + this.startTimeBeforeEmit + " endTimeAfterResponse:" + this.endTimeAfterResponse + " _latency:" + _latency)
        if (this.latency.has(this.socket_id) == false) {
            this.latency.set(this.socket_id, _latency)
        } else {
            this.latency.set(this.socket_id, this.latency.get(this.socket_id) + _latency)
        }
        let respo_index = "respo_" + this.emitNumber.get(this.socket_id)
        console.log("Response for user:", this.socket_id, " ", respo_index, " ", data);
        if (transcriptJson[0][respo_index] == data) {
            this.passCounter = this.passCounter + 1
        } else {
            this.failCounter = this.failCounter + 1
        }
    }
    ended = () => {
        this.endTime = new Date().getTime();
        let time_diff = this.endTime - this.startTime;
        console.log('Execution time: ' + time_diff);
        let result = this.socket_id + "," + time_diff + "," + this.passCounter + "," + this.failCounter + "," + this.startTime + "," + this.endTime + "," + [...this.latency.entries()] + "\n"
        console.log(result)
        fs.appendFileSync("./performaceResult/result.csv", result)
    }
}
const simulateUsers = (numberOfUsers) => {
    var fs = require('fs');
    var jsonFile = fs.readFileSync("./audio_transcript.json")
    transcriptJson = JSON.parse(jsonFile)

    let socket_client;
    let user;
    for (i = 0; i < numberOfUsers; i++) {
        console.log("language ", lang)
        socket_client = new Socket_Client('https://meity-dev-asr.ulcacontrib.org', lang)
        user = new UserProcessor(socket_client)
        socket_client.connectSocket(user.onConnectSuccess, user.onResponse, user.ended)
        //startStreaming(socket);
    }
}
simulateUsers(1)