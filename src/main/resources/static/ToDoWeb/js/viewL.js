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
        console.log('Fetch Error :-S', err);
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
        viewBtn.href="viewTasks.html?"+lists.id;
        deleteBtn.onclick = function(){
            deleteList(lists.id);
            return false;
        };
        artic.appendChild(title);
        artic.appendChild(deleteBtn);
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
        .then(res => res.json())
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });

        location.reload();
    }
