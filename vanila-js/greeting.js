const form = document.querySelector(".js-form"),
  input = form.querySelector("input"),
  greeting = document.querySelector(".js-greetings");

const USER_LS = "currentUser",
  SHOWING_CN = "showing";

//이름 저장하기
function saveName(text) {
  localStorage.setItem(USER_LS, text);
}

function handleSubmit(event) {
  // 기본 동작 막기 (엔터 치면 새로고침 되는거)
  event.preventDefault();
  const currentValue = input.value;
  paintGreeting(currentValue);
  // 이름 저장하기
  saveName(currentValue);
}

//currentuser 없을때 user이름 요청
function askForName() {
  //what is your name? input칸 보여주기
  form.classList.add(SHOWING_CN);
  //뭔가를 form에  submit하면 handlesubmit함수 실행
  form.addEventListener("submit", handleSubmit);
}

function paintGreeting(text) {
  // 텍스트 색칠할거면 폼을 숨기자
  form.classList.remove(SHOWING_CN);
  // greeting을 보여준다.
  greeting.classList.add(SHOWING_CN);
  // 내가 보낸 텍스트 넣는다.
  greeting.innerText = `Hello ${text}`;
}

// 이름 불러오기
// localStorage에서 우리가 key를 주면 value얻게
function loadName() {
  const currentUser = localStorage.getItem(USER_LS);
  // 유저가 없는 경우
  if (currentUser === null) {
    askForName();
    // 유저가 있는 경우
  } else {
    paintGreeting(currentUser);
  }
}

function init() {
  loadName();
}

init();
