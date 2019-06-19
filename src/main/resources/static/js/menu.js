$(document).ready(function () {
    setNavigation();
});

function setNavigation() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);
    $(".menu a").each(function () {
        if (!$(this).hasClass("fastblog")) {
            var href = $(this).attr('href');
            if (path.indexOf(href) !== -1) {
                $(this).closest('a').addClass('active');
                document.title = $(this).attr('data-title');
            }
        }
    });
}