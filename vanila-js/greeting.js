const form = document.querySelector(".js-form"),
  input = form.querySelector("input"),
  greeting = document.querySelector(".js-greetings");

const USER_LS = "currentUser",
  SHOWING_CN = "showing";

function paintGreeting(text) {
  // 텍스트 색칠할거면 폼을 숨기자
  form.classList.remove(SHOWING_CN);
  greeting.classList.add(SHOWING_CN);
  greeting.innerText = `Hello ${text}`;
}

// localStorage에서 우리가 key를 주면 value얻게
function loadName() {
  const currentUser = localStorage.getItem(USER_LS);
  // 유저가 없는 경우
  if (currentUser === null) {
    // 유저가 있는 경우
  } else {
    paintGreeting(currentUser);
  }
}

function int() {
  loadName();
}

init();
