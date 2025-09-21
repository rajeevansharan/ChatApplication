# WebSocket with Redis Pub/Sub

This project demonstrates a simple **real-time chat application** using **Spring Boot WebSockets** with **Redis Pub/Sub** integration.  
It allows messages to be broadcast across multiple server instances, making it scalable for distributed environments.

---

## 🚀 Features

- WebSocket communication with **STOMP protocol**
- Redis **Pub/Sub** for cross-server message broadcasting
- Real-time chat messaging between multiple clients
- Easy to scale horizontally (multiple app instances)
- Simple configuration for local development or Docker-based Redis

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot** (WebSocket, Data Redis)
- **Redis** (Pub/Sub)
- **Maven** (build tool)

---

```
## 📂 Project Structure
src/main/java/com/example/websocket
│
├── config
│ ├── WebSocketConfig.java # WebSocket + STOMP configuration
│ ├── RedisConfig.java # Redis connection configuration
│ └── RedisMessageSubscriberConfig.java # Redis listener setup
│
├── controller
│ └── ChatController.java # Handles incoming WebSocket messages
│
├── service
│ ├── ChatPublisher.java # Publishes messages to Redis
│ └── ChatSubscriber.java # Listens to Redis messages
│
└── Application.java # Main Spring Boot application
```

### Start Redis
Make sure Redis is running locally or via Docker:
```bash
docker run -d -p 6379:6379 redis
```

Build and Run the App
```bash
mvn spring-boot:run
```


The app will start on:

```bash
http://localhost:8080
```


### WebSocket Endpoints

WebSocket Endpoint:
```bash
ws://localhost:8080/ws
```
Client → Server: 
```bash
/app/chat
```
Server → Clients (broadcast):
```bash
/topic/messages
```


### 🧪 Testing the App
You can test with any WebSocket client (e.g., Postman, websocat, or a simple HTML frontend).

Example Frontend (HTML + SockJS + StompJS)
```html
<!DOCTYPE html>
<html>
<head>
  <title>WebSocket Chat</title>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
</head>
<body>
  <h2>WebSocket Chat with Redis</h2>
  <input type="text" id="msgInput" placeholder="Type a message..." />
  <button onclick="sendMessage()">Send</button>
  <ul id="messages"></ul>

  <script>
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      stompClient.subscribe("/topic/messages", (msg) => {
        const li = document.createElement("li");
        li.innerText = msg.body;
        document.getElementById("messages").appendChild(li);
      });
    });

    function sendMessage() {
      const message = document.getElementById("msgInput").value;
      stompClient.send("/app/chat", {}, message);
      document.getElementById("msgInput").value = "";
    }
  </script>
</body>
</html>
```



🌍 Scaling Across Multiple Servers

Run multiple instances of this application (e.g., with different ports: server.port=8081, server.port=8082).

Connect them to the same Redis server.

Messages published on one instance will be received and broadcast by all others.













