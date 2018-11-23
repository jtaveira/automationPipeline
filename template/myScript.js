var acc = document.getElementsByClassName("test-case");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");

        var panel = this.childNodes;
        for (j = 1; j < panel.length; j++) {
            console.log(panel[j])
            if (panel[j].style.display === "block") {
                panel[j].style.display = "none";
            } else {
                panel[j].style.display = "block";
            }
        }
    });
}