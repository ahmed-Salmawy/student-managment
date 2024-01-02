const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/mywhoosh-websocket?token=Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzAzNzkwMDIyLCJleHAiOjE3MDM4NzY0MjJ9.0o2F7J9onWq-iY49Ng4BZQnLc2wMnj8xY35ybuXoW1s',
    connectHeaders: {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzAzNzkwMDIyLCJleHAiOjE3MDM4NzY0MjJ9.0o2F7J9onWq-iY49Ng4BZQnLc2wMnj8xY35ybuXoW1s"
    },
    debug: function (str) {
        console.log(str);
    },
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/students', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function addStudent() {
    stompClient.publish({
        destination: "/app/student",
        body: JSON.stringify({
            'name': $("#name").val(),
            'rollNumber': $("#rollNumber").val(),
            'fatherName': $("#fathersName").val(),
            'grade': $("#grade").val()
        })
    });
}

function saveResult() {
    stompClient.publish({
        destination: "/app/result",
        body: JSON.stringify({
            'totalMarks': $("#totalMarks").val(),
            'obtainedMarks': $("#obtainedMarks").val(),
            'rollNumber': $("#resultRollNumber").val()
        })
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#addStudent").click(() => addStudent());
    $("#saveResult").click(() => saveResult());
});

