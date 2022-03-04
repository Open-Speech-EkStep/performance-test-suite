const {io} = require('socket.io-client');
const SocketStatus = require('./socket-status');
const {WaveFile} = require("wavefile");
const con = require('../config.js');
const fs = require("fs");

class Socket_Client {
    constructor(socketURL, transcription_language) {
        this.socketURL = socketURL;
        this.transcription_language = transcription_language;
        this.socket = io(this.socketURL, {
            // path: '/',
            autoConnect: false,
            withCredentials: false,
            reconnectionAttempts: 5,
            query: `language=${this.transcription_language}`,
            transports: [ "websocket" ]
        });
        // this.socket.connect();
    }
    isSocketConnected = false;

    connectSocket (onSuccessCallback = () => {}, responseCallback = () => {},endedCallback = () => {}) {
        console.log(this.socketURL,this.transcription_language)
        console.log("Connecting to socket", typeof this.socket)
        this.socket.connect();
        const _this = this;

        _this.socket.on('connect', function () { // EVENT EMITTER this
            console.log("Hi Connected to socket", _this.socket.id)
            _this.socket.emit('connect_mic_stream');
            console.log("Connected")
        });

        _this.socket.on('connect-success', function (data) {
            console.log("Connection success", _this.socket.id)
            onSuccessCallback(SocketStatus.CONNECTED, _this.socket.id)
            _this.isSocketConnected = true;
        });

        _this.socket.on('response', function (data, language) {
            console.log("response called")
            if (language === "en-IN") data = data.toLowerCase();
            responseCallback(data)
        })

        _this.socket.on('disconnect', function () {
            console.log("disconnect called")
            // _this.disconnect(endedCallback);
            _this.isSocketConnected = false;
        })

        _this.socket.on('terminate', function () {
            console.log("terminate called")
            onSuccessCallback(SocketStatus.TERMINATED, _this.socket.id);
            _this.disconnect(endedCallback);

        });

        _this.socket.on('abort', function () {
            console.log("ABORTED due to user limit")
        });
        return _this.socket;
    }
    disconnect (endedCallback = () => {}) {
        let id = this.socket.id
        this.socket.disconnect();
        endedCallback()
    }
    startStreaming = (beforeEmitCallback = () => {}) => {
        const WaveFile = require('wavefile').WaveFile;
        let wav = new WaveFile(fs.readFileSync(con.configr.audio_path));

        wav.toSampleRate(16000);
        let wavBuffer = wav.toBuffer();
        console.log(wavBuffer.length)

        var i,j, wavBuff, chunk = con.configr.chunksize, k=1;
        const _this = this;
        for (i = 0,j = wavBuffer.length; i + chunk < j; i += chunk) {
            console.log("value of i:", i, k)
            setTimeout(function(i){
                var temp = wavBuffer.slice(i, i + chunk);
                console.log(_this.socket.id + " is emiting...",i, (i+chunk))
                beforeEmitCallback()
                _this.socket.emit('mic_data', temp, _this.transcription_language, true, false);
                _this.socket.emit('mic_data', null, _this.transcription_language, false, false);
            }, con.configr.timeout * k,i);
            k++;
        }
        setTimeout(function () {
            if(i != 0 && i != chunk) {
                //i = i - chunk
                wavBuff = wavBuffer.slice(i, wavBuffer.length);
                console.log("value remains:", i, wavBuffer.length)
                _this.socket.emit('mic_data', wavBuff, _this.transcription_language, true, false);
            }
            beforeEmitCallback()
            _this.socket.emit('mic_data', null, _this.transcription_language, false, false);
            _this.socket.emit('mic_data', null, _this.transcription_language, false, true);

        },1800 * k);
    };
}

module.exports = {Socket_Client};