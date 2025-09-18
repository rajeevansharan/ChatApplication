var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/message', function (message) {
        var messages = document.getElementById('messages');
        var li = document.createElement('li');
        li.appendChild(document.createTextNode(message.body));
        messages.appendChild(li);
    });
});

function sendMessage() {
    var message = document.getElementById('messageInput').value;
    stompClient.send("/app/chat", {}, message);
}


