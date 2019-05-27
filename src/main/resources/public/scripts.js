var allDadsURL = "http://localhost:8080/dad/getAll";
var allPostsURL = "http://localhost:8080/post/getAll";
var top10Posts = "http://localhost:8080/post/getTop10";
var categoryUrl = "http://localhost:8080/post/category/getCategoryByName";

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
                                <p class="card-header bg-white pt-0">r/`
                        + printCategories(data[post].categories)
                        + ` • Posted by u/`
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
            allCategories += "<a href='" + categoryUrl + "/" + list[i].name + "'>" + list[i].name + "</a>, ";
            console.log(list[i].name);
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
    var formData = JSON.stringify($("#createDadForm").serializeArray());
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

function createFormForPost() {
    var a = document.getElementById('createNewPostForm');
    if (a.style.display === 'none') {
        a.style.display = "block";
    } else {
        a.style.display = 'none';
    }
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});




function userLogin() {
    
    var login = {"username": $("#login_username").val(), "password": $("#login_password").val()};
    $.ajax({
        url: '/dad/login',
        type: 'POST',
        data: JSON.stringify(login),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var moderator = Object.values(data)[2];
//            console.log(data);
            sessionStorage.setItem("username", JSON.stringify(data.username));
            sessionStorage.setItem("moderator", JSON.stringify(data.moderator));
//            console.log(sessionStorage.getItem("username"));
//            if (moderator == true) {
//                console.log("admin")
//
//
//
//            } else
//                console.log("dad");
        },
        error: function (responseTxt, statusTxt, errorThrown) {
            console.log(errorThrown);
        },
    });
}

console.log(sessionStorage.getItem("username"));


function searchPostsbyString() {
    event.preventDefault();
    var searchString = $("#form-control").val();
    $.ajax({
        url: '/post/search',
        type: 'POST',
        data: {str: searchString},
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        success: function (data) {
//            emptyForm();
            buildForm(data);
            console.log(sessionStorage.getItem("username"));
        },
        error: function (responseTxt, statusTxt, errorThrown) {
            console.log(errorThrown);
        },
    });
}

function buildForm(data) {
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
                                <p class="card-header bg-white pt-0">r/`
                + printCategories(data[post].categories)
                + ` • Posted by u/`
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
}

function emptyForm() {
    var main = document.getElementById("main");
    main.innerHTML = "";
}
