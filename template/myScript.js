var testCase = document.getElementsByClassName("test-case");

for (i = 0; i < testCase.length; i++) {
    testCase[i].getElementsByTagName('button')[0].addEventListener("click", function() {
        this.classList.toggle("active");

        var panel = this.parentNode.childNodes;

        for (j = 5; j < panel.length; j=j+2) {

            if (panel[j].style.display === "block") {
                panel[j].style.display = "none";
            } else {
                panel[j].style.display = "block";
            }
        }
    });
}

var step = document.getElementsByClassName("step");

for (i = 0; i < step.length; i++) {
    step[i].getElementsByTagName('button')[0].addEventListener("click", function() {
        this.classList.toggle("active");

        var panel = this.parentNode.childNodes;

        for (j = 5; j < panel.length; j=j+2) {

            if (panel[j].style.display === "block") {
                panel[j].style.display = "none";
            } else {
                panel[j].style.display = "block";
            }
        }
    });
}

var action = document.getElementsByClassName("action");

for (i = 0; i < action.length; i++) {
    action[i].getElementsByTagName('button')[0].addEventListener("click", function() {
        this.classList.toggle("active");

        var panel = this.parentNode.childNodes;

        for (j = 5; j < panel.length; j=j+2) {

            if (panel[j].style.display === "block") {
                panel[j].style.display = "none";
            } else {
                panel[j].style.display = "block";
            }
        }
    });
}

var buttons = document.getElementsByTagName('button');

for (i=0; i < buttons.length; i++){

    buttons[i].addEventListener("click", function() {

        if(this.className == "active")
            this.innerText = "-"
        else 
            this.innerText = "+"
    });
}