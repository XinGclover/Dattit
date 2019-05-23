var allDadsURL = "http://localhost:8080/dad/getAll";
var allPostsURL = "http://localhost:8080/post/getAll";
var top10Posts = "http://localhost:8080/post/getTop10";

getAllDads(allDadsURL);
listPosts(top10Posts);
//            listAllPosts(allPostsURL);

function listPosts(url) {
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(request.responseText);
            var main = document.getElementById("main");
            for (var post in data) {

                var postItem = document.createElement('div');
                postItem.setAttribute('class', 'mb-3');
                postItem.innerHTML = `<div class="row no-gutters">
                        <div class="col-md-1 bg-light text-center">
                            <a href=""> <i class="fas fa-chevron-up"></i></a> <br>
                            ` + countVotes(data[post].votes) + `<br>
                            <a href=""> <i class="fas fa-chevron-down"></i></a>
                        </div>
                        <div class="col-md-11">
                            <div class="card-body">
                                <p class="card-header bg-white pt-0"><a href="">r/`
                        + printCategories(data[post].categories)
                        + `</a> • Posted by u/`
                        + data[post].dad.username + ` `
                        + data[post].created + `
                    <h5 class="card-title">` + data[post].headline + `</h5>
                            <p class="card-text">` + data[post].content + `
                            </p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                    </div>
                    </div>
                        `;
                main.appendChild(postItem);
            }
        } else {
            alert('Some undefined error while reading from the funny dad server!');
        }
    };

    request.onerror = function () {
        alert('Error connecting to server!');
    };
    request.send();
}

function countVotes(votes) {
    var currentVotes = 0;

    for (var i in votes) {
        currentVotes += votes[i].vote;
    }
    return currentVotes;
}

function printCategories(list) {
    var allCategories = "";
    for (var i in list) {
        if (list[i].name !== undefined) {
            allCategories += list[i].name + ", ";
        }
    }
    return allCategories.substring(0, allCategories.length - 2);
}

function getAllDads(url) {
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(request.responseText);
            var allDads = document.getElementById("allDads");
            var newList = document.createElement('ul');
            allDads.appendChild(newList);
            allDads.style.listStyle = 'none';
            for (var dad in data) {
                var newItem = document.createElement('li');
                newItem.innerHTML = "<a href='http://localhost:8080/post/" + data[dad].id + " '> " + data[dad].username + "</a>";
                allDads.appendChild(newItem);
            }
        } else {
            alert('Some undefined error while reading from the funny dad server!');
        }
    };

    request.onerror = function () {
        alert('Error connecting to server!');
    };
    request.send();
}

function createNewDadAccount() {
    var url = 'http://localhost:8080/dad/newDad';
    var formData = JSON.stringify($("#myForm").serializeArray());
    fetch(url, {
        method: 'POST',
        body: formData,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});