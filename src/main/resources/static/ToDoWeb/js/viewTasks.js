    const params = new URLSearchParams(window.location.search);

    for(let param of params){
        console.log("List id: ",param);
        let id = param[0];
        console.log("this is the id: ",id);
        getTheList(id);
    }

    function getTheList(id){

        fetch("http://localhost:8901/todolists/view/"+id)
            .then(
                function(response){
                    if(response.status !== 200){
        
                        console.log('Looks like something went wrong' + response.status);
                        return;
                    }
        
                    response.json().then(function(data){
                        console.log(data);
        
                        let tdlist = document.querySelector('p.lTitle');
                        let title = document.createElement("h2");
                        title.innerHTML = data.title;
                        tdlist.appendChild(title);

                        let table = document.querySelector("table");

                        showHeader(table,data);
                        showTasks(table,data);
        
                    });
        
                }
            )
            .catch(function(err){
                console.log('Fetch failed:', err);
            });
    }

    function showHeader(table, data){
        let tableHead = table.createTHead();
        let row = tableHead.insertRow();
        for(let keys of Object.keys(data.tasks[0])){
            if(keys === "tasks"){

            }else{
                let th = document.createElement("th");
                let text = document.createTextNode(keys);
                th.appendChild(text);
                row.appendChild(th);
            }
        }

        let th2 = document.createElement("th");
        let text2 = document.createTextNode("View");
        th2.appendChild(text2);
        row.appendChild(th2);
    }

    function showTasks(table,data){

        for(let d of data.tasks){
            let row = table.insertRow();
            console.log(d);
            for(let values in d){
                let cell = row.insertCell();
                let text = document.createTextNode("");
                if (values === "isDone"){
                    if(d[values] === true){
                        text = document.createTextNode("DONE");
                    }else{

                    }
                }else{
                    console.log("my values:",d[values]);
                    text = document.createTextNode(d[values]);
                }
                cell.appendChild(text);
            }
            let newCell = row.insertCell();
            let myViewButton = document.createElement("a");
            myViewButton.innerHTML = "View";
            myViewButton.className = "btn btn-danger";
            myViewButton.href = "#"+d.id;
            newCell.appendChild(myViewButton);
        }

    }