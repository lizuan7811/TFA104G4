const FB_appID = '245269857723664';
var custFBServletPath = "/customer/CustomerFBServlet";
var regisPath = "/customer/customerRegister.jsp";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var customerFBServlet = "https://" + window.location.host + webCtx + custFBServletPath;
var regisPage = "https://" + window.location.host + webCtx + regisPath;

// Initiate FB SDK
window.fbAsyncInit = function () {
    FB.init({
        appId: FB_appID,
        cookie: true, // Enable cookies to allow the server to access the session.
        xfbml: true, // Parse social plugins on this webpage.
        version: "v12.0", // Use this Graph API version for this call.
    });
};


// Load the SDK Asynchronously
(function (d, s, id) {
    var js,
        fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
})(document, "script", "facebook-jssdk");


function statusChangeCallback(response) {
    // Called with the results from FB.getLoginStatus().
    console.log("statusChangeCallback");
    // console.log(response);                   // The current login status of the person.
    if (response.status === "connected") {
        // Logged into your webpage and Facebook.
        console.log("connected");
        getUserData();
    } else {
        // Not logged into your webpage or we are unable to tell.
        console.log("Not logged in");
        login();
    }
}

function checkLoginState() {
    // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function (response) {
        // See the onlogin handler
        statusChangeCallback(response);
    });
}

function login() {
    FB.login(
        function (response) {
            if (response.authResponse) {
                // connected
                getUserData();
            } else {
                // cancelled
                console.log("user cancelled login through facebook");
            }
        },
        { scope: "email" }
    );
}

// Get user data and save to local storage
function getUserData() {
    FB.api("/me?fields=id,name,email,picture.width(300).height(300)", function (response) {
        localStorage.setItem("fbAcc", "true");
        localStorage.setItem("fbIdToken", response.id);
        localStorage.setItem("name", response.name);
        localStorage.setItem("email", response.email);
        localStorage.setItem("profic", JSON.stringify(response.picture));
        window.location.href = regisPage;
        showUserData();
    });
}

// Show user data in customerRegister.jsp
function showUserData() {
    document.getElementById("name").value = localStorage.getItem("name");
    document.getElementById("email").value = localStorage.getItem("email");
    document.getElementById("fbAcc").value = localStorage.getItem("fbAcc");
    document.getElementById("fbIdToken").value = localStorage.getItem("fbIdToken");
    var profic = JSON.parse(localStorage.getItem("profic"));
    var proficURL = profic.data.url;
    document.getElementById("imagePreview").setAttribute("src", proficURL);
};
