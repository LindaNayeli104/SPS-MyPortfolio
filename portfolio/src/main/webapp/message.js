/** Fetches the current message from the server and adds it to the page. */
async function showServerMessage() {
  const responseFromServer = await fetch('/message');
  const textFromResponse = await responseFromServer.text();

  const messageContainer = document.getElementById('message-container');
  messageContainer.innerText = textFromResponse;
}
