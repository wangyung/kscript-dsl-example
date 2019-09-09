function getDslValue() {
    console.info(editor.getValue());
    return editor.getValue()
}

function updateDsl() {
    var payload = {
        code: getDslValue()
    };

    fetch("/update", {
        method: "post",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    }).then(function (response) {
        console.info(response.status);
        document.getElementById("extensionUrl").textContent = getUrl()
    })
}
