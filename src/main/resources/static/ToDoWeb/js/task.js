    const params = new URLSearchParams(window.location.search);

    for(let param of params){
        let id = param[0];
        getTheTask(id);

    }

    function getTheTask(id){

        fetch('http://localhost:8901/tasks/view/'+id)
        .then(
            function(response){
                if(response.status !== 200){

                    console.log('Looks like something went wrong' + response.status);
                    return;
                }

                response.json().then(function(task){

                    viewTask(task);
                });

            }
        )
        .catch(function(err){
            console.log('Fetch failed:', err);
        });

    }

    function viewTask(task){
        console.log("id: ",task.id,",name: ",task.name,"done? ",task.isDone);
        document.getElementById("id").value=task.id;
        document.getElementById("name").value=task.name;
        document.getElementById("description").value=task.description;
        document.getElementById("importance").value=task.importance;
        if(task.isDone){
            document.getElementById("done").checked=true;
        }else{
            document.getElementById("notDone").checked=true;
        }
    }

    function updateClick(){
    
        let formElements = document.querySelector("form.theTask").elements;
        let tId = formElements["id"].value;
        let tName = formElements["name"].value;
        let tDescription = formElements["description"].value;
        let tImportance = formElements["importance"].value;
        let tDone = formElements["done"].value;
        let isDone = "";
        if(tDone){
            isDone = true;
        }else{
            isDone = false;
        }
        updateList(tId, tName, tDescription, tImportance, isDone);
    }

    function updateList(tId, tName, tDescription, tImportance, isDone){

        fetch("http://localhost:8901/tasks/update/"+lid, {
            method: "put",
            headers: {
                "Content-type": "application/json"
            },
            body: json = JSON.stringify({
            "id": tId,
            "name": tName,
            "description": tDescription,
            "importance": tImportance,
            "isDone": isDone,
            "list":{
                "id": 1
            }
            })
        })
        .then(json)
        .then(function(data){
            console.log('Request succeeded with JSON response', data)
            location.reload();
        })
        .catch(function (err){
            console.log('Request faild:', err)
        });
    }
