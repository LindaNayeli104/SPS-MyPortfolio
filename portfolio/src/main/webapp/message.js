/** Fetches the current message from the server and adds it to the page. */
async function showServerMessage() {
  const responseFromServer = await fetch('/message');
  const ArrayFromResponse = await responseFromServer.json();
  const place = ArrayFromResponse[Math.floor(Math.random() * ArrayFromResponse.length)];
  const arrayElement = document.getElementById('message-container');
  arrayElement.innerHTML = '';
  arrayElement.appendChild(
      createListElement(place));
}


function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
