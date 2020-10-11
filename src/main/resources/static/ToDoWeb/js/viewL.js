fetch('http://localhost:8901/todolists/viewAll')
    .then(
        function(response){
            if(response.status !== 200){

                console.log('Looks like something went wrong' + response.status);
                return;
            }

            response.json().then(function(listData){

                showLists(listData);
            });

        }
    )
    .catch(function(err){
        console.log('Fetch failed:', err);
    });

    function allMyLists(lists) {
        let tdlists = document.querySelector('p.tdlists');
        let artic = document.createElement("article");
        let title = document.createElement("h3");
        title.innerHTML = "~ "+lists.title;
        let viewBtn = document.createElement("a");
        viewBtn.innerHTML=("View this list")
        viewBtn.className= ("btn")
        let deleteBtn = document.createElement("button");
        deleteBtn.innerHTML=("Delete list");
        deleteBtn.className= ("btn");
        let updateBtn = document.createElement("button");
        updateBtn.innerHTML=("Update list");
        updateBtn.className= ("btn");
        viewBtn.href="viewTasks.html?"+lists.id;
        deleteBtn.onclick = function(){
            deleteList(lists.id);
            return false;
        };
        updateBtn.onclick = function(){
            let show = document.querySelector("form.updatelist");
            if (show.style.display === "none") {
                show.style.display = "block";
            } else {
                show.style.display = "none";
            }
            document.getElementById("lID").value=lists.id;
            document.getElementById("lTitle").value=lists.title;
        };
        artic.appendChild(title);
        artic.appendChild(deleteBtn);
        artic.appendChild(updateBtn);
        artic.appendChild(viewBtn);
        tdlists.appendChild(artic);
    }
    
    function showLists(data) {
      for (let element of data) {
        allMyLists(element);
      }
    }

    function deleteList(id) {
        fetch("http://localhost:8901/todolists/delete/"+id, {
          method: "DELETE",
          headers: {
            "Content-type": "application/json"
          },
        })
        .then(json)
        .catch(function (err) {
            console.log('Request faild:', err);
        });
        location.reload();
    }

    document.querySelector("form.updatelist").addEventListener("submit",function(stop){
        stop.preventDefault();
    
        let formElements = document.querySelector("form.updatelist").elements;
        let lID = formElements["lID"].value;
        let lTitle = formElements["lTitle"].value;
        updateList(lID, lTitle);
    });

    function updateList(lid, ltitle){

        fetch("http://localhost:8901/todolists/update/"+lid, {
            method: "put",
            headers: {
                "Content-type": "application/json"
            },
            body: json = JSON.stringify({
                "id": lid,
                "title": ltitle
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
