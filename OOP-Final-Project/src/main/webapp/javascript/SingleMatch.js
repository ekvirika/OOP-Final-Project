let selectedQuestion = null;
let selectedAnswer = null;
let colorIndex = 0;

const colors = [
    "#10C969", "#2A7373", "#327D7D", "#3A87B7", "#42B191",
    "#DB6FED", "#E272F0", "#9cea65", "#F093F8", "#F79FF8",
    "#3075C1", "#6c04c2", "#409CD6", "#58A1D6", "#60B6E1",
    "#8FCAB2", "#99D2BA", "#56f106", "#ADE3CA", "#B77CD2",
    "#17EC48", "#2E5FE8", "#45B8E8", "#5BCBEF", "#72D7F5",
    "#ea3d27", "#FEA8E5", "#042498", "#FECBEB", "#FED8F1",
    "#fc6916", "#fad20b", "#FC9240", "#FCAABF", "#FCA0A9"
];

function selectQuestion(id) {
    selectedQuestion = document.getElementById(id);
    checkMatch();
}

function selectAnswer(id) {
    selectedAnswer = document.getElementById(id);
    checkMatch();
}

function checkMatch() {

    if (selectedQuestion && selectedAnswer) {
        colorIndex = Math.ceil(Math.random()*colors.length);
        selectedQuestion.style.backgroundColor = colors[colorIndex];
        selectedAnswer.style.backgroundColor = colors[colorIndex];
        selectedQuestion.style.borderColor = colors[colorIndex];
        selectedAnswer.style.borderColor = colors[colorIndex];
        selectedQuestion = null;
        selectedAnswer = null;
    }
}
