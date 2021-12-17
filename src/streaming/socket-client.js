const {io} = require('socket.io-client');
const SocketStatus = require('./socket-status');


const disconnect = (socket) => socket.disconnect();
const connectSocket = (socketURL, transcription_language, onSuccess = () => {}, responseCallback = () => {},endedCallback = () => {}) => {
    // establish connection
    // emit connect event
    // listen on connect success
    // trigger onSuccess/onError depending on response

    let userId = "";
    let isSocketConnected = false;
    const socket = io(socketURL, {
        // path: '/',
        autoConnect: false,
        withCredentials: false,
        reconnectionAttempts: 5,
        query: `language=${transcription_language}`
    });
    console.log("Connecting to socket")
    socket.connect();
    console.log("Connected to socket", socket.id)
    socket.on('connect', function () {
        userId = socket.id;
        socket.emit('connect_mic_stream');
        console.log("Connected")
    });

    socket.on('connect-success', function (data) {
        console.log("Connection success")
        onSuccess(SocketStatus.CONNECTED, userId, socket)
        isSocketConnected = true;
    });

    socket.on('response', function (data, language) {
        // console.log("response:",data)
        if (language === "en-IN") data = data.toLowerCase();
        responseCallback(data, socket, userId)
    })

    socket.on('disconnect', function () {
        console.log("disconnect")
        disconnect(socket);
        isSocketConnected = false;
        endedCallback(userId)
    })

    socket.on('terminate', function () {
        onSuccess(SocketStatus.TERMINATED, userId, socket);
        disconnect(socket);

    });

    socket.on('abort', function () {
        console.log("ABORTED due to user limit")
    });



    return socket;

}

module.exports = {connectSocket, disconnect};