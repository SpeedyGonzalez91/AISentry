function queryAssistant() {
    const queryInput = document.getElementById("query-input");
    const responseText = document.getElementById("response-text");

// Get the user query from the input field
    const query = queryInput.value;


// Load the JSON file
    fetch("/data/response.json")
    .then(response => response.json())
    .then(data => {
// Check if the query exists in the JSON data

    for (let i = 0; i < data.length; i++) {
    const question = data[i]["question"];
    const answer = data[i]["answer"];

    if (question !== query) {
} else {
    responseText.textContent = answer;
    return;
}
}

    responseText.textContent = "Sorry, I couldn't find a response for that query.";
})
    .catch(error => {
    console.error(error);
    responseText.textContent = "Error: Failed to get response.";
});

// Clear the input field
    queryInput.value = "";
}
