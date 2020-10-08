document.querySelector("form.newlist").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.newlist").elements;
    let title = formElements["title"].value;
    console.log(title);
    createToDoList(title);
});

function createToDoList(title){

    fetch("http://localhost:8901/todolists/create", {
        method: "post",
        headers: {
            "Content-type": "application/json"
        },
        body: json = JSON.stringify({
            "title": title
        })
    })
    .then(json)
    .then(function(data){
        console.log('Request succeeded with JSON response', data)
        window.location.href = "viewLists.html";
    })
    .catch(function (err){
        console.log('Request faild:', err)
    });
}