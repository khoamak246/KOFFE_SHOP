
$(document).ready(function () {
    let currentLocation = window.location.pathname;
    let navLinkArr = ["dashboard", "user", "order", "billing", "product", "comment"];
    $("a").click(function(event) {
        event.preventDefault();
        let href = this.getAttribute("href");
        if (href !== currentLocation) {
            location.href = href;
        }
    });
    for (let i = 0; i < navLinkArr.length; i++) {
        if (currentLocation.includes(navLinkArr[i])) {
            let selectedNavLink = "nav-" + navLinkArr[i];
            $(`#${selectedNavLink}`).addClass("active bg-gradient-primary");
            let pagesName = navLinkArr[i].charAt(0).toUpperCase() + navLinkArr[i].slice(1)
            $(".pageSpecific").html(pagesName)
            break;
        }
    }
})
