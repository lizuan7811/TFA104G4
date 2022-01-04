var custServletPath = "/customer/CustomerServlet";
var loginPath = "/customer/customerLogin.jsp";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var customerServlet = "https://" + window.location.host + webCtx + custServletPath;
var loginPage = "https://" + window.location.host + webCtx + loginPath;


// logout
var logout = document.getElementById("logout_a");
logout.onclick = function () {
    confirm("確認登出？");
    if (confirm) {
        sessionStorage.removeItem('idCustomer');
        $.ajax({
            url: customerServlet,
            method: "POST",
            dataType: "json",
            data: { "action": "logout" },
            success: function (data) {
                // console.log("data = ", data);      
                window.location.replace(loginPage);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log("error");
                console.log(xhr);
                console.log(textStatus);
                console.log(errorThrown);
            }
        })
    }
};


