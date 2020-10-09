    const params = new URLSearchParams(window.location.search);

    for(let param of params){
        console.log("List id: ",param);
        let id = param[1];
        console.log(id);
        getTheList(id);
    }

    function getTheList(id){

        fetch("http://localhost:8901/todolists/read/"+id)
            .then(
                function(response){
                    if(response.status !== 200){
        
                        console.log('Looks like something went wrong' + response.status);
                        return;
                    }
        
                    response.json().then(function(data){
                        console.log(data);
        
                        document.getElementById("listTitle").value=data.title;
        
                    });
        
                }
            )
            .catch(function(err){
                console.log('Fetch failed:', err);
            });
        }