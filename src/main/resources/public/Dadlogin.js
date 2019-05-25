$(document).ready(function () {

    $("#login-form").submit(function (event) {

        event.preventDefault();


        var uname = $("login_username").val();
        var psw = $("login_password").val();

        var login = {"username": uname, "password": psw};
        $("#login_button").prop("disabled", true);
        $.ajax({
            url: '/dad/login',
            type: 'POST',
            data: JSON.stringify(login),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            cache: false,
            timeout: 600000,
            success: function (data, textStatus, jqXHR) {
                var json = "<h4>Ajax Response</h4><pre>"
                        + JSON.stringify(data) + "</pre>";
                $('#feedback').html(json);
                console.log("SUCCESS : ", data);

            },
            error: function (jqXhr, textStatus, errorThrown) {
                console.log(errorThrown);
            }

        });
    });

});

