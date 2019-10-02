function getDslValue() {
    console.info(editor.getValue());
    return editor.getValue()
}

function updateDsl() {
    var payload = {
        code: getDslValue()
    };

    fetch("/run", {
        method: "post",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    }).then(function (response) {
        console.info(response.status);
        response.text().then(function (text) {
            document.getElementById("output").textContent = text;
        });
    })
}
